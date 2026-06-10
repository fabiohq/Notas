package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersRequest;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BanksServiceImplTest {

    @Test
    void shouldReturnBanksResponseWhenApiCallIsSuccessful() throws IOException {
        BanksApi banksApi = mock(BanksApi.class);
        Call<BanksDTO> call = mock(Call.class);

        BanksDTO expected = BanksDTO.builder().build();

        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        when(banksApi.callBanks("Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        BanksServiceImpl service = new BanksServiceImpl(banksApi);

        BanksDTO result = service.banksResponse(request);

        assertEquals(expected, result);
        verify(banksApi).callBanks("Bearer token", "client-id");
        verify(call).execute();
    }

    @Test
    void shouldReturnNullWhenApiThrowsException() throws IOException {
        BanksApi banksApi = mock(BanksApi.class);
        Call<BanksDTO> call = mock(Call.class);

        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        when(banksApi.callBanks("Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("error"));

        BanksServiceImpl service = new BanksServiceImpl(banksApi);

        assertNull(service.banksResponse(request));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductDirectoryServiceImplTest {

    @Test
    void shouldReturnAmountRangeWhenApiCallIsSuccessful() throws IOException {
        ProductDirectoryAPI api = mock(ProductDirectoryAPI.class);
        Call<AmountRangeResponse> call = mock(Call.class);

        AmountRangeRequest request = AmountRangeRequest.builder()
                .productId("60001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        AmountRangeResponse expected = AmountRangeResponse.builder().build();

        when(api.callAmountRange("60001", "Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(api);

        AmountRangeResponse result = service.amountRange(request);

        assertEquals(expected, result);
        verify(api).callAmountRange("60001", "Bearer token", "client-id");
        verify(call).execute();
    }

    @Test
    void shouldReturnNullWhenApiThrowsException() throws IOException {
        ProductDirectoryAPI api = mock(ProductDirectoryAPI.class);
        Call<AmountRangeResponse> call = mock(Call.class);

        AmountRangeRequest request = AmountRangeRequest.builder()
                .productId("60001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        when(api.callAmountRange("60001", "Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("error"));

        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(api);

        assertNull(service.amountRange(request));
    }
}
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersResponse;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TermDepositParametersServiceImplTest {

    @Test
    void shouldReturnParametersWhenApiCallIsSuccessful() throws IOException {
        TermDepositParametersAPI api = mock(TermDepositParametersAPI.class);
        Call<TermDepositParametersResponse> call = mock(Call.class);

        TermDepositParametersRequest request = TermDepositParametersRequest.builder()
                .productId("60001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        TermDepositParametersResponse expected =
                TermDepositParametersResponse.builder().build();

        when(api.callTermDepositParameters("60001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TermDepositParametersServiceImpl service =
                new TermDepositParametersServiceImpl(api);

        TermDepositParametersResponse result =
                service.termDepositParameters(request);

        assertEquals(expected, result);
        verify(api).callTermDepositParameters("60001", "Bearer token", "client-id");
        verify(call).execute();
    }

    @Test
    void shouldReturnNullWhenApiThrowsException() throws IOException {
        TermDepositParametersAPI api = mock(TermDepositParametersAPI.class);
        Call<TermDepositParametersResponse> call = mock(Call.class);

        TermDepositParametersRequest request = TermDepositParametersRequest.builder()
                .productId("60001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        when(api.callTermDepositParameters("60001", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenThrow(new IOException("error"));

        TermDepositParametersServiceImpl service =
                new TermDepositParametersServiceImpl(api);

        assertNull(service.termDepositParameters(request));
    }
}


