package com.santander.bnc.bsn049.bncbsn049mscontracts.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "CONTRACTS");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("CONTRACTS-P-T-9409", response.getBody().getErrors().get(0).getCode());
        assertEquals("Unhandled exception", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        Object target = new Object();
        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(target, "target");

        bindingResult.addError(new FieldError(
                "target",
                "fieldName",
                "must not be blank"
        ));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CONTRACTS-P-F-9400", response.getBody().getErrors().get(0).getCode());
        assertEquals("'fieldName' must not be blank", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleNoResourceFoundException() {
        NoResourceFoundException ex =
                new NoResourceFoundException("GET", "/not-found");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("CONTRACTS-P-F-9404", response.getBody().getErrors().get(0).getCode());
        assertEquals("Not Found", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleMissingServletRequestParameterException() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("id", "String");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Required query parameter id not specified",
                response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(
                        new IllegalArgumentException("'relationshipTypeCode': Invalid value")
                );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("'relationshipTypeCode': Invalid value",
                response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleMissingRequestHeaderException() {
        MissingRequestHeaderException ex =
                new MissingRequestHeaderException(
                        "Authorization",
                        null,
                        true
                );

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Required header Authorization not specified",
                response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleServiceException() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CONTRACTS-P-F-9400")
                .message("service error")
                .level("error")
                .description("contracts-api-services-v3: service error")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.NOT_FOUND, error);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("service error", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CONTRACTS-P-F-9400")
                .message("client error")
                .level("error")
                .description("contracts-api-services-v3: client error")
                .build();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrors(List.of(error));

        ServiceExceptionClient ex = mock(ServiceExceptionClient.class);
        when(ex.getErrorResponseDTO()).thenReturn(errorResponseDTO);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("client error", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldBuildResponseEntityWithDefaultStatusWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CONTRACTS-P-F-9400")
                .message("default status")
                .level("error")
                .description("contracts-api-services-v3: default status")
                .build();

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldBuildResponseEntityWhenErrorsAreNull() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, HttpStatus.CONFLICT);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex =
                new HttpMessageNotReadableException("bad body");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid body structure", response.getBody().getErrors().get(0).getMessage());
    }

    @Test
    void shouldHandleMethodNotAllowedException() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("CONTRACTS-P-F-9405", response.getBody().getErrors().get(0).getCode());
        assertEquals("Method not allowed", response.getBody().getErrors().get(0).getMessage());
    }
}