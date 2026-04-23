package com.santander.bnc.bsn049.bncbsn049mscontracts.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "CONTRACTS");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "api-services-v3");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "P-F-9400");

        HashMap<String, String> type = new HashMap<>();
        type.put("onlyNumbers", "^[0-9]+$");
        type.put("onlyNumbers_error", "Only numbers allowed");
        type.put("onlyLetters", "^[a-zA-Z]+$");
        // sin onlyLetters_error a propósito para cubrir el default "Invalid format"

        ReflectionTestUtils.setField(regexUtils, "type", type);
    }

    @Test
    void shouldNotThrowWhenRegexMatches() {
        assertDoesNotThrow(() ->
                regexUtils.validateRegex("onlyNumbers", "123456", "account")
        );
    }

    @Test
    void shouldThrowServiceExceptionWhenRegexDoesNotMatchAndCustomErrorExists() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> regexUtils.validateRegex("onlyNumbers", "ABC123", "account")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertNotNull(ex.getError());
        assertEquals("CONTRACTS-P-F-9400", ex.getError().getCode());
        assertEquals("error", ex.getError().getLevel());
        assertEquals("'account': Only numbers allowed", ex.getError().getMessage());
        assertEquals("contracts-api-services-v3: 'account': Only numbers allowed",
                ex.getError().getDescription());
    }

    @Test
    void shouldThrowServiceExceptionWhenRegexDoesNotMatchAndDefaultMessageIsUsed() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> regexUtils.validateRegex("onlyLetters", "12345", "name")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertNotNull(ex.getError());
        assertEquals("CONTRACTS-P-F-9400", ex.getError().getCode());
        assertEquals("error", ex.getError().getLevel());
        assertEquals("'name': Invalid format", ex.getError().getMessage());
        assertEquals("contracts-api-services-v3: 'name': Invalid format",
                ex.getError().getDescription());
    }
}