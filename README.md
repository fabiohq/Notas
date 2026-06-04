
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientSimpleImplTest {

    @Mock
    BanksApi banksApi;

    @Mock
    ProductDirectoryAPI productDirectoryAPI;

    @Mock
    TermDepositParametersAPI termDepositParametersAPI;

    @Mock
    Call<BanksDTO> banksCall;

    @Mock
    Call<AmountRangeResponse> amountRangeCall;

    @Mock
    Call<TermDepositParametersResponse> parametersCall;

    @Test
    void banksResponseShouldReturnBodyWhenApiSuccess() throws Exception {
        BanksDTO body = new BanksDTO();

        when(banksApi.callBanks("auth", "client")).thenReturn(banksCall);
        when(banksCall.execute()).thenReturn(Response.success(body));

        BanksServiceImpl service = new BanksServiceImpl(banksApi);

        BanksDTO response = service.banksResponse(new BanksParametersRequest("auth", "client"));

        assertSame(body, response);
        verify(banksApi).callBanks("auth", "client");
    }

    @Test
    void banksResponseShouldReturnNullWhenApiFails() throws Exception {
        when(banksApi.callBanks(anyString(), anyString())).thenReturn(banksCall);
        when(banksCall.execute()).thenThrow(new IOException("error"));

        BanksServiceImpl service = new BanksServiceImpl(banksApi);

        assertNull(service.banksResponse(new BanksParametersRequest("auth", "client")));
    }

    @Test
    void productDirectoryAmountRangeShouldReturnBodyWhenApiSuccess() throws Exception {
        AmountRangeResponse body = new AmountRangeResponse();

        when(productDirectoryAPI.callAmountRange("12", "auth", "client"))
                .thenReturn(amountRangeCall);
        when(amountRangeCall.execute()).thenReturn(Response.success(body));

        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(productDirectoryAPI);

        AmountRangeRequest request = new AmountRangeRequest();
        request.setProductId("12");
        request.setAuthorization("auth");
        request.setxSantanderClientId("client");

        AmountRangeResponse response = service.amountRange(request);

        assertSame(body, response);
        verify(productDirectoryAPI).callAmountRange("12", "auth", "client");
    }

    @Test
    void productDirectoryAmountRangeShouldReturnNullWhenApiFails() throws Exception {
        when(productDirectoryAPI.callAmountRange(anyString(), anyString(), anyString()))
                .thenReturn(amountRangeCall);
        when(amountRangeCall.execute()).thenThrow(new IOException("error"));

        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(productDirectoryAPI);

        AmountRangeRequest request = new AmountRangeRequest();
        request.setProductId("12");
        request.setAuthorization("auth");
        request.setxSantanderClientId("client");

        assertNull(service.amountRange(request));
    }

    @Test
    void termDepositParametersShouldReturnBodyWhenApiSuccess() throws Exception {
        TermDepositParametersResponse body = new TermDepositParametersResponse();

        when(termDepositParametersAPI.callTermDepositParameters("12", "auth", "client"))
                .thenReturn(parametersCall);
        when(parametersCall.execute()).thenReturn(Response.success(body));

        TermDepositParametersServiceImpl service =
                new TermDepositParametersServiceImpl(termDepositParametersAPI);

        TermDepositParametersRequest request = new TermDepositParametersRequest();
        request.setProductId("12");
        request.setAuthorization("auth");
        request.setxSantanderClientId("client");

        TermDepositParametersResponse response = service.termDepositParameters(request);

        assertSame(body, response);
        verify(termDepositParametersAPI).callTermDepositParameters("12", "auth", "client");
    }

    @Test
    void termDepositParametersShouldReturnNullWhenApiFails() throws Exception {
        when(termDepositParametersAPI.callTermDepositParameters(anyString(), anyString(), anyString()))
                .thenReturn(parametersCall);
        when(parametersCall.execute()).thenThrow(new IOException("error"));

        TermDepositParametersServiceImpl service =
                new TermDepositParametersServiceImpl(termDepositParametersAPI);

        TermDepositParametersRequest request = new TermDepositParametersRequest();
        request.setProductId("12");
        request.setAuthorization("auth");
        request.setxSantanderClientId("client");

        assertNull(service.termDepositParameters(request));
    }
}
