package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP21DataResponseTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP21DataResponse dto = new TrxBP21DataResponse();

        dto.setSaldoDesde("100");
        dto.setSaldoHasta("200");
        dto.setDiasDesde("1");
        dto.setDiasHasta("30");
        dto.setTasaEfectiva("10.5");
        dto.setTasaNominal("9.5");

        assertEquals("100", dto.getSaldoDesde());
        assertEquals("200", dto.getSaldoHasta());
        assertEquals("1", dto.getDiasDesde());
        assertEquals("30", dto.getDiasHasta());
        assertEquals("10.5", dto.getTasaEfectiva());
        assertEquals("9.5", dto.getTasaNominal());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP21DataResponse dto = TrxBP21DataResponse.builder()
                .saldoDesde("100")
                .saldoHasta("200")
                .diasDesde("1")
                .diasHasta("30")
                .tasaEfectiva("10.5")
                .tasaNominal("9.5")
                .build();

        assertNotNull(dto);
        assertEquals("100", dto.getSaldoDesde());
        assertEquals("9.5", dto.getTasaNominal());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP21DataResponse dto = new TrxBP21DataResponse(
                "100",
                "200",
                "1",
                "30",
                "10.5",
                "9.5"
        );

        assertNotNull(dto);
        assertEquals("200", dto.getSaldoHasta());
        assertEquals("10.5", dto.getTasaEfectiva());
    }
}