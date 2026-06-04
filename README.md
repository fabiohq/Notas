private com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO trxError(String message) {
    var error = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(error.getMensaje()).thenReturn(message);
    return error;
}

private okhttp3.ResponseBody errorBody(String json) {
    return okhttp3.ResponseBody.create(
            json,
            okhttp3.MediaType.parse("application/json")
    );
}

private void mockErrorBuilder(String message) {
    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message(message)
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
}







@Test
void trxBP49ShouldThrowBadRequestWhenApiReturnsErrorBody() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    var err = new TrxBP49Response();
    err.setErrores(java.util.List.of(trxError("ERROR BP49")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

    mockErrorBuilder("ERROR BP49");

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    var err = new TrxBP49Response();
    err.setErrores(java.util.List.of(trxError("SECUENCIA NO EXISTE")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("SECUENCIA NO EXISTE")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(org.springframework.http.HttpStatus.NOT_FOUND, errorDto));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP49ShouldThrowConflictWhenInvalidJsonErrorBody() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody("{invalid-json")));

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(org.springframework.http.HttpStatus.CONFLICT, "json-error"));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}






@Test
void trxbp49ValidateShouldFillErrorsFromValidJson() throws Exception {
    var err = new TrxBP49Response();
    err.setErrores(java.util.List.of(trxError("ERROR BP49")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

    mockErrorBuilder("ERROR BP49");

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, null, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

    assertEquals(1, errors.size());
}





