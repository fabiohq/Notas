Aquí tienes los test separados por clase.
ExternalApisHealthPropertiesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldSetAndGetProperties() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("parameters");
        check.setUrl("http://localhost:8080/health");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 204));

        properties.setTimeoutMs(5000);
        properties.setChecks(List.of(check));

        assertEquals(5000, properties.getTimeoutMs());
        assertEquals(1, properties.getChecks().size());

        ExternalApisHealthProperties.ApiCheck result = properties.getChecks().get(0);

        assertEquals("parameters", result.getName());
        assertEquals("http://localhost:8080/health", result.getUrl());
        assertFalse(result.isCritical());
        assertEquals(List.of(200, 204), result.getAcceptedStatuses());
    }

    @Test
    void shouldHaveDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertEquals(2000, properties.getTimeoutMs());
        assertNotNull(properties.getChecks());
        assertTrue(properties.getChecks().isEmpty());
    }
}
ExternalApisHealthIndicatorTest.java
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.observability;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalApisHealthIndicatorTest {

    @Test
    void healthShouldBeUpWhenThereAreNoChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setChecks(List.of());

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().isEmpty());
    }

    @Test
    void healthShouldBeDownWhenCriticalApiFails() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(200);

        ExternalApisHealthProperties.ApiCheck api = new ExternalApisHealthProperties.ApiCheck();
        api.setName("bad-api");
        api.setUrl("http://localhost:1/not-found");
        api.setCritical(true);

        properties.setChecks(List.of(api));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.DOWN, health.getStatus());
        assertTrue(health.getDetails().containsKey("bad-api"));
    }

    @Test
    void healthShouldBeUpWhenNonCriticalApiFails() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(200);

        ExternalApisHealthProperties.ApiCheck api = new ExternalApisHealthProperties.ApiCheck();
        api.setName("optional-api");
        api.setUrl("http://localhost:1/not-found");
        api.setCritical(false);

        properties.setChecks(List.of(api));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertEquals(Status.UP, health.getStatus());
        assertTrue(health.getDetails().containsKey("optional-api"));
    }
}
Este enfoque no acopla con servidores externos reales; usa un puerto inválido para forzar error controlado.