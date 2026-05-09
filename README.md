AuditDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {

    @Test
    void shouldTestAuditDTO() {

        AuditDTO dto = new AuditDTO();

        dto.setCreationDate("2024-01-01");
        dto.setLastUpdateDate("2024-01-02");

        assertEquals("2024-01-01", dto.getCreationDate());
        assertEquals("2024-01-02", dto.getLastUpdateDate());

        AuditDTO builder = AuditDTO.builder()
                .creationDate("2024-01-01")
                .lastUpdateDate("2024-01-02")
                .build();

        assertEquals("2024-01-01", builder.getCreationDate());

        AuditDTO allArgs = new AuditDTO("2024-01-01", "2024-01-02");

        assertEquals("2024-01-02", allArgs.getLastUpdateDate());
    }
}
BestContactTimeDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestContactTimeDTOTest {

    @Test
    void shouldTestBestContactTimeDTO() {

        BestContactTimeDTO dto = new BestContactTimeDTO();

        dto.setFromDateTime("FROM");
        dto.setToDateTime("TO");
        dto.setBestTimeFrameCode("CODE");
        dto.setBestTimeFrameDescription("DESC");

        assertEquals("FROM", dto.getFromDateTime());
        assertEquals("TO", dto.getToDateTime());
        assertEquals("CODE", dto.getBestTimeFrameCode());
        assertEquals("DESC", dto.getBestTimeFrameDescription());

        BestContactTimeDTO builder = BestContactTimeDTO.builder()
                .fromDateTime("FROM")
                .toDateTime("TO")
                .bestTimeFrameCode("CODE")
                .bestTimeFrameDescription("DESC")
                .build();

        assertEquals("FROM", builder.getFromDateTime());

        BestContactTimeDTO allArgs =
                new BestContactTimeDTO("FROM", "TO", "CODE", "DESC");

        assertEquals("DESC", allArgs.getBestTimeFrameDescription());
    }
}
ConsentDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsentDTOTest {

    @Test
    void shouldTestConsentDTO() {

        ConsentDTO dto = new ConsentDTO();

        dto.setLegitimateCode("LC");
        dto.setLegitimateDescription("LD");
        dto.setTreatmentCode("TC");
        dto.setTreatmentDescription("TD");
        dto.setPurposeCode("PC");
        dto.setPurposeDescription("PD");

        assertEquals("LC", dto.getLegitimateCode());
        assertEquals("LD", dto.getLegitimateDescription());
        assertEquals("TC", dto.getTreatmentCode());
        assertEquals("TD", dto.getTreatmentDescription());
        assertEquals("PC", dto.getPurposeCode());
        assertEquals("PD", dto.getPurposeDescription());

        ConsentDTO builder = ConsentDTO.builder()
                .legitimateCode("LC")
                .legitimateDescription("LD")
                .treatmentCode("TC")
                .treatmentDescription("TD")
                .purposeCode("PC")
                .purposeDescription("PD")
                .build();

        assertEquals("LC", builder.getLegitimateCode());

        ConsentDTO allArgs =
                new ConsentDTO("LC", "LD", "TC", "TD", "PC", "PD");

        assertEquals("PD", allArgs.getPurposeDescription());
    }
}
ContactPointDTOTest.java
Java
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointDTOTest {

    @Test
    void shouldTestContactPointDTO() {

        List<UseTypeDTO> useTypes =
                List.of(new UseTypeDTO("C", "D"));

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

        ContactPointDTO allArgs =
                new ContactPointDTO("ID", useTypes, phone, true, false, electronic, null);

        assertEquals("ID", allArgs.getContactPointId());
    }
}
ContactPointsResponseDTOTest.java
Java
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointsResponseDTOTest {

    @Test
    void shouldTestContactPointsResponseDTO() {

        List<ContactPointDTO> list =
                List.of(new ContactPointDTO());

        ContactPointsResponseDTO dto =
                new ContactPointsResponseDTO();

        dto.setContactPoints(list);

        assertSame(list, dto.getContactPoints());

        ContactPointsResponseDTO builder =
                ContactPointsResponseDTO.builder()
                        .contactPoints(list)
                        .build();

        assertSame(list, builder.getContactPoints());

        ContactPointsResponseDTO allArgs =
                new ContactPointsResponseDTO(list);

        assertSame(list, allArgs.getContactPoints());
    }
}
CountryDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryDTOTest {

    @Test
    void shouldTestCountryDTO() {

        CountryDTO dto = new CountryDTO();

        dto.setCode("CO");
        dto.setName("COLOMBIA");

        assertEquals("CO", dto.getCode());
        assertEquals("COLOMBIA", dto.getName());

        CountryDTO builder =
                CountryDTO.builder()
                        .code("CO")
                        .name("COLOMBIA")
                        .build();

        assertEquals("CO", builder.getCode());

        CountryDTO allArgs =
                new CountryDTO("CO", "COLOMBIA");

        assertEquals("COLOMBIA", allArgs.getName());
    }
}
CountyIdentificationDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountyIdentificationDTOTest {

    @Test
    void shouldTestCountyIdentificationDTO() {

        CountyIdentificationDTO dto =
                new CountyIdentificationDTO();

        dto.setCode("C");
        dto.setName("NAME");

        assertEquals("C", dto.getCode());
        assertEquals("NAME", dto.getName());

        CountyIdentificationDTO builder =
                CountyIdentificationDTO.builder()
                        .code("C")
                        .name("NAME")
                        .build();

        assertEquals("C", builder.getCode());

        CountyIdentificationDTO allArgs =
                new CountyIdentificationDTO("C", "NAME");

        assertEquals("NAME", allArgs.getName());
    }
}
ElectronicAddressDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressDTOTest {

    @Test
    void shouldTestElectronicAddressDTO() {

        ElectronicAddressDTO dto =
                new ElectronicAddressDTO();

        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com",
                dto.getEmailAddress());

        ElectronicAddressDTO builder =
                ElectronicAddressDTO.builder()
                        .emailAddress("test@mail.com")
                        .build();

        assertEquals("test@mail.com",
                builder.getEmailAddress());

        ElectronicAddressDTO allArgs =
                new ElectronicAddressDTO("test@mail.com");

        assertEquals("test@mail.com",
                allArgs.getEmailAddress());
    }
}
FirstDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstDTOTest {

    @Test
    void shouldTestFirstDTO() {

        FirstDTO dto = new FirstDTO();

        dto.setHref("first");

        assertEquals("first", dto.getHref());

        FirstDTO builder =
                FirstDTO.builder()
                        .href("first")
                        .build();

        assertEquals("first", builder.getHref());

        FirstDTO allArgs =
                new FirstDTO("first");

        assertEquals("first", allArgs.getHref());
    }
}
LastDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastDTOTest {

    @Test
    void shouldTestLastDTO() {

        LastDTO dto = new LastDTO();

        dto.setHref("last");

        assertEquals("last", dto.getHref());

        LastDTO builder =
                LastDTO.builder()
                        .href("last")
                        .build();

        assertEquals("last", builder.getHref());

        LastDTO allArgs =
                new LastDTO("last");

        assertEquals("last", allArgs.getHref());
    }
}
LinksDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinksDTOTest {

    @Test
    void shouldTestLinksDTO() {

        FirstDTO first = new FirstDTO("first");
        PrevDTO prev = new PrevDTO("prev");
        NextDTO next = new NextDTO("next");
        LastDTO last = new LastDTO("last");

        LinksDTO dto = new LinksDTO();

        dto.setFirst(first);
        dto.setPrev(prev);
        dto.setNext(next);
        dto.setLast(last);

        assertSame(first, dto.getFirst());
        assertSame(prev, dto.getPrev());
        assertSame(next, dto.getNext());
        assertSame(last, dto.getLast());

        LinksDTO builder =
                LinksDTO.builder()
                        .first(first)
                        .prev(prev)
                        .next(next)
                        .last(last)
                        .build();

        assertSame(first, builder.getFirst());

        LinksDTO allArgs =
                new LinksDTO(first, prev, next, last);

        assertSame(last, allArgs.getLast());
    }
}
NextDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextDTOTest {

    @Test
    void shouldTestNextDTO() {

        NextDTO dto = new NextDTO();

        dto.setHref("next");

        assertEquals("next", dto.getHref());

        NextDTO builder =
                NextDTO.builder()
                        .href("next")
                        .build();

        assertEquals("next", builder.getHref());

        NextDTO allArgs =
                new NextDTO("next");

        assertEquals("next", allArgs.getHref());
    }
}
PhoneAddressDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressDTOTest {

    @Test
    void shouldTestPhoneAddressDTO() {

        PhoneAddressDTO dto =
                new PhoneAddressDTO();

        dto.setMobileNumber("1");
        dto.setPhoneNumber("2");
        dto.setFaxNumber("3");
        dto.setInternationalCode("+57");
        dto.setExtension("99");

        assertEquals("1", dto.getMobileNumber());
        assertEquals("2", dto.getPhoneNumber());
        assertEquals("3", dto.getFaxNumber());
        assertEquals("+57", dto.getInternationalCode());
        assertEquals("99", dto.getExtension());

        PhoneAddressDTO builder =
                PhoneAddressDTO.builder()
                        .mobileNumber("1")
                        .phoneNumber("2")
                        .faxNumber("3")
                        .internationalCode("+57")
                        .extension("99")
                        .build();

        assertEquals("1", builder.getMobileNumber());

        PhoneAddressDTO allArgs =
                new PhoneAddressDTO("1", "2", "3", "+57", "99");

        assertEquals("99", allArgs.getExtension());
    }
}
PrevDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrevDTOTest {

    @Test
    void shouldTestPrevDTO() {

        PrevDTO dto = new PrevDTO();

        dto.setHref("prev");

        assertEquals("prev", dto.getHref());

        PrevDTO builder =
                PrevDTO.builder()
                        .href("prev")
                        .build();

        assertEquals("prev", builder.getHref());

        PrevDTO allArgs =
                new PrevDTO("prev");

        assertEquals("prev", allArgs.getHref());
    }
}
ProvinceDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceDTOTest {

    @Test
    void shouldTestProvinceDTO() {

        ProvinceDTO dto = new ProvinceDTO();

        dto.setCode("P");
        dto.setName("PROVINCE");

        assertEquals("P", dto.getCode());
        assertEquals("PROVINCE", dto.getName());

        ProvinceDTO builder =
                ProvinceDTO.builder()
                        .code("P")
                        .name("PROVINCE")
                        .build();

        assertEquals("P", builder.getCode());

        ProvinceDTO allArgs =
                new ProvinceDTO("P", "PROVINCE");

        assertEquals("PROVINCE", allArgs.getName());
    }
}
RegionIdentificationDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionIdentificationDTOTest {

    @Test
    void shouldTestRegionIdentificationDTO() {

        RegionIdentificationDTO dto =
                new RegionIdentificationDTO();

        dto.setCode("R");
        dto.setName("REGION");

        assertEquals("R", dto.getCode());
        assertEquals("REGION", dto.getName());

        RegionIdentificationDTO builder =
                RegionIdentificationDTO.builder()
                        .code("R")
                        .name("REGION")
                        .build();

        assertEquals("R", builder.getCode());

        RegionIdentificationDTO allArgs =
                new RegionIdentificationDTO("R", "REGION");

        assertEquals("REGION", allArgs.getName());
    }
}
RootDTOTest.java
Java
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RootDTOTest {

    @Test
    void shouldTestRootDTO() {

        List<ContactPointDTO> list =
                List.of(new ContactPointDTO());

        LinksDTO links = new LinksDTO();

        RootDTO dto = new RootDTO();

        dto.setContactPoints(list);
        dto.setLinks(links);

        assertSame(list, dto.getContactPoints());
        assertSame(links, dto.getLinks());

        RootDTO builder =
                RootDTO.builder()
                        .contactPoints(list)
                        .links(links)
                        .build();

        assertSame(list, builder.getContactPoints());

        RootDTO allArgs =
                new RootDTO(list, links);

        assertSame(links, allArgs.getLinks());
    }
}
StateDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateDTOTest {

    @Test
    void shouldTestStateDTO() {

        StateDTO dto = new StateDTO();

        dto.setCode("S");
        dto.setName("STATE");

        assertEquals("S", dto.getCode());
        assertEquals("STATE", dto.getName());

        StateDTO builder =
                StateDTO.builder()
                        .code("S")
                        .name("STATE")
                        .build();

        assertEquals("S", builder.getCode());

        StateDTO allArgs =
                new StateDTO("S", "STATE");

        assertEquals("STATE", allArgs.getName());
    }
}
UseTypeDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseTypeDTOTest {

    @Test
    void shouldTestUseTypeDTO() {

        UseTypeDTO dto = new UseTypeDTO();

        dto.setCode("C");
        dto.setDescription("DESC");

        assertEquals("C", dto.getCode());
        assertEquals("DESC", dto.getDescription());

        UseTypeDTO builder =
                UseTypeDTO.builder()
                        .code("C")
                        .description("DESC")
                        .build();

        assertEquals("C", builder.getCode());

        UseTypeDTO allArgs =
                new UseTypeDTO("C", "DESC");

        assertEquals("DESC", allArgs.getDescription());
    }
}
ValidityPeriodDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodDTOTest {

    @Test
    void shouldTestValidityPeriodDTO() {

        ValidityPeriodDTO dto =
                new ValidityPeriodDTO();

        dto.setStartDate("START");
        dto.setEndDate("END");

        assertEquals("START", dto.getStartDate());
        assertEquals("END", dto.getEndDate());

        ValidityPeriodDTO builder =
                ValidityPeriodDTO.builder()
                        .startDate("START")
                        .endDate("END")
                        .build();

        assertEquals("START", builder.getStartDate());

        ValidityPeriodDTO allArgs =
                new ValidityPeriodDTO("START", "END");

        assertEquals("END", allArgs.getEndDate());
    }
}
WebAddressDTOTest.java
Java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebAddressDTOTest {

    @Test
    void shouldTestWebAddressDTO() {

        WebAddressDTO dto =
                new WebAddressDTO();

        dto.setUrl("url");

        assertEquals("url", dto.getUrl());

        WebAddressDTO builder =
                WebAddressDTO.builder()
                        .url("url")
                        .build();

        assertEquals("url", builder.getUrl());

        WebAddressDTO allArgs =
                new WebAddressDTO("url");

        assertEquals("url", allArgs.getUrl());
    }
}