package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BanksResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        BanksResponseDTO dto = new BanksResponseDTO();
        BanksDTO banks = new BanksDTO();

        dto.setBanks(banks);

        assertEquals(banks, dto.getBanks());
    }

    @Test
    void shouldCoverBuilder() {
        BanksDTO banks = new BanksDTO();

        BanksResponseDTO dto = BanksResponseDTO.builder()
                .banks(banks)
                .build();

        assertNotNull(dto);
        assertEquals(banks, dto.getBanks());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksDTO banks = new BanksDTO();

        BanksResponseDTO dto = new BanksResponseDTO(banks);

        assertNotNull(dto);
        assertEquals(banks, dto.getBanks());
    }
}