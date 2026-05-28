
// Package de pruebas sugerido: // src/test/java/com/santander/bnc/bsn049/bncbsn049msprspctcntctpnt/domain/customer/contactpoint/put/request/ // // Cada bloque corresponde a una clase de test independiente.

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointsRequestDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    PostalAddressDTO postalAddress = new PostalAddressDTO();
    PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
    ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();

    ContactPointsRequestDTO dto = new ContactPointsRequestDTO();
    dto.setPostalAddress(postalAddress);
    dto.setPhoneAddress(phoneAddress);
    dto.setElectronicAddress(electronicAddress);

    assertSame(postalAddress, dto.getPostalAddress());
    assertSame(phoneAddress, dto.getPhoneAddress());
    assertSame(electronicAddress, dto.getElectronicAddress());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    PostalAddressDTO postalAddress = new PostalAddressDTO();
    PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
    ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();

    ContactPointsRequestDTO dto = new ContactPointsRequestDTO(postalAddress, phoneAddress, electronicAddress);

    assertSame(postalAddress, dto.getPostalAddress());
    assertSame(phoneAddress, dto.getPhoneAddress());
    assertSame(electronicAddress, dto.getElectronicAddress());
}

@Test
void shouldCreateWithBuilder() {
    PostalAddressDTO postalAddress = new PostalAddressDTO();
    PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
    ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();

    ContactPointsRequestDTO dto = ContactPointsRequestDTO.builder()
            .postalAddress(postalAddress)
            .phoneAddress(phoneAddress)
            .electronicAddress(electronicAddress)
            .build();

    assertSame(postalAddress, dto.getPostalAddress());
    assertSame(phoneAddress, dto.getPhoneAddress());
    assertSame(electronicAddress, dto.getElectronicAddress());
}

}

class CountryDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    CountryDTO dto = new CountryDTO();
    dto.setCode("CO");
    dto.setName("Colombia");

    assertEquals("CO", dto.getCode());
    assertEquals("Colombia", dto.getName());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    CountryDTO dto = new CountryDTO("CO", "Colombia");

    assertEquals("CO", dto.getCode());
    assertEquals("Colombia", dto.getName());
}

@Test
void shouldCreateWithBuilder() {
    CountryDTO dto = CountryDTO.builder()
            .code("CO")
            .name("Colombia")
            .build();

    assertEquals("CO", dto.getCode());
    assertEquals("Colombia", dto.getName());
}

}

class ElectronicAddressDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    ElectronicAddressDTO dto = new ElectronicAddressDTO();
    dto.setEmailAddress("test@santander.com");

    assertEquals("test@santander.com", dto.getEmailAddress());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    ElectronicAddressDTO dto = new ElectronicAddressDTO("test@santander.com");

    assertEquals("test@santander.com", dto.getEmailAddress());
}

@Test
void shouldCreateWithBuilder() {
    ElectronicAddressDTO dto = ElectronicAddressDTO.builder()
            .emailAddress("test@santander.com")
            .build();

    assertEquals("test@santander.com", dto.getEmailAddress());
}

}

class PhoneAddressDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    PhoneAddressDTO dto = new PhoneAddressDTO();
    dto.setMobileNumber("3001234567");
    dto.setPhoneNumber("6011234567");
    dto.setFaxNumber("6017654321");
    dto.setInternationalCode("+57");
    dto.setExtension("123");

    assertEquals("3001234567", dto.getMobileNumber());
    assertEquals("6011234567", dto.getPhoneNumber());
    assertEquals("6017654321", dto.getFaxNumber());
    assertEquals("+57", dto.getInternationalCode());
    assertEquals("123", dto.getExtension());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    PhoneAddressDTO dto = new PhoneAddressDTO(
            "3001234567",
            "6011234567",
            "6017654321",
            "+57",
            "123"
    );

    assertEquals("3001234567", dto.getMobileNumber());
    assertEquals("6011234567", dto.getPhoneNumber());
    assertEquals("6017654321", dto.getFaxNumber());
    assertEquals("+57", dto.getInternationalCode());
    assertEquals("123", dto.getExtension());
}

