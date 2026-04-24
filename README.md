package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

class ErrorResponseTrxDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        ErrorResponseTrxDTO dto = new ErrorResponseTrxDTO();

        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        dto.setErrores(errores);

        assertEquals(errores, dto.getErrores());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        ErrorResponseTrxDTO dto = new ErrorResponseTrxDTO(errores);

        assertNotNull(dto);
        assertEquals(errores, dto.getErrores());
    }

    @Test
    void shouldCoverBuilder() {
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        ErrorResponseTrxDTO dto = ErrorResponseTrxDTO.builder()
                .errores(errores)
                .build();

        assertNotNull(dto);
        assertEquals(errores, dto.getErrores());
    }
}