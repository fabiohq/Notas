
package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.observability;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObservabilityTest {

    @Test
    void externalApisHealthPropertiesShouldSetAndGetValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck apiCheck =
                new ExternalApisHealthProperties.ApiCheck();

        apiCheck.setName("api-test");
        apiCheck.setUrl("http://localhost:8080/health");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 204));

        properties.setTimeoutMs(3000);
        properties.setChecks(List.of(apiCheck));

        assertEquals(3000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());

        ExternalApisHealthProperties.ApiCheck result = properties.getChecks().get(0);

        assertEquals("api-test", result.getName());
        assertEquals("http://localhost:8080/health", result.getUrl());
        assertFalse(result.isCritical());
        assertEquals(List.of(200, 204), result.getAcceptedStatuses());
    }

    @Test
    void externalApisHealthPropertiesShouldHaveDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());
    }

    @Test
    void healthShouldReturnUpWhenCriticalApiRespondsWith2xx() throws IOException {
        HttpServer server = createServer(200);

        try {
            ExternalApisHealthProperties properties = buildProperties(
                    "api-up",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    null
            );

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("api-up"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void healthShouldReturnUpWhenStatusIsAcceptedByConfiguration() throws IOException {
        HttpServer server = createServer(404);

        try {
            ExternalApisHealthProperties properties = buildProperties(
                    "api-accepted",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(404)
            );

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("api-accepted"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void healthShouldReturnDownWhenCriticalApiRespondsWithNon2xx() throws IOException {
        HttpServer server = createServer(500);

        try {
            ExternalApisHealthProperties properties = buildProperties(
                    "api-down",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    null
            );

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.DOWN, health.getStatus());
            assertTrue(health.getDetails().containsKey("api-down"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void healthShouldReturnUpWhenNonCriticalApiIsDown() {
        ExternalApisHealthProperties properties = buildProperties(
                "api-non-critical",
                "http://localhost:1/health",
                false,
                null
        );

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("api-non-critical"));
    }

    @Test
    void healthShouldReturnDownWhenCriticalApiThrowsException() {
        ExternalApisHealthProperties properties = buildProperties(
                "api-error",
                "http://localhost:1/health",
                true,
                null
        );

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("api-error"));
    }

    @Test
    void healthShouldReturnUpWhenThereAreNoChecks() {
        ExternalApisHealthProperties properties =
                new ExternalApisHealthProperties();

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().isEmpty());
    }

    private ExternalApisHealthProperties buildProperties(
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

        ExternalApisHealthProperties properties =
                new ExternalApisHealthProperties();

        properties.setTimeoutMs(500);
        properties.setChecks(List.of(apiCheck));

        return properties;
    }

    private HttpServer createServer(int statusCode) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);

        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(statusCode, -1);
            exchange.close();
        });

        server.start();
        return server;
    }
}
