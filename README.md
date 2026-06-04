
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SimulatePlacementResponseDtoTest {

    @Test
    void shouldCoverSimulatePlacementResponseDTO() {
        ProductResponseDTO product = new ProductResponseDTO();
        AmountResponseDTO amount = new AmountResponseDTO();
        PeriodicityResponseDTO periodicity = new PeriodicityResponseDTO();
        PecentageYieldResponseDTO percentageYield = new PecentageYieldResponseDTO();
        ProfitabilityAtMaturityDTO profitability = new ProfitabilityAtMaturityDTO();
        InitialTotalInvestedDTO initial = new InitialTotalInvestedDTO();
        SettlementResponseDTO settlement = new SettlementResponseDTO();

        SimulatePlacementResponseDTO dto = new SimulatePlacementResponseDTO();

        dto.setProduct(product);
        dto.setAmount(amount);
        dto.setPeriodicity(periodicity);
        dto.setMaturityDate("2026-12-31");
        dto.setOpeningValueDate("2026-06-04");
        dto.setSettlementConditionCode("C");
        dto.setSettlementConditionDescription("Condition");
        dto.setCapitalizable(true);
        dto.setRenewal(true);
        dto.setPercentageYield(percentageYield);
        dto.setProfitabilityAtMaturity(profitability);
        dto.setInitialTotalInvested(initial);
        dto.setSettlements(List.of(settlement));

        assertSame(product, dto.getProduct());
        assertSame(amount, dto.getAmount());
        assertSame(periodicity, dto.getPeriodicity());
        assertEquals("2026-12-31", dto.getMaturityDate());
        assertEquals("2026-06-04", dto.getOpeningValueDate());
        assertEquals("C", dto.getSettlementConditionCode());
        assertEquals("Condition", dto.getSettlementConditionDescription());
        assertTrue(dto.isCapitalizable());
        assertTrue(dto.isRenewal());
        assertSame(percentageYield, dto.getPercentageYield());
        assertSame(profitability, dto.getProfitabilityAtMaturity());
        assertSame(initial, dto.getInitialTotalInvested());
        assertEquals(1, dto.getSettlements().size());
        assertSame(settlement, dto.getSettlements().get(0));
    }

    @Test
    void shouldCoverSettlementResponseDTO() {
        AmountResponseSettlementDTO amount = new AmountResponseSettlementDTO();

        SettlementResponseDTO dto = new SettlementResponseDTO();
        dto.setConceptCode("BGMF");
        dto.setConceptDescription("GMF");
        dto.setTypeCode("C");
        dto.setTypeDescription("Credit");
        dto.setRate("1.5");
        dto.setAmount(amount);

        assertEquals("BGMF", dto.getConceptCode());
        assertEquals("GMF", dto.getConceptDescription());
        assertEquals("C", dto.getTypeCode());
        assertEquals("Credit", dto.getTypeDescription());
        assertEquals("1.5", dto.getRate());
        assertSame(amount, dto.getAmount());
    }

    @Test
    void shouldCoverProductResponseDTO() {
        SubproductResponseDTO subproduct = new SubproductResponseDTO();

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductCode("12");
        dto.setProductDescription("CDT");
        dto.setSubproduct(subproduct);

        assertEquals("12", dto.getProductCode());
        assertEquals("CDT", dto.getProductDescription());
        assertSame(subproduct, dto.getSubproduct());
    }

    @Test
    void shouldCoverAmountResponseDTO() {
        AmountResponseDTO dto = new AmountResponseDTO();
        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverAmountResponseSettlementDTO() {
        AmountResponseSettlementDTO dto = new AmountResponseSettlementDTO();
        dto.setAmount("50");
        dto.setCurrency("COP");

        assertEquals("50", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverSubproductResponseDTO() {
        SubproductResponseDTO dto = new SubproductResponseDTO();
        dto.setSubproductId("001");

        assertEquals("001", dto.getSubproductId());
    }

    @Test
    void shouldCoverPecentageYieldResponseDTO() {
        PecentageYieldResponseDTO dto = new PecentageYieldResponseDTO();
        dto.setNominalInterestRate("10");
        dto.setPercentageYield("11");

        assertEquals("10", dto.getNominalInterestRate());
        assertEquals("11", dto.getPercentageYield());
    }

    @Test
    void shouldCoverProfitabilityAtMaturityDTO() {
        ProfitabilityAtMaturityDTO dto = new ProfitabilityAtMaturityDTO();
        dto.setAmount("1100");
        dto.setCurrency("COP");

        assertEquals("1100", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }

    @Test
    void shouldCoverPeriodicityResponseDTO() {
        PeriodicityResponseDTO dto = new PeriodicityResponseDTO();
        dto.setFrequency("30");
        dto.setPeriodTypeCode("D");

        assertEquals("30", dto.getFrequency());
        assertEquals("D", dto.getPeriodTypeCode());
    }

    @Test
    void shouldCoverInitialTotalInvestedDTO() {
        InitialTotalInvestedDTO dto = new InitialTotalInvestedDTO();
        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}
