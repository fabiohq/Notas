package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CustomerRequestDTOTest {

    @Test
    void shouldCoverCustomerRequestDTO() {
        CustomerRequestDTO dto = new CustomerRequestDTO();

        PersonRequestDto person = new PersonRequestDto();
        OrganizationRequestDto organization = new OrganizationRequestDto();
        PhoneAddressRequestDTO phoneAddress = new PhoneAddressRequestDTO();
        ElectronicAddressRequestDtO electronicAddress = new ElectronicAddressRequestDtO();
        PostalAddressRequestDTO postalAddress = new PostalAddressRequestDTO();
        BankRequestDTO bank = new BankRequestDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setPhoneAddress(phoneAddress);
        dto.setElectronicAddress(electronicAddress);
        dto.setPostalAddress(postalAddress);
        dto.setBank(bank);

        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals(phoneAddress, dto.getPhoneAddress());
        assertEquals(electronicAddress, dto.getElectronicAddress());
        assertEquals(postalAddress, dto.getPostalAddress());
        assertEquals(bank, dto.getBank());
    }
}

class PhoneAddressRequestDTOTest {

    @Test
    void shouldCoverPhoneAddressRequestDTO() {
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

class PostalAddressRequestDTOTest {

    @Test
    void shouldCoverPostalAddressRequestDTO() {
        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();

        ProvinceRequestDTO province = new ProvinceRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();

        dto.setProvince(province);
        dto.setTownName("Bogota");
        dto.setCountry(country);

        assertEquals(province, dto.getProvince());
        assertEquals("Bogota", dto.getTownName());
        assertEquals(country, dto.getCountry());
    }
}

class PersonNameRequestDTOTest {

    @Test
    void shouldCoverPersonNameRequestDTO() {
        PersonNameRequestDTO dto = new PersonNameRequestDTO();

        dto.setGivenName("Fabio");
        dto.setLastName("Hernandez");
        dto.setSecondLastName("Test");

        assertEquals("Fabio", dto.getGivenName());
        assertEquals("Hernandez", dto.getLastName());
        assertEquals("Test", dto.getSecondLastName());
    }
}

class OrganizationRequestDtoTest {

    @Test
    void shouldCoverOrganizationRequestDto() {
        OrganizationRequestDto dto = new OrganizationRequestDto();

        OrganizationNameRequestDTO organizationName = new OrganizationNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        dto.setRegistrationDate("2026-05-29");
        dto.setOrganizationName(organizationName);
        dto.setDocument(document);

        assertEquals("2026-05-29", dto.getRegistrationDate());
        assertEquals(organizationName, dto.getOrganizationName());
        assertEquals(document, dto.getDocument());
    }
}

class PersonRequestDtoTest {

    @Test
    void shouldCoverPersonRequestDto() {
        PersonRequestDto dto = new PersonRequestDto();

        PersonNameRequestDTO personName = new PersonNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        dto.setPersonName(personName);
        dto.setBirthDate("2000-01-01");
        dto.setDocument(document);

        assertEquals(personName, dto.getPersonName());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertEquals(document, dto.getDocument());
    }
}

class BankRequestDTOTest {

    @Test
    void shouldCoverBankRequestDTO() {
        BankRequestDTO dto = new BankRequestDTO();

        CenterRequestDTO center = new CenterRequestDTO();

        dto.setBankId("001");
        dto.setCenter(center);

        assertEquals("001", dto.getBankId());
        assertEquals(center, dto.getCenter());
    }
}

class DocumentRequestDTOTest {

    @Test
    void shouldCoverDocumentRequestDTO() {
        DocumentRequestDTO dto = new DocumentRequestDTO();

        StateRequestDTO state = new StateRequestDTO();

        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("123456789");
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("123456789", dto.getDocumentNumber());
        assertEquals(state, dto.getState());
    }
}

class CountryRequestDTOTest {

    @Test
    void shouldCoverCountryRequestDTO() {
        CountryRequestDTO dto = new CountryRequestDTO();

        dto.setCode("CO");

        assertEquals("CO", dto.getCode());
    }
}

class CenterRequestDTOTest {

    @Test
    void shouldCoverCenterRequestDTO() {
        CenterRequestDTO dto = new CenterRequestDTO();

        dto.setCenterId("1234");

        assertEquals("1234", dto.getCenterId());
    }
}

class OrganizationNameRequestDTOTest {

    @Test
    void shouldCoverOrganizationNameRequestDTO() {
        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();

        dto.setLegalName("Santander Test");

        assertEquals("Santander Test", dto.getLegalName());
    }
}

class StateRequestDTOTest {

    @Test
    void shouldCoverStateRequestDTO() {
        StateRequestDTO dto = new StateRequestDTO();

        dto.setCode("11");

        assertEquals("11", dto.getCode());
    }
}

class ElectronicAddressRequestDtOTest {

    @Test
    void shouldCoverElectronicAddressRequestDtO() {
        ElectronicAddressRequestDtO dto = new ElectronicAddressRequestDtO();

        dto.setEmailAddress("test@santander.com");

        assertEquals("test@santander.com", dto.getEmailAddress());
    }
}

class ProvinceRequestDTOTest {

    @Test
    void shouldCoverProvinceRequestDTO() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO();

        dto.setCode("CUN");

        assertEquals("CUN", dto.getCode());
    }
}
