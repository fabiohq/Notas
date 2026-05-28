
package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.BankDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO; import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO; import org.junit.jupiter.api.Test;

import java.util.ArrayList; import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

@Test
void shouldCoverPersonDTOCompletely() {
    PersonNameDTO personName = new PersonNameDTO();
    PlaceOfBirthDTO placeOfBirth = new PlaceOfBirthDTO();
    CodeNameDTO countryOfResidence = new CodeNameDTO();
    CodeNameDTO firstNationality = new CodeNameDTO();
    CodeNameDTO secondNationality = new CodeNameDTO();
    PublicOfficeInformationDTO publicOfficeInformation = new PublicOfficeInformationDTO();
    EmploymentInformationDTO employmentInformation = new EmploymentInformationDTO();
    CodeNameDTO preferredLanguage = new CodeNameDTO();
    List<DocumentDTO> documents = List.of(new DocumentDTO());
    DocumentDTO document = new DocumentDTO();

    PersonDTO dto = new PersonDTO();

    dto.setPersonName(personName);
    dto.setMotherName("Mother");
    dto.setFatherName("Father");
    dto.setGenderCode("M");
    dto.setGenderDescription("Male");
    dto.setBirthDate("2000-01-01");
    dto.setPlaceOfBirth(placeOfBirth);
    dto.setCountryOfResidence(countryOfResidence);
    dto.setForeignTaxIndicator("NO");
    dto.setFirstNationality(firstNationality);
    dto.setSecondNationality(secondNationality);
    dto.setResidentialStatusCode("RES");
    dto.setResidentialStatusDescription("Resident");
    dto.setCivilStatusCode("S");
    dto.setCivilStatusDescription("Single");
    dto.setPublicOfficeInformation(publicOfficeInformation);
    dto.setDeathDate("2099-01-01");
    dto.setEmployeeIndicator(Boolean.TRUE);
    dto.setStaffCode("STAFF");
    dto.setStaffDescription("Staff description");
    dto.setLegallyIncapacitated(Boolean.FALSE);
    dto.setLegallyCapableMinor(Boolean.FALSE);
    dto.setDiplomatic(Boolean.FALSE);
    dto.setEducationalLevelCode("UNI");
    dto.setEducationalLevelDescription("University");
    dto.setAccountingSectorCode("ACC");
    dto.setAccountingSectorDescription("Accounting");
    dto.setEmploymentInformation(employmentInformation);
    dto.setPreferredLanguage(preferredLanguage);
    dto.setDocuments(documents);
    dto.setDocument(document);

    assertSame(personName, dto.getPersonName());
    assertEquals("Mother", dto.getMotherName());
    assertEquals("Father", dto.getFatherName());
    assertEquals("M", dto.getGenderCode());
    assertEquals("Male", dto.getGenderDescription());
    assertEquals("2000-01-01", dto.getBirthDate());
    assertSame(placeOfBirth, dto.getPlaceOfBirth());
    assertSame(countryOfResidence, dto.getCountryOfResidence());
    assertEquals("NO", dto.getForeignTaxIndicator());
    assertSame(firstNationality, dto.getFirstNationality());
    assertSame(secondNationality, dto.getSecondNationality());
    assertEquals("RES", dto.getResidentialStatusCode());
    assertEquals("Resident", dto.getResidentialStatusDescription());
    assertEquals("S", dto.getCivilStatusCode());
    assertEquals("Single", dto.getCivilStatusDescription());
    assertSame(publicOfficeInformation, dto.getPublicOfficeInformation());
    assertEquals("2099-01-01", dto.getDeathDate());
    assertTrue(dto.getEmployeeIndicator());
    assertEquals("STAFF", dto.getStaffCode());
    assertEquals("Staff description", dto.getStaffDescription());
    assertFalse(dto.getLegallyIncapacitated());
    assertFalse(dto.getLegallyCapableMinor());
    assertFalse(dto.getDiplomatic());
    assertEquals("UNI", dto.getEducationalLevelCode());
    assertEquals("University", dto.getEducationalLevelDescription());
    assertEquals("ACC", dto.getAccountingSectorCode());
    assertEquals("Accounting", dto.getAccountingSectorDescription());
    assertSame(employmentInformation, dto.getEmploymentInformation());
    assertSame(preferredLanguage, dto.getPreferredLanguage());
    assertEquals(documents, dto.getDocuments());
    assertSame(document, dto.getDocument());

    PersonDTO builder = PersonDTO.builder()
            .personName(personName)
            .motherName("Mother")
            .fatherName("Father")
            .genderCode("M")
            .genderDescription("Male")
            .birthDate("2000-01-01")
            .document(document)
            .employeeIndicator(Boolean.TRUE)
            .build();

    assertNotNull(builder);
}

}

