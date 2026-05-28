
// Package de pruebas sugerido: // 

// Package de pruebas sugerido: // src/test/java/com/santander/bnc/bsn049/bncbsn049msprspctcntctpnt/domain/host/person/request/ // // Cada bloque corresponde a una clase de test independiente. // Sin helpers compartidos, sin herencia y sin acoplar pruebas entre clases.

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader; import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalInfoTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    AdditionalInfo dto = new AdditionalInfo();
    dto.setPaisResidenciaFiscal1("CO");
    dto.setPaisDescripcion("Colombia");
    dto.setPaisResidenciaFiscal2("US");
    dto.setPaisDescripcion2("Estados Unidos");
    dto.setClasificacionFATCA("FATCA");
    dto.setClasificacionCRS("CRS");
    dto.setValidacionFATCA(Boolean.TRUE);
    dto.setSalario(Boolean.TRUE);
    dto.setOtro(Boolean.FALSE);
    dto.setNumIdentiTributaria1("NIT1");
    dto.setNumIdentiTributaria2("NIT2");
    dto.setPreFormalizacion("PRE");
    dto.setValidacionCRS(Boolean.TRUE);
    dto.setSelfCertificacion(Boolean.FALSE);
    dto.setContribuyenteVentaColombia(Boolean.TRUE);
    dto.setReportable(Boolean.FALSE);
    dto.setAutorizoEnvioInformacion(Boolean.TRUE);
    dto.setCanalVenta("WEB");
    dto.setOficial("OFI");
    dto.setSucursal("001");
    dto.setuNeg("NEG");
    dto.setSitCliente("ACTIVO");
    dto.setfAltaCliente("2026-05-27");
    dto.setNumdoc("123");
    dto.setNumper("456");
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
    assertFalse(dto.getOtro());
    assertEquals("NIT1", dto.getNumIdentiTributaria1());
    assertEquals("NIT2", dto.getNumIdentiTributaria2());
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
    assertEquals("2026-05-27", dto.getfAltaCliente());
    assertEquals("123", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    AdditionalInfo dto = AdditionalInfo.builder()
            .paisResidenciaFiscal1("CO")
            .paisDescripcion("Colombia")
            .validacionFATCA(Boolean.TRUE)
            .salario(Boolean.TRUE)
            .canalVenta("WEB")
            .uNeg("NEG")
            .tipdoc("CC")
            .build();

    assertEquals("CO", dto.getPaisResidenciaFiscal1());
    assertEquals("Colombia", dto.getPaisDescripcion());
    assertTrue(dto.getValidacionFATCA());
    assertTrue(dto.getSalario());
    assertEquals("WEB", dto.getCanalVenta());
    assertEquals("NEG", dto.getuNeg());
    assertEquals("CC", dto.getTipdoc());
}

}

class BasicDataTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    BasicData dto = new BasicData();
    dto.setTipoIdentificacion("CC");
    dto.setNumeroIdentificacion("123456789");
    dto.setNombre("Fabio");
    dto.setPrimerApellido("Hernandez");
    dto.setSegundoApellido("Perez");
    dto.setPaisExpedicion("CO");
    dto.setCiudadExpedicion("BOG");
    dto.setFechaExpedicion("2020-01-01");
    dto.setPaisNacimiento("CO");
    dto.setNacionalidad("CO");
    dto.setCiudadNacimiento("BOG");
    dto.setFechaNacimiento("2000-01-01");
    dto.setSexo("M");
    dto.setPaisDireccion("CO");
    dto.setDepartamento("11");
    dto.setCiudad("BOG");
    dto.setTipoVia("CL");
    dto.setNombreVia("100");
    dto.setDescripcionDireccion("Calle 100 # 1-2");
    dto.setIndicativo("601");
    dto.setTelefono("1234567");
    dto.setPrecelular("300");
    dto.setCelular("3001234567");
    dto.setEmail("test@santander.com");
    dto.setAutorizoTelefono(true);
    dto.setAutorizacionEmail(true);
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setTipper("P");

    assertEquals("CC", dto.getTipoIdentificacion());
    assertEquals("123456789", dto.getNumeroIdentificacion());
    assertEquals("Fabio", dto.getNombre());
    assertEquals("Hernandez", dto.getPrimerApellido());
    assertEquals("Perez", dto.getSegundoApellido());
    assertEquals("CO", dto.getPaisExpedicion());
    assertEquals("BOG", dto.getCiudadExpedicion());
    assertEquals("2020-01-01", dto.getFechaExpedicion());
    assertEquals("CO", dto.getPaisNacimiento());
    assertEquals("CO", dto.getNacionalidad());
    assertEquals("BOG", dto.getCiudadNacimiento());
    assertEquals("2000-01-01", dto.getFechaNacimiento());
    assertEquals("M", dto.getSexo());
    assertEquals("CO", dto.getPaisDireccion());
    assertEquals("11", dto.getDepartamento());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("CL", dto.getTipoVia());
    assertEquals("100", dto.getNombreVia());
    assertEquals("Calle 100 # 1-2", dto.getDescripcionDireccion());
    assertEquals("601", dto.getIndicativo());
    assertEquals("1234567", dto.getTelefono());
    assertEquals("300", dto.getPrecelular());
    assertEquals("3001234567", dto.getCelular());
    assertEquals("test@santander.com", dto.getEmail());
    assertTrue(dto.isAutorizoTelefono());
    assertTrue(dto.isAutorizacionEmail());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("P", dto.getTipper());
}

@Test
void shouldCreateWithBuilder() {
    BasicData dto = BasicData.builder()
            .tipoIdentificacion("CC")
            .numeroIdentificacion("123456789")
            .nombre("Fabio")
            .primerApellido("Hernandez")
            .email("test@santander.com")
            .autorizoTelefono(true)
            .autorizacionEmail(true)
            .numper("456")
            .build();

    assertEquals("CC", dto.getTipoIdentificacion());
    assertEquals("123456789", dto.getNumeroIdentificacion());
    assertEquals("Fabio", dto.getNombre());
    assertEquals("Hernandez", dto.getPrimerApellido());
    assertEquals("test@santander.com", dto.getEmail());
    assertTrue(dto.isAutorizoTelefono());
    assertTrue(dto.isAutorizacionEmail());
    assertEquals("456", dto.getNumper());
}

}

class ComplementaryInfoTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    ComplementaryInfo dto = new ComplementaryInfo();
    dto.setEstadoCivil("SOLTERO");
    dto.setTipoVivienda("PROPIA");
    dto.setEstrato("3");
    dto.setAnios("2");
    dto.setMes("6");
    dto.setAnios2("1");
    dto.setNivelEstudios("UNIVERSITARIO");
    dto.setPersonasCargo("1");
    dto.setNumHijos("0");
    dto.setHstamp1("H1");
    dto.setHstamp2("H2");
    dto.setLugnac("BOG");
    dto.setNumper2("456");
    dto.setNumintp("789");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");

    assertEquals("SOLTERO", dto.getEstadoCivil());
    assertEquals("PROPIA", dto.getTipoVivienda());
    assertEquals("3", dto.getEstrato());
    assertEquals("2", dto.getAnios());
    assertEquals("6", dto.getMes());
    assertEquals("1", dto.getAnios2());
    assertEquals("UNIVERSITARIO", dto.getNivelEstudios());
    assertEquals("1", dto.getPersonasCargo());
    assertEquals("0", dto.getNumHijos());
    assertEquals("H1", dto.getHstamp1());
    assertEquals("H2", dto.getHstamp2());
    assertEquals("BOG", dto.getLugnac());
    assertEquals("456", dto.getNumper2());
    assertEquals("789", dto.getNumintp());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    ComplementaryInfo dto = ComplementaryInfo.builder()
            .estadoCivil("SOLTERO")
            .tipoVivienda("PROPIA")
            .estrato("3")
            .nivelEstudios("UNIVERSITARIO")
            .tipdoc("CC")
            .build();

    assertEquals("SOLTERO", dto.getEstadoCivil());
    assertEquals("PROPIA", dto.getTipoVivienda());
    assertEquals("3", dto.getEstrato());
    assertEquals("UNIVERSITARIO", dto.getNivelEstudios());
    assertEquals("CC", dto.getTipdoc());
}

}

class EconomyActivityTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    EconomyActivity dto = new EconomyActivity();
    dto.setCargo("DEV");
    dto.setDescCargo("Developer");
    dto.setTipoVia("CL");
    dto.setDepartamento("11");
    dto.setTipoContrato("INDEFINIDO");
    dto.setPais("CO");
    dto.setPaisDescripcion("Colombia");
    dto.setCiudad("BOG");
    dto.setCiudadDescripcion("Bogota");
    dto.setOcupacion("ING");
    dto.setDescOcupacion("Ingeniero");
    dto.setActiEconomica("001");
    dto.setDescActiEconomica("Tecnologia");
    dto.setAntiguedadAnio("3");
    dto.setAntiguedadMes("4");
    dto.setNombreEmpresa("Santander");
    dto.setNit("900123456");
    dto.setFechaIngreso("2020-01-01");
    dto.setNombreVia("100");
    dto.setDescripcionDireccion("Calle 100");
    dto.setIndicativo("601");
    dto.setTelefono("1234567");
    dto.setOpcionActividad("A");
    dto.setNumdoc("123");
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");

    assertEquals("DEV", dto.getCargo());
    assertEquals("Developer", dto.getDescCargo());
    assertEquals("CL", dto.getTipoVia());
    assertEquals("11", dto.getDepartamento());
    assertEquals("INDEFINIDO", dto.getTipoContrato());
    assertEquals("CO", dto.getPais());
    assertEquals("Colombia", dto.getPaisDescripcion());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("Bogota", dto.getCiudadDescripcion());
    assertEquals("ING", dto.getOcupacion());
    assertEquals("Ingeniero", dto.getDescOcupacion());
    assertEquals("001", dto.getActiEconomica());
    assertEquals("Tecnologia", dto.getDescActiEconomica());
    assertEquals("3", dto.getAntiguedadAnio());
    assertEquals("4", dto.getAntiguedadMes());
    assertEquals("Santander", dto.getNombreEmpresa());
    assertEquals("900123456", dto.getNit());
    assertEquals("2020-01-01", dto.getFechaIngreso());
    assertEquals("100", dto.getNombreVia());
    assertEquals("Calle 100", dto.getDescripcionDireccion());
    assertEquals("601", dto.getIndicativo());
    assertEquals("1234567", dto.getTelefono());
    assertEquals("A", dto.getOpcionActividad());
    assertEquals("123", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    EconomyActivity dto = EconomyActivity.builder()
            .cargo("DEV")
            .descCargo("Developer")
            .pais("CO")
            .ciudad("BOG")
            .ocupacion("ING")
            .numdoc("123")
            .tipdoc("CC")
            .build();

    assertEquals("DEV", dto.getCargo());
    assertEquals("Developer", dto.getDescCargo());
    assertEquals("CO", dto.getPais());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("ING", dto.getOcupacion());
    assertEquals("123", dto.getNumdoc());
    assertEquals("CC", dto.getTipdoc());
}

}

class FinancialInformationTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    FinancialInformation dto = new FinancialInformation();
    dto.setIngresosFijos(1000);
    dto.setOtrosIngresos1(200);
    dto.setTotalIngresos(1200);
    dto.setCuotasCreditos(100);
    dto.setOtrosEgresos(50);
    dto.setTotalEgresos(150);
    dto.setBienesRaices(5000);
    dto.setOtrosBienes(800);
    dto.setTotalActivos(5800);
    dto.setSaldoTarjetaCredito(300);
    dto.setSaldoOtrasObligaciones(400);
    dto.setTotalPasivos(700);
    dto.setMatricinmuebles("MAT1");
    dto.setValorComercial("10000");
    dto.setSaldoHipoteca("2000");
    dto.setSaldo("500");
    dto.setNumdoc("123");
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");
    dto.setTipoInmueble("CASA");

    assertEquals(1000, dto.getIngresosFijos());
    assertEquals(200, dto.getOtrosIngresos1());
    assertEquals(1200, dto.getTotalIngresos());
    assertEquals(100, dto.getCuotasCreditos());
    assertEquals(50, dto.getOtrosEgresos());
    assertEquals(150, dto.getTotalEgresos());
    assertEquals(5000, dto.getBienesRaices());
    assertEquals(800, dto.getOtrosBienes());
    assertEquals(5800, dto.getTotalActivos());
    assertEquals(300, dto.getSaldoTarjetaCredito());
    assertEquals(400, dto.getSaldoOtrasObligaciones());
    assertEquals(700, dto.getTotalPasivos());
    assertEquals("MAT1", dto.getMatricinmuebles());
    assertEquals("10000", dto.getValorComercial());
    assertEquals("2000", dto.getSaldoHipoteca());
    assertEquals("500", dto.getSaldo());
    assertEquals("123", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
    assertEquals("CASA", dto.getTipoInmueble());
}

@Test
void shouldCreateWithBuilder() {
    FinancialInformation dto = FinancialInformation.builder()
            .ingresosFijos(1000)
            .totalIngresos(1200)
            .totalEgresos(150)
            .totalActivos(5800)
            .totalPasivos(700)
            .numdoc("123")
            .tipdoc("CC")
            .build();

    assertEquals(1000, dto.getIngresosFijos());
    assertEquals(1200, dto.getTotalIngresos());
    assertEquals(150, dto.getTotalEgresos());
    assertEquals(5800, dto.getTotalActivos());
    assertEquals(700, dto.getTotalPasivos());
    assertEquals("123", dto.getNumdoc());
    assertEquals("CC", dto.getTipdoc());
}

}

class IdentificationDataTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    IdentificationData dto = new IdentificationData();
    dto.setNumeroDocumento("123456789");
    dto.setNumPersona("456");
    dto.setApellidos("Hernandez Perez");
    dto.setEstado("ACTIVO");
    dto.setTipoPersona("N");
    dto.setpECODPR("CODPR");
    dto.setpEESTPE("ESTPE");
    dto.setpEFECNA("2000-01-01");
    dto.setpEHSTAM("HSTAM");
    dto.setpEINDAV("S");
    dto.setpEINDCO("N");
    dto.setpEINDGR("S");
    dto.setpEINDN3("N3");
    dto.setpEINDN4("N4");
    dto.setpEINDN5("N5");
    dto.setpEINDRE("RE");
    dto.setpEMARNO("MARNO");
    dto.setpENOMCA("NOMCA");
    dto.setpENOMLO("NOMLO");
    dto.setpEOBSE1("OBSE1");
    dto.setpEPRIAP("PRIAP");
    dto.setpERUTCA("RUTCA");
    dto.setpESECDO("SECDO");
    dto.setpESEGAP("SEGAP");
    dto.setpETIPDO("CC");
    dto.setpETIPVI("TIPVI");

    assertEquals("123456789", dto.getNumeroDocumento());
    assertEquals("456", dto.getNumPersona());
    assertEquals("Hernandez Perez", dto.getApellidos());
    assertEquals("ACTIVO", dto.getEstado());
    assertEquals("N", dto.getTipoPersona());
    assertEquals("CODPR", dto.getpECODPR());
    assertEquals("ESTPE", dto.getpEESTPE());
    assertEquals("2000-01-01", dto.getpEFECNA());
    assertEquals("HSTAM", dto.getpEHSTAM());
    assertEquals("S", dto.getpEINDAV());
    assertEquals("N", dto.getpEINDCO());
    assertEquals("S", dto.getpEINDGR());
    assertEquals("N3", dto.getpEINDN3());
    assertEquals("N4", dto.getpEINDN4());
    assertEquals("N5", dto.getpEINDN5());
    assertEquals("RE", dto.getpEINDRE());
    assertEquals("MARNO", dto.getpEMARNO());
    assertEquals("NOMCA", dto.getpENOMCA());
    assertEquals("NOMLO", dto.getpENOMLO());
    assertEquals("OBSE1", dto.getpEOBSE1());
    assertEquals("PRIAP", dto.getpEPRIAP());
    assertEquals("RUTCA", dto.getpERUTCA());
    assertEquals("SECDO", dto.getpESECDO());
    assertEquals("SEGAP", dto.getpESEGAP());
    assertEquals("CC", dto.getpETIPDO());
    assertEquals("TIPVI", dto.getpETIPVI());
}

