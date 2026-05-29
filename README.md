@Test
void apiEntryShouldSetAndGetMissingFields() {
    ApiEntry apiEntry = new ApiEntry();

    apiEntry.setPort("8080");
    apiEntry.setEndpoint("/api");
    apiEntry.setTimeOutConn(30);
    apiEntry.setTimeOutRead(60);

    assertEquals("8080", apiEntry.getPort());
    assertEquals("/api", apiEntry.getEndpoint());
    assertEquals(30, apiEntry.getTimeOutConn());
    assertEquals(60, apiEntry.getTimeOutRead());
}

@Test
void apiEntryAllArgsConstructorShouldSetAllFields() {
    ApiEntry apiEntry = new ApiEntry(
            "sanba",
            "localhost",
            "8080",
            true,
            "/api",
            30,
            60
    );

    assertEquals("sanba", apiEntry.getIntegrationType());
    assertEquals("localhost", apiEntry.getHost());
    assertEquals("8080", apiEntry.getPort());
    assertTrue(apiEntry.isHttps());
    assertEquals("/api", apiEntry.getEndpoint());
    assertEquals(30, apiEntry.getTimeOutConn());
    assertEquals(60, apiEntry.getTimeOutRead());
}
