package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositPlacementRequestDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        DepositPlacementRequestDTO dto = new DepositPlacementRequestDTO();

        dto.setAuthorization("Bearer token");
        dto.setxSantanderClientId("client-id");
        dto.setDepositId("deposit-001");
        dto.setPlacementId("placement-001");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client-id", dto.getxSantanderClientId());
        assertEquals("deposit-001", dto.getDepositId());
        assertEquals("placement-001", dto.getPlacementId());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        DepositPlacementRequestDTO dto = new DepositPlacementRequestDTO(
                "Bearer token",
                "client-id",
                "deposit-001",
                "placement-001"
        );

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client-id", dto.getxSantanderClientId());
        assertEquals("deposit-001", dto.getDepositId());
        assertEquals("placement-001", dto.getPlacementId());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        DepositPlacementRequestDTO dtoOne = DepositPlacementRequestDTO.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .depositId("deposit-001")
                .placementId("placement-001")
                .build();

        DepositPlacementRequestDTO dtoTwo = DepositPlacementRequestDTO.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .depositId("deposit-001")
                .placementId("placement-001")
                .build();

        assertEquals(dtoOne, dtoTwo);
        assertEquals(dtoOne.hashCode(), dtoTwo.hashCode());
        assertNotEquals(dtoOne, null);
        assertNotEquals(dtoOne, new Object());
        assertTrue(dtoOne.toString().contains("authorization"));
        assertTrue(dtoOne.toString().contains("xSantanderClientId"));
        assertTrue(dtoOne.toString().contains("depositId"));
        assertTrue(dtoOne.toString().contains("placementId"));
    }
}
