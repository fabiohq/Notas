// Package sugerido: // src/test/java/com/santander/bnc/bsn049/bncbsn049msprspctcntctpnt/utils/ // // Cada bloque es una clase de test independiente. // No comparten helpers entre clases para mantenerlos desacoplados.

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.ContactPointDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.PersonDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.PlaceOfBirthDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.Parameters; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.CustomerRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.DocumentRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.PersonRequestDto; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException; import org.junit.jupiter.api.Test; import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Constructor; import java.time.LocalDate; import java.time.format.DateTimeFormatter; import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperUtilsTest {

@Test
void shouldMapPersonDtoNamesWhenGenderIsMale() {
    TrxPersonData personData = new TrxPersonData();
    personData.setSexo("H");
    personData.setNombre("Fabio");
    personData.setPrimerApellido("Hernandez");
    personData.setSegundoApellido("Perez");
    personData.setFechaNacimiento("2000-01-01");

    Parameters parameters = new Parameters();
    CodeNameDTO countryBirth = new CodeNameDTO("CO", "Colombia");
    CodeNameDTO cityBirth = new CodeNameDTO("11", "Bogota");
    CodeNameDTO town = new CodeNameDTO("11001", "Bogota D.C.");
    CodeNameDTO countryNationality = new CodeNameDTO("CO", "Colombiana");
    CodeNameDTO countryDir = new CodeNameDTO("CO", "Colombia");
    parameters.setCountryBirth(countryBirth);
    parameters.setCityBirth(cityBirth);
    parameters.setTown(town);
    parameters.setCountryNationality(countryNationality);
    parameters.setCountryDir(countryDir);

    PersonDTO response = CustomerMapperUtils.personDTONames(personData, parameters);

    assertEquals("H", response.getGenderCode());
    assertEquals("HOMBRE", response.getGenderDescription());
    assertEquals("Fabio", response.getPersonName().getGivenName());
    assertEquals("Hernandez", response.getPersonName().getLastName());
    assertEquals("Perez", response.getPersonName().getSecondLastName());
    assertEquals("Fabio Hernandez Perez", response.getPersonName().getFullName());
    assertEquals("2000-01-01", response.getBirthDate());
    assertSame(countryBirth, response.getPlaceOfBirth().getCountry());
    assertSame(cityBirth, response.getPlaceOfBirth().getState());
    assertEquals("Bogota D.C.", response.getPlaceOfBirth().getTown());
    assertSame(countryNationality, response.getFirstNationality());
    assertSame(countryDir, response.getCountryOfResidence());
}

@Test
void shouldMapPersonDtoNamesWhenGenderIsFemale() {
    TrxPersonData personData = new TrxPersonData();
    personData.setSexo("M");
    personData.setNombre("Ana");
    personData.setPrimerApellido("Gomez");
    personData.setSegundoApellido("Lopez");
    personData.setFechaNacimiento("1999-02-03");

    Parameters parameters = new Parameters();
    parameters.setCountryBirth(new CodeNameDTO("CO", "Colombia"));
    parameters.setCityBirth(new CodeNameDTO("11", "Bogota"));
    parameters.setTown(new CodeNameDTO("11001", "Bogota D.C."));
    parameters.setCountryNationality(new CodeNameDTO("CO", "Colombiana"));
    parameters.setCountryDir(new CodeNameDTO("CO", "Colombia"));

    PersonDTO response = CustomerMapperUtils.personDTONames(personData, parameters);

    assertEquals("M", response.getGenderCode());
    assertEquals("MUJER", response.getGenderDescription());
    assertEquals("Ana Gomez Lopez", response.getPersonName().getFullName());
}

@Test
void shouldMapPlaceOfBirth() {
    Parameters parameters = new Parameters();
    CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
    CodeNameDTO state = new CodeNameDTO("11", "Bogota");
    CodeNameDTO town = new CodeNameDTO("11001", "Bogota D.C.");
    parameters.setCountryBirth(country);
    parameters.setCityBirth(state);
    parameters.setTown(town);

    PlaceOfBirthDTO response = CustomerMapperUtils.getPlaceOfBirth(parameters);

    assertSame(country, response.getCountry());
    assertSame(state, response.getState());
    assertEquals("Bogota D.C.", response.getTown());
}

@Test
void shouldReturnSourceCodeWhenUsualtHasValue() {
    TrxPersonData personData = new TrxPersonData();
    personData.setUsualt("APPNO");

    String response = CustomerMapperUtils.getSourceCode(personData);

    assertEquals("APP", response);
}

@Test
void shouldReturnNullSourceCodeWhenUsualtIsBlankOrNull() {
    TrxPersonData blankData = new TrxPersonData();
    blankData.setUsualt(" ");
    TrxPersonData nullData = new TrxPersonData();
    nullData.setUsualt(null);

    assertNull(CustomerMapperUtils.getSourceCode(blankData));
    assertNull(CustomerMapperUtils.getSourceCode(nullData));
}

@Test
void shouldMapContactPoint() {
    TrxPersonData personData = new TrxPersonData();
    personData.setNombreVia("Calle 100");
    personData.setTipoVia("CL");
    personData.setDescripcionDireccion("Apto 101");
    personData.setPrecelular("57");
    personData.setTelefono("6011234567");
    personData.setCelular("3001234567");
    personData.setEmail("test@santander.com");

    Parameters parameters = new Parameters();
    parameters.setStreetTypeDescription("Calle");
    parameters.setCityStandard(new CodeNameDTO("11001", "Bogota"));
    parameters.setCountryDir(new CodeNameDTO("CO", "Colombia"));
    parameters.setCityDepartment(new CodeNameDTO("11", "Bogota D.C."));

    ContactPointDTO response = CustomerMapperUtils.getContactPoint(personData, parameters);

    assertEquals("PRI001", response.getContactPointId());
    assertEquals("PRI", response.getUseTypes().get(0).getCode());
    assertEquals("Contactos Principales", response.getUseTypes().get(0).getName());
    assertEquals("57", response.getPhoneAddress().getInternationalCode());
    assertEquals("6011234567", response.getPhoneAddress().getPhoneNumber());
    assertEquals("3001234567", response.getPhoneAddress().getMobileNumber());
    assertEquals("test@santander.com", response.getElectronicAddress().getEmailAddress());
    assertEquals("Calle 100", response.getPostalAddress().getFullAddress());
    assertEquals("CL", response.getPostalAddress().getStreetTypeCode());
    assertEquals("Calle", response.getPostalAddress().getStreetTypeDescription());
    assertEquals("Bogota", response.getPostalAddress().getTownName());
    assertEquals("Apto 101", response.getPostalAddress().getPremise());
}

@Test
void shouldMapForeignTaxIndicatorPositiveAndNegative() {
    CustomerMapperUtils mapperUtils = new CustomerMapperUtils();
    ReflectionTestUtils.setField(mapperUtils, "foreignTaxIndicatorPositiveResponse", "YES");

    TrxPersonData positive = new TrxPersonData();
    positive.setUsualt("APPYESX");
    TrxPersonData negative = new TrxPersonData();
    negative.setUsualt("APPNOXX");
    TrxPersonData nullData = new TrxPersonData();
    nullData.setUsualt(null);

    assertEquals("YES", mapperUtils.getForeignTaxIndicator(positive));
    assertEquals("NO", mapperUtils.getForeignTaxIndicator(negative));
    assertEquals("NO", mapperUtils.getForeignTaxIndicator(nullData));
}

@Test
void shouldMapPostalAddressBasic() {
    TrxPersonData personData = new TrxPersonData();
    personData.setNombreVia("Calle 100");
    personData.setTipoVia("CL");
    personData.setDescripcionDireccion("Apto 101");

    Parameters parameters = new Parameters();
    parameters.setStreetTypeDescription("Calle");
    parameters.setCityStandard(new CodeNameDTO("11001", "Bogota"));
    parameters.setCountryDir(new CodeNameDTO("CO", "Colombia"));
    parameters.setCityDepartment(new CodeNameDTO("11", "Bogota D.C."));

    PostalAddressDTO response = CustomerMapperUtils.getPostalAddressBasic(personData, parameters);

    assertEquals("Calle 100", response.getFullAddress());
    assertEquals("CL", response.getStreetTypeCode());
    assertEquals("Calle", response.getStreetTypeDescription());
    assertEquals("Bogota", response.getTownName());
    assertEquals("Apto 101", response.getPremise());
    assertEquals("CO", response.getCountry().getCode());
    assertEquals("11", response.getState().getCode());
}

@Test
void shouldMapDocumentBasics() {
    TrxPersonData personData = new TrxPersonData();
    personData.setTipoIdentificacion("CC");
    personData.setNumeroIdentificacion("123456789");
    personData.setFechaExpedicion("2020-01-01");

    Parameters parameters = new Parameters();
    parameters.setDocumentTypeDescription("Cedula");
    parameters.setCityStandard(new CodeNameDTO("11001", "Bogota"));
    parameters.setCountryExp(new CodeNameDTO("CO", "Colombia"));

    DocumentDTO response = CustomerMapperUtils.getDocumentBasics(personData, parameters);

    assertEquals("CC", response.getDocumentTypeCode());
    assertEquals("Cedula", response.getDocumentTypeDescription());
    assertEquals("123456789", response.getDocumentNumber());
    assertEquals("2020-01-01", response.getIssueDate());
    assertEquals("11001", response.getState().getCode());
    assertEquals("CO", response.getCountry().getCode());
}

@Test
void shouldValidateProspectAndPenumper() {
    assertTrue(CustomerMapperUtils.isProspect("PRO"));
    assertTrue(CustomerMapperUtils.isProspect("pro"));
    assertFalse(CustomerMapperUtils.isProspect("CLI"));

    assertTrue(CustomerMapperUtils.isPenumperValid("12345678"));
    assertFalse(CustomerMapperUtils.isPenumperValid("1234567"));
    assertFalse(CustomerMapperUtils.isPenumperValid("ABC45678"));
}

@Test
void shouldCleanAddress() {
    assertNull(CustomerMapperUtils.cleanAddress(null));
    assertEquals("", CustomerMapperUtils.cleanAddress(""));
    assertEquals("Calle 100 1 2 Nandu Arbol", CustomerMapperUtils.cleanAddress("Calle 100 #1-2 Ñandú Árbol"));
}

}

