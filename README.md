package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrxSanbaServiceImplTest {

    private TrxSanbaAPI trxSanbaAPI;
    private ErrorService errorService;
    private TrxSanbaServiceImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaAPI = mock(TrxSanbaAPI.class);
        errorService = mock(ErrorService.class);

        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

        ReflectionTestUtils.setField(service, "BP49_SERVICE_ROUTE", "BP49_ROUTE");
        ReflectionTestUtils.setField(service, "mqRoute", "MQ_ROUTE");
        ReflectionTestUtils.setField(service, "channel", "WEB");
        ReflectionTestUtils.setField(service, "user", "TEST_USER");
    }

    @Test
    void trxBP13ShouldReturnResponseWhenSuccessful() throws IOException {
        TrxBP13Request request = buildBP13Request();
        TrxBP13Response expected = TrxBP13Response.builder()
                .ok(true)
                .build();

        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(
                request,
                "consultaDatosIPF",
                "consultaDatosIPF",
                "MQ_ROUTE"
        )).thenReturn(call);

        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP13Response result = service.trxBP13(request);

        assertEquals(expected, result);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("TEST_USER", request.getCabecera().getSesion().getUsuario());

        verify(trxSanbaAPI).callBP13TRX(
                request,
                "consultaDatosIPF",
                "consultaDatosIPF",
                "MQ_ROUTE"
        );
    }

    @Test
    void trxBP13ShouldThrowServiceExceptionWhenRuntimeExceptionOccurs() {
        TrxBP13Request request = buildBP13Request();

        when(trxSanbaAPI.callBP13TRX(
                request,
                "consultaDatosIPF",
                "consultaDatosIPF",
                "MQ_ROUTE"
        )).thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowServiceExceptionWhenIOExceptionOccurs() throws IOException {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(
                request,
                "consultaDatosIPF",
                "consultaDatosIPF",
                "MQ_ROUTE"
        )).thenReturn(call);

        when(call.execute()).thenThrow(new IOException("io error"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP49ShouldReturnResponseWhenSuccessful() throws IOException {
        TrxBP49Request request = buildBP49Request();
        TrxBP49Response expected = TrxBP49Response.builder()
                .ok(true)
                .build();

        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(
                request,
                "BP49_ROUTE",
                "BP49_ROUTE",
                "MQ_ROUTE"
        )).thenReturn(call);

        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP49Response result = service.trxBP49(request);

        assertEquals(expected, result);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("TEST_USER", request.getCabecera().getSesion().getUsuario());

        verify(trxSanbaAPI).callBP49(
                request,
                "BP49_ROUTE",
                "BP49_ROUTE",
                "MQ_ROUTE"
        );
    }

    @Test
    void trxBP49ShouldThrowServiceExceptionWhenRuntimeExceptionOccurs() {
        TrxBP49Request request = buildBP49Request();

        when(trxSanbaAPI.callBP49(
                request,
                "BP49_ROUTE",
                "BP49_ROUTE",
                "MQ_ROUTE"
        )).thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowServiceExceptionWhenIOExceptionOccurs() throws IOException {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(
                request,
                "BP49_ROUTE",
                "BP49_ROUTE",
                "MQ_ROUTE"
        )).thenReturn(call);

        when(call.execute()).thenThrow(new IOException("io error"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    private TrxBP13Request buildBP13Request() {
        TrxBP13Request request = new TrxBP13Request();
        request.setCabecera(buildHeader());
        request.setData(new TrxBP13DataRequest());
        return request;
    }

    private TrxBP49Request buildBP49Request() {
        TrxBP49Request request = new TrxBP49Request();
        request.setCabecera(buildHeader());
        request.setData(new TrxBP49DataRequest());
        return request;
    }

    private TrxHeader buildHeader() {
        Session session = new Session();
        session.setUsuario("OLD_USER");

        TrxHeader header = new TrxHeader();
        header.setCanal("OLD_CHANNEL");
        header.setSesion(session);

        return header;
    }
}
