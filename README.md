package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
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

    private static final String CONTRACT_ID = "12345678901234567890";
    private static final String PENUMPER = "12345678";
    private static final String LAST_12_CONTRACT = "901234567890";

    @BeforeEach
    void setUp() {
        camelContext = new DefaultCamelContext();
        restTemplate = mock(RestTemplate.class);
        service = new OCService(camelContext, restTemplate);

        ReflectionTestUtils.setField(service, "TD_AUTHORIZATION", "Bearer td-token");
        ReflectionTestUtils.setField(service, "PUBLIC_KEY", "public-key");
        ReflectionTestUtils.setField(service, "OPERATIVE_CONTROL_URL", "http://localhost:8081");
        ReflectionTestUtils.setField(service, "ACR_DEFINED", "1");
        ReflectionTestUtils.setField(service, "SANBA_URL", "http://sanba");
        ReflectionTestUtils.setField(service, "SANBA_CHANNEL", "60");
        ReflectionTestUtils.setField(service, "SANBA_USER", "ODSUSU01");
        ReflectionTestUtils.setField(service, "SANBA_MQROUTE", "QCTFD");
    }

    @Test
    void getCamelContextDebeRetornarContextoInyectado() {
        assertSame(camelContext, service.getCamelContext());
    }

    @Test
    void validateDebeRetornarTrueCuandoExisteHeaderOctest() {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("octest", "true");

        assertTrue(service.validatePenumperAndContracts(exchange));
        verifyNoInteractions(restTemplate);
    }

    @Test
    void validateDebeRetornarFalseCuandoExchangeLanzaExcepcion() {
        Exchange exchange = mock(Exchange.class);
        when(exchange.getIn()).thenThrow(new RuntimeException("error"));

        assertFalse(service.validatePenumperAndContracts(exchange));
    }

    @Test
    void validateDebeRetornarFalseCuandoAuthorizationNoExiste() {
        Exchange exchange = nuevoExchange();

        assertFalse(service.validatePenumperAndContracts(exchange));
    }

    @Test
    void validateDebeRetornarFalseCuandoAuthorizationNoEsBearer() {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "token");

        assertFalse(service.validatePenumperAndContracts(exchange));
    }

    @Test
    void validateDebeRetornarTrueCuandoAcrNoCoincide() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");

        try (MockedStatic<SignedJWT> ignored = mockJwt("5", PENUMPER)) {
            assertTrue(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarTrueCuandoNoHayReglasActivas() throws Exception {
        Exchange exchange = exchangeBase(reglasVacias());
        exchange.getIn().setBody("{}");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertTrue(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoPenumperBodyEsInvalido() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperBody());
        exchange.getIn().setBody("{\"numper\":\"123\"}");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoPenumperNoCoincideConJwt() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperBody());
        exchange.getIn().setBody("{\"numper\":\"87654321\"}");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoContratoUrlEsInvalido() throws Exception {
        Exchange exchange = exchangeBase(reglaContratoUrl());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER + "/contratos/123");
        exchange.getIn().setBody("{}");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoSanbaNoRetornaPem758A() throws Exception {
        Exchange exchange = exchangeConPenumperYContrato();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{\"data\":{}}"));

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarTrueCuandoContratoCoincideConSanba() throws Exception {
        Exchange exchange = exchangeConPenumperYContrato();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("""
                    {
                      "data": {
                        "PEM758A": {
                          "PENUMCO": "901234567890"
                        }
                      }
                    }
                    """));

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertTrue(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoContratoNoCoincideConSanba() throws Exception {
        Exchange exchange = exchangeConPenumperYContrato();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("""
                    {
                      "data": {
                        "PEM758A": {
                          "PENUMCO": "000000000000"
                        }
                      }
                    }
                    """));

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoSanbaRetornaJsonInvalido() throws Exception {
        Exchange exchange = exchangeConPenumperYContrato();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{json-invalido"));

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void validateDebeRetornarFalseCuandoRestTemplateLanzaExcepcion() throws Exception {
        Exchange exchange = exchangeConPenumperYContrato();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new RuntimeException("error sanba"));

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertFalse(service.validatePenumperAndContracts(exchange));
        }
    }

    @Test
    void extractPenumperDebeExtraerDesdeUrl() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperUrl());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER);
        exchange.getIn().setBody("{}");

        assertEquals(PENUMPER, invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperDebeExtraerDesdeQueryParam() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperQueryParam());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes?participant_id=" + PENUMPER);
        exchange.getIn().setBody("{}");

        assertEquals(PENUMPER, invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperQueryParamSinQueryDebeRetornarVacio() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperQueryParam());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes");
        exchange.getIn().setBody("{}");

        assertEquals("", invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperDebeExtraerDesdeBody() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperBody());
        exchange.getIn().setBody("{\"numper\":\"" + PENUMPER + "\"}");

        assertEquals(PENUMPER, invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperDebeExtraerDesdeHeader() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperHeader());
        exchange.getIn().setHeader("X-PENUMPER", PENUMPER);
        exchange.getIn().setBody("{}");

        assertEquals(PENUMPER, invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperDebeRetornarVacioCuandoLocationYPositionVacios() throws Exception {
        Exchange exchange = exchangeBase(reglaPenumperSinLocation());
        exchange.getIn().setBody("{}");

        assertEquals("", invoke("extractPENUMPERBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperDebeLanzarExcepcionCuandoLocationEsDesconocida() {
        Exchange exchange = exchangeBase(reglaPenumperLocationDesconocida());
        exchange.getIn().setBody("{}");

        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
                () -> invoke("extractPENUMPERBasedOnRule", new Class[] { Exchange.class }, exchange));

        assertTrue(ex.getCause() instanceof IllegalArgumentException);
    }

    @Test
    void extractContractIdDebeExtraerDesdeUrl() throws Exception {
        Exchange exchange = exchangeBase(reglaContratoUrl());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER + "/contratos/" + CONTRACT_ID);
        exchange.getIn().setBody("{}");

        assertEquals(CONTRACT_ID, invoke("extractContractIdBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractContractIdDebeRetornarVacioCuandoLocationYPositionVacios() throws Exception {
        Exchange exchange = exchangeBase(reglaContratoSinLocation());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER + "/contratos/" + CONTRACT_ID);
        exchange.getIn().setBody("{}");

        assertEquals("", invoke("extractContractIdBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractContractIdDebeRetornarVacioCuandoNoHaceMatch() throws Exception {
        Exchange exchange = exchangeBase(reglaContratoUrl());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/otra/ruta");
        exchange.getIn().setBody("{}");

        assertEquals("", invoke("extractContractIdBasedOnRule",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperFromJwtDebeRetornarClaim() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertEquals(PENUMPER, invoke("extractPENUMPERFromJwt",
                    new Class[] { Exchange.class }, exchange));
        }
    }

    @Test
    void extractPenumperFromJwtDebeRetornarNullCuandoNoEsBearer() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "token");

        assertNull(invoke("extractPENUMPERFromJwt",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractPenumperFromJwtDebeRetornarNullCuandoJwtFalla() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");

        try (MockedStatic<SignedJWT> mocked = mockStatic(SignedJWT.class)) {
            mocked.when(() -> SignedJWT.parse(anyString())).thenThrow(new RuntimeException("jwt error"));

            assertNull(invoke("extractPENUMPERFromJwt",
                    new Class[] { Exchange.class }, exchange));
        }
    }

    @Test
    void extractAcrDebeRetornarClaimAcr() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");

        try (MockedStatic<SignedJWT> ignored = mockJwt("1", PENUMPER)) {
            assertEquals("1", invoke("extractAcr",
                    new Class[] { Exchange.class }, exchange));
        }
    }

    @Test
    void extractAcrDebeRetornarNullCuandoNoEsBearer() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "token");

        assertNull(invoke("extractAcr",
                new Class[] { Exchange.class }, exchange));
    }

    @Test
    void extractAcrDebeRetornarNullCuandoJwtFalla() throws Exception {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");

        try (MockedStatic<SignedJWT> mocked = mockStatic(SignedJWT.class)) {
            mocked.when(() -> SignedJWT.parse(anyString())).thenThrow(new RuntimeException("jwt error"));

            assertNull(invoke("extractAcr",
                    new Class[] { Exchange.class }, exchange));
        }
    }

    @Test
    void isValidPenumperDebeCubrirTrueYFalse() throws Exception {
        assertTrue((boolean) invoke("isValidPenumper", new Class[] { String.class }, PENUMPER));
        assertFalse((boolean) invoke("isValidPenumper", new Class[] { String.class }, "123"));
        assertFalse((boolean) invoke("isValidPenumper", new Class[] { String.class }, "ABCDEFGH"));
    }

    @Test
    void isValidContractIdDebeCubrirTrueYFalse() throws Exception {
        assertTrue((boolean) invoke("isValidContractId", new Class[] { String.class }, CONTRACT_ID));
        assertFalse((boolean) invoke("isValidContractId", new Class[] { String.class }, "123"));
        assertFalse((boolean) invoke("isValidContractId", new Class[] { String.class }, "ABCDEFGHIJKLMNOPQRST"));
    }

    @Test
    void transformServicePathDebeRetornarSlashCuandoServicePathEsSlash() throws Exception {
        assertEquals("/", invoke("transformServicePath",
                new Class[] { String.class, String.class }, "/", "/"));
    }

    @Test
    void transformServicePathDebeTransformarCustomerDepositYQuery() throws Exception {
        String result = (String) invoke("transformServicePath",
                new Class[] { String.class, String.class },
                "/clientes/" + PENUMPER + "/contratos/" + CONTRACT_ID + "?participant_id=" + PENUMPER + "&x=ABC",
                "/clientes/{customer_id}/contratos/{deposit_id}");

        assertEquals("/clientes/{customer_id}/contratos/{deposit_id}?participant_id={customer_id}&x={randomNum}", result);
    }

    @Test
    void transformServicePathDebeTransformarPlaceholderGenerico() throws Exception {
        String result = (String) invoke("transformServicePath",
                new Class[] { String.class, String.class },
                "/clientes/ABC",
                "/clientes/{otro}");

        assertEquals("/clientes/{randomNum}", result);
    }

    @Test
    void transformQueryStringDebeTransformarParticipantIdValidoYOtrosParametros() throws Exception {
        String result = (String) invoke("transformQueryString",
                new Class[] { String.class },
                "participant_id=" + PENUMPER + "&empty=&solo");

        assertEquals("participant_id={customer_id}&empty={randomNum}&solo={randomNum}", result);
    }

    @Test
    void extractPathDebeUsarCamelHttpUriCuandoEmpiezaConBasePath() throws Exception {
        String result = (String) invoke("extractPath",
                new Class[] { String.class, String.class },
                "http://localhost/no-usado",
                "/operativecontrol/validation/clientes/" + PENUMPER);

        assertEquals("/clientes/" + PENUMPER, result);
    }

    @Test
    void extractPathDebeExtraerPathYQueryDesdeServiceEndpoint() throws Exception {
        String result = (String) invoke("extractPath",
                new Class[] { String.class, String.class },
                "http://localhost/clientes?participant_id=" + PENUMPER,
                null);

        assertEquals("/clientes?participant_id=" + PENUMPER, result);
    }

    @Test
    void extractPathDebeRetornarVacioCuandoUrlEsMalFormada() throws Exception {
        String result = (String) invoke("extractPath",
                new Class[] { String.class, String.class },
                "url-mal-formada",
                null);

        assertEquals("", result);
    }

    @Test
    void extractContractBySanbaDebeRetornarDtoCuandoResponseEsValido() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("""
                    {
                      "data": {
                        "PEM758A": {
                          "PENUMCO": "901234567890"
                        }
                      }
                    }
                    """));

        Pem758ADto result = service.extractContractBySanba(PENUMPER, CONTRACT_ID);

        assertNotNull(result);
        assertEquals(LAST_12_CONTRACT, result.getPenumco());
    }

    @Test
    void extractContractBySanbaDebeRetornarNullCuandoNoExistePem758A() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{\"data\":{}}"));

        assertNull(service.extractContractBySanba(PENUMPER, CONTRACT_ID));
    }

    @Test
    void extractContractBySanbaDebeRetornarNullCuandoJsonEsInvalido() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{json-invalido"));

        assertNull(service.extractContractBySanba(PENUMPER, CONTRACT_ID));
    }

    @Test
    void validarContratoDebeRetornarTrueCuandoUltimos12Coinciden() {
        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco(LAST_12_CONTRACT);

        assertTrue(service.validarContrato(CONTRACT_ID, dto));
    }

    @Test
    void validarContratoDebeRetornarFalseCuandoUltimos12NoCoinciden() {
        Pem758ADto dto = new Pem758ADto();
        dto.setPenumco("000000000000");

        assertFalse(service.validarContrato(CONTRACT_ID, dto));
    }

    private Exchange exchangeConPenumperYContrato() {
        Exchange exchange = exchangeBase(reglasPenumperBodyYContratoUrl());
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER + "/contratos/" + CONTRACT_ID);
        exchange.getIn().setBody("{\"numper\":\"" + PENUMPER + "\"}");
        return exchange;
    }

    private Exchange exchangeBase(String reglas) {
        Exchange exchange = nuevoExchange();
        exchange.getIn().setHeader("Authorization", "Bearer token");
        exchange.getIn().setHeader("serviceEndpoint", "http://localhost/clientes/" + PENUMPER + "/contratos/" + CONTRACT_ID);
        exchange.getIn().setHeader("CamelHttpUri", null);
        exchange.getIn().setHeader("serviceMethod", "GET");
        exchange.getIn().setHeader("X-Operative-Control-Rules", reglas);
        return exchange;
    }

    private Exchange nuevoExchange() {
        return ExchangeBuilder.anExchange(camelContext).build();
    }

    private Object invoke(String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
        Method method = OCService.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(service, args);
    }

    private MockedStatic<SignedJWT> mockJwt(String acr, String attPersonNumberCode) throws Exception {
        SignedJWT signedJWT = mock(SignedJWT.class);
        JWTClaimsSet claimsSet = mock(JWTClaimsSet.class);

        MockedStatic<SignedJWT> mocked = mockStatic(SignedJWT.class);
        mocked.when(() -> SignedJWT.parse(anyString())).thenReturn(signedJWT);

        when(signedJWT.getJWTClaimsSet()).thenReturn(claimsSet);
        when(claimsSet.getStringClaim("acr")).thenReturn(acr);
        when(claimsSet.getStringClaim("attPersonNumberCode")).thenReturn(attPersonNumberCode);

        return mocked;
    }

    private String reglasVacias() {
        return """
            {
              "operativeControl": {
                "rules": []
              }
            }
            """;
    }

    private String reglaPenumperBody() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "body",
                    "clientPosition": "$.numper",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaPenumperHeader() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "header",
                    "clientPosition": "X-PENUMPER",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaPenumperUrl() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}",
                    "method": "GET",
                    "clientUbication": "url",
                    "clientPosition": "3",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaPenumperQueryParam() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes?participant_id={customer_id}",
                    "method": "GET",
                    "clientUbication": "url",
                    "clientPosition": "queryparam",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaPenumperSinLocation() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "",
                    "clientPosition": "",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaPenumperLocationDesconocida() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "otro",
                    "clientPosition": "$.numper",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglaContratoUrl() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "",
                    "clientPosition": "",
                    "cccUbication": "url",
                    "cccPosition": "5"
                  }
                ]
              }
            }
            """;
    }

    private String reglaContratoSinLocation() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "",
                    "clientPosition": "",
                    "cccUbication": "",
                    "cccPosition": ""
                  }
                ]
              }
            }
            """;
    }

    private String reglasPenumperBodyYContratoUrl() {
        return """
            {
              "operativeControl": {
                "rules": [
                  {
                    "active": true,
                    "path": "/clientes/{customer_id}/contratos/{deposit_id}",
                    "method": "GET",
                    "clientUbication": "body",
                    "clientPosition": "$.numper",
                    "cccUbication": "url",
                    "cccPosition": "5"
                  }
                ]
              }
            }
            """;
    }
}
