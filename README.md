Java
package com.santander.bnc.bsn049.bncbsn049msprospects.config;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationDataConfigurationTest {

    @Test
    void getByApiShouldReturnConfiguredApiEntry() {
        ApiEntry trx = new ApiEntry();
        trx.setIntegrationType("trx_person");
        trx.setHost("localhost");

        ApiEntry parameters = new ApiEntry();
        parameters.setIntegrationType("parameters");
        parameters.setHost("parameters-host");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(trx, parameters));

        ApiEntry result = config.getByApi("parameters");

        assertNotNull(result);
        assertEquals("parameters", result.getIntegrationType());
        assertEquals("parameters-host", result.getHost());
    }

    @Test
    void getByApiShouldReturnNullWhenApiDoesNotExist() {
        ApiEntry trx = new ApiEntry();
        trx.setIntegrationType("trx_person");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(trx));

        assertNull(config.getByApi("context"));
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.config;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.ApiEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestClientConfigTest {

    private RestClientConfig restClientConfig;

    @BeforeEach
    void setUp() {
        IntegrationDataConfiguration integrationConfig = new IntegrationDataConfiguration();

        integrationConfig.setCatalogue(List.of(
                apiEntry("trx_person", "localhost", "8080", false, "/", 5, 5),
                apiEntry("parameters", "localhost", "8081", false, "/", 5, 5),
                apiEntry("context", "localhost", "8082", false, "/", 5, 5)
        ));

        restClientConfig = new RestClientConfig();
        ReflectionTestUtils.setField(restClientConfig, "properties", integrationConfig);
    }

    @Test
    void txrTransactionApiShouldCreateBean() {
        TrxPersonAPI api = restClientConfig.txrTransactionApi();

        assertNotNull(api);
    }

    @Test
    void parametersAPIShouldCreateBean() {
        ParametersAPI api = restClientConfig.parametersAPI();

        assertNotNull(api);
    }

    @Test
    void contextAPIShouldCreateBean() {
        ContextAPI api = restClientConfig.contextAPI();

        assertNotNull(api);
    }

    private ApiEntry apiEntry(
            String integrationType,
            String host,
            String port,
            boolean https,
            String endpoint,
            Integer timeOutConn,
            Integer timeOutRead
    ) {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType(integrationType);
        apiEntry.setHost(host);
        apiEntry.setPort(port);
        apiEntry.setHttps(https);
        apiEntry.setEndpoint(endpoint);
        apiEntry.setTimeOutConn(timeOutConn);
        apiEntry.setTimeOutRead(timeOutRead);
        return apiEntry;
    }
}
Para que compile RestClientConfigTest, asegúrate de tener spring-test en test scope.