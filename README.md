package com.santander.bnc.bsn049.bncbsn049mstermdeposits.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.GetListDepositsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request.UpdateCdtRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement.SimulatePlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.TermDepositService;

class ProductControllersTest {

    private TermDepositService termDepositService;
    private ProductControllers controller;

    @BeforeEach
    void setUp() {
        termDepositService = mock(TermDepositService.class);
        TrxSanbaService trxSanbaService = mock(TrxSanbaService.class);
        ProductDirectoryService productDirectoryService = mock(ProductDirectoryService.class);

        controller = new ProductControllers(
                termDepositService,
                trxSanbaService,
                productDirectoryService,
                new ObjectMapper()
        );
    }

    @Test
    void shouldHandlePostSimulatePlacement() {
        SimulatePlacementResponseDTO response = new SimulatePlacementResponseDTO();
        when(termDepositService.processSimulatePlacement(any(), any())).thenReturn(response);

        assertSame(response, controller.handlePostRequest(
                "auth",
                "client",
                new RequestSimulatePlacementDTO()
        ));
    }

    @Test
    void shouldGetTermDepositsOkAndNoContent() {
        GetListDepositsResponseDTO response = new GetListDepositsResponseDTO();
        when(termDepositService.listGetTermsDeposits(any())).thenReturn(response);

        ResponseEntity<GetListDepositsResponseDTO> ok = controller.getTermsdeposits(
                "auth", "client", "123", "A", "10", "0");

        assertEquals(HttpStatus.OK, ok.getStatusCode());
        assertSame(response, ok.getBody());

        when(termDepositService.listGetTermsDeposits(any())).thenReturn(null);

        ResponseEntity<GetListDepositsResponseDTO> noContent = controller.getTermsdeposits(
                "auth", "client", "123", "A", null, null);

        assertEquals(HttpStatus.NO_CONTENT, noContent.getStatusCode());
    }

    @Test
    void shouldGetDepositsPlacement() {
        DepositPlacementResponseDTO response = new DepositPlacementResponseDTO();
        when(termDepositService.getDepositsPlacement(any(), any())).thenReturn(response);

        assertSame(response, controller.getDepositsPlacement(
                "auth", "client", "deposit", "placement"
        ));
    }

    @Test
    void shouldCreateTermDeposit() {
        ResponseTermDepositsDTO response = new ResponseTermDepositsDTO();
        when(termDepositService.responseTermDepositsDTO(any(), any(), any(), any())).thenReturn(response);

        assertSame(response, controller.termDepositsPostRequest(
                "auth",
                "client",
                new RequestTermDepositsDTO()
        ));
    }

    @Test
    void shouldUpdateCdtNoContentAndBadRequest() {
        UpdateCdtRequestDTO update = new UpdateCdtRequestDTO();
        update.setIsRenewable(Boolean.TRUE);

        ResponseEntity ok = controller.updateCdt("client", "deposit", "placement", update);

        assertEquals(HttpStatus.NO_CONTENT, ok.getStatusCode());
        verify(termDepositService).updateCdt("deposit", "placement", update);

        UpdateCdtRequestDTO invalid = new UpdateCdtRequestDTO();

        ResponseEntity bad = controller.updateCdt("client", null, "placement", invalid);

        assertEquals(HttpStatus.BAD_REQUEST, bad.getStatusCode());
    }

    @Test
    void shouldCalculateDepositSummary() {
        CalculateDepositSummaryResponseDTO response = new CalculateDepositSummaryResponseDTO();
        when(termDepositService.calculateDepositSummary(any())).thenReturn(response);

        assertSame(response, controller.calculateDepositSummaryPostRequest(
                "auth",
                "client",
                new CalculateDepositSummaryRequestDTO()
        ));
    }

    @Test
    void shouldDeleteProspectCdt() {
        ResponseEntity response = controller.deleteProspectCDT(
                "auth",
                "client",
                "deposit"
        );

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(termDepositService).deleteProspectCdt("deposit");
    }
}
