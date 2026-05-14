package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response.PemfvResponse;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorType;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrxSanbaServiceImplTest {

    @Mock
    private TrxSanbaAPI trxSanbaAPI;

    @Mock
    private ErrorService errorService;

    @Mock
    private Call<TrxBP49Response> bp49Call;

    @Mock
    private Call<PemfvResponse> pemfvCall;

    @InjectMocks
    private TrxSanbaServiceImpl service;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "BP49_SERVICE_ROUTE", "BP49");
        ReflectionTestUtils.setField(service, "BP49_SERVICE_PEMFV", "PEMFV");
        ReflectionTestUtils.setField(service, "mqRoute", "MQ");
        ReflectionTestUtils.setField(service, "channel", "60");
        ReflectionTestUtils.setField(service, "user", "@NETE781");
    }

    @Test
    void trxBP49ShouldReturnBodyWhenApiIsSuccessful() throws Exception {
        TrxBP49Request request = buildBP49Request();
        TrxBP49Response expected = TrxBP49Response.builder()
                .ok(true)
                .build();

        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "MQ"))
                .thenReturn(bp49Call);
        when(bp49Call.execute())
                .thenReturn(Response.success(expected));

        TrxBP49Response result = service.trxBP49(request);

        assertThat(result).isEqualTo(expected);
        assertThat(request.getCabecera().getCanal()).isEqualTo("60");
        assertThat(request.getCabecera().getSesion().getUsuario()).isEqualTo("@NETE781");
    }

    @Test
    void trxBP49ShouldThrowServiceExceptionWhenRuntimeExceptionOccurs() throws Exception {
        TrxBP49Request request = buildBP49Request();

        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "MQ"))
                .thenThrow(new RuntimeException("runtime error"));

        assertThatThrownBy(() -> service.trxBP49(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxBP49ShouldThrowServiceExceptionWhenIOExceptionOccurs() throws Exception {
        TrxBP49Request request = buildBP49Request();

        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "MQ"))
                .thenReturn(bp49Call);
        when(bp49Call.execute())
                .thenThrow(new IOException("network error"));

        assertThatThrownBy(() -> service.trxBP49(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxBP49ShouldThrowServiceExceptionWhenGenericExceptionOccurs() throws Exception {
        TrxBP49Request request = buildBP49Request();

        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "MQ"))
                .thenReturn(bp49Call);
        when(bp49Call.execute())
                .thenThrow(new Exception("generic error"));

        assertThatThrownBy(() -> service.trxBP49(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxBP49ShouldThrowConflictWhenErrorBodyCannotBeParsed() throws Exception {
        TrxBP49Request request = buildBP49Request();

        ResponseBody errorBody = ResponseBody.create(
                "invalid-json",
                MediaType.parse("application/json")
        );

        when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "MQ"))
                .thenReturn(bp49Call);
        when(bp49Call.execute())
                .thenReturn(Response.error(400, errorBody));

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.CONFLICT),
                anyString(),
                eq(ErrorType.TECHNICAL)
        )).thenReturn(new ServiceException(
                HttpStatus.CONFLICT,
                ErrorDTO.builder().code("ERROR").message("error").build()
        ));

        assertThatThrownBy(() -> service.trxBP49(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxPemfvShouldReturnBodyWhenApiIsSuccessful() throws Exception {
        PemfvRequest request = buildPemfvRequest();
        PemfvResponse expected = PemfvResponse.builder()
                .ok(true)
                .build();

        when(trxSanbaAPI.callPEMFV(request, "PEMFV", "PEMFV", "MQ"))
                .thenReturn(pemfvCall);
        when(pemfvCall.execute())
                .thenReturn(Response.success(expected));

        PemfvResponse result = service.trxPemfv(request);

        assertThat(result).isEqualTo(expected);
        assertThat(request.getCabecera().getCanal()).isEqualTo("60");
        assertThat(request.getCabecera().getSesion().getUsuario()).isEqualTo("@NETE781");
    }

    @Test
    void trxPemfvShouldThrowServiceExceptionWhenRuntimeExceptionOccurs() throws Exception {
        PemfvRequest request = buildPemfvRequest();

        when(trxSanbaAPI.callPEMFV(request, "PEMFV", "PEMFV", "MQ"))
                .thenThrow(new RuntimeException("runtime error"));

        assertThatThrownBy(() -> service.trxPemfv(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxPemfvShouldThrowServiceExceptionWhenIOExceptionOccurs() throws Exception {
        PemfvRequest request = buildPemfvRequest();

        when(trxSanbaAPI.callPEMFV(request, "PEMFV", "PEMFV", "MQ"))
                .thenReturn(pemfvCall);
        when(pemfvCall.execute())
                .thenThrow(new IOException("network error"));

        assertThatThrownBy(() -> service.trxPemfv(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxPemfvShouldThrowServiceExceptionWhenGenericExceptionOccurs() throws Exception {
        PemfvRequest request = buildPemfvRequest();

        when(trxSanbaAPI.callPEMFV(request, "PEMFV", "PEMFV", "MQ"))
                .thenReturn(pemfvCall);
        when(pemfvCall.execute())
                .thenThrow(new Exception("generic error"));

        assertThatThrownBy(() -> service.trxPemfv(request))
                .isInstanceOf(ServiceException.class);
    }

    @Test
    void trxPemfvShouldThrowConflictWhenErrorBodyCannotBeParsed() throws Exception {
        PemfvRequest request = buildPemfvRequest();

        ResponseBody errorBody = ResponseBody.create(
                "invalid-json",
                MediaType.parse("application/json")
        );

        when(trxSanbaAPI.callPEMFV(request, "PEMFV", "PEMFV", "MQ"))
                .thenReturn(pemfvCall);
        when(pemfvCall.execute())
                .thenReturn(Response.error(400, errorBody));

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.CONFLICT),
                anyString(),
                eq(ErrorType.TECHNICAL)
        )).thenReturn(new ServiceException(
                HttpStatus.CONFLICT,
                ErrorDTO.builder().code("ERROR").message("error").build()
        ));

        assertThatThrownBy(() -> service.trxPemfv(request))
                .isInstanceOf(ServiceException.class);
    }

    private TrxBP49Request buildBP49Request() {
        Session session = new Session();
        session.setUsuario("original-user");

        TrxHeader header = new TrxHeader();
        header.setSesion(session);
        header.setCanal("original-channel");

        TrxBP49Request request = new TrxBP49Request();
        request.setCabecera(header);

        return request;
    }

    private PemfvRequest buildPemfvRequest() {
        Session session = new Session();
        session.setUsuario("original-user");

        TrxHeader header = new TrxHeader();
        header.setSesion(session);
        header.setCanal("original-channel");

        PemfvRequest request = new PemfvRequest();
        request.setCabecera(header);

        return request;
    }
}