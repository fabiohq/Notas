package com.santander.bnc.bsn049.bncbsn049mscountries.utils;

import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.StatesResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.TownsResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.enums.ParametersEnums;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void shouldTranslateCountryCodeWhenListCodeIsCountry() {
        String result = DataUtils.translateValueCode(ParametersEnums.COUNTRY.value(), "COL");

        assertEquals("CO", result);
    }

    @Test
    void shouldReturnSameCodeWhenCountryCodeDoesNotExist() {
        String result = DataUtils.translateValueCode(ParametersEnums.COUNTRY.value(), "XXX");

        assertEquals("XXX", result);
    }

    @Test
    void shouldReturnSameValueWhenListCodeIsNotCountry() {
        String result = DataUtils.translateValueCode("OTHER", "COL");

        assertEquals("COL", result);
    }

    @Test
    void shouldReturnIsoAlphaCodeByCountryCode() {
        String result = DataUtils.getIsoAlphaCodeByCountryCode("CO");

        assertEquals("COL", result);
    }

    @Test
    void shouldReturnEmptyWhenIsoAlphaCountryCodeDoesNotExist() {
        String result = DataUtils.getIsoAlphaCodeByCountryCode("XX");

        assertEquals("", result);
    }

    @Test
    void shouldReturnInternalByCountryCode() {
        String result = DataUtils.getInternalByCountryCode("CO");

        assertEquals("COL", result);
    }

    @Test
    void shouldReturnEmptyWhenInternalCountryCodeDoesNotExist() {
        String result = DataUtils.getInternalByCountryCode("XX");

        assertEquals("", result);
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<DataUtils> constructor = DataUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());
    }
}






>>>>>>>>>>>>


package com.santander.bnc.bsn049.bncbsn049mscountries.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class GUtilsTest {

    @Test
    void shouldReturnLogConstants() {
        assertEquals("--> Start ", GUtils.SLOG);
        assertEquals("<-- End ", GUtils.ELOG);
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<GUtils> constructor = GUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());
    }
}





>>>>>>>>>>>>>>




package com.santander.bnc.bsn049.bncbsn049mscountries.utils;

import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.StatesResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.TownsResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParamsUtilsTest {

    @Test
    void shouldFormatTownsResponse() {
        List<DataListDTO> states = List.of(
                new DataListDTO("STATE", "05", "Antioquia")
        );

        List<DataListDTO> towns = List.of(
                new DataListDTO("TOWN", "05001", "Medellin")
        );

        TownsResponse response = ParamsUtils.formatTownsResponse(states, towns);

        assertNotNull(response);
        assertNotNull(response.getCountry());
        assertEquals("CO", response.getCountry().getCode());
        assertNotNull(response.getTowns());
        assertFalse(response.getTowns().isEmpty());
    }

    @Test
    void shouldReturnNullWhenTownsResponseIsNull() {
        TownsResponse response = ParamsUtils.formatTownsResponse(
                List.of(new DataListDTO("STATE", "05", "Antioquia")),
                null
        );

        assertNull(response);
    }

    @Test
    void shouldReturnNullWhenTownsResponseIsEmpty() {
        TownsResponse response = ParamsUtils.formatTownsResponse(
                List.of(new DataListDTO("STATE", "05", "Antioquia")),
                Collections.emptyList()
        );

        assertNull(response);
    }

    @Test
    void shouldFormatStatesResponse() {
        List<DataListDTO> states = List.of(
                new DataListDTO("STATE", "05", "Antioquia")
        );

        StatesResponse response = ParamsUtils.formaStatesResponse(states);

        assertNotNull(response);
        assertNotNull(response.getCountry());
        assertEquals("CO", response.getCountry().getCode());
        assertNotNull(response.getStates());
        assertFalse(response.getStates().isEmpty());
    }

    @Test
    void shouldReturnNullWhenStatesResponseIsNull() {
        StatesResponse response = ParamsUtils.formaStatesResponse(null);

        assertNull(response);
    }

    @Test
    void shouldReturnNullWhenStatesResponseIsEmpty() {
        StatesResponse response = ParamsUtils.formaStatesResponse(Collections.emptyList());

        assertNull(response);
    }

    @Test
    void shouldReturnTrueWhenStringContainsNumbers() {
        assertTrue(ParamsUtils.stringContainNumbers("ABC123"));
    }

    @Test
    void shouldReturnFalseWhenStringDoesNotContainNumbers() {
        assertFalse(ParamsUtils.stringContainNumbers("ABC"));
    }

    @Test
    void shouldReturnFalseWhenStringIsNull() {
        assertFalse(ParamsUtils.stringContainNumbers(null));
    }

    @Test
    void shouldReturnFalseWhenStringIsBlank() {
        assertFalse(ParamsUtils.stringContainNumbers("   "));
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<ParamsUtils> constructor = ParamsUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertNotNull(constructor.newInstance());
    }
}