package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ContractsServiceRequestDTOTest {

    @Test
    void shouldCoverSettersAndGetters() {
        ContractsServiceRequestDTO dto = new ContractsServiceRequestDTO();

        NewAssociatedContractRequestDTO newDto =
                new NewAssociatedContractRequestDTO();
        OldAssociatedContractRequestDTO oldDto =
                new OldAssociatedContractRequestDTO();

        dto.setNewAssociatedContract(newDto);
        dto.setOldAssociatedContract(oldDto);

        assertEquals(newDto, dto.getNewAssociatedContract());
        assertEquals(oldDto, dto.getOldAssociatedContract());
    }

    @Test
    void shouldCoverBuilderAndAllArgs() {
        NewAssociatedContractRequestDTO newDto =
                new NewAssociatedContractRequestDTO();
        OldAssociatedContractRequestDTO oldDto =
                new OldAssociatedContractRequestDTO();

        ContractsServiceRequestDTO dto =
                ContractsServiceRequestDTO.builder()
                        .newAssociatedContract(newDto)
                        .oldAssociatedContract(oldDto)
                        .build();

        assertNotNull(dto);
        assertEquals(newDto, dto.getNewAssociatedContract());
        assertEquals(oldDto, dto.getOldAssociatedContract());

        ContractsServiceRequestDTO dto2 =
                new ContractsServiceRequestDTO(newDto, oldDto);

        assertEquals(newDto, dto2.getNewAssociatedContract());
        assertEquals(oldDto, dto2.getOldAssociatedContract());
    }
}