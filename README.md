package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {
        AmountDTO dto = new AmountDTO();

        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        AmountDTO dto = new AmountDTO("1000", "COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverBuilder() {
        AmountDTO dto = AmountDTO.builder()
                .amount("1000")
                .currency("COP")
                .build();

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceResultDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        BalanceResultDTO dto = new BalanceResultDTO();

        AmountDTO amount = AmountDTO.builder()
                .amount("1000")
                .currency("COP")
                .build();

        dto.setAmount(amount);
        dto.setCreditDebitIndicator("CRDT");

        assertEquals(amount, dto.getAmount());
        assertEquals("CRDT", dto.getCreditDebitIndicator());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        AmountDTO amount = new AmountDTO("1000", "COP");

        BalanceResultDTO dto =
                new BalanceResultDTO(amount, "CRDT");

        assertEquals(amount, dto.getAmount());
        assertEquals("CRDT", dto.getCreditDebitIndicator());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        AmountDTO amount = new AmountDTO("1000", "COP");

        BalanceResultDTO one = BalanceResultDTO.builder()
                .amount(amount)
                .creditDebitIndicator("CRDT")
                .build();

        BalanceResultDTO two = BalanceResultDTO.builder()
                .amount(amount)
                .creditDebitIndicator("CRDT")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("creditDebitIndicator"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTypeDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        BalanceTypeDTO dto = new BalanceTypeDTO();

        dto.setTypeCode("01");
        dto.setTypeDescription("CAPITAL");

        assertEquals("01", dto.getTypeCode());
        assertEquals("CAPITAL", dto.getTypeDescription());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        BalanceTypeDTO dto =
                new BalanceTypeDTO("01", "CAPITAL");

        assertEquals("01", dto.getTypeCode());
        assertEquals("CAPITAL", dto.getTypeDescription());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        BalanceTypeDTO one = BalanceTypeDTO.builder()
                .typeCode("01")
                .typeDescription("CAPITAL")
                .build();

        BalanceTypeDTO two = BalanceTypeDTO.builder()
                .typeCode("01")
                .typeDescription("CAPITAL")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("typeCode"));
        assertTrue(one.toString().contains("typeDescription"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HrefDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        HrefDTO dto = new HrefDTO();

        dto.setHref("/transactions");

        assertEquals("/transactions", dto.getHref());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        HrefDTO dto = new HrefDTO("/transactions");

        assertEquals("/transactions", dto.getHref());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        HrefDTO one = HrefDTO.builder()
                .href("/transactions")
                .build();

        HrefDTO two = HrefDTO.builder()
                .href("/transactions")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("href"));
    }
}
