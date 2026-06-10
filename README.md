package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTrxDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        ErrorResponseTrxDTO dto = new ErrorResponseTrxDTO();

        List<ErrorTrxDTO> errors =
                Collections.singletonList(new ErrorTrxDTO());

        dto.setErrores(errors);

        assertEquals(errors, dto.getErrores());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        List<ErrorTrxDTO> errors =
                Collections.singletonList(new ErrorTrxDTO());

        ErrorResponseTrxDTO dto =
                new ErrorResponseTrxDTO(errors);

        assertEquals(errors, dto.getErrores());
    }

    @Test
    void shouldCoverBuilder() {

        List<ErrorTrxDTO> errors =
                Collections.singletonList(new ErrorTrxDTO());

        ErrorResponseTrxDTO dto =
                ErrorResponseTrxDTO.builder()
                        .errores(errors)
                        .build();

        assertEquals(errors, dto.getErrores());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP13ResponseTest {

    @Test
    void shouldCoverGettersAndSetters() {

        TrxBP13Response response = new TrxBP13Response();

        TrxBP13DataResponse data = new TrxBP13DataResponse();

        Object authorization = new Object();
        Object pagination = new Object();
        Object connection = new Object();

        List<Object> warnings =
                Collections.singletonList("warning");

        List<ErrorTrxDTO> errors =
                Collections.singletonList(new ErrorTrxDTO());

        response.setData(data);
        response.setCabecera(null);
        response.setAutorizacion(authorization);
        response.setPaginacion(pagination);
        response.setAvisos(warnings);
        response.setErrores(errors);
        response.setConexion(connection);
        response.setOk(Boolean.TRUE);

        assertEquals(data, response.getData());
        assertNull(response.getCabecera());
        assertEquals(authorization, response.getAutorizacion());
        assertEquals(pagination, response.getPaginacion());
        assertEquals(warnings, response.getAvisos());
        assertEquals(errors, response.getErrores());
        assertEquals(connection, response.getConexion());
        assertTrue(response.getOk());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        TrxBP13DataResponse data = new TrxBP13DataResponse();

        Object authorization = new Object();
        Object pagination = new Object();
        Object connection = new Object();

        List<Object> warnings =
                Collections.singletonList("warning");

        List<ErrorTrxDTO> errors =
                Collections.singletonList(new ErrorTrxDTO());

        TrxBP13Response response =
                new TrxBP13Response(
                        data,
                        null,
                        authorization,
                        pagination,
                        warnings,
                        errors,
                        connection,
                        Boolean.TRUE
                );

        assertEquals(data, response.getData());
        assertEquals(authorization, response.getAutorizacion());
        assertEquals(pagination, response.getPaginacion());
        assertEquals(warnings, response.getAvisos());
        assertEquals(errors, response.getErrores());
        assertEquals(connection, response.getConexion());
        assertTrue(response.getOk());
    }

    @Test
    void shouldCoverBuilder() {

        TrxBP13DataResponse data = new TrxBP13DataResponse();

        TrxBP13Response response =
                TrxBP13Response.builder()
                        .data(data)
                        .cabecera(null)
                        .autorizacion(new Object())
                        .paginacion(new Object())
                        .avisos(Collections.singletonList("warning"))
                        .errores(Collections.singletonList(new ErrorTrxDTO()))
                        .conexion(new Object())
                        .ok(Boolean.TRUE)
                        .build();

        assertNotNull(response);
        assertEquals(data, response.getData());
        assertTrue(response.getOk());
    }
}
