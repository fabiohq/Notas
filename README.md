package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.santander.bnc.bsn049.bncbsn049msprtvcntrl.Models.Dtos.Pem758ADto;

class OCServiceTest {

    private CamelContext camelContext;
    private RestTemplate restTemplate;
    private OCService service;

    @BeforeEach
    void setUp() {

        camelContext = new DefaultCamelContext();
        restTemplate = mock(RestTemplate.class);

        service = new OCService(camelContext, restTemplate);

        ReflectionTestUtils.setField(service, "TD_AUTHORIZATION", "Bearer td-token");
        ReflectionTestUtils.setField(service, "PUBLIC_KEY", "public-key");
        ReflectionTestUtils.setField(service, "OPERATIVE_CONTROL_URL", "http://localhost");
        ReflectionTestUtils.setField(service, "ACR_DEFINED", "1");

        ReflectionTestUtils.setField(service, "SANBA_URL", "http://localhost/sanba");
        ReflectionTestUtils.setField(service, "SANBA_CHANNEL", "60");
        ReflectionTestUtils.setField(service, "SANBA_USER", "ODSUSU01");
        ReflectionTestUtils.setField(service, "SANBA_MQROUTE", "QCTFD");
    }

    @Test
    void getCamelContextDebeRetornarCamelContext() {

        CamelContext result = service.getCamelContext();

        assertNotNull(result);
        assertEquals(camelContext, result);
    }

    @Test
    void validateDebeRetornarTrueCuandoExisteHeaderOctest() {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader("octest", "true");

        boolean result =
                service.validatePenumperAndContracts(exchange);

        assertTrue(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoAuthorizationEsNull() {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        boolean result =
                service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoAuthorizationNoTieneBearer() {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "token-sin-bearer"
        );

        boolean result =
                service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarTrueCuandoAcrNoCoincide() throws Exception {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "Bearer token"
        );

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("5", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertTrue(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoPenumperEsInvalido() throws Exception {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "Bearer token"
        );

        exchange.getIn().setHeader(
                "X-Operative-Control-Rules",
                "$.numper"
        );

        exchange.getIn().setBody("""
            {
               "numper":"123"
            }
        """);

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoPenumperNoCoincideConJwt() throws Exception {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "Bearer token"
        );

        exchange.getIn().setHeader(
                "X-Operative-Control-Rules",
                "$.numper"
        );

        exchange.getIn().setBody("""
            {
               "numper":"87654321"
            }
        """);

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoContratoEsInvalido() throws Exception {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "Bearer token"
        );

        exchange.getIn().setHeader(
                "X-Operative-Control-Rules",
                "$.contractId"
        );

        exchange.getIn().setBody("""
            {
               "contractId":"123"
            }
        """);

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoSanbaRetornaNull() throws Exception {

        Exchange exchange = exchangeValido();

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Pem758ADto.class)
        )).thenReturn(ResponseEntity.ok(null));

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void validateDebeRetornarTrueCuandoContratoCoincide() throws Exception {

        Exchange exchange = exchangeValido();

        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco("12345678901234567890");

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Pem758ADto.class)
        )).thenReturn(ResponseEntity.ok(dto));

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertTrue(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoContratoNoCoincide() throws Exception {

        Exchange exchange = exchangeValido();

        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco("99999999999999999999");

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Pem758ADto.class)
        )).thenReturn(ResponseEntity.ok(dto));

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoRestTemplateLanzaExcepcion() throws Exception {

        Exchange exchange = exchangeValido();

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Pem758ADto.class)
        )).thenThrow(new RuntimeException("error"));

        try (MockedStatic<SignedJWT> mocked =
                     mockJwt("1", "12345678")) {

            boolean result =
                    service.validatePenumperAndContracts(exchange);

            assertFalse(result);
        }
    }

    @Test
    void isValidPenumperDebeRetornarTrue() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "isValidPenumper",
                        String.class
                );

        method.setAccessible(true);

        boolean result =
                (boolean) method.invoke(service, "12345678");

        assertTrue(result);
    }

    @Test
    void isValidPenumperDebeRetornarFalse() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "isValidPenumper",
                        String.class
                );

        method.setAccessible(true);

        boolean result =
                (boolean) method.invoke(service, "ABC");

        assertFalse(result);
    }

    @Test
    void isValidContractIdDebeRetornarTrue() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "isValidContractId",
                        String.class
                );

        method.setAccessible(true);

        boolean result =
                (boolean) method.invoke(
                        service,
                        "12345678901234567890"
                );

        assertTrue(result);
    }

    @Test
    void isValidContractIdDebeRetornarFalse() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "isValidContractId",
                        String.class
                );

        method.setAccessible(true);

        boolean result =
                (boolean) method.invoke(service, "123");

        assertFalse(result);
    }

    @Test
    void validarContratoDebeRetornarTrue() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "validarContrato",
                        String.class,
                        Pem758ADto.class
                );

        method.setAccessible(true);

        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco("12345678901234567890");

        boolean result =
                (boolean) method.invoke(
                        service,
                        "12345678901234567890",
                        dto
                );

        assertTrue(result);
    }

    @Test
    void validarContratoDebeRetornarFalse() throws Exception {

        Method method =
                OCService.class.getDeclaredMethod(
                        "validarContrato",
                        String.class,
                        Pem758ADto.class
                );

        method.setAccessible(true);

        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco("99999999999999999999");

        boolean result =
                (boolean) method.invoke(
                        service,
                        "12345678901234567890",
                        dto
                );

        assertFalse(result);
    }

    private Exchange exchangeValido() {

        Exchange exchange = ExchangeBuilder
                .anExchange(camelContext)
                .build();

        exchange.getIn().setHeader(
                "Authorization",
                "Bearer token"
        );

        exchange.getIn().setHeader(
                "X-Operative-Control-Rules",
                "$.numper,$.contractId"
        );

        exchange.getIn().setBody("""
            {
               "numper":"12345678",
               "contractId":"12345678901234567890"
            }
        """);

        return exchange;
    }

    private MockedStatic<SignedJWT> mockJwt(
            String acr,
            String numper
    ) throws Exception {

        SignedJWT signedJWT = mock(SignedJWT.class);

        JWTClaimsSet claims = mock(JWTClaimsSet.class);

        MockedStatic<SignedJWT> mocked =
                mockStatic(SignedJWT.class);

        mocked.when(() ->
                SignedJWT.parse(anyString())
        ).thenReturn(signedJWT);

        when(signedJWT.getJWTClaimsSet())
                .thenReturn(claims);

        when(claims.getStringClaim("acr"))
                .thenReturn(acr);

        when(claims.getStringClaim("numper"))
                .thenReturn(numper);

        when(claims.getStringClaim("PENUMPER"))
                .thenReturn(numper);

        when(claims.getStringClaim("penumper"))
                .thenReturn(numper);

        return mocked;
    }
}