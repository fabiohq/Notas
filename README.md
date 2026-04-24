package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class OldAssociatedContractRequestDTOTest {

    @Test
    void shouldCoverSettersAndGetters() {
        OldAssociatedContractRequestDTO dto =
                new OldAssociatedContractRequestDTO();

        ContractRequestDTO contract = new ContractRequestDTO();
        ValidityPeriodRequestDTO validity =
                new ValidityPeriodRequestDTO();

        dto.setContract(contract);
        dto.setRelationshipTypeCode("REL01");
        dto.setValidityPeriod(validity);

        assertEquals(contract, dto.getContract());
        assertEquals("REL01", dto.getRelationshipTypeCode());
        assertEquals(validity, dto.getValidityPeriod());
    }

    @Test
    void shouldCoverBuilder() {
        ContractRequestDTO contract = new ContractRequestDTO();
        ValidityPeriodRequestDTO validity =
                new ValidityPeriodRequestDTO();

        OldAssociatedContractRequestDTO dto =
                OldAssociatedContractRequestDTO.builder()
                        .contract(contract)
                        .relationshipTypeCode("REL02")
                        .validityPeriod(validity)
                        .build();

        assertNotNull(dto);
        assertEquals(contract, dto.getContract());
        assertEquals("REL02", dto.getRelationshipTypeCode());
        assertEquals(validity, dto.getValidityPeriod());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        ContractRequestDTO contract = new ContractRequestDTO();
        ValidityPeriodRequestDTO validity =
                new ValidityPeriodRequestDTO();

        OldAssociatedContractRequestDTO dto =
                new OldAssociatedContractRequestDTO(
                        contract,
                        "REL03",
                        validity
                );

        assertNotNull(dto);
        assertEquals(contract, dto.getContract());
        assertEquals("REL03", dto.getRelationshipTypeCode());
        assertEquals(validity, dto.getValidityPeriod());
    }
}