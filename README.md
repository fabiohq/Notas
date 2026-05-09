Crea este test en el mismo package:
src/test/java/.../domain/customer/contactpoint/response/ResponseDtosTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDtosTest {

    @Test
    void simpleDtosShouldHaveFullCoverage() {
        assertSimpleCodeName(new CountryDTO(), CountryDTO.builder().code("C").name("N").build(), new CountryDTO("C", "N"));
        assertSimpleCodeName(new CountyIdentificationDTO(), CountyIdentificationDTO.builder().code("C").name("N").build(), new CountyIdentificationDTO("C", "N"));
        assertSimpleCodeName(new ProvinceDTO(), ProvinceDTO.builder().code("C").name("N").build(), new ProvinceDTO("C", "N"));
        assertSimpleCodeName(new RegionIdentificationDTO(), RegionIdentificationDTO.builder().code("C").name("N").build(), new RegionIdentificationDTO("C", "N"));
        assertSimpleCodeName(new StateDTO(), StateDTO.builder().code("C").name("N").build(), new StateDTO("C", "N"));

        UseTypeDTO useType = new UseTypeDTO();
        useType.setCode("C");
        useType.setDescription("D");
        assertEquals("C", useType.getCode());
        assertEquals("D", useType.getDescription());
        assertEquals("C", UseTypeDTO.builder().code("C").description("D").build().getCode());
        assertEquals("D", new UseTypeDTO("C", "D").getDescription());

        AuditDTO audit = new AuditDTO();
        audit.setCreationDate("2024-01-01");
        audit.setLastUpdateDate("2024-01-02");
        assertEquals("2024-01-01", audit.getCreationDate());
        assertEquals("2024-01-02", audit.getLastUpdateDate());
        assertEquals("2024-01-01", AuditDTO.builder().creationDate("2024-01-01").lastUpdateDate("2024-01-02").build().getCreationDate());
        assertEquals("2024-01-02", new AuditDTO("2024-01-01", "2024-01-02").getLastUpdateDate());

        ValidityPeriodDTO validity = new ValidityPeriodDTO();
        validity.setStartDate("START");
        validity.setEndDate("END");
        assertEquals("START", validity.getStartDate());
        assertEquals("END", validity.getEndDate());
        assertEquals("START", ValidityPeriodDTO.builder().startDate("START").endDate("END").build().getStartDate());
        assertEquals("END", new ValidityPeriodDTO("START", "END").getEndDate());
    }

    private void assertSimpleCodeName(Object empty, Object builder, Object allArgs) {
        if (empty instanceof CountryDTO dto) {
            dto.setCode("C"); dto.setName("N");
            assertEquals("C", dto.getCode()); assertEquals("N", dto.getName());
            assertEquals("C", ((CountryDTO) builder).getCode()); assertEquals("N", ((CountryDTO) allArgs).getName());
        } else if (empty instanceof CountyIdentificationDTO dto) {
            dto.setCode("C"); dto.setName("N");
            assertEquals("C", dto.getCode()); assertEquals("N", dto.getName());
            assertEquals("C", ((CountyIdentificationDTO) builder).getCode()); assertEquals("N", ((CountyIdentificationDTO) allArgs).getName());
        } else if (empty instanceof ProvinceDTO dto) {
            dto.setCode("C"); dto.setName("N");
            assertEquals("C", dto.getCode()); assertEquals("N", dto.getName());
            assertEquals("C", ((ProvinceDTO) builder).getCode()); assertEquals("N", ((ProvinceDTO) allArgs).getName());
        } else if (empty instanceof RegionIdentificationDTO dto) {
            dto.setCode("C"); dto.setName("N");
            assertEquals("C", dto.getCode()); assertEquals("N", dto.getName());
            assertEquals("C", ((RegionIdentificationDTO) builder).getCode()); assertEquals("N", ((RegionIdentificationDTO) allArgs).getName());
        } else if (empty instanceof StateDTO dto) {
            dto.setCode("C"); dto.setName("N");
            assertEquals("C", dto.getCode()); assertEquals("N", dto.getName());
            assertEquals("C", ((StateDTO) builder).getCode()); assertEquals("N", ((StateDTO) allArgs).getName());
        }
    }

    @Test
    void hrefDtosShouldHaveFullCoverage() {
        FirstDTO first = new FirstDTO();
        first.setHref("first");
        assertEquals("first", first.getHref());
        assertEquals("first", FirstDTO.builder().href("first").build().getHref());
        assertEquals("first", new FirstDTO("first").getHref());

        PrevDTO prev = new PrevDTO();
        prev.setHref("prev");
        assertEquals("prev", prev.getHref());
        assertEquals("prev", PrevDTO.builder().href("prev").build().getHref());
        assertEquals("prev", new PrevDTO("prev").getHref());

        NextDTO next = new NextDTO();
        next.setHref("next");
        assertEquals("next", next.getHref());
        assertEquals("next", NextDTO.builder().href("next").build().getHref());
        assertEquals("next", new NextDTO("next").getHref());

        LastDTO last = new LastDTO();
        last.setHref("last");
        assertEquals("last", last.getHref());
        assertEquals("last", LastDTO.builder().href("last").build().getHref());
        assertEquals("last", new LastDTO("last").getHref());

        WebAddressDTO web = new WebAddressDTO();
        web.setUrl("url");
        assertEquals("url", web.getUrl());
        assertEquals("url", WebAddressDTO.builder().url("url").build().getUrl());
        assertEquals("url", new WebAddressDTO("url").getUrl());

        ElectronicAddressDTO electronic = new ElectronicAddressDTO();
        electronic.setEmailAddress("a@b.com");
        assertEquals("a@b.com", electronic.getEmailAddress());
        assertEquals("a@b.com", ElectronicAddressDTO.builder().emailAddress("a@b.com").build().getEmailAddress());
        assertEquals("a@b.com", new ElectronicAddressDTO("a@b.com").getEmailAddress());
    }

    @Test
    void phoneAndBestContactTimeShouldHaveFullCoverage() {
        PhoneAddressDTO phone = new PhoneAddressDTO();
        phone.setMobileNumber("1");
        phone.setPhoneNumber("2");
        phone.setFaxNumber("3");
        phone.setInternationalCode("+57");
        phone.setExtension("99");

        assertEquals("1", phone.getMobileNumber());
        assertEquals("2", phone.getPhoneNumber());
        assertEquals("3", phone.getFaxNumber());
        assertEquals("+57", phone.getInternationalCode());
        assertEquals("99", phone.getExtension());

        PhoneAddressDTO phoneBuilder = PhoneAddressDTO.builder()
                .mobileNumber("1").phoneNumber("2").faxNumber("3").internationalCode("+57").extension("99").build();
        assertEquals("1", phoneBuilder.getMobileNumber());

        PhoneAddressDTO phoneAllArgs = new PhoneAddressDTO("1", "2", "3", "+57", "99");
        assertEquals("99", phoneAllArgs.getExtension());

        BestContactTimeDTO best = new BestContactTimeDTO();
        best.setFromDateTime("FROM");
        best.setToDateTime("TO");
        best.setBestTimeFrameCode("CODE");
        best.setBestTimeFrameDescription("DESC");

        assertEquals("FROM", best.getFromDateTime());
        assertEquals("TO", best.getToDateTime());
        assertEquals("CODE", best.getBestTimeFrameCode());
        assertEquals("DESC", best.getBestTimeFrameDescription());

        assertEquals("FROM", BestContactTimeDTO.builder()
                .fromDateTime("FROM").toDateTime("TO").bestTimeFrameCode("CODE").bestTimeFrameDescription("DESC")
                .build().getFromDateTime());

        assertEquals("DESC", new BestContactTimeDTO("FROM", "TO", "CODE", "DESC").getBestTimeFrameDescription());
    }

    @Test
    void linksAndRootDtosShouldHaveFullCoverage() {
        FirstDTO first = new FirstDTO("first");
        PrevDTO prev = new PrevDTO("prev");
        NextDTO next = new NextDTO("next");
        LastDTO last = new LastDTO("last");

        LinksDTO links = new LinksDTO();
        links.setFirst(first);
        links.setPrev(prev);
        links.setNext(next);
        links.setLast(last);

        assertSame(first, links.getFirst());
        assertSame(prev, links.getPrev());
        assertSame(next, links.getNext());
        assertSame(last, links.getLast());

        assertSame(first, LinksDTO.builder().first(first).prev(prev).next(next).last(last).build().getFirst());
        assertSame(last, new LinksDTO(first, prev, next, last).getLast());

        ContactPointDTO contactPoint = new ContactPointDTO();
        List<ContactPointDTO> contactPoints = List.of(contactPoint);

        ContactPointsResponseDTO response = new ContactPointsResponseDTO();
        response.setContactPoints(contactPoints);
        assertSame(contactPoints, response.getContactPoints());
        assertSame(contactPoints, ContactPointsResponseDTO.builder().contactPoints(contactPoints).build().getContactPoints());
        assertSame(contactPoints, new ContactPointsResponseDTO(contactPoints).getContactPoints());

        RootDTO root = new RootDTO();
        root.setContactPoints(contactPoints);
        root.setLinks(links);

        assertSame(contactPoints, root.getContactPoints());
        assertSame(links, root.getLinks());
        assertSame(contactPoints, RootDTO.builder().contactPoints(contactPoints).links(links).build().getContactPoints());
        assertSame(links, new RootDTO(contactPoints, links).getLinks());
    }

    @Test
    void contactPointDtoShouldHaveFullCoverage() {
        UseTypeDTO useType = new UseTypeDTO("C", "D");
        List<UseTypeDTO> useTypes = List.of(useType);
        PhoneAddressDTO phone = new PhoneAddressDTO();
        ElectronicAddressDTO electronic = new ElectronicAddressDTO();

        ContactPointDTO dto = new ContactPointDTO();

        dto.setContactPointId("ID");
        dto.setUseTypes(useTypes);
        dto.setPhoneAddress(phone);
        dto.setPreferredIndicator(true);
        dto.setPrimaryIndicator(false);
        dto.setElectronicAddress(electronic);
        dto.setPostalAddress(null);

        assertEquals("ID", dto.getContactPointId());
        assertSame(useTypes, dto.getUseTypes());
        assertSame(phone, dto.getPhoneAddress());
        assertTrue(dto.getPreferredIndicator());
        assertFalse(dto.getPrimaryIndicator());
        assertSame(electronic, dto.getElectronicAddress());
        assertNull(dto.getPostalAddress());

        ContactPointDTO builder = ContactPointDTO.builder()
                .contactPointId("ID")
                .useTypes(useTypes)
                .phoneAddress(phone)
                .preferredIndicator(true)
                .primaryIndicator(false)
                .electronicAddress(electronic)
                .postalAddress(null)
                .build();

        assertEquals("ID", builder.getContactPointId());

        ContactPointDTO allArgs = new ContactPointDTO("ID", useTypes, phone, true, false, electronic, null);
        assertEquals("ID", allArgs.getContactPointId());
    }

    @Test
    void postalAddressDtoShouldHaveFullCoverage() {
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

        assertEquals("FULL", PostalAddressDTO.builder()
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
                .build()
                .getFullAddress());
    }
}
Ojo: si ContactPointDTO te falla por postalAddress, es porque esa clase importa:
Java
com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO
no la del package response. Por eso en el test la dejé en null.