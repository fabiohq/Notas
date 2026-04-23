package com.santander.bnc.bsn049.bncbsn049mscontracts.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorType;

@ExtendWith(MockitoExtension.class)
class ContractsUtilsTest {

    @Mock
    private RegexUtils regexUtils;

    @Mock
    private ErrorService errorService;

    @Mock
    private BanksService banksService;

    @InjectMocks
    private ContractsUtils contractsUtils;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(contractsUtils, "productCode", "04");
        ReflectionTestUtils.setField(contractsUtils, "subproductCode", "0250");
        ReflectionTestUtils.setField(contractsUtils, "validFrequencies",
                new String[] { "90", "180", "270", "360", "540", "720" });
        ReflectionTestUtils.setField(contractsUtils, "validSettlements",
                new String[] { "BGMF", "REFT", "ITEA" });
        ReflectionTestUtils.setField(contractsUtils, "SETTLEMENT_CONDITION_CODES", "TMVC");
        ReflectionTestUtils.setField(contractsUtils, "bankId", "0065");
        ReflectionTestUtils.setField(contractsUtils, "centerId", "0060");
    }

    @Test
    void shouldCleanFormatNumberString() {
        String result = ContractsUtils.cleanAndFormatNumberString("1.234,56");
        assertEquals("123456", result);
    }

    @Test
    void shouldTransformSettlementConditionCode() {
        assertEquals("S", ContractsUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", ContractsUtils.settlementConditionCodeTransformation("X"));
    }

    @Test
    void shouldValidateSettlementConditionCode() {
        assertTrue(ContractsUtils.settlementConditionCodeValidation("C"));
        assertFalse(ContractsUtils.settlementConditionCodeValidation("X"));
    }

    @Test
    void shouldAddLeftZeros() {
        assertEquals("00123", ContractsUtils.padLeftWithZeros("123", 5));
        assertEquals("12345", ContractsUtils.padLeftWithZeros("12345", 5));
    }

    @Test
    void shouldValidateAccountIdType() {
        assertTrue(ContractsUtils.validateAccountIdType("CC"));
        assertTrue(ContractsUtils.validateAccountIdType("CA"));
        assertFalse(ContractsUtils.validateAccountIdType("XX"));
    }

    @Test
    void shouldValidateSettlementConcept() {
        assertTrue(ContractsUtils.validateSettlementConcept("BGMF"));
        assertTrue(ContractsUtils.validateSettlementConcept("REFT"));
        assertTrue(ContractsUtils.validateSettlementConcept("ITEA"));
        assertFalse(ContractsUtils.validateSettlementConcept("AAA"));
    }

    @Test
    void shouldValidateSettlementConceptTypeCode() {
        assertEquals("M", ContractsUtils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", ContractsUtils.validateSettlementConceptTypeCode("REFT"));
        assertEquals("C", ContractsUtils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldParseDouble() {
        assertEquals(1234.56, ContractsUtils.parseDouble("1.234,56"));
    }

    @Test
    void shouldConvertToLineaDecimal() {
        assertEquals("1234567890123.45",
                ContractsUtils.toLinea2Decimal("123456789012345"));
    }

    @Test
    void shouldFormat15DigitNumber() {
        assertEquals("0,00", ContractsUtils.format15DigitNumber(null));
        assertEquals("0,00", ContractsUtils.format15DigitNumber(""));
        assertEquals("0,05", ContractsUtils.format15DigitNumber("5"));
        assertEquals("0,15", ContractsUtils.format15DigitNumber("15"));
        assertEquals("1,23", ContractsUtils.format15DigitNumber("123"));
    }

    @Test
    void shouldRemoveLeadingZeros() {
        assertEquals("123", ContractsUtils.removeLeadingZeros("000123"));
        assertEquals("0", ContractsUtils.removeLeadingZeros(null));
    }

    @Test
    void shouldReturnFalseWhenFrequencyIsValid() {
        boolean result = contractsUtils.frequencyValidation("90");
        assertFalse(result);
    }

    @Test
    void shouldThrowWhenFrequencyIsInvalid() {
        HashMap<String, String> general = new HashMap<>();
        general.put("frequency_not_found", "frequency not found");

        ServiceException expected = new ServiceException(
                HttpStatus.NOT_FOUND,
                ErrorDTO.builder()
                        .code("COD-001")
                        .message("frequency not found")
                        .level("ERROR")
                        .description("desc")
                        .build()
        );

        when(errorService.getGeneral()).thenReturn(general);
        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.NOT_FOUND),
                eq("frequency not found"),
                eq(ErrorType.FUNCTIONAL)))
                .thenReturn(expected);

        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> contractsUtils.frequencyValidation("999")
        );

        assertSame(expected, ex);
    }

    @Test
    void shouldNotThrowWhenAmountIsWithinRange() {
        assertDoesNotThrow(() ->
                contractsUtils.amountValidation("50", "10", "100"));
    }

    @Test
    void shouldThrowWhenAmountIsUnderLimit() {
        HashMap<String, String> general = new HashMap<>();
        general.put("amount_under_limit", "amount under limit");

        ServiceException expected = new ServiceException(
                HttpStatus.BAD_REQUEST,
                ErrorDTO.builder()
                        .code("COD-002")
                        .message("amount under limit")
                        .level("ERROR")
                        .description("desc")
                        .build()
        );

        when(errorService.getGeneral()).thenReturn(general);
        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.BAD_REQUEST),
                eq("amount under limit"),
                eq(ErrorType.FUNCTIONAL)))
                .thenReturn(expected);

        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> contractsUtils.amountValidation("5", "10", "100")
        );

        assertSame(expected, ex);
    }

    @Test
    void shouldThrowWhenAmountIsOverLimit() {
        HashMap<String, String> general = new HashMap<>();
        general.put("amount_over_limit", "amount over limit");

        ServiceException expected = new ServiceException(
                HttpStatus.BAD_REQUEST,
                ErrorDTO.builder()
                        .code("COD-003")
                        .message("amount over limit")
                        .level("ERROR")
                        .description("desc")
                        .build()
        );

        when(errorService.getGeneral()).thenReturn(general);
        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.BAD_REQUEST),
                eq("amount over limit"),
                eq(ErrorType.FUNCTIONAL)))
                .thenReturn(expected);

        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> contractsUtils.amountValidation("150", "10", "100")
        );

        assertSame(expected, ex);
    }
}