package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();

        StateRequestDTO state = new StateRequestDTO("11");
        ProvinceRequestDTO province = new ProvinceRequestDTO("25");
        RegionIdentificationRequestDTO region = new RegionIdentificationRequestDTO("REG");
        CountyIdentificationRequestDTO county = new CountyIdentificationRequestDTO("COUNTY");
        CountryRequestDTO country = new CountryRequestDTO("CO");
        List<String> foreignLines = List.of("line1", "line2");

        dto.setFullAddress("CL 1 # 2-3");
        dto.setFormatCode("FMT");
        dto.setStreetTypeCode("CL");
        dto.setStreetName("1");
        dto.setSecondaryStreetName("2");
        dto.setStreetBuildingIdentification("3");
        dto.setBuildingName("BUILDING");
        dto.setFloor("5");
        dto.setDetailCode("APT");
        dto.setUnitType("AP");
        dto.setUnitNumber("501");
        dto.setPremise("PREMISE");
        dto.setAlternativePremise("ALT");
        dto.setMailingInstructions("MAIL");
        dto.setPostCodeIdentification("110111");
        dto.setTownName("BOGOTA");
        dto.setMailDeliverySubLocation("SUB");
        dto.setState(state);
        dto.setDistrictName("DIST");
        dto.setSecondaryDistrictName("DIST2");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("MIL");
        dto.setPostOfficeBox("BOX");
        dto.setPostBoxTypeCode("PBOX");
        dto.setForeignAddressLines(foreignLines);
        dto.setZipCode("110111");
        dto.setZip4Code("1234");
        dto.setRuralTypeCode("RUR");
        dto.setRuralNumber("99");

        assertEquals("CL 1 # 2-3", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("1", dto.getStreetName());
        assertEquals("2", dto.getSecondaryStreetName());
        assertEquals("3", dto.getStreetBuildingIdentification());
        assertEquals("BUILDING", dto.getBuildingName());
        assertEquals("5", dto.getFloor());
        assertEquals("APT", dto.getDetailCode());
        assertEquals("AP", dto.getUnitType());
        assertEquals("501", dto.getUnitNumber());
        assertEquals("PREMISE", dto.getPremise());
        assertEquals("ALT", dto.getAlternativePremise());
        assertEquals("MAIL", dto.getMailingInstructions());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertEquals("BOGOTA", dto.getTownName());
        assertEquals("SUB", dto.getMailDeliverySubLocation());
        assertSame(state, dto.getState());
        assertEquals("DIST", dto.getDistrictName());
        assertEquals("DIST2", dto.getSecondaryDistrictName());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MIL", dto.getMilitary());
        assertEquals("BOX", dto.getPostOfficeBox());
        assertEquals("PBOX", dto.getPostBoxTypeCode());
        assertEquals(foreignLines, dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
        assertEquals("1234", dto.getZip4Code());
        assertEquals("RUR", dto.getRuralTypeCode());
        assertEquals("99", dto.getRuralNumber());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        StateRequestDTO state = new StateRequestDTO("11");
        ProvinceRequestDTO province = new ProvinceRequestDTO("25");
        RegionIdentificationRequestDTO region = new RegionIdentificationRequestDTO("REG");
        CountyIdentificationRequestDTO county = new CountyIdentificationRequestDTO("COUNTY");
        CountryRequestDTO country = new CountryRequestDTO("CO");
        List<String> foreignLines = List.of("line1", "line2");

        PostalAddressRequestDTO dto = new PostalAddressRequestDTO(
                "CL 1 # 2-3",
                "FMT",
                "CL",
                "1",
                "2",
                "3",
                "BUILDING",
                "5",
                "APT",
                "AP",
                "501",
                "PREMISE",
                "ALT",
                "MAIL",
                "110111",
                "BOGOTA",
                "SUB",
                state,
                "DIST",
                "DIST2",
                province,
                region,
                county,
                country,
                "MIL",
                "BOX",
                "PBOX",
                foreignLines,
                "110111",
                "1234",
                "RUR",
                "99"
        );

        assertEquals("CL 1 # 2-3", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("1", dto.getStreetName());
        assertEquals("2", dto.getSecondaryStreetName());
        assertEquals("3", dto.getStreetBuildingIdentification());
        assertEquals("BUILDING", dto.getBuildingName());
        assertEquals("5", dto.getFloor());
        assertEquals("APT", dto.getDetailCode());
        assertEquals("AP", dto.getUnitType());
        assertEquals("501", dto.getUnitNumber());
        assertEquals("PREMISE", dto.getPremise());
        assertEquals("ALT", dto.getAlternativePremise());
        assertEquals("MAIL", dto.getMailingInstructions());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertEquals("BOGOTA", dto.getTownName());
        assertEquals("SUB", dto.getMailDeliverySubLocation());
        assertSame(state, dto.getState());
        assertEquals("DIST", dto.getDistrictName());
        assertEquals("DIST2", dto.getSecondaryDistrictName());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MIL", dto.getMilitary());
        assertEquals("BOX", dto.getPostOfficeBox());
        assertEquals("PBOX", dto.getPostBoxTypeCode());
        assertEquals(foreignLines, dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
        assertEquals("1234", dto.getZip4Code());
        assertEquals("RUR", dto.getRuralTypeCode());
        assertEquals("99", dto.getRuralNumber());
    }
}