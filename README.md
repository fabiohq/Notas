package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ContextApiService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.BasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxBasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ClientEnum; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ParametersEnums; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorType; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.mappers.CustomerContactPointsMapper; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.CustomerMapperUtils; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexTypes; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexUtils; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.ArgumentCaptor; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.http.HttpStatus; import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections; import java.util.List;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.ArgumentMatchers.; import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) class ParamServiceImplTest {

@Mock
private ParameterApiService parameterApiService;

private ParamServiceImpl service;
private SecurityHeaders securityHeaders;

@BeforeEach
void setUp() {
    service = new ParamServiceImpl(parameterApiService);
    securityHeaders = new SecurityHeaders("Bearer token", "client-id");
}

@Test
void shouldGetWayTypeDescriptionWhenParameterExists() {
    when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "CL", securityHeaders))
            .thenReturn(List.of(dataList("CL", "Calle")));

    String response = service.getWayTypeDescription("CL", securityHeaders);

    assertEquals("Calle", response);
}

@Test
void shouldReturnEmptyWayTypeDescriptionWhenValueIsNullOrEmpty() {
    assertEquals("", service.getWayTypeDescription(null, securityHeaders));
    assertEquals("", service.getWayTypeDescription("", securityHeaders));
    verifyNoInteractions(parameterApiService);
}

@Test
void shouldReturnEmptyWayTypeDescriptionWhenParameterIsEmpty() {
    when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "XX", securityHeaders))
            .thenReturn(Collections.emptyList());

    String response = service.getWayTypeDescription("XX", securityHeaders);

    assertEquals("", response);
}

@Test
void shouldGetWayTypesWhenParameterExists() {
    when(parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), "KR", securityHeaders))
            .thenReturn(List.of(dataList("KR", "Carrera")));

    String response = service.getWayTypes("KR", securityHeaders);

    assertEquals("Carrera", response);
}

@Test
void shouldReturnEmptyWayTypesWhenValueIsNullOrEmpty() {
    assertEquals("", service.getWayTypes(null, securityHeaders));
    assertEquals("", service.getWayTypes("", securityHeaders));
    verifyNoInteractions(parameterApiService);
}

@Test
void shouldReturnCountryWhenParameterExists() {
    when(parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), "CO", securityHeaders))
            .thenReturn(List.of(dataList("CO", "Colombia")));

    CodeNameDTO response = service.getCountry("CO", securityHeaders);

    assertEquals("CO", response.getCode());
    assertEquals("Colombia", response.getName());
}

@Test
void shouldReturnEmptyCountryWhenParameterIsNullOrEmpty() {
    when(parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), "CO", securityHeaders))
            .thenReturn(null);
    CodeNameDTO nullResponse = service.getCountry("CO", securityHeaders);
    assertNull(nullResponse.getCode());
    assertNull(nullResponse.getName());

    when(parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), "US", securityHeaders))
            .thenReturn(Collections.emptyList());
    CodeNameDTO emptyResponse = service.getCountry("US", securityHeaders);
    assertNull(emptyResponse.getCode());
    assertNull(emptyResponse.getName());
}

@Test
void shouldReturnTownWhenParameterExists() {
    when(parameterApiService.getParameter(ParametersEnums.TOWNS.value(), "11001", securityHeaders))
            .thenReturn(List.of(dataList("11001", "Bogota")));

    CodeNameDTO response = service.getTown("11001", securityHeaders);

    assertEquals("11001", response.getCode());
    assertEquals("Bogota", response.getName());
}

@Test
void shouldReturnCityUsingFirstTwoCharacters() {
    when(parameterApiService.getParameter(ParametersEnums.STATES.value(), "11", securityHeaders))
            .thenReturn(List.of(dataList("11", "Bogota D.C.")));

    CodeNameDTO response = service.getCity("11001", securityHeaders);

    assertEquals("11", response.getCode());
    assertEquals("Bogota D.C.", response.getName());
}

