// ============================================================================
// RegexUtilsTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();
        ReflectionTestUtils.setField(regexUtils, "msName", "bncbsn049");
        ReflectionTestUtils.setField(regexUtils, "msVersion", "v3");
        ReflectionTestUtils.setField(regexUtils, "level", "error");
        ReflectionTestUtils.setField(regexUtils, "code", "P-F-9400");

        ReflectionTestUtils.setField(regexUtils, "regexProductFormat", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "regexProductFormatError", "only numbers");
        ReflectionTestUtils.setField(regexUtils, "regexProductLength", "^[0-9]{6}$");
        ReflectionTestUtils.setField(regexUtils, "regexProductLengthError", "invalid product length");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferCodeNumerLength", "^[0-9]{4}$");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferCodeNumerLengthError", "invalid code length");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferProductCodeLength", "^[0-9]{6}$");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferProductCodeLengthError", "invalid product code length");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferChannelCodeFormat", "^[A-Z]+$");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferChannelCodeFormatError", "invalid channel format");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferChannelCodeLength", "^[A-Z]{3}$");
        ReflectionTestUtils.setField(regexUtils, "regexCommercialOfferChannelCodeLengthError", "invalid channel length");
    }

    @Test
    void validateRegex_whenValueMatches_shouldNotThrow() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "123456", "product_id"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PRODUCT_LENGTH, "123456", "product_id"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CODE_NUMBER_LENGTH, "1234", "bank_id"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_PRODUCT_CODE_LENGTH, "123456", "product_code"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CHANNEL_CODE_FORMAT, "ABC", "channel_code"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CHANNEL_CODE_LENGTH, "ABC", "channel_code"));
    }

    @Test
    void validateRegex_whenValueDoesNotMatch_shouldThrowServiceException() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "product_id"));

        assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("bncbsn049-P-F-9400", exception.getError().getCode());
        assertEquals("error", exception.getError().getLevel());
        assertEquals("'product_id': only numbers", exception.getError().getMessage());
        assertTrue(exception.getError().getDescription().contains("bncbsn049-v3: 'product_id': only numbers"));
    }
}

// ============================================================================
// ServiceDirectoryTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceDirectoryTest {

    @Test
    void product_shouldExposeExpectedEndpoint() {
        assertEquals("/v3/product", ServiceDirectory.PRODUCT);
    }

    @Test
    void constructor_whenInvoked_shouldThrowIllegalStateException() throws Exception {
        Constructor<ServiceDirectory> constructor = ServiceDirectory.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(IllegalStateException.class, exception.getCause().getClass());
        assertEquals("Utility class", exception.getCause().getMessage());
    }
}

// ============================================================================
// AmountResponseDtoTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AmountResponseDtoTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetValues() {
        AmountResponseDto dto = new AmountResponseDto();
        MaxAndMinAmountDto min = new MaxAndMinAmountDto("100", "MXN");
        MaxAndMinAmountDto max = new MaxAndMinAmountDto("5000", "MXN");

        dto.setMinimumAmount(min);
        dto.setMaximumAmount(max);

        assertEquals(min, dto.getMinimumAmount());
        assertEquals(max, dto.getMaximumAmount());
    }

    @Test
    void builder_shouldCreateDtoWithValues() {
        MaxAndMinAmountDto min = MaxAndMinAmountDto.builder().amount("100").currency("MXN").build();
        MaxAndMinAmountDto max = MaxAndMinAmountDto.builder().amount("5000").currency("MXN").build();

        AmountResponseDto dto = AmountResponseDto.builder()
                .minimumAmount(min)
                .maximumAmount(max)
                .build();

        assertNotNull(dto);
        assertEquals("100", dto.getMinimumAmount().getAmount());
        assertEquals("5000", dto.getMaximumAmount().getAmount());

        AmountResponseDto emptyDto = new AmountResponseDto();
        assertNull(emptyDto.getMinimumAmount());
        assertNull(emptyDto.getMaximumAmount());
    }
}