class RegexUtilsTest {

@Test
void shouldValidateAllRegexTypesWhenValueMatches() {
    RegexUtils regexUtils = new RegexUtils();
    ReflectionTestUtils.setField(regexUtils, "MS_NAME", "MS");
    ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
    ReflectionTestUtils.setField(regexUtils, "LEVEL", "ERROR");
    ReflectionTestUtils.setField(regexUtils, "CODE", "REGEX");

    ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS_ERROR", "only numbers");
    ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_STRICT_LENGTH_8_ERROR", "length 8");
    ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_CHAR_STRICT_LENGTH_2_ERROR", "length 2");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_LENGTH_ERROR", "address length");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ADDRESS_FORMAT_ERROR", "address format");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_ERROR", "email");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_LENGTH", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_LENGTH_ERROR", "email length");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_LEFT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_LEFT_ERROR", "email left");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_RIGHT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_RIGHT_ERROR", "email right");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_FIRST_CHAR", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_EMAIL_FORMAT_FIRST_CHAR_ERROR", "email first char");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_LENGTH_ERROR", "phone length");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PHONE_FORMAT_ERROR", "phone format");
    ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_LENGTH_ERROR", "international length");
    ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_INTERNATIONAL_CODE_FORMAT_ERROR", "international format");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_LENGTH", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_LENGTH_ERROR", "premise length");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_FORMAT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_PREMISE_FORMAT_ERROR", "premise format");
    ReflectionTestUtils.setField(regexUtils, "REGEX_STREET_TYPE_CODE_FORMAT", ".*");
    ReflectionTestUtils.setField(regexUtils, "REGEX_STREET_TYPE_CODE_FORMAT_ERROR", "street format");

    for (RegexTypes regexType : RegexTypes.values()) {
        assertDoesNotThrow(() -> regexUtils.validateRegex(regexType, "value", "field"));
    }
}

