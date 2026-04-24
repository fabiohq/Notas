package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BanksParametersDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        BanksParametersDTO dto = new BanksParametersDTO();

        dto.setBankId("0065");
        dto.setBankName("Santander");

        assertEquals("0065", dto.getBankId());
        assertEquals("Santander", dto.getBankName());
    }

    @Test
    void shouldCoverBuilder() {
        BanksParametersDTO dto = BanksParametersDTO.builder()
                .bankId("1234")
                .bankName("Banco Test")
                .build();

        assertNotNull(dto);
        assertEquals("1234", dto.getBankId());
        assertEquals("Banco Test", dto.getBankName());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksParametersDTO dto =
                new BanksParametersDTO("9999", "Banco Demo");

        assertNotNull(dto);
        assertEquals("9999", dto.getBankId());
        assertEquals("Banco Demo", dto.getBankName());
    }
}