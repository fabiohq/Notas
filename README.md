package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiEntryTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        ApiEntry apiEntry = new ApiEntry();

        apiEntry.setIntegrationType("banks");
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setHttps(true);
        apiEntry.setEndpoint("/api/v1");
        apiEntry.setTimeOutConn(10);
        apiEntry.setTimeOutRead(20);

        assertEquals("banks", apiEntry.getIntegrationType());
        assertEquals("localhost", apiEntry.getHost());
        assertEquals("8080", apiEntry.getPort());
        assertTrue(apiEntry.isHttps());
        assertEquals("/api/v1", apiEntry.getEndpoint());
        assertEquals(10, apiEntry.getTimeOutConn());
        assertEquals(20, apiEntry.getTimeOutRead());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        ApiEntry apiEntry = new ApiEntry(
                "banks",
                "localhost",
                "8080",
                false,
                "/api/v1",
                10,
                20
        );

        assertEquals("banks", apiEntry.getIntegrationType());
        assertEquals("localhost", apiEntry.getHost());
        assertEquals("8080", apiEntry.getPort());
        assertFalse(apiEntry.isHttps());
        assertEquals("/api/v1", apiEntry.getEndpoint());
        assertEquals(10, apiEntry.getTimeOutConn());
        assertEquals(20, apiEntry.getTimeOutRead());
    }

    @Test
    void shouldCoverEqualsHashCodeAndToStringFromData() {
        ApiEntry one = new ApiEntry(
                "banks",
                "localhost",
                "8080",
                true,
                "/api/v1",
                10,
                20
        );

        ApiEntry two = new ApiEntry(
                "banks",
                "localhost",
                "8080",
                true,
                "/api/v1",
                10,
                20
        );

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertNotEquals(one, null);
        assertNotEquals(one, new Object());

        assertTrue(one.toString().contains("integrationType"));
        assertTrue(one.toString().contains("host"));
        assertTrue(one.toString().contains("port"));
        assertTrue(one.toString().contains("https"));
        assertTrue(one.toString().contains("endpoint"));
        assertTrue(one.toString().contains("timeOutConn"));
        assertTrue(one.toString().contains("timeOutRead"));
    }
}
