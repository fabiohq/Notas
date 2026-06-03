package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RestClientConfigTest {

    @Test
    void shouldCreateAllBeans() {

        RestClientConfig config = new RestClientConfig();

        RestTemplate restTemplate = config.restTemplate();

        assertNotNull(restTemplate);
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class IntegrationDataConfigurationTest {

    @Test
    void shouldCreateIntegrationDataBean() {

        IntegrationDataConfiguration config =
                new IntegrationDataConfiguration();

        ReflectionTestUtils.setField(
                config,
                "application",
                "APP"
        );

        ReflectionTestUtils.setField(
                config,
                "user",
                "USER"
        );

        ReflectionTestUtils.setField(
                config,
                "password",
                "PASSWORD"
        );

        ReflectionTestUtils.setField(
                config,
                "host",
                "HOST"
        );

        Object bean = config.integrationData();

        assertNotNull(bean);
    }
}


