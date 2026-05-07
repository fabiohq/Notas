package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalInfoTest {

    @Test
    void shouldSetAndGetAllFields() {

        AdditionalInfo dto = new AdditionalInfo();

        dto.setPaisResidenciaFiscal1("CO");
        dto.setPaisDescripcion("Colombia");
        dto.setPaisResidenciaFiscal2("US");
        dto.setPaisDescripcion2("Estados Unidos");

        dto.setClasificacionFATCA("FATCA");
        dto.setClasificacionCRS("CRS");

        dto.setValidacionFATCA(true);
        dto.setSalario(true);
        dto.setPensiones(false);
        dto.setPrestacionesServicio(true);
        dto.setArrendamientos(false);
        dto.setDonacionHerencia(true);
        dto.setHonorarios(false);
        dto.setMesada(true);
        dto.setActividadIndependiente(false);
        dto.setOtro(true);

        dto.setNumIdentiTributaria1("123456");
        dto.setNumIdentiTributaria2("654321");

        dto.setPreFormalizacion("PRE");

        dto.setValidacionCRS(true);
        dto.setSelfCertificacion(false);
        dto.setContribuyenteVentaColombia(true);
        dto.setReportable(false);
        dto.setAutorizoEnvioInformacion(true);

        dto.setCanalVenta("WEB");
        dto.setOficial("OFI01");
        dto.setSucursal("0001");
        dto.setuNeg("NEG01");

        dto.setSitCliente("ACTIVO");
        dto.setfAltaCliente("2024-01-01");

        dto.setNumdoc("123456789");
        dto.setNumper("999999");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");

        assertEquals("CO", dto.getPaisResidenciaFiscal1());
        assertEquals("Colombia", dto.getPaisDescripcion());
        assertEquals("US", dto.getPaisResidenciaFiscal2());
        assertEquals("Estados Unidos", dto.getPaisDescripcion2());

        assertEquals("FATCA", dto.getClasificacionFATCA());
        assertEquals("CRS", dto.getClasificacionCRS());

        assertTrue(dto.getValidacionFATCA());
        assertTrue(dto.getSalario());
        assertFalse(dto.getPensiones());
        assertTrue(dto.getPrestacionesServicio());
        assertFalse(dto.getArrendamientos());
        assertTrue(dto.getDonacionHerencia());
        assertFalse(dto.getHonorarios());
        assertTrue(dto.getMesada());
        assertFalse(dto.getActividadIndependiente());
        assertTrue(dto.getOtro());

        assertEquals("123456", dto.getNumIdentiTributaria1());
        assertEquals("654321", dto.getNumIdentiTributaria2());

        assertEquals("PRE", dto.getPreFormalizacion());

        assertTrue(dto.getValidacionCRS());
        assertFalse(dto.getSelfCertificacion());
        assertTrue(dto.getContribuyenteVentaColombia());
        assertFalse(dto.getReportable());
        assertTrue(dto.getAutorizoEnvioInformacion());

        assertEquals("WEB", dto.getCanalVenta());
        assertEquals("OFI01", dto.getOficial());
        assertEquals("0001", dto.getSucursal());
        assertEquals("NEG01", dto.getuNeg());

        assertEquals("ACTIVO", dto.getSitCliente());
        assertEquals("2024-01-01", dto.getfAltaCliente());

        assertEquals("123456789", dto.getNumdoc());
        assertEquals("999999", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
    }
}