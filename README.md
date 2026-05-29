package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestClientConfigTest {

    @Test
    void txrTransactionApiShouldCreateClientWithHttpsUrl() {
        IntegrationDataConfiguration properties = mock(IntegrationDataConfiguration.class);

        ApiEntry apiEntry = new ApiEntry(
                "sanba",
                "localhost",
                "8080",
                true,
                "/",
                5,
                5
        );

        when(properties.getByApi("sanba")).thenReturn(apiEntry);

        RestClientConfig config = new RestClientConfig(properties);

        TrxSanbaAPI result = config.txrTransactionApi();

        assertNotNull(result);
    }

    @Test
    void txrTransactionApiShouldCreateClientWithHttpUrl() {
        IntegrationDataConfiguration properties = mock(IntegrationDataConfiguration.class);

        ApiEntry apiEntry = new ApiEntry(
                "sanba",
                "localhost",
                "8080",
                false,
                "/",
                5,
                5
        );

        when(properties.getByApi("sanba")).thenReturn(apiEntry);

        RestClientConfig config = new RestClientConfig(properties);

        TrxSanbaAPI result = config.txrTransactionApi();

        assertNotNull(result);
    }
}
