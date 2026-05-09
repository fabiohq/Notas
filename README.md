Aquí van los tests por clase y sin acoplar.
PaginationCommonDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationCommonDTOTest {

    @Test
    void shouldCoverPaginationCommonDTO() {
        PaginationCommonDTO dto = new PaginationCommonDTO();

        dto.setOffset("0");
        dto.setLimit("10");

        assertEquals("0", dto.getOffset());
        assertEquals("10", dto.getLimit());

        PaginationCommonDTO builder = PaginationCommonDTO.builder()
                .offset("0")
                .limit("10")
                .build();

        assertEquals("0", builder.getOffset());
        assertEquals("10", builder.getLimit());

        PaginationCommonDTO allArgs = new PaginationCommonDTO("0", "10");

        assertEquals("0", allArgs.getOffset());
        assertEquals("10", allArgs.getLimit());
    }
}
PaginationDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationDTOTest {

    @Test
    void shouldCoverPaginationDTO() {
        PaginationCommonDTO first = new PaginationCommonDTO();
        PaginationCommonDTO prev = new PaginationCommonDTO();
        PaginationCommonDTO next = new PaginationCommonDTO();
        PaginationCommonDTO last = new PaginationCommonDTO();
        PaginationCommonDTO self = new PaginationCommonDTO();

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

        PaginationDTO builder = PaginationDTO.builder()
                .first(first)
                .prev(prev)
                .next(next)
                .last(last)
                .self(self)
                .build();

        assertSame(first, builder.getFirst());
        assertSame(self, builder.getSelf());

        PaginationDTO allArgs = new PaginationDTO(first, prev, next, last, self);

        assertSame(next, allArgs.getNext());
        assertSame(last, allArgs.getLast());
    }
}
BankDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDTOTest {

    @Test
    void shouldCoverBankDTO() {
        BankDTO dto = new BankDTO();

        dto.setBankId("BANK_ID");
        dto.setBankName("BANK_NAME");

        assertEquals("BANK_ID", dto.getBankId());
        assertEquals("BANK_NAME", dto.getBankName());

        BankDTO builder = BankDTO.builder()
                .bankId("BANK_ID")
                .bankName("BANK_NAME")
                .build();

        assertEquals("BANK_ID", builder.getBankId());
        assertEquals("BANK_NAME", builder.getBankName());

        BankDTO allArgs = new BankDTO("BANK_ID", "BANK_NAME");

        assertEquals("BANK_ID", allArgs.getBankId());
        assertEquals("BANK_NAME", allArgs.getBankName());
    }
}
CodeNameDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeNameDTOTest {

    @Test
    void shouldCoverCodeNameDTO() {
        CodeNameDTO dto = new CodeNameDTO();

        dto.setCode("CODE");
        dto.setName("NAME");

        assertEquals("CODE", dto.getCode());
        assertEquals("NAME", dto.getName());

        CodeNameDTO builder = CodeNameDTO.builder()
                .code("CODE")
                .name("NAME")
                .build();

        assertEquals("CODE", builder.getCode());
        assertEquals("NAME", builder.getName());

        CodeNameDTO allArgs = new CodeNameDTO("CODE", "NAME");

        assertEquals("CODE", allArgs.getCode());
        assertEquals("NAME", allArgs.getName());
    }
}
DocumentDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentDTOTest {

    @Test
    void shouldCoverDocumentDTO() {
        CodeNameDTO country = new CodeNameDTO();
        CodeNameDTO state = new CodeNameDTO();
        CodeNameDTO town = new CodeNameDTO();

        DocumentDTO dto = new DocumentDTO();

        dto.setDocumentTypeCode("TYPE_CODE");
        dto.setDocumentTypeDescription("TYPE_DESC");
        dto.setDocumentNumber("NUMBER");
        dto.setIssueDate("ISSUE");
        dto.setExpirationDate("EXPIRATION");
        dto.setIssuerEntity("ISSUER");
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown(town);

        assertEquals("TYPE_CODE", dto.getDocumentTypeCode());
        assertEquals("TYPE_DESC", dto.getDocumentTypeDescription());
        assertEquals("NUMBER", dto.getDocumentNumber());
        assertEquals("ISSUE", dto.getIssueDate());
        assertEquals("EXPIRATION", dto.getExpirationDate());
        assertEquals("ISSUER", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertSame(town, dto.getTown());

        DocumentDTO builder = DocumentDTO.builder()
                .documentTypeCode("TYPE_CODE")
                .documentTypeDescription("TYPE_DESC")
                .documentNumber("NUMBER")
                .issueDate("ISSUE")
                .expirationDate("EXPIRATION")
                .issuerEntity("ISSUER")
                .country(country)
                .state(state)
                .town(town)
                .build();

        assertEquals("TYPE_CODE", builder.getDocumentTypeCode());
        assertSame(country, builder.getCountry());

        DocumentDTO allArgs = new DocumentDTO(
                "TYPE_CODE",
                "TYPE_DESC",
                "NUMBER",
                "ISSUE",
                "EXPIRATION",
                "ISSUER",
                country,
                state,
                town
        );

        assertEquals("NUMBER", allArgs.getDocumentNumber());
        assertSame(town, allArgs.getTown());
    }
}
ParametersTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void shouldCoverParameters() {
        CodeNameDTO countryNationality = new CodeNameDTO();
        CodeNameDTO countryExp = new CodeNameDTO();
        CodeNameDTO countryBirth = new CodeNameDTO();
        CodeNameDTO countryDir = new CodeNameDTO();
        CodeNameDTO cityStandard = new CodeNameDTO();
        CodeNameDTO cityDepartment = new CodeNameDTO();
        CodeNameDTO cityExp = new CodeNameDTO();
        CodeNameDTO cityBirth = new CodeNameDTO();
        CodeNameDTO town = new CodeNameDTO();

        Parameters dto = new Parameters();

        dto.setCountryNationality(countryNationality);
        dto.setCountryExp(countryExp);
        dto.setCountryBirth(countryBirth);
        dto.setCountryDir(countryDir);
        dto.setCityStandard(cityStandard);
        dto.setCityDepartment(cityDepartment);
        dto.setCityExp(cityExp);
        dto.setCityBirth(cityBirth);
        dto.setTown(town);
        dto.setDocumentTypeDescription("DOC_DESC");
        dto.setStreetTypeDescription("STREET_DESC");

        assertSame(countryNationality, dto.getCountryNationality());
        assertSame(countryExp, dto.getCountryExp());
        assertSame(countryBirth, dto.getCountryBirth());
        assertSame(countryDir, dto.getCountryDir());
        assertSame(cityStandard, dto.getCityStandard());
        assertSame(cityDepartment, dto.getCityDepartment());
        assertSame(cityExp, dto.getCityExp());
        assertSame(cityBirth, dto.getCityBirth());
        assertSame(town, dto.getTown());
        assertEquals("DOC_DESC", dto.getDocumentTypeDescription());
        assertEquals("STREET_DESC", dto.getStreetTypeDescription());

        Parameters builder = Parameters.builder()
                .countryNationality(countryNationality)
                .countryExp(countryExp)
                .countryBirth(countryBirth)
                .countryDir(countryDir)
                .cityStandard(cityStandard)
                .cityDepartment(cityDepartment)
                .cityExp(cityExp)
                .cityBirth(cityBirth)
                .town(town)
                .documentTypeDescription("DOC_DESC")
                .streetTypeDescription("STREET_DESC")
                .build();

        assertSame(countryNationality, builder.getCountryNationality());
        assertEquals("STREET_DESC", builder.getStreetTypeDescription());

        Parameters allArgs = new Parameters(
                countryNationality,
                countryExp,
                countryBirth,
                countryDir,
                cityStandard,
                cityDepartment,
                cityExp,
                cityBirth,
                town,
                "DOC_DESC",
                "STREET_DESC"
        );

        assertSame(town, allArgs.getTown());
        assertEquals("DOC_DESC", allArgs.getDocumentTypeDescription());
    }
}
PostalAddressDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldCoverPostalAddressDTO() {
        CodeNameDTO town = new CodeNameDTO();
        CodeNameDTO state = new CodeNameDTO();
        CodeNameDTO province = new CodeNameDTO();
        CodeNameDTO regionIdentification = new CodeNameDTO();
        CodeNameDTO countyIdentification = new CodeNameDTO();
        CodeNameDTO country = new CodeNameDTO();

        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setFullAddress("FULL");
        dto.setFormatCode("FORMAT_CODE");
        dto.setFormatDescription("FORMAT_DESC");
        dto.setIsAddressValidated(true);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("STREET_TYPE_CODE");
        dto.setStreetTypeDescription("STREET_TYPE_DESC");
        dto.setStreetName("STREET");
        dto.setSecondaryStreetName("SECONDARY");
        dto.setStreetBuildingIdentification("BUILDING_ID");
        dto.setMailDeliverySubLocation("MAIL_SUB");
        dto.setBuildingName("BUILDING");
        dto.setFloor("FLOOR");
        dto.setDetailCode("DETAIL");
        dto.setUnitType("UNIT_TYPE");
        dto.setUnitNumber("UNIT_NUMBER");
        dto.setPremise("PREMISE");
        dto.setAlternativePremise("ALT_PREMISE");
        dto.setDepartment("DEPARTMENT");
        dto.setSubDepartment("SUB_DEPARTMENT");
        dto.setPostCodeIdentification("POST_CODE");
        dto.setTown(town);
        dto.setState(state);
        dto.setDistrictName("DISTRICT");
        dto.setSecondaryDistrictName("SECONDARY_DISTRICT");
        dto.setMailingInstructions("INSTRUCTIONS");
        dto.setProvince(province);
        dto.setRegionIdentification(regionIdentification);
        dto.setCountyIdentification(countyIdentification);
        dto.setCountry(country);
        dto.setMilitary("MILITARY");
        dto.setPostOfficeBox("POST_BOX");
        dto.setPostBoxTypeCode("POST_BOX_CODE");
        dto.setPostBoxTypeDescription("POST_BOX_DESC");
        dto.setForeignAddressLines(Arrays.asList("LINE_1", "LINE_2"));
        dto.setZipCode("ZIP");
        dto.setZip4Code("ZIP4");
        dto.setRuralTypeCode("RURAL_CODE");
        dto.setRuralTypeDescription("RURAL_DESC");
        dto.setRuralNumber("RURAL_NUMBER");

        assertEquals("FULL", dto.getFullAddress());
        assertEquals("FORMAT_CODE", dto.getFormatCode());
        assertEquals("FORMAT_DESC", dto.getFormatDescription());
        assertTrue(dto.getIsAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("STREET_TYPE_CODE", dto.getStreetTypeCode());
        assertEquals("STREET_TYPE_DESC", dto.getStreetTypeDescription());
        assertEquals("STREET", dto.getStreetName());
        assertEquals("SECONDARY", dto.getSecondaryStreetName());
        assertEquals("BUILDING_ID", dto.getStreetBuildingIdentification());
        assertEquals("MAIL_SUB", dto.getMailDeliverySubLocation());
        assertEquals("BUILDING", dto.getBuildingName());
        assertEquals("FLOOR", dto.getFloor());
        assertEquals("DETAIL", dto.getDetailCode());
        assertEquals("UNIT_TYPE", dto.getUnitType());
        assertEquals("UNIT_NUMBER", dto.getUnitNumber());
        assertEquals("PREMISE", dto.getPremise());
        assertEquals("ALT_PREMISE", dto.getAlternativePremise());
        assertEquals("DEPARTMENT", dto.getDepartment());
        assertEquals("SUB_DEPARTMENT", dto.getSubDepartment());
        assertEquals("POST_CODE", dto.getPostCodeIdentification());
        assertSame(town, dto.getTown());
        assertSame(state, dto.getState());
        assertEquals("DISTRICT", dto.getDistrictName());
        assertEquals("SECONDARY_DISTRICT", dto.getSecondaryDistrictName());
        assertEquals("INSTRUCTIONS", dto.getMailingInstructions());
        assertSame(province, dto.getProvince());
        assertSame(regionIdentification, dto.getRegionIdentification());
        assertSame(countyIdentification, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MILITARY", dto.getMilitary());
        assertEquals("POST_BOX", dto.getPostOfficeBox());
        assertEquals("POST_BOX_CODE", dto.getPostBoxTypeCode());
        assertEquals("POST_BOX_DESC", dto.getPostBoxTypeDescription());
        assertEquals(Arrays.asList("LINE_1", "LINE_2"), dto.getForeignAddressLines());
        assertEquals("ZIP", dto.getZipCode());
        assertEquals("ZIP4", dto.getZip4Code());
        assertEquals("RURAL_CODE", dto.getRuralTypeCode());
        assertEquals("RURAL_DESC", dto.getRuralTypeDescription());
        assertEquals("RURAL_NUMBER", dto.getRuralNumber());

        PostalAddressDTO builder = PostalAddressDTO.builder()
                .fullAddress("FULL")
                .formatCode("FORMAT_CODE")
                .formatDescription("FORMAT_DESC")
                .isAddressValidated(true)
                .matchId("MATCH")
                .streetTypeCode("STREET_TYPE_CODE")
                .streetTypeDescription("STREET_TYPE_DESC")
                .streetName("STREET")
                .secondaryStreetName("SECONDARY")
                .streetBuildingIdentification("BUILDING_ID")
                .mailDeliverySubLocation("MAIL_SUB")
                .buildingName("BUILDING")
                .floor("FLOOR")
                .detailCode("DETAIL")
                .unitType("UNIT_TYPE")
                .unitNumber("UNIT_NUMBER")
                .premise("PREMISE")
                .alternativePremise("ALT_PREMISE")
                .department("DEPARTMENT")
                .subDepartment("SUB_DEPARTMENT")
                .postCodeIdentification("POST_CODE")
                .town(town)
                .state(state)
                .districtName("DISTRICT")
                .secondaryDistrictName("SECONDARY_DISTRICT")
                .mailingInstructions("INSTRUCTIONS")
                .province(province)
                .regionIdentification(regionIdentification)
                .countyIdentification(countyIdentification)
                .country(country)
                .military("MILITARY")
                .postOfficeBox("POST_BOX")
                .postBoxTypeCode("POST_BOX_CODE")
                .postBoxTypeDescription("POST_BOX_DESC")
                .foreignAddressLines(Arrays.asList("LINE_1", "LINE_2"))
                .zipCode("ZIP")
                .zip4Code("ZIP4")
                .ruralTypeCode("RURAL_CODE")
                .ruralTypeDescription("RURAL_DESC")
                .ruralNumber("RURAL_NUMBER")
                .build();

        assertEquals("FULL", builder.getFullAddress());
        assertSame(country, builder.getCountry());

        PostalAddressDTO allArgs = new PostalAddressDTO(
                "FULL",
                "FORMAT_CODE",
                "FORMAT_DESC",
                true,
                "MATCH",
                "STREET_TYPE_CODE",
                "STREET_TYPE_DESC",
                "STREET",
                "SECONDARY",
                "BUILDING_ID",
                "MAIL_SUB",
                "BUILDING",
                "FLOOR",
                "DETAIL",
                "UNIT_TYPE",
                "UNIT_NUMBER",
                "PREMISE",
                "ALT_PREMISE",
                "DEPARTMENT",
                "SUB_DEPARTMENT",
                "POST_CODE",
                town,
                state,
                "DISTRICT",
                "SECONDARY_DISTRICT",
                "INSTRUCTIONS",
                province,
                regionIdentification,
                countyIdentification,
                country,
                "MILITARY",
                "POST_BOX",
                "POST_BOX_CODE",
                "POST_BOX_DESC",
                Arrays.asList("LINE_1", "LINE_2"),
                "ZIP",
                "ZIP4",
                "RURAL_CODE",
                "RURAL_DESC",
                "RURAL_NUMBER"
        );

        assertEquals("FULL", allArgs.getFullAddress());
        assertEquals("RURAL_NUMBER", allArgs.getRuralNumber());
    }
}
StadisticsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StadisticsTest {

    @Test
    void shouldCoverStadistics() {
        Stadistics dto = new Stadistics();

        dto.setEntryTime("ENTRY");
        dto.setTrxCallTime("CALL");
        dto.setTrxExitTime("TRX_EXIT");
        dto.setExitTime("EXIT");

        assertEquals("ENTRY", dto.getEntryTime());
        assertEquals("CALL", dto.getTrxCallTime());
        assertEquals("TRX_EXIT", dto.getTrxExitTime());
        assertEquals("EXIT", dto.getExitTime());

        Stadistics builder = Stadistics.builder()
                .entryTime("ENTRY")
                .trxCallTime("CALL")
                .trxExitTime("TRX_EXIT")
                .exitTime("EXIT")
                .build();

        assertEquals("ENTRY", builder.getEntryTime());
        assertEquals("EXIT", builder.getExitTime());

        Stadistics allArgs = new Stadistics("ENTRY", "CALL", "TRX_EXIT", "EXIT");

        assertEquals("CALL", allArgs.getTrxCallTime());
        assertEquals("TRX_EXIT", allArgs.getTrxExitTime());
    }
}