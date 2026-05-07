Te dejo estos primero, clase por clase.
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AddressRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        AddressRequestDTO dto = new AddressRequestDTO();

        StateRequestDTO state = new StateRequestDTO("11");
        ProvinceRequestDTO province = new ProvinceRequestDTO("25");
        RegionIdentificationRequestDTO region = new RegionIdentificationRequestDTO();
        CountyIdentificationRequestDTO county = new CountyIdentificationRequestDTO("001");
        CountryRequestDTO country = new CountryRequestDTO("CO");

        dto.setFormatCode("FORMAT");
        dto.setStreetTypeCode("CL");
        dto.setStreetName("Main");
        dto.setSecondaryStreetName("Second");
        dto.setStreetBuildingIdentification("123");
        dto.setBuildingName("Tower");
        dto.setFloor("5");
        dto.setDetailCode("APT");
        dto.setUnitType("AP");
        dto.setUnitNumber("501");
        dto.setPremise("Premise");
        dto.setAlternativePremise("Alt");
        dto.setMailingInstructions("Mail");
        dto.setPostCodeIdentification("110111");
        dto.setTownName("Bogota");
        dto.setMailDeliverySubLocation("Sub");
        dto.setState(state);
        dto.setDistrictName("District");
        dto.setSecondaryDistrictName("SecDistrict");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("MIL");
        dto.setPostOfficeBox("PO");
        dto.setPostBoxTypeCode("BOX");
        dto.setForeignAddressLines(List.of("Line1"));
        dto.setZipCode("11001");
        dto.setZip4Code("0001");
        dto.setRuralTypeCode("R");
        dto.setRuralNumber("9");

        assertEquals("FORMAT", dto.getFormatCode());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("Main", dto.getStreetName());
        assertEquals("Second", dto.getSecondaryStreetName());
        assertEquals("123", dto.getStreetBuildingIdentification());
        assertEquals("Tower", dto.getBuildingName());
        assertEquals("5", dto.getFloor());
        assertEquals("APT", dto.getDetailCode());
        assertEquals("AP", dto.getUnitType());
        assertEquals("501", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alt", dto.getAlternativePremise());
        assertEquals("Mail", dto.getMailingInstructions());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertEquals("Bogota", dto.getTownName());
        assertEquals("Sub", dto.getMailDeliverySubLocation());
        assertSame(state, dto.getState());
        assertEquals("District", dto.getDistrictName());
        assertEquals("SecDistrict", dto.getSecondaryDistrictName());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MIL", dto.getMilitary());
        assertEquals("PO", dto.getPostOfficeBox());
        assertEquals("BOX", dto.getPostBoxTypeCode());
        assertEquals(List.of("Line1"), dto.getForeignAddressLines());
        assertEquals("11001", dto.getZipCode());
        assertEquals("0001", dto.getZip4Code());
        assertEquals("R", dto.getRuralTypeCode());
        assertEquals("9", dto.getRuralNumber());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("001");

        assertEquals("001", dto.getBankId());

        BankRequestDTO built = BankRequestDTO.builder()
                .bankId("002")
                .build();

        assertEquals("002", built.getBankId());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestContactDayRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        BestContactDayRequestDTO dto = new BestContactDayRequestDTO();
        dto.setDay("MONDAY");
        dto.setFromDateTime("08:00");
        dto.setToDateTime("12:00");

        assertEquals("MONDAY", dto.getDay());
        assertEquals("08:00", dto.getFromDateTime());
        assertEquals("12:00", dto.getToDateTime());

        BestContactDayRequestDTO built = BestContactDayRequestDTO.builder()
                .day("FRIDAY")
                .fromDateTime("09:00")
                .toDateTime("17:00")
                .build();

        assertEquals("FRIDAY", built.getDay());
        assertEquals("09:00", built.getFromDateTime());
        assertEquals("17:00", built.getToDateTime());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestContactTimeRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        BestContactTimeRequestDTO dto = new BestContactTimeRequestDTO();
        dto.setFromDateTime("08:00");
        dto.setToDateTime("10:00");
        dto.setBestTimeFrameCode("M");
        dto.setBestTimeFrameDescription("Morning");

        assertEquals("08:00", dto.getFromDateTime());
        assertEquals("10:00", dto.getToDateTime());
        assertEquals("M", dto.getBestTimeFrameCode());
        assertEquals("Morning", dto.getBestTimeFrameDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactPointRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ContactPointRequestDTO dto = new ContactPointRequestDTO();

        UseTypeRequestDTO useType = new UseTypeRequestDTO("PRI");
        ValidityPeriodRequestDTO validity = new ValidityPeriodRequestDTO("2024-01-01", "2024-12-31");
        BestContactTimeRequestDTO bestTime = new BestContactTimeRequestDTO("08:00", "10:00", "M", "Morning");
        BestContactDayRequestDTO bestDay = new BestContactDayRequestDTO("MONDAY", "08:00", "10:00");
        PostalAddressRequestDTO postal = new PostalAddressRequestDTO();
        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO("3001234567", "6011234567", "57", "123");
        ElectronicAddressRequestDTO email = new ElectronicAddressRequestDTO("test@mail.com");
        WebAddressRequestDTO web = new WebAddressRequestDTO("https://test.com");

        dto.setUseTypes(List.of(useType));
        dto.setPreferredIndicator(true);
        dto.setPrimaryIndicator(false);
        dto.setValidityPeriod(validity);
        dto.setBestContactTime(bestTime);
        dto.setBestContactDays(List.of(bestDay));
        dto.setPostalAddress(postal);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(email);
        dto.setWebAddress(web);

        assertEquals(1, dto.getUseTypes().size());
        assertTrue(dto.getPreferredIndicator());
        assertFalse(dto.getPrimaryIndicator());
        assertSame(validity, dto.getValidityPeriod());
        assertSame(bestTime, dto.getBestContactTime());
        assertEquals(1, dto.getBestContactDays().size());
        assertSame(postal, dto.getPostalAddress());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(email, dto.getElectronicAddress());
        assertSame(web, dto.getWebAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        CountryRequestDTO dto = new CountryRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        assertEquals("US", CountryRequestDTO.builder().code("US").build().getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryOfOperationRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        CountryOfOperationRequestDTO dto = new CountryOfOperationRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        assertEquals("ES", CountryOfOperationRequestDTO.builder().code("ES").build().getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryOfResidenceRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        CountryOfResidenceRequestDTO dto = new CountryOfResidenceRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        assertEquals("MX", CountryOfResidenceRequestDTO.builder().code("MX").build().getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountyIdentificationRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        CountyIdentificationRequestDTO dto = new CountyIdentificationRequestDTO();
        dto.setCode("001");

        assertEquals("001", dto.getCode());

        assertEquals("002", CountyIdentificationRequestDTO.builder().code("002").build().getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CreateProspectRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CreateProspectRequestDTO dto = new CreateProspectRequestDTO();

        PersonRequestDTO person = new PersonRequestDTO();
        OrganizationRequestDTO organization = new OrganizationRequestDTO();
        BankRequestDTO bank = new BankRequestDTO("001");
        ContactPointRequestDTO contactPoint = new ContactPointRequestDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setBank(bank);
        dto.setContactPoints(List.of(contactPoint));

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertSame(bank, dto.getBank());
        assertEquals(1, dto.getContactPoints().size());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        DocumentRequestDTO dto = new DocumentRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("CO");

        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("12345678901");
        dto.setIssueDate("2024-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("REG");
        dto.setCountry(country);
        dto.setTown("Bogota");

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("12345678901", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("REG", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertEquals("Bogota", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Document2RequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        Document2RequestDTO dto = new Document2RequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("123");
        dto.setIssueDate("2024-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("REG");
        dto.setCountry(country);
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("123", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("REG", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EconomicActivityRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        EconomicActivityRequestDTO dto = new EconomicActivityRequestDTO();
        dto.setSubCategoryCode("001");

        assertEquals("001", dto.getSubCategoryCode());

        assertEquals("002", EconomicActivityRequestDTO.builder().subCategoryCode("002").build().getSubCategoryCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        ElectronicAddressRequestDTO dto = new ElectronicAddressRequestDTO();
        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());

        assertEquals("a@b.com", ElectronicAddressRequestDTO.builder().emailAddress("a@b.com").build().getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployerRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        EmployerRequestDTO dto = new EmployerRequestDTO();
        EconomicActivityRequestDTO activity = new EconomicActivityRequestDTO("001");
        AddressRequestDTO address = new AddressRequestDTO();

        dto.setName("Company");
        dto.setEconomicActivity(activity);
        dto.setAddress(address);
        dto.setTypeCode("EMP");

        assertEquals("Company", dto.getName());
        assertSame(activity, dto.getEconomicActivity());
        assertSame(address, dto.getAddress());
        assertEquals("EMP", dto.getTypeCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmploymentInformationRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        EmploymentInformationRequestDTO dto = new EmploymentInformationRequestDTO();
        EconomicActivityRequestDTO activity = new EconomicActivityRequestDTO("001");
        EmployerRequestDTO employer = new EmployerRequestDTO();

        dto.setEconomicActivity(activity);
        dto.setOccupationCode("OCC");
        dto.setStatusCode("ACTIVE");
        dto.setSubActivityCode("SUB");
        dto.setSubActivityComments("Comments");
        dto.setEmployer(employer);

        assertSame(activity, dto.getEconomicActivity());
        assertEquals("OCC", dto.getOccupationCode());
        assertEquals("ACTIVE", dto.getStatusCode());
        assertEquals("SUB", dto.getSubActivityCode());
        assertEquals("Comments", dto.getSubActivityComments());
        assertSame(employer, dto.getEmployer());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FirstNationalityRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        FirstNationalityRequestDTO dto = new FirstNationalityRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        assertEquals("US", FirstNationalityRequestDTO.builder().code("US").build().getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForeignTaxisRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ForeignTaxisRequestDTO dto = new ForeignTaxisRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("US");
        DocumentRequestDTO document = new DocumentRequestDTO();
        NoDocumentRequestDTO noDocument = new NoDocumentRequestDTO("R1", "No document");

        dto.setCountry(country);
        dto.setReasonCode("REASON");
        dto.setDocument(document);
        dto.setNoDocument(noDocument);

        assertSame(country, dto.getCountry());
        assertEquals("REASON", dto.getReasonCode());
        assertSame(document, dto.getDocument());
        assertSame(noDocument, dto.getNoDocument());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoDocumentRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        NoDocumentRequestDTO dto = new NoDocumentRequestDTO();
        dto.setReasonCode("R1");
        dto.setReasonDetails("Details");

        assertEquals("R1", dto.getReasonCode());
        assertEquals("Details", dto.getReasonDetails());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();
        dto.setLegalName("Legal");
        dto.setTradingNames(List.of("Trade"));

        assertEquals("Legal", dto.getLegalName());
        assertEquals(List.of("Trade"), dto.getTradingNames());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationRequestDTO dto = new OrganizationRequestDTO();

        OrganizationNameRequestDTO name = new OrganizationNameRequestDTO("Legal", List.of("Trade"));
        PlaceOfRegistrationRequestDTO registration = new PlaceOfRegistrationRequestDTO();
        ForeignTaxisRequestDTO tax = new ForeignTaxisRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();
        CountryOfOperationRequestDTO operation = new CountryOfOperationRequestDTO("CO");
        EconomicActivityRequestDTO activity = new EconomicActivityRequestDTO("001");
        PreferredLanguageRequestDTO language = new PreferredLanguageRequestDTO("ES");

        dto.setOrganizationName(name);
        dto.setTypeCode("TYPE");
        dto.setSubtypeCode("SUB");
        dto.setRegistrationDate("2024-01-01");
        dto.setAccountingSectorCode("ACC");
        dto.setResidentialStatusCode("RES");
        dto.setForeignTaxIndicator("NO");
        dto.setPlaceOfRegistration(registration);
        dto.setForeignTaxes(List.of(tax));
        dto.setDocuments(List.of(document));
        dto.setCountryOfOperation(operation);
        dto.setEconomicActivity(activity);
        dto.setPreferredLanguage(language);

        assertSame(name, dto.getOrganizationName());
        assertEquals("TYPE", dto.getTypeCode());
        assertEquals("SUB", dto.getSubtypeCode());
        assertEquals("2024-01-01", dto.getRegistrationDate());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertEquals("RES", dto.getResidentialStatusCode());
        assertEquals("NO", dto.getForeignTaxIndicator());
        assertSame(registration, dto.getPlaceOfRegistration());
        assertEquals(1, dto.getForeignTaxes().size());
        assertEquals(1, dto.getDocuments().size());
        assertSame(operation, dto.getCountryOfOperation());
        assertSame(activity, dto.getEconomicActivity());
        assertSame(language, dto.getPreferredLanguage());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonNameRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameRequestDTO dto = new PersonNameRequestDTO();

        dto.setNamePrefixCode("MR");
        dto.setGivenName("Juan");
        dto.setMiddleName("Carlos");
        dto.setLastName("Perez");
        dto.setSecondLastName("Lopez");
        dto.setNameSuffixCode("JR");
        dto.setBirthName("Juan Perez");
        dto.setAliases(List.of("JP"));

        assertEquals("MR", dto.getNamePrefixCode());
        assertEquals("Juan", dto.getGivenName());
        assertEquals("Carlos", dto.getMiddleName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Lopez", dto.getSecondLastName());
        assertEquals("JR", dto.getNameSuffixCode());
        assertEquals("Juan Perez", dto.getBirthName());
        assertEquals(List.of("JP"), dto.getAliases());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDTOTest {

    @Test
    void shouldSetAndGetMainFields() {
        PersonRequestDTO dto = new PersonRequestDTO();

        PersonNameRequestDTO name = new PersonNameRequestDTO();
        ForeignTaxisRequestDTO tax = new ForeignTaxisRequestDTO();
        PlaceOfBirthRequestDTO birth = new PlaceOfBirthRequestDTO();
        CountryOfResidenceRequestDTO residence = new CountryOfResidenceRequestDTO("CO");
        FirstNationalityRequestDTO firstNationality = new FirstNationalityRequestDTO("CO");
        SecondNationalityRequestDTO secondNationality = new SecondNationalityRequestDTO("US");
        PublicOfficeInformationRequestDTO office = new PublicOfficeInformationRequestDTO();
        EmploymentInformationRequestDTO employment = new EmploymentInformationRequestDTO();
        PreferredLanguageRequestDTO language = new PreferredLanguageRequestDTO("ES");
        DocumentRequestDTO document = new DocumentRequestDTO();

        dto.setPersonName(name);
        dto.setMotherName("Mother");
        dto.setFatherName("Father");
        dto.setForeignTaxes(List.of(tax));
        dto.setGenderCode("H");
        dto.setBirthDate("1990-01-01");
        dto.setPlaceOfBirth(birth);
        dto.setCountryOfResidence(residence);
        dto.setFirstNationality(firstNationality);
        dto.setSecondNationality(secondNationality);
        dto.setResidentialStatusCode("RES");
        dto.setCivilStatusCode("S");
        dto.setStaffCode("STAFF");
        dto.setLegallyIncapacitated(false);
        dto.setLegallyCapableMinor(false);
        dto.setDiplomatic(false);
        dto.setPublicOfficeInformation(office);
        dto.setEducationalLevelCode("EDU");
        dto.setForeignTaxIndicator("NO");
        dto.setAccountingSectorCode("ACC");
        dto.setEmploymentInformation(employment);
        dto.setPreferredLanguage(language);
        dto.setDocuments(List.of(document));

        assertSame(name, dto.getPersonName());
        assertEquals("Mother", dto.getMotherName());
        assertEquals("Father", dto.getFatherName());
        assertEquals(1, dto.getForeignTaxes().size());
        assertEquals("H", dto.getGenderCode());
        assertEquals("1990-01-01", dto.getBirthDate());
        assertSame(birth, dto.getPlaceOfBirth());
        assertSame(residence, dto.getCountryOfResidence());
        assertSame(firstNationality, dto.getFirstNationality());
        assertSame(secondNationality, dto.getSecondNationality());
        assertEquals("RES", dto.getResidentialStatusCode());
        assertEquals("S", dto.getCivilStatusCode());
        assertEquals("STAFF", dto.getStaffCode());
        assertFalse(dto.getLegallyIncapacitated());
        assertFalse(dto.getLegallyCapableMinor());
        assertFalse(dto.getDiplomatic());
        assertSame(office, dto.getPublicOfficeInformation());
        assertEquals("EDU", dto.getEducationalLevelCode());
        assertEquals("NO", dto.getForeignTaxIndicator());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertSame(employment, dto.getEmploymentInformation());
        assertSame(language, dto.getPreferredLanguage());
        assertEquals(1, dto.getDocuments().size());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        PhoneAddressRequestDTO dto = new PhoneAddressRequestDTO();

        dto.setMobileNumber("3001234567");
        dto.setPhoneNumber("6011234567");
        dto.setInternationalCode("57");
        dto.setExtension("123");

        assertEquals("3001234567", dto.getMobileNumber());
        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("57", dto.getInternationalCode());
        assertEquals("123", dto.getExtension());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfBirthRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogota");
        dto.setTownCode("11001");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogota", dto.getTown());
        assertEquals("11001", dto.getTownCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfRegistrationRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PlaceOfRegistrationRequestDTO dto = new PlaceOfRegistrationRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogota");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogota", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RootRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        RootRequestDTO dto = new RootRequestDTO();

        PersonRequestDTO person = new PersonRequestDTO();
        OrganizationRequestDTO organization = new OrganizationRequestDTO();
        BankRequestDTO bank = new BankRequestDTO("001");
        ContactPointRequestDTO contactPoint = new ContactPointRequestDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setBank(bank);
        dto.setContactPoints(List.of(contactPoint));

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertSame(bank, dto.getBank());
        assertEquals(1, dto.getContactPoints().size());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCodeDTOsTest {

    @Test
    void shouldCoverSimpleCodeDtos() {
        assertEquals("ES", new PreferredLanguageRequestDTO("ES").getCode());
        assertEquals("11", new ProvinceRequestDTO("11").getCode());
        assertEquals("US", new SecondNationalityRequestDTO("US").getCode());
        assertEquals("25", new StateRequestDTO("25").getCode());
        assertEquals("PRI", new UseTypeRequestDTO("PRI").getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicOfficeInformationRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PublicOfficeInformationRequestDTO dto = new PublicOfficeInformationRequestDTO();
        ValidityPeriodRequestDTO validity = new ValidityPeriodRequestDTO("2024-01-01", "2025-01-01");

        dto.setPositionCode("POS");
        dto.setValidityPeriod(validity);

        assertEquals("POS", dto.getPositionCode());
        assertSame(validity, dto.getValidityPeriod());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        ValidityPeriodRequestDTO dto = new ValidityPeriodRequestDTO();

        dto.setStartDate("2024-01-01");
        dto.setEndDate("2025-01-01");

        assertEquals("2024-01-01", dto.getStartDate());
        assertEquals("2025-01-01", dto.getEndDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WebAddressRequestDTOTest {

    @Test
    void shouldSetGetAndBuild() {
        WebAddressRequestDTO dto = new WebAddressRequestDTO();
        dto.setUrl("https://test.com");

        assertEquals("https://test.com", dto.getUrl());

        assertEquals("https://a.com", WebAddressRequestDTO.builder().url("https://a.com").build().getUrl());
    }
}