package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TrxBP49DataResponseTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP49DataResponse dto = new TrxBP49DataResponse();

        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());

        dto.setMovimientos(movimientos);

        assertEquals(movimientos, dto.getMovimientos());
    }

    @Test
    void shouldCoverBuilder() {
        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());

        TrxBP49DataResponse dto = TrxBP49DataResponse.builder()
                .movimientos(movimientos)
                .build();

        assertNotNull(dto);
        assertEquals(movimientos, dto.getMovimientos());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());

        TrxBP49DataResponse dto = new TrxBP49DataResponse(movimientos);

        assertNotNull(dto);
        assertEquals(1, dto.getMovimientos().size());
    }
}