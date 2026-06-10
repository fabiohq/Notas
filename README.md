package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MovementConceptUtilsTest {

    @Test
    void shouldCoverGettersSettersEqualsHashCodeAndToString() {
        MovementConceptUtils one = new MovementConceptUtils();
        MovementConceptUtils two = new MovementConceptUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("INTP", "INTERESES");

        one.setType(type);
        two.setType(type);

        assertEquals(type, one.getType());
        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("type"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^[0-9]+$");
        type.put("only_numbers_error", "Only numbers are allowed");
        type.put("letters", "^[A-Z]+$");

        regexUtils.setType(type);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "BSN049");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "1.0.0");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "ERROR");
        ReflectionTestUtils.setField(regexUtils, "CODE", "400");
    }

    @Test
    void shouldPassWhenRegexMatches() {
        assertDoesNotThrow(() ->
                regexUtils.validateRegex("only_numbers", "123456", "deposit_id")
        );
    }

    @Test
    void shouldThrowServiceExceptionWhenRegexDoesNotMatchWithCustomMessage() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> regexUtils.validateRegex("only_numbers", "ABC", "deposit_id")
        );

        assertNotNull(exception);
    }

    @Test
    void shouldThrowServiceExceptionWhenRegexDoesNotMatchWithDefaultMessage() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> regexUtils.validateRegex("letters", "123", "field")
        );

        assertNotNull(exception);
    }

    @Test
    void shouldCoverDataMethods() {
        RegexUtils another = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^[0-9]+$");

        another.setType(type);

        assertEquals(type, another.getType());
        assertTrue(another.toString().contains("type"));
    }
}
