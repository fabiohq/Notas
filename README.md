package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;

class TrxBP49RequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP49Request dto = new TrxBP49Request();

        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverBuilder() {
        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        TrxBP49Request dto = TrxBP49Request.builder()
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
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        TrxBP49Request dto = new TrxBP49Request(header, data);

        assertNotNull(dto);
        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverConstructorWithTrxPersonHeader() {
        TrxPersonHeader personHeader = new TrxPersonHeader();

        personHeader.canal = "WEB";
        personHeader.rutaServicio = "ROUTE49";
        personHeader.setFuncion("FUNC49");
        personHeader.setResultado("OK");
        personHeader.setSecuencia("888");

        personHeader.sesion = new Session();
        personHeader.sesion.setEntorno("DEV");
        personHeader.sesion.setFechaContable("20240101");
        personHeader.sesion.setHoraConexion("111500");
        personHeader.sesion.setPerfil("ADMIN");
        personHeader.sesion.setSucursal("001");
        personHeader.sesion.setTerminal("TERM49");
        personHeader.sesion.setUsuario("USER49");

        TrxBP49Request dto = new TrxBP49Request(personHeader);

        assertNotNull(dto);
        assertNotNull(dto.getCabecera());
        assertNotNull(dto.getCabecera().getSesion());

        assertEquals("888", dto.getCabecera().getSecuencia());
        assertEquals("ROUTE49", dto.getCabecera().getRutaServicio());
        assertEquals("FUNC49", dto.getCabecera().getFuncion());
        assertEquals("WEB", dto.getCabecera().getCanal());
        assertEquals("OK", dto.getCabecera().getResultado());

        assertEquals("0065", dto.getCabecera().getSesion().getEntidad());
        assertEquals("DEV", dto.getCabecera().getSesion().getEntorno());
        assertEquals("20240101", dto.getCabecera().getSesion().getFechaContable());
        assertEquals("111500", dto.getCabecera().getSesion().getHoraConexion());
        assertEquals("ADMIN", dto.getCabecera().getSesion().getPerfil());
        assertEquals("001", dto.getCabecera().getSesion().getSucursal());
        assertEquals("TERM49", dto.getCabecera().getSesion().getTerminal());
        assertEquals("USER49", dto.getCabecera().getSesion().getUsuario());
    }
}