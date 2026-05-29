package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldCoverGettersAndSetters() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        apiCheck.setName("api-test");
        apiCheck.setUrl("http://localhost/test");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 201));

        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(apiCheck));

        assertEquals(1000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());

        assertEquals("api-test", properties.getChecks().get(0).getName());
        assertEquals("http://localhost/test", properties.getChecks().get(0).getUrl());
        assertFalse(properties.getChecks().get(0).isCritical());
        assertEquals(List.of(200, 201), properties.getChecks().get(0).getAcceptedStatuses());
    }

    @Test
    void shouldHaveDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisPropertiesApiCheckTest {

    @Test
    void shouldCoverApiCheckGettersAndSetters() {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        apiCheck.setName("parameter-api");
        apiCheck.setUrl("http://localhost:8080/health");
        apiCheck.setCritical(true);
        apiCheck.setAcceptedStatuses(List.of(200, 204));

        assertEquals("parameter-api", apiCheck.getName());
        assertEquals("http://localhost:8080/health", apiCheck.getUrl());
        assertTrue(apiCheck.isCritical());
        assertEquals(List.of(200, 204), apiCheck.getAcceptedStatuses());
    }

    @Test
    void shouldHaveDefaultCriticalTrue() {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        assertTrue(apiCheck.isCritical());
        assertNull(apiCheck.getAcceptedStatuses());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class ExternalApisHealthIndicatorApiResultTest {

    @Test
    void shouldCreateApiResultUsingReflection() throws Exception {
        Class<?> apiResultClass = Class.forName(
                "com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability.ExternalApisHealthIndicator$ApiResult"
        );

        Constructor<?> constructor =
                apiResultClass.getDeclaredConstructor(boolean.class, Integer.class, String.class);

        constructor.setAccessible(true);

        Object apiResult = constructor.newInstance(true, 200, null);

        assertNotNull(apiResult);
        assertEquals(true, getField(apiResult, "up"));
        assertEquals(200, getField(apiResult, "httpStatus"));
        assertEquals(null, getField(apiResult, "error"));
    }

    private Object getField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}
package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import com.sun.net.httpserver.HttpServer;

class ExternalApisHealthIndicatorTest {

    @Test
    void shouldReturnUpWhenApiRespondsSuccessfulStatus() throws Exception {
        HttpServer server = createServer(200);
        server.start();

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "api-ok",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    null
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("api-ok"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnUpWhenAcceptedStatusMatches() throws Exception {
        HttpServer server = createServer(404);
        server.start();

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "api-accepted",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(404)
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnDownWhenCriticalApiFails() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(apiCheck(
                "api-down",
                "http://localhost:1/error",
                true,
                null
        )));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("api-down"));
    }

    @Test
    void shouldReturnUpWhenNonCriticalApiFails() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(apiCheck(
                "api-non-critical",
                "http://localhost:1/error",
                false,
                null
        )));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
    }

    @Test
    void shouldReturnDownWhenStatusIsNotAccepted() throws Exception {
        HttpServer server = createServer(500);
        server.start();

        try {
            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(apiCheck(
                    "api-error",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(200)
            )));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.DOWN, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api-error");
            assertEquals("DOWN", detail.get("status"));
            assertEquals(500, detail.get("httpStatus"));
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

    private HttpServer createServer(int status) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);

        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(status, 0);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write("OK".getBytes());
            }
        });

        return server;
    }
}


