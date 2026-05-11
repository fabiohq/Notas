package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalInfoTest {

    @Test
    void shouldCoverAllGettersAndSetters() {

        AdditionalInfo dto = new AdditionalInfo();

        dto.setPaisResidenciaFiscal1("CO");
        dto.setPaisDescripcion("Colombia");
        dto.setPaisResidenciaFiscal2("US");
        dto.setPaisDescripcion2("USA");
        dto.setClasificacionFATCA("FATCA");
        dto.setClasificacionCRS("CRS");

        dto.setValidacionFATCA(true);
        dto.setSalario(true);
        dto.setPensiones(true);
        dto.setPrestacionesServicio(true);
        dto.setArrendamientos(true);
        dto.setDonacionHerencia(true);
        dto.setHonorarios(true);
        dto.setMesada(true);
        dto.setActividadIndependiente(true);
        dto.setOtro(true);

        dto.setNumIdentiTributaria1("123");
        dto.setNumIdentiTributaria2("456");
        dto.setPreFormalizacion("PRE");

        dto.setValidacionCRS(true);
        dto.setSelfCertificacion(true);
        dto.setContribuyenteVentaColombia(true);
        dto.setReportable(true);
        dto.setAutorizoEnvioInformacion(true);

        dto.setCanalVenta("WEB");
        dto.setOficial("OFI");
        dto.setSucursal("0100");
        dto.setuNeg("NEG");
        dto.setSitCliente("ACTIVO");
        dto.setfAltaCliente("2024-01-01");

        dto.setNumdoc("111");
        dto.setNumper("222");
        dto.setSecdoc("333");
        dto.setTipdoc("CC");

        assertEquals("CO", dto.getPaisResidenciaFiscal1());
        assertEquals("Colombia", dto.getPaisDescripcion());
        assertEquals("US", dto.getPaisResidenciaFiscal2());
        assertEquals("USA", dto.getPaisDescripcion2());
        assertEquals("FATCA", dto.getClasificacionFATCA());
        assertEquals("CRS", dto.getClasificacionCRS());

        assertTrue(dto.getValidacionFATCA());
        assertTrue(dto.getSalario());
        assertTrue(dto.getPensiones());
        assertTrue(dto.getPrestacionesServicio());
        assertTrue(dto.getArrendamientos());
        assertTrue(dto.getDonacionHerencia());
        assertTrue(dto.getHonorarios());
        assertTrue(dto.getMesada());
        assertTrue(dto.getActividadIndependiente());
        assertTrue(dto.getOtro());

        assertEquals("123", dto.getNumIdentiTributaria1());
        assertEquals("456", dto.getNumIdentiTributaria2());
        assertEquals("PRE", dto.getPreFormalizacion());

        assertTrue(dto.getValidacionCRS());
        assertTrue(dto.getSelfCertificacion());
        assertTrue(dto.getContribuyenteVentaColombia());
        assertTrue(dto.getReportable());
        assertTrue(dto.getAutorizoEnvioInformacion());

        assertEquals("WEB", dto.getCanalVenta());
        assertEquals("OFI", dto.getOficial());
        assertEquals("0100", dto.getSucursal());
        assertEquals("NEG", dto.getuNeg());
        assertEquals("ACTIVO", dto.getSitCliente());
        assertEquals("2024-01-01", dto.getfAltaCliente());

        assertEquals("111", dto.getNumdoc());
        assertEquals("222", dto.getNumper());
        assertEquals("333", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
    }

    @Test
    void shouldCoverBuilderAndConstructors() {

        AdditionalInfo dto = AdditionalInfo.builder()
                .paisResidenciaFiscal1("CO")
                .clasificacionFATCA("FATCA")
                .build();

        assertEquals("CO", dto.getPaisResidenciaFiscal1());
        assertEquals("FATCA", dto.getClasificacionFATCA());

        AdditionalInfo dto2 = new AdditionalInfo();

        assertNotNull(dto2);
    }
}