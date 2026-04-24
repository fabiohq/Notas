package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class Bp01HeaderRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        Bp01HeaderRequest dto = new Bp01HeaderRequest();
        Bp01SesionRequest sesion = new Bp01SesionRequest();

        dto.setRutaServicio("ruta");
        dto.setSesion(sesion);
        dto.setFuncion("funcion");
        dto.setSecuencia("1");
        dto.setCanal("60");

        assertEquals("ruta", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("funcion", dto.getFuncion());
        assertEquals("1", dto.getSecuencia());
        assertEquals("60", dto.getCanal());
    }

    @Test
    void shouldCoverBuilder() {
        Bp01SesionRequest sesion = new Bp01SesionRequest();

        Bp01HeaderRequest dto = Bp01HeaderRequest.builder()
                .rutaServicio("ruta")
                .sesion(sesion)
                .canal("60")
                .build();

        assertNotNull(dto);
        assertEquals("ruta", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("60", dto.getCanal());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        Bp01SesionRequest sesion = new Bp01SesionRequest();

        Bp01HeaderRequest dto = new Bp01HeaderRequest(
                "ruta", sesion, "funcion", "1", "60"
        );

        assertNotNull(dto);
        assertEquals("ruta", dto.getRutaServicio());
        assertEquals(sesion, dto.getSesion());
        assertEquals("funcion", dto.getFuncion());
        assertEquals("1", dto.getSecuencia());
        assertEquals("60", dto.getCanal());
    }
}