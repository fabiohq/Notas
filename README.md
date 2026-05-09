Te los dejo en el mismo orden. Para DTOs sin @Builder, solo va new, setters/getters y constructor completo.
ContextRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.context;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContextRequestTest {

    @Test
    void shouldCoverContextRequest() {
        Object value = new Object();

        ContextRequest dto = new ContextRequest();
        dto.setKey("KEY");
        dto.setValue(value);
        dto.setProduct("PRODUCT");

        assertEquals("KEY", dto.getKey());
        assertSame(value, dto.getValue());
        assertEquals("PRODUCT", dto.getProduct());

        ContextRequest allArgs = new ContextRequest("KEY", value, "PRODUCT");

        assertEquals("KEY", allArgs.getKey());
        assertSame(value, allArgs.getValue());
        assertEquals("PRODUCT", allArgs.getProduct());
    }
}
ContextResponseTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.context;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContextResponseTest {

    @Test
    void shouldCoverContextResponse() {
        Object value = new Object();

        ContextResponse dto = new ContextResponse();
        dto.setKey("KEY");
        dto.setValue(value);
        dto.setProduct("PRODUCT");

        assertEquals("KEY", dto.getKey());
        assertSame(value, dto.getValue());
        assertEquals("PRODUCT", dto.getProduct());

        ContextResponse allArgs = new ContextResponse("KEY", value, "PRODUCT");

        assertEquals("KEY", allArgs.getKey());
        assertSame(value, allArgs.getValue());
        assertEquals("PRODUCT", allArgs.getProduct());
    }
}
ApiEntryTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiEntryTest {

    @Test
    void shouldCoverApiEntry() {
        ApiEntry dto = new ApiEntry();

        dto.setIntegrationType("REST");
        dto.setHost("localhost");
        dto.setPort("8080");
        dto.setHttps(true);
        dto.setEndpoint("/api");
        dto.setTimeOutConn(1000);
        dto.setTimeOutRead(2000);

        assertEquals("REST", dto.getIntegrationType());
        assertEquals("localhost", dto.getHost());
        assertEquals("8080", dto.getPort());
        assertTrue(dto.isHttps());
        assertEquals("/api", dto.getEndpoint());
        assertEquals(1000, dto.getTimeOutConn());
        assertEquals(2000, dto.getTimeOutRead());

        ApiEntry allArgs = new ApiEntry("REST", "localhost", "8080", true, "/api", 1000, 2000);

        assertEquals("REST", allArgs.getIntegrationType());
        assertTrue(allArgs.isHttps());
        assertEquals(2000, allArgs.getTimeOutRead());
    }
}
SecurityHeadersTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersTest {

    @Test
    void shouldCoverSecurityHeaders() {
        SecurityHeaders dto = new SecurityHeaders();

        dto.setAuthorization("Bearer token");
        dto.setxSantanderClientId("CLIENT");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("CLIENT", dto.getxSantanderClientId());

        SecurityHeaders allArgs = new SecurityHeaders("Bearer token", "CLIENT");

        assertEquals("Bearer token", allArgs.getAuthorization());
        assertEquals("CLIENT", allArgs.getxSantanderClientId());
    }
}
DataListDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataListDTOTest {

    @Test
    void shouldCoverDataListDTO() {
        DataListDTO dto = new DataListDTO();

        dto.setListCode("LIST");
        dto.setCode("CODE");
        dto.setDescription("DESCRIPTION");

        assertEquals("LIST", dto.getListCode());
        assertEquals("CODE", dto.getCode());
        assertEquals("DESCRIPTION", dto.getDescription());

        DataListDTO builder = DataListDTO.builder()
                .listCode("LIST")
                .code("CODE")
                .description("DESCRIPTION")
                .build();

        assertEquals("LIST", builder.getListCode());

        DataListDTO allArgs = new DataListDTO("LIST", "CODE", "DESCRIPTION");

        assertEquals("DESCRIPTION", allArgs.getDescription());
    }
}
GeographiesParametersResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GeographiesParametersResponseDTOTest {

    @Test
    void shouldCoverGeographiesParametersResponseDTO() {
        List<DataListDTO> parameters = List.of(new DataListDTO());

        GeographiesParametersResponseDTO dto = new GeographiesParametersResponseDTO();
        dto.setParameters(parameters);

        assertSame(parameters, dto.getParameters());

        GeographiesParametersResponseDTO builder = GeographiesParametersResponseDTO.builder()
                .parameters(parameters)
                .build();

        assertSame(parameters, builder.getParameters());

        GeographiesParametersResponseDTO allArgs = new GeographiesParametersResponseDTO(parameters);

        assertSame(parameters, allArgs.getParameters());
    }
}
ClientEnumTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientEnumTest {

    @Test
    void shouldCoverClientEnum() {
        assertEquals("ingresoAltaPersonaNatural", ClientEnum.PEF1.value());
        assertEquals("modificarMantencionPersonaNaturalDatosBasicos", ClientEnum.PEF2.value());
        assertEquals("ConsultaDatosBasicosPNatural", ClientEnum.PEF3.value());
        assertEquals("modificarMantencionPersonaNaturalReferencias", ClientEnum.PEFT.value());
        assertEquals("modificarMantencionPersonaNaturalInfAdicional", ClientEnum.PEFP.value());
        assertEquals("modificarMantencionPersonaNaturalInfAdicional", ClientEnum.PEFV.value());
        assertEquals("AltaPersonaNatural2", ClientEnum.PE37.value());
        assertEquals("modificarMantencionPersonaNaturalInfComplementariaDos", ClientEnum.PEF4.value());
        assertEquals("modificarMantencionPersonaNaturalActivivadEconomica2", ClientEnum.PEF8.value());
        assertEquals("QCTFD", ClientEnum.MQROUTE.value());
    }
}
ParametersEnumsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParametersEnumsTest {

    @Test
    void shouldCoverParametersEnums() {
        assertEquals("0008", ParametersEnums.TOWNS.value());
        assertEquals("0112", ParametersEnums.COUNTRY.value());
        assertEquals("0009", ParametersEnums.STATES.value());
        assertEquals("0314", ParametersEnums.WAY_TYPE.value());
        assertEquals("0116", ParametersEnums.CIVIL_STATE.value());
        assertEquals("0026", ParametersEnums.LIST_BCO_EXT.value());
        assertEquals("0113", ParametersEnums.DOCU_TYPE.value());
    }
}
ErrorDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorDTOTest {

    @Test
    void shouldCoverErrorDTO() {
        ErrorDTO dto = new ErrorDTO();

        dto.setCode("CODE");
        dto.setMessage("MESSAGE");
        dto.setLevel("LEVEL");
        dto.setDescription("DESCRIPTION");

        assertEquals("CODE", dto.getCode());
        assertEquals("MESSAGE", dto.getMessage());
        assertEquals("LEVEL", dto.getLevel());
        assertEquals("DESCRIPTION", dto.getDescription());

        ErrorDTO builder = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .level("LEVEL")
                .description("DESCRIPTION")
                .build();

        assertEquals("CODE", builder.getCode());
        assertNotNull(builder.toString());

        ErrorDTO allArgs = new ErrorDTO("CODE", "MESSAGE", "LEVEL", "DESCRIPTION");

        assertEquals("DESCRIPTION", allArgs.getDescription());
    }
}
ErrorResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDTOTest {

    @Test
    void shouldCoverErrorResponseDTO() {
        List<ErrorDTO> errors = List.of(new ErrorDTO());

        ErrorResponseDTO dto = new ErrorResponseDTO();
        dto.setErrors(errors);

        assertSame(errors, dto.getErrors());
        assertNotNull(dto.toString());

        ErrorResponseDTO allArgs = new ErrorResponseDTO(errors);

        assertSame(errors, allArgs.getErrors());
    }
}
ErrorCatalogTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class ErrorCatalogTest {

    @Test
    void shouldCoverErrorCatalogConstantsAndAccessors() {
        assertEquals("-P-T-9400", ErrorCatalog.PT_400);
        assertEquals("-P-T-9404", ErrorCatalog.PT_404);
        assertEquals("-P-T-9409", ErrorCatalog.PT_409);
        assertEquals("-P-T-9429", ErrorCatalog.PT_429);
        assertEquals("-P-T-9500", ErrorCatalog.PT_500);
        assertEquals("-P-T-9503", ErrorCatalog.PT_503);

        ErrorDTO error = ErrorDTO.builder().code("CODE").message("MESSAGE").level("LEVEL").description("DESC").build();

        ErrorCatalog.setMsParametersNetworkConection(error);
        ErrorCatalog.setMsParametersResponse(error);
        ErrorCatalog.setMsParametersNoEntry(error);
        ErrorCatalog.setMsSambaNetworkConnection(error);
        ErrorCatalog.setMsParametersGeneral(error);
        ErrorCatalog.setMsSanbaTrxError(error);
        ErrorCatalog.setMsSanbaResponse(error);
        ErrorCatalog.setInvalidCustomerId(error);
        ErrorCatalog.setNonExistentPerson(error);

        assertSame(error, ErrorCatalog.getMsParametersNetworkConection());
        assertSame(error, ErrorCatalog.getMsParametersResponse());
        assertSame(error, ErrorCatalog.getMsParametersNoEntry());
        assertSame(error, ErrorCatalog.getMsSambaNetworkConnection());
        assertSame(error, ErrorCatalog.getMsParametersGeneral());
        assertSame(error, ErrorCatalog.getMsSanbaTrxError());
        assertSame(error, ErrorCatalog.getMsSanbaResponse());
        assertSame(error, ErrorCatalog.getInvalidCustomerId());
        assertSame(error, ErrorCatalog.getNonExistentPerson());
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<ErrorCatalog> constructor = ErrorCatalog.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
ErrorDictionaryTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorDictionaryTest {

    @Test
    void shouldCoverErrorDictionary() {
        assertEquals("CUSTOMER_CONTACT_POINTS", ErrorDictionary.MS_NAME);
        assertEquals("error", ErrorDictionary.ERROR_LEVEL);
        assertEquals("-api-service-v2: ", ErrorDictionary.MS_VERSION);
        assertEquals("Cannot connect to MS Parameters", ErrorDictionary.ERROR_MS_PARAMETERS_NETWORK);
        assertEquals("Error trying to process response", ErrorDictionary.ERROR_MS_PARAMETERS_RESPONSE);
        assertEquals("No entry founds", ErrorDictionary.ERROR_MS_PARAMETERS_NO_ENTRY);
        assertEquals("Cannot connect to MS Parameters", ErrorDictionary.ERROR_MS_PARAMETERS_GENERAL);
        assertEquals("Cannot connect to MS Sanba", ErrorDictionary.ERROR_MS_SANBA_NETWORK);
        assertEquals("Error in transaction execution", ErrorDictionary.ERROR_MS_SANBA_TRX);
        assertEquals("Invalid customer_id", ErrorDictionary.INVALID_CUSTOMER_ID);
        assertEquals("Customer not found", ErrorDictionary.NONEXISTENT_PERSON);
    }
}
ErrorTypeTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorTypeTest {

    @Test
    void shouldCoverErrorType() {
        assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
        assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
        assertEquals(2, ErrorType.values().length);
    }
}
ErrorServiceTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
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

        ReflectionTestUtils.setField(service, "msName", "MS");
        ReflectionTestUtils.setField(service, "msVersion", "v1");
        ReflectionTestUtils.setField(service, "errorLevel", "error");
        ReflectionTestUtils.setField(service, "functionalError", "F");
        ReflectionTestUtils.setField(service, "technicalError", "T");
        ReflectionTestUtils.setField(service, "blankData", "is blank");
        ReflectionTestUtils.setField(service, "invalidValue", "invalid");
    }

    @Test
    void shouldBuildFunctionalError() {
        ServiceException exception = service.errorBuilder(
                HttpStatus.BAD_REQUEST,
                "MESSAGE",
                ErrorType.FUNCTIONAL
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("MS-F-9400", exception.getError().getCode());
        assertEquals("MESSAGE", exception.getError().getMessage());
    }

    @Test
    void shouldBuildTechnicalError() {
        ServiceException exception = service.errorBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "MESSAGE",
                ErrorType.TECHNICAL
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getCode());
        assertEquals("MS-T-9500", exception.getError().getCode());
    }

    @Test
    void shouldThrowWhenBlank() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.isBlank("", "field")
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("'field' is blank", exception.getError().getMessage());
    }
}
ServiceExceptionTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void shouldCoverServiceException() {
        ServiceException empty = new ServiceException();
        empty.setCode(HttpStatus.BAD_REQUEST);
        empty.setError(new ErrorDTO());
        assertEquals(HttpStatus.BAD_REQUEST, empty.getCode());
        assertNotNull(empty.getError());

        ServiceException withMessage = new ServiceException(HttpStatus.NOT_FOUND, "MESSAGE");
        assertEquals(HttpStatus.NOT_FOUND, withMessage.getCode());
        assertEquals("MESSAGE", withMessage.getMessage());
        assertNotNull(withMessage.getTimestamp());

        ErrorDTO error = ErrorDTO.builder().message("ERROR").build();
        ServiceException withError = new ServiceException(HttpStatus.CONFLICT, error);

        assertEquals(HttpStatus.CONFLICT, withError.getCode());
        assertSame(error, withError.getError());
        assertEquals("ERROR", withError.getMessage());
        assertNotNull(withError.toString());
    }
}
ServiceExceptionClientTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionClientTest {

    @Test
    void shouldCoverServiceExceptionClient() {
        ErrorResponseDTO response = new ErrorResponseDTO();

        ServiceExceptionClient dto = new ServiceExceptionClient();
        dto.setErrorResponseDTO(response);

        assertSame(response, dto.getErrorResponseDTO());

        ServiceExceptionClient allArgs = new ServiceExceptionClient(response);

        assertSame(response, allArgs.getErrorResponseDTO());
        assertNotNull(allArgs.toString());
    }
}
GlobalExceptionHandlerTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "msName", "MS");
    }

    @Test
    void shouldHandleGenericException() {
        ResponseEntity response = handler.handleException(new RuntimeException("ERROR"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponseDTO);
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        ResponseEntity response = handler.handleValidationExceptions(new IllegalArgumentException("BAD"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponseDTO);
    }

    @Test
    void shouldHandleMissingParameterException() {
        MissingServletRequestParameterException ex =
                new MissingServletRequestParameterException("id", "String");

        ResponseEntity response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldHandleMissingHeaderException() {
        MissingRequestHeaderException ex =
                new MissingRequestHeaderException("x-test", null);

        ResponseEntity response = handler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldHandleServiceException() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .description("DESCRIPTION")
                .build();

        ServiceException exception = new ServiceException(HttpStatus.CONFLICT, error);

        ResponseEntity response = handler.handleSchemaException(exception, null);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void shouldHandleServiceExceptionClient() {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrors(List.of(ErrorDTO.builder().message("MESSAGE").build()));

        ServiceExceptionClient exception = new ServiceExceptionClient(errorResponse);

        ResponseEntity response = handler.handleSchemaException(exception, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldBuildResponseEntityWithNullStatus() {
        ErrorDTO error = ErrorDTO.builder()
                .code("CODE")
                .message("MESSAGE")
                .description("SECRET")
                .build();

        ResponseEntity response = handler.buildResponseEntity(List.of(error), null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ErrorResponseDTO body = (ErrorResponseDTO) response.getBody();

        assertNotNull(body);
        assertEquals("CODE", body.getErrors().get(0).getCode());
        assertEquals(
                "Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                body.getErrors().get(0).getDescription()
        );
    }
}
ExternalApisHealthPropertiesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldCoverExternalApisHealthProperties() {
        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

        check.setName("api");
        check.setUrl("http://localhost");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 204));

        assertEquals("api", check.getName());
        assertEquals("http://localhost", check.getUrl());
        assertFalse(check.isCritical());
        assertEquals(List.of(200, 204), check.getAcceptedStatuses());

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(check));

        assertEquals(1000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertSame(check, properties.getChecks().get(0));
    }
}
ExternalApisHealthIndicatorTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.observability;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;

import java.net.InetSocketAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthIndicatorTest {

    @Test
    void shouldReturnUpWhenCriticalApiIsUp() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        });
        server.start();

        try {
            int port = server.getAddress().getPort();

            ExternalApisHealthProperties.ApiCheck check =
                    new ExternalApisHealthProperties.ApiCheck();

            check.setName("api");
            check.setUrl("http://localhost:" + port + "/health");
            check.setCritical(true);
            check.setAcceptedStatuses(List.of(200));

            ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
            properties.setTimeoutMs(1000);
            properties.setChecks(List.of(check));

            ExternalApisHealthIndicator indicator =
                    new ExternalApisHealthIndicator(properties);

            Health health = indicator.health();

            assertEquals("UP", health.getStatus().getCode());
            assertTrue(health.getDetails().containsKey("api"));
        } finally {
            server.stop(0);
        }
    }

    @Test
    void shouldReturnDownWhenCriticalApiIsDown() {
        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        check.setName("api");
        check.setUrl("http://localhost:1/not-found");
        check.setCritical(true);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(check));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals("DOWN", health.getStatus().getCode());
        assertTrue(health.getDetails().containsKey("api"));
    }

    @Test
    void shouldReturnUpWhenNonCriticalApiIsDown() {
        ExternalApisHealthProperties.ApiCheck check =
                new ExternalApisHealthProperties.ApiCheck();

        check.setName("api");
        check.setUrl("http://localhost:1/not-found");
        check.setCritical(false);

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);
        properties.setChecks(List.of(check));

        ExternalApisHealthIndicator indicator =
                new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals("UP", health.getStatus().getCode());
    }
}
Para GlobalExceptionHandlerTest, si te falla el constructor de MissingRequestHeaderException por versión de Spring, dime qué versión usas y te lo ajusto exacto.