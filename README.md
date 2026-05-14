@Test
void handleMethodArgumentNotValidShouldHandleMultipleErrors() {

    BindingResult bindingResult = mock(BindingResult.class);

    FieldError fieldError1 =
            new FieldError("object", "field1", "invalid1");

    FieldError fieldError2 =
            new FieldError("object", "field2", "invalid2");

    when(bindingResult.getAllErrors())
            .thenReturn(List.of(fieldError1, fieldError2));

    MethodArgumentNotValidException exception =
            new MethodArgumentNotValidException(
                    mock(org.springframework.core.MethodParameter.class),
                    bindingResult
            );

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody().getErrors())
            .hasSize(2);
}

@Test
void handleServiceExceptionClientShouldReturnBadRequest() {

    ErrorDTO error = ErrorDTO.builder()
            .code("MS_NAME-P-F-9400")
            .description("ms_name-description")
            .message("client error")
            .build();

    ErrorResponseDTO dto = new ErrorResponseDTO();
    dto.setErrors(List.of(error));

    ServiceExceptionClient exception =
            mock(ServiceExceptionClient.class);

    when(exception.getErrorResponseDTO()).thenReturn(dto);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleSchemaException(
                    exception,
                    mock(WebRequest.class)
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody().getErrors())
            .hasSize(1);
}

@Test
void handleServiceExceptionShouldReturnCustomStatus() {

    ErrorDTO error = ErrorDTO.builder()
            .code("CODE")
            .description("description")
            .message("message")
            .build();

    ServiceException exception =
            new ServiceException(HttpStatus.NOT_FOUND, error);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleSchemaException(
                    exception,
                    mock(WebRequest.class)
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.NOT_FOUND);

    assertThat(response.getBody()).isNotNull();
}

@Test
void buildResponseEntityShouldHandleNullErrors() {

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity(
                    null,
                    HttpStatus.BAD_REQUEST
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody().getErrors())
            .isNull();
}

@Test
void buildResponseEntityShouldUseDefaultStatusWhenNull() {

    ErrorDTO error = ErrorDTO.builder()
            .code("CODE")
            .description("description")
            .build();

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity(
                    List.of(error),
                    null
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);
}

@Test
void buildResponseEntity2ShouldHandleMultipleErrors() {

    ErrorDTO error1 = ErrorDTO.builder()
            .code("MS_NAME-001")
            .description("ms_name-description1")
            .message("message1")
            .build();

    ErrorDTO error2 = ErrorDTO.builder()
            .code("MS_NAME-002")
            .description("ms_name-description2")
            .message("message2")
            .build();

    ErrorResponseDTO dto = new ErrorResponseDTO();
    dto.setErrors(List.of(error1, error2));

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity2(
                    dto,
                    HttpStatus.BAD_REQUEST
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody().getErrors())
            .hasSize(2);
}

@Test
void handleExceptionShouldReturnConflictStatus() {

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleException(
                    new RuntimeException("unexpected")
            );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.CONFLICT);
}

@Test
void handleMissingServletRequestParameterShouldContainParameterName() {

    MissingServletRequestParameterException exception =
            new MissingServletRequestParameterException(
                    "page",
                    "String"
            );

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody()
            .getErrors()
            .get(0)
            .getDescription())
            .contains("Ocurrió un error");
}

@Test
void handleMissingRequestHeaderShouldContainHeaderName() {

    MissingRequestHeaderException exception =
            new MissingRequestHeaderException(
                    "authorization",
                    mock(org.springframework.core.MethodParameter.class)
            );

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.BAD_REQUEST);
}

@Test
void handleHttpRequestMethodNotSupportedShouldReturnMethodNotAllowed() {

    HttpRequestMethodNotSupportedException exception =
            new HttpRequestMethodNotSupportedException("POST");

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleMethodNotAllowedExceptions(exception);

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
}

@Test
void buildResponseEntityShouldSanitizeDescriptions() {

    ErrorDTO error = ErrorDTO.builder()
            .code("CODE")
            .description("sensitive internal detail")
            .build();

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity(
                    List.of(error),
                    HttpStatus.BAD_REQUEST
            );

    assertThat(response.getBody()).isNotNull();

    String description =
            response.getBody()
                    .getErrors()
                    .get(0)
                    .getDescription();

    assertThat(description)
            .doesNotContain("sensitive");

    assertThat(description)
            .contains("Ocurrió un error");
}

@Test
void buildResponseEntityShouldPreserveErrorCount() {

    List<ErrorDTO> errors = List.of(
            ErrorDTO.builder().code("1").description("d1").build(),
            ErrorDTO.builder().code("2").description("d2").build(),
            ErrorDTO.builder().code("3").description("d3").build()
    );

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity(
                    errors,
                    HttpStatus.BAD_REQUEST
            );

    assertThat(response.getBody()).isNotNull();

    assertThat(response.getBody().getErrors())
            .hasSize(3);
}