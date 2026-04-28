package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryDTOTest {

    @Test
    void shouldCoverGettersSettersAndConstructors() {
        CountryDTO dto = new CountryDTO();
        dto.setCode("CO");
        dto.setName("Colombia");
        dto.setInternal("COL");
        dto.setIsoAlpha3("COL");

        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getName());
        assertEquals("COL", dto.getInternal());
        assertEquals("COL", dto.getIsoAlpha3());

        CountryDTO dto2 = new CountryDTO("US", "Estados Unidos", "USA", "USA");

        assertEquals("US", dto2.getCode());
        assertEquals("Estados Unidos", dto2.getName());
        assertEquals("USA", dto2.getInternal());
        assertEquals("USA", dto2.getIsoAlpha3());
    }
}