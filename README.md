package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinksDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        LinksDTO dto = new LinksDTO();

        HrefDTO first = new HrefDTO("/first");
        HrefDTO prev = new HrefDTO("/prev");
        HrefDTO next = new HrefDTO("/next");

        dto.setFirst(first);
        dto.setPrev(prev);
        dto.setNext(next);

        assertEquals(first, dto.getFirst());
        assertEquals(prev, dto.getPrev());
        assertEquals(next, dto.getNext());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        HrefDTO first = new HrefDTO("/first");
        HrefDTO prev = new HrefDTO("/prev");
        HrefDTO next = new HrefDTO("/next");

        LinksDTO dto = new LinksDTO(first, prev, next);

        assertEquals(first, dto.getFirst());
        assertEquals(prev, dto.getPrev());
        assertEquals(next, dto.getNext());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        LinksDTO one = LinksDTO.builder()
                .first(new HrefDTO("/first"))
                .prev(new HrefDTO("/prev"))
                .next(new HrefDTO("/next"))
                .build();

        LinksDTO two = LinksDTO.builder()
                .first(new HrefDTO("/first"))
                .prev(new HrefDTO("/prev"))
                .next(new HrefDTO("/next"))
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("first"));
        assertTrue(one.toString().contains("prev"));
        assertTrue(one.toString().contains("next"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTransactionDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        ListTransactionDTO dto = new ListTransactionDTO();

        AmountDTO amount = new AmountDTO("1000", "COP");

        dto.setValueDate("2024-01-01");
        dto.setAmount(amount);
        dto.setDescription("INTERESES");

        assertEquals("2024-01-01", dto.getValueDate());
        assertEquals(amount, dto.getAmount());
        assertEquals("INTERESES", dto.getDescription());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        AmountDTO amount = new AmountDTO("1000", "COP");

        ListTransactionDTO dto =
                new ListTransactionDTO(
                        "2024-01-01",
                        amount,
                        "INTERESES"
                );

        assertEquals("2024-01-01", dto.getValueDate());
        assertEquals(amount, dto.getAmount());
        assertEquals("INTERESES", dto.getDescription());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        AmountDTO amount = new AmountDTO("1000", "COP");

        ListTransactionDTO one = ListTransactionDTO.builder()
                .valueDate("2024-01-01")
                .amount(amount)
                .description("INTERESES")
                .build();

        ListTransactionDTO two = ListTransactionDTO.builder()
                .valueDate("2024-01-01")
                .amount(amount)
                .description("INTERESES")
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("valueDate"));
        assertTrue(one.toString().contains("description"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositTransactionResponseTest {

    @Test
    void shouldCoverNoArgsConstructorGettersAndSetters() {

        TermDepositTransactionResponse response =
                new TermDepositTransactionResponse();

        List<ListTransactionDTO> transactions =
                Collections.singletonList(
                        ListTransactionDTO.builder()
                                .valueDate("2024-01-01")
                                .amount(new AmountDTO("1000", "COP"))
                                .description("INTERESES")
                                .build()
                );

        response.setListTransactions(transactions);

        assertEquals(transactions, response.getListTransactions());
    }

    @Test
    void shouldCoverAllArgsConstructor() {

        List<ListTransactionDTO> transactions =
                Collections.singletonList(
                        new ListTransactionDTO(
                                "2024-01-01",
                                new AmountDTO("1000", "COP"),
                                "INTERESES"
                        )
                );

        TermDepositTransactionResponse response =
                new TermDepositTransactionResponse(transactions);

        assertEquals(transactions, response.getListTransactions());
    }

    @Test
    void shouldCoverBuilderEqualsHashCodeAndToString() {

        List<ListTransactionDTO> transactions =
                Collections.singletonList(
                        ListTransactionDTO.builder()
                                .valueDate("2024-01-01")
                                .amount(new AmountDTO("1000", "COP"))
                                .description("INTERESES")
                                .build()
                );

        TermDepositTransactionResponse one =
                TermDepositTransactionResponse.builder()
                        .listTransactions(transactions)
                        .build();

        TermDepositTransactionResponse two =
                TermDepositTransactionResponse.builder()
                        .listTransactions(transactions)
                        .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("listTransactions"));
    }
}
