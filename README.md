Te dejo los tests que faltan para utils y service.impl.
ClientUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ClientUtilsTest {

    @Test
    void shouldBuildTrxRequestByCustomerId() {
        TrxPersonRequest request = ClientUtils.buildTrxRequestByCustomerId(null, "12345678");

        assertNotNull(request);
        assertNotNull(request.getData());
        assertEquals("12345678", request.getData().getPenumpe());
    }

    @Test
    void shouldBuildTrxRequestByNullCustomerId() {
        TrxPersonRequest request = ClientUtils.buildTrxRequestByCustomerId(null, null);

        assertEquals("", request.getData().getPenumpe());
    }

    @Test
    void shouldBuildHeader() {
        TrxPersonHeader header = ClientUtils.buildHeader("ROUTE");

        assertEquals("ROUTE", header.getRutaServicio());
        assertEquals("Intro", header.getFuncion());
        assertEquals("60", header.getCanal());
        assertEquals(44204, header.getSecuencia());
        assertNotNull(header.getSesion());
        assertEquals("@NETE781", header.getSesion().getUsuario());
        assertEquals("0065", header.getSesion().getEntidad());
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<ClientUtils> constructor = ClientUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
CustomerMapperUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.PlaceOfBirthDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperUtilsTest {

    @Test
    void shouldMapPersonDTOWithMaleGender() {
        TrxPersonData data = buildPersonData("H");
        Parameters params = buildParameters();

        PersonDTO result = CustomerMapperUtils.personDTONames(data, params);

        assertEquals("H", result.getGenderCode());
        assertEquals("HOMBRE", result.getGenderDescription());
        assertEquals("JUAN", result.getPersonName().getGivenName());
        assertEquals("JUAN PEREZ GOMEZ", result.getPersonName().getFullName());
        assertEquals("2024-01-01", result.getBirthDate());
        assertSame(params.getCountryNationality(), result.getFirstNationality());
    }

    @Test
    void shouldMapPersonDTOWithFemaleGender() {
        TrxPersonData data = buildPersonData("F");
        Parameters params = buildParameters();

        PersonDTO result = CustomerMapperUtils.personDTONames(data, params);

        assertEquals("M", result.getGenderCode());
        assertEquals("MUJER", result.getGenderDescription());
    }

    @Test
    void shouldGetPlaceOfBirth() {
        Parameters params = buildParameters();

        PlaceOfBirthDTO result = CustomerMapperUtils.getPlaceOfBirth(params);

        assertSame(params.getCountryBirth(), result.getCountry());
        assertSame(params.getCityBirth(), result.getState());
        assertEquals("Bogota", result.getTown());
    }

    @Test
    void shouldGetSourceCode() {
        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ABC123");

        assertEquals("ABC", CustomerMapperUtils.getSourceCode(data));
    }

    @Test
    void shouldReturnNullWhenSourceCodeIsBlank() {
        TrxPersonData data = new TrxPersonData();
        data.setUsualt("");

        assertNull(CustomerMapperUtils.getSourceCode(data));
    }

    @Test
    void shouldGetContactPoint() {
        TrxPersonData data = buildPersonData("H");
        Parameters params = buildParameters();

        ContactPointDTO result = CustomerMapperUtils.getContactPoint(data, params);

        assertEquals("PRI001", result.getContactPointId());
        assertEquals("300", result.getPhoneAddress().getInternationalCode());
        assertEquals("601123", result.getPhoneAddress().getPhoneNumber());
        assertEquals("300123", result.getPhoneAddress().getMobileNumber());
        assertEquals("test@mail.com", result.getElectronicAddress().getEmailAddress());
        assertNotNull(result.getPostalAddress());
    }

    @Test
    void shouldReturnForeignTaxIndicatorYes() {
        CustomerMapperUtils utils = new CustomerMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "YES");

        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ABCYES1");

        assertEquals("YES", utils.getForeignTaxIndicator(data));
    }

    @Test
    void shouldReturnForeignTaxIndicatorNo() {
        CustomerMapperUtils utils = new CustomerMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "YES");

        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ABCNOOO");

        assertEquals("NO", utils.getForeignTaxIndicator(data));
    }

    @Test
    void shouldGetPostalAddressBasic() {
        TrxPersonData data = buildPersonData("H");
        Parameters params = buildParameters();

        PostalAddressDTO result = CustomerMapperUtils.getPostalAddressBasic(data, params);

        assertEquals("CALLE 1", result.getFullAddress());
        assertEquals("CL", result.getStreetTypeCode());
        assertEquals("CALLE", result.getStreetTypeDescription());
        assertEquals("APT 101", result.getPremise());
    }

    @Test
    void shouldGetDocumentBasics() {
        TrxPersonData data = buildPersonData("H");
        Parameters params = buildParameters();

        com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO result =
                CustomerMapperUtils.getDocumentBasics(data, params);

        assertEquals("CC", result.getDocumentTypeCode());
        assertEquals("Cedula", result.getDocumentTypeDescription());
        assertEquals("123", result.getDocumentNumber());
        assertEquals("2024-01-02", result.getIssueDate());
    }

    @Test
    void shouldValidateNotCustomer() {
        assertTrue(CustomerMapperUtils.isNotCustomer("PRO"));
        assertTrue(CustomerMapperUtils.isNotCustomer("pro"));
        assertFalse(CustomerMapperUtils.isNotCustomer("CLI"));
    }

    @Test
    void shouldValidatePenumper() {
        assertTrue(CustomerMapperUtils.isPenumperValid("12345678"));
        assertFalse(CustomerMapperUtils.isPenumperValid("ABC"));
        assertFalse(CustomerMapperUtils.isPenumperValid("123"));
    }

    private TrxPersonData buildPersonData(String sexo) {
        TrxPersonData data = new TrxPersonData();
        data.setSexo(sexo);
        data.setNombre("JUAN");
        data.setPrimerApellido("PEREZ");
        data.setSegundoApellido("GOMEZ");
        data.setFechaNacimiento("2024-01-01");
        data.setFechaExpedicion("2024-01-02");
        data.setTipoIdentificacion("CC");
        data.setNumeroIdentificacion("123");
        data.setPrecelular("300");
        data.setTelefono("601123");
        data.setCelular("300123");
        data.setEmail("test@mail.com");
        data.setNombreVia("CALLE 1");
        data.setTipoVia("CL");
        data.setDescripcionDireccion("APT 101");
        return data;
    }

    private Parameters buildParameters() {
        Parameters params = new Parameters();
        params.setCountryNationality(new CodeNameDTO("CO", "Colombia"));
        params.setCountryDir(new CodeNameDTO("CO", "Colombia"));
        params.setCountryBirth(new CodeNameDTO("CO", "Colombia"));
        params.setCountryExp(new CodeNameDTO("CO", "Colombia"));
        params.setCityBirth(new CodeNameDTO("11", "Bogota"));
        params.setCityStandard(new CodeNameDTO("11", "Bogota"));
        params.setCityDepartment(new CodeNameDTO("11", "Bogota"));
        params.setTown(new CodeNameDTO("11001", "Bogota"));
        params.setStreetTypeDescription("CALLE");
        params.setDocumentTypeDescription("Cedula");
        return params;
    }
}
Ojo: si te marca error en este import:
Java
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.DocumentDTO;
elimínalo. En tu proyecto DocumentDTO está en domain.customer.generic.
GUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GUtilsTest {

    static class Dummy {
        private String name;
        private String email;

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @Test
    void shouldGetNullPropertyNames() {
        Dummy dummy = new Dummy();
        dummy.setName("Fabio");

        String[] result = GUtils.getNullPropertyNames(dummy);

        assertTrue(Arrays.asList(result).contains("email"));
        assertFalse(Arrays.asList(result).contains("name"));
    }

    @Test
    void shouldCoverConstants() {
        assertEquals("--> Start ", GUtils.SLOG);
        assertEquals("<-- End ", GUtils.ELOG);
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<GUtils> constructor = GUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
RegexTypesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexTypesTest {

    @Test
    void shouldCoverRegexTypes() {
        assertEquals(RegexTypes.ONLY_NUMBERS, RegexTypes.valueOf("ONLY_NUMBERS"));
        assertEquals(RegexTypes.EMAIL, RegexTypes.valueOf("EMAIL"));
        assertTrue(RegexTypes.values().length > 0);
    }
}
RegexUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "MS");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "P-F-9400");

        ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS_ERROR", "only numbers");

        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8", "^.{8}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8_ERROR", "length 8");

        ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2", "^.{2}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2_ERROR", "length 2");

        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH", "^.{1,50}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH_ERROR", "address length");

        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT", "^[A-Za-z0-9 #]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT_ERROR", "address format");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL", "^[^@]+@[^@]+\\.[^@]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_ERROR", "email error");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_LENGTH", "^.{1,60}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_LENGTH_ERROR", "email length");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_LEFT", "^[A-Za-z0-9._-]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_LEFT_ERROR", "email left");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_RIGHT", "^[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_RIGHT_ERROR", "email right");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_FIRST_CHAR", "^[A-Za-z0-9]$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_FIRST_CHAR_ERROR", "email first");

        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH", "^.{1,10}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH_ERROR", "phone length");

        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT_ERROR", "phone format");

        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT", "^\\+?[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT_ERROR", "code format");

        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH", "^.{1,4}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH_ERROR", "code length");

        ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_LENGTH", "^.{1,50}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_LENGTH_ERROR", "premise length");

        ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_FORMAT", "^[A-Za-z0-9 #]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_FORMAT_ERROR", "premise format");

        ReflectionTestUtils.setField(regexUtils, "REGEX_STREET_TYPE_CODE_FORMAT", "^[A-Z]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_STREET_TYPE_CODE_FORMAT_ERROR", "street format");

        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_BETWEEN", "^[^@]+@[^@]+$");
    }

    @Test
    void shouldValidateAllRegexTypesSuccessfully() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "123", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, "AB", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, "CALLE 1", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, "CALLE 1", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL, "a@b.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH_60, "a@b.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, "abc", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, "b.com", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, "a", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, "1234567", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, "1234567", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, "+57", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, "+57", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PREMISE_LENGTH, "APT 101", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PREMISE_FORMAT, "APT 101", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STREET_TYPE_CODE_FORMAT, "CL", "field"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, "a@b", "field"));
    }

    @Test
    void shouldThrowWhenRegexDoesNotMatch() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "customer_id")
        );

        assertEquals("MS-P-F-9400", exception.getError().getCode());
        assertEquals("'customer_id': only numbers", exception.getError().getMessage());
    }
}
ServiceDirectoryTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDirectoryTest {

    @Test
    void shouldCoverConstants() {
        assertEquals("/v2/customer_contact_points", ServiceDirectory.CUSTOMERS);
        assertEquals("/v2/customer_contact_points/search", ServiceDirectory.CUSTOMERS_SEARCH);
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<ServiceDirectory> constructor = ServiceDirectory.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
StringUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void shouldReturnBlankWhenFieldIsNull() {
        assertEquals("", StringUtils.blankField(null));
    }

    @Test
    void shouldReturnSameFieldWhenNotNull() {
        assertEquals("ABC", StringUtils.blankField("ABC"));
    }

    @Test
    void shouldCompleteAndConcatenateWhenShorter() {
        assertEquals("ABC  XYZ", StringUtils.completarYConcatenar("ABC", "XYZ", 5));
    }

    @Test
    void shouldTruncateAndConcatenateWhenLonger() {
        assertEquals("ABCXY", StringUtils.completarYConcatenar("ABCDEFG", "XY", 3));
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
TimeUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void shouldGetLocalDateTimeByFormat() {
        String result = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");

        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void shouldFormatDate() {
        assertEquals("2024-01-01", TimeUtils.formatDate("2024-01-01"));
    }

    @Test
    void shouldMarkTime() {
        String result = TimeUtils.markTime();

        assertNotNull(result);
        assertFalse(result.isBlank());
    }

    @Test
    void shouldCoverPrivateConstructor() throws Exception {
        Constructor<TimeUtils> constructor = TimeUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                constructor::newInstance
        );

        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }
}
ParamServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ParametersEnums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParamServiceImplTest {

    private ParameterApiService parameterApiService;
    private ParamServiceImpl service;
    private SecurityHeaders headers;

    @BeforeEach
    void setUp() {
        parameterApiService = mock(ParameterApiService.class);
        service = new ParamServiceImpl();
        headers = new SecurityHeaders();

        ReflectionTestUtils.setField(service, "parameterApiService", parameterApiService);
    }

    @Test
    void shouldGetWayTypeDescription() {
        when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "CL", headers))
                .thenReturn(List.of(new DataListDTO("0314", "CL", "CALLE")));

        assertEquals("CALLE", service.getWayTypeDescription("CL", headers));
    }

    @Test
    void shouldReturnEmptyWayTypeDescriptionWhenEmpty() {
        assertEquals("", service.getWayTypeDescription("", headers));
        assertEquals("", service.getWayTypeDescription(null, headers));
    }

    @Test
    void shouldReturnEmptyWayTypeDescriptionWhenNoParameter() {
        when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "CL", headers))
                .thenReturn(Collections.emptyList());

        assertEquals("", service.getWayTypeDescription("CL", headers));
    }

    @Test
    void shouldGetWayTypes() {
        when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "CL", headers))
                .thenReturn(List.of(new DataListDTO("0314", "CL", "CALLE")));

        assertEquals("CALLE", service.getWayTypes("CL", headers));
    }

    @Test
    void shouldGetCountry() {
        when(parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), "CO", headers))
                .thenReturn(List.of(new DataListDTO("0112", "CO", "COLOMBIA")));

        CodeNameDTO result = service.getCountry("CO", headers);

        assertEquals("CO", result.getCode());
        assertEquals("COLOMBIA", result.getName());
    }

    @Test
    void shouldGetEmptyCountryWhenNoParameter() {
        when(parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), "CO", headers))
                .thenReturn(Collections.emptyList());

        CodeNameDTO result = service.getCountry("CO", headers);

        assertNull(result.getCode());
        assertNull(result.getName());
    }

    @Test
    void shouldGetTown() {
        when(parameterApiService.getParameter(ParametersEnums.TOWNS.value(), "11001", headers))
                .thenReturn(List.of(new DataListDTO("0008", "11001", "BOGOTA")));

        CodeNameDTO result = service.getTown("11001", headers);

        assertEquals("11001", result.getCode());
        assertEquals("BOGOTA", result.getName());
    }

    @Test
    void shouldGetCity() {
        when(parameterApiService.getParameter(ParametersEnums.STATES.value(), "11", headers))
                .thenReturn(List.of(new DataListDTO("0009", "11", "BOGOTA")));

        CodeNameDTO result = service.getCity("11001", headers);

        assertEquals("11", result.getCode());
        assertEquals("BOGOTA", result.getName());
    }

    @Test
    void shouldGetEmptyCityWhenNoParameter() {
        when(parameterApiService.getParameter(ParametersEnums.STATES.value(), "", headers))
                .thenReturn(Collections.emptyList());

        CodeNameDTO result = service.getCity("", headers);

        assertNull(result.getCode());
        assertNull(result.getName());
    }
}
Para CustomerSevicerImpl, pásame las interfaces TrxPersonService, ContextApiService, ParameterApiService y CustomerContactPointsMapper, porque los mocks dependen de las firmas exactas.