
// src/test/java/com/santander/bnc/bsn049/bncbsn049mstermdeposits/client/impl/ClientImplCoverageTest.java
package com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;

import retrofit2.Call;
import retrofit2.Response;

class ClientImplCoverageTest {

    @Test
    void banksServiceShouldReturnBodyAndNullOnException() throws Exception {
        BanksApi api = mock(BanksApi.class);
        Call<BanksDTO> call = mock(Call.class);
        BanksDTO body = new BanksDTO();

        when(api.callBanks("auth", "client")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        BanksServiceImpl service = new BanksServiceImpl(api);

        assertSame(body, service.banksResponse(new BanksParametersRequest("auth", "client")));

        when(api.callBanks(any(), any())).thenThrow(new RuntimeException("error"));

        assertNull(service.banksResponse(new BanksParametersRequest("auth", "client")));
    }

    @Test
    void productDirectoryServiceShouldReturnBodyAndNullOnException() throws Exception {
        ProductDirectoryAPI api = mock(ProductDirectoryAPI.class);
        Call<AmountRangeResponse> call = mock(Call.class);
        AmountRangeResponse body = new AmountRangeResponse();

        when(api.callAmountRange("940250", "auth", "client")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        ProductDirectoryServiceImpl service = new ProductDirectoryServiceImpl(api);

        assertSame(body, service.amountRange(new AmountRangeRequest("auth", "client", "940250")));

        when(api.callAmountRange(any(), any(), any())).thenThrow(new RuntimeException("error"));

        assertNull(service.amountRange(new AmountRangeRequest("auth", "client", "940250")));
    }

    @Test
    void termDepositParametersServiceShouldReturnBodyAndNullOnException() throws Exception {
        TermDepositParametersAPI api = mock(TermDepositParametersAPI.class);
        Call<TermDepositParametersResponse> call = mock(Call.class);
        TermDepositParametersResponse body = new TermDepositParametersResponse();

        when(api.callTermDepositParameters("940250", "auth", "client")).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(body));

        TermDepositParametersServiceImpl service = new TermDepositParametersServiceImpl(api);

        assertSame(body, service.termDepositParameters(new TermDepositParametersRequest("940250", "auth", "client")));

        when(api.callTermDepositParameters(any(), any(), any())).thenThrow(new RuntimeException("error"));

        assertNull(service.termDepositParameters(new TermDepositParametersRequest("940250", "auth", "client")));
    }
}

// src/test/java/com/santander/bnc/bsn049/bncbsn049mstermdeposits/mappers/ProductsMappersTest.java
package com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

class ProductsMappersTest {

    private ProductsMappers mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductsMappers(mock(ErrorService.class), mock(TermDepositParametersService.class));
        ReflectionTestUtils.setField(mapper, "appName", "term_deposits");
        ReflectionTestUtils.setField(mapper, "appVersion", "v1");
        ReflectionTestUtils.setField(mapper, "isDecimal", true);
        ReflectionTestUtils.setField(mapper, "inputSettlementCompare", "C");
        ReflectionTestUtils.setField(mapper, "inputSettlementDescription", "Reinversión de intereses");
    }

