// ============================================================================ // TESTS DESDE EL PAQUETE observability EN ADELANTE // ============================================================================

// ----------------------------------------------------------------------------- // 1) src/test/java/.../observability/ExternalApisHealthIndicatorTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException; import java.net.InetSocketAddress; import java.util.List; import java.util.Map;

import org.junit.jupiter.api.AfterEach; import org.junit.jupiter.api.Test; import org.springframework.boot.actuate.health.Health; import org.springframework.boot.actuate.health.Status;

import com.sun.net.httpserver.HttpServer;

class ExternalApisHealthIndicatorTest {

private HttpServer server;

@AfterEach
void tearDown() {
    if (server != null) {
        server.stop(0);
    }
}

@Test
void health_cuandoApiCriticaEstaUp_retornaUp() throws IOException {
    int port = startServer(204);

    ExternalApisHealthProperties properties = buildProperties(
            "api-up",
            "http://localhost:" + port,
            true,
            List.of(204));

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.UP, health.getStatus());

    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api-up");

    assertEquals("UP", detail.get("status"));
    assertEquals("http://localhost:" + port, detail.get("url"));
    assertEquals(true, detail.get("critical"));
    assertEquals(204, detail.get("httpStatus"));
}

@Test
void health_cuandoApiCriticaEstaDown_retornaDown() throws IOException {
    int port = startServer(500);

    ExternalApisHealthProperties properties = buildProperties(
            "api-down",
            "http://localhost:" + port,
            true,
            null);

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.DOWN, health.getStatus());

    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api-down");

    assertEquals("DOWN", detail.get("status"));
    assertEquals(500, detail.get("httpStatus"));
}

@Test
void health_cuandoApiNoCriticaEstaDown_retornaUp() {
    ExternalApisHealthProperties properties = buildProperties(
            "api-non-critical",
            "http://localhost:1",
            false,
            null);

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.UP, health.getStatus());

    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api-non-critical");

    assertEquals("DOWN", detail.get("status"));
    assertEquals(false, detail.get("critical"));
    assertNotNull(detail.get("error"));
}

@Test
void health_cuandoAcceptedStatusesEsNull_usaRango2xx() throws IOException {
    int port = startServer(200);

    ExternalApisHealthProperties properties = buildProperties(
            "api-2xx",
            "http://localhost:" + port,
            true,
            null);

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.UP, health.getStatus());
}

private int startServer(int status) throws IOException {
    server = HttpServer.create(new InetSocketAddress(0), 0);

    server.createContext("/", exchange -> {
        exchange.sendResponseHeaders(status, -1);
        exchange.close();
    });

    server.start();

    return server.getAddress().getPort();
}

private ExternalApisHealthProperties buildProperties(
        String name,
        String url,
        boolean critical,
        List<Integer> acceptedStatuses) {

    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);

    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName(name);
    check.setUrl(url);
    check.setCritical(critical);
    check.setAcceptedStatuses(acceptedStatuses);

    properties.setChecks(List.of(check));

    return properties;
}

}

// ----------------------------------------------------------------------------- // 2) src/test/java/.../observability/ExternalApisHealthPropertiesTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

@Test
void gettersSetters_timeoutYChecks() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

    assertEquals(2000, properties.getTimeoutMs());
    assertNotNull(properties.getChecks());
    assertTrue(properties.getChecks().isEmpty());

    properties.setTimeoutMs(500);

    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName("api");
    check.setUrl("http://localhost:8080/health");
    check.setCritical(false);
    check.setAcceptedStatuses(List.of(200, 204));

    properties.setChecks(List.of(check));

    assertEquals(500, properties.getTimeoutMs());
    assertEquals(1, properties.getChecks().size());

    ExternalApisHealthProperties.ApiCheck result = properties.getChecks().get(0);

    assertEquals("api", result.getName());
    assertEquals("http://localhost:8080/health", result.getUrl());
    assertFalse(result.isCritical());
    assertEquals(List.of(200, 204), result.getAcceptedStatuses());
}

@Test
void apiCheck_valoresPorDefecto() {
    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

    assertTrue(check.isCritical());
    assertNull(check.getAcceptedStatuses());
}

}