class OrganizationDTOTest {

@Test
void shouldCoverOrganizationDTOCompletely() {
    PlaceOfRegistrationDTO place = new PlaceOfRegistrationDTO();
    OrganizationNameDTO organizationName = new OrganizationNameDTO();
    List<DocumentDTO> documents = List.of(new DocumentDTO());
    CodeNameDTO country = new CodeNameDTO();
    EconomicActivityDTO economicActivity = new EconomicActivityDTO();
    CodeNameDTO preferredLanguage = new CodeNameDTO();

    OrganizationDTO dto = new OrganizationDTO();

    dto.setRegistrationDate("2026-01-01");
    dto.setEntityDisolutionDate("2099-12-31");
    dto.setResidentialStatusCode("RES");
    dto.setResidentialStatusDescription("Resident");
    dto.setForeignTaxIndicator("NO");
    dto.setPlaceOfRegistration(place);
    dto.setOrganizationName(organizationName);
    dto.setTypeCode("TYPE");
    dto.setTypeDescription("Type description");
    dto.setSubtypeCode("SUB");
    dto.setSubtypeDescription("Subtype description");
    dto.setDocuments(documents);
    dto.setCountryOfOperation(country);
    dto.setAccountingSectorCode("ACC");
    dto.setAccountingSectorDescription("Accounting");
    dto.setEconomicActivity(economicActivity);
    dto.setPreferredLanguage(preferredLanguage);

    assertEquals("2026-01-01", dto.getRegistrationDate());
    assertEquals("2099-12-31", dto.getEntityDisolutionDate());
    assertEquals("RES", dto.getResidentialStatusCode());
    assertEquals("Resident", dto.getResidentialStatusDescription());
    assertEquals("NO", dto.getForeignTaxIndicator());
    assertSame(place, dto.getPlaceOfRegistration());
    assertSame(organizationName, dto.getOrganizationName());
    assertEquals("TYPE", dto.getTypeCode());
    assertEquals("Type description", dto.getTypeDescription());
    assertEquals("SUB", dto.getSubtypeCode());
    assertEquals("Subtype description", dto.getSubtypeDescription());
    assertEquals(documents, dto.getDocuments());
    assertSame(country, dto.getCountryOfOperation());
    assertEquals("ACC", dto.getAccountingSectorCode());
    assertEquals("Accounting", dto.getAccountingSectorDescription());
    assertSame(economicActivity, dto.getEconomicActivity());
    assertSame(preferredLanguage, dto.getPreferredLanguage());

    OrganizationDTO builder = OrganizationDTO.builder()
            .registrationDate("2026-01-01")
            .organizationName(organizationName)
            .build();

    assertNotNull(builder);
}

}

class CustomerDetailsResponseDTOTest {

@Test
void shouldCoverCustomerDetailsResponseDTOCompletely() {
    PersonDTO person = new PersonDTO();
    OrganizationDTO organization = new OrganizationDTO();
    List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());
    BankDTO bank = new BankDTO();
    List<DataOriginDTO> dataOrigins = List.of(new DataOriginDTO());

    CustomerDetailsResponseDTO dto = new CustomerDetailsResponseDTO();

