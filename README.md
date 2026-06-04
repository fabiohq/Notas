private okhttp3.ResponseBody errorBody(String json) {
    return okhttp3.ResponseBody.create(
            json,
            okhttp3.MediaType.parse("application/json")
    );
}

private com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO trxError(String message) {
    var error = org.mockito.Mockito.mock(
            com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
    when(error.getMensaje()).thenReturn(message);
    return error;
}

private void mockErrorBuilder(String message) {
    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message(message)
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
}

private void mockServiceExceptionBuilder() {
    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(org.springframework.http.HttpStatus.CONFLICT, "error"));
}


@Test
void trxBP01ShouldThrowWhenIOException() throws Exception {
    TrxBp01Request request = request(TrxBp01Request.class);

    when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp01Call);
    when(bp01Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP01(request));
}

@Test
void trxBP02ShouldThrowWhenIOException() throws Exception {
    TrxBp02Request request = request(TrxBp02Request.class);

    when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp02Call);
    when(bp02Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP02(request));
}

@Test
void trxBP13ShouldThrowWhenIOException() throws Exception {
    TrxBP13Request request = request(TrxBP13Request.class);

    when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp13Call);
    when(bp13Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP13(request));
}

@Test
void trxBP17ShouldThrowWhenIOException() throws Exception {
    TrxBP17Request request = request(TrxBP17Request.class);

    when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp17Call);
    when(bp17Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP17(request));
}

@Test
void trxBP31ShouldThrowWhenIOException() throws Exception {
    TrxBP31Request request = request(TrxBP31Request.class);

    when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp31Call);
    when(bp31Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP31(request));
}

@Test
void trxBP49ShouldThrowWhenIOException() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp49Call);
    when(bp49Call.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxPEPFShouldThrowWhenIOException() throws Exception {
    TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

    when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString()))
            .thenReturn(pepfCall);
    when(pepfCall.execute()).thenThrow(new java.io.IOException("io"));

    assertThrows(ServiceException.class, () -> service.trxPEPF(request));
}


@Test
void trxBP49ShouldThrowBadRequestWhenResponseHasErrors() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    var responseError = new TrxBP49Response();
    responseError.setErrores(java.util.List.of(trxError("ERROR BP49")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(responseError);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

    mockErrorBuilder("ERROR BP49");

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    var responseError = new TrxBP49Response();
    responseError.setErrores(java.util.List.of(trxError("SECUENCIA NO EXISTE")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(responseError);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

    var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
            .message("SECUENCIA NO EXISTE")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(org.springframework.http.HttpStatus.NOT_FOUND, "Deposit not found"));

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void trxBP49ShouldThrowConflictWhenInvalidJson() throws Exception {
    TrxBP49Request request = request(TrxBP49Request.class);

    when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
            .thenReturn(bp49Call);
    when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody("{bad-json")));

    mockServiceExceptionBuilder();

    assertThrows(ServiceException.class, () -> service.trxBP49(request));
}

@Test
void validateTrxBp31ShouldFillErrorsFromErrObject() throws Exception {
    var err = new TrxBP31Response();
    err.setErrores(java.util.List.of(trxError("ERROR BP31")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

    mockErrorBuilder("ERROR BP31");

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxBp31",
            TrxBP31Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class
    );
    method.setAccessible(true);

    method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

    assertEquals(1, errors.size());
}

@Test
void validateTrxBp31ShouldThrowWhenInvalidJson() throws Exception {
    var err = new TrxBP31Response();
    err.setErrores(java.util.Collections.emptyList());

    mockServiceExceptionBuilder();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxBp31",
            TrxBP31Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class
    );
    method.setAccessible(true);

    assertThrows(Exception.class, () ->
            method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(),
                    "{bad-json", new java.util.ArrayList<>()));
}

@Test
void trxbp49ValidateShouldFillErrorsFromJson() throws Exception {
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
            java.util.List.class
    );
    method.setAccessible(true);

    method.invoke(service, null, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

    assertEquals(1, errors.size());
}

@Test
void trxbp49ValidateShouldThrowWhenInvalidJson() throws Exception {
    mockServiceExceptionBuilder();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class
    );
    method.setAccessible(true);

    assertThrows(Exception.class, () ->
            method.invoke(service, null, new com.fasterxml.jackson.databind.ObjectMapper(),
                    "{bad-json", new java.util.ArrayList<>()));
}

@Test
void validateTrxPEPFShouldFillErrorsFromJson() throws Exception {
    var err = new TrxPEPFDataResponse();
    err.setErrores(java.util.List.of(trxError("ERROR PEPF")));

    String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

    mockErrorBuilder("ERROR PEPF");

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

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

@Test
void validateTrxPEPFShouldThrowWhenInvalidJson() throws Exception {
    mockServiceExceptionBuilder();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "validateTrxPEPF",
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            TrxPEPFDataResponse.class,
            java.util.List.class
    );
    method.setAccessible(true);

    assertThrows(Exception.class, () ->
            method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(),
                    "{bad-json", null, new java.util.ArrayList<>()));
}


import java.lang.reflect.Method;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;




