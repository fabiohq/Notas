@Test
void shouldContinueWhenBp13RequestSerializationFails() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);

    Call<TrxBP13Response> call = mock(Call.class);
    TrxBP13Response responseBody = new TrxBP13Response();

    when(objectMapper.writeValueAsString(any()))
            .thenThrow(new RuntimeException("serialization error"))
            .thenReturn("{}");

    when(trxSanbaAPI.callBP13TRX(
            eq(request),
            eq("consultaDatosIPF"),
            eq("consultaDatosIPF"),
            eq("QCTFD")
    )).thenReturn(call);

    when(call.execute()).thenReturn(Response.success(responseBody));

    TrxBP13Response result = service.trxBP13(request);

    assertSame(responseBody, result);
}