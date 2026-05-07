Aquí van los tests que faltan.
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CiudadComparisonRequestTest {

    @Test
    void shouldSetAndGetAllFields() {
        CiudadComparisonRequest dto = new CiudadComparisonRequest();

        dto.setCiudadIngresada("Bogota");
        dto.setCiudadServicio("Bogotá");

        assertEquals("Bogota", dto.getCiudadIngresada());
        assertEquals("Bogotá", dto.getCiudadServicio());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CiudadComparisonRequest dto = new CiudadComparisonRequest("Cali", "Cali");

        assertEquals("Cali", dto.getCiudadIngresada());
        assertEquals("Cali", dto.getCiudadServicio());
    }

    @Test
    void shouldBuildWithBuilder() {
        CiudadComparisonRequest dto = CiudadComparisonRequest.builder()
                .ciudadIngresada("Medellin")
                .ciudadServicio("Medellín")
                .build();

        assertEquals("Medellin", dto.getCiudadIngresada());
        assertEquals("Medellín", dto.getCiudadServicio());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContextAPIImplTest {

    @Mock
    private ContextAPI contextAPI;

    @Mock
    private Call<ContextResponse> call;

    @InjectMocks
    private ContextAPIImpl contextAPIImpl;

    @Test
    void putContextShouldSendRequestWithProductKeyAndValue() {
        when(contextAPI.putCache(any(ContextRequest.class))).thenReturn(call);

        contextAPIImpl.putContext("key-1", "value-1");

        ArgumentCaptor<ContextRequest> captor = ArgumentCaptor.forClass(ContextRequest.class);
        verify(contextAPI).putCache(captor.capture());
        verify(call).enqueue(any());

        ContextRequest request = captor.getValue();
        assertEquals("key-1", request.getKey());
        assertEquals("value-1", request.getValue());
        assertEquals(ContextAPIImpl.PRODUCT, request.getProduct());
    }

    @Test
    void putContextShouldNotThrowWhenRuntimeExceptionOccurs() {
        when(contextAPI.putCache(any(ContextRequest.class))).thenThrow(new RuntimeException("error"));

        assertDoesNotThrow(() -> contextAPIImpl.putContext("key", "value"));

        verify(contextAPI).putCache(any(ContextRequest.class));
    }

    @Test
    void getContextShouldReturnValueWhenResponseIsOk() throws IOException {
        ContextResponse responseBody = new ContextResponse();
        responseBody.setKey("key-1");
        responseBody.setProduct(ContextAPIImpl.PRODUCT);
        responseBody.setValue("cached-value");

        when(contextAPI.getCache("key-1", ContextAPIImpl.PRODUCT)).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(responseBody));

        Object result = contextAPIImpl.getContext("key-1");

        assertEquals("cached-value", result);
        verify(contextAPI).getCache("key-1", ContextAPIImpl.PRODUCT);
    }

    @Test
    void getContextShouldReturnNullWhenIOExceptionOccurs() throws IOException {
        when(contextAPI.getCache("key-1", ContextAPIImpl.PRODUCT)).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("io error"));

        Object result = contextAPIImpl.getContext("key-1");

        assertNull(result);
    }

    @Test
    void getContextShouldReturnNullWhenRuntimeExceptionOccurs() {
        when(contextAPI.getCache("key-1", ContextAPIImpl.PRODUCT))
                .thenThrow(new RuntimeException("runtime error"));

        Object result = contextAPIImpl.getContext("key-1");

        assertNull(result);
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParameterAPIImplTest {

    @Mock
    private ParametersAPI parametersAPI;

    @Mock
    private Call<GeographiesParametersResponseDTO> call;

    @InjectMocks
    private ParameterAPIImpl parameterAPIImpl;

    @Test
    void getParameterShouldReturnParametersWhenResponseIsSuccessful() throws IOException {
        DataListDTO data = DataListDTO.builder()
                .listCode("0008")
                .code("11001")
                .description("BOGOTA")
                .build();

        GeographiesParametersResponseDTO body = GeographiesParametersResponseDTO.builder()
                .parameters(List.of(data))
                .build();

        when(parametersAPI.getParameter("0008", "11001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        List<DataListDTO> result = parameterAPIImpl.getParameter(
                "0008",
                "11001",
                "Bearer token",
                "client-id"
        );

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("11001", result.get(0).getCode());
        assertEquals("BOGOTA", result.get(0).getDescription());
    }

    @Test
    void getParameterShouldThrowServiceExceptionWhenResponseBodyIsNull() throws IOException {
        when(parametersAPI.getParameter("0008", "11001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        assertThrows(ServiceException.class, () ->
                parameterAPIImpl.getParameter("0008", "11001", "Bearer token", "client-id")
        );
    }

    @Test
    void getParameterShouldThrowServiceExceptionWhenParametersAreEmpty() throws IOException {
        GeographiesParametersResponseDTO body = GeographiesParametersResponseDTO.builder()
                .parameters(List.of())
                .build();

        when(parametersAPI.getParameter("0008", "11001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        assertThrows(ServiceException.class, () ->
                parameterAPIImpl.getParameter("0008", "11001", "Bearer token", "client-id")
        );
    }

    @Test
    void getParameterShouldThrowServiceExceptionWhenIOExceptionOccurs() throws IOException {
        when(parametersAPI.getParameter("0008", "11001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("io error"));

        assertThrows(ServiceException.class, () ->
                parameterAPIImpl.getParameter("0008", "11001", "Bearer token", "client-id")
        );
    }

    @Test
    void getParameterShouldThrowServiceExceptionWhenRuntimeExceptionOccurs() {
        when(parametersAPI.getParameter("0008", "11001", "Bearer token", "client-id"))
                .thenThrow(new RuntimeException("runtime error"));

        assertThrows(ServiceException.class, () ->
                parameterAPIImpl.getParameter("0008", "11001", "Bearer token", "client-id")
        );
    }
}
Ojo: pegaste ParameterAPIImpl dos veces; deja solo un ParameterAPIImplTest.