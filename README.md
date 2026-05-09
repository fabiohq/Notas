import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldTestPostalAddressDTO() {
        StateDTO state = new StateDTO("ST", "STATE");
        ProvinceDTO province = new ProvinceDTO("PR", "PROVINCE");
        RegionIdentificationDTO region = new RegionIdentificationDTO("RG", "REGION");
        CountyIdentificationDTO county = new CountyIdentificationDTO("CT", "COUNTY");
        CountryDTO country = new CountryDTO("CO", "COLOMBIA");

        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setFullAddress("FULL");
        dto.setFormatCode("FC");
        dto.setFormatDescription("FD");
        dto.setIsAddressValidated(true);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("STC");
        dto.setStreetTypeDescription("STD");
        dto.setStreetName("STREET");
        dto.setSecondaryStreetName("SECOND");
        dto.setStreetBuildingIdentification("BUILD");
        dto.setMailDeliverySubLocation("MAIL");
        dto.setBuildingName("BUILDING");
        dto.setFloor("10");
        dto.setDetailCode("DETAIL");
        dto.setUnitType("APT");
        dto.setUnitNumber("101");
        dto.setPremise("PREMISE");
        dto.setAlternativePremise("ALT");
        dto.setDepartment("DEP");
        dto.setSubDepartment("SUBDEP");
        dto.setPostCodeIdentification("POSTCODE");
        dto.setTownName("TOWN");
        dto.setState(state);
        dto.setDistrictName("DISTRICT");
        dto.setSecondaryDistrictName("SECDIST");
        dto.setMailingInstructions("MAILINST");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("MIL");
        dto.setPostOfficeBox("BOX");
        dto.setPostBoxTypeCode("PBTC");
        dto.setPostBoxTypeDescription("PBTD");
        dto.setForeignAddressLines(Arrays.asList("LINE1", "LINE2"));
        dto.setZipCode("ZIP");
        dto.setZip4Code("ZIP4");
        dto.setRuralTypeCode("RTC");
        dto.setRuralTypeDescription("RTD");
        dto.setRuralNumber("RN");

        assertEquals("FULL", dto.getFullAddress());
        assertEquals("FC", dto.getFormatCode());
        assertEquals("FD", dto.getFormatDescription());
        assertTrue(dto.getIsAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("STC", dto.getStreetTypeCode());
        assertEquals("STD", dto.getStreetTypeDescription());
        assertEquals("STREET", dto.getStreetName());
        assertEquals("SECOND", dto.getSecondaryStreetName());
        assertEquals("BUILD", dto.getStreetBuildingIdentification());
        assertEquals("MAIL", dto.getMailDeliverySubLocation());
        assertEquals("BUILDING", dto.getBuildingName());
        assertEquals("10", dto.getFloor());
        assertEquals("DETAIL", dto.getDetailCode());
        assertEquals("APT", dto.getUnitType());
        assertEquals("101", dto.getUnitNumber());
        assertEquals("PREMISE", dto.getPremise());
        assertEquals("ALT", dto.getAlternativePremise());
        assertEquals("DEP", dto.getDepartment());
        assertEquals("SUBDEP", dto.getSubDepartment());
        assertEquals("POSTCODE", dto.getPostCodeIdentification());
        assertEquals("TOWN", dto.getTownName());
        assertSame(state, dto.getState());
        assertEquals("DISTRICT", dto.getDistrictName());
        assertEquals("SECDIST", dto.getSecondaryDistrictName());
        assertEquals("MAILINST", dto.getMailingInstructions());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MIL", dto.getMilitary());
        assertEquals("BOX", dto.getPostOfficeBox());
        assertEquals("PBTC", dto.getPostBoxTypeCode());
        assertEquals("PBTD", dto.getPostBoxTypeDescription());
        assertEquals(Arrays.asList("LINE1", "LINE2"), dto.getForeignAddressLines());
        assertEquals("ZIP", dto.getZipCode());
        assertEquals("ZIP4", dto.getZip4Code());
        assertEquals("RTC", dto.getRuralTypeCode());
        assertEquals("RTD", dto.getRuralTypeDescription());
        assertEquals("RN", dto.getRuralNumber());

        PostalAddressDTO builder = PostalAddressDTO.builder()
                .fullAddress("FULL")
                .formatCode("FC")
                .formatDescription("FD")
                .isAddressValidated(true)
                .matchId("MATCH")
                .streetTypeCode("STC")
                .streetTypeDescription("STD")
                .streetName("STREET")
                .secondaryStreetName("SECOND")
                .streetBuildingIdentification("BUILD")
                .mailDeliverySubLocation("MAIL")
                .buildingName("BUILDING")
                .floor("10")
                .detailCode("DETAIL")
                .unitType("APT")
                .unitNumber("101")
                .premise("PREMISE")
                .alternativePremise("ALT")
                .department("DEP")
                .subDepartment("SUBDEP")
                .postCodeIdentification("POSTCODE")
                .townName("TOWN")
                .state(state)
                .districtName("DISTRICT")
                .secondaryDistrictName("SECDIST")
                .mailingInstructions("MAILINST")
                .province(province)
                .regionIdentification(region)
                .countyIdentification(county)
                .country(country)
                .military("MIL")
                .postOfficeBox("BOX")
                .postBoxTypeCode("PBTC")
                .postBoxTypeDescription("PBTD")
                .foreignAddressLines(Arrays.asList("LINE1", "LINE2"))
                .zipCode("ZIP")
                .zip4Code("ZIP4")
                .ruralTypeCode("RTC")
                .ruralTypeDescription("RTD")
                .ruralNumber("RN")
                .build();

        assertEquals("FULL", builder.getFullAddress());
        assertEquals("RN", builder.getRuralNumber());
    }
}