@Test
void shouldThrowServiceExceptionWhenRegexDoesNotMatch() {
    RegexUtils regexUtils = new RegexUtils();
    ReflectionTestUtils.setField(regexUtils, "MS_NAME", "MS");
    ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "v1");
    ReflectionTestUtils.setField(regexUtils, "LEVEL", "ERROR");
    ReflectionTestUtils.setField(regexUtils, "CODE", "REGEX");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS", "^\\d+$");
    ReflectionTestUtils.setField(regexUtils, "REGEX_ONLY_NUMBERS_ERROR", "only numbers");

    ServiceException exception = assertThrows(ServiceException.class,
            () -> regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, "ABC", "prospect_Id"));

    assertNotNull(exception.getError());
    assertEquals("MS-REGEX", exception.getError().getCode());
    assertEquals("ERROR", exception.getError().getLevel());
    assertTrue(exception.getError().getMessage().contains("prospect_Id"));
    assertTrue(exception.getError().getMessage().contains("only numbers"));
}

}

class RegexTypesTest {

@Test
void shouldContainAllRegexTypes() {
    assertEquals(17, RegexTypes.values().length);
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.ONLY_NUMBERS));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.STRICT_LENGTH_8));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.STRICT_CHAR_LENGTH_2));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.STREET_TYPE_CODE_FORMAT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.EMAIL));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.EMAIL_LENGTH));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.EMAIL_FORMAT_LEFT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.EMAIL_FORMAT_RIGHT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.EMAIL_FORMAT_FIRST_CHAR));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.PHONE_LENGTH));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.PHONE_FORMAT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.INTERNATIONAL_CODE_LENGTH));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.INTERNATIONAL_CODE_FORMAT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.ADDRESS_LENGTH));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.ADDRESS_FORMAT));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.PREMISE_LENGTH));
    assertTrue(Arrays.asList(RegexTypes.values()).contains(RegexTypes.PREMISE_FORMAT));
}

