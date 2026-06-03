@Test
void shouldCoverAllErrorResponseHandlers() throws Exception {
    mockErrorService();

    assertThrows(ServiceException.class,
            () -> invoke("handleBP17Response", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP17\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle31ErrorResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP31\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle13ErrorResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP13\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle01ErrorResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP01\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle02ErrorResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP02\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle21ErrorResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP21\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handleError92Response", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR BP92\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handlePEPFResponse", Response.class, errorResponse("{\"errores\":[{\"mensaje\":\"ERROR PEPF\"}]}")));
}

@Test
void shouldCoverNotFoundErrorBranches() throws Exception {
    mockErrorService();

    assertThrows(ServiceException.class,
            () -> invoke("handle13ErrorResponse", Response.class,
                    errorResponse("{\"errores\":[{\"mensaje\":\"CDT / DAT NO EXISTE.\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handle21ErrorResponse", Response.class,
                    errorResponse("{\"errores\":[{\"mensaje\":\"CDT / DAT NO EXISTE.\"}]}")));

    assertThrows(ServiceException.class,
            () -> invoke("handlePEPFResponse", Response.class,
                    errorResponse("{\"errores\":[{\"mensaje\":\"NO EXISTE CDT\"}]}")));
}

@Test
void shouldCoverParseErrorBranches() throws Exception {
    mockErrorService();

    assertThrows(ServiceException.class,
            () -> invoke("handleBP17Response", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handle31ErrorResponse", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handle13ErrorResponse", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handle01ErrorResponse", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handle02ErrorResponse", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handle21ErrorResponse", Response.class, errorResponse("BAD_JSON")));

    assertThrows(ServiceException.class,
            () -> invoke("handlePEPFResponse", Response.class, errorResponse("BAD_JSON")));
}

@Test
void shouldCoverGenericExceptionPrivateHandlers() throws Exception {
    assertThrows(ServiceException.class,
            () -> invoke("handle31GenericException", Exception.class, new Exception("generic")));

    assertThrows(ServiceException.class,
            () -> invoke("handle13GenericException", Exception.class, new Exception("generic")));

    assertThrows(ServiceException.class,
            () -> invoke("handle01GenericException", Exception.class, new Exception("generic")));

    assertThrows(ServiceException.class,
            () -> invoke("handle02GenericException", Exception.class, new Exception("generic")));

    assertThrows(ServiceException.class,
            () -> invoke("handle21GenericException", Exception.class, new Exception("generic")));
}

@Test
void shouldCoverRuntimeAndIOExceptionPrivateHandlers() throws Exception {
    assertThrows(ServiceException.class,
            () -> invoke("handle31RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertThrows(ServiceException.class,
            () -> invoke("handle13RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertThrows(ServiceException.class,
            () -> invoke("handle01RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertThrows(ServiceException.class,
            () -> invoke("handle02RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertThrows(ServiceException.class,
            () -> invoke("handle21RuntimeException", RuntimeException.class, new RuntimeException("runtime")));

    assertThrows(ServiceException.class, () -> invoke("handle31IOException"));
    assertThrows(ServiceException.class, () -> invoke("handle13IOException"));
    assertThrows(ServiceException.class, () -> invoke("handle01IOException"));
    assertThrows(ServiceException.class, () -> invoke("handle02IOException"));
    assertThrows(ServiceException.class, () -> invoke("handle21IOException"));
}

@Test
void shouldCoverHandle31NoExisteCdtReturnsNull() throws Exception {
    mockErrorService();

    Object result = invoke("handle31ErrorResponse", Response.class,
            errorResponse("{\"errores\":[{\"mensaje\":\"NO EXISTE CDT\"}]}"));

    assertNull(result);
}

private void mockErrorService() {
    when(errorService.errorBuilder(any(), anyString(), any()))
            .thenAnswer(inv -> ErrorDTO.builder()
                    .message(inv.getArgument(1))
                    .build());

    when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
            .thenAnswer(inv -> new ServiceException(
                    inv.getArgument(0),
                    ErrorDTO.builder()
                            .message(inv.getArgument(1))
                            .build()
            ));
}

private Response errorResponse(String body) {
    return Response.error(
            400,
            okhttp3.ResponseBody.create(
                    body,
                    okhttp3.MediaType.parse("application/json")
            )
    );
}

@SuppressWarnings("unchecked")
private <T> T invoke(String methodName) throws Exception {
    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(methodName);
    method.setAccessible(true);
    try {
        return (T) method.invoke(service);
    } catch (java.lang.reflect.InvocationTargetException e) {
        throw (Exception) e.getTargetException();
    }
}

@SuppressWarnings("unchecked")
private <T> T invoke(String methodName, Class<?> parameterType, Object arg) throws Exception {
    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(methodName, parameterType);
    method.setAccessible(true);
    try {
        return (T) method.invoke(service, arg);
    } catch (java.lang.reflect.InvocationTargetException e) {
        throw (Exception) e.getTargetException();
    }
}

