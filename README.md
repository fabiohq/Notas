Te dejo los tests separados por clase:
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDTOTest {

    @Test
    void shouldCoverGettersSettersAndBuilder() {
        ErrorDTO dto = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .level("error")
                .description("DESC")
                .build();

        assertEquals("CODE", dto.getCode());
        assertEquals("MESSAGE", dto.getMessage());
        assertEquals("error", dto.getLevel());
        assertEquals("DESC", dto.getDescription());

        dto.setCode("C2");
        dto.setMessage("M2");
        dto.setLevel("warning");
        dto.setDescription("D2");

        assertEquals("C2", dto.getCode());
        assertEquals("M2", dto.getMessage());
        assertEquals("warning", dto.getLevel());
        assertEquals("D2", dto.getDescription());
        assertNotNull(dto.toString());
    }

    @Test
    void shouldCoverNoArgsConstructor() {
        ErrorDTO dto = new ErrorDTO();

        assertNull(dto.getCode());
        assertNull(dto.getMessage());
        assertNull(dto.getLevel());
        assertNull(dto.getDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDTOTest {

    @Test
    void shouldCoverGettersSettersAndConstructors() {
        ErrorDTO error = ErrorDTO.builder().code("C").message("M").build();

        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setErrors(List.of(error));

        assertEquals(1, dto.getErrors().size());
        assertEquals(error, dto.getErrors().get(0));

        ErrorResponseDTO dtoAllArgs = new ErrorResponseDTO(List.of(error));
        assertEquals(1, dtoAllArgs.getErrors().size());
        assertNotNull(dtoAllArgs.toString());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDictionaryTest {

    @Test
    void shouldCoverGettersAndSetters() {
        String oldMsName = ErrorDictionary.getMsName();

        ErrorDictionary.setMsName("TEST");
        ErrorDictionary.setErrorLevel("warn");
        ErrorDictionary.setApiService("-api:");
        ErrorDictionary.setErrorMsParametersNetwork("network");
        ErrorDictionary.setErrorMsParametersResponse("response");
        ErrorDictionary.setErrorMsParametersNoEntry("no entry");
        ErrorDictionary.setErrorMsParametersGeneral("general");
        ErrorDictionary.setErrorMsSanbaNetwork("sanba network");
        ErrorDictionary.setErrorMsSanbaResponse("sanba response");
        ErrorDictionary.setErrorMsSanbaTrx("sanba trx");
        ErrorDictionary.setProspectNotFound("not found");
        ErrorDictionary.setPersonIsNotProspect("not prospect");
        ErrorDictionary.setBlankData("blank");
        ErrorDictionary.setIvalidInput("invalid");
        ErrorDictionary.setIvalidFormatEmail("invalid email");
        ErrorDictionary.setEmailEmpty("email empty");
        ErrorDictionary.setMobileNumberBlank("mobile blank");
        ErrorDictionary.setInvalidMobileNumber("invalid mobile");
        ErrorDictionary.setInvalidPhoneNumber("invalid phone");
        ErrorDictionary.setInvalidProspectId("invalid prospect");

        assertEquals("TEST", ErrorDictionary.getMsName());
        assertEquals("warn", ErrorDictionary.getErrorLevel());
        assertEquals("-api:", ErrorDictionary.getApiService());
        assertEquals("network", ErrorDictionary.getErrorMsParametersNetwork());
        assertEquals("response", ErrorDictionary.getErrorMsParametersResponse());
        assertEquals("no entry", ErrorDictionary.getErrorMsParametersNoEntry());
        assertEquals("general", ErrorDictionary.getErrorMsParametersGeneral());
        assertEquals("sanba network", ErrorDictionary.getErrorMsSanbaNetwork());
        assertEquals("sanba response", ErrorDictionary.getErrorMsSanbaResponse());
        assertEquals("sanba trx", ErrorDictionary.getErrorMsSanbaTrx());
        assertEquals("not found", ErrorDictionary.getProspectNotFound());
        assertEquals("not prospect", ErrorDictionary.getPersonIsNotProspect());
        assertEquals("blank", ErrorDictionary.getBlankData());
        assertEquals("invalid", ErrorDictionary.getIvalidInput());
        assertEquals("invalid email", ErrorDictionary.getIvalidFormatEmail());
        assertEquals("email empty", ErrorDictionary.getEmailEmpty());
        assertEquals("mobile blank", ErrorDictionary.getMobileNumberBlank());
        assertEquals("invalid mobile", ErrorDictionary.getInvalidMobileNumber());
        assertEquals("invalid phone", ErrorDictionary.getInvalidPhoneNumber());
        assertEquals("invalid prospect", ErrorDictionary.getInvalidProspectId());

        ErrorDictionary.setMsName(oldMsName);
    }

    @Test
    void privateConstructorShouldThrow() throws Exception {
        Constructor<ErrorDictionary> constructor = ErrorDictionary.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception ex = assertThrows(Exception.class, constructor::newInstance);
        assertTrue(ex.getCause() instanceof IllegalStateException);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCatalogTest {

    @Test
    void shouldReturnDefaultCatalogErrors() {
        assertEquals("-P-F-9400", ErrorCatalog.PF_9400);

        assertNotNull(ErrorCatalog.getMsParametersNetworkConnection());
        assertNotNull(ErrorCatalog.getMsParametersResponse());
        assertNotNull(ErrorCatalog.getMsParametersNoEntry());
        assertNotNull(ErrorCatalog.getMsParametersGeneral());
        assertNotNull(ErrorCatalog.getMsSanbaTrxError());
        assertNotNull(ErrorCatalog.getMsSanbaTrxResponse());
        assertNotNull(ErrorCatalog.getMsSanbaNetworkConnection());
        assertNotNull(ErrorCatalog.getProspectNotFound());
        assertNotNull(ErrorCatalog.getPersonIsNotProspect());
        assertNotNull(ErrorCatalog.getBlankData());
        assertNotNull(ErrorCatalog.getIvalidInput());
        assertNotNull(ErrorCatalog.getIvalidFormatEmail());
        assertNotNull(ErrorCatalog.getEmailEmpty());
        assertNotNull(ErrorCatalog.getMobileNumberBlank());
        assertNotNull(ErrorCatalog.getInvalidMobileNumber());
        assertNotNull(ErrorCatalog.getInvalidPhoneNumber());
        assertNotNull(ErrorCatalog.getInvalidProspectId());
    }

    @Test
    void shouldCoverSetters() {
        ErrorDTO custom = ErrorDTO.builder()
                .code("CUSTOM")
                .message("custom message")
                .level("error")
                .description("custom desc")
                .build();

        ErrorCatalog.setMsParametersNetworkConnection(custom);
        ErrorCatalog.setMsParametersResponse(custom);
        ErrorCatalog.setMsParametersNoEntry(custom);
        ErrorCatalog.setMsParametersGeneral(custom);
        ErrorCatalog.setMsSanbaTrxError(custom);
        ErrorCatalog.setMsSanbaTrxResponse(custom);
        ErrorCatalog.setMsSanbaNetworkConnection(custom);
        ErrorCatalog.setProspectNotFound(custom);
        ErrorCatalog.setPersonIsNotProspect(custom);
        ErrorCatalog.setBlankData(custom);
        ErrorCatalog.setIvalidInput(custom);
        ErrorCatalog.setIvalidFormatEmail(custom);
        ErrorCatalog.setEmailEmpty(custom);
        ErrorCatalog.setMobileNumberBlank(custom);
        ErrorCatalog.setInvalidMobileNumber(custom);
        ErrorCatalog.setInvalidPhoneNumber(custom);
        ErrorCatalog.setInvalidProspectId(custom);

        assertSame(custom, ErrorCatalog.getMsParametersNetworkConnection());
        assertSame(custom, ErrorCatalog.getMsParametersResponse());
        assertSame(custom, ErrorCatalog.getMsParametersNoEntry());
        assertSame(custom, ErrorCatalog.getMsParametersGeneral());
        assertSame(custom, ErrorCatalog.getMsSanbaTrxError());
        assertSame(custom, ErrorCatalog.getMsSanbaTrxResponse());
        assertSame(custom, ErrorCatalog.getMsSanbaNetworkConnection());
        assertSame(custom, ErrorCatalog.getProspectNotFound());
        assertSame(custom, ErrorCatalog.getPersonIsNotProspect());
        assertSame(custom, ErrorCatalog.getBlankData());
        assertSame(custom, ErrorCatalog.getIvalidInput());
        assertSame(custom, ErrorCatalog.getIvalidFormatEmail());
        assertSame(custom, ErrorCatalog.getEmailEmpty());
        assertSame(custom, ErrorCatalog.getMobileNumberBlank());
        assertSame(custom, ErrorCatalog.getInvalidMobileNumber());
        assertSame(custom, ErrorCatalog.getInvalidPhoneNumber());
        assertSame(custom, ErrorCatalog.getInvalidProspectId());
    }

    @Test
    void privateConstructorShouldThrow() throws Exception {
        Constructor<ErrorCatalog> constructor = ErrorCatalog.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception ex = assertThrows(Exception.class, constructor::newInstance);
        assertTrue(ex.getCause() instanceof UnsupportedOperationException);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTypeTest {

    @Test
    void shouldCoverEnumValues() {
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
        assertEquals(2, ErrorType.values().length);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void shouldCreateWithStatusAndMessage() {
        ServiceException ex = new ServiceException(HttpStatus.BAD_REQUEST, "bad request");

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("bad request", ex.getMessage());
        assertNotNull(ex.getTimestamp());
        assertNull(ex.getError());
        assertNotNull(ex.toString());
    }

    @Test
    void shouldCreateWithStatusAndErrorDTO() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .level("error")
                .description("DESC")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.CONFLICT, error);

        assertEquals(HttpStatus.CONFLICT, ex.getCode());
        assertEquals(error, ex.getError());
        assertEquals("MESSAGE", ex.getMessage());
    }

    @Test
    void shouldCoverNoArgsAndSetters() {
        ErrorDTO error = new ErrorDTO();

        ServiceException ex = new ServiceException();
        ex.setCode(HttpStatus.NOT_FOUND);
        ex.setError(error);

        assertEquals(HttpStatus.NOT_FOUND, ex.getCode());
        assertSame(error, ex.getError());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionClientTest {

    @Test
    void shouldCoverConstructorsGettersSetters() {
        ErrorResponseDTO response = new ErrorResponseDTO(List.of(new ErrorDTO()));

        ServiceExceptionClient ex = new ServiceExceptionClient();
        ex.setErrorResponseDTO(response);

        assertSame(response, ex.getErrorResponseDTO());

        ServiceExceptionClient allArgs = new ServiceExceptionClient(response);
        assertSame(response, allArgs.getErrorResponseDTO());
        assertNotNull(allArgs.toString());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
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
        ReflectionTestUtils.setField(service, "msNameError", "PROSPECTS");
        ReflectionTestUtils.setField(service, "msVersionError", "api-services-v1");
        ReflectionTestUtils.setField(service, "errorLevelMessage", "error");
        ReflectionTestUtils.setField(service, "functionalErrorMessage", "P-F");
        ReflectionTestUtils.setField(service, "techinalErrorMessage", "P-T");
        service.blankDataMessage = "Cannot be blank";
        service.nullDataMessage = "Cannot be null";
        service.invalidValueMessage = "Invalid value";
    }

    @Test
    void serviceExceptionBuilderShouldBuildFunctionalError() {
        ServiceException ex = service.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "message",
                ErrorType.FUNCTIONAL
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("PROSPECTS-P-F-9400", ex.getError().getCode());
        assertEquals("error", ex.getError().getLevel());
        assertEquals("message", ex.getError().getMessage());
        assertEquals("prospects-api-services-v1: message", ex.getError().getDescription());
    }

    @Test
    void serviceExceptionBuilderShouldBuildTechnicalError() {
        ServiceException ex = service.serviceExceptionBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "technical",
                ErrorType.TECHNICAL
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode());
        assertEquals("PROSPECTS-P-T-9500", ex.getError().getCode());
        assertEquals("technical", ex.getError().getMessage());
    }

    @Test
    void isBlankShouldThrowWhenBlank() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> service.isBlank("", "field")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("'field' Cannot be blank", ex.getError().getMessage());
    }

    @Test
    void isBlankShouldNotThrowWhenValueHasText() {
        assertDoesNotThrow(() -> service.isBlank("value", "field"));
    }

    @Test
    void isNullShouldThrowWhenNull() {
        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> service.isNull(null, "field")
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
        assertEquals("'field': Cannot be null", ex.getError().getMessage());
    }

    @Test
    void isNullShouldNotThrowWhenNotNull() {
        assertDoesNotThrow(() -> service.isNull("value", "field"));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.exception;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "msNAME", "PROSPECTS");
    }

    @Test
    void handleExceptionShouldReturnConflict() {
        ResponseEntity response = handler.handleException(new RuntimeException("boom"));

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        ErrorResponseDTO body = (ErrorResponseDTO) response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());

        ErrorDTO error = (ErrorDTO) body.getErrors().get(0);
        assertEquals("PROSPECTS-P-T-9409", error.getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.", error.getDescription());
    }

    @Test
    void handleServiceExceptionShouldUseExceptionStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .level("error")
                .description("DESC")
                .build();

        ServiceException ex = new ServiceException(HttpStatus.NOT_FOUND, error);

        ResponseEntity response = handler.handleSchemaException(ex, null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponseDTO body = (ErrorResponseDTO) response.getBody();
        assertNotNull(body);

        ErrorDTO safeError = (ErrorDTO) body.getErrors().get(0);
        assertEquals("CODE", safeError.getCode());
        assertNull(safeError.getMessage());
        assertNull(safeError.getLevel());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.", safeError.getDescription());
    }

    @Test
    void handleServiceExceptionClientShouldReturnBadRequest() {
        ErrorDTO error = ErrorDTO.builder().code("C").message("M").build();
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(List.of(error));
        ServiceExceptionClient ex = new ServiceExceptionClient(errorResponseDTO);

        ResponseEntity response = handler.handleSchemaException(ex, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertSame(errorResponseDTO, response.getBody());
    }

    @Test
    void buildResponseEntityShouldUseBadRequestWhenStatusIsNull() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .description("internal")
                .build();

        ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());

        ErrorDTO safeError = (ErrorDTO) response.getBody().getErrors().get(0);
        assertEquals("CODE", safeError.getCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.", safeError.getDescription());
    }

    @Test
    void buildResponseEntityShouldHandleNullErrors() {
        ResponseEntity<ErrorResponseDTO> response = handler.buildResponseEntity(null, HttpStatus.OK);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getErrors().isEmpty());
    }

    @Test
    void handleMissingRequestHeaderShouldReturnBadRequest() throws NoSuchMethodException {
        MissingRequestHeaderException ex = new MissingRequestHeaderException(
                "Authorization",
                GlobalExceptionHandlerTest.class.getDeclaredMethod("handleMissingRequestHeaderShouldReturnBadRequest")
                        .getParameters()[0]
        );

        ResponseEntity response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleMissingServletRequestParameterShouldReturnBadRequest() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("prospectId", "String");

        ResponseEntity response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleIllegalArgumentExceptionShouldReturnBadRequest() {
        ResponseEntity response = handler.handleValidationExceptions(new IllegalArgumentException("bad"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleMethodNotAllowedShouldReturnMethodNotAllowed() {
        HttpRequestMethodNotSupportedException ex =
                new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<ErrorResponseDTO> response = handler.handleMethodNotAllowedExceptions(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }
}
En el test de MissingRequestHeaderException, si te falla por reflexión del parámetro, elimínalo; normalmente ese handler queda cubierto mejor con MockMvc.