package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CodeNameDTO town = new CodeNameDTO("05001", "Medellin");
        CodeNameDTO townDocument = new CodeNameDTO("11001", "Bogota");

        Parameters dto = new Parameters();

        dto.setCountryNationality(new CodeNameDTO("CO", "Colombia"));
        dto.setCountryDir(new CodeNameDTO("CO", "Colombia"));
        dto.setCountryBirth(new CodeNameDTO("CO", "Colombia"));
        dto.setCityBirth(new CodeNameDTO("05", "Antioquia"));
        dto.setTown(town);
        dto.setStreetTypeDescription("Calle");
        dto.setCityStandard(new CodeNameDTO("05001", "Medellin"));
        dto.setCityDepartment(new CodeNameDTO("05", "Antioquia"));
        dto.setDocumentTypeDescription("Cedula");
        dto.setCityExp(new CodeNameDTO("11", "Bogota"));
        dto.setCountryExp(new CodeNameDTO("CO", "Colombia"));
        dto.setTownDocument(townDocument);

        assertEquals("CO", dto.getCountryNationality().getCode());
        assertEquals("CO", dto.getCountryDir().getCode());
        assertEquals("CO", dto.getCountryBirth().getCode());
        assertEquals("05", dto.getCityBirth().getCode());
        assertEquals(town, dto.getTown());
        assertEquals("Calle", dto.getStreetTypeDescription());
        assertEquals("05001", dto.getCityStandard().getCode());
        assertEquals("05", dto.getCityDepartment().getCode());
        assertEquals("Cedula", dto.getDocumentTypeDescription());
        assertEquals("11", dto.getCityExp().getCode());
        assertEquals("CO", dto.getCountryExp().getCode());
        assertEquals(townDocument, dto.getTownDocument());
    }
}