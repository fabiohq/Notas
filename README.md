package com.santander.bnc.bsn049.bncbsn049mscustomer.exception;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "CUSTOMERS");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("generic"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() throws Exception {
        Object target = new Object();
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, "target");
        bindingResult.addError(new FieldError("target", "field", "Invalid field"));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleNoResourceFoundException() {
        NoResourceFoundException ex =
                new NoResourceFoundException(HttpMethod.GET, "/missing");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleMissingServletRequestParameterException() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("customerId", "String");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("bad argument"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleMissingRequestHeaderException() {
        MissingRequestHeaderException ex =
                new MissingRequestHeaderException("authorization", null);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleServiceException() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("message")
                .level("error")
                .description("description")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.CONFLICT, error);
        ServletWebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("message")
                .level("error")
                .description("description")
                .build();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(List.of(error));
        ServiceExceptionClient ex = new ServiceExceptionClient(errorResponseDTO);
        ServletWebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorResponseDTO, response.getBody());
    }

    @Test
    void shouldBuildResponseEntityWithNullStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("message")
                .level("error")
                .description("description")
                .build();

        ResponseEntity response =
                handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldBuildResponseEntityWithNullErrors() {
        ResponseEntity response =
                handler.buildResponseEntity(null, HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex =
                new HttpMessageNotReadableException("invalid body");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }

    @Test
    void shouldHandleMethodNotAllowedException() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
    }
}