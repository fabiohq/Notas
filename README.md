package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.ParticipantsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MinimumAmount;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MaximumAmount;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.AmountRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.Product;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.SubproductRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;

class TermDepositUtilsTest {

    private RegexUtils regexUtils;
    private ErrorService errorService;
    private ProductDirectoryService productDirectoryService;
    private TermDepositParametersService termDepositParametersService;
    private BanksService banksService;
    private TermDepositUtils utils;

    @BeforeEach
    void setUp() {
        regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);
        productDirectoryService = mock(ProductDirectoryService.class);
        termDepositParametersService = mock(TermDepositParametersService.class);
        banksService = mock(BanksService.class);

        utils = new TermDepositUtils(
                regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

        ReflectionTestUtils.setField(utils, "productCode", "940250");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "bankId", "001");
        ReflectionTestUtils.setField(utils, "centerId", "1234");
        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "validSettlements", new String[]{"C", "V"});
        ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "CV");

        HashMap<String, String> errors = new HashMap<>();
        errors.put("productcode_not_found", "product error");
        errors.put("subproductid_not_found", "subproduct error");
        errors.put("bankid_not_found", "bank error");
        errors.put("centerid_not_found", "center error");
        errors.put("accountIdType_not_found", "account error");
        errors.put("settlementconditioncode_not_found", "settlement error");
        errors.put("periodtypecode_not_found", "period error");
        errors.put("frecuency_not_found", "frequency error");
        errors.put("purposecode_not_found", "purpose error");
        errors.put("amount_under_limit", "amount under");
        errors.put("amount_over_limit", "amount over");
        errors.put("amount_under_limit_economicData", "amount under");
        errors.put("amount_over_limit_economicData", "amount over");
        errors.put("economicData.initialTotalInvested.currency_not_found", "currency error");
        errors.put("deposits.placement.destinationFunds.bankcode_not_found", "bank code error");

        when(errorService.getGeneral()).thenReturn(errors);
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
                .thenAnswer(inv -> new ServiceException(inv.getArgument(0),
                        ErrorDTO.builder().message(String.valueOf(inv.getArgument(1))).build()));
    }

    @Test
    void shouldCoverStaticMethods() {
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("+123.456"));
        assertEquals("123,45", TermDepositUtils.toLinea2Decimal("000000000012345"));
        assertEquals("00012", TermDepositUtils.padLeftWithZeros("12", 5));
        assertEquals("12345", TermDepositUtils.padLeftWithZeros("12345", 5));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
        assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,05", TermDepositUtils.format15DigitNumber("5"));
        assertEquals("0,50", TermDepositUtils.format15DigitNumber("50"));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("0000012345"));
        assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("V"));
        assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
        assertFalse(TermDepositUtils.settlementConditionCodeValidation("V"));
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("XX"));
    }

    @Test
    void shouldCoverFrequencyAndAmountValidation() {
        assertFalse(utils.frecuencyValidation("30"));
        assertThrows(ServiceException.class, () -> utils.frecuencyValidation("15"));

        assertDoesNotThrow(() -> utils.amountValidation("1000", "500", "2000"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("100", "500", "2000"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("3000", "500", "2000"));
    }

    @Test
    void shouldCoverSettlementValidations() {
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidation("C"));
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidationForDeposits("V"));
        assertThrows(ServiceException.class, () -> utils.settlementContionCodeInputValidation("X"));
        assertThrows(ServiceException.class, () -> utils.settlementContionCodeInputValidationForDeposits("X"));

        assertDoesNotThrow(() -> utils.validateSettlementConcept("BGMF"));
        assertDoesNotThrow(() -> utils.validateSettlementConcept("RETF"));
        assertDoesNotThrow(() -> utils.validateSettlementConcept("ITEA"));
        assertThrows(ServiceException.class, () -> utils.validateSettlementConcept("BAD"));

        assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("C"));
        assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("D"));
        assertThrows(ServiceException.class, () -> utils.validateSettlementConceptTypeCodeInput("X"));

        assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
        assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldCoverSimpleInputValidations() {
        utils.deleteProspectCdtInputValidation("12345678901234567890");

        CalculateDepositSummaryRequestDTO dto = new CalculateDepositSummaryRequestDTO();
        ParticipantsRequestDTO participants = new ParticipantsRequestDTO();
        participants.setParticipantId("12345678");
        dto.setParticipants(participants);

        utils.calculateDepositSummaryInputValidation(dto);

        verify(regexUtils, atLeastOnce()).validateRegex(anyString(), anyString(), anyString());
    }

    @Test
    void shouldCoverSimulatePlacementInputValidationOk() {
        AmountRangeResponse range = new AmountRangeResponse();
        MinimumAmount min = new MinimumAmount();
        min.setAmount("500");
        MaximumAmount max = new MaximumAmount();
        max.setAmount("2000");
        range.setMinimumAmount(min);
        range.setMaximumAmount(max);

        when(productDirectoryService.amountRange(any())).thenReturn(range);

        RequestSimulatePlacementDTO request = simulateRequest("940250", "001", "1000", "30", "D", "C");

        assertDoesNotThrow(() -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldCoverSimulatePlacementInputValidationErrors() {
        AmountRangeResponse range = new AmountRangeResponse();
        MinimumAmount min = new MinimumAmount();
        min.setAmount("500");
        MaximumAmount max = new MaximumAmount();
        max.setAmount("2000");
        range.setMinimumAmount(min);
        range.setMaximumAmount(max);

        when(productDirectoryService.amountRange(any())).thenReturn(range);

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("000000", "001", "1000", "30", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "999", "1000", "30", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "30", "M", "C"),
                        new AmountRangeRequest()));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "15", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "30", "D", "X"),
                        new AmountRangeRequest()));
    }

    @Test
    void shouldCoverTermDepositPublicValidatorsWithReflectionRequest() throws Exception {
        RequestTermDepositsDTO request = termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30");

        assertDoesNotThrow(() -> utils.validateBank(request));
        assertDoesNotThrow(() -> utils.validateCenter(request));
        assertDoesNotThrow(() -> utils.validateProduct(request));
        assertDoesNotThrow(() -> utils.validateSubproduct(request));
        assertDoesNotThrow(() -> utils.validateAccountIdType2(request));
        assertDoesNotThrow(() -> utils.validateNationalIdentification(request));
        assertDoesNotThrow(() -> utils.validateSettlementConditionCode(request));
        assertDoesNotThrow(() -> utils.validateTotalInvestedAmount(request));
        assertDoesNotThrow(() -> utils.validateCurrency(request));

        assertThrows(ServiceException.class, () -> utils.validateBank(termRequest("940250", "001", "999", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateCenter(termRequest("940250", "001", "001", "9999", "CC", "001", "123456789", "C", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateProduct(termRequest("000000", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateSubproduct(termRequest("940250", "999", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateAccountIdType2(termRequest("940250", "001", "001", "1234", "XX", "001", "123456789", "C", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateSettlementConditionCode(termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "X", "010", "1000", "COP", "30")));
        assertThrows(ServiceException.class, () -> utils.validateCurrency(termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "USD", "30")));
    }

    @Test
    void shouldCoverBankValidationAndBankCode() throws Exception {
        BanksDTO banksDTO = new BanksDTO();
        BanksParametersDTO bank = new BanksParametersDTO();
        bank.setBankId("001");
        bank.setBankName("BANK");
        banksDTO.setBanks(List.of(bank));

        when(banksService.banksResponse(any())).thenReturn(banksDTO);

        RequestTermDepositsDTO request = termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30");

        assertDoesNotThrow(() -> utils.bankValidation(new BanksParametersRequest(), "001"));
        assertDoesNotThrow(() -> utils.validateBankCode(request, new BanksParametersRequest()));
        assertThrows(ServiceException.class, () -> utils.bankValidation(new BanksParametersRequest(), "999"));
    }

    @Test
    void shouldCoverPurposeCodeValidation() throws Exception {
        TermDepositParametersResponse response = mock(TermDepositParametersResponse.class, RETURNS_DEEP_STUBS);
        when(termDepositParametersService.termDepositParameters(any())).thenReturn(response);

        assertDoesNotThrow(() -> utils.purposeCodeValidation(new TermDepositParametersRequest(), "010"));
        assertDoesNotThrow(() -> utils.validatePurposeCode(termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30"), new TermDepositParametersRequest()));
    }

    @Test
    void shouldCoverPrivateModifyPlacementId() {
        String result = ReflectionTestUtils.invokeMethod(utils, "modifyPlacementId", "1234567890");
        assertEquals("12345", result);
    }

    private RequestSimulatePlacementDTO simulateRequest(String productCode, String subproductCode, String amountValue,
                                                       String frequency, String periodType, String settlement) {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId(subproductCode);

        Product product = new Product();
        product.setProductCode(productCode);
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount(amountValue);

        RequestDTO periodicity = new RequestDTO();
        periodicity.setFrequency(frequency);
        periodicity.setPeriodTypeCode(periodType);

        RequestSimulatePlacementDTO request = new RequestSimulatePlacementDTO();
        request.setProduct(product);
        request.setAmount(amount);
        request.setPeriodicity(periodicity);
        request.setSettlementConditionCode(settlement);

        return request;
    }

    private RequestTermDepositsDTO termRequest(String productCode, String subproductCode, String bankId,
                                               String centerId, String accountType, String bankCode,
                                               String nationalId, String settlementCode, String purposeCode,
                                               String amount, String currency, String frequency) throws Exception {
        RequestTermDepositsDTO request = new RequestTermDepositsDTO();

        Object product = newObj("TermDepositProductDTO");
        Object subproduct = newObj("TermDepositSubproductDTO");
        call(subproduct, "setSubproductId", subproductCode);
        call(product, "setProductCode", productCode);
        call(product, "setSubproduct", subproduct);
        call(request, "setProduct", product);

        Object bank = newObj("TermDepositBankDTO");
        Object center = newObj("TermDepositCenterDTO");
        call(center, "setCenterId", centerId);
        call(bank, "setBankId", bankId);
        call(bank, "setCenter", center);
        call(request, "setBank", bank);

        Object amountDto = newObj("TermDepositAmountDTO");
        call(amountDto, "setAmount", amount);
        call(amountDto, "setCurrency", currency);

        Object economic = newObj("TermDepositEconomicDataDTO");
        Object settlement = newObj("TermDepositSettlementsDTO");
        Object concept = newObj("TermDepositSettlementConceptDTO");
        Object conceptAmount = newObj("TermDepositAmountDTO");
        call(conceptAmount, "setAmount", "100");
        call(conceptAmount, "setCurrency", "COP");
        call(concept, "setCode", "BGMF");
        call(concept, "setTypeCode", "C");
        call(concept, "setRate", "0");
        call(concept, "setAmount", conceptAmount);
        call(settlement, "setSettlementConcept", concept);
        call(economic, "setInitialTotalInvested", amountDto);
        call(economic, "setSettlements", List.of(settlement));
        call(request, "setEconomicData", economic);

        Object deposit = newObj("TermDepositDepositDTO");
        Object placement = newObj("TermDepositPlacementDTO");
        Object destinationFunds = newObj("TermDepositDestinationFundsDTO");
        Object account = newObj("TermDepositAccountDTO");
        Object periodicity = newObj("TermDepositPeriodicityDTO");
        Object settlementCondition = newObj("TermDepositSettlementConditionDTO");

        call(account, "setNationalIdentification", nationalId);
        call(destinationFunds, "setAccountIdType", accountType);
        call(destinationFunds, "setBankcode", bankCode);
        call(destinationFunds, "setAccount", account);

        call(periodicity, "setFrequency", frequency);

        call(settlementCondition, "setCode", settlementCode);

        call(placement, "setDestinationFunds", destinationFunds);
        call(placement, "setPeriodicity", periodicity);
        call(placement, "setSettlementCondition", settlementCondition);
        call(placement, "setPurposeCode", purposeCode);

        call(deposit, "setPlacement", placement);
        call(request, "setDeposit", deposit);

        return request;
    }

    private Object newObj(String simpleName) throws Exception {
        return Class.forName("com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits." + simpleName)
                .getDeclaredConstructor()
                .newInstance();
    }

    private void call(Object target, String methodName, Object value) throws Exception {
        Method method = findMethod(target.getClass(), methodName);
        assertNotNull(method, methodName);
        method.setAccessible(true);
        method.invoke(target, value);
    }

    private Method findMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                return method;
            }
        }
        return null;
    }
}
