package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void cleanAndFormatNumberStringShouldRemovePlusMinusDotsAndTrim() {
        String result = TermDepositFundsUtils.cleanAndFormatNumberString(" +12.345- ");

        assertEquals("12345", result);
    }

    @Test
    void validateRegexShouldNotThrowWhenValueMatches() {
        RegexUtils regexUtils = buildRegexUtils();

        assertDoesNotThrow(() ->
                regexUtils.validateRegex("deposit_id", "12345", "deposit_id")
        );
    }

    @Test
    void validateRegexShouldThrowServiceExceptionWhenValueDoesNotMatch() {
        RegexUtils regexUtils = buildRegexUtils();

        assertThrows(ServiceException.class, () ->
                regexUtils.validateRegex("deposit_id", "ABC", "deposit_id")
        );
    }

    @Test
    void validateRegexShouldUseDefaultMessageWhenErrorMessageDoesNotExist() {
        RegexUtils regexUtils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "\\d+");

        regexUtils.setType(type);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "BNCBSN049");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "9400");

        assertThrows(ServiceException.class, () ->
                regexUtils.validateRegex("only_numbers", "ABC", "field")
        );
    }

    private RegexUtils buildRegexUtils() {
        RegexUtils regexUtils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("deposit_id", "\\d+");
        type.put("deposit_id_error", "Only numbers allowed");

        regexUtils.setType(type);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "BNCBSN049");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "9400");

        return regexUtils;
    }
}
