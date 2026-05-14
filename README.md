Aquí tienes los tests para ese bloque, separados por clase.
BanksServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks.BanksParametersRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BanksServiceImplTest {

    @Mock
    BanksApi banksApi;

    @Mock
    Call<BanksDTO> call;

    @Test
    void banksResponseShouldReturnBodyWhenApiCallIsSuccessful() throws Exception {
        BanksServiceImpl service = new BanksServiceImpl(banksApi);
        BanksParametersRequest request = new BanksParametersRequest("Bearer token", "client-id");
        BanksDTO expected = BanksDTO.builder()
                .Banks(List.of(new BanksParametersDTO("0065", "Banco Santander")))
                .build();

        when(banksApi.callBanks("Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        BanksDTO response = service.banksResponse(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void banksResponseShouldReturnNullWhenApiThrowsException() throws Exception {
        BanksServiceImpl service = new BanksServiceImpl(banksApi);
        BanksParametersRequest request = new BanksParametersRequest("Bearer token", "client-id");

        when(banksApi.callBanks("Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("api error"));

        BanksDTO response = service.banksResponse(request);

        assertThat(response).isNull();
    }
}
ProductDirectoryServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory.MaxAndMinAmountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDirectoryServiceImplTest {

    @Mock
    ProductDirectoryAPI productDirectoryAPI;

    @Mock
    Call<AmountRangeResponse> call;

    @Test
    void amountRangeShouldReturnBodyWhenApiCallIsSuccessful() throws Exception {
        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(productDirectoryAPI);
        AmountRangeRequest request = new AmountRangeRequest("Bearer token", "client-id", "04");
        AmountRangeResponse expected = new AmountRangeResponse(
                new MaxAndMinAmountDto("1000,00", "COP"),
                new MaxAndMinAmountDto("9999,00", "COP")
        );

        when(productDirectoryAPI.callAmountRange("04", "Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        AmountRangeResponse response = service.amountRange(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void amountRangeShouldReturnNullWhenApiThrowsException() throws Exception {
        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(productDirectoryAPI);
        AmountRangeRequest request = new AmountRangeRequest("Bearer token", "client-id", "04");

        when(productDirectoryAPI.callAmountRange("04", "Bearer token", "client-id")).thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("api error"));

        AmountRangeResponse response = service.amountRange(request);

        assertThat(response).isNull();
    }
}
TermDepositParametersServiceImplTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters.TermDepositParametersResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermDepositParametersServiceImplTest {

    @Mock
    TermDepositParametersAPI termDepositParametersAPI;

    @Mock
    Call<TermDepositParametersResponse> call;

    @Test
    void termDepositParametersShouldReturnBodyWhenApiCallIsSuccessful() throws Exception {
        TermDepositParametersServiceImpl service = new TermDepositParametersServiceImpl(termDepositParametersAPI);
        TermDepositParametersRequest request = new TermDepositParametersRequest("04", "Bearer token", "client-id");
        TermDepositParametersResponse expected = new TermDepositParametersResponse(
                List.of(new TermDepositParametersDTO("90", "content", "description"))
        );

        when(termDepositParametersAPI.callTermDepositParameters("04", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenReturn(Response.success(expected));

        TermDepositParametersResponse response = service.termDepositParameters(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void termDepositParametersShouldReturnNullWhenApiThrowsException() throws Exception {
        TermDepositParametersServiceImpl service = new TermDepositParametersServiceImpl(termDepositParametersAPI);
        TermDepositParametersRequest request = new TermDepositParametersRequest("04", "Bearer token", "client-id");

        when(termDepositParametersAPI.callTermDepositParameters("04", "Bearer token", "client-id"))
                .thenReturn(call);
        when(call.execute()).thenThrow(new RuntimeException("api error"));

        TermDepositParametersResponse response = service.termDepositParameters(request);

        assertThat(response).isNull();
    }
}
IntegrationDataConfigurationTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.config;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntegrationDataConfigurationTest {

    @Test
    void getByApiShouldReturnApiEntryByIntegrationType() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry("sanba", "localhost", "8080", false, "/service", 1, 1);
        ApiEntry banks = new ApiEntry("banks", "banks-host", "443", true, "/v2/banks/", 1, 1);

        configuration.setCatalogue(List.of(sanba, banks));

        ApiEntry result = configuration.getByApi("banks");

        assertThat(result).isEqualTo(banks);
    }

    @Test
    void getByApiShouldReturnNullWhenIntegrationTypeDoesNotExist() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry("sanba", "localhost", "8080", false, "/service", 1, 1);

        configuration.setCatalogue(List.of(sanba));

        ApiEntry result = configuration.getByApi("unknown");

        assertThat(result).isNull();
    }

    @Test
    void getByApiShouldReuseLoadedCatalogueMap() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry("sanba", "localhost", "8080", false, "/service", 1, 1);

        configuration.setCatalogue(List.of(sanba));

        assertThat(configuration.getByApi("sanba")).isEqualTo(sanba);
        assertThat(configuration.getByApi("sanba")).isEqualTo(sanba);
    }
}
RestClientConfigTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import retrofit2.Retrofit;

import static org.assertj.core.api.Assertions.assertThat;

class RestClientConfigTest {

    @Test
    void buildURLShouldReturnHttpsUrlWhenHttpsIsTrue() {
        IntegrationDataConfiguration properties = new IntegrationDataConfiguration();
        RestClientConfig config = new RestClientConfig(properties);

        ApiEntry apiEntry = new ApiEntry("banks", "localhost", "443", true, "/v2/banks/", 1, 1);

        String result = ReflectionTestUtils.invokeMethod(config, "buildURL", apiEntry);

        assertThat(result).isEqualTo("https://localhost:443/v2/banks/");
    }

    @Test
    void buildURLShouldReturnHttpUrlWhenHttpsIsFalse() {
        IntegrationDataConfiguration properties = new IntegrationDataConfiguration();
        RestClientConfig config = new RestClientConfig(properties);

        ApiEntry apiEntry = new ApiEntry("sanba", "localhost", "8080", false, "/service/", 1, 1);

        String result = ReflectionTestUtils.invokeMethod(config, "buildURL", apiEntry);

        assertThat(result).isEqualTo("http://localhost:8080/service/");
    }

    @Test
    void getRetrofitConfigShouldCreateBuilder() {
        IntegrationDataConfiguration properties = new IntegrationDataConfiguration();
        RestClientConfig config = new RestClientConfig(properties);

        ApiEntry apiEntry = new ApiEntry("sanba", "localhost", "8080", false, "/service/", 1, 1);

        Retrofit.Builder builder = ReflectionTestUtils.invokeMethod(config, "getRetrofitConfig", apiEntry);

        assertThat(builder).isNotNull();
    }

    @Test
    void getObjectMapperShouldReturnConfiguredObjectMapper() {
        IntegrationDataConfiguration properties = new IntegrationDataConfiguration();
        RestClientConfig config = new RestClientConfig(properties);

        ObjectMapper mapper = ReflectionTestUtils.invokeMethod(config, "getObjectMapper", new ObjectMapper());

        assertThat(mapper).isNotNull();
    }
}
Para TrxSanbaServiceImpl, lo ideal es hacerlo separado porque tiene más ramas: success, IOException, RuntimeException, error body JSON válido, error body JSON inválido, “SECUENCIA NO EXISTE” y PEMFV.