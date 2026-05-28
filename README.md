package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ContextApiService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.BasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxBasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ClientEnum; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ParametersEnums; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorType; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.mappers.CustomerContactPointsMapper; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.CustomerMapperUtils; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexTypes; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexUtils; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.ArgumentCaptor; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.http.HttpStatus; import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.ArgumentMatchers.; import static org.mockito.Mockito.*;

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

    assertSame(cachedResponse, response);
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
void shouldPatchProspectContactPointSuccessfully() {
    ContactPointsRequestDTO request = buildPatchRequest();

    TrxPersonResponse trxResponse = buildTrxPersonResponse("PRO", "Direccion 2020-01-01");

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

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3)))
            .thenReturn(trxResponse);

    when(mapper.pef3ResponseToPef2Request(any(TrxPersonData.class)))
            .thenReturn(basicDataFromPef3);

    when(mapper.contactPointPatchToPef2Request(request))
            .thenReturn(patchData);

    when(mapperUtils.getForeignTaxIndicator(any(TrxPersonData.class)))
            .thenReturn("NO");

    when(mapper.usualtMapper("APPNO", "NO"))
            .thenReturn("APPPNO");

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2)))
            .thenReturn(trxResponse);

    service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders);

    verify(regexUtils).validateRegex(RegexTypes.ONLY_NUMBERS, "12345678", "prospect_Id");
    verify(regexUtils).validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "prospect_Id");

    ArgumentCaptor<TrxPersonRequest> captor = ArgumentCaptor.forClass(TrxPersonRequest.class);

    verify(trxPersonService).callPostTRX(captor.capture(), eq(ClientEnum.PEF2));

    BasicData sentData = captor.getValue().getData().getDatosBasicos();

    assertEquals("C", sentData.getEstciv());
    assertEquals("APPPNO", sentData.getUsualt());
    assertEquals("Apto 101                                          2020-01-01", sentData.getDescripcionDireccion());
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

    verifyNoInteractions(trxPersonService, mapper, mapperUtils);
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

    assertSame(serviceException, response);
}

@Test
void shouldThrowWhenProspectIsNotValidOnPatch() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    TrxPersonResponse trxResponse = buildTrxPersonResponse("CLI", "Direccion 2020-01-01");

    when(trxPersonService.callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF3)))
            .thenReturn(trxResponse);

    assertThrows(ServiceException.class,
            () -> service.patchProspectContactPoint("12345678", "PRI001", request, securityHeaders));

    verify(trxPersonService, never()).callPostTRX(any(TrxPersonRequest.class), eq(ClientEnum.PEF2));
}

private ContactPointsRequestDTO buildPatchRequest() {
    PostalAddressDTO postalAddress = new PostalAddressDTO();
    postalAddress.setStreetTypeCode("CL");
    postalAddress.setFullAddress("Calle 100");
    postalAddress.setTown("11001");
    postalAddress.setPremise("Apto 101");

    PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
    phoneAddress.setMobileNumber("3001234567");
    phoneAddress.setPhoneNumber("6011234567");
    phoneAddress.setInternationalCode("57");

    ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();
    electronicAddress.setEmailAddress("test@santander.com");

    ContactPointsRequestDTO request = new ContactPointsRequestDTO();
    request.setPostalAddress(postalAddress);
    request.setPhoneAddress(phoneAddress);
    request.setElectronicAddress(electronicAddress);

    return request;
}

private TrxPersonResponse buildTrxPersonResponse(String conper, String descripcionDireccion) {
    TrxPersonData personData = new TrxPersonData();
    personData.setConper(conper);
    personData.setDescripcionDireccion(descripcionDireccion);

    TrxBasicData trxBasicData = new TrxBasicData();
    trxBasicData.setDatosBasicos(personData);

    TrxPersonResponse response = new TrxPersonResponse();
    response.setData(trxBasicData);

    return response;
}

private DataListDTO dataList(String code, String description) {
    DataListDTO dto = new DataListDTO();
    dto.setCode(code);
    dto.setDescription(description);
    return dto;
}

}
