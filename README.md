package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ContractIdentificationRequestDTOTest {

    @Test
    void shouldCoverPojo() {
        ContractIdentificationRequestDTO dto =
                new ContractIdentificationRequestDTO();

        dto.setNationalIdentification("123");
        dto.setInternalIdentification("ABC");

        assertEquals("123", dto.getNationalIdentification());
        assertEquals("ABC", dto.getInternalIdentification());
    }

    @Test
    void shouldCoverBuilderAndAllArgs() {
        ContractIdentificationRequestDTO dto =
                ContractIdentificationRequestDTO.builder()
                        .nationalIdentification("123")
                        .internalIdentification("ABC")
                        .build();

        assertNotNull(dto);
        assertEquals("123", dto.getNationalIdentification());

        ContractIdentificationRequestDTO dto2 =
                new ContractIdentificationRequestDTO("999", "XYZ");

        assertEquals("999", dto2.getNationalIdentification());
        assertEquals("XYZ", dto2.getInternalIdentification());
    }
}