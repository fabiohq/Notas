@Test
void shouldThrowConflictWhenBp31CallThrowsSQLException() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP31TRX(
            eq(request),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenPepfCallThrowsSQLException() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callPEPF(
            eq(request),
            eq("modificarMantencionPersonaNaturalInfAdicional"),
            eq("modificarMantencionPersonaNaturalInfAdicional"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp13CallThrowsSQLException() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP13TRX(
            eq(request),
            eq("consultaDatosIPF"),
            eq("consultaDatosIPF"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp01CallThrowsSQLException() throws Exception {
    TrxBp01Request request = mock(TrxBp01Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP01(
            eq(request),
            eq("altaCuentaPlazoODS"),
            eq("altaCuentaPlazoODS"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP01(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp02CallThrowsSQLException() throws Exception {
    TrxBp02Request request = mock(TrxBp02Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP02(
            eq(request),
            eq("altaIpfOdsCtaExterna"),
            eq("altaIpfOdsCtaExterna"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP02(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp49CallThrowsSQLException() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP49(
            eq(request),
            eq("SBCCG001ConsultaDetalladaMovimientos2652"),
            eq("SBCCG001ConsultaDetalladaMovimientos2652"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}

@Test
void shouldThrowConflictWhenBp21CallThrowsSQLException() throws Exception {
    TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP21TRX(
            eq(request),
            eq("modificacionDatosIPF"),
            eq("modificacionDatosIPF"),
            eq("QCTFD")
    )).thenThrow(new SQLException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP21(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}