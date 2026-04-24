@Test
void shouldThrowBadRequestWhenBp17ExecuteThrowsRuntimeException() throws Exception {
    TrxBP17Request request = mock(TrxBP17Request.class);

    @SuppressWarnings("unchecked")
    Call<TrxBP17Response> call = mock(Call.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");

    when(trxSanbaAPI.callBP17TRX(
            eq(request),
            eq("simulacionCDTBP17S171"),
            eq("simulacionCDTBP17S171"),
            eq("QCTFD")
    )).thenReturn(call);

    when(call.execute()).thenThrow(new RuntimeException("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

    assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
}