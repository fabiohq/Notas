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
    dto.setNumdoc("123456789");
    dto.setNumper("456");
    dto.setSecdoc("1");
    dto.setTipdoc("CC");

    assertTrue(dto.getPensiones());
    assertFalse(dto.getPrestacionesServicio());
    assertTrue(dto.getArrendamientos());
    assertFalse(dto.getDonacionHerencia());
    assertTrue(dto.getHonorarios());
    assertFalse(dto.getMesada());
    assertTrue(dto.getActividadIndependiente());
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
    assertEquals("123456789", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

@Test
void shouldCreateWithBuilder() {
    AdditionalInfo dto = AdditionalInfo.builder()
            .pensiones(Boolean.TRUE)
            .prestacionesServicio(Boolean.FALSE)
            .arrendamientos(Boolean.TRUE)
            .donacionHerencia(Boolean.FALSE)
            .honorarios(Boolean.TRUE)
            .mesada(Boolean.FALSE)
            .actividadIndependiente(Boolean.TRUE)
            .paisResidenciaFiscal1("CO")
            .paisDescripcion("Colombia")
            .paisResidenciaFiscal2("US")
            .paisDescripcion2("Estados Unidos")
            .clasificacionFATCA("FATCA")
            .clasificacionCRS("CRS")
            .validacionFATCA(Boolean.TRUE)
            .salario(Boolean.TRUE)
            .otro(Boolean.FALSE)
            .numIdentiTributaria1("NIT1")
            .numIdentiTributaria2("NIT2")
            .preFormalizacion("PRE")
            .validacionCRS(Boolean.TRUE)
            .selfCertificacion(Boolean.FALSE)
            .contribuyenteVentaColombia(Boolean.TRUE)
            .reportable(Boolean.FALSE)
            .autorizoEnvioInformacion(Boolean.TRUE)
            .canalVenta("WEB")
            .oficial("OFI")
            .sucursal("001")
            .uNeg("NEG")
            .sitCliente("ACTIVO")
            .fAltaCliente("2026-05-27")
            .numdoc("123456789")
            .numper("456")
            .secdoc("1")
            .tipdoc("CC")
            .build();

    assertTrue(dto.getPensiones());
    assertFalse(dto.getPrestacionesServicio());
    assertTrue(dto.getArrendamientos());
    assertFalse(dto.getDonacionHerencia());
    assertTrue(dto.getHonorarios());
    assertFalse(dto.getMesada());
    assertTrue(dto.getActividadIndependiente());
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
    assertEquals("123456789", dto.getNumdoc());
    assertEquals("456", dto.getNumper());
    assertEquals("1", dto.getSecdoc());
    assertEquals("CC", dto.getTipdoc());
}

}
