package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;

class TermDepositUtilsTest {

    private TermDepositUtils utils;
    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        RegexUtils regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);

        utils = new TermDepositUtils(
                regexUtils,
                errorService,
                mock(ProductDirectoryService.class),
                mock(TermDepositParametersService.class),
                mock(BanksService.class)
        );

        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "validSettlements", new String[]{"C", "V"});
        ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "CV");

        when(errorService.getGeneral()).thenReturn(new HashMap<>());
        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
                .thenReturn(new ServiceException(HttpStatus.BAD_REQUEST,
                        ErrorDTO.builder().message("error").build()));
    }

    @Test
    void shouldCoverStaticUtils() {
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("+123.456"));
        assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("V"));
        assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
        assertFalse(TermDepositUtils.settlementConditionCodeValidation("V"));
        assertEquals("00012", TermDepositUtils.padLeftWithZeros("12", 5));
        assertEquals("12345", TermDepositUtils.padLeftWithZeros("12345", 5));
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("TI"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals("123,45", TermDepositUtils.toLinea2Decimal("000000000012345"));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,05", TermDepositUtils.format15DigitNumber("5"));
        assertEquals("0,50", TermDepositUtils.format15DigitNumber("50"));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("0000012345"));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
        assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
    }

    @Test
    void shouldCoverFrequencyValidAndInvalid() {
        assertFalse(utils.frecuencyValidation("30"));
        assertThrows(ServiceException.class, () -> utils.frecuencyValidation("15"));
    }

    @Test
    void shouldCoverAmountValidation() {
        assertDoesNotThrow(() -> utils.amountValidation("1000", "500", "2000"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("100", "500", "2000"));
        assertThrows(ServiceException.class, () -> utils.amountValidation("3000", "500", "2000"));
    }

    @Test
    void shouldCoverSettlementConditionInputs() {
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidation("C"));
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidationForDeposits("V"));

        assertThrows(ServiceException.class, () -> utils.settlementContionCodeInputValidation("X"));
        assertThrows(ServiceException.class, () -> utils.settlementContionCodeInputValidationForDeposits("X"));
    }

    @Test
    void shouldCoverSettlementConceptValidations() {
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
}
