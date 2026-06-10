@Test
void trxBP13ShouldReturnResponseWhenBodyHasNotFoundError() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setCodigo("NO_FOUND");
    error.setMensaje("not found");

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(List.of(error))
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    TrxBP13Response result = service.trxBP13(request);

    assertEquals(response, result);
    assertFalse(result.getOk());
    assertEquals("NO_FOUND", result.getErrores().get(0).getCodigo());
}

@Test
void trxBP13ShouldReturnResponseWhenBodyHasEmptyErrors() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(new ArrayList<>())
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    TrxBP13Response result = service.trxBP13(request);

    assertEquals(response, result);
    assertFalse(result.getOk());
    assertTrue(result.getErrores().isEmpty());
}
