Empieza con estas, sin acoplar:
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.PostalAddressDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactPointDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO useType = new CodeNameDTO("PRI", "Principal");
        PostalAddressDTO postal = new PostalAddressDTO();
        PhoneAddressDTO phone = new PhoneAddressDTO();
        ElectronicAddressDTO email = new ElectronicAddressDTO();
        AuditDTO audit = new AuditDTO();

        ContactPointDTO dto = new ContactPointDTO();
        dto.setContactPointId("CP1");
        dto.setUseTypes(List.of(useType));
        dto.setPostalAddress(postal);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(email);
        dto.setAudit(audit);

        assertEquals("CP1", dto.getContactPointId());
        assertEquals(List.of(useType), dto.getUseTypes());
        assertSame(postal, dto.getPostalAddress());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(email, dto.getElectronicAddress());
        assertSame(audit, dto.getAudit());
    }

    @Test
    void shouldBuildWithBuilder() {
        ContactPointDTO dto = ContactPointDTO.builder()
                .contactPointId("CP2")
                .useTypes(List.of(new CodeNameDTO("SEC", "Secundario")))
                .postalAddress(new PostalAddressDTO())
                .phoneAddress(new PhoneAddressDTO())
                .electronicAddress(new ElectronicAddressDTO())
                .audit(new AuditDTO())
                .build();

        assertEquals("CP2", dto.getContactPointId());
        assertNotNull(dto.getUseTypes());
        assertNotNull(dto.getPostalAddress());
        assertNotNull(dto.getPhoneAddress());
        assertNotNull(dto.getElectronicAddress());
        assertNotNull(dto.getAudit());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataOriginDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        DataOriginDTO dto = new DataOriginDTO();

        dto.setSourceCode("ODS");
        dto.setSourceDescription("Origen");
        dto.setCreationDate("2026-01-01");

        assertEquals("ODS", dto.getSourceCode());
        assertEquals("Origen", dto.getSourceDescription());
        assertEquals("2026-01-01", dto.getCreationDate());
    }

    @Test
    void shouldBuildWithBuilder() {
        DataOriginDTO dto = DataOriginDTO.builder()
                .sourceCode("OTRO")
                .sourceDescription("Otro origen")
                .creationDate("2026-02-01")
                .build();

        assertEquals("OTRO", dto.getSourceCode());
        assertEquals("Otro origen", dto.getSourceDescription());
        assertEquals("2026-02-01", dto.getCreationDate());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EconomicActivityDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        EconomicActivityDTO dto = new EconomicActivityDTO();

        dto.setCategoryCode("CAT");
        dto.setCategoryDescription("Category");
        dto.setSubCategoryCode("SUB");
        dto.setSubCategoryDescription("Subcategory");

        assertEquals("CAT", dto.getCategoryCode());
        assertEquals("Category", dto.getCategoryDescription());
        assertEquals("SUB", dto.getSubCategoryCode());
        assertEquals("Subcategory", dto.getSubCategoryDescription());
    }

    @Test
    void shouldBuildWithBuilder() {
        EconomicActivityDTO dto = EconomicActivityDTO.builder()
                .categoryCode("01")
                .categoryDescription("Actividad")
                .subCategoryCode("011")
                .subCategoryDescription("Sub actividad")
                .build();

        assertEquals("01", dto.getCategoryCode());
        assertEquals("Actividad", dto.getCategoryDescription());
        assertEquals("011", dto.getSubCategoryCode());
        assertEquals("Sub actividad", dto.getSubCategoryDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO();

        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());
    }

    @Test
    void shouldBuildWithBuilder() {
        ElectronicAddressDTO dto = ElectronicAddressDTO.builder()
                .emailAddress("builder@mail.com")
                .build();

        assertEquals("builder@mail.com", dto.getEmailAddress());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentInformationDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        EconomicActivityDTO economicActivity = new EconomicActivityDTO();

        EmploymentInformationDTO dto = new EmploymentInformationDTO();
        dto.setStatusCode("ACT");
        dto.setStatusDescription("Activo");
        dto.setEconomicActivity(economicActivity);
        dto.setOccupationCode("001");
        dto.setOccupationDescription("Empleado");
        dto.setSubActivityCode("SUB");
        dto.setSubActivityDescription("Sub actividad");
        dto.setSubActivityComments("Comentarios");

        assertEquals("ACT", dto.getStatusCode());
        assertEquals("Activo", dto.getStatusDescription());
        assertSame(economicActivity, dto.getEconomicActivity());
        assertEquals("001", dto.getOccupationCode());
        assertEquals("Empleado", dto.getOccupationDescription());
        assertEquals("SUB", dto.getSubActivityCode());
        assertEquals("Sub actividad", dto.getSubActivityDescription());
        assertEquals("Comentarios", dto.getSubActivityComments());
    }

    @Test
    void shouldBuildWithBuilder() {
        EmploymentInformationDTO dto = EmploymentInformationDTO.builder()
                .statusCode("INA")
                .statusDescription("Inactivo")
                .economicActivity(new EconomicActivityDTO())
                .occupationCode("002")
                .occupationDescription("Independiente")
                .subActivityCode("S2")
                .subActivityDescription("Desc")
                .subActivityComments("Obs")
                .build();

        assertEquals("INA", dto.getStatusCode());
        assertEquals("Inactivo", dto.getStatusDescription());
        assertNotNull(dto.getEconomicActivity());
        assertEquals("002", dto.getOccupationCode());
        assertEquals("Independiente", dto.getOccupationDescription());
        assertEquals("S2", dto.getSubActivityCode());
        assertEquals("Desc", dto.getSubActivityDescription());
        assertEquals("Obs", dto.getSubActivityComments());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        PlaceOfRegistrationDTO place = new PlaceOfRegistrationDTO();
        OrganizationNameDTO name = new OrganizationNameDTO();
        DocumentDTO document = new DocumentDTO();
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        EconomicActivityDTO activity = new EconomicActivityDTO();
        CodeNameDTO language = new CodeNameDTO("ES", "Español");

        OrganizationDTO dto = new OrganizationDTO();
        dto.setRegistrationDate("2026-01-01");
        dto.setEntityDisolutionDate("2030-01-01");
        dto.setResidentialStatusCode("RES");
        dto.setResidentialStatusDescription("Residente");
        dto.setForeignTaxIndicator("NO");
        dto.setPlaceOfRegistration(place);
        dto.setOrganizationName(name);
        dto.setTypeCode("TYPE");
        dto.setTypeDescription("Tipo");
        dto.setSubtypeCode("SUB");
        dto.setSubtypeDescription("Subtipo");
        dto.setDocuments(List.of(document));
        dto.setCountryOfOperation(country);
        dto.setAccountingSectorCode("ACC");
        dto.setAccountingSectorDescription("Sector");
        dto.setEconomicActivity(activity);
        dto.setPreferredLanguage(language);

        assertEquals("2026-01-01", dto.getRegistrationDate());
        assertEquals("2030-01-01", dto.getEntityDisolutionDate());
        assertEquals("RES", dto.getResidentialStatusCode());
        assertEquals("Residente", dto.getResidentialStatusDescription());
        assertEquals("NO", dto.getForeignTaxIndicator());
        assertSame(place, dto.getPlaceOfRegistration());
        assertSame(name, dto.getOrganizationName());
        assertEquals("TYPE", dto.getTypeCode());
        assertEquals("Tipo", dto.getTypeDescription());
        assertEquals("SUB", dto.getSubtypeCode());
        assertEquals("Subtipo", dto.getSubtypeDescription());
        assertEquals(List.of(document), dto.getDocuments());
        assertSame(country, dto.getCountryOfOperation());
        assertEquals("ACC", dto.getAccountingSectorCode());
        assertEquals("Sector", dto.getAccountingSectorDescription());
        assertSame(activity, dto.getEconomicActivity());
        assertSame(language, dto.getPreferredLanguage());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationNameDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        OrganizationNameDTO dto = new OrganizationNameDTO();

        dto.setLegalName("Empresa SAS");
        dto.setTradingNames(List.of("Marca 1", "Marca 2"));

        assertEquals("Empresa SAS", dto.getLegalName());
        assertEquals(List.of("Marca 1", "Marca 2"), dto.getTradingNames());
    }

    @Test
    void shouldBuildWithBuilder() {
        OrganizationNameDTO dto = OrganizationNameDTO.builder()
                .legalName("Builder SAS")
                .tradingNames(List.of("Builder"))
                .build();

        assertEquals("Builder SAS", dto.getLegalName());
        assertEquals(List.of("Builder"), dto.getTradingNames());
    }
}
Te continúo con PersonDTO, PersonNameDTO, PhoneAddressDTO, PlaceOfBirthDTO, PlaceOfRegistrationDTO, ProspectDetailResponseDTO, PublicOfficeInformationDTO y ValidityPeriodDTO en el siguiente bloque.