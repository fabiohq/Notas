package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP13DataResponseTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        TrxBP13DataResponse dto = new TrxBP13DataResponse();

        dto.setNumCertificado("numCertificado");
        dto.setCertificadoReemplazado("certificadoReemplazado");
        dto.setCodigoInversor("codigoInversor");
        dto.setSecuenciaIPF("secuenciaIPF");
        dto.setSecuenciaRenovacion("secuenciaRenovacion");
        dto.setProducto("producto");
        dto.setSubproducto("subproducto");
        dto.setFecAlta("fecAlta");
        dto.setFecOperacion("fecOperacion");
        dto.setIndicadorGrantia("indicadorGrantia");
        dto.setTarifaVigente("tarifaVigente");
        dto.setEstadoIPF("estadoIPF");
        dto.setSpread("spread");
        dto.setIndicadorONP("indicadorONP");
        dto.setMoneda("moneda");
        dto.setDesMoneda("desMoneda");
        dto.setSaldoInicial("saldoInicial");
        dto.setPlazo(90);
        dto.setPeriodoLiquidacion("periodoLiquidacion");
        dto.setTipoTitular("tipoTitular");
        dto.setNumTitular("numTitular");
        dto.setPriApellido("priApellido");
        dto.setSegApellido("segApellido");
        dto.setNombreTitular("nombreTitular");
        dto.setTipoEfectivo("tipoEfectivo");
        dto.setCambioUR(1);
        dto.setTipoInteres("tipoInteres");
        dto.setCapInteres("capInteres");
        dto.setCapReajuste("capReajuste");
        dto.setRenovacionAutomatica("renovacionAutomatica");
        dto.setEjecutivoComercial("ejecutivoComercial");
        dto.setPlanComercial("planComercial");
        dto.setCustodia("custodia");
        dto.setDesCustodia("desCustodia");
        dto.setCanalApertura("canalApertura");
        dto.setTransferible("transferible");
        dto.setOrigen("origen");
        dto.setObservaciones("observaciones");
        dto.setSucIngCustodia("sucIngCustodia");
        dto.setFecIngCustodia("fecIngCustodia");
        dto.setSucEgrCustodia("sucEgrCustodia");
        dto.setFecEgrCustodia("fecEgrCustodia");
        dto.setImporteRestProgra(2);
        dto.setCentroGestor("centroGestor");
        dto.setAcuerdo("acuerdo");
        dto.setCuentaAsociada("cuentaAsociada");
        dto.setFecAnulacion("fecAnulacion");
        dto.setFecVencimiento("fecVencimiento");
        dto.setFecCancelacion("fecCancelacion");
        dto.setFecLiquidacion("fecLiquidacion");
        dto.setFecLiqReajuste("fecLiqReajuste");
        dto.setTarifaRenovacion("tarifaRenovacion");
        dto.setSpreadRenovacion(3);
        dto.setInteresesAvonado("interesesAvonado");
        dto.setInteresesPendiente("interesesPendiente");
        dto.setImportePeriodico("importePeriodico");
        dto.setIndicadorBloqueo("indicadorBloqueo");
        dto.setInteresesReajuste(4);
        dto.setPago("pago");
        dto.setDesPago("desPago");
        dto.setSaldoDisponible("saldoDisponible");
        dto.setCuentaCliente("cuentaCliente");
        dto.setImporteTipoTasa("importeTipoTasa");
        dto.setNumDocumento("numDocumento");
        dto.setTipoDocumento("tipoDocumento");
        dto.setTipoOperacion("tipoOperacion");
        dto.setMotivoCancelacion("motivoCancelacion");
        dto.setLina1("lina1");
        dto.setLina2("lina2");

        assertEquals("numCertificado", dto.getNumCertificado());
        assertEquals("certificadoReemplazado", dto.getCertificadoReemplazado());
        assertEquals("codigoInversor", dto.getCodigoInversor());
        assertEquals("secuenciaIPF", dto.getSecuenciaIPF());
        assertEquals("secuenciaRenovacion", dto.getSecuenciaRenovacion());
        assertEquals("producto", dto.getProducto());
        assertEquals("subproducto", dto.getSubproducto());
        assertEquals("fecAlta", dto.getFecAlta());
        assertEquals("fecOperacion", dto.getFecOperacion());
        assertEquals("indicadorGrantia", dto.getIndicadorGrantia());
        assertEquals("tarifaVigente", dto.getTarifaVigente());
        assertEquals("estadoIPF", dto.getEstadoIPF());
        assertEquals("spread", dto.getSpread());
        assertEquals("indicadorONP", dto.getIndicadorONP());
        assertEquals("moneda", dto.getMoneda());
        assertEquals("desMoneda", dto.getDesMoneda());
        assertEquals("saldoInicial", dto.getSaldoInicial());
        assertEquals(90, dto.getPlazo());
        assertEquals("periodoLiquidacion", dto.getPeriodoLiquidacion());
        assertEquals("tipoTitular", dto.getTipoTitular());
        assertEquals("numTitular", dto.getNumTitular());
        assertEquals("priApellido", dto.getPriApellido());
        assertEquals("segApellido", dto.getSegApellido());
        assertEquals("nombreTitular", dto.getNombreTitular());
        assertEquals("tipoEfectivo", dto.getTipoEfectivo());
        assertEquals(1, dto.getCambioUR());
        assertEquals("tipoInteres", dto.getTipoInteres());
        assertEquals("capInteres", dto.getCapInteres());
        assertEquals("capReajuste", dto.getCapReajuste());
        assertEquals("renovacionAutomatica", dto.getRenovacionAutomatica());
        assertEquals("ejecutivoComercial", dto.getEjecutivoComercial());
        assertEquals("planComercial", dto.getPlanComercial());
        assertEquals("custodia", dto.getCustodia());
        assertEquals("desCustodia", dto.getDesCustodia());
        assertEquals("canalApertura", dto.getCanalApertura());
        assertEquals("transferible", dto.getTransferible());
        assertEquals("origen", dto.getOrigen());
        assertEquals("observaciones", dto.getObservaciones());
        assertEquals("sucIngCustodia", dto.getSucIngCustodia());
        assertEquals("fecIngCustodia", dto.getFecIngCustodia());
        assertEquals("sucEgrCustodia", dto.getSucEgrCustodia());
        assertEquals("fecEgrCustodia", dto.getFecEgrCustodia());
        assertEquals(2, dto.getImporteRestProgra());
        assertEquals("centroGestor", dto.getCentroGestor());
        assertEquals("acuerdo", dto.getAcuerdo());
        assertEquals("cuentaAsociada", dto.getCuentaAsociada());
        assertEquals("fecAnulacion", dto.getFecAnulacion());
        assertEquals("fecVencimiento", dto.getFecVencimiento());
        assertEquals("fecCancelacion", dto.getFecCancelacion());
        assertEquals("fecLiquidacion", dto.getFecLiquidacion());
        assertEquals("fecLiqReajuste", dto.getFecLiqReajuste());
        assertEquals("tarifaRenovacion", dto.getTarifaRenovacion());
        assertEquals(3, dto.getSpreadRenovacion());
        assertEquals("interesesAvonado", dto.getInteresesAvonado());
        assertEquals("interesesPendiente", dto.getInteresesPendiente());
        assertEquals("importePeriodico", dto.getImportePeriodico());
        assertEquals("indicadorBloqueo", dto.getIndicadorBloqueo());
        assertEquals(4, dto.getInteresesReajuste());
        assertEquals("pago", dto.getPago());
        assertEquals("desPago", dto.getDesPago());
        assertEquals("saldoDisponible", dto.getSaldoDisponible());
        assertEquals("cuentaCliente", dto.getCuentaCliente());
        assertEquals("importeTipoTasa", dto.getImporteTipoTasa());
        assertEquals("numDocumento", dto.getNumDocumento());
        assertEquals("tipoDocumento", dto.getTipoDocumento());
        assertEquals("tipoOperacion", dto.getTipoOperacion());
        assertEquals("motivoCancelacion", dto.getMotivoCancelacion());
        assertEquals("lina1", dto.getLina1());
        assertEquals("lina2", dto.getLina2());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP13DataResponse dto = TrxBP13DataResponse.builder()
                .numCertificado("numCertificado")
                .plazo(90)
                .cambioUR(1)
                .importeRestProgra(2)
                .spreadRenovacion(3)
                .interesesReajuste(4)
                .lina1("lina1")
                .lina2("lina2")
                .build();

        assertNotNull(dto);
        assertEquals("numCertificado", dto.getNumCertificado());
        assertEquals(90, dto.getPlazo());
        assertEquals(1, dto.getCambioUR());
        assertEquals(2, dto.getImporteRestProgra());
        assertEquals(3, dto.getSpreadRenovacion());
        assertEquals(4, dto.getInteresesReajuste());
        assertEquals("lina1", dto.getLina1());
        assertEquals("lina2", dto.getLina2());
    }
}