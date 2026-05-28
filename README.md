
package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.mappers;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.CustomerRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.DocumentRequestDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.PersonRequestDto; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response.CustomerSearchResponseDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.BasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxBasicData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*; import static org.mockito.ArgumentMatchers.any; import static org.mockito.ArgumentMatchers.anyString; import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) class CustomerContactPointsMapperTest {

@Mock
private ParameterApiService parameterApiService;

private CustomerContactPointsMapper mapper;
private SecurityHeaders securityHeaders;

@BeforeEach
void setUp() {
    mapper = new CustomerContactPointsMapper(parameterApiService);
    ReflectionTestUtils.setField(mapper, "defaultChannel", "APP");
    ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorPositive", "PYES");
    ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorNegative", "PNO");

    securityHeaders = new SecurityHeaders();
    securityHeaders.setAuthorization("Bearer token");
    securityHeaders.setxSantanderClientId("client-id");
}

@Test
void shouldMapTrxPersonToCustomerDetailsDTO() {
    mockParameters();
    TrxPersonResponse trxPersonResponse = buildTrxPersonResponse("CLI");

    ContactPointsResponseDTO response = mapper.trxPersonToCustomerDetailsDTO(trxPersonResponse, securityHeaders);

    assertNotNull(response);
    assertNotNull(response.getContactPoints());
    assertEquals(1, response.getContactPoints().size());
    assertEquals("PRI001", response.getContactPoints().get(0).getContactPointId());
    assertTrue(response.getContactPoints().get(0).getPreferredIndicator());
    assertTrue(response.getContactPoints().get(0).getPrimaryIndicator());
    assertEquals("3001234567", response.getContactPoints().get(0).getPhoneAddress().getMobileNumber());
    assertEquals("6011234567", response.getContactPoints().get(0).getPhoneAddress().getPhoneNumber());
    assertEquals("300", response.getContactPoints().get(0).getPhoneAddress().getInternationalCode());
    assertEquals("123", response.getContactPoints().get(0).getPhoneAddress().getExtension());
    assertEquals("test@santander.com", response.getContactPoints().get(0).getElectronicAddress().getEmailAddress());
    assertEquals("CL", response.getContactPoints().get(0).getPostalAddress().getStreetTypeCode());
    assertEquals("Calle 100", response.getContactPoints().get(0).getPostalAddress().getFullAddress());
    assertEquals("Descripcion CL", response.getContactPoints().get(0).getPostalAddress().getStreetTypeDescription());
    assertEquals("Descripcion BOG11001", response.getContactPoints().get(0).getPostalAddress().getTownName());
    assertEquals("Descripcion CO", response.getContactPoints().get(0).getPostalAddress().getCountry().getName());
    assertEquals("Descripcion BO", response.getContactPoints().get(0).getPostalAddress().getState().getName());
    assertEquals("Apto 101", response.getContactPoints().get(0).getPostalAddress().getPremise());
    assertEquals("PRI", response.getContactPoints().get(0).getUseTypes().get(0).getCode());
}

@Test
void shouldReturnNullWhenCustomerDetailsIsProspect() {
    TrxPersonResponse trxPersonResponse = buildTrxPersonResponse("PRO");

    ContactPointsResponseDTO response = mapper.trxPersonToCustomerDetailsDTO(trxPersonResponse, securityHeaders);

    assertNull(response);
}

@Test
void shouldMapTrxPersonToCustomerSearchDTO() {
    mockParameters();
    TrxPersonResponse trxPersonResponse = buildTrxPersonResponse("CLI");

    CustomerSearchResponseDTO response = mapper.trxPersonToCustomerSearchDTO(trxPersonResponse, securityHeaders);

    assertNotNull(response);
    assertNotNull(response.getPagination());
    assertEquals(1, response.getCustomers().size());
    assertEquals("00012345", response.getCustomers().get(0).getCustomerId());
    assertNotNull(response.getCustomers().get(0).getOrganization());
    assertEquals("Fabio", response.getCustomers().get(0).getPerson().getPersonName().getGivenName());
    assertEquals("Hernandez", response.getCustomers().get(0).getPerson().getPersonName().getLastName());
    assertEquals("Perez", response.getCustomers().get(0).getPerson().getPersonName().getSecondLastName());
    assertEquals("Fabio Hernandez Perez", response.getCustomers().get(0).getPerson().getPersonName().getFullName());
    assertEquals("01-01-2000", response.getCustomers().get(0).getPerson().getBirthDate());
    assertEquals("CC", response.getCustomers().get(0).getPerson().getDocument().getDocumentTypeCode());
    assertEquals("123456789", response.getCustomers().get(0).getPerson().getDocument().getDocumentNumber());
    assertEquals("Descripcion CC", response.getCustomers().get(0).getPerson().getDocument().getDocumentTypeDescription());
    assertEquals("Descripcion CO", response.getCustomers().get(0).getPerson().getDocument().getCountry().getName());
    assertEquals("Descripcion BO", response.getCustomers().get(0).getPerson().getDocument().getState().getName());
    assertEquals("01-01-2020", response.getCustomers().get(0).getPerson().getDocument().getIssueDate());
    assertEquals("Calle 100", response.getCustomers().get(0).getContactPoint().getPostalAddress().getFullAddress());
}

@Test
void shouldReturnNullWhenCustomerSearchIsProspect() {
    TrxPersonResponse trxPersonResponse = buildTrxPersonResponse("PRO");

    CustomerSearchResponseDTO response = mapper.trxPersonToCustomerSearchDTO(trxPersonResponse, securityHeaders);

    assertNull(response);
}

@Test
void shouldMapDtoRequestToTrxRequest() {
    DocumentRequestDTO document = new DocumentRequestDTO();
    document.setDocumentNumber("123456789");

    PersonRequestDto person = new PersonRequestDto();
    person.setDocument(document);

    CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
    customerRequestDTO.setPerson(person);

    TrxPersonRequest response = CustomerContactPointsMapper.dtoRequestToTrxRequest(customerRequestDTO);

    assertNotNull(response);
    assertNotNull(response.getData());
    assertEquals("123456789", response.getData().getNumDocumento());
}

@Test
void shouldMapDocumentBasics() {
    mockParameters();
    TrxPersonData personData = buildTrxPersonData("CLI");

    var response = mapper.getDocumentBasics(personData, securityHeaders);

    assertEquals("CC", response.getDocumentTypeCode());
    assertEquals("Descripcion CC", response.getDocumentTypeDescription());
    assertEquals("123456789", response.getDocumentNumber());
}

@Test
void shouldMapPef3ResponseToPef2Request() {
    TrxPersonData trxBasicData = buildTrxPersonData("CLI");
    trxBasicData.setCelular("300 123 4567");
    trxBasicData.setTelefono("601 123 4567");
    trxBasicData.setAutorizoTelefono("true");
    trxBasicData.setAutorizacionEmail("true");

    BasicData response = mapper.pef3ResponseToPef2Request(trxBasicData);

    assertEquals("3001234567", response.getCelular());
    assertEquals("6011234567", response.getTelefono());
    assertEquals("0006", response.getAgrofic());
    assertEquals("00000010", response.getCodact());
    assertEquals("004", response.getClase());
    assertEquals("CLI", response.getConper());
    assertEquals("11", response.getDepartamento());
    assertEquals("CO", response.getCodpaip());
    assertEquals("CC", response.getTipoIdentificacion());
    assertEquals("123456789", response.getNumeroIdentificacion());
    assertEquals("CL", response.getTipoVia());
    assertEquals("Calle 100", response.getNombreVia());
    assertEquals("300", response.getPrecelular());
    assertEquals("300", response.getPrecel());
    assertEquals("H", response.getSexo());
    assertEquals("Hernandez", response.getPrimerApellido());
    assertEquals("Perez", response.getSegundoApellido());
    assertEquals("CO", response.getPaisDireccion());
    assertEquals("CO", response.getPaisExpedicion());
    assertEquals("CO", response.getPaisNacimiento());
    assertEquals("", response.getPaisDireccionDesc());
    assertEquals("", response.getPaisNacimientoDesc());
    assertEquals("", response.getPaisExpedicionDesc());
    assertEquals("", response.getLugardeExpDescripcion());
    assertEquals("", response.getLugardeNacimiento());
    assertEquals("Apto 101", response.getDescripcionDireccion());
    assertEquals("601", response.getIndicativo());
    assertEquals("TERM", response.getTermod());
    assertEquals("BOG11001", response.getCiudad());
    assertEquals("BOG", response.getCiudadExpedicion());
    assertEquals("BOG", response.getCiudadNacimiento());
    assertEquals("2023-01-01", response.getFecing());
    assertEquals("", response.getCiudadDescripcion());
    assertEquals("1", response.getDomant());
    assertEquals("2", response.getSeccel());
    assertEquals("3", response.getSecema());
    assertEquals("4", response.getSecdotc());
    assertEquals("5", response.getSectelp());
    assertEquals("6", response.getSecdomp());
    assertEquals("7", response.getSecdotp());
    assertEquals("0100", response.getSucadm());
    assertEquals("0200", response.getSucmod());
    assertTrue(response.isAutorizoTelefono());
    assertTrue(response.isAutorizacionEmail());
    assertEquals("LOGDOMP", response.getLogdomp());
    assertEquals("LOGTELP", response.getLogtelp());
    assertEquals("HSTAMP", response.getHstamp());
    assertEquals("HSTAMP2", response.getHstamp2());
    assertEquals("HSTAMP3", response.getHstamp3());
    assertEquals("HSTAMP4", response.getHstamp4());
    assertEquals("HSTAMP5", response.getHstamp5());
    assertEquals("3", response.getEstrat());
    assertEquals("S", response.getEstciv());
    assertEquals("ACTIVO", response.getEstper());
    assertEquals("ENT", response.getEntpre());
    assertEquals("APPNO", response.getUsualt());
    assertEquals("2020-01-01", response.getFechaExpedicion());
    assertEquals("2000-01-01", response.getFechaNacimiento());
    assertEquals("2024-01-01", response.getFecalt());
    assertEquals("9999-12-31", response.getFecfal());
    assertEquals("USUMOD", response.getUsumod());
    assertEquals("test@santander.com", response.getEmail());
    assertEquals("1", response.getSecdoc());
    assertEquals("001", response.getTiptelp());
    assertEquals("P", response.getTipper());
    assertEquals("TIPOCU", response.getTipocu());
    assertEquals("PROFES", response.getProfes());
    assertEquals("00012345", response.getNumper());
    assertEquals("PRI", response.getTipdomp());
    assertEquals("Fabio", response.getNombre());
    assertEquals("CO", response.getNacionalidad());
}

@Test
void shouldMapContactPointPatchToPef2RequestWithValues() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO postalAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO();
    postalAddress.setStreetTypeCode("cl");
    postalAddress.setFullAddress("Calle 100 # 1-2 Ñandú");
    postalAddress.setTown("BOG11001");
    postalAddress.setPremise("Apto-101/Interior");

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO phoneAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO();
    phoneAddress.setMobileNumber("3001234567");
    phoneAddress.setPhoneNumber("6011234567");
    phoneAddress.setInternationalCode("57");

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO electronicAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO();
    electronicAddress.setEmailAddress("test@santander.com");

    request.setPostalAddress(postalAddress);
    request.setPhoneAddress(phoneAddress);
    request.setElectronicAddress(electronicAddress);

    BasicData response = mapper.contactPointPatchToPef2Request(request);

    assertEquals("CL", response.getTipoVia());
    assertEquals("Calle 100 1 2 Nandu", response.getNombreVia());
    assertEquals("BOG11001", response.getCiudad());
    assertEquals("Apto 101 Interior", response.getDescripcionDireccion());
    assertEquals("3001234567", response.getCelular());
    assertEquals("6011234567", response.getTelefono());
    assertEquals("57", response.getPrecelular());
    assertEquals("test@santander.com", response.getEmail());
}

