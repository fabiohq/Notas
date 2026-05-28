package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    dto.setMatricinmuebles2("MAT2");
    dto.setMatricinmuebles3("MAT3");
    dto.setValorComercial("10000");
    dto.setValorComercial2("20000");
    dto.setValorComercial3("30000");
    dto.setSaldoHipoteca("1000");
    dto.setSaldoHipoteca2("2000");
    dto.setSaldoHipoteca3("3000");
    dto.setSaldo("500");
    dto.setSaldo2("600");
    dto.setNumdoc("123456789");
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
    assertEquals("MAT2", dto.getMatricinmuebles2());
    assertEquals("MAT3", dto.getMatricinmuebles3());
    assertEquals("10000", dto.getValorComercial());
    assertEquals("20000", dto.getValorComercial2());
    assertEquals("30000", dto.getValorComercial3());
    assertEquals("1000", dto.getSaldoHipoteca());
    assertEquals("2000", dto.getSaldoHipoteca2());
    assertEquals("3000", dto.getSaldoHipoteca3());
    assertEquals("500", dto.getSaldo());
    assertEquals("600", dto.getSaldo2());
    assertEquals("123456789", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
    assertEquals("CASA", dto.getTipoInmueble());
}

@Test
void shouldCreateWithBuilder() {
    FinancialInformation dto = FinancialInformation.builder()
            .ingresosFijos(1000)
            .otrosIngresos1(200)
            .totalIngresos(1200)
            .cuotasCreditos(100)
            .otrosEgresos(50)
            .totalEgresos(150)
            .bienesRaices(5000)
            .otrosBienes(800)
            .totalActivos(5800)
            .saldoTarjetaCredito(300)
            .saldoOtrasObligaciones(400)
            .totalPasivos(700)
            .matricinmuebles("MAT1")
            .matricinmuebles2("MAT2")
            .matricinmuebles3("MAT3")
            .valorComercial("10000")
            .valorComercial2("20000")
            .valorComercial3("30000")
            .saldoHipoteca("1000")
            .saldoHipoteca2("2000")
            .saldoHipoteca3("3000")
            .saldo("500")
            .saldo2("600")
            .numdoc("123456789")
            .numper("456")
            .secdoc("1")
            .tipdoc("CC")
            .tipoInmueble("CASA")
            .build();

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
    assertEquals("MAT2", dto.getMatricinmuebles2());
    assertEquals("MAT3", dto.getMatricinmuebles3());
    assertEquals("10000", dto.getValorComercial());
    assertEquals("20000", dto.getValorComercial2());
    assertEquals("30000", dto.getValorComercial3());
    assertEquals("1000", dto.getSaldoHipoteca());
    assertEquals("2000", dto.getSaldoHipoteca2());
    assertEquals("3000", dto.getSaldoHipoteca3());
    assertEquals("500", dto.getSaldo());
    assertEquals("600", dto.getSaldo2());
    assertEquals("123456789", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
    assertEquals("CASA", dto.getTipoInmueble());
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
    dto.setHstamp1("HSTAMP1");
    dto.setHstamp2("HSTAMP2");
    dto.setLugnac("BOG");
    dto.setNumper2("456");
    dto.setNumintp("789");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");
    dto.setSeccel("2");
    dto.setSecdotc("3");
    dto.setSecdotp("4");
    dto.setSecema("5");
    dto.setSectelp("6");

    assertEquals("SOLTERO", dto.getEstadoCivil());
    assertEquals("PROPIA", dto.getTipoVivienda());
    assertEquals("3", dto.getEstrato());
    assertEquals("2", dto.getAnios());
    assertEquals("6", dto.getMes());
    assertEquals("1", dto.getAnios2());
    assertEquals("UNIVERSITARIO", dto.getNivelEstudios());
    assertEquals("1", dto.getPersonasCargo());
    assertEquals("0", dto.getNumHijos());
    assertEquals("HSTAMP1", dto.getHstamp1());
    assertEquals("HSTAMP2", dto.getHstamp2());
    assertEquals("BOG", dto.getLugnac());
    assertEquals("456", dto.getNumper2());
    assertEquals("789", dto.getNumintp());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
    assertEquals("2", dto.getSeccel());
    assertEquals("3", dto.getSecdotc());
    assertEquals("4", dto.getSecdotp());
    assertEquals("5", dto.getSecema());
    assertEquals("6", dto.getSectelp());
}

@Test
void shouldCreateWithBuilder() {
    ComplementaryInfo dto = ComplementaryInfo.builder()
            .estadoCivil("SOLTERO")
            .tipoVivienda("PROPIA")
            .estrato("3")
            .anios("2")
            .mes("6")
            .anios2("1")
            .nivelEstudios("UNIVERSITARIO")
            .personasCargo("1")
            .numHijos("0")
            .hstamp1("HSTAMP1")
            .hstamp2("HSTAMP2")
            .lugnac("BOG")
            .numper2("456")
            .numintp("789")
            .secdoc("1")
            .tipdoc("CC")
            .seccel("2")
            .secdotc("3")
            .secdotp("4")
            .secema("5")
            .sectelp("6")
            .build();

    assertEquals("SOLTERO", dto.getEstadoCivil());
    assertEquals("PROPIA", dto.getTipoVivienda());
    assertEquals("3", dto.getEstrato());
    assertEquals("2", dto.getAnios());
    assertEquals("6", dto.getMes());
    assertEquals("1", dto.getAnios2());
    assertEquals("UNIVERSITARIO", dto.getNivelEstudios());
    assertEquals("1", dto.getPersonasCargo());
    assertEquals("0", dto.getNumHijos());
    assertEquals("HSTAMP1", dto.getHstamp1());
    assertEquals("HSTAMP2", dto.getHstamp2());
    assertEquals("BOG", dto.getLugnac());
    assertEquals("456", dto.getNumper2());
    assertEquals("789", dto.getNumintp());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
    assertEquals("2", dto.getSeccel());
    assertEquals("3", dto.getSecdotc());
    assertEquals("4", dto.getSecdotp());
    assertEquals("5", dto.getSecema());
    assertEquals("6", dto.getSectelp());
}

}
