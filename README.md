package com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Placement;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

class ProductsMappersMissingBp13Test {

    private ProductsMappers mapper;
    private TermDepositParametersService termDepositParametersService;

    @BeforeEach
    void setUp() {
        termDepositParametersService = mock(TermDepositParametersService.class);
        mapper = new ProductsMappers(mock(ErrorService.class), termDepositParametersService);

        ReflectionTestUtils.setField(mapper, "appName", "term_deposits");
        ReflectionTestUtils.setField(mapper, "appVersion", "v1");
        ReflectionTestUtils.setField(mapper, "isDecimal", true);
        ReflectionTestUtils.setField(mapper, "inputSettlementCompare", "C");
        ReflectionTestUtils.setField(mapper, "inputSettlementDescription", "CAPITALIZA");
    }

    @Test
    void shouldCoverBp13ToDepositsPlacementResponseComplete() {
        TermDepositParametersDTO parameter = new TermDepositParametersDTO();
        parameter.setCode("01");
        parameter.setDescription("Compra de vivienda");

        TermDepositParametersResponse parametersResponse = new TermDepositParametersResponse();
        parametersResponse.setParameters(List.of(parameter));

        when(termDepositParametersService.termDepositParameters(any())).thenReturn(parametersResponse);

        DepositPlacementResponseDTO response = mapper.bp13toDepositsPlacementResponse(
                trx("A", "S", "S", "S", "0101234567800012300000456"),
                new TermDepositParametersRequest("940250", "auth", "client"));

        assertNotNull(response);
        assertNotNull(response.getContract());
        assertNotNull(response.getContract().getProduct());
        assertNotNull(response.getPlacement());

        Placement placement = response.getPlacement();

        assertEquals("04", response.getContract().getProduct().getProductCode());
        assertEquals("CDT DIGITAL", response.getContract().getProduct().getProductDescription());

        assertEquals("123456", placement.getPlacementIdentification().getIsin());
        assertEquals("A", placement.getStatusInfo().getStatusCode());
        assertEquals("ACTIVO", placement.getStatusInfo().getStatusDescription());

        assertEquals("0001", placement.getSubproduct().getSubproductId());
        assertEquals("CDT DIGITAL", placement.getSubproduct().getName());

        assertEquals("COP", placement.getCurrency().getCode());
        assertEquals(30, placement.getPeriodicity().getFrequency());
        assertEquals("D", placement.getPeriodicity().getPeriodTypeCode());
        assertEquals("DIAS", placement.getPeriodicity().getPeriodTypeDescription());

        assertTrue(placement.isRenewable());
        assertTrue(placement.isCapitalized());
        assertTrue(placement.isBlocked());

        assertNotNull(placement.getOriginIdentifier());
        assertNotNull(placement.getSettlementCondition());
        assertNotNull(placement.getAnnualPercentageYield());
        assertNotNull(placement.getRate());
        assertNotNull(placement.getDestinationFunds());
        assertNotNull(placement.getProfitabilityAtMaturity());
        assertNotNull(placement.getInitialTotalInvested());
        assertNotNull(placement.getSettlements());
        assertEquals(6, placement.getSettlements().size());
    }

    @Test
    void shouldCoverCreatePlacementDataStatuses() throws Exception {
        assertStatus("A", "ACTIVO");
        assertStatus("P", "PREAPERTURADO");
        assertStatus("V", "VENCIDO");
        assertStatus("C", "CANCELADO");
        assertStatus("Z", "ANULADO");
        assertStatus("X", "VENCIDO PENDIENTE DE PAGO");
        assertStatus("N", "RETENIDO");
        assertStatus("W", "");
    }

    @Test
    void shouldCoverRenewableCapitalizedBlockedTrueAndFalse() throws Exception {
        Placement placement = new Placement();

        TrxBP13Response trxTrue = trx("A", "S", "S", "S", "0101234567800012300000456");

        invoke("setRenewable", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxTrue, placement);
        invoke("setCapitalized", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxTrue, placement);
        invoke("setBlocked", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxTrue, placement);

        assertTrue(placement.isRenewable());
        assertTrue(placement.isCapitalized());
        assertTrue(placement.isBlocked());

        TrxBP13Response trxFalse = trx("A", "N", "N", "N", "0101234567800012300000456");

        invoke("setRenewable", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxFalse, placement);
        invoke("setCapitalized", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxFalse, placement);
        invoke("setBlocked", new Class<?>[] { TrxBP13Response.class, Placement.class }, trxFalse, placement);

        assertFalse(placement.isRenewable());
        assertFalse(placement.isCapitalized());
        assertFalse(placement.isBlocked());
    }

