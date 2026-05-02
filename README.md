Perfecto, seguimos igual: tests unitarios simples, clase por clase, sin acoplar, solo validando getters/setters y builder.
Aquí tienes los tests para esas clases 👇
✅ CustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.BankDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchDTOTest {

    @Test
    void testGettersAndSetters() {
        CustomerSearchDTO dto = new CustomerSearchDTO();

        dto.setCustomerId("123");
        dto.setPerson(new PersonDTO());
        dto.setOrganization(new OrganizationCustomerSearchDTO());
        dto.setContactPoint(new ContactPointDTO());
        dto.setHighConfidentialityIndicator(true);
        dto.setBank(new BankDTO());

        assertEquals("123", dto.getCustomerId());
        assertNotNull(dto.getPerson());
        assertNotNull(dto.getOrganization());
        assertNotNull(dto.getContactPoint());
        assertTrue(dto.getHighConfidentialityIndicator());
        assertNotNull(dto.getBank());
    }

    @Test
    void testBuilder() {
        CustomerSearchDTO dto = CustomerSearchDTO.builder()
                .customerId("123")
                .highConfidentialityIndicator(true)
                .build();

        assertEquals("123", dto.getCustomerId());
        assertTrue(dto.getHighConfidentialityIndicator());
    }
}
✅ CustomerSearchResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination.PaginationDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchResponseDTOTest {

    @Test
    void testGettersAndSetters() {
        CustomerSearchResponseDTO dto = new CustomerSearchResponseDTO();

        dto.setCustomers(List.of(new CustomerSearchDTO()));
        dto.setPagination(new PaginationDTO());

        assertNotNull(dto.getCustomers());
        assertEquals(1, dto.getCustomers().size());
        assertNotNull(dto.getPagination());
    }

    @Test
    void testBuilder() {
        CustomerSearchResponseDTO dto = CustomerSearchResponseDTO.builder()
                .customers(List.of(new CustomerSearchDTO()))
                .build();

        assertNotNull(dto.getCustomers());
    }
}
✅ OrganizationCustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationCustomerSearchDTOTest {

    @Test
    void testGettersAndSetters() {
        OrganizationCustomerSearchDTO dto = new OrganizationCustomerSearchDTO();

        dto.setRegistrationDate("2024-01-01");
        dto.setOrganizationName(new OrganizationNameCustomerSearchDTO());
        dto.setDocument(new DocumentDTO());

        assertEquals("2024-01-01", dto.getRegistrationDate());
        assertNotNull(dto.getOrganizationName());
        assertNotNull(dto.getDocument());
    }

    @Test
    void testBuilder() {
        OrganizationCustomerSearchDTO dto = OrganizationCustomerSearchDTO.builder()
                .registrationDate("2024-01-01")
                .build();

        assertEquals("2024-01-01", dto.getRegistrationDate());
    }
}
✅ OrganizationNameCustomerSearchDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameCustomerSearchDTOTest {

    @Test
    void testGettersAndSetters() {
        OrganizationNameCustomerSearchDTO dto = new OrganizationNameCustomerSearchDTO();

        dto.setLegalName("Santander");

        assertEquals("Santander", dto.getLegalName());
    }

    @Test
    void testBuilder() {
        OrganizationNameCustomerSearchDTO dto = OrganizationNameCustomerSearchDTO.builder()
                .legalName("Santander")
                .build();

        assertEquals("Santander", dto.getLegalName());
    }
}
💡 Notas importantes (rápidas y útiles)
✔️ No hay mocks → porque son POJOs
✔️ No hay lógica → solo validamos estado
✔️ Builder + getters/setters = cobertura suficiente
✔️ Tests rápidos y mantenibles
Si quieres, el siguiente paso lógico sería:
👉 tests del CustomerMapper (lo pesado de verdad)
Ahí sí entramos en:
mocks (ParamService, RegexUtils, etc.)
casos edge
validaciones reales
Solo dime 👍