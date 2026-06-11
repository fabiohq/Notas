package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.service.TermDepositTransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TermDepositTransactionControllerTest {

    private TermDepositTransactionsService service;
    private ObjectMapper objectMapper;
    private TermDepositTransactionController controller;

    @BeforeEach
    void setUp() {
        service = mock(TermDepositTransactionsService.class);
        objectMapper = mock(ObjectMapper.class);
        controller = new TermDepositTransactionController(service, objectMapper);
    }

    @Test
    void shouldReturnOkWhenServiceReturnsResponse() throws Exception {
        TermDepositTransactionResponse serviceResponse =
                TermDepositTransactionResponse.builder()
                        .listTransactions(List.of())
                        .build();

        when(service.getDepositTrasaction(any(TermDepositTransactionRequest.class)))
                .thenReturn(serviceResponse);
        when(objectMapper.writeValueAsString(serviceResponse))
                .thenReturn("{}");

        ResponseEntity<TermDepositTransactionResponse> response =
                controller.getDepositTrasaction(
                        "Bearer token",
                        "client-id",
                        "DEP001",
                        "PLC-001"
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());

        ArgumentCaptor<TermDepositTransactionRequest> captor =
                ArgumentCaptor.forClass(TermDepositTransactionRequest.class);

        verify(service).getDepositTrasaction(captor.capture());

        TermDepositTransactionRequest request = captor.getValue();

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getClientId());
        assertEquals("DEP001", request.getDepositId());
        assertEquals("PLC-001", request.getPlacementId());

        verify(objectMapper).writeValueAsString(serviceResponse);
    }

    @Test
    void shouldReturnNoContentWhenServiceReturnsNull() throws Exception {
        when(service.getDepositTrasaction(any(TermDepositTransactionRequest.class)))
                .thenReturn(null);
        when(objectMapper.writeValueAsString(null))
                .thenReturn("null");

        ResponseEntity<TermDepositTransactionResponse> response =
                controller.getDepositTrasaction(
                        "Bearer token",
                        "client-id",
                        "DEP001",
                        "PLC-001"
                );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service).getDepositTrasaction(any(TermDepositTransactionRequest.class));
        verify(objectMapper).writeValueAsString(null);
    }

    @Test
    void shouldReturnOkWhenObjectMapperThrowsException() throws Exception {
        TermDepositTransactionResponse serviceResponse =
                TermDepositTransactionResponse.builder()
                        .listTransactions(List.of())
                        .build();

        when(service.getDepositTrasaction(any(TermDepositTransactionRequest.class)))
                .thenReturn(serviceResponse);

        when(objectMapper.writeValueAsString(serviceResponse))
                .thenThrow(new JsonProcessingException("serialization error") {});

        ResponseEntity<TermDepositTransactionResponse> response =
                controller.getDepositTrasaction(
                        "Bearer token",
                        "client-id",
                        "DEP001",
                        "PLC-001"
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceResponse, response.getBody());

        verify(objectMapper).writeValueAsString(serviceResponse);
    }
}
