Te faltan cubrir principalmente:
handleErrorResponse
handlePemfvErrorResponse
buildErrorDTOs
branches de:
processResponse
trxBP49
trxPemfv
Te dejo los tests EXACTOS que faltan.
TESTS FALTANTES — TrxSanbaServiceImplTest
1. cubrir handleOtherExceptions()
Java
@Test
void trxBP49ShouldThrowConflictWhenUnexpectedRuntimeOccurs() throws Exception {

    when(trxSanbaAPI.callBP49(any(), any(), any(), any()))
            .thenThrow(new NullPointerException("unexpected"));

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}
2. cubrir processResponse ELSE
Java
@Test
void trxBP49ShouldHandleErrorResponseWhenApiFails() throws Exception {

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setMensaje("functional error");

    TrxBP49Response errorResponse = new TrxBP49Response();
    errorResponse.setErrores(List.of(error));

    Response<TrxBP49Response> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            mapper.writeValueAsString(errorResponse)
                    )
            );

    when(bp49Call.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}
3. cubrir JsonMappingException
Java
@Test
void trxBP49ShouldThrowTechnicalExceptionWhenJsonMappingFails() throws Exception {

    Response<TrxBP49Response> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            "invalid-json"
                    )
            );

    when(bp49Call.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}
4. cubrir "SECUENCIA NO EXISTE"
Java
@Test
void trxBP49ShouldThrowNotFoundWhenSequenceNotExists() throws Exception {

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setMensaje("SECUENCIA NO EXISTE");

    TrxBP49Response errorResponse = new TrxBP49Response();
    errorResponse.setErrores(List.of(error));

    Response<TrxBP49Response> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            mapper.writeValueAsString(errorResponse)
                    )
            );

    when(bp49Call.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}
5. cubrir IOException leyendo errorBody
Java
@Test
void trxBP49ShouldHandleIOExceptionReadingErrorBody() throws Exception {

    ResponseBody body = mock(ResponseBody.class);

    when(body.string()).thenThrow(new IOException());

    Response<TrxBP49Response> retrofitResponse =
            Response.error(400, body);

    when(bp49Call.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}
PEMFV
6. cubrir handlePemfvGenericException
Java
@Test
void trxPemfvShouldThrowConflictWhenUnexpectedRuntimeOccurs() throws Exception {

    when(trxSanbaAPI.callPEMFV(any(), any(), any(), any()))
            .thenThrow(new NullPointerException("unexpected"));

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}
7. cubrir handlePemfvErrorResponse
Java
@Test
void trxPemfvShouldHandleErrorResponse() throws Exception {

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setMensaje("pemfv error");

    PemfvResponse errorResponse = new PemfvResponse();
    errorResponse.setErrores(List.of(error));

    Response<PemfvResponse> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            mapper.writeValueAsString(errorResponse)
                    )
            );

    when(pemfvCall.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}
8. cubrir JsonProcessingException PEMFV
Java
@Test
void trxPemfvShouldThrowTechnicalExceptionWhenJsonFails() throws Exception {

    Response<PemfvResponse> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            "invalid-json"
                    )
            );

    when(pemfvCall.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}
9. cubrir SECUENCIA NO EXISTE PEMFV
Java
@Test
void trxPemfvShouldThrowNotFoundWhenSequenceNotExists() throws Exception {

    ErrorTrxDTO error = new ErrorTrxDTO();
    error.setMensaje("SECUENCIA NO EXISTE");

    PemfvResponse errorResponse = new PemfvResponse();
    errorResponse.setErrores(List.of(error));

    Response<PemfvResponse> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            mapper.writeValueAsString(errorResponse)
                    )
            );

    when(pemfvCall.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}
10. cubrir buildErrorDTOs null
Java
@Test
void trxPemfvShouldHandleNullErrorsList() throws Exception {

    PemfvResponse response = new PemfvResponse();

    Response<PemfvResponse> retrofitResponse =
            Response.error(
                    400,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            mapper.writeValueAsString(response)
                    )
            );

    when(pemfvCall.execute()).thenReturn(retrofitResponse);

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}
Con estos deberías subir aproximadamente:
instrucciones → 85%-95%
branches → 80%-90%
en TrxSanbaServiceImpl.