// ============================================================================
// MaxAndMinAmountDtoTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MaxAndMinAmountDtoTest {

    @Test
    void noArgsConstructorAndSetters_shouldSetValues() {
        MaxAndMinAmountDto dto = new MaxAndMinAmountDto();

        dto.setAmount("100");
        dto.setCurrency("MXN");

        assertEquals("100", dto.getAmount());
        assertEquals("MXN", dto.getCurrency());
    }

    @Test
    void builderAndAllArgsConstructor_shouldCreateDtoWithValues() {
        MaxAndMinAmountDto built = MaxAndMinAmountDto.builder()
                .amount("200")
                .currency("COP")
                .build();

        MaxAndMinAmountDto allArgs = new MaxAndMinAmountDto("300", "USD");
        MaxAndMinAmountDto empty = new MaxAndMinAmountDto();

        assertNotNull(built);
        assertEquals("200", built.getAmount());
        assertEquals("COP", built.getCurrency());
        assertEquals("300", allArgs.getAmount());
        assertEquals("USD", allArgs.getCurrency());
        assertNull(empty.getAmount());
        assertNull(empty.getCurrency());
    }
}

// ============================================================================
// TermDTOTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TermDTOTest {

    @Test
    void noArgsConstructorAndSetter_shouldSetDays() {
        TermDTO dto = new TermDTO();

        dto.setDays(30);

        assertEquals(30, dto.getDays());
    }

    @Test
    void builderAndAllArgsConstructor_shouldCreateDtoWithDays() {
        TermDTO built = TermDTO.builder().days(60).build();
        TermDTO allArgs = new TermDTO(90);
        TermDTO empty = new TermDTO();

        assertNotNull(built);
        assertEquals(60, built.getDays());
        assertEquals(90, allArgs.getDays());
        assertNull(empty.getDays());
    }
}

// ============================================================================
// TermsDTOTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TermsDTOTest {

    @Test
    void noArgsConstructorAndSetter_shouldSetTerms() {
        TermsDTO dto = new TermsDTO();
        List<TermDTO> terms = List.of(new TermDTO(30), new TermDTO(60));

        dto.setTerms(terms);

        assertEquals(2, dto.getTerms().size());
        assertEquals(30, dto.getTerms().get(0).getDays());
    }

    @Test
    void builderAndAllArgsConstructor_shouldCreateDtoWithTerms() {
        List<TermDTO> terms = List.of(TermDTO.builder().days(90).build());
        TermsDTO built = TermsDTO.builder().terms(terms).build();
        TermsDTO allArgs = new TermsDTO(terms);
        TermsDTO empty = new TermsDTO();

        assertNotNull(built);
        assertEquals(90, built.getTerms().get(0).getDays());
        assertEquals(1, allArgs.getTerms().size());
        assertNull(empty.getTerms());
    }
}

// ============================================================================
// ErrorServiceTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();
        ReflectionTestUtils.setField(errorService, "msName", "BNCBSN049");
        ReflectionTestUtils.setField(errorService, "msVersion", "v3");
        ReflectionTestUtils.setField(errorService, "errorLevel", "error");
        ReflectionTestUtils.setField(errorService, "functionalError", "P-F");
        ReflectionTestUtils.setField(errorService, "techinicalError", "P-T");
        ReflectionTestUtils.setField(errorService, "blankValue", "is blank");
    }

    @Test
    void errorBuilder_whenFunctionalAndTechnical_shouldBuildExpectedServiceException() {
        ServiceException functional = errorService.errorBuilder(HttpStatus.NOT_FOUND, "not found", ErrorType.FUNCTIONAL);
        ServiceException technical = errorService.errorBuilder(HttpStatus.CONFLICT, "conflict", ErrorType.TECHNICAL);

        assertEquals(HttpStatus.NOT_FOUND, functional.getCode());
        assertEquals("BNCBSN049-P-F-9404", functional.getError().getCode());
        assertEquals("not found", functional.getError().getMessage());
        assertEquals("error", functional.getError().getLevel());
        assertEquals("bncbsn049-v3: not found", functional.getError().getDescription());

        assertEquals(HttpStatus.CONFLICT, technical.getCode());
        assertEquals("BNCBSN049-P-T-9409", technical.getError().getCode());
    }

    @Test
    void isBlank_whenBlankThrowsAndWhenNotBlankDoesNotThrow() {
        ServiceException exception = assertThrows(ServiceException.class,
                () -> errorService.isBlank("   ", "bank_id"));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        assertEquals("'bank_id' is blank", exception.getError().getMessage());
        assertDoesNotThrow(() -> errorService.isBlank("1234", "bank_id"));
    }
}

