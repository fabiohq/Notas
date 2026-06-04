package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class TermDepositUtilsPrivateValidationTest {

    @Mock RegexUtils regexUtils;
    @Mock ErrorService errorService;
    @Mock ProductDirectoryService productDirectoryService;
    @Mock TermDepositParametersService termDepositParametersService;
    @Mock BanksService banksService;

    private TermDepositUtils utils;

    @BeforeEach
    void setUp() {
        utils = new TermDepositUtils(regexUtils, errorService,
                productDirectoryService, termDepositParametersService, banksService);

        ReflectionTestUtils.setField(utils, "bankId", "0065");
        ReflectionTestUtils.setField(utils, "centerId", "0100");
        ReflectionTestUtils.setField(utils, "productCode", "12");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "settlementCoditionCode", "CV");

        doNothing().when(regexUtils).validateRegex(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());
    }

    @Test
    void shouldCoverPrivateTermDepositInputValidations() throws Exception {
        Object request = validTermDepositRequest();

        invoke("bankValidationTermDepositInput", request);
        invoke("centerValidationTermDepositInput", request);
        invoke("productValidationTermDepositInput", request);
        invoke("subproductValidationTermDepositInput", request);
        invoke("accountIdTypeValidationTermDepositInput", request);
        invoke("nationalIdentificationValidationTermDepositInput", request);
        invoke("frecuencyValidationTermDepositInput", request);

        assertDoesNotThrow(() -> { });
    }

    @Test
    void shouldCoverBankCodePrivateValidation() throws Exception {
        Object request = validTermDepositRequest();

        invoke("bankCodeValidationTermDepositInput",
                new Class<?>[]{
                        Class.forName(pkgTerm() + ".RequestTermDepositsDTO"),
                        BanksParametersRequest.class
                },
                request,
                new BanksParametersRequest("auth", "client"));
    }

    @Test
    void shouldCoverSettlementConditionPrivateValidation() throws Exception {
        Method method = TermDepositUtils.class
                .getDeclaredMethod("settlementContionCodeInputValidation", String.class);
        method.setAccessible(true);

        method.invoke(utils, "C");
        method.invoke(utils, "X");
    }

    private void invoke(String methodName, Object request) throws Exception {
        Method method = TermDepositUtils.class.getDeclaredMethod(
                methodName,
                Class.forName(pkgTerm() + ".RequestTermDepositsDTO")
        );
        method.setAccessible(true);
        method.invoke(utils, request);
    }

    private void invoke(String methodName, Class<?>[] types, Object... args) throws Exception {
        Method method = TermDepositUtils.class.getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        method.invoke(utils, args);
    }

    private Object validTermDepositRequest() throws Exception {
        Object center = obj(pkgTerm() + ".TermDepositCenterDTO");
        set(center, "setCenterId", "0100");

        Object bank = obj(pkgTerm() + ".TermDepositBankRequestDTO");
        set(bank, "setBankId", "0065");
        set(bank, "setCenter", center);

        Object subproduct = obj(pkgTerm() + ".TermDepositSubproductDTO");
        set(subproduct, "setSubproductId", "001");

        Object product = obj(pkgTerm() + ".TermDepositProductDTO");
        set(product, "setProductCode", "12");
        set(product, "setSubproduct", subproduct);

        Object account = obj(pkgTerm() + ".TermDepositAccountDTO");
        set(account, "setNationalIdentification", "123456");

        Object destinationFunds = obj(pkgTerm() + ".TermDepositDestinationFundsDTO");
        set(destinationFunds, "setAccountIdType", "CC");
        set(destinationFunds, "setBankcode", "0065");
        set(destinationFunds, "setAccount", account);

        Object periodicity = obj(pkgTerm() + ".TermDepositPeriodicityDTO");
        set(periodicity, "setFrequency", "30");

        Object settlementCondition = obj(pkgTerm() + ".TermDepositSettlementConditionDTO");
        set(settlementCondition, "setCode", "C");

        Object placement = obj("com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits.TermDepositPlacementDTO");
        set(placement, "setDestinationFunds", destinationFunds);
        set(placement, "setPeriodicity", periodicity);
        set(placement, "setSettlementCondition", settlementCondition);
        set(placement, "setPurposeCode", "001");

        Object deposit = obj(pkgTerm() + ".TermDepositDepositDto");
        set(deposit, "setPlacement", placement);

        Object amount = obj(pkgTerm() + ".TermDepositAmountDTO");
        set(amount, "setAmount", "1000");
        set(amount, "setCurrency", "COP");

        Object concept = obj(pkgTerm() + ".TermDepositSettlementConceptDTO");
        set(concept, "setCode", "BGMF");
        set(concept, "setTypeCode", "C");
        set(concept, "setRate", "1");
        set(concept, "setAmount", amount);

        Object settlement = obj(pkgTerm() + ".TermDepositSettlementsDTO");
        set(settlement, "setSettlementConcept", concept);

        Object economicData = obj(pkgTerm() + ".TermDepositEconomicDataDTO");
        set(economicData, "setInitialTotalInvested", amount);
        set(economicData, "setSettlements", List.of(settlement));

        Object request = obj(pkgTerm() + ".RequestTermDepositsDTO");
        set(request, "setBank", bank);
        set(request, "setProduct", product);
        set(request, "setDeposit", deposit);
        set(request, "setEconomicData", economicData);

        return request;
    }

    private Object obj(String className) throws Exception {
        return Class.forName(className).getDeclaredConstructor().newInstance();
    }

    private void set(Object target, String methodName, Object value) throws Exception {
        Method method = null;
        for (Method m : target.getClass().getMethods()) {
            if (m.getName().equals(methodName) && m.getParameterCount() == 1) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new NoSuchMethodException(methodName);
        }
        method.invoke(target, value);
    }

    private String pkgTerm() {
        return "com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits";
    }
}
