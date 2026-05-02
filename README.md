Te dejo los tests por clase, sin acoplar.
BankRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CenterRequestDTO center = new CenterRequestDTO("001");

        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("0065");
        dto.setCenter(center);

        assertEquals("0065", dto.getBankId());
        assertEquals(center, dto.getCenter());

        BankRequestDTO dto2 = new BankRequestDTO("0001", center);
        assertEquals("0001", dto2.getBankId());
        assertEquals(center, dto2.getCenter());

        BankRequestDTO dto3 = BankRequestDTO.builder()
                .bankId("0002")
                .center(center)
                .build();

        assertEquals("0002", dto3.getBankId());
        assertEquals(center, dto3.getCenter());
    }
}
CenterRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CenterRequestDTO dto = new CenterRequestDTO();
        dto.setCenterId("001");

        assertEquals("001", dto.getCenterId());

        CenterRequestDTO dto2 = new CenterRequestDTO("002");
        assertEquals("002", dto2.getCenterId());

        CenterRequestDTO dto3 = CenterRequestDTO.builder()
                .centerId("003")
                .build();

        assertEquals("003", dto3.getCenterId());
    }
}
CountryRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CountryRequestDTO dto = new CountryRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        CountryRequestDTO dto2 = new CountryRequestDTO("US");
        assertEquals("US", dto2.getCode());

        CountryRequestDTO dto3 = CountryRequestDTO.builder()
                .code("ES")
                .build();

        assertEquals("ES", dto3.getCode());
    }
}
CustomerRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PersonDTO person = new PersonDTO();
        OrganizationRequestDto organization = new OrganizationRequestDto();
        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO();
        ElectronicAddressRequestDtO email = new ElectronicAddressRequestDtO();
        PostalAddressRequestDTO postal = new PostalAddressRequestDTO();
        BankRequestDTO bank = new BankRequestDTO();

        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(email);
        dto.setPostalAddress(postal);
        dto.setBank(bank);

        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals(phone, dto.getPhoneAddress());
        assertEquals(email, dto.getElectronicAddress());
        assertEquals(postal, dto.getPostalAddress());
        assertEquals(bank, dto.getBank());

        CustomerRequestDTO dto2 = new CustomerRequestDTO(person, organization, phone, email, postal, bank);
        assertEquals(person, dto2.getPerson());

        CustomerRequestDTO dto3 = CustomerRequestDTO.builder()
                .person(person)
                .organization(organization)
                .phoneAddress(phone)
                .electronicAddress(email)
                .postalAddress(postal)
                .bank(bank)
                .build();

        assertEquals(bank, dto3.getBank());
    }
}
DocumentRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        CountryRequestDTO country = new CountryRequestDTO("CO");

        DocumentRequestDTO dto = new DocumentRequestDTO();
        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("123456");
        dto.setIssueDate("2020-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("REG");
        dto.setCountry(country);
        dto.setTown("Medellin");

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("123456", dto.getDocumentNumber());
        assertEquals("2020-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("REG", dto.getIssuerEntity());
        assertEquals(country, dto.getCountry());
        assertEquals("Medellin", dto.getTown());

        DocumentRequestDTO dto2 = new DocumentRequestDTO(
                "CE", "789", "2021-01-01", "2031-01-01", "ENT", country, "Bogota"
        );

        assertEquals("CE", dto2.getDocumentTypeCode());

        DocumentRequestDTO dto3 = DocumentRequestDTO.builder()
                .documentTypeCode("CC")
                .documentNumber("999")
                .country(country)
                .town("Cali")
                .build();

        assertEquals("999", dto3.getDocumentNumber());
        assertEquals("Cali", dto3.getTown());
    }
}
ElectronicAddressRequestDtOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressRequestDtOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        ElectronicAddressRequestDtO dto = new ElectronicAddressRequestDtO();
        dto.setEmailAddress("test@test.com");

        assertEquals("test@test.com", dto.getEmailAddress());

        ElectronicAddressRequestDtO dto2 = new ElectronicAddressRequestDtO("a@b.com");
        assertEquals("a@b.com", dto2.getEmailAddress());

        ElectronicAddressRequestDtO dto3 = ElectronicAddressRequestDtO.builder()
                .emailAddress("c@d.com")
                .build();

        assertEquals("c@d.com", dto3.getEmailAddress());
    }
}
OrganizationNameRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();
        dto.setLegalName("Empresa");

        assertEquals("Empresa", dto.getLegalName());

        OrganizationNameRequestDTO dto2 = new OrganizationNameRequestDTO("Empresa 2");
        assertEquals("Empresa 2", dto2.getLegalName());

        OrganizationNameRequestDTO dto3 = OrganizationNameRequestDTO.builder()
                .legalName("Empresa 3")
                .build();

        assertEquals("Empresa 3", dto3.getLegalName());
    }
}
OrganizationRequestDtoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRequestDtoTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        OrganizationNameRequestDTO name = new OrganizationNameRequestDTO("Empresa");
        DocumentRequestDTO document = new DocumentRequestDTO();

        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setRegistrationDate("2020-01-01");
        dto.setOrganizationName(name);
        dto.setDocument(document);

        assertEquals("2020-01-01", dto.getRegistrationDate());
        assertEquals(name, dto.getOrganizationName());
        assertEquals(document, dto.getDocument());

        OrganizationRequestDto dto2 = new OrganizationRequestDto("2021-01-01", name, document);
        assertEquals("2021-01-01", dto2.getRegistrationDate());

        OrganizationRequestDto dto3 = OrganizationRequestDto.builder()
                .registrationDate("2022-01-01")
                .organizationName(name)
                .document(document)
                .build();

        assertEquals(document, dto3.getDocument());
    }
}
PersonNameRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PersonNameRequestDTO dto = new PersonNameRequestDTO();
        dto.setGivenName("Juan");
        dto.setLastName("Perez");
        dto.setSecondLastName("Lopez");

        assertEquals("Juan", dto.getGivenName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Lopez", dto.getSecondLastName());

        PersonNameRequestDTO dto2 = new PersonNameRequestDTO("Ana", "Gomez", "Ruiz");
        assertEquals("Ana", dto2.getGivenName());

        PersonNameRequestDTO dto3 = PersonNameRequestDTO.builder()
                .givenName("Luis")
                .lastName("Diaz")
                .secondLastName("Mora")
                .build();

        assertEquals("Luis", dto3.getGivenName());
    }
}
PersonRequestDtoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.EmploymentInformationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PlaceOfBirthDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PublicOfficeInformationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDtoTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PersonNameRequestDTO personName = new PersonNameRequestDTO();
        PlaceOfBirthDTO placeOfBirth = new PlaceOfBirthDTO();
        CodeNameDTO codeName = new CodeNameDTO("CO", "Colombia");
        PublicOfficeInformationDTO office = new PublicOfficeInformationDTO();
        EmploymentInformationDTO employment = new EmploymentInformationDTO();
        DocumentRequestDTO documentRequest = new DocumentRequestDTO();
        DocumentDTO document = new DocumentDTO();

        PersonRequestDto dto = new PersonRequestDto();
        dto.setPersonName(personName);
        dto.setMotherName("Madre");
        dto.setFatherName("Padre");
        dto.setForeignTaxIndicator("YES");
        dto.setGenderCode("H");
        dto.setBirthDate("2000-01-01");
        dto.setPlaceOfBirth(placeOfBirth);
        dto.setCountryOfResidence(codeName);
        dto.setFirstNationality(codeName);
        dto.setSecondNationality(codeName);
        dto.setResidentialStatusCode("RES");
        dto.setCivilStatusCode("SOL");
        dto.setStaffCode("STAFF");
        dto.setLegallyIncapacitated(false);
        dto.setLegallyCapableMinor(false);
        dto.setDiplomatic(false);
        dto.setPublicOfficeInformation(office);
        dto.setEducationalLevelCode("EDU");
        dto.setAccountingSectorCode("ACC");
        dto.setEmploymentInformation(employment);
        dto.setPreferredLanguage(codeName);
        dto.setDocuments(List.of(documentRequest));
        dto.setDocument(document);

        assertEquals(personName, dto.getPersonName());
        assertEquals("Madre", dto.getMotherName());
        assertEquals("Padre", dto.getFatherName());
        assertEquals("YES", dto.getForeignTaxIndicator());
        assertEquals("H", dto.getGenderCode());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertEquals(placeOfBirth, dto.getPlaceOfBirth());
        assertEquals(codeName, dto.getCountryOfResidence());
        assertEquals(codeName, dto.getFirstNationality());
        assertEquals(codeName, dto.getSecondNationality());
        assertEquals("RES", dto.getResidentialStatusCode());
        assertEquals("SOL", dto.getCivilStatusCode());
        assertEquals("STAFF", dto.getStaffCode());
        assertFalse(dto.getLegallyIncapacitated());
        assertFalse(dto.getLegallyCapableMinor());
        assertFalse(dto.getDiplomatic());
        assertEquals(office, dto.getPublicOfficeInformation());
        assertEquals("EDU", dto.getEducationalLevelCode());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertEquals(employment, dto.getEmploymentInformation());
        assertEquals(codeName, dto.getPreferredLanguage());
        assertEquals(1, dto.getDocuments().size());
        assertEquals(document, dto.getDocument());

        PersonRequestDto dto2 = PersonRequestDto.builder()
                .personName(personName)
                .documents(List.of(documentRequest))
                .genderCode("M")
                .build();

        assertEquals("M", dto2.getGenderCode());
    }
}
PhoneAddressRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        PhoneAddressRequestDTO dto = new PhoneAddressRequestDTO();
        dto.setMobileNumber("3001234567");
        dto.setPhoneNumber("6011234567");
        dto.setInternationalCode("57");
        dto.setExtension("123");

        assertEquals("3001234567", dto.getMobileNumber());
        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("57", dto.getInternationalCode());
        assertEquals("123", dto.getExtension());

        PhoneAddressRequestDTO dto2 = new PhoneAddressRequestDTO("1", "2", "3", "4");
        assertEquals("1", dto2.getMobileNumber());

        PhoneAddressRequestDTO dto3 = PhoneAddressRequestDTO.builder()
                .mobileNumber("5")
                .phoneNumber("6")
                .internationalCode("7")
                .extension("8")
                .build();

        assertEquals("8", dto3.getExtension());
    }
}
PostalAddressRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        ProvinceRequestDTO province = new ProvinceRequestDTO("05");
        CountryRequestDTO country = new CountryRequestDTO("CO");

        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();
        dto.setProvince(province);
        dto.setTownName("Medellin");
        dto.setCountry(country);

        assertEquals(province, dto.getProvince());
        assertEquals("Medellin", dto.getTownName());
        assertEquals(country, dto.getCountry());

        PostalAddressRequestDTO dto2 = new PostalAddressRequestDTO(province, "Bogota", country);
        assertEquals("Bogota", dto2.getTownName());

        PostalAddressRequestDTO dto3 = PostalAddressRequestDTO.builder()
                .province(province)
                .townName("Cali")
                .country(country)
                .build();

        assertEquals("Cali", dto3.getTownName());
    }
}
ProvinceRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO();
        dto.setCode("05");

        assertEquals("05", dto.getCode());

        ProvinceRequestDTO dto2 = new ProvinceRequestDTO("11");
        assertEquals("11", dto2.getCode());

        ProvinceRequestDTO dto3 = ProvinceRequestDTO.builder()
                .code("76")
                .build();

        assertEquals("76", dto3.getCode());
    }
}
StateRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateRequestDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        StateRequestDTO dto = new StateRequestDTO();
        dto.setCode("05");

        assertEquals("05", dto.getCode());

        StateRequestDTO dto2 = new StateRequestDTO("11");
        assertEquals("11", dto2.getCode());

        StateRequestDTO dto3 = StateRequestDTO.builder()
                .code("76")
                .build();

        assertEquals("76", dto3.getCode());
    }
}