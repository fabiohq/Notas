Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextRequestTest {

    @Test
    void shouldSetAndGetAllFields() {

        ContextRequest dto = new ContextRequest();

        dto.setKey("KEY_01");
        dto.setValue("VALUE_01");
        dto.setProduct("cdt");

        assertEquals("KEY_01", dto.getKey());
        assertEquals("VALUE_01", dto.getValue());
        assertEquals("cdt", dto.getProduct());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        ContextRequest dto = new ContextRequest(
                "KEY_02",
                "VALUE_02",
                "loan"
        );

        assertEquals("KEY_02", dto.getKey());
        assertEquals("VALUE_02", dto.getValue());
        assertEquals("loan", dto.getProduct());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextResponseTest {

    @Test
    void shouldSetAndGetAllFields() {

        ContextResponse dto = new ContextResponse();

        dto.setKey("KEY_01");
        dto.setValue("VALUE_01");
        dto.setProduct("cdt");

        assertEquals("KEY_01", dto.getKey());
        assertEquals("VALUE_01", dto.getValue());
        assertEquals("cdt", dto.getProduct());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        ContextResponse dto = new ContextResponse(
                "KEY_02",
                "VALUE_02",
                "loan"
        );

        assertEquals("KEY_02", dto.getKey());
        assertEquals("VALUE_02", dto.getValue());
        assertEquals("loan", dto.getProduct());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiEntryTest {

    @Test
    void shouldSetAndGetAllFields() {

        ApiEntry dto = new ApiEntry();

        dto.setIntegrationType("SANBA");
        dto.setHost("localhost");
        dto.setPort("8080");
        dto.setHttps(true);
        dto.setEndpoint("/api/test");
        dto.setTimeOutConn(5000);
        dto.setTimeOutRead(10000);

        assertEquals("SANBA", dto.getIntegrationType());
        assertEquals("localhost", dto.getHost());
        assertEquals("8080", dto.getPort());
        assertTrue(dto.isHttps());
        assertEquals("/api/test", dto.getEndpoint());
        assertEquals(5000, dto.getTimeOutConn());
        assertEquals(10000, dto.getTimeOutRead());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        ApiEntry dto = new ApiEntry(
                "PARAMS",
                "127.0.0.1",
                "9090",
                false,
                "/params",
                3000,
                6000
        );

        assertEquals("PARAMS", dto.getIntegrationType());
        assertEquals("127.0.0.1", dto.getHost());
        assertEquals("9090", dto.getPort());
        assertFalse(dto.isHttps());
        assertEquals("/params", dto.getEndpoint());
        assertEquals(3000, dto.getTimeOutConn());
        assertEquals(6000, dto.getTimeOutRead());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersTest {

    @Test
    void shouldSetAndGetAllFields() {

        SecurityHeaders dto = new SecurityHeaders();

        dto.setAuthorization("Bearer token");
        dto.setxSantanderClientId("client-001");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client-001", dto.getxSantanderClientId());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        SecurityHeaders dto = new SecurityHeaders(
                "Bearer abc123",
                "client-999"
        );

        assertEquals("Bearer abc123", dto.getAuthorization());
        assertEquals("client-999", dto.getxSantanderClientId());
    }
}