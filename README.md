package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PepfNoticeResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        PepfNoticeResponseDTO dto = new PepfNoticeResponseDTO();

        dto.setCodigo("codigo");
        dto.setMensaje("mensaje");
        dto.setTransaccion("transaccion");

        assertEquals("codigo", dto.getCodigo());
        assertEquals("mensaje", dto.getMensaje());
        assertEquals("transaccion", dto.getTransaccion());
    }

    @Test
    void shouldCoverBuilder() {
        PepfNoticeResponseDTO dto = PepfNoticeResponseDTO.builder()
                .codigo("001")
                .mensaje("ok")
                .transaccion("trx")
                .build();

        assertNotNull(dto);
        assertEquals("001", dto.getCodigo());
        assertEquals("ok", dto.getMensaje());
        assertEquals("trx", dto.getTransaccion());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PepfNoticeResponseDTO dto = new PepfNoticeResponseDTO(
                "999",
                "mensaje prueba",
                "transaccion prueba"
        );

        assertNotNull(dto);
        assertEquals("999", dto.getCodigo());
        assertEquals("mensaje prueba", dto.getMensaje());
        assertEquals("transaccion prueba", dto.getTransaccion());
    }
}