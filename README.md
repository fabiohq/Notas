package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class NewAssociatedContractRequestDTOTest {

    @Test
    void shouldCoverPojo() {
        NewAssociatedContractRequestDTO dto =
                new NewAssociatedContractRequestDTO();

        ContractRequestDTO contract = new ContractRequestDTO();
        ValidityPeriodRequestDTO validity =
                new ValidityPeriodRequestDTO();

        dto.setContract(contract);
        dto.setValidityPeriod(validity);

        assertEquals(contract, dto.getContract());
        assertEquals(validity, dto.getValidityPeriod());
    }

    @Test
    void shouldCoverBuilderAndAllArgs() {
        ContractRequestDTO contract = new ContractRequestDTO();
        ValidityPeriodRequestDTO validity =
                new ValidityPeriodRequestDTO();

        NewAssociatedContractRequestDTO dto =
                NewAssociatedContractRequestDTO.builder()
                        .contract(contract)
                        .validityPeriod(validity)
                        .build();

        assertNotNull(dto);

        NewAssociatedContractRequestDTO dto2 =
                new NewAssociatedContractRequestDTO(
                        contract, validity);

        assertEquals(contract, dto2.getContract());
        assertEquals(validity, dto2.getValidityPeriod());
    }
}