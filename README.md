package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP49DataTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorEqualsHashCodeAndToString() {
        TrxBP49Data dto = new TrxBP49Data();

        dto.setCdtDat("CDT");
        dto.setCERTIFI("CERT001");
        dto.setFecha("2024-01-01");
        dto.setSecuencia("001");
        dto.setRETEN("RETEN");
        dto.setSecRen("SEC-REN");
        dto.setINTABON("INT");
        dto.setEstado("ACTIVE");
        dto.setNumMov("1");
        dto.setInteresPendienteLiquidar("100");
        dto.setPago("PAGO");
        dto.setConcepto("INTP");
        dto.setValor("000000000001000");

        assertEquals("CDT", dto.getCdtDat());
        assertEquals("CERT001", dto.getCERTIFI());
        assertEquals("2024-01-01", dto.getFecha());
        assertEquals("001", dto.getSecuencia());
        assertEquals("RETEN", dto.getRETEN());
        assertEquals("SEC-REN", dto.getSecRen());
        assertEquals("INT", dto.getINTABON());
        assertEquals("ACTIVE", dto.getEstado());
        assertEquals("1", dto.getNumMov());
        assertEquals("100", dto.getInteresPendienteLiquidar());
        assertEquals("PAGO", dto.getPago());
        assertEquals("INTP", dto.getConcepto());
        assertEquals("000000000001000", dto.getValor());

        TrxBP49Data allArgs = new TrxBP49Data(
                "CDT",
                "CERT001",
                "2024-01-01",
                "001",
                "RETEN",
                "SEC-REN",
                "INT",
                "ACTIVE",
                "1",
                "100",
                "PAGO",
                "INTP",
                "000000000001000"
        );

        TrxBP49Data builder = TrxBP49Data.builder()
                .cdtDat("CDT")
                .CERTIFI("CERT001")
                .fecha("2024-01-01")
                .secuencia("001")
                .RETEN("RETEN")
                .secRen("SEC-REN")
                .INTABON("INT")
                .estado("ACTIVE")
                .numMov("1")
                .interesPendienteLiquidar("100")
                .pago("PAGO")
                .concepto("INTP")
                .valor("000000000001000")
                .build();

        assertEquals(allArgs, dto);
        assertEquals(dto, builder);
        assertEquals(dto.hashCode(), builder.hashCode());
        assertTrue(dto.toString().contains("cdtDat"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP49DataResponseTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorEqualsHashCodeAndToString() {
        TrxBP49Data movement = TrxBP49Data.builder()
                .cdtDat("CDT")
                .CERTIFI("CERT001")
                .build();

        TrxBP49DataResponse dto = new TrxBP49DataResponse();
        dto.setMovimientos(List.of(movement));

        assertEquals(List.of(movement), dto.getMovimientos());

        TrxBP49DataResponse allArgs =
                new TrxBP49DataResponse(List.of(movement));

        TrxBP49DataResponse builder =
                TrxBP49DataResponse.builder()
                        .movimientos(List.of(movement))
                        .build();

        assertEquals(allArgs, dto);
        assertEquals(dto, builder);
        assertEquals(dto.hashCode(), builder.hashCode());
        assertTrue(dto.toString().contains("movimientos"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP49ResponseTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorEqualsHashCodeAndToString() {
        TrxBP49DataResponse data = TrxBP49DataResponse.builder()
                .movimientos(List.of(new TrxBP49Data()))
                .build();

        TrxHeader cabecera = new TrxHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        List<Object> avisos = List.of("warning");
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());
        Object conexion = new Object();

        TrxBP49Response dto = new TrxBP49Response();

        dto.setData(data);
        dto.setCabecera(cabecera);
        dto.setAutorizacion(autorizacion);
        dto.setPaginacion(paginacion);
        dto.setAvisos(avisos);
        dto.setErrores(errores);
        dto.setConexion(conexion);
        dto.setOk(Boolean.TRUE);

        assertEquals(data, dto.getData());
        assertEquals(cabecera, dto.getCabecera());
        assertEquals(autorizacion, dto.getAutorizacion());
        assertEquals(paginacion, dto.getPaginacion());
        assertEquals(avisos, dto.getAvisos());
        assertEquals(errores, dto.getErrores());
        assertEquals(conexion, dto.getConexion());
        assertTrue(dto.getOk());

        TrxBP49Response allArgs = new TrxBP49Response(
                data,
                cabecera,
                autorizacion,
                paginacion,
                avisos,
                errores,
                conexion,
                Boolean.TRUE
        );

        TrxBP49Response builder = TrxBP49Response.builder()
                .data(data)
                .cabecera(cabecera)
                .autorizacion(autorizacion)
                .paginacion(paginacion)
                .avisos(avisos)
                .errores(errores)
                .conexion(conexion)
                .ok(Boolean.TRUE)
                .build();

        assertEquals(allArgs, dto);
        assertEquals(dto, builder);
        assertEquals(dto.hashCode(), builder.hashCode());
        assertTrue(dto.toString().contains("data"));
    }
}