// ============================================================================
// ProductsMappersTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.mappers;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProductsMappersTest {

    @Test
    void init_shouldCreateModelMapper() {
        ProductsMappers productsMappers = new ProductsMappers();

        productsMappers.init();

        assertNotNull(productsMappers.getMapper());
    }

    @Test
    void setMapper_shouldReplaceMapper() {
        ProductsMappers productsMappers = new ProductsMappers();
        ModelMapper mapper = new ModelMapper();

        productsMappers.setMapper(mapper);

        assertSame(mapper, productsMappers.getMapper());
    }
}

// ============================================================================
// ExternalApisHealthPropertiesTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalApisHealthPropertiesTest {

    @Test
    void properties_shouldSetTimeoutAndChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        ExternalApisHealthProperties.ApiCheck apiCheck = new ExternalApisHealthProperties.ApiCheck();
        apiCheck.setName("customers");
        apiCheck.setUrl("http://localhost:9999/health");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(200, 204));

        properties.setTimeoutMs(500);
        properties.setChecks(List.of(apiCheck));

        assertEquals(500, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());
        assertEquals("customers", properties.getChecks().get(0).getName());
    }

    @Test
    void apiCheckDefaultsAndSetters_shouldWork() {
        ExternalApisHealthProperties.ApiCheck apiCheck = new ExternalApisHealthProperties.ApiCheck();

        assertTrue(apiCheck.isCritical());

        apiCheck.setName("products");
        apiCheck.setUrl("http://localhost/test");
        apiCheck.setCritical(false);
        apiCheck.setAcceptedStatuses(List.of(202));

        assertEquals("products", apiCheck.getName());
        assertEquals("http://localhost/test", apiCheck.getUrl());
        assertFalse(apiCheck.isCritical());
        assertEquals(List.of(202), apiCheck.getAcceptedStatuses());
    }
}

// ============================================================================
// ExternalApisHealthIndicatorTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.observability;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalApisHealthIndicatorTest {

    private HttpServer server;

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void health_whenCriticalApiIsUp_shouldReturnUp() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/health", exchange -> {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
        });
        server.start();

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("external-api");
        check.setUrl("http://localhost:" + server.getAddress().getPort() + "/health");
        check.setCritical(true);
        check.setAcceptedStatuses(List.of(204));

        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(1000);
        properties.setChecks(List.of(check));

        Health health = new ExternalApisHealthIndicator(properties).health();

        assertEquals(Status.UP, health.getStatus());
        assertNotNull(health.getDetails().get("external-api"));
    }

    @Test
    void health_whenCriticalApiIsDownAndNonCriticalFails_shouldReturnExpectedStatuses() {
        ExternalApisHealthProperties.ApiCheck critical = new ExternalApisHealthProperties.ApiCheck();
        critical.setName("critical-api");
        critical.setUrl("http://localhost:1/health");
        critical.setCritical(true);

        ExternalApisHealthProperties.ApiCheck nonCritical = new ExternalApisHealthProperties.ApiCheck();
        nonCritical.setName("non-critical-api");
        nonCritical.setUrl("http://localhost:1/health");
        nonCritical.setCritical(false);

        ExternalApisHealthProperties criticalProperties = new ExternalApisHealthProperties();
        criticalProperties.setTimeoutMs(100);
        criticalProperties.setChecks(List.of(critical));

        ExternalApisHealthProperties nonCriticalProperties = new ExternalApisHealthProperties();
        nonCriticalProperties.setTimeoutMs(100);
        nonCriticalProperties.setChecks(List.of(nonCritical));

        Health criticalHealth = new ExternalApisHealthIndicator(criticalProperties).health();
        Health nonCriticalHealth = new ExternalApisHealthIndicator(nonCriticalProperties).health();

        assertEquals(Status.DOWN, criticalHealth.getStatus());
        assertEquals(Status.UP, nonCriticalHealth.getStatus());
        assertTrue(criticalHealth.getDetails().containsKey("critical-api"));
        assertTrue(nonCriticalHealth.getDetails().containsKey("non-critical-api"));
    }
}

