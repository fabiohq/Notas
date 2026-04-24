package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class Bp01SesionRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        Bp01SesionRequest dto = new Bp01SesionRequest();

        dto.setUsuario("usuario");
        dto.setHoraConexion("hora");
        dto.setEntorno("entorno");
        dto.setPerfil("perfil");
        dto.setSucursal("sucursal");
        dto.setEntidad("entidad");
        dto.setDiasRestantesCambioClave("5");
        dto.setFechaContable("fecha");

        assertEquals("usuario", dto.getUsuario());
        assertEquals("hora", dto.getHoraConexion());
        assertEquals("entorno", dto.getEntorno());
        assertEquals("perfil", dto.getPerfil());
        assertEquals("sucursal", dto.getSucursal());
        assertEquals("entidad", dto.getEntidad());
        assertEquals("5", dto.getDiasRestantesCambioClave());
        assertEquals("fecha", dto.getFechaContable());
    }

    @Test
    void shouldCoverBuilder() {
        Bp01SesionRequest dto = Bp01SesionRequest.builder()
                .usuario("usuario")
                .entidad("0065")
                .fechaContable("fecha")
                .build();

        assertNotNull(dto);
        assertEquals("usuario", dto.getUsuario());
        assertEquals("0065", dto.getEntidad());
        assertEquals("fecha", dto.getFechaContable());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        Bp01SesionRequest dto = new Bp01SesionRequest(
                "usuario", "hora", "entorno", "perfil",
                "sucursal", "entidad", "5", "fecha"
        );

        assertNotNull(dto);
        assertEquals("usuario", dto.getUsuario());
        assertEquals("fecha", dto.getFechaContable());
    }
}