@Test
void shouldIgnoreBlankAndNullValuesOnContactPointPatchToPef2Request() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO postalAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PostalAddressDTO();
    postalAddress.setStreetTypeCode(" ");
    postalAddress.setFullAddress(null);
    postalAddress.setTown(" ");
    postalAddress.setPremise(" ");

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO phoneAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.PhoneAddressDTO();
    phoneAddress.setMobileNumber(" ");
    phoneAddress.setPhoneNumber(null);
    phoneAddress.setInternationalCode(" ");

    com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO electronicAddress =
            new com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ElectronicAddressDTO();
    electronicAddress.setEmailAddress(" ");

    request.setPostalAddress(postalAddress);
    request.setPhoneAddress(phoneAddress);
    request.setElectronicAddress(electronicAddress);

    BasicData response = mapper.contactPointPatchToPef2Request(request);

    assertNull(response.getTipoVia());
    assertNull(response.getNombreVia());
    assertNull(response.getCiudad());
    assertNull(response.getDescripcionDireccion());
    assertNull(response.getCelular());
    assertNull(response.getTelefono());
    assertNull(response.getPrecelular());
    assertNull(response.getEmail());
}

@Test
void shouldMapContactPointPatchToPef2RequestWhenSectionsAreNull() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    BasicData response = mapper.contactPointPatchToPef2Request(request);

    assertNotNull(response);
    assertNull(response.getCelular());
    assertNull(response.getTelefono());
    assertNull(response.getEmail());
    assertNull(response.getTipoVia());
}

