@Test
void trxBP13ShouldThrowServiceExceptionWhenBodyHasNotFoundError() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setCodigo("NO_FOUND");
    error.setDescripcion("not found");

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(List.of(error))
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP13ShouldThrowServiceExceptionWhenBodyHasNormalError() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setCodigo("ERROR");
    error.setDescripcion("technical error");

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(List.of(error))
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP13ShouldThrowServiceExceptionWhenBodyHasEmptyErrors() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    TrxBP13Response response = TrxBP13Response.builder()
            .ok(false)
            .errores(new ArrayList<>())
            .build();

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(response));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP13ShouldThrowServiceExceptionWhenHttpErrorBodyIsValidJson() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ResponseBody errorBody = ResponseBody.create(
            "{"
                    + "\"errores\":["
                    + "{"
                    + "\"codigo\":\"ERROR\","
                    + "\"descripcion\":\"technical error\""
                    + "}"
                    + "]"
                    + "}",
            MediaType.parse("application/json")
    );

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(500, errorBody));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP13ShouldThrowServiceExceptionWhenHttpErrorBodyHasNotFoundJson() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ResponseBody errorBody = ResponseBody.create(
            "{"
                    + "\"errores\":["
                    + "{"
                    + "\"codigo\":\"NO_FOUND\","
                    + "\"descripcion\":\"not found\""
                    + "}"
                    + "]"
                    + "}",
            MediaType.parse("application/json")
    );

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(404, errorBody));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP13ShouldThrowServiceExceptionWhenHttpErrorBodyIsInvalidJson() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    ResponseBody errorBody = ResponseBody.create(
            "invalid-json",
            MediaType.parse("application/json")
    );

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(500, errorBody));

    assertThrows(Exception.class, () -> service.trxBP13(request));
}

@Test
void trxBP49ShouldThrowServiceExceptionWhenHttpErrorBodyIsValidJson() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    ResponseBody errorBody = ResponseBody.create(
            "{"
                    + "\"errores\":["
                    + "{"
                    + "\"codigo\":\"ERROR\","
                    + "\"descripcion\":\"technical error\""
                    + "}"
                    + "]"
                    + "}",
            MediaType.parse("application/json")
    );

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(500, errorBody));

    assertThrows(Exception.class, () -> service.trxBP49(request));
}

@Test
void trxBP49ShouldThrowServiceExceptionWhenHttpErrorBodyIsInvalidJson() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    ResponseBody errorBody = ResponseBody.create(
            "invalid-json",
            MediaType.parse("application/json")
    );

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(500, errorBody));

    assertThrows(Exception.class, () -> service.trxBP49(request));
}
