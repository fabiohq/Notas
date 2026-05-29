package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.service.TermDepositFundsService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TermDepositFundsControllerTest {

    @Test
    void manageFundsShouldReturnOkResponse() {
        TermDepositFundsService fundsService = mock(TermDepositFundsService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        TermDepositFundsController controller =
                new TermDepositFundsController(fundsService, objectMapper);

        TermDepositFundsRequestDto request = buildRequest();
        TermDepositFundsResponseDto expectedResponse = new TermDepositFundsResponseDto();
        expectedResponse.getStatusInfo().setStatusCode("OK");

        when(fundsService.manageFunds("client-id", "12345", "67890", request))
                .thenReturn(expectedResponse);

        ResponseEntity<TermDepositFundsResponseDto> response =
                controller.manageFunds(
                        "Bearer token",
                        "client-id",
                        "12345",
                        "67890",
                        request
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(fundsService).manageFunds("client-id", "12345", "67890", request);
    }

    private TermDepositFundsRequestDto buildRequest() {
        OtherSourceRequestDto otherSource = new OtherSourceRequestDto();
        otherSource.setPaymentReference("123456");

        SourceFundsRequestDTO sourceFunds = new SourceFundsRequestDTO();
        sourceFunds.setOtherSource(otherSource);

        TermDepositFundsRequestDto request = new TermDepositFundsRequestDto();
        request.setSourceFunds(sourceFunds);

        return request;
    }
}
