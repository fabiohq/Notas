package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BanksDomainTest {

    @Test
    void banksParametersDTO_shouldSetAndGetValues() {
        BanksParametersDTO dto = new BanksParametersDTO();

        dto.setBankId("001");
        dto.setBankName("Banco Santander");

        assertEquals("001", dto.getBankId());
        assertEquals("Banco Santander", dto.getBankName());
    }

    @Test
    void banksParametersDTO_shouldBuildWithBuilder() {
        BanksParametersDTO dto = BanksParametersDTO.builder()
                .bankId("002")
                .bankName("Banco Test")
                .build();

        assertEquals("002", dto.getBankId());
        assertEquals("Banco Test", dto.getBankName());
    }

    @Test
    void banksDTO_shouldSetAndGetBanksList() {
        BanksParametersDTO bank = BanksParametersDTO.builder()
                .bankId("001")
                .bankName("Banco Santander")
                .build();

        BanksDTO dto = new BanksDTO();
        dto.setBanks(List.of(bank));

        assertNotNull(dto.getBanks());
        assertEquals(1, dto.getBanks().size());
        assertEquals("001", dto.getBanks().get(0).getBankId());
        assertEquals("Banco Santander", dto.getBanks().get(0).getBankName());
    }

    @Test
    void banksDTO_shouldBuildWithBuilder() {
        BanksParametersDTO bank = BanksParametersDTO.builder()
                .bankId("003")
                .bankName("Banco Builder")
                .build();

        BanksDTO dto = BanksDTO.builder()
                .banks(List.of(bank))
                .build();

        assertNotNull(dto.getBanks());
        assertEquals(1, dto.getBanks().size());
        assertEquals("003", dto.getBanks().get(0).getBankId());
    }

    @Test
    void banksParametersRequest_shouldSetAndGetValues() {
        BanksParametersRequest request = new BanksParametersRequest();

        request.setAuthorization("Bearer token");
        request.setxSantanderClientId("client-id");

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getxSantanderClientId());
    }

    @Test
    void banksParametersRequest_shouldBuildWithBuilder() {
        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("Bearer test-token")
                .xSantanderClientId("test-client")
                .build();

        assertEquals("Bearer test-token", request.getAuthorization());
        assertEquals("test-client", request.getxSantanderClientId());
    }

    @Test
    void banksResponseDTO_shouldSetAndGetBanks() {
        BanksDTO banks = BanksDTO.builder()
                .banks(List.of(
                        BanksParametersDTO.builder()
                                .bankId("001")
                                .bankName("Banco Santander")
                                .build()
                ))
                .build();

        BanksResponseDTO response = new BanksResponseDTO();
        response.setBanks(banks);

        assertNotNull(response.getBanks());
        assertEquals(1, response.getBanks().getBanks().size());
        assertEquals("001", response.getBanks().getBanks().get(0).getBankId());
    }

    @Test
    void banksResponseDTO_shouldBuildWithBuilder() {
        BanksDTO banks = BanksDTO.builder()
                .banks(List.of(
                        BanksParametersDTO.builder()
                                .bankId("004")
                                .bankName("Banco Response")
                                .build()
                ))
                .build();

        BanksResponseDTO response = BanksResponseDTO.builder()
                .banks(banks)
                .build();

        assertNotNull(response.getBanks());
        assertEquals("004", response.getBanks().getBanks().get(0).getBankId());
        assertEquals("Banco Response", response.getBanks().getBanks().get(0).getBankName());
    }
}
