package com.santander.bnc.bsn049.bncbsn049mscountries.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParametersEnumsTest {

    @Test
    void shouldReturnCorrectValueForEachEnum() {
        assertEquals("0008", ParametersEnums.TOWNS.value());
        assertEquals("0009", ParametersEnums.STATES.value());
        assertEquals("0314", ParametersEnums.WAY_TYPE.value());
        assertEquals("0116", ParametersEnums.CIVIL_STATE.value());
        assertEquals("0305", ParametersEnums.LIST_EMPLACEMENT.value());
        assertEquals("0026", ParametersEnums.LIST_BCO_EXT.value());
        assertEquals("0113", ParametersEnums.DOCU_TYPE.value());
        assertEquals("0112", ParametersEnums.COUNTRY.value());
    }

    @Test
    void shouldReturnTrueWhenValueContainsValidCode() {
        assertTrue(ParametersEnums.isValidEnumValue("test0008"));
        assertTrue(ParametersEnums.isValidEnumValue("abc0009xyz"));
        assertTrue(ParametersEnums.isValidEnumValue("0314"));
        assertTrue(ParametersEnums.isValidEnumValue("0116"));
        assertTrue(ParametersEnums.isValidEnumValue("0305"));
        assertTrue(ParametersEnums.isValidEnumValue("0026"));
        assertTrue(ParametersEnums.isValidEnumValue("0113"));
        assertTrue(ParametersEnums.isValidEnumValue("0112"));
    }

    @Test
    void shouldReturnFalseWhenValueIsNull() {
        assertFalse(ParametersEnums.isValidEnumValue(null));
    }

    @Test
    void shouldReturnFalseWhenValueDoesNotContainAnyValidCode() {
        assertFalse(ParametersEnums.isValidEnumValue(""));
        assertFalse(ParametersEnums.isValidEnumValue("9999"));
        assertFalse(ParametersEnums.isValidEnumValue("invalid"));
    }
}