package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.context.ContextResponse;

import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class ContextAPIImplTest {

    @Mock
    private ContextAPI contextAPI;

    @Mock
    private Call<ContextResponse> call;

    @Test
    void shouldPutContextSuccessfully() throws Exception {
        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        ContextResponse response = new ContextResponse();

        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(response));

        service.putContext("key-test", "value-test");

        verify(contextAPI).putCache(any(ContextRequest.class));
        verify(call).execute();
    }

    @Test
    void shouldCatchExceptionWhenPutContextFails() throws Exception {
        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("error"));

        service.putContext("key-test", "value-test");

        verify(contextAPI).putCache(any(ContextRequest.class));
        verify(call).execute();
    }

    @Test
    void shouldGetContextSuccessfully() throws Exception {
        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        ContextResponse response = new ContextResponse();
        response.setValue("value-test");

        when(contextAPI.getCache(eq("key-test"), eq("cdt"))).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(response));

        Object result = service.getContext("key-test");

        assertEquals("value-test", result);
    }

    @Test
    void shouldReturnNullWhenGetContextFails() throws Exception {
        ContextAPIImpl service = new ContextAPIImpl(contextAPI);

        when(contextAPI.getCache(eq("key-test"), eq("cdt"))).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("error"));

        Object result = service.getContext("key-test");

        assertNull(result);
    }
}
package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class ParameterAPIImplTest {

    @Mock
    private ParametersAPI parametersAPI;

    @Mock
    private Call<GeographiesParametersResponseDTO> call;

    @Test
    void shouldGetParameterSuccessfully() throws Exception {
        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        SecurityHeaders headers = new SecurityHeaders("Bearer token", "client-id");

        DataListDTO data = new DataListDTO();
        data.setCode("001");
        data.setDescription("description");
        data.setListCode("list");

        GeographiesParametersResponseDTO body = new GeographiesParametersResponseDTO();
        body.setParameters(List.of(data));

        when(parametersAPI.getParameter(
                eq("0112"),
                eq("CO"),
                eq("Bearer token"),
                eq("client-id")))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.success(body));

        List<DataListDTO> result =
                service.getParameter("0112", "CO", headers);

        assertEquals(1, result.size());
        assertEquals("001", result.get(0).getCode());
    }

    @Test
    void shouldThrowServiceExceptionWhenParametersAreEmpty() throws Exception {
        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        SecurityHeaders headers = new SecurityHeaders("Bearer token", "client-id");

        GeographiesParametersResponseDTO body = new GeographiesParametersResponseDTO();
        body.setParameters(Collections.emptyList());

        when(parametersAPI.getParameter(
                eq("0112"),
                eq("CO"),
                eq("Bearer token"),
                eq("client-id")))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.success(body));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers));
    }

    @Test
    void shouldThrowServiceExceptionWhenResponseIsNotSuccessful() throws Exception {
        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        SecurityHeaders headers = new SecurityHeaders("Bearer token", "client-id");

        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                "{}"
        );

        when(parametersAPI.getParameter(
                eq("0112"),
                eq("CO"),
                eq("Bearer token"),
                eq("client-id")))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.error(500, errorBody));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers));
    }

    @Test
    void shouldThrowServiceExceptionWhenIOExceptionOccurs() throws Exception {
        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        SecurityHeaders headers = new SecurityHeaders("Bearer token", "client-id");

        when(parametersAPI.getParameter(
                eq("0112"),
                eq("CO"),
                eq("Bearer token"),
                eq("client-id")))
                .thenReturn(call);

        when(call.execute()).thenThrow(new IOException("network error"));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers));
    }

    @Test
    void shouldThrowServiceExceptionWhenRuntimeExceptionOccurs() throws Exception {
        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        SecurityHeaders headers = new SecurityHeaders("Bearer token", "client-id");

        when(parametersAPI.getParameter(
                eq("0112"),
                eq("CO"),
                eq("Bearer token"),
                eq("client-id")))
                .thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class,
                () -> service.getParameter("0112", "CO", headers));
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class TrxPersonAPIImplTest {

    @Mock
    private TrxPersonAPI trxPersonAPI;

    @Mock
    private Call<TrxPersonResponse> call;

    private TrxPersonAPIImpl service;

    @BeforeEach
    void setUp() throws Exception {
        service = new TrxPersonAPIImpl(trxPersonAPI);

        setField(service, "mqRoute", "QCTFD");
        setField(service, "channel", "WEB");
        setField(service, "user", "TEST_USER");
    }

    @Test
    void shouldCallPostTRXSuccessfully() throws Exception {
        TrxPersonRequest request = new TrxPersonRequest();
        TrxPersonResponse body = new TrxPersonResponse();

        when(trxPersonAPI.callPostTRX(
                any(TrxPersonRequest.class),
                eq(ClientEnum.PEF1.value()),
                eq(ClientEnum.PEF1.value()),
                eq("QCTFD")))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.success(body));

        TrxPersonResponse result =
                service.callPostTRX(request, ClientEnum.PEF1);

        assertEquals(body, result);
    }

    @Test
    void shouldThrowServiceExceptionWhenResponseIsNotSuccessful() throws Exception {
        TrxPersonRequest request = new TrxPersonRequest();

        String errorJson = """
                {
                  "errores": [
                    {
                      "mensaje": "Error trx"
                    }
                  ]
                }
                """;

        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"),
                errorJson
        );

        when(trxPersonAPI.callPostTRX(
                any(TrxPersonRequest.class),
                eq(ClientEnum.PEF1.value()),
                eq(ClientEnum.PEF1.value()),
                eq("QCTFD")))
                .thenReturn(call);

        when(call.execute()).thenReturn(Response.error(409, errorBody));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(request, ClientEnum.PEF1));
    }

    @Test
    void shouldThrowServiceExceptionWhenIOExceptionOccurs() throws Exception {
        TrxPersonRequest request = new TrxPersonRequest();

        when(trxPersonAPI.callPostTRX(
                any(TrxPersonRequest.class),
                eq(ClientEnum.PEF1.value()),
                eq(ClientEnum.PEF1.value()),
                eq("QCTFD")))
                .thenReturn(call);

        when(call.execute()).thenThrow(new IOException("network error"));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(request, ClientEnum.PEF1));
    }

    @Test
    void shouldThrowServiceExceptionWhenRuntimeExceptionOccurs() throws Exception {
        TrxPersonRequest request = new TrxPersonRequest();

        when(trxPersonAPI.callPostTRX(
                any(TrxPersonRequest.class),
                eq(ClientEnum.PEF1.value()),
                eq(ClientEnum.PEF1.value()),
                eq("QCTFD")))
                .thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class,
                () -> service.callPostTRX(request, ClientEnum.PEF1));
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}


