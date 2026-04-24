package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.request.dto.PepfDataDTO;

class TrxPEPFDataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxPEPFDataRequest dto = new TrxPEPFDataRequest();

        TrxHeader header = new TrxHeader();
        PepfDataDTO data = new PepfDataDTO();

        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverBuilder() {
        TrxHeader header = new TrxHeader();
        PepfDataDTO data = new PepfDataDTO();

        TrxPEPFDataRequest dto = TrxPEPFDataRequest.builder()
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
        PepfDataDTO data = new PepfDataDTO();

        TrxPEPFDataRequest dto = new TrxPEPFDataRequest(header, data);

        assertNotNull(dto);
        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
    }

    @Test
    void shouldCoverConstructorWithTrxPersonHeader() {
        TrxPersonHeader personHeader = new TrxPersonHeader();

        personHeader.canal = "WEB";
        personHeader.rutaServicio = "ROUTE01";
        personHeader.setFuncion("FUNC01");
        personHeader.setResultado("OK");
        personHeader.setSecuencia("999");

        personHeader.sesion = new Session();
        personHeader.sesion.setEntorno("DEV");
        personHeader.sesion.setFechaContable("20240101");
        personHeader.sesion.setHoraConexion("101010");
        personHeader.sesion.setPerfil("ADMIN");
        personHeader.sesion.setSucursal("001");
        personHeader.sesion.setTerminal("TERM01");
        personHeader.sesion.setTurno("A");
        personHeader.sesion.setUsuario("USER01");

        TrxPEPFDataRequest dto = new TrxPEPFDataRequest(personHeader);

        assertNotNull(dto);
        assertNotNull(dto.getCabecera());
        assertNotNull(dto.getCabecera().getSesion());

        assertEquals("WEB", dto.getCabecera().getCanal());
        assertEquals("FUNC01", dto.getCabecera().getFuncion());
        assertEquals("OK", dto.getCabecera().getResultado());
        assertEquals("ROUTE01", dto.getCabecera().getRutaServicio());
        assertEquals("999", dto.getCabecera().getSecuencia());

        assertEquals("0065", dto.getCabecera().getSesion().getEntidad());
        assertEquals("DEV", dto.getCabecera().getSesion().getEntorno());
        assertEquals("20240101", dto.getCabecera().getSesion().getFechaContable());
        assertEquals("101010", dto.getCabecera().getSesion().getHoraConexion());
        assertEquals("ADMIN", dto.getCabecera().getSesion().getPerfil());
        assertEquals("001", dto.getCabecera().getSesion().getSucursal());
        assertEquals("TERM01", dto.getCabecera().getSesion().getTerminal());
        assertEquals("A", dto.getCabecera().getSesion().getTurno());
        assertEquals("USER01", dto.getCabecera().getSesion().getUsuario());
    }
}