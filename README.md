package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataListDTOTest {

    @Test
    void shouldCoverGettersSettersConstructorsAndBuilder() {
        DataListDTO dto = new DataListDTO();
        dto.setListCode("0112");
        dto.setCode("CO");
        dto.setDescription("Colombia");

        assertEquals("0112", dto.getListCode());
        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getDescription());

        DataListDTO dto2 = new DataListDTO("0009", "05", "Antioquia");
        assertEquals("0009", dto2.getListCode());
        assertEquals("05", dto2.getCode());
        assertEquals("Antioquia", dto2.getDescription());

        DataListDTO dto3 = DataListDTO.builder()
                .listCode("0008")
                .code("05001")
                .description("Medellin")
                .build();

        assertEquals("0008", dto3.getListCode());
        assertEquals("05001", dto3.getCode());
        assertEquals("Medellin", dto3.getDescription());
    }
}


>>>>>>>


package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityHeadersTest {

    @Test
    void shouldCoverGettersSettersAndConstructors() {
        SecurityHeaders headers = new SecurityHeaders();
        headers.setAuthorization("Bearer token");
        headers.setxSantanderClientId("client-id");

        assertEquals("Bearer token", headers.getAuthorization());
        assertEquals("client-id", headers.getxSantanderClientId());

        SecurityHeaders headers2 = new SecurityHeaders("auth", "client");

        assertEquals("auth", headers2.getAuthorization());
        assertEquals("client", headers2.getxSantanderClientId());
    }
}



>>>>>>>



package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateDTOTest {

    @Test
    void shouldCoverGettersSettersAndConstructors() {
        StateDTO dto = new StateDTO();
        dto.setCode("05");
        dto.setName("Antioquia");

        assertEquals("05", dto.getCode());
        assertEquals("Antioquia", dto.getName());

        StateDTO dto2 = new StateDTO("11", "Bogota");

        assertEquals("11", dto2.getCode());
        assertEquals("Bogota", dto2.getName());
    }
}



>>>>>>


package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TownsDTOTest {

    @Test
    void shouldCoverGettersSettersConstructorsAndBuilder() {
        List<StateDTO> states = List.of(new StateDTO("05", "Antioquia"));

        TownsDTO dto = new TownsDTO();
        dto.setListCode("0008");
        dto.setCode("05001");
        dto.setDescription("Medellin");
        dto.setStates(states);

        assertEquals("0008", dto.getListCode());
        assertEquals("05001", dto.getCode());
        assertEquals("Medellin", dto.getDescription());
        assertEquals(states, dto.getStates());

        TownsDTO dto2 = new TownsDTO("0008", "11001", "Bogota", states);

        assertEquals("0008", dto2.getListCode());
        assertEquals("11001", dto2.getCode());
        assertEquals("Bogota", dto2.getDescription());
        assertEquals(states, dto2.getStates());

        TownsDTO dto3 = TownsDTO.builder()
                .listCode("0008")
                .code("76001")
                .description("Cali")
                .states(states)
                .build();

        assertEquals("0008", dto3.getListCode());
        assertEquals("76001", dto3.getCode());
        assertEquals("Cali", dto3.getDescription());
        assertEquals(states, dto3.getStates());
    }
}



>>>>>