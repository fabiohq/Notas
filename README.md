Perfecto, estas clases son simples → aquí sí conviene tests directos, claros y completos (100% cobertura) sin reflection.
✅ ContextRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContextRequestTest {

    @Test
    void shouldCoverAll() {
        ContextRequest dto = new ContextRequest();

        dto.setKey("k");
        dto.setValue("value");
        dto.setProduct("prod");

        assertEquals("k", dto.getKey());
        assertEquals("value", dto.getValue());
        assertEquals("prod", dto.getProduct());

        ContextRequest allArgs = new ContextRequest("k2", "v2", "p2");
        assertEquals("k2", allArgs.getKey());
        assertEquals("v2", allArgs.getValue());
        assertEquals("p2", allArgs.getProduct());
    }
}
✅ ContextResponseTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContextResponseTest {

    @Test
    void shouldCoverAll() {
        ContextResponse dto = new ContextResponse();

        dto.setKey("k");
        dto.setValue("value");
        dto.setProduct("prod");

        assertEquals("k", dto.getKey());
        assertEquals("value", dto.getValue());
        assertEquals("prod", dto.getProduct());

        ContextResponse allArgs = new ContextResponse("k2", "v2", "p2");
        assertEquals("k2", allArgs.getKey());
        assertEquals("v2", allArgs.getValue());
        assertEquals("p2", allArgs.getProduct());
    }
}
✅ ApiEntryTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiEntryTest {

    @Test
    void shouldCoverAll() {
        ApiEntry dto = new ApiEntry();

        dto.setIntegrationType("REST");
        dto.setHost("localhost");
        dto.setPort("8080");
        dto.setHttps(true);
        dto.setEndpoint("/api");
        dto.setTimeOutConn(1000);
        dto.setTimeOutRead(2000);

        assertEquals("REST", dto.getIntegrationType());
        assertEquals("localhost", dto.getHost());
        assertEquals("8080", dto.getPort());
        assertTrue(dto.isHttps());
        assertEquals("/api", dto.getEndpoint());
        assertEquals(1000, dto.getTimeOutConn());
        assertEquals(2000, dto.getTimeOutRead());

        ApiEntry allArgs = new ApiEntry("SOAP", "host", "9090", false, "/ep", 1, 2);

        assertEquals("SOAP", allArgs.getIntegrationType());
        assertEquals("host", allArgs.getHost());
        assertEquals("9090", allArgs.getPort());
        assertFalse(allArgs.isHttps());
    }
}
✅ SecurityHeadersTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersTest {

    @Test
    void shouldCoverAll() {
        SecurityHeaders dto = new SecurityHeaders();

        dto.setAuthorization("Bearer token");
        dto.setxSantanderClientId("client");

        assertEquals("Bearer token", dto.getAuthorization());
        assertEquals("client", dto.getxSantanderClientId());

        SecurityHeaders allArgs = new SecurityHeaders("auth", "id");

        assertEquals("auth", allArgs.getAuthorization());
        assertEquals("id", allArgs.getxSantanderClientId());
    }
}
✅ DataListDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataListDTOTest {

    @Test
    void shouldCoverAll() {
        DataListDTO dto = new DataListDTO();

        dto.setListCode("L");
        dto.setCode("C");
        dto.setDescription("Desc");

        assertEquals("L", dto.getListCode());
        assertEquals("C", dto.getCode());
        assertEquals("Desc", dto.getDescription());

        DataListDTO built = DataListDTO.builder()
                .listCode("L2")
                .code("C2")
                .description("D2")
                .build();

        assertEquals("L2", built.getListCode());

        DataListDTO allArgs = new DataListDTO("L3", "C3", "D3");
        assertEquals("L3", allArgs.getListCode());
    }
}
✅ GeographiesParametersResponseDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GeographiesParametersResponseDTOTest {

    @Test
    void shouldCoverAll() {
        List<DataListDTO> list = List.of(new DataListDTO());

        GeographiesParametersResponseDTO dto = new GeographiesParametersResponseDTO();
        dto.setParameters(list);

        assertEquals(list, dto.getParameters());

        GeographiesParametersResponseDTO built = GeographiesParametersResponseDTO.builder()
                .parameters(list)
                .build();

        assertEquals(list, built.getParameters());

        GeographiesParametersResponseDTO allArgs =
                new GeographiesParametersResponseDTO(list);

        assertEquals(list, allArgs.getParameters());
    }
}
✅ ClientEnumTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientEnumTest {

    @Test
    void shouldCoverEnumValues() {
        assertEquals("ingresoAltaPersonaNatural", ClientEnum.PEF1.value());
        assertEquals("QCTFD", ClientEnum.MQROUTE.value());
    }
}
✅ ParametersEnumsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParametersEnumsTest {

    @Test
    void shouldCoverEnumValues() {
        assertEquals("0008", ParametersEnums.TOWNS.value());
        assertEquals("0112", ParametersEnums.COUNTRY.value());
        assertEquals("0113", ParametersEnums.DOCU_TYPE.value());
    }
}
