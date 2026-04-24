package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class TrxBP49DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        TrxBP49DataRequest dto = new TrxBP49DataRequest();

        dto.setBuscarPor("CERT");
        dto.setEnt("0065");
        dto.setOfic("001");
        dto.setCuenta("123456");
        dto.setSecuencia("999");
        dto.setNumeroCertificado("ABC123");
        dto.setDocumentoCajero("DOC01");

        assertEquals("CERT", dto.getBuscarPor());
        assertEquals("0065", dto.getEnt());
        assertEquals("001", dto.getOfic());
        assertEquals("123456", dto.getCuenta());
        assertEquals("999", dto.getSecuencia());
        assertEquals("ABC123", dto.getNumeroCertificado());
        assertEquals("DOC01", dto.getDocumentoCajero());
    }

    @Test
    void shouldCoverBuilder() {
        TrxBP49DataRequest dto = TrxBP49DataRequest.builder()
                .buscarPor("CERT")
                .ent("0065")
                .ofic("001")
                .cuenta("123456")
                .secuencia("999")
                .numeroCertificado("ABC123")
                .documentoCajero("DOC01")
                .build();

        assertNotNull(dto);
        assertEquals("CERT", dto.getBuscarPor());
        assertEquals("ABC123", dto.getNumeroCertificado());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        TrxBP49DataRequest dto = new TrxBP49DataRequest(
                "CERT",
                "0065",
                "001",
                "123456",
                "999",
                "ABC123",
                "DOC01"
        );

        assertNotNull(dto);
        assertEquals("001", dto.getOfic());
        assertEquals("123456", dto.getCuenta());
        assertEquals("DOC01", dto.getDocumentoCajero());
    }
}