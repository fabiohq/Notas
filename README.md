
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorType;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

        lenient().when(errorService.errorBuilder(any(), anyString(), any()))
                .thenAnswer(invocation -> {
                    ErrorDTO dto = new ErrorDTO();
                    dto.setMessage(invocation.getArgument(1));
                    return dto;
                });

        lenient().when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
                .thenAnswer(invocation ->
                        new ServiceException(
                                invocation.getArgument(0),
                                buildError(invocation.getArgument(1))
                        )
                );
    }

    @Test
    void trxBP13ShouldReturnResponseWhenSuccessful() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);
        TrxBP13Response expected = TrxBP13Response.builder().ok(true).build();

        when(trxSanbaAPI.callBP13TRX(request, "consultaDatosIPF", "consultaDatosIPF", "MQ_ROUTE"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP13Response result = service.trxBP13(request);

        assertEquals(expected, result);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("TEST_USER", request.getCabecera().getSesion().getUsuario());
    }

    @Test
    void trxBP13ShouldReturnNullWhenSuccessfulBodyIsNull() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        assertNull(service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowBadRequestWhenRuntimeExceptionOccurs() {
        TrxBP13Request request = buildBP13Request();

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowServiceUnavailableWhenIOExceptionOccurs() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowNotFoundWhenErrorBodyHasCdtDatNoExiste() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                404,
                bp13ErrorBody("CDT / DAT NO EXISTE.")
        ));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowConflictWhenErrorBodyHasGenericError() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                409,
                bp13ErrorBody("ERROR GENERICO")
        ));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowTechnicalConflictWhenErrorBodyIsInvalidJson() throws Exception {
        TrxBP13Request request = buildBP13Request();
        Call<TrxBP13Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                500,
                ResponseBody.create("invalid-json", MediaType.parse("application/json"))
        ));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void buildErrorDTOsShouldBuildNotFoundAndBadRequestErrors() {
        ErrorTrxDTO notFound = new ErrorTrxDTO();
        notFound.setMensaje("CDT / DAT NO EXISTE.");

        ErrorTrxDTO generic = new ErrorTrxDTO();
        generic.setMensaje("ERROR GENERICO");

        TrxBP13Response response = TrxBP13Response.builder()
                .errores(List.of(notFound, generic))
                .build();

        List<ErrorDTO> result = service.buildErrorDTOs(response);

        assertEquals(2, result.size());
        verify(errorService).errorBuilder(HttpStatus.NOT_FOUND, "CDT / DAT NO EXISTE.", ErrorType.FUNCTIONAL);
        verify(errorService).errorBuilder(HttpStatus.BAD_REQUEST, "ERROR GENERICO", ErrorType.FUNCTIONAL);
    }

    @Test
    void hasNotFound13ErrorShouldReturnTrueAndFalse() {
        ErrorDTO notFound = new ErrorDTO();
        notFound.setMessage("CDT / DAT NO EXISTE.");

        ErrorDTO generic = new ErrorDTO();
        generic.setMessage("ERROR");

        assertTrue(service.hasNotFound13Error(List.of(notFound)));
        assertFalse(service.hasNotFound13Error(List.of(generic)));
    }

    @Test
    void getFirst13ErrorShouldReturnFirstOrDefault() {
        ErrorDTO error = new ErrorDTO();
        error.setMessage("ERROR");

        assertEquals(error, service.getFirst13Error(List.of(error)));
        assertNotNull(service.getFirst13Error(List.of()));
    }

    @Test
    void trxBP49ShouldReturnResponseWhenSuccessful() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);
        TrxBP49Response expected = TrxBP49Response.builder().ok(true).build();

        when(trxSanbaAPI.callBP49(request, "BP49_ROUTE", "BP49_ROUTE", "MQ_ROUTE"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TrxBP49Response result = service.trxBP49(request);

        assertEquals(expected, result);
        assertEquals("WEB", request.getCabecera().getCanal());
        assertEquals("TEST_USER", request.getCabecera().getSesion().getUsuario());
    }

    @Test
    void trxBP49ShouldReturnNullWhenSuccessfulBodyIsNull() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        assertNull(service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowBadRequestWhenRuntimeExceptionOccurs() {
        TrxBP49Request request = buildBP49Request();

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowServiceUnavailableWhenIOExceptionOccurs() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowNotFoundWhenErrorBodyHasSequenceNotExists() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                404,
                bp49ErrorBody("SECUENCIA NO EXISTE")
        ));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowBadRequestWhenErrorBodyHasGenericError() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                400,
                bp49ErrorBody("ERROR GENERICO")
        ));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowTechnicalConflictWhenErrorBodyIsInvalidJson() throws Exception {
        TrxBP49Request request = buildBP49Request();
        Call<TrxBP49Response> call = mock(Call.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.error(
                500,
                ResponseBody.create("invalid-json", MediaType.parse("application/json"))
        ));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void handleMethodsShouldThrowExpectedServiceExceptions() {
        assertThrows(ServiceException.class,
                () -> service.handleRuntimeException(new RuntimeException("runtime")));

        assertThrows(ServiceException.class,
                () -> service.handleIOException());

        assertThrows(ServiceException.class,
                () -> service.handleOtherExceptions(new Exception("other")));
    }

    private TrxBP13Request buildBP13Request() {
        TrxBP13Request request = new TrxBP13Request();
        request.setCabecera(buildHeader());
        return request;
    }

    private TrxBP49Request buildBP49Request() {
        TrxBP49Request request = new TrxBP49Request();
        request.setCabecera(buildHeader());
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

    private ResponseBody bp13ErrorBody(String message) {
        return ResponseBody.create(
                "{\"errores\":[{\"mensaje\":\"" + message + "\"}]}",
                MediaType.parse("application/json")
        );
    }

    private ResponseBody bp49ErrorBody(String message) {
        return ResponseBody.create(
                "{\"errores\":[{\"mensaje\":\"" + message + "\"}]}",
                MediaType.parse("application/json")
        );
    }

    private ErrorDTO buildError(String message) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(message);
        return error;
    }
}
