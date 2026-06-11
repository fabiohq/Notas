package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.mappers.TermDepositTransactionMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.TermDepositUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TermDepositTransactionImplTest {

    private TrxSanbaService trxSanbaService;
    private TermDepositUtils termDepositUtils;
    private RegexUtils regexUtils;
    private ErrorService errorService;
    private TermDepositTransactionMappers transactionMappers;
    private TermDepositTransactionImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaService = mock(TrxSanbaService.class);
        termDepositUtils = mock(TermDepositUtils.class);
        regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);
        transactionMappers = mock(TermDepositTransactionMappers.class);

        service = new TermDepositTransactionImpl(
                trxSanbaService,
                termDepositUtils,
                regexUtils,
                errorService,
                transactionMappers
        );

        ReflectionTestUtils.setField(service, "BP49_SERVICE_ROUTE", "BP49_ROUTE");
    }

    @Test
    void shouldReturnMappedResponseWhenFirstMovementExists() {
        TermDepositTransactionRequest request = buildRequest();

        TrxBP49Data movement = new TrxBP49Data();
        TrxBP49Response bp49Response = buildBp49Response(List.of(movement));

        TermDepositTransactionResponse expected =
                TermDepositTransactionResponse.builder()
                        .listTransactions(List.of())
                        .build();

        when(trxSanbaService.trxBP49(any(TrxBP49Request.class)))
                .thenReturn(bp49Response);
        when(transactionMappers.transactionResponse(bp49Response, request))
                .thenReturn(expected);

        TermDepositTransactionResponse result =
                service.getDepositTrasaction(request);

        assertEquals(expected, result);

        verify(errorService).isBlank("12345678901234567890", "deposit_id");
        verify(regexUtils).validateRegex("only_numbers", "12345678901234567890", "deposit_id");
        verify(regexUtils).validateRegex("strict_length_20", "12345678901234567890", "deposit_id");

        verify(errorService).isBlank("12345-ABCDE", "placement_id");
        verify(regexUtils).validateRegex("placement_format", "12345-ABCDE", "placement_id");

        ArgumentCaptor<TrxBP49Request> captor =
                ArgumentCaptor.forClass(TrxBP49Request.class);

        verify(trxSanbaService).trxBP49(captor.capture());

        TrxBP49Request trxRequest = captor.getValue();

        assertNotNull(trxRequest.getData());
        assertEquals("", trxRequest.getData().getCuenta());
        assertEquals("12345", trxRequest.getData().getSecuencia());
        assertEquals("0100", trxRequest.getData().getOfic());
        assertEquals("0065", trxRequest.getData().getEnt());
        assertEquals("1234567890123456789012345ABCDE", trxRequest.getData().getNumeroCertificado());
        assertEquals("", trxRequest.getData().getBuscarPor());
        assertEquals("", trxRequest.getData().getDocumentoCajero());

        verify(transactionMappers).transactionResponse(bp49Response, request);
    }

    @Test
    void shouldReturnNullWhenFirstMovementIsNull() {
        TermDepositTransactionRequest request = buildRequest();

        List<TrxBP49Data> movements = new ArrayList<>();
        movements.add(null);

        TrxBP49Response bp49Response = buildBp49Response(movements);

        when(trxSanbaService.trxBP49(any(TrxBP49Request.class)))
                .thenReturn(bp49Response);

        TermDepositTransactionResponse result =
                service.getDepositTrasaction(request);

        assertNull(result);
        verify(transactionMappers, never()).transactionResponse(any(), any());
    }

    private TermDepositTransactionRequest buildRequest() {
        TermDepositTransactionRequest request = new TermDepositTransactionRequest();
        request.setAuthorization("Bearer token");
        request.setClientId("client-id");
        request.setDepositId("12345678901234567890");
        request.setPlacementId("12345-ABCDE");
        return request;
    }

    private TrxBP49Response buildBp49Response(List<TrxBP49Data> movements) {
        TrxBP49DataResponse dataResponse = new TrxBP49DataResponse();
        dataResponse.setMovimientos(movements);

        TrxBP49Response response = new TrxBP49Response();
        response.setData(dataResponse);
        return response;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositTransactionRequestTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        TermDepositTransactionRequest dto = new TermDepositTransactionRequest();

        dto.setAuthorization("Bearer token");
        dto.setClientId("client-id");
        dto.setDepositId("12345678901234567890");
        dto.setPlacementId("12345-ABCDE");
        dto.setType_code(1);
        dto.setCredit_debit_indicator("C");
        dto.setStart_date("2024-01-01");
        dto.setEnd_date("2024-12-31");
        dto.setMinim_amount(100);
        dto.setMaxim_amount(1000);
        dto.setOffset("0");
        dto.setLimit("10");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client-id", dto.getClientId());
        assertEquals("12345678901234567890", dto.getDepositId());
        assertEquals("12345-ABCDE", dto.getPlacementId());
        assertEquals(1, dto.getType_code());
        assertEquals("C", dto.getCredit_debit_indicator());
        assertEquals("2024-01-01", dto.getStart_date());
        assertEquals("2024-12-31", dto.getEnd_date());
        assertEquals(100, dto.getMinim_amount());
        assertEquals(1000, dto.getMaxim_amount());
        assertEquals("0", dto.getOffset());
        assertEquals("10", dto.getLimit());

        TermDepositTransactionRequest allArgs =
                new TermDepositTransactionRequest(
                        "Bearer token",
                        "client-id",
                        "12345678901234567890",
                        "12345-ABCDE",
                        1,
                        "C",
                        "2024-01-01",
                        "2024-12-31",
                        100,
                        1000,
                        "0",
                        "10"
                );

        assertEquals("Bearer token", allArgs.getAuthorization());
        assertEquals("10", allArgs.getLimit());

        TermDepositTransactionRequest builder =
                TermDepositTransactionRequest.builder()
                        .authorization("Bearer token")
                        .clientId("client-id")
                        .depositId("12345678901234567890")
                        .placementId("12345-ABCDE")
                        .type_code(1)
                        .credit_debit_indicator("C")
                        .start_date("2024-01-01")
                        .end_date("2024-12-31")
                        .minim_amount(100)
                        .maxim_amount(1000)
                        .offset("0")
                        .limit("10")
                        .build();

        assertEquals("client-id", builder.getClientId());
        assertEquals("12345-ABCDE", builder.getPlacementId());
        assertEquals(1000, builder.getMaxim_amount());
    }
}
