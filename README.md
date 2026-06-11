package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config.ParamsConfig;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.mappers.TermDepositParametersMapper;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.utils.RegexUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class TermDepositParametersServiceTest {

    @Mock
    private TrxSanbaService trxSanbaService;

    @Mock
    private TermDepositParametersMapper mapper;

    @Mock
    private ParamsConfig paramsConfig;

    @Mock
    private ErrorService errorService;

    @Mock
    private RegexUtils regexUtils;

    @InjectMocks
    private TermDepositParametersService service;

    private static final String DEFAULT_PRODUCT_ID = "010001";
    private static final String PROPOSAL_PRODUCT_ID = "020001";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(service, "periodHardcode", Boolean.TRUE);

        when(paramsConfig.getDefaultProductId()).thenReturn(DEFAULT_PRODUCT_ID);
        when(paramsConfig.getProposalProductId()).thenReturn(PROPOSAL_PRODUCT_ID);
    }

    @Test
    @DisplayName("Debe retornar propuestas cuando productId es proposalProductId")
    void getParametersShouldReturnProposalsWhenProductIsProposal() {
        TermDepositParameterResponse expectedResponse = new TermDepositParameterResponse();

        when(mapper.getProposalsResponse()).thenReturn(expectedResponse);

        TermDepositParameterResponse actualResponse =
                service.getParameters(PROPOSAL_PRODUCT_ID, null, null);

        assertSame(expectedResponse, actualResponse);

        verify(errorService).isNull(PROPOSAL_PRODUCT_ID, "productId");
        verify(errorService).isBlank(PROPOSAL_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_format", PROPOSAL_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_length", PROPOSAL_PRODUCT_ID, "productId");
        verify(mapper).getProposalsResponse();

        verifyNoInteractions(trxSanbaService);
    }

    @Test
    @DisplayName("Debe retornar lista hardcodeada cuando periodicity es menor a 90 y periodHardcode true")
    void getParametersShouldReturnHardcodedListWhenPeriodicityLessThan90AndHardcodeTrue() {
        String amount = "1000000";
        String periodicity = "30";
        TermDepositParameterResponse expectedResponse = new TermDepositParameterResponse();

        when(mapper.getListParametersHardCode()).thenReturn(expectedResponse);

        TermDepositParameterResponse actualResponse =
                service.getParameters(DEFAULT_PRODUCT_ID, amount, periodicity);

        assertSame(expectedResponse, actualResponse);

        verify(errorService).isNull(DEFAULT_PRODUCT_ID, "productId");
        verify(errorService).isBlank(DEFAULT_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_format", DEFAULT_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_length", DEFAULT_PRODUCT_ID, "productId");

        verify(errorService).isNull(amount, "amount");
        verify(errorService).isBlank(amount, "amount");
        verify(regexUtils).validateRegex("amount", amount, "amount");

        verify(errorService).isNull(periodicity, "periodicity");
        verify(errorService).isBlank(periodicity, "periodicity");
        verify(regexUtils).validateRegex("product_format", periodicity, "periodicity");

        verify(mapper).getListParametersHardCode();
        verifyNoInteractions(trxSanbaService);
    }

    @Test
    @DisplayName("Debe llamar BP17 cuando periodicity es mayor o igual a 90")
    void getParametersShouldCallBp17WhenPeriodicityGreaterOrEqual90() {
        String amount = "1000000";
        String periodicity = "90";

        TrxBP17Request request = new TrxBP17Request();
        TrxBP17Response bp17Response = new TrxBP17Response();
        TermDepositParameterResponse expectedResponse = new TermDepositParameterResponse();

        when(mapper.getBP17RequestFromInputData(DEFAULT_PRODUCT_ID, amount, periodicity))
                .thenReturn(request);
        when(trxSanbaService.trxBP17(request)).thenReturn(bp17Response);
        when(mapper.getParametersResponseFromBP17(bp17Response)).thenReturn(expectedResponse);

        TermDepositParameterResponse actualResponse =
                service.getParameters(DEFAULT_PRODUCT_ID, amount, periodicity);

        assertSame(expectedResponse, actualResponse);

        verify(mapper).getBP17RequestFromInputData(DEFAULT_PRODUCT_ID, amount, periodicity);
        verify(trxSanbaService).trxBP17(request);
        verify(mapper).getParametersResponseFromBP17(bp17Response);
        verify(mapper, never()).getListParametersHardCode();
    }

    @Test
    @DisplayName("Debe llamar BP17 cuando periodHardcode es false aunque periodicity sea menor a 90")
    void getParametersShouldCallBp17WhenHardcodeFalse() {
        ReflectionTestUtils.setField(service, "periodHardcode", Boolean.FALSE);

        String amount = "1000000";
        String periodicity = "30";

        TrxBP17Request request = new TrxBP17Request();
        TrxBP17Response bp17Response = new TrxBP17Response();
        TermDepositParameterResponse expectedResponse = new TermDepositParameterResponse();

        when(mapper.getBP17RequestFromInputData(DEFAULT_PRODUCT_ID, amount, periodicity))
                .thenReturn(request);
        when(trxSanbaService.trxBP17(request)).thenReturn(bp17Response);
        when(mapper.getParametersResponseFromBP17(bp17Response)).thenReturn(expectedResponse);

        TermDepositParameterResponse actualResponse =
                service.getParameters(DEFAULT_PRODUCT_ID, amount, periodicity);

        assertSame(expectedResponse, actualResponse);

        verify(mapper).getBP17RequestFromInputData(DEFAULT_PRODUCT_ID, amount, periodicity);
        verify(trxSanbaService).trxBP17(request);
        verify(mapper).getParametersResponseFromBP17(bp17Response);
        verify(mapper, never()).getListParametersHardCode();
    }

    @Test
    @DisplayName("Debe validar campos obligatorios para defaultProductId")
    void getParametersShouldValidateRequiredFieldsForDefaultProduct() {
        String amount = "1000000";
        String periodicity = "120";

        TrxBP17Request request = new TrxBP17Request();
        TrxBP17Response bp17Response = new TrxBP17Response();
        TermDepositParameterResponse expectedResponse = new TermDepositParameterResponse();

        when(mapper.getBP17RequestFromInputData(DEFAULT_PRODUCT_ID, amount, periodicity))
                .thenReturn(request);
        when(trxSanbaService.trxBP17(request)).thenReturn(bp17Response);
        when(mapper.getParametersResponseFromBP17(bp17Response)).thenReturn(expectedResponse);

        service.getParameters(DEFAULT_PRODUCT_ID, amount, periodicity);

        verify(errorService).isNull(DEFAULT_PRODUCT_ID, "productId");
        verify(errorService).isBlank(DEFAULT_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_format", DEFAULT_PRODUCT_ID, "productId");
        verify(regexUtils).validateRegex("product_length", DEFAULT_PRODUCT_ID, "productId");

        verify(errorService).isNull(amount, "amount");
        verify(errorService).isBlank(amount, "amount");
        verify(regexUtils).validateRegex("amount", amount, "amount");

        verify(errorService).isNull(periodicity, "periodicity");
        verify(errorService).isBlank(periodicity, "periodicity");
        verify(regexUtils).validateRegex("product_format", periodicity, "periodicity");
    }

    @Test
    @DisplayName("Debe propagar excepción cuando el producto no existe")
    void getParametersShouldThrowWhenProductDoesNotExist() {
        String invalidProductId = "999999";

        RuntimeException expectedException = new RuntimeException("product not found");

        when(errorService.getGeneral())
                .thenReturn(new java.util.HashMap<>() {{
                    put("product_not_found", "Producto no encontrado");
                }});

        when(errorService.serviceExceptionBuilder(
                any(),
                eq("Producto no encontrado"),
                any()
        )).thenReturn(expectedException);

        RuntimeException actualException = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> service.getParameters(invalidProductId, null, null)
        );

        assertSame(expectedException, actualException);

        verify(mapper, never()).getProposalsResponse();
        verify(mapper, never()).getListParametersHardCode();
        verifyNoInteractions(trxSanbaService);
    }
}




