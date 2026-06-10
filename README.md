package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxBP13DataResponseTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {

        TrxBP13DataResponse dto = new TrxBP13DataResponse();

        dto.setNumCertificado("1");
        dto.setCertificadoReemplazado("2");
        dto.setCodigoInversor("3");
        dto.setSecuenciaIPF("4");
        dto.setSecuenciaRenovacion("5");
        dto.setProducto("6");
        dto.setSubproducto("7");
        dto.setFecAlta("8");
        dto.setFecOperacion("9");
        dto.setIndicadorGrantia("10");
        dto.setTarifaVigente("11");
        dto.setEstadoIPF("12");
        dto.setSpread("13");
        dto.setIndicadorONP("14");
        dto.setMoneda("15");
        dto.setDesMoneda("16");
        dto.setSaldoInicial("17");
        dto.setPlazo(18);
        dto.setPeriodoLiquidacion("19");
        dto.setTipoTitular("20");
        dto.setNumTitular("21");
        dto.setPriApellido("22");
        dto.setSegApellido("23");
        dto.setNombreTitular("24");
        dto.setTipoEfectivo("25");
        dto.setCambioUR(26);
        dto.setTipoInteres("27");
        dto.setCapInteres("28");
        dto.setCapReajuste("29");
        dto.setRenovacionAutomatica("30");
        dto.setEjecutivoComercial("31");
        dto.setPlanComercial("32");
        dto.setCustodia("33");
        dto.setDesCustodia("34");
        dto.setCanalApertura("35");
        dto.setTransferible("36");
        dto.setOrigen("37");
        dto.setObservaciones("38");
        dto.setSucIngCustodia("39");
        dto.setFecIngCustodia("40");
        dto.setSucEgrCustodia("41");
        dto.setFecEgrCustodia("42");
        dto.setImporteRestProgra(43);
        dto.setCentroGestor("44");
        dto.setAcuerdo("45");
        dto.setCuentaAsociada("46");
        dto.setFecAnulacion("47");
        dto.setFecVencimiento("48");
        dto.setFecCancelacion("49");
        dto.setFecLiquidacion("50");
        dto.setFecLiqReajuste("51");
        dto.setTarifaRenovacion("52");
        dto.setSpreadRenovacion(53);
        dto.setInteresesAvonado("54");
        dto.setInteresesPendiente("55");
        dto.setImportePeriodico("56");
        dto.setIndicadorBloqueo("57");
        dto.setInteresesReajuste(58);
        dto.setPago("59");
        dto.setDesPago("60");
        dto.setSaldoDisponible("61");
        dto.setCuentaCliente("62");
        dto.setImporteTipoTasa("63");
        dto.setNumDocumento("64");
        dto.setTipoDocumento("65");
        dto.setTipoOperacion("66");
        dto.setMotivoCancelacion("67");
        dto.setLina1("68");
        dto.setLina2("69");

        assertEquals("1", dto.getNumCertificado());
        assertEquals("2", dto.getCertificadoReemplazado());
        assertEquals("3", dto.getCodigoInversor());
        assertEquals("4", dto.getSecuenciaIPF());
        assertEquals("5", dto.getSecuenciaRenovacion());
        assertEquals("6", dto.getProducto());
        assertEquals("7", dto.getSubproducto());
        assertEquals("8", dto.getFecAlta());
        assertEquals("9", dto.getFecOperacion());
        assertEquals("10", dto.getIndicadorGrantia());
        assertEquals("11", dto.getTarifaVigente());
        assertEquals("12", dto.getEstadoIPF());
        assertEquals("13", dto.getSpread());
        assertEquals("14", dto.getIndicadorONP());
        assertEquals("15", dto.getMoneda());
        assertEquals("16", dto.getDesMoneda());
        assertEquals("17", dto.getSaldoInicial());
        assertEquals(18, dto.getPlazo());
        assertEquals("19", dto.getPeriodoLiquidacion());
        assertEquals("20", dto.getTipoTitular());
        assertEquals("21", dto.getNumTitular());
        assertEquals("22", dto.getPriApellido());
        assertEquals("23", dto.getSegApellido());
        assertEquals("24", dto.getNombreTitular());
        assertEquals("25", dto.getTipoEfectivo());
        assertEquals(26, dto.getCambioUR());
        assertEquals("27", dto.getTipoInteres());
        assertEquals("28", dto.getCapInteres());
        assertEquals("29", dto.getCapReajuste());
        assertEquals("30", dto.getRenovacionAutomatica());
        assertEquals("31", dto.getEjecutivoComercial());
        assertEquals("32", dto.getPlanComercial());
        assertEquals("33", dto.getCustodia());
        assertEquals("34", dto.getDesCustodia());
        assertEquals("35", dto.getCanalApertura());
        assertEquals("36", dto.getTransferible());
        assertEquals("37", dto.getOrigen());
        assertEquals("38", dto.getObservaciones());
        assertEquals("39", dto.getSucIngCustodia());
        assertEquals("40", dto.getFecIngCustodia());
        assertEquals("41", dto.getSucEgrCustodia());
        assertEquals("42", dto.getFecEgrCustodia());
        assertEquals(43, dto.getImporteRestProgra());
        assertEquals("44", dto.getCentroGestor());
        assertEquals("45", dto.getAcuerdo());
        assertEquals("46", dto.getCuentaAsociada());
        assertEquals("47", dto.getFecAnulacion());
        assertEquals("48", dto.getFecVencimiento());
        assertEquals("49", dto.getFecCancelacion());
        assertEquals("50", dto.getFecLiquidacion());
        assertEquals("51", dto.getFecLiqReajuste());
        assertEquals("52", dto.getTarifaRenovacion());
        assertEquals(53, dto.getSpreadRenovacion());
        assertEquals("54", dto.getInteresesAvonado());
        assertEquals("55", dto.getInteresesPendiente());
        assertEquals("56", dto.getImportePeriodico());
        assertEquals("57", dto.getIndicadorBloqueo());
        assertEquals(58, dto.getInteresesReajuste());
        assertEquals("59", dto.getPago());
        assertEquals("60", dto.getDesPago());
        assertEquals("61", dto.getSaldoDisponible());
        assertEquals("62", dto.getCuentaCliente());
        assertEquals("63", dto.getImporteTipoTasa());
        assertEquals("64", dto.getNumDocumento());
        assertEquals("65", dto.getTipoDocumento());
        assertEquals("66", dto.getTipoOperacion());
        assertEquals("67", dto.getMotivoCancelacion());
        assertEquals("68", dto.getLina1());
        assertEquals("69", dto.getLina2());
    }

    @Test
    void shouldCoverBuilder() {

        TrxBP13DataResponse dto = TrxBP13DataResponse.builder()
                .numCertificado("ABC")
                .producto("CDT")
                .capInteres("S")
                .plazo(30)
                .build();

        assertEquals("ABC", dto.getNumCertificado());
        assertEquals("CDT", dto.getProducto());
        assertEquals("S", dto.getCapInteres());
        assertEquals(30, dto.getPlazo());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        TrxBP13DataResponse dto =
                new TrxBP13DataResponse(
                        "1","2","3","4","5","6","7","8","9","10",
                        "11","12","13","14","15","16","17",18,"19","20",
                        "21","22","23","24","25",26,"27","28","29","30",
                        "31","32","33","34","35","36","37","38","39","40",
                        "41","42",43,"44","45","46","47","48","49","50",
                        "51","52",53,"54","55","56","57",58,"59","60",
                        "61","62","63","64","65","66","67","68","69"
                );

        assertEquals("1", dto.getNumCertificado());
        assertEquals("69", dto.getLina2());
    }
}
