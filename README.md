@BeforeEach
void setUp() {
    controller = new SecurityController(securityService);
    camelContext = new DefaultCamelContext();
}

@Test
@DisplayName("Debe retornar 200 OK cuando SecurityService valida el token")
void shouldReturnOkWhenTokenIsValid() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class))).thenReturn(true);

    ResponseEntity<String> response = controller.validateToken(
            "Bearer jwt-valido",
            "263ec146",
            "service-test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo("Token validation successful");
    verify(securityService).getCamelContext();
    verify(securityService).validateJwt(any(Exchange.class));
}

@Test
@DisplayName("Debe retornar 401 UNAUTHORIZED con errorDescription cuando SecurityService retorna false")
void shouldReturnUnauthorizedWhenTokenIsInvalid() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class))).thenAnswer(invocation -> {
        Exchange exchange = invocation.getArgument(0);
        exchange.getIn().setHeader("errorDescription", "Invalid sign");
        return false;
    });

    ResponseEntity<String> response = controller.validateToken(
            "Bearer jwt-invalido",
            "263ec146",
            "service-test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    assertThat(response.getBody()).isEqualTo("Invalid sign");
    verify(securityService).validateJwt(any(Exchange.class));
}

@Test
@DisplayName("Debe retornar 500 INTERNAL_SERVER_ERROR cuando ocurre una excepción")
void shouldReturnInternalServerErrorWhenExceptionOccurs() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class)))
            .thenThrow(new RuntimeException("Error simulado"));

    ResponseEntity<String> response = controller.validateToken(
            "Bearer jwt",
            "263ec146",
            "service-test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(response.getBody())
            .isEqualTo("Ocurrió un error al procesar la solicitud de validacion token. Consulte los logs para más detalle.: ");
}

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

@Test
@DisplayName("Debe iniciar con timeout por defecto de 2000 ms y lista vacía")
void shouldHaveDefaultValues() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

    assertThat(properties.getTimeoutMs()).isEqualTo(2000);
    assertThat(properties.getChecks()).isNotNull().isEmpty();
}

@Test
@DisplayName("Debe permitir modificar timeoutMs")
void shouldSetTimeoutMs() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

    properties.setTimeoutMs(5000);

    assertThat(properties.getTimeoutMs()).isEqualTo(5000);
}

@Test
@DisplayName("Debe permitir asignar lista de checks")
void shouldSetChecks() {
    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName("pkm");
    check.setUrl("http://localhost:8080/health");
    check.setCritical(false);
    check.setAcceptedStatuses(List.of(200, 204));

    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setChecks(List.of(check));

    assertThat(properties.getChecks()).hasSize(1);
    assertThat(properties.getChecks().get(0).getName()).isEqualTo("pkm");
    assertThat(properties.getChecks().get(0).getUrl()).isEqualTo("http://localhost:8080/health");
    assertThat(properties.getChecks().get(0).isCritical()).isFalse();
    assertThat(properties.getChecks().get(0).getAcceptedStatuses()).containsExactly(200, 204);
}


@BeforeEach
void setUp() throws Exception {
    camelContext = new DefaultCamelContext();
    securityService = new SecurityService(jdbcTemplate, camelContext);

    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    keyPair = generator.generateKeyPair();

    setField(securityService, "PUBLIC_KEY", publicKeyAsBase64((RSAPublicKey) keyPair.getPublic()));
    setField(securityService, "JWT_ISS", "CO_ODS");
    setField(securityService, "X_SANTANDER_CLIENT_ID", "263ec146");
    setField(securityService, "EXP_CLAIM_NAME", "exp");
}

@Test
@DisplayName("Debe retornar false cuando no viene header Authorization")
void shouldReturnFalseWhenAuthorizationHeaderIsMissing() throws Exception {
    Exchange exchange = exchange();
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("Authorization header not present");
}

@Test
@DisplayName("Debe retornar true cuando token, headers, claims y scope son válidos")
void shouldReturnTrueWhenJwtIsValid() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    when(jdbcTemplate.queryForObject(
            eq("SELECT COUNT(1) FROM authentication.\"Scopes\" WHERE \"ScopeName\" = ?"),
            any(Object[].class),
            eq(Integer.class)
    )).thenReturn(1);

    when(jdbcTemplate.queryForObject(
            eq("SELECT COUNT(1) FROM authentication.\"ScopeAPICatalog\" WHERE \"ScopeName\" = ? AND \"ServiceName\" = ?"),
            any(Object[].class),
            eq(Integer.class)
    )).thenReturn(1);

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");
    exchange.getIn().setHeader("apiName", "api-test");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isTrue();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(true);
}


