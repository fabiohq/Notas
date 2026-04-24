package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP17DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP17DataRequest dto = new TrxBP17DataRequest();

        dto.setProducto("01");
        dto.setSubProducto("02");
        dto.setTarifa("03");
        dto.setPlazo("30");
        dto.setPeriodoLiquidacion("M");
        dto.setValor("1000");
        dto.setMoneda("USD");
        dto.setPuntosAdicionales("5");

        assertEquals("01", dto.getProducto());
        assertEquals("02", dto.getSubProducto());
        assertEquals("03", dto.getTarifa());
        assertEquals("30", dto.getPlazo());
        assertEquals("M", dto.getPeriodoLiquidacion());
        assertEquals("1000", dto.getValor());
        assertEquals("USD", dto.getMoneda());
        assertEquals("5", dto.getPuntosAdicionales());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP17DataRequest dto = TrxBP17DataRequest.builder()
                .producto("01")
                .subProducto("02")
                .tarifa("03")
                .plazo("30")
                .periodoLiquidacion("M")
                .valor("1000")
                .moneda("USD")
                .puntosAdicionales("5")
                .build();

        assertNotNull(dto);
        assertEquals("01", dto.getProducto());
        assertEquals("USD", dto.getMoneda());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP17DataRequest dto = new TrxBP17DataRequest(
                "01",
                "02",
                "03",
                "30",
                "M",
                "1000",
                "USD",
                "5"
        );

        assertNotNull(dto);
        assertEquals("02", dto.getSubProducto());
        assertEquals("1000", dto.getValor());
    }
}