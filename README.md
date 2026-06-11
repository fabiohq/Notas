package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP49DataRequestTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorEqualsHashCodeAndToString() {
        TrxBP49DataRequest dto = new TrxBP49DataRequest();

        dto.setBuscarPor("CERTIFICADO");
        dto.setEnt("0065");
        dto.setOfic("0100");
        dto.setCuenta("123456789");
        dto.setSecuencia("001");
        dto.setNumeroCertificado("CERT001");
        dto.setDocumentoCajero("CAJ001");

        assertEquals("CERTIFICADO", dto.getBuscarPor());
        assertEquals("0065", dto.getEnt());
        assertEquals("0100", dto.getOfic());
        assertEquals("123456789", dto.getCuenta());
        assertEquals("001", dto.getSecuencia());
        assertEquals("CERT001", dto.getNumeroCertificado());
        assertEquals("CAJ001", dto.getDocumentoCajero());

        TrxBP49DataRequest allArgs = new TrxBP49DataRequest(
                "CERTIFICADO",
                "0065",
                "0100",
                "123456789",
                "001",
                "CERT001",
                "CAJ001"
        );

        TrxBP49DataRequest builder = TrxBP49DataRequest.builder()
                .buscarPor("CERTIFICADO")
                .ent("0065")
                .ofic("0100")
                .cuenta("123456789")
                .secuencia("001")
                .numeroCertificado("CERT001")
                .documentoCajero("CAJ001")
                .build();

        assertEquals(allArgs, dto);
        assertEquals(dto, builder);
        assertEquals(dto.hashCode(), builder.hashCode());
        assertTrue(dto.toString().contains("buscarPor"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP49RequestTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorEqualsHashCodeAndToString() {
        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        TrxBP49Request dto = new TrxBP49Request();

        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());

        TrxBP49Request allArgs = new TrxBP49Request(header, data);

        TrxBP49Request builder = TrxBP49Request.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertEquals(allArgs, dto);
        assertEquals(dto, builder);
        assertEquals(dto.hashCode(), builder.hashCode());
        assertTrue(dto.toString().contains("cabecera"));
    }

    @Test
    void shouldCoverConstructorFromTrxPersonHeader() {
        TrxPersonHeader source = new TrxPersonHeader();

        source.canal = "WEB";
        source.rutaServicio = "BP49_ROUTE";

        source.sesion.usuario = "USER01";
        source.sesion.terminal = "TERM01";
        source.sesion.horaConexion = "120000";
        source.sesion.entorno = "DEV";
        source.sesion.perfil = "ADMIN";
        source.sesion.sucursal = "0100";
        source.sesion.fechaContable = "2024-01-01";

        source.setFuncion("FUNCTION");
        source.setResultado("OK");
        source.setSecuencia(123);

        TrxBP49Request request = new TrxBP49Request(source);

        assertNotNull(request.getCabecera());
        assertNotNull(request.getCabecera().getSesion());

        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("BP49_ROUTE", request.getCabecera().getRutaServicio());
        assertEquals("FUNCTION", request.getCabecera().getFuncion());
        assertEquals("OK", request.getCabecera().getResultado());
        assertEquals(123, request.getCabecera().getSecuencia());

        Session session = request.getCabecera().getSesion();

        assertEquals("0065", session.getEntidad());
        assertEquals("USER01", session.getUsuario());
        assertEquals("TERM01", session.getTerminal());
        assertEquals("120000", session.getHoraConexion());
        assertEquals("DEV", session.getEntorno());
        assertEquals("ADMIN", session.getPerfil());
        assertEquals("0100", session.getSucursal());
        assertEquals("2024-01-01", session.getFechaContable());
    }
}

source.sesion = new TrxPersonHeader.Session();
