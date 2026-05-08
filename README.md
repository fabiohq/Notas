Te dejo los 2 tests separados:
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ProspectMapperUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PatchProspectMapperTest {

    @Mock RegexUtils regexUtils;
    @Mock ProspectMapperUtils prospectMapperUtils;
    @Mock ErrorService errorService;
    @Mock ParameterApiService parameterApiService;
    @Mock ParamService paramsService;

    private PatchProspectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mapper = new PatchProspectMapper(
                regexUtils,
                prospectMapperUtils,
                errorService,
                parameterApiService,
                paramsService
        );

        ReflectionTestUtils.setField(mapper, "dEFAULTcHANNEL", "ODS");
        ReflectionTestUtils.setField(mapper, "fOREIGNtAXiNDICATORpOSITIVE", "SI");
        ReflectionTestUtils.setField(mapper, "fOREIGNtAXiNDICATORnEGATIVE", "NO");

        errorService.invalidValueMessage = "Invalid value";
    }

    @Test
    void prospectPatchToPef2RequestWhenPersonIsNullShouldReturnEmptyBasicData() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertNotNull(result);
        assertNull(result.getNombre());
        assertNull(result.getPrimerApellido());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapPersonName() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        PersonRequestDTO person = new PersonRequestDTO();
        PersonNameRequestDTO name = new PersonNameRequestDTO();

        name.setGivenName("JUAN   CARLOS");
        name.setLastName("PEREZ");
        name.setSecondLastName("GOMEZ");

        person.setPersonName(name);
        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("JUAN CARLOS", result.getNombre());
        assertEquals("PEREZ", result.getPrimerApellido());
        assertEquals("GOMEZ", result.getSegundoApellido());

        verify(regexUtils, atLeastOnce()).validateRegex(any(), anyString(), anyString());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapDocuments() {
        when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
                .thenReturn(List.of(
                        DataListDTO.builder().code("11001").description("BOGOTA").build(),
                        DataListDTO.builder().code("99999").description("NO INFORMADO").build()
                ));

        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        PersonRequestDTO person = new PersonRequestDTO();

        DocumentRequestDTO document = new DocumentRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode("CO");

        document.setCountry(country);
        document.setTown("BOGOTA");
        document.setIssueDate("2024-01-01");
        document.setExpirationDate("2030-01-01");

        person.setDocuments(List.of(document));
        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("COL", result.getPaisExpedicion());
        assertEquals("11001", result.getCiudadExpedicion());
        assertEquals("2024-01-01", result.getFechaExpedicion());
        assertEquals("2030-01-01", result.getDescripcionDireccion());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapPlaceOfBirthByTownCode() {
        when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("05001").description("MEDELLIN").build()));

        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        PersonRequestDTO person = new PersonRequestDTO();

        PlaceOfBirthRequestDTO place = new PlaceOfBirthRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode("CO");

        place.setCountry(country);
        place.setTownCode("05001");

        person.setPlaceOfBirth(place);
        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("COL", result.getPaisNacimiento());
        assertEquals("05001", result.getCiudadNacimiento());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapBirthDateAndGender() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        PersonRequestDTO person = new PersonRequestDTO();

        person.setBirthDate("1990-01-01");
        person.setGenderCode("M");

        request.setPerson(person);

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("1990-01-01", result.getFechaNacimiento());
        assertEquals("M", result.getSexo());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapContactPoint() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        request.setPerson(new PersonRequestDTO());

        ContactPointRequestDTO contact = new ContactPointRequestDTO();

        PostalAddressRequestDTO postal = new PostalAddressRequestDTO();
        postal.setStreetTypeCode("CL");
        postal.setFullAddress("CALLE 1");
        postal.setTownName("BOGOTA");
        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode("CO");
        postal.setCountry(country);

        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO();
        phone.setInternationalCode("57");
        phone.setPhoneNumber("6011234567");
        phone.setMobileNumber("3001234567");

        ElectronicAddressRequestDTO email = new ElectronicAddressRequestDTO();
        email.setEmailAddress("test@test.com");

        contact.setPostalAddress(postal);
        contact.setPhoneAddress(phone);
        contact.setElectronicAddress(email);

        request.setContactPoints(List.of(contact));

        BasicData result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("CL", result.getTipoVia());
        assertEquals("CALLE 1", result.getNombreVia());
        assertEquals("BOGOTA", result.getCiudad());
        assertEquals("CO", result.getCodpaip());
        assertEquals("57", result.getIndicativo());
        assertEquals("6011234567", result.getTelefono());
        assertEquals("3001234567", result.getCelular());
        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void prospectPatchToPef2RequestShouldThrowWhenForeignTaxIndicatorInvalid() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();
        PersonRequestDTO person = new PersonRequestDTO();
        person.setForeignTaxIndicator("MAYBE");
        request.setPerson(person);

        assertThrows(ServiceException.class,
                () -> mapper.prospectPatchToPef2Request(request, "auth", "client"));
    }

    @Test
    void usualtMapperShouldUseExistingPrefixAndPositiveIndicator() {
        String result = mapper.usualtMapper("ABC1234", "YES");

        assertEquals("ABCSI", result);
    }

    @Test
    void usualtMapperShouldUseDefaultChannelAndNegativeIndicator() {
        String result = mapper.usualtMapper(null, "NO");

        assertEquals("ODSNO", result);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ProspectMapperUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProspectMapperTest {

    @Mock RegexUtils regexUtils;
    @Mock ProspectMapperUtils prospectMapperUtils;
    @Mock ErrorService errorService;
    @Mock ParameterApiService parameterApiService;
    @Mock ParamService paramsService;

    private ProspectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mapper = new ProspectMapper(
                regexUtils,
                prospectMapperUtils,
                errorService,
                parameterApiService,
                paramsService
        );

        ReflectionTestUtils.setField(mapper, "defaultChannel", "ODS");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorPositive", "SI");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorNegative", "NO");

        errorService.invalidValueMessage = "Invalid value";

        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), anyString(), any()))
                .thenAnswer(invocation -> new ServiceException(
                        invocation.getArgument(0),
                        invocation.getArgument(1)
                ));
    }

    @Test
    void replaceSpacesShouldRemoveDoubleAndTripleSpaces() {
        String result = mapper.replaceSpaces("JUAN   CARLOS  PEREZ");

        assertEquals("JUAN CARLOS PEREZ", result);
    }

    @Test
    void dtoRequestToTrxRequestShouldMapBasicCreateProspectCC() {
        when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
                .thenReturn(List.of(
                        DataListDTO.builder().code("05").description("ANTIOQUIA").build(),
                        DataListDTO.builder().code("05101").description("CIUDAD").build(),
                        DataListDTO.builder().code("99999").description("NO INFORMADO").build()
                ));

        CreateProspectRequestDTO request = buildCreateRequest("CC", "CO", "05101");

        TrxPersonRequest result = mapper.dtoRequestToTrxRequest(request, "auth", "client");

        assertNotNull(result);
        assertNotNull(result.getData());
        assertNotNull(result.getData().getDatosBasicos());

        assertEquals("CC", result.getData().getDatosBasicos().getTipoIdentificacion());
        assertEquals("12345678901", result.getData().getDatosBasicos().getNumeroIdentificacion());
        assertEquals("JUAN", result.getData().getDatosBasicos().getNombre());
        assertEquals("PEREZ", result.getData().getDatosBasicos().getPrimerApellido());
        assertEquals("GOMEZ", result.getData().getDatosBasicos().getSegundoApellido());
        assertEquals("COL", result.getData().getDatosBasicos().getPaisNacimiento());
        assertEquals("05101", result.getData().getDatosBasicos().getCiudadNacimiento());
        assertEquals("test@test.com", result.getData().getDatosBasicos().getEmail());
        assertEquals("3001234567", result.getData().getDatosBasicos().getCelular());
        assertEquals("3001234567", result.getData().getDatosBasicos().getTelefono());
        assertEquals("004", result.getData().getDatosBasicos().getClase());
        assertEquals("10000001", result.getData().getDatosBasicos().getAgrofic());
    }

    @Test
    void dtoRequestToTrxRequestShouldDefaultCountryToColombiaWhenDocumentIsCC() {
        CreateProspectRequestDTO request = buildCreateRequest("CC", "US", null);

        TrxPersonRequest result = mapper.dtoRequestToTrxRequest(request, "auth", "client");

        assertEquals("COL", result.getData().getDatosBasicos().getPaisNacimiento());
        assertEquals("05101", result.getData().getDatosBasicos().getCiudadNacimiento());
    }

    @Test
    void dtoRequestToTrxRequestShouldThrowWhenCEDocumentHasColombiaAsBirthCountry() {
        CreateProspectRequestDTO request = buildCreateRequest("CE", "CO", "99999");

        assertThrows(ServiceException.class,
                () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
    }

    @Test
    void dtoRequestToTrxRequestShouldThrowWhenBirthDateIsFuture() {
        CreateProspectRequestDTO request = buildCreateRequest("CC", "CO", null);
        request.getPerson().setBirthDate(LocalDate.now().plusDays(1).toString());

        assertThrows(ServiceException.class,
                () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
    }

    @Test
    void dtoRequestToTrxRequestShouldThrowWhenMobileNumberInvalid() {
        CreateProspectRequestDTO request = buildCreateRequest("CC", "CO", null);
        request.getContactPoints().get(0).getPhoneAddress().setMobileNumber("123");

        assertThrows(ServiceException.class,
                () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
    }

    @Test
    void prospectPatchToPef2RequestWhenPersonNullShouldReturnEmptyBasicData() {
        CreateProspectRequestDTO request = new CreateProspectRequestDTO();

        var result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertNotNull(result);
        assertNull(result.getNombre());
    }

    @Test
    void prospectPatchToPef2RequestShouldMapPatchFields() {
        when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("05101").description("CIUDAD").build()));

        CreateProspectRequestDTO request = buildCreateRequest("CC", "CO", "05101");

        var result = mapper.prospectPatchToPef2Request(request, "auth", "client");

        assertEquals("JUAN", result.getNombre());
        assertEquals("PEREZ", result.getPrimerApellido());
        assertEquals("GOMEZ", result.getSegundoApellido());
        assertEquals("COL", result.getPaisExpedicion());
        assertEquals("05101", result.getCiudadExpedicion());
        assertEquals("1990-01-01", result.getFechaNacimiento());
        assertEquals("test@test.com", result.getEmail());
    }

    @Test
    void usualtMapperShouldUseExistingPrefixAndPositiveIndicator() {
        String result = mapper.usualtMapper("ABC1234", "YES");

        assertEquals("ABCSI", result);
    }

    @Test
    void usualtMapperShouldUseDefaultChannelAndNegativeIndicator() {
        String result = mapper.usualtMapper(null, "NO");

        assertEquals("ODSNO", result);
    }

    @Test
    void validateDocumentTypeForCountryShouldForceCOWhenDocumentIsCC() {
        CreateProspectRequestDTO request = buildCreateRequest("CC", "US", null);

        CreateProspectRequestDTO result = mapper.validateDocumentTypeForCountry(request);

        assertEquals("CO", result.getPerson().getPlaceOfBirth().getCountry().getCode());
    }

    @Test
    void validateDocumentTypeForCountryShouldThrowWhenCEAndCountryCO() {
        CreateProspectRequestDTO request = buildCreateRequest("CE", "CO", null);

        assertThrows(ServiceException.class,
                () -> mapper.validateDocumentTypeForCountry(request));
    }

    private CreateProspectRequestDTO buildCreateRequest(String documentType, String birthCountry, String townCode) {
        CreateProspectRequestDTO request = new CreateProspectRequestDTO();

        PersonRequestDTO person = new PersonRequestDTO();

        PersonNameRequestDTO name = new PersonNameRequestDTO();
        name.setGivenName("JUAN");
        name.setLastName("PEREZ");
        name.setSecondLastName("GOMEZ");

        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setDocumentTypeCode(documentType);
        document.setDocumentNumber("12345678901");
        document.setIssueDate("2024-01-01");
        document.setTown("05101");

        CountryRequestDTO documentCountry = new CountryRequestDTO();
        documentCountry.setCode("CO");
        document.setCountry(documentCountry);

        PlaceOfBirthRequestDTO place = new PlaceOfBirthRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();
        country.setCode(birthCountry);
        place.setCountry(country);

        StateRequestDTO state = new StateRequestDTO();
        state.setCode("05");
        place.setState(state);
        place.setTownCode(townCode);

        FirstNationalityRequestDTO firstNationality = new FirstNationalityRequestDTO();
        firstNationality.setCode("CO");

        CountryOfResidenceRequestDTO residence = new CountryOfResidenceRequestDTO();
        residence.setCode("CO");

        person.setPersonName(name);
        person.setDocuments(List.of(document));
        person.setPlaceOfBirth(place);
        person.setBirthDate("1990-01-01");
        person.setGenderCode("M");
        person.setFirstNationality(firstNationality);
        person.setCountryOfResidence(residence);

        ContactPointRequestDTO contact = new ContactPointRequestDTO();

        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO();
        phone.setInternationalCode("57");
        phone.setMobileNumber("3001234567");
        phone.setPhoneNumber("");

        ElectronicAddressRequestDTO email = new ElectronicAddressRequestDTO();
        email.setEmailAddress("test@test.com");

        contact.setPhoneAddress(phone);
        contact.setElectronicAddress(email);

        request.setPerson(person);
        request.setContactPoints(List.of(contact));

        return request;
    }
}
Ojo: si ServiceException(HttpStatus, String) no te sirve para el mock de serviceExceptionBuilder, cámbialo por un ErrorDTO como en tus tests anteriores.