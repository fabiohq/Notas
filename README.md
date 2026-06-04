package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.observability;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.test.util.ReflectionTestUtils;

class ExternalApisHealthIndicatorTest {

    @Test
    void healthShouldReturnUpWhenCriticalApiReturns2xxDefaultAcceptedStatus() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-ok", "http://localhost/ok", true, null)
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse<Void> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-ok");
        assertEquals("UP", apiDetail.get("status"));
        assertEquals("http://localhost/ok", apiDetail.get("url"));
        assertEquals(true, apiDetail.get("critical"));
        assertEquals(200, apiDetail.get("httpStatus"));
        assertFalse(apiDetail.containsKey("error"));
    }

    @Test
    void healthShouldReturnDownWhenCriticalApiReturnsNonAcceptedStatus() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-down", "http://localhost/down", true, null)
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse<Void> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-down");
        assertEquals("DOWN", apiDetail.get("status"));
        assertEquals(500, apiDetail.get("httpStatus"));
    }

    @Test
    void healthShouldReturnUpWhenNonCriticalApiIsDown() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-non-critical", "http://localhost/non-critical", false, null)
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse<Void> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-non-critical");
        assertEquals("DOWN", apiDetail.get("status"));
        assertEquals(false, apiDetail.get("critical"));
    }

    @Test
    void healthShouldUseCustomAcceptedStatuses() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-custom", "http://localhost/custom", true, List.of(404))
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse<Void> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(404);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-custom");
        assertEquals("UP", apiDetail.get("status"));
        assertEquals(404, apiDetail.get("httpStatus"));
    }

    @Test
    void healthShouldUseDefaultAcceptedStatusesWhenAcceptedStatusesIsEmpty() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-empty-statuses", "http://localhost/empty", true, List.of())
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);
        HttpResponse<Void> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode()).thenReturn(204);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-empty-statuses");
        assertEquals("UP", apiDetail.get("status"));
        assertEquals(204, apiDetail.get("httpStatus"));
    }

    @Test
    void healthShouldReturnDownAndErrorWhenIOExceptionOccurs() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-io", "http://localhost/io", true, null)
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("connection refused"));

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-io");
        assertEquals("DOWN", apiDetail.get("status"));
        assertEquals("IOException:: connection refused", apiDetail.get("error"));
        assertFalse(apiDetail.containsKey("httpStatus"));
    }

    @Test
    void healthShouldReturnDownAndInterruptThreadWhenInterruptedExceptionOccurs() throws Exception {
        ExternalApisHealthProperties properties = properties(
                api("api-interrupted", "http://localhost/interrupted", true, null)
        );

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        HttpClient httpClient = Mockito.mock(HttpClient.class);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new InterruptedException("interrupted"));

        ReflectionTestUtils.setField(indicator, "httpClient", httpClient);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<String, Object> apiDetail = detail(health, "api-interrupted");
        assertEquals("DOWN", apiDetail.get("status"));
        assertEquals("InterruptedException: interrupted", apiDetail.get("error"));
        assertFalse(apiDetail.containsKey("httpStatus"));
        assertTrue(Thread.currentThread().isInterrupted());

        Thread.interrupted();
    }

    @Test
    void propertiesShouldCoverGettersSettersAndDefaults() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("api");
        check.setUrl("http://localhost");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 201));

        properties.setTimeoutMs(5000);
        properties.setChecks(List.of(check));

        assertEquals(5000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertEquals("api", properties.getChecks().get(0).getName());
        assertEquals("http://localhost", properties.getChecks().get(0).getUrl());
        assertFalse(properties.getChecks().get(0).isCritical());
        assertEquals(List.of(200, 201), properties.getChecks().get(0).getAcceptedStatuses());
    }

    @Test
    void apiCheckShouldHaveCriticalTrueByDefault() {
        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

        assertTrue(check.isCritical());
    }

    @Test
    void apiResultPrivateClassShouldCoverConstructorAndGetters() throws Exception {
        Class<?> clazz = Class.forName(
                "com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.observability.ExternalApisHealthIndicator$ApiResult"
        );

        Constructor<?> constructor = clazz.getDeclaredConstructor(boolean.class, Integer.class, String.class);
        constructor.setAccessible(true);

        Object apiResult = constructor.newInstance(true, 200, "none");

        assertEquals(true, clazz.getDeclaredMethod("isUp").invoke(apiResult));
        assertEquals(200, clazz.getDeclaredMethod("getHttpStatus").invoke(apiResult));
        assertEquals("none", clazz.getDeclaredMethod("getError").invoke(apiResult));
    }

    private ExternalApisHealthProperties properties(ExternalApisHealthProperties.ApiCheck... checks) {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(checks));
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

    @SuppressWarnings("unchecked")
    private Map<String, Object> detail(Health health, String apiName) {
        return (Map<String, Object>) health.getDetails().get(apiName);
    }
}





when(httpClient.send(any(HttpRequest.class),
        org.mockito.ArgumentMatchers.<HttpResponse.BodyHandler<Void>>any()))
        .thenReturn(response);
