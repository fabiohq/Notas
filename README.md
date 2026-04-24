package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBp02DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBp02DataRequest dto = new TrxBp02DataRequest();

        dto.setNumeroDePersona("1");
        dto.setCuentaInversor("2");
        dto.setProducto("3");
        dto.setSubproducto("4");
        dto.setPlazo("5");
        dto.setDivisa("6");
        dto.setImporte("7");
        dto.setEjecutivoComercial("8");
        dto.setFechaDeAltaDeIpf("9");
        dto.setPeriodoLiquidacion("10");
        dto.setTipoDeTasa("11");
        dto.setTarifa("12");
        dto.setTipoInteresTotal("13");
        dto.setSpread("14");
        dto.setRenovacionAutomatic("15");
        dto.setTarifaRenovacion("16");
        dto.setTipoInteresRenov("17");
        dto.setSpreadRenovacion("18");
        dto.setCapitalizaIntereses("19");
        dto.setCapitalizaReajuste("20");
        dto.setRentaProgramada("21");
        dto.setPlanComisiones("22");
        dto.setObjetivoDeLaInver("23");
        dto.setObservaciones("24");
        dto.setOrigenDeLosFondos("25");
        dto.setCccAsociado("26");
        dto.setDivisaAsociadaCcc("27");
        dto.setCccAsociadoDos("28");
        dto.setDivisaAsociadaCcc2("29");
        dto.setBancoCtaExtAbono("30");
        dto.setCuentaExtAbono("31");
        dto.setDivisCtaExtAbono("32");
        dto.setTipoCtaExtAbono("33");
        dto.setBancoCtaExtFondeo("34");
        dto.setCuentaExtFondeo("35");
        dto.setDivisCtaExtFondeo("36");
        dto.setTipoCtaExtFonde("37");
        dto.setImporteGmfBonifica("38");
        dto.setPorcentajeDeGmfBonificado("39");
        dto.setImporteRetFteCal("40");
        dto.setPorcentajeDeRetFuentaCa("41");

        assertEquals("1", dto.getNumeroDePersona());
        assertEquals("2", dto.getCuentaInversor());
        assertEquals("3", dto.getProducto());
        assertEquals("4", dto.getSubproducto());
        assertEquals("5", dto.getPlazo());
        assertEquals("6", dto.getDivisa());
        assertEquals("7", dto.getImporte());
        assertEquals("8", dto.getEjecutivoComercial());
        assertEquals("9", dto.getFechaDeAltaDeIpf());
        assertEquals("10", dto.getPeriodoLiquidacion());
        assertEquals("11", dto.getTipoDeTasa());
        assertEquals("12", dto.getTarifa());
        assertEquals("13", dto.getTipoInteresTotal());
        assertEquals("14", dto.getSpread());
        assertEquals("15", dto.getRenovacionAutomatic());
        assertEquals("16", dto.getTarifaRenovacion());
        assertEquals("17", dto.getTipoInteresRenov());
        assertEquals("18", dto.getSpreadRenovacion());
        assertEquals("19", dto.getCapitalizaIntereses());
        assertEquals("20", dto.getCapitalizaReajuste());
        assertEquals("21", dto.getRentaProgramada());
        assertEquals("22", dto.getPlanComisiones());
        assertEquals("23", dto.getObjetivoDeLaInver());
        assertEquals("24", dto.getObservaciones());
        assertEquals("25", dto.getOrigenDeLosFondos());
        assertEquals("26", dto.getCccAsociado());
        assertEquals("27", dto.getDivisaAsociadaCcc());
        assertEquals("28", dto.getCccAsociadoDos());
        assertEquals("29", dto.getDivisaAsociadaCcc2());
        assertEquals("30", dto.getBancoCtaExtAbono());
        assertEquals("31", dto.getCuentaExtAbono());
        assertEquals("32", dto.getDivisCtaExtAbono());
        assertEquals("33", dto.getTipoCtaExtAbono());
        assertEquals("34", dto.getBancoCtaExtFondeo());
        assertEquals("35", dto.getCuentaExtFondeo());
        assertEquals("36", dto.getDivisCtaExtFondeo());
        assertEquals("37", dto.getTipoCtaExtFonde());
        assertEquals("38", dto.getImporteGmfBonifica());
        assertEquals("39", dto.getPorcentajeDeGmfBonificado());
        assertEquals("40", dto.getImporteRetFteCal());
        assertEquals("41", dto.getPorcentajeDeRetFuentaCa());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBp02DataRequest dto = TrxBp02DataRequest.builder()
                .numeroDePersona("123")
                .producto("04")
                .importe("1000")
                .build();

        assertNotNull(dto);
        assertEquals("123", dto.getNumeroDePersona());
        assertEquals("04", dto.getProducto());
        assertEquals("1000", dto.getImporte());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBp02DataRequest dto = new TrxBp02DataRequest(
                "1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40","41"
        );

        assertNotNull(dto);
        assertEquals("1", dto.getNumeroDePersona());
        assertEquals("41", dto.getPorcentajeDeRetFuentaCa());
    }
}