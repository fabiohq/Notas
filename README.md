package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxSessionHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;

class TrxBp01RequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBp01Request request = new TrxBp01Request();
        TrxHeader header = new TrxHeader();
        Bp01DataRequest data = new Bp01DataRequest();

        request.setCabecera(header);
        request.setData(data);

        assertEquals(header, request.getCabecera());
        assertEquals(data, request.getData());
    }

    @Test
    void shouldCoverBuilder() {
        TrxHeader header = new TrxHeader();
        Bp01DataRequest data = new Bp01DataRequest();

        TrxBp01Request request = TrxBp01Request.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertNotNull(request);
        assertEquals(header, request.getCabecera());
        assertEquals(data, request.getData());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxHeader header = new TrxHeader();
        Bp01DataRequest data = new Bp01DataRequest();

        TrxBp01Request request = new TrxBp01Request(header, data);

        assertNotNull(request);
        assertEquals(header, request.getCabecera());
        assertEquals(data, request.getData());
    }

    @Test
    void shouldCoverConstructorWithTrxPersonHeader() {
        TrxPersonHeader personHeader = new TrxPersonHeader();
        personHeader.canal = "60";
        personHeader.rutaServicio = "ruta";
        personHeader.setFuncion("funcion");
        personHeader.setResultado("OK");
        personHeader.setSecuencia(10);

        TrxSessionHeader session = new TrxSessionHeader();
        session.usuario = "user";
        session.terminal = "terminal";
        session.horaConexion = "10:00";
        session.entorno = "DEV";
        session.perfil = "perfil";
        session.sucursal = "0001";
        session.turno = "A";
        session.fechaContable = "20240101";

        personHeader.sesion = session;

        TrxBp01Request request = new TrxBp01Request(personHeader);

        assertNotNull(request);
        assertNotNull(request.getCabecera());
        assertEquals("60", request.getCabecera().getCanal());
        assertEquals("funcion", request.getCabecera().getFuncion());
        assertEquals("OK", request.getCabecera().getResultado());
        assertEquals("ruta", request.getCabecera().getRutaServicio());
        assertEquals(10, request.getCabecera().getSecuencia());
        assertEquals("0065", request.getCabecera().getSesion().getEntidad());
        assertEquals("user", request.getCabecera().getSesion().getUsuario());
    }
}