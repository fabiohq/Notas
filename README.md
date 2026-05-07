package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Document2RequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        Document2RequestDTO dto = new Document2RequestDTO();

        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode("CO");

        StateRequestDTO state = new StateRequestDTO();
        state.setCode("11");

        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("123456789");
        dto.setIssueDate("2024-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("REGISTRADURIA");
        dto.setCountry(country);
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("123456789", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("REGISTRADURIA", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        Document2RequestDTO dto = new Document2RequestDTO(
                "CE",
                "987654321",
                "2023-05-10",
                "2029-05-10",
                "MIGRACION",
                country,
                state
        );

        assertEquals("CE", dto.getDocumentTypeCode());
        assertEquals("987654321", dto.getDocumentNumber());
        assertEquals("2023-05-10", dto.getIssueDate());
        assertEquals("2029-05-10", dto.getExpirationDate());
        assertEquals("MIGRACION", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
    }
}