    @Test
    void shouldCoverAssignProductDataBlankAndFilled() throws Exception {
        DepositPlacementResponseDTO filled = new DepositPlacementResponseDTO();
        invoke("assignProductData",
                new Class<?>[] { TrxBP13Response.class, DepositPlacementResponseDTO.class },
                trx("A", "S", "S", "N", "0101234567800012300000456"),
                filled);

        assertNotNull(filled.getContract());

        DepositPlacementResponseDTO blank = new DepositPlacementResponseDTO();
        TrxBP13Response trx = trx("A", "S", "S", "N", "0101234567800012300000456");
        when(trx.getData().getProducto()).thenReturn("");

        invoke("assignProductData",
                new Class<?>[] { TrxBP13Response.class, DepositPlacementResponseDTO.class },
                trx,
                blank);

        assertNull(blank.getContract());
    }

    @Test
    void shouldCoverCreatePlacementDataBlankSubproduct() throws Exception {
        TrxBP13Response trx = trx("A", "S", "S", "N", "0101234567800012300000456");
        when(trx.getData().getSubproducto()).thenReturn("");

        Placement placement = invoke("createPlacementData",
                new Class<?>[] { TrxBP13Response.class },
                trx);

        assertNull(placement.getSubproduct());
        assertNotNull(placement.getCurrency());
        assertNotNull(placement.getPeriodicity());
    }

    private void assertStatus(String status, String expectedDescription) throws Exception {
        Placement placement = invoke("createPlacementData",
                new Class<?>[] { TrxBP13Response.class },
                trx(status, "S", "S", "N", "0101234567800012300000456"));

        assertEquals(status, placement.getStatusInfo().getStatusCode());
        assertEquals(expectedDescription, placement.getStatusInfo().getStatusDescription());
    }

    @SuppressWarnings("unchecked")
    private <T> T invoke(String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
        Method method = ProductsMappers.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return (T) method.invoke(mapper, args);
    }

    private TrxBP13Response trx(String estado, String renovacion, String capitaliza, String bloqueo, String observaciones) {
        TrxBP13Response trx = mock(TrxBP13Response.class);
        TrxBP13DataResponse data = mock(TrxBP13DataResponse.class);

        when(trx.getData()).thenReturn(data);

        when(data.getProducto()).thenReturn("04 CDT DIGITAL");
        when(data.getNumCertificado()).thenReturn("123456");
        when(data.getEstadoIPF()).thenReturn(estado);
        when(data.getSubproducto()).thenReturn("0001 CDT DIGITAL");
        when(data.getMoneda()).thenReturn("COP");
        when(data.getPlazo()).thenReturn(30);
        when(data.getFecVencimiento()).thenReturn("2026-12-01");
        when(data.getFecAlta()).thenReturn("2026-06-01");

        when(data.getRenovacionAutomatica()).thenReturn(renovacion);
        when(data.getCapInteres()).thenReturn(capitaliza);
        when(data.getIndicadorBloqueo()).thenReturn(bloqueo);

        when(data.getCanalApertura()).thenReturn("60");
        when(data.getPeriodoLiquidacion()).thenReturn("V VENCIMIENTO");
        when(data.getTipoInteres()).thenReturn("+000000001234");
        when(data.getTipoEfectivo()).thenReturn("+000000005678");

        when(data.getLina1()).thenReturn("00010" + pad("123456789", 30) + "ING");
        when(data.getLina2()).thenReturn("000000000100000000000000000000000000000120000000000000000000");
        when(data.getObservaciones()).thenReturn(observaciones);

        when(data.getSaldoInicial()).thenReturn("000000000100000");
        when(data.getInteresesAvonado()).thenReturn("+000000000010000");
        when(data.getSaldoDisponible()).thenReturn("000000000090000");

        return trx;
    }

    private String pad(String value, int length) {
        String text = value == null ? "" : value;
        if (text.length() >= length) {
            return text.substring(0, length);
        }
        return text + " ".repeat(length - text.length());
    }
}
