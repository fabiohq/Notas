package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.ApiEntry;

class IntegrationDataConfigurationTest {

    @Test
    void shouldGetApiEntryByIntegrationType() {
        IntegrationDataConfiguration configuration =
                new IntegrationDataConfiguration();

        ApiEntry apiEntry = buildApiEntry("trx_person");

        configuration.setCatalogue(List.of(apiEntry));

        ApiEntry result = configuration.getByApi("trx_person");

        assertEquals(apiEntry, result);
    }

    @Test
    void shouldReturnNullWhenIntegrationTypeDoesNotExist() {
        IntegrationDataConfiguration configuration =
                new IntegrationDataConfiguration();

        ApiEntry apiEntry = buildApiEntry("parameters");

        configuration.setCatalogue(List.of(apiEntry));

        ApiEntry result = configuration.getByApi("context");

        assertNull(result);
    }

    private ApiEntry buildApiEntry(String integrationType) {
        ApiEntry apiEntry = new ApiEntry();

        apiEntry.setIntegrationType(integrationType);
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setEndpoint("/");
        apiEntry.setHttps(false);
        apiEntry.setTimeOutConn(1);
        apiEntry.setTimeOutRead(1);

        return apiEntry;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.ApiEntry;

class RestClientConfigTest {

    @Test
    void shouldCreateTrxPersonApiBean() {
        RestClientConfig config =
                new RestClientConfig(buildIntegrationDataConfiguration());

        TrxPersonAPI api = config.txrTransactionApi();

        assertNotNull(api);
    }

    @Test
    void shouldCreateParametersApiBean() {
        RestClientConfig config =
                new RestClientConfig(buildIntegrationDataConfiguration());

        ParametersAPI api = config.parametersAPI();

        assertNotNull(api);
    }

    @Test
    void shouldCreateContextApiBean() {
        RestClientConfig config =
                new RestClientConfig(buildIntegrationDataConfiguration());

        ContextAPI api = config.contextAPI();

        assertNotNull(api);
    }

    private IntegrationDataConfiguration buildIntegrationDataConfiguration() {
        IntegrationDataConfiguration configuration =
                new IntegrationDataConfiguration();

        configuration.setCatalogue(List.of(
                buildApiEntry("trx_person"),
                buildApiEntry("parameters"),
                buildApiEntry("context")
        ));

        return configuration;
    }

    private ApiEntry buildApiEntry(String integrationType) {
        ApiEntry apiEntry = new ApiEntry();

        apiEntry.setIntegrationType(integrationType);
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setEndpoint("/");
        apiEntry.setHttps(false);
        apiEntry.setTimeOutConn(1);
        apiEntry.setTimeOutRead(1);

        return apiEntry;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.ProspectContactPointsService;

class ProspectContactPointsControllerTest {

    private ProspectContactPointsController controller;
    private ProspectContactPointsService customerService;
    private TrxPersonService trxPersonService;

    @BeforeEach
    void setUp() throws Exception {
        customerService = Mockito.mock(ProspectContactPointsService.class);
        trxPersonService = Mockito.mock(TrxPersonService.class);

        controller = new ProspectContactPointsController(new ObjectMapper());

        setField(controller, "customerService", customerService);
        setField(controller, "trxPersonService", trxPersonService);
    }

    @Test
    void shouldGetCustomersReturnOk() {
        ContactPointsResponseDTO responseDTO = new ContactPointsResponseDTO();

        when(customerService.getCustomerDetails(
                eq("123"),
                any(SecurityHeaders.class)))
                .thenReturn(responseDTO);

        ResponseEntity<ContactPointsResponseDTO> response =
                controller.getCustomers("123", "Bearer token", "client-id");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void shouldGetCustomersReturnNoContentWhenResponseIsNull() {
        when(customerService.getCustomerDetails(
                eq("123"),
                any(SecurityHeaders.class)))
                .thenReturn(null);

        ResponseEntity<ContactPointsResponseDTO> response =
                controller.getCustomers("123", "Bearer token", "client-id");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldPatchProspectContactPointsReturnNoContent() {
        ContactPointsRequestDTO requestDTO = new ContactPointsRequestDTO();

        doNothing().when(customerService).patchProspectContactPoint(
                eq("123"),
                eq("456"),
                eq(requestDTO),
                any(SecurityHeaders.class));

        ResponseEntity<Object> response =
                controller.patchProspectContactPoints(
                        "Bearer token",
                        "client-id",
                        "123",
                        "456",
                        requestDTO
                );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void setField(Object target, String fieldName, Object value)
            throws Exception {

        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}



