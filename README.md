@Test
void shouldThrowConflictWhenBp31CallThrowsError() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP31TRX(eq(request), eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
                    eq("SBCDTTI01-ConsultaCDTDATTitular2654"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenPepfCallThrowsError() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callPEPF(eq(request), eq("modificarMantencionPersonaNaturalInfAdicional"),
                    eq("modificarMantencionPersonaNaturalInfAdicional"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp13CallThrowsError() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP13TRX(eq(request), eq("consultaDatosIPF"),
                    eq("consultaDatosIPF"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp01CallThrowsError() throws Exception {
    TrxBp01Request request = mock(TrxBp01Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP01(eq(request), eq("altaCuentaPlazoODS"),
                    eq("altaCuentaPlazoODS"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP01(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp02CallThrowsError() throws Exception {
    TrxBp02Request request = mock(TrxBp02Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP02(eq(request), eq("altaIpfOdsCtaExterna"),
                    eq("altaIpfOdsCtaExterna"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP02(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp49CallThrowsError() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP49(eq(request), eq("SBCCG001ConsultaDetalladaMovimientos2652"),
                    eq("SBCCG001ConsultaDetalladaMovimientos2652"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp21CallThrowsError() throws Exception {
    TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    doThrow(new Error("generic error"))
            .when(trxSanbaAPI)
            .callBP21TRX(eq(request), eq("modificacionDatosIPF"),
                    eq("modificacionDatosIPF"), eq("QCTFD"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP21(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}