package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PepfHeaderResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        PepfHeaderResponseDTO dto = new PepfHeaderResponseDTO();
        PepfSessionResponseDTO sesion = new PepfSessionResponseDTO();

        dto.setSecuencia(1);
        dto.setRutaServicio("rutaServicio");
        dto.setSesion(sesion);
        dto.setResultado("resultado");

        assertEquals(1, dto.getSecuencia());
        assertEquals("rutaServicio", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("resultado", dto.getResultado());
    }

    @Test
    void shouldCoverBuilder() {
        PepfSessionResponseDTO sesion = new PepfSessionResponseDTO();

        PepfHeaderResponseDTO dto = PepfHeaderResponseDTO.builder()
                .secuencia(2)
                .rutaServicio("ruta")
                .sesion(sesion)
                .resultado("ok")
                .build();

        assertNotNull(dto);
        assertEquals(2, dto.getSecuencia());
        assertEquals("ruta", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("ok", dto.getResultado());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PepfSessionResponseDTO sesion = new PepfSessionResponseDTO();

        PepfHeaderResponseDTO dto = new PepfHeaderResponseDTO(
                3,
                "servicio",
                sesion,
                "resultado"
        );

        assertNotNull(dto);
        assertEquals(3, dto.getSecuencia());
        assertEquals("servicio", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("resultado", dto.getResultado());
    }
}