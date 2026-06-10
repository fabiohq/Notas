@Test
void trxBP13ShouldThrowWhenResponseBodyIsNull() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(null));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP49ShouldThrowWhenResponseBodyIsNull() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(null));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP13ShouldThrowWhenHttpResponseIsNotSuccessful() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    okhttp3.ResponseBody errorBody =
            okhttp3.ResponseBody.create(
                    "{\"errores\":[{\"codigo\":\"404\",\"descripcion\":\"not found\"}]}",
                    okhttp3.MediaType.parse("application/json")
            );

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(404, errorBody));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP49ShouldThrowWhenHttpResponseIsNotSuccessful() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    okhttp3.ResponseBody errorBody =
            okhttp3.ResponseBody.create(
                    "{\"errores\":[{\"codigo\":\"500\",\"descripcion\":\"error\"}]}",
                    okhttp3.MediaType.parse("application/json")
            );

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(500, errorBody));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP13ShouldThrowWhenApiReturnsResponseWithErrors() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(List.of(new ErrorTrxDTO()))
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP49ShouldThrowWhenApiReturnsResponseWithErrors() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    TrxBP49Response response = TrxBP49Response.builder()
            .ok(false)
            .errores(List.of(new ErrorTrxDTO()))
            .build();

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP13ShouldThrowWhenUnexpectedExceptionOccurs() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenThrow(new NullPointerException("unexpected"));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP49ShouldThrowWhenUnexpectedExceptionOccurs() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenThrow(new NullPointerException("unexpected"));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}