@Test
void shouldCreateWithBuilder() {
    PhoneAddressDTO dto = PhoneAddressDTO.builder()
            .mobileNumber("3001234567")
            .phoneNumber("6011234567")
            .faxNumber("6017654321")
            .internationalCode("+57")
            .extension("123")
            .build();

    assertEquals("3001234567", dto.getMobileNumber());
    assertEquals("6011234567", dto.getPhoneNumber());
    assertEquals("6017654321", dto.getFaxNumber());
    assertEquals("+57", dto.getInternationalCode());
    assertEquals("123", dto.getExtension());
}

}

class PostalAddressDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    StateDTO state = new StateDTO();
    CountryDTO country = new CountryDTO();
    List<String> foreignAddressLines = List.of("line1", "line2");

    PostalAddressDTO dto = new PostalAddressDTO();
    dto.setFullAddress("Calle 1 # 2-3");
    dto.setFormatCode("FMT");
    dto.setFormatDescription("Format description");
    dto.setAddressValidated(Boolean.TRUE);
    dto.setMatchId("MATCH-1");
    dto.setStreetTypeCode("CL");
    dto.setStreetName("Street name");
    dto.setSecondaryStreetName("Secondary street");
    dto.setStreetBuildingIdentification("12A");
    dto.setMailDeliverySubLocation("Sub location");
    dto.setBuildingName("Building");
    dto.setFloor("5");
    dto.setDetailCode("DETAIL");
    dto.setUnitType("APT");
    dto.setUnitNumber("501");
    dto.setPremise("Premise");
    dto.setAlternativePremise("Alternative premise");
    dto.setDepartment("Department");
    dto.setSubDepartment("Sub department");
    dto.setPostCodeIdentification("110111");
    dto.setTown("Town code");
    dto.setTownName("Bogota");
    dto.setState(state);
    dto.setDistrictName("District");
    dto.setSecondaryDistrictName("Secondary district");
    dto.setMailingInstructions("Instructions");
    dto.setCountry(country);
    dto.setMilitary("Military");
    dto.setPostOfficeBox("PO BOX");
    dto.setPostBoxTypeCode("PBT");
    dto.setPostBoxTypeDescription("Post box type");
    dto.setForeignAddressLines(foreignAddressLines);
    dto.setZipCode("110111");
    dto.setZip4Code("1234");
    dto.setRuralTypeCode("RUR");
    dto.setRuralTypeDescription("Rural type");
    dto.setRuralNumber("R-1");

    assertEquals("Calle 1 # 2-3", dto.getFullAddress());
    assertEquals("FMT", dto.getFormatCode());
    assertEquals("Format description", dto.getFormatDescription());
    assertTrue(dto.getAddressValidated());
    assertEquals("MATCH-1", dto.getMatchId());
    assertEquals("CL", dto.getStreetTypeCode());
    assertEquals("Street name", dto.getStreetName());
    assertEquals("Secondary street", dto.getSecondaryStreetName());
    assertEquals("12A", dto.getStreetBuildingIdentification());
    assertEquals("Sub location", dto.getMailDeliverySubLocation());
    assertEquals("Building", dto.getBuildingName());
    assertEquals("5", dto.getFloor());
    assertEquals("DETAIL", dto.getDetailCode());
    assertEquals("APT", dto.getUnitType());
    assertEquals("501", dto.getUnitNumber());
    assertEquals("Premise", dto.getPremise());
    assertEquals("Alternative premise", dto.getAlternativePremise());
    assertEquals("Department", dto.getDepartment());
    assertEquals("Sub department", dto.getSubDepartment());
    assertEquals("110111", dto.getPostCodeIdentification());
    assertEquals("Town code", dto.getTown());
    assertEquals("Bogota", dto.getTownName());
    assertSame(state, dto.getState());
    assertEquals("District", dto.getDistrictName());
    assertEquals("Secondary district", dto.getSecondaryDistrictName());
    assertEquals("Instructions", dto.getMailingInstructions());
    assertSame(country, dto.getCountry());
    assertEquals("Military", dto.getMilitary());
    assertEquals("PO BOX", dto.getPostOfficeBox());
    assertEquals("PBT", dto.getPostBoxTypeCode());
    assertEquals("Post box type", dto.getPostBoxTypeDescription());
    assertEquals(foreignAddressLines, dto.getForeignAddressLines());
    assertEquals("110111", dto.getZipCode());
    assertEquals("1234", dto.getZip4Code());
    assertEquals("RUR", dto.getRuralTypeCode());
    assertEquals("Rural type", dto.getRuralTypeDescription());
    assertEquals("R-1", dto.getRuralNumber());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    StateDTO state = new StateDTO("11", "Bogota D.C.");
    CountryDTO country = new CountryDTO("CO", "Colombia");
    List<String> foreignAddressLines = List.of("line1", "line2");

    PostalAddressDTO dto = new PostalAddressDTO(
            "Calle 1 # 2-3",
            "FMT",
            "Format description",
            Boolean.TRUE,
            "MATCH-1",
            "CL",
            "Street name",
            "Secondary street",
            "12A",
            "Sub location",
            "Building",
            "5",
            "DETAIL",
            "APT",
            "501",
            "Premise",
            "Alternative premise",
            "Department",
            "Sub department",
            "110111",
            "Town code",
            "Bogota",
            state,
            "District",
            "Secondary district",
            "Instructions",
            country,
            "Military",
            "PO BOX",
            "PBT",
            "Post box type",
            foreignAddressLines,
            "110111",
            "1234",
            "RUR",
            "Rural type",
            "R-1"
    );

    assertEquals("Calle 1 # 2-3", dto.getFullAddress());
    assertEquals("FMT", dto.getFormatCode());
    assertEquals("Format description", dto.getFormatDescription());
    assertTrue(dto.getAddressValidated());
    assertEquals("MATCH-1", dto.getMatchId());
    assertEquals("CL", dto.getStreetTypeCode());
    assertEquals("Street name", dto.getStreetName());
    assertEquals("Secondary street", dto.getSecondaryStreetName());
    assertEquals("12A", dto.getStreetBuildingIdentification());
    assertEquals("Sub location", dto.getMailDeliverySubLocation());
    assertEquals("Building", dto.getBuildingName());
    assertEquals("5", dto.getFloor());
    assertEquals("DETAIL", dto.getDetailCode());
    assertEquals("APT", dto.getUnitType());
    assertEquals("501", dto.getUnitNumber());
    assertEquals("Premise", dto.getPremise());
    assertEquals("Alternative premise", dto.getAlternativePremise());
    assertEquals("Department", dto.getDepartment());
    assertEquals("Sub department", dto.getSubDepartment());
    assertEquals("110111", dto.getPostCodeIdentification());
    assertEquals("Town code", dto.getTown());
    assertEquals("Bogota", dto.getTownName());
    assertSame(state, dto.getState());
    assertEquals("District", dto.getDistrictName());
    assertEquals("Secondary district", dto.getSecondaryDistrictName());
    assertEquals("Instructions", dto.getMailingInstructions());
    assertSame(country, dto.getCountry());
    assertEquals("Military", dto.getMilitary());
    assertEquals("PO BOX", dto.getPostOfficeBox());
    assertEquals("PBT", dto.getPostBoxTypeCode());
    assertEquals("Post box type", dto.getPostBoxTypeDescription());
    assertEquals(foreignAddressLines, dto.getForeignAddressLines());
    assertEquals("110111", dto.getZipCode());
    assertEquals("1234", dto.getZip4Code());
    assertEquals("RUR", dto.getRuralTypeCode());
    assertEquals("Rural type", dto.getRuralTypeDescription());
    assertEquals("R-1", dto.getRuralNumber());
}

