package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexTypesTest {

    @Test
    void enumShouldContainAllValues() {
        assertEquals(31, RegexTypes.values().length);

        assertNotNull(RegexTypes.valueOf("ONLY_NUMBERS"));
        assertNotNull(RegexTypes.valueOf("STRICT_LENGTH_8"));
        assertNotNull(RegexTypes.valueOf("STRICT_LENGTH_11"));
        assertNotNull(RegexTypes.valueOf("STRICT_CHAR_LENGTH_2"));
        assertNotNull(RegexTypes.valueOf("EMAIL"));
        assertNotNull(RegexTypes.valueOf("EMAIL_LENGTH"));
        assertNotNull(RegexTypes.valueOf("EMAIL_FORMAT_LEFT"));
        assertNotNull(RegexTypes.valueOf("EMAIL_FORMAT_RIGHT"));
        assertNotNull(RegexTypes.valueOf("EMAIL_FORMAT_FIRST_CHAR"));
        assertNotNull(RegexTypes.valueOf("PHONE_LENGTH"));
        assertNotNull(RegexTypes.valueOf("PHONE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("INTERNATIONAL_CODE_LENGTH"));
        assertNotNull(RegexTypes.valueOf("INTERNATIONAL_CODE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("ADDRESS_LENGTH"));
        assertNotNull(RegexTypes.valueOf("TEXT_20_LENGTH"));
        assertNotNull(RegexTypes.valueOf("TEXT_20_FORMAT"));
        assertNotNull(RegexTypes.valueOf("TEXT_40_LENGTH"));
        assertNotNull(RegexTypes.valueOf("TEXT_40_FORMAT"));
        assertNotNull(RegexTypes.valueOf("ADDRESS_FORMAT"));
        assertNotNull(RegexTypes.valueOf("COUNTRY_LENGTH"));
        assertNotNull(RegexTypes.valueOf("COUNTRT_FORMAT"));
        assertNotNull(RegexTypes.valueOf("GENDER_CODE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("BIRTH_DAY_DATE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("ISSUE_DATE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("GENDER_CODE_LENGTH"));
        assertNotNull(RegexTypes.valueOf("SECOND_LAST_NAME_FORMAT"));
        assertNotNull(RegexTypes.valueOf("SECOND_LAST_NAME_CE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("REGEX_TOWN_DESCRIPTION_FORMAT"));
        assertNotNull(RegexTypes.valueOf("REGEX_TOWN_DESCRIPTION_LENGTH"));
        assertNotNull(RegexTypes.valueOf("REGEX_TOWN_CODE_LENGTH"));
        assertNotNull(RegexTypes.valueOf("REGEX_STATE_CODE_FORMAT"));
        assertNotNull(RegexTypes.valueOf("REGEX_STATE_CODE_LENGTH"));
    }

    @Test
    void enumNameShouldReturnExpectedValue() {
        assertEquals("ONLY_NUMBERS", RegexTypes.ONLY_NUMBERS.name());
        assertEquals("EMAIL", RegexTypes.EMAIL.name());
        assertEquals("REGEX_STATE_CODE_LENGTH", RegexTypes.REGEX_STATE_CODE_LENGTH.name());
    }
}