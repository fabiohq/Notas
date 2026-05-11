package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.mappers;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ElectronicAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.PhoneAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request.DocumentRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request.PersonRequestDto;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxBasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ParametersEnums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerContactPointsMapperTest {

    private ParameterApiService parameterApiService;
    private CustomerContactPointsMapper mapper;
    private SecurityHeaders headers;

    @BeforeEach
    void setUp() {
        parameterApiService = mock(ParameterApiService.class);
        mapper = new CustomerContactPointsMapper(parameterApiService);
        headers = new SecurityHeaders();

        ReflectionTestUtils.setField(mapper, "defaultChannel", "DEF");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorPositive", "YES");
        ReflectionTestUtils.setField(mapper, "foreignTaxIndicatorNegative", "NO");

        mockParameters();
    }

    @Test
    void shouldMapTrxPersonToCustomerDetailsDTO() {
        TrxPersonResponse trxResponse = buildTrxResponse("CLI");

        ContactPointsResponseDTO result =
                mapper.trxPersonToCustomerDetailsDTO(trxResponse, headers);

        assertNotNull(result);
        assertEquals(1, result.getContactPoints().size());
        assertEquals("PRI001", result.getContactPoints().get(0).getContactPointId());
        assertTrue(result.getContactPoints().get(0).getPreferredIndicator());
        assertTrue(result.getContactPoints().get(0).getPrimaryIndicator());
        assertEquals("3001234567", result.getContactPoints().get(0).getPhoneAddress().getMobileNumber());
        assertEquals("test@mail.com", result.getContactPoints().get(0).getElectronicAddress().getEmailAddress());
        assertEquals("CALLE 123", result.getContactPoints().get(0).getPostalAddress().getFullAddress());
    }

    @Test
    void shouldReturnNullWhenCustomerDetailsIsProspect() {
        TrxPersonResponse trxResponse = buildTrxResponse("PRO");

        ContactPointsResponseDTO result =
                mapper.trxPersonToCustomerDetailsDTO(trxResponse, headers);

        assertNull(result);
    }

    @Test
    void shouldMapTrxPersonToCustomerSearchDTO() {
        TrxPersonResponse trxResponse = buildTrxResponse("CLI");

        CustomerSearchResponseDTO result =
                mapper.trxPersonToCustomerSearchDTO(trxResponse, headers);

        assertNotNull(result);
        assertEquals(1, result.getCustomers().size());
        assertEquals("99999999", result.getCustomers().get(0).getCustomerId());
        assertNotNull(result.getPagination());
        assertNotNull(result.getCustomers().get(0).getPerson());
        assertNotNull(result.getCustomers().get(0).getContactPoint());
    }

    @Test
    void shouldReturnNullWhenCustomerSearchIsProspect() {
        TrxPersonResponse trxResponse = buildTrxResponse("PRO");

        CustomerSearchResponseDTO result =
                mapper.trxPersonToCustomerSearchDTO(trxResponse, headers);

        assertNull(result);
    }

    @Test
    void shouldMapDtoRequestToTrxRequest() {
        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setDocumentNumber("123456");

        PersonRequestDto person = new PersonRequestDto();
        person.setDocument(document);

        CustomerRequestDTO request = new CustomerRequestDTO();
        request.setPerson(person);

        var result = CustomerContactPointsMapper.dtoRequestToTrxRequest(request);

        assertNotNull(result);
        assertEquals("123456", result.getData().getNumDocumento());
    }

    @Test
    void shouldGetDocumentBasics() {
        TrxPersonData data = buildPersonData("CLI");

        DocumentDTO result = mapper.getDocumentBasics(data, headers);

        assertEquals("CC", result.getDocumentTypeCode());
        assertEquals("Cédula", result.getDocumentTypeDescription());
        assertEquals("123456", result.getDocumentNumber());
    }

    @Test
    void shouldMapPef3ResponseToPef2Request() {
        TrxPersonData data = buildPersonData("CLI");

        BasicData result = mapper.pef3ResponseToPef2Request(data);

        assertEquals("3001234567", result.getCelular());
        assertEquals("6011234567", result.getTelefono());
        assertEquals("CC", result.getTipoIdentificacion());
        assertEquals("123456", result.getNumeroIdentificacion());
        assertEquals("CL", result.getTipoVia());
        assertEquals("CALLE 123", result.getNombreVia());
        assertEquals("JUAN", result.getNombre());
        assertEquals("PEREZ", result.getPrimerApellido());
        assertEquals("GOMEZ", result.getSegundoApellido());
        assertEquals("99999999", result.getNumper());
    }

    @Test
    void shouldMapContactPointPatchToPef2Request() {
        PostalAddressDTO postal = new PostalAddressDTO();
        postal.setStreetTypeCode("CL");
        postal.setFullAddress("CALLE # 123-45");
        postal.setTown("11001");
        postal.setPremise("APT # 101");

        PhoneAddressDTO phone = new PhoneAddressDTO();
        phone.setMobileNumber("3001234567");
        phone.setPhoneNumber("6011234567");
        phone.setInternationalCode("57");

        ElectronicAddressDTO email = new ElectronicAddressDTO();
        email.setEmailAddress("test@mail.com");

        ContactPointsRequestDTO request = new ContactPointsRequestDTO();
        request.setPostalAddress(postal);
        request.setPhoneAddress(phone);
        request.setElectronicAddress(email);

        BasicData result = mapper.contactPointPatchToPef2Request(request);

        assertEquals("CL", result.getTipoVia());
        assertEquals("CALLE 123 45", result.getNombreVia());
        assertEquals("11001", result.getCiudad());
        assertEquals("APT 101", result.getDescripcionDireccion());
        assertEquals("3001234567", result.getCelular());
        assertEquals("6011234567", result.getTelefono());
        assertEquals("57", result.getPrecelular());
        assertEquals("test@mail.com", result.getEmail());
    }

    @Test
    void shouldIgnoreBlankPatchValues() {
        PostalAddressDTO postal = new PostalAddressDTO();
        postal.setStreetTypeCode("");
        postal.setFullAddress("");
        postal.setTown("");
        postal.setPremise("");

        PhoneAddressDTO phone = new PhoneAddressDTO();
        phone.setMobileNumber("");
        phone.setPhoneNumber("");
        phone.setInternationalCode("");

        ElectronicAddressDTO email = new ElectronicAddressDTO();
        email.setEmailAddress("");

        ContactPointsRequestDTO request = new ContactPointsRequestDTO();
        request.setPostalAddress(postal);
        request.setPhoneAddress(phone);
        request.setElectronicAddress(email);

        BasicData result = mapper.contactPointPatchToPef2Request(request);

        assertNull(result.getTipoVia());
        assertNull(result.getNombreVia());
        assertNull(result.getCiudad());
        assertNull(result.getDescripcionDireccion());
        assertNull(result.getCelular());
        assertNull(result.getTelefono());
        assertNull(result.getPrecelular());
        assertNull(result.getEmail());
    }

    @Test
    void shouldMapUsualtWithPositiveForeignTaxIndicator() {
        String result = mapper.usualtMapper("ABC123", "YES");

        assertEquals("ABCYES", result);
    }

    @Test
    void shouldMapUsualtWithNegativeForeignTaxIndicatorAndDefaultChannel() {
        String result = mapper.usualtMapper("", "NO");

        assertEquals("DEFNO", result);
    }

    @Test
    void shouldCleanAddress() {
        String result = CustomerContactPointsMapper.cleanAddress("CALLE # 12-34 ÑÁÉÍÓÚ");

        assertEquals("CALLE   12 34 NAEIOU", result);
    }

    @Test
    void shouldReplaceSpaces() {
        String result = CustomerContactPointsMapper.replaceSpaces("CALLE   12  34 ");

        assertEquals("CALLE 12 34", result);
    }

    private void mockParameters() {
        when(parameterApiService.getParameter(eq(ParametersEnums.DOCU_TYPE.value()), anyString(), eq(headers)))
                .thenReturn(List.of(new DataListDTO("0113", "CC", "Cédula")));

        when(parameterApiService.getParameter(eq(ParametersEnums.COUNTRY.value()), anyString(), eq(headers)))
                .thenReturn(List.of(new DataListDTO("0112", "CO", "Colombia")));

        when(parameterApiService.getParameter(eq(ParametersEnums.STATES.value()), anyString(), eq(headers)))
                .thenReturn(List.of(new DataListDTO("0009", "11", "Bogotá")));

        when(parameterApiService.getParameter(eq(ParametersEnums.TOWNS.value()), anyString(), eq(headers)))
                .thenReturn(List.of(new DataListDTO("0008", "11001", "Bogotá")));

        when(parameterApiService.getParameter(eq(ParametersEnums.WAY_TYPE.value()), anyString(), eq(headers)))
                .thenReturn(List.of(new DataListDTO("0314", "CL", "Calle")));
    }

    private TrxPersonResponse buildTrxResponse(String conper) {
        TrxPersonData personData = buildPersonData(conper);

        TrxBasicData basicData = new TrxBasicData();
        basicData.setDatosBasicos(personData);

        TrxPersonResponse response = new TrxPersonResponse();
        response.setData(basicData);

        return response;
    }

    private TrxPersonData buildPersonData(String conper) {
        TrxPersonData data = new TrxPersonData();

        data.setConper(conper);
        data.setTipoIdentificacion("CC");
        data.setNumeroIdentificacion("123456");
        data.setNombre("JUAN");
        data.setPrimerApellido("PEREZ");
        data.setSegundoApellido("GOMEZ");
        data.setFechaNacimiento("2024-01-01");
        data.setFechaExpedicion("2024-01-02");
        data.setPaisNacimiento("CO");
        data.setPaisExpedicion("CO");
        data.setNacionalidad("CO");
        data.setCiudad("11001");
        data.setNumper("99999999");

        data.setPrecel("57");
        data.setPrecelular("57");
        data.setCelular("3001234567");
        data.setTelefono("6011234567");
        data.setNumintp("101");
        data.setEmail("test@mail.com");

        data.setNombreVia("CALLE 123");
        data.setTipoVia("CL");
        data.setDescripcionDireccion("APT 101");

        data.setAgrofic("AGRO");
        data.setCodact("ACT");
        data.setClase("004");
        data.setDepartamento("11");
        data.setCodpaip("CO");
        data.setPaisDireccion("CO");
        data.setCiudadExpedicion("11001");
        data.setCiudadNacimiento("11001");
        data.setFecing("2024-01-03");
        data.setIndicativo("57");
        data.setTermod("TERM");
        data.setDomant(1);
        data.setSeccel(2);
        data.setSecema(3);
        data.setSecdotc(4);
        data.setSectelp(5);
        data.setSecdomp(6);
        data.setSecdotp(7);
        data.setSucadm("ADM");
        data.setSucmod("MOD");
        data.setAutorizoTelefono("true");
        data.setAutorizacionEmail("true");
        data.setLogdomp("LOGD");
        data.setLogtelp("LOGT");
        data.setHstamp("H1");
        data.setHstamp2("H2");
        data.setHstamp3("H3");
        data.setHstamp4("H4");
        data.setHstamp5("H5");
        data.setEstrat("3");
        data.setEstciv("S");
        data.setEstper("A");
        data.setEntpre("ENT");
        data.setUsualt("ABCNO");
        data.setFecalt("2024-01-04");
        data.setFecfal("2024-01-05");
        data.setUsumod("USER");
        data.setSecdoc("1");
        data.setTiptelp("T");
        data.setTipper("P");
        data.setTipocu("O");
        data.setProfes("PROF");

        return data;
    }
}