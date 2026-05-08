package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

class TrxPersonAPIImplTest {

    @Mock
    private TrxPersonAPI trxPersonAPI;

    @Mock
    private Call<TrxPersonResponse> call;

    private TrxPersonAPIImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new TrxPersonAPIImpl(trxPersonAPI, new ObjectMapper());

        ReflectionTestUtils.setField(service, "mqRoute", "QCTFD");
        ReflectionTestUtils.setField(service, "channel", "60");
        ReflectionTestUtils.setField(service, "user", "@NETE781");
    }

    @Test
    void callPostTRXShouldReturnResponseWhenApiIsSuccessful() throws Exception {
        TrxPersonRequest request = buildRequest();

        TrxPersonResponse body = new TrxPersonResponse();

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        TrxPersonResponse result = service.callPostTRX(request, ClientEnum.PEF3);

        assertNotNull(result);
        assertSame(body, result);
        assertEquals("60", request.getCabecera().getCanal());
        assertEquals("@NETE781", request.getCabecera().getSesion().getUsuario());
    }

    @Test
    void callPostTRXShouldThrowServiceExceptionWhenApiReturnsErrorBodyWithErrors() throws Exception {
        TrxPersonRequest request = buildRequest();

        TrxPersonResponse errorResponse = new TrxPersonResponse();
        ErrorTrxDTO error = new ErrorTrxDTO();
        error.setMensaje("ERROR TRX");
        errorResponse.setErrores(List.of(error));

        String json = new ObjectMapper().writeValueAsString(errorResponse);

        ResponseBody errorBody = ResponseBody.create(
            json,
            MediaType.parse("application/json")
        );

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(409, errorBody));

        ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.callPostTRX(request, ClientEnum.PEF3)
        );

        assertEquals(409, exception.getCode().value());
        assertNotNull(exception.getError());
    }

    @Test
    void callPostTRXShouldThrowDefaultTrxErrorWhenErrorBodyHasEmptyErrors() throws Exception {
        TrxPersonRequest request = buildRequest();

        TrxPersonResponse errorResponse = new TrxPersonResponse();
        errorResponse.setErrores(List.of());

        String json = new ObjectMapper().writeValueAsString(errorResponse);

        ResponseBody errorBody = ResponseBody.create(
            json,
            MediaType.parse("application/json")
        );

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(409, errorBody));

        ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.callPostTRX(request, ClientEnum.PEF3)
        );

        assertEquals(409, exception.getCode().value());
        assertNotNull(exception.getError());
    }

    @Test
    void callPostTRXShouldThrowBadRequestWhenRuntimeExceptionOccurs() throws Exception {
        TrxPersonRequest request = buildRequest();

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("runtime error"));

        ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.callPostTRX(request, ClientEnum.PEF3)
        );

        assertEquals(400, exception.getCode().value());
        assertNotNull(exception.getError());
        assertEquals("runtime error", exception.getError().getMessage());
    }

    @Test
    void callPostTRXShouldThrowServiceUnavailableWhenIOExceptionOccurs() throws Exception {
        TrxPersonRequest request = buildRequest();

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("network error"));

        ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.callPostTRX(request, ClientEnum.PEF3)
        );

        assertEquals(503, exception.getCode().value());
        assertNotNull(exception.getError());
    }

    @Test
    void callPostTRXShouldThrowInternalServerErrorWhenGenericExceptionOccurs() throws Exception {
        TrxPersonRequest request = buildRequest();

        when(trxPersonAPI.callPostTRX(any(), anyString(), anyString(), anyString()))
            .thenReturn(call);
        when(call.execute()).thenThrow(new Exception("generic error"));

        ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.callPostTRX(request, ClientEnum.PEF3)
        );

        assertEquals(500, exception.getCode().value());
        assertNotNull(exception.getError());
        assertEquals("generic error", exception.getError().getMessage());
    }

    private TrxPersonRequest buildRequest() {
        TrxPersonRequest request = new TrxPersonRequest();
        request.setCabecera(new TrxPersonHeader());
        return request;
    }
}