package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();

        HashMap<String, String> general = new HashMap<>();
        general.put("blank_data", "no puede estar vacío");
        general.put("null", "no puede ser nulo");

        errorService.setMsName("MS-TEST");
        errorService.setMsVersion("api-services-v1");
        errorService.setLevel("error");
        errorService.setFunctional("P-F");
        errorService.setTechnical("P-T");
        errorService.setGeneral(general);
        errorService.setInvalidValue("Valor inválido");
    }

    @Test
    @DisplayName("Debe construir ServiceException funcional")
    void serviceExceptionBuilderShouldBuildFunctionalException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "Mensaje funcional",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertNotNull(exception.getError());
        assertEquals("MS-TEST-P-F-9400", exception.getError().getCode());
        assertEquals("error", exception.getError().getLevel());
        assertEquals("Mensaje funcional", exception.getError().getMessage());
        assertEquals(
                "ms-test-api-services-v1: Mensaje funcional",
                exception.getError().getDescription()
        );
    }

    @Test
    @DisplayName("Debe construir ServiceException técnica")
    void serviceExceptionBuilderShouldBuildTechnicalException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.CONFLICT,
                "Mensaje técnico",
                ErrorType.TECHNICAL
        );

        assertNotNull(exception);
        assertEquals(HttpStatus.CONFLICT, exception.getCode());
        assertNotNull(exception.getError());
        assertEquals("MS-TEST-P-T-9409", exception.getError().getCode());
        assertEquals("error", exception.getError().getLevel());
        assertEquals("Mensaje técnico", exception.getError().getMessage());
        assertEquals(
                "ms-test-api-services-v1: Mensaje técnico",
                exception.getError().getDescription()
        );
    }

    @Test
    @DisplayName("Debe construir ErrorDTO funcional")
    void errorBuilderShouldBuildFunctionalErrorDTO() {
        ErrorDTO error = errorService.errorBuilder(
                HttpStatus.NOT_FOUND,
                "No encontrado",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(error);
        assertEquals("MS-TEST-P-F-9404", error.getCode());
        assertEquals("error", error.getLevel());
        assertEquals("No encontrado", error.getMessage());
        assertEquals("ms-test-api-services-v1: No encontrado", error.getDescription());
    }

    @Test
    @DisplayName("Debe construir ErrorDTO técnico")
    void errorBuilderShouldBuildTechnicalErrorDTO() {
        ErrorDTO error = errorService.errorBuilder(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Servicio no disponible",
                ErrorType.TECHNICAL
        );

        assertNotNull(error);
        assertEquals("MS-TEST-P-T-9503", error.getCode());
        assertEquals("error", error.getLevel());
        assertEquals("Servicio no disponible", error.getMessage());
        assertEquals(
                "ms-test-api-services-v1: Servicio no disponible",
                error.getDescription()
        );
    }

    @Test
    @DisplayName("isBlank no debe lanzar excepción cuando el valor tiene texto")
    void isBlankShouldNotThrowWhenValueHasText() {
        assertDoesNotThrow(() -> errorService.isBlank("abc", "campo"));
    }

    @Test
    @DisplayName("isBlank debe lanzar ServiceException cuando el valor está vacío")
    void isBlankShouldThrowWhenValueIsBlank() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> errorService.isBlank("", "campo")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("'campo' no puede estar vacío", exception.getError().getMessage());
        assertEquals("MS-TEST-P-F-9400", exception.getError().getCode());
    }

    @Test
    @DisplayName("isBlank debe lanzar ServiceException cuando el valor tiene espacios")
    void isBlankShouldThrowWhenValueHasOnlySpaces() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> errorService.isBlank("   ", "campo")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("'campo' no puede estar vacío", exception.getError().getMessage());
    }

    @Test
    @DisplayName("isNull no debe lanzar excepción cuando el valor no es null")
    void isNullShouldNotThrowWhenValueIsNotNull() {
        assertDoesNotThrow(() -> errorService.isNull("abc", "campo"));
    }

    @Test
    @DisplayName("isNull debe lanzar ServiceException cuando el valor es null")
    void isNullShouldThrowWhenValueIsNull() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> errorService.isNull(null, "campo")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("'campo' no puede ser nulo", exception.getError().getMessage());
        assertEquals("MS-TEST-P-F-9400", exception.getError().getCode());
    }

    @Test
    @DisplayName("Debe cubrir getters y setters")
    void gettersAndSettersShouldWork() {
        HashMap<String, String> general = new HashMap<>();
        general.put("key", "value");

        errorService.setMsName("MS");
        errorService.setMsVersion("V1");
        errorService.setLevel("warn");
        errorService.setFunctional("FUNC");
        errorService.setTechnical("TECH");
        errorService.setGeneral(general);
        errorService.setInvalidValue("invalid");

        assertEquals("MS", errorService.getMsName());
        assertEquals("V1", errorService.getMsVersion());
        assertEquals("warn", errorService.getLevel());
        assertEquals("FUNC", errorService.getFunctional());
        assertEquals("TECH", errorService.getTechnical());
        assertSame(general, errorService.getGeneral());
        assertEquals("invalid", errorService.getInvalidValue());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorTypeTest {

    @Test
    @DisplayName("Debe contener los valores FUNCTIONAL y TECHNICAL")
    void enumShouldContainExpectedValues() {
        ErrorType[] values = ErrorType.values();

        assertEquals(2, values.length);
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
    }
}