@Test
void shouldCreateWithBuilder() {
    IdentificationData dto = IdentificationData.builder()
            .numeroDocumento("123456789")
            .numPersona("456")
            .apellidos("Hernandez Perez")
            .estado("ACTIVO")
            .tipoPersona("N")
            .pETIPDO("CC")
            .build();

    assertEquals("123456789", dto.getNumeroDocumento());
    assertEquals("456", dto.getNumPersona());
    assertEquals("Hernandez Perez", dto.getApellidos());
    assertEquals("ACTIVO", dto.getEstado());
    assertEquals("N", dto.getTipoPersona());
    assertEquals("CC", dto.getpETIPDO());
}

}

class InternationalOperationsTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    InternationalOperations dto = new InternationalOperations();
    dto.setRealizaOperaMoneExtran("S");
    dto.setInversiones(Boolean.TRUE);
    dto.setGiros(Boolean.FALSE);
    dto.setCreditos(Boolean.TRUE);
    dto.setImportaciones(Boolean.FALSE);
    dto.setExportaciones(Boolean.TRUE);
    dto.setOtro(Boolean.FALSE);
    dto.setTieneProdMoneExtraje("N");
    dto.setNumdoc("123");
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");

    assertEquals("S", dto.getRealizaOperaMoneExtran());
    assertTrue(dto.getInversiones());
    assertFalse(dto.getGiros());
    assertTrue(dto.getCreditos());
    assertFalse(dto.getImportaciones());
    assertTrue(dto.getExportaciones());
    assertFalse(dto.getOtro());
    assertEquals("N", dto.getTieneProdMoneExtraje());
    assertEquals("123", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    InternationalOperations dto = InternationalOperations.builder()
            .realizaOperaMoneExtran("S")
            .inversiones(Boolean.TRUE)
            .giros(Boolean.FALSE)
            .numdoc("123")
            .tipdoc("CC")
            .build();

    assertEquals("S", dto.getRealizaOperaMoneExtran());
    assertTrue(dto.getInversiones());
    assertFalse(dto.getGiros());
    assertEquals("123", dto.getNumdoc());
    assertEquals("CC", dto.getTipdoc());
}

}

class ReferencesTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    References dto = new References();
    dto.setParentesco("PADRE");
    dto.setNombre("Juan");
    dto.setPrimerApellido("Perez");
    dto.setCiudad("BOG");
    dto.setCiudadReferencia("Bogota");
    dto.setIndictivo("601");
    dto.setTelefono("1234567");
    dto.setDireccion("Calle 1");
    dto.setNumdoc("123");
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setSecref1("REF1");
    dto.setTipdoc("CC");

    assertEquals("PADRE", dto.getParentesco());
    assertEquals("Juan", dto.getNombre());
    assertEquals("Perez", dto.getPrimerApellido());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("Bogota", dto.getCiudadReferencia());
    assertEquals("601", dto.getIndictivo());
    assertEquals("1234567", dto.getTelefono());
    assertEquals("Calle 1", dto.getDireccion());
    assertEquals("123", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("REF1", dto.getSecref1());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    References dto = References.builder()
            .parentesco("PADRE")
            .nombre("Juan")
            .primerApellido("Perez")
            .telefono("1234567")
            .tipdoc("CC")
            .build();

    assertEquals("PADRE", dto.getParentesco());
    assertEquals("Juan", dto.getNombre());
    assertEquals("Perez", dto.getPrimerApellido());
    assertEquals("1234567", dto.getTelefono());
    assertE
