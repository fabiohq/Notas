package com.santander.bnc.bsn049.bncbsn049msprspctcntctptnt.domain.customer.contactpoint.response;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class PostalAddressDTOTest {

    @Test
    void shouldCreatePostalAddressDTO() {
        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setAddressLine("Main Street");
        dto.setPostalCode("1010");
        dto.setCity("Caracas");

        assertEquals("Main Street", dto.getAddressLine());
        assertEquals("1010", dto.getPostalCode());
        assertEquals("Caracas", dto.getCity());
    }
}

class ConsentDTOTest {

    @Test
    void shouldCreateConsentDTO() {
        ConsentDTO dto = new ConsentDTO();

        dto.setId("1");
        dto.setValue(true);

        assertEquals("1", dto.getId());
        assertTrue(dto.getValue());
    }
}

class LinksDTOTest {

    @Test
    void shouldCreateLinksDTO() {
        LinksDTO dto = new LinksDTO();

        dto.setSelf("/contact-point");

        assertEquals("/contact-point", dto.getSelf());
    }
}

class BestContactTimeDTOTest {

    @Test
    void shouldCreateBestContactTimeDTO() {
        BestContactTimeDTO dto = new BestContactTimeDTO();

        dto.setStartTime("08:00");
        dto.setEndTime("17:00");

        assertEquals("08:00", dto.getStartTime());
        assertEquals("17:00", dto.getEndTime());
    }
}

class RootDTOTest {

    @Test
    void shouldCreateRootDTO() {
        RootDTO dto = new RootDTO();

        ContactPointsResponseDTO response = new ContactPointsResponseDTO();
        dto.setData(response);

        assertNotNull(dto.getData());
    }
}

class RegionIdentificationDTOTest {

    @Test
    void shouldCreateRegionIdentificationDTO() {
        RegionIdentificationDTO dto = new RegionIdentificationDTO();

        dto.setCode("VE-DC");
        dto.setName("Distrito Capital");

        assertEquals("VE-DC", dto.getCode());
        assertEquals("Distrito Capital", dto.getName());
    }
}

class CountryDTOTest {

    @Test
    void shouldCreateCountryDTO() {
        CountryDTO dto = new CountryDTO();

        dto.setCode("VE");
        dto.setName("Venezuela");

        assertEquals("VE", dto.getCode());
        assertEquals("Venezuela", dto.getName());
    }
}

class AuditDTOTest {

    @Test
    void shouldCreateAuditDTO() {
        AuditDTO dto = new AuditDTO();

        dto.setUser("fabio");
        dto.setDate("2025-01-01");

        assertEquals("fabio", dto.getUser());
        assertEquals("2025-01-01", dto.getDate());
    }
}

class StateDTOTest {

    @Test
    void shouldCreateStateDTO() {
        StateDTO dto = new StateDTO();

        dto.setCode("DC");
        dto.setDescription("Distrito Capital");

        assertEquals("DC", dto.getCode());
        assertEquals("Distrito Capital", dto.getDescription());
    }
}

class ProvinceDTOTest {

    @Test
    void shouldCreateProvinceDTO() {
        ProvinceDTO dto = new ProvinceDTO();

        dto.setCode("01");
        dto.setDescription("Capital Province");

        assertEquals("01", dto.getCode());
        assertEquals("Capital Province", dto.getDescription());
    }
}

class CountyIdentificationDTOTest {

    @Test
    void shouldCreateCountyIdentificationDTO() {
        CountyIdentificationDTO dto = new CountyIdentificationDTO();

        dto.setCode("CNT01");
        dto.setName("County Test");

        assertEquals("CNT01", dto.getCode());
        assertEquals("County Test", dto.getName());
    }
}

class ValidityPeriodDTOTest {

    @Test
    void shouldCreateValidityPeriodDTO() {
        ValidityPeriodDTO dto = new ValidityPeriodDTO();

        dto.setStartDate("2025-01-01");
        dto.setEndDate("2025-12-31");

        assertEquals("2025-01-01", dto.getStartDate());
        assertEquals("2025-12-31", dto.getEndDate());
    }
}

class PhoneAddressDTOTest {

    @Test
    void shouldCreatePhoneAddressDTO() {
        PhoneAddressDTO dto = new PhoneAddressDTO();

        dto.setPhoneNumber("04141234567");
        dto.setCountryCode("58");

        assertEquals("04141234567", dto.getPhoneNumber());
        assertEquals("58", dto.getCountryCode());
    }
}

class FirstDTOTest {

    @Test
    void shouldCreateFirstDTO() {
        FirstDTO dto = new FirstDTO();

        dto.setHref("/first");

        assertEquals("/first", dto.getHref());
    }
}

class LastDTOTest {

    @Test
    void shouldCreateLastDTO() {
        LastDTO dto = new LastDTO();

        dto.setHref("/last");

        assertEquals("/last", dto.getHref());
    }
}

class NextDTOTest {

    @Test
    void shouldCreateNextDTO() {
        NextDTO dto = new NextDTO();

        dto.setHref("/next");

        assertEquals("/next", dto.getHref());
    }
}

class WebAddressDTOTest {

    @Test
    void shouldCreateWebAddressDTO() {
        WebAddressDTO dto = new WebAddressDTO();

        dto.setUrl("https://santander.com");

        assertEquals("https://santander.com", dto.getUrl());
    }
}

class PrevDTOTest {

    @Test
    void shouldCreatePrevDTO() {
        PrevDTO dto = new PrevDTO();

        dto.setHref("/prev");

        assertEquals("/prev", dto.getHref());
    }
}

class UseTypeDTOTest {

    @Test
    void shouldCreateUseTypeDTO() {
        UseTypeDTO dto = new UseTypeDTO();

        dto.setCode("HOME");
        dto.setDescription("Home phone");

        assertEquals("HOME", dto.getCode());
        assertEquals("Home phone", dto.getDescription());
    }
}

class ContactPointDTOTest {

    @Test
    void shouldCreateContactPointDTO() {
        ContactPointDTO dto = new ContactPointDTO();

        dto.setId("CP001");
        dto.setType("PHONE");

        assertEquals("CP001", dto.getId());
        assertEquals("PHONE", dto.getType());
    }
}

class ContactPointsResponseDTOTest {

    @Test
    void shouldCreateContactPointsResponseDTO() {
        ContactPointsResponseDTO dto = new ContactPointsResponseDTO();

        ContactPointDTO pointDTO = new ContactPointDTO();
        pointDTO.setId("1");

        dto.setContactPoints(Collections.singletonList(pointDTO));

        assertNotNull(dto.getContactPoints());
        assertEquals(1, dto.getContactPoints().size());
    }
}

class ElectronicAddressDTOTest {

    @Test
    void shouldCreateElectronicAddressDTO() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO();

        dto.setEmail("test@santander.com");

        assertEquals("test@santander.com", dto.getEmail());
    }
}

