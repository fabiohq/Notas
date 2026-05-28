package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    dto.setPaisExpedicionDesc("Colombia");
    dto.setCiudadExpedicion("BOG");
    dto.setLugardeExpDescripcion("Bogota D.C.");
    dto.setFechaExpedicion("2020-01-01");
    dto.setPaisNacimiento("CO");
    dto.setPaisNacimientoDesc("Colombia");
    dto.setNacionalidad("CO");
    dto.setNacionalidadDesc("Colombiana");
    dto.setCiudadNacimiento("BOG");
    dto.setLugardeNacimiento("Bogota D.C.");
    dto.setFechaNacimiento("2000-01-01");
    dto.setSexo("M");
    dto.setPaisDireccion("CO");
    dto.setPaisDireccionDesc("Colombia");
    dto.setDepartamento("11");
    dto.setCiudad("BOG");
    dto.setCiudadDescripcion("Bogota");
    dto.setTipoVia("CL");
    dto.setNombreVia("100");
    dto.setDescripcionDireccion("Calle 100 # 1-2");
    dto.setClase("004");
    dto.setIndicativo("601");
    dto.setTelefono("1234567");
    dto.setPrecelular("300");
    dto.setCelular("3001234567");
    dto.setEmail("test@santander.com");
    dto.setAutorizoTelefono(true);
    dto.setAutorizacionEmail(true);
    dto.setAgrofic("0006");
    dto.setCodact("00000010");
    dto.setCodpaip("CO");
    dto.setConper("CONPER");
    dto.setDomant("DOMANT");
    dto.setEntpre("ENTPRE");
    dto.setEstciv("S");
    dto.setEstper("ACTIVO");
    dto.setEstrat("3");
    dto.setFecalt("2024-01-01");
    dto.setFecfal("9999-12-31");
    dto.setFecing("2023-01-01");
    dto.setHstamp("HSTAMP");
    dto.setHstamp2("HSTAMP2");
    dto.setHstamp3("HSTAMP3");
    dto.setHstamp4("HSTAMP4");
    dto.setHstamp5("HSTAMP5");
    dto.setLogdomp("LOGDOMP");
    dto.setLogtelp("LOGTELP");
    dto.setNumper("456");
    dto.setPrecel("300");
    dto.setProfes("PROFES");
    dto.setSeccel("1");
    dto.setSecdoc("2");
    dto.setSecdomp("3");
    dto.setSecdotc("4");
    dto.setSecdotp("5");
    dto.setSecema("6");
    dto.setSectelp("7");
    dto.setSucadm("0100");
    dto.setSucmod("0200");
    dto.setTermod("TERMOD");
    dto.setTipdomp("PRI");
    dto.setTipocu("TIPOCU");
    dto.setTipper("P");
    dto.setTiptelp("001");
    dto.setUsualt("USUALT");
    dto.setUsumod("USUMOD");

    assertEquals("CC", dto.getTipoIdentificacion());
    assertEquals("123456789", dto.getNumeroIdentificacion());
    assertEquals("Fabio", dto.getNombre());
    assertEquals("Hernandez", dto.getPrimerApellido());
    assertEquals("Perez", dto.getSegundoApellido());
    assertEquals("CO", dto.getPaisExpedicion());
    assertEquals("Colombia", dto.getPaisExpedicionDesc());
    assertEquals("BOG", dto.getCiudadExpedicion());
    assertEquals("Bogota D.C.", dto.getLugardeExpDescripcion());
    assertEquals("2020-01-01", dto.getFechaExpedicion());
    assertEquals("CO", dto.getPaisNacimiento());
    assertEquals("Colombia", dto.getPaisNacimientoDesc());
    assertEquals("CO", dto.getNacionalidad());
    assertEquals("Colombiana", dto.getNacionalidadDesc());
    assertEquals("BOG", dto.getCiudadNacimiento());
    assertEquals("Bogota D.C.", dto.getLugardeNacimiento());
    assertEquals("2000-01-01", dto.getFechaNacimiento());
    assertEquals("M", dto.getSexo());
    assertEquals("CO", dto.getPaisDireccion());
    assertEquals("Colombia", dto.getPaisDireccionDesc());
    assertEquals("11", dto.getDepartamento());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("Bogota", dto.getCiudadDescripcion());
    assertEquals("CL", dto.getTipoVia());
    assertEquals("100", dto.getNombreVia());
    assertEquals("Calle 100 # 1-2", dto.getDescripcionDireccion());
    assertEquals("004", dto.getClase());
    assertEquals("601", dto.getIndicativo());
    assertEquals("1234567", dto.getTelefono());
    assertEquals("300", dto.getPrecelular());
    assertEquals("3001234567", dto.getCelular());
    assertEquals("test@santander.com", dto.getEmail());
    assertTrue(dto.isAutorizoTelefono());
    assertTrue(dto.isAutorizacionEmail());
    assertEquals("0006", dto.getAgrofic());
    assertEquals("00000010", dto.getCodact());
    assertEquals("CO", dto.getCodpaip());
    assertEquals("CONPER", dto.getConper());
    assertEquals("DOMANT", dto.getDomant());
    assertEquals("ENTPRE", dto.getEntpre());
    assertEquals("S", dto.getEstciv());
    assertEquals("ACTIVO", dto.getEstper());
    assertEquals("3", dto.getEstrat());
    assertEquals("2024-01-01", dto.getFecalt());
    assertEquals("9999-12-31", dto.getFecfal());
    assertEquals("2023-01-01", dto.getFecing());
    assertEquals("HSTAMP", dto.getHstamp());
    assertEquals("HSTAMP2", dto.getHstamp2());
    assertEquals("HSTAMP3", dto.getHstamp3());
    assertEquals("HSTAMP4", dto.getHstamp4());
    assertEquals("HSTAMP5", dto.getHstamp5());
    assertEquals("LOGDOMP", dto.getLogdomp());
    assertEquals("LOGTELP", dto.getLogtelp());
    assertEquals("456", dto.getNumper());
    assertEquals("300", dto.getPrecel());
    assertEquals("PROFES", dto.getProfes());
    assertEquals("1", dto.getSeccel());
    assertEquals("2", dto.getSecdoc());
    assertEquals("3", dto.getSecdomp());
    assertEquals("4", dto.getSecdotc());
    assertEquals("5", dto.getSecdotp());
    assertEquals("6", dto.getSecema());
    assertEquals("7", dto.getSectelp());
    assertEquals("0100", dto.getSucadm());
    assertEquals("0200", dto.getSucmod());
    assertEquals("TERMOD", dto.getTermod());
    assertEquals("PRI", dto.getTipdomp());
    assertEquals("TIPOCU", dto.getTipocu());
    assertEquals("P", dto.getTipper());
    assertEquals("001", dto.getTiptelp());
    assertEquals("USUALT", dto.getUsualt());
    assertEquals("USUMOD", dto.getUsumod());
}

