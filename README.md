Aquí van separados, clase por clase.
RegexUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "CUSTOMERS");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "api-service-v3");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "P-F-9400");

        ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS_ERROR", "Invalid format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8", "^[0-9]{8}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8_ERROR", "Invalid length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL", "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_ERROR", "Invalid email");

        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_11", "^[0-9]{1,11}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_11_ERROR", "Invalid length 11");
        ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2", "^[a-zA-Z]{2}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2_ERROR", "Invalid char length 2");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH", "^[\\w\\W]{1,17}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH_ERROR", "Invalid address length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT", "[0-9a-zA-Z]+");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT_ERROR", "Invalid address format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH", "^[0-9]{10}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH_ERROR", "Invalid phone length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT_ERROR", "Invalid phone format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT_ERROR", "Invalid international code format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH", "^[0-9]{2,3}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH_ERROR", "Invalid international code length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_20_LENGTH", "^[\\p{L} ]{1,20}+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_20_LENGTH_ERROR", "Invalid text 20 length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_20_FORMAT", "^[\\p{L} ]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_20_FORMAT_ERROR", "Invalid text 20 format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_40_LENGTH", "^[\\p{L} ]{1,40}+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_40_LENGTH_ERROR", "Invalid text 40 length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_40_FORMAT", "^[\\p{L} ]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TEXT_40_FORMAT_ERROR", "Invalid text 40 format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_COUNTRY_CODE_LENGTH", "^[\\p{L} ]{2}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_COUNTRY_CODE_LENGTH_ERROR", "Invalid country length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_COUNTRY_CODE_FORMAT", "^[A-Za-z]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_COUNTRY_CODE_FORMAT_ERROR", "Invalid country format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_GENDER_CODE_FORMAT", "^[HM]{1}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_GENDER_CODE_ERROR_FORMAT", "Invalid gender format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_BIRTHDAY_FORMAT", "^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_BIRTHDAY_FORMAT_ERROR", "Invalid birthdate");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_FORMAT", "^[0-9]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_FORMAT_ERROR", "Invalid town format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_LENGTH", "^[0-9]{5}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_LENGHT_ERROR", "Invalid town length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DOCUMENT_TYPE_FORMAT", "(CC|CE)");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DOCUMENT_TYPE_FORMAT_ERROR", "Invalid document type");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DOCUMENT_TYPE_COUNTRY", "^[CO]+$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DOCUMENT_TYPE_COUNTRY_ERROR", "Invalid document country");
        ReflectionTestUtils.setField(regexUtils, "REGEX_GENDER_CODE_LENGTH", "^[\\w\\W]{1}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_GENDER_CODE_LENGTH_ERROR", "Invalid gender length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DATAORIGIN_SOURCECODE_FORMAT", "(OTRO)");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DATAORIGIN_SOURCECODE_FORMAT_ERROR", "Invalid source format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DATAORIGIN_SOURCECODE_LENGTH", "^[a-zA-Z]{4}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_DATAORIGIN_SOURCECODE_LENGTH_ERROR", "Invalid source length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_DESCRIPTION_FORMAT", "^[a-zA-Z()-]+(?: [a-zA-Z()-]+)*$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_DESCRIPTION_FORMAT_ERROR", "Invalid town description format");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_DESCRIPTION_LENGTH", "^.{1,40}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_TOWN_DESCRIPTION_LENGTH_ERROR", "Invalid town description length");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ISSUE_DATE_FORMAT", "^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        ReflectionTestUtils.setField(regexUtils, "REGEX_ISSUE_DATE_ERROR_FORMAT", "Invalid issue date");
    }

    @Test
    void shouldValidateValidOnlyNumbers() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "12345", "field"));
    }

    @Test
    void shouldThrowWhenInvalidOnlyNumbers() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "field"));
    }

    @Test
    void shouldValidateValidEmail() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL, "test@mail.com", "email"));
    }

    @Test
    void shouldThrowWhenInvalidEmail() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex(RegexTypes.EMAIL, "bad-email", "email"));
    }

    @Test
    void shouldCoverAllRegexTypesWithValidValues() {
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_11, "12345678901", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, "CO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, "CLL 1", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, "CLL123", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, "3001234567", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, "3001234567", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, "57", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, "57", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, "Fabio", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, "Fabio", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, "Fabio Andres", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, "Fabio Andres", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, "CO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, "CO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, "H", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.BIRTHDAY_FORMAT, "2000-01-01", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TOWN_FORMAT, "05001", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TOWN_LENGTH, "05001", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.DOCUMENT_TYPE_FORMAT, "CC", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.DOCUMENT_COUNTRY_TYPE, "CO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, "M", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.DATAORIGIN_SOURCECODE_FORMAT, "OTRO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.DATAORIGIN_SOURCECODE_LENGTH, "OTRO", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, "Medellin", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, "Medellin", "f"));
        assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, "2020-01-01", "f"));
    }
}
CustomerMapperUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PlaceOfBirthDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class CustomerMapperUtilsTest {

    private CustomerMapperUtils utils;

    @BeforeEach
    void setUp() {
        utils = new CustomerMapperUtils();
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorPositiveResponse", "CONS");
        ReflectionTestUtils.setField(utils, "foreignTaxIndicatorNegativeResponse", "CONN");
        ReflectionTestUtils.setField(utils, "deleteCustomerCode", "999");
    }

    @Test
    void shouldBuildPersonDtoWithMaleGender() {
        TrxPersonData personData = new TrxPersonData();
        personData.setSexo("H");
        personData.setNombre("JUAN");
        personData.setPrimerApellido("PEREZ");
        personData.setSegundoApellido("LOPEZ");
        personData.setFechaNacimiento("2000-01-01");

        Parameters pa = new Parameters();
        pa.setCountryNationality("CO");
        pa.setCountryDir("CO");
        pa.setCountryBirth("CO");
        pa.setCityBirth("05");
        pa.setTown(new CodeNameDTO("05001", "Medellin"));

        PersonDTO result = CustomerMapperUtils.personDTONames(personData, pa);

        assertEquals("H", result.getGenderCode());
        assertEquals("HOMBRE", result.getGenderDescription());
        assertEquals("JUAN", result.getPersonName().getGivenName());
        assertEquals("JUAN PEREZ LOPEZ", result.getPersonName().getFullName());
        assertEquals("CO", result.getFirstNationality());
    }

    @Test
    void shouldBuildPersonDtoWithFemaleGender() {
        TrxPersonData personData = new TrxPersonData();
        personData.setSexo("M");
        personData.setNombre("ANA");
        personData.setPrimerApellido("GOMEZ");
        personData.setSegundoApellido("RUIZ");
        personData.setFechaNacimiento("2000-01-01");

        Parameters pa = new Parameters();
        pa.setCountryNationality("CO");
        pa.setCountryDir("CO");
        pa.setCountryBirth("CO");
        pa.setCityBirth("05");

        PersonDTO result = CustomerMapperUtils.personDTONames(personData, pa);

        assertEquals("M", result.getGenderCode());
        assertEquals("MUJER", result.getGenderDescription());
    }

    @Test
    void shouldGetPlaceOfBirthWithTown() {
        Parameters pa = new Parameters();
        pa.setCountryBirth("CO");
        pa.setCityBirth("05");
        pa.setTown(new CodeNameDTO("05001", "Medellin"));

        PlaceOfBirthDTO result = CustomerMapperUtils.getPlaceOfBirth(pa);

        assertEquals("CO", result.getCountry());
        assertEquals("05", result.getState());
        assertEquals("Medellin", result.getTown());
    }

    @Test
    void shouldGetPlaceOfBirthWithoutTown() {
        Parameters pa = new Parameters();
        pa.setCountryBirth("CO");
        pa.setCityBirth("05");

        PlaceOfBirthDTO result = CustomerMapperUtils.getPlaceOfBirth(pa);

        assertEquals("", result.getTown());
    }

    @Test
    void shouldReturnOdsSourceCode() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ODSCONS");

        assertEquals("ODS", CustomerMapperUtils.getSourceCode(personData));
    }

    @Test
    void shouldReturnOtroSourceCodeWhenInvalid() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ABC");

        assertEquals("OTRO", CustomerMapperUtils.getSourceCode(personData));
    }

    @Test
    void shouldBuildContactPoint() {
        TrxPersonData personData = new TrxPersonData();
        personData.setNombreVia("Calle 1");
        personData.setTipoVia("CL");
        personData.setDescripcionDireccion("APTO 101 2026-01-01");
        personData.setPrecelular("57");
        personData.setTelefono("6041234567");
        personData.setCelular("3001234567");
        personData.setEmail("test@mail.com");

        Parameters pa = new Parameters();
        pa.setStreetTypeDescription("Calle");
        pa.setCityStandard("05001");
        pa.setCountryDir("CO");
        pa.setCityDepartment("05");

        ContactPointDTO result = CustomerMapperUtils.getContactPoint(personData, pa);

        assertEquals("PRI001", result.getContactPointId());
        assertNotNull(result.getPostalAddress());
        assertNotNull(result.getPhoneAddress());
        assertNotNull(result.getElectronicAddress());
        assertEquals("test@mail.com", result.getElectronicAddress().getEmailAddress());
    }

    @Test
    void shouldReturnYesForeignTaxIndicator() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ODSCONS123");

        assertEquals("YES", utils.getForeignTaxIndicator(personData));
    }

    @Test
    void shouldReturnNoForeignTaxIndicator() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ODSCONN123");

        assertEquals("NO", utils.getForeignTaxIndicator(personData));
    }

    @Test
    void shouldBuildPostalAddressWithPremise() {
        TrxPersonData personData = new TrxPersonData();
        personData.setNombreVia("Carrera 10");
        personData.setTipoVia("CR");
        personData.setDescripcionDireccion("CASA 22   2026-01-01");

        Parameters pa = new Parameters();
        pa.setStreetTypeDescription("Carrera");
        pa.setCityStandard("05001");
        pa.setCountryDir("CO");
        pa.setCityDepartment("05");

        PostalAddressDTO result = CustomerMapperUtils.getPostalAddressBasic(personData, pa);

        assertEquals("Carrera 10", result.getFullAddress());
        assertEquals("CASA 22", result.getPremise());
    }

    @Test
    void shouldBuildDocumentWithValidExpirationDate() {
        TrxPersonData personData = new TrxPersonData();
        personData.setTipoIdentificacion("CC");
        personData.setNumeroIdentificacion("123");
        personData.setFechaExpedicion("2020-01-01");
        personData.setDescripcionDireccion("ANY DATA 2026-12-31");

        Parameters pa = new Parameters();
        pa.setDocumentTypeDescription("Cedula");
        pa.setCityExp("05");
        pa.setCountryExp("CO");
        pa.setTownDocument(new CodeNameDTO("05001", "Medellin"));

        DocumentDTO result = CustomerMapperUtils.getDocumentBasics(personData, pa);

        assertEquals("CC", result.getDocumentTypeCode());
        assertEquals("123", result.getDocumentNumber());
        assertEquals("Medellin", result.getTown());
        assertEquals("2026-12-31", result.getExpirationDate());
    }

    @Test
    void shouldBuildDocumentWithDefaultExpirationDateWhenInvalid() {
        TrxPersonData personData = new TrxPersonData();
        personData.setFechaExpedicion("2020-01-01");
        personData.setDescripcionDireccion("ANY DATA 9999-99-99");

        Parameters pa = new Parameters();

        DocumentDTO result = CustomerMapperUtils.getDocumentBasics(personData, pa);

        assertEquals("1999-12-31", result.getExpirationDate());
    }

    @Test
    void shouldValidateNotCustomer() {
        assertTrue(CustomerMapperUtils.isNotCustomer("PRO"));
        assertFalse(CustomerMapperUtils.isNotCustomer("CLI"));
    }

    @Test
    void shouldValidatePenumper() {
        assertTrue(CustomerMapperUtils.isPenumperValid("12345678"));
        assertFalse(CustomerMapperUtils.isPenumperValid("ABC"));
        assertFalse(CustomerMapperUtils.isPenumperValid("123"));
    }

    @Test
    void shouldRemoveCustomerWithLongUsualt() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ODSCONS123");

        assertEquals("999CONS", utils.removeCustomer(personData));
    }

    @Test
    void shouldRemoveCustomerWithShortUsualt() {
        TrxPersonData personData = new TrxPersonData();
        personData.setUsualt("ABC");

        assertEquals("999CONN", utils.removeCustomer(personData));
    }

    @Test
    void shouldRemoveCustomerWhenUsualtIsNull() {
        TrxPersonData personData = new TrxPersonData();

        assertEquals("999CONN", utils.removeCustomer(personData));
    }
}