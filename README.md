@Test
void validateTrxBp31ShouldFillErrors() throws Exception {
    var trxError = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(trxError.getMensaje()).thenReturn("ERROR BP31");

    var err = new TrxBP31Response();
    err.setErrores(java.util.List.of(trxError));
    err.setAvisos(java.util.Collections.emptyList());

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR BP31")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxBp31",
            TrxBP31Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), "{}", errors);

    assertEquals(1, errors.size());
}

@Test
void trxbp49ValidateShouldFillErrors() throws Exception {
    var trxError = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(trxError.getMensaje()).thenReturn("ERROR BP49");

    var err = new TrxBP49Response();
    err.setErrores(java.util.List.of(trxError));
    err.setAvisos(java.util.Collections.emptyList());

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

    method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), "{}", errors);

    assertEquals(1, errors.size());
}

@Test
void validateTrxPEPFShouldFillErrors() throws Exception {
    var trxError = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(trxError.getMensaje()).thenReturn("ERROR PEPF");

    var parsed = new TrxPEPFDataResponse();
    parsed.setErrores(java.util.List.of(trxError));
    parsed.setAvisos(java.util.Collections.emptyList());

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(parsed);

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR PEPF")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxPEPF",
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            TrxPEPFDataResponse.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(), json, null, errors);

    assertEquals(1, errors.size());
}
