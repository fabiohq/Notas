package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationDataConfigurationTest {

    @Test
    void shouldLoadCatalogueMapAndReturnApiEntry() {
        ApiEntry sanba = new ApiEntry(
                "sanba",
                "localhost",
                "8080",
                false,
                "/",
                10,
                20
        );

        ApiEntry banks = new ApiEntry(
                "banks",
                "localhost",
                "8081",
                true,
                "/",
                10,
                20
        );

        IntegrationDataConfiguration configuration =
                new IntegrationDataConfiguration();

        configuration.setCatalogue(List.of(sanba, banks));

        assertEquals(List.of(sanba, banks), configuration.getCatalogue());

        assertEquals(sanba, configuration.getByApi("sanba"));
        assertEquals(banks, configuration.getByApi("banks"));
        assertNull(configuration.getByApi("not-found"));
    }

    @Test
    void shouldCoverDataMethods() {
        ApiEntry sanba = new ApiEntry(
                "sanba",
                "localhost",
                "8080",
                false,
                "/",
                10,
                20
        );

        IntegrationDataConfiguration one =
                new IntegrationDataConfiguration();

        IntegrationDataConfiguration two =
                new IntegrationDataConfiguration();

        one.setCatalogue(List.of(sanba));
        two.setCatalogue(List.of(sanba));

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("catalogue"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration.ApiEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestClientConfigTest {

    private RestClientConfig config;

    @BeforeEach
    void setUp() {
        IntegrationDataConfiguration properties =
                new IntegrationDataConfiguration();

        properties.setCatalogue(List.of(
                new ApiEntry(
                        "sanba",
                        "localhost",
                        "8080",
                        true,
                        "/",
                        10,
                        20
                ),
                new ApiEntry(
                        "product-directory",
                        "localhost",
                        "8081",
                        true,
                        "/",
                        10,
                        20
                ),
                new ApiEntry(
                        "term-deposit-parameters",
                        "localhost",
                        "8082",
                        true,
                        "/",
                        10,
                        20
                ),
                new ApiEntry(
                        "banks",
                        "localhost",
                        "8083",
                        false,
                        "/",
                        10,
                        20
                )
        ));

        config = new RestClientConfig(properties);
    }

    @Test
    void shouldCreateTrxSanbaApiBean() {
        TrxSanbaAPI api = config.txrTransactionApi();

        assertNotNull(api);
    }

    @Test
    void shouldCreateProductDirectoryApiBean() {
        ProductDirectoryAPI api = config.productDirectoryAPI();

        assertNotNull(api);
    }

    @Test
    void shouldCreateTermDepositParametersApiBean() {
        TermDepositParametersAPI api =
                config.termDepositParametersAPI();

        assertNotNull(api);
    }

    @Test
    void shouldCreateBanksApiBeanUsingHttpBranch() {
        BanksApi api = config.banksAPI();

        assertNotNull(api);
    }
}
