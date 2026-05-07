package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionIdentificationRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        RegionIdentificationRequestDTO dto = new RegionIdentificationRequestDTO();

        dto.setCode("REG01");

        assertEquals("REG01", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        RegionIdentificationRequestDTO dto =
                new RegionIdentificationRequestDTO("REG02");

        assertEquals("REG02", dto.getCode());
    }

    @Test
    void shouldBuildWithBuilder() {
        RegionIdentificationRequestDTO dto =
                RegionIdentificationRequestDTO.builder()
                        .code("REG03")
                        .build();

        assertEquals("REG03", dto.getCode());
    }
}