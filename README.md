package com.santander.bnc.bsn049.bncbsn049mscontracts.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.ContractsServiceRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.utils.ContractsUtils;
import com.santander.bnc.bsn049.bncbsn049mscontracts.utils.RegexUtils;

@ExtendWith(MockitoExtension.class)
class ContractsImplTest {

    @Mock
    private TrxSanbaService trxSanbaService;

    @Mock
    private ContractsUtils termDepositUtils;

    @Mock
    private RegexUtils regexUtils;

    @Mock
    private ErrorService errorService;

    @Mock
    private ContractsUtils contractsUtils;

    private ContractsImpl contractsImpl;

    @BeforeEach
    void setUp() {
        contractsImpl = new ContractsImpl(
                trxSanbaService,
                termDepositUtils,
                regexUtils,
                errorService,
                contractsUtils
        );

        ReflectionTestUtils.setField(
                contractsImpl,
                "BP21_SERVICE_ROUTE",
                "BP21_ROUTE_TEST"
        );
    }

    @Test
    void shouldModifyAssociatedContractSuccessfully() {
        String contractId = "12345678901234567890";
        String nationalIdentification = "0065";
        String internalIdentification = "1234567890";
        String relationshipTypeCode = "CC";

        ContractsServiceRequestDTO request = mockRequest(
                nationalIdentification,
                internalIdentification,
                relationshipTypeCode
        );

        TrxBP21Response expectedResponse = mock(TrxBP21Response.class);

        when(contractsUtils.bankValidation(any(BanksParametersRequest.class), eq(nationalIdentification)))
                .thenReturn(nationalIdentification);
        when(trxSanbaService.trxBP21(any(TrxBP21Request.class)))
                .thenReturn(expectedResponse);

        contractsImpl.modifyAssociatedContract(contractId, request);

        verify(errorService).isNull(contractId, "contract Id");
        verify(errorService).isBlank(contractId, "contract Id");
        verify(regexUtils).validateRegex("only_numbers", contractId, "contract Id");
        verify(regexUtils).validateRegex("strict_length_20", contractId, "contract_ Id");

        verify(errorService).isNull(nationalIdentification, "NationalIdentification");
        verify(errorService).isBlank(nationalIdentification, "NationalIdentification");
        verify(regexUtils).validateRegex("national_length", nationalIdentification, "NationalIdentification");
        verify(regexUtils).validateRegex("national_format", nationalIdentification, "NationalIdentification");

        verify(errorService).isNull(internalIdentification, "InternalIdentification");
        verify(errorService).isBlank(internalIdentification, "InternalIdentification");
        verify(regexUtils).validateRegex("internal_identification_length", internalIdentification, "InternalIdentification");
        verify(regexUtils).validateRegex("internal_identification_format", internalIdentification, "InternalIdentification");

        verify(errorService).isNull(relationshipTypeCode, "relationshipTypeCode");
        verify(errorService).isBlank(relationshipTypeCode, "relationshipTypeCode");
        verify(regexUtils).validateRegex("relationship_length", relationshipTypeCode, "relationshipTypeCode");
        verify(regexUtils).validateRegex("relationship_format", relationshipTypeCode, "relationshipTypeCode");

        verify(contractsUtils, times(1))
                .bankValidation(any(BanksParametersRequest.class), eq(nationalIdentification));

        verify(trxSanbaService, times(1)).trxBP21(any(TrxBP21Request.class));
    }

    @Test
    void shouldThrowWhenRelationshipTypeCodeIsInvalid() {
        String contractId = "12345678901234567890";

        ContractsServiceRequestDTO request = mockRequest(
                "0065",
                "1234567890",
                "XX"
        );

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> contractsImpl.modifyAssociatedContract(contractId, request)
        );

        assertEquals("'relationshipTypeCode': Invalid value", ex.getMessage());
        verify(trxSanbaService, never()).trxBP21(any(TrxBP21Request.class));
    }

    @Test
    void shouldBuildBp21RequestAndCallService() {
        String contractId = "12345678901234567890";
        String nationalIdentification = "0065";
        String internalIdentification = "999888777";
        String relationshipTypeCode = "CA";

        ContractsServiceRequestDTO request = mockRequest(
                nationalIdentification,
                internalIdentification,
                relationshipTypeCode
        );

        TrxBP21Response expectedResponse = mock(TrxBP21Response.class);

        when(contractsUtils.bankValidation(any(BanksParametersRequest.class), eq(nationalIdentification)))
                .thenReturn(nationalIdentification);
        when(trxSanbaService.trxBP21(any(TrxBP21Request.class)))
                .thenReturn(expectedResponse);

        TrxBP21Response response = contractsImpl.trxBP21Call(contractId, request);

        assertSame(expectedResponse, response);

        ArgumentCaptor<TrxBP21Request> captor =
                ArgumentCaptor.forClass(TrxBP21Request.class);

        verify(trxSanbaService).trxBP21(captor.capture());

        TrxBP21Request sentRequest = captor.getValue();

        assertEquals("ECP", sentRequest.getData().getCertificado());
        assertEquals(contractId, sentRequest.getData().getCcc());
        assertEquals("00001", sentRequest.getData().getSecuenciaImposicion());
        assertEquals("4250", sentRequest.getData().getTarifaVigente());
        assertEquals("090", sentRequest.getData().getPlazo());
        assertEquals("M", sentRequest.getData().getPeriodoLiquidacion());
        assertEquals("COP", sentRequest.getData().getDivisa());
        assertEquals("0000000000000.00", sentRequest.getData().getSaldoDeLaIpf());
        assertEquals("000.00000", sentRequest.getData().getSpread());
        assertEquals("N", sentRequest.getData().getRenovacionAutomatic());
        assertEquals("N", sentRequest.getData().getCapitalizaIntereses());
        assertEquals("N", sentRequest.getData().getCapitalizaReajustes());
        assertEquals("0001", sentRequest.getData().getEjecutivoComercial());
        assertEquals("0000000000000.00", sentRequest.getData().getRentaProgramada());
        assertEquals("N", sentRequest.getData().getIndBloqueo());
        assertEquals("0001", sentRequest.getData().getCentroGestor());
        assertEquals("N", sentRequest.getData().getIndicadorGarantia());
        assertEquals("N", sentRequest.getData().getIndicadorFraccionab());
        assertEquals("000.00000", sentRequest.getData().getSpreadRenovacion());
        assertEquals("0000000000000.00", sentRequest.getData().getSaldoEnajenacion());
        assertEquals(
                nationalIdentification + relationshipTypeCode + internalIdentification,
                sentRequest.getData().getObservaciones()
        );
        assertEquals("N", sentRequest.getData().getTipoDeTasa());
    }

    private ContractsServiceRequestDTO mockRequest(
            String nationalIdentification,
            String internalIdentification,
            String relationshipTypeCode
    ) {
        ContractsServiceRequestDTO request =
                mock(ContractsServiceRequestDTO.class, RETURNS_DEEP_STUBS);

        when(request.getNewAssociatedContract()
                .getContract()
                .getContractIdentification()
                .getNationalIdentification())
                .thenReturn(nationalIdentification);

        when(request.getNewAssociatedContract()
                .getContract()
                .getContractIdentification()
                .getInternalIdentification())
                .thenReturn(internalIdentification);

        when(request.getOldAssociatedContract()
                .getRelationshipTypeCode())
                .thenReturn(relationshipTypeCode);

        return request;
    }
}