package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.mappers.TermDepositSettlementsMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.TermDepositUtils;

class TermDepositSettlementsImplTest {

    private TrxSanbaService trxSanbaService;
    private TermDepositUtils termDepositUtils;
    private RegexUtils regexUtils;
    private ErrorService errorService;
    private TermDepositSettlementsMappers settlementsMappers;
    private TermDepositSettlementsImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaService = mock(TrxSanbaService.class);
        termDepositUtils = mock(TermDepositUtils.class);
        regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);
        settlementsMappers = mock(TermDepositSettlementsMappers.class);

        service = new TermDepositSettlementsImpl(
                trxSanbaService,
                termDepositUtils,
                regexUtils,
                errorService,
                settlementsMappers
        );

        ReflectionTestUtils.setField(service, "bp49serviceRoute", "BP49_ROUTE");
        ReflectionTestUtils.setField(service, "bp13serviceRoute", "BP13_ROUTE");
    }

    @Test
    void getDepositSettlementsShouldReturnMappedResponseWhenMovementExists() {
        TermDepositSettlementsRequest request = validRequest();

        TrxBP49Response bp49Response = bp49WithMovement(new TrxBP49Data());
        TrxBP13Response bp13Response = new TrxBP13Response();
        TermDepositSettlementsReponse expected = new TermDepositSettlementsReponse();

        when(trxSanbaService.trxBP49(any())).thenReturn(bp49Response);
        when(trxSanbaService.trxBP13(any())).thenReturn(bp13Response);
        when(settlementsMappers.settlementsReponse(bp49Response, bp13Response)).thenReturn(expected);

        TermDepositSettlementsReponse result = service.getDepositSettlements(request);

        assertSame(expected, result);
        verify(termDepositUtils).getSettlementsInputValidation(request);

        ArgumentCaptor<com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request>
                bp49Captor = ArgumentCaptor.forClass(
                com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request.class);

        verify(trxSanbaService).trxBP49(bp49Captor.capture());

        var bp49Request = bp49Captor.getValue();
        assertEquals("12345", bp49Request.getData().getSecuencia());
        assertEquals("0100", bp49Request.getData().getOfic());
        assertEquals("0065", bp49Request.getData().getEnt());
        assertEquals("99999999991234567890", bp49Request.getData().getNumeroCertificado());
        assertEquals("", bp49Request.getData().getBuscarPor());
        assertEquals("", bp49Request.getData().getDocumentoCajero());
    }

    @Test
    void getDepositSettlementsShouldReturnNullWhenFirstMovementIsNull() {
        TermDepositSettlementsRequest request = validRequest();

        TrxBP49DataResponse data = new TrxBP49DataResponse();
        ArrayList<TrxBP49Data> movements = new ArrayList<>();
        movements.add(null);
        data.setMovimientos(movements);

        TrxBP49Response bp49Response = new TrxBP49Response();
        bp49Response.setData(data);

        when(trxSanbaService.trxBP49(any())).thenReturn(bp49Response);
        when(trxSanbaService.trxBP13(any())).thenReturn(new TrxBP13Response());

        TermDepositSettlementsReponse result = service.getDepositSettlements(request);

        assertNull(result);
        verify(settlementsMappers, never()).settlementsReponse(any(), any());
    }

    @Test
    void trxBP13callShouldBuildRequestCorrectly() {
        TrxBP13Response expected = new TrxBP13Response();
        when(trxSanbaService.trxBP13(any())).thenReturn(expected);

        TrxBP13Response result = service.trxBP13call("9999999999", "12345-67890");

        assertSame(expected, result);

        ArgumentCaptor<TrxBP13Request> captor = ArgumentCaptor.forClass(TrxBP13Request.class);
        verify(trxSanbaService).trxBP13(captor.capture());

        TrxBP13Request request = captor.getValue();

        assertEquals("0065", request.getData().getEntidad());
        assertEquals("0100", request.getData().getOficina());
        assertEquals("", request.getData().getCuenta());
        assertEquals("", request.getData().getNumSecuencia());
        assertEquals("99999999991234567890", request.getData().getNumCertificado());
        assertNotNull(request.getCabecera());
    }

    private TermDepositSettlementsRequest validRequest() {
        TermDepositSettlementsRequest request = new TermDepositSettlementsRequest();
        request.setAuthorization("auth");
        request.setClient_id("client");
        request.setDeposit_id("9999999999");
        request.setPlacement_id("12345-67890");
        return request;
    }

    private TrxBP49Response bp49WithMovement(TrxBP49Data movement) {
        TrxBP49DataResponse data = new TrxBP49DataResponse();
        data.setMovimientos(List.of(movement));

        TrxBP49Response response = new TrxBP49Response();
        response.setData(data);

        return response;
    }
}
