package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;

class TrxBP21ResponseTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP21Response dto = new TrxBP21Response();

        TrxBP21DataResponse data = new TrxBP21DataResponse();
        TrxHeader header = new TrxHeader();
        Object auth = new Object();
        Object pag = new Object();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());
        Object conexion = new Object();

        dto.setData(data);
        dto.setCabecera(header);
        dto.setAutorizacion(auth);
        dto.setPaginacion(pag);
        dto.setAvisos(avisos);
        dto.setErrores(errores);
        dto.setConexion(conexion);
        dto.setOk(Boolean.TRUE);

        assertEquals(data, dto.getData());
        assertEquals(header, dto.getCabecera());
        assertEquals(auth, dto.getAutorizacion());
        assertEquals(pag, dto.getPaginacion());
        assertEquals(avisos, dto.getAvisos());
        assertEquals(errores, dto.getErrores());
        assertEquals(conexion, dto.getConexion());
        assertEquals(Boolean.TRUE, dto.getOk());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP21DataResponse data = new TrxBP21DataResponse();
        TrxHeader header = new TrxHeader();

        TrxBP21Response dto = TrxBP21Response.builder()
                .data(data)
                .cabecera(header)
                .autorizacion("auth")
                .paginacion("pag")
                .avisos(List.of("aviso"))
                .errores(List.of(new ErrorTrxDTO()))
                .conexion("conexion")
                .ok(Boolean.FALSE)
                .build();

        assertNotNull(dto);
        assertEquals(data, dto.getData());
        assertEquals(header, dto.getCabecera());
        assertEquals("auth", dto.getAutorizacion());
        assertEquals("pag", dto.getPaginacion());
        assertEquals(Boolean.FALSE, dto.getOk());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP21DataResponse data = new TrxBP21DataResponse();

        TrxBP21Response dto = new TrxBP21Response(
                data,
                new TrxHeader(),
                "auth",
                "pag",
                List.of(),
                List.of(),
                "conexion",
                true
        );

        assertNotNull(dto);
        assertEquals(data, dto.getData());
        assertEquals(Boolean.TRUE, dto.getOk());
    }
}