// ============================================================================
// GlobalExceptionHandlerTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        ReflectionTestUtils.setField(handler, "msName", "BNCBSN049");
    }

    @Test
    void handleBasicExceptions_shouldReturnExpectedStatusAndSanitizedDescription() {
        ResponseEntity<ErrorResponseDTO> generic = handler.handleException(new RuntimeException("boom"));
        ResponseEntity<ErrorResponseDTO> illegalArgument = handler.handleValidationExceptions(new IllegalArgumentException("bad argument"));
        ResponseEntity<ErrorResponseDTO> body = handler.handleValidationExceptions(
                new org.springframework.http.converter.HttpMessageNotReadableException("bad body"));
        ResponseEntity<ErrorResponseDTO> method = handler.handleMethodNotAllowedExceptions(
                new org.springframework.web.HttpRequestMethodNotSupportedException("POST"));

        assertEquals(HttpStatus.CONFLICT, generic.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, illegalArgument.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, body.getStatusCode());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, method.getStatusCode());
        assertEquals("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.",
                generic.getBody().getErrors().get(0).getDescription());
    }

    @Test
    void handleSpecificExceptions_shouldReturnExpectedStatusAndCode() {
        ResponseEntity<ErrorResponseDTO> missingParam = handler.handleValidationExceptions(
                new MissingServletRequestParameterException("bank_id", "String"));
        ResponseEntity<ErrorResponseDTO> missingHeader = handler.handleValidationExceptions(
                new MissingRequestHeaderException("Authorization", null));

        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("BNCBSN049-P-F-9404")
                .level("error")
                .message("service error")
                .description("technical internal detail")
                .build();
        ResponseEntity<ErrorResponseDTO> service = handler.handleSchemaException(
                new ServiceException(HttpStatus.NOT_FOUND, errorDTO), null);

        ErrorResponseDTO clientErrorResponse = new ErrorResponseDTO();
        clientErrorResponse.setErrors(List.of(ErrorDTO.builder()
                .code("MS_NAME-P-F-9400")
                .description("MS_NAME-api-services-v3: client error")
                .message("client error")
                .level("error")
                .build()));
        ResponseEntity<ErrorResponseDTO> client = handler.buildResponseEntity2(clientErrorResponse, HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, missingParam.getStatusCode());
        assertEquals("BNCBSN049-P-F-9400", missingParam.getBody().getErrors().get(0).getCode());
        assertEquals(HttpStatus.BAD_REQUEST, missingHeader.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, service.getStatusCode());
        assertEquals("BNCBSN049-P-F-9404", service.getBody().getErrors().get(0).getCode());
        assertEquals(HttpStatus.BAD_REQUEST, client.getStatusCode());
        assertNotNull(client.getBody());
    }
}

