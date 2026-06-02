package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MaximumAmount;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MinimumAmount;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;

class TermDepositUtilsTermDepositsInputValidationTest {

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

        ReflectionTestUtils.setField(utils, "bankId", "001");
        ReflectionTestUtils.setField(utils, "centerId", "1234");
        ReflectionTestUtils.setField(utils, "productCode", "940250");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[] { "30", "60", "90" });
        ReflectionTestUtils.setField(utils, "validSettlements", new String[] { "C", "V" });
        ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "CV");

        HashMap<String, String> general = new HashMap<>();
        general.put("bankId_not_found", "bankId_not_found");
        general.put("centerId_not_found", "centerId_not_found");
        general.put("productcode_not_found", "productcode_not_found");
        general.put("subproductid_not_found", "subproductid_not_found");
        general.put("accountIdType_not_found", "accountIdType_not_found");
        general.put("economicData.initialTotalInvested.currency_not_found", "currency_not_found");
        general.put("amount_under_limit_economicData", "amount_under_limit");
        general.put("amount_over_limit_economicData", "amount_over_limit");
        general.put("deposits.settlementConcept.currency_not_found", "settlement_currency_not_found");
        general.put("purposecode_not_found", "purposecode_not_found");

        when(errorService.getGeneral()).thenReturn(general);
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), anyString(), any(ErrorType.class)))
                .thenAnswer(inv -> new ServiceException(inv.getArgument(0), inv.getArgument(1)));

        mockAmountRange("500", "2000");
        mockBanks("001");
        mockPurpose("010");
    }

    @Test
    void shouldPassTermDepositsInputValidationWithBGMF_RETF_ITEA() throws Exception {
        RequestTermDepositsDTO request = validRequest();

        assertDoesNotThrow(() -> utils.termDepositsInputValidation(
                request,
                new AmountRangeRequest("auth", "client", null),
                new TermDepositParametersRequest("940250", "auth", "client"),
                new BanksParametersRequest("auth", "client")
        ));

        verify(productDirectoryService).amountRange(any(AmountRangeRequest.class));
        verify(banksService).banksResponse(any(BanksParametersRequest.class));
        verify(termDepositParametersService).termDepositParameters(any(TermDepositParametersRequest.class));
        verify(regexUtils, atLeastOnce()).validateRegex(anyString(), anyString(), anyString());
        verify(errorService, atLeastOnce()).isBlank(anyString(), anyString());
    }

    @Test
    void shouldThrowWhenAmountIsUnderRange() throws Exception {
        RequestTermDepositsDTO request = validRequest();
        setNestedAmount(request, "100", "COP");

        assertThrows(ServiceException.class, () -> utils.validateAmountRange(
                request,
                new AmountRangeRequest("auth", "client", null)
        ));
    }

    @Test
    void shouldThrowWhenAmountIsOverRangePlusGmf() throws Exception {
        RequestTermDepositsDTO request = validRequest();
        setNestedAmount(request, "999999", "COP");

        assertThrows(ServiceException.class, () -> utils.validateAmountRange(
                request,
                new AmountRangeRequest("auth", "client", null)
        ));
    }

    @Test
    void shouldThrowForInvalidMainFields() throws Exception {
        assertThrows(ServiceException.class, () -> utils.validateBank(requestWith("bankId", "999")));
        assertThrows(ServiceException.class, () -> utils.validateCenter(requestWith("centerId", "9999")));
        assertThrows(ServiceException.class, () -> utils.validateProduct(requestWith("productCode", "000000")));
        assertThrows(ServiceException.class, () -> utils.validateSubproduct(requestWith("subproductId", "999")));
        assertThrows(ServiceException.class, () -> utils.validateAccountIdType2(requestWith("accountIdType", "XX")));
        assertThrows(ServiceException.class, () -> utils.validateSettlementConditionCode(requestWith("settlementCode", "X")));
        assertThrows(ServiceException.class, () -> utils.validateCurrency(requestWith("currency", "USD")));
    }

    @Test
    void shouldThrowForInvalidBankCode() throws Exception {
        mockBanks("999");

        RequestTermDepositsDTO request = validRequest();

        assertThrows(ServiceException.class, () -> utils.validateBankCode(
                request,
                new BanksParametersRequest("auth", "client")
        ));
    }

    @Test
    void shouldThrowForInvalidSettlementConceptCurrencyInsideTermDepositsValidation() throws Exception {
        RequestTermDepositsDTO request = validRequest();
        setFirstSettlementCurrency(request, "USD");

        assertThrows(ServiceException.class, () -> utils.termDepositsInputValidation(
                request,
                new AmountRangeRequest("auth", "client", null),
                new TermDepositParametersRequest("940250", "auth", "client"),
                new BanksParametersRequest("auth", "client")
        ));
    }

    private RequestTermDepositsDTO validRequest() throws Exception {
        RequestTermDepositsDTO request = new RequestTermDepositsDTO();

        Object product = ensure(request, "getProduct", "setProduct");
        set(product, "setProductCode", "940250");
        Object subproduct = ensure(product, "getSubproduct", "setSubproduct");
        set(subproduct, "setSubproductId", "001");

        Object bank = ensure(request, "getBank", "setBank");
        set(bank, "setBankId", "001");
        Object center = ensure(bank, "getCenter", "setCenter");
        set(center, "setCenterId", "1234");

        Object deposit = ensure(request, "getDeposit", "setDeposit");
        Object placement = ensure(deposit, "getPlacement", "setPlacement");

        Object destinationFunds = ensure(placement, "getDestinationFunds", "setDestinationFunds");
        set(destinationFunds, "setAccountIdType", "CC");
        set(destinationFunds, "setBankcode", "001");

        Object account = ensure(destinationFunds, "getAccount", "setAccount");
        set(account, "setNationalIdentification", "123456789");

        Object settlementCondition = ensure(placement, "getSettlementCondition", "setSettlementCondition");
        set(settlementCondition, "setCode", "C");

        Object periodicity = ensure(placement, "getPeriodicity", "setPeriodicity");
        set(periodicity, "setFrequency", "30");

        set(placement, "setPurposeCode", "010");

        Object economicData = ensure(request, "getEconomicData", "setEconomicData");
        Object initialTotalInvested = ensure(economicData, "getInitialTotalInvested", "setInitialTotalInvested");
        set(initialTotalInvested, "setAmount", "1000");
        set(initialTotalInvested, "setCurrency", "COP");

        Object bgmf = settlement("BGMF", "C", "1,00", "100", "COP");
        Object retf = settlement("RETF", "D", "1,00", "100", "COP");
        Object itea = settlement("ITEA", "C", "1,00", "100", "COP");

        set(economicData, "setSettlements", List.of(bgmf, retf, itea));

        return request;
    }

    private RequestTermDepositsDTO requestWith(String field, String value) throws Exception {
        RequestTermDepositsDTO request = validRequest();

        switch (field) {
            case "bankId":
                set(get(request, "getBank"), "setBankId", value);
                break;
            case "centerId":
                set(get(get(request, "getBank"), "getCenter"), "setCenterId", value);
                break;
            case "productCode":
                set(get(request, "getProduct"), "setProductCode", value);
                break;
            case "subproductId":
                set(get(get(request, "getProduct"), "getSubproduct"), "setSubproductId", value);
                break;
            case "accountIdType":
                set(get(get(get(get(request, "getDeposit"), "getPlacement"), "getDestinationFunds")), "setAccountIdType", value);
                break;
            case "settlementCode":
                set(get(get(get(request, "getDeposit"), "getPlacement"), "getSettlementCondition"), "setCode", value);
                break;
            case "currency":
                setNestedAmount(request, "1000", value);
                break;
            default:
                break;
        }

        return request;
    }

    private void setNestedAmount(RequestTermDepositsDTO request, String amount, String currency) throws Exception {
        Object economicData = get(request, "getEconomicData");
        Object initialTotalInvested = get(economicData, "getInitialTotalInvested");

        set(initialTotalInvested, "setAmount", amount);
        set(initialTotalInvested, "setCurrency", currency);
    }

    @SuppressWarnings("unchecked")
    private void setFirstSettlementCurrency(RequestTermDepositsDTO request, String currency) throws Exception {
        Object economicData = get(request, "getEconomicData");
        List<Object> settlements = (List<Object>) get(economicData, "getSettlements");
        Object concept = get(settlements.get(0), "getSettlementConcept");
        Object amount = get(concept, "getAmount");

        set(amount, "setCurrency", currency);
    }

    private Object settlement(String code, String typeCode, String rate, String amountValue, String currency) throws Exception {
        String basePackage = "com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.";

        Object settlement = Class.forName(basePackage + "TermDepositSettlementsDTO")
                .getDeclaredConstructor()
                .newInstance();

        Object concept = Class.forName(basePackage + "TermDepositSettlementConceptDTO")
                .getDeclaredConstructor()
                .newInstance();

        Object amount = Class.forName(basePackage + "TermDepositAmountDTO")
                .getDeclaredConstructor()
                .newInstance();

        set(amount, "setAmount", amountValue);
        set(amount, "setCurrency", currency);

        set(concept, "setCode", code);
        set(concept, "setTypeCode", typeCode);
        set(concept, "setRate", rate);
        set(concept, "setAmount", amount);

        set(settlement, "setSettlementConcept", concept);

        return settlement;
    }

    private void mockAmountRange(String min, String max) {
        MinimumAmount minimumAmount = new MinimumAmount();
        minimumAmount.setAmount(min);

        MaximumAmount maximumAmount = new MaximumAmount();
        maximumAmount.setAmount(max);

        AmountRangeResponse response = new AmountRangeResponse();
        response.setMinimumAmount(minimumAmount);
        response.setMaximumAmount(maximumAmount);

        when(productDirectoryService.amountRange(any(AmountRangeRequest.class))).thenReturn(response);
    }

    private void mockBanks(String validBankCode) {
        BanksParametersDTO bank = new BanksParametersDTO();
        bank.setBankId(validBankCode);
        bank.setBankName("BANK");

        BanksDTO banksDTO = new BanksDTO();
        banksDTO.setBanks(List.of(bank));

        when(banksService.banksResponse(any(BanksParametersRequest.class))).thenReturn(banksDTO);
    }

    private void mockPurpose(String code) {
        TermDepositParametersDTO parameter = new TermDepositParametersDTO();
        parameter.setCode(code);
        parameter.setDescription("PURPOSE");

        TermDepositParametersResponse response = new TermDepositParametersResponse();
        response.setParameters(List.of(parameter));

        when(termDepositParametersService.termDepositParameters(any(TermDepositParametersRequest.class)))
                .thenReturn(response);
    }

    private Object ensure(Object target, String getterName, String setterName) throws Exception {
        Object current = get(target, getterName);

        if (current != null) {
            return current;
        }

        Method setter = findSetter(target.getClass(), setterName);
        Class<?> parameterType = setter.getParameterTypes()[0];
        Object value = parameterType.getDeclaredConstructor().newInstance();

        setter.invoke(target, value);

        return value;
    }

    private Object get(Object target, String getterName) throws Exception {
        Method getter = target.getClass().getMethod(getterName);
        return getter.invoke(target);
    }

    private void set(Object target, String setterName, Object value) throws Exception {
        Method setter = findSetter(target.getClass(), setterName);
        setter.invoke(target, value);
    }

    private Method findSetter(Class<?> clazz, String setterName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                return method;
            }
        }
        throw new IllegalArgumentException("Setter not found: " + setterName + " in " + clazz.getName());
    }
}
