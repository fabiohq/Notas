package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP31DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP31DataRequest dto = new TrxBP31DataRequest();

        dto.setTipoDocumento("tipoDocumento");
        dto.setNumDocumento("numDocumento");
        dto.setOficina("oficina");
        dto.setCodigoInversor("codigoInversor");
        dto.setNroCliente("nroCliente");
        dto.setEjecutivoComercial("ejecutivoComercial");
        dto.setIndicadorEstado("indicadorEstado");
        dto.setTipoCustodia("tipoCustodia");
        dto.setTipoFecha("tipoFecha");
        dto.setFechaDesde("fechaDesde");
        dto.setFechaHasta("fechaHasta");
        dto.setCccReposicionam("cccReposicionam");
        dto.setSecuenciaReposicion("secuenciaReposicion");
        dto.setSecRenovReposic("secRenovReposic");

        assertEquals("tipoDocumento", dto.getTipoDocumento());
        assertEquals("numDocumento", dto.getNumDocumento());
        assertEquals("oficina", dto.getOficina());
        assertEquals("codigoInversor", dto.getCodigoInversor());
        assertEquals("nroCliente", dto.getNroCliente());
        assertEquals("ejecutivoComercial", dto.getEjecutivoComercial());
        assertEquals("indicadorEstado", dto.getIndicadorEstado());
        assertEquals("tipoCustodia", dto.getTipoCustodia());
        assertEquals("tipoFecha", dto.getTipoFecha());
        assertEquals("fechaDesde", dto.getFechaDesde());
        assertEquals("fechaHasta", dto.getFechaHasta());
        assertEquals("cccReposicionam", dto.getCccReposicionam());
        assertEquals("secuenciaReposicion", dto.getSecuenciaReposicion());
        assertEquals("secRenovReposic", dto.getSecRenovReposic());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP31DataRequest dto = TrxBP31DataRequest.builder()
                .tipoDocumento("CC")
                .numDocumento("123")
                .nroCliente("456")
                .build();

        assertNotNull(dto);
        assertEquals("CC", dto.getTipoDocumento());
        assertEquals("123", dto.getNumDocumento());
        assertEquals("456", dto.getNroCliente());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP31DataRequest dto = new TrxBP31DataRequest(
                "tipoDocumento",
                "numDocumento",
                "oficina",
                "codigoInversor",
                "nroCliente",
                "ejecutivoComercial",
                "indicadorEstado",
                "tipoCustodia",
                "tipoFecha",
                "fechaDesde",
                "fechaHasta",
                "cccReposicionam",
                "secuenciaReposicion",
                "secRenovReposic"
        );

        assertNotNull(dto);
        assertEquals("tipoDocumento", dto.getTipoDocumento());
        assertEquals("secRenovReposic", dto.getSecRenovReposic());
    }
}