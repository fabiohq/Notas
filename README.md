package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.config;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration.ApiEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RestClientConfigTest {

    private RestClientConfig config;

    private IntegrationDataConfiguration properties;

    @BeforeEach
    void setUp() {

        properties = mock(IntegrationDataConfiguration.class);

        config = new RestClientConfig(properties);

        when(properties.getByApi("sanba"))
                .thenReturn(buildApiEntry());

        when(properties.getByApi("product-directory"))
                .thenReturn(buildApiEntry());

        when(properties.getByApi("term-deposit-parameters"))
                .thenReturn(buildApiEntry());

        when(properties.getByApi("banks"))
                .thenReturn(buildApiEntry());
    }

    @Test
    void txrTransactionApiShouldCreateApi() {

        TrxSanbaAPI api = config.txrTransactionApi();

        assertThat(api).isNotNull();
    }

    @Test
    void productDirectoryAPIShouldCreateApi() {

        ProductDirectoryAPI api = config.productDirectoryAPI();

        assertThat(api).isNotNull();
    }

    @Test
    void termDepositParametersAPIShouldCreateApi() {

        TermDepositParametersAPI api = config.termDepositParametersAPI();

        assertThat(api).isNotNull();
    }

    @Test
    void banksAPIShouldCreateApi() {

        BanksApi api = config.banksAPI();

        assertThat(api).isNotNull();
    }

    @Test
    void lambdaLoggingShouldExecute() throws Exception {

        Method method = RestClientConfig.class.getDeclaredMethod(
                "getHttpClient",
                okhttp3.logging.HttpLoggingInterceptor.Level.class,
                long.class,
                long.class
        );

        method.setAccessible(true);

        Object client = method.invoke(
                config,
                okhttp3.logging.HttpLoggingInterceptor.Level.BODY,
                1L,
                1L
        );

        assertThat(client).isNotNull();
    }

    private ApiEntry buildApiEntry() {

        ApiEntry entry = new ApiEntry();

        entry.setIntegrationType("test");
        entry.setHost("localhost");
        entry.setPort("8080");
        entry.setHttps(false);
        entry.setEndpoint("/");
        entry.setTimeOutConn(1);
        entry.setTimeOutRead(1);

        return entry;
    }
}