@Test
void shouldMapUsualtWithForeignTaxIndicatorPositive() {
    String response = mapper.usualtMapper("WEB123456", "YES");

    assertEquals("WEBPYES", response);
}

@Test
void shouldMapUsualtWithForeignTaxIndicatorNegative() {
    String response = mapper.usualtMapper("WEB123456", "NO");

    assertEquals("WEBPNO", response);
}

@Test
void shouldMapUsualtWithDefaultChannelWhenUsualtIsNull() {
    String response = mapper.usualtMapper(null, null);

    assertEquals("APPPNO", response);
}

@Test
void shouldMapUsualtWithDefaultChannelWhenUsualtIsShort() {
    String response = mapper.usualtMapper("AB", "YES");

    assertEquals("APPPYES", response);
}

private void mockParameters() {
    when(parameterApiService.getParameter(anyString(), any(), any(SecurityHeaders.class)))
            .thenAnswer(invocation -> {
                String code = invocation.getArgument(1);
                DataListDTO dataListDTO = new DataListDTO();
                dataListDTO.setListCode(invocation.getArgument(0));
                dataListDTO.setCode(code);
                dataListDTO.setDescription("Descripcion " + code);
                return List.of(dataListDTO);
            });
}

private TrxPersonResponse buildTrxPersonResponse(String conper) {
    TrxBasicData trxBasicData = new TrxBasicData();
    trxBasicData.setDatosBasicos(buildTrxPersonData(conper));

    TrxPersonResponse trxPersonResponse = new TrxPersonResponse();
    trxPersonResponse.setData(trxBasicData);
    return trxPersonResponse;
}

