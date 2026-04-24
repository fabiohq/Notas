@Test
void shouldThrowConflictWhenBp13CallThrowsGenericException() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP13TRX(request, "consultaDatosIPF", "consultaDatosIPF", "QCTFD"))
            .thenThrow(new Exception("generic error"));

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertEquals(HttpStatus.CONFLICT, ex.getCode());
}