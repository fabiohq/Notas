Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CenterRequestDTO center = new CenterRequestDTO();

        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("123");
        dto.setCenter(center);

        assertEquals("123", dto.getBankId());
        assertSame(center, dto.getCenter());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CenterRequestDTO dto = new CenterRequestDTO();

        dto.setCenterId("CENTER01");

        assertEquals("CENTER01", dto.getCenterId());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        StateRequestDTO state = new StateRequestDTO();

        DocumentRequestDTO dto = new DocumentRequestDTO();
        dto.setDocumentTypeCode("CC");
        dto.setDocumentNumber("123456789");
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("123456789", dto.getDocumentNumber());
        assertSame(state, dto.getState());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressRequestDtOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ElectronicAddressRequestDtO dto = new ElectronicAddressRequestDtO();

        dto.setEmailAddress("test@test.com");

        assertEquals("test@test.com", dto.getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameRequestDTO dto = new OrganizationNameRequestDTO();

        dto.setLegalName("Santander");

        assertEquals("Santander", dto.getLegalName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRequestDtoTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameRequestDTO name = new OrganizationNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setRegistrationDate("2025-01-01");
        dto.setOrganizationName(name);
        dto.setDocument(document);

        assertEquals("2025-01-01", dto.getRegistrationDate());
        assertSame(name, dto.getOrganizationName());
        assertSame(document, dto.getDocument());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameRequestDTO dto = new PersonNameRequestDTO();

        dto.setGivenName("Juan");
        dto.setLastName("Perez");
        dto.setSecondLastName("Gomez");

        assertEquals("Juan", dto.getGivenName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Gomez", dto.getSecondLastName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDtoTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameRequestDTO name = new PersonNameRequestDTO();
        DocumentRequestDTO document = new DocumentRequestDTO();

        PersonRequestDto dto = new PersonRequestDto();
        dto.setPersonName(name);
        dto.setBirthDate("2000-01-01");
        dto.setDocument(document);

        assertSame(name, dto.getPersonName());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertSame(document, dto.getDocument());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ProvinceRequestDTO province = new ProvinceRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO();

        PostalAddressRequestDTO dto = new PostalAddressRequestDTO();
        dto.setProvince(province);
        dto.setTownName("Bogotá");
        dto.setCountry(country);

        assertSame(province, dto.getProvince());
        assertEquals("Bogotá", dto.getTownName());
        assertSame(country, dto.getCountry());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonRequestDto person = new PersonRequestDto();
        OrganizationRequestDto organization = new OrganizationRequestDto();
        PhoneAddressRequestDTO phone = new PhoneAddressRequestDTO();
        ElectronicAddressRequestDtO electronic = new ElectronicAddressRequestDtO();
        ContactPointDTO contactPoint = new ContactPointDTO();

        ProspectRequestDTO dto = new ProspectRequestDTO();
        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(electronic);
        dto.setContactPointCustomer(contactPoint);

        assertSame(person, dto.getPerson());
        assertSame(organization, dto.getOrganization());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(electronic, dto.getElectronicAddress());
        assertSame(contactPoint, dto.getContactPointCustomer());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateRequestDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        StateRequestDTO dto = new StateRequestDTO();

        dto.setCode("05");

        assertEquals("05", dto.getCode());
    }
}