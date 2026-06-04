package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "msName", "BNCBSN049");
    }

    @Test
    void handleExceptionShouldReturnConflict() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("BNCBSN049-P-T-9409", response.getBody().getErrors().get(0).getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription());
    }

    @Test
    void handleValidationExceptionsShouldReturnBadRequest() throws Exception {
        BindException bindException = new BindException(new Object(), "request");
        bindException.addError(new FieldError("request", "amount", "must not be null"));

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(bindException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleNoResourceFoundShouldReturnNotFound() {
        NoResourceFoundException ex = mock(NoResourceFoundException.class);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9404", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleMissingServletRequestParameterShouldReturnBadRequest() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("authorization", "String");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleIllegalArgumentShouldReturnBadRequest() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("bad argument"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleMissingRequestHeaderShouldReturnBadRequest() throws Exception {
        MethodParameter parameter = mock(MethodParameter.class);
        MissingRequestHeaderException ex =
                new MissingRequestHeaderException("x-santander-client-id", parameter);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleServiceExceptionShouldReturnExceptionStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("BNCBSN049-P-F-9400")
                .message("functional")
                .description("internal description")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.BAD_REQUEST, error);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleServiceExceptionClientShouldReturnBadRequest() {
        ErrorDTO error = ErrorDTO.builder()
                .code(ErrorCatalog.MS_NAME + "-P-F-9400")
                .message("client error")
                .description(ErrorCatalog.MS_NAME + "-api-services-v1: client error")
                .build();

        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrors(List.of(error));

        ServiceExceptionClient ex = mock(ServiceExceptionClient.class);
        when(ex.getErrorResponseDTO()).thenReturn(errorResponse);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleHttpMessageNotReadableShouldReturnBadRequest() {
        HttpMessageNotReadableException ex =
                new HttpMessageNotReadableException("invalid body", new MockHttpServletRequest());

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void handleMethodNotAllowedShouldReturnMethodNotAllowed() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9405", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void buildResponseEntityShouldUseBadRequestWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code("BNCBSN049-P-F-9400")
                .message("message")
                .description("sensitive internal description")
                .build();

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription());
    }

    @Test
    void buildResponseEntityShouldHandleNullErrors() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, HttpStatus.CONFLICT);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody().getErrors());
        assertTrue(response.getBody().getErrors().isEmpty());
    }

    @Test
    void buildResponseEntity2ShouldUseBadRequestWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code(ErrorCatalog.MS_NAME + "-P-F-9400")
                .message("message")
                .description(ErrorCatalog.MS_NAME + "-api-services-v1: message")
                .build();

        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrors(List.of(error));

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity2(errorResponse, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }
}

@Test
void handleValidationExceptionsShouldReturnBadRequest() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    org.springframework.validation.BindingResult result = mock(org.springframework.validation.BindingResult.class);

    when(ex.getBindingResult()).thenReturn(result);
    when(result.getAllErrors()).thenReturn(List.of(
            new FieldError("request", "amount", "must not be null")
    ));

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("BNCBSN049-P-F-9400", response.getBody().getErrors().get(0).getCode());
}