    @Test
    void shouldMapBp17SimulatePlacement() {
        TrxBP17Response trx = mock(TrxBP17Response.class, RETURNS_DEEP_STUBS);

        when(trx.getData().getCodigoDeProducto()).thenReturn("940250");
        when(trx.getData().getCodigoDeSubproduct()).thenReturn("001");
        when(trx.getData().getDescripcionProdu()).thenReturn("CDT DIGITAL");
        when(trx.getData().getImporteTotalInvers()).thenReturn("000000000100000");
        when(trx.getData().getCodigoDeDivisa()).thenReturn("COP");
        when(trx.getData().getPlazoEnDias()).thenReturn("00030");
        when(trx.getData().getFechaDeVencimiento()).thenReturn("2026-12-01");
        when(trx.getData().getFechaDeAlta()).thenReturn("2026-06-01");
        when(trx.getData().getPeriodoLiquidacion()).thenReturn("V");
        when(trx.getData().getDescrPeriodoLiq()).thenReturn("Vencimiento");
        when(trx.getData().getPorcentajeDeInteresNominal()).thenReturn("000000001234");
        when(trx.getData().getPorcentajeDeTasaEfectiva()).thenReturn("000000005678");
        when(trx.getData().getImporteTotalCobrar()).thenReturn("000000000110000");
        when(trx.getData().getImporteBaseInvers()).thenReturn("000000000100000");
        when(trx.getData().getPorcentajeFijoBonGmf()).thenReturn("000000000000");
        when(trx.getData().getImporteGmfBonific()).thenReturn("000000000000000");
        when(trx.getData().getPorcentajeDeRetencionFuent()).thenReturn("000000000000");
        when(trx.getData().getImporteRetencFuent()).thenReturn("000000000000000");
        when(trx.getData().getImporteBrutoIntere()).thenReturn("000000000010000");
        when(trx.getData().getImporteNetoInteres()).thenReturn("000000000009000");

        assertNotNull(mapper.bp17toSimulatePlacementResponse(trx, "C"));
        assertNotNull(mapper.bp17toSimulatePlacementResponse(trx, "V"));

        ReflectionTestUtils.setField(mapper, "isDecimal", false);
        assertNotNull(mapper.bp17toSimulatePlacementResponse(trx, "C"));
    }

    @Test
    void shouldMapBp31ListAndLinks() {
        CdtsDatsDTO active = cdt("A");
        CdtsDatsDTO pending = cdt("P");
        CdtsDatsDTO due = cdt("V");
        CdtsDatsDTO cancelled = cdt("C");
        CdtsDatsDTO unknown = cdt("W");

        assertEquals(5, mapper.bp31mapResponse(List.of(active, pending, due, cancelled, unknown)).size());
    }

    @Test
    void shouldMapTermDepositsResponse() {
        TrxBp02Response trx = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);

        when(trx.getData().getBGMP020().getCCCINVE()).thenReturn("12345");
        when(trx.getData().getBGMP020().getSECUIPF()).thenReturn("12");
        when(trx.getData().getBGMP020().getFECVCTO()).thenReturn("2026-12-01");
        when(trx.getData().getBGMP020().getFECALTA()).thenReturn("2026-06-01");
        when(trx.getData().getBGMP020().getTIPINTN()).thenReturn("000000001234");

        assertNotNull(mapper.responseTermDepositsDTOMapper(trx));
    }

    @Test
    void shouldMapDepositSummary() {
        CdtsDatsDTO active = cdt("A");
        CdtsDatsDTO pending = cdt("P");
        CdtsDatsDTO due = cdt("V");
        CdtsDatsDTO duePending = cdt("X");
        CdtsDatsDTO cancelled = cdt("C");

        assertNotNull(mapper.calculateDepositSummaryMapper(
                mock(com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response.class),
                List.of(active, pending, due, duePending, cancelled),
                "1000,00"));

        assertNotNull(mapper.calculateDepositSummaryNullMapper());
    }

    private CdtsDatsDTO cdt(String status) {
        CdtsDatsDTO dto = mock(CdtsDatsDTO.class);

        when(dto.getCodigoInversor()).thenReturn("00000000000000000001");
        when(dto.getProducto()).thenReturn("940250");
        when(dto.getDescripcionProducto()).thenReturn("CDT");
        when(dto.getSecuencia()).thenReturn("00001");
        when(dto.getSecRenov()).thenReturn("00000");
        when(dto.getEstado()).thenReturn(status);
        when(dto.getFechaApertura()).thenReturn("2026-06-01");
        when(dto.getSaldo()).thenReturn("000000000100000");
        when(dto.getDivisa()).thenReturn("COP");
        when(dto.getCertificado()).thenReturn("01234567");
        when(dto.getFechaVencimiento()).thenReturn("2026-12-01");
        when(dto.getSubproducto()).thenReturn("001");
        when(dto.getSecuenciaReposicionamiento()).thenReturn("00001");

        return dto;
    }
}
