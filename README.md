Claro. Como son DTOs simples, las pruebas unitarias deben validar constructor vacío, setters/getters, builder y constructor allArgs por clase.
Ejemplo con JUnit 5:
Java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryDTOTest {

    @Test
    void shouldSetAndGetValues() {
        CountryDTO dto = new CountryDTO();

        dto.setCode("CO");
        dto.setName("Colombia");

        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getName());
    }

    @Test
    void shouldBuildCountryDTO() {
        CountryDTO dto = CountryDTO.builder()
                .code("ES")
                .name("Spain")
                .build();

        assertEquals("ES", dto.getCode());
        assertEquals("Spain", dto.getName());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        CountryDTO dto = new CountryDTO("US", "United States");

        assertEquals("US", dto.getCode());
        assertEquals("United States", dto.getName());
    }
}
Java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateDTOTest {

    @Test
    void shouldSetAndGetValues() {
        StateDTO dto = new StateDTO();

        dto.setCode("MD");
        dto.setName("Madrid");

        assertEquals("MD", dto.getCode());
        assertEquals("Madrid", dto.getName());
    }

    @Test
    void shouldBuildStateDTO() {
        StateDTO dto = StateDTO.builder()
                .code("CA")
                .name("California")
                .build();

        assertEquals("CA", dto.getCode());
        assertEquals("California", dto.getName());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        StateDTO dto = new StateDTO("NY", "New York");

        assertEquals("NY", dto.getCode());
        assertEquals("New York", dto.getName());
    }
}
Java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicAddressDTOTest {

    @Test
    void shouldSetAndGetEmailAddress() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO();

        dto.setEmailAddress("test@mail.com");

        assertEquals("test@mail.com", dto.getEmailAddress());
    }

    @Test
    void shouldBuildElectronicAddressDTO() {
        ElectronicAddressDTO dto = ElectronicAddressDTO.builder()
                .emailAddress("user@mail.com")
                .build();

        assertEquals("user@mail.com", dto.getEmailAddress());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        ElectronicAddressDTO dto = new ElectronicAddressDTO("admin@mail.com");

        assertEquals("admin@mail.com", dto.getEmailAddress());
    }
}
Java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhoneAddressDTOTest {

    @Test
    void shouldSetAndGetValues() {
        PhoneAddressDTO dto = new PhoneAddressDTO();

        dto.setMobileNumber("3001234567");
        dto.setPhoneNumber("6011234567");
        dto.setFaxNumber("123");
        dto.setInternationalCode("+57");
        dto.setExtension("99");

        assertEquals("3001234567", dto.getMobileNumber());
        assertEquals("6011234567", dto.getPhoneNumber());
        assertEquals("123", dto.getFaxNumber());
        assertEquals("+57", dto.getInternationalCode());
        assertEquals("99", dto.getExtension());
    }

    @Test
    void shouldBuildPhoneAddressDTO() {
        PhoneAddressDTO dto = PhoneAddressDTO.builder()
                .mobileNumber("300")
                .phoneNumber("601")
                .faxNumber("111")
                .internationalCode("+57")
                .extension("22")
                .build();

        assertEquals("300", dto.getMobileNumber());
        assertEquals("601", dto.getPhoneNumber());
        assertEquals("111", dto.getFaxNumber());
        assertEquals("+57", dto.getInternationalCode());
        assertEquals("22", dto.getExtension());
    }
}
Java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactPointsRequestDTOTest {

    @Test
    void shouldSetAndGetValues() {
        PostalAddressDTO postal = new PostalAddressDTO();
        PhoneAddressDTO phone = new PhoneAddressDTO();
        ElectronicAddressDTO electronic = new ElectronicAddressDTO();

        ContactPointsRequestDTO dto = new ContactPointsRequestDTO();

        dto.setPostalAddress(postal);
        dto.setPhoneAddress(phone);
        dto.setElectronicAddress(electronic);

        assertSame(postal, dto.getPostalAddress());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(electronic, dto.getElectronicAddress());
    }

    @Test
    void shouldBuildContactPointsRequestDTO() {
        PostalAddressDTO postal = PostalAddressDTO.builder().fullAddress("Address").build();
        PhoneAddressDTO phone = PhoneAddressDTO.builder().mobileNumber("300").build();
        ElectronicAddressDTO electronic = ElectronicAddressDTO.builder().emailAddress("a@b.com").build();

        ContactPointsRequestDTO dto = ContactPointsRequestDTO.builder()
                .postalAddress(postal)
                .phoneAddress(phone)
                .electronicAddress(electronic)
                .build();

        assertSame(postal, dto.getPostalAddress());
        assertSame(phone, dto.getPhoneAddress());
        assertSame(electronic, dto.getElectronicAddress());
    }
}
Para PostalAddressDTO, por ser grande, puedes probarlo igual pero en una sola prueba:
Java
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PostalAddressDTOTest {

    @Test
    void shouldSetAndGetValues() {
        StateDTO state = new StateDTO("11", "Bogotá");
        CountryDTO country = new CountryDTO("CO", "Colombia");

        PostalAddressDTO dto = new PostalAddressDTO();

        dto.setFullAddress("Calle 123");
        dto.setFormatCode("FMT");
        dto.setFormatDescription("Formato");
        dto.setIsAddressValidated(true);
        dto.setMatchId("MATCH");
        dto.setStreetTypeCode("CL");
        dto.setStreetName("Main");
        dto.setTown("Bogotá");
        dto.setTownName("Bogotá D.C.");
        dto.setState(state);
        dto.setCountry(country);
        dto.setForeignAddressLines(List.of("Line 1", "Line 2"));
        dto.setZipCode("110111");

        assertEquals("Calle 123", dto.getFullAddress());
        assertEquals("FMT", dto.getFormatCode());
        assertEquals("Formato", dto.getFormatDescription());
        assertTrue(dto.getIsAddressValidated());
        assertEquals("MATCH", dto.getMatchId());
        assertEquals("CL", dto.getStreetTypeCode());
        assertEquals("Main", dto.getStreetName());
        assertEquals("Bogotá", dto.getTown());
        assertEquals("Bogotá D.C.", dto.getTownName());
        assertSame(state, dto.getState());
        assertSame(country, dto.getCountry());
        assertEquals(List.of("Line 1", "Line 2"), dto.getForeignAddressLines());
        assertEquals("110111", dto.getZipCode());
    }

    @Test
    void shouldBuildPostalAddressDTO() {
        PostalAddressDTO dto = PostalAddressDTO.builder()
                .fullAddress("Address")
                .zipCode("12345")
                .country(new CountryDTO("CO", "Colombia"))
                .state(new StateDTO("11", "Bogotá"))
                .build();

        assertEquals("Address", dto.getFullAddress());
        assertEquals("12345", dto.getZipCode());
        assertEquals("CO", dto.getCountry().getCode());
        assertEquals("11", dto.getState().getCode());
    }
}
Con esto quedan sin acoplar, porque cada clase tiene su propio test y no depende de servicios, mocks ni lógica externa.