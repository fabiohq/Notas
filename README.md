package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositParametersDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        TermDepositParametersDTO dto = new TermDepositParametersDTO();

        dto.setCode("001");
        dto.setContent("CONTENT");
        dto.setDescription("Description");

        assertEquals("001", dto.getCode());
        assertEquals("CONTENT", dto.getContent());
        assertEquals("Description", dto.getDescription());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TermDepositParametersDTO dto =
                new TermDepositParametersDTO("001", "CONTENT", "Description");

        assertEquals("001", dto.getCode());
        assertEquals("CONTENT", dto.getContent());
        assertEquals("Description", dto.getDescription());
    }

    @Test
    void shouldCoverBuilder() {
        TermDepositParametersDTO dto = TermDepositParametersDTO.builder()
                .code("001")
                .content("CONTENT")
                .description("Description")
                .build();

        assertEquals("001", dto.getCode());
        assertEquals("CONTENT", dto.getContent());
        assertEquals("Description", dto.getDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositParametersRequestTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        TermDepositParametersRequest request = new TermDepositParametersRequest();

        request.setProductId("product-001");
        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");

        assertEquals("product-001", request.getProductId());
        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TermDepositParametersRequest request =
                new TermDepositParametersRequest(
                        "product-001",
                        "Bearer token",
                        "client-id"
                );

        assertEquals("product-001", request.getProductId());
        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        TermDepositParametersRequest one = TermDepositParametersRequest.builder()
                .productId("product-001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        TermDepositParametersRequest two = TermDepositParametersRequest.builder()
                .productId("product-001")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertNotEquals(one, null);
        assertNotEquals(one, new Object());

        assertTrue(one.toString().contains("productId"));
        assertTrue(one.toString().contains("authorization"));
        assertTrue(one.toString().contains("xSantanderClientId"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositParametersResponseTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        TermDepositParametersResponse response = new TermDepositParametersResponse();

        List<TermDepositParametersDTO> parameters = Collections.singletonList(
                TermDepositParametersDTO.builder()
                        .code("001")
                        .content("CONTENT")
                        .description("Description")
                        .build()
        );

        response.setParameters(parameters);

        assertEquals(parameters, response.getParameters());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        List<TermDepositParametersDTO> parameters = Collections.singletonList(
                new TermDepositParametersDTO("001", "CONTENT", "Description")
        );

        TermDepositParametersResponse response =
                new TermDepositParametersResponse(parameters);

        assertEquals(parameters, response.getParameters());
    }

    @Test
    void shouldCoverBuilder() {
        List<TermDepositParametersDTO> parameters = Collections.singletonList(
                TermDepositParametersDTO.builder()
                        .code("001")
                        .content("CONTENT")
                        .description("Description")
                        .build()
        );

        TermDepositParametersResponse response =
                TermDepositParametersResponse.builder()
                        .parameters(parameters)
                        .build();

        assertEquals(parameters, response.getParameters());
    }
}
