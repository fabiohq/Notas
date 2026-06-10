package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountRangeRequestTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        AmountRangeRequest request = new AmountRangeRequest();

        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");
        request.setProductId("001");

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
        assertEquals("001", request.getProductId());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        AmountRangeRequest request = new AmountRangeRequest(
                "Bearer token",
                "client-id",
                "001"
        );

        assertEquals("Bearer token", request.getAuthorization());
        assertEquals("client-id", request.getXSantanderClientId());
        assertEquals("001", request.getProductId());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {
        AmountRangeRequest one = AmountRangeRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .productId("001")
                .build();

        AmountRangeRequest two = AmountRangeRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .productId("001")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertNotEquals(one, null);
        assertNotEquals(one, new Object());

        assertTrue(one.toString().contains("authorization"));
        assertTrue(one.toString().contains("xSantanderClientId"));
        assertTrue(one.toString().contains("productId"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAndMinAmountDtoTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        MaxAndMinAmountDto dto = new MaxAndMinAmountDto();

        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        MaxAndMinAmountDto dto =
                new MaxAndMinAmountDto("1000", "COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverBuilder() {
        MaxAndMinAmountDto dto = MaxAndMinAmountDto.builder()
                .amount("1000")
                .currency("COP")
                .build();

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountRangeResponseTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        AmountRangeResponse response = new AmountRangeResponse();

        MaxAndMinAmountDto minimum =
                MaxAndMinAmountDto.builder()
                        .amount("1000")
                        .currency("COP")
                        .build();

        MaxAndMinAmountDto maximum =
                MaxAndMinAmountDto.builder()
                        .amount("50000")
                        .currency("COP")
                        .build();

        response.setMinimumAmount(minimum);
        response.setMaximumAmount(maximum);

        assertEquals(minimum, response.getMinimumAmount());
        assertEquals(maximum, response.getMaximumAmount());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        MaxAndMinAmountDto minimum =
                new MaxAndMinAmountDto("1000", "COP");

        MaxAndMinAmountDto maximum =
                new MaxAndMinAmountDto("50000", "COP");

        AmountRangeResponse response =
                new AmountRangeResponse(minimum, maximum);

        assertEquals(minimum, response.getMinimumAmount());
        assertEquals(maximum, response.getMaximumAmount());
    }

    @Test
    void shouldCoverBuilder() {

        MaxAndMinAmountDto minimum =
                MaxAndMinAmountDto.builder()
                        .amount("1000")
                        .currency("COP")
                        .build();

        MaxAndMinAmountDto maximum =
                MaxAndMinAmountDto.builder()
                        .amount("50000")
                        .currency("COP")
                        .build();

        AmountRangeResponse response =
                AmountRangeResponse.builder()
                        .minimumAmount(minimum)
                        .maximumAmount(maximum)
                        .build();

        assertEquals(minimum, response.getMinimumAmount());
        assertEquals(maximum, response.getMaximumAmount());
    }
}
