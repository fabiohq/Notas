@Test
void handleNoResourceFoundExceptionShouldReturnNotFound() {
    NoResourceFoundException exception =
            new NoResourceFoundException(
                    org.springframework.http.HttpMethod.GET,
                    "/test"
            );

    var response = handler.handleValidationExceptions(exception);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getErrors()).hasSize(1);
    assertThat(response.getBody().getErrors().get(0).getCode())
            .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9404");
}

@Test
void buildResponseEntity2ShouldReplaceMsNameAndReturnBadRequest() {

    ErrorDTO error = ErrorDTO.builder()
            .code("MS_NAME-P-F-9400")
            .description("ms_name-api error")
            .message("error message")
            .build();

    ErrorResponseDTO dto = new ErrorResponseDTO();
    dto.setErrors(new ArrayList<>(List.of(error)));

    var response = handler.buildResponseEntity2(dto, HttpStatus.BAD_REQUEST);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();
    assertThat(body.getErrors()).hasSize(1);

    ErrorDTO result = body.getErrors().get(0);

    assertThat(result.getCode())
            .contains("DATA_CONSENT_MANAGEMENT");

    assertThat(result.getDescription())
            .contains("data_consent_management");
}

@Test
void buildResponseEntity2ShouldUseBadRequestWhenStatusIsNull() {

    ErrorDTO error = ErrorDTO.builder()
            .code("MS_NAME-P-F-9400")
            .description("ms_name-description")
            .message("message")
            .build();

    ErrorResponseDTO dto = new ErrorResponseDTO();
    dto.setErrors(new ArrayList<>(List.of(error)));

    var response = handler.buildResponseEntity2(dto, null);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
}

@Test
void buildResponseEntityShouldHandleEmptyErrorList() {

    var response = handler.buildResponseEntity(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();
    assertThat(body.getErrors()).isEmpty();
}

@Test
void buildResponseEntityShouldSanitizeMultipleErrors() {

    ErrorDTO error1 = ErrorDTO.builder()
            .code("CODE1")
            .description("internal description 1")
            .message("msg1")
            .build();

    ErrorDTO error2 = ErrorDTO.builder()
            .code("CODE2")
            .description("internal description 2")
            .message("msg2")
            .build();

    var response = handler.buildResponseEntity(
            List.of(error1, error2),
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();
    assertThat(body.getErrors()).hasSize(2);

    body.getErrors().forEach(error ->
            assertThat(error.getDescription())
                    .isEqualTo("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.")
    );
}

@Test
void buildResponseEntityShouldKeepOriginalCode() {

    ErrorDTO error = ErrorDTO.builder()
            .code("ORIGINAL-CODE")
            .description("internal")
            .build();

    var response = handler.buildResponseEntity(
            List.of(error),
            HttpStatus.BAD_REQUEST
    );

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();
    assertThat(body.getErrors().get(0).getCode())
            .isEqualTo("ORIGINAL-CODE");
}

@Test
void handleExceptionShouldGenerateSanitizedDescription() {

    var response = handler.handleException(new Exception("unexpected"));

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();

    assertThat(body.getErrors().get(0).getDescription())
            .isEqualTo("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
}

@Test
void handleMethodNotAllowedShouldReturnCorrectCode() {

    var response = handler.handleMethodNotAllowedExceptions(
            new HttpRequestMethodNotSupportedException("PUT")
    );

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();

    assertThat(body.getErrors().get(0).getCode())
            .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9405");
}

@Test
void handleHttpMessageNotReadableShouldReturnCorrectCode() {

    var response = handler.handleValidationExceptions(
            new HttpMessageNotReadableException("invalid")
    );

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();

    assertThat(body.getErrors().get(0).getCode())
            .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9400");
}

@Test
void handleIllegalArgumentShouldReturnCorrectCode() {

    var response = handler.handleValidationExceptions(
            new IllegalArgumentException("illegal")
    );

    ErrorResponseDTO body = response.getBody();

    assertThat(body).isNotNull();

    assertThat(body.getErrors().get(0).getCode())
            .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9400");
}