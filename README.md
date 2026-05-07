Java
class ElectronicAddressRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ElectronicAddressRequestDTO dto = new ElectronicAddressRequestDTO();
        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        ElectronicAddressRequestDTO dto = new ElectronicAddressRequestDTO("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());
    }
}
Java
class PlaceOfRegistrationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        PlaceOfRegistrationRequestDTO dto = new PlaceOfRegistrationRequestDTO();
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        dto.setCountry(country);
        dto.setState(state);
        dto.setTown("BOGOTA");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("BOGOTA", dto.getTown());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        CountryRequestDTO country = new CountryRequestDTO("CO");
        StateRequestDTO state = new StateRequestDTO("11");

        PlaceOfRegistrationRequestDTO dto =
                new PlaceOfRegistrationRequestDTO(country, state, "BOGOTA");

        assertSame(country, dto.getCountry());
        assertSame(state, dto.getState());
        assertEquals("BOGOTA", dto.getTown());
    }
}
Java
class PreferredLanguageRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        PreferredLanguageRequestDTO dto = new PreferredLanguageRequestDTO();
        dto.setCode("ES");

        assertEquals("ES", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        PreferredLanguageRequestDTO dto = new PreferredLanguageRequestDTO("ES");

        assertEquals("ES", dto.getCode());
    }
}
Java
class ProvinceRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO();
        dto.setCode("11");

        assertEquals("11", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        ProvinceRequestDTO dto = new ProvinceRequestDTO("11");

        assertEquals("11", dto.getCode());
    }
}
Java
class RegionIdentificationRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        RegionIdentificationRequestDTO dto = new RegionIdentificationRequestDTO();
        dto.setCode("REG");

        assertEquals("REG", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        RegionIdentificationRequestDTO dto = new RegionIdentificationRequestDTO("REG");

        assertEquals("REG", dto.getCode());
    }
}
Java
class SecondNationalityRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        SecondNationalityRequestDTO dto = new SecondNationalityRequestDTO();
        dto.setCode("CO");

        assertEquals("CO", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        SecondNationalityRequestDTO dto = new SecondNationalityRequestDTO("CO");

        assertEquals("CO", dto.getCode());
    }
}
Java
class StateRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        StateRequestDTO dto = new StateRequestDTO();
        dto.setCode("11");

        assertEquals("11", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        StateRequestDTO dto = new StateRequestDTO("11");

        assertEquals("11", dto.getCode());
    }
}
Java
class UseTypeRequestDTOTest {
    @Test
    void shouldSetAndGetAllFields() {
        UseTypeRequestDTO dto = new UseTypeRequestDTO();
        dto.setCode("PRI");

        assertEquals("PRI", dto.getCode());
    }

    @Test
    void shouldBuildWithAllArgsConstructor() {
        UseTypeRequestDTO dto = new UseTypeRequestDTO("PRI");

        assertEquals("PRI", dto.getCode());
    }
}
PostalAddressRequestDTO es larga; te la dejo aparte para que no se mezcle.