@Test
void shouldReturnEmptyCityWhenResponseIsNullOrEmpty() {
    when(parameterApiService.getParameter(ParametersEnums.STATES.value(), "", securityHeaders))
            .thenReturn(null);
    CodeNameDTO nullResponse = service.getCity("", securityHeaders);
    assertNull(nullResponse.getCode());
    assertNull(nullResponse.getName());

    when(parameterApiService.getParameter(ParametersEnums.STATES.value(), "12", securityHeaders))
            .thenReturn(Collections.emptyList());
    CodeNameDTO emptyResponse = service.getCity("12001", securityHeaders);
    assertNull(emptyResponse.getCode());
    assertNull(emptyResponse.getName());
}

private DataListDTO dataList(String code, String description) {
    DataListDTO dataListDTO = new DataListDTO();
    dataListDTO.setCode(code);
    dataListDTO.setDescription(description);
    return dataListDTO;
}

}

@ExtendWith(MockitoExtension.class) class ProspectContactPointSevicerImplTest {

@Mock
private TrxPersonService trxPersonService;
@Mock
private ContextApiService contextService;
@Mock
private CustomerContactPointsMapper mapper;
@Mock
private RegexUtils regexUtils;
@Mock
private CustomerMapperUtils mapperUtils;
@Mock
private ErrorService errorService;
@Mock
private ParameterApiService parameterApiService;

private ProspectContactPointSevicerImpl service;
private SecurityHeaders securityHeaders;

@BeforeEach
void setUp() {
    service = new ProspectContactPointSevicerImpl(
            trxPersonService,
            contextService,
            mapper,
            regexUtils,
            mapperUtils,
            errorService,
            parameterApiService
    );
    ReflectionTestUtils.setField(service, "CONTACT_POINT_ID", "PRI001");
    securityHeaders = new SecurityHeaders("Bearer token", "client-id");
}

@Test
void shouldReturnCustomerDetailsFromContextWhenExists() {
    ContactPointsResponseDTO cachedResponse = new ContactPointsResponseDTO();
    when(contextService.getContext("12345678")).thenReturn(cachedResponse);

    ContactPointsResponseDTO response = service.getCustomerDetails("12345678", securityHeaders);

    assertNotNull(response);
    verify(contextService).getContext("12345678");
    verifyNoInteractions(trxPersonService, mapper);
}

@Test
void shouldCallTrxAndMapperWhenContextIsNull() {
    ContactPointsResponseDTO mappedResponse = new ContactPointsResponseDTO();
    TrxPersonResponse trxResponse = buildTrxPersonResponse("CLI", "Direccion 2020-01-01");

    when(contextService.getContext("12345678")).thenReturn(null);
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
    when(mapper.trxPersonToCustomerDetailsDTO(trxResponse, securityHeaders)).thenReturn(mappedResponse);

    ContactPointsResponseDTO response = service.getCustomerDetails("12345678", securityHeaders);

    assertSame(mappedResponse, response);
    verify(trxPersonService).callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3));
    verify(mapper).trxPersonToCustomerDetailsDTO(trxResponse, securityHeaders);
}

@Test
void shouldThrowServiceExceptionWhenProspectIdIsNotNumeric() {
    assertThrows(ServiceException.class, () -> service.getCustomerDetails("ABC45678", securityHeaders));
    verifyNoInteractions(contextService, trxPersonService, mapper);
}

@Test
void shouldThrowServiceExceptionWhenProspectIdLengthIsInvalid() {
    assertThrows(ServiceException.class, () -> service.getCustomerDetails("1234567", securityHeaders));
    verifyNoInteractions(contextService, trxPersonService, mapper);
}

