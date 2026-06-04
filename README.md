// ========================= BP17 =========================

@Test
void trxBP17ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp17Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP17(bp17Request));
}

@Test
void trxBP17ShouldThrowIOExceptionBranch() throws Exception {
    when(bp17Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP17(bp17Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP17ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp17Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP17(bp17Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP17ShouldHandleJsonProcessingException() throws Exception {

    Response<TrxBP17Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            "json roto",
                            MediaType.parse("application/json")));

    when(bp17Call.execute()).thenReturn(response);

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(
                    HttpStatus.CONFLICT,
                    ErrorCatalog.MS_SANBA_TRX_ERROR));

    assertThrows(ServiceException.class,
            () -> service.trxBP17(bp17Request));
}


// ========================= BP31 =========================

@Test
void trxBP31ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp31Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP31(bp31Request));
}

@Test
void trxBP31ShouldThrowIOExceptionBranch() throws Exception {
    when(bp31Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP31(bp31Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP31ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp31Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP31(bp31Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP31ShouldReturnNullWhenNoExisteCdt() throws Exception {

    String json =
            """
            {
              "errores":[
                 {
                    "mensaje":"NO EXISTE CDT"
                 }
              ]
            }
            """;

    Response<TrxBP31Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            json,
                            MediaType.parse("application/json")));

    when(bp31Call.execute()).thenReturn(response);

    ErrorDTO dto = ErrorDTO.builder()
            .message("NO EXISTE CDT")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any()))
            .thenReturn(dto);

    assertNull(service.trxBP31(bp31Request));
}


// ========================= PEPF =========================

@Test
void trxPEPFShouldThrowRuntimeExceptionBranch() throws Exception {
    when(pepfCall.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxPEPF(pepfRequest));
}

@Test
void trxPEPFShouldThrowIOExceptionBranch() throws Exception {
    when(pepfCall.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxPEPF(pepfRequest));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxPEPFShouldThrowGenericExceptionBranch() throws Exception {
    when(pepfCall.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxPEPF(pepfRequest));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxPEPFShouldReturnNullWhenNoExisteCdt() throws Exception {

    String json =
            """
            {
              "errores":[
                 {
                    "mensaje":"NO EXISTE CDT"
                 }
              ]
            }
            """;

    Response<TrxPEPFDataResponse> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            json,
                            MediaType.parse("application/json")));

    when(pepfCall.execute()).thenReturn(response);

    ErrorDTO dto = ErrorDTO.builder()
            .message("NO EXISTE CDT")
            .build();

    when(errorService.errorBuilder(any(), anyString(), any()))
            .thenReturn(dto);

    assertNull(service.trxPEPF(pepfRequest));
}


// ========================= BP13 =========================

@Test
void trxBP13ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp13Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP13(bp13Request));
}

@Test
void trxBP13ShouldThrowIOExceptionBranch() throws Exception {
    when(bp13Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP13(bp13Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP13ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp13Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP13(bp13Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP13ShouldHandleJsonProcessingException() throws Exception {

    Response<TrxBP13Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            "json roto",
                            MediaType.parse("application/json")));

    when(bp13Call.execute()).thenReturn(response);

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(
                    HttpStatus.CONFLICT,
                    ErrorCatalog.MS_SANBA_TRX_ERROR));

    assertThrows(ServiceException.class,
            () -> service.trxBP13(bp13Request));
}


// ========================= BP01 =========================

@Test
void trxBP01ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp01Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP01(bp01Request));
}

@Test
void trxBP01ShouldThrowIOExceptionBranch() throws Exception {
    when(bp01Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP01(bp01Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP01ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp01Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP01(bp01Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP01ShouldHandleJsonProcessingException() throws Exception {

    Response<TrxBp01Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            "json roto",
                            MediaType.parse("application/json")));

    when(bp01Call.execute()).thenReturn(response);

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(
                    HttpStatus.CONFLICT,
                    ErrorCatalog.MS_SANBA_TRX_ERROR));

    assertThrows(ServiceException.class,
            () -> service.trxBP01(bp01Request));
}


// ========================= BP02 =========================

@Test
void trxBP02ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp02Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP02(bp02Request));
}

@Test
void trxBP02ShouldThrowIOExceptionBranch() throws Exception {
    when(bp02Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP02(bp02Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP02ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp02Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP02(bp02Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP02ShouldHandleJsonProcessingException() throws Exception {

    Response<TrxBp02Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            "json roto",
                            MediaType.parse("application/json")));

    when(bp02Call.execute()).thenReturn(response);

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenReturn(new ServiceException(
                    HttpStatus.CONFLICT,
                    ErrorCatalog.MS_SANBA_TRX_ERROR));

    assertThrows(ServiceException.class,
            () -> service.trxBP02(bp02Request));
}


// ========================= BP49 =========================

@Test
void trxBP49ShouldThrowRuntimeExceptionBranch() throws Exception {
    when(bp49Call.execute()).thenThrow(new RuntimeException("runtime"));

    assertThrows(ServiceException.class,
            () -> service.trxBP49(bp49Request));
}

@Test
void trxBP49ShouldThrowIOExceptionBranch() throws Exception {
    when(bp49Call.execute()).thenThrow(new IOException("io"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP49(bp49Request));

    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getHttpStatus());
}

@Test
void trxBP49ShouldThrowGenericExceptionBranch() throws Exception {
    when(bp49Call.execute()).thenThrow(new Exception("generic"));

    ServiceException ex = assertThrows(ServiceException.class,
            () -> service.trxBP49(bp49Request));

    assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
}

@Test
void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist() throws Exception {

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

    Response<TrxBP49Response> response =
            Response.error(
                    400,
                    ResponseBody.create(
                            json,
                            MediaType.parse("application/json")));

    when(bp49Call.execute()).thenReturn(response);

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            anyString(),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(new ServiceException(
                    HttpStatus.NOT_FOUND,
                    ErrorCatalog.MS_SANBA_TRX_ERROR));

    assertThrows(ServiceException.class,
            () -> service.trxBP49(bp49Request));
}
