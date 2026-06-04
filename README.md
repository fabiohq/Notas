package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermDepositUtilsValidationTest {

    @Mock RegexUtils regexUtils;
    @Mock ErrorService errorService;
    @Mock ProductDirectoryService productDirectoryService;
    @Mock TermDepositParametersService termDepositParametersService;
    @Mock BanksService banksService;

    private TermDepositUtils utils;

    @BeforeEach
    void setUp() {
        utils = new TermDepositUtils(
                regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "validSettlements", new String[]{"BGMF", "RETF", "ITEA"});
        ReflectionTestUtils.setField(utils, "settlementCoditionCode", "CV");
    }

    @Test
    void settlementConditionCodeTransformationShouldReturnSWhenInputIsC() {
        assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
    }

    @Test
    void settlementConditionCodeTransformationShouldReturnNWhenInputIsNotC() {
        assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("V"));
    }

    @Test
    void settlementConditionCodeValidationShouldReturnTrueOnlyForC() {
        assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
        assertFalse(TermDepositUtils.settlementConditionCodeValidation("V"));
    }

    @Test
    void frecuencyValidationShouldReturnFalseWhenFrequencyExists() {
        assertFalse(utils.frecuencyValidation("30"));
    }

    @Test
    void frecuencyValidationShouldThrowWhenFrequencyDoesNotExist() {
        when(errorService.getGeneral()).thenReturn(new HashMap<>());
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any()))
                .thenReturn(new ServiceException("frequency_not_found"));

        assertThrows(ServiceException.class, () -> utils.frecuencyValidation("15"));
    }

    @Test
    void amountValidationShouldPassWhenAmountIsInsideRange() {
        assertDoesNotThrow(() -> utils.amountValidation("1000", "500", "2000"));
    }

    @Test
    void amountValidationShouldThrowWhenAmountIsUnderLimit() {
        when(errorService.getGeneral()).thenReturn(new HashMap<>());
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any()))
                .thenReturn(new ServiceException("amount_under_limit"));

        assertThrows(ServiceException.class,
                () -> utils.amountValidation("100", "500", "2000"));
    }

    @Test
    void amountValidationShouldThrowWhenAmountIsOverLimit() {
        when(errorService.getGeneral()).thenReturn(new HashMap<>());
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any()))
                .thenReturn(new ServiceException("amount_over_limit"));

        assertThrows(ServiceException.class,
                () -> utils.amountValidation("3000", "500", "2000"));
    }

    @Test
    void padLeftWithZerosShouldPadWhenInputIsShorterThanLength() {
        assertEquals("000123", TermDepositUtils.padLeftWithZeros("123", 6));
    }

    @Test
    void padLeftWithZerosShouldReturnSameInputWhenLengthIsEnough() {
        assertEquals("123456", TermDepositUtils.padLeftWithZeros("123456", 3));
    }

    @Test
    void validateAccountIdTypeShouldAcceptOnlyCCOrCA() {
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("CE"));
    }

    @Test
    void validateSettlementConceptShouldAcceptOnlyValidConcepts() {
        assertTrue(TermDepositUtils.validateSettlementConcept("BGMF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("RETF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("ITEA"));
        assertFalse(TermDepositUtils.validateSettlementConcept("OTRO"));
    }

    @Test
    void validateSettlementConceptTypeCodeShouldReturnExpectedCode() {
        assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
        assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
    }
}
