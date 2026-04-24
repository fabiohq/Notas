package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PepfDataResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        PepfDataResponseDTO dto = new PepfDataResponseDTO();
        PepfPEMFV0AResponseDTO response = new PepfPEMFV0AResponseDTO();

        dto.setPemfvoaResponse(response);

        assertEquals(response, dto.getPemfvoaResponse());
    }

    @Test
    void shouldCoverBuilder() {
        PepfPEMFV0AResponseDTO response = new PepfPEMFV0AResponseDTO();

        PepfDataResponseDTO dto = PepfDataResponseDTO.builder()
                .pemfvoaResponse(response)
                .build();

        assertNotNull(dto);
        assertEquals(response, dto.getPemfvoaResponse());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PepfPEMFV0AResponseDTO response = new PepfPEMFV0AResponseDTO();

        PepfDataResponseDTO dto = new PepfDataResponseDTO(response);

        assertNotNull(dto);
        assertEquals(response, dto.getPemfvoaResponse());
    }
}