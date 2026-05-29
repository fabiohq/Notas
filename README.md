

package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration.ApiEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationDataConfigurationTest {

    @Test
    void getByApiShouldReturnApiEntryWhenExists() {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType("sanba");
        apiEntry.setHost("localhost");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(apiEntry));

        ApiEntry result = config.getByApi("sanba");

        assertNotNull(result);
        assertEquals("sanba", result.getIntegrationType());
        assertEquals("localhost", result.getHost());
    }

    @Test
    void getByApiShouldReturnNullWhenNotExists() {
        ApiEntry apiEntry = new ApiEntry();
        apiEntry.setIntegrationType("sanba");

        IntegrationDataConfiguration config = new IntegrationDataConfiguration();
        config.setCatalogue(List.of(apiEntry));

        assertNull(config.getByApi("other"));
    }
}
