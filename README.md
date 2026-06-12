package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleServiceException() {

        ErrorDTO error = ErrorDTO.builder()
                .code("TEST001")
                .message("Error funcional")
                .description("Descripción")
                .level("ERROR")
                .build();

        ServiceException exception =
                new ServiceException(HttpStatus.BAD_REQUEST, error);

        ResponseEntity<List<ErrorDTO>> response =
                handler.handleServiceException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        ErrorDTO dto = response.getBody().get(0);

        assertEquals("TEST001", dto.getCode());
    }

    @Test
    void shouldHandleGenericException() {

        Exception exception = new RuntimeException("error");

        ResponseEntity<List<ErrorDTO>> response =
                handler.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

class ServiceExceptionTest {

    @Test
    void shouldCreateException() {

        ErrorDTO error = ErrorDTO.builder()
                .code("001")
                .message("mensaje")
                .description("desc")
                .level("ERROR")
                .build();

        ServiceException exception =
                new ServiceException(HttpStatus.BAD_REQUEST, error);

        assertEquals(HttpStatus.BAD_REQUEST,
                exception.getCode());

        assertEquals(error,
                exception.getError());
    }
}



package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

class ErrorDTOTest {

    @Test
    void shouldCoverBuilder() {

        ErrorDTO dto = ErrorDTO.builder()
                .code("001")
                .message("mensaje")
                .description("descripcion")
                .level("ERROR")
                .build();

        assertEquals("001", dto.getCode());
        assertEquals("mensaje", dto.getMessage());
        assertEquals("descripcion", dto.getDescription());
        assertEquals("ERROR", dto.getLevel());
    }

    @Test
    void shouldCoverSettersAndGetters() {

        ErrorDTO dto = new ErrorDTO();

        dto.setCode("002");
        dto.setMessage("msg");
        dto.setDescription("desc");
        dto.setLevel("WARN");

        assertEquals("002", dto.getCode());
        assertEquals("msg", dto.getMessage());
        assertEquals("desc", dto.getDescription());
        assertEquals("WARN", dto.getLevel());
    }
}