@Test
void shouldCreateWithBuilder() {
    BasicData dto = BasicData.builder()
            .tipoIdentificacion("CC")
            .numeroIdentificacion("123456789")
            .nombre("Fabio")
            .primerApellido("Hernandez")
            .segundoApellido("Perez")
            .paisExpedicion("CO")
            .paisExpedicionDesc("Colombia")
            .ciudadExpedicion("BOG")
            .lugardeExpDescripcion("Bogota D.C.")
            .fechaExpedicion("2020-01-01")
            .paisNacimiento("CO")
            .paisNacimientoDesc("Colombia")
            .nacionalidad("CO")
            .nacionalidadDesc("Colombiana")
            .ciudadNacimiento("BOG")
            .lugardeNacimiento("Bogota D.C.")
            .fechaNacimiento("2000-01-01")
            .sexo("M")
            .paisDireccion("CO")
            .paisDireccionDesc("Colombia")
            .departamento("11")
            .ciudad("BOG")
            .ciudadDescripcion("Bogota")
            .tipoVia("CL")
            .nombreVia("100")
            .descripcionDireccion("Calle 100 # 1-2")
            .clase("004")
            .indicativo("601")
            .telefono("1234567")
            .precelular("300")
            .celular("3001234567")
            .email("test@santander.com")
            .autorizoTelefono(true)
            .autorizacionEmail(true)
            .agrofic("0006")
            .codact("00000010")
            .codpaip("CO")
            .conper("CONPER")
            .domant("DOMANT")
            .entpre("ENTPRE")
            .estciv("S")
            .estper("ACTIVO")
            .estrat("3")
            .fecalt("2024-01-01")
            .fecfal("9999-12-31")
            .fecing("2023-01-01")
            .hstamp("HSTAMP")
            .hstamp2("HSTAMP2")
            .hstamp3("HSTAMP3")
            .hstamp4("HSTAMP4")
            .hstamp5("HSTAMP5")
            .logdomp("LOGDOMP")
            .logtelp("LOGTELP")
            .numper("456")
            .precel("300")
            .profes("PROFES")
            .seccel("1")
            .secdoc("2")
            .secdomp("3")
            .secdotc("4")
            .secdotp("5")
            .secema("6")
            .sectelp("7")
            .sucadm("0100")
            .sucmod("0200")
            .termod("TERMOD")
            .tipdomp("PRI")
            .tipocu("TIPOCU")
            .tipper("P")
            .tiptelp("001")
            .usualt("USUALT")
            .usumod("USUMOD")
            .build();

    assertEquals("CC", dto.getTipoIdentificacion());
    assertEquals("123456789", dto.getNumeroIdentificacion());
    assertEquals("Fabio", dto.getNombre());
    assertEquals("Hernandez", dto.getPrimerApellido());
    assertEquals("Perez", dto.getSegundoApellido());
    assertEquals("CO", dto.getPaisExpedicion());
    assertEquals("Colombia", dto.getPaisExpedicionDesc());
    assertEquals("BOG", dto.getCiudadExpedicion());
    assertEquals("Bogota D.C.", dto.getLugardeExpDescripcion());
    assertEquals("2020-01-01", dto.getFechaExpedicion());
    assertEquals("CO", dto.getPaisNacimiento());
    assertEquals("Colombia", dto.getPaisNacimientoDesc());
    assertEquals("CO", dto.getNacionalidad());
    assertEquals("Colombiana", dto.getNacionalidadDesc());
    assertEquals("BOG", dto.getCiudadNacimiento());
    assertEquals("Bogota D.C.", dto.getLugardeNacimiento());
    assertEquals("2000-01-01", dto.getFechaNacimiento());
    assertEquals("M", dto.getSexo());
    assertEquals("CO", dto.getPaisDireccion());
    assertEquals("Colombia", dto.getPaisDireccionDesc());
    assertEquals("11", dto.getDepartamento());
    assertEquals("BOG", dto.getCiudad());
    assertEquals("Bogota", dto.getCiudadDescripcion());
    assertEquals("CL", dto.getTipoVia());
    assertEquals("100", dto.getNombreVia());
    assertEquals("Calle 100 # 1-2", dto.getDescripcionDireccion());
    assertEquals("004", dto.getClase());
    assertEquals("601", dto.getIndicativo());
    assertEquals("1234567", dto.getTelefono());
    assertEquals("300", dto.getPrecelular());
    assertEquals("3001234567", dto.getCelular());
    assertEquals("test@santander.com", dto.getEmail());
    assertTrue(dto.isAutorizoTelefono());
    assertTrue(dto.isAutorizacionEmail());
    assertEquals("0006", dto.getAgrofic());
    assertEquals("00000010", dto.getCodact());
    assertEquals("CO", dto.getCodpaip());
    assertEquals("CONPER", dto.getConper());
    assertEquals("DOMANT", dto.getDomant());
    assertEquals("ENTPRE", dto.getEntpre());
    assertEquals("S", dto.getEstciv());
    assertEquals("ACTIVO", dto.getEstper());
    assertEquals("3", dto.getEstrat());
    assertEquals("2024-01-01", dto.getFecalt());
    assertEquals("9999-12-31", dto.getFecfal());
    assertEquals("2023-01-01", dto.getFecing());
    assertEquals("HSTAMP", dto.getHstamp());
    assertEquals("HSTAMP2", dto.getHstamp2());
    assertEquals("HSTAMP3", dto.getHstamp3());
    assertEquals("HSTAMP4", dto.getHstamp4());
    assertEquals("HSTAMP5", dto.getHstamp5());
    assertEquals("LOGDOMP", dto.getLogdomp());
    assertEquals("LOGTELP", dto.getLogtelp());
    assertEquals("456", dto.getNumper());
    assertEquals("300", dto.getPrecel());
    assertEquals("PROFES", dto.getProfes());
    assertEquals("1", dto.getSeccel());
    assertEquals("2", dto.getSecdoc());
    assertEquals("3", dto.getSecdomp());
    assertEquals("4", dto.getSecdotc());
    assertEquals("5", dto.getSecdotp());
    assertEquals("6", dto.getSecema());
    assertEquals("7", dto.getSectelp());
    assertEquals("0100", dto.getSucadm());
    assertEquals("0200", dto.getSucmod());
    assertEquals("TERMOD", dto.getTermod());
    assertEquals("PRI", dto.getTipdomp());
    assertEquals("TIPOCU", dto.getTipocu());
    assertEquals("P", dto.getTipper());
    assertEquals("001", dto.getTiptelp());
    assertEquals("USUALT", dto.getUsualt());
    assertEquals("USUMOD", dto.getUsumod());
}

}xxx
