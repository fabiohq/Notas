Te dejo los tests clase por clase, sin acoplar, para el paquete update.request.
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        AddressRequestDTO dto = new AddressRequestDTO();
        StateRequestDTO state = new StateRequestDTO();
        ProvinceRequestDTO province = new ProvinceRequestDTO();
        RegionIdentificationRequestDTO region = new RegionIdentificationRequestDTO();
        CountyIdentificationRequestDTO county = new CountyIdentificationRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();
        List<String> lines = List.of("line1");

        dto.setFormatCode("F");
        dto.setStreetTypeCode("ST");
        dto.setStreetName("Main");
        dto.setSecondaryStreetName("Second");
        dto.setStreetBuildingIdentification("10");
        dto.setBuildingName("Tower");
        dto.setFloor("2");
        dto.setDetailCode("D");
        dto.setUnitType("APT");
        dto.setUnitNumber("201");
        dto.setPremise("Premise");
        dto.setAlternativePremise("Alt");
        dto.setMailingInstructions("Mail");
        dto.setPostCodeIdentification("110111");
        dto.setTownName("Bogota");
        dto.setMailDeliverySubLocation("Sub");
        dto.setState(state);
        dto.setDistrictName("District");
        dto.setSecondaryDistrictName("District2");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("N");
        dto.setPostOfficeBox("PO");
        dto.setPostBoxTypeCode("PB");
        dto.setForeignAddressLines(lines);
        dto.setZipCode("110111");
        dto.setZip4Code("1234");
        dto.setRuralTypeCode("R");
        dto.setRuralNumber("1");

        assertEquals("F", dto.getFormatCode());
        assertEquals("ST", dto.getStreetTypeCode());
        assertEquals("Main", dto.getStreetName());
        assertEquals("Second", dto.getSecondaryStreetName());
        assertEquals("10", dto.getStreetBuildingIdentification());
        assertEquals("Tower", dto.getBuildingName());
        assertEquals("2", dto.getFloor());
        assertEquals("D", dto.getDetailCode());
        assertEquals("APT", dto.getUnitType());
        assertEquals("201", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alt", dto.getAlternativePremise());
        assertEquals("Mail", dto.getMailingInstructions());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertEquals("Bogota", dto.getTownName());
        assertEquals("Sub", dto.getMailDeliverySubLocation());
        assertSame(state, dto.getState());
        assertEquals("District", dto.getDistrictName());
        assertEquals("District2", dto.getSecondaryDistrictName());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("N", dto.getMilitary());
        assertEquals("PO", dto.getPostOfficeBox());
        assertEquals("PB", dto.getPostBoxTypeCode());
        assertSame(lines, dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
        assertEquals("1234", dto.getZip4Code());
        assertEquals("R", dto.getRuralTypeCode());
        assertEquals("1", dto.getRuralNumber());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("001");
        assertEquals("001", dto.getBankId());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestContactDayRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        BestContactDayRequestDTO dto = new BestContactDayRequestDTO();
        dto.setDay("MONDAY");
        dto.setFromDateTime("08:00");
        dto.setToDateTime("17:00");

        assertEquals("MONDAY", dto.getDay());
        assertEquals("08:00", dto.getFromDateTime());
        assertEquals("17:00", dto.getToDateTime());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestContactTimeRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        BestContactTimeRequestDTO dto = new BestContactTimeRequestDTO();
        dto.setFromDateTime("08:00");
        dto.setToDateTime("17:00");
        dto.setBestTimeFrameCode("AM");
        dto.setBestTimeFrameDescription("Morning");

        assertEquals("08:00", dto.getFromDateTime());
        assertEquals("17:00", dto.getToDateTime());
        assertEquals("AM", dto.getBestTimeFrameCode());
        assertEquals("Morning", dto.getBestTimeFrameDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactPointRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        List<UseTypeRequestDTO> useTypes = List.of(new UseTypeRequestDTO());
        List<BestContactDayRequestDTO> days = List.of(new BestContactDayRequestDTO());
        ValidityPeriodRequestDTO validity = new ValidityPeriodRequestDTO();
        BestContactTimeRequestDTO time = new BestContactTimeRequestDTO();
        PostalAddressRequestDTO postal = new PostalAddressRequestDTO();
        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO();
        ElectronicAddressRequestDTO email = new ElectronicAddressRequestDTO();
        WebAddressRequestDTO web = new WebAddressRequestDTO();

        ContactPointRequestDTO dto = new ContactPointRequestDTO();
        dto.setUseTypes(useTypes);
        dto.setPreferredIndicator(true);
        dto.setPrimaryIndicator(false);
        dto.setValidityPeriod(validity);
        dto.setBestContactTime(time);
        dto.setBestContactDays(days);
        dto.setPostalAddress(postal);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(email);
        dto.setWebAddress(web);

        assertSame(useTypes, dto.getUseTypes());
        assertTrue(dto.getPreferredIndicator());
        assertFalse(dto.getPrimaryIndicator());
        assertSame(validity, dto.getValidityPeriod());
        assertSame(time, dto.getBestContactTime());
        assertSame(days, dto.getBestContactDays());
        assertSame(postal, dto.getPostalAddress());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(email, dto.getElectronicAddress());
        assertSame(web, dto.getWebAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryOfOperationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryOfOperationRequestDTO dto = new CountryOfOperationRequestDTO();
        dto.setCode("CO");
        assertEquals("CO", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryOfResidenceRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryOfResidenceRequestDTO dto = new CountryOfResidenceRequestDTO();
        dto.setCode("CO");
        assertEquals("CO", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO dto = new CountryRequestDTO();
        dto.setCode("CO");
        assertEquals("CO", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountyIdentificationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountyIdentificationRequestDTO dto = new CountyIdentificationRequestDTO();
        dto.setCode("001");
        assertEquals("001", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Document2RequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO country = new CountryRequestDTO();
        StateRequestDTO state = new StateRequestDTO();

        Document2RequestDTO dto = new Document2RequestDTO();
        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("12345678901");
        dto.setIssueDate("2024-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("REG");
        dto.setCountry(country);
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("12345678901", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("REG", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DocumentRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO country = new CountryRequestDTO();

        DocumentRequestDTO dto = new DocumentRequestDTO();
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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EconomicActivityRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        EconomicActivityRequestDTO dto = new EconomicActivityRequestDTO();
        dto.setSubCategoryCode("001");
        assertEquals("001", dto.getSubCategoryCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ElectronicAddressRequestDTO dto = new ElectronicAddressRequestDTO();
        dto.setEmailAddress("test@test.com");
        assertEquals("test@test.com", dto.getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployerRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        EconomicActivityRequestDTO economic = new EconomicActivityRequestDTO();
        AddressRequestDTO address = new AddressRequestDTO();

        EmployerRequestDTO dto = new EmployerRequestDTO();
        dto.setName("Company");
        dto.setEconomicActivity(economic);
        dto.setAddress(address);
        dto.setTypeCode("EMP");

        assertEquals("Company", dto.getName());
        assertSame(economic, dto.getEconomicActivity());
        assertSame(address, dto.getAddress());
        assertEquals("EMP", dto.getTypeCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmploymentInformationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        EconomicActivityRequestDTO economic = new EconomicActivityRequestDTO();
        EmployerRequestDTO employer = new EmployerRequestDTO();

        EmploymentInformationRequestDTO dto = new EmploymentInformationRequestDTO();
        dto.setEconomicActivity(economic);
        dto.setOccupationCode("OCC");
        dto.setStatusCode("ACTIVE");
        dto.setSubActivityCode("SUB");
        dto.setSubActivityComments("comments");
        dto.setEmployer(employer);

        assertSame(economic, dto.getEconomicActivity());
        assertEquals("OCC", dto.getOccupationCode());
        assertEquals("ACTIVE", dto.getStatusCode());
        assertEquals("SUB", dto.getSubActivityCode());
        assertEquals("comments", dto.getSubActivityComments());
        assertSame(employer, dto.getEmployer());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FirstNationalityRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        FirstNationalityRequestDTO dto = new FirstNationalityRequestDTO();
        dto.setCode("CO");
        assertEquals("CO", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForeignTaxisRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO country = new CountryRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();
        NoDocumentRequestDTO noDocument = new NoDocumentRequestDTO();

        ForeignTaxisRequestDTO dto = new ForeignTaxisRequestDTO();
        dto.setCountry(country);
        dto.setReasonCode("R");
        dto.setDocument(document);
        dto.setNoDocument(noDocument);

        assertSame(country, dto.getCountry());
        assertEquals("R", dto.getReasonCode());
        assertSame(document, dto.getDocument());
        assertSame(noDocument, dto.getNoDocument());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoDocumentRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        NoDocumentRequestDTO dto = new NoDocumentRequestDTO();
        dto.setReasonCode("R");
        dto.setReasonDetails("No document");

        assertEquals("R", dto.getReasonCode());
        assertEquals("No document", dto.getReasonDetails());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        List<String> tradingNames = List.of("Trade");

        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();
        dto.setLegalName("Legal Name");
        dto.setTradingNames(tradingNames);

        assertEquals("Legal Name", dto.getLegalName());
        assertSame(tradingNames, dto.getTradingNames());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameRequestDTO name = new OrganizationNameRequestDTO();
        PlaceOfRegistrationRequestDTO place = new PlaceOfRegistrationRequestDTO();
        List<ForeignTaxisRequestDTO> taxes = List.of(new ForeignTaxisRequestDTO());
        List<DocumentRequestDTO> documents = List.of(new DocumentRequestDTO());
        CountryOfOperationRequestDTO operation = new CountryOfOperationRequestDTO();
        EconomicActivityRequestDTO economic = new EconomicActivityRequestDTO();
        PreferredLanguageRequestDTO language = new PreferredLanguageRequestDTO();

        OrganizationRequestDTO dto = new OrganizationRequestDTO();
        dto.setOrganizationName(name);
        dto.setTypeCode("T");
        dto.setSubtypeCode("ST");
        dto.setRegistrationDate("2024-01-01");
        dto.setAccountingSectorCode("AS");
        dto.setResidentialStatusCode("RS");
        dto.setForeignTaxIndicator("YES");
        dto.setPlaceOfRegistration(place);
        dto.setForeignTaxes(taxes);
        dto.setDocuments(documents);
        dto.setCountryOfOperation(operation);
        dto.setEconomicActivity(economic);
        dto.setPreferredLanguage(language);

        assertSame(name, dto.getOrganizationName());
        assertEquals("T", dto.getTypeCode());
        assertEquals("ST", dto.getSubtypeCode());
        assertEquals("2024-01-01", dto.getRegistrationDate());
        assertEquals("AS", dto.getAccountingSectorCode());
        assertEquals("RS", dto.getResidentialStatusCode());
        assertEquals("YES", dto.getForeignTaxIndicator());
        assertSame(place, dto.getPlaceOfRegistration());
        assertSame(taxes, dto.getForeignTaxes());
        assertSame(documents, dto.getDocuments());
        assertSame(operation, dto.getCountryOfOperation());
        assertSame(economic, dto.getEconomicActivity());
        assertSame(language, dto.getPreferredLanguage());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PatchProspectRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        PersonRequestDTO person = new PersonRequestDTO();
        OrganizationRequestDTO organization = new OrganizationRequestDTO();
        BankRequestDTO bank = new BankRequestDTO();
        List<ContactPointRequestDTO> contactPoints = List.of(new ContactPointRequestDTO());

        PatchProspectRequestDTO dto = new PatchProspectRequestDTO();
        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setBank(bank);
        dto.setContactPoints(contactPoints);

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertSame(bank, dto.getBank());
        assertSame(contactPoints, dto.getContactPoints());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonNameRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        List<String> aliases = List.of("Alias");

        PersonNameRequestDTO dto = new PersonNameRequestDTO();
        dto.setNamePrefixCode("MR");
        dto.setGivenName("Juan");
        dto.setMiddleName("Carlos");
        dto.setLastName("Perez");
        dto.setSecondLastName("Gomez");
        dto.setNameSuffixCode("JR");
        dto.setBirthName("Juan");
        dto.setAliases(aliases);

        assertEquals("MR", dto.getNamePrefixCode());
        assertEquals("Juan", dto.getGivenName());
        assertEquals("Carlos", dto.getMiddleName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Gomez", dto.getSecondLastName());
        assertEquals("JR", dto.getNameSuffixCode());
        assertEquals("Juan", dto.getBirthName());
        assertSame(aliases, dto.getAliases());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        PersonNameRequestDTO name = new PersonNameRequestDTO();
        List<ForeignTaxisRequestDTO> taxes = List.of(new ForeignTaxisRequestDTO());
        PlaceOfBirthRequestDTO birth = new PlaceOfBirthRequestDTO();
        CountryOfResidenceRequestDTO residence = new CountryOfResidenceRequestDTO();
        FirstNationalityRequestDTO first = new FirstNationalityRequestDTO();
        SecondNationalityRequestDTO second = new SecondNationalityRequestDTO();
        PublicOfficeInformationRequestDTO publicOffice = new PublicOfficeInformationRequestDTO();
        EmploymentInformationRequestDTO employment = new EmploymentInformationRequestDTO();
        PreferredLanguageRequestDTO language = new PreferredLanguageRequestDTO();
        List<DocumentRequestDTO> documents = List.of(new DocumentRequestDTO());

        PersonRequestDTO dto = new PersonRequestDTO();
        dto.setPersonName(name);
        dto.setMotherName("Mother");
        dto.setFatherName("Father");
        dto.setForeignTaxes(taxes);
        dto.setGenderCode("M");
        dto.setBirthDate("2000-01-01");
        dto.setPlaceOfBirth(birth);
        dto.setCountryOfResidence(residence);
        dto.setFirstNationality(first);
        dto.setSecondNationality(second);
        dto.setResidentialStatusCode("R");
        dto.setCivilStatusCode("S");
        dto.setStaffCode("STF");
        dto.setLegallyIncapacitated(false);
        dto.setLegallyCapableMinor(false);
        dto.setDiplomatic(false);
        dto.setPublicOfficeInformation(publicOffice);
        dto.setEducationalLevelCode("EDU");
        dto.setForeignTaxIndicator("YES");
        dto.setAccountingSectorCode("ACC");
        dto.setEmploymentInformation(employment);
        dto.setPreferredLanguage(language);
        dto.setDocuments(documents);

        assertSame(name, dto.getPersonName());
        assertEquals("Mother", dto.getMotherName());
        assertEquals("Father", dto.getFatherName());
        assertSame(taxes, dto.getForeignTaxes());
        assertEquals("M", dto.getGenderCode());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertSame(birth, dto.getPlaceOfBirth());
        assertSame(residence, dto.getCountryOfResidence());
        assertSame(first, dto.getFirstNationality());
        assertSame(second, dto.getSecondNationality());
        assertEquals("R", dto.getResidentialStatusCode());
        assertEquals("S", dto.getCivilStatusCode());
        assertEquals("STF", dto.getStaffCode());
        assertFalse(dto.getLegallyIncapacitated());
        assertFalse(dto.getLegallyCapableMinor());
        assertFalse(dto.getDiplomatic());
        assertSame(publicOffice, dto.getPublicOfficeInformation());
        assertEquals("EDU", dto.getEducationalLevelCode());
        assertEquals("YES", dto.getForeignTaxIndicator());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertSame(employment, dto.getEmploymentInformation());
        assertSame(language, dto.getPreferredLanguage());
        assertSame(documents, dto.getDocuments());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfBirthRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO country = new CountryRequestDTO();
        StateRequestDTO state = new StateRequestDTO();

        PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();
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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaceOfRegistrationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        CountryRequestDTO country = new CountryRequestDTO();
        StateRequestDTO state = new StateRequestDTO();

        PlaceOfRegistrationRequestDTO dto = new PlaceOfRegistrationRequestDTO();
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogota");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogota", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PostalAddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        StateRequestDTO state = new StateRequestDTO();
        ProvinceRequestDTO province = new ProvinceRequestDTO();
        RegionIdentificationRequestDTO region = new RegionIdentificationRequestDTO();
        CountyIdentificationRequestDTO county = new CountyIdentificationRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();
        List<String> lines = List.of("line1");

        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();
        dto.setFullAddress("Full address");
        dto.setFormatCode("F");
        dto.setStreetTypeCode("ST");
        dto.setStreetName("Main");
        dto.setSecondaryStreetName("Second");
        dto.setStreetBuildingIdentification("10");
        dto.setBuildingName("Tower");
        dto.setFloor("2");
        dto.setDetailCode("D");
        dto.setUnitType("APT");
        dto.setUnitNumber("201");
        dto.setPremise("Premise");
        dto.setAlternativePremise("Alt");
        dto.setMailingInstructions("Mail");
        dto.setPostCodeIdentification("110111");
        dto.setTownName("Bogota");
        dto.setMailDeliverySubLocation("Sub");
        dto.setState(state);
        dto.setDistrictName("District");
        dto.setSecondaryDistrictName("District2");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("N");
        dto.setPostOfficeBox("PO");
        dto.setPostBoxTypeCode("PB");
        dto.setForeignAddressLines(lines);
        dto.setZipCode("110111");
        dto.setZip4Code("1234");
        dto.setRuralTypeCode("R");
        dto.setRuralNumber("1");

        assertEquals("Full address", dto.getFullAddress());
        assertEquals("F", dto.getFormatCode());
        assertEquals("ST", dto.getStreetTypeCode());
        assertEquals("Main", dto.getStreetName());
        assertEquals("Second", dto.getSecondaryStreetName());
        assertEquals("10", dto.getStreetBuildingIdentification());
        assertEquals("Tower", dto.getBuildingName());
        assertEquals("2", dto.getFloor());
        assertEquals("D", dto.getDetailCode());
        assertEquals("APT", dto.getUnitType());
        assertEquals("201", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alt", dto.getAlternativePremise());
        assertEquals("Mail", dto.getMailingInstructions());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertEquals("Bogota", dto.getTownName());
        assertEquals("Sub", dto.getMailDeliverySubLocation());
        assertSame(state, dto.getState());
        assertEquals("District", dto.getDistrictName());
        assertEquals("District2", dto.getSecondaryDistrictName());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("N", dto.getMilitary());
        assertEquals("PO", dto.getPostOfficeBox());
        assertEquals("PB", dto.getPostBoxTypeCode());
        assertSame(lines, dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
        assertEquals("1234", dto.getZip4Code());
        assertEquals("R", dto.getRuralTypeCode());
        assertEquals("1", dto.getRuralNumber());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PreferredLanguageRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        PreferredLanguageRequestDTO dto = new PreferredLanguageRequestDTO();
        dto.setCode("ES");
        assertEquals("ES", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProvinceRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO();
        dto.setCode("11");
        assertEquals("11", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicOfficeInformationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ValidityPeriodRequestDTO validity = new ValidityPeriodRequestDTO();

        PublicOfficeInformationRequestDTO dto = new PublicOfficeInformationRequestDTO();
        dto.setPositionCode("POS");
        dto.setValidityPeriod(validity);

        assertEquals("POS", dto.getPositionCode());
        assertSame(validity, dto.getValidityPeriod());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegionIdentificationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        RegionIdentificationRequestDTO dto = new RegionIdentificationRequestDTO();
        dto.setCode("REG");
        assertEquals("REG", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecondNationalityRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        SecondNationalityRequestDTO dto = new SecondNationalityRequestDTO();
        dto.setCode("US");
        assertEquals("US", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        StateRequestDTO dto = new StateRequestDTO();
        dto.setCode("11");
        assertEquals("11", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UseTypeRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        UseTypeRequestDTO dto = new UseTypeRequestDTO();
        dto.setCode("HOME");
        assertEquals("HOME", dto.getCode());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ValidityPeriodRequestDTO dto = new ValidityPeriodRequestDTO();
        dto.setStartDate("2024-01-01");
        dto.setEndDate("2025-01-01");

        assertEquals("2024-01-01", dto.getStartDate());
        assertEquals("2025-01-01", dto.getEndDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WebAddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        WebAddressRequestDTO dto = new WebAddressRequestDTO();
        dto.setUrl("https://test.com");
        assertEquals("https://test.com", dto.getUrl());
    }
}