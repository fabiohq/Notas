package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;

class TrxBP17RequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP17Request dto = new TrxBP17Request();

        TrxHeader header = new TrxHeader();
        TrxBP17DataRequest data = new TrxBP17DataRequest();

        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverBuilder() {
        TrxHeader header = new TrxHeader();
        TrxBP17DataRequest data = new TrxBP17DataRequest();

        TrxBP17Request dto = TrxBP17Request.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertNotNull(dto);
        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxHeader header = new TrxHeader();
        TrxBP17DataRequest data = new TrxBP17DataRequest();

        TrxBP17Request dto = new TrxBP17Request(header, data);

        assertNotNull(dto);
        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverConstructorWithTrxPersonHeader() {
        TrxPersonHeader personHeader = new TrxPersonHeader();
        personHeader.canal = "WEB";
        personHeader.rutaServicio = "ROUTE";

        personHeader.setSecuencia("123");
        personHeader.setFuncion("FUNC");
        personHeader.setResultado("OK");

        personHeader.sesion = new Session();
        personHeader.sesion.setEntorno("DEV");
        personHeader.sesion.setFechaContable("20240101");
        personHeader.sesion.setHoraConexion("120000");
        personHeader.sesion.setPerfil("ADMIN");
        personHeader.sesion.setSucursal("001");
        personHeader.sesion.setTerminal("TERM");
        personHeader.sesion.setTurno("A");
        personHeader.sesion.setUsuario("USER");

        TrxBP17Request dto = new TrxBP17Request(personHeader);

        assertNotNull(dto);
        assertNotNull(dto.getCabecera());
        assertEquals("123", dto.getCabecera().getSecuencia());
        assertEquals("ROUTE", dto.getCabecera().getRutaServicio());
        assertEquals("FUNC", dto.getCabecera().getFuncion());
        assertEquals("WEB", dto.getCabecera().getCanal());
        assertEquals("OK", dto.getCabecera().getResultado());

        assertEquals("0065", dto.getCabecera().getSesion().getEntidad());
        assertEquals("USER", dto.getCabecera().getSesion().getUsuario());
        assertEquals("DEV", dto.getCabecera().getSesion().getEntorno());
    }
}