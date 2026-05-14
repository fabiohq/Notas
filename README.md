@Test
void healthShouldContainHttpStatusWhenApiReturnsResponse() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    properties.setTimeoutMs(1);

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("google");
    api.setUrl("https://www.google.com");
    api.setCritical(false);

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    assertThat(result.getDetails())
            .containsKey("google");

    Map<String, Object> detail =
            (Map<String, Object>) result.getDetails().get("google");

    assertThat(detail)
            .containsKeys("status", "url", "critical");
}

@Test
void healthShouldAddErrorFieldWhenConnectionFails() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    properties.setTimeoutMs(1);

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("failing-api");
    api.setUrl("http://localhost:9999");
    api.setCritical(true);

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    Map<String, Object> detail =
            (Map<String, Object>) result.getDetails().get("failing-api");

    assertThat(detail)
            .containsKey("error");
}

@Test
void healthShouldReturnDownWhenAcceptedStatusDoesNotMatch() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    properties.setTimeoutMs(1);

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("api");
    api.setUrl("http://localhost:9999");
    api.setCritical(true);
    api.setAcceptedStatuses(List.of(201));

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    assertThat(result.getStatus())
            .isEqualTo(Status.DOWN);
}

@Test
void apiCheckShouldSetAndGetName() {

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("backend");

    assertThat(api.getName())
            .isEqualTo("backend");
}

@Test
void apiCheckShouldSetAndGetUrl() {

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setUrl("http://localhost");

    assertThat(api.getUrl())
            .isEqualTo("http://localhost");
}

@Test
void apiCheckShouldSetAndGetCritical() {

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setCritical(false);

    assertThat(api.isCritical())
            .isFalse();
}

@Test
void apiCheckShouldSetAndGetAcceptedStatuses() {

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setAcceptedStatuses(List.of(200, 202));

    assertThat(api.getAcceptedStatuses())
            .containsExactly(200, 202);
}

@Test
void propertiesShouldSetAndGetChecks() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("api1");

    properties.setChecks(List.of(api));

    assertThat(properties.getChecks())
            .hasSize(1);

    assertThat(properties.getChecks().get(0).getName())
            .isEqualTo("api1");
}

@Test
void propertiesShouldSetAndGetTimeoutMs() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    properties.setTimeoutMs(5000);

    assertThat(properties.getTimeoutMs())
            .isEqualTo(5000);
}

@Test
void healthShouldContainCriticalFlag() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("api");
    api.setUrl("http://localhost:9999");
    api.setCritical(true);

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    Map<String, Object> detail =
            (Map<String, Object>) result.getDetails().get("api");

    assertThat(detail.get("critical"))
            .isEqualTo(true);
}

@Test
void healthShouldContainUrlField() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("api");
    api.setUrl("http://localhost:9999");

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    Map<String, Object> detail =
            (Map<String, Object>) result.getDetails().get("api");

    assertThat(detail.get("url"))
            .isEqualTo("http://localhost:9999");
}

@Test
void healthShouldMarkStatusDownWhenIOExceptionOccurs() {

    ExternalApisHealthProperties properties =
            new ExternalApisHealthProperties();

    ExternalApisHealthProperties.ApiCheck api =
            new ExternalApisHealthProperties.ApiCheck();

    api.setName("io-api");
    api.setUrl("http://localhost:9999");
    api.setCritical(true);

    properties.setChecks(List.of(api));

    ExternalApisHealthIndicator indicator =
            new ExternalApisHealthIndicator(properties);

    Health result = indicator.health();

    Map<String, Object> detail =
            (Map<String, Object>) result.getDetails().get("io-api");

    assertThat(detail.get("status"))
            .isEqualTo("DOWN");
}