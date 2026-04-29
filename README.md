Aquí tienes test por clase, separados, para DTOs simples.
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditDTOTest {

    @Test
    void shouldGetAndSetFields() {
        AuditDTO dto = new AuditDTO();

        dto.setCreationDate("2026-04-29");
        dto.setLastUpdateDate("2026-04-30");

        assertEquals("2026-04-29", dto.getCreationDate());
        assertEquals("2026-04-30", dto.getLastUpdateDate());
    }

    @Test
    void shouldBuildWithBuilder() {
        AuditDTO dto = AuditDTO.builder()
                .creationDate("2026-04-29")
                .lastUpdateDate("2026-04-30")
                .build();

        assertEquals("2026-04-29", dto.getCreationDate());
        assertEquals("2026-04-30", dto.getLastUpdateDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressDTOTest {

    @Test
    void shouldGetAndSetFields() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO();

        dto.setEmailAddress("test@test.com");

        assertEquals("test@test.com", dto.getEmailAddress());
    }

    @Test
    void shouldBuildWithBuilder() {
        ElectronicAddressDTO dto = ElectronicAddressDTO.builder()
                .emailAddress("test@test.com")
                .build();

        assertEquals("test@test.com", dto.getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataOriginDTOTest {

    @Test
    void shouldGetAndSetFields() {
        DataOriginDTO dto = new DataOriginDTO();

        dto.setSourceCode("ODS");
        dto.setSourceDescription("Origen");
        dto.setCreationDate("2026-04-29");

        assertEquals("ODS", dto.getSourceCode());
        assertEquals("Origen", dto.getSourceDescription());
        assertEquals("2026-04-29", dto.getCreationDate());
    }

    @Test
    void shouldBuildWithBuilder() {
        DataOriginDTO dto = DataOriginDTO.builder()
                .sourceCode("ODS")
                .sourceDescription("Origen")
                .creationDate("2026-04-29")
                .build();

        assertEquals("ODS", dto.getSourceCode());
        assertEquals("Origen", dto.getSourceDescription());
        assertEquals("2026-04-29", dto.getCreationDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EconomicActivityDTOTest {

    @Test
    void shouldGetAndSetFields() {
        EconomicActivityDTO dto = new EconomicActivityDTO();

        dto.setCategoryCode("001");
        dto.setCategoryDescription("Categoria");
        dto.setSubCategoryCode("002");
        dto.setSubCategoryDescription("Subcategoria");

        assertEquals("001", dto.getCategoryCode());
        assertEquals("Categoria", dto.getCategoryDescription());
        assertEquals("002", dto.getSubCategoryCode());
        assertEquals("Subcategoria", dto.getSubCategoryDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressDTOTest {

    @Test
    void shouldGetAndSetFields() {
        PhoneAddressDTO dto = new PhoneAddressDTO();

        dto.setMobileNumber("3001234567");
        dto.setPhoneNumber("6011234567");
        dto.setFaxNumber("123");
        dto.setInternationalCode("57");
        dto.setExtension("999");

        assertEquals("3001234567", dto.getMobileNumber());
        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("123", dto.getFaxNumber());
        assertEquals("57", dto.getInternationalCode());
        assertEquals("999", dto.getExtension());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonNameDTOTest {

    @Test
    void shouldGetAndSetFields() {
        PersonNameDTO dto = new PersonNameDTO();

        dto.setNamePrefixCode("MR");
        dto.setNamePrefixDescription("Mister");
        dto.setGivenName("Juan");
        dto.setMiddleName("Carlos");
        dto.setLastName("Perez");
        dto.setSecondLastName("Lopez");
        dto.setNameSuffixCode("JR");
        dto.setNameSuffixDescription("Junior");
        dto.setFullName("Juan Carlos Perez Lopez");
        dto.setBirthName("Juan");
        dto.setAliases(List.of("Alias"));

        assertEquals("MR", dto.getNamePrefixCode());
        assertEquals("Mister", dto.getNamePrefixDescription());
        assertEquals("Juan", dto.getGivenName());
        assertEquals("Carlos", dto.getMiddleName());
        assertEquals("Perez", dto.getLastName());
        assertEquals("Lopez", dto.getSecondLastName());
        assertEquals("JR", dto.getNameSuffixCode());
        assertEquals("Junior", dto.getNameSuffixDescription());
        assertEquals("Juan Carlos Perez Lopez", dto.getFullName());
        assertEquals("Juan", dto.getBirthName());
        assertEquals(List.of("Alias"), dto.getAliases());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TownDTOTest {

    @Test
    void shouldGetAndSetFields() {
        TownDTO dto = new TownDTO();

        dto.setCode("05001");
        dto.setName("Medellin");

        assertEquals("05001", dto.getCode());
        assertEquals("Medellin", dto.getName());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidityPeriodDTOTest {

    @Test
    void shouldGetAndSetFields() {
        ValidityPeriodDTO dto = new ValidityPeriodDTO();

        dto.setStartDate("2026-01-01");
        dto.setEndDate("2026-12-31");

        assertEquals("2026-01-01", dto.getStartDate());
        assertEquals("2026-12-31", dto.getEndDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicOfficeInformationDTOTest {

    @Test
    void shouldGetAndSetFields() {
        ValidityPeriodDTO validity = new ValidityPeriodDTO();
        PublicOfficeInformationDTO dto = new PublicOfficeInformationDTO();

        dto.setPositionCode("001");
        dto.setPositionDescription("Cargo");
        dto.setValidityPeriod(validity);

        assertEquals("001", dto.getPositionCode());
        assertEquals("Cargo", dto.getPositionDescription());
        assertSame(validity, dto.getValidityPeriod());
    }
}
Para PlaceOfRegistrationDTO, tu clase no tiene getters ni setters explícitos ni @Getter/@Setter, entonces no se puede probar normal. Agrégale esto:
Java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
}