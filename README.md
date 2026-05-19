/*

Pruebas unitarias desacopladas clase por clase

Proyecto: bnc-bsn049-msiam

Paquetes cubiertos:

controllers.SecurityController


observability.ExternalApisHealthIndicator


observability.ExternalApisHealthProperties


services.SecurityService


Stack:

JUnit 5


Mockito


AssertJ


Spring Test


Nimbus JOSE JWT */



// ========================================================= // Dependencias sugeridas para test // =========================================================

/* Maven:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.nimbusds</groupId>
    <artifactId>nimbus-jose-jwt</artifactId>
    <scope>test</scope>
</dependency>
*/// ========================================================= // 1. SecurityControllerTest // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.controllers;

import static org.assertj.core.api.Assertions.assertThat; import static org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.verify; import static org.mockito.Mockito.when;

import com.santander.bnc.bsn049.bncbsn049msiam.services.SecurityService; import org.apache.camel.CamelContext; import org.apache.camel.Exchange; import org.apache.camel.impl.DefaultCamelContext; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class) class SecurityControllerTest {

@Mock
private SecurityService securityService;

private SecurityController controller;
private CamelContext camelContext;

@BeforeEach
void setUp() {
    controller = new SecurityController(securityService);
    camelContext = new DefaultCamelContext();
}

@Test
@DisplayName("Debe retornar 200 cuando el token es válido")
void shouldReturnOkWhenTokenIsValid() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class))).thenReturn(true);

    ResponseEntity<String> response = controller.validateToken(
            "Bearer token-valido",
            "263ec146",
            "/api/test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo("Token validation successful");
    verify(securityService).validateJwt(any(Exchange.class));
}

@Test
@DisplayName("Debe retornar 401 cuando el token no es válido")
void shouldReturnUnauthorizedWhenTokenIsInvalid() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class))).thenAnswer(invocation -> {
        Exchange exchange = invocation.getArgument(0);
        exchange.getIn().setHeader("errorDescription", "Invalid sign");
        return false;
    });

    ResponseEntity<String> response = controller.validateToken(
            "Bearer token-invalido",
            "263ec146",
            "/api/test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    assertThat(response.getBody()).isEqualTo("Invalid sign");
    verify(securityService).validateJwt(any(Exchange.class));
}

@Test
@DisplayName("Debe retornar 500 cuando ocurre excepción validando token")
void shouldReturnInternalServerErrorWhenServiceThrowsException() throws Exception {
    when(securityService.getCamelContext()).thenReturn(camelContext);
    when(securityService.validateJwt(any(Exchange.class))).thenThrow(new RuntimeException("error inesperado"));

    ResponseEntity<String> response = controller.validateToken(
            "Bearer token",
            "263ec146",
            "/api/test",
            "api-test"
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(response.getBody()).contains("Ocurrió un error al procesar la solicitud");
}

}

// ========================================================= // 2. ExternalApisHealthPropertiesTest // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.observability;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

@Test
@DisplayName("Debe tener valores por defecto")
void shouldHaveDefaultValues() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

    assertThat(properties.getTimeoutMs()).isEqualTo(2000);
    assertThat(properties.getChecks()).isEmpty();
}

@Test
@DisplayName("Debe asignar propiedades de checks externos")
void shouldSetApiChecks() {
    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName("pkm");
    check.setUrl("http://localhost:8080/health");
    check.setCritical(false);
    check.setAcceptedStatuses(List.of(200, 204));

    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);
    properties.setChecks(List.of(check));

    assertThat(properties.getTimeoutMs()).isEqualTo(1000);
    assertThat(properties.getChecks()).hasSize(1);
    assertThat(properties.getChecks().get(0).getName()).isEqualTo("pkm");
    assertThat(properties.getChecks().get(0).getUrl()).isEqualTo("http://localhost:8080/health");
    assertThat(properties.getChecks().get(0).isCritical()).isFalse();
    assertThat(properties.getChecks().get(0).getAcceptedStatuses()).containsExactly(200, 204);
}

}

// ========================================================= // 3. ExternalApisHealthIndicatorTest // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.observability;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpServer; import java.io.IOException; import java.net.InetSocketAddress; import java.util.List; import java.util.Map; import org.junit.jupiter.api.AfterEach; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.boot.actuate.health.Health; import org.springframework.boot.actuate.health.Status;

