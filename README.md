Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationCustomerSearchDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameCustomerSearchDTO organizationName = new OrganizationNameCustomerSearchDTO();
        DocumentDTO document = new DocumentDTO();

        OrganizationCustomerSearchDTO dto = new OrganizationCustomerSearchDTO();
        dto.setOrganizationName(organizationName);
        dto.setDocument(document);
        dto.setTypeCode("TC");
        dto.setTypeDescription("TYPE");
        dto.setSubtypeCode("ST");
        dto.setSubtypeDescription("SUBTYPE");
        dto.setRegistrationDate("2025-01-01");

        assertSame(organizationName, dto.getOrganizationName());
        assertSame(document, dto.getDocument());
        assertEquals("TC", dto.getTypeCode());
        assertEquals("TYPE", dto.getTypeDescription());
        assertEquals("ST", dto.getSubtypeCode());
        assertEquals("SUBTYPE", dto.getSubtypeDescription());
        assertEquals("2025-01-01", dto.getRegistrationDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameCustomerSearchDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameCustomerSearchDTO dto = new OrganizationNameCustomerSearchDTO();

        dto.setLegalName("Santander");

        assertEquals("Santander", dto.getLegalName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonCustomerSearchResponseDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameCustomerSearchResponseDTO personName = new PersonNameCustomerSearchResponseDTO();
        ProspectSearchPlaceOfBirthDTO placeOfBirth = new ProspectSearchPlaceOfBirthDTO();
        CodeNameDTO nationality = new CodeNameDTO();
        CodeNameDTO resident = new CodeNameDTO();

        PersonCustomerSearchResponseDTO dto = new PersonCustomerSearchResponseDTO();
        dto.setPersonName(personName);
        dto.setPlaceOfBirth(placeOfBirth);
        dto.setFirstNationality(nationality);
        dto.setBirthDate("2000-01-01");
        dto.setGenderCode("M");
        dto.setGenderDescription("Male");
        dto.setCountryOfResident(resident);

        assertSame(personName, dto.getPersonName());
        assertSame(placeOfBirth, dto.getPlaceOfBirth());
        assertSame(nationality, dto.getFirstNationality());
        assertEquals("2000-01-01", dto.getBirthDate());
        assertEquals("M", dto.getGenderCode());
        assertEquals("Male", dto.getGenderDescription());
        assertSame(resident, dto.getCountryOfResident());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameCustomerSearchResponseDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonNameCustomerSearchResponseDTO dto = new PersonNameCustomerSearchResponseDTO();

        dto.setGivenName("Juan");
        dto.setLastName("Perez");
        dto.setSecondLastName("Gomez");
        dto.setFullName("Juan Perez Gomez");

        assertEquals("Juan", dto.getGivenName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Gomez", dto.getSecondLastName());
        assertEquals("Juan Perez Gomez", dto.getFullName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchDocumentDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO country = new CodeNameDTO();
        CodeNameDTO state = new CodeNameDTO();

        ProspectSearchDocumentDTO dto = new ProspectSearchDocumentDTO();
        dto.setDocumentTypeCode("CC");
        dto.setDocumentTypeDescription("Cedula");
        dto.setDocumentNumber("123456");
        dto.setIssueDate("2024-01-01");
        dto.setCountry(country);
        dto.setState(state);

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("Cedula", dto.getDocumentTypeDescription());
        assertEquals("123456", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.PersonDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PersonDTO person = new PersonDTO();
        DocumentDTO document = new DocumentDTO();
        List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());

        ProspectSearchDTO dto = new ProspectSearchDTO();
        dto.setProspectId("P001");
        dto.setPerson(person);
        dto.setDocument(document);
        dto.setContactPoints(contactPoints);

        assertEquals("P001", dto.getProspectId());
        assertSame(person, dto.getPerson());
        assertSame(document, dto.getDocument());
        assertSame(contactPoints, dto.getContactPoints());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchElectronicAddressDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ProspectSearchElectronicAddressDTO dto = new ProspectSearchElectronicAddressDTO();

        dto.setEmailAddress("test@test.com");

        assertEquals("test@test.com", dto.getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchPhoneAddressDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ProspectSearchPhoneAddressDTO dto = new ProspectSearchPhoneAddressDTO();

        dto.setPhoneNumber("6011234567");
        dto.setInternationalCode("57");
        dto.setMobileNumber("3001234567");

        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("57", dto.getInternationalCode());
        assertEquals("3001234567", dto.getMobileNumber());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchPlaceOfBirthDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO country = new CodeNameDTO();
        CodeNameDTO state = new CodeNameDTO();

        ProspectSearchPlaceOfBirthDTO dto = new ProspectSearchPlaceOfBirthDTO();
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogota");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogota", dto.getTown());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination.PaginationDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchResponseDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        List<ProspectSearchDTO> prospects = List.of(new ProspectSearchDTO());
        PaginationDTO pagination = new PaginationDTO();

        ProspectSearchResponseDTO dto = new ProspectSearchResponseDTO();
        dto.setProspects(prospects);
        dto.setPagination(pagination);

        assertSame(prospects, dto.getProspects());
        assertSame(pagination, dto.getPagination());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProspectSearchUseTypesDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ProspectSearchUseTypesDTO dto = new ProspectSearchUseTypesDTO();

        dto.setCode("HOME");
        dto.setDescription("Home Address");

        assertEquals("HOME", dto.getCode());
        assertEquals("Home Address", dto.getDescription());
    }
}