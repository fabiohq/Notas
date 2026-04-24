package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP49DataTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP49Data dto = new TrxBP49Data();

        dto.setCdtDat("cdtDat");
        dto.setCERTIFI("CERTIFI");
        dto.setFecha("fecha");
        dto.setSecuencia("secuencia");
        dto.setRETEN("RETEN");
        dto.setSecRen("secRen");
        dto.setINTABON("INTABON");
        dto.setEstado("estado");
        dto.setNumMov("numMov");
        dto.setInteresPendienteLiquidar("interes");
        dto.setPago("pago");
        dto.setConcepto("concepto");
        dto.setValor("valor");

        assertEquals("cdtDat", dto.getCdtDat());
        assertEquals("CERTIFI", dto.getCERTIFI());
        assertEquals("fecha", dto.getFecha());
        assertEquals("secuencia", dto.getSecuencia());
        assertEquals("RETEN", dto.getRETEN());
        assertEquals("secRen", dto.getSecRen());
        assertEquals("INTABON", dto.getINTABON());
        assertEquals("estado", dto.getEstado());
        assertEquals("numMov", dto.getNumMov());
        assertEquals("interes", dto.getInteresPendienteLiquidar());
        assertEquals("pago", dto.getPago());
        assertEquals("concepto", dto.getConcepto());
        assertEquals("valor", dto.getValor());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP49Data dto = TrxBP49Data.builder()
                .cdtDat("cdt")
                .CERTIFI("cert")
                .valor("100")
                .build();

        assertNotNull(dto);
        assertEquals("cdt", dto.getCdtDat());
        assertEquals("cert", dto.getCERTIFI());
        assertEquals("100", dto.getValor());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP49Data dto = new TrxBP49Data(
                "cdtDat",
                "CERTIFI",
                "fecha",
                "secuencia",
                "RETEN",
                "secRen",
                "INTABON",
                "estado",
                "numMov",
                "interes",
                "pago",
                "concepto",
                "valor"
        );

        assertNotNull(dto);
        assertEquals("cdtDat", dto.getCdtDat());
        assertEquals("valor", dto.getValor());
    }
}