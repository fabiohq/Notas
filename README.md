package com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.request.GetListDepositsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Placement;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

class ProductsMappersPrivateCoverageTest {

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
        ReflectionTestUtils.setField(mapper, "inputSettlementDescription", "Reinversión de intereses");
    }

    @Test
    void shouldCoverPrivateFormatMethods() throws Exception {
        assertEquals("\"\"", invoke("ajustarValor", new Class<?>[]{String.class}, new Object[]{null}));
        assertEquals("\"\"", invoke("ajustarValor", new Class<?>[]{String.class}, new Object[]{""}));
        assertEquals("ABC", invoke("ajustarValor", new Class<?>[]{String.class}, new Object[]{"ABC"}));

        assertEquals("123,45", invoke("formatearCantidad", new Class<?>[]{String.class}, new Object[]{"000000000012345"}));
        assertEquals("123", invoke("formatearCantidad", new Class<?>[]{String.class}, new Object[]{"123"}));

        assertEquals("12345", invoke("limpiarCantidad", new Class<?>[]{String.class}, new Object[]{" +12.345 "}));
        assertEquals("", invoke("limpiarCantidad", new Class<?>[]{String.class}, new Object[]{""}));
        assertNull(invoke("limpiarCantidad", new Class<?>[]{String.class}, new Object[]{null}));
    }

    @Test
    void shouldCoverSetDestinationFundsAllDescriptions() throws Exception {
        String[] codes = {"ING", "VAE", "VAP", "ACT", "VAR", "BAJ", "VDA", "RCL", "XXX"};

        for (String code : codes) {
            Placement placement = new Placement();
            TrxBP13Response trx = bp13();
            when(trx.getData().getLina1()).thenReturn("00010" + pad("123456789", 30) + code);

            invoke("setDestinationFunds",
                    new Class<?>[]{TrxBP13Response.class, Placement.class},
                    new Object[]{trx, placement});

            assertNotNull(placement.getDestinationFunds());
            assertNotNull(placement.getDestinationFunds().getAccount());
        }

        Placement placementCa = new Placement();
        TrxBP13Response trxCa = bp13();
        when(trxCa.getData().getLina1()).thenReturn("00011" + pad("123456789", 30) + "ING");

        invoke("setDestinationFunds",
                new Class<?>[]{TrxBP13Response.class, Placement.class},
                new Object[]{trxCa, placementCa});

        assertEquals("CA", placementCa.getDestinationFunds().getAccountIdType());

        Placement shortLine = new Placement();
        TrxBP13Response trxShort = bp13();
        when(trxShort.getData().getLina1()).thenReturn("123");

        invoke("setDestinationFunds",
                new Class<?>[]{TrxBP13Response.class, Placement.class},
                new Object[]{trxShort, shortLine});

        assertNull(shortLine.getDestinationFunds());
    }

    @Test
    void shouldCoverPurposeBranches() throws Exception {
        TermDepositParametersDTO parameter = new TermDepositParametersDTO();
        parameter.setCode("01");
        parameter.setDescription("Compra vivienda");

        TermDepositParametersResponse parametersResponse = new TermDepositParametersResponse();
        parametersResponse.setParameters(List.of(parameter));

        when(termDepositParametersService.termDepositParameters(any())).thenReturn(parametersResponse);

        Placement placement = new Placement();
        invoke("setPurpose",
                new Class<?>[]{TrxBP13Response.class, TermDepositParametersRequest.class, Placement.class, String.class},
                new Object[]{bp13(), new TermDepositParametersRequest("940250", "auth", "client"), placement, "01OBS"});

        assertEquals("01", placement.getPurposeCode());
        assertEquals("COMPRA VIVIENDA", placement.getPurposeDescription());

        Placement notFound = new Placement();
        invoke("setPurpose",
                new Class<?>[]{TrxBP13Response.class, TermDepositParametersRequest.class, Placement.class, String.class},
                new Object[]{bp13(), new TermDepositParametersRequest("940250", "auth", "client"), notFound, "99OBS"});

        assertEquals("", notFound.getPurposeDescription());

        Placement blank = new Placement();
        invoke("setPurpose",
                new Class<?>[]{TrxBP13Response.class, TermDepositParametersRequest.class, Placement.class, String.class},
                new Object[]{bp13(), new TermDepositParametersRequest("940250", "auth", "client"), blank, ""});

        assertEquals("", blank.getPurposeDescription());

        when(termDepositParametersService.termDepositParameters(any())).thenReturn(null);

        Placement nullResponse = new Placement();
        invoke("setPurpose",
                new Class<?>[]{TrxBP13Response.class, TermDepositParametersRequest.class, Placement.class, String.class},
                new Object[]{bp13(), new TermDepositParametersRequest("940250", "auth", "client"), nullResponse, "01OBS"});

        assertNull(nullResponse.getPurposeDescription());
    }

    @Test
    void shouldCoverPrivatePlacementSetters() throws Exception {
        TrxBP13Response trx = bp13();
        Placement placement = new Placement();

        when(trx.getData().getCanalApertura()).thenReturn("60");
        invoke("setOriginIdentifier", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertEquals("ODS", placement.getOriginIdentifier().getDescription());

        when(trx.getData().getCanalApertura()).thenReturn("99");
        invoke("setOriginIdentifier", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertEquals("OTRO", placement.getOriginIdentifier().getDescription());

        placement.setCapitalized(false);
        when(trx.getData().getPeriodoLiquidacion()).thenReturn("V VENCIMIENTO");
        invoke("setSettlementCondition", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertEquals("V", placement.getSettlementCondition().getCode());

        placement.setCapitalized(true);
        invoke("setSettlementCondition", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertEquals("C", placement.getSettlementCondition().getCode());

        when(trx.getData().getPeriodoLiquidacion()).thenReturn("");
        invoke("setSettlementCondition", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, new Placement()});

        when(trx.getData().getTipoInteres()).thenReturn("+000000001234");
        invoke("setAnnualPercentageYield", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertNotNull(placement.getAnnualPercentageYield());

        when(trx.getData().getTipoInteres()).thenReturn("");
        invoke("setAnnualPercentageYield", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});

        when(trx.getData().getTipoEfectivo()).thenReturn("+000000005678");
        invoke("setRate", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertNotNull(placement.getRate());

        when(trx.getData().getTipoEfectivo()).thenReturn("");
        invoke("setRate", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
    }

    @Test
    void shouldCoverRenewableCapitalizedBlockedBranches() throws Exception {
        TrxBP13Response trx = bp13();

        Placement placement = new Placement();

        when(trx.getData().getIndicRenov()).thenReturn("S");
        invoke("setRenewable", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertTrue(placement.isRenewable());

        when(trx.getData().getIndicRenov()).thenReturn("N");
        invoke("setRenewable", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertFalse(placement.isRenewable());

        when(trx.getData().getIndCapitalizacion()).thenReturn("S");
        invoke("setCapitalized", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertTrue(placement.isCapitalized());

        when(trx.getData().getIndCapitalizacion()).thenReturn("N");
        invoke("setCapitalized", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertFalse(placement.isCapitalized());

        when(trx.getData().getIndicBloq()).thenReturn("S");
        invoke("setBlocked", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertTrue(placement.isBlocked());

        when(trx.getData().getIndicBloq()).thenReturn("N");
        invoke("setBlocked", new Class<?>[]{TrxBP13Response.class, Placement.class}, new Object[]{trx, placement});
        assertFalse(placement.isBlocked());
    }

    @Test
    void shouldCoverBp31StatusesAndLinks() {
        List<CdtsDatsDTO> list = List.of(
                cdt("A"),
                cdt("P"),
                cdt("V"),
                cdt("C"),
                cdt("X"),
                cdt("N"),
                cdt("Z")
        );

        assertEquals(7, mapper.bp31mapResponse(list).size());

        GetListDepositsRequestDTO request = GetListDepositsRequestDTO.builder()
                .participantId("03003502")
                .placementStatus("A")
                .limit("1")
                .offset("00001-00000")
                .build();

        assertNotNull(mapper.bp31mapResponseLinks(list, request));

        TrxBP31Response response = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
        when(response.getData().getCdtsDats()).thenReturn(list);
        assertNotNull(mapper.bp31mapResponseLinks(response, request));
    }

    private Object invoke(String methodName, Class<?>[] parameterTypes, Object[] args) throws Exception {
        Method method = ProductsMappers.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(mapper, args);
    }

    private TrxBP13Response bp13() {
        TrxBP13Response trx = mock(TrxBP13Response.class, RETURNS_DEEP_STUBS);

        when(trx.getData().getCodigoProducto()).thenReturn("940250");
        when(trx.getData().getSubProducto()).thenReturn("001");
        when(trx.getData().getDescProducto()).thenReturn("CDT");
        when(trx.getData().getDescSubProducto()).thenReturn("DIGITAL");
        when(trx.getData().getNumContrato()).thenReturn("1234567890");
        when(trx.getData().getSecuencia()).thenReturn("00001");
        when(trx.getData().getSecRenov()).thenReturn("00000");
        when(trx.getData().getEstadoIPF()).thenReturn("A");
        when(trx.getData().getDescEstadoIPF()).thenReturn("ACTIVO");
        when(trx.getData().getMoneda()).thenReturn("COP");
        when(trx.getData().getPlazo()).thenReturn("30");
        when(trx.getData().getTipoPeriodo()).thenReturn("D");
        when(trx.getData().getDescPeriodo()).thenReturn("DIAS");
        when(trx.getData().getFecVencimiento()).thenReturn("2026-12-01");
        when(trx.getData().getFecAlta()).thenReturn("2026-06-01");
        when(trx.getData().getCanalApertura()).thenReturn("60");
        when(trx.getData().getPeriodoLiquidacion()).thenReturn("V VENCIMIENTO");
        when(trx.getData().getTipoInteres()).thenReturn("+000000001234");
        when(trx.getData().getTipoEfectivo()).thenReturn("+000000005678");
        when(trx.getData().getLina1()).thenReturn("00010" + pad("123456789", 30) + "ING");
        when(trx.getData().getLina2()).thenReturn(pad("", 32) + "000000000123456" + pad("", 60));
        when(trx.getData().getObservaciones()).thenReturn("01OBSERVACION");
        when(trx.getData().getIndicRenov()).thenReturn("S");
        when(trx.getData().getIndCapitalizacion()).thenReturn("S");
        when(trx.getData().getIndicBloq()).thenReturn("N");

        return trx;
    }

    private CdtsDatsDTO cdt(String estado) {
        CdtsDatsDTO dto = mock(CdtsDatsDTO.class);

        when(dto.getCodigoInversor()).thenReturn("00000000000000000001");
        when(dto.getProducto()).thenReturn("940250");
        when(dto.getDescripcionProducto()).thenReturn("CDT");
        when(dto.getSecuencia()).thenReturn("00001");
        when(dto.getSecRenov()).thenReturn("00000");
        when(dto.getEstado()).thenReturn(estado);
        when(dto.getFechaApertura()).thenReturn("2026-06-01");
        when(dto.getSaldo()).thenReturn("000000000123456");
        when(dto.getDivisa()).thenReturn("COP");
        when(dto.getCertificado()).thenReturn("12345678");
        when(dto.getFechaVencimiento()).thenReturn("2026-12-01");
        when(dto.getSubproducto()).thenReturn("001");
        when(dto.getSecuenciaReposicionamiento()).thenReturn("00001");
        dto.cccReposicionamiento = "00000000000000000001";

        return dto;
    }

    private String pad(String value, int length) {
        String text = value == null ? "" : value;
        if (text.length() >= length) {
            return text.substring(0, length);
        }
        return text + " ".repeat(length - text.length());
    }
}
