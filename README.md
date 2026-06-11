
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.mappers;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.MovementConceptUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.TermDepositUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TermDepositTransactionMappersTest {

    private TermDepositUtils termDepositUtils;
    private RegexUtils regexUtils;
    private ErrorService errorService;
    private MovementConceptUtils movementConceptUtils;
    private TrxSanbaService trxSanbaService;
    private TermDepositTransactionMappers mapper;

    @BeforeEach
    void setUp() {
        termDepositUtils = mock(TermDepositUtils.class);
        regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);
        movementConceptUtils = new MovementConceptUtils();
        trxSanbaService = mock(TrxSanbaService.class);

        HashMap<String, String> concepts = new HashMap<>();
        concepts.put("INTP", "INTERESES PAGADOS");
        movementConceptUtils.setType(concepts);

        mapper = new TermDepositTransactionMappers(
                termDepositUtils,
                regexUtils,
                errorService,
                movementConceptUtils,
                trxSanbaService
        );

        ReflectionTestUtils.setField(mapper, "BP13_SERVICE_ROUTE", "consultaDatosIPF");
    }

    @Test
    void shouldMapTransactionWhenConceptExists() {
        TrxBP49Response bp49Response = buildBP49Response(
                buildMovement("INTP", "000000000012345", "2024-01-01")
        );

        TermDepositTransactionResponse result =
                mapper.transactionResponse(bp49Response, buildRequest());

        assertNotNull(result);
        assertEquals(1, result.getListTransactions().size());
        assertEquals("COP", result.getListTransactions().get(0).getAmount().getCurrency());
        assertEquals("123,45", result.getListTransactions().get(0).getAmount().getAmount());
        assertEquals("2024-01-01", result.getListTransactions().get(0).getValueDate());
        assertEquals("INTERESES PAGADOS", result.getListTransactions().get(0).getDescription());

        verifyNoInteractions(trxSanbaService);
    }

    @Test
    void shouldMapTransactionAsOtrosWhenConceptDoesNotExist() {
        TrxBP49Response bp49Response = buildBP49Response(
                buildMovement("UNKNOWN", "000000000000500", "2024-01-02")
        );

        TermDepositTransactionResponse result =
                mapper.transactionResponse(bp49Response, buildRequest());

        assertEquals(1, result.getListTransactions().size());
        assertEquals("5,00", result.getListTransactions().get(0).getAmount().getAmount());
        assertEquals("OTROS", result.getListTransactions().get(0).getDescription());

        verifyNoInteractions(trxSanbaService);
    }

    @Test
    void shouldMapEmirAsReinversionWhenCapInteresIsS() {
        when(trxSanbaService.trxBP13(any(TrxBP13Request.class)))
                .thenReturn(buildBP13Response("S"));

        TrxBP49Response bp49Response = buildBP49Response(
                buildMovement("EMIR", "-000000000001000", "2024-01-03")
        );

        TermDepositTransactionResponse result =
                mapper.transactionResponse(bp49Response, buildRequest());

        assertEquals(1, result.getListTransactions().size());
        assertEquals("10,00", result.getListTransactions().get(0).getAmount().getAmount());
        assertEquals("REINVERSION CDT", result.getListTransactions().get(0).getDescription());

        verify(trxSanbaService).trxBP13(any(TrxBP13Request.class));
    }

    @Test
    void shouldMapEmirAsRenovacionWhenCapInteresIsNotS() {
        when(trxSanbaService.trxBP13(any(TrxBP13Request.class)))
                .thenReturn(buildBP13Response("N"));

        TrxBP49Response bp49Response = buildBP49Response(
                buildMovement("EMIR", "000000000001000", "2024-01-04")
        );

        TermDepositTransactionResponse result =
                mapper.transactionResponse(bp49Response, buildRequest());

        assertEquals(1, result.getListTransactions().size());
        assertEquals("10,00", result.getListTransactions().get(0).getAmount().getAmount());
        assertEquals("RENOVACION CDT", result.getListTransactions().get(0).getDescription());

        verify(trxSanbaService).trxBP13(any(TrxBP13Request.class));
    }

    @Test
    void shouldMapEmptyMovementsList() {
        TrxBP49DataResponse data = new TrxBP49DataResponse();
        data.setMovimientos(List.of());

        TrxBP49Response bp49Response = new TrxBP49Response();
        bp49Response.setData(data);

        TermDepositTransactionResponse result =
                mapper.transactionResponse(bp49Response, buildRequest());

        assertNotNull(result);
        assertNotNull(result.getListTransactions());
        assertTrue(result.getListTransactions().isEmpty());
    }

    @Test
    void shouldBuildAndCallBP13Request() {
        when(trxSanbaService.trxBP13(any(TrxBP13Request.class)))
                .thenReturn(buildBP13Response("S"));

        TrxBP13Response result = mapper.trxBP13call(buildRequest());

        assertNotNull(result);
        assertEquals("S", result.getData().getCapInteres());

        verify(trxSanbaService).trxBP13(argThat(request -> {
            assertNotNull(request.getData());
            assertEquals("0065", request.getData().getEntidad());
            assertEquals("0100", request.getData().getOficina());
            assertEquals("", request.getData().getCuenta());
            assertEquals("", request.getData().getNumSecuencia());
            assertEquals("DEP001PLC001", request.getData().getNumCertificado());
            return true;
        }));
    }

    private TermDepositTransactionRequest buildRequest() {
        TermDepositTransactionRequest request = new TermDepositTransactionRequest();
        request.setDepositId("DEP001");
        request.setPlacementId("PLC-001");
        return request;
    }

    private TrxBP49Response buildBP49Response(TrxBP49Data movement) {
        TrxBP49DataResponse dataResponse = new TrxBP49DataResponse();
        dataResponse.setMovimientos(List.of(movement));

        TrxBP49Response response = new TrxBP49Response();
        response.setData(dataResponse);

        return response;
    }

    private TrxBP49Data buildMovement(String concept, String value, String date) {
        TrxBP49Data movement = new TrxBP49Data();
        movement.setConcepto(concept);
        movement.setValor(value);
        movement.setFecha(date);
        return movement;
    }

    private TrxBP13Response buildBP13Response(String capInteres) {
        TrxBP13DataResponse data = new TrxBP13DataResponse();
        data.setCapInteres(capInteres);

        TrxBP13Response response = new TrxBP13Response();
        response.setData(data);
        response.setOk(true);

        return response;
    }
}
