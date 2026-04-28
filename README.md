package com.santander.bnc.bsn049.bncbsn049mscountries.exception.error;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorClassesTest {

    @Test
    void shouldCoverErrorDictionaryConstantsAndPrivateConstructor() throws Exception {
        assertEquals("COUNTRIES-P-F-0006", ErrorDictionary.STATE_0006_CODE);
        assertEquals("error", ErrorDictionary.STATE_0006_LEVEL);
        assertEquals("Bad request - Invalid input data", ErrorDictionary.STATE_0006_MESSAGE);
        assertEquals("countries-api-services-v2: Invalid input data", ErrorDictionary.STATE_0006_DESCRIPTION);

        Constructor<ErrorDictionary> constructor = ErrorDictionary.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    @Test
    void shouldCoverErrorDTO() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .level("LEVEL")
                .description("DESCRIPTION")
                .build();

        assertEquals("CODE", error.getCode());
        assertEquals("MESSAGE", error.getMessage());
        assertEquals("LEVEL", error.getLevel());
        assertEquals("DESCRIPTION", error.getDescription());

        error.setCode("CODE2");
        error.setMessage("MESSAGE2");
        error.setLevel("LEVEL2");
        error.setDescription("DESCRIPTION2");

        assertEquals("CODE2", error.getCode());
        assertEquals("MESSAGE2", error.getMessage());
        assertEquals("LEVEL2", error.getLevel());
        assertEquals("DESCRIPTION2", error.getDescription());
        assertNotNull(error.toString());
    }

    @Test
    void shouldCoverErrorResponseDTO() {
        ErrorDTO error = ErrorDTO.builder().code("400").message("error").build();

        ErrorResponseDTO response = new ErrorResponseDTO();
        response.setErrors(List.of(error));

        assertNotNull(response.getErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("400", response.getErrors().get(0).getCode());
        assertNotNull(response.toString());

        ErrorResponseDTO response2 = new ErrorResponseDTO(List.of(error));
        assertEquals(1, response2.getErrors().size());
    }
}



>>>>>>>>>

package com.santander.bnc.bsn049.bncbsn049mscountries.exception;

import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "MS_NAME", "COUNTRIES");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response = handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("400", response.getBody().getErrors().get(0).getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription());
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() throws Exception {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "name", "must not be blank"));

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9404", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceException() {
        ServiceException ex = new ServiceException(HttpStatus.CONFLICT, "service error");

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("CONFLICT", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldBuildResponseEntityWithBadRequestWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("message")
                .description("description")
                .build();

        ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CODE", response.getBody().getErrors().get(0).getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                response.getBody().getErrors().get(0).getDescription());
    }

    @Test
    void shouldBuildResponseEntityWithNullErrors() {
        ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity(null, HttpStatus.I_AM_A_TEAPOT);

        assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertTrue(response.getBody().getErrors().isEmpty());
    }

    @Test
    void shouldHandleNoResourceFoundException() {
        NoResourceFoundException ex =
                new NoResourceFoundException(HttpMethod.GET, "/not-found");

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9404", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleMissingServletRequestParameterException() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("country", "String");

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("invalid"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleMissingRequestHeaderException() {
        MissingRequestHeaderException ex =
                new MissingRequestHeaderException("Authorization", null);

        ResponseEntity<ErrorResponseDTO> response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleMethodNotAllowedException() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response = handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("COUNTRIES-P-F-9405", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CLIENT")
                .message("client error")
                .description("client description")
                .build();

        ServiceExceptionClient ex = new ServiceExceptionClient(HttpStatus.NOT_FOUND, error);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(ex, mock(WebRequest.class));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("CLIENT", response.getBody().getErrors().get(0).getCode());
    }
}



>>>>>>>>>



package com.santander.bnc.bsn049.bncbsn049mscountries.exception;

import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void shouldCreateServiceExceptionWithMessage() {
        ServiceException ex = new ServiceException(HttpStatus.BAD_REQUEST, "bad request");

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("bad request", ex.getMessage());
        assertNotNull(ex.getTimestamp());
        assertNotNull(ex.toString());
    }

    @Test
    void shouldCreateServiceExceptionWithRequiredArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();

        ServiceException ex = new ServiceException(HttpStatus.CONFLICT, now);

        assertEquals(HttpStatus.CONFLICT, ex.getCode());
        assertEquals(now, ex.getTimestamp());
    }

    @Test
    void shouldCreateServiceExceptionClientWithErrorDTO() {
        ErrorDTO error = ErrorDTO.builder()
                .code("400")
                .message("client error")
                .description("description")
                .build();

        ServiceExceptionClient ex = new ServiceExceptionClient(HttpStatus.BAD_REQUEST, error);

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
        assertNotNull(ex.getErrorResponseDTO());
        assertEquals("client error", ex.getMessage());
        assertEquals(1, ex.getErrorResponseDTO().getErrors().size());
    }

    @Test
    void shouldCoverServiceExceptionClientNoArgsAndSetters() {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(List.of(
                ErrorDTO.builder().code("500").message("error").build()
        ));

        ServiceExceptionClient ex = new ServiceExceptionClient();
        ex.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        ex.setErrorResponseDTO(responseDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getHttpStatus());
        assertEquals(responseDTO, ex.getErrorResponseDTO());
        assertNotNull(ex.toString());
    }

    @Test
    void shouldCoverServiceExceptionClientAllArgsConstructor() {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO();

        ServiceExceptionClient ex =
                new ServiceExceptionClient(HttpStatus.CONFLICT, responseDTO);

        assertEquals(HttpStatus.CONFLICT, ex.getHttpStatus());
        assertEquals(responseDTO, ex.getErrorResponseDTO());
    }
}




>>>>>>>>>>




