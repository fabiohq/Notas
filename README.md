Sí. Hazlos archivo por archivo, así:
1. ClientUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ClientUtilsTest {

    @Test
    void shouldBuildTrxRequestWithCustomerIdOnly() {
        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(null, "12345678");

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals("12345678", result.getData().getpENUMPE());
    }

    @Test
    void shouldBuildTrxRequestWithDocumentData() {
        CustomerRequestDTO request = Mockito.mock(CustomerRequestDTO.class, RETURNS_DEEP_STUBS);

        when(request.getPerson().getDocument().getDocumentTypeCode()).thenReturn("CC");
        when(request.getPerson().getDocument().getDocumentNumber()).thenReturn("123");

        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(request, "87654321");

        assertEquals("87654321", result.getData().getpENUMPE());
        assertEquals("CC", result.getData().getTipoDocumento());
        assertEquals("123", result.getData().getNumDocumento());
    }

    @Test
    void shouldBuildHeader() {
        var header = ClientUtils.buildHeader("ROUTE_TEST");

        assertNotNull(header);
        assertEquals("ROUTE_TEST", header.getRutaServicio());
        assertEquals("Intro", header.getFuncion());
        assertEquals("60", header.getCanal());
        assertEquals(44204, header.getSecuencia());
        assertNotNull(header.getSesion());
        assertEquals("@NETE781", header.getSesion().getUsuario());
        assertEquals("0065", header.getSesion().getEntidad());
    }
}
2. CompareStringUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompareStringUtilsTest {

    private final CompareStringUtils utils = new CompareStringUtils();

    @Test
    void shouldReturnTrueWhenCitiesAreSimilar() {
        assertTrue(utils.ciudadMatch("Medellin", "Medellín"));
    }

    @Test
    void shouldReturnFalseWhenCitiesAreDifferent() {
        assertFalse(utils.ciudadMatch("Bogota", "Cartagena"));
    }

    @Test
    void shouldReturnTrueWhenBothCitiesAreEmpty() {
        assertTrue(utils.ciudadMatch("", ""));
    }

    @Test
    void shouldReturnTrueIgnoringCase() {
        assertTrue(utils.ciudadMatch("CALI", "cali"));
    }
}
3. StringUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void shouldReturnBlankWhenFieldIsNull() {
        assertEquals("", StringUtils.blankField(null));
    }

    @Test
    void shouldReturnSameValueWhenFieldIsNotNull() {
        assertEquals("abc", StringUtils.blankField("abc"));
    }

    @Test
    void shouldReplaceDoubleAndTripleSpaces() {
        assertEquals("A B C", StringUtils.replaceSpaces("A   B  C"));
    }

    @Test
    void shouldRightPadWhenStringIsShorter() {
        assertEquals("ABC00", StringUtils.rightPad("ABC", 5, '0'));
    }

    @Test
    void shouldNotRightPadWhenStringIsLongEnough() {
        assertEquals("ABCDEFG", StringUtils.rightPad("ABCDEFG", 5, '0'));
    }
}
4. TimeUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeUtilsTest {

    @Test
    void shouldFormatDate() {
        assertEquals("2026-04-28", TimeUtils.formatDate("2026-04-28"));
    }

    @Test
    void shouldReturnBlankWhenDateIsNull() {
        assertEquals("", TimeUtils.formatDate(null));
    }

    @Test
    void shouldReturnBlankWhenDateIsBlank() {
        assertEquals("", TimeUtils.formatDate(" "));
    }

    @Test
    void shouldReturnCurrentDateWithPattern() {
        String result = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");

        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void shouldReturnMarkTime() {
        String result = TimeUtils.markTime();

        assertNotNull(result);
        assertFalse(result.isBlank());
    }
}
5. GUtilsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GUtilsTest {

    static class Dummy {
        private String name;
        private String empty;
        private Integer age;

        public String getName() {
            return name;
        }

        public String getEmpty() {
            return empty;
        }

        public Integer getAge() {
            return age;
        }
    }

    @Test
    void shouldReturnNullOrBlankProperties() {
        Dummy dummy = new Dummy();
        dummy.name = "Fabio";
        dummy.empty = "";
        dummy.age = null;

        String[] result = GUtils.getNullOrBlankPropertyNames(dummy);

        assertNotNull(result);
        assertTrue(java.util.Arrays.asList(result).contains("empty"));
        assertTrue(java.util.Arrays.asList(result).contains("age"));
        assertFalse(java.util.Arrays.asList(result).contains("name"));
    }

    @Test
    void shouldValidateConstants() {
        assertEquals("--> Start ", GUtils.SLOG);
        assertEquals("<-- End ", GUtils.ELOG);
    }
}
6. RegexTypesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegexTypesTest {

    @Test
    void shouldLoadAllEnumValues() {
        assertNotNull(RegexTypes.valueOf("ONLY_NUMBERS"));
        assertTrue(RegexTypes.values().length > 0);
    }
}
Para RegexUtils y CustomerMapperUtils te los paso aparte porque son más grandes y necesitan ReflectionTestUtils para inyectar los @Value.