@Test
void shouldReturnEnumByName() {
    assertEquals(RegexTypes.EMAIL, RegexTypes.valueOf("EMAIL"));
    assertEquals("PHONE_FORMAT", RegexTypes.PHONE_FORMAT.name());
}

}

class ClientUtilsTest {

@Test
void shouldBuildTrxRequestByProspectIdWhenRequestIsNull() {
    TrxPersonRequest response = ClientUtils.buildTrxRequestByProspectId(null, "12345678");

    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals("12345678", response.getData().getpENUMPE());
    assertNull(response.getData().getTipoDocumento());
    assertNull(response.getData().getNumDocumento());
}

@Test
void shouldBuildTrxRequestByProspectIdWhenPersonExists() {
    DocumentRequestDTO document = new DocumentRequestDTO();
    document.setDocumentTypeCode("CC");
    document.setDocumentNumber("123456789");

    PersonRequestDto person = new PersonRequestDto();
    person.setDocument(document);

    CustomerRequestDTO request = new CustomerRequestDTO();
    request.setPerson(person);

    TrxPersonRequest response = ClientUtils.buildTrxRequestByProspectId(request, null);

    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals("", response.getData().getpENUMPE());
    assertEquals("CC", response.getData().getTipoDocumento());
    assertEquals("123456789", response.getData().getNumDocumento());
}

@Test
void shouldBuildHeader() {
    TrxPersonHeader response = ClientUtils.buildHeader("PEF3");

    assertNotNull(response);
    assertEquals("PEF3", response.getRutaServicio());
    assertEquals("Intro", response.getFuncion());
    assertEquals("60", response.getCanal());
    assertEquals(44204, response.getSecuencia());
    assertNotNull(response.getSesion());
    assertEquals("@NETE781", response.getSesion().getUsuario());
    assertEquals("", response.getSesion().getTerminal());
    assertEquals("GCAJASTL", response.getSesion().getPerfil());
    assertEquals("0100", response.getSesion().getSucursal());
    assertEquals("0065", response.getSesion().getEntidad());
    assertEquals("29", response.getSesion().getDiasRestantesCambioClave());
    assertEquals("N", response.getSesion().getEntorno());
    assertEquals("", response.getSesion().getTurno());
    assertNotNull(response.getSesion().getHoraConexion());
    assertNotNull(response.getSesion().getFechaContable());
}

@Test
void shouldInstantiatePrivateConstructorByReflection() throws Exception {
    Constructor<ClientUtils> constructor = ClientUtils.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertNotNull(constructor.newInstance());
}

}

class GUtilsTest {

@Test
void shouldReturnNullPropertyNames() {
    TestBean bean = new TestBean();
    bean.setName("Fabio");
    bean.setAge(null);
    bean.setEmail(null);

    String[] response = GUtils.getNullPropertyNames(bean);

    assertTrue(Arrays.asList(response).contain
