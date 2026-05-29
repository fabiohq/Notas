package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorResponseDTO;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() throws Exception {
        handler = new GlobalExceptionHandler();
        setField(handler, "msName", "test-ms");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleException(new RuntimeException("Error test"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("test-ms-P-T-9499", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.handleValidationExceptions(new IllegalArgumentException("Invalid value"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("test-ms-p-f-9400", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceException() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("CODE-001");
        errorDTO.setMessage("Service error");
        errorDTO.setDescription("Service description");

        ServiceException exception =
                new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(exception, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("CODE-001", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("CLIENT-001");
        errorDTO.setMessage("Client error");
        errorDTO.setDescription("Client description");

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrors(List.of(errorDTO));

        ServiceExceptionClient exception =
                new ServiceExceptionClient(errorResponseDTO);

        ResponseEntity<ErrorResponseDTO> response =
                handler.handleSchemaException(exception, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("CLIENT-001", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldBuildResponseEntityWithNullStatus() {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("CODE-NULL");
        errorDTO.setMessage("Error");
        errorDTO.setDescription("Description");

        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(List.of(errorDTO), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("CODE-NULL", response.getBody().getErrors().get(0).getCode());
    }

    @Test
    void shouldBuildResponseEntityWithNullErrors() {
        ResponseEntity<ErrorResponseDTO> response =
                handler.buildResponseEntity(null, HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getErrors());
        assertEquals(0, response.getBody().getErrors().size());
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
