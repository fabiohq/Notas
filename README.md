ErrorDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDTOTest {

    @Test
    void shouldCoverAll() {
        ErrorDTO dto = new ErrorDTO();

        dto.setCode("CODE");
        dto.setMessage("message");
        dto.setLevel("error");
        dto.setDescription("description");

        assertEquals("CODE", dto.getCode());
        assertEquals("message", dto.getMessage());
        assertEquals("error", dto.getLevel());
        assertEquals("description", dto.getDescription());
        assertTrue(dto.toString().contains("CODE"));

        ErrorDTO built = ErrorDTO.builder()
                .code("CODE2")
                .message("message2")
                .level("error")
                .description("description2")
                .build();

        assertEquals("CODE2", built.getCode());

        ErrorDTO allArgs = new ErrorDTO("CODE3", "message3", "error", "description3");
        assertEquals("CODE3", allArgs.getCode());
    }
}
ErrorResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDTOTest {

    @Test
    void shouldCoverAll() {
        List<ErrorDTO> errors = List.of(new ErrorDTO());

        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setErrors(errors);

        assertEquals(errors, dto.getErrors());
        assertTrue(dto.toString().contains("errors"));

        ErrorResponseDTO allArgs = new ErrorResponseDTO(errors);
        assertEquals(errors, allArgs.getErrors());
    }
}
ErrorCatalogTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCatalogTest {

    @Test
    void shouldCoverStaticErrors() {
        assertEquals("MS_NAME", ErrorCatalog.MS_NAME);

        assertError(ErrorCatalog.MS_PARAMETERS_NETWORK_CONNECTION);
        assertError(ErrorCatalog.MS_PARAMETERS_RESPONSE);
        assertError(ErrorCatalog.MS_PARAMETERS_NO_ENTRY);
        assertError(ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
        assertError(ErrorCatalog.MS_PARAMETERS_GENERAL);
        assertError(ErrorCatalog.MS_SANBA_TRX_RUNTIME_ERROR);
        assertError(ErrorCatalog.MS_SANBA_TRX_ERROR);
        assertError(ErrorCatalog.MS_SANBA_RESPONSE);
        assertError(ErrorCatalog.INVALID_CUSTOMER_ID);
        assertError(ErrorCatalog.PERSON_IS_NOT_CLIENT);
        assertError(ErrorCatalog.PERSON_IS_NOT_PROSPECT);
        assertError(ErrorCatalog.PERSON_IS_NOT_CUSTOMER);
        assertError(ErrorCatalog.BLANK_DATA);
        assertError(ErrorCatalog.COUNTRY_NOT_FOUND);
        assertError(ErrorCatalog.CUSTOMER_NOT_FOUND);
    }

    private void assertError(ErrorDTO error) {
        assertNotNull(error);
        assertNotNull(error.getCode());
        assertNotNull(error.getLevel());
        assertNotNull(error.getMessage());
        assertNotNull(error.getDescription());
    }
}
ErrorDictionaryTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDictionaryTest {

    @Test
    void shouldCoverConstants() {
        assertEquals("CUSTOMERS", ErrorDictionary.MS_NAME);
        assertEquals("error", ErrorDictionary.ERROR_LEVEL);
        assertEquals("-api-services-v3:", ErrorDictionary.API_SERVICE);
        assertEquals("Cannot connect to MS Parameters", ErrorDictionary.ERROR_MS_PARAMETERS_NETWORK);
        assertEquals("Error trying to process response", ErrorDictionary.ERROR_MS_PARAMETERS_RESPONSE);
        assertEquals("No entry founds", ErrorDictionary.ERROR_MS_PARAMETERS_NO_ENTRY);
        assertEquals("Cannot connect to MS Sanba", ErrorDictionary.ERROR_MS_SANBA_NETWORK);
        assertEquals("Invalid customerId", ErrorDictionary.INVALID_CUSTOMER_ID);
        assertEquals("Person is not client", ErrorDictionary.PERSON_IS_NOT_CLIENT);
        assertEquals("Person is not prospect", ErrorDictionary.PERSON_IS_NOT_PROSPECT);
        assertEquals("Person is not customer", ErrorDictionary.PERSON_IS_NOT_CUSTOMER);
        assertEquals("Cannot be blank", ErrorDictionary.BLANK_DATA);
        assertEquals("Country not found", ErrorDictionary.COUNTRY_NOT_FOUND);
        assertEquals("Customer not found", ErrorDictionary.CUSTOMER_NOT_FOUND);
    }
}
ErrorTypeTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTypeTest {

    @Test
    void shouldCoverEnum() {
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
        assertEquals(2, ErrorType.values().length);
    }
}
ServiceExceptionTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void shouldCoverNoArgsAndSetters() {
        ErrorDTO error = new ErrorDTO();

        ServiceException ex = new ServiceException();
        ex.setCode(HttpStatus.BAD_REQUEST);
        ex.setError(error);
        ex.setTimestamp(LocalDateTime.now());

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals(error, ex.getError());
        assertNotNull(ex.getTimestamp());
        assertTrue(ex.toString().contains("BAD_REQUEST"));
    }

    @Test
    void shouldCoverMessageConstructor() {
        ServiceException ex = new ServiceException(HttpStatus.NOT_FOUND, "not found");

        assertEquals(HttpStatus.NOT_FOUND, ex.getCode());
        assertEquals("not found", ex.getMessage());
        assertNotNull(ex.getTimestamp());
    }

    @Test
    void shouldCoverErrorDtoConstructor() {
        ErrorDTO error = ErrorDTO.builder()
                .message("error message")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.BAD_REQUEST, error);

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals(error, ex.getError());
        assertEquals("error message", ex.getMessage());
    }
}
ServiceExceptionClientTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionClientTest {

    @Test
    void shouldCoverAll() {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

        ServiceExceptionClient ex = new ServiceExceptionClient();
        ex.setErrorResponseDTO(errorResponseDTO);

        assertEquals(errorResponseDTO, ex.getErrorResponseDTO());
        assertTrue(ex.toString().contains("errorResponseDTO"));

        ServiceExceptionClient allArgs = new ServiceExceptionClient(errorResponseDTO);
        assertEquals(errorResponseDTO, allArgs.getErrorResponseDTO());
    }
}
ErrorServiceTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class ErrorServiceTest {

    private ErrorService service;

    @BeforeEach
    void setUp() {
        service = new ErrorService();

        ReflectionTestUtils.setField(service, "MS_NAME", "CUSTOMERS");
        ReflectionTestUtils.setField(service, "MS_VERSION", "api-services-v3");
        ReflectionTestUtils.setField(service, "ERROR_LEVEL", "error");
        ReflectionTestUtils.setField(service, "FUNCTIONAL_ERROR", "P-F");
        ReflectionTestUtils.setField(service, "TECHNICAL_ERROR", "P-T");

        service.INVALID_VALUE = "Invalid value";
        service.BLANK_DATA = "Cannot be blank";
        service.COUNTRY_NOT_FOUND = "Country not found";
        service.DATE_INVALID = "Date invalid";
        service.CODE_NOT_EXIST = "The code does not exist";
        service.COUNTRY_IS_BLANK = "The code can only contain letters";
        service.TOWN_IS_BLANK = "The town can only contain letters";
        service.BIRTHDATE_IS_BLANK = "The birthDate must follow the schema aaaa-mm-dd";
        service.nullDataMessage = "Cannot be null";
    }

    @Test
    void shouldBuildFunctionalError() {
        ServiceException ex = service.errorBuilder(HttpStatus.BAD_REQUEST, "bad", ErrorType.FUNCTIONAL);

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("bad", ex.getError().getMessage());
        assertTrue(ex.getError().getCode().contains("P-F"));
    }

    @Test
    void shouldBuildTechnicalError() {
        ServiceException ex = service.errorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "tech", ErrorType.TECHNICAL);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode());
        assertEquals("tech", ex.getError().getMessage());
        assertTrue(ex.getError().getCode().contains("P-T"));
    }

    @Test
    void shouldThrowWhenBlank() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.isBlank("", "field"));

        assertTrue(ex.getError().getMessage().contains("Cannot be blank"));
    }

    @Test
    void shouldNotThrowWhenNotBlank() {
        assertDoesNotThrow(() -> service.isBlank("value", "field"));
    }

    @Test
    void shouldThrowWhenBirthDateBlank() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.birthDateisBlank("", "birthDate"));

        assertTrue(ex.getError().getMessage().contains("birthDate"));
    }

    @Test
    void shouldThrowWhenCountryBlank() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.countryIsBlank("", "country"));

        assertTrue(ex.getError().getMessage().contains("country"));
    }

    @Test
    void shouldThrowWhenTownBlank() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.townIsBlank("", "town"));

        assertTrue(ex.getError().getMessage().contains("town"));
    }

    @Test
    void shouldValidateDate() {
        assertDoesNotThrow(() -> service.isValidDate("2026-05-02", "date"));
    }

    @Test
    void shouldThrowWhenInvalidDate() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.isValidDate("02/05/2026", "date"));

        assertTrue(ex.getError().getMessage().contains("Date invalid"));
    }

    @Test
    void shouldThrowWhenNull() {
        ServiceException ex = assertThrows(ServiceException.class,
                () -> service.isNull(null, "field"));

        assertTrue(ex.getError().getMessage().contains("Cannot be null"));
    }

    @Test
    void shouldNotThrowWhenNotNull() {
        assertDoesNotThrow(() -> service.isNull("value", "field"));
    }
}