package com.santander.bnc.bsn049.bncbsn049mscustomer.mappers;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CountryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.DocumentRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonNameRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonRequestDto;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxBasicData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.CustomerMapperUtils;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

    @Mock
    private ParamService paramsService;

    @Mock
    private ParameterApiService parameterApiService;

    @Mock
    private CustomerMapperUtils customerMapperUtils;

    @Mock
    private RegexUtils regexUtils;

    @Mock
    private ErrorService errorService;

    private CustomerMapper mapper;

    private SecurityHeaders securityHeaders;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper(
                paramsService,
                parameterApiService,
                customerMapperUtils,
                regexUtils,
                errorService
        );

        ReflectionTestUtils.setField(mapper, "defaultChannel", "ODS");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorPositive", "CONS");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorNegative", "CONN");

        ReflectionTestUtils.setField(errorService, "INVALID_VALUE", "Invalid value");
        ReflectionTestUtils.setField(errorService, "CODE_NOT_EXIST", "The code does not exist");

        securityHeaders = new SecurityHeaders();

        doNothing().when(regexUtils).validateRegex(any(), anyString(), anyString());

        lenient().when(errorService.errorBuilder(any(HttpStatus.class), anyString(), any(ErrorType.class)))
                .thenAnswer(invocation -> new ServiceException(
                        invocation.getArgument(0),
                        ErrorDTO.builder()
                                .message(invocation.getArgument(1))
                                .build()
                ));
    }

    @Test
    void trxPersonToCustomerDetailsDTOShouldReturnNullWhenPersonIsProspect() {
        TrxPersonResponse response = buildTrxPersonResponse("PRO");

        CustomerDetailsResponseDTO result = mapper.trxPersonToCustomerDetailsDTO(response, securityHeaders);

        assertNull(result);
    }

    @Test
    void trxPersonToCustomerDetailsDTOShouldMapCustomer() {
        TrxPersonResponse response = buildTrxPersonResponse("CLI");
        Parameters parameters = buildParameters();

        when(paramsService.findParameters(any(), any())).thenReturn(parameters);
        when(customerMapperUtils.getForeignTaxIndicator(any())).thenReturn("YES");

        CustomerDetailsResponseDTO result = mapper.trxPersonToCustomerDetailsDTO(response, securityHeaders);

        assertNotNull(result);
        assertNotNull(result.getPerson());
        assertNotNull(result.getContactPoints());
        assertNotNull(result.getDataOrigins());
        assertFalse(result.getPendingExCustomer());
        assertEquals("YES", result.getPerson().getForeignTaxIndicator());
    }

    @Test
    void trxPersonToCustomerDetailsDTOShouldSetPendingExCustomerTrue() {
        TrxPersonResponse response = buildTrxPersonResponse("EXC");

        when(paramsService.findParameters(any(), any())).thenReturn(buildParameters());
        when(customerMapperUtils.getForeignTaxIndicator(any())).thenReturn("NO");

        CustomerDetailsResponseDTO result = mapper.trxPersonToCustomerDetailsDTO(response, securityHeaders);

        assertNotNull(result);
        assertTrue(result.getPendingExCustomer());
    }

    @Test
    void trxPersonToCustomerSearchDTOShouldReturnNullWhenPersonIsProspect() {
        TrxPersonResponse response = buildTrxPersonResponse("PRO");

        CustomerSearchResponseDTO result = mapper.trxPersonToCustomerSearchDTO(response, securityHeaders);

        assertNull(result);
    }

    @Test
    void trxPersonToCustomerSearchDTOShouldMapCustomer() {
        TrxPersonResponse response = buildTrxPersonResponse("CLI");

        when(paramsService.findParameters(any(), any())).thenReturn(buildParameters());

        CustomerSearchResponseDTO result = mapper.trxPersonToCustomerSearchDTO(response, securityHeaders);

        assertNotNull(result);
        assertNotNull(result.getCustomers());
        assertEquals(1, result.getCustomers().size());
        assertNotNull(result.getPagination());
        assertEquals("12345678", result.getCustomers().get(0).getCustomerId());
        assertNull(result.getCustomers().get(0).getPerson().getGenderCode());
    }

    @Test
    void pef3ResponseToPef2RequestShouldMapData() {
        TrxPersonData trx = buildPersonData("CLI");
        trx.setCelular("300 123");
        trx.setTelefono("601 456");
        trx.setNombreVia("Cra#10-20 Ñandú");

        BasicData result = mapper.pef3ResponseToPef2Request(trx);

        assertNotNull(result);
        assertEquals("300123", result.getCelular());
        assertEquals("601456", result.getTelefono());
        assertEquals("Cra 10 20 Nandu", result.getNombreVia());
        assertEquals(trx.getNombre(), result.getNombre());
    }

    @Test
    void prospectPatchToPef2RequestShouldReturnEmptyWhenPersonIsNull() {
        CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();

        BasicData result = mapper.prospectPatchToPef2Request(request, securityHeaders, "CC");

        assertNotNull(result);
        assertNull(result.getNombre());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapFullRequest() {
        when(parameterApiService.getParameter(eq("0008"), isNull(), any()))
                .thenReturn(List.of(
                        new DataListDTO("0008", "05001", "MEDELLIN"),
                        new DataListDTO("0008", "99999", "NO INFORMADO")
                ));

        when(parameterApiService.getParameter(eq("0112"), isNull(), any()))
                .thenReturn(List.of(new DataListDTO("0112", "CO", "COL")));

        CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();
        PersonRequestDto person = new PersonRequestDto();

        PersonNameRequestDTO name = new PersonNameRequestDTO();
        name.setGivenName("JUAN   CARLOS");
        name.setLastName("PEREZ");
        name.setSecondLastName("GOMEZ");

        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode("CO");

        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setCountry(country);
        document.setTown("MEDELLIN");
        document.setIssueDate("2020-01-01");
        document.setExpirationDate("2030-01-01");

        PlaceOfBirthDTO place = new PlaceOfBirthDTO();
        place.setCountry(new CodeNameDTO("CO", "Colombia"));
        place.setTown("MEDELLIN");
        place.setTownCode("05001");

        person.setPersonName(name);
        person.setDocuments(List.of(document));
        person.setPlaceOfBirth(place);
        person.setBirthDate("1990-01-01");
        person.setGenderCode("H");

        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, securityHeaders, "CC");

        assertNotNull(result);
        assertEquals("JUAN CARLOS", result.getNombre());
        assertEquals("PEREZ", result.getPrimerApellido());
        assertEquals("GOMEZ", result.getSegundoApellido());
        assertEquals("COL", result.getPaisExpedicion());
        assertEquals("05001", result.getCiudadExpedicion());
        assertEquals("05001", result.getCiudadNacimiento());
        assertEquals("2020-01-01", result.getFechaExpedicion());
        assertEquals("2030-01-01", result.getDescripcionDireccion());
        assertEquals("1990-01-01", result.getFechaNacimiento());
        assertEquals("H", result.getSexo());
    }

    @Test
    void prospectPatchToPef2RequestShouldUseDefaultCityWhenDocumentTownIsBlank() {
        CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();
        PersonRequestDto person = new PersonRequestDto();

        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setTown("");

        person.setDocuments(List.of(document));
        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, securityHeaders, "CC");

        assertEquals("99999", result.getCiudadExpedicion());
    }

    @Test
    void prospectPutToPef2RequestShouldReturnEmptyWhenPersonIsNull() {
        UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();

        BasicData result = mapper.prospectPutToPef2Request(request, securityHeaders);

        assertNotNull(result);
        assertNull(result.getNombre());
    }

    @Test
    void prospectPutToPef2RequestShouldMapFullRequest() {
        when(parameterApiService.getParameter(eq("0112"), isNull(), any()))
                .thenReturn(List.of(new DataListDTO("0112", "CO", "COL")));

        when(parameterApiService.getParameter(eq("0008"), isNull(), any()))
                .thenReturn(List.of(new DataListDTO("0008", "05001", "MEDELLIN")));

        UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();

        PersonDTO person = new PersonDTO();
        PersonNameDTO personName = new PersonNameDTO();
        personName.setGivenName("JUAN");
        personName.setLastName("PEREZ");
        personName.setSecondLastName("GOMEZ");

        PlaceOfBirthDTO place = new PlaceOfBirthDTO();
        place.setCountry(new CodeNameDTO("CO", "Colombia"));
        place.setTown("MEDELLIN");

        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode("CC");
        document.setDocumentNumber("123456789");
        document.setIssueDate("2020-01-01");
        document.setCountry(new CodeNameDTO("CO", "Colombia"));

        person.setPersonName(personName);
        person.setGenderCode("H");
        person.setBirthDate("1990-01-01");
        person.setPlaceOfBirth(place);
        person.setCountryOfResidence(new CodeNameDTO("CO", "Colombia"));
        person.setFirstNationality(new CodeNameDTO("CO", "Colombia"));
        person.setDocuments(List.of(document));

        ContactPointDTO contactPoint = new ContactPointDTO();

        PostalAddressDTO postalAddress = new PostalAddressDTO();
        postalAddress.setStreetTypeCode("CL");
        postalAddress.setFullAddress("Calle 1");
        postalAddress.setTown(new CodeNameDTO("05001", "MEDELLIN"));
        postalAddress.setCountry(new CodeNameDTO("57", "Colombia"));

        PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
        phoneAddress.setInternationalCode("57");
        phoneAddress.setPhoneNumber("6011234567");
        phoneAddress.setMobileNumber("3001234567");

        ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();
        electronicAddress.setEmailAddress("test@test.com");

        contactPoint.setPostalAddress(postalAddress);
        contactPoint.setPhoneAddress(phoneAddress);
        contactPoint.setElectronicAddress(electronicAddress);

        request.setPerson(person);
        request.setContactPoints(List.of(contactPoint));

        BasicData result = mapper.prospectPutToPef2Request(request, securityHeaders);

        assertNotNull(result);
        assertEquals("JUAN", result.getNombre());
        assertEquals("PEREZ", result.getPrimerApellido());
        assertEquals("GOMEZ", result.getSegundoApellido());
        assertEquals("H", result.getSexo());
        assertEquals("1990-01-01", result.getFechaNacimiento());
        assertEquals("COL", result.getPaisNacimiento());
        assertEquals("05001", result.getCiudadNacimiento());
        assertEquals("COL", result.getPaisDireccion());
        assertEquals("COL", result.getNacionalidad());
        assertEquals("2020-01-01", result.getFechaExpedicion());
        assertEquals("COL", result.getPaisExpedicion());
        assertEquals("CL", result.getTipoVia());
        assertEquals("Calle 1", result.getNombreVia());
        assertEquals("MEDELLIN", result.getCiudad());
        assertEquals("57", result.getCodpaip());
        assertEquals("6011234567", result.getTelefono());
        assertEquals("3001234567", result.getCelular());
        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void usualtMapperShouldUsePositiveForeignTaxIndicator() {
        String result = mapper.usualtMapper("ABCXXXX", "YES");

        assertEquals("ABCCONS", result);
    }

    @Test
    void usualtMapperShouldUseNegativeForeignTaxIndicatorAndDefaultChannel() {
        String result = mapper.usualtMapper(null, "NO");

        assertEquals("ODSCONN", result);
    }

    @Test
    void cleanAddressShouldReplaceSpecialCharactersAndAccents() {
        String result = CustomerMapper.cleanAddress("Cra#10-20 Ñandú ÁÉÍÓÚ");

        assertEquals("Cra 10 20 Nandu AEIOU", result);
    }

    @Test
    void replaceSpacesShouldNormalizeSpaces() {
        String result = CustomerMapper.replaceSpaces("JUAN   CARLOS  PEREZ");

        assertEquals("JUAN CARLOS PEREZ", result);
    }

    private TrxPersonResponse buildTrxPersonResponse(String conper) {
        TrxPersonResponse response = new TrxPersonResponse();

        TrxBasicData trxBasicData = new TrxBasicData();
        trxBasicData.setDatosBasicos(buildPersonData(conper));

        response.setData(trxBasicData);

        return response;
    }

    private TrxPersonData buildPersonData(String conper) {
        TrxPersonData data = new TrxPersonData();

        data.setConper(conper);
        data.setNombre("JUAN");
        data.setPrimerApellido("PEREZ");
        data.setSegundoApellido("GOMEZ");
        data.setSexo("H");
        data.setFechaNacimiento("1990-01-01");
        data.setFechaExpedicion("2020-01-01");
        data.setTipoIdentificacion("CC");
        data.setNumeroIdentificacion("123456789");
        data.setNumper("12345678");
        data.setPaisExpedicion("COL");
        data.setPaisNacimiento("COL");
        data.setPaisDireccion("COL");
        data.setNacionalidad("COL");
        data.setCiudadExpedicion("05001");
        data.setCiudadNacimiento("05001");
        data.setCiudad("05001");
        data.setDepartamento("05");
        data.setTipoVia("CL");
        data.setNombreVia("Calle 1");
        data.setDescripcionDireccion("Direccion 2030-01-01");
        data.setTelefono("6011234567");
        data.setCelular("3001234567");
        data.setPrecelular("57");
        data.setEmail("test@test.com");
        data.setUsualt("ODSCONS");
        data.setAutorizoTelefono("true");
        data.setAutorizacionEmail("true");

        return data;
    }

    private Parameters buildParameters() {
        Parameters parameters = new Parameters();

        parameters.setStreetTypeDescription("Calle");
        parameters.setCityStandard(new CodeNameDTO("05001", "MEDELLIN"));
        parameters.setCountryDir(new CodeNameDTO("CO", "Colombia"));
        parameters.setCityDepartment(new CodeNameDTO("05", "ANTIOQUIA"));
        parameters.setCountryBirth(new CodeNameDTO("CO", "Colombia"));
        parameters.setCityBirth(new CodeNameDTO("05", "ANTIOQUIA"));
        parameters.setTown(new CodeNameDTO("05001", "MEDELLIN"));
        parameters.setCountryNationality(new CodeNameDTO("CO", "Colombia"));
        parameters.setDocumentTypeDescription("Cedula");
        parameters.setCityExp(new CodeNameDTO("05", "ANTIOQUIA"));
        parameters.setCountryExp(new CodeNameDTO("CO", "Colombia"));
        parameters.setTownDocument(new CodeNameDTO("05001", "MEDELLIN"));

        return parameters;
    }
}