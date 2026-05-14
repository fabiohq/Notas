package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "DATA_CONSENT_MANAGEMENT");
    }

    @Test
    void handleExceptionShouldReturnConflict() {
        var response = handler.handleException(new RuntimeException("boom"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
        assertThat(response.getBody().getErrors().get(0).getCode())
                .isEqualTo("DATA_CONSENT_MANAGEMENT-P-T-9409");
        assertThat(response.getBody().getErrors().get(0).getDescription())
                .isEqualTo("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
    }

    @Test
    void handleMissingServletRequestParameterExceptionShouldReturnBadRequest() {
        var exception = new MissingServletRequestParameterException("party_id", "String");

        var response = handler.handleValidationExceptions(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
        assertThat(response.getBody().getErrors().get(0).getCode())
                .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9400");
    }

    @Test
    void handleIllegalArgumentExceptionShouldReturnBadRequest() {
        var response = handler.handleValidationExceptions(new IllegalArgumentException("Invalid argument"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    void handleMissingRequestHeaderExceptionShouldReturnBadRequest() {
        var exception = new MissingRequestHeaderException("Authorization", null);

        var response = handler.handleValidationExceptions(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    void handleSchemaExceptionShouldReturnServiceExceptionStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("DATA_CONSENT_MANAGEMENT-P-F-9400")
                .level("error")
                .message("Functional error")
                .description("internal description")
                .build();

        ServiceException exception = new ServiceException(HttpStatus.BAD_REQUEST, error);

        var response = handler.handleSchemaException(exception, null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
        assertThat(response.getBody().getErrors().get(0).getDescription())
                .isEqualTo("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
    }

    @Test
    void buildResponseEntityShouldSanitizeDescription() {
        ErrorDTO error = ErrorDTO.builder()
                .code("DATA_CONSENT_MANAGEMENT-P-F-9400")
                .level("error")
                .message("Sensitive message")
                .description("Sensitive internal description")
                .build();

        var response = handler.buildResponseEntity(List.of(error), HttpStatus.BAD_REQUEST);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
        assertThat(response.getBody().getErrors().get(0).getCode())
                .isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9400");
        assertThat(response.getBody().getErrors().get(0).getDescription())
                .isEqualTo("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
    }

    @Test
    void buildResponseEntityShouldUseBadRequestWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .description("description")
                .build();

        var response = handler.buildResponseEntity(List.of(error), null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleHttpMessageNotReadableExceptionShouldReturnBadRequest() {
        var response = handler.handleValidationExceptions(new HttpMessageNotReadableException("invalid body"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    void handleMethodNotAllowedExceptionsShouldReturnMethodNotAllowed() {
        var response = handler.handleMethodNotAllowedExceptions(
                new HttpRequestMethodNotSupportedException("POST")
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).hasSize(1);
    }

    @Test
    void buildResponseEntityShouldReturnEmptyErrorsWhenInputErrorsIsNull() {
        var response = handler.buildResponseEntity(null, HttpStatus.BAD_REQUEST);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getErrors()).isEmpty();
    }
}