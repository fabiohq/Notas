Aquí tienes tests para esas clases.
ContextAPIImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.context.ContextResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContextAPIImplTest {

    @Test
    void shouldPutContext() throws Exception {
        ContextAPI contextAPI = mock(ContextAPI.class);
        Call<Void> call = mock(Call.class);

        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        service.putContext("KEY", "VALUE");

        verify(contextAPI).putCache(any(ContextRequest.class));
        verify(call).execute();
    }

    @Test
    void shouldNotThrowWhenPutContextFails() throws Exception {
        ContextAPI contextAPI = mock(ContextAPI.class);
        Call<Void> call = mock(Call.class);

        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("ERROR"));

        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        assertDoesNotThrow(() -> service.putContext("KEY", "VALUE"));
    }

    @Test
    void shouldGetContext() throws Exception {
        ContextAPI contextAPI = mock(ContextAPI.class);
        Call<ContextResponse> call = mock(Call.class);

        ContextResponse response = new ContextResponse();
        response.setValue("VALUE");

        when(contextAPI.getCache("KEY", "cdt")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(response));

        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        Object result = service.getContext("KEY");

        assertEquals("VALUE", result);
    }

    @Test
    void shouldReturnNullWhenGetContextFails() throws Exception {
        ContextAPI contextAPI = mock(ContextAPI.class);
        Call<ContextResponse> call = mock(Call.class);

        when(contextAPI.getCache("KEY", "cdt")).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("ERROR"));

        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        assertNull(service.getContext("KEY"));
    }
}
CustomerContactPointsControllerTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.CustomerContactPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerContactPointsControllerTest {

    @Test
    void shouldGetCustomersOk() {
        TrxPersonService trxPersonService = mock(TrxPersonService.class);
        CustomerContactPointsService customerService = mock(CustomerContactPointsService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        ContactPointsResponseDTO serviceResponse = new ContactPointsResponseDTO();

        when(customerService.getCustomerDetails(eq("12345678"), any()))
                .thenReturn(serviceResponse);

        CustomerContactPointsController controller =
                new CustomerContactPointsController(trxPersonService, customerService, objectMapper);

        ResponseEntity<ContactPointsResponseDTO> response =
                controller.getCustomers("12345678", "Bearer token", "client");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(serviceResponse, response.getBody());
    }

    @Test
    void shouldGetCustomersNoContentWhenServiceReturnsNull() {
        TrxPersonService trxPersonService = mock(TrxPersonService.class);
        CustomerContactPointsService customerService = mock(CustomerContactPointsService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        when(customerService.getCustomerDetails(eq("12345678"), any()))
                .thenReturn(null);

        CustomerContactPointsController controller =
                new CustomerContactPointsController(trxPersonService, customerService, objectMapper);

        ResponseEntity<ContactPointsResponseDTO> response =
                controller.getCustomers("12345678", "Bearer token", "client");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldPutCustomerContactPoints() {
        TrxPersonService trxPersonService = mock(TrxPersonService.class);
        CustomerContactPointsService customerService = mock(CustomerContactPointsService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        ContactPointsRequestDTO request = new ContactPointsRequestDTO();

        CustomerContactPointsController controller =
                new CustomerContactPointsController(trxPersonService, customerService, objectMapper);

        ResponseEntity<Object> response =
                controller.putCustomerContactPoints(
                        "Bearer token",
                        "client",
                        "12345678",
                        "PRI001",
                        request
                );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(customerService).putCustomerContactPoint(
                eq("12345678"),
                eq("PRI001"),
                same(request),
                any()
        );
    }
}
IntegrationDataConfigurationTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.config;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationDataConfigurationTest {

    @Test
    void shouldGetByApi() {
        ApiEntry trx = new ApiEntry();
        trx.setIntegrationType("trx_person");
        trx.setHost("localhost");

        ApiEntry context = new ApiEntry();
        context.setIntegrationType("context");
        context.setHost("context-host");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(trx, context));

        assertSame(trx, config.getByApi("trx_person"));
        assertSame(context, config.getByApi("context"));
        assertNull(config.getByApi("missing"));
    }
}
RestClientConfigTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.config;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestClientConfigTest {

    @Test
    void shouldCreateApis() {
        IntegrationDataConfiguration properties = mock(IntegrationDataConfiguration.class);

        when(properties.getByApi("trx_person")).thenReturn(apiEntry("trx_person"));
        when(properties.getByApi("parameters")).thenReturn(apiEntry("parameters"));
        when(properties.getByApi("context")).thenReturn(apiEntry("context"));

        RestClientConfig config = new RestClientConfig(properties);

        TrxPersonAPI trxPersonAPI = config.txrTransactionApi();
        ParametersAPI parametersAPI = config.parametersAPI();
        ContextAPI contextAPI = config.contextAPI();

        assertNotNull(trxPersonAPI);
        assertNotNull(parametersAPI);
        assertNotNull(contextAPI);
    }

    private ApiEntry apiEntry(String type) {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType(type);
        apiEntry.setHttps(false);
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setEndpoint("/");
        apiEntry.setTimeOutConn(1);
        apiEntry.setTimeOutRead(1);
        return apiEntry;
    }
}
Si ContextAPIImplTest falla por el tipo de putCache, cambia Call<Void> por el tipo exacto que retorne tu interfaz ContextAPI.