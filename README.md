Aquí van las que faltan:
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonDTO dto = new PersonDTO();
        PersonNameDTO name = new PersonNameDTO();
        PlaceOfBirthDTO birth = new PlaceOfBirthDTO();
        CodeNameDTO codeName = new CodeNameDTO("CO", "Colombia");
        PublicOfficeInformationDTO publicOffice = new PublicOfficeInformationDTO();
        EmploymentInformationDTO employment = new EmploymentInformationDTO();
        DocumentDTO document = new DocumentDTO();

        dto.setPersonName(name);
        dto.setMotherName("Mother");
        dto.setFatherName("Father");
        dto.setGenderCode("H");
        dto.setGenderDescription("HOMBRE");
        dto.setBirthDate("2000-01-01");
        dto.setPlaceOfBirth(birth);
        dto.setCountryOfResidence(codeName);
        dto.setForeignTaxIndicator("NO");
        dto.setFirstNationality(codeName);
        dto.setSecondNationality(codeName);
        dto.setResidentialStatusCode("RES");
        dto.setResidentialStatusDescription("Residente");
        dto.setCivilStatusCode("S");
        dto.setCivilStatusDescription("Soltero");
        dto.setPublicOfficeInformation(publicOffice);
        dto.setDeathDate("2099-01-01");
        dto.setEmployeeIndicator(true);
        dto.setStaffCode("STF");
        dto.setStaffDescription("Staff");
        dto.setLegallyIncapacitated(false);
        dto.setLegallyCapableMinor(false);
        dto.setDiplomatic(false);
        dto.setEducationalLevelCode("UNI");
        dto.setEducationalLevelDescription("Universitario");
        dto.setAccountingSectorCode("ACC");
        dto.setAccountingSectorDescription("Sector");
        dto.setEmploymentInformation(employment);
        dto.setPreferredLanguage(codeName);
        dto.setDocuments(List.of(document));

        assertSame(name, dto.getPersonName());
        assertEquals("Mother", dto.getMotherName());
        assertEquals("Father", dto.getFatherName());
        assertEquals("H", dto.getGenderCode());
        assertEquals("HOMBRE", dto.getGenderDescription());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertSame(birth, dto.getPlaceOfBirth());
        assertSame(codeName, dto.getCountryOfResidence());
        assertEquals("NO", dto.getForeignTaxIndicator());
        assertSame(codeName, dto.getFirstNationality());
        assertSame(codeName, dto.getSecondNationality());
        assertEquals("RES", dto.getResidentialStatusCode());
        assertEquals("Residente", dto.getResidentialStatusDescription());
        assertEquals("S", dto.getCivilStatusCode());
        assertEquals("Soltero", dto.getCivilStatusDescription());
        assertSame(publicOffice, dto.getPublicOfficeInformation());
        assertEquals("2099-01-01", dto.getDeathDate());
        assertTrue(dto.getEmployeeIndicator());
        assertEquals("STF", dto.getStaffCode());
        assertEquals("Staff", dto.getStaffDescription());
        assertFalse(dto.getLegallyIncapacitated());
        assertFalse(dto.getLegallyCapableMinor());
        assertFalse(dto.getDiplomatic());
        assertEquals("UNI", dto.getEducationalLevelCode());
        assertEquals("Universitario", dto.getEducationalLevelDescription());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertEquals("Sector", dto.getAccountingSectorDescription());
        assertSame(employment, dto.getEmploymentInformation());
        assertSame(codeName, dto.getPreferredLanguage());
        assertEquals(List.of(document), dto.getDocuments());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameDTO dto = new PersonNameDTO();

        dto.setNamePrefixCode("MR");
        dto.setNamePrefixDescription("Señor");
        dto.setGivenName("Juan");
        dto.setMiddleName("Carlos");
        dto.setLastName("Pérez");
        dto.setSecondLastName("Gómez");
        dto.setNameSuffixCode("JR");
        dto.setNameSuffixDescription("Junior");
        dto.setFullName("Juan Carlos Pérez Gómez");
        dto.setBirthName("Juan");
        dto.setAliases(List.of("Alias1"));

        assertEquals("MR", dto.getNamePrefixCode());
        assertEquals("Señor", dto.getNamePrefixDescription());
        assertEquals("Juan", dto.getGivenName());
        assertEquals("Carlos", dto.getMiddleName());
        assertEquals("Pérez", dto.getLastName());
        assertEquals("Gómez", dto.getSecondLastName());
        assertEquals("JR", dto.getNameSuffixCode());
        assertEquals("Junior", dto.getNameSuffixDescription());
        assertEquals("Juan Carlos Pérez Gómez", dto.getFullName());
        assertEquals("Juan", dto.getBirthName());
        assertEquals(List.of("Alias1"), dto.getAliases());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PhoneAddressDTO dto = new PhoneAddressDTO();

        dto.setMobileNumber("3001234567");
        dto.setPhoneNumber("6011234567");
        dto.setFaxNumber("6017654321");
        dto.setInternationalCode("57");
        dto.setExtension("123");

        assertEquals("3001234567", dto.getMobileNumber());
        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("6017654321", dto.getFaxNumber());
        assertEquals("57", dto.getInternationalCode());
        assertEquals("123", dto.getExtension());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfBirthDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO state = new CodeNameDTO("11", "Bogotá");

        PlaceOfBirthDTO dto = new PlaceOfBirthDTO();
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogotá");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogotá", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfRegistrationDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO state = new CodeNameDTO("11", "Bogotá");

        PlaceOfRegistrationDTO dto = new PlaceOfRegistrationDTO();
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogotá");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogotá", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.BankDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProspectDetailResponseDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonDTO person = new PersonDTO();
        OrganizationDTO organization = new OrganizationDTO();
        ContactPointDTO contactPoint = new ContactPointDTO();
        BankDTO bank = new BankDTO();
        DataOriginDTO origin = new DataOriginDTO();

        ProspectDetailResponseDTO dto = new ProspectDetailResponseDTO();
        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setContactPoints(List.of(contactPoint));
        dto.setHighConfidentialityIndicator(true);
        dto.setIsPendingExCustomer(false);
        dto.setConfidentialityLevel("LOW");
        dto.setBank(bank);
        dto.setDataOrigins(List.of(origin));
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSegmentDescription("Segmento");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setStructuralSubsegmentDescription("Subsegmento");

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertEquals(List.of(contactPoint), dto.getContactPoints());
        assertTrue(dto.getHighConfidentialityIndicator());
        assertFalse(dto.getIsPendingExCustomer());
        assertEquals("LOW", dto.getConfidentialityLevel());
        assertSame(bank, dto.getBank());
        assertEquals(List.of(origin), dto.getDataOrigins());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("Segmento", dto.getStructuralSegmentDescription());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertEquals("Subsegmento", dto.getStructuralSubsegmentDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicOfficeInformationDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ValidityPeriodDTO validity = new ValidityPeriodDTO();

        PublicOfficeInformationDTO dto = new PublicOfficeInformationDTO();
        dto.setPositionCode("POS");
        dto.setPositionDescription("Cargo público");
        dto.setValidityPeriod(validity);

        assertEquals("POS", dto.getPositionCode());
        assertEquals("Cargo público", dto.getPositionDescription());
        assertSame(validity, dto.getValidityPeriod());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ValidityPeriodDTO dto = new ValidityPeriodDTO();

        dto.setStartDate("2026-01-01");
        dto.setEndDate("2026-12-31");

        assertEquals("2026-01-01", dto.getStartDate());
        assertEquals("2026-12-31", dto.getEndDate());
    }
}