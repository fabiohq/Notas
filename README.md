package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ValidityPeriodRequestDTOTest {

    @Test
    void shouldCoverPojo() {
        ValidityPeriodRequestDTO dto = new ValidityPeriodRequestDTO();

        dto.setStartDate("2024-01-01");
        dto.setEndDate("2024-12-31");

        assertEquals("2024-01-01", dto.getStartDate());
        assertEquals("2024-12-31", dto.getEndDate());
    }

    @Test
    void shouldCoverBuilderAndAllArgs() {
        ValidityPeriodRequestDTO dto =
                ValidityPeriodRequestDTO.builder()
                        .startDate("2024-01-01")
                        .endDate("2024-12-31")
                        .build();

        assertNotNull(dto);

        ValidityPeriodRequestDTO dto2 =
                new ValidityPeriodRequestDTO("2025-01-01", "2025-12-31");

        assertEquals("2025-01-01", dto2.getStartDate());
        assertEquals("2025-12-31", dto2.getEndDate());
    }
}