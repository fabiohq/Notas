package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

class ExternalApisHealthIndicatorTest {

    private HttpServer server;

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    private String startServerWithStatus(int statusCode) throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(statusCode, -1);
            exchange.close();
        });
        server.start();

        int port = server.getAddress().getPort();
        return "http://localhost:" + port + "/health";
    }

    private ExternalApisHealthProperties.ApiCheck buildCheck(
            String name,
            String url,
            boolean critical,
            List<Integer> acceptedStatuses
    ) {
        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        check.setName(name);
        check.setUrl(url);
        check.setCritical(critical);
        check.setAcceptedStatuses(acceptedStatuses);

        return check;
    }

    @Test
    @DisplayName("Debe retornar UP cuando no existen checks configurados")
    void healthShouldBeUpWhenNoChecksConfigured() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of());

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().isEmpty());
    }

    @Test
    @DisplayName("Debe retornar UP cuando API crítica responde 2xx")
    void healthShouldBeUpWhenCriticalApiReturns2xx() throws Exception {
        String url = startServerWithStatus(200);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(
                buildCheck("sanba", url, true, null)
        ));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("sanba"));
    }

    @Test
    @DisplayName("Debe retornar UP cuando API responde status aceptado custom")
    void healthShouldBeUpWhenApiReturnsCustomAcceptedStatus() throws Exception {
        String url = startServerWithStatus(404);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(
                buildCheck("catalogue", url, true, List.of(404))
        ));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("catalogue"));
    }

    @Test
    @DisplayName("Debe retornar DOWN cuando API crítica responde status no aceptado")
    void healthShouldBeDownWhenCriticalApiReturnsNotAcceptedStatus() throws Exception {
        String url = startServerWithStatus(500);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(
                buildCheck("sanba", url, true, null)
        ));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("sanba"));
    }

    @Test
    @DisplayName("Debe retornar UP cuando API no crítica responde status no aceptado")
    void healthShouldBeUpWhenNonCriticalApiReturnsNotAcceptedStatus() throws Exception {
        String url = startServerWithStatus(500);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(
                buildCheck("optional-api", url, false, null)
        ));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("optional-api"));
    }

    @Test
    @DisplayName("Debe retornar DOWN cuando API crítica no está disponible")
    void healthShouldBeDownWhenCriticalApiIsUnavailable() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(200);
        properties.setChecks(List.of(
                buildCheck("down-api", "http://localhost:1/health", true, null)
        ));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("down-api"));
    }
}



package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

    @Test
    @DisplayName("Debe tener valores por defecto")
    void shouldHaveDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());
    }

    @Test
    @DisplayName("Debe asignar timeout y checks")
    void shouldSetTimeoutAndChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        check.setName("sanba");
        check.setUrl("http://localhost:8080/health");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 204));

        properties.setTimeoutMs(3000);
        properties.setChecks(List.of(check));

        assertEquals(3000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertSame(check, properties.getChecks().get(0));
    }

    @Test
    @DisplayName("ApiCheck debe tener critical true por defecto")
    void apiCheckShouldHaveCriticalTrueByDefault() {
        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        assertTrue(check.isCritical());
    }

    @Test
    @DisplayName("ApiCheck getters y setters deben funcionar")
    void apiCheckGettersAndSettersShouldWork() {
        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        List<Integer> statuses = List.of(200, 201, 204);

        check.setName("api-test");
        check.setUrl("http://localhost/test");
        check.setCritical(false);
        check.setAcceptedStatuses(statuses);

        assertEquals("api-test", check.getName());
        assertEquals("http://localhost/test", check.getUrl());
        assertFalse(check.isCritical());
        assertSame(statuses, check.getAcceptedStatuses());
    }
}