// ============================================================================
// ProductControllersTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.CommerccialOfferDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response.AmountResponseDto;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermsDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllersTest {

    private ProductsService productsService;
    private ProductControllers controller;

    @BeforeEach
    void setUp() {
        productsService = mock(ProductsService.class);
        controller = new ProductControllers(new ObjectMapper());
        ReflectionTestUtils.setField(controller, "productsService", productsService);
    }

    @Test
    void endpoints_whenServiceReturnsData_shouldReturnOkResponses() {
        CommerccialOfferDTO commercialOffer = new CommerccialOfferDTO();
        TermsDTO terms = TermsDTO.builder().terms(List.of(new TermDTO(30))).build();
        AmountResponseDto amount = new AmountResponseDto();

        when(productsService.getCommercialOffer("0049", "1234", "040250", "WEB")).thenReturn(commercialOffer);
        when(productsService.getProductTerms("040250")).thenReturn(terms);
        when(productsService.getProductAmountRange("040250")).thenReturn(amount);

        CommerccialOfferDTO commercialResponse = controller.getCommercialOffer(
                "0049", "1234", "040250", "WEB", "Bearer token", "client-id");
        ResponseEntity<TermsDTO> termsResponse = controller.getTProductTerms("040250", "Bearer token", "client-id");
        ResponseEntity<AmountResponseDto> amountResponse = controller.amountRange("040250", "Bearer token", "client-id");

        assertSame(commercialOffer, commercialResponse);
        assertEquals(HttpStatus.OK, termsResponse.getStatusCode());
        assertSame(terms, termsResponse.getBody());
        assertEquals(HttpStatus.OK, amountResponse.getStatusCode());
        assertSame(amount, amountResponse.getBody());
    }

    @Test
    void endpoints_whenNullOrSerializationFails_shouldCoverExceptionBranches() throws JsonProcessingException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        ProductControllers localController = new ProductControllers(objectMapper);
        ReflectionTestUtils.setField(localController, "productsService", productsService);

        TermsDTO terms = TermsDTO.builder().terms(List.of(new TermDTO(30))).build();
        AmountResponseDto amount = new AmountResponseDto();
        when(objectMapper.writeValueAsString(org.mockito.ArgumentMatchers.any())).thenThrow(new RuntimeException("json error"));
        when(productsService.getProductTerms("040250")).thenReturn(terms);
        when(productsService.getProductAmountRange("040250")).thenReturn(amount);
        when(productsService.getProductTerms("000000")).thenReturn(null);
        when(productsService.getProductAmountRange("000000")).thenReturn(null);

        assertEquals(HttpStatus.OK, localController.getTProductTerms("040250", "Bearer token", "client-id").getStatusCode());
        assertEquals(HttpStatus.OK, localController.amountRange("040250", "Bearer token", "client-id").getStatusCode());
        assertThrows(ServiceException.class,
                () -> localController.getTProductTerms("000000", "Bearer token", "client-id"));
        assertThrows(ServiceException.class,
                () -> localController.amountRange("000000", "Bearer token", "client-id"));
    }
}