@Test
void shouldCreateWithBuilder() {
    StateDTO state = StateDTO.builder().code("11").name("Bogota D.C.").build();
    CountryDTO country = CountryDTO.builder().code("CO").name("Colombia").build();
    List<String> foreignAddressLines = List.of("line1", "line2");

    PostalAddressDTO dto = PostalAddressDTO.builder()
            .fullAddress("Calle 1 # 2-3")
            .formatCode("FMT")
            .formatDescription("Format description")
            .isAddressValidated(Boolean.TRUE)
            .matchId("MATCH-1")
            .streetTypeCode("CL")
            .streetName("Street name")
            .secondaryStreetName("Secondary street")
            .streetBuildingIdentification("12A")
            .mailDeliverySubLocation("Sub location")
            .buildingName("Building")
            .floor("5")
            .detailCode("DETAIL")
            .unitType("APT")
            .unitNumber("501")
            .premise("Premise")
            .alternativePremise("Alternative premise")
            .department("Department")
            .subDepartment("Sub department")
            .postCodeIdentification("110111")
            .town("Town code")
            .townName("Bogota")
            .state(state)
            .districtName("District")
            .secondaryDistrictName("Secondary district")
            .mailingInstructions("Instructions")
            .country(country)
            .military("Military")
            .postOfficeBox("PO BOX")
            .postBoxTypeCode("PBT")
            .postBoxTypeDescription("Post box type")
            .foreignAddressLines(foreignAddressLines)
            .zipCode("110111")
            .zip4Code("1234")
            .ruralTypeCode("RUR")
            .ruralTypeDescription("Rural type")
            .ruralNumber("R-1")
            .build();

    assertEquals("Calle 1 # 2-3", dto.getFullAddress());
    assertEquals("FMT", dto.getFormatCode());
    assertEquals("Format description", dto.getFormatDescription());
    assertTrue(dto.getAddressValidated());
    assertEquals("MATCH-1", dto.getMatchId());
    assertEquals("CL", dto.getStreetTypeCode());
    assertEquals("Street name", dto.getStreetName());
    assertEquals("Secondary street", dto.getSecondaryStreetName());
    assertEquals("12A", dto.getStreetBuildingIdentification());
    assertEquals("Sub location", dto.getMailDeliverySubLocation());
    assertEquals("Building", dto.getBuildingName());
    assertEquals("5", dto.getFloor());
    assertEquals("DETAIL", dto.getDetailCode());
    assertEquals("APT", dto.getUnitType());
    assertEquals("501", dto.getUnitNumber());
    assertEquals("Premise", dto.getPremise());
    assertEquals("Alternative premise", dto.getAlternativePremise());
    assertEquals("Department", dto.getDepartment());
    assertEquals("Sub department", dto.getSubDepartment());
    assertEquals("110111", dto.getPostCodeIdentification());
    assertEquals("Town code", dto.getTown());
    assertEquals("Bogota", dto.getTownName());
    assertSame(state, dto.getState());
    assertEquals("District", dto.getDistrictName());
    assertEquals("Secondary district", dto.getSecondaryDistrictName());
    assertEquals("Instructions", dto.getMailingInstructions());
    assertSame(country, dto.getCountry());
    assertEquals("Military", dto.getMilitary());
    assertEquals("PO BOX", dto.getPostOfficeBox());
    assertEquals("PBT", dto.getPostBoxTypeCode());
    assertEquals("Post box type", dto.getPostBoxTypeDescription());
    assertEquals(foreignAddressLines, dto.getForeignAddressLines());
    assertEquals("110111", dto.getZipCode());
    assertEquals("1234", dto.getZip4Code());
    assertEquals("RUR", dto.getRuralTypeCode());
    assertEquals("Rural type", dto.getRuralTypeDescription());
    assertEquals("R-1", dto.getRuralNumber());
}

}

class StateDTOTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    StateDTO dto = new StateDTO();
    dto.setCode("11");
    dto.setName("Bogota D.C.");

    assertEquals("11", dto.getCode());
    assertEquals("Bogota D.C.", dto.getName());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    StateDTO dto = new StateDTO("11", "Bogota D.C.");

    assertEquals("11", dto.getCode());
    assertEquals("Bogota D.C.", dto.getName());
}

@Test
void shouldCreateWithBuilder() {
    StateDTO dto = StateDTO.builder()
            .code("11")
            .name("Bogota D.C.")
            .build();

    assertEquals("11", dto.getCode());
    assertEquals("Bogota D.C.", dto.getName());
}

}
