package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class BanksParametersRequestTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        BanksParametersRequest dto = new BanksParametersRequest();

        dto.setAuthorization("Bearer token");
        dto.setxSantanderClientId("client-id");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client-id", dto.getxSantanderClientId());
    }

    @Test
    void shouldCoverBuilder() {
        BanksParametersRequest dto = BanksParametersRequest.builder()
                .authorization("auth")
                .xSantanderClientId("client123")
                .build();

        assertNotNull(dto);
        assertEquals("auth", dto.getAuthorization());
        assertEquals("client123", dto.getxSantanderClientId());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        BanksParametersRequest dto =
                new BanksParametersRequest("token", "client-001");

        assertNotNull(dto);
        assertEquals("token", dto.getAuthorization());
        assertEquals("client-001", dto.getxSantanderClientId());
    }
}