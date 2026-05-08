package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(regexUtils, "msName", "PROSPECTS");
        ReflectionTestUtils.setField(regexUtils, "msVersion", "v1");
        ReflectionTestUtils.setField(regexUtils, "level", "error");
        ReflectionTestUtils.setField(regexUtils, "code", "P-F-9400");

        set("regexOnlyNumbers", "^\\d+$", "only numbers");
        set("regexStrictLength8", "^\\d{8}$", "length 8");
        set("regexStrictLength11", "^\\d{11}$", "length 11");
        set("regexStrictCharLength2", "^[A-Z]{2}$", "char length 2");
        set("regexAdressLength", "^.{1,50}$", "address length");
        set("regexAdressFormat", "^[A-Za-z0-9 ]+$", "address format");
        set("regexEmail", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", "email");
        set("regexEmailBetween", "^.{5,80}$", "email between");
        set("regexEmailLength", "^.{1,80}$", "email length");
        set("regexEmailFormatLeft", "^[A-Za-z0-9._%+-]+$", "email left");
        set("regexEmailFormatRight", "^[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", "email right");
        set("regexEmailFormatFirstChar", "^[A-Za-z0-9]$", "email first char");
        set("regexPhoneLength", "^\\d{10}$", "phone length");
        set("regexPhoneFormat", "^\\d+$", "phone format");
        set("regexInternationaCodeFormat", "^\\d+$", "international format");
        set("regexInternationaCodeLength", "^\\d{2,3}$", "international length");
        set("regexText20Length", "^.{1,20}$", "text 20 length");
        set("regexText20Format", "^[\\p{L} ]+$", "text 20 format");
        set("regexText40Length", "^.{1,40}$", "text 40 length");
        set("regexText40Format", "^[\\p{L} ]+$", "text 40 format");
        set("regexCountryCodeLength", "^[A-Z]{2}$", "country length");
        set("regexCountryCodeFormat", "^[A-Z]{2}$", "country format");
        set("regexGenderCodeFormat", "^[MF]$", "gender format");
        set("regexBirthdayDate", "^\\d{4}-\\d{2}-\\d{2}$", "birthday");
        set("regexIssueDateFormat", "^\\d{4}-\\d{2}-\\d{2}$", "issue date");
        set("regexGenderCodeLength", "^.{1}$", "gender length");
        set("regexSecondLastNameFormat", "^[\\p{L} ]*$", "second last name");
        set("regexSecondLastNameCeFormat", "^[\\p{L} ]*$", "second last name ce");
        set("regexTownDescpritionFormat", "^[\\p{L} ]+$", "town format");
        set("regexTownDescriptionLength", "^.{1,40}$", "town length");
        set("regexTownCodeLength", "^\\d{5}$", "town code length");
        set("regexStateCodeFormat", "^\\d+$", "state format");
        set("regexStateCodeLength", "^\\d{2}$", "state length");
    }

    private void set(String regexField, String regexValue, String errorMessage) {
        ReflectionTestUtils.setField(regexUtils, regexField, regexValue);
        ReflectionTestUtils.setField(regexUtils, regexField + "Error", errorMessage);
    }

    @Test
    void validateRegexShouldPassForAllRegexTypes() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "123", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_11, "12345678901", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, "CC", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, "Cra 10", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, "Cra 10", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL, "test@test.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, "test@test.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, "test@test.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, "test", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, "test.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, "t", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, "3001234567", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, "3001234567", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, "57", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, "57", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, "Perez", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, "Pérez", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, "Juan Carlos", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, "Juan Carlos", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, "CO", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, "CO", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, "M", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, "2000-01-01", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, "2020-01-01", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, "M", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_FORMAT, "Gomez", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_CE_FORMAT, "Gomez", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, "Medellin", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, "Medellin", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, "05001", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_FORMAT, "05", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_LENGTH, "05", "field"));
    }

    @Test
    void validateRegexShouldThrowWhenValueDoesNotMatch() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "documentNumber"));

        assertEquals(400, ex.getCode().value());
        assertNotNull(ex.getError());
        assertTrue(ex.getError().getMessage().contains("documentNumber"));
    }

    @Test
    void validateRegexShouldThrowForEachRegexTypeWhenInvalid() {
        for (RegexTypes type : RegexTypes.values()) {
            assertThrows(ServiceException.class,
                    () -> regexUtils.validateRegex(type, "@@@", "field"));
        }
    }
}