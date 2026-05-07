Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataListDTOTest {

    @Test
    void shouldSetAndGetAllFields() {

        DataListDTO dto = new DataListDTO();

        dto.setListCode("COUNTRY");
        dto.setCode("CO");
        dto.setDescription("Colombia");

        assertEquals("COUNTRY", dto.getListCode());
        assertEquals("CO", dto.getCode());
        assertEquals("Colombia", dto.getDescription());
    }

    @Test
    void shouldCreateUsingBuilder() {

        DataListDTO dto = DataListDTO.builder()
                .listCode("CITY")
                .code("11001")
                .description("Bogota")
                .build();

        assertEquals("CITY", dto.getListCode());
        assertEquals("11001", dto.getCode());
        assertEquals("Bogota", dto.getDescription());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        DataListDTO dto = new DataListDTO(
                "STATE",
                "11",
                "Cundinamarca"
        );

        assertEquals("STATE", dto.getListCode());
        assertEquals("11", dto.getCode());
        assertEquals("Cundinamarca", dto.getDescription());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographiesParametersResponseDTOTest {

    @Test
    void shouldSetAndGetParameters() {

        DataListDTO parameter = new DataListDTO();
        parameter.setListCode("COUNTRY");
        parameter.setCode("CO");
        parameter.setDescription("Colombia");

        GeographiesParametersResponseDTO dto =
                new GeographiesParametersResponseDTO();

        dto.setParameters(List.of(parameter));

        assertNotNull(dto.getParameters());
        assertEquals(1, dto.getParameters().size());

        DataListDTO response = dto.getParameters().get(0);

        assertEquals("COUNTRY", response.getListCode());
        assertEquals("CO", response.getCode());
        assertEquals("Colombia", response.getDescription());
    }

    @Test
    void shouldCreateUsingBuilder() {

        DataListDTO parameter = DataListDTO.builder()
                .listCode("CITY")
                .code("11001")
                .description("Bogota")
                .build();

        GeographiesParametersResponseDTO dto =
                GeographiesParametersResponseDTO.builder()
                        .parameters(List.of(parameter))
                        .build();

        assertEquals(1, dto.getParameters().size());
        assertEquals("11001", dto.getParameters().get(0).getCode());
    }

    @Test
    void shouldCreateUsingAllArgsConstructor() {

        DataListDTO parameter = new DataListDTO(
                "STATE",
                "11",
                "Cundinamarca"
        );

        GeographiesParametersResponseDTO dto =
                new GeographiesParametersResponseDTO(
                        List.of(parameter)
                );

        assertNotNull(dto.getParameters());
        assertEquals(1, dto.getParameters().size());

        assertEquals(
                "Cundinamarca",
                dto.getParameters().get(0).getDescription()
        );
    }
}