package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.mappers.TermDepositFundsMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TermDepositFundsServiceImplTest {

    private TrxSanbaService trxSanbaService;
    private TermDepositFundsMappers mapper;
    private ErrorService errorService;
    private RegexUtils regexUtils;
    private TermDepositFundsServiceImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaService = mock(TrxSanbaService.class);
        mapper = new TermDepositFundsMappers();
        errorService = new ErrorService();
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(errorService, "msName", "BNCBSN049");
        ReflectionTestUtils.setField(errorService, "msVersion", "v1");
        ReflectionTestUtils.setField(errorService, "level", "error");
        ReflectionTestUtils.setField(errorService, "functional", "P-F");
        ReflectionTestUtils.setField(errorService, "BLANK_DATA", "Blank data");

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "Null data");
        errorService.setGeneral(general);

        HashMap<String, String> regexTypes = new HashMap<>();
        regexTypes.put("placement_format", "\\d+");
        regexTypes.put("payment_reference_length", "\\d+");
        regexTypes.put("deposit_id", "\\d+");
        regexUtils.setType(regexTypes);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "BNCBSN049");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "9400");

        service = new TermDepositFundsServiceImpl(
                trxSanbaService,
                mapper,
                errorService,
                regexUtils
        );

        ReflectionTestUtils.setField(service, "BP14_SERVICE_ROUTE", "BP14");
        ReflectionTestUtils.setField(service, "CURRENCY", "COP");
    }

    @Test
    void manageFundsShouldValidateCallTrxAndMapResponse() {
        TermDepositFundsRequestDto request = buildRequest("123456");

        TrxBP14Response trxResponse = buildTrxResponse("INTERNAL-REF");

        when(trxSanbaService.trxBP14(any(TrxBP14Request.class)))
                .thenReturn(trxResponse);

        TermDepositFundsResponseDto result =
                service.manageFunds("client-id", "12345", "67890", request);

        assertNotNull(result);
        assertEquals("INTERNAL-REF", result.getSourceFunds().getInternalReference());
        assertEquals("OK", result.getStatusInfo().getStatusCode());

        verify(trxSanbaService).trxBP14(any(TrxBP14Request.class));
    }

    @Test
    void trxBP14callShouldBuildRequestAndCallSanbaService() {
        TermDepositFundsRequestDto request = buildRequest("999999");
        TrxBP14Response trxResponse = buildTrxResponse("REF");

        when(trxSanbaService.trxBP14(any(TrxBP14Request.class)))
                .thenReturn(trxResponse);

        TrxBP14Response result =
                service.trxBP14call("12345", "678901234", request);

        assertEquals(trxResponse, result);

        verify(trxSanbaService).trxBP14(argThat(trxRequest ->
                trxRequest.getData().getCodigoInversor().equals("12345")
                        && trxRequest.getData().getSecuenciaIpf().equals("67890")
                        && trxRequest.getData().getFormaDePagoIpf().equals("O")
                        && trxRequest.getData().getTipoDeDocumento().equals("PE")
                        && trxRequest.getData().getNumeroDeDocumento().equals("999999")
        ));
    }

    private TermDepositFundsRequestDto buildRequest(String paymentReference) {
        OtherSourceRequestDto otherSource = new OtherSourceRequestDto();
        otherSource.setPaymentReference(paymentReference);

        SourceFundsRequestDTO sourceFunds = new SourceFundsRequestDTO();
        sourceFunds.setOtherSource(otherSource);

        TermDepositFundsRequestDto request = new TermDepositFundsRequestDto();
        request.setSourceFunds(sourceFunds);

        return request;
    }

    private TrxBP14Response buildTrxResponse(String nomper) {
        TrxBP14BGMP140Response bgmp140 = new TrxBP14BGMP140Response();
        bgmp140.setNOMPER(nomper);

        TrxBP14DataResponse data = new TrxBP14DataResponse();
        data.setBGMP140(bgmp140);

        TrxBP14Response response = new TrxBP14Response();
        response.setData(data);

        return response;
    }
}
