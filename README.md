package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.response.dto.Bp01DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;

class TrxBp01ResponseTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBp01Response dto = new TrxBp01Response();

        Bp01DataResponseDTO data = new Bp01DataResponseDTO();
        PepfHeaderResponseDTO header = new PepfHeaderResponseDTO();
        Object auth = new Object();

        Map<String, Object> pag = new HashMap<>();
        pag.put("page", 1);

        List<PepfNoticeResponseDTO> avisos =
                List.of(new PepfNoticeResponseDTO());

        List<ErrorTrxDTO> errores =
                List.of(new ErrorTrxDTO());

        Object conexion = new Object();

        dto.setData(data);
        dto.setCabecera(header);
        dto.setAutorizacion(auth);
        dto.setPaginacion(pag);
        dto.setAvisos(avisos);
        dto.setErrores(errores);
        dto.setConexion(conexion);
        dto.setOk(true);

        assertEquals(data, dto.getData());
        assertEquals(header, dto.getCabecera());
        assertEquals(auth, dto.getAutorizacion());
        assertEquals(pag, dto.getPaginacion());
        assertEquals(avisos, dto.getAvisos());
        assertEquals(errores, dto.getErrores());
        assertEquals(conexion, dto.getConexion());
        assertTrue(dto.isOk());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBp01Response dto = TrxBp01Response.builder()
                .data(new Bp01DataResponseDTO())
                .cabecera(new PepfHeaderResponseDTO())
                .autorizacion("AUTH")
                .paginacion(Map.of("page", 1))
                .avisos(List.of(new PepfNoticeResponseDTO()))
                .errores(List.of(new ErrorTrxDTO()))
                .conexion("CONN")
                .ok(false)
                .build();

        assertNotNull(dto);
        assertEquals("AUTH", dto.getAutorizacion());
        assertEquals("CONN", dto.getConexion());
        assertFalse(dto.isOk());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        Bp01DataResponseDTO data = new Bp01DataResponseDTO();
        PepfHeaderResponseDTO header = new PepfHeaderResponseDTO();

        TrxBp01Response dto = new TrxBp01Response(
                data,
                header,
                "AUTH",
                Map.of("page", 2),
                List.of(),
                List.of(),
                "CONN",
                true
        );

        assertNotNull(dto);
        assertEquals(data, dto.getData());
        assertEquals(header, dto.getCabecera());
        assertTrue(dto.isOk());
    }
}