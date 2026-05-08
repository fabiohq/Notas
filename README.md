ParamsServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParamsServiceImplTest {

    @Mock
    private ParameterApiService parameterApiService;

    @Mock
    private ContextApiService contextService;

    private ParamsServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ParamsServiceImpl(parameterApiService, contextService);
    }

    @Test
    void getCountryShouldReturnFromContextWhenExists() {
        DataListDTO dto = DataListDTO.builder()
                .code("CO")
                .description("Colombia")
                .build();

        when(contextService.getContext("0112-CO")).thenReturn(dto);

        CodeNameDTO result = service.getCountry("CO", "auth", "client");

        assertEquals("CO", result.getCode());
        assertEquals("Colombia", result.getName());
        verify(parameterApiService, never()).getParameter(anyString(), any(), anyString(), anyString());
    }

    @Test
    void getCountryShouldCallApiWhenContextIsNull() {
        when(contextService.getContext("0112-CO")).thenReturn(null);
        when(parameterApiService.getParameter("0112", "CO", "auth", "client"))
                .thenReturn(List.of(DataListDTO.builder()
                        .code("CO")
                        .description("Colombia")
                        .build()));

        CodeNameDTO result = service.getCountry("CO", "auth", "client");

        assertEquals("CO", result.getCode());
        assertEquals("Colombia", result.getName());
    }

    @Test
    void getTownShouldReturnTown() {
        when(contextService.getContext("0008-05101")).thenReturn(null);
        when(parameterApiService.getParameter("0008", "05101", "auth", "client"))
                .thenReturn(List.of(DataListDTO.builder()
                        .code("05101")
                        .description("MEDELLIN")
                        .build()));

        CodeNameDTO result = service.getTown("05101", "auth", "client");

        assertEquals("05101", result.getCode());
        assertEquals("MEDELLIN", result.getName());
    }

    @Test
    void getCityShouldUseFirstTwoChars() {
        when(contextService.getContext("0009-05")).thenReturn(null);
        when(parameterApiService.getParameter("0009", "05", "auth", "client"))
                .thenReturn(List.of(DataListDTO.builder()
                        .code("05")
                        .description("ANTIOQUIA")
                        .build()));

        CodeNameDTO result = service.getCity("05101", "auth", "client");

        assertEquals("05", result.getCode());
        assertEquals("ANTIOQUIA", result.getName());
    }

    @Test
    void findParametersShouldMapAllData() {
        TrxPersonData data = new TrxPersonData();
        data.setNacionalidad("CO");
        data.setPaisExpedicion("CO");
        data.setPaisNacimiento("CO");
        data.setPaisDireccion("CO");
        data.setCiudad("05101");
        data.setDepartamento("05");
        data.setCiudadExpedicion("99999");
        data.setCiudadNacimiento("05101");
        data.setTipoVia("NN");
        data.setTipoIdentificacion("CC");

        when(contextService.getContext(anyString())).thenReturn(null);

        when(parameterApiService.getParameter(eq("0112"), any(), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("CO").description("Colombia").build()));

        when(parameterApiService.getParameter(eq("0009"), any(), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("05").description("ANTIOQUIA").build()));

        when(parameterApiService.getParameter(eq("0008"), any(), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("05101").description("MEDELLIN").build()));

        when(parameterApiService.getParameter(eq("0314"), eq("NN"), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("NN").description("NO INFORMADO").build()));

        when(parameterApiService.getParameter(eq("0113"), eq("CC"), anyString(), anyString()))
                .thenReturn(List.of(DataListDTO.builder().code("CC").description("CEDULA").build()));

        Parameters result = service.findParameters(data, "auth", "client");

        assertNotNull(result);
        assertEquals("CO", result.getCountryNationality().getCode());
        assertEquals("Colombia", result.getCountryNationality().getName());
        assertEquals("NO INFORMADO", result.getStreetTypeDescription());
        assertEquals("CEDULA", result.getDocumentTypeDescription());
        assertEquals("MEDELLIN", result.getTown().getName());
    }
}
ProspectSevicerImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.PatchProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProspectSevicerImplTest {

    @Mock private TrxPersonService trxPersonService;
    @Mock private ContextApiService contextService;
    @Mock private ProspectMapper mapper;
    @Mock private PatchProspectMapper patchProspectMapper;
    @Mock private ErrorService errorService;
    @Mock private ParameterApiService parameterApiService;
    @Mock private ProspectMapperUtils prospectMapperUtils;
    @Mock private RegexUtils regexUtils;
    @Mock private StringUtils utils;

    private ProspectSevicerImpl service;

    @BeforeEach
    void setUp() {
        service = new ProspectSevicerImpl(
                trxPersonService,
                contextService,
                mapper,
                patchProspectMapper,
                errorService,
                parameterApiService,
                prospectMapperUtils,
                regexUtils,
                utils
        );
    }

    @Test
    void getProspectDetailsShouldReturnResponse() {
        TrxPersonResponse trxResponse = new TrxPersonResponse();
        ProspectDetailResponseDTO expected = new ProspectDetailResponseDTO();

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(mapper.trxPersonToProspectDTO(trxResponse, "auth", "client")).thenReturn(expected);

        ProspectDetailResponseDTO result = service.getProspectDetails("12345678", "auth", "client");

        assertSame(expected, result);
    }

    @Test
    void getProspectDetailsShouldThrowWhenInvalidProspectId() {
        assertThrows(ServiceException.class,
                () -> service.getProspectDetails("ABC", "auth", "client"));
    }

    @Test
    void getProspectDetailsShouldReturnNullWhenPersonNotFound() {
        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3)))
                .thenThrow(new RuntimeException("PERSONA INEXISTENTE"));

        ProspectDetailResponseDTO result = service.getProspectDetails("12345678", "auth", "client");

        assertNull(result);
    }

    @Test
    void searchProspectShouldReturnResponse() {
        ProspectRequestDTO request = new ProspectRequestDTO();
        TrxPersonResponse trxResponse = new TrxPersonResponse();
        ProspectSearchResponseDTO expected = new ProspectSearchResponseDTO();

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(mapper.trxPersonToCustomerSearchDTO(trxResponse, "auth", "client")).thenReturn(expected);

        ProspectSearchResponseDTO result = service.searchProspect(request, "auth", "client");

        assertSame(expected, result);
    }

    @Test
    void searchProspectShouldReturnNullWhenPersonNotFound() {
        ProspectRequestDTO request = new ProspectRequestDTO();

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3)))
                .thenThrow(new RuntimeException("PERSONA INEXISTENTE"));

        ProspectSearchResponseDTO result = service.searchProspect(request, "auth", "client");

        assertNull(result);
    }

    @Test
    void createProspectShouldReturnCreatedProspectId() {
        CreateProspectRequestDTO request = buildCreateRequest();

        TrxPersonResponse trxResponse = new TrxPersonResponse();
        com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonDataResponse data =
                new com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonDataResponse();
        data.setNumPersona("87654321");
        trxResponse.setData(data);

        when(mapper.dtoRequestToTrxRequest(request, "auth", "client"))
                .thenReturn(new com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest());

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF1))).thenReturn(trxResponse);

        ProspectCreatedResponseDTO result = service.createProspect(request, "auth", "client");

        assertEquals("87654321", result.getProspectId());
        verify(utils).inputSencondLastNameValidation("CC", "Perez");
    }

    @Test
    void updateProspectShouldExecutePef2() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();

        TrxPersonData trxData = buildTrxPersonData("PRO");
        TrxPersonResponse trxResponse = buildTrxResponse(trxData);

        BasicData original = new BasicData();
        original.setDescripcionDireccion("123456789012345678901234567890123456789012345678901234567890");
        original.setUsualt("ODSAAAA");

        BasicData patch = new BasicData();
        patch.setNombre("Juan");

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(patchProspectMapper.pef3ResponseToPef2Request(trxData)).thenReturn(original);
        when(patchProspectMapper.prospectPatchToPef2Request(any(), eq("auth"), eq("client"))).thenReturn(patch);
        when(patchProspectMapper.usualtMapper(any(), any())).thenReturn("ODSCONN");
        doNothing().when(regexUtils).validateRegex(any(), anyString(), anyString());

        service.updateProspect(request, "12345678", "auth", "client");

        verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF2));
    }

    @Test
    void updateProspectShouldThrowWhenPersonIsNotProspect() {
        PatchProspectRequestDTO request = new PatchProspectRequestDTO();

        TrxPersonResponse trxResponse = buildTrxResponse(buildTrxPersonData("CLI"));

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        doNothing().when(regexUtils).validateRegex(any(), anyString(), anyString());

        assertThrows(ServiceException.class,
                () -> service.updateProspect(request, "12345678", "auth", "client"));
    }

    @Test
    void removeProspectShouldExecutePef2() {
        TrxPersonData trxData = buildTrxPersonData("PRO");
        TrxPersonResponse trxResponse = buildTrxResponse(trxData);

        BasicData basicData = new BasicData();
        basicData.setEstciv("S");

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(mapper.pef3ResponseToPef2Request(trxData)).thenReturn(basicData);
        when(prospectMapperUtils.lowProspectLogic(trxData)).thenReturn("BAJACONN");
        doNothing().when(regexUtils).validateRegex(any(), anyString(), anyString());

        service.removeProspect("12345678", "auth", "client");

        verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF2));
        assertEquals("C", basicData.getEstciv());
        assertEquals("BAJACONN", basicData.getUsualt());
    }

    @Test
    void removeProspectShouldThrowWhenNotProspect() {
        TrxPersonResponse trxResponse = buildTrxResponse(buildTrxPersonData("CLI"));

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        doNothing().when(regexUtils).validateRegex(any(), anyString(), anyString());

        assertThrows(ServiceException.class,
                () -> service.removeProspect("12345678", "auth", "client"));
    }

    private CreateProspectRequestDTO buildCreateRequest() {
        DocumentRequestDTO document = new DocumentRequestDTO();
        document.setDocumentTypeCode("CC");
        document.setDocumentNumber("12345678901");

        PersonNameRequestDTO name = new PersonNameRequestDTO();
        name.setGivenName("Juan");
        name.setLastName("Gomez");
        name.setSecondLastName("Perez");

        PersonRequestDTO person = new PersonRequestDTO();
        person.setDocuments(List.of(document));
        person.setPersonName(name);

        CreateProspectRequestDTO request = new CreateProspectRequestDTO();
        request.setPerson(person);

        return request;
    }

    private TrxPersonData buildTrxPersonData(String conper) {
        TrxPersonData data = new TrxPersonData();
        data.setConper(conper);
        data.setDescripcionDireccion("123456789012345678901234567890123456789012345678901234567890");
        return data;
    }

    private TrxPersonResponse buildTrxResponse(TrxPersonData personData) {
        TrxPersonResponse response = new TrxPersonResponse();

        com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonDataResponse data =
                new com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonDataResponse();

        data.setDatosBasicos(personData);
        response.setData(data);

        return response;
    }
}
Si tu clase real no se llama TrxPersonDataResponse, cambia ese tipo por el nombre exacto de tu DTO interno de TrxPersonResponse.getData().