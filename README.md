package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorType;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import retrofit2.Call;
import retrofit2.Response;

class TrxSanbaServiceImplTest {

    private TrxSanbaAPI trxSanbaAPI;
    private ErrorService errorService;
    private TrxSanbaServiceImpl service;
    private Call<TrxBP17Response> call;

    @BeforeEach
    void setUp() {
        trxSanbaAPI = mock(TrxSanbaAPI.class);
        errorService = mock(ErrorService.class);
        call = mock(Call.class);

        service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

        ReflectionTestUtils.setField(service, "mqRoute", "MQ.ROUTE");
        ReflectionTestUtils.setField(service, "channel", "WEB");
        ReflectionTestUtils.setField(service, "user", "USRTEST");
    }

    private TrxBP17Request buildRequest() {
        Session session = new Session();
        TrxHeader header = new TrxHeader();
        header.setSesion(session);

        TrxBP17Request request = new TrxBP17Request();
        request.setCabecera(header);

        return request;
    }

    @Test
    @DisplayName("Debe retornar respuesta cuando SANBA responde exitosamente")
    void trxBP17ShouldReturnResponseWhenApiIsSuccessful() throws Exception {
        TrxBP17Request request = buildRequest();
        TrxBP17Response expectedResponse = new TrxBP17Response();
        expectedResponse.setOk(Boolean.TRUE);

        when(trxSanbaAPI.callBP17TRX(
                eq(request),
                eq("simulacionCDTBP17S171"),
                eq("simulacionCDTBP17S171"),
                eq("MQ.ROUTE")
        )).thenReturn(call);

        when(call.execute()).thenReturn(Response.success(expectedResponse));

        TrxBP17Response actualResponse = service.trxBP17(request);

        assertSame(expectedResponse, actualResponse);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("USRTEST", request.getCabecera().getSesion().getUsuario());

        verify(trxSanbaAPI).callBP17TRX(
                eq(request),
                eq("simulacionCDTBP17S171"),
                eq("simulacionCDTBP17S171"),
                eq("MQ.ROUTE")
        );
    }

    @Test
    @DisplayName("Debe lanzar ServiceException cuando ocurre IOException")
    void trxBP17ShouldThrowServiceUnavailableWhenIOExceptionOccurs() throws Exception {
        TrxBP17Request request = buildRequest();

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenThrow(new IOException("Network error"));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, exception.getCode());
    }

    @Test
    @DisplayName("Debe lanzar ServiceException BAD_REQUEST cuando ocurre RuntimeException")
    void trxBP17ShouldThrowBadRequestWhenRuntimeExceptionOccurs() throws Exception {
        TrxBP17Request request = buildRequest();

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenThrow(new RuntimeException("Runtime error"));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
    }

    @Test
    @DisplayName("Debe lanzar ServiceException CONFLICT cuando ocurre excepción genérica")
    void trxBP17ShouldThrowConflictWhenGenericExceptionOccurs() throws Exception {
        TrxBP17Request request = buildRequest();

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenThrow(new Exception("Generic error"));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertEquals(HttpStatus.CONFLICT, exception.getCode());
    }

    @Test
    @DisplayName("Debe lanzar ServiceException con error funcional cuando SANBA responde error parseable")
    void trxBP17ShouldThrowFunctionalErrorWhenApiReturnsErrorBody() throws Exception {
        TrxBP17Request request = buildRequest();

        String errorJson = """
                {
                  "errores": [
                    {
                      "mensaje": "Error funcional SANBA"
                    }
                  ]
                }
                """;

        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                errorJson
        );

        ErrorDTO errorDTO = ErrorDTO.builder()
                .code("MS-TEST-P-F-9400")
                .level("error")
                .message("Error funcional SANBA")
                .description("Error funcional SANBA")
                .build();

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.error(400, errorBody));

        when(errorService.errorBuilder(
                eq(HttpStatus.BAD_REQUEST),
                eq("Error funcional SANBA"),
                eq(ErrorType.FUNCTIONAL)
        )).thenReturn(errorDTO);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertEquals(HttpStatus.CONFLICT, exception.getCode());
        assertSame(errorDTO, exception.getError());
    }

    @Test
    @DisplayName("Debe lanzar ServiceException técnico cuando el error body no se puede parsear")
    void trxBP17ShouldThrowTechnicalErrorWhenErrorBodyIsInvalidJson() throws Exception {
        TrxBP17Request request = buildRequest();

        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                "INVALID_JSON"
        );

        ErrorDTO technicalError = ErrorDTO.builder()
                .code("MS-TEST-P-T-9409")
                .level("error")
                .message("Error técnico")
                .description("Error técnico")
                .build();

        ServiceException expectedException =
                new ServiceException(HttpStatus.CONFLICT, technicalError);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.error(500, errorBody));

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.CONFLICT),
                anyString(),
                eq(ErrorType.TECHNICAL)
        )).thenReturn(expectedException);

        ServiceException actualException = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertSame(expectedException, actualException);
    }

    @Test
    @DisplayName("Debe lanzar error genérico cuando SANBA responde errores vacíos")
    void trxBP17ShouldThrowGenericErrorWhenParsedErrorHasEmptyErrors() throws Exception {
        TrxBP17Request request = buildRequest();

        String errorJson = """
                {
                  "errores": []
                }
                """;

        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                errorJson
        );

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.error(400, errorBody));

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> service.trxBP17(request)
        );

        assertEquals(HttpStatus.CONFLICT, exception.getCode());
    }
}









ResponseBody.create(
        "INVALID_JSON",
        MediaType.parse("application/json")
);

