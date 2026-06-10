package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "Null data");

        errorService.setMsName("BSN049");
        errorService.setMsVersion("1.0.0");
        errorService.setLevel("error");
        errorService.setFunctional("F");
        errorService.setTechnical("T");
        errorService.setGeneral(general);
        errorService.INVALID_VALUE = "Invalid value";
        errorService.BLANK_DATA = "Blank data";
    }

    @Test
    void shouldBuildFunctionalServiceException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "functional error",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(exception);
    }

    @Test
    void shouldBuildTechnicalServiceException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.CONFLICT,
                "technical error",
                ErrorType.TECHNICAL
        );

        assertNotNull(exception);
    }

    @Test
    void shouldBuildFunctionalErrorDTO() {
        ErrorDTO error = errorService.errorBuilder(
                HttpStatus.BAD_REQUEST,
                "functional error",
                ErrorType.FUNCTIONAL
        );

        assertEquals("BSN049-F-9400", error.getCode());
        assertEquals("error", error.getLevel());
        assertEquals("functional error", error.getMessage());
        assertTrue(error.getDescription().contains("bsn049-1.0.0"));
    }

    @Test
    void shouldBuildTechnicalErrorDTO() {
        ErrorDTO error = errorService.errorBuilder(
                HttpStatus.CONFLICT,
                "technical error",
                ErrorType.TECHNICAL
        );

        assertEquals("BSN049-T-9409", error.getCode());
        assertEquals("error", error.getLevel());
        assertEquals("technical error", error.getMessage());
        assertTrue(error.getDescription().contains("bsn049-1.0.0"));
    }

    @Test
    void shouldNotThrowWhenValueIsNotBlank() {
        assertDoesNotThrow(() -> errorService.isBlank("value", "field"));
    }

    @Test
    void shouldThrowWhenValueIsBlank() {
        assertThrows(ServiceException.class,
                () -> errorService.isBlank("", "field"));
    }

    @Test
    void shouldNotThrowWhenValueIsNotNull() {
        assertDoesNotThrow(() -> errorService.isNull("value", "field"));
    }

    @Test
    void shouldThrowWhenValueIsNull() {
        assertThrows(ServiceException.class,
                () -> errorService.isNull(null, "field"));
    }

    @Test
    void shouldCoverDataMethods() {
        ErrorService one = new ErrorService();
        ErrorService two = new ErrorService();

        HashMap<String, String> general = new HashMap<>();
        general.put("key", "value");

        one.setMsName("BSN049");
        one.setMsVersion("1.0.0");
        one.setLevel("error");
        one.setFunctional("F");
        one.setTechnical("T");
        one.setGeneral(general);

        two.setMsName("BSN049");
        two.setMsVersion("1.0.0");
        two.setLevel("error");
        two.setFunctional("F");
        two.setTechnical("T");
        two.setGeneral(general);

        assertEquals("BSN049", one.getMsName());
        assertEquals("1.0.0", one.getMsVersion());
        assertEquals("error", one.getLevel());
        assertEquals("F", one.getFunctional());
        assertEquals("T", one.getTechnical());
        assertEquals(general, one.getGeneral());
        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("msName"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTypeTest {

    @Test
    void shouldCoverEnumValues() {
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
        assertArrayEquals(
                new ErrorType[]{ErrorType.FUNCTIONAL, ErrorType.TECHNICAL},
                ErrorType.values()
        );
    }
}
