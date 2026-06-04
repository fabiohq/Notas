package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.config;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.integration.ApiEntry;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

class ConfigTest {

    @Test
    void integrationDataConfigurationShouldLoadCatalogueMapAndReturnApi() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry sanba = api("sanba", "localhost", "8080", "/sanba", false);
        ApiEntry banks = api("banks", "bank-host", "9090", "/banks", true);

        config.setCatalogue(List.of(sanba, banks));

        assertSame(sanba, config.getByApi("sanba"));
        assertSame(banks, config.getByApi("banks"));
        assertNull(config.getByApi("missing"));

        assertNotNull(config.getCatalogueMap());
        assertEquals(2, config.getCatalogueMap().size());
        assertEquals(List.of(sanba, banks), config.getCatalogue());
    }

    @Test
    void integrationDataConfigurationShouldUseExistingCatalogueMapOnSecondCall() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry sanba = api("sanba", "localhost", "8080", "/sanba", false);
        config.setCatalogue(List.of(sanba));

        assertSame(sanba, config.getByApi("sanba"));

        ApiEntry newSanba = api("sanba", "other-host", "9090", "/other", true);
        config.setCatalogue(List.of(newSanba));

        assertSame(sanba, config.getByApi("sanba"));
    }

    @Test
    void restClientConfigShouldCreateAllApis() {
        IntegrationDataConfiguration properties = new IntegrationDataConfiguration();
        properties.setCatalogue(List.of(
                api("sanba", "localhost", "8080", "/", false),
                api("product-directory", "localhost", "8081", "/", false),
                api("term-deposit-parameters", "localhost", "8082", "/", false),
                api("banks", "localhost", "8083", "/", false)
        ));

        RestClientConfig config = new RestClientConfig(properties);

        assertNotNull(config.txrTransactionApi());
        assertTrue(config.txrTransactionApi() instanceof TrxSanbaAPI);

        assertNotNull(config.productDirectoryAPI());
        assertTrue(config.productDirectoryAPI() instanceof ProductDirectoryAPI);

        assertNotNull(config.termDepositParametersAPI());
        assertTrue(config.termDepositParametersAPI() instanceof TermDepositParametersAPI);

        assertNotNull(config.banksAPI());
        assertTrue(config.banksAPI() instanceof BanksApi);
    }

    @Test
    void buildUrlShouldUseHttpWhenHttpsFalse() throws Exception {
        RestClientConfig config = new RestClientConfig(new IntegrationDataConfiguration());

        Method method = RestClientConfig.class.getDeclaredMethod("buildURL", ApiEntry.class);
        method.setAccessible(true);

        String url = (String) method.invoke(config, api("api", "localhost", "8080", "/test", false));

        assertEquals("http://localhost:8080/test", url);
    }

    @Test
    void buildUrlShouldUseHttpsWhenHttpsTrue() throws Exception {
        RestClientConfig config = new RestClientConfig(new IntegrationDataConfiguration());

        Method method = RestClientConfig.class.getDeclaredMethod("buildURL", ApiEntry.class);
        method.setAccessible(true);

        String url = (String) method.invoke(config, api("api", "localhost", "8443", "/secure", true));

        assertEquals("https://localhost:8443/secure", url);
    }

    @Test
    void getRetrofitConfigShouldCreateBuilder() throws Exception {
        RestClientConfig config = new RestClientConfig(new IntegrationDataConfiguration());

        Method method = RestClientConfig.class.getDeclaredMethod("getRetrofitConfig", ApiEntry.class);
        method.setAccessible(true);

        Object result = method.invoke(config, api("api", "localhost", "8080", "/", false));

        assertNotNull(result);
        assertTrue(result instanceof Retrofit.Builder);
    }

    @Test
    void getHttpClientShouldCreateClient() throws Exception {
        RestClientConfig config = new RestClientConfig(new IntegrationDataConfiguration());

        Method method = RestClientConfig.class.getDeclaredMethod(
                "getHttpClient",
                HttpLoggingInterceptor.Level.class,
                long.class,
                long.class
        );
        method.setAccessible(true);

        Object result = method.invoke(config, HttpLoggingInterceptor.Level.BODY, 5L, 3L);

        assertNotNull(result);
        assertTrue(result instanceof OkHttpClient);
    }

    @Test
    void getObjectMapperShouldConfigureMapper() throws Exception {
        RestClientConfig config = new RestClientConfig(new IntegrationDataConfiguration());

        Method method = RestClientConfig.class.getDeclaredMethod("getObjectMapper", ObjectMapper.class);
        method.setAccessible(true);

        ObjectMapper mapper = (ObjectMapper) method.invoke(config, new ObjectMapper());

        assertNotNull(mapper);
        assertFalse(mapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        assertTrue(mapper.getDeserializationConfig().isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
        assertNotNull(mapper.getDateFormat());
    }

    @Test
    void apiEntryShouldCoverGettersSettersAndConstructors() {
        ApiEntry entry = new ApiEntry();

        entry.setIntegrationType("api");
        entry.setHost("localhost");
        entry.setPort("8080");
        entry.setHttps(true);
        entry.setEndpoint("/endpoint");
        entry.setTimeOutConn(10);
        entry.setTimeOutRead(20);

        assertEquals("api", entry.getIntegrationType());
        assertEquals("localhost", entry.getHost());
        assertEquals("8080", entry.getPort());
        assertTrue(entry.isHttps());
        assertEquals("/endpoint", entry.getEndpoint());
        assertEquals(10, entry.getTimeOutConn());
        assertEquals(20, entry.getTimeOutRead());

        ApiEntry allArgs = new ApiEntry("api2", "host2", "9090", false, "/v2", 30, 40);

        assertEquals("api2", allArgs.getIntegrationType());
        assertEquals("host2", allArgs.getHost());
        assertEquals("9090", allArgs.getPort());
        assertFalse(allArgs.isHttps());
        assertEquals("/v2", allArgs.getEndpoint());
        assertEquals(30, allArgs.getTimeOutConn());
        assertEquals(40, allArgs.getTimeOutRead());
    }

    private ApiEntry api(String type, String host, String port, String endpoint, boolean https) {
        ApiEntry entry = new ApiEntry();
        entry.setIntegrationType(type);
        entry.setHost(host);
        entry.setPort(port);
        entry.setEndpoint(endpoint);
        entry.setHttps(https);
        entry.setTimeOutConn(1);
        entry.setTimeOutRead(1);
        return entry;
    }
}


*****


assertNotNull(ReflectionTestUtils.getField(config, "catalogueMap"));
assertEquals(2, ((java.util.Map<?, ?>) ReflectionTestUtils.getField(config, "catalogueMap")).size());


