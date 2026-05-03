Te dejo los tests separados clase por clase.
1. ContextAPIImplTest
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context.ContextResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ContextAPIImplTest {

    private final ContextAPI contextAPI = mock(ContextAPI.class);
    private final ContextAPIImpl service = new ContextAPIImpl(contextAPI);

    @Test
    void putContextShouldCallApi() throws Exception {
        Call<Void> call = mock(Call.class);
        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        service.putContext("key1", "value1");

        verify(contextAPI).putCache(any(ContextRequest.class));
        verify(call).execute();
    }

    @Test
    void putContextShouldHandleIOException() throws Exception {
        Call<Void> call = mock(Call.class);
        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("error"));

        assertDoesNotThrow(() -> service.putContext("key1", "value1"));
    }

    @Test
    void putContextShouldHandleRuntimeException() {
        when(contextAPI.putCache(any(ContextRequest.class))).thenThrow(new RuntimeException("runtime"));

        assertDoesNotThrow(() -> service.putContext("key1", "value1"));
    }

    @Test
    void getContextShouldReturnValue() throws Exception {
        ContextResponse contextResponse = new ContextResponse();
        contextResponse.setKey("key1");
        contextResponse.setProduct("cdt");
        contextResponse.setValue("value1");

        Call<ContextResponse> call = mock(Call.class);
        when(contextAPI.getCache("key1", "cdt")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(contextResponse));

        Object result = service.getContext("key1");

        assertEquals("value1", result);
    }

    @Test
    void getContextShouldReturnNullWhenIOException() throws Exception {
        Call<ContextResponse> call = mock(Call.class);
        when(contextAPI.getCache("key1", "cdt")).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("error"));

        Object result = service.getContext("key1");

        assertNull(result);
    }

    @Test
    void getContextShouldReturnNullWhenRuntimeException() {
        when(contextAPI.getCache("key1", "cdt")).thenThrow(new RuntimeException("runtime"));

        Object result = service.getContext("key1");

        assertNull(result);
    }
}
2. ParameterAPIImplTest
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParameterAPIImplTest {

    private final ParametersAPI parametersAPI = mock(ParametersAPI.class);
    private final ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

    private SecurityHeaders headers() {
        return new SecurityHeaders("Bearer token", "client-id");
    }

    @Test
    void getParameterShouldReturnParameters() throws Exception {
        GeographiesParametersResponseDTO body = new GeographiesParametersResponseDTO();
        body.setParameters(List.of(new DataListDTO("0112", "CO", "Colombia")));

        Call<GeographiesParametersResponseDTO> call = mock(Call.class);
        when(parametersAPI.getParameter("0112", "CO", "Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        List<DataListDTO> result = service.getParameter("0112", "CO", headers());

        assertEquals(1, result.size());
        assertEquals("CO", result.get(0).getCode());
    }

    @Test
    void getParameterShouldThrowWhenBodyIsNull() throws Exception {
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);
        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString())).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers()));
    }

    @Test
    void getParameterShouldThrowWhenParametersEmpty() throws Exception {
        GeographiesParametersResponseDTO body = new GeographiesParametersResponseDTO();
        body.setParameters(List.of());

        Call<GeographiesParametersResponseDTO> call = mock(Call.class);
        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString())).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers()));
    }

    @Test
    void getParameterShouldThrowWhenIOException() throws Exception {
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);
        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString())).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("network"));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers()));
    }

    @Test
    void getParameterShouldThrowWhenRuntimeException() {
        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers()));
    }
}
3. TrxPersonAPIImplTest
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrxPersonAPIImplTest {

    private final TrxPersonAPI trxPersonAPI = mock(TrxPersonAPI.class);
    private TrxPersonAPIImpl service;

    @BeforeEach
    void setUp() {
        service = new TrxPersonAPIImpl(trxPersonAPI, new ObjectMapper());
        ReflectionTestUtils.setField(service, "mqRoute", "QCTFD");
        ReflectionTestUtils.setField(service, "channel", "60");
        ReflectionTestUtils.setField(service, "user", "@NETE781");
    }

    @Test
    void callPostTRXShouldReturnResponseWhenSuccessful() throws Exception {
        TrxPersonResponse body = new TrxPersonResponse();
        body.setOk(true);

        Call<TrxPersonResponse> call = mock(Call.class);
        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        TrxPersonResponse result = service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF3);

        assertNotNull(result);
        assertTrue(result.getOk());
    }

    @Test
    void callPostTRXShouldThrowWhenIOException() throws Exception {
        Call<TrxPersonResponse> call = mock(Call.class);
        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("network"));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF3));
    }

    @Test
    void callPostTRXShouldThrowWhenRuntimeException() {
        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF3));
    }

    @Test
    void callPostTRXShouldThrowCustomerNotFoundWhenPersonDoesNotExist() {
        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("PERSONA INEXISTENTE"));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF3));
    }
}
4. CustomerControllerTest
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.CustomerDetailsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private final CustomerService customerService = mock(CustomerService.class);
    private final CustomerController controller = new CustomerController(customerService, new ObjectMapper());

    @Test
    void searchCustomersShouldReturnOk() {
        CustomerSearchResponseDTO responseDTO = new CustomerSearchResponseDTO();

        when(customerService.searchCustomer(any(), any())).thenReturn(responseDTO);

        ResponseEntity<CustomerSearchResponseDTO> response =
                controller.searchCustomers(new CustomerRequestDTO(), "auth", "client");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(responseDTO, response.getBody());
    }

    @Test
    void searchCustomersShouldReturnNoContentWhenNull() {
        when(customerService.searchCustomer(any(), any())).thenReturn(null);

        ResponseEntity<CustomerSearchResponseDTO> response =
                controller.searchCustomers(new CustomerRequestDTO(), "auth", "client");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getCustomersShouldReturnOk() {
        CustomerDetailsResponseDTO responseDTO = new CustomerDetailsResponseDTO();

        when(customerService.getCustomerDetails(anyString(), any())).thenReturn(responseDTO);

        ResponseEntity<CustomerDetailsResponseDTO> response =
                controller.getCustomers("12345678", "auth", "client");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(responseDTO, response.getBody());
    }

    @Test
    void updateCustomersShouldReturnNoContent() {
        ResponseEntity<Object> response =
                controller.updateCustomers("12345678", new CreateCustomerRequestDTO(), "auth", "client");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService).updateCustomer(any(), eq("12345678"), any());
    }

    @Test
    void updateCustomersProspectShouldReturnNoContent() {
        ResponseEntity<Object> response =
                controller.updateCustomersProspect("12345678", new UpdateProspectRequestDTO(), "auth", "client");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService).updateCustomersProspect(any(), eq("12345678"), any());
    }
}
5. IntegrationDataConfigurationTest
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.config;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationDataConfigurationTest {

    @Test
    void getByApiShouldReturnApiEntry() {
        ApiEntry entry = new ApiEntry();
        entry.setIntegrationType("parameters");
        entry.setHost("localhost");
        entry.setPort("8080");
        entry.setEndpoint("/test");
        entry.setHttps(false);
        entry.setTimeOutConn(1000);
        entry.setTimeOutRead(2000);

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(entry));

        ApiEntry result = config.getByApi("parameters");

        assertNotNull(result);
        assertEquals("parameters", result.getIntegrationType());
        assertEquals("localhost", result.getHost());
    }

    @Test
    void getByApiShouldReturnNullWhenNotFound() {
        ApiEntry entry = new ApiEntry();
        entry.setIntegrationType("parameters");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(entry));

        ApiEntry result = config.getByApi("trx_person");

        assertNull(result);
    }
}
Ojo: en TrxPersonAPIImplTest, si tu constructor generado por Lombok tiene otro orden, ajusta esta línea:
Java
service = new TrxPersonAPIImpl(trxPersonAPI, new ObjectMapper());