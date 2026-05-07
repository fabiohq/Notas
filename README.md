Te dejo los tests faltantes, separados clase por clase.
ProspectCreatedResponseDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectCreatedResponseDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ProspectCreatedResponseDTO dto = new ProspectCreatedResponseDTO();

        dto.setProspectId("12345678");

        assertEquals("12345678", dto.getProspectId());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        ProspectCreatedResponseDTO dto = new ProspectCreatedResponseDTO("87654321");

        assertEquals("87654321", dto.getProspectId());
    }

    @Test
    void shouldBuildWithBuilder() {
        ProspectCreatedResponseDTO dto = ProspectCreatedResponseDTO.builder()
                .prospectId("99999999")
                .build();

        assertEquals("99999999", dto.getProspectId());
    }
}
PaginationCommonDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationCommonDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PaginationCommonDTO dto = new PaginationCommonDTO();

        dto.setOffset("0");
        dto.setLimit("10");

        assertEquals("0", dto.getOffset());
        assertEquals("10", dto.getLimit());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        PaginationCommonDTO dto = new PaginationCommonDTO("5", "20");

        assertEquals("5", dto.getOffset());
        assertEquals("20", dto.getLimit());
    }

    @Test
    void shouldBuildWithBuilder() {
        PaginationCommonDTO dto = PaginationCommonDTO.builder()
                .offset("1")
                .limit("50")
                .build();

        assertEquals("1", dto.getOffset());
        assertEquals("50", dto.getLimit());
    }
}
PaginationDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PaginationCommonDTO first = new PaginationCommonDTO("0", "10");
        PaginationCommonDTO prev = new PaginationCommonDTO("0", "10");
        PaginationCommonDTO next = new PaginationCommonDTO("10", "10");
        PaginationCommonDTO last = new PaginationCommonDTO("90", "10");
        PaginationCommonDTO self = new PaginationCommonDTO("0", "10");

        PaginationDTO dto = new PaginationDTO();

        dto.setFirst(first);
        dto.setPrev(prev);
        dto.setNext(next);
        dto.setLast(last);
        dto.setSelf(self);

        assertSame(first, dto.getFirst());
        assertSame(prev, dto.getPrev());
        assertSame(next, dto.getNext());
        assertSame(last, dto.getLast());
        assertSame(self, dto.getSelf());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        PaginationCommonDTO common = new PaginationCommonDTO("0", "10");

        PaginationDTO dto = new PaginationDTO(common, common, common, common, common);

        assertSame(common, dto.getFirst());
        assertSame(common, dto.getPrev());
        assertSame(common, dto.getNext());
        assertSame(common, dto.getLast());
        assertSame(common, dto.getSelf());
    }

    @Test
    void shouldBuildWithBuilder() {
        PaginationCommonDTO common = PaginationCommonDTO.builder()
                .offset("1")
                .limit("25")
                .build();

        PaginationDTO dto = PaginationDTO.builder()
                .first(common)
                .prev(common)
                .next(common)
                .last(common)
                .self(common)
                .build();

        assertSame(common, dto.getFirst());
        assertSame(common, dto.getPrev());
        assertSame(common, dto.getNext());
        assertSame(common, dto.getLast());
        assertSame(common, dto.getSelf());
    }
}
BankDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        BankDTO dto = new BankDTO();

        dto.setBankId("0065");
        dto.setBankName("Santander");

        assertEquals("0065", dto.getBankId());
        assertEquals("Santander", dto.getBankName());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        BankDTO dto = new BankDTO("001", "Bank");

        assertEquals("001", dto.getBankId());
        assertEquals("Bank", dto.getBankName());
    }

    @Test
    void shouldBuildWithBuilder() {
        BankDTO dto = BankDTO.builder()
                .bankId("002")
                .bankName("Test Bank")
                .build();

        assertEquals("002", dto.getBankId());
        assertEquals("Test Bank", dto.getBankName());
    }
}
CodeNameDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeNameDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO dto = new CodeNameDTO();

        dto.setCode("CO");
        dto.setName("Colombia");

        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getName());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CodeNameDTO dto = new CodeNameDTO("ES", "España");

        assertEquals("ES", dto.getCode());
        assertEquals("España", dto.getName());
    }

    @Test
    void shouldBuildWithBuilder() {
        CodeNameDTO dto = CodeNameDTO.builder()
                .code("US")
                .name("Estados Unidos")
                .build();

        assertEquals("US", dto.getCode());
        assertEquals("Estados Unidos", dto.getName());
    }
}
AuditDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        AuditDTO dto = new AuditDTO();

        dto.setCreationDate("2026-05-07");
        dto.setLastUpdateDate("2026-05-08");

        assertEquals("2026-05-07", dto.getCreationDate());
        assertEquals("2026-05-08", dto.getLastUpdateDate());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        AuditDTO dto = new AuditDTO("2026-01-01", "2026-01-02");

        assertEquals("2026-01-01", dto.getCreationDate());
        assertEquals("2026-01-02", dto.getLastUpdateDate());
    }

    @Test
    void shouldBuildWithBuilder() {
        AuditDTO dto = AuditDTO.builder()
                .creationDate("2026-02-01")
                .lastUpdateDate("2026-02-02")
                .build();

        assertEquals("2026-02-01", dto.getCreationDate());
        assertEquals("2026-02-02", dto.getLastUpdateDate());
    }
}