@Test
void shouldPatchProspectContactPointSuccessfullyWithAllSections() {
    ContactPointsRequestDTO request = buildPatchRequest();
    TrxPersonResponse trxResponse = buildTrxPersonResponse("PRO", "Direccion original 2020-01-01");
    BasicData basicDataFromPef3 = new BasicData();
    basicDataFromPef3.setEstciv("S");
    basicDataFromPef3.setDescripcionDireccion("Direccion base");
    basicDataFromPef3.setUsualt("APPNO");

    BasicData patchData = new BasicData();
    patchData.setCelular("3001234567");
    patchData.setEmail("test@santander.com");
    patchData.setDescripcionDireccion("Apto 101");

    when(parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders))
            .thenReturn(List.of(dataList("11001", "Bogota")));
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
    when(mapper.pef3ResponseToPef2Request(any(TrxPersonData.class))).thenReturn(basicDataFromPef3);
    when(mapper.contactPointPatchToPef2Request(request)).thenReturn(patchData);
    when(mapperUtils.getForeignTaxIndicator(any(TrxPersonData.class))).thenReturn("NO");
    when(mapper.usualtMapper("APPNO", "NO")).thenReturn("APPPNO");
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2))).thenReturn(trxResponse);

    service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders);

    verify(regexUtils).validateRegex(RegexTypes.ONLY_NUMBERS, "12345678", "prospect_Id");
    verify(regexUtils).validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "prospect_Id");
    verify(regexUtils).validateRegex(RegexTypes.STREET_TYPE_CODE_FORMAT, "CL", "streetTypeCode");
    verify(regexUtils).validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, "CL", "streetTypeCode");
    verify(regexUtils).validateRegex(RegexTypes.ADDRESS_LENGTH, "Calle 100", "fullAddress");
    verify(regexUtils).validateRegex(RegexTypes.ADDRESS_FORMAT, "Calle 100", "fullAddress");
    verify(regexUtils).validateRegex(RegexTypes.PREMISE_LENGTH, "Apto 101", "premise");
    verify(regexUtils).validateRegex(RegexTypes.PREMISE_FORMAT, "Apto 101", "premise");
    verify(regexUtils).validateRegex(RegexTypes.PHONE_FORMAT, "3001234567", "mobileNumber");
    verify(regexUtils).validateRegex(RegexTypes.PHONE_LENGTH, "3001234567", "mobileNumber");
    verify(regexUtils).validateRegex(RegexTypes.PHONE_FORMAT, "6011234567", "phoneNumber");
    verify(regexUtils).validateRegex(RegexTypes.PHONE_LENGTH, "6011234567", "phoneNumber");
    verify(regexUtils).validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, "57", "internationalCode");
    verify(regexUtils).validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, "57", "internationalCode");
    verify(regexUtils).validateRegex(RegexTypes.EMAIL, "test@santander.com", "emailAddress");
    verify(regexUtils).validateRegex(RegexTypes.EMAIL_LENGTH, "test@santander.com", "emailAddress");
    verify(regexUtils).validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, "t", "emailAddress");
    verify(regexUtils).validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, "test", "emailAddress");
    verify(regexUtils).validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, "santander.com", "emailAddress");

    ArgumentCaptor<TrxPersonRequest> pef2RequestCaptor = ArgumentCaptor.forClass(TrxPersonRequest.class);
    verify(trxPersonService).callPostTRX(pef2RequestCaptor.capture(), eq(ClientEnum.PEF2));
    BasicData sentBasicData = pef2RequestCaptor.getValue().getData().getDatosBasicos();
    assertEquals("C", sentBasicData.getEstciv());
    assertEquals("APPPNO", sentBasicData.getUsualt());
    assertEquals("Apto 101                                          2020-01-01", sentBasicData.getDescripcionDireccion());
}

@Test
void shouldPatchAndSetCivilStatusToSWhenCivilStatusIsBlank() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    TrxPersonResponse trxResponse = buildTrxPersonResponse("PRO", "short");
    BasicData basicDataFromPef3 = new BasicData();
    basicDataFromPef3.setEstciv(" ");
    basicDataFromPef3.setDescripcionDireccion("Base");
    basicDataFromPef3.setUsualt("APPNO");
    BasicData patchData = new BasicData();

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
    when(mapper.pef3ResponseToPef2Request(any(TrxPersonData.class))).thenReturn(basicDataFromPef3);
    when(mapper.contactPointPatchToPef2Request(request)).thenReturn(patchData);
    when(mapperUtils.getForeignTaxIndicator(any(TrxPersonData.class))).thenReturn("NO");
    when(mapper.usualtMapper("APPNO", "NO")).thenReturn("APPPNO");
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2))).thenReturn(trxResponse);

    service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders);

    ArgumentCaptor<TrxPersonRequest> captor = ArgumentCaptor.forClass(TrxPersonRequest.class);
    verify(trxPersonService).callPostTRX(captor.capture(), eq(ClientEnum.PEF2));
    assertEquals("S", captor.getValue().getData().getDatosBasicos().getEstciv());
    assertEquals("Base                                              short", captor.getValue().getData().getDatosBasicos().getDescripcionDireccion());
}

