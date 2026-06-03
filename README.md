package com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.GetListDepositsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.ParticipantsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.request.DepositPlacementRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.response.TrxBp92Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request.GetListDepositsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request.UpdateCdtRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers.ProductsMappers;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.AmountRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.Product;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.SubproductRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement.SimulatePlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils;

class TermDepositServiceImplTest {

    private TrxSanbaService trxSanbaService;
    private ProductsMappers mapper;
    private TermDepositUtils termDepositUtils;
    private RegexUtils regexUtils;
    private ErrorService errorService;
    private TermDepositServiceImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaService = mock(TrxSanbaService.class);
        mapper = mock(ProductsMappers.class);
        termDepositUtils = mock(TermDepositUtils.class);
        regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);

        service = new TermDepositServiceImpl(
                trxSanbaService,
                mapper,
                termDepositUtils,
                regexUtils,
                errorService
        );

        ReflectionTestUtils.setField(service, "BP17_SERVICE_ROUTE", "BP17");
        ReflectionTestUtils.setField(service, "BP31_SERVICE_ROUTE", "BP31");
        ReflectionTestUtils.setField(service, "BP21_SERVICE_ROUTE", "BP21");
        ReflectionTestUtils.setField(service, "BP13_SERVICE_ROUTE", "BP13");
        ReflectionTestUtils.setField(service, "PEPF_SERVICE_ROUTE", "PEPF");
        ReflectionTestUtils.setField(service, "BP01_SERVICE_ROUTE", "BP01");
        ReflectionTestUtils.setField(service, "BP02_SERVICE_ROUTE", "BP02");
        ReflectionTestUtils.setField(service, "BP92_SERVICE_ROUTE", "BP92");
        ReflectionTestUtils.setField(service, "CURRENCY", "COP");
        ReflectionTestUtils.setField(service, "USER", "USER");
        ReflectionTestUtils.setField(service, "NUMPER", "12345678");
        ReflectionTestUtils.setField(service, "isDecimal", true);
        ReflectionTestUtils.setField(service, "inputSettlementCompare", "C");
        ReflectionTestUtils.setField(service, "setSettlement", "S");
        ReflectionTestUtils.setField(service, "error_patrimonialInformation", "patrimonial");

        when(errorService.serviceExceptionBuilder(any(), any(), any()))
                .thenAnswer(inv -> new ServiceException(
                        inv.getArgument(0),
                        ErrorDTO.builder()
                                .message(inv.getArgument(1) == null ? "error" : inv.getArgument(1).toString())
                                .build()
                ));
    }

    @Test
    void shouldProcessSimulatePlacement() {
        RequestSimulatePlacementDTO request = simulateRequest();

        TrxBP17Response trx = mock(TrxBP17Response.class, RETURNS_DEEP_STUBS);
        SimulatePlacementResponseDTO mapped = new SimulatePlacementResponseDTO();

        when(trxSanbaService.trxBP17(any())).thenReturn(trx);
        when(mapper.bp17toSimulatePlacementResponse(any(), any())).thenReturn(mapped);

        SimulatePlacementResponseDTO result =
                service.processSimulatePlacement(request, new AmountRangeRequest("auth", "client", null));

        assertSame(mapped, result);
        verify(termDepositUtils).simulatePlacementInputValidation(eq(request), any());
        verify(trx.getData()).setPlazoEnDias("30");
    }

    @Test
    void shouldListGetTermsDepositsWithResultsAndLinks() {
        GetListDepositsRequestDTO request = GetListDepositsRequestDTO.builder()
                .participantId("12345678")
                .placementStatus("A")
                .limit("1")
                .offset("00001-00000")
                .build();

        TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
        CdtsDatsDTO first = cdt("A", "1000");
        CdtsDatsDTO second = cdt("P", "2000");

        when(trx.getData().getCdtsDats()).thenReturn(List.of(first, second));
        when(trxSanbaService.trxBP31(any())).thenReturn(trx);
        when(mapper.bp31mapResponse(any())).thenReturn(List.of());
        when(mapper.bp31mapResponseLinks(anyList(), any())).thenReturn(null);

        GetListDepositsResponseDTO result = service.listGetTermsDeposits(request);

        assertNotNull(result);
        verify(regexUtils, atLeastOnce()).validateRegex(anyString(), anyString(), anyString());
        verify(mapper).bp31mapResponse(any());
    }

    @Test
    void shouldReturnNullWhenListGetTermsDepositsHasNoData() {
        GetListDepositsRequestDTO request = GetListDepositsRequestDTO.builder()
                .participantId("12345678")
                .placementStatus("A")
                .build();

        TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
        when(trx.getData().getCdtsDats()).thenReturn(List.of());
        when(trxSanbaService.trxBP31(any())).thenReturn(trx);

        assertNull(service.listGetTermsDeposits(request));
    }

    @Test
    void shouldGetDepositsPlacement() {
        DepositPlacementRequestDTO request =
                new DepositPlacementRequestDTO("auth", "client", "00000000000000000001", "00001-00000");

        TrxBP13Response trx = mock(TrxBP13Response.class, RETURNS_DEEP_STUBS);
        DepositPlacementResponseDTO mapped = new DepositPlacementResponseDTO();

        when(trx.getData().getNombreTitular()).thenReturn("JUAN");
        when(trx.getData().getPriApellido()).thenReturn("PEREZ");
        when(trx.getData().getSegApellido()).thenReturn("LOPEZ");
        when(trxSanbaService.trxBP13(any())).thenReturn(trx);
        when(mapper.bp13toDepositsPlacementResponse(any(), any())).thenReturn(mapped);

        DepositPlacementResponseDTO result =
                service.getDepositsPlacement(request, new TermDepositParametersRequest("940250", "auth", "client"));

        assertSame(mapped, result);
        assertNotNull(result.getPerson());
        assertEquals("JUAN PEREZ LOPEZ", result.getPerson().getPersonName().getFullName());
        assertEquals("0000100000", request.getPlacementId());
    }

    @Test
    void shouldCreateTermDeposit() {
        RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

        TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
        TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
        ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

        when(trxSanbaService.trxPEPF(any())).thenReturn(new TrxPEPFDataResponse());
        when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
        when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
        when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

        ResponseTermDepositsDTO result = service.responseTermDepositsDTO(
                request,
                new AmountRangeRequest("auth", "client", null),
                new TermDepositParametersRequest("940250", "auth", "client"),
                new BanksParametersRequest("auth", "client")
        );

        assertSame(mapped, result);
        verify(termDepositUtils).termDepositsInputValidation(any(), any(), any(), any());
        verify(mapper).responseTermDepositsDTOMapper(bp02);
    }

    @Test
    void shouldCreateTermDepositIgnoringPatrimonialError() {
        RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

        TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
        TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
        ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

        when(trxSanbaService.trxPEPF(any())).thenThrow(new RuntimeException("patrimonial"));
        when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
        when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
        when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

        assertSame(mapped, service.responseTermDepositsDTO(
                request,
                new AmountRangeRequest(),
                new TermDepositParametersRequest(),
                new BanksParametersRequest()
        ));
    }

    @Test
    void shouldThrowWhenPepfFailsWithOtherError() {
        RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

        when(trxSanbaService.trxPEPF(any())).thenThrow(new RuntimeException("other error"));

        assertThrows(ServiceException.class, () -> service.responseTermDepositsDTO(
                request,
                new AmountRangeRequest(),
                new TermDepositParametersRequest(),
                new BanksParametersRequest()
        ));
    }

    @Test
    void shouldCalculateDepositSummaryNullWhenTrxReturnsEmpty() {
        CalculateDepositSummaryRequestDTO request = summaryRequest();

        TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
        CalculateDepositSummaryResponseDTO nullMapped = new CalculateDepositSummaryResponseDTO();

        when(trx.getData().getCdtsDats()).thenReturn(List.of());
        when(trxSanbaService.trxBP31(any())).thenReturn(trx);
        when(mapper.calculateDepositSummaryNullMapper()).thenReturn(nullMapped);

        assertSame(nullMapped, service.calculateDepositSummary(request));
    }

    @Test
    void shouldCalculateDepositSummaryWithData() {
        CalculateDepositSummaryRequestDTO request = summaryRequest();

        TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
        CalculateDepositSummaryResponseDTO mapped = new CalculateDepositSummaryResponseDTO();

        when(trx.getData().getCdtsDats()).thenReturn(List.of(cdt("A", "1000"), cdt("N", "2000"), cdt("C", "9999")));
        when(trxSanbaService.trxBP31(any())).thenReturn(trx);
        when(mapper.calculateDepositSummaryMapper(any(), anyList(), anyString())).thenReturn(mapped);

        assertSame(mapped, service.calculateDepositSummary(request));
        verify(mapper).calculateDepositSummaryMapper(eq(trx), anyList(), anyString());
    }

    @Test
    void shouldDeleteProspectCdtWhenStatusIsP() {
        TrxBP13Response trx = mock(TrxBP13Response.class, RETURNS_DEEP_STUBS);

        when(trx.getData().getEstadoIPF()).thenReturn("P");
        when(trxSanbaService.trxBP13(any())).thenReturn(trx);
        when(trxSanbaService.trxBP92(any())).thenReturn(new TrxBp92Response());

        assertDoesNotThrow(() -> service.deleteProspectCdt("00000000000000000001"));
        verify(termDepositUtils).deleteProspectCdtInputValidation("00000000000000000001");
        verify(trxSanbaService).trxBP92(any());
    }

    @Test
    void shouldThrowDeleteProspectCdtWhenStatusIsNotP() {
        TrxBP13Response trx = mock(TrxBP13Response.class, RETURNS_DEEP_STUBS);

        when(trx.getData().getEstadoIPF()).thenReturn("A");
        when(trxSanbaService.trxBP13(any())).thenReturn(trx);

        assertThrows(ServiceException.class, () -> service.deleteProspectCdt("00000000000000000001"));
    }

    @Test
    void shouldUpdateCdt() {
        UpdateCdtRequestDTO update = new UpdateCdtRequestDTO();
        update.setIsRenewable(Boolean.TRUE);
        update.setIsCapitalized(Boolean.FALSE);

        when(trxSanbaService.trxBP21(any())).thenReturn(new TrxBP21Response());

        assertDoesNotThrow(() -> service.updateCdt("00000000000000000001", "00001-00000", update));
        verify(trxSanbaService).trxBP21(any());
    }

    @Test
    void shouldCallTrxMethodsDirectly() {
        when(trxSanbaService.trxBP17(any())).thenReturn(mock(TrxBP17Response.class, RETURNS_DEEP_STUBS));
        when(trxSanbaService.trxBP31(any())).thenReturn(mock(TrxBP31Response.class, RETURNS_DEEP_STUBS));
        when(trxSanbaService.trxBP13(any())).thenReturn(mock(TrxBP13Response.class, RETURNS_DEEP_STUBS));
        when(trxSanbaService.trxBP92(any())).thenReturn(new TrxBp92Response());

        assertNotNull(service.trxBP17call(simulateRequest()));
        assertNotNull(service.trxBP31call(GetListDepositsRequestDTO.builder()
                .participantId("12345678")
                .placementStatus("A")
                .build()));
        assertNotNull(service.trxBP13call(new DepositPlacementRequestDTO("auth", "client", "00000000000000000001", "0000100000")));
        assertNotNull(service.trxBp92call("00000000000000000001"));
    }

    private RequestSimulatePlacementDTO simulateRequest() {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId("001");

        Product product = new Product();
        product.setProductCode("940250");
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount("1000");

        RequestDTO periodicity = new RequestDTO();
        periodicity.setFrequency("30");
        periodicity.setPeriodTypeCode("D");

        RequestSimulatePlacementDTO request = new RequestSimulatePlacementDTO();
        request.setProduct(product);
        request.setAmount(amount);
        request.setPeriodicity(periodicity);
        request.setSettlementConditionCode("C");

        return request;
    }

    private CalculateDepositSummaryRequestDTO summaryRequest() {
        ParticipantsRequestDTO participants = new ParticipantsRequestDTO();
        participants.setParticipantId("12345678");

        CalculateDepositSummaryRequestDTO request = new CalculateDepositSummaryRequestDTO();
        request.setParticipants(participants);

        return request;
    }

    private CdtsDatsDTO cdt(String status, String saldo) {
        CdtsDatsDTO dto = mock(CdtsDatsDTO.class);
        when(dto.getEstado()).thenReturn(status);
        when(dto.getSaldo()).thenReturn(saldo);
        dto.cccReposicionamiento = "00000000000000000001";
        dto.secuenciaReposicionamiento = "00001";
        dto.secuenciaRenovacion = "00000";
        return dto;
    }
}
