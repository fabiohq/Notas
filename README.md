Java
package com.santander.bnc.bsn049.bncbsn049msprospects.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.CiudadComparisonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ProspectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProspectControllerTest {

    private static final String AUTH = "Bearer token";
    private static final String CLIENT_ID = "client-id";

    @Mock
    private ProspectService prospectService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProspectController controller;

    @Test
    void searchCustomersShouldReturnOkWhenServiceReturnsResponse() throws Exception {
        ProspectRequestDTO request = new ProspectRequestDTO();
        ProspectSearchResponseDTO response = new ProspectSearchResponseDTO();

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(prospectService.searchProspect(request, AUTH, CLIENT_ID)).thenReturn(response);

        ResponseEntity<ProspectSearchResponseDTO> result =
                controller.searchCustomers(request, CLIENT_ID, AUTH);

        assertEquals(200, result.getStatusCode().value());
        assertSame(response, result.getBody());
        verify(prospectService).searchProspect(request, AUTH, CLIENT_ID);
    }

    @Test
    void searchCustomersShouldReturnNoContentWhenServiceReturnsNull() throws Exception {
        ProspectRequestDTO request = new ProspectRequestDTO();

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(prospectService.searchProspect(request, AUTH, CLIENT_ID)).thenReturn(null);

        ResponseEntity<ProspectSearchResponseDTO> result =
                controller.searchCustomers(request, CLIENT_ID, AUTH);

        assertEquals(204, result.getStatusCode().value());
        assertNull(result.getBody());
    }

    @Test
    void getProspectShouldReturnOkWhenServiceReturnsResponse() throws Exception {
        ProspectDetailResponseDTO response = new ProspectDetailResponseDTO();

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(prospectService.getProspectDetails("123", AUTH, CLIENT_ID)).thenReturn(response);

        ResponseEntity<ProspectDetailResponseDTO> result =
                controller.getProspect("123", AUTH, CLIENT_ID);

        assertEquals(200, result.getStatusCode().value());
        assertSame(response, result.getBody());
    }

    @Test
    void getProspectShouldThrowServiceExceptionWhenServiceReturnsNull() {
        when(prospectService.getProspectDetails("123", AUTH, CLIENT_ID)).thenReturn(null);

        assertThrows(ServiceException.class,
                () -> controller.getProspect("123", AUTH, CLIENT_ID));
    }

    @Test
    void createProspectShouldReturnCreated() throws Exception {
        CreateProspectRequestDTO request = new CreateProspectRequestDTO();
        ProspectCreatedResponseDTO response = new ProspectCreatedResponseDTO();
        response.setProspectId("999");

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(prospectService.createProspect(request, AUTH, CLIENT_ID)).thenReturn(response);

        ResponseEntity<ProspectCreatedResponseDTO> result =
                controller.createProspect(request, AUTH, CLIENT_ID);

        assertEquals(201, result.getStatusCode().value());
        assertEquals("999", result.getBody().getProspectId());
    }

    @Test
    void updateCustomersShouldReturnNoContent() throws Exception {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");

        ResponseEntity<Object> result =
                controller.updateCustomers("123", request, AUTH, CLIENT_ID);

        assertEquals(204, result.getStatusCode().value());
        assertNull(result.getBody());
        verify(prospectService).updateProspect(request, "123", AUTH, CLIENT_ID);
    }

    @Test
    void removeProspectShouldReturnNoContent() {
        ResponseEntity<ProspectDetailResponseDTO> result =
                controller.removeProspect("123", AUTH, CLIENT_ID);

        assertEquals(204, result.getStatusCode().value());
        assertNull(result.getBody());
        verify(prospectService).removeProspect("123", AUTH, CLIENT_ID);
    }

    @Test
    void compareCitiesShouldReturnTrueWhenCitiesAreSimilar() {
        CiudadComparisonRequest request = new CiudadComparisonRequest();
        request.setCiudadIngresada("Bogota");
        request.setCiudadServicio("Bogotá");

        ResponseEntity<Boolean> result = controller.compareCities(request);

        assertEquals(200, result.getStatusCode().value());
        assertTrue(result.getBody());
    }

    @Test
    void shouldContinueWhenObjectMapperFails() throws Exception {
        ProspectRequestDTO request = new ProspectRequestDTO();
        ProspectSearchResponseDTO response = new ProspectSearchResponseDTO();

        when(objectMapper.writeValueAsString(any()))
                .thenThrow(new RuntimeException("serialization error"));
        when(prospectService.searchProspect(request, AUTH, CLIENT_ID)).thenReturn(response);

        ResponseEntity<ProspectSearchResponseDTO> result =
                controller.searchCustomers(request, CLIENT_ID, AUTH);

        assertEquals(200, result.getStatusCode().value());
        assertSame(response, result.getBody());
    }
}
Dependencias clave: junit-jupiter, mockito-junit-jupiter y spring-web.