package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    @Test
    void validateRegexShouldPassWhenValueMatches() {
        RegexUtils utils = buildRegexUtils();

        assertDoesNotThrow(() ->
                utils.validateRegex("only_numbers", "12345", "field")
        );
    }

    @Test
    void validateRegexShouldThrowWhenValueDoesNotMatchWithCustomMessage() {
        RegexUtils utils = buildRegexUtils();

        assertThrows(ServiceException.class, () ->
                utils.validateRegex("only_numbers", "ABC", "field")
        );
    }

    @Test
    void validateRegexShouldThrowWithDefaultMessageWhenErrorMessageDoesNotExist() {
        RegexUtils utils = buildRegexUtils();
        utils.getType().remove("only_numbers_error");

        assertThrows(ServiceException.class, () ->
                utils.validateRegex("only_numbers", "ABC", "field")
        );
    }

    private RegexUtils buildRegexUtils() {
        RegexUtils utils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^\\d+$");
        type.put("only_numbers_error", "Only numbers allowed");

        utils.setType(type);

        ReflectionTestUtils.setField(utils, "msName", "MS");
        ReflectionTestUtils.setField(utils, "msVersion", "1.0");
        ReflectionTestUtils.setField(utils, "level", "ERROR");
        ReflectionTestUtils.setField(utils, "code", "001");

        return utils;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MovementConceptUtilsTest {

    @Test
    void shouldSetAndGetType() {
        MovementConceptUtils utils = new MovementConceptUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("INTP", "Intereses");
        type.put("RFIP", "Retefuente");

        utils.setType(type);

        assertEquals(type, utils.getType());
        assertEquals("Intereses", utils.getType().get("INTP"));
        assertEquals("Retefuente", utils.getType().get("RFIP"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositUtilsStaticTest {

    @Test
    void parseDoubleShouldNormalizeSymbolsDotsAndComma() {
        assertEquals(1234.56D, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals(1234.56D, TermDepositUtils.parseDouble("-1.234,56"));
    }

    @Test
    void toLinea2DecimalShouldConvertLastTwoDigitsToDecimals() {
        assertEquals("1234567890123,45",
                TermDepositUtils.toLinea2Decimal("123456789012345"));
    }

    @Test
    void format15DigitNumberShouldReturnZeroWhenNullBlankOrEmpty() {
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(" "));
    }

    @Test
    void format15DigitNumberShouldFormatOneTwoAndManyDigits() {
        assertEquals("0,05", TermDepositUtils.format15DigitNumber("000000000000005"));
        assertEquals("0,50", TermDepositUtils.format15DigitNumber("000000000000050"));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("000000000012345"));
    }

    @Test
    void removeLeadingZerosShouldReturnZeroWhenNullBlankOrEmpty() {
        assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(" "));
    }

    @Test
    void removeLeadingZerosShouldRemoveZerosButKeepSingleZero() {
        assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
        assertEquals("0", TermDepositUtils.removeLeadingZeros("0000"));
    }
}
