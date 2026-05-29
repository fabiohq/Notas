package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();

        ReflectionTestUtils.setField(errorService, "msName", "BNCBSN049");
        ReflectionTestUtils.setField(errorService, "msVersion", "v1");
        ReflectionTestUtils.setField(errorService, "level", "error");
        ReflectionTestUtils.setField(errorService, "functional", "P-F");
        ReflectionTestUtils.setField(errorService, "technical", "P-T");
        ReflectionTestUtils.setField(errorService, "INVALID_VALUE", "Invalid value");
        ReflectionTestUtils.setField(errorService, "BLANK_DATA", "Blank data");

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "Null data");
        errorService.setGeneral(general);
    }

    @Test
    void serviceExceptionBuilderShouldBuildFunctionalException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "message",
                ErrorType.FUNCTIONAL
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("BNCBSN049-P-F-9400", exception.getError().getCode());
        assertEquals("message", exception.getError().getMessage());
    }

    @Test
    void errorBuilderShouldBuildTechnicalError() {
        ErrorDTO error = errorService.errorBuilder(
                HttpStatus.CONFLICT,
                "technical error",
                ErrorType.TECHNICAL
        );

        assertEquals("BNCBSN049-P-T-9409", error.getCode());
        assertEquals("error", error.getLevel());
        assertEquals("technical error", error.getMessage());
    }

    @Test
    void isBlankShouldThrowServiceExceptionWhenValueIsBlank() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> errorService.isBlank("", "field")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
    }

    @Test
    void isBlankShouldNotThrowWhenValueIsNotBlank() {
        assertDoesNotThrow(() -> errorService.isBlank("value", "field"));
    }

    @Test
    void isNullShouldThrowServiceExceptionWhenValueIsNull() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> errorService.isNull(null, "field")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
    }

    @Test
    void isNullShouldNotThrowWhenValueIsNotNull() {
        assertDoesNotThrow(() -> errorService.isNull("value", "field"));
    }

    @Test
    void errorTypeShouldContainValues() {
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
    }
}
