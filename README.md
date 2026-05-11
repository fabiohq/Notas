Sí. Usa estos tests para completar esos métodos en rojo.
FinancialInformationTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinancialInformationTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        FinancialInformation dto = new FinancialInformation();

        dto.setCuotasCreditos(1);
        dto.setOtrosEgresos(2);
        dto.setTotalEgresos(3);
        dto.setBienesRaices(4);
        dto.setOtrosBienes(5);
        dto.setTotalActivos(6);
        dto.setSaldoTarjetaCredito(7);
        dto.setSaldoOtrasObligaciones(8);
        dto.setTotalPasivos(9);

        dto.setMatricinmuebles("M1");
        dto.setMatricinmuebles2("M2");
        dto.setMatricinmuebles3("M3");
        dto.setValorComercial2("VC2");
        dto.setValorComercial3("VC3");
        dto.setSaldoHipoteca("SH1");
        dto.setSaldoHipoteca2("SH2");
        dto.setSaldoHipoteca3("SH3");
        dto.setSaldo2("S2");

        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("789");
        dto.setTipdoc("CC");

        dto.setIngresosFijos(100);
        dto.setOtrosIngresos1(200);
        dto.setTotalIngresos(300);
        dto.setValorComercial("VC1");
        dto.setSaldo("S1");
        dto.setTipoInmueble("CASA");

        assertEquals(1, dto.getCuotasCreditos());
        assertEquals(2, dto.getOtrosEgresos());
        assertEquals(3, dto.getTotalEgresos());
        assertEquals(4, dto.getBienesRaices());
        assertEquals(5, dto.getOtrosBienes());
        assertEquals(6, dto.getTotalActivos());
        assertEquals(7, dto.getSaldoTarjetaCredito());
        assertEquals(8, dto.getSaldoOtrasObligaciones());
        assertEquals(9, dto.getTotalPasivos());

        assertEquals("M1", dto.getMatricinmuebles());
        assertEquals("M2", dto.getMatricinmuebles2());
        assertEquals("M3", dto.getMatricinmuebles3());
        assertEquals("VC2", dto.getValorComercial2());
        assertEquals("VC3", dto.getValorComercial3());
        assertEquals("SH1", dto.getSaldoHipoteca());
        assertEquals("SH2", dto.getSaldoHipoteca2());
        assertEquals("SH3", dto.getSaldoHipoteca3());
        assertEquals("S2", dto.getSaldo2());

        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("789", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());

        assertEquals(100, dto.getIngresosFijos());
        assertEquals(200, dto.getOtrosIngresos1());
        assertEquals(300, dto.getTotalIngresos());
        assertEquals("VC1", dto.getValorComercial());
        assertEquals("S1", dto.getSaldo());
        assertEquals("CASA", dto.getTipoInmueble());
    }
}
IdentificationDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentificationDataTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        IdentificationData dto = new IdentificationData();

        dto.setEstado("ACTIVO");
        dto.setpECODPR("CODPR");
        dto.setpEESTPE("ESTPE");
        dto.setpEFECNA("FECNA");
        dto.setpEHSTAM("HSTAM");
        dto.setpEINDAV("INDAV");
        dto.setpEINDCO("INDCO");
        dto.setpEINDGR("INDGR");
        dto.setpEINDN3("INDN3");
        dto.setpEINDN4("INDN4");
        dto.setpEINDN5("INDN5");
        dto.setpEINDRE("INDRE");
        dto.setpEMARNO("MARNO");
        dto.setpENOMCA("NOMCA");
        dto.setpENOMLO("NOMLO");
        dto.setpEOBSE1("OBSE1");
        dto.setpEPRIAP("PRIAP");
        dto.setpERUTCA("RUTCA");
        dto.setpESECDO("SECDO");
        dto.setpESEGAP("SEGAP");
        dto.setpETIPVI("TIPVI");

        dto.setNumeroDocumento("123");
        dto.setNumPersona("456");
        dto.setApellidos("PEREZ");
        dto.setTipoPersona("N");
        dto.setpETIPDO("CC");

        assertEquals("ACTIVO", dto.getEstado());
        assertEquals("CODPR", dto.getpECODPR());
        assertEquals("ESTPE", dto.getpEESTPE());
        assertEquals("FECNA", dto.getpEFECNA());
        assertEquals("HSTAM", dto.getpEHSTAM());
        assertEquals("INDAV", dto.getpEINDAV());
        assertEquals("INDCO", dto.getpEINDCO());
        assertEquals("INDGR", dto.getpEINDGR());
        assertEquals("INDN3", dto.getpEINDN3());
        assertEquals("INDN4", dto.getpEINDN4());
        assertEquals("INDN5", dto.getpEINDN5());
        assertEquals("INDRE", dto.getpEINDRE());
        assertEquals("MARNO", dto.getpEMARNO());
        assertEquals("NOMCA", dto.getpENOMCA());
        assertEquals("NOMLO", dto.getpENOMLO());
        assertEquals("OBSE1", dto.getpEOBSE1());
        assertEquals("PRIAP", dto.getpEPRIAP());
        assertEquals("RUTCA", dto.getpERUTCA());
        assertEquals("SECDO", dto.getpESECDO());
        assertEquals("SEGAP", dto.getpESEGAP());
        assertEquals("TIPVI", dto.getpETIPVI());

        assertEquals("123", dto.getNumeroDocumento());
        assertEquals("456", dto.getNumPersona());
        assertEquals("PEREZ", dto.getApellidos());
        assertEquals("N", dto.getTipoPersona());
        assertEquals("CC", dto.getpETIPDO());
    }
}
ComplementaryInfoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplementaryInfoTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        ComplementaryInfo dto = new ComplementaryInfo();

        dto.setMes("06");
        dto.setAnios2("10");
        dto.setPersonasCargo("3");
        dto.setHstamp1("H1");
        dto.setHstamp2("H2");
        dto.setLugnac("BOG");
        dto.setNumper2("999");
        dto.setNumintp("101");
        dto.setSeccel("1");
        dto.setSecdoc("2");
        dto.setSecdotc("3");
        dto.setSecdotp("4");
        dto.setSecema("5");
        dto.setSectelp("6");

        dto.setEstadoCivil("S");
        dto.setTipoVivienda("CASA");
        dto.setEstrato("3");
        dto.setAnios("5");
        dto.setNivelEstudios("UNI");
        dto.setNumHijos("2");
        dto.setTipdoc("CC");

        assertEquals("06", dto.getMes());
        assertEquals("10", dto.getAnios2());
        assertEquals("3", dto.getPersonasCargo());
        assertEquals("H1", dto.getHstamp1());
        assertEquals("H2", dto.getHstamp2());
        assertEquals("BOG", dto.getLugnac());
        assertEquals("999", dto.getNumper2());
        assertEquals("101", dto.getNumintp());
        assertEquals("1", dto.getSeccel());
        assertEquals("2", dto.getSecdoc());
        assertEquals("3", dto.getSecdotc());
        assertEquals("4", dto.getSecdotp());
        assertEquals("5", dto.getSecema());
        assertEquals("6", dto.getSectelp());

        assertEquals("S", dto.getEstadoCivil());
        assertEquals("CASA", dto.getTipoVivienda());
        assertEquals("3", dto.getEstrato());
        assertEquals("5", dto.getAnios());
        assertEquals("UNI", dto.getNivelEstudios());
        assertEquals("2", dto.getNumHijos());
        assertEquals("CC", dto.getTipdoc());
    }
}
ReferencesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferencesTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        References dto = new References();

        dto.setPrimerApellido("PEREZ");
        dto.setCiudad("BOG");
        dto.setCiudadReferencia("BOGOTA");
        dto.setIndictivo("57");
        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("789");
        dto.setSecref1("REF1");

        dto.setParentesco("PADRE");
        dto.setNombre("JUAN");
        dto.setTelefono("300123");
        dto.setDireccion("CALLE 1");
        dto.setTipdoc("CC");

        assertEquals("PEREZ", dto.getPrimerApellido());
        assertEquals("BOG", dto.getCiudad());
        assertEquals("BOGOTA", dto.getCiudadReferencia());
        assertEquals("57", dto.getIndictivo());
        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("789", dto.getSecdoc());
        assertEquals("REF1", dto.getSecref1());

        assertEquals("PADRE", dto.getParentesco());
        assertEquals("JUAN", dto.getNombre());
        assertEquals("300123", dto.getTelefono());
        assertEquals("CALLE 1", dto.getDireccion());
        assertEquals("CC", dto.getTipdoc());
    }
}
InternationalOperationsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalOperationsTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        InternationalOperations dto = new InternationalOperations();

        dto.setCreditos(true);
        dto.setImportaciones(true);
        dto.setExportaciones(true);
        dto.setOtro(true);
        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("789");

        dto.setRealizaOperaMoneExtran("YES");
        dto.setInversiones(true);
        dto.setGiros(true);
        dto.setTieneProdMoneExtraje("YES");
        dto.setTipdoc("CC");

        assertTrue(dto.getCreditos());
        assertTrue(dto.getImportaciones());
        assertTrue(dto.getExportaciones());
        assertTrue(dto.getOtro());
        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("789", dto.getSecdoc());

        assertEquals("YES", dto.getRealizaOperaMoneExtran());
        assertTrue(dto.getInversiones());
        assertTrue(dto.getGiros());
        assertEquals("YES", dto.getTieneProdMoneExtraje());
        assertEquals("CC", dto.getTipdoc());
    }
}
TrxPersonDataRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonDataRequestTest {

    @Test
    void shouldCoverAllGettersAndSetters() {
        BasicData basic = new BasicData();
        ComplementaryInfo complementary = new ComplementaryInfo();
        EconomyActivity economy = new EconomyActivity();
        FinancialInformation financial = new FinancialInformation();
        References references = new References();
        AdditionalInfo additional = new AdditionalInfo();
        InternationalOperations operations = new InternationalOperations();
        List<IdentificationData> identificationData = List.of(new IdentificationData());

        TrxPersonDataRequest dto = new TrxPersonDataRequest();

        dto.setPenumpe("PENUMPE");
        dto.setTipoInmueble("CASA");
        dto.setDatosBasicos(basic);
        dto.setInfComplementaria(complementary);
        dto.setActividadEconomica(economy);
        dto.setInfFinanciera(financial);
        dto.setReferencias(references);
        dto.setInfAdicional(additional);
        dto.setOperacionesInternacionales(operations);
        dto.setDocumentoCajero("CAJERO");
        dto.setTipoDocumento("CC");
        dto.setNumDocumento("123");
        dto.setNombre("JUAN");
        dto.setDatosIdentificacion(identificationData);

        assertEquals("PENUMPE", dto.getPenumpe());
        assertEquals("CASA", dto.getTipoInmueble());
        assertSame(basic, dto.getDatosBasicos());
        assertSame(complementary, dto.getInfComplementaria());
        assertSame(economy, dto.getActividadEconomica());
        assertSame(financial, dto.getInfFinanciera());
        assertSame(references, dto.getReferencias());
        assertSame(additional, dto.getInfAdicional());
        assertSame(operations, dto.getOperacionesInternacionales());
        assertEquals("CAJERO", dto.getDocumentoCajero());
        assertEquals("CC", dto.getTipoDocumento());
        assertEquals("123", dto.getNumDocumento());
        assertEquals("JUAN", dto.getNombre());
        assertSame(identificationData, dto.getDatosIdentificacion());
    }
}