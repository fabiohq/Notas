package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TrxSanbaServiceImplTest {

    private TrxSanbaAPI trxSanbaAPI;
    private ErrorService errorService;
    private TrxSanbaServiceImpl service;
    private Call<TrxBP14Response> call;

    @BeforeEach
    void setUp() {
        trxSanbaAPI = mock(TrxSanbaAPI.class);
        call = mock(Call.class);

        errorService = new ErrorService();
        ReflectionTestUtils.setField(errorService, "msName", "BNCBSN049");
        ReflectionTestUtils.setField(errorService, "msVersion", "v1");
        ReflectionTestUtils.setField(errorService, "level", "error");
        ReflectionTestUtils.setField(errorService, "functional", "P-F");
        ReflectionTestUtils.setField(errorService, "technical", "P-T");

        HashMap<String, String> general = new HashMap<>();
        general.put("null", "Null data");
        errorService.setGeneral(general);

        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

        ReflectionTestUtils.setField(service, "BP14_SERVICE_ROUTE", "BP14");
        ReflectionTestUtils.setField(service, "mqRoute", "MQ");
        ReflectionTestUtils.setField(service, "user", "USR");
        ReflectionTestUtils.setField(service, "channel", "WEB");
    }

    @Test
    void trxBP14ShouldReturnResponseWhenApiIsSuccessful() throws IOException {
        TrxBP14Request request = buildRequest();
        TrxBP14Response expected = new TrxBP14Response();

        when(trxSanbaAPI.callBP14TRX(any(), eq("BP14"), eq("BP14"), eq("MQ")))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP14Response result = service.trxBP14(request);

        assertEquals(expected, result);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("USR", request.getCabecera().getSesion().getUsuario());
    }

    @Test
    void trxBP14ShouldThrowServiceExceptionWhenApiThrowsRuntimeException() {
        TrxBP14Request request = buildRequest();

        when(trxSanbaAPI.callBP14TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class, () -> service.trxBP14(request));
    }

    @Test
    void trxBP14ShouldThrowServiceExceptionWhenApiThrowsIOException() throws IOException {
        TrxBP14Request request = buildRequest();

        when(trxSanbaAPI.callBP14TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("network error"));

        assertThrows(ServiceException.class, () -> service.trxBP14(request));
    }

    @Test
    void trxBP14ShouldThrowServiceExceptionWhenErrorBodyIsInvalidJson() throws IOException {
        TrxBP14Request request = buildRequest();

        ResponseBody responseBody = ResponseBody.create(
                "invalid-json",
                MediaType.parse("application/json")
        );

        when(trxSanbaAPI.callBP14TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(400, responseBody));

        assertThrows(ServiceException.class, () -> service.trxBP14(request));
    }

    @Test
    void trxBP14ShouldThrowServiceExceptionWhenErrorBodyHasErrors() throws IOException {
        TrxBP14Request request = buildRequest();

        String json = """
                {
                  "errores": [
                    {
                      "mensaje": "ERROR FUNCIONAL"
                    }
                  ]
                }
                """;

        ResponseBody responseBody = ResponseBody.create(
                json,
                MediaType.parse("application/json")
        );

        when(trxSanbaAPI.callBP14TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(400, responseBody));

        assertThrows(ServiceException.class, () -> service.trxBP14(request));
    }

    @Test
    void trxBP14ShouldThrowNotFoundWhenErrorIsCdtDatNoExiste() throws IOException {
        TrxBP14Request request = buildRequest();

        String json = """
                {
                  "errores": [
                    {
                      "mensaje": "CDT / DAT NO EXISTE."
                    }
                  ]
                }
                """;

        ResponseBody responseBody = ResponseBody.create(
                json,
                MediaType.parse("application/json")
        );

        when(trxSanbaAPI.callBP14TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(400, responseBody));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP14(request)
        );

        assertNotNull(exception);
    }

    private TrxBP14Request buildRequest() {
        Session session = new Session();
        TrxHeader header = new TrxHeader();
        header.setSesion(session);

        TrxBP14Request request = new TrxBP14Request();
        request.setCabecera(header);

        return request;
    }
}
