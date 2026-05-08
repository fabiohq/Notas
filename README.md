Aquí van los tests para utils, separados por clase.
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.DocumentRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.PersonRequestDto;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class ClientUtilsTest {

    @Test
    void buildTrxRequestByCustomerIdOnlyCustomerId() {
        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(null, "12345678");

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals("12345678", result.getData().getpENUMPE());
    }

    @Test
    void buildTrxRequestByCustomerIdWithProspectRequest() {
        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setDocumentTypeCode("CC");
        document.setDocumentNumber("12345678901");

        PersonRequestDto person = new PersonRequestDto();
        person.setDocument(document);

        ProspectRequestDTO request = new ProspectRequestDTO();
        request.setPerson(person);

        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(request, null);

        assertEquals("", result.getData().getpENUMPE());
        assertEquals("CC", result.getData().getTipoDocumento());
        assertEquals("12345678901", result.getData().getNumDocumento());
    }

    @Test
    void buildHeaderShouldSetDefaultValues() {
        TrxPersonHeader result = ClientUtils.buildHeader("PEF3");

        assertEquals("PEF3", result.getRutaServicio());
        assertEquals("Intro", result.getFuncion());
        assertEquals("60", result.getCanal());
        assertEquals(44204, result.getSecuencia());
        assertNotNull(result.getSesion());
        assertEquals("@NETE781", result.getSesion().getUsuario());
        assertEquals("0065", result.getSesion().getEntidad());
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<ClientUtils> constructor = ClientUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception ex = assertThrows(Exception.class, constructor::newInstance);
        assertNotNull(ex);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareStringUtilsTest {

    private final CompareStringUtils utils = new CompareStringUtils();

    @Test
    void ciudadMatchShouldReturnTrueForEqualCities() {
        assertTrue(utils.ciudadMatch("Bogota", "Bogota"));
    }

    @Test
    void ciudadMatchShouldReturnTrueForSimilarCities() {
        assertTrue(utils.ciudadMatch("Bogota", "Bogotá"));
    }

    @Test
    void ciudadMatchShouldReturnFalseForDifferentCities() {
        assertFalse(utils.ciudadMatch("Bogota", "Medellin"));
    }

    @Test
    void ciudadMatchShouldReturnTrueForEmptyValues() {
        assertTrue(utils.ciudadMatch("", ""));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void translateCountryToXXShouldTranslateKnownCountry() {
        assertEquals("CO", DataUtils.translateCountryToXX("COL"));
    }

    @Test
    void translateCountryToXXShouldReturnSameWhenNotFound() {
        assertEquals("XXX", DataUtils.translateCountryToXX("XXX"));
    }

    @Test
    void translateCountryToXXXShouldTranslateKnownCountry() {
        assertEquals("COL", DataUtils.translateCountryToXXX("CO"));
    }

    @Test
    void translateCountryToXXXShouldReturnSameWhenNotFound() {
        assertEquals("ZZ", DataUtils.translateCountryToXXX("ZZ"));
    }

    @Test
    void translateValueCodeShouldTranslateCountry() {
        assertEquals("CO", DataUtils.translateValueCode(ParametersEnums.COUNTRY.value(), "COL"));
    }

    @Test
    void translateValueCodeShouldTranslateState() {
        assertEquals("CO-ANT", DataUtils.translateValueCode(ParametersEnums.STATES.value(), "CO-ANT"));
    }

    @Test
    void translateDepartmentDescShouldReturnDescription() {
        assertEquals("05", DataUtils.translateDepartmentDesc("CO-ANT"));
    }

    @Test
    void translateDepartmentDescShouldReturnEmptyWhenNotFound() {
        assertEquals("", DataUtils.translateDepartmentDesc("NO_EXISTE"));
    }

    @Test
    void getStateCodeByCountryCodeAndStateIsoShouldReturnCode() {
        assertEquals("05", DataUtils.getStateCodeByCountryCodeAndStateIso("CO", "ANT"));
    }

    @Test
    void getStateCodeByCountryCodeAndStateIsoShouldReturnEmptyWhenNotFound() {
        assertEquals("", DataUtils.getStateCodeByCountryCodeAndStateIso("XX", "YY"));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class GUtilsTest {

    static class Dummy {
        private String name;
        private String empty;
        private Integer age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmpty() { return empty; }
        public void setEmpty(String empty) { this.empty = empty; }

        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }

    @Test
    void getNullOrBlankPropertyNamesShouldReturnNullAndEmptyFields() {
        Dummy dummy = new Dummy();
        dummy.setName("Juan");
        dummy.setEmpty("");
        dummy.setAge(null);

        String[] result = GUtils.getNullOrBlankPropertyNames(dummy);

        assertTrue(java.util.Arrays.asList(result).contains("empty"));
        assertTrue(java.util.Arrays.asList(result).contains("age"));
        assertFalse(java.util.Arrays.asList(result).contains("name"));
    }

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<GUtils> constructor = GUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThrows(Exception.class, constructor::newInstance);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatchProspectMapperUtilsTest {

    @Test
    void cleanFieldsFromProspectUpdateShouldCleanDocumentsAndContactData() {
        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setDocumentTypeCode("CC");
        document.setDocumentNumber("12345678901");

        PersonRequestDTO person = new PersonRequestDTO();
        person.setDocuments(List.of(document));

        ContactPointRequestDTO contactPoint = new ContactPointRequestDTO();
        contactPoint.setElectronicAddress(new ElectronicAddressRequestDTO());
        contactPoint.setPhoneAddress(new PhoneAddressRequestDTO());

        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        request.setPerson(person);
        request.setContactPoints(List.of(contactPoint));

        PatchProspectRequestDTO result = PatchProspectMapperUtils.cleanFieldsFromProspectUpdate(request);

        assertNull(result.getPerson().getDocuments().get(0).getDocumentTypeCode());
        assertNull(result.getPerson().getDocuments().get(0).getDocumentNumber());
        assertNull(result.getContactPoints().get(0).getElectronicAddress());
        assertNull(result.getContactPoints().get(0).getPhoneAddress());
    }

    @Test
    void replaceDoubleOrTripleSpacesShouldNormalizeSpaces() {
        String result = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces("Calle   10  Sur");

        assertEquals("Calle 10 Sur", result);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StringUtilsTest {

    @Mock
    private RegexUtils regexUtils;

    @Mock
    private ErrorService errorService;

    @InjectMocks
    private StringUtils stringUtils;

    @Test
    void blankFieldShouldReturnEmptyWhenNull() {
        assertEquals("", StringUtils.blankField(null));
    }

    @Test
    void blankFieldShouldReturnSameValueWhenNotNull() {
        assertEquals("ABC", StringUtils.blankField("ABC"));
    }

    @Test
    void inputSencondLastNameValidationShouldValidateWhenCC() {
        stringUtils.inputSencondLastNameValidation("CC", "Perez");

        verify(regexUtils).validateRegex(
                RegexTypes.SECOND_LAST_NAME_CE_FORMAT,
                "Perez",
                "person.personName.secondLastName"
        );
    }

    @Test
    void inputSencondLastNameValidationShouldNotValidateWhenOtherDocumentType() {
        stringUtils.inputSencondLastNameValidation("TI", "Perez");

        verifyNoInteractions(regexUtils);
    }

    @Test
    void inputSencondLastNameValidationShouldPropagateException() {
        doThrow(ServiceException.class).when(regexUtils)
                .validateRegex(any(), anyString(), anyString());

        assertThrows(ServiceException.class,
                () -> stringUtils.inputSencondLastNameValidation("CC", "123"));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void getSlocalDateTimeByFormatShouldReturnFormattedDate() {
        String result = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");

        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void formatDateShouldReturnSameValidDateFormat() {
        assertEquals("2025-01-31", TimeUtils.formatDate("2025-01-31"));
    }

    @Test
    void formatDateShouldThrowExceptionWhenInvalidDate() {
        assertThrows(Exception.class, () -> TimeUtils.formatDate("31-01-2025"));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDirectoryTest {

    @Test
    void constantsShouldHaveExpectedValues() {
        assertEquals("/v1/prospects", ServiceDirectory.PROSPECT);
        assertEquals("/v1/prospects/search", ServiceDirectory.PROSPECT_SEARCH);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(regexUtils, "msName", "MS");
        ReflectionTestUtils.setField(regexUtils, "msVersion", "v1");
        ReflectionTestUtils.setField(regexUtils, "level", "error");
        ReflectionTestUtils.setField(regexUtils, "code", "P-F-9400");

        ReflectionTestUtils.setField(regexUtils, "regexOnlyNumbers", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "regexOnlyNumberError", "only numbers");

        ReflectionTestUtils.setField(regexUtils, "regexStrictLength8", "^.{8}$");
        ReflectionTestUtils.setField(regexUtils, "regexStrictLength8Error", "length 8");

        ReflectionTestUtils.setField(regexUtils, "regexStrictLength11", "^.{11}$");
        ReflectionTestUtils.setField(regexUtils, "regexStrictLength11Error", "length 11");

        ReflectionTestUtils.setField(regexUtils, "regexEmail", "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        ReflectionTestUtils.setField(regexUtils, "regexEmailError", "invalid email");

        ReflectionTestUtils.setField(regexUtils, "regexPhoneFormat", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "regexPhoneFormatError", "invalid phone");

        ReflectionTestUtils.setField(regexUtils, "regexGenderCodeFormat", "^[HM]$");
        ReflectionTestUtils.setField(regexUtils, "regexGenderCodeFormatError", "invalid gender");
    }

    @Test
    void validateRegexShouldPassWhenValueMatches() {
        assertDoesNotThrow(() ->
                regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "123456", "field"));
    }

    @Test
    void validateRegexShouldThrowWhenValueDoesNotMatch() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "field"));
    }

    @Test
    void validateRegexEmailShouldPass() {
        assertDoesNotThrow(() ->
                regexUtils.validateRegex(RegexTypes.EMAIL, "test@mail.com", "email"));
    }

    @Test
    void validateRegexEmailShouldThrow() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.EMAIL, "bad-email", "email"));
    }
}
Para ProspectMapperUtils, ojo: tiene posibles NullPointerException por condiciones como personData.getDescripcionDireccion().length() > 0 && personData.getDescripcionDireccion() != null. Primero valida length() y después null. En test hay que enviar ese campo con valor.
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class ProspectMapperUtilsTest {

    @Test
    void isPenumperValidShouldReturnTrue() {
        assertTrue(ProspectMapperUtils.isPenumperValid("12345678"));
    }

    @Test
    void isPenumperValidShouldReturnFalseWhenNotNumeric() {
        assertFalse(ProspectMapperUtils.isPenumperValid("ABCDEFGH"));
    }

    @Test
    void isPenumperValidShouldReturnFalseWhenInvalidLength() {
        assertFalse(ProspectMapperUtils.isPenumperValid("123"));
    }

    @Test
    void getSourceCodeShouldReturnODS() {
        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ODSXXXX");

        assertEquals("ODS", ProspectMapperUtils.getSourceCode(data));
    }

    @Test
    void getSourceCodeShouldReturnOtroWhenDifferent() {
        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ABCXXXX");

        assertEquals("OTRO", ProspectMapperUtils.getSourceCode(data));
    }

    @Test
    void isNotProspectShouldReturnFalseWhenPRO() {
        assertFalse(ProspectMapperUtils.isNotProspect("PRO"));
    }

    @Test
    void isNotProspectShouldReturnTrueWhenNotPRO() {
        assertTrue(ProspectMapperUtils.isNotProspect("CLI"));
    }

    @Test
    void personDTONamesShouldMapBasicData() {
        TrxPersonData data = buildTrxPersonData();
        Parameters params = buildParameters();

        PersonDTO result = ProspectMapperUtils.personDTONames(data, params);

        assertEquals("Juan", result.getPersonName().getGivenName());
        assertEquals("Perez", result.getPersonName().getLastName());
        assertEquals("Lopez", result.getPersonName().getSecondLastName());
        assertEquals("Juan Perez Lopez", result.getPersonName().getFullName());
        assertEquals("H", result.getGenderCode());
        assertEquals("HOMBRE", result.getGenderDescription());
        assertEquals("1990-01-01", result.getBirthDate());
    }

    @Test
    void getContactPointShouldMapData() {
        TrxPersonData data = buildTrxPersonData();
        Parameters params = buildParameters();

        ContactPointDTO result = ProspectMapperUtils.getContactPoint(data, params);

        assertEquals("PRI001", result.getContactPointId());
        assertEquals("3001234567", result.getPhoneAddress().getMobileNumber());
        assertEquals("6011234567", result.getPhoneAddress().getPhoneNumber());
        assertEquals("test@mail.com", result.getElectronicAddress().getEmailAddress());
        assertNotNull(result.getPostalAddress());
    }

    @Test
    void getDocumentBasicsShouldMapData() {
        TrxPersonData data = buildTrxPersonData();
        Parameters params = buildParameters();

        DocumentDTO result = ProspectMapperUtils.getDocumentBasics(data, params);

        assertEquals("CC", result.getDocumentTypeCode());
        assertEquals("Cédula", result.getDocumentTypeDescription());
        assertEquals("12345678901", result.getDocumentNumber());
        assertEquals("2020-01-01", result.getIssueDate());
        assertEquals("2030-01-01", result.getExpirationDate());
    }

    @Test
    void getForeignTaxIndicatorShouldReturnYes() {
        ProspectMapperUtils utils = new ProspectMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "CONN");

        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ODSCONN");

        assertEquals("YES", utils.getForeignTaxIndicator(data));
    }

    @Test
    void getForeignTaxIndicatorShouldReturnNo() {
        ProspectMapperUtils utils = new ProspectMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "CONN");

        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ODSXXXX");

        assertEquals("NO", utils.getForeignTaxIndicator(data));
    }

    @Test
    void getForeingTaxIndicatoFromRequestShouldReturnPositiveValue() {
        ProspectMapperUtils utils = new ProspectMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "CONN");
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorNegativeResponse", "COFF");

        assertEquals("CONN", utils.getForeingTaxIndicatoFromRequest("YES"));
    }

    @Test
    void getForeingTaxIndicatoFromRequestShouldReturnNegativeValue() {
        ProspectMapperUtils utils = new ProspectMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "CONN");
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorNegativeResponse", "COFF");

        assertEquals("COFF", utils.getForeingTaxIndicatoFromRequest("NO"));
    }

    @Test
    void lowProspectLogicShouldBuildValue() {
        ProspectMapperUtils utils = new ProspectMapperUtils();
        ReflectionTestUtils.setField(utils, "lowProspect", "BAJ");

        TrxPersonData data = new TrxPersonData();
        data.setUsualt("ODSCONN");

        assertEquals("BAJCONN", utils.lowProspectLogic(data));
    }

    private TrxPersonData buildTrxPersonData() {
        TrxPersonData data = new TrxPersonData();
        data.setNombre("Juan");
        data.setPrimerApellido("Perez");
        data.setSegundoApellido("Lopez");
        data.setSexo("H");
        data.setFechaNacimiento("1990-01-01");
        data.setTipoIdentificacion("CC");
        data.setNumeroIdentificacion("12345678901");
        data.setFechaExpedicion("2020-01-01");
        data.setDescripcionDireccion("CALLE 123                                         2030-01-01");
        data.setNombreVia("CALLE 123");
        data.setTipoVia("CL");
        data.setPrecelular("57");
        data.setTelefono("6011234567");
        data.setCelular("3001234567");
        data.setEmail("test@mail.com");
        return data;
    }

    private Parameters buildParameters() {
        Parameters params = new Parameters();
        params.setCountryNationality(new CodeNameDTO("CO", "Colombia"));
        params.setCountryDir(new CodeNameDTO("CO", "Colombia"));
        params.setCountryBirth(new CodeNameDTO("CO", "Colombia"));
        params.setCountryExp(new CodeNameDTO("CO", "Colombia"));
        params.setCityBirth(new CodeNameDTO("11", "Bogotá"));
        params.setCityExp(new CodeNameDTO("11", "Bogotá"));
        params.setCityStandard(new CodeNameDTO("11", "Bogotá"));
        params.setCityDepartment(new CodeNameDTO("11", "Bogotá"));
        params.setTown(new CodeNameDTO("11001", "Bogotá"));
        params.setTownDocument(new CodeNameDTO("11001", "Bogotá"));
        params.setStreetTypeDescription("Calle");
        params.setDocumentTypeDescription("Cédula");
        return params;
    }
}