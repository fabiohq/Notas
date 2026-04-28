Test
void shouldReturnDownWhenApiUrlIsInvalid() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);

    ExternalApisHealthProperties.ApiCheck api = new ExternalApisHealthProperties.ApiCheck();
    api.setName("invalid-api");
    api.setUrl("http://invalid-host-test");
    api.setCritical(true);

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.DOWN, health.getStatus());
    assertTrue(health.getDetails().containsKey("invalid-api"));
}
Java
@Test
void shouldReturnUpWhenNoChecksConfigured() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);
    properties.setChecks(List.of());

    ExternalApisHealthIndicator indicator = new ExternalApisHealthIndicator(properties);

    Health health = indicator.health();

    assertEquals(Status.UP, health.getStatus());
    assertTrue(health.getDetails().isEmpty());
}
Java
@Test
void shouldCoverApiResultGettersByReflection() throws Exception {
    Class<?> clazz = Class.forName(
            "com.santander.bnc.bsn049.bncbsn049mscountries.observability.ExternalApisHealthIndicator$ApiResult"
    );

    Constructor<?> constructor = clazz.getDeclaredConstructor(boolean.class, Integer.class, String.class);
    constructor.setAccessible(true);

    Object apiResult = constructor.newInstance(true, 200, "error");

    Method isUp = clazz.getDeclaredMethod("isUp");
    Method getHttpStatus = clazz.getDeclaredMethod("getHttpStatus");
    Method getError = clazz.getDeclaredMethod("getError");

    isUp.setAccessible(true);
    getHttpStatus.setAccessible(true);
    getError.setAccessible(true);

    assertEquals(true, isUp.invoke(apiResult));
    assertEquals(200, getHttpStatus.invoke(apiResult));
    assertEquals("error", getError.invoke(apiResult));
}
Imports que necesitas:
Java
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
>>>>>>>>

private HttpClient httpClient;