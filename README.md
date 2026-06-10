package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BanksDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        BanksDTO dto = new BanksDTO();
        BanksParametersDTO parameter = new BanksParametersDTO();

        List<BanksParametersDTO> banks = Collections.singletonList(parameter);
        dto.setBanks(banks);

        assertEquals(banks, dto.getBanks());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksParametersDTO parameter = BanksParametersDTO.builder()
                .bankId("001")
                .bankName("Santander")
                .build();

        List<BanksParametersDTO> banks = Collections.singletonList(parameter);
        BanksDTO dto = new BanksDTO(banks);

        assertEquals(banks, dto.getBanks());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        List<BanksParametersDTO> banks = Collections.singletonList(
                BanksParametersDTO.builder()
                        .bankId("001")
                        .bankName("Santander")
                        .build()
        );

        BanksDTO dtoOne = BanksDTO.builder()
                .banks(banks)
                .build();

        BanksDTO dtoTwo = BanksDTO.builder()
                .banks(banks)
                .build();

        assertEquals(dtoOne, dtoTwo);
        assertEquals(dtoOne.hashCode(), dtoTwo.hashCode());
        assertTrue(dtoOne.toString().contains("banks"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BanksParametersDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        BanksParametersDTO dto = new BanksParametersDTO();

        dto.setBankId("001");
        dto.setBankName("Santander");

        assertEquals("001", dto.getBankId());
        assertEquals("Santander", dto.getBankName());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksParametersDTO dto = new BanksParametersDTO("001", "Santander");

        assertEquals("001", dto.getBankId());
        assertEquals("Santander", dto.getBankName());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        BanksParametersDTO dtoOne = BanksParametersDTO.builder()
                .bankId("001")
                .bankName("Santander")
                .build();

        BanksParametersDTO dtoTwo = BanksParametersDTO.builder()
                .bankId("001")
                .bankName("Santander")
                .build();

        assertEquals(dtoOne, dtoTwo);
        assertEquals(dtoOne.hashCode(), dtoTwo.hashCode());
        assertTrue(dtoOne.toString().contains("bankId"));
        assertTrue(dtoOne.toString().contains("bankName"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BanksParametersRequestTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        BanksParametersRequest request = new BanksParametersRequest();

        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksParametersRequest request = new BanksParametersRequest(
                "Bearer token",
                "client-id"
        );

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        BanksParametersRequest requestOne = BanksParametersRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        BanksParametersRequest requestTwo = BanksParametersRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        assertEquals(requestOne, requestTwo);
        assertEquals(requestOne.hashCode(), requestTwo.hashCode());
        assertTrue(requestOne.toString().contains("authorization"));
        assertTrue(requestOne.toString().contains("xSantanderClientId"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BanksResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        BanksResponseDTO response = new BanksResponseDTO();

        BanksDTO banks = BanksDTO.builder()
                .banks(Collections.emptyList())
                .build();

        response.setBanks(banks);

        assertEquals(banks, response.getBanks());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksDTO banks = BanksDTO.builder()
                .banks(Collections.emptyList())
                .build();

        BanksResponseDTO response = new BanksResponseDTO(banks);

        assertEquals(banks, response.getBanks());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        BanksDTO banks = BanksDTO.builder()
                .banks(Collections.emptyList())
                .build();

        BanksResponseDTO responseOne = BanksResponseDTO.builder()
                .banks(banks)
                .build();

        BanksResponseDTO responseTwo = BanksResponseDTO.builder()
                .banks(banks)
                .build();

        assertEquals(responseOne, responseTwo);
        assertEquals(responseOne.hashCode(), responseTwo.hashCode());
        assertTrue(responseOne.toString().contains("banks"));
    }
}
