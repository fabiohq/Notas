package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrxSanbaServiceImplTest {

    @Mock TrxSanbaAPI trxSanbaAPI;
    @Mock ErrorService errorService;

    @Mock Call<TrxBP17Response> bp17Call;
    @Mock Call<TrxBP31Response> bp31Call;
    @Mock Call<TrxBP13Response> bp13Call;
    @Mock Call<TrxBp01Response> bp01Call;
    @Mock Call<TrxBp02Response> bp02Call;
    @Mock Call<TrxBP49Response> bp49Call;
    @Mock Call<TrxPEPFDataResponse> pepfCall;

    private TrxSanbaServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

        ReflectionTestUtils.setField(service, "bp17ServiceRoute", "BP17_ROUTE");
        ReflectionTestUtils.setField(service, "pepfServiceRoute", "PEPF_ROUTE");
        ReflectionTestUtils.setField(service, "bp01ServiceRoute", "BP01_ROUTE");
        ReflectionTestUtils.setField(service, "bp02ServiceRoute", "BP02_ROUTE");
        ReflectionTestUtils.setField(service, "bp49ServiceRoute", "BP49_ROUTE");
        ReflectionTestUtils.setField(service, "mqRoute", "MQ_ROUTE");
        ReflectionTestUtils.setField(service, "channel", "CHANNEL");
        ReflectionTestUtils.setField(service, "user", "USER");
    }

    @Test
    void trxBP17ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);
        TrxBP17Response body = new TrxBP17Response();

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP17(request));
        assertEquals("CHANNEL", request.getCabecera().getCanal());
        assertEquals("USER", request.getCabecera().getSesion().getUsuario());
    }

    @Test
    void trxBP31ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBP31Request request = request(TrxBP31Request.class);
        TrxBP31Response body = new TrxBP31Response();

        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
        when(bp31Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP31(request));
    }

    @Test
    void trxBP13ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBP13Request request = request(TrxBP13Request.class);
        TrxBP13Response body = new TrxBP13Response();

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
        when(bp13Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP13(request));
    }

    @Test
    void trxBP01ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBp01Request request = request(TrxBp01Request.class);
        TrxBp01Response body = new TrxBp01Response();

        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
        when(bp01Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP01(request));
    }

    @Test
    void trxBP02ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBp02Request request = request(TrxBp02Request.class);
        TrxBp02Response body = new TrxBp02Response();

        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
        when(bp02Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP02(request));
    }

    @Test
    void trxBP49ShouldReturnBodyWhenSuccess() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);
        TrxBP49Response body = new TrxBP49Response();

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxBP49(request));
    }

    @Test
    void trxPEPFShouldReturnBodyWhenSuccess() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);
        TrxPEPFDataResponse body = new TrxPEPFDataResponse();

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenReturn(Response.success(body));

        assertSame(body, service.trxPEPF(request));
    }

    @Test
    void trxBP17ShouldThrowServiceExceptionWhenRuntimeException() {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
    }

    @Test
    void trxBP17ShouldThrowServiceExceptionWhenIOException() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenThrow(new IOException("io error"));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
    }

    private <T> T request(Class<T> clazz) {
        try {
            T request = clazz.getDeclaredConstructor().newInstance();

            TrxHeader header = new TrxHeader();
            Session session = new Session();
            header.setSesion(session);

            clazz.getMethod("setCabecera", TrxHeader.class).invoke(request, header);

            return request;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);



when(trxSanbaAPI.callPEPFTRX(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
