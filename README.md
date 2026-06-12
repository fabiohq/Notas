package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.config.StandardType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParamsConfigTest {

    @Test
    @DisplayName("Debe cubrir getters y setters")
    void gettersAndSettersShouldWork() {
        ParamsConfig config = new ParamsConfig();

        StandardType periodicity = StandardType.builder()
                .code("V")
                .description("Vencimiento")
                .content("Contenido")
                .build();

        StandardType proposal = StandardType.builder()
                .code("01")
                .description("Propuesta")
                .content("Contenido")
                .build();

        config.setAppName("app");
        config.setAppVersion("v1");
        config.setDefaultProductId("010001");
        config.setProposalProductId("020001");
        config.setDefaultAmount("100000");
        config.setDefaultPeriodType("V");
        config.setDefaultPeriodicity("90");
        config.setDefaultCurrency("COP");
        config.setServiceRouteBp17("route");
        config.setPeriodicity(List.of(periodicity));
        config.setProposal(List.of(proposal));

        assertEquals("app", config.getAppName());
        assertEquals("v1", config.getAppVersion());
        assertEquals("010001", config.getDefaultProductId());
        assertEquals("020001", config.getProposalProductId());
        assertEquals("100000", config.getDefaultAmount());
        assertEquals("V", config.getDefaultPeriodType());
        assertEquals("90", config.getDefaultPeriodicity());
        assertEquals("COP", config.getDefaultCurrency());
        assertEquals("route", config.getServiceRouteBp17());
        assertEquals(1, config.getPeriodicity().size());
        assertEquals(1, config.getProposal().size());
        assertSame(periodicity, config.getPeriodicity().get(0));
        assertSame(proposal, config.getProposal().get(0));
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.integration.ApiEntry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntegrationDataConfigurationTest {

    @Test
    @DisplayName("Debe retornar ApiEntry por integrationType")
    void getByApiShouldReturnApiEntryByIntegrationType() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry();
        sanba.setIntegrationType("sanba");
        sanba.setHost("localhost");
        sanba.setPort("8080");
        sanba.setHttps(false);
        sanba.setEndpoint("/api/");
        sanba.setTimeOutConn(10);
        sanba.setTimeOutRead(20);

        config.setCatalogue(List.of(sanba));

        ApiEntry result = config.getByApi("sanba");

        assertSame(sanba, result);
        assertNotNull(config.getCatalogueMap());
        assertEquals(1, config.getCatalogueMap().size());
    }

    @Test
    @DisplayName("Debe retornar null cuando integrationType no existe")
    void getByApiShouldReturnNullWhenIntegrationTypeDoesNotExist() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry sanba = new ApiEntry();
        sanba.setIntegrationType("sanba");

        config.setCatalogue(List.of(sanba));

        ApiEntry result = config.getByApi("other");

        assertNull(result);
    }

    @Test
    @DisplayName("Debe usar catalogueMap existente si ya fue seteado")
    void getByApiShouldUseExistingCatalogueMap() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry api = new ApiEntry();
        api.setIntegrationType("custom");

        config.setCatalogueMap(Map.of("custom", api));

        ApiEntry result = config.getByApi("custom");

        assertSame(api, result);
    }

    @Test
    @DisplayName("Debe cubrir getters y setters")
    void gettersAndSettersShouldWork() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        ApiEntry api = new ApiEntry();
        api.setIntegrationType("sanba");

        List<ApiEntry> catalogue = List.of(api);
        Map<String, ApiEntry> catalogueMap = Map.of("sanba", api);

        config.setCatalogue(catalogue);
        config.setCatalogueMap(catalogueMap);

        assertSame(catalogue, config.getCatalogue());
        assertSame(catalogueMap, config.getCatalogueMap());
    }

    @Test
    @DisplayName("Debe lanzar NullPointerException cuando catalogue es null y catalogueMap es null")
    void getByApiShouldThrowWhenCatalogueIsNull() {
        IntegrationDataConfiguration config = new IntegrationDataConfiguration();

        assertThrows(
                NullPointerException.class,
                () -> config.getByApi("sanba")
        );
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.integration.ApiEntry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestClientConfigTest {

    @Test
    @DisplayName("Debe crear bean TrxSanbaAPI con configuración HTTP")
    void txrTransactionApiShouldCreateBeanWithHttpConfiguration() {
        IntegrationDataConfiguration integrationDataConfiguration =
                mock(IntegrationDataConfiguration.class);

        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType("sanba");
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setHttps(false);
        apiEntry.setEndpoint("/");
        apiEntry.setTimeOutRead(5);
        apiEntry.setTimeOutConn(5);

        when(integrationDataConfiguration.getByApi("sanba"))
                .thenReturn(apiEntry);

        RestClientConfig config = new RestClientConfig(integrationDataConfiguration);

        TrxSanbaAPI api = config.txrTransactionApi();

        assertNotNull(api);
        verify(integrationDataConfiguration).getByApi("sanba");
    }

    @Test
    @DisplayName("Debe crear bean TrxSanbaAPI con configuración HTTPS")
    void txrTransactionApiShouldCreateBeanWithHttpsConfiguration() {
        IntegrationDataConfiguration integrationDataConfiguration =
                mock(IntegrationDataConfiguration.class);

        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType("sanba");
        apiEntry.setHost("localhost");
        apiEntry.setPort("443");
        apiEntry.setHttps(true);
        apiEntry.setEndpoint("/");
        apiEntry.setTimeOutRead(5);
        apiEntry.setTimeOutConn(5);

        when(integrationDataConfiguration.getByApi("sanba"))
                .thenReturn(apiEntry);

        RestClientConfig config = new RestClientConfig(integrationDataConfiguration);

        TrxSanbaAPI api = config.txrTransactionApi();

        assertNotNull(api);
        verify(integrationDataConfiguration).getByApi("sanba");
    }

    @Test
    @DisplayName("Debe lanzar NullPointerException cuando ApiEntry es null")
    void txrTransactionApiShouldThrowWhenApiEntryIsNull() {
        IntegrationDataConfiguration integrationDataConfiguration =
                mock(IntegrationDataConfiguration.class);

        when(integrationDataConfiguration.getByApi("sanba"))
                .thenReturn(null);

        RestClientConfig config = new RestClientConfig(integrationDataConfiguration);

        assertThrows(
                NullPointerException.class,
                config::txrTransactionApi
        );
    }
}



