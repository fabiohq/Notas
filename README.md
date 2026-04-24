package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;

class TrxBP13RequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP13Request dto = new TrxBP13Request();

        TrxHeader header = new TrxHeader();
        TrxBP13DataRequest data = new TrxBP13DataRequest();

        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverBuilder() {
        TrxHeader header = new TrxHeader();
        TrxBP13DataRequest data = new TrxBP13DataRequest();

        TrxBP13Request dto = TrxBP13Request.builder()
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
        TrxBP13DataRequest data = new TrxBP13DataRequest();

        TrxBP13Request dto = new TrxBP13Request(header, data);

        assertNotNull(dto);
        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverConstructorWithTrxPersonHeader() {
        TrxPersonHeader personHeader = new TrxPersonHeader();

        personHeader.canal = "WEB";
        personHeader.rutaServicio = "ROUTE13";
        personHeader.setFuncion("FUNC13");
        personHeader.setResultado("OK");
        personHeader.setSecuencia("321");

        personHeader.sesion = new Session();
        personHeader.sesion.setUsuario("USER13");
        personHeader.sesion.setTerminal("TERM13");
        personHeader.sesion.setHoraConexion("101500");
        personHeader.sesion.setEntorno("DEV");
        personHeader.sesion.setPerfil("ADMIN");
        personHeader.sesion.setSucursal("001");
        personHeader.sesion.setDiasRestantesCambioClave(5);
        personHeader.sesion.setFechaContable("20240101");

        TrxBP13Request dto = new TrxBP13Request(personHeader);

        assertNotNull(dto);
        assertNotNull(dto.getCabecera());
        assertNotNull(dto.getCabecera().getSesion());

        assertEquals("WEB", dto.getCabecera().getCanal());
        assertEquals("FUNC13", dto.getCabecera().getFuncion());
        assertEquals("OK", dto.getCabecera().getResultado());
        assertEquals("ROUTE13", dto.getCabecera().getRutaServicio());
        assertEquals("321", dto.getCabecera().getSecuencia());

        assertEquals("USER13", dto.getCabecera().getSesion().getUsuario());
        assertEquals("TERM13", dto.getCabecera().getSesion().getTerminal());
        assertEquals("101500", dto.getCabecera().getSesion().getHoraConexion());
        assertEquals("DEV", dto.getCabecera().getSesion().getEntorno());
        assertEquals("ADMIN", dto.getCabecera().getSesion().getPerfil());
        assertEquals("001", dto.getCabecera().getSesion().getSucursal());
        assertEquals("0065", dto.getCabecera().getSesion().getEntidad());
        assertEquals(5, dto.getCabecera().getSesion().getDiasRestantesCambioClave());
        assertEquals("20240101", dto.getCabecera().getSesion().getFechaContable());
    }
}