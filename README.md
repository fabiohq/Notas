package com.santander.bnc.bsn049.bncbsn049mscontracts.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorService;

import retrofit2.Call;
import retrofit2.Response;

class TrxSanbaServiceImplTest {

    private TrxSanbaAPI trxSanbaAPI;
    private ErrorService errorService;
    private ObjectMapper objectMapper;
    private TrxSanbaServiceImpl service;

    @BeforeEach
    void setUp() {
        trxSanbaAPI = mock(TrxSanbaAPI.class);
        errorService = mock(ErrorService.class);
        objectMapper = mock(ObjectMapper.class);

        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService, objectMapper);

        ReflectionTestUtils.setField(service, "BP17_SERVICE_ROUTE", "BP17");
        ReflectionTestUtils.setField(service, "PEPF_SERVICE_ROUTE", "PEPF");
        ReflectionTestUtils.setField(service, "BP01_SERVICE_ROUTE", "BP01");
        ReflectionTestUtils.setField(service, "BP02_SERVICE_ROUTE", "BP02");
        ReflectionTestUtils.setField(service, "BP49_SERVICE_ROUTE", "BP49");

        ReflectionTestUtils.setField(service, "mqRoute", "QCTFD");
        ReflectionTestUtils.setField(service, "user", "@NETE781");
        ReflectionTestUtils.setField(service, "channel", "60");
    }

    @Test
    void shouldReturnBp17ResponseWhenSuccess() throws Exception {
        TrxBP17Request request = mock(TrxBP17Request.class);
        TrxBP17Response expected = mock(TrxBP17Response.class);

        Call<TrxBP17Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP17Response result = service.trxBP17(request);

        assertSame(expected, result);
    }

    @Test
    void shouldThrowBadRequestWhenBp17RuntimeException() throws Exception {
        TrxBP17Request request = mock(TrxBP17Request.class);
        Call<TrxBP17Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD")).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("boom"));

        ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getCode());
    }

    @Test
    void shouldThrowServiceUnavailableWhenBp17IOException() throws Exception {
        TrxBP17Request request = mock(TrxBP17Request.class);
        Call<TrxBP17Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP17TRX(request, "BP17", "BP17", "QCTFD")).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("network"));

        ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP17(request));

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, ex.getCode());
    }

    @Test
    void shouldReturnBp31ResponseWhenSuccess() throws Exception {
        TrxBP31Request request = mock(TrxBP31Request.class);
        TrxBP31Response expected = mock(TrxBP31Response.class);

        Call<TrxBP31Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP31TRX(
                request,
                "SBCDTTI01-ConsultaCDTDATTitular2654",
                "SBCDTTI01-ConsultaCDTDATTitular2654",
                "QCTFD"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP31Response result = service.trxBP31(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnPepfResponseWhenSuccess() throws Exception {
        TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
        TrxPEPFDataResponse expected = mock(TrxPEPFDataResponse.class);

        Call<TrxPEPFDataResponse> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxPEPFDataResponse result = service.trxPEPF(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnBp13ResponseWhenSuccess() throws Exception {
        TrxBP13Request request = mock(TrxBP13Request.class);
        TrxBP13Response expected = mock(TrxBP13Response.class);

        Call<TrxBP13Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP13TRX(
                request,
                "consultaDatosIPF",
                "consultaDatosIPF",
                "QCTFD"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP13Response result = service.trxBP13(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnBp01ResponseWhenSuccess() throws Exception {
        TrxBp01Request request = mock(TrxBp01Request.class);
        TrxBp01Response expected = mock(TrxBp01Response.class);

        Call<TrxBp01Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP01(request, "BP01", "BP01", "QCTFD")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBp01Response result = service.trxBP01(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnBp02ResponseWhenSuccess() throws Exception {
        TrxBp02Request request = mock(TrxBp02Request.class);
        TrxBp02Response expected = mock(TrxBp02Response.class);

        Call<TrxBp02Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP02(request, "BP02", "BP02", "QCTFD")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBp02Response result = service.trxBP02(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnBp49ResponseWhenSuccess() throws Exception {
        TrxBP49Request request = mock(TrxBP49Request.class);
        TrxBP49Response expected = mock(TrxBP49Response.class);

        Call<TrxBP49Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "QCTFD")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP49Response result = service.trxBP49(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnBp21ResponseWhenSuccessAndSetHeader() throws Exception {
        TrxBP21Request request = mock(TrxBP21Request.class, RETURNS_DEEP_STUBS);
        TrxBP21Response expected = mock(TrxBP21Response.class);

        Call<TrxBP21Response> call = mock(Call.class);

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
        when(trxSanbaAPI.callBP21TRX(
                request,
                "modificacionDatosIPF",
                "modificacionDatosIPF",
                "QCTFD"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP21Response result = service.trxBP21(request);

        assertSame(expected, result);
        verify(request.getCabecera()).setCanal("60");
        verify(request.getCabecera().getSesion()).setUsuario("@NETE781");
    }
}