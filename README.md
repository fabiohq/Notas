@Test
void shouldThrowConflictWhenBp17ExecuteThrowsGenericException() throws Exception {
    TrxBP17Request request = mock(TrxBP17Request.class);
    Call<TrxBP17Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD")).thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp31ExecuteThrowsGenericException() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            eq(request),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("QCTFD")))
            .thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenPepfExecuteThrowsGenericException() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
    Call<TrxPEPFDataResponse> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD")).thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp13ExecuteThrowsGenericException() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);
    Call<TrxBP13Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP13TRX(request, "consultaDatosIPF", "consultaDatosIPF", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp01ExecuteThrowsGenericException() throws Exception {
    TrxBp01Request request = mock(TrxBp01Request.class);
    Call<TrxBp01Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP01(request, "BP01", "BP01", "QCTFD")).thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP01(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp02ExecuteThrowsGenericException() throws Exception {
    TrxBp02Request request = mock(TrxBp02Request.class);
    Call<TrxBp02Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP02(request, "BP02", "BP02", "QCTFD")).thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP02(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp49ExecuteThrowsGenericException() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);
    Call<TrxBP49Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "QCTFD")).thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Java
@Test
void shouldThrowConflictWhenBp21ExecuteThrowsGenericException() throws Exception {
    TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);
    Call<TrxBP21Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP21TRX(
            eq(request),
            eq("modificacionDatosIPF"),
            eq("modificacionDatosIPF"),
            eq("QCTFD")))
            .thenReturn(call);
    when(call.execute()).thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP21(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}
Y para cubrir los catch de parseErrorResponse... con JSON inválido:
Java
@Test
void shouldThrowConflictWhenBp17ErrorBodyIsInvalidJson() throws Exception {
    TrxBP17Request request = mock(TrxBP17Request.class);
    Call<TrxBP17Response> call = mock(Call.class);
    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, errorDto("json"));

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));
    when(errorService.serviceExceptionBuilder(eq(HttpStatus.CONFLICT), anyString(), eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

    assertSame(expected, ex);
}
Java
@Test
void shouldThrowConflictWhenBp31ErrorBodyIsInvalidJson() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);
    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, errorDto("json"));

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            eq(request),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("SBCDTTI01-ConsultaCDTDATTitular2654"),
            eq("QCTFD")))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));
    when(errorService.serviceExceptionBuilder(eq(HttpStatus.CONFLICT), anyString(), eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertSame(expected, ex);
}
Java
@Test
void shouldThrowConflictWhenPepfErrorBodyIsInvalidJson() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
    Call<TrxPEPFDataResponse> call = mock(Call.class);
    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, errorDto("json"));

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));
    when(errorService.serviceExceptionBuilder(eq(HttpStatus.CONFLICT), anyString(), eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertSame(expected, ex);
}
Helper faltante:
Java
private ResponseBody invalidJsonBody() {
    return ResponseBody.create(
            MediaType.parse("application/json"),
            "{invalid-json"
    );
}