package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "BSN049");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertSanitized(response);
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() throws Exception {
        MethodParameter methodParameter = methodParameter();

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(new DummyRequest(), "dummyRequest");

        bindingResult.addError(new FieldError(
                "dummyRequest",
                "name",
                "must not be blank"
        ));

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleNoResourceFoundException() throws Exception {
        NoResourceFoundException exception =
                new NoResourceFoundException(
                        "GET",
                        org.springframework.web.util.pattern.PathPatternParser
                                .defaultInstance
                                .parse("/not-found")
                );

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertSanitized(response);
    }

    @Test
    void shouldHandleMissingServletRequestParameterException() {
        MissingServletRequestParameterException exception =
                new MissingServletRequestParameterException("depositId", "String");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("bad request"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
    }

    @Test
    void shouldHandleMissingRequestHeaderException() throws Exception {
        MethodParameter methodParameter = methodParameter();

        MissingRequestHeaderException exception =
                new MissingRequestHeaderException("Authorization", methodParameter);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceException() {
        ErrorDTO error = ErrorDTO.builder()
                .code("BSN049-P-F-9400")
                .message("service error")
                .description("technical description")
                .level("error")
                .build();

        ServiceException exception = new ServiceException(HttpStatus.NOT_FOUND, error);
        WebRequest webRequest = mock(WebRequest.class);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertSanitized(response);
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorDTO error = ErrorDTO.builder()
                .code("MS_NAME-P-F-9400")
                .message("client error")
                .description("MS_NAME-api-services-v1: client error")
                .level("error")
                .build();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrors(List.of(error));

        ServiceExceptionClient exception =
                mock(ServiceExceptionClient.class);

        when(exception.getErrorResponseDTO()).thenReturn(errorResponseDTO);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(exception, mock(WebRequest.class));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldBuildResponseEntity2WithNullStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("MS_NAME-P-F-9400")
                .message("msg")
                .description("MS_NAME description")
                .build();

        ErrorResponseDTO body = new ErrorResponseDTO();
        body.setErrors(List.of(error));

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity2(body, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception =
                new HttpMessageNotReadableException("invalid body");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
    }

    @Test
    void shouldHandleMethodNotAllowedException() {
        HttpRequestMethodNotSupportedException exception =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleMethodNotAllowedExceptions(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertSanitized(response);
        assertEquals("BSN049-P-F-9405", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldBuildResponseEntityWithNullErrorsAndNullStatus() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldBuildResponseEntityWithErrorsAndNullStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("BSN049-P-F-9400")
                .message("internal message")
                .description("internal technical detail")
                .build();

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSanitized(response);
    }

    private void assertSanitized(ResponseEntity<ErrorResponseDTO> response) {
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertFalse(response.getBody().getErrors().isEmpty());
        assertEquals(
                "Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription()
        );
    }

    private MethodParameter methodParameter() throws Exception {
        Method method = TestController.class.getDeclaredMethod("method", DummyRequest.class);
        return new MethodParameter(method, 0);
    }

    static class DummyRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class TestController {
        @SuppressWarnings("unused")
        public void method(DummyRequest request) {
        }
    }
}
