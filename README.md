package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

class ErrorServiceTest {

    private ErrorService service;

    @BeforeEach
    void setUp() {
        service = new ErrorService();

        service.setMsName("BNCBSN049");
        service.setMsVersion("api-services-v1");
        service.setLevel("ERROR");
        service.setFunctional("P-F");
        service.setTechnical("P-T");
        service.setBlankData("No puede estar vacío");

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "No puede ser nulo");
        service.setGeneral(general);
    }

    @Test
    void serviceExceptionBuilderShouldBuildFunctionalServiceException() {
        ServiceException ex = service.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "mensaje funcional",
                ErrorType.FUNCTIONAL
        );

        assertNotNull(ex);
        assertTrue(ex instanceof ServiceException);
    }

    @Test
    void serviceExceptionBuilderShouldBuildTechnicalServiceException() {
        ServiceException ex = service.serviceExceptionBuilder(
                HttpStatus.CONFLICT,
                "mensaje tecnico",
                ErrorType.TECHNICAL
        );

        assertNotNull(ex);
        assertTrue(ex instanceof ServiceException);
    }

    @Test
    void errorBuilderShouldBuildFunctionalError() {
        ErrorDTO error = service.errorBuilder(
                HttpStatus.BAD_REQUEST,
                "mensaje funcional",
                ErrorType.FUNCTIONAL
        );

        assertEquals("BNCBSN049-P-F-9400", error.getCode());
        assertEquals("ERROR", error.getLevel());
        assertEquals("mensaje funcional", error.getMessage());
        assertEquals("bncbsn049-api-services-v1: mensaje funcional", error.getDescription());
    }

    @Test
    void errorBuilderShouldBuildTechnicalError() {
        ErrorDTO error = service.errorBuilder(
                HttpStatus.CONFLICT,
                "mensaje tecnico",
                ErrorType.TECHNICAL
        );

        assertEquals("BNCBSN049-P-T-9409", error.getCode());
        assertEquals("ERROR", error.getLevel());
        assertEquals("mensaje tecnico", error.getMessage());
        assertEquals("bncbsn049-api-services-v1: mensaje tecnico", error.getDescription());
    }

    @Test
    void isBlankShouldThrowWhenValueIsBlank() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.isBlank("", "amount"));

        assertNotNull(ex);
    }

    @Test
    void isBlankShouldNotThrowWhenValueHasText() {
        assertDoesNotThrow(() -> service.isBlank("123", "amount"));
    }

    @Test
    void isNullShouldThrowWhenValueIsNull() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.isNull(null, "amount"));

        assertNotNull(ex);
    }

    @Test
    void isNullShouldNotThrowWhenValueIsNotNull() {
        assertDoesNotThrow(() -> service.isNull("123", "amount"));
    }

    @Test
    void shouldCoverGetterSetterFields() {
        HashMap<String, String> general = new HashMap<>();
        general.put("invalid", "Valor inválido");

        service.setInvalidValue("Inválido");
        service.setBlankData("Vacío");
        service.setGeneral(general);

        assertEquals("BNCBSN049", service.getMsName());
        assertEquals("api-services-v1", service.getMsVersion());
        assertEquals("ERROR", service.getLevel());
        assertEquals("P-F", service.getFunctional());
        assertEquals("P-T", service.getTechnical());
        assertEquals("Inválido", service.getInvalidValue());
        assertEquals("Vacío", service.getBlankData());
        assertEquals("Valor inválido", service.getGeneral().get("invalid"));
    }
}
