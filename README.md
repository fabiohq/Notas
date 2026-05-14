package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.observability;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalApisHealthIndicatorTest {

    @Test
    void healthShouldBeUpWhenThereAreNoChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setChecks(List.of());

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).isEmpty();
    }

    @Test
    void healthShouldBeUpWhenNonCriticalApiIsDown() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("non-critical-api");
        check.setUrl("http://localhost:1/health");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200));

        properties.setChecks(List.of(check));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).containsKey("non-critical-api");

        Map<String, Object> detail =
                (Map<String, Object>) health.getDetails().get("non-critical-api");

        assertThat(detail.get("status")).isEqualTo("DOWN");
        assertThat(detail.get("critical")).isEqualTo(false);
        assertThat(detail.get("url")).isEqualTo("http://localhost:1/health");
        assertThat(detail).containsKey("error");
    }

    @Test
    void healthShouldBeDownWhenCriticalApiIsDown() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("critical-api");
        check.setUrl("http://localhost:1/health");
        check.setCritical(true);
        check.setAcceptedStatuses(List.of(200));

        properties.setChecks(List.of(check));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
        assertThat(health.getDetails()).containsKey("critical-api");

        Map<String, Object> detail =
                (Map<String, Object>) health.getDetails().get("critical-api");

        assertThat(detail.get("status")).isEqualTo("DOWN");
        assertThat(detail.get("critical")).isEqualTo(true);
        assertThat(detail).containsKey("error");
    }

    @Test
    void healthShouldBeDownWhenOneCriticalApiIsDownEvenIfAnotherIsNonCritical() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        properties.setTimeoutMs(100);

        ExternalApisHealthProperties.ApiCheck critical = new ExternalApisHealthProperties.ApiCheck();
        critical.setName("critical-api");
        critical.setUrl("http://localhost:1/health");
        critical.setCritical(true);

        ExternalApisHealthProperties.ApiCheck nonCritical = new ExternalApisHealthProperties.ApiCheck();
        nonCritical.setName("non-critical-api");
        nonCritical.setUrl("http://localhost:1/health");
        nonCritical.setCritical(false);

        properties.setChecks(List.of(critical, nonCritical));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
        assertThat(health.getDetails()).containsKeys("critical-api", "non-critical-api");
    }

    @Test
    void healthShouldHandleInvalidUrlAsDown() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
        check.setName("invalid-api");
        check.setUrl("http://");
        check.setCritical(true);

        properties.setChecks(List.of(check));

        ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

        Health health = indicator.health();

        assertThat(health.getStatus()).isEqualTo(Status.DOWN);
        assertThat(health.getDetails()).containsKey("invalid-api");

        Map<String, Object> detail =
                (Map<String, Object>) health.getDetails().get("invalid-api");

        assertThat(detail.get("status")).isEqualTo("DOWN");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.observability;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalApisHealthPropertiesTest {

    @Test
    void shouldHaveDefaultValues() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        assertThat(properties.getTimeoutMs()).isEqualTo(2000);
        assertThat(properties.getChecks()).isEmpty();
    }

    @Test
    void shouldSetAndGetTimeoutMs() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

        properties.setTimeoutMs(5000);

        assertThat(properties.getTimeoutMs()).isEqualTo(5000);
    }

    @Test
    void shouldSetAndGetChecks() {
        ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

        check.setName("backend-for-frontend");
        check.setUrl("http://localhost:8080/actuator/health");
        check.setCritical(false);
        check.setAcceptedStatuses(List.of(200, 404));

        properties.setChecks(List.of(check));

        assertThat(properties.getChecks()).hasSize(1);
        assertThat(properties.getChecks().get(0).getName()).isEqualTo("backend-for-frontend");
        assertThat(properties.getChecks().get(0).getUrl()).isEqualTo("http://localhost:8080/actuator/health");
        assertThat(properties.getChecks().get(0).isCritical()).isFalse();
        assertThat(properties.getChecks().get(0).getAcceptedStatuses()).containsExactly(200, 404);
    }

    @Test
    void apiCheckShouldBeCriticalByDefault() {
        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

        assertThat(check.isCritical()).isTrue();
    }

    @Test
    void apiCheckShouldAllowNullAcceptedStatuses() {
        ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

        check.setAcceptedStatuses(null);

        assertThat(check.getAcceptedStatuses()).isNull();
    }
}