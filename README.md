package com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
        ReflectionTestUtils.setField(handler, "msName", "MS");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() throws Exception {
        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "field", "must not be null"));

        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex =
                new HttpMessageNotReadableException("bad body");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldHandleMethodNotAllowed() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldBuildResponseEntityWithErrorsAndNullStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("internal message")
                .description("internal description")
                .build();

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CODE", response.getBody().getErrors().get(0).getCode());
        assertEquals(
                "Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription()
        );
    }

    @Test
    void shouldBuildResponseEntityWithNullErrors() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, HttpStatus.CONFLICT);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldHandleServiceException() {
        ServiceException ex = new ServiceException(
                HttpStatus.BAD_REQUEST,
                ErrorDTO.builder().code("SVC").message("service error").build()
        );

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void shouldHandleOtherSpringExceptionsAsGeneric() throws Exception {
        assertEquals(HttpStatus.CONFLICT,
                handler.handleException(new MissingRequestHeaderException("authorization", mock(MethodParameter.class)))
                        .getStatusCode());

        assertEquals(HttpStatus.CONFLICT,
                handler.handleException(new MissingServletRequestParameterException("id", "String"))
                        .getStatusCode());

        assertEquals(HttpStatus.CONFLICT,
                handler.handleException(mock(NoResourceFoundException.class))
                        .getStatusCode());

        assertEquals(HttpStatus.CONFLICT,
                handler.handleException(mock(ServiceExceptionClient.class))
                        .getStatusCode());
    }
}
