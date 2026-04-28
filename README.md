package com.santander.bnc.bsn049.bncbsn049mscountries.observability;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExternalApisHealthIndicatorTest {

    @Test
    void shouldReturnUpWhenApiReturnsAcceptedStatus() throws Exception {
        ExternalApisHealthProperties properties = properties(api("api-ok", "http://localhost/test", true, List.of(404)));
        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<Void> response = mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(404);
        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-ok");
        assertEquals("UP", detail.get("status"));
        assertEquals(404, detail.get("httpStatus"));
    }

    @Test
    void shouldReturnDownWhenCriticalApiReturnsNotAcceptedStatus() throws Exception {
        ExternalApisHealthProperties properties = properties(api("api-down", "http://localhost/test", true, List.of(200)));
        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<Void> response = mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(500);
        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-down");
        assertEquals("DOWN", detail.get("status"));
        assertEquals(500, detail.get("httpStatus"));
    }

    @Test
    void shouldReturnDownWhenHttpClientThrowsIOException() throws Exception {
        ExternalApisHealthProperties properties = properties(api("api-io", "http://localhost/test", true, null));
        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = mock(HttpClient.class);

        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("connection error"));

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-io");
        assertEquals("DOWN", detail.get("status"));
        assertTrue(detail.get("error").toString().contains("IOException"));
    }

    @Test
    void shouldReturnDownWhenHttpClientThrowsInterruptedException() throws Exception {
        ExternalApisHealthProperties properties = properties(api("api-interrupted", "http://localhost/test", true, null));
        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = mock(HttpClient.class);

        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new InterruptedException("interrupted error"));

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(Thread.currentThread().isInterrupted());

        Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-interrupted");
        assertEquals("DOWN", detail.get("status"));
        assertTrue(detail.get("error").toString().contains("InterruptedException"));

        Thread.interrupted();
    }

    @Test
    void shouldReturnUpWhenNonCriticalApiIsDown() throws Exception {
        ExternalApisHealthProperties properties = properties(api("api-non-critical", "http://localhost/test", false, null));
        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = mock(HttpClient.class);
        HttpResponse<Void> response = mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(500);
        when(httpClient.send(any(), any(HttpResponse.BodyHandler.class))).thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-non-critical");
        assertEquals("DOWN", detail.get("status"));
        assertEquals(false, detail.get("critical"));
    }

    @Test
    void shouldCoverApiResultGettersByReflection() throws Exception {
        Class<?> clazz = Class.forName(
                "com.santander.bnc.bsn049.bncbsn049mscountries.observability.ExternalApisHealthIndicator$ApiResult"
        );

        Constructor<?> constructor = clazz.getDeclaredConstructor(boolean.class, Integer.class, String.class);
        constructor.setAccessible(true);

        Object apiResult = constructor.newInstance(true, 200, "error");

        Method isUp = clazz.getDeclaredMethod("isUp");
        Method getHttpStatus = clazz.getDeclaredMethod("getHttpStatus");
        Method getError = clazz.getDeclaredMethod("getError");

        isUp.setAccessible(true);
        getHttpStatus.setAccessible(true);
        getError.setAccessible(true);

        assertEquals(true, isUp.invoke(apiResult));
        assertEquals(200, getHttpStatus.invoke(apiResult));
        assertEquals("error", getError.invoke(apiResult));
    }

    private ExternalApisHealthProperties properties(ExternalApisHealthProperties.ApiCheck api) {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(api));
        return properties;
    }

    private ExternalApisHealthProperties.ApiCheck api(
            String name,
            String url,
            boolean critical,
            List<Integer> acceptedStatuses
    ) {
        ExternalApisHealthProperties.ApiCheck api = new ExternalApisHealthProperties.ApiCheck();
        api.setName(name);
        api.setUrl(url);
        api.setCritical(critical);
        api.setAcceptedStatuses(acceptedStatuses);
        return api;
    }
}