package com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request.TrxBp92Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.response.TrxBp92Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

import retrofit2.Call;
import retrofit2.Response;

class TrxSanbaServiceImplTest {

    private TrxSanbaAPI trxSanbaAPI;
    private ErrorService errorService;
    private TrxSanbaServiceImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaAPI = mock(TrxSanbaAPI.class);
        errorService = mock(ErrorService.class);

        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

        ReflectionTestUtils.setField(service, "BP17_SERVICE_ROUTE", "BP17");
        ReflectionTestUtils.setField(service, "PEPF_SERVICE_ROUTE", "PEPF");
        ReflectionTestUtils.setField(service, "BP01_SERVICE_ROUTE", "BP01");
        ReflectionTestUtils.setField(service, "BP02_SERVICE_ROUTE", "BP02");
        ReflectionTestUtils.setField(service, "BP92_SERVICE_ROUTE", "BP92");
        ReflectionTestUtils.setField(service, "mqRoute", "MQ");
        ReflectionTestUtils.setField(service, "user", "USER");
        ReflectionTestUtils.setField(service, "channel", "CHANNEL");
    }

    @Test
    void shouldReturnSuccessForAllTrx() throws Exception {
        mockSuccessBP17();
        mockSuccessBP31();
        mockSuccessPEPF();
        mockSuccessBP13();
        mockSuccessBP01();
        mockSuccessBP02();
        mockSuccessBP21();
        mockSuccessBP92();

        assertNotNull(service.trxBP17(mock(TrxBP17Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP31(mock(TrxBP31Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxPEPF(mock(TrxPEPFDataRequest.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP13(mock(TrxBP13Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP01(mock(TrxBp01Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP02(mock(TrxBp02Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP21(mock(TrxBP21Request.class, RETURNS_DEEP_STUBS)));
        assertNotNull(service.trxBP92(mock(TrxBp92Request.class, RETURNS_DEEP_STUBS)));
    }

    @Test
    void shouldThrowRuntimeExceptions() throws Exception {
        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP21TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));
        when(trxSanbaAPI.callBP92TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("error"));

        assertThrows(ServiceException.class, () -> service.trxBP17(mock(TrxBP17Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP31(mock(TrxBP31Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxPEPF(mock(TrxPEPFDataRequest.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP13(mock(TrxBP13Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP01(mock(TrxBp01Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP02(mock(TrxBp02Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP21(mock(TrxBP21Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP92(mock(TrxBp92Request.class, RETURNS_DEEP_STUBS)));
    }

    @Test
    void shouldThrowIOExceptionBranches() throws Exception {
        mockIOExceptionBP17();
        mockIOExceptionBP31();
        mockIOExceptionPEPF();
        mockIOExceptionBP13();
        mockIOExceptionBP01();
        mockIOExceptionBP02();
        mockIOExceptionBP21();
        mockIOExceptionBP92();

        assertThrows(ServiceException.class, () -> service.trxBP17(mock(TrxBP17Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP31(mock(TrxBP31Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxPEPF(mock(TrxPEPFDataRequest.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP13(mock(TrxBP13Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP01(mock(TrxBp01Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP02(mock(TrxBp02Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP21(mock(TrxBP21Request.class, RETURNS_DEEP_STUBS)));
        assertThrows(ServiceException.class, () -> service.trxBP92(mock(TrxBp92Request.class, RETURNS_DEEP_STUBS)));
    }

    private void mockSuccessBP17() throws Exception {
        Call<TrxBP17Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBP17Response()));
        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP31() throws Exception {
        Call<TrxBP31Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBP31Response()));
        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessPEPF() throws Exception {
        Call<TrxPEPFDataResponse> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxPEPFDataResponse()));
        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP13() throws Exception {
        Call<TrxBP13Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBP13Response()));
        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP01() throws Exception {
        Call<TrxBp01Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBp01Response()));
        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP02() throws Exception {
        Call<TrxBp02Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBp02Response()));
        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP21() throws Exception {
        Call<TrxBP21Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBP21Response()));
        when(trxSanbaAPI.callBP21TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockSuccessBP92() throws Exception {
        Call<TrxBp92Response> call = mock(Call.class);
        when(call.execute()).thenReturn(Response.success(new TrxBp92Response()));
        when(trxSanbaAPI.callBP92TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP17() throws Exception {
        Call<TrxBP17Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP31() throws Exception {
        Call<TrxBP31Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionPEPF() throws Exception {
        Call<TrxPEPFDataResponse> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP13() throws Exception {
        Call<TrxBP13Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP01() throws Exception {
        Call<TrxBp01Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP02() throws Exception {
        Call<TrxBp02Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP21() throws Exception {
        Call<TrxBP21Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP21TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }

    private void mockIOExceptionBP92() throws Exception {
        Call<TrxBp92Response> call = mock(Call.class);
        when(call.execute()).thenThrow(new IOException("io"));
        when(trxSanbaAPI.callBP92TRX(any(), anyString(), anyString(), anyString())).thenReturn(call);
    }
}
