package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParameterAPIImplTest {

    @Test
    void shouldGetParameterSuccessfully() throws Exception {
        ParametersAPI parametersAPI = mock(ParametersAPI.class);
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);

        DataListDTO data = new DataListDTO("LIST", "CODE", "DESC");
        GeographiesParametersResponseDTO body =
                new GeographiesParametersResponseDTO(List.of(data));

        when(parametersAPI.getParameter("PARAM", "CODE", "AUTH", "CLIENT"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        List<DataListDTO> result =
                service.getParameter("PARAM", "CODE", new SecurityHeaders("AUTH", "CLIENT"));

        assertEquals(1, result.size());
        assertEquals("CODE", result.get(0).getCode());
        assertEquals("DESC", result.get(0).getDescription());
    }

    @Test
    void shouldThrowWhenResponseBodyIsNull() throws Exception {
        ParametersAPI parametersAPI = mock(ParametersAPI.class);
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);

        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(null));

        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        assertThrows(ServiceException.class,
                () -> service.getParameter("PARAM", "CODE", new SecurityHeaders("AUTH", "CLIENT")));
    }

    @Test
    void shouldThrowWhenParametersAreEmpty() throws Exception {
        ParametersAPI parametersAPI = mock(ParametersAPI.class);
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);

        GeographiesParametersResponseDTO body =
                new GeographiesParametersResponseDTO(List.of());

        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        assertThrows(ServiceException.class,
                () -> service.getParameter("PARAM", "CODE", new SecurityHeaders("AUTH", "CLIENT")));
    }

    @Test
    void shouldThrowInternalServerErrorWhenRuntimeExceptionOccurs() throws Exception {
        ParametersAPI parametersAPI = mock(ParametersAPI.class);
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);

        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("ERROR"));

        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> service.getParameter("PARAM", "CODE", new SecurityHeaders("AUTH", "CLIENT")));

        assertEquals(500, exception.getCode().value());
    }

    @Test
    void shouldThrowInternalServerErrorWhenIOExceptionOccurs() throws Exception {
        ParametersAPI parametersAPI = mock(ParametersAPI.class);
        Call<GeographiesParametersResponseDTO> call = mock(Call.class);

        when(parametersAPI.getParameter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("ERROR"));

        ParameterAPIImpl service = new ParameterAPIImpl(parametersAPI);

        ServiceException exception = assertThrows(ServiceException.class,
                () -> service.getParameter("PARAM", "CODE", new SecurityHeaders("AUTH", "CLIENT")));

        assertEquals(500, exception.getCode().value());
    }
}