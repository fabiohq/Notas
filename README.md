package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP17DataResponseTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP17DataResponse dto = new TrxBP17DataResponse();

        dto.setIntPendAbonar("1");
        dto.setTasaInteresNominal("2");
        dto.setTasaInteresEA("3");
        dto.setFechaProximoReajuste("4");
        dto.setFecVencimiento("5");
        dto.setCodigoDeProducto("6");
        dto.setCodigoDeSubproduct("7");
        dto.setDescripcionProdu("8");
        dto.setImporteBaseInvers("9");
        dto.setCodigoDeDivisa("10");
        dto.setCodigoDeTarifa("11");
        dto.setPlazoEnDias("12");
        dto.setDescripPlazo("13");
        dto.setPlazoEnDiasDispon("14");
        dto.setPeriodoLiquidacion("15");
        dto.setDescrPeriodoLiq("16");
        dto.setPeriodosDisponibles("17");
        dto.setMontoFijoBonGmf("18");
        dto.setPorcentajeFijoBonGmf("19");
        dto.setImporteGmfMaximo("20");
        dto.setImporteGmfBonific("21");
        dto.setTipoBonifGmfCalc("22");
        dto.setImporteTotalInvers("23");
        dto.setImporteBrutoIntere("24");
        dto.setPorcentajeDeRetencionFuent("25");
        dto.setImporteRetencFuent("26");
        dto.setImporteNetoInteres("27");
        dto.setImporteTotalCobrar("28");
        dto.setFechaDeAlta("29");
        dto.setFechaDeVencimiento("30");
        dto.setFechaProxLiquidac("31");
        dto.setPorcentajeDeInteresNominal("32");
        dto.setPorcentajeDeSpread("33");
        dto.setPorcentajeDeTasaEfectiva("34");

        assertEquals("1", dto.getIntPendAbonar());
        assertEquals("2", dto.getTasaInteresNominal());
        assertEquals("3", dto.getTasaInteresEA());
        assertEquals("4", dto.getFechaProximoReajuste());
        assertEquals("5", dto.getFecVencimiento());
        assertEquals("6", dto.getCodigoDeProducto());
        assertEquals("7", dto.getCodigoDeSubproduct());
        assertEquals("8", dto.getDescripcionProdu());
        assertEquals("9", dto.getImporteBaseInvers());
        assertEquals("10", dto.getCodigoDeDivisa());
        assertEquals("11", dto.getCodigoDeTarifa());
        assertEquals("12", dto.getPlazoEnDias());
        assertEquals("13", dto.getDescripPlazo());
        assertEquals("14", dto.getPlazoEnDiasDispon());
        assertEquals("15", dto.getPeriodoLiquidacion());
        assertEquals("16", dto.getDescrPeriodoLiq());
        assertEquals("17", dto.getPeriodosDisponibles());
        assertEquals("18", dto.getMontoFijoBonGmf());
        assertEquals("19", dto.getPorcentajeFijoBonGmf());
        assertEquals("20", dto.getImporteGmfMaximo());
        assertEquals("21", dto.getImporteGmfBonific());
        assertEquals("22", dto.getTipoBonifGmfCalc());
        assertEquals("23", dto.getImporteTotalInvers());
        assertEquals("24", dto.getImporteBrutoIntere());
        assertEquals("25", dto.getPorcentajeDeRetencionFuent());
        assertEquals("26", dto.getImporteRetencFuent());
        assertEquals("27", dto.getImporteNetoInteres());
        assertEquals("28", dto.getImporteTotalCobrar());
        assertEquals("29", dto.getFechaDeAlta());
        assertEquals("30", dto.getFechaDeVencimiento());
        assertEquals("31", dto.getFechaProxLiquidac());
        assertEquals("32", dto.getPorcentajeDeInteresNominal());
        assertEquals("33", dto.getPorcentajeDeSpread());
        assertEquals("34", dto.getPorcentajeDeTasaEfectiva());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP17DataResponse dto = TrxBP17DataResponse.builder()
                .intPendAbonar("1")
                .codigoDeProducto("04")
                .porcentajeDeTasaEfectiva("34")
                .build();

        assertNotNull(dto);
        assertEquals("1", dto.getIntPendAbonar());
        assertEquals("04", dto.getCodigoDeProducto());
        assertEquals("34", dto.getPorcentajeDeTasaEfectiva());
    }
}