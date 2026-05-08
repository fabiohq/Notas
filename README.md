import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldSetAndGetAllValues() {

        StateDTO state = new StateDTO("ST", "STATE");
        CountryDTO country = new CountryDTO("CO", "COLOMBIA");

        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setFullAddress("FULL");
        dto.setFormatCode("FC");
        dto.setFormatDescription("FD");
        dto.setIsAddressValidated(true);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("STC");
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
        dto.setTown("TOWN");
        dto.setTownName("TOWNNAME");
        dto.setState(state);
        dto.setDistrictName("DISTRICT");
        dto.setSecondaryDistrictName("SECDIST");
        dto.setMailingInstructions("MAILINST");
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
        assertEquals("TOWN", dto.getTown());
        assertEquals("TOWNNAME", dto.getTownName());
        assertEquals(state, dto.getState());
        assertEquals("DISTRICT", dto.getDistrictName());
        assertEquals("SECDIST", dto.getSecondaryDistrictName());
        assertEquals("MAILINST", dto.getMailingInstructions());
        assertEquals(country, dto.getCountry());
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
    }

    @Test
    void shouldBuildPostalAddressDTO() {

        PostalAddressDTO dto = PostalAddressDTO.builder()
                .fullAddress("ADDRESS")
                .zipCode("ZIP")
                .town("TOWN")
                .build();

        assertEquals("ADDRESS", dto.getFullAddress());
        assertEquals("ZIP", dto.getZipCode());
        assertEquals("TOWN", dto.getTown());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {

        PostalAddressDTO dto = new PostalAddressDTO(
                "FULL",
                "FC",
                "FD",
                true,
                "MATCH",
                "STC",
                "STREET",
                "SECOND",
                "BUILD",
                "MAIL",
                "BUILDING",
                "10",
                "DETAIL",
                "APT",
                "101",
                "PREMISE",
                "ALT",
                "DEP",
                "SUBDEP",
                "POSTCODE",
                "TOWN",
                "TOWNNAME",
                new StateDTO("ST", "STATE"),
                "DISTRICT",
                "SECDIST",
                "MAILINST",
                new CountryDTO("CO", "COLOMBIA"),
                "MIL",
                "BOX",
                "PBTC",
                "PBTD",
                Arrays.asList("LINE1"),
                "ZIP",
                "ZIP4",
                "RTC",
                "RTD",
                "RN"
        );

        assertNotNull(dto);
        assertEquals("FULL", dto.getFullAddress());
        assertEquals("ZIP", dto.getZipCode());
    }
}