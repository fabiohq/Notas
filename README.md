import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;

@Test
void handleNoResourceFoundExceptionShouldReturnNotFound() {
    NoResourceFoundException exception =
            new NoResourceFoundException(HttpMethod.GET, "/test");

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().getErrors().isEmpty());
}

@Test
void handleMethodArgumentNotValidExceptionShouldReturnBadRequest() throws Exception {
    Object target = new Object() {
        @SuppressWarnings("unused")
        String field;
    };

    BeanPropertyBindingResult bindingResult =
            new BeanPropertyBindingResult(target, "target");

    bindingResult.addError(new FieldError(
            "target",
            "field",
            "must not be null"
    ));

    MethodParameter methodParameter =
            new MethodParameter(
                    this.getClass()
                            .getDeclaredMethod("dummyMethod", String.class),
                    0
            );

    MethodArgumentNotValidException exception =
            new MethodArgumentNotValidException(methodParameter, bindingResult);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().getErrors().isEmpty());
}

@Test
void buildResponseEntity2ShouldReturnStatusAndReplaceCatalogValues() {
    ErrorDTO error = ErrorDTO.builder()
            .code("MS-NAME-P-F-9400")
            .message("message")
            .level("error")
            .description("ms-name-api-services-v1: description")
            .build();

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
    errorResponseDTO.setErrors(List.of(error));

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity2(errorResponseDTO, HttpStatus.BAD_REQUEST);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().getErrors().isEmpty());
}

@Test
void buildResponseEntity2ShouldUseBadRequestWhenStatusIsNull() {
    ErrorDTO error = ErrorDTO.builder()
            .code("CODE")
            .message("message")
            .level("error")
            .description("description")
            .build();

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
    errorResponseDTO.setErrors(List.of(error));

    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity2(errorResponseDTO, null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
}

@Test
void handleServiceExceptionClientShouldReturnBadRequest() {
    ErrorDTO error = ErrorDTO.builder()
            .code("CODE")
            .message("message")
            .level("error")
            .description("description")
            .build();

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
    errorResponseDTO.setErrors(List.of(error));

    ServiceExceptionClient exception =
            new ServiceExceptionClient(errorResponseDTO);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleSchemaException(exception, null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().getErrors().isEmpty());
}

@SuppressWarnings("unused")
private void dummyMethod(String value) {
}
