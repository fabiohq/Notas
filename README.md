package com.santander.bnc.bsn049.bncbsn049mscontracts.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.integration.ApiEntry;

class IntegrationDataConfigurationTest {

    @Test
    void shouldReturnApiEntryByIntegrationType() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry();
        sanba.setIntegrationType("sanba");
        sanba.setHost("localhost");
        sanba.setPort(8080);
        sanba.setEndpoint("/service-engine/procesar/");
        sanba.setHttps(false);
        sanba.setTimeOutConn(10);
        sanba.setTimeOutRead(20);

        ApiEntry banks = new ApiEntry();
        banks.setIntegrationType("banks");
        banks.setHost("localhost");
        banks.setPort(8081);
        banks.setEndpoint("/v2/banks/");
        banks.setHttps(true);
        banks.setTimeOutConn(11);
        banks.setTimeOutRead(21);

        configuration.setCatalogue(List.of(sanba, banks));

        ApiEntry result = configuration.getByApi("sanba");

        assertEquals("sanba", result.getIntegrationType());
        assertEquals("localhost", result.getHost());
        assertEquals(8080, result.getPort());
        assertEquals("/service-engine/procesar/", result.getEndpoint());
        assertEquals(false, result.isHttps());
        assertEquals(10, result.getTimeOutConn());
        assertEquals(20, result.getTimeOutRead());
    }

    @Test
    void shouldReturnNullWhenIntegrationTypeDoesNotExist() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry();
        sanba.setIntegrationType("sanba");

        configuration.setCatalogue(List.of(sanba));

        ApiEntry result = configuration.getByApi("unknown");

        assertNull(result);
    }

    @Test
    void shouldReuseLoadedCatalogueMap() {
        IntegrationDataConfiguration configuration = new IntegrationDataConfiguration();

        ApiEntry banks = new ApiEntry();
        banks.setIntegrationType("banks");
        banks.setHost("host");
        banks.setPort(8080);
        banks.setEndpoint("/endpoint");
        banks.setHttps(false);

        configuration.setCatalogue(List.of(banks));

        ApiEntry first = configuration.getByApi("banks");
        ApiEntry second = configuration.getByApi("banks");

        assertEquals("banks", first.getIntegrationType());
        assertEquals("banks", second.getIntegrationType());
        assertEquals(first, second);
    }
}



*****?**?*******
package com.santander.bnc.bsn049.bncbsn049mscontracts.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.integration.ApiEntry;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

class RestClientConfigTest {

    private IntegrationDataConfiguration configuration;
    private RestClientConfig restClientConfig;

    @BeforeEach
    void setUp() {
        configuration = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry();
        sanba.setIntegrationType("sanba");
        sanba.setHost("localhost");
        sanba.setPort(8080);
        sanba.setEndpoint("/service-engine/procesar/");
        sanba.setHttps(false);
        sanba.setTimeOutConn(10);
        sanba.setTimeOutRead(20);

        ApiEntry banks = new ApiEntry();
        banks.setIntegrationType("banks");
        banks.setHost("localhost");
        banks.setPort(8081);
        banks.setEndpoint("/v2/banks/");
        banks.setHttps(true);
        banks.setTimeOutConn(11);
        banks.setTimeOutRead(21);

        configuration.setCatalogue(List.of(sanba, banks));
        restClientConfig = new RestClientConfig(configuration);
    }

    @Test
    void shouldCreateTrxSanbaApiBean() {
        TrxSanbaAPI api = restClientConfig.txrTransactionApi();
        assertNotNull(api);
    }

    @Test
    void shouldCreateBanksApiBean() {
        BanksApi api = restClientConfig.banksAPI();
        assertNotNull(api);
    }

    @Test
    void shouldBuildHttpUrlWithHttpWhenHttpsIsFalse() throws Exception {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setHost("localhost");
        apiEntry.setPort(8080);
        apiEntry.setEndpoint("/service-engine/procesar/");
        apiEntry.setHttps(false);

        Method method = RestClientConfig.class.getDeclaredMethod("buildURL", ApiEntry.class);
        method.setAccessible(true);

        String url = (String) method.invoke(restClientConfig, apiEntry);

        assertEquals("http://localhost:8080/service-engine/procesar/", url);
    }

    @Test
    void shouldBuildHttpUrlWithHttpsWhenHttpsIsTrue() throws Exception {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setHost("example.com");
        apiEntry.setPort(443);
        apiEntry.setEndpoint("/v2/banks/");
        apiEntry.setHttps(true);

        Method method = RestClientConfig.class.getDeclaredMethod("buildURL", ApiEntry.class);
        method.setAccessible(true);

        String url = (String) method.invoke(restClientConfig, apiEntry);

        assertEquals("https://example.com:443/v2/banks/", url);
    }

    @Test
    void shouldBuildRetrofitConfig() throws Exception {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setHost("localhost");
        apiEntry.setPort(8080);
        apiEntry.setEndpoint("/service-engine/procesar/");
        apiEntry.setHttps(false);
        apiEntry.setTimeOutConn(5);
        apiEntry.setTimeOutRead(7);

        Method method = RestClientConfig.class.getDeclaredMethod("getRetrofitConfig", ApiEntry.class);
        method.setAccessible(true);

        Retrofit.Builder builder = (Retrofit.Builder) method.invoke(restClientConfig, apiEntry);

        assertNotNull(builder);
    }

    @Test
    void shouldBuildHttpClient() throws Exception {
        Method method = RestClientConfig.class.getDeclaredMethod(
                "getHttpClient",
                HttpLoggingInterceptor.Level.class,
                long.class,
                long.class
        );
        method.setAccessible(true);

        OkHttpClient client = (OkHttpClient) method.invoke(
                restClientConfig,
                HttpLoggingInterceptor.Level.BODY,
                5L,
                7L
        );

        assertNotNull(client);
        assertEquals(7_000, client.readTimeoutMillis());
        assertEquals(5_000, client.connectTimeoutMillis());
        assertTrue(client.interceptors().stream()
                .anyMatch(HttpLoggingInterceptor.class::isInstance));
    }

    @Test
    void shouldConfigureObjectMapper() throws Exception {
        Method method = RestClientConfig.class.getDeclaredMethod("getObjectMapper", ObjectMapper.class);
        method.setAccessible(true);

        ObjectMapper mapper = (ObjectMapper) method.invoke(restClientConfig, new ObjectMapper());

        assertNotNull(mapper);
        assertTrue(mapper.isEnabled(com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
        assertTrue(mapper.getRegisteredModuleIds().stream()
                .anyMatch(id -> id.toString().contains("jackson-datatype-jsr310")
                        || id.toString().contains("JavaTimeModule")));
    }
}