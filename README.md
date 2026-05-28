package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        AuditDTO dto = new AuditDTO();
        dto.setCreationDate("2026-01-01");
        dto.setLastUpdateDate("2026-02-01");

        assertEquals("2026-01-01", dto.getCreationDate());
        assertEquals("2026-02-01", dto.getLastUpdateDate());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        AuditDTO dto = new AuditDTO("2026-01-01", "2026-02-01");

        assertEquals("2026-01-01", dto.getCreationDate());
        assertEquals("2026-02-01", dto.getLastUpdateDate());
    }

    @Test
    void shouldCreateWithBuilder() {
        AuditDTO dto = AuditDTO.builder()
                .creationDate("2026-01-01")
                .lastUpdateDate("2026-02-01")
                .build();

        assertEquals("2026-01-01", dto.getCreationDate());
        assertEquals("2026-02-01", dto.getLastUpdateDate());
    }
}

class DataOriginDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        DataOriginDTO dto = new DataOriginDTO();
        dto.setSourceCode("SRC");
        dto.setSourceDescription("Source description");
        dto.setCreationDate("2026-01-01");

        assertEquals("SRC", dto.getSourceCode());
        assertEquals("Source description", dto.getSourceDescription());
        assertEquals("2026-01-01", dto.getCreationDate());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfBirthDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        CodeNameDTO country = new CodeNameDTO();
        CodeNameDTO state = new CodeNameDTO();

        PlaceOfBirthDTO dto = new PlaceOfBirthDTO();
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogota");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogota", dto.getTown());
    }
}

class PublicOfficeInformationDTOTest {

    @Test
    void shouldCreateWithBuilder() {
        ValidityPeriodDTO validityPeriod = new ValidityPeriodDTO();

        PublicOfficeInformationDTO dto = PublicOfficeInformationDTO.builder()
                .positionCode("POS")
                .positionDescription("Position description")
                .validityPeriod(validityPeriod)
                .build();

        assertEquals("POS", dto.getPositionCode());
        assertEquals("Position description", dto.getPositionDescription());
        assertSame(validityPeriod, dto.getValidityPeriod());
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameDTOTest {

    @Test
    void shouldCreateWithBuilder() {
        ArrayList<String> tradingNames =
                new ArrayList<>(List.of("Trade 1", "Trade 2"));

        OrganizationNameDTO dto = OrganizationNameDTO.builder()
                .legalName("Santander Legal Name")
                .tradingNames(tradingNames)
                .build();

        assertEquals("Santander Legal Name", dto.getLegalName());
        assertEquals(tradingNames, dto.getTradingNames());
    }
}

class ContactPointDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ContactPointDTO dto = new ContactPointDTO();

        dto.setContactPointId("CP-1");

        assertEquals("CP-1", dto.getContactPointId());
    }
}
