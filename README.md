trxBP13ShouldThrowWhenApiReturnsResponseWithErrors
trxBP49ShouldThrowWhenApiReturnsResponseWithErrors

@Test
void trxBP13ShouldReturnResponseWhenApiReturnsErrorsInBody() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(List.of(new ErrorTrxDTO()))
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    TrxBP13Response result = service.trxBP13(request);

    assertEquals(response, result);
    assertFalse(result.getOk());
    assertFalse(result.getErrores().isEmpty());
}

@Test
void trxBP49ShouldReturnResponseWhenApiReturnsErrorsInBody() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    TrxBP49Response response = TrxBP49Response.builder()
            .ok(false)
            .errores(List.of(new ErrorTrxDTO()))
            .build();

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    TrxBP49Response result = service.trxBP49(request);

    assertEquals(response, result);
    assertFalse(result.getOk());
    assertFalse(result.getErrores().isEmpty());
}
