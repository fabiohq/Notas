@AfterEach
void tearDown() {
    if (server != null) {
        server.stop(0);
    }
}

@Test
@DisplayName("Debe retornar UP cuando todos los checks críticos responden 2xx")
void shouldReturnUpWhenCriticalApiResponds2xx() throws Exception {
    int port = startServerWithStatus(200);
    ExternalApisHealthProperties properties = propertiesWithCheck(
            "pkm",
            "http://localhost:" + port + "/health",
            true,
            null
    );

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertThat(health.getStatus()).isEqualTo(Status.UP);
    assertThat(health.getDetails()).containsKey("pkm");

    Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("pkm");
    assertThat(detail.get("status")).isEqualTo("UP");
    assertThat(detail.get("url")).isEqualTo("http://localhost:" + port + "/health");
    assertThat(detail.get("critical")).isEqualTo(true);
    assertThat(detail.get("httpStatus")).isEqualTo(200);
}

@Test
@DisplayName("Debe retornar DOWN cuando un check crítico responde error")
void shouldReturnDownWhenCriticalApiRespondsError() throws Exception {
    int port = startServerWithStatus(500);
    ExternalApisHealthProperties properties = propertiesWithCheck(
            "pkm",
            "http://localhost:" + port + "/health",
            true,
            null
    );

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertThat(health.getStatus()).isEqualTo(Status.DOWN);

    Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("pkm");
    assertThat(detail.get("status")).isEqualTo("DOWN");
    assertThat(detail.get("httpStatus")).isEqualTo(500);
}

@Test
@DisplayName("Debe retornar UP cuando un check no crítico responde error")
void shouldReturnUpWhenNonCriticalApiRespondsError() throws Exception {
    int port = startServerWithStatus(500);
    ExternalApisHealthProperties properties = propertiesWithCheck(
            "pkm",
            "http://localhost:" + port + "/health",
            false,
            null
    );

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertThat(health.getStatus()).isEqualTo(Status.UP);

    Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("pkm");
    assertThat(detail.get("status")).isEqualTo("DOWN");
    assertThat(detail.get("critical")).isEqualTo(false);
}

@Test
@DisplayName("Debe aceptar códigos HTTP configurados en acceptedStatuses")
void shouldAcceptConfiguredHttpStatuses() throws Exception {
    int port = startServerWithStatus(401);
    ExternalApisHealthProperties properties = propertiesWithCheck(
            "auth",
            "http://localhost:" + port + "/health",
            true,
            List.of(200, 401)
    );

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertThat(health.getStatus()).isEqualTo(Status.UP);

    Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("auth");
    assertThat(detail.get("status")).isEqualTo("UP");
    assertThat(detail.get("httpStatus")).isEqualTo(401);
}