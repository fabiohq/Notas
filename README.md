CustomerSevicerImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.mappers.CustomerMapper;
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
class CustomerSevicerImplTest {

    @Mock private TrxPersonService trxPersonService;
    @Mock private RegexUtils regexUtils;
    @Mock private CustomerMapper mapper;
    @Mock private CustomerMapperUtils mapperUtils;
    @Mock private ErrorService errorService;

    private CustomerSevicerImpl service;
    private SecurityHeaders headers;

    @BeforeEach
    void setUp() {
        service = new CustomerSevicerImpl(
                trxPersonService,
                regexUtils,
                mapper,
                mapperUtils,
                errorService
        );

        ReflectionTestUtils.setField(service, "DEFAULT_AGROFIC", "0003");
        headers = new SecurityHeaders();
    }

    @Test
    void getCustomerDetailsShouldThrowWhenCustomerIdInvalid() {
        assertThrows(ServiceException.class,
                () -> service.getCustomerDetails("123", headers));
    }

    @Test
    void getCustomerDetailsShouldThrowWhenMapperReturnsNull() {
        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponse("CLI"));

        when(mapper.trxPersonToCustomerDetailsDTO(any(), any()))
                .thenReturn(null);

        assertThrows(ServiceException.class,
                () -> service.getCustomerDetails("12345678", headers));
    }

    @Test
    void getCustomerDetailsShouldReturnResponseAndNormalizeBlankTowns() {
        CustomerDetailsResponseDTO dto = new CustomerDetailsResponseDTO();

        PersonDTO person = new PersonDTO();

        PlaceOfBirthDTO place = new PlaceOfBirthDTO();
        place.setTown("");

        DocumentDTO document = new DocumentDTO();
        document.setTown("");

        person.setPlaceOfBirth(place);
        person.setDocuments(List.of(document));
        dto.setPerson(person);

        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponse("CLI"));

        when(mapper.trxPersonToCustomerDetailsDTO(any(), any()))
                .thenReturn(dto);

        CustomerDetailsResponseDTO result = service.getCustomerDetails("12345678", headers);

        assertNotNull(result);
        assertEquals("", result.getPerson().getPlaceOfBirth().getTown());
        assertEquals("", result.getPerson().getDocuments().get(0).getTown());
    }

    @Test
    void searchCustomerShouldReturnMappedResponse() {
        CustomerRequestDTO request = buildSearchRequest();

        CustomerSearchResponseDTO mapped = new CustomerSearchResponseDTO();

        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponse("CLI"));

        when(mapper.trxPersonToCustomerSearchDTO(any(), any()))
                .thenReturn(mapped);

        CustomerSearchResponseDTO result = service.searchCustomer(request, headers);

        assertSame(mapped, result);
        verify(regexUtils, atLeastOnce()).validateRegex(any(), anyString(), anyString());
    }

    @Test
    void updateCustomerShouldThrowWhenCurrentPersonIsProspect() {
        CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();

        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponse("PRO"));

        assertThrows(ServiceException.class,
                () -> service.updateCustomer(request, "12345678", headers));
    }

    @Test
    void updateCustomerShouldCallUpdateTrx() {
        CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();
        PersonDTO person = new PersonDTO();
        person.setForeignTaxIndicator("YES");
        request.setPerson(person);

        TrxPersonData trxData = buildPersonData("CLI");
        trxData.setDescripcionDireccion("CALLE 1                            2030-01-01");

        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponseWithData(trxData));

        BasicData basicData = new BasicData();
        basicData.setTipoIdentificacion("CC");
        basicData.setDescripcionDireccion(trxData.getDescripcionDireccion());
        basicData.setUsualt("ODSCONN");
        basicData.setEstciv("S");

        BasicData patch = new BasicData();
        patch.setDescripcionDireccion("2035-01-01");

        when(mapper.pef3ResponseToPef2Request(any())).thenReturn(basicData);
        when(mapper.prospectPatchToPef2Request(any(), any(), anyString())).thenReturn(patch);
        when(mapper.usualtMapper(any(), any())).thenReturn("ODSCONS");

        service.updateCustomer(request, "12345678", headers);

        verify(trxPersonService, times(2)).callPostTRX(any(), any());
        assertEquals("0003", basicData.getAgrofic());
    }

    @Test
    void updateCustomersProspectShouldThrowWhenAlreadyCustomer() {
        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponse("CLI"));

        assertThrows(ServiceException.class,
                () -> service.updateCustomersProspect(new UpdateProspectRequestDTO(), "12345678", headers));
    }

    @Test
    void updateCustomersProspectShouldCallUpdateWhenProspect() {
        UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();
        PersonDTO person = new PersonDTO();
        person.setForeignTaxIndicator("NO");
        request.setPerson(person);

        TrxPersonData trxData = buildPersonData("PRO");

        when(trxPersonService.callPostTRX(any(), any()))
                .thenReturn(buildResponseWithData(trxData));

        BasicData basicData = new BasicData();
        basicData.setTipoIdentificacion("CE");
        basicData.setUsualt("ODSCONS");

        BasicData patch = new BasicData();

        when(mapper.pef3ResponseToPef2Request(any())).thenReturn(basicData);
        when(mapper.prospectPutToPef2Request(any(), any())).thenReturn(patch);
        when(mapper.usualtMapper(any(), any())).thenReturn("ODSCONN");

        service.updateCustomersProspect(request, "12345678", headers);

        verify(trxPersonService, times(2)).callPostTRX(any(), any());
        assertEquals("", basicData.getCiudadNacimiento());
        assertEquals("0003", basicData.getAgrofic());
    }

    private CustomerRequestDTO buildSearchRequest() {
        CustomerRequestDTO request = new CustomerRequestDTO();

        PersonDTO person = new PersonDTO();
        DocumentDTO document = new DocumentDTO();
        document.setDocumentNumber("123456789");
        document.setDocumentTypeCode("CC");
        person.setDocument(document);

        request.setPerson(person);
        return request;
    }

    private TrxPersonResponse buildResponse(String conper) {
        return buildResponseWithData(buildPersonData(conper));
    }

    private TrxPersonResponse buildResponseWithData(TrxPersonData data) {
        TrxBasicData basic = new TrxBasicData();
        basic.setDatosBasicos(data);

        TrxPersonResponse response = new TrxPersonResponse();
        response.setData(basic);

        return response;
    }

    private TrxPersonData buildPersonData(String conper) {
        TrxPersonData data = new TrxPersonData();
        data.setConper(conper);
        data.setDescripcionDireccion("CALLE 1                            2030-01-01");
        return data;
    }
}
ParamsServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParamsServiceImplTest {

    @Mock
    private ParameterApiService parameterApiService;

    private ParamsServiceImpl service;
    private SecurityHeaders headers;

    @BeforeEach
    void setUp() {
        service = new ParamsServiceImpl();
        ReflectionTestUtils.setField(service, "parameterApiService", parameterApiService);
        headers = new SecurityHeaders();
    }

    @Test
    void findParametersShouldMapAllParameters() {
        when(parameterApiService.getParameter(eq("0112"), anyString(), any()))
                .thenAnswer(invocation -> List.of(new DataListDTO("0112", invocation.getArgument(1), "COUNTRY")));

        when(parameterApiService.getParameter(eq("0009"), anyString(), any()))
                .thenAnswer(invocation -> List.of(new DataListDTO("0009", invocation.getArgument(1), "STATE")));

        when(parameterApiService.getParameter(eq("0008"), anyString(), any()))
                .thenAnswer(invocation -> List.of(new DataListDTO("0008", invocation.getArgument(1), "TOWN")));

        when(parameterApiService.getParameter(eq("0314"), anyString(), any()))
                .thenReturn(List.of(new DataListDTO("0314", "CL", "Calle")));

        when(parameterApiService.getParameter(eq("0113"), anyString(), any()))
                .thenReturn(List.of(new DataListDTO("0113", "CC", "Cedula")));

        Parameters result = service.findParameters(buildPersonData(), headers);

        assertNotNull(result);
        assertEquals("COL", result.getCountryNationality().getCode());
        assertEquals("COL", result.getCountryExp().getCode());
        assertEquals("COL", result.getCountryBirth().getCode());
        assertEquals("COL", result.getCountryDir().getCode());
        assertEquals("05001", result.getCityStandard().getCode());
        assertEquals("05", result.getCityDepartment().getCode());
        assertEquals("05001", result.getTownDocument().getCode());
        assertEquals("05001", result.getTown().getCode());
        assertEquals("Calle", result.getStreetTypeDescription());
        assertEquals("Cedula", result.getDocumentTypeDescription());
    }

    @Test
    void findParametersShouldReturnEmptyValuesWhenPersonHasBlankFields() {
        TrxPersonData data = new TrxPersonData();

        Parameters result = service.findParameters(data, headers);

        assertNotNull(result);
        assertNotNull(result.getCountryNationality());
        assertEquals("", result.getStreetTypeDescription());
        assertEquals("", result.getDocumentTypeDescription());
    }

    @Test
    void findParametersShouldHandleEmptyParameterResponses() {
        when(parameterApiService.getParameter(anyString(), anyString(), any()))
                .thenReturn(List.of());

        Parameters result = service.findParameters(buildPersonData(), headers);

        assertNotNull(result);
        assertEquals("", result.getCountryNationality().getCode());
        assertEquals("", result.getStreetTypeDescription());
        assertEquals("", result.getDocumentTypeDescription());
    }

    private TrxPersonData buildPersonData() {
        TrxPersonData data = new TrxPersonData();

        data.setNacionalidad("COL");
        data.setPaisExpedicion("COL");
        data.setPaisNacimiento("COL");
        data.setPaisDireccion("COL");
        data.setCiudad("05001");
        data.setDepartamento("05");
        data.setCiudadExpedicion("05001");
        data.setCiudadNacimiento("05001");
        data.setTipoVia("CL");
        data.setTipoIdentificacion("CC");

        return data;
    }
}
En CustomerSevicerImplTest, el nombre de la clase debe quedar igual a tu clase real: CustomerSevicerImpl está escrito con typo, no CustomerServiceImpl.