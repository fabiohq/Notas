Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationCommonDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PaginationCommonDTO dto = new PaginationCommonDTO();
        dto.setOffset("0");
        dto.setLimit("10");

        assertEquals("0", dto.getOffset());
        assertEquals("10", dto.getLimit());

        PaginationCommonDTO dto2 = new PaginationCommonDTO("1", "20");
        assertEquals("1", dto2.getOffset());
        assertEquals("20", dto2.getLimit());

        PaginationCommonDTO dto3 = PaginationCommonDTO.builder()
                .offset("2")
                .limit("30")
                .build();

        assertEquals("2", dto3.getOffset());
        assertEquals("30", dto3.getLimit());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PaginationCommonDTO common = new PaginationCommonDTO("0", "10");

        PaginationDTO dto = new PaginationDTO();
        dto.setFirst(common);
        dto.setPrev(common);
        dto.setNext(common);
        dto.setLast(common);
        dto.setSelf(common);

        assertEquals(common, dto.getFirst());
        assertEquals(common, dto.getPrev());
        assertEquals(common, dto.getNext());
        assertEquals(common, dto.getLast());
        assertEquals(common, dto.getSelf());

        PaginationDTO dto2 = new PaginationDTO(common, common, common, common, common);
        assertEquals(common, dto2.getFirst());

        PaginationDTO dto3 = PaginationDTO.builder()
                .first(common)
                .prev(common)
                .next(common)
                .last(common)
                .self(common)
                .build();

        assertEquals(common, dto3.getSelf());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        BankDTO dto = new BankDTO();
        dto.setBankId("0065");
        dto.setBankName("Santander");

        assertEquals("0065", dto.getBankId());
        assertEquals("Santander", dto.getBankName());

        BankDTO dto2 = new BankDTO("001", "Bank");
        assertEquals("001", dto2.getBankId());
        assertEquals("Bank", dto2.getBankName());

        BankDTO dto3 = BankDTO.builder()
                .bankId("002")
                .bankName("Other")
                .build();

        assertEquals("002", dto3.getBankId());
        assertEquals("Other", dto3.getBankName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeNameDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CodeNameDTO dto = new CodeNameDTO();
        dto.setCode("CO");
        dto.setName("Colombia");

        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getName());

        CodeNameDTO dto2 = new CodeNameDTO("US", "Estados Unidos");
        assertEquals("US", dto2.getCode());
        assertEquals("Estados Unidos", dto2.getName());

        CodeNameDTO dto3 = CodeNameDTO.builder()
                .code("ES")
                .name("España")
                .build();

        assertEquals("ES", dto3.getCode());
        assertEquals("España", dto3.getName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO state = new CodeNameDTO("05", "Antioquia");

        DocumentDTO dto = new DocumentDTO();
        dto.setDocumentTypeCode("CC");
        dto.setDocumentTypeDescription("Cedula");
        dto.setDocumentNumber("123456");
        dto.setIssueDate("2020-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("Registraduria");
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Medellin");

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("Cedula", dto.getDocumentTypeDescription());
        assertEquals("123456", dto.getDocumentNumber());
        assertEquals("2020-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("Registraduria", dto.getIssuerEntity());
        assertEquals(country, dto.getCountry());
        assertEquals(state, dto.getState());
        assertEquals("Medellin", dto.getTown());

        DocumentDTO dto2 = new DocumentDTO("CE", "Cedula extranjeria", "789",
                "2021-01-01", "2031-01-01", "Entity", country, state, "Bogota");

        assertEquals("CE", dto2.getDocumentTypeCode());

        DocumentDTO dto3 = DocumentDTO.builder()
                .documentTypeCode("CC")
                .documentNumber("999")
                .country(country)
                .state(state)
                .town("Cali")
                .build();

        assertEquals("CC", dto3.getDocumentTypeCode());
        assertEquals("999", dto3.getDocumentNumber());
        assertEquals("Cali", dto3.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CodeNameDTO codeName = new CodeNameDTO("CO", "Colombia");
        List<String> lines = List.of("Line 1");

        PostalAddressDTO dto = new PostalAddressDTO();
        dto.setFullAddress("Calle 1");
        dto.setFormatCode("FMT");
        dto.setFormatDescription("Format");
        dto.setAddressValidated(true);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("CL");
        dto.setStreetTypeDescription("Calle");
        dto.setStreetName("1");
        dto.setSecondaryStreetName("2");
        dto.setStreetBuildingIdentification("B1");
        dto.setMailDeliverySubLocation("Sub");
        dto.setBuildingName("Building");
        dto.setFloor("3");
        dto.setDetailCode("D");
        dto.setUnitType("APT");
        dto.setUnitNumber("101");
        dto.setPremise("Premise");
        dto.setAlternativePremise("Alt");
        dto.setDepartment("Dept");
        dto.setSubDepartment("SubDept");
        dto.setPostCodeIdentification("Post");
        dto.setTown(codeName);
        dto.setState(codeName);
        dto.setDistrictName("District");
        dto.setSecondaryDistrictName("District2");
        dto.setMailingInstructions("Instructions");
        dto.setProvince(codeName);
        dto.setRegionIdentification(codeName);
        dto.setCountyIdentification(codeName);
        dto.setCountry(codeName);
        dto.setMilitary("Military");
        dto.setPostOfficeBox("PO");
        dto.setPostBoxTypeCode("POT");
        dto.setPostBoxTypeDescription("Post Box");
        dto.setForeignAddressLines(lines);
        dto.setZipCode("050001");
        dto.setZip4Code("0001");
        dto.setRuralTypeCode("R");
        dto.setRuralTypeDescription("Rural");
        dto.setRuralNumber("10");

        assertEquals("Calle 1", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("Format", dto.getFormatDescription());
        assertTrue(dto.getAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("Calle", dto.getStreetTypeDescription());
        assertEquals("1", dto.getStreetName());
        assertEquals("2", dto.getSecondaryStreetName());
        assertEquals("B1", dto.getStreetBuildingIdentification());
        assertEquals("Sub", dto.getMailDeliverySubLocation());
        assertEquals("Building", dto.getBuildingName());
        assertEquals("3", dto.getFloor());
        assertEquals("D", dto.getDetailCode());
        assertEquals("APT", dto.getUnitType());
        assertEquals("101", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alt", dto.getAlternativePremise());
        assertEquals("Dept", dto.getDepartment());
        assertEquals("SubDept", dto.getSubDepartment());
        assertEquals("Post", dto.getPostCodeIdentification());
        assertEquals(codeName, dto.getTown());
        assertEquals(codeName, dto.getState());
        assertEquals("District", dto.getDistrictName());
        assertEquals("District2", dto.getSecondaryDistrictName());
        assertEquals("Instructions", dto.getMailingInstructions());
        assertEquals(codeName, dto.getProvince());
        assertEquals(codeName, dto.getRegionIdentification());
        assertEquals(codeName, dto.getCountyIdentification());
        assertEquals(codeName, dto.getCountry());
        assertEquals("Military", dto.getMilitary());
        assertEquals("PO", dto.getPostOfficeBox());
        assertEquals("POT", dto.getPostBoxTypeCode());
        assertEquals("Post Box", dto.getPostBoxTypeDescription());
        assertEquals(lines, dto.getForeignAddressLines());
        assertEquals("050001", dto.getZipCode());
        assertEquals("0001", dto.getZip4Code());
        assertEquals("R", dto.getRuralTypeCode());
        assertEquals("Rural", dto.getRuralTypeDescription());
        assertEquals("10", dto.getRuralNumber());

        PostalAddressDTO dto2 = PostalAddressDTO.builder()
                .fullAddress("Carrera 10")
                .streetTypeCode("CR")
                .country(codeName)
                .foreignAddressLines(lines)
                .build();

        assertEquals("Carrera 10", dto2.getFullAddress());
        assertEquals("CR", dto2.getStreetTypeCode());
        assertEquals(codeName, dto2.getCountry());
        assertEquals(lines, dto2.getForeignAddressLines());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StadisticsTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        Stadistics dto = new Stadistics();
        dto.setEntryTime("1");
        dto.setTrxCallTime("2");
        dto.setTrxExitTime("3");
        dto.setExitTime("4");

        assertEquals("1", dto.getEntryTime());
        assertEquals("2", dto.getTrxCallTime());
        assertEquals("3", dto.getTrxExitTime());
        assertEquals("4", dto.getExitTime());

        Stadistics dto2 = new Stadistics("a", "b", "c", "d");
        assertEquals("a", dto2.getEntryTime());
        assertEquals("b", dto2.getTrxCallTime());
        assertEquals("c", dto2.getTrxExitTime());
        assertEquals("d", dto2.getExitTime());

        Stadistics dto3 = Stadistics.builder()
                .entryTime("e")
                .trxCallTime("f")
                .trxExitTime("g")
                .exitTime("h")
                .build();

        assertEquals("e", dto3.getEntryTime());
        assertEquals("f", dto3.getTrxCallTime());
        assertEquals("g", dto3.getTrxExitTime());
        assertEquals("h", dto3.getExitTime());
    }
}