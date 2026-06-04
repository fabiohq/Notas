package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
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
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorType;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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

        when(errorService.errorBuilder(any(), anyString(), any()))
                .thenAnswer(inv -> ErrorDTO.builder()
                        .message(inv.getArgument(1))
                        .build());

        when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
                .thenReturn(new ServiceException(HttpStatus.CONFLICT, "technical-error"));
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
    void trxBP17ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
    }

    @Test
    void trxBP17ShouldThrowIOExceptionBranch() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
    }

    @Test
    void trxBP17ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
    }

    @Test
    void trxBP17ShouldThrowWhenFunctionalErrors() throws Exception {
        TrxBP17Request request = request(TrxBP17Request.class);

        when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
        when(bp17Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP17"))));

        assertThrows(ServiceException.class, () -> service.trxBP17(request));
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
    void trxBP31ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBP31Request request = request(TrxBP31Request.class);

        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
        when(bp31Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP31(request));
    }

    @Test
    void trxBP31ShouldThrowIOExceptionBranch() throws Exception {
        TrxBP31Request request = request(TrxBP31Request.class);

        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
        when(bp31Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP31(request));
    }

    @Test
    void trxBP31ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBP31Request request = request(TrxBP31Request.class);

        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
        when(bp31Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP31(request));
    }

    @Test
    void trxBP31ShouldEnterErrorBranchWithValidJson() throws Exception {
        TrxBP31Request request = request(TrxBP31Request.class);

        when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
        when(bp31Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP31"))));

        assertThrows(Exception.class, () -> service.trxBP31(request));
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
    void trxPEPFShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxPEPF(request));
    }

    @Test
    void trxPEPFShouldThrowIOExceptionBranch() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxPEPF(request));
    }

    @Test
    void trxPEPFShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxPEPF(request));
    }

    @Test
    void trxPEPFShouldReturnNullWhenNoExisteCdt() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenReturn(Response.error(400, errorBody(errorJson("NO EXISTE CDT"))));

        assertNull(service.trxPEPF(request));
    }

    @Test
    void trxPEPFShouldThrowWhenFunctionalErrors() throws Exception {
        TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

        when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
        when(pepfCall.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR PEPF"))));

        assertThrows(ServiceException.class, () -> service.trxPEPF(request));
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
    void trxBP13ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBP13Request request = request(TrxBP13Request.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
        when(bp13Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowIOExceptionBranch() throws Exception {
        TrxBP13Request request = request(TrxBP13Request.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
        when(bp13Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBP13Request request = request(TrxBP13Request.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
        when(bp13Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
    }

    @Test
    void trxBP13ShouldThrowWhenFunctionalErrors() throws Exception {
        TrxBP13Request request = request(TrxBP13Request.class);

        when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
        when(bp13Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP13"))));

        assertThrows(ServiceException.class, () -> service.trxBP13(request));
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
    void trxBP01ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBp01Request request = request(TrxBp01Request.class);

        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
        when(bp01Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP01(request));
    }

    @Test
    void trxBP01ShouldThrowIOExceptionBranch() throws Exception {
        TrxBp01Request request = request(TrxBp01Request.class);

        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
        when(bp01Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP01(request));
    }

    @Test
    void trxBP01ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBp01Request request = request(TrxBp01Request.class);

        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
        when(bp01Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP01(request));
    }

    @Test
    void trxBP01ShouldThrowWhenFunctionalErrors() throws Exception {
        TrxBp01Request request = request(TrxBp01Request.class);

        when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
        when(bp01Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP01"))));

        assertThrows(ServiceException.class, () -> service.trxBP01(request));
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
    void trxBP02ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBp02Request request = request(TrxBp02Request.class);

        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
        when(bp02Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP02(request));
    }

    @Test
    void trxBP02ShouldThrowIOExceptionBranch() throws Exception {
        TrxBp02Request request = request(TrxBp02Request.class);

        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
        when(bp02Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP02(request));
    }

    @Test
    void trxBP02ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBp02Request request = request(TrxBp02Request.class);

        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
        when(bp02Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP02(request));
    }

    @Test
    void trxBP02ShouldThrowWhenFunctionalErrors() throws Exception {
        TrxBp02Request request = request(TrxBp02Request.class);

        when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
        when(bp02Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP02"))));

        assertThrows(ServiceException.class, () -> service.trxBP02(request));
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
    void trxBP49ShouldThrowRuntimeExceptionBranch() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenThrow(new RuntimeException("runtime"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowIOExceptionBranch() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenThrow(new IOException("io"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowWhenInvalidJsonErrorBody() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenReturn(Response.error(400, errorBody("{bad-json")));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("SECUENCIA NO EXISTE"))));

        when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
                .thenReturn(new ServiceException(HttpStatus.NOT_FOUND, "Deposit not found"));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void trxBP49ShouldThrowWhenFunctionalErrors() throws Exception {
        TrxBP49Request request = request(TrxBP49Request.class);

        when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
        when(bp49Call.execute()).thenReturn(Response.error(400, errorBody(errorJson("ERROR BP49"))));

        assertThrows(ServiceException.class, () -> service.trxBP49(request));
    }

    @Test
    void validateTrxBp31ShouldFillErrors() throws Exception {
        TrxBP31Response err = new TrxBP31Response();
        err.setErrores(List.of(trxError("ERROR BP31")));

        List<ErrorDTO> errors = new ArrayList<>();

        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "validateTrxBp31",
                TrxBP31Response.class,
                ObjectMapper.class,
                String.class,
                List.class);
        method.setAccessible(true);

        method.invoke(service, err, new ObjectMapper(), errorJson("ERROR BP31"), errors);

        assertEquals(1, errors.size());
    }

    @Test
    void validateTrxBp31ShouldThrowWhenInvalidJson() throws Exception {
        TrxBP31Response err = new TrxBP31Response();
        err.setErrores(new ArrayList<>());

        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "validateTrxBp31",
                TrxBP31Response.class,
                ObjectMapper.class,
                String.class,
                List.class);
        method.setAccessible(true);

        assertThrows(Exception.class,
                () -> method.invoke(service, err, new ObjectMapper(), "{bad-json", new ArrayList<>()));
    }

    @Test
    void validateTrxPEPFShouldFillErrors() throws Exception {
        List<ErrorDTO> errors = new ArrayList<>();

        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "validateTrxPEPF",
                ObjectMapper.class,
                String.class,
                TrxPEPFDataResponse.class,
                List.class);
        method.setAccessible(true);

        method.invoke(service, new ObjectMapper(), errorJson("ERROR PEPF"), null, errors);

        assertEquals(1, errors.size());
    }

    @Test
    void validateTrxPEPFShouldThrowWhenInvalidJson() throws Exception {
        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "validateTrxPEPF",
                ObjectMapper.class,
                String.class,
                TrxPEPFDataResponse.class,
                List.class);
        method.setAccessible(true);

        assertThrows(Exception.class,
                () -> method.invoke(service, new ObjectMapper(), "{bad-json", null, new ArrayList<>()));
    }

    @Test
    void trxbp49ValidateShouldFillErrors() throws Exception {
        List<ErrorDTO> errors = new ArrayList<>();

        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "trxbp49Validate",
                TrxBP49Response.class,
                ObjectMapper.class,
                String.class,
                List.class);
        method.setAccessible(true);

        method.invoke(service, null, new ObjectMapper(), errorJson("ERROR BP49"), errors);

        assertEquals(1, errors.size());
    }

    @Test
    void trxbp49ValidateShouldThrowWhenInvalidJson() throws Exception {
        Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
                "trxbp49Validate",
                TrxBP49Response.class,
                ObjectMapper.class,
                String.class,
                List.class);
        method.setAccessible(true);

        assertThrows(Exception.class,
                () -> method.invoke(service, null, new ObjectMapper(), "{bad-json", new ArrayList<>()));
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

    private ErrorTrxDTO trxError(String message) {
        ErrorTrxDTO error = org.mockito.Mockito.mock(ErrorTrxDTO.class);
        when(error.getMensaje()).thenReturn(message);
        return error;
    }

    private String errorJson(String message) {
        return "{\"errores\":[{\"mensaje\":\"" + message + "\"}]}";
    }

    private ResponseBody errorBody(String json) {
        return ResponseBody.create(json, MediaType.parse("application/json"));
    }
}
