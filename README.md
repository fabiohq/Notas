
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.MaxAndMinAmountDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits.TermDepositPlacementDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TermDepositUtilsTest {

    @Mock private RegexUtils regexUtils;
    @Mock private ErrorService errorService;
    @Mock private ProductDirectoryService productDirectoryService;
    @Mock private TermDepositParametersService termDepositParametersService;
    @Mock private BanksService banksService;

    private TermDepositUtils utils;
    private ServiceException serviceException;

    @BeforeEach
    void setUp() {
        utils = new TermDepositUtils(
                regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

        serviceException = new ServiceException(HttpStatus.BAD_REQUEST, null);

        ReflectionTestUtils.setField(utils, "productCode", "60");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "validSettlements", new String[]{"C"});
        ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "C");
        ReflectionTestUtils.setField(utils, "bankId", "0065");
        ReflectionTestUtils.setField(utils, "centerId", "0100");

        lenient().when(errorService.getGeneral()).thenReturn(generalErrors());
        lenient().when(errorService.serviceExceptionBuilder(any(), any(), any()))
                .thenReturn(serviceException);
    }

    @Test
    void shouldCoverStaticMethods() {
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("+123.456 "));
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("-123.456"));
        assertEquals("00000123", TermDepositUtils.padLeftWithZeros("123", 8));
        assertEquals("12345678", TermDepositUtils.padLeftWithZeros("12345678", 8));
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("XX"));
        assertTrue(TermDepositUtils.validateSettlementConcept("BGMF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("RETF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("ITEA"));
        assertFalse(TermDepositUtils.validateSettlementConcept("OTRO"));
        assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("X"));
        assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
        assertFalse(TermDepositUtils.settlementConditionCodeValidation("X"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("-1.234,56"));
        assertEquals("1234567890123,45", TermDepositUtils.toLinea2Decimal("123456789012345"));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber("   "));
        assertEquals("0,05", TermDepositUtils.format15DigitNumber("000000000000005"));
        assertEquals("0,50", TermDepositUtils.format15DigitNumber("000000000000050"));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("000000000012345"));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
        assertEquals("0", TermDepositUtils.removeLeadingZeros("   "));
        assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
    }

    @Test
    void shouldCoverValidateSettlementConceptTypeCode() {
        assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
        assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldValidateFrequencyWhenExistsAndThrowWhenNotExists() {
        assertFalse(utils.frecuencyValidation("30"));
        assertThrows(ServiceException.class, () -> utils.frecuencyValidation("999"));
    }

    @Test
    void shouldValidateAmountInsideRangeAndThrowOutsideRange() {
        assertDoesNotThrow(() -> utils.amountValidation("5000", "1000", "9999"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("999", "1000", "9999"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("10000", "1000", "9999"));
    }

    @Test
    void shouldValidatePurposeCodeWhenExistsAndThrowWhenNotExists() {
        TermDepositParametersRequest request = new TermDepositParametersRequest();

        when(termDepositParametersService.termDepositParameters(request))
                .thenReturn(new TermDepositParametersResponse(List.of(
                        new TermDepositParametersDTO("12", "content", "description")
                )));

        assertEquals("12", utils.purposeCodeValidation(request, "129999"));

        when(termDepositParametersService.termDepositParameters(request))
                .thenReturn(new TermDepositParametersResponse(List.of()));

        assertThrows(ServiceException.class,
                () -> utils.purposeCodeValidation(request, "129999"));
    }

    @Test
    void shouldValidateBankWhenExistsAndThrowWhenNotExists() {
        BanksParametersRequest request = new BanksParametersRequest();

        BanksDTO banksDTO = BanksDTO.builder()
                .banks(List.of(BanksParametersDTO.builder()
                        .bankId("0065")
                        .bankName("Santander")
                        .build()))
                .build();

        when(banksService.banksResponse(request)).thenReturn(banksDTO);

        assertEquals("0065", utils.bankValidation(request, "0065"));

        when(banksService.banksResponse(request))
                .thenReturn(BanksDTO.builder().banks(List.of()).build());

        assertThrows(ServiceException.class,
                () -> utils.bankValidation(request, "9999"));
    }

    @Test
    void shouldValidateSimulatePlacementHappyPath() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        AmountRangeRequest amountRangeRequest = new AmountRangeRequest();

        when(productDirectoryService.amountRange(amountRangeRequest))
                .thenReturn(new AmountRangeResponse(
                        new MaxAndMinAmountDto("1000", "COP"),
                        new MaxAndMinAmountDto("999999", "COP")
                ));

        assertDoesNotThrow(() ->
                utils.simulatePlacementInputValidation(request, amountRangeRequest));

        assertEquals("60001", amountRangeRequest.getProductId());
    }

    @Test
    void shouldThrowInSimulatePlacementWhenProductIsInvalid() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        request.getProduct().setProductCode("99");

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldThrowInSimulatePlacementWhenSubproductIsInvalid() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        request.getProduct().getSubproduct().setSubproductId("999");

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldThrowInSimulatePlacementWhenAmountIsUnderLimit() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        request.getAmount().setAmount("500");

        when(productDirectoryService.amountRange(any()))
                .thenReturn(new AmountRangeResponse(
                        new MaxAndMinAmountDto("1000", "COP"),
                        new MaxAndMinAmountDto("999999", "COP")
                ));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldThrowInSimulatePlacementWhenPeriodTypeIsInvalid() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        request.getPeriodicity().setPeriodTypeCode("M");

        when(productDirectoryService.amountRange(any()))
                .thenReturn(new AmountRangeResponse(
                        new MaxAndMinAmountDto("1000", "COP"),
                        new MaxAndMinAmountDto("999999", "COP")
                ));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldThrowInSimulatePlacementWhenSettlementConditionIsInvalid() {
        RequestSimulatePlacementDTO request = buildSimulateRequest();
        request.setSettlementConditionCode("X");

        when(productDirectoryService.amountRange(any()))
                .thenReturn(new AmountRangeResponse(
                        new MaxAndMinAmountDto("1000", "COP"),
                        new MaxAndMinAmountDto("999999", "COP")
                ));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldValidateTermDepositsHappyPath() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");

        mockValidBank();
        mockValidPurpose();

        assertDoesNotThrow(() -> utils.termDepositsInputValidation(
                request,
                new AmountRangeRequest(),
                new TermDepositParametersRequest(),
                new BanksParametersRequest()
        ));
    }

    @Test
    void shouldValidateTermDepositsForRETFAndITEA() {
        mockValidBank();
        mockValidPurpose();

        assertDoesNotThrow(() -> utils.termDepositsInputValidation(
                buildTermDepositsRequest("RETF"),
                new AmountRangeRequest(),
                new TermDepositParametersRequest(),
                new BanksParametersRequest()
        ));

        assertDoesNotThrow(() -> utils.termDepositsInputValidation(
                buildTermDepositsRequest("ITEA"),
                new AmountRangeRequest(),
                new TermDepositParametersRequest(),
                new BanksParametersRequest()
        ));
    }

    @Test
    void shouldThrowWhenTermDepositBankIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getBank().setBankId("9999");

        assertThrows(ServiceException.class,
                () -> utils.termDepositsInputValidation(
                        request,
                        new AmountRangeRequest(),
                        new TermDepositParametersRequest(),
                        new BanksParametersRequest()
                ));
    }

    @Test
    void shouldThrowWhenCenterIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getBank().getCenter().setCenterId("9999");

        assertThrows(ServiceException.class,
                () -> utils.validateCenter(request));
    }

    @Test
    void shouldThrowWhenProductIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getProduct().setProductCode("99");

        assertThrows(ServiceException.class,
                () -> utils.validateProduct(request));
    }

    @Test
    void shouldThrowWhenSubproductIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getProduct().getSubproduct().setSubproductId("999");

        assertThrows(ServiceException.class,
                () -> utils.validateSubproduct(request));
    }

    @Test
    void shouldThrowWhenAccountIdTypeIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getDeposit().getPlacement().getDestinationFunds().setAccountIdType("XX");

        assertThrows(ServiceException.class,
                () -> utils.validateAccountIdType2(request));
    }

    @Test
    void shouldThrowWhenBankCodeIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");

        when(banksService.banksResponse(any()))
                .thenReturn(BanksDTO.builder().banks(List.of()).build());

        assertThrows(ServiceException.class,
                () -> utils.validateBankCode(request, new BanksParametersRequest()));
    }

    @Test
    void shouldValidateNationalIdentification() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");

        assertDoesNotThrow(() -> utils.validateNationalIdentification(request));
    }

    @Test
    void shouldThrowWhenPurposeCodeDoesNotExist() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");

        when(termDepositParametersService.termDepositParameters(any()))
                .thenReturn(new TermDepositParametersResponse(List.of()));

        assertThrows(ServiceException.class,
                () -> utils.validatePurposeCode(request, new TermDepositParametersRequest()));
    }

    @Test
    void shouldValidateTotalInvestedAmount() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");

        assertDoesNotThrow(() -> utils.validateTotalInvestedAmount(request));
    }

    @Test
    void shouldThrowWhenCurrencyIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getEconomicData().getInitialTotalInvested().setCurrency("USD");

        assertThrows(ServiceException.class,
                () -> utils.validateCurrency(request));
    }

    @Test
    void shouldThrowWhenSettlementConceptCodeIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BAD");

        mockValidBank();
        mockValidPurpose();

        assertThrows(IllegalArgumentException.class,
                () -> utils.termDepositsInputValidation(
                        request,
                        new AmountRangeRequest(),
                        new TermDepositParametersRequest(),
                        new BanksParametersRequest()
                ));
    }

    @Test
    void shouldThrowWhenSettlementConceptCurrencyIsInvalid() {
        RequestTermDepositsDTO request = buildTermDepositsRequest("BGMF");
        request.getEconomicData()
                .getSettlements()
                .get(0)
                .getSettlementConcept()
                .getAmount()
                .setCurrency("USD");

        mockValidBank();
        mockValidPurpose();

        assertThrows(ServiceException.class,
                () -> utils.termDepositsInputValidation(
                        request,
                        new AmountRangeRequest(),
                        new TermDepositParametersRequest(),
                        new BanksParametersRequest()
                ));
    }

    private RequestSimulatePlacementDTO buildSimulateRequest() {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId("001");

        Product product = new Product();
        product.setProductCode("60");
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount("5000");

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

    private RequestTermDepositsDTO buildTermDepositsRequest(String conceptCode) {
        TermDepositCenterDTO center = new TermDepositCenterDTO();
        center.setCenterId("0100");

        TermDepositBankRequestDTO bank = new TermDepositBankRequestDTO();
        bank.setBankId("0065");
        bank.setCenter(center);

        TermDepositSubproductDTO subproduct = new TermDepositSubproductDTO();
        subproduct.setSubproductId("001");

        TermDepositProductDTO product = new TermDepositProductDTO();
        product.setProductCode("60");
        product.setSubproduct(subproduct);

        TermDepositAccountDTO account = new TermDepositAccountDTO();
        account.setNationalIdentification("123456789");

        TermDepositDestinationFundsDTO destinationFunds = new TermDepositDestinationFundsDTO();
        destinationFunds.setAccountIdType("CC");
        destinationFunds.setBankcode("0065");
        destinationFunds.setAccount(account);

        TermDepositPeriodicityDTO periodicity = new TermDepositPeriodicityDTO();
        periodicity.setFrequency("30");

        TermDepositSettlementConditionDTO settlementCondition = new TermDepositSettlementConditionDTO();
        settlementCondition.setCode("C");

        TermDepositPlacementDTO placement = new TermDepositPlacementDTO();
        placement.setDestinationFunds(destinationFunds);
        placement.setPeriodicity(periodicity);
        placement.setSettlementCondition(settlementCondition);
        placement.setPurposeCode("129999");

        TermDepositDepositDto deposit = new TermDepositDepositDto();
        deposit.setPlacement(placement);

        TermDepositAmountDTO initialTotalInvested = new TermDepositAmountDTO();
        initialTotalInvested.setAmount("5000");
        initialTotalInvested.setCurrency("COP");

        TermDepositAmountDTO settlementAmount = new TermDepositAmountDTO();
        settlementAmount.setAmount("100");
        settlementAmount.setCurrency("COP");

        TermDepositSettlementConceptDTO concept = new TermDepositSettlementConceptDTO();
        concept.setCode(conceptCode);
        concept.setTypeCode("C");
        concept.setRate("1");
        concept.setAmount(settlementAmount);

        TermDepositSettlementsDTO settlement = new TermDepositSettlementsDTO();
        settlement.setSettlementConcept(concept);

        TermDepositEconomicDataDTO economicData = new TermDepositEconomicDataDTO();
        economicData.setInitialTotalInvested(initialTotalInvested);
        economicData.setSettlements(List.of(settlement));

        RequestTermDepositsDTO request = new RequestTermDepositsDTO();
        request.setBank(bank);
        request.setProduct(product);
        request.setDeposit(deposit);
        request.setEconomicData(economicData);

        return request;
    }

    private void mockValidBank() {
        when(banksService.banksResponse(any()))
                .thenReturn(BanksDTO.builder()
                        .banks(List.of(BanksParametersDTO.builder()
                                .bankId("0065")
                                .bankName("Santander")
                                .build()))
                        .build());
    }

    private void mockValidPurpose() {
        when(termDepositParametersService.termDepositParameters(any()))
                .thenReturn(new TermDepositParametersResponse(List.of(
                        new TermDepositParametersDTO("12", "content", "description")
                )));
    }

    private Map<String, String> generalErrors() {
        Map<String, String> errors = new HashMap<>();
        errors.put("productcode_not_found", "product not found");
        errors.put("subproductid_not_found", "subproduct not found");
        errors.put("amount_under_limit", "amount under limit");
        errors.put("amount_over_limit", "amount over limit");
        errors.put("frequency_not_found", "frequency not found");
        errors.put("periodtypecode_not_found", "period type not found");
        errors.put("settlementconditioncode_not_found", "settlement condition not found");
        errors.put("bankId_not_found", "bank not found");
        errors.put("centerId_not_found", "center not found");
        errors.put("accountIdType_not_found", "account type not found");
        errors.put("settlementcondition.code_not_found", "settlement condition code not found");
        errors.put("purposeCode_not_found", "purpose not found");
        errors.put("economicData.initialTotalInvested.currency_not_found", "currency not found");
        errors.put("economicData.settlements.settlementConcept.code_not_found", "concept not found");
        errors.put("economicData.settlements.settlemenmtConcept.amount.currency_not_found", "concept currency not found");
        return errors;
    }
}
