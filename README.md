package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.springframework.core.MethodParameter; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.mock.web.MockHttpServletRequest; import org.springframework.test.util.ReflectionTestUtils; import org.springframework.validation.BeanPropertyBindingResult; import org.springframework.validation.FieldError; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.servlet.resource.NoResourceFoundException; import org.springframework.web.util.ServletRequestPathUtils;

import java.lang.reflect.Constructor; import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals; import static org.junit.jupiter.api.Assertions.assertNotNull; import static org.junit.jupiter.api.Assertions.assertTrue; import static org.mockito.Mockito.mock; import static org.mockito.Mockito.when;

class GlobalExceptionHandlerMissingTest {

private GlobalExceptionHandler handler;

@BeforeEach
void setUp() {
    handler = new GlobalExceptionHandler();
    ReflectionTestUtils.setField(handler, "msName", "BNCBSN049");
}

@Test
void handleNoResourceFoundException_shouldReturnNotFound() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/v3/products/no-existe");
    ServletRequestPathUtils.parseAndCache(request);

    NoResourceFoundException exception = new NoResourceFoundException(
            org.springframework.http.HttpMethod.GET,
            ServletRequestPathUtils.getParsedRequestPath(request).pathWithinApplication()
    );

    ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("BNCBSN049-P-F-9404", response.getBody().getErrors().get(0).getCode());
    assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
            response.getBody().getErrors().get(0).getDescription());
}

@Test
void handleMethodArgumentNotValidException_shouldReturnBadRequestAndCoverFieldErrorLoop() throws Exception {
    BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new DummyRequest(), "dummyRequest");
    bindingResult.addError(new FieldError("dummyRequest", "productId", "must not be blank"));
    bindingResult.addError(new FieldError("dummyRequest", "bankId", "must be numeric"));

    Method method = DummyController.class.getDeclaredMethod("dummy", DummyRequest.class);
    MethodParameter methodParameter = new MethodParameter(method, 0);
    MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);

    ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().getErrors().size());
    assertEquals("BNCBSN049-P-F-9404", response.getBody().getErrors().get(0).getCode());
    assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
            response.getBody().getErrors().get(0).getDescription());
}

@Test
void handleServiceExceptionClient_shouldDelegateToBuildResponseEntity2() {
    ErrorDTO errorDTO = ErrorDTO.builder()
            .code("MS_NAME-P-F-9400")
            .level("error")
            .message("client error")
            .description("MS_NAME-api-services-v3: client error")
            .build();

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
    errorResponseDTO.setErrors(new ArrayList<>(List.of(errorDTO)));

    ServiceExceptionClient exception = mock(ServiceExceptionClient.class);
    when(exception.getErrorResponseDTO()).thenReturn(errorResponseDTO);

    ResponseEntity<ErrorResponseDTO> response = handler.handleSchemaException(exception, null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    assertEquals("bncbsn049-api-services-v3: client error", response.getBody().getErrors().get(0).getDescription());
}

@Test
void buildResponseEntity_whenErrorsNullAndStatusNull_shouldReturnBadRequestWithEmptyErrors() {
    ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity(null, null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getErrors());
    assertTrue(response.getBody().getErrors().isEmpty());
}

@Test
void buildResponseEntity2_whenStatusNull_shouldReturnBadRequest() {
    ErrorDTO errorDTO = ErrorDTO.builder()
            .code("MS_NAME-P-T-9409")
            .level("error")
            .message("technical")
            .description("MS_NAME-api-services-v3: technical")
            .build();

    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
    errorResponseDTO.setErrors(new ArrayList<>(List.of(errorDTO)));

    ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity2(errorResponseDTO, null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("BNCBSN049-P-T-9409", response.getBody().getErrors().get(0).getCode());
    assertEquals("bncbsn049-api-services-v3: technical", response.getBody().getErrors().get(0).getDescription());
}

@Test
void privateApiResultAccessors_shouldCoverGeneratedGetterLinesIfJacocoShowsThem() throws Exception {
    Class<?> apiResultClass = Class.forName(
            "com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.observability.ExternalApisHealthIndicator$ApiResult"
    );
    Constructor<?> constructor = apiResultClass.getDeclaredConstructor(boolean.class, Integer.class, String.class);
    constructor.setAccessible(true);
    Object apiResult = constructor.newInstance(true, 200, "OK");

    Method isUp = apiResultClass.getDeclaredMethod("isUp");
    Method getHttpStatus = apiResultClass.getDeclaredMethod("getHttpStatus");
    Method getError = apiResultClass.getDeclaredMethod("getError");
    isUp.setAccessible(true);
    getHttpStatus.setAccessible(true);
    getError.setAccessible(true);

    assertEquals(true, isUp.invoke(apiResult));
    assertEquals(200, getHttpStatus.invoke(apiResult));
    assertEquals("OK", getError.invoke(apiResult));
}

static class DummyRequest {
    private String productId;
    private String bankId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}

static class DummyController {
    void dummy(DummyRequest request) {
        // método dummy solo para construir MethodArgumentNotValidException
    }
}

}