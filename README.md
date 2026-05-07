Sí. Para cubrir esas clases DTO, lo más directo es un test por clase, usando setters/getters. Empieza con estos:
AdditionalInfoTest.java
Java
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
        dto.setPaisDescripcion2("USA");
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
        dto.setNumIdentiTributaria1("123");
        dto.setNumIdentiTributaria2("456");
        dto.setPreFormalizacion("PRE");
        dto.setValidacionCRS(true);
        dto.setSelfCertificacion(false);
        dto.setContribuyenteVentaColombia(true);
        dto.setReportable(false);
        dto.setAutorizoEnvioInformacion(true);
        dto.setCanalVenta("WEB");
        dto.setOficial("OFI");
        dto.setSucursal("001");
        dto.setuNeg("NEG");
        dto.setSitCliente("ACTIVO");
        dto.setfAltaCliente("2026-05-07");
        dto.setNumdoc("123456");
        dto.setNumper("87654321");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");

        assertEquals("CO", dto.getPaisResidenciaFiscal1());
        assertEquals("Colombia", dto.getPaisDescripcion());
        assertEquals("US", dto.getPaisResidenciaFiscal2());
        assertEquals("USA", dto.getPaisDescripcion2());
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
        assertEquals("123", dto.getNumIdentiTributaria1());
        assertEquals("456", dto.getNumIdentiTributaria2());
        assertEquals("PRE", dto.getPreFormalizacion());
        assertTrue(dto.getValidacionCRS());
        assertFalse(dto.getSelfCertificacion());
        assertTrue(dto.getContribuyenteVentaColombia());
        assertFalse(dto.getReportable());
        assertTrue(dto.getAutorizoEnvioInformacion());
        assertEquals("WEB", dto.getCanalVenta());
        assertEquals("OFI", dto.getOficial());
        assertEquals("001", dto.getSucursal());
        assertEquals("NEG", dto.getuNeg());
        assertEquals("ACTIVO", dto.getSitCliente());
        assertEquals("2026-05-07", dto.getfAltaCliente());
        assertEquals("123456", dto.getNumdoc());
        assertEquals("87654321", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());
    }
}
BasicDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicDataTest {

    @Test
    void shouldSetAndGetAllFields() {
        BasicData dto = new BasicData();

        dto.setTipoIdentificacion("CC");
        dto.setNumeroIdentificacion("123");
        dto.setNombre("Fabio");
        dto.setPrimerApellido("Perez");
        dto.setSegundoApellido("Lopez");
        dto.setPaisExpedicion("COL");
        dto.setPaisExpedicionDesc("Colombia");
        dto.setCiudadExpedicion("11001");
        dto.setLugardeExpDescripcion("Bogota");
        dto.setFechaExpedicion("2020-01-01");
        dto.setPaisNacimiento("COL");
        dto.setPaisNacimientoDesc("Colombia");
        dto.setNacionalidad("COL");
        dto.setNacionalidadDesc("Colombiano");
        dto.setCiudadNacimiento("11001");
        dto.setLugardeNacimiento("Bogota");
        dto.setFechaNacimiento("1990-01-01");
        dto.setSexo("H");
        dto.setPaisDireccion("COL");
        dto.setPaisDireccionDesc("Colombia");
        dto.setDepartamento("11");
        dto.setCiudad("11001");
        dto.setCiudadDescripcion("Bogota");
        dto.setTipoVia("CL");
        dto.setNombreVia("Calle 1");
        dto.setDescripcionDireccion("Apto 101");
        dto.setClase("004");
        dto.setIndicativo("57");
        dto.setTelefono("6011234567");
        dto.setPrecelular("57");
        dto.setCelular("3001234567");
        dto.setEmail("test@test.com");
        dto.setAutorizoTelefono(true);
        dto.setAutorizacionEmail(false);
        dto.setAgrofic("0003");
        dto.setCodact("ACT");
        dto.setCodpaip("57");
        dto.setConper("NCL");
        dto.setDomant("1");
        dto.setEntpre("ENT");
        dto.setEstciv("S");
        dto.setEstper("A");
        dto.setEstrat("3");
        dto.setFecalt("2020-01-01");
        dto.setFecfal("2021-01-01");
        dto.setFecing("2022-01-01");
        dto.setHstamp("h1");
        dto.setHstamp2("h2");
        dto.setHstamp3("h3");
        dto.setHstamp4("h4");
        dto.setHstamp5("h5");
        dto.setLogdomp("logdomp");
        dto.setLogtelp("logtelp");
        dto.setNumper("87654321");
        dto.setPrecel("57");
        dto.setProfes("DEV");
        dto.setSeccel("1");
        dto.setSecdoc("2");
        dto.setSecdomp("3");
        dto.setSecdotc("4");
        dto.setSecdotp("5");
        dto.setSecema("6");
        dto.setSectelp("7");
        dto.setSucadm("0100");
        dto.setSucmod("0200");
        dto.setTermod("TERM");
        dto.setTipdomp("TD");
        dto.setTipocu("TO");
        dto.setTipper("TP");
        dto.setTiptelp("TT");
        dto.setUsualt("ODSCONS");
        dto.setUsumod("USER");

        assertEquals("CC", dto.getTipoIdentificacion());
        assertEquals("123", dto.getNumeroIdentificacion());
        assertEquals("Fabio", dto.getNombre());
        assertEquals("Perez", dto.getPrimerApellido());
        assertEquals("Lopez", dto.getSegundoApellido());
        assertEquals("COL", dto.getPaisExpedicion());
        assertEquals("Colombia", dto.getPaisExpedicionDesc());
        assertEquals("11001", dto.getCiudadExpedicion());
        assertEquals("Bogota", dto.getLugardeExpDescripcion());
        assertEquals("2020-01-01", dto.getFechaExpedicion());
        assertEquals("COL", dto.getPaisNacimiento());
        assertEquals("Colombia", dto.getPaisNacimientoDesc());
        assertEquals("COL", dto.getNacionalidad());
        assertEquals("Colombiano", dto.getNacionalidadDesc());
        assertEquals("11001", dto.getCiudadNacimiento());
        assertEquals("Bogota", dto.getLugardeNacimiento());
        assertEquals("1990-01-01", dto.getFechaNacimiento());
        assertEquals("H", dto.getSexo());
        assertEquals("COL", dto.getPaisDireccion());
        assertEquals("Colombia", dto.getPaisDireccionDesc());
        assertEquals("11", dto.getDepartamento());
        assertEquals("11001", dto.getCiudad());
        assertEquals("Bogota", dto.getCiudadDescripcion());
        assertEquals("CL", dto.getTipoVia());
        assertEquals("Calle 1", dto.getNombreVia());
        assertEquals("Apto 101", dto.getDescripcionDireccion());
        assertEquals("004", dto.getClase());
        assertEquals("57", dto.getIndicativo());
        assertEquals("6011234567", dto.getTelefono());
        assertEquals("57", dto.getPrecelular());
        assertEquals("3001234567", dto.getCelular());
        assertEquals("test@test.com", dto.getEmail());
        assertTrue(dto.getAutorizoTelefono());
        assertFalse(dto.getAutorizacionEmail());
        assertEquals("0003", dto.getAgrofic());
        assertEquals("ACT", dto.getCodact());
        assertEquals("57", dto.getCodpaip());
        assertEquals("NCL", dto.getConper());
        assertEquals("1", dto.getDomant());
        assertEquals("ENT", dto.getEntpre());
        assertEquals("S", dto.getEstciv());
        assertEquals("A", dto.getEstper());
        assertEquals("3", dto.getEstrat());
        assertEquals("2020-01-01", dto.getFecalt());
        assertEquals("2021-01-01", dto.getFecfal());
        assertEquals("2022-01-01", dto.getFecing());
        assertEquals("h1", dto.getHstamp());
        assertEquals("h2", dto.getHstamp2());
        assertEquals("h3", dto.getHstamp3());
        assertEquals("h4", dto.getHstamp4());
        assertEquals("h5", dto.getHstamp5());
        assertEquals("logdomp", dto.getLogdomp());
        assertEquals("logtelp", dto.getLogtelp());
        assertEquals("87654321", dto.getNumper());
        assertEquals("57", dto.getPrecel());
        assertEquals("DEV", dto.getProfes());
        assertEquals("1", dto.getSeccel());
        assertEquals("2", dto.getSecdoc());
        assertEquals("3", dto.getSecdomp());
        assertEquals("4", dto.getSecdotc());
        assertEquals("5", dto.getSecdotp());
        assertEquals("6", dto.getSecema());
        assertEquals("7", dto.getSectelp());
        assertEquals("0100", dto.getSucadm());
        assertEquals("0200", dto.getSucmod());
        assertEquals("TERM", dto.getTermod());
        assertEquals("TD", dto.getTipdomp());
        assertEquals("TO", dto.getTipocu());
        assertEquals("TP", dto.getTipper());
        assertEquals("TT", dto.getTiptelp());
        assertEquals("ODSCONS", dto.getUsualt());
        assertEquals("USER", dto.getUsumod());
    }
}
TrxPersonRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonRequestTest {

    @Test
    void shouldSetAndGetAllFields() {
        TrxPersonHeader header = new TrxPersonHeader();
        TrxPersonDataRequest data = new TrxPersonDataRequest();

        TrxPersonRequest request = new TrxPersonRequest();
        request.setCabecera(header);
        request.setData(data);

        assertSame(header, request.getCabecera());
        assertSame(data, request.getData());
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxPersonHeader header = new TrxPersonHeader();
        TrxPersonDataRequest data = new TrxPersonDataRequest();

        TrxPersonRequest request = TrxPersonRequest.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertSame(header, request.getCabecera());
        assertSame(data, request.getData());
    }
}
TrxPersonDataRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonDataRequestTest {

    @Test
    void shouldSetAndGetAllFields() {
        BasicData basicData = new BasicData();
        ComplementaryInfo complementaryInfo = new ComplementaryInfo();
        EconomyActivity economyActivity = new EconomyActivity();
        FinancialInformation financialInformation = new FinancialInformation();
        References references = new References();
        AdditionalInfo additionalInfo = new AdditionalInfo();
        InternationalOperations internationalOperations = new InternationalOperations();
        IdentificationData identificationData = new IdentificationData();

        TrxPersonDataRequest dto = new TrxPersonDataRequest();

        dto.setpENUMPE("12345678");
        dto.setTipoInmueble("CASA");
        dto.setDatosBasicos(basicData);
        dto.setInfComplementaria(complementaryInfo);
        dto.setActividadEconomica(economyActivity);
        dto.setInfFinanciera(financialInformation);
        dto.setReferencias(references);
        dto.setInfAdicional(additionalInfo);
        dto.setOperacionesInternacionales(internationalOperations);
        dto.setDocumentoCajero("DOC");
        dto.setTipoDocumento("CC");
        dto.setNumDocumento("123");
        dto.setNombre("Fabio");
        dto.setDatosIdentificacion(List.of(identificationData));

        assertEquals("12345678", dto.getpENUMPE());
        assertEquals("CASA", dto.getTipoInmueble());
        assertSame(basicData, dto.getDatosBasicos());
        assertSame(complementaryInfo, dto.getInfComplementaria());
        assertSame(economyActivity, dto.getActividadEconomica());
        assertSame(financialInformation, dto.getInfFinanciera());
        assertSame(references, dto.getReferencias());
        assertSame(additionalInfo, dto.getInfAdicional());
        assertSame(internationalOperations, dto.getOperacionesInternacionales());
        assertEquals("DOC", dto.getDocumentoCajero());
        assertEquals("CC", dto.getTipoDocumento());
        assertEquals("123", dto.getNumDocumento());
        assertEquals("Fabio", dto.getNombre());
        assertEquals(1, dto.getDatosIdentificacion().size());
        assertSame(identificationData, dto.getDatosIdentificacion().get(0));
    }
