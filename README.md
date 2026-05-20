package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

class AppConfigTest {

    @Test
    void restTemplateDebeConstruirseCorrectamente() {
        AppConfig config = new AppConfig();

        RestTemplate restTemplate = config.restTemplate(new RestTemplateBuilder());

        assertNotNull(restTemplate);
    }
}


package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services.OCService;

class OCControllerTest {

    @Test
    void validateOperativeControlDebeRetornarOkCuandoValidacionExitosa() {
        OCService ocService = mock(OCService.class);
        CamelContext camelContext = new DefaultCamelContext();

        when(ocService.getCamelContext()).thenReturn(camelContext);
        when(ocService.validatePenumperAndContracts(any())).thenReturn(true);

        OCController controller = new OCController(ocService, new ObjectMapper());

        ResponseEntity<String> response = controller.validateOperativeControl(
                "Bearer token",
                "$.penumper",
                "/endpoint",
                "POST",
                "{\"penumper\":\"12345678\"}"
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("PENUMPER and/or CONTRACT validation successful", response.getBody());
    }

    @Test
    void validateOperativeControlDebeRetornarUnauthorizedCuandoValidacionFalla() {
        OCService ocService = mock(OCService.class);
        CamelContext camelContext = new DefaultCamelContext();

        when(ocService.getCamelContext()).thenReturn(camelContext);
        when(ocService.validatePenumperAndContracts(any())).thenReturn(false);

        OCController controller = new OCController(ocService, new ObjectMapper());

        ResponseEntity<String> response = controller.validateOperativeControl(
                "Bearer token",
                "$.penumper",
                "/endpoint",
                "POST",
                "{}"
        );

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("PENUMPE or Contract validation failed", response.getBody());
    }

    @Test
    void validateOperativeControlDebeRetornarInternalServerErrorCuandoOcurreExcepcion() {
        OCService ocService = mock(OCService.class);

        when(ocService.getCamelContext()).thenThrow(new RuntimeException("Error"));

        OCController controller = new OCController(ocService, new ObjectMapper());

        ResponseEntity<String> response = controller.validateOperativeControl(
                "Bearer token",
                "$.penumper",
                "/endpoint",
                "POST",
                "{}"
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.Models.Dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Pem758ADtoTest {

    @Test
    void gettersYSettersDebenFuncionarCorrectamente() {
        Pem758ADto dto = new Pem758ADto();

        dto.setPecodpr("001");
        dto.setPecodsu("002");
        dto.setPesecdo(3);
        dto.setPedesco("descripcion");
        dto.setPecoden("004");
        dto.setPetipvi("V");
        dto.setPehstam("H");
        dto.setPeobse2("obs2");
        dto.setPenomca("nombre");
        dto.setNbpobla("poblacion");
        dto.setPecdgen("gen");
        dto.setNumper("12345678");
        dto.setPecodof("oficina");
        dto.setPenumco("12345678901234567890");
        dto.setPeobse1("obs1");

        assertEquals("001", dto.getPecodpr());
        assertEquals("002", dto.getPecodsu());
        assertEquals(3, dto.getPesecdo());
        assertEquals("descripcion", dto.getPedesco());
        assertEquals("004", dto.getPecoden());
        assertEquals("V", dto.getPetipvi());
        assertEquals("H", dto.getPehstam());
        assertEquals("obs2", dto.getPeobse2());
        assertEquals("nombre", dto.getPenomca());
        assertEquals("poblacion", dto.getNbpobla());
        assertEquals("gen", dto.getPecdgen());
        assertEquals("12345678", dto.getNumper());
        assertEquals("oficina", dto.getPecodof());
        assertEquals("12345678901234567890", dto.getPenumco());
        assertEquals("obs1", dto.getPeobse1());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import com.sun.net.httpserver.HttpServer;

class ExternalApisHealthIndicatorTest {

    @Test
    void healthDebeRetornarUpCuandoApiCriticaRespondeStatusAceptado() throws IOException {
        HttpServer server = crearServidor(200);

        try {
            ExternalApisHealthProperties properties = crearProperties(
                    "api-test",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(200)
            );

            ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
            assertTrue(health.getDetails().containsKey("api-test"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void healthDebeRetornarDownCuandoApiCriticaNoRespondeStatusAceptado() throws IOException {
        HttpServer server = crearServidor(500);

        try {
            ExternalApisHealthProperties properties = crearProperties(
                    "api-test",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    true,
                    List.of(200)
            );

            ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.DOWN, health.getStatus());

            Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api-test");
            assertEquals("DOWN", detail.get("status"));
            assertEquals(500, detail.get("httpStatus"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void healthDebeRetornarUpCuandoApiNoCriticaFalla() throws IOException {
        HttpServer server = crearServidor(500);

        try {
            ExternalApisHealthProperties properties = crearProperties(
                    "api-no-critica",
                    "http://localhost:" + server.getAddress().getPort() + "/health",
                    false,
                    List.of(200)
            );

            ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals(Status.UP, health.getStatus());
        } finally {
            server.stop(0);
        }
    }

    private HttpServer crearServidor(int status) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(status, -1);
            exchange.close();
        });
        server.start();
        return server;
    }

    private ExternalApisHealthProperties crearProperties(
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

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.observability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

    @Test
    void gettersYSettersDebenFuncionarCorrectamente() {
        ExternalApisHealthProperties.ApiCheck apiCheck = new ExternalApisHealthProperties.ApiCheck();
        apiCheck.setName("backend-for-frontend");
        apiCheck.setUrl("http://localhost:8080/actuator/health");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 404));

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(5000);
        properties.setChecks(List.of(apiCheck));

        assertEquals(5000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());

        ExternalApisHealthProperties.ApiCheck result = properties.getChecks().get(0);
        assertEquals("backend-for-frontend", result.getName());
        assertEquals("http://localhost:8080/actuator/health", result.getUrl());
        assertFalse(result.isCritical());
        assertEquals(List.of(200, 404), result.getAcceptedStatuses());
    }

    @Test
    void valoresPorDefectoDebenSerCorrectos() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        ExternalApisHealthProperties.ApiCheck apiCheck = new ExternalApisHealthProperties.ApiCheck();

        assertEquals(2000, properties.getTimeoutMs());
        assertTrue(properties.getChecks().isEmpty());
        assertTrue(apiCheck.isCritical());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class OCServiceTest {

    @Test
    void getCamelContextDebeRetornarContextoInyectado() {
        CamelContext camelContext = new DefaultCamelContext();
        RestTemplate restTemplate = mock(RestTemplate.class);

        OCService service = new OCService(camelContext, restTemplate);

        assertSame(camelContext, service.getCamelContext());
    }

    @Test
    void validatePenumperAndContractsDebeRetornarTrueCuandoExisteHeaderOctest() {
        CamelContext camelContext = new DefaultCamelContext();
        RestTemplate restTemplate = mock(RestTemplate.class);

        OCService service = new OCService(camelContext, restTemplate);

        Exchange exchange = ExchangeBuilder.anExchange(camelContext).build();
        exchange.getIn().setHeader("octest", "true");

        boolean result = service.validatePenumperAndContracts(exchange);

        assertTrue(result);
    }
}

