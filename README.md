Aquí están en el mismo orden y por separado.
BankRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankRequestDTOTest {

    @Test
    void shouldCoverBankRequestDTO() {
        CenterRequestDTO center = new CenterRequestDTO();

        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("BANK");
        dto.setCenter(center);

        assertEquals("BANK", dto.getBankId());
        assertSame(center, dto.getCenter());

        BankRequestDTO builder = BankRequestDTO.builder()
                .bankId("BANK")
                .center(center)
                .build();

        assertEquals("BANK", builder.getBankId());
        assertSame(center, builder.getCenter());

        BankRequestDTO allArgs = new BankRequestDTO("BANK", center);

        assertEquals("BANK", allArgs.getBankId());
        assertSame(center, allArgs.getCenter());
    }
}
CenterRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterRequestDTOTest {

    @Test
    void shouldCoverCenterRequestDTO() {
        CenterRequestDTO dto = new CenterRequestDTO();
        dto.setCenterId("CENTER");

        assertEquals("CENTER", dto.getCenterId());

        CenterRequestDTO builder = CenterRequestDTO.builder()
                .centerId("CENTER")
                .build();

        assertEquals("CENTER", builder.getCenterId());

        CenterRequestDTO allArgs = new CenterRequestDTO("CENTER");

        assertEquals("CENTER", allArgs.getCenterId());
    }
}
CountryRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryRequestDTOTest {

    @Test
    void shouldCoverCountryRequestDTO() {
        CountryRequestDTO dto = new CountryRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());

        CountryRequestDTO builder = CountryRequestDTO.builder()
                .code("CO")
                .build();

        assertEquals("CO", builder.getCode());

        CountryRequestDTO allArgs = new CountryRequestDTO("CO");

        assertEquals("CO", allArgs.getCode());
    }
}
CustomerRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestDTOTest {

    @Test
    void shouldCoverCustomerRequestDTO() {
        PersonRequestDto person = new PersonRequestDto();
        OrganizationRequestDto organization = new OrganizationRequestDto();
        PhoneAddressRequestDTO phoneAddress = new PhoneAddressRequestDTO();
        ElectronicAddressRequestDtO electronicAddress = new ElectronicAddressRequestDtO();
        PostalAddressRequestDTO postalAddress = new PostalAddressRequestDTO();
        BankRequestDTO bank = new BankRequestDTO();

        CustomerRequestDTO dto = new CustomerRequestDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setPhoneAddress(phoneAddress);
        dto.setElectronicAddress(electronicAddress);
        dto.setPostalAddress(postalAddress);
        dto.setBank(bank);

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertSame(phoneAddress, dto.getPhoneAddress());
        assertSame(electronicAddress, dto.getElectronicAddress());
        assertSame(postalAddress, dto.getPostalAddress());
        assertSame(bank, dto.getBank());

        CustomerRequestDTO builder = CustomerRequestDTO.builder()
                .person(person)
                .organization(organization)
                .phoneAddress(phoneAddress)
                .electronicAddress(electronicAddress)
                .postalAddress(postalAddress)
                .bank(bank)
                .build();

        assertSame(person, builder.getPerson());
        assertSame(bank, builder.getBank());

        CustomerRequestDTO allArgs = new CustomerRequestDTO(
                person, organization, phoneAddress, electronicAddress, postalAddress, bank
        );

        assertSame(organization, allArgs.getOrganization());
        assertSame(postalAddress, allArgs.getPostalAddress());
    }
}
DocumentRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRequestDTOTest {

    @Test
    void shouldCoverDocumentRequestDTO() {
        StateRequestDTO state = new StateRequestDTO();

        DocumentRequestDTO dto = new DocumentRequestDTO();

        dto.setDocumentTypeCode("TYPE");
        dto.setDocumentNumber("NUMBER");
        dto.setState(state);

        assertEquals("TYPE", dto.getDocumentTypeCode());
        assertEquals("NUMBER", dto.getDocumentNumber());
        assertSame(state, dto.getState());

        DocumentRequestDTO builder = DocumentRequestDTO.builder()
                .documentTypeCode("TYPE")
                .documentNumber("NUMBER")
                .state(state)
                .build();

        assertEquals("TYPE", builder.getDocumentTypeCode());
        assertSame(state, builder.getState());

        DocumentRequestDTO allArgs = new DocumentRequestDTO("TYPE", "NUMBER", state);

        assertEquals("NUMBER", allArgs.getDocumentNumber());
        assertSame(state, allArgs.getState());
    }
}
ElectronicAddressRequestDtOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressRequestDtOTest {

    @Test
    void shouldCoverElectronicAddressRequestDtO() {
        ElectronicAddressRequestDtO dto = new ElectronicAddressRequestDtO();

        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());

        ElectronicAddressRequestDtO builder = ElectronicAddressRequestDtO.builder()
                .emailAddress("test@mail.com")
                .build();

        assertEquals("test@mail.com", builder.getEmailAddress());

        ElectronicAddressRequestDtO allArgs =
                new ElectronicAddressRequestDtO("test@mail.com");

        assertEquals("test@mail.com", allArgs.getEmailAddress());
    }
}
OrganizationNameRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameRequestDTOTest {

    @Test
    void shouldCoverOrganizationNameRequestDTO() {
        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();

        dto.setLegalName("LEGAL");

        assertEquals("LEGAL", dto.getLegalName());

        OrganizationNameRequestDTO builder = OrganizationNameRequestDTO.builder()
                .legalName("LEGAL")
                .build();

        assertEquals("LEGAL", builder.getLegalName());

        OrganizationNameRequestDTO allArgs = new OrganizationNameRequestDTO("LEGAL");

        assertEquals("LEGAL", allArgs.getLegalName());
    }
}
OrganizationRequestDtoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRequestDtoTest {

    @Test
    void shouldCoverOrganizationRequestDto() {
        OrganizationNameRequestDTO organizationName = new OrganizationNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        OrganizationRequestDto dto = new OrganizationRequestDto();

        dto.setRegistrationDate("DATE");
        dto.setOrganizationName(organizationName);
        dto.setDocument(document);

        assertEquals("DATE", dto.getRegistrationDate());
        assertSame(organizationName, dto.getOrganizationName());
        assertSame(document, dto.getDocument());

        OrganizationRequestDto builder = OrganizationRequestDto.builder()
                .registrationDate("DATE")
                .organizationName(organizationName)
                .document(document)
                .build();

        assertEquals("DATE", builder.getRegistrationDate());
        assertSame(document, builder.getDocument());

        OrganizationRequestDto allArgs =
                new OrganizationRequestDto("DATE", organizationName, document);

        assertSame(organizationName, allArgs.getOrganizationName());
        assertSame(document, allArgs.getDocument());
    }
}
PersonNameRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameRequestDTOTest {

    @Test
    void shouldCoverPersonNameRequestDTO() {
        PersonNameRequestDTO dto = new PersonNameRequestDTO();

        dto.setGivenName("GIVEN");
        dto.setLastName("LAST");
        dto.setSecondLastName("SECOND");

        assertEquals("GIVEN", dto.getGivenName());
        assertEquals("LAST", dto.getLastName());
        assertEquals("SECOND", dto.getSecondLastName());

        PersonNameRequestDTO builder = PersonNameRequestDTO.builder()
                .givenName("GIVEN")
                .lastName("LAST")
                .secondLastName("SECOND")
                .build();

        assertEquals("GIVEN", builder.getGivenName());

        PersonNameRequestDTO allArgs =
                new PersonNameRequestDTO("GIVEN", "LAST", "SECOND");

        assertEquals("SECOND", allArgs.getSecondLastName());
    }
}
PersonRequestDtoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDtoTest {

    @Test
    void shouldCoverPersonRequestDto() {
        PersonNameRequestDTO personName = new PersonNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        PersonRequestDto dto = new PersonRequestDto();

        dto.setPersonName(personName);
        dto.setBirthDate("BIRTH");
        dto.setDocument(document);

        assertSame(personName, dto.getPersonName());
        assertEquals("BIRTH", dto.getBirthDate());
        assertSame(document, dto.getDocument());

        PersonRequestDto builder = PersonRequestDto.builder()
                .personName(personName)
                .birthDate("BIRTH")
                .document(document)
                .build();

        assertSame(personName, builder.getPersonName());

        PersonRequestDto allArgs =
                new PersonRequestDto(personName, "BIRTH", document);

        assertEquals("BIRTH", allArgs.getBirthDate());
        assertSame(document, allArgs.getDocument());
    }
}
PhoneAddressRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressRequestDTOTest {

    @Test
    void shouldCoverPhoneAddressRequestDTO() {
        PhoneAddressRequestDTO dto = new PhoneAddressRequestDTO();

        dto.setMobileNumber("MOBILE");
        dto.setPhoneNumber("PHONE");
        dto.setInternationalCode("+57");
        dto.setExtension("EXT");

        assertEquals("MOBILE", dto.getMobileNumber());
        assertEquals("PHONE", dto.getPhoneNumber());
        assertEquals("+57", dto.getInternationalCode());
        assertEquals("EXT", dto.getExtension());

        PhoneAddressRequestDTO builder = PhoneAddressRequestDTO.builder()
                .mobileNumber("MOBILE")
                .phoneNumber("PHONE")
                .internationalCode("+57")
                .extension("EXT")
                .build();

        assertEquals("MOBILE", builder.getMobileNumber());

        PhoneAddressRequestDTO allArgs =
                new PhoneAddressRequestDTO("MOBILE", "PHONE", "+57", "EXT");

        assertEquals("EXT", allArgs.getExtension());
    }
}
PostalAddressRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressRequestDTOTest {

    @Test
    void shouldCoverPostalAddressRequestDTO() {
        ProvinceRequestDTO province = new ProvinceRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();

        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();

        dto.setProvince(province);
        dto.setTownName("TOWN");
        dto.setCountry(country);

        assertSame(province, dto.getProvince());
        assertEquals("TOWN", dto.getTownName());
        assertSame(country, dto.getCountry());

        PostalAddressRequestDTO builder = PostalAddressRequestDTO.builder()
                .province(province)
                .townName("TOWN")
                .country(country)
                .build();

        assertSame(province, builder.getProvince());

        PostalAddressRequestDTO allArgs =
                new PostalAddressRequestDTO(province, "TOWN", country);

        assertEquals("TOWN", allArgs.getTownName());
        assertSame(country, allArgs.getCountry());
    }
}
ProvinceRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceRequestDTOTest {

    @Test
    void shouldCoverProvinceRequestDTO() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO();

        dto.setCode("PROVINCE");

        assertEquals("PROVINCE", dto.getCode());

        ProvinceRequestDTO builder = ProvinceRequestDTO.builder()
                .code("PROVINCE")
                .build();

        assertEquals("PROVINCE", builder.getCode());

        ProvinceRequestDTO allArgs = new ProvinceRequestDTO("PROVINCE");

        assertEquals("PROVINCE", allArgs.getCode());
    }
}
StateRequestDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateRequestDTOTest {

    @Test
    void shouldCoverStateRequestDTO() {
        StateRequestDTO dto = new StateRequestDTO();

        dto.setCode("STATE");

        assertEquals("STATE", dto.getCode());

        StateRequestDTO builder = StateRequestDTO.builder()
                .code("STATE")
                .build();

        assertEquals("STATE", builder.getCode());

        StateRequestDTO allArgs = new StateRequestDTO("STATE");

        assertEquals("STATE", allArgs.getCode());
    }
}
ContactPointCustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointCustomerSearchDTOTest {

    @Test
    void shouldCoverContactPointCustomerSearchDTO() {
        PostalAddressDTO postalAddress = new PostalAddressDTO();

        ContactPointCustomerSearchDTO dto = new ContactPointCustomerSearchDTO();

        dto.setPostalAddress(postalAddress);

        assertSame(postalAddress, dto.getPostalAddress());

        ContactPointCustomerSearchDTO builder = ContactPointCustomerSearchDTO.builder()
                .postalAddress(postalAddress)
                .build();

        assertSame(postalAddress, builder.getPostalAddress());

        ContactPointCustomerSearchDTO allArgs =
                new ContactPointCustomerSearchDTO(postalAddress);

        assertSame(postalAddress, allArgs.getPostalAddress());
    }
}
CustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.BankDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchDTOTest {

    @Test
    void shouldCoverCustomerSearchDTO() {
        PersonDTO person = new PersonDTO();
        OrganizationCustomerSearchDTO organization = new OrganizationCustomerSearchDTO();
        ContactPointCustomerSearchDTO contactPoint = new ContactPointCustomerSearchDTO();
        BankDTO bank = new BankDTO();

        CustomerSearchDTO dto = new CustomerSearchDTO();

        dto.setCustomerId("CUSTOMER");
        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setContactPoint(contactPoint);
        dto.setHighConfidentialityIndicator(true);
        dto.setBank(bank);

        assertEquals("CUSTOMER", dto.getCustomerId());
        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertSame(contactPoint, dto.getContactPoint());
        assertTrue(dto.getHighConfidentialityIndicator());
        assertSame(bank, dto.getBank());

        CustomerSearchDTO builder = CustomerSearchDTO.builder()
                .customerId("CUSTOMER")
                .person(person)
                .organization(organization)
                .contactPoint(contactPoint)
                .highConfidentialityIndicator(true)
                .bank(bank)
                .build();

        assertEquals("CUSTOMER", builder.getCustomerId());

        CustomerSearchDTO allArgs = new CustomerSearchDTO(
                "CUSTOMER", person, organization, contactPoint, true, bank
        );

        assertSame(bank, allArgs.getBank());
    }
}
CustomerSearchResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.pagination.PaginationDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchResponseDTOTest {

    @Test
    void shouldCoverCustomerSearchResponseDTO() {
        List<CustomerSearchDTO> customers = List.of(new CustomerSearchDTO());
        PaginationDTO pagination = new PaginationDTO();

        CustomerSearchResponseDTO dto = new CustomerSearchResponseDTO();

        dto.setCustomers(customers);
        dto.setPagination(pagination);

        assertSame(customers, dto.getCustomers());
        assertSame(pagination, dto.getPagination());

        CustomerSearchResponseDTO builder = CustomerSearchResponseDTO.builder()
                .customers(customers)
                .pagination(pagination)
                .build();

        assertSame(customers, builder.getCustomers());

        CustomerSearchResponseDTO allArgs =
                new CustomerSearchResponseDTO(customers, pagination);

        assertSame(pagination, allArgs.getPagination());
    }
}
OrganizationCustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationCustomerSearchDTOTest {

    @Test
    void shouldCoverOrganizationCustomerSearchDTO() {
        OrganizationNameCustomerSearchDTO organizationName =
                new OrganizationNameCustomerSearchDTO();

        DocumentDTO document = new DocumentDTO();

        OrganizationCustomerSearchDTO dto = new OrganizationCustomerSearchDTO();

        dto.setRegistrationDate("DATE");
        dto.setOrganizationName(organizationName);
        dto.setDocument(document);

        assertEquals("DATE", dto.getRegistrationDate());
        assertSame(organizationName, dto.getOrganizationName());
        assertSame(document, dto.getDocument());

        OrganizationCustomerSearchDTO builder = OrganizationCustomerSearchDTO.builder()
                .registrationDate("DATE")
                .organizationName(organizationName)
                .document(document)
                .build();

        assertEquals("DATE", builder.getRegistrationDate());

        OrganizationCustomerSearchDTO allArgs =
                new OrganizationCustomerSearchDTO("DATE", organizationName, document);

        assertSame(document, allArgs.getDocument());
    }
}
OrganizationNameCustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameCustomerSearchDTOTest {

    @Test
    void shouldCoverOrganizationNameCustomerSearchDTO() {
        OrganizationNameCustomerSearchDTO dto =
                new OrganizationNameCustomerSearchDTO();

        dto.setLegalName("LEGAL");

        assertEquals("LEGAL", dto.getLegalName());

        OrganizationNameCustomerSearchDTO builder =
                OrganizationNameCustomerSearchDTO.builder()
                        .legalName("LEGAL")
                        .build();

        assertEquals("LEGAL", builder.getLegalName());

        OrganizationNameCustomerSearchDTO allArgs =
                new OrganizationNameCustomerSearchDTO("LEGAL");

        assertEquals("LEGAL", allArgs.getLegalName());
    }
}