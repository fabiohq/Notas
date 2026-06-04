@Test
void trxbp49ValidateShouldFillErrors() throws Exception {
    var trxError = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(trxError.getMensaje()).thenReturn("ERROR BP49");

    var errorResponse = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.ErrorResponseTrxDTO();
    errorResponse.setErrores(java.util.List.of(trxError));

    var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    String json = mapper.writeValueAsString(errorResponse);

    var response = new TrxBP49Response();
    response.setErrores(new java.util.ArrayList<>());
    response.setAvisos(new java.util.ArrayList<>());

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR BP49")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, response, mapper, json, errors);

    assertEquals(1, errors.size());
}
