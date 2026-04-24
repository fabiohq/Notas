package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

class TrxBP13ResponseTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        TrxBP13Response response = new TrxBP13Response();

        TrxBP13DataResponse data = new TrxBP13DataResponse();
        TrxHeader cabecera = new TrxHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());
        Object conexion = new Object();

        response.setData(data);
        response.setCabecera(cabecera);
        response.setAutorizacion(autorizacion);
        response.setPaginacion(paginacion);
        response.setAvisos(avisos);
        response.setErrores(errores);
        response.setConexion(conexion);
        response.setOk(Boolean.TRUE);

        assertEquals(data, response.getData());
        assertEquals(cabecera, response.getCabecera());
        assertEquals(autorizacion, response.getAutorizacion());
        assertEquals(paginacion, response.getPaginacion());
        assertEquals(avisos, response.getAvisos());
        assertEquals(errores, response.getErrores());
        assertEquals(conexion, response.getConexion());
        assertEquals(Boolean.TRUE, response.getOk());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP13DataResponse data = new TrxBP13DataResponse();
        TrxHeader cabecera = new TrxHeader();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        TrxBP13Response response = TrxBP13Response.builder()
                .data(data)
                .cabecera(cabecera)
                .autorizacion("auth")
                .paginacion("page")
                .avisos(avisos)
                .errores(errores)
                .conexion("conexion")
                .ok(Boolean.FALSE)
                .build();

        assertNotNull(response);
        assertEquals(data, response.getData());
        assertEquals(cabecera, response.getCabecera());
        assertEquals("auth", response.getAutorizacion());
        assertEquals("page", response.getPaginacion());
        assertEquals(avisos, response.getAvisos());
        assertEquals(errores, response.getErrores());
        assertEquals("conexion", response.getConexion());
        assertEquals(Boolean.FALSE, response.getOk());
    }
}