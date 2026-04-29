package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ParametersEnums;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void shouldTranslateCountryToXXWhenCountryExists() {
        assertEquals("CO", DataUtils.translateCountryToXX("COL"));
    }

    @Test
    void shouldReturnSameCountryWhenCountryDoesNotExistToXX() {
        assertEquals("XXX", DataUtils.translateCountryToXX("XXX"));
    }

    @Test
    void shouldTranslateCountryToXXXWhenIsoExists() {
        assertEquals("COL", DataUtils.translateCountryToXXX("CO"));
    }

    @Test
    void shouldReturnSameCountryWhenIsoDoesNotExistToXXX() {
        assertEquals("XX", DataUtils.translateCountryToXXX("XX"));
    }

    @Test
    void shouldTranslateValueCodeCountry() {
        assertEquals("CO", DataUtils.translateValueCode(ParametersEnums.COUNTRY.value(), "COL"));
    }

    @Test
    void shouldTranslateValueCodeState() {
        assertEquals("CO-ANT", DataUtils.translateValueCode(ParametersEnums.STATES.value(), "CO-ANT"));
    }

    @Test
    void shouldReturnSameValueWhenListCodeIsNotCountryOrState() {
        assertEquals("ABC", DataUtils.translateValueCode("9999", "ABC"));
    }

    @Test
    void shouldReturnSameStateWhenStateDoesNotExist() {
        assertEquals("CO-XXX", DataUtils.translateValueCode(ParametersEnums.STATES.value(), "CO-XXX"));
    }

    @Test
    void shouldReturnDepartmentDescriptionWhenExists() {
        assertEquals("05", DataUtils.translateDepartmentDesc("CO-ANT"));
    }

    @Test
    void shouldReturnEmptyDepartmentDescriptionWhenDoesNotExist() {
        assertEquals("", DataUtils.translateDepartmentDesc("CO-XXX"));
    }

    @Test
    void shouldReturnStateCodeByCountryCodeAndStateIso() {
        assertEquals("05", DataUtils.getStateCodeByCountryCodeAndStateIso("CO", "ANT"));
    }

    @Test
    void shouldReturnEmptyStateCodeWhenCountryAndStateIsoDoNotExist() {
        assertEquals("", DataUtils.getStateCodeByCountryCodeAndStateIso("CO", "XXX"));
    }
}