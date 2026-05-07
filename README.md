Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplementaryInfoTest {

    @Test
    void shouldSetAndGetAllFields() {

        ComplementaryInfo dto = new ComplementaryInfo();

        dto.setEstadoCivil("S");
        dto.setTipoVivienda("CASA");
        dto.setEstrato("3");
        dto.setAnios("10");
        dto.setMes("5");
        dto.setAnios2("2");
        dto.setNivelEstudios("UNIVERSITARIO");
        dto.setPersonasCargo("2");
        dto.setNumHijos("1");
        dto.setHstamp1("H1");
        dto.setHstamp2("H2");
        dto.setLugnac("11001");
        dto.setNumper2("123456");
        dto.setNumintp("999");
        dto.setSeccel("1");
        dto.setSecdoc("2");
        dto.setSecdotc("3");
        dto.setSecdotp("4");
        dto.setSecema("5");
        dto.setSectelp("6");
        dto.setTipdoc("CC");

        assertEquals("S", dto.getEstadoCivil());
        assertEquals("CASA", dto.getTipoVivienda());
        assertEquals("3", dto.getEstrato());
        assertEquals("10", dto.getAnios());
        assertEquals("5", dto.getMes());
        assertEquals("2", dto.getAnios2());
        assertEquals("UNIVERSITARIO", dto.getNivelEstudios());
        assertEquals("2", dto.getPersonasCargo());
        assertEquals("1", dto.getNumHijos());
        assertEquals("H1", dto.getHstamp1());
        assertEquals("H2", dto.getHstamp2());
        assertEquals("11001", dto.getLugnac());
        assertEquals("123456", dto.getNumper2());
        assertEquals("999", dto.getNumintp());
        assertEquals("1", dto.getSeccel());
        assertEquals("2", dto.getSecdoc());
        assertEquals("3", dto.getSecdotc());
        assertEquals("4", dto.getSecdotp());
        assertEquals("5", dto.getSecema());
        assertEquals("6", dto.getSectelp());
        assertEquals("CC", dto.getTipdoc());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EconomyActivityTest {

    @Test
    void shouldSetAndGetAllFields() {

        EconomyActivity dto = new EconomyActivity();

        dto.setCargo("DEV");
        dto.setDescCargo("Developer");
        dto.setTipoVia("CL");
        dto.setDepartamento("11");
        dto.setTipoContrato("FIJO");
        dto.setPais("COL");
        dto.setPaisDescripcion("Colombia");
        dto.setCiudad("11001");
        dto.setCiudadDescripcion("Bogota");
        dto.setOcupacion("ING");
        dto.setDescOcupacion("Ingeniero");
        dto.setActiEconomica("6201");
        dto.setDescActiEconomica("Software");
        dto.setAntiguedadAnio("5");
        dto.setAntiguedadMes("6");
        dto.setNombreEmpresa("Santander");
        dto.setNit("123456");
        dto.setFechaIngreso("2020-01-01");
        dto.setFecha2("2021-01-01");
        dto.setNombreVia("Calle 1");
        dto.setDescripcionDireccion("Apto 101");
        dto.setIndicativo("57");
        dto.setTelefono("3001234567");
        dto.setOpcionActividad("EMP");
        dto.setNumdoc("123");
        dto.setNumper("999");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");

        assertEquals("DEV", dto.getCargo());
        assertEquals("Developer", dto.getDescCargo());
        assertEquals("CL", dto.getTipoVia());
        assertEquals("11", dto.getDepartamento());
        assertEquals("FIJO", dto.getTipoContrato());
        assertEquals("COL", dto.getPais());
        assertEquals("Colombia", dto.getPaisDescripcion());
        assertEquals("11001", dto.getCiudad());
        assertEquals("Bogota", dto.getCiudadDescripcion());
        assertEquals("ING", dto.getOcupacion());
        assertEquals("Ingeniero", dto.getDescOcupacion());
        assertEquals("6201", dto.getActiEconomica());
        assertEquals("Software", dto.getDescActiEconomica());
        assertEquals("5", dto.getAntiguedadAnio());
        assertEquals("6", dto.getAntiguedadMes());
        assertEquals("Santander", dto.getNombreEmpresa());
        assertEquals("123456", dto.getNit());
        assertEquals("2020-01-01", dto.getFechaIngreso());
        assertEquals("2021-01-01", dto.getFecha2());
        assertEquals("Calle 1", dto.getNombreVia());
        assertEquals("Apto 101", dto.getDescripcionDireccion());
        assertEquals("57", dto.getIndicativo());
        assertEquals("3001234567", dto.getTelefono());
        assertEquals("EMP", dto.getOpcionActividad());
        assertEquals("123", dto.getNumdoc());
        assertEquals("999", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinancialInformationTest {

    @Test
    void shouldSetAndGetAllFields() {

        FinancialInformation dto = new FinancialInformation();

        dto.setIngresosFijos(1000);
        dto.setOtrosIngresos1(200);
        dto.setTotalIngresos(1200);
        dto.setCuotasCreditos(300);
        dto.setOtrosEgresos(100);
        dto.setTotalEgresos(400);
        dto.setBienesRaices(10000);
        dto.setOtrosBienes(5000);
        dto.setTotalActivos(15000);
        dto.setSaldoTarjetaCredito(1000);
        dto.setSaldoOtrasObligaciones(500);
        dto.setTotalPasivos(1500);

        dto.setMatricinmuebles("MAT1");
        dto.setMatricinmuebles2("MAT2");
        dto.setMatricinmuebles3("MAT3");

        dto.setValorComercial("100");
        dto.setValorComercial2("200");
        dto.setValorComercial3("300");

        dto.setSaldoHipoteca("10");
        dto.setSaldoHipoteca2("20");
        dto.setSaldoHipoteca3("30");

        dto.setSaldo("500");
        dto.setSaldo2("600");

        dto.setNumdoc("123");
        dto.setNumper("999");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");
        dto.setTipoInmueble("CASA");

        assertEquals(1000, dto.getIngresosFijos());
        assertEquals(200, dto.getOtrosIngresos1());
        assertEquals(1200, dto.getTotalIngresos());
        assertEquals(300, dto.getCuotasCreditos());
        assertEquals(100, dto.getOtrosEgresos());
        assertEquals(400, dto.getTotalEgresos());
        assertEquals(10000, dto.getBienesRaices());
        assertEquals(5000, dto.getOtrosBienes());
        assertEquals(15000, dto.getTotalActivos());
        assertEquals(1000, dto.getSaldoTarjetaCredito());
        assertEquals(500, dto.getSaldoOtrasObligaciones());
        assertEquals(1500, dto.getTotalPasivos());

        assertEquals("MAT1", dto.getMatricinmuebles());
        assertEquals("MAT2", dto.getMatricinmuebles2());
        assertEquals("MAT3", dto.getMatricinmuebles3());

        assertEquals("100", dto.getValorComercial());
        assertEquals("200", dto.getValorComercial2());
        assertEquals("300", dto.getValorComercial3());

        assertEquals("10", dto.getSaldoHipoteca());
        assertEquals("20", dto.getSaldoHipoteca2());
        assertEquals("30", dto.getSaldoHipoteca3());

        assertEquals("500", dto.getSaldo());
        assertEquals("600", dto.getSaldo2());

        assertEquals("123", dto.getNumdoc());
        assertEquals("999", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
        assertEquals("CASA", dto.getTipoInmueble());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentificationDataTest {

    @Test
    void shouldSetAndGetAllFields() {

        IdentificationData dto = new IdentificationData();

        dto.setNumeroDocumento("123");
        dto.setNumPersona("999");
        dto.setApellidos("Perez");
        dto.setEstado("ACTIVO");
        dto.setTipoPersona("N");

        dto.setpECODPR("COD");
        dto.setpEESTPE("EST");
        dto.setpEFECNA("1990");
        dto.setpEHSTAM("STAMP");
        dto.setpEINDAV("A");
        dto.setpEINDCO("B");
        dto.setpEINDGR("C");
        dto.setpEINDN3("D");
        dto.setpEINDN4("E");
        dto.setpEINDN5("F");
        dto.setpEINDRE("G");
        dto.setpEMARNO("H");
        dto.setpENOMCA("I");
        dto.setpENOMLO("J");
        dto.setpEOBSE1("K");
        dto.setpEPRIAP("L");
        dto.setpERUTCA("M");
        dto.setpESECDO("N");
        dto.setpESEGAP("O");
        dto.setpETIPDO("CC");
        dto.setpETIPVI("CASA");

        assertEquals("123", dto.getNumeroDocumento());
        assertEquals("999", dto.getNumPersona());
        assertEquals("Perez", dto.getApellidos());
        assertEquals("ACTIVO", dto.getEstado());
        assertEquals("N", dto.getTipoPersona());

        assertEquals("COD", dto.getpECODPR());
        assertEquals("EST", dto.getpEESTPE());
        assertEquals("1990", dto.getpEFECNA());
        assertEquals("STAMP", dto.getpEHSTAM());
        assertEquals("A", dto.getpEINDAV());
        assertEquals("B", dto.getpEINDCO());
        assertEquals("C", dto.getpEINDGR());
        assertEquals("D", dto.getpEINDN3());
        assertEquals("E", dto.getpEINDN4());
        assertEquals("F", dto.getpEINDN5());
        assertEquals("G", dto.getpEINDRE());
        assertEquals("H", dto.getpEMARNO());
        assertEquals("I", dto.getpENOMCA());
        assertEquals("J", dto.getpENOMLO());
        assertEquals("K", dto.getpEOBSE1());
        assertEquals("L", dto.getpEPRIAP());
        assertEquals("M", dto.getpERUTCA());
        assertEquals("N", dto.getpESECDO());
        assertEquals("O", dto.getpESEGAP());
        assertEquals("CC", dto.getpETIPDO());
        assertEquals("CASA", dto.getpETIPVI());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalOperationsTest {

    @Test
    void shouldSetAndGetAllFields() {

        InternationalOperations dto = new InternationalOperations();

        dto.setRealizaOperaMoneExtran("SI");
        dto.setInversiones(true);
        dto.setGiros(false);
        dto.setCreditos(true);
        dto.setImportaciones(false);
        dto.setExportaciones(true);
        dto.setOtro(false);
        dto.setTieneProdMoneExtraje("NO");
        dto.setNumdoc("123");
        dto.setNumper("999");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");

        assertEquals("SI", dto.getRealizaOperaMoneExtran());
        assertTrue(dto.getInversiones());
        assertFalse(dto.getGiros());
        assertTrue(dto.getCreditos());
        assertFalse(dto.getImportaciones());
        assertTrue(dto.getExportaciones());
        assertFalse(dto.getOtro());
        assertEquals("NO", dto.getTieneProdMoneExtraje());
        assertEquals("123", dto.getNumdoc());
        assertEquals("999", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferencesTest {

    @Test
    void shouldSetAndGetAllFields() {

        References dto = new References();

        dto.setParentesco("PADRE");
        dto.setNombre("Juan");
        dto.setPrimerApellido("Perez");
        dto.setCiudad("11001");
        dto.setCiudadReferencia("Bogota");
        dto.setIndictivo("57");
        dto.setTelefono("3001234567");
        dto.setDireccion("Calle 1");
        dto.setNumdoc("123");
        dto.setNumper("999");
        dto.setSecdoc("1");
        dto.setSecref1("2");
        dto.setTipdoc("CC");

        assertEquals("PADRE", dto.getParentesco());
        assertEquals("Juan", dto.getNombre());
        assertEquals("Perez", dto.getPrimerApellido());
        assertEquals("11001", dto.getCiudad());
        assertEquals("Bogota", dto.getCiudadReferencia());
        assertEquals("57", dto.getIndictivo());
        assertEquals("3001234567", dto.getTelefono());
        assertEquals("Calle 1", dto.getDireccion());
        assertEquals("123", dto.getNumdoc());
        assertEquals("999", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("2", dto.getSecref1());
        assertEquals("CC", dto.getTipdoc());
    }
}