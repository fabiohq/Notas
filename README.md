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

@Test
@DisplayName("Debe retornar false cuando la llave pública es inválida")
void shouldReturnFalseWhenPublicKeyIsInvalid() throws Exception {
    setField(securityService, "PUBLIC_KEY", "llave-publica-invalida");

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer token-cualquiera");
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
}

@Test
@DisplayName("Debe retornar false cuando la firma del JWT es inválida")
void shouldReturnFalseWhenJwtSignatureIsInvalid() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048);
    KeyPair anotherKeyPair = generator.generateKeyPair();

    String jwt = buildJwtWithPrivateKey(
            "CO_ODS",
            "scope-test",
            List.of("aud-test"),
            Instant.now().plusSeconds(3600),
            (RSAPrivateKey) anotherKeyPair.getPrivate()
    );

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("Invalid sign");
}

@Test
@DisplayName("Debe retornar false cuando no viene X-Santander-Client-Id")
void shouldReturnFalseWhenSantanderClientIdHeaderIsMissing() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("Header X-Santander-Client-Id no present");
}

@Test
@DisplayName("Debe retornar false cuando apiName viene vacío")
void shouldReturnFalseWhenApiNameIsEmpty() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");
    exchange.getIn().setHeader("apiName", "");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("Invalid apiName header");
}

@Test
@DisplayName("Debe retornar false cuando falla la consulta de scope en BD")
void shouldReturnFalseWhenScopeQueryThrowsException() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    when(jdbcTemplate.queryForObject(
            eq("SELECT COUNT(1) FROM authentication.\"Scopes\" WHERE \"ScopeName\" = ?"),
            any(Object[].class),
            eq(Integer.class)
    )).thenThrow(new RuntimeException("DB error"));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");
    exchange.getIn().setHeader("apiName", "api-test");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("DB error");
}

@Test
@DisplayName("Debe retornar false cuando falla la consulta de servicio en BD")
void shouldReturnFalseWhenServiceQueryThrowsException() throws Exception {
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
    )).thenThrow(new RuntimeException("DB service error"));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");
    exchange.getIn().setHeader("apiName", "api-test");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("DB service error");
}

private String buildJwtWithPrivateKey(
        String issuer,
        String scope,
        List<String> audience,
        Instant expiration,
        RSAPrivateKey privateKey
) throws Exception {
    JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
            .issuer(issuer)
            .claim("scope", scope)
            .expirationTime(Date.from(expiration));

    if (audience != null) {
        claimsBuilder.audience(audience);
    }

    SignedJWT signedJWT = new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
            claimsBuilder.build()
    );

    signedJWT.sign(new RSASSASigner(privateKey));
    return signedJWT.serialize();
}