package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

class OCServiceTest {

    private OCService service;
    private RestTemplate restTemplate;
    private CamelContext camelContext;

    @BeforeEach
    void setUp() {

        restTemplate = mock(RestTemplate.class);
        camelContext = new DefaultCamelContext();

        service = new OCService(camelContext, restTemplate);

        ReflectionTestUtils.setField(service, "ACR_DEFINED", "1");
        ReflectionTestUtils.setField(service, "OPERATIVE_CONTROL_URL", "http://localhost");
        ReflectionTestUtils.setField(service, "PUBLIC_KEY", "PUBLIC_KEY");
        ReflectionTestUtils.setField(service, "TD_AUTHORIZATION", "TOKEN");

        ReflectionTestUtils.setField(service, "SANBA_URL", "http://localhost");
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

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();

        exchange.getIn().setHeader("octest", "true");

        boolean result = service.validatePenumperAndContracts(exchange);

        assertTrue(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoAuthorizationEsNull() {

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();

        boolean result = service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoJwtEsInvalido() {

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();

        exchange.getIn().setHeader("Authorization", "Bearer token_invalido");

        boolean result = service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoPenumperNoEsValido() {

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();

        exchange.getIn().setHeader("Authorization", "Bearer invalido");
        exchange.getIn().setHeader("X-Operative-Control-Rules", "$.penumper");

        boolean result = service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoContractIdNoEsValido() {

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();

        exchange.getIn().setHeader("Authorization", "Bearer invalido");
        exchange.getIn().setHeader("X-Operative-Control-Rules", "$.contractId");

        exchange.getIn().setBody("""
            {
               "contractId":"123"
            }
        """);

        boolean result = service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }

    @Test
    void validateDebeRetornarFalseCuandoOcurreExcepcion() {

        Exchange exchange = mock(Exchange.class);

        when(exchange.getIn()).thenThrow(new RuntimeException("ERROR"));

        boolean result = service.validatePenumperAndContracts(exchange);

        assertFalse(result);
    }
}