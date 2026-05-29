
package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "BNCBSN049");
    }

    @Test
    void handleExceptionShouldReturnConflict() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("error"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleServiceExceptionShouldReturnStatusFromException() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("message")
                .level("error")
                .description("description")
                .build();

        ServiceException exception = new ServiceException(HttpStatus.BAD_REQUEST, error);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(exception, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CODE", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleMissingServletRequestParameterExceptionShouldReturnBadRequest() {
        MissingServletRequestParameterException exception =
                new MissingServletRequestParameterException("deposit_id", "String");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleIllegalArgumentExceptionShouldReturnBadRequest() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("bad request"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleMissingRequestHeaderExceptionShouldReturnBadRequest() {
        MissingRequestHeaderException exception =
                new MissingRequestHeaderException("Authorization", null);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleHttpMessageNotReadableExceptionShouldReturnBadRequest() {
        HttpMessageNotReadableException exception =
                new HttpMessageNotReadableException("Invalid body");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleHttpRequestMethodNotSupportedExceptionShouldReturnMethodNotAllowed() {
        HttpRequestMethodNotSupportedException exception =
                new HttpRequestMethodNotSupportedException("GET");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getErrors().isEmpty());
    }

    @Test
    void buildResponseEntityShouldSanitizeDescription() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("technical message")
                .level("error")
                .description("technical description")
                .build();

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(error), HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CODE", response.getBody().getErrors().get(0).getCode());
        assertEquals(
                "Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription()
        );
    }

    @Test
    void buildResponseEntityShouldUseBadRequestWhenStatusIsNull() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getErrors().isEmpty());
    }
}
