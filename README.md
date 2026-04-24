package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class Bp01DataRequestTest {

    @Test
    void shouldCoverSettersAndGetters() {
        Bp01DataRequest dto = new Bp01DataRequest();

        dto.setNroCliente("cliente");
        dto.setProducto("producto");
        dto.setNroCtaExtAbono("cuenta");
        dto.setTipoCtaExtAbono("tipo");
        dto.setDiviCtaExtAbono("COP");
        dto.setBancoCtaExtAbono("0065");
        dto.setEjecutivoComercial("0001");

        assertEquals("cliente", dto.getNroCliente());
        assertEquals("producto", dto.getProducto());
        assertEquals("cuenta", dto.getNroCtaExtAbono());
        assertEquals("tipo", dto.getTipoCtaExtAbono());
        assertEquals("COP", dto.getDiviCtaExtAbono());
        assertEquals("0065", dto.getBancoCtaExtAbono());
        assertEquals("0001", dto.getEjecutivoComercial());
    }

    @Test
    void shouldCoverBuilder() {
        Bp01DataRequest dto = Bp01DataRequest.builder()
                .nroCliente("cliente")
                .producto("04")
                .bancoCtaExtAbono("0065")
                .build();

        assertNotNull(dto);
        assertEquals("cliente", dto.getNroCliente());
        assertEquals("04", dto.getProducto());
        assertEquals("0065", dto.getBancoCtaExtAbono());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        Bp01DataRequest dto = new Bp01DataRequest(
                "cliente", "producto", "cuenta", "tipo",
                "COP", "0065", "0001"
        );

        assertNotNull(dto);
        assertEquals("cliente", dto.getNroCliente());
        assertEquals("0001", dto.getEjecutivoComercial());
    }
}