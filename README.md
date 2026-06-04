@Test
void trxBP01ShouldThrowRuntimeBranch() {
    TrxBp01Request request = request(TrxBp01Request.class);

    when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxBP01(request));
}

@Test
void trxBP02ShouldThrowRuntimeBranch() {
    TrxBp02Request request = request(TrxBp02Request.class);

    when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxBP02(request));
}

@Test
void trxBP13ShouldThrowRuntimeBranch() {
    TrxBP13Request request = request(TrxBP13Request.class);

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP31ShouldThrowRuntimeBranch() {
    TrxBP31Request request = request(TrxBP31Request.class);

    when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxBP31(request));
}

@Test
void trxBP49ShouldThrowRuntimeBranch() {
    TrxBP49Request request = request(TrxBP49Request.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxPEPFShouldThrowRuntimeBranch() {
    TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

    when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class, () -> service.trxPEPF(request));
}

@Test
void validateTrxBp31ShouldFillErrors() throws Exception {
    var trxError = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(trxError.getMensaje()).thenReturn("ERROR BP31");

    var err = new TrxBP31Response();
    err.setErrores(java.util.List.of(trxError));

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR BP31")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO> errors =
            new java.util.ArrayList<>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxBp31",
            TrxBP31Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class
    );
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

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR BP49")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO> errors =
            new java.util.ArrayList<>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class
    );
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

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(parsed);

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("ERROR PEPF")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

    java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO> errors =
            new java.util.ArrayList<>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxPEPF",
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            TrxPEPFDataResponse.class,
            java.util.List.class
    );
    method.setAccessible(true);

    method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(), json, null, errors);

    assertEquals(1, errors.size());
}


import java.lang.reflect.Method;
