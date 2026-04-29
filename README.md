package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ClientUtilsTest {

    @Test
    void shouldBuildTrxRequestWithCustomerIdOnly() {
        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(null, "12345678");

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals("12345678", result.getData().getpENUMPE());
    }

    @Test
    void shouldBuildTrxRequestWithDocumentData() {
        CustomerRequestDTO request = Mockito.mock(CustomerRequestDTO.class, RETURNS_DEEP_STUBS);

        when(request.getPerson().getDocument().getDocumentTypeCode()).thenReturn("CC");
        when(request.getPerson().getDocument().getDocumentNumber()).thenReturn("123");

        TrxPersonRequest result = ClientUtils.buildTrxRequestByCustomerId(request, "87654321");

        assertEquals("87654321", result.getData().getpENUMPE());
        assertEquals("CC", result.getData().getTipoDocumento());
        assertEquals("123", result.getData().getNumDocumento());
    }

    @Test
    void shouldBuildHeader() {
        var header = ClientUtils.buildHeader("ROUTE_TEST");

        assertNotNull(header);
        assertEquals("ROUTE_TEST", header.getRutaServicio());
        assertEquals("Intro", header.getFuncion());
        assertEquals("60", header.getCanal());
        assertEquals(44204, header.getSecuencia());
        assertNotNull(header.getSesion());
        assertEquals("@NETE781", header.getSesion().getUsuario());
        assertEquals("0065", header.getSesion().getEntidad());
    }
}