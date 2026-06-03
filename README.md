package com.santander.bnc.bsn049.bncbsn049mstermdeposits.observability;

import static org.junit.jupiter.api.Assertions.*;

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
    void shouldCoverPropertiesAndApiCheckAccessors() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(500);

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("api");
        check.setUrl("http://localhost");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 204));

        properties.setChecks(List.of(check));

        assertEquals(500, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertEquals("api", check.getName());
        assertEquals("http://localhost", check.getUrl());
        assertFalse(check.isCritical());
        assertEquals(List.of(200, 204), check.getAcceptedStatuses());
    }

    @Test
    void shouldReturnUpWhenCriticalApisAreUp() throws Exception {
        HttpServer server = server(204);
        try {
            ExternalApisHealthProperties properties = properties(
                    api("ok", url(server), true, List.of(204))
            );

            Health health = new ExternalApisHealthIndicator(properties).health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("ok"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnDownWhenCriticalApiIsDown() throws Exception {
        HttpServer server = server(500);
        try {
            ExternalApisHealthProperties properties = properties(
                    api("critical", url(server), true, null)
            );

            Health health = new ExternalApisHealthIndicator(properties).health();

            assertEquals(Status.DOWN, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("critical");
            assertEquals("DOWN", detail.get("status"));
            assertEquals(500, detail.get("httpStatus"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnUpWhenOnlyNonCriticalApiIsDown() throws Exception {
        HttpServer server = server(500);
        try {
            ExternalApisHealthProperties properties = properties(
                    api("nonCritical", url(server), false, null)
            );

            Health health = new ExternalApisHealthIndicator(properties).health();

            assertEquals(Status.UP, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("nonCritical");
            assertEquals("DOWN", detail.get("status"));
            assertEquals(false, detail.get("critical"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnDownAndErrorWhenUrlIsInvalid() {
        ExternalApisHealthProperties properties = properties(
                api("bad", "http://localhost:1/not-found", true, null)
        );
        properties.setTimeoutMs(100);

        Health health = new ExternalApisHealthIndicator(properties).health();

        assertEquals(Status.DOWN, health.getStatus());

        Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("bad");
        assertEquals("DOWN", detail.get("status"));
        assertNotNull(detail.get("error"));
    }

    @Test
    void shouldReturnUpWithEmptyChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setChecks(List.of());

        Health health = new ExternalApisHealthIndicator(properties).health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().isEmpty());
    }

    private ExternalApisHealthProperties properties(ExternalApisHealthProperties.ApiCheck... checks) {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(checks));
        return properties;
    }

    private ExternalApisHealthProperties.ApiCheck api(
            String name,
            String url,
            boolean critical,
            List<Integer> acceptedStatuses) {

        ExternalApisHealthProperties.ApiCheck api = new ExternalApisHealthProperties.ApiCheck();
        api.setName(name);
        api.setUrl(url);
        api.setCritical(critical);
        api.setAcceptedStatuses(acceptedStatuses);
        return api;
    }

    private HttpServer server(int status) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/health", exchange -> {
            byte[] body = "ok".getBytes();
            exchange.sendResponseHeaders(status, body.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.start();
        return server;
    }

    private String url(HttpServer server) {
        return "http://localhost:" + server.getAddress().getPort() + "/health";
    }
}
