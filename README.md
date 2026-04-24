package com.santander.bnc.bsn049.bncbsn049mscontracts.client.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksParametersRequest;

import retrofit2.Call;
import retrofit2.Response;

class BanksServiceImplTest {

    private BanksApi banksApi;
    private BanksServiceImpl banksService;

    @BeforeEach
    void setUp() {
        banksApi = mock(BanksApi.class);
        banksService = new BanksServiceImpl(banksApi);
    }

    @Test
    void shouldReturnBodyWhenApiCallIsSuccessful() throws Exception {
        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("auth")
                .xSantanderClientId("client-id")
                .build();

        BanksDTO expected = mock(BanksDTO.class);

        @SuppressWarnings("unchecked")
        Call<BanksDTO> call = mock(Call.class);

        when(banksApi.callBanks("auth", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        BanksDTO result = banksService.banksResponse(request);

        assertSame(expected, result);
    }

    @Test
    void shouldReturnNullWhenApiThrowsException() throws Exception {
        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("auth")
                .xSantanderClientId("client-id")
                .build();

        @SuppressWarnings("unchecked")
        Call<BanksDTO> call = mock(Call.class);

        when(banksApi.callBanks("auth", "client-id")).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("boom"));

        BanksDTO result = banksService.banksResponse(request);

        assertNull(result);
    }
}