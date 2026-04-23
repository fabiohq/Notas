package com.santander.bnc.bsn049.bncbsn049mscontracts.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldHandlePropertiesGettersAndSetters() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        properties.setTimeoutMs(5000);

        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();
        apiCheck.setName("banks");
        apiCheck.setUrl("http://localhost:8080/actuator/health");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 404));

        properties.setChecks(new ArrayList<>(List.of(apiCheck)));

        assertEquals(5000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertEquals("banks", properties.getChecks().get(0).getName());
        assertEquals("http://localhost:8080/actuator/health", properties.getChecks().get(0).getUrl());
        assertFalse(properties.getChecks().get(0).isCritical());
        assertEquals(List.of(200, 404), properties.getChecks().get(0).getAcceptedStatuses());
    }

    @Test
    void shouldUseDefaultValuesInApiCheck() {
        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        assertTrue(apiCheck.isCritical());
        assertEquals(null, apiCheck.getAcceptedStatuses());
    }
}




_____________________________


package com.santander.bnc.bsn049.bncbsn049mscontracts.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

class ExternalApisHealthIndicatorTest {

    private static HttpServer server;
    private static String baseUrl;

    @BeforeAll
    static void setUpServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);

        server.createContext("/ok", new FixedStatusHandler(200));
        server.createContext("/not-found", new FixedStatusHandler(404));
        server.createContext("/error", new FixedStatusHandler(500));

        server.start();
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterAll
    static void tearDownServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnUpWhenAllCriticalApisAreUp() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(2000);

        ExternalApisHealthProperties.ApiCheck criticalOk = buildApiCheck(
                "critical-ok",
                baseUrl + "/ok",
                true,
                null
        );

        ExternalApisHealthProperties.ApiCheck nonCritical404Accepted = buildApiCheck(
                "non-critical-404",
                baseUrl + "/not-found",
                false,
                List.of(200, 404)
        );

        properties.setChecks(List.of(criticalOk, nonCritical404Accepted));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, Object> criticalDetails =
                (Map<String, Object>) health.getDetails().get("critical-ok");

        @SuppressWarnings("unchecked")
        Map<String, Object> nonCriticalDetails =
                (Map<String, Object>) health.getDetails().get("non-critical-404");

        assertNotNull(criticalDetails);
        assertNotNull(nonCriticalDetails);

        assertEquals("UP", criticalDetails.get("status"));
        assertEquals(baseUrl + "/ok", criticalDetails.get("url"));
        assertEquals(true, criticalDetails.get("critical"));
        assertEquals(200, criticalDetails.get("httpStatus"));

        assertEquals("UP", nonCriticalDetails.get("status"));
        assertEquals(baseUrl + "/not-found", nonCriticalDetails.get("url"));
        assertEquals(false, nonCriticalDetails.get("critical"));
        assertEquals(404, nonCriticalDetails.get("httpStatus"));
    }

    @Test
    void shouldReturnDownWhenCriticalApiFailsWithDefaultStatusValidation() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(2000);

        ExternalApisHealthProperties.ApiCheck criticalError = buildApiCheck(
                "critical-error",
                baseUrl + "/error",
                true,
                null
        );

        properties.setChecks(List.of(criticalError));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, Object> details =
                (Map<String, Object>) health.getDetails().get("critical-error");

        assertNotNull(details);
        assertEquals("DOWN", details.get("status"));
        assertEquals(baseUrl + "/error", details.get("url"));
        assertEquals(true, details.get("critical"));
        assertEquals(500, details.get("httpStatus"));
    }

    @Test
    void shouldReturnUpWhenAcceptedStatusesContains404ForCriticalApi() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(2000);

        ExternalApisHealthProperties.ApiCheck critical404Accepted = buildApiCheck(
                "critical-404-accepted",
                baseUrl + "/not-found",
                true,
                List.of(200, 404)
        );

        properties.setChecks(List.of(critical404Accepted));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, Object> details =
                (Map<String, Object>) health.getDetails().get("critical-404-accepted");

        assertNotNull(details);
        assertEquals("UP", details.get("status"));
        assertEquals(404, details.get("httpStatus"));
    }

    @Test
    void shouldIncludeErrorWhenRequestFails() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(200);

        ExternalApisHealthProperties.ApiCheck unreachableCritical = buildApiCheck(
                "unreachable-api",
                "http://localhost:1/health",
                true,
                null
        );

        properties.setChecks(List.of(unreachableCritical));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());

        @SuppressWarnings("unchecked")
        Map<String, Object> details =
                (Map<String, Object>) health.getDetails().get("unreachable-api");

        assertNotNull(details);
        assertEquals("DOWN", details.get("status"));
        assertEquals("http://localhost:1/health", details.get("url"));
        assertEquals(true, details.get("critical"));
        assertNotNull(details.get("error"));
    }

    private static ExternalApisHealthProperties.ApiCheck buildApiCheck(
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

    private static class FixedStatusHandler implements HttpHandler {

        private final int status;

        private FixedStatusHandler(int status) {
            this.status = status;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(status, -1);
            try (OutputStream os = exchange.getResponseBody()) {
                // no body
            }
        }
    }
}