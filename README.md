package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PepfSessionResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        PepfSessionResponseDTO dto = new PepfSessionResponseDTO();

        dto.setUsuario("usuario");
        dto.setTerminal("terminal");
        dto.setHoraConexion("horaConexion");
        dto.setEntorno("entorno");
        dto.setPerfil("perfil");
        dto.setSucursal("sucursal");
        dto.setEntidad("entidad");
        dto.setDiasRestantesCambioClave(5);
        dto.setFechaContable("fechaContable");

        assertEquals("usuario", dto.getUsuario());
        assertEquals("terminal", dto.getTerminal());
        assertEquals("horaConexion", dto.getHoraConexion());
        assertEquals("entorno", dto.getEntorno());
        assertEquals("perfil", dto.getPerfil());
        assertEquals("sucursal", dto.getSucursal());
        assertEquals("entidad", dto.getEntidad());
        assertEquals(5, dto.getDiasRestantesCambioClave());
        assertEquals("fechaContable", dto.getFechaContable());
    }

    @Test
    void shouldCoverBuilder() {
        PepfSessionResponseDTO dto = PepfSessionResponseDTO.builder()
                .usuario("usuario")
                .terminal("terminal")
                .diasRestantesCambioClave(3)
                .fechaContable("fechaContable")
                .build();

        assertNotNull(dto);
        assertEquals("usuario", dto.getUsuario());
        assertEquals("terminal", dto.getTerminal());
        assertEquals(3, dto.getDiasRestantesCambioClave());
        assertEquals("fechaContable", dto.getFechaContable());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PepfSessionResponseDTO dto = new PepfSessionResponseDTO(
                "usuario",
                "terminal",
                "horaConexion",
                "entorno",
                "perfil",
                "sucursal",
                "entidad",
                10,
                "fechaContable"
        );

        assertNotNull(dto);
        assertEquals("usuario", dto.getUsuario());
        assertEquals("terminal", dto.getTerminal());
        assertEquals("horaConexion", dto.getHoraConexion());
        assertEquals("entorno", dto.getEntorno());
        assertEquals("perfil", dto.getPerfil());
        assertEquals("sucursal", dto.getSucursal());
        assertEquals("entidad", dto.getEntidad());
        assertEquals(10, dto.getDiasRestantesCambioClave());
        assertEquals("fechaContable", dto.getFechaContable());
    }
}