private TrxPersonData buildTrxPersonData(String conper) {
    TrxPersonData data = new TrxPersonData();
    data.setTipoIdentificacion("CC");
    data.setNumeroIdentificacion("123456789");
    data.setNombre("Fabio");
    data.setPrimerApellido("Hernandez");
    data.setSegundoApellido("Perez");
    data.setPaisExpedicion("CO");
    data.setCiudadExpedicion("BOG");
    data.setFechaExpedicion("2020-01-01");
    data.setPaisNacimiento("CO");
    data.setNacionalidad("CO");
    data.setCiudadNacimiento("BOG");
    data.setFechaNacimiento("2000-01-01");
    data.setSexo("H");
    data.setPaisDireccion("CO");
    data.setDepartamento("11");
    data.setCiudad("BOG11001");
    data.setTipoVia("CL");
    data.setNombreVia("Calle 100");
    data.setDescripcionDireccion("Apto 101");
    data.setClase("004");
    data.setIndicativo("601");
    data.setTelefono("6011234567");
    data.setPrecelular("300");
    data.setCelular("3001234567");
    data.setEmail("test@santander.com");
    data.setAutorizacionEmail("false");
    data.setAutorizoTelefono("false");
    data.setAgrofic("0006");
    data.setCodact("00000010");
    data.setCodpaip("CO");
    data.setConper(conper);
    data.setDomant(1);
    data.setEntpre("ENT");
    data.setEstciv("S");
    data.setEstper("ACTIVO");
    data.setEstrat("3");
    data.setFecalt("2024-01-01");
    data.setFecfal("9999-12-31");
    data.setFecing("2023-01-01");
    data.setHstamp("HSTAMP");
    data.setHstamp2("HSTAMP2");
    data.setHstamp3("HSTAMP3");
    data.setHstamp4("HSTAMP4");
    data.setHstamp5("HSTAMP5");
    data.setLogdomp("LOGDOMP");
    data.setLogtelp("LOGTELP");
    data.setNumintp("123");
    data.setNumper("00012345");
    data.setPrecel("300");
    data.setProfes("PROFES");
    data.setSeccel(2);
    data.setSecdoc("1");
    data.setSecdomp(6);
    data.setSecdotc(4);
    data.setSecdotp(7);
    data.setSecema(3);
    data.setSectelp(5);
    data.setSucadm("0100");
    data.setSucmod("0200");
    data.setTermod("TERM");
    data.setTipdomp("PRI");
    data.setTipocu("TIPOCU");
    data.setTipper("P");
    data.setTiptelp("001");
    data.setUsualt("APPNO")