// ============================================================================
// ProductsServiceImplTest.java
// ============================================================================
package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.service.impl;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.CommerccialOfferDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response.AmountResponseDto;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermsDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils.RegexTypes;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductsServiceImplTest {

    private ErrorService errorService;
    private RegexUtils regexUtils;
    private ProductsServiceImpl service;

    @BeforeEach
    void setUp() {
        errorService = mock(ErrorService.class);
        regexUtils = mock(RegexUtils.class);
        service = new ProductsServiceImpl(errorService, regexUtils);

        ReflectionTestUtils.setField(service, "product", "040250");
        ReflectionTestUtils.setField(service, "minimumAmount", "100");
        ReflectionTestUtils.setField(service, "maximumAmount", "5000");
        ReflectionTestUtils.setField(service, "currency", "MXN");
        ReflectionTestUtils.setField(service, "bankIdCodeRrrorMessage", "bank not found");
        ReflectionTestUtils.setField(service, "centerIdCodeErrorMessage", "center not found");
        ReflectionTestUtils.setField(service, "productIdCodeErrorMessage", "product not found");
        ReflectionTestUtils.setField(service, "channelIdCodeErrorMessage", "channel not found");
        ReflectionTestUtils.setField(service, "centerIdCode", "1234");
        ReflectionTestUtils.setField(service, "bankIdCode", "0049");
        ReflectionTestUtils.setField(service, "channelCodeId", "WEB");
        ReflectionTestUtils.setField(service, "productId", "PRODUCT-ID");
        ReflectionTestUtils.setField(service, "offerName", "Offer name");
        ReflectionTestUtils.setField(service, "offerDesription", "Offer description");
        ReflectionTestUtils.setField(service, "offerProductCode", "040250");
        ReflectionTestUtils.setField(service, "offerProductDescription", "Product description");
        ReflectionTestUtils.setField(service, "subproductsSubproductsId", "SUB-1");
        ReflectionTestUtils.setField(service, "subproductsName", "Subproduct name");
        ReflectionTestUtils.setField(service, "termDays", new ArrayList<>(java.util.List.of(30, 60)));
    }

    @Test
    void serviceMethods_whenValidInput_shouldReturnExpectedResponses() {
        TermsDTO terms = service.getProductTerms("040250");
        AmountResponseDto amountRange = service.getProductAmountRange("040250");
        CommerccialOfferDTO offer = service.getCommercialOffer("0049", "1234", "040250", "WEB");

        assertEquals(2, terms.getTerms().size());
        assertEquals(30, terms.getTerms().get(0).getDays());
        assertEquals("100", amountRange.getMinimumAmount().getAmount());
        assertEquals("5000", amountRange.getMaximumAmount().getAmount());
        assertNotNull(offer.getOffer());
        assertEquals("Offer name", offer.getOffer().get(0).getName());

        verify(regexUtils).validateRegex(RegexTypes.ONLY_NUMBERS, "040250", "product_id");
        verify(regexUtils).validateRegex(RegexTypes.PRODUCT_LENGTH, "040250", "product_id");
        verify(regexUtils).validateRegex(RegexTypes.ONLY_NUMBERS, "040250", "productId");
        verify(regexUtils).validateRegex(RegexTypes.PRODUCT_LENGTH, "040250", "productId");
    }

    @Test
    void serviceMethods_whenInvalidInput_shouldThrowExpectedExceptions() {
        ServiceException blankProduct = assertThrows(ServiceException.class, () -> service.getProductTerms(" "));
        ServiceException productNotExistTerms = assertThrows(ServiceException.class, () -> service.getProductTerms("999999"));
        ServiceException productNotExistAmount = assertThrows(ServiceException.class, () -> service.getProductAmountRange("999999"));

        ServiceException regexException = new ServiceException(HttpStatus.BAD_REQUEST,
                com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog.BLANK_DATA);
        doThrow(regexException).when(regexUtils).validateRegex(eq(RegexTypes.ONLY_NUMBERS), eq("ABC"), eq("productId"));
        ServiceException regexThrown = assertThrows(ServiceException.class, () -> service.getProductAmountRange("ABC"));

        ServiceException bankError = new ServiceException(HttpStatus.NOT_FOUND,
                com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog.PRODUCT_NOT_EXIST);
        when(errorService.errorBuilder(eq(HttpStatus.NOT_FOUND), eq("bank not found"), eq(ErrorType.FUNCTIONAL))).thenReturn(bankError);
        ServiceException commercialError = assertThrows(ServiceException.class,
                () -> service.getCommercialOffer("9999", "1234", "040250", "WEB"));

        assertEquals(HttpStatus.BAD_REQUEST, blankProduct.getCode());
        assertEquals(HttpStatus.NOT_FOUND, productNotExistTerms.getCode());
        assertEquals(HttpStatus.NOT_FOUND, productNotExistAmount.getCode());
        assertEquals(HttpStatus.BAD_REQUEST, regexThrown.getCode());
        assertEquals(HttpStatus.NOT_FOUND, commercialError.getCode());
    }
}
