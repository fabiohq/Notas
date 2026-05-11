package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxBasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.mappers.CustomerContactPointsMapper;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.CustomerMapperUtils;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerSevicerImplTest {

    private TrxPersonService trxPersonService;
    private ContextApiService contextService;
    private CustomerContactPointsMapper mapper;
    private RegexUtils regexUtils;
    private CustomerMapperUtils mapperUtils;
    private ErrorService errorService;
    private ParameterApiService parameterApiService;
    private CustomerSevicerImpl service;
    private SecurityHeaders headers;

    @BeforeEach
    void setUp() {
        trxPersonService = mock(TrxPersonService.class);
        contextService = mock(ContextApiService.class);
        mapper = mock(CustomerContactPointsMapper.class);
        regexUtils = mock(RegexUtils.class);
        mapperUtils = mock(CustomerMapperUtils.class);
        errorService = mock(ErrorService.class);
        parameterApiService = mock(ParameterApiService.class);

        service = new CustomerSevicerImpl(
                trxPersonService,
                contextService,
                mapper,
                regexUtils,
                mapperUtils,
                errorService,
                parameterApiService
        );

        headers = new SecurityHeaders();

        ReflectionTestUtils.setField(service, "contactPointID", "PRI001");
        ReflectionTestUtils.setField(errorService, "invalidValue", "invalid value");
    }

    @Test
    void shouldReturnCustomerDetailsFromContext() {
        ContactPointsResponseDTO cachedResponse = new ContactPointsResponseDTO();

        when(contextService.getContext("12345678")).thenReturn(cachedResponse);

        ContactPointsResponseDTO response = service.getCustomerDetails("12345678", headers);

        assertSame(cachedResponse, response);
        verify(contextService).getContext("12345678");
        verifyNoInteractions(trxPersonService);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnCustomerDetailsFromTrxWhenContextIsNull() {
        TrxPersonResponse trxResponse = new TrxPersonResponse();
        ContactPointsResponseDTO mappedResponse = new ContactPointsResponseDTO();

        when(contextService.getContext("12345678")).thenReturn(null);
        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(mapper.trxPersonToCustomerDetailsDTO(trxResponse, headers)).thenReturn(mappedResponse);

        ContactPointsResponseDTO response = service.getCustomerDetails("12345678", headers);

        assertSame(mappedResponse, response);
        verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF3));
        verify(mapper).trxPersonToCustomerDetailsDTO(trxResponse, headers);
    }

    @Test
    void shouldThrowWhenCustomerIdIsInvalidOnGetCustomerDetails() {
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.getCustomerDetails("ABC", headers)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
    }

    @Test
    void shouldThrowWhenContactPointRequestIsNull() {
        ServiceException serviceException = new ServiceException(
                HttpStatus.BAD_REQUEST,
                "body invalid"
        );

        when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(serviceException);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.putCustomerContactPoint("12345678", "PRI001", null, headers)
        );

        assertSame(serviceException, exception);
    }

    @Test
    void shouldThrowWhenContactPointIdIsInvalid() {
        ContactPointsRequestDTO request = new ContactPointsRequestDTO();

        ServiceException serviceException = new ServiceException(
                HttpStatus.BAD_REQUEST,
                "contact point invalid"
        );

        when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(serviceException);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.putCustomerContactPoint("12345678", "OTHER", request, headers)
        );

        assertSame(serviceException, exception);
    }

    @Test
    void shouldPutCustomerContactPointWithEmptyPatch() {
        ContactPointsRequestDTO request = new ContactPointsRequestDTO();

        TrxPersonData trxPersonData = new TrxPersonData();
        trxPersonData.setDescripcionDireccion("DESCRIPTION2024-01-01");
        trxPersonData.setConper("CLI");
        trxPersonData.setUsualt("ABCNOOO");

        TrxBasicData trxBasicData = new TrxBasicData();
        trxBasicData.setDatosBasicos(trxPersonData);

        TrxPersonResponse trxResponse = new TrxPersonResponse();
        trxResponse.setData(trxBasicData);

        BasicData basicData = new BasicData();
        basicData.setDescripcionDireccion("ADDRESS");
        basicData.setUsualt("ABCNOOO");
        basicData.setEstciv("");

        BasicData patchData = new BasicData();

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);
        when(mapper.pef3ResponseToPef2Request(trxPersonData)).thenReturn(basicData);
        when(mapper.contactPointPatchToPef2Request(request)).thenReturn(patchData);
        when(mapperUtils.getForeignTaxIndicator(trxPersonData)).thenReturn("NO");
        when(mapper.usualtMapper(anyString(), eq("NO"))).thenReturn("ABCNOOO");
        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF2))).thenReturn(new TrxPersonResponse());

        assertDoesNotThrow(
                () -> service.putCustomerContactPoint("12345678", "PRI001", request, headers)
        );

        verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF3));
        verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF2));
        verify(mapper).pef3ResponseToPef2Request(trxPersonData);
        verify(mapper).contactPointPatchToPef2Request(request);
    }

    @Test
    void shouldThrowWhenPef3ReturnsProspectCustomer() {
        ContactPointsRequestDTO request = new ContactPointsRequestDTO();

        TrxPersonData trxPersonData = new TrxPersonData();
        trxPersonData.setDescripcionDireccion("DESCRIPTION2024-01-01");
        trxPersonData.setConper("PRO");

        TrxBasicData trxBasicData = new TrxBasicData();
        trxBasicData.setDatosBasicos(trxPersonData);

        TrxPersonResponse trxResponse = new TrxPersonResponse();
        trxResponse.setData(trxBasicData);

        when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3))).thenReturn(trxResponse);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.putCustomerContactPoint("12345678", "PRI001", request, headers)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
        verify(trxPersonService, never()).callPostTRX(any(), eq(ClientEnum.PEF2));
    }
}