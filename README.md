@Test
void handleErrorResponseShouldThrowConflictWhenJsonMappingFails() throws Exception {

    TrxBP49Request request = buildRequest();

    retrofit2.Call<TrxBP49Response> call = mock(retrofit2.Call.class);

    ResponseBody errorBody =
            ResponseBody.create(
                    "invalid-json",
                    MediaType.parse("application/json")
            );

    Response<TrxBP49Response> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callBP49(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}

@Test
void handleErrorResponseShouldThrowNotFoundWhenSequenceNotExists() throws Exception {

    TrxBP49Request request = buildRequest();

    retrofit2.Call<TrxBP49Response> call = mock(retrofit2.Call.class);

    String json =
            """
            {
              "errores":[
                {
                  "mensaje":"SECUENCIA NO EXISTE"
                }
              ]
            }
            """;

    ResponseBody errorBody =
            ResponseBody.create(
                    json,
                    MediaType.parse("application/json")
            );

    Response<TrxBP49Response> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callBP49(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    when(errorService.errorBuilder(any(), any(), any()))
            .thenReturn(
                    ErrorDTO.builder()
                            .message("SECUENCIA NO EXISTE")
                            .build()
            );

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            any(),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(
                    new ServiceException(
                            HttpStatus.NOT_FOUND,
                            ErrorDTO.builder().message("error").build()
                    )
            );

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}

@Test
void handleErrorResponseShouldThrowBadRequestWhenErrorsExist() throws Exception {

    TrxBP49Request request = buildRequest();

    retrofit2.Call<TrxBP49Response> call = mock(retrofit2.Call.class);

    String json =
            """
            {
              "errores":[
                {
                  "mensaje":"ERROR FUNCIONAL"
                }
              ]
            }
            """;

    ResponseBody errorBody =
            ResponseBody.create(
                    json,
                    MediaType.parse("application/json")
            );

    Response<TrxBP49Response> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callBP49(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    when(errorService.errorBuilder(any(), any(), any()))
            .thenReturn(
                    ErrorDTO.builder()
                            .message("ERROR FUNCIONAL")
                            .build()
            );

    assertThatThrownBy(() -> service.trxBP49(request))
            .isInstanceOf(ServiceException.class);
}

@Test
void handlePemfvErrorResponseShouldThrowConflictWhenJsonFails() throws Exception {

    PemfvRequest pemfvRequest = buildPemfvRequest();

    retrofit2.Call<PemfvResponse> call = mock(retrofit2.Call.class);

    ResponseBody errorBody =
            ResponseBody.create(
                    "invalid-json",
                    MediaType.parse("application/json")
            );

    Response<PemfvResponse> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callPEMFV(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            any(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(
                    new ServiceException(
                            HttpStatus.CONFLICT,
                            ErrorDTO.builder().message("conflict").build()
                    )
            );

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}

@Test
void handlePemfvErrorResponseShouldThrowNotFound() throws Exception {

    PemfvRequest pemfvRequest = buildPemfvRequest();

    retrofit2.Call<PemfvResponse> call = mock(retrofit2.Call.class);

    String json =
            """
            {
              "errores":[
                {
                  "mensaje":"SECUENCIA NO EXISTE"
                }
              ]
            }
            """;

    ResponseBody errorBody =
            ResponseBody.create(
                    json,
                    MediaType.parse("application/json")
            );

    Response<PemfvResponse> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callPEMFV(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    when(errorService.errorBuilder(any(), any(), any()))
            .thenReturn(
                    ErrorDTO.builder()
                            .message("SECUENCIA NO EXISTE")
                            .build()
            );

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            any(),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(
                    new ServiceException(
                            HttpStatus.NOT_FOUND,
                            ErrorDTO.builder().message("notfound").build()
                    )
            );

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}

@Test
void handlePemfvErrorResponseShouldThrowBadRequest() throws Exception {

    PemfvRequest pemfvRequest = buildPemfvRequest();

    retrofit2.Call<PemfvResponse> call = mock(retrofit2.Call.class);

    String json =
            """
            {
              "errores":[
                {
                  "mensaje":"ERROR PEMFV"
                }
              ]
            }
            """;

    ResponseBody errorBody =
            ResponseBody.create(
                    json,
                    MediaType.parse("application/json")
            );

    Response<PemfvResponse> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callPEMFV(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    when(errorService.errorBuilder(any(), any(), any()))
            .thenReturn(
                    ErrorDTO.builder()
                            .message("ERROR PEMFV")
                            .build()
            );

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}

@Test
void buildErrorDTOsShouldHandleNullErrors() throws Exception {

    PemfvRequest pemfvRequest = buildPemfvRequest();

    retrofit2.Call<PemfvResponse> call = mock(retrofit2.Call.class);

    String json =
            """
            {
              "errores":[]
            }
            """;

    ResponseBody errorBody =
            ResponseBody.create(
                    json,
                    MediaType.parse("application/json")
            );

    Response<PemfvResponse> response =
            Response.error(400, errorBody);

    when(trxSanbaAPI.callPEMFV(any(), any(), any(), any()))
            .thenReturn(call);

    when(call.execute())
            .thenReturn(response);

    assertThatThrownBy(() -> service.trxPemfv(pemfvRequest))
            .isInstanceOf(ServiceException.class);
}