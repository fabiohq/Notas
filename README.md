trxBP13ShouldThrowWhenResponseBodyIsNull
trxBP49ShouldThrowWhenResponseBodyIsNull

@Test
void trxBP13ShouldReturnNullWhenResponseBodyIsNull() throws Exception {
    TrxBP13Request request = buildBP13Request();
    Call<TrxBP13Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(null));

    assertNull(service.trxBP13(request));
}

@Test
void trxBP49ShouldReturnNullWhenResponseBodyIsNull() throws Exception {
    TrxBP49Request request = buildBP49Request();
    Call<TrxBP49Response> call = mock(Call.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.success(null));

    assertNull(service.trxBP49(request));
}
