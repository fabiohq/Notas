package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP13DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP13DataRequest dto = new TrxBP13DataRequest();

        dto.setEntidad("0065");
        dto.setOficina("001");
        dto.setCuenta("123456");
        dto.setNumSecuencia("999");
        dto.setNumCertificado("ABC123");

        assertEquals("0065", dto.getEntidad());
        assertEquals("001", dto.getOficina());
        assertEquals("123456", dto.getCuenta());
        assertEquals("999", dto.getNumSecuencia());
        assertEquals("ABC123", dto.getNumCertificado());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP13DataRequest dto = TrxBP13DataRequest.builder()
                .entidad("0065")
                .oficina("001")
                .cuenta("123456")
                .numSecuencia("999")
                .numCertificado("ABC123")
                .build();

        assertNotNull(dto);
        assertEquals("0065", dto.getEntidad());
        assertEquals("ABC123", dto.getNumCertificado());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP13DataRequest dto = new TrxBP13DataRequest(
                "0065",
                "001",
                "123456",
                "999",
                "ABC123"
        );

        assertNotNull(dto);
        assertEquals("001", dto.getOficina());
        assertEquals("123456", dto.getCuenta());
    }
}