package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ContractRequestDTOTest {

    @Test
    void shouldCoverPojo() {
        ContractRequestDTO dto = new ContractRequestDTO();

        ContractIdentificationRequestDTO id =
                new ContractIdentificationRequestDTO();

        dto.setExternalContract(true);
        dto.setContractIdentification(id);

        assertTrue(dto.getExternalContract());
        assertEquals(id, dto.getContractIdentification());
    }

    @Test
    void shouldCoverBuilderAndAllArgs() {
        ContractIdentificationRequestDTO id =
                new ContractIdentificationRequestDTO();

        ContractRequestDTO dto =
                ContractRequestDTO.builder()
                        .isExternalContract(false)
                        .contractIdentification(id)
                        .build();

        assertNotNull(dto);
        assertFalse(dto.getExternalContract());

        ContractRequestDTO dto2 =
                new ContractRequestDTO(true, id);

        assertTrue(dto2.getExternalContract());
        assertEquals(id, dto2.getContractIdentification());
    }
}