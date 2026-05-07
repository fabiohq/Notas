Aquí van las 3 que faltan, separadas.
DocumentDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO state = new CodeNameDTO("11", "Bogotá");

        DocumentDTO dto = new DocumentDTO();

        dto.setDocumentTypeCode("CC");
        dto.setDocumentTypeDescription("Cédula");
        dto.setDocumentNumber("123456789");
        dto.setIssueDate("2026-01-01");
        dto.setExpirationDate("2030-01-01");
        dto.setIssuerEntity("Registraduría");
        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("Bogotá");

        assertEquals("CC", dto.getDocumentTypeCode());
        assertEquals("Cédula", dto.getDocumentTypeDescription());
        assertEquals("123456789", dto.getDocumentNumber());
        assertEquals("2026-01-01", dto.getIssueDate());
        assertEquals("2030-01-01", dto.getExpirationDate());
        assertEquals("Registraduría", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Bogotá", dto.getTown());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO state = new CodeNameDTO("05", "Antioquia");

        DocumentDTO dto = new DocumentDTO(
                "CE",
                "Cédula extranjería",
                "987654321",
                "2025-01-01",
                "2029-01-01",
                "Migración",
                country,
                state,
                "Medellín"
        );

        assertEquals("CE", dto.getDocumentTypeCode());
        assertEquals("Cédula extranjería", dto.getDocumentTypeDescription());
        assertEquals("987654321", dto.getDocumentNumber());
        assertEquals("2025-01-01", dto.getIssueDate());
        assertEquals("2029-01-01", dto.getExpirationDate());
        assertEquals("Migración", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Medellín", dto.getTown());
    }

    @Test
    void shouldBuildWithBuilder() {
        CodeNameDTO country = CodeNameDTO.builder().code("ES").name("España").build();
        CodeNameDTO state = CodeNameDTO.builder().code("MD").name("Madrid").build();

        DocumentDTO dto = DocumentDTO.builder()
                .documentTypeCode("PP")
                .documentTypeDescription("Pasaporte")
                .documentNumber("ABC123")
                .issueDate("2024-01-01")
                .expirationDate("2034-01-01")
                .issuerEntity("Entidad")
                .country(country)
                .state(state)
                .town("Madrid")
                .build();

        assertEquals("PP", dto.getDocumentTypeCode());
        assertEquals("Pasaporte", dto.getDocumentTypeDescription());
        assertEquals("ABC123", dto.getDocumentNumber());
        assertEquals("2024-01-01", dto.getIssueDate());
        assertEquals("2034-01-01", dto.getExpirationDate());
        assertEquals("Entidad", dto.getIssuerEntity());
        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("Madrid", dto.getTown());
    }
}
ParametersTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO countryNationality = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO countryExp = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO countryBirth = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO countryDir = new CodeNameDTO("CO", "Colombia");
        CodeNameDTO cityStandard = new CodeNameDTO("11001", "Bogotá");
        CodeNameDTO cityDepartment = new CodeNameDTO("11", "Bogotá D.C.");
        CodeNameDTO cityExp = new CodeNameDTO("11001", "Bogotá");
        CodeNameDTO cityBirth = new CodeNameDTO("05001", "Medellín");
        CodeNameDTO town = new CodeNameDTO("05001", "Medellín");
        CodeNameDTO townDocument = new CodeNameDTO("11001", "Bogotá");

        Parameters dto = new Parameters();

        dto.setCountryNationality(countryNationality);
        dto.setCountryExp(countryExp);
        dto.setCountryBirth(countryBirth);
        dto.setCountryDir(countryDir);
        dto.setCityStandard(cityStandard);
        dto.setCityDepartment(cityDepartment);
        dto.setCityExp(cityExp);
        dto.setCityBirth(cityBirth);
        dto.setTown(town);
        dto.setTownDocument(townDocument);
        dto.setDocumentTypeDescription("Cédula");
        dto.setStreetTypeDescription("Calle");

        assertSame(countryNationality, dto.getCountryNationality());
        assertSame(countryExp, dto.getCountryExp());
        assertSame(countryBirth, dto.getCountryBirth());
        assertSame(countryDir, dto.getCountryDir());
        assertSame(cityStandard, dto.getCityStandard());
        assertSame(cityDepartment, dto.getCityDepartment());
        assertSame(cityExp, dto.getCityExp());
        assertSame(cityBirth, dto.getCityBirth());
        assertSame(town, dto.getTown());
        assertSame(townDocument, dto.getTownDocument());
        assertEquals("Cédula", dto.getDocumentTypeDescription());
        assertEquals("Calle", dto.getStreetTypeDescription());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CodeNameDTO value = new CodeNameDTO("CO", "Colombia");

        Parameters dto = new Parameters(
                value,
                value,
                value,
                value,
                value,
                value,
                value,
                value,
                value,
                value,
                "Pasaporte",
                "Carrera"
        );

        assertSame(value, dto.getCountryNationality());
        assertSame(value, dto.getCountryExp());
        assertSame(value, dto.getCountryBirth());
        assertSame(value, dto.getCountryDir());
        assertSame(value, dto.getCityStandard());
        assertSame(value, dto.getCityDepartment());
        assertSame(value, dto.getCityExp());
        assertSame(value, dto.getCityBirth());
        assertSame(value, dto.getTown());
        assertSame(value, dto.getTownDocument());
        assertEquals("Pasaporte", dto.getDocumentTypeDescription());
        assertEquals("Carrera", dto.getStreetTypeDescription());
    }

    @Test
    void shouldBuildWithBuilder() {
        CodeNameDTO value = CodeNameDTO.builder()
                .code("11")
                .name("Bogotá")
                .build();

        Parameters dto = Parameters.builder()
                .countryNationality(value)
                .countryExp(value)
                .countryBirth(value)
                .countryDir(value)
                .cityStandard(value)
                .cityDepartment(value)
                .cityExp(value)
                .cityBirth(value)
                .town(value)
                .townDocument(value)
                .documentTypeDescription("Cédula ciudadanía")
                .streetTypeDescription("Avenida")
                .build();

        assertSame(value, dto.getCountryNationality());
        assertSame(value, dto.getCountryExp());
        assertSame(value, dto.getCountryBirth());
        assertSame(value, dto.getCountryDir());
        assertSame(value, dto.getCityStandard());
        assertSame(value, dto.getCityDepartment());
        assertSame(value, dto.getCityExp());
        assertSame(value, dto.getCityBirth());
        assertSame(value, dto.getTown());
        assertSame(value, dto.getTownDocument());
        assertEquals("Cédula ciudadanía", dto.getDocumentTypeDescription());
        assertEquals("Avenida", dto.getStreetTypeDescription());
    }
}
PostalAddressDTOTest
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldSetAndGetAllFields() {
        CodeNameDTO town = new CodeNameDTO("11001", "Bogotá");
        CodeNameDTO state = new CodeNameDTO("11", "Bogotá D.C.");
        CodeNameDTO province = new CodeNameDTO("PROV", "Provincia");
        CodeNameDTO region = new CodeNameDTO("REG", "Región");
        CodeNameDTO county = new CodeNameDTO("COUNTY", "Condado");
        CodeNameDTO country = new CodeNameDTO("CO", "Colombia");
        List<String> foreignAddressLines = List.of("line1", "line2");

        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setFullAddress("Calle 1 # 2-3");
        dto.setFormatCode("FMT");
        dto.setFormatDescription("Formato");
        dto.setIsAddressValidated(Boolean.TRUE);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("CL");
        dto.setStreetTypeDescription("Calle");
        dto.setStreetName("1");
        dto.setSecondaryStreetName("2");
        dto.setStreetBuildingIdentification("3");
        dto.setMailDeliverySubLocation("Sub");
        dto.setBuildingName("Edificio");
        dto.setFloor("5");
        dto.setDetailCode("DET");
        dto.setUnitType("APT");
        dto.setUnitNumber("501");
        dto.setPremise("Premise");
        dto.setAlternativePremise("Alt");
        dto.setDepartment("Dept");
        dto.setSubDepartment("SubDept");
        dto.setPostCodeIdentification("110111");
        dto.setTown(town);
        dto.setState(state);
        dto.setDistrictName("Distrito");
        dto.setSecondaryDistrictName("Distrito 2");
        dto.setMailingInstructions("Instrucciones");
        dto.setProvince(province);
        dto.setRegionIdentification(region);
        dto.setCountyIdentification(county);
        dto.setCountry(country);
        dto.setMilitary("MIL");
        dto.setPostOfficeBox("POBOX");
        dto.setPostBoxTypeCode("PBT");
        dto.setPostBoxTypeDescription("Post box");
        dto.setForeignAddressLines(foreignAddressLines);
        dto.setZipCode("110111");
        dto.setZip4Code("0001");
        dto.setRuralTypeCode("RUR");
        dto.setRuralTypeDescription("Rural");
        dto.setRuralNumber("10");

        assertEquals("Calle 1 # 2-3", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("Formato", dto.getFormatDescription());
        assertTrue(dto.getIsAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("Calle", dto.getStreetTypeDescription());
        assertEquals("1", dto.getStreetName());
        assertEquals("2", dto.getSecondaryStreetName());
        assertEquals("3", dto.getStreetBuildingIdentification());
        assertEquals("Sub", dto.getMailDeliverySubLocation());
        assertEquals("Edificio", dto.getBuildingName());
        assertEquals("5", dto.getFloor());
        assertEquals("DET", dto.getDetailCode());
        assertEquals("APT", dto.getUnitType());
        assertEquals("501", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alt", dto.getAlternativePremise());
        assertEquals("Dept", dto.getDepartment());
        assertEquals("SubDept", dto.getSubDepartment());
        assertEquals("110111", dto.getPostCodeIdentification());
        assertSame(town, dto.getTown());
        assertSame(state, dto.getState());
        assertEquals("Distrito", dto.getDistrictName());
        assertEquals("Distrito 2", dto.getSecondaryDistrictName());
        assertEquals("Instrucciones", dto.getMailingInstructions());
        assertSame(province, dto.getProvince());
        assertSame(region, dto.getRegionIdentification());
        assertSame(county, dto.getCountyIdentification());
        assertSame(country, dto.getCountry());
        assertEquals("MIL", dto.getMilitary());
        assertEquals("POBOX", dto.getPostOfficeBox());
        assertEquals("PBT", dto.getPostBoxTypeCode());
        assertEquals("Post box", dto.getPostBoxTypeDescription());
        assertSame(foreignAddressLines, dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
        assertEquals("0001", dto.getZip4Code());
        assertEquals("RUR", dto.getRuralTypeCode());
        assertEquals("Rural", dto.getRuralTypeDescription());
        assertEquals("10", dto.getRuralNumber());
    }

    @Test
    void shouldBuildWithBuilder() {
        CodeNameDTO codeName = CodeNameDTO.builder()
                .code("CO")
                .name("Colombia")
                .build();

        PostalAddressDTO dto = PostalAddressDTO.builder()
                .fullAddress("Address")
                .formatCode("FMT")
                .formatDescription("Format description")
                .isAddressValidated(Boolean.FALSE)
                .matchId("MATCH")
                .streetTypeCode("CL")
                .streetTypeDescription("Calle")
                .streetName("Street")
                .secondaryStreetName("Second")
                .streetBuildingIdentification("BuildingId")
                .mailDeliverySubLocation("SubLocation")
                .buildingName("Building")
                .floor("2")
                .detailCode("Detail")
                .unitType("Unit")
                .unitNumber("202")
                .premise("Premise")
                .alternativePremise("Alternative")
                .department("Department")
                .subDepartment("SubDepartment")
                .postCodeIdentification("PostCode")
                .town(codeName)
                .state(codeName)
                .districtName("District")
                .secondaryDistrictName("SecondaryDistrict")
                .mailingInstructions("Mailing")
                .province(codeName)
                .regionIdentification(codeName)
                .countyIdentification(codeName)
                .country(codeName)
                .military("Military")
                .postOfficeBox("Box")
                .postBoxTypeCode("BoxType")
                .postBoxTypeDescription("Box description")
                .foreignAddressLines(List.of("line"))
                .zipCode("Zip")
                .zip4Code("Zip4")
                .ruralTypeCode("RuralType")
                .ruralTypeDescription("Rural description")
                .ruralNumber("RuralNumber")
                .build();

        assertEquals("Address", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("Format description", dto.getFormatDescription());
        assertFalse(dto.getIsAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("Calle", dto.getStreetTypeDescription());
        assertEquals("Street", dto.getStreetName());
        assertEquals("Second", dto.getSecondaryStreetName());
        assertEquals("BuildingId", dto.getStreetBuildingIdentification());
        assertEquals("SubLocation", dto.getMailDeliverySubLocation());
        assertEquals("Building", dto.getBuildingName());
        assertEquals("2", dto.getFloor());
        assertEquals("Detail", dto.getDetailCode());
        assertEquals("Unit", dto.getUnitType());
        assertEquals("202", dto.getUnitNumber());
        assertEquals("Premise", dto.getPremise());
        assertEquals("Alternative", dto.getAlternativePremise());
        assertEquals("Department", dto.getDepartment());
        assertEquals("SubDepartment", dto.getSubDepartment());
        assertEquals("PostCode", dto.getPostCodeIdentification());
        assertSame(codeName, dto.getTown());
        assertSame(codeName, dto.getState());
        assertEquals("District", dto.getDistrictName());
        assertEquals("SecondaryDistrict", dto.getSecondaryDistrictName());
        assertEquals("Mailing", dto.getMailingInstructions());
        assertSame(codeName, dto.getProvince());
        assertSame(codeName, dto.getRegionIdentification());
        assertSame(codeName, dto.getCountyIdentification());
        assertSame(codeName, dto.getCountry());
        assertEquals("Military", dto.getMilitary());
        assertEquals("Box", dto.getPostOfficeBox());
        assertEquals("BoxType", dto.getPostBoxTypeCode());
        assertEquals("Box description", dto.getPostBoxTypeDescription());
        assertEquals(List.of("line"), dto.getForeignAddressLines());
        assertEquals("Zip", dto.getZipCode());
        assertEquals("Zip4", dto.getZip4Code());
        assertEquals("RuralType", dto.getRuralTypeCode());
        assertEquals("Rural description", dto.getRuralTypeDescription());
        assertEquals("RuralNumber", dto.getRuralNumber());
    }
}