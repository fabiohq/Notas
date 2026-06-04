package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.SettlementsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.service.TermDepositSettlementsService;

class TermDepositSettlementsControllerTest {

    @Test
    void getDepositSettlementsShouldReturnOkWhenResponseHasSettlements() throws Exception {
        TermDepositSettlementsService service = mock(TermDepositSettlementsService.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        TermDepositSettlementsController controller =
                new TermDepositSettlementsController(service, objectMapper);

        TermDepositSettlementsReponse response = new TermDepositSettlementsReponse();
        ArrayList<SettlementsDTO> settlements = new ArrayList<>();
        settlements.add(new SettlementsDTO());
        response.setSettlements(settlements);

        when(service.getDepositSettlements(any())).thenReturn(response);
        when(objectMapper.writeValueAsString(response)).thenReturn("{}");

        ResponseEntity<TermDepositSettlementsReponse> result =
                controller.getDepositSettlements("auth", "client", "123", "456");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(response, result.getBody());

        ArgumentCaptor<TermDepositSettlementsRequest> captor =
                ArgumentCaptor.forClass(TermDepositSettlementsRequest.class);

        verify(service).getDepositSettlements(captor.capture());

        assertEquals("auth", captor.getValue().getAuthorization());
        assertEquals("client", captor.getValue().getClient_id());
        assertEquals("123", captor.getValue().getDeposit_id());
        assertEquals("456", captor.getValue().getPlacement_id());
    }

    @Test
    void getDepositSettlementsShouldReturnNoContentWhenResponseIsNull() {
        TermDepositSettlementsService service = mock(TermDepositSettlementsService.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        TermDepositSettlementsController controller =
                new TermDepositSettlementsController(service, objectMapper);

        when(service.getDepositSettlements(any())).thenReturn(null);

        ResponseEntity<TermDepositSettlementsReponse> result =
                controller.getDepositSettlements("auth", "client", "123", "456");

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void getDepositSettlementsShouldReturnNoContentWhenSettlementsIsNull() throws Exception {
        TermDepositSettlementsService service = mock(TermDepositSettlementsService.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        TermDepositSettlementsController controller =
                new TermDepositSettlementsController(service, objectMapper);

        TermDepositSettlementsReponse response = new TermDepositSettlementsReponse();
        response.setSettlements(null);

        when(service.getDepositSettlements(any())).thenReturn(response);
        when(objectMapper.writeValueAsString(response)).thenReturn("{}");

        ResponseEntity<TermDepositSettlementsReponse> result =
                controller.getDepositSettlements("auth", "client", "123", "456");

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void getDepositSettlementsShouldReturnOkWhenObjectMapperThrows() throws Exception {
        TermDepositSettlementsService service = mock(TermDepositSettlementsService.class);
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        TermDepositSettlementsController controller =
                new TermDepositSettlementsController(service, objectMapper);

        TermDepositSettlementsReponse response = new TermDepositSettlementsReponse();
        ArrayList<SettlementsDTO> settlements = new ArrayList<>();
        settlements.add(new SettlementsDTO());
        response.setSettlements(settlements);

        when(service.getDepositSettlements(any())).thenReturn(response);
        when(objectMapper.writeValueAsString(response)).thenThrow(new RuntimeException("json error"));

        ResponseEntity<TermDepositSettlementsReponse> result =
                controller.getDepositSettlements("auth", "client", "123", "456");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(response, result.getBody());
    }
}
