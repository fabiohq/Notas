assertThrows(ServiceException.class, () -> service.trxBP49(request));


por 
assertThrows(NullPointerException.class, () -> service.trxBP49(request));



****
assertThrows(Exception.class, () -> service.trxBP49(request));

por 
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

    assertThrows(Exception.class, () -> service.trxBP49(request));
}
****
BP13
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

    assertThrows(Exception.class, () -> service.trxBP13(request));
}
