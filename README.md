package com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();

        ReflectionTestUtils.setField(errorService, "msName", "MSCONTRACTS");
        ReflectionTestUtils.setField(errorService, "msVersion", "v1");
        ReflectionTestUtils.setField(errorService, "level", "ERROR");
        ReflectionTestUtils.setField(errorService, "functional", "FNC");
        ReflectionTestUtils.setField(errorService, "technical", "TECH");
        ReflectionTestUtils.setField(errorService, "BLANK_DATA", "Cannot be blank");

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "Cannot be null");

        ReflectionTestUtils.setField(errorService, "general", general);
    }

    @Test
    void shouldBuildServiceExceptionFunctional() {
        ServiceException ex = errorService.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "campo inválido",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(ex);
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        assertNotNull(ex.getError());
        assertEquals("MSCONTRACTS-FNC-9400", ex.getError().getCode());
        assertEquals("ERROR", ex.getError().getLevel());
        assertEquals("campo inválido", ex.getError().getMessage());
        assertEquals("mscontracts-v1: campo inválido", ex.getError().getDescription());
    }

    @Test
    void shouldBuildServiceExceptionTechnical() {
        ServiceException ex = errorService.serviceExceptionBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "error técnico",
                ErrorType.TECHNICAL
        );

        assertNotNull(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatus());
        assertNotNull(ex.getError());
        assertEquals("MSCONTRACTS-TECH-9500", ex.getError().getCode());
        assertEquals("ERROR", ex.getError().getLevel());
        assertEquals("error técnico", ex.getError().getMessage());
        assertEquals("mscontracts-v1: error técnico", ex.getError().getDescription());
    }

    @Test
    void shouldBuildErrorDtoFunctional() {
        ErrorDTO dto = errorService.errorBuilder(
                HttpStatus.BAD_REQUEST,
                "mensaje funcional",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(dto);
        assertEquals("MSCONTRACTS-FNC-9400", dto.getCode());
        assertEquals("ERROR", dto.getLevel());
        assertEquals("mensaje funcional", dto.getMessage());
        assertEquals("mscontracts-v1: mensaje funcional", dto.getDescription());
    }

    @Test
    void shouldBuildErrorDtoTechnical() {
        ErrorDTO dto = errorService.errorBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "mensaje técnico",
                ErrorType.TECHNICAL
        );

        assertNotNull(dto);
        assertEquals("MSCONTRACTS-TECH-9500", dto.getCode());
        assertEquals("ERROR", dto.getLevel());
        assertEquals("mensaje técnico", dto.getMessage());
        assertEquals("mscontracts-v1: mensaje técnico", dto.getDescription());
    }

    @Test
    void shouldThrowWhenValueIsBlank() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> errorService.isBlank("", "name")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        assertNotNull(ex.getError());
        assertEquals("MSCONTRACTS-FNC-9400", ex.getError().getCode());
        assertEquals("ERROR", ex.getError().getLevel());
        assertEquals("\"name\": Cannot be blank", ex.getError().getMessage());
        assertEquals("mscontracts-v1: \"name\": Cannot be blank", ex.getError().getDescription());
    }

    @Test
    void shouldNotThrowWhenValueIsNotBlank() {
        assertDoesNotThrow(() -> errorService.isBlank("valor", "name"));
    }

    @Test
    void shouldThrowWhenValueIsNull() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> errorService.isNull(null, "name")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        assertNotNull(ex.getError());
        assertEquals("MSCONTRACTS-FNC-9400", ex.getError().getCode());
        assertEquals("ERROR", ex.getError().getLevel());
        assertEquals("\"name\": Cannot be null", ex.getError().getMessage());
        assertEquals("mscontracts-v1: \"name\": Cannot be null", ex.getError().getDescription());
    }

    @Test
    void shouldNotThrowWhenValueIsNotNull() {
        assertDoesNotThrow(() -> errorService.isNull("valor", "name"));
    }
}