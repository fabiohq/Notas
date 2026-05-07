Aquí van por separado:
SessionTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void shouldSetAndGetAllFields() {
        Session session = new Session();

        session.setUsuario("user");
        session.setTerminal("terminal");
        session.setHoraConexion("2026-05-07T10:30");
        session.setEntorno("N");
        session.setPerfil("perfil");
        session.setSucursal("0100");
        session.setEntidad("0065");
        session.setDiasRestantesCambioClave("29");
        session.setFechaContable("2026-05-07");
        session.setTurno("turno");

        assertEquals("user", session.getUsuario());
        assertEquals("terminal", session.getTerminal());
        assertEquals("2026-05-07T10:30", session.getHoraConexion());
        assertEquals("N", session.getEntorno());
        assertEquals("perfil", session.getPerfil());
        assertEquals("0100", session.getSucursal());
        assertEquals("0065", session.getEntidad());
        assertEquals("29", session.getDiasRestantesCambioClave());
        assertEquals("2026-05-07", session.getFechaContable());
        assertEquals("turno", session.getTurno());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Session session = new Session(
                "user",
                "terminal",
                "2026-05-07T10:30",
                "N",
                "perfil",
                "0100",
                "0065",
                "29",
                "2026-05-07",
                "turno"
        );

        assertEquals("user", session.getUsuario());
        assertEquals("terminal", session.getTerminal());
        assertEquals("2026-05-07T10:30", session.getHoraConexion());
        assertEquals("N", session.getEntorno());
        assertEquals("perfil", session.getPerfil());
        assertEquals("0100", session.getSucursal());
        assertEquals("0065", session.getEntidad());
        assertEquals("29", session.getDiasRestantesCambioClave());
        assertEquals("2026-05-07", session.getFechaContable());
        assertEquals("turno", session.getTurno());
    }

    @Test
    void shouldCreateWithBuilder() {
        Session session = Session.builder()
                .usuario("user")
                .terminal("terminal")
                .horaConexion("2026-05-07T10:30")
                .entorno("N")
                .perfil("perfil")
                .sucursal("0100")
                .entidad("0065")
                .diasRestantesCambioClave("29")
                .fechaContable("2026-05-07")
                .turno("turno")
                .build();

        assertEquals("user", session.getUsuario());
        assertEquals("terminal", session.getTerminal());
        assertEquals("2026-05-07T10:30", session.getHoraConexion());
        assertEquals("N", session.getEntorno());
        assertEquals("perfil", session.getPerfil());
        assertEquals("0100", session.getSucursal());
        assertEquals("0065", session.getEntidad());
        assertEquals("29", session.getDiasRestantesCambioClave());
        assertEquals("2026-05-07", session.getFechaContable());
        assertEquals("turno", session.getTurno());
    }
}
TrxPersonHeaderTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonHeaderTest {

    @Test
    void shouldSetAndGetAllFields() {
        Session session = new Session();
        session.setUsuario("user");

        TrxPersonHeader header = new TrxPersonHeader();

        header.setRutaServicio("ruta");
        header.setSesion(session);
        header.setFuncion("Intro");
        header.setSecuencia(44204);
        header.setCanal("60");
        header.setResultado("OK");

        assertEquals("ruta", header.getRutaServicio());
        assertSame(session, header.getSesion());
        assertEquals("Intro", header.getFuncion());
        assertEquals(44204, header.getSecuencia());
        assertEquals("60", header.getCanal());
        assertEquals("OK", header.getResultado());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Session session = new Session();
        session.setUsuario("user");

        TrxPersonHeader header = new TrxPersonHeader(
                "ruta",
                session,
                "Intro",
                44204,
                "60",
                "OK"
        );

        assertEquals("ruta", header.getRutaServicio());
        assertSame(session, header.getSesion());
        assertEquals("Intro", header.getFuncion());
        assertEquals(44204, header.getSecuencia());
        assertEquals("60", header.getCanal());
        assertEquals("OK", header.getResultado());
    }

    @Test
    void shouldCreateWithBuilder() {
        Session session = Session.builder()
                .usuario("user")
                .build();

        TrxPersonHeader header = TrxPersonHeader.builder()
                .rutaServicio("ruta")
                .sesion(session)
                .funcion("Intro")
                .secuencia(44204)
                .canal("60")
                .resultado("OK")
                .build();

        assertEquals("ruta", header.getRutaServicio());
        assertSame(session, header.getSesion());
        assertEquals("Intro", header.getFuncion());
        assertEquals(44204, header.getSecuencia());
        assertEquals("60", header.getCanal());
        assertEquals("OK", header.getResultado());
    }
}