class ExternalApisHealthIndicatorTest {

private HttpServer server;

@AfterEach
void tearDown() {
    if (server != null) {
        server.stop(0);
    }
}

@Test
@DisplayName("Debe retornar UP cuando API crítica responde 2xx")
void shouldReturnUpWhenCriticalApiRespondsOk() throws Exception {
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
}

@Test
@DisplayName("Debe retornar DOWN cuando API crítica responde fuera de rango permitido")
void shouldReturnDownWhenCriticalApiFails() throws Exception {
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
    assertThat(detail.get("critical")).isEqualTo(true);
}

@Test
@DisplayName("Debe retornar UP cuando API no crítica falla")
void shouldReturnUpWhenNonCriticalApiFails() throws Exception {
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
}

@Test
@DisplayName("Debe aceptar status configurados")
void shouldAcceptConfiguredStatuses() throws Exception {
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
    assertThat(detail.get("httpStatus")).isEqualTo(401);
}

@Test
@DisplayName("Debe retornar DOWN cuando URL crítica no existe")
void shouldReturnDownWhenCriticalUrlIsUnavailable() {
    ExternalApisHealthProperties properties = propertiesWithCheck(
            "api-caida",
            "http://localhost:1/health",
            true,
            null
    );
    properties.setTimeoutMs(200);

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertThat(health.getStatus()).isEqualTo(Status.DOWN);
    Map<String, Object> detail = (Map<String, Object>) health.getDetails().get("api-caida");
    assertThat(detail.get("status")).isEqualTo("DOWN");
    assertThat(detail.get("error")).isNotNull();
}

private int startServerWithStatus(int status) throws IOException {
    server = HttpServer.create(new InetSocketAddress(0), 0);
    server.createContext("/health", exchange -> {
        exchange.sendResponseHeaders(status, -1);
        exchange.close();
    });
    server.start();
    return server.getAddress().getPort();
}

private ExternalApisHealthProperties propertiesWithCheck(
        String name,
        String url,
        boolean critical,
        List<Integer> acceptedStatuses
) {
    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName(name);
    check.setUrl(url);
    check.setCritical(critical);
    check.setAcceptedStatuses(acceptedStatuses);

    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);
    properties.setChecks(List.of(check));
    return properties;
}

}

// ========================================================= // 4. SecurityServiceTest // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.services;

import static org.assertj.core.api.Assertions.assertThat; import static org.mockito.ArgumentMatchers.any; import static org.mockito.ArgumentMatchers.eq; import static org.mockito.Mockito.verify; import static org.mockito.Mockito.when;

import com.nimbusds.jose.JWSAlgorithm; import com.nimbusds.jose.JWSHeader; import com.nimbusds.jose.crypto.RSASSASigner; import com.nimbusds.jwt.JWTClaimsSet; import com.nimbusds.jwt.SignedJWT; import java.lang.reflect.Field; import java.security.KeyPair; import java.security.KeyPairGenerator; import java.security.interfaces.RSAPrivateKey; import java.security.interfaces.RSAPublicKey; import java.time.Instant; import java.util.Base64; import java.util.Date; import java.util.List; import org.apache.camel.CamelContext; import org.apache.camel.Exchange; import org.apache.camel.builder.ExchangeBuilder; import org.apache.camel.impl.DefaultCamelContext; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class) class SecurityServiceTest {

@Mock
private JdbcTemplate jdbcTemplate;

private CamelContext camelContext;
private SecurityService securityService;
private KeyPair keyPair;

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
@DisplayName("Debe retornar CamelContext inyectado")
void shouldReturnInjectedCamelContext() {
    assertThat(securityService.getCamelContext()).isSameAs(camelContext);
}

@Test
@DisplayName("Debe retornar false cuando no llega header Authorization")
void shouldReturnFalseWhenAuthorizationHeaderIsMissing() throws Exception {
    Exchange exchange = exchange();
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Authorization header not present");
}

@Test
@DisplayName("Debe retornar false cuando Authorization no tiene Bearer")
void shouldReturnFalseWhenBearerIsInvalid() throws Exception {
    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "token-sin-bearer");
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Invalid bearer token");
}

@Test
@DisplayName("Debe retornar false cuando JWT no se puede parsear")
void shouldReturnFalseWhenJwtCannotBeParsed() throws Exception {
    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer token-mal-formado");
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Unexpected Jwt token");
}

@Test
@DisplayName("Debe retornar false cuando X-Santander-Client-Id es inválido")
void shouldReturnFalseWhenSantanderClientIdIsInvalid() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "cliente-invalido");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Invalid X-Santander-Client-Id");
}

@Test
@DisplayName("Debe retornar false cuando ISS es inválido")
void shouldReturnFalseWhenIssuerIsInvalid() throws Exception {
    String jwt = buildJwt("OTRO_ISS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Invalid ISS");
}

@Test
@DisplayName("Debe retornar false cuando token está expirado")
void shouldReturnFalseWhenTokenIsExpired() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().minusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Token has expired");
}

@Test
@DisplayName("Debe retornar false cuando claim aud no existe")
void shouldReturnFalseWhenAudienceClaimIsMissing() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", null, Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Invalid claims");
}

@Test
@DisplayName("Debe retornar false cuando apiName no llega")
void shouldReturnFalseWhenApiNameIsMissing() throws Exception {
    String jwt = buildJwt("CO_ODS", "scope-test", List.of("aud-test"), Instant.now().plusSeconds(3600));

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription")).isEqualTo("Invalid apiName header");
}

@Test
@DisplayName("Debe retornar false cuando scope no tiene permiso para apiName")
void shouldReturnFalseWhenScopeIsNotAuthorizedForApiName() throws Exception {
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
    )).thenReturn(0);

    Exchange exchange = exchange();
    exchange.getIn().setHeader("Authorization", "Bearer " + jwt);
    exchange.getIn().setHeader("X-Santander-Client-Id", "263ec146");
    exchange.getIn().setHeader("apiName", "api-test");

    Boolean result = securityService.validateJwt(exchange);

    assertThat(result).isFalse();
    assertThat(exchange.getIn().getHeader("isJwtValid")).isEqualTo(false);
    assertThat(exchange.getIn().getHeader("errorDescription"))
            .isEqualTo("The scope does not have the necessary permissions");
}

@Test
@DisplayName("Debe retornar true cuand