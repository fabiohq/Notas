Sí. Ponlos todos en:
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;
AuditDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {

    @Test
    void shouldCoverAuditDTO() {
        AuditDTO dto = new AuditDTO();
        dto.setCreationDate("CREATION");
        dto.setLastUpdateDate("UPDATE");

        assertEquals("CREATION", dto.getCreationDate());
        assertEquals("UPDATE", dto.getLastUpdateDate());

        AuditDTO builder = AuditDTO.builder()
                .creationDate("CREATION")
                .lastUpdateDate("UPDATE")
                .build();

        assertEquals("CREATION", builder.getCreationDate());
        assertEquals("UPDATE", builder.getLastUpdateDate());

        AuditDTO allArgs = new AuditDTO("CREATION", "UPDATE");

        assertEquals("CREATION", allArgs.getCreationDate());
        assertEquals("UPDATE", allArgs.getLastUpdateDate());
    }
}
ElectronicAddressDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressDTOTest {

    @Test
    void shouldCoverElectronicAddressDTO() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO();
        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());

        ElectronicAddressDTO builder = ElectronicAddressDTO.builder()
                .emailAddress("test@mail.com")
                .build();

        assertEquals("test@mail.com", builder.getEmailAddress());

        ElectronicAddressDTO allArgs = new ElectronicAddressDTO("test@mail.com");

        assertEquals("test@mail.com", allArgs.getEmailAddress());
    }
}
PhoneAddressDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressDTOTest {

    @Test
    void shouldCoverPhoneAddressDTO() {
        PhoneAddressDTO dto = new PhoneAddressDTO();

        dto.setMobileNumber("MOBILE");
        dto.setPhoneNumber("PHONE");
        dto.setFaxNumber("FAX");
        dto.setInternationalCode("+57");
        dto.setExtension("EXT");

        assertEquals("MOBILE", dto.getMobileNumber());
        assertEquals("PHONE", dto.getPhoneNumber());
        assertEquals("FAX", dto.getFaxNumber());
        assertEquals("+57", dto.getInternationalCode());
        assertEquals("EXT", dto.getExtension());

        PhoneAddressDTO builder = PhoneAddressDTO.builder()
                .mobileNumber("MOBILE")
                .phoneNumber("PHONE")
                .faxNumber("FAX")
                .internationalCode("+57")
                .extension("EXT")
                .build();

        assertEquals("MOBILE", builder.getMobileNumber());
        assertEquals("EXT", builder.getExtension());

        PhoneAddressDTO allArgs = new PhoneAddressDTO("MOBILE", "PHONE", "FAX", "+57", "EXT");

        assertEquals("PHONE", allArgs.getPhoneNumber());
        assertEquals("EXT", allArgs.getExtension());
    }
}
ValidityPeriodDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodDTOTest {

    @Test
    void shouldCoverValidityPeriodDTO() {
        ValidityPeriodDTO dto = new ValidityPeriodDTO();
        dto.setStartDate("START");
        dto.setEndDate("END");

        assertEquals("START", dto.getStartDate());
        assertEquals("END", dto.getEndDate());

        ValidityPeriodDTO builder = ValidityPeriodDTO.builder()
                .startDate("START")
                .endDate("END")
                .build();

        assertEquals("START", builder.getStartDate());
        assertEquals("END", builder.getEndDate());

        ValidityPeriodDTO allArgs = new ValidityPeriodDTO("START", "END");

        assertEquals("START", allArgs.getStartDate());
        assertEquals("END", allArgs.getEndDate());
    }
}
DataOriginDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataOriginDTOTest {

    @Test
    void shouldCoverDataOriginDTO() {
        DataOriginDTO dto = new DataOriginDTO();

        dto.setSourceCode("CODE");
        dto.setSourceDescription("DESCRIPTION");
        dto.setCreationDate("DATE");

        assertEquals("CODE", dto.getSourceCode());
        assertEquals("DESCRIPTION", dto.getSourceDescription());
        assertEquals("DATE", dto.getCreationDate());

        DataOriginDTO builder = DataOriginDTO.builder()
                .sourceCode("CODE")
                .sourceDescription("DESCRIPTION")
                .creationDate("DATE")
                .build();

        assertEquals("CODE", builder.getSourceCode());

        DataOriginDTO allArgs = new DataOriginDTO("CODE", "DESCRIPTION", "DATE");

        assertEquals("DATE", allArgs.getCreationDate());
    }
}
EconomicActivityDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EconomicActivityDTOTest {

    @Test
    void shouldCoverEconomicActivityDTO() {
        EconomicActivityDTO dto = new EconomicActivityDTO();

        dto.setCategoryCode("CAT");
        dto.setCategoryDescription("CAT_DESC");
        dto.setSubCategoryCode("SUB");
        dto.setSubCategoryDescription("SUB_DESC");

        assertEquals("CAT", dto.getCategoryCode());
        assertEquals("CAT_DESC", dto.getCategoryDescription());
        assertEquals("SUB", dto.getSubCategoryCode());
        assertEquals("SUB_DESC", dto.getSubCategoryDescription());

        EconomicActivityDTO builder = EconomicActivityDTO.builder()
                .categoryCode("CAT")
                .categoryDescription("CAT_DESC")
                .subCategoryCode("SUB")
                .subCategoryDescription("SUB_DESC")
                .build();

        assertEquals("CAT", builder.getCategoryCode());

        EconomicActivityDTO allArgs = new EconomicActivityDTO("CAT", "CAT_DESC", "SUB", "SUB_DESC");

        assertEquals("SUB_DESC", allArgs.getSubCategoryDescription());
    }
}
EmploymentInformationDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmploymentInformationDTOTest {

    @Test
    void shouldCoverEmploymentInformationDTO() {
        EconomicActivityDTO economicActivity = new EconomicActivityDTO();

        EmploymentInformationDTO dto = new EmploymentInformationDTO();

        dto.setStatusCode("STATUS");
        dto.setStatusDescription("STATUS_DESC");
        dto.setEconomicActivity(economicActivity);
        dto.setOccupationCode("OCC");
        dto.setOccupationDescription("OCC_DESC");
        dto.setSubActivityCode("SUB");
        dto.setSubActivityDescription("SUB_DESC");
        dto.setSubActivityComments("COMMENTS");

        assertEquals("STATUS", dto.getStatusCode());
        assertEquals("STATUS_DESC", dto.getStatusDescription());
        assertSame(economicActivity, dto.getEconomicActivity());
        assertEquals("OCC", dto.getOccupationCode());
        assertEquals("OCC_DESC", dto.getOccupationDescription());
        assertEquals("SUB", dto.getSubActivityCode());
        assertEquals("SUB_DESC", dto.getSubActivityDescription());
        assertEquals("COMMENTS", dto.getSubActivityComments());

        EmploymentInformationDTO builder = EmploymentInformationDTO.builder()
                .statusCode("STATUS")
                .statusDescription("STATUS_DESC")
                .economicActivity(economicActivity)
                .occupationCode("OCC")
                .occupationDescription("OCC_DESC")
                .subActivityCode("SUB")
                .subActivityDescription("SUB_DESC")
                .subActivityComments("COMMENTS")
                .build();

        assertEquals("STATUS", builder.getStatusCode());

        EmploymentInformationDTO allArgs = new EmploymentInformationDTO(
                "STATUS", "STATUS_DESC", economicActivity, "OCC", "OCC_DESC", "SUB", "SUB_DESC", "COMMENTS"
        );

        assertEquals("COMMENTS", allArgs.getSubActivityComments());
    }
}
OrganizationNameDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameDTOTest {

    @Test
    void shouldCoverOrganizationNameDTO() {
        List<String> names = List.of("TRADE");

        OrganizationNameDTO dto = new OrganizationNameDTO();

        dto.setLegalName("LEGAL");
        dto.setTradingNames(names);

        assertEquals("LEGAL", dto.getLegalName());
        assertSame(names, dto.getTradingNames());

        OrganizationNameDTO builder = OrganizationNameDTO.builder()
                .legalName("LEGAL")
                .tradingNames(names)
                .build();

        assertEquals("LEGAL", builder.getLegalName());

        OrganizationNameDTO allArgs = new OrganizationNameDTO("LEGAL", names);

        assertSame(names, allArgs.getTradingNames());
    }
}
PersonNameDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonNameDTOTest {

    @Test
    void shouldCoverPersonNameDTO() {
        List<String> aliases = List.of("ALIAS");

        PersonNameDTO dto = new PersonNameDTO();

        dto.setNamePrefixCode("NPC");
        dto.setNamePrefixDescription("NPD");
        dto.setGivenName("GIVEN");
        dto.setMiddleName("MIDDLE");
        dto.setLastName("LAST");
        dto.setSecondLastName("SECOND");
        dto.setNameSuffixCode("NSC");
        dto.setNameSuffixDescription("NSD");
        dto.setFullName("FULL");
        dto.setBirthName("BIRTH");
        dto.setAliases(aliases);

        assertEquals("NPC", dto.getNamePrefixCode());
        assertEquals("NPD", dto.getNamePrefixDescription());
        assertEquals("GIVEN", dto.getGivenName());
        assertEquals("MIDDLE", dto.getMiddleName());
        assertEquals("LAST", dto.getLastName());
        assertEquals("SECOND", dto.getSecondLastName());
        assertEquals("NSC", dto.getNameSuffixCode());
        assertEquals("NSD", dto.getNameSuffixDescription());
        assertEquals("FULL", dto.getFullName());
        assertEquals("BIRTH", dto.getBirthName());
        assertSame(aliases, dto.getAliases());

        PersonNameDTO builder = PersonNameDTO.builder()
                .namePrefixCode("NPC")
                .namePrefixDescription("NPD")
                .givenName("GIVEN")
                .middleName("MIDDLE")
                .lastName("LAST")
                .secondLastName("SECOND")
                .nameSuffixCode("NSC")
                .nameSuffixDescription("NSD")
                .fullName("FULL")
                .birthName("BIRTH")
                .aliases(aliases)
                .build();

        assertEquals("FULL", builder.getFullName());

        PersonNameDTO allArgs = new PersonNameDTO(
                "NPC", "NPD", "GIVEN", "MIDDLE", "LAST", "SECOND", "NSC", "NSD", "FULL", "BIRTH", aliases
        );

        assertSame(aliases, allArgs.getAliases());
    }
}
PlaceOfBirthDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfBirthDTOTest {

    @Test
    void shouldCoverPlaceOfBirthDTO() {
        CodeNameDTO country = null;
        CodeNameDTO state = null;

        PlaceOfBirthDTO dto = new PlaceOfBirthDTO();

        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("TOWN");

        assertNull(dto.getCountry());
        assertNull(dto.getState());
        assertEquals("TOWN", dto.getTown());

        PlaceOfBirthDTO builder = PlaceOfBirthDTO.builder()
                .country(country)
                .state(state)
                .town("TOWN")
                .build();

        assertEquals("TOWN", builder.getTown());

        PlaceOfBirthDTO allArgs = new PlaceOfBirthDTO(country, state, "TOWN");

        assertEquals("TOWN", allArgs.getTown());
    }
}
PlaceOfRegistrationDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfRegistrationDTOTest {

    @Test
    void shouldCoverPlaceOfRegistrationDTO() {
        CodeNameDTO country = null;
        CodeNameDTO state = null;

        PlaceOfRegistrationDTO dto = new PlaceOfRegistrationDTO();

        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("TOWN");

        assertNull(dto.getCountry());
        assertNull(dto.getState());
        assertEquals("TOWN", dto.getTown());

        PlaceOfRegistrationDTO builder = PlaceOfRegistrationDTO.builder()
                .country(country)
                .state(state)
                .town("TOWN")
                .build();

        assertEquals("TOWN", builder.getTown());

        PlaceOfRegistrationDTO allArgs = new PlaceOfRegistrationDTO(country, state, "TOWN");

        assertEquals("TOWN", allArgs.getTown());
    }
}
PublicOfficeInformationDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicOfficeInformationDTOTest {

    @Test
    void shouldCoverPublicOfficeInformationDTO() {
        ValidityPeriodDTO validityPeriod = new ValidityPeriodDTO();

        PublicOfficeInformationDTO dto = new PublicOfficeInformationDTO();

        dto.setPositionCode("POS");
        dto.setPositionDescription("POS_DESC");
        dto.setValidityPeriod(validityPeriod);

        assertEquals("POS", dto.getPositionCode());
        assertEquals("POS_DESC", dto.getPositionDescription());
        assertSame(validityPeriod, dto.getValidityPeriod());

        PublicOfficeInformationDTO builder = PublicOfficeInformationDTO.builder()
                .positionCode("POS")
                .positionDescription("POS_DESC")
                .validityPeriod(validityPeriod)
                .build();

        assertEquals("POS", builder.getPositionCode());

        PublicOfficeInformationDTO allArgs =
                new PublicOfficeInformationDTO("POS", "POS_DESC", validityPeriod);

        assertSame(validityPeriod, allArgs.getValidityPeriod());
    }
}
ContactPointDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactPointDTOTest {

    @Test
    void shouldCoverContactPointDTO() {
        List<CodeNameDTO> useTypes = List.of();
        PostalAddressDTO postalAddress = null;
        PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
        ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();
        AuditDTO audit = new AuditDTO();

        ContactPointDTO dto = new ContactPointDTO();

        dto.setContactPointId("ID");
        dto.setUseTypes(useTypes);
        dto.setPostalAddress(postalAddress);
        dto.setPhoneAddress(phoneAddress);
        dto.setElectronicAddress(electronicAddress);
        dto.setAudit(audit);

        assertEquals("ID", dto.getContactPointId());
        assertSame(useTypes, dto.getUseTypes());
        assertNull(dto.getPostalAddress());
        assertSame(phoneAddress, dto.getPhoneAddress());
        assertSame(electronicAddress, dto.getElectronicAddress());
        assertSame(audit, dto.getAudit());

        ContactPointDTO builder = ContactPointDTO.builder()
                .contactPointId("ID")
                .useTypes(useTypes)
                .postalAddress(postalAddress)
                .phoneAddress(phoneAddress)
                .electronicAddress(electronicAddress)
                .audit(audit)
                .build();

        assertEquals("ID", builder.getContactPointId());

        ContactPointDTO allArgs = new ContactPointDTO(
                "ID", useTypes, postalAddress, phoneAddress, electronicAddress, audit
        );

        assertSame(audit, allArgs.getAudit());
    }
}
CustomerDetailsResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.BankDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDetailsResponseDTOTest {

    @Test
    void shouldCoverCustomerDetailsResponseDTO() {
        PersonDTO person = new PersonDTO();
        OrganizationDTO organization = new OrganizationDTO();
        List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());
        BankDTO bank = null;
        List<DataOriginDTO> dataOrigins = List.of(new DataOriginDTO());

        CustomerDetailsResponseDTO dto = new CustomerDetailsResponseDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setContactPoints(contactPoints);
        dto.setHighConfidentialityIndicator(true);
        dto.setIsPendingExCustomer(false);
        dto.setConfidentialityLevel("LEVEL");
        dto.setBank(bank);
        dto.setDataOrigins(dataOrigins);
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSegmentDescription("SEG_DESC");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setStructuralSubsegmentDescription("SUB_DESC");

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertSame(contactPoints, dto.getContactPoints());
        assertTrue(dto.getHighConfidentialityIndicator());
        assertFalse(dto.getIsPendingExCustomer());
        assertEquals("LEVEL", dto.getConfidentialityLevel());
        assertNull(dto.getBank());
        assertSame(dataOrigins, dto.getDataOrigins());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SEG_DESC", dto.getStructuralSegmentDescription());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertEquals("SUB_DESC", dto.getStructuralSubsegmentDescription());

        CustomerDetailsResponseDTO builder = CustomerDetailsResponseDTO.builder()
                .person(person)
                .organization(organization)
                .contactPoints(contactPoints)
                .highConfidentialityIndicator(true)
                .isPendingExCustomer(false)
                .confidentialityLevel("LEVEL")
                .bank(bank)
                .dataOrigins(dataOrigins)
                .structuralSegmentCode("SEG")
                .structuralSegmentDescription("SEG_DESC")
                .structuralSubsegmentCode("SUB")
                .structuralSubsegmentDescription("SUB_DESC")
                .build();

        assertSame(person, builder.getPerson());

        CustomerDetailsResponseDTO allArgs = new CustomerDetailsResponseDTO(
                person, organization, contactPoints, true, false, "LEVEL", bank,
                dataOrigins, "SEG", "SEG_DESC", "SUB", "SUB_DESC"
        );

        assertEquals("SUB_DESC", allArgs.getStructuralSubsegmentDescription());
    }
}
OrganizationDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationDTOTest {

    @Test
    void shouldCoverOrganizationDTO() {
        PlaceOfRegistrationDTO place = new PlaceOfRegistrationDTO();
        OrganizationNameDTO name = new OrganizationNameDTO();
        List<DocumentDTO> documents = List.of();
        CodeNameDTO country = null;
        EconomicActivityDTO economicActivity = new EconomicActivityDTO();
        CodeNameDTO language = null;

        OrganizationDTO dto = new OrganizationDTO();

        dto.setRegistrationDate("REG");
        dto.setEntityDisolutionDate("DIS");
        dto.setResidentialStatusCode("RSC");
        dto.setResidentialStatusDescription("RSD");
        dto.setForeignTaxIndicator("FTI");
        dto.setPlaceOfRegistration(place);
        dto.setOrganizationName(name);
        dto.setTypeCode("TC");
        dto.setTypeDescription("TD");
        dto.setSubtypeCode("STC");
        dto.setSubtypeDescription("STD");
        dto.setDocuments(documents);
        dto.setCountryOfOperation(country);
        dto.setAccountingSectorCode("ASC");
        dto.setAccountingSectorDescription("ASD");
        dto.setEconomicActivity(economicActivity);
        dto.setPreferredLanguage(language);

        assertEquals("REG", dto.getRegistrationDate());
        assertEquals("DIS", dto.getEntityDisolutionDate());
        assertEquals("RSC", dto.getResidentialStatusCode());
        assertEquals("RSD", dto.getResidentialStatusDescription());
        assertEquals("FTI", dto.getForeignTaxIndicator());
        assertSame(place, dto.getPlaceOfRegistration());
        assertSame(name, dto.getOrganizationName());
        assertEquals("TC", dto.getTypeCode());
        assertEquals("TD", dto.getTypeDescription());
        assertEquals("STC", dto.getSubtypeCode());
        assertEquals("STD", dto.getSubtypeDescription());
        assertSame(documents, dto.getDocuments());
        assertNull(dto.getCountryOfOperation());
        assertEquals("ASC", dto.getAccountingSectorCode());
        assertEquals("ASD", dto.getAccountingSectorDescription());
        assertSame(economicActivity, dto.getEconomicActivity());
        assertNull(dto.getPreferredLanguage());

        OrganizationDTO builder = OrganizationDTO.builder()
                .registrationDate("REG")
                .entityDisolutionDate("DIS")
                .residentialStatusCode("RSC")
                .residentialStatusDescription("RSD")
                .foreignTaxIndicator("FTI")
                .placeOfRegistration(place)
                .organizationName(name)
                .typeCode("TC")
                .typeDescription("TD")
                .subtypeCode("STC")
                .subtypeDescription("STD")
                .documents(documents)
                .countryOfOperation(country)
                .accountingSectorCode("ASC")
                .accountingSectorDescription("ASD")
                .economicActivity(economicActivity)
                .preferredLanguage(language)
                .build();

        assertEquals("REG", builder.getRegistrationDate());

        OrganizationDTO allArgs = new OrganizationDTO(
                "REG", "DIS", "RSC", "RSD", "FTI", place, name, "TC", "TD",
                "STC", "STD", documents, country, "ASC", "ASD", economicActivity, language
        );

        assertEquals("ASD", allArgs.getAccountingSectorDescription());
    }
}
PersonDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

    @Test
    void shouldCoverPersonDTO() {
        PersonNameDTO personName = new PersonNameDTO();
        PlaceOfBirthDTO placeOfBirth = new PlaceOfBirthDTO();
        CodeNameDTO codeName = null;
        PublicOfficeInformationDTO publicOfficeInformation = new PublicOfficeInformationDTO();
        EmploymentInformationDTO employmentInformation = new EmploymentInformationDTO();
        List<DocumentDTO> documents = List.of();
        DocumentDTO document = null;

        PersonDTO dto = new PersonDTO();

        dto.setPersonName(personName);
        dto.setMotherName("MOTHER");
        dto.setFatherName("FATHER");
        dto.setGenderCode("GC");
        dto.setGenderDescription("GD");
        dto.setBirthDate("BIRTH");
        dto.setPlaceOfBirth(placeOfBirth);
        dto.setCountryOfResidence(codeName);
        dto.setForeignTaxIndicator("FTI");
        dto.setFirstNationality(codeName);
        dto.setSecondNationality(codeName);
        dto.setResidentialStatusCode("RSC");
        dto.setResidentialStatusDescription("RSD");
        dto.setCivilStatusCode("CSC");
        dto.setCivilStatusDescription("CSD");
        dto.setPublicOfficeInformation(publicOfficeInformation);
        dto.setDeathDate("DEATH");
        dto.setEmployeeIndicator(true);
        dto.setStaffCode("SC");
        dto.setStaffDescription("SD");
        dto.setLegallyIncapacitated(false);
        dto.setLegallyCapableMinor(true);
        dto.setDiplomatic(false);
        dto.setEducationalLevelCode("ELC");
        dto.setEducationalLevelDescription("ELD");
        dto.setAccountingSectorCode("ASC");
        dto.setAccountingSectorDescription("ASD");
        dto.setEmploymentInformation(employmentInformation);
        dto.setPreferredLanguage(codeName);
        dto.setDocuments(documents);
        dto.setDocument(document);

        assertSame(personName, dto.getPersonName());
        assertEquals("MOTHER", dto.getMotherName());
        assertEquals("FATHER", dto.getFatherName());
        assertEquals("GC", dto.getGenderCode());
        assertEquals("GD", dto.getGenderDescription());
        assertEquals("BIRTH", dto.getBirthDate());
        assertSame(placeOfBirth, dto.getPlaceOfBirth());
        assertNull(dto.getCountryOfResidence());
        assertEquals("FTI", dto.getForeignTaxIndicator());
        assertNull(dto.getFirstNationality());
        assertNull(dto.getSecondNationality());
        assertEquals("RSC", dto.getResidentialStatusCode());
        assertEquals("RSD", dto.getResidentialStatusDescription());
        assertEquals("CSC", dto.getCivilStatusCode());
        assertEquals("CSD", dto.getCivilStatusDescription());
        assertSame(publicOfficeInformation, dto.getPublicOfficeInformation());
        assertEquals("DEATH", dto.getDeathDate());
        assertTrue(dto.getEmployeeIndicator());
        assertEquals("SC", dto.getStaffCode());
        assertEquals("SD", dto.getStaffDescription());
        assertFalse(dto.getLegallyIncapacitated());
        assertTrue(dto.getLegallyCapableMinor());
        assertFalse(dto.getDiplomatic());
        assertEquals("ELC", dto.getEducationalLevelCode());
        assertEquals("ELD", dto.getEducationalLevelDescription());
        assertEquals("ASC", dto.getAccountingSectorCode());
        assertEquals("ASD", dto.getAccountingSectorDescription());
        assertSame(employmentInformation, dto.getEmploymentInformation());
        assertNull(dto.getPreferredLanguage());
        assertSame(documents, dto.getDocuments());
        assertNull(dto.getDocument());

        PersonDTO builder = PersonDTO.builder()
                .personName(personName)
                .motherName("MOTHER")
                .fatherName("FATHER")
                .genderCode("GC")
                .genderDescription("GD")
                .birthDate("BIRTH")
                .placeOfBirth(placeOfBirth)
                .countryOfResidence(codeName)
                .foreignTaxIndicator("FTI")
                .firstNationality(codeName)
                .secondNationality(codeName)
                .residentialStatusCode("RSC")
                .residentialStatusDescription("RSD")
                .civilStatusCode("CSC")
                .civilStatusDescription("CSD")
                .publicOfficeInformation(publicOfficeInformation)
                .deathDate("DEATH")
                .employeeIndicator(true)
                .staffCode("SC")
                .staffDescription("SD")
                .legallyIncapacitated(false)
                .legallyCapableMinor(true)
                .diplomatic(false)
                .educationalLevelCode("ELC")
                .educationalLevelDescription("ELD")
                .accountingSectorCode("ASC")
                .accountingSectorDescription("ASD")
                .employmentInformation(employmentInformation)
                .preferredLanguage(codeName)
                .documents(documents)
                .document(document)
                .build();

        assertSame(personName, builder.getPersonName());

        PersonDTO allArgs = new PersonDTO(
                personName, "MOTHER", "FATHER", "GC", "GD", "BIRTH", placeOfBirth,
                codeName, "FTI", codeName, codeName, "RSC", "RSD", "CSC", "CSD",
                publicOfficeInformation, "DEATH", true, "SC", "SD", false, true,
                false, "ELC", "ELD", "ASC", "ASD", employmentInformation,
                codeName, documents, document
        );

        assertEquals("ASD", allArgs.getAccountingSectorDescription());
    }
}