    dto.setPerson(person);
    dto.setOrganization(organization);
    dto.setContactPoints(contactPoints);
    dto.setHighConfidentialityIndicator(Boolean.TRUE);
    dto.setPendingExCustomer(Boolean.FALSE);
    dto.setConfidentialityLevel("HIGH");
    dto.setBank(bank);
    dto.setDataOrigins(dataOrigins);
    dto.setStructuralSegmentCode("SEG");
    dto.setStructuralSegmentDescription("Segment");
    dto.setStructuralSubsegmentCode("SUB");
    dto.setStructuralSubsegmentDescription("Subsegment");

    assertSame(person, dto.getPerson());
    assertSame(organization, dto.getOrganization());
    assertEquals(contactPoints, dto.getContactPoints());
    assertTrue(dto.getHighConfidentialityIndicator());
    assertFalse(dto.getPendingExCustomer());
    assertEquals("HIGH", dto.getConfidentialityLevel());
    assertSame(bank, dto.getBank());
    assertEquals(dataOrigins, dto.getDataOrigins());
    assertEquals("SEG", dto.getStructuralSegmentCode());
    assertEquals("Segment", dto.getStructuralSegmentDescription());
    assertEquals("SUB", dto.getStructuralSubsegmentCode());
    assertEquals("Subsegment", dto.getStructuralSubsegmentDescription());

    CustomerDetailsResponseDTO builder = CustomerDetailsResponseDTO.builder()
            .person(person)
            .organization(organization)
            .build();

    assertNotNull(builder);
}

}

class EmploymentInformationDTOTest {

@Test
void shouldCoverEmploymentInformationDTOCompletely() {
    EconomicActivityDTO economicActivity = new EconomicActivityDTO();

    EmploymentInformationDTO dto = new EmploymentInformationDTO();

    dto.setStatusCode("ACTIVE");
    dto.setStatusDescription("Active");
    dto.setEconomicActivity(economicActivity);
    dto.setOccupationCode("DEV");
    dto.setOccupationDescription("Developer");
    dto.setSubActivityCode("SUB");
    dto.setSubActivityDescription("Sub activity");
    dto.setSubActivityComments("Comments");

    assertEquals("ACTIVE", dto.getStatusCode());
    assertEquals("Active", dto.getStatusDescription());
    assertSame(economicActivity, dto.getEconomicActivity());
    assertEquals("DEV", dto.getOccupationCode());
    assertEquals("Developer", dto.getOccupationDescription());
    assertEquals("SUB", dto.getSubActivityCode());
    assertEquals("Sub activity", dto.getSubActivityDescription());
    assertEquals("Comments", dto.getSubActivityComments());

    EmploymentInformationDTO builder = EmploymentInformationDTO.builder()
            .statusCode("ACTIVE")
            .build();

    assertNotNull(builder);
}

}

class PersonNameDTOTest {

@Test
void shouldCoverPersonNameDTOCompletely() {
    List<String> aliases = List.of("alias1", "alias2");

    PersonNameDTO dto = new PersonNameDTO();

    dto.setNamePrefixCode("MR");
    dto.setNamePrefixDescription("Mister");
    dto.setGivenName("Fabio");
    dto.setMiddleName("Andres");
    dto.setLastName("Hernandez");
    dto.setSecondLastName("Perez");
    dto.setNameSuffixCode("JR");
    dto.setNameSuffixDescription("Junior");
    dto.setFullName("Fabio Andres Hernandez Perez");
    dto.setBirthName("Fabio");
    dto.setAliases(aliases);

    assertEquals("MR", dto.getNamePrefixCode());
    assertEquals("Mister", dto.getNamePrefixDescription());
    assertEquals("Fabio", dto.getGivenName());
    assertEquals("Andres", dto.getMiddleName());
    assertEquals("Hernandez", dto.getLastName());
    assertEquals("Perez", dto.getSecondLastName());
    assertEquals("JR", dto.getNameSuffixCode());
    assertEquals("Junior", dto.getNameSuffixDescription());
    assertEquals("Fabio Andres Hernandez Perez", dto.getFullName());
    assertEquals("Fabio", dto.getBirthName());
    assertEquals(aliases, dto.getAliases());

    PersonNameDTO builder = PersonNameDTO.builder()
            .givenName("Fabio")
            .build();

    assertNotNull(builder);
}

}

