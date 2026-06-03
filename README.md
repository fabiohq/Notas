package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry;

class IntegrationDataConfigurationTest {

    @Test
    void shouldReturnApiEntryByIntegrationTypeAndReuseMap() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry sanba = api("sanba", true);
        ApiEntry banks = api("banks", false);

        config.setCatalogue(List.of(sanba, banks));

        assertSame(sanba, config.getByApi("sanba"));
        assertSame(banks, config.getByApi("banks"));
        assertNull(config.getByApi("other"));

        assertNotNull(ReflectionTestUtils.getField(config, "catalogueMap"));
    }

    private ApiEntry api(String type, boolean https) {
        ApiEntry api = new ApiEntry();
        api.setIntegrationType(type);
        api.setHttps(https);
        api.setHost("localhost");
        api.setPort("8080");
        api.setEndpoint("/");
        api.setTimeOutRead(1L);
        api.setTimeOutConn(1L);
        return api;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

class RestClientConfigTest {

    private IntegrationDataConfiguration properties;
    private RestClientConfig config;

    @BeforeEach
    void setUp() {
        properties = mock(IntegrationDataConfiguration.class);
        config = new RestClientConfig(properties);

        when(properties.getByApi("sanba")).thenReturn(api("sanba", true));
        when(properties.getByApi("product-directory")).thenReturn(api("product-directory", true));
        when(properties.getByApi("term-deposit-parameters")).thenReturn(api("term-deposit-parameters", true));
        when(properties.getByApi("banks")).thenReturn(api("banks", true));
    }

    @Test
    void shouldCreateApiBeans() {
        assertNotNull(config.txrTransactionApi());
        assertNotNull(config.productDirectoryAPI());
        assertNotNull(config.termDepositParametersAPI());
        assertNotNull(config.banksAPI());
    }

    @Test
    void shouldCoverPrivateBuildUrlHttpsAndHttp() throws Exception {
        assertEquals("https://localhost:8080/api/",
                invoke("buildURL", new Class<?>[] { ApiEntry.class }, api("sanba", true)));

        assertEquals("http://localhost:8080/api/",
                invoke("buildURL", new Class<?>[] { ApiEntry.class }, api("sanba", false)));
    }

    @Test
    void shouldCoverPrivateHttpClientObjectMapperAndRetrofit() throws Exception {
        OkHttpClient client = invoke(
                "getHttpClient",
                new Class<?>[] { HttpLoggingInterceptor.Level.class, long.class, long.class },
                HttpLoggingInterceptor.Level.BODY,
                1L,
                1L
        );

        assertNotNull(client);

        ObjectMapper mapper = invoke(
                "getObjectMapper",
                new Class<?>[] { ObjectMapper.class },
                new ObjectMapper()
        );

        assertNotNull(mapper);

        Retrofit.Builder builder = invoke(
                "getRetrofitConfig",
                new Class<?>[] { ApiEntry.class },
                api("sanba", true)
        );

        assertNotNull(builder);
    }

    private ApiEntry api(String type, boolean https) {
        ApiEntry api = new ApiEntry();
        api.setIntegrationType(type);
        api.setHttps(https);
        api.setHost("localhost");
        api.setPort("8080");
        api.setEndpoint("/api/");
        api.setTimeOutRead(1L);
        api.setTimeOutConn(1L);
        return api;
    }

    @SuppressWarnings("unchecked")
    private <T> T invoke(String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
        Method method = RestClientConfig.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return (T) method.invoke(config, args);
    }
}