@Test
void shouldPatchAndSetCivilStatusToSWhenCivilStatusIsDifferentFromS() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    TrxPersonResponse trxResponse = buildTrxPersonResponse("PRO", "Direccion 2020-01-01");
    BasicData basicDataFromPef3 = new BasicData();
    basicDataFromPef3.setEstciv("C");
    basicDataFromPef3.setDescripcionDireccion(null);
    basicDataFromPef3.setUsualt("APPNO");
    BasicData patchData = new BasicData();

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
    when(mapper.pef3ResponseToPef2Request(any(TrxPersonData.class))).thenReturn(basicDataFromPef3);
    when(mapper.contactPointPatchToPef2Request(request)).thenReturn(patchData);
    when(mapperUtils.getForeignTaxIndicator(any(TrxPersonData.class))).thenReturn("NO");
    when(mapper.usualtMapper("APPNO", "NO")).thenReturn("APPPNO");
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2))).thenReturn(trxResponse);

    service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders);

    ArgumentCaptor<TrxPersonRequest> captor = ArgumentCaptor.forClass(TrxPersonRequest.class);
    verify(trxPersonService).callPostTRX(captor.capture(), eq(ClientEnum.PEF2));
    assertEquals("S", captor.getValue().getData().getDatosBasicos().getEstciv());
    assertNull(captor.getValue().getData().getDatosBasicos().getDescripcionDireccion());
}

@Test
void shouldPatchAndSetCivilStatusToSWhenCivilStatusIsNull() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    TrxPersonResponse trxResponse = buildTrxPersonResponse("PRO", "Direccion 2020-01-01");
    BasicData basicDataFromPef3 = new BasicData();
    basicDataFromPef3.setEstciv(null);
    basicDataFromPef3.setDescripcionDireccion("Base");
    basicDataFromPef3.setUsualt("APPNO");
    BasicData patchData = new BasicData();

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
    when(mapper.pef3ResponseToPef2Request(any(TrxPersonData.class))).thenReturn(basicDataFromPef3);
    when(mapper.contactPointPatchToPef2Request(request)).thenReturn(patchData);
    when(mapperUtils.getForeignTaxIndicator(any(TrxPersonData.class))).thenReturn("NO");
    when(mapper.usualtMapper("APPNO", "NO")).thenReturn("APPPNO");
    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2))).thenReturn(trxResponse);

    service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders);

    ArgumentCaptor<TrxPersonRequest> captor = ArgumentCaptor.forClass(TrxPersonRequest.class);
    verify(trxPersonService).callPostTRX(captor.capture(), eq(ClientEnum.PEF2));
    assertEquals("S", captor.getValue().getData().getDatosBasicos().getEstciv());
}

@Test
void shouldThrowWhenContactPointIdIsInvalid() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    ServiceException serviceException = new ServiceException(HttpStatus.BAD_REQUEST, null);
    when(errorService.getInvalidValue()).thenReturn("invalid value");
    when(errorService.errorBuilder(eq(HttpStatus.BAD_REQUEST), contains("contact_point_id"), eq(ErrorType.FUNCTIONAL)))
            .thenReturn(serviceException);

    ServiceException response = assertThrows(ServiceException.class,
            () -> service.patchProspectContactPoint("12345678", "OTHER", request, securityHeaders));

    assertSame(serviceException, response);
    verifyNoInteractions(trxPersonService, mapper, mapperUtils, parameterApiService);
}

@Test
void shouldThrowWhenTownIsInvalid() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    PostalAddressDTO postalAddress = new PostalAddressDTO();
    postalAddress.setTown("99999");
    request.setPostalAddress(postalAddress);
    ServiceException serviceException = new ServiceException(HttpStatus.BAD_REQUEST, null);

    when(parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders))
            .thenReturn(List.of(dataList("11001", "Bogota")));
    when(errorService.getInvalidValue()).thenReturn("invalid value");
    when(errorService.errorBuilder(eq(HttpStatus.BAD_REQUEST), contains("town"), eq(ErrorType.FUNCTIONAL)))
            .thenReturn(serviceException);

    ServiceException response = assertThrows(ServiceException.class,
            () -> service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders));

    a