class ValidityPeriodDTOTest {

@Test
void shouldCoverValidityPeriodDTOCompletely() {
    ValidityPeriodDTO dto = new ValidityPeriodDTO();

    dto.setStartDate("2026-01-01");
    dto.setEndDate("2026-12-31");

    assertEquals("2026-01-01", dto.getStartDate());
    assertEquals("2026-12-31", dto.getEndDate());

    ValidityPeriodDTO allArgs = new ValidityPeriodDTO("2026-01-01", "2026-12-31");

    assertEquals("2026-01-01", allArgs.getStartDate());
    assertEquals("2026-12-31", allArgs.getEndDate());

    ValidityPeriodDTO builder = ValidityPeriodDTO.builder()
            .startDate("2026-01-01")
            .endDate("2026-12-31")
            .build();

    assertEquals("2026-01-01", builder.getStartDate());
    assertEquals("2026-12-31", builder.getEndDate());
}

}

class EconomicActivityDTOTest {

@Test
void shouldCoverEconomicActivityDTOCompletely() {
    EconomicActivityDTO dto = new EconomicActivityDTO();

    dto.setCategoryCode("CAT");
    dto.setCategoryDescription("Category description");
    dto.setSubCategoryCode("SUB");
    dto.setSubCategoryDescription("Subcategory description");

    assertEquals("CAT", dto.getCategoryCode());
    assertEquals("Category description", dto.getCategoryDescription());
    assertEquals("SUB", dto.getSubCategoryCode());
    assertEquals("Subcategory description", dto.getSubCategoryDescription());

    EconomicActivityDTO allArgs = new EconomicActivityDTO(
            "CAT",
            "Category description",
            "SUB",
            "Subcategory description"
    );

    assertEquals("CAT", allArgs.getCategoryCode());
    assertEquals("Category description", allArgs.getCategoryDescription());
    assertEquals("SUB", allArgs.getSubCategoryCode());
    assertEquals("Subcategory description", allArgs.getSubCategoryDescription());

    EconomicActivityDTO builder = EconomicActivityDTO.builder()
            .categoryCode("CAT")
            .categoryDescription("Category description")
            .subCategoryCode("SUB")
            .subCategoryDescription("Subcategory description")
            .build();

    assertEquals("CAT", builder.getCategoryCode());
    assertEquals("Category description", builder.getCategoryDescription());
    assertEquals("SUB", builder.getSubCategoryCode());
    assertEquals("Subcategory description", builder.getSubCategoryDescription());
}

}

class PlaceOfRegistrationDTOTest {

@Test
void shouldCoverPlaceOfRegistrationDTOCompletely() {
    CodeNameDTO country = new CodeNameDTO();
    CodeNameDTO state = new CodeNameDTO();

    PlaceOfRegistrationDTO dto = new PlaceOfRegistrationDTO();

    dto.setCountry(country);
    dto.setState(state);
    dto.setTown("Bogota");

    assertSame(country, dto.getCountry());
    assertSame(state, dto.getState());
    assertEquals("Bogota", dto.getTown());

    PlaceOfRegistrationDTO allArgs = new PlaceOfRegistrationDTO(country, state, "Bogota");

    assertSame(country, allArgs.getCountry());
    assertSame(state, allArgs.getState());
    assertEquals("Bogota", allArgs.getTown());

    PlaceOfRegistrationDTO builder = PlaceOfRegistrationDTO.builder()
            .country(country)
            .state(state)
            .town("Bogota")
            .build();

    assertSame(country, builder.getCountry());
    assertSame(state, builder.getState());
    assertEquals("Bogota", builder.getTown());
}

}
