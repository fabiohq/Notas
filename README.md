Te faltan TrxPersonAPIImpl y ParameterAPIImpl.
Haz tests iguales al de ContextAPIImpl.
TrxPersonAPIImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ClientEnum;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrxPersonAPIImplTest {

    @Test
    void shouldCallPostTRX() throws Exception {

        TrxPersonAPI api = mock(TrxPersonAPI.class);

        Call<TrxPersonResponse> call = mock(Call.class);

        TrxPersonResponse trxResponse = new TrxPersonResponse();

        when(api.callPostTRX(any(), anyString()))
                .thenReturn(call);

        when(call.execute())
                .thenReturn(Response.success(trxResponse));

        TrxPersonAPIImpl service = new TrxPersonAPIImpl(api);

        TrxPersonResponse response =
                service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF2);

        assertNotNull(response);

        verify(api).callPostTRX(any(), anyString());
        verify(call).execute();
    }

    @Test
    void shouldThrowExceptionWhenCallFails() throws Exception {

        TrxPersonAPI api = mock(TrxPersonAPI.class);

        Call<TrxPersonResponse> call = mock(Call.class);

        when(api.callPostTRX(any(), anyString()))
                .thenReturn(call);

        when(call.execute())
                .thenThrow(new RuntimeException("ERROR"));

        TrxPersonAPIImpl service = new TrxPersonAPIImpl(api);

        assertThrows(Exception.class, () ->
                service.callPostTRX(new TrxPersonRequest(), ClientEnum.PEF2)
        );
    }
}
ParameterAPIImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.ParameterResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParameterAPIImplTest {

    @Test
    void shouldGetParameters() throws Exception {

        ParametersAPI api = mock(ParametersAPI.class);

        Call<ParameterResponse> call = mock(Call.class);

        DataListDTO dto = new DataListDTO();
        dto.setCode("01");

        ParameterResponse parameterResponse = new ParameterResponse();
        parameterResponse.setData(List.of(dto));

        when(api.getParameters(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute())
                .thenReturn(Response.success(parameterResponse));

        ParameterAPIImpl service = new ParameterAPIImpl(api);

        List<DataListDTO> response =
                service.getParameter(
                        "COUNTRY",
                        "01",
                        new SecurityHeaders("token", "client")
                );

        assertNotNull(response);
        assertEquals(1, response.size());

        verify(api).getParameters(anyString(), anyString(), anyString(), anyString());
        verify(call).execute();
    }

    @Test
    void shouldReturnEmptyListWhenExceptionOccurs() throws Exception {

        ParametersAPI api = mock(ParametersAPI.class);

        Call<ParameterResponse> call = mock(Call.class);

        when(api.getParameters(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(call);

        when(call.execute())
                .thenThrow(new RuntimeException("ERROR"));

        ParameterAPIImpl service = new ParameterAPIImpl(api);

        List<DataListDTO> response =
                service.getParameter(
                        "COUNTRY",
                        "01",
                        new SecurityHeaders("token", "client")
                );

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}
Con esos dos ya deberías dejar client.impl prácticamente en 100%.