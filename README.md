@Test
void shouldCoverSerializationCatchWhenObjectMapperFails() throws Exception {
    TrxBP17Request request = mock(TrxBP17Request.class);
    TrxBP17Response expected = mock(TrxBP17Response.class);

    Call<TrxBP17Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any()))
            .thenThrow(new RuntimeException("json error"));

    when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(expected));

    TrxBP17Response result = service.trxBP17(request);

    assertSame(expected, result);
}
Java
@Test
void shouldThrowBadRequestWhenBp17ReturnsErrorBody() throws Exception {
    TrxBP17Request request = mock(TrxBP17Request.class);
    Call<TrxBP17Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP17 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP17 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP17 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

    assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
}
Java
@Test
void shouldReturnNullWhenBp31ErrorIsNoExisteCdt() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);

    ErrorDTO error = errorDto("NO EXISTE CDT");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            request,
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("NO EXISTE CDT")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("NO EXISTE CDT"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    TrxBP31Response result = service.trxBP31(request);

    assertNull(result);
}
Java
@Test
void shouldThrowBadRequestWhenBp31ReturnsErrorBody() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP31 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            request,
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP31 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP31 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
}
Java
@Test
void shouldThrowNoContentWhenPepfErrorIsNoExisteCdt() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
    Call<TrxPEPFDataResponse> call = mock(Call.class);

    ErrorDTO error = errorDto("NO EXISTE CDT");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("NO EXISTE CDT")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("NO EXISTE CDT"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertEquals(HttpStatus.NO_CONTENT, ex.getCode());
}
Java
@Test
void shouldThrowBadRequestWhenPepfReturnsErrorBody() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
    Call<TrxPEPFDataResponse> call = mock(Call.class);

    ErrorDTO error = errorDto("PEPF error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("PEPF error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("PEPF error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp13ReturnsErrorBody() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);
    Call<TrxBP13Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP13 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP13TRX(request, "consultaDatosIPF", "consultaDatosIPF", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP13 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP13 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp01ReturnsErrorBody() throws Exception {
    TrxBp01Request request = mock(TrxBp01Request.class);
    Call<TrxBp01Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP01 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP01(request, "BP01", "BP01", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP01 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP01 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP01(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp02ReturnsErrorBody() throws Exception {
    TrxBp02Request request = mock(TrxBp02Request.class);
    Call<TrxBp02Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP02 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP02(request, "BP02", "BP02", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP02 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP02 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP02(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowNotFoundWhenBp49ErrorIsSecuenciaNoExiste() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);
    Call<TrxBP49Response> call = mock(Call.class);

    ErrorDTO error = errorDto("SECUENCIA NO EXISTE");

    ServiceException expected =
            new ServiceException(HttpStatus.NOT_FOUND, error);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("SECUENCIA NO EXISTE")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("SECUENCIA NO EXISTE"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            eq("Deposit not found"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertSame(expected, ex);
}
Java
@Test
void shouldThrowBadRequestWhenBp49ReturnsErrorBody() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);
    Call<TrxBP49Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP49 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP49 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.BAD_REQUEST),
            eq("BP49 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
}
Java
@Test
void shouldThrowNotFoundWhenBp21ReturnsErrorBody() throws Exception {
    TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);
    Call<TrxBP21Response> call = mock(Call.class);

    ErrorDTO error = errorDto("BP21 error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP21TRX(
            request,
            "modificacionDatosIPF",
            "modificacionDatosIPF",
            "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, errorBody("BP21 error")));

    when(errorService.errorBuilder(
            eq(HttpStatus.NOT_FOUND),
            eq("BP21 error"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(error);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP21(request));

    assertEquals(HttpStatus.NOT_FOUND, ex.getCode());
}
Java
@Test
void shouldThrowServiceUnavailableWhenBp31IOException() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            request,
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenThrow(new IOException("network"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getCode());
}
Java
@Test
void shouldThrowServiceUnavailableWhenBp21IOException() throws Exception {
    TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);
    Call<TrxBP21Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP21TRX(
            request,
            "modificacionDatosIPF",
            "modificacionDatosIPF",
            "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenThrow(new IOException("network"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP21(request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getCode());
}