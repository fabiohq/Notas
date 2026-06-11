package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldCoverConstructorGettersAndSetters() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        properties.setTimeoutMs(500);
        properties.setChecks(List.of(check));

        assertEquals(500, properties.getTimeoutMs());
        assertEquals(List.of(check), properties.getChecks());
    }

    @Test
    void shouldCoverDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthPropertiesApiCheckTest {

    @Test
    void shouldCoverConstructorGettersAndSetters() {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        apiCheck.setName("banks");
        apiCheck.setUrl("http://localhost:8080/health");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 204));

        assertEquals("banks", apiCheck.getName());
        assertEquals("http://localhost:8080/health", apiCheck.getUrl());
        assertFalse(apiCheck.isCritical());
        assertEquals(List.of(200, 204), apiCheck.getAcceptedStatuses());
    }

    @Test
    void shouldCoverDefaultCriticalValue() {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        assertTrue(apiCheck.isCritical());
        assertNull(apiCheck.getAcceptedStatuses());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthIndicatorApiResultTest {

    @Test
    void shouldCoverPrivateApiResultConstructorAndGetters() throws Exception {
        Class<?> clazz = Class.forName(
                "com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability.ExternalApisHealthIndicator$ApiResult"
        );

        Constructor<?> constructor =
                clazz.getDeclaredConstructor(boolean.class, Integer.class, String.class);
        constructor.setAccessible(true);

        Object result = constructor.newInstance(true, 200, null);

        Method isUp = clazz.getDeclaredMethod("isUp");
        Method getHttpStatus = clazz.getDeclaredMethod("getHttpStatus");
        Method getError = clazz.getDeclaredMethod("getError");

        isUp.setAccessible(true);
        getHttpStatus.setAccessible(true);
        getError.setAccessible(true);

        assertEquals(true, isUp.invoke(result));
        assertEquals(200, getHttpStatus.invoke(result));
        assertNull(getError.invoke(result));
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthIndicatorTest {

    @Test
    void shouldReturnUpWhenApiRespondsWithDefault2xxStatus() throws Exception {
        HttpServer server = startServer(200);

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "banks",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    null
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("banks"));

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("banks");

            assertEquals("UP", detail.get("status"));
            assertEquals(200, detail.get("httpStatus"));
            assertEquals(true, detail.get("critical"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnDownWhenCriticalApiRespondsWithNonAcceptedStatus() throws Exception {
        HttpServer server = startServer(500);

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "sanba",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(200)
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.DOWN, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("sanba");

            assertEquals("DOWN", detail.get("status"));
            assertEquals(500, detail.get("httpStatus"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnUpWhenNonCriticalApiIsDown() throws Exception {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(apiCheck(
                "optional-api",
                "http://localhost:1/not-available",
                false,
                List.of(200)
        )));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("optional-api");

        assertEquals("DOWN", detail.get("status"));
        assertEquals(false, detail.get("critical"));
        assertTrue(detail.containsKey("error"));
    }

    @Test
    void shouldReturnDownWhenCriticalApiThrowsException() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(apiCheck(
                "critical-api",
                "http://localhost:1/not-available",
                true,
                List.of(200)
        )));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("critical-api");

        assertEquals("DOWN", detail.get("status"));
        assertTrue(detail.containsKey("error"));
    }

    @Test
    void shouldReturnUpWhenAcceptedStatusesContainsHttpStatus() throws Exception {
        HttpServer server = startServer(204);

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "custom-status-api",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(204)
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("custom-status-api");

            assertEquals("UP", detail.get("status"));
            assertEquals(204, detail.get("httpStatus"));
        } finally {
            server.stop(0);
        }
    }

    private ExternalApisHealthProperties.ApiCheck apiCheck(
            String name,
            String url,
            boolean critical,
            List<Integer> acceptedStatuses
    ) {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        apiCheck.setName(name);
        apiCheck.setUrl(url);
        apiCheck.setCritical(critical);
        apiCheck.setAcceptedStatuses(acceptedStatuses);

        return apiCheck;
    }

    private HttpServer startServer(int statusCode) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);

        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(statusCode, -1);
            exchange.close();
        });

        server.start();

        return server;
    }
}



