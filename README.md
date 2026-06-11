package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        AmountResponseDTO dto = new AmountResponseDTO();

        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountResponseSettlementDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        AmountResponseSettlementDTO dto = new AmountResponseSettlementDTO();

        dto.setAmount("100");
        dto.setCurrency("COP");

        assertEquals("100", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialTotalInvestedDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        InitialTotalInvestedDTO dto = new InitialTotalInvestedDTO();

        dto.setAmount("5000");
        dto.setCurrency("COP");

        assertEquals("5000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PecentageYieldResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        PecentageYieldResponseDTO dto = new PecentageYieldResponseDTO();

        dto.setNominalInterestRate("10.5");
        dto.setPercentageYield("12.5");

        assertEquals("10.5", dto.getNominalInterestRate());
        assertEquals("12.5", dto.getPercentageYield());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicityResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        PeriodicityResponseDTO dto = new PeriodicityResponseDTO();

        dto.setFrequency("30");
        dto.setPeriodTypeCode("D");

        assertEquals("30", dto.getFrequency());
        assertEquals("D", dto.getPeriodTypeCode());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubproductResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        SubproductResponseDTO dto = new SubproductResponseDTO();

        dto.setSubproductId("001");
        dto.setName("CDT Digital");

        assertEquals("001", dto.getSubproductId());
        assertEquals("CDT Digital", dto.getName());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        SubproductResponseDTO subproduct = new SubproductResponseDTO();
        subproduct.setSubproductId("001");
        subproduct.setName("CDT Digital");

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setProductCode("60");
        dto.setProductDescription("CDT");
        dto.setSubproduct(subproduct);

        assertEquals("60", dto.getProductCode());
        assertEquals("CDT", dto.getProductDescription());
        assertEquals(subproduct, dto.getSubproduct());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfitabilityAtMaturityDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        ProfitabilityAtMaturityDTO dto = new ProfitabilityAtMaturityDTO();

        dto.setAmount("1200");
        dto.setCurrency("COP");

        assertEquals("1200", dto.getAmount());
        assertEquals("COP", dto.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettlementResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        AmountResponseSettlementDTO amount = new AmountResponseSettlementDTO();
        amount.setAmount("100");
        amount.setCurrency("COP");

        SettlementResponseDTO dto = new SettlementResponseDTO();

        dto.setConceptCode("BGMF");
        dto.setConceptDescription("GMF");
        dto.setTypeCode("C");
        dto.setTypeDescription("Credit");
        dto.setRate("1.0");
        dto.setAmount(amount);

        assertEquals("BGMF", dto.getConceptCode());
        assertEquals("GMF", dto.getConceptDescription());
        assertEquals("C", dto.getTypeCode());
        assertEquals("Credit", dto.getTypeDescription());
        assertEquals("1.0", dto.getRate());
        assertEquals(amount, dto.getAmount());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulatePlacementResponseDTOTest {

    @Test
    void shouldCoverConstructorGetterAndSetter() {
        ProductResponseDTO product = new ProductResponseDTO();
        AmountResponseDTO amount = new AmountResponseDTO();
        PeriodicityResponseDTO periodicity = new PeriodicityResponseDTO();
        PecentageYieldResponseDTO percentageYield = new PecentageYieldResponseDTO();
        ProfitabilityAtMaturityDTO profitabilityAtMaturity = new ProfitabilityAtMaturityDTO();
        InitialTotalInvestedDTO initialTotalInvested = new InitialTotalInvestedDTO();
        SettlementResponseDTO settlement = new SettlementResponseDTO();

        SimulatePlacementResponseDTO dto = new SimulatePlacementResponseDTO();

        dto.setProduct(product);
        dto.setAmount(amount);
        dto.setPeriodicity(periodicity);
        dto.setMaturityDate("2025-12-31");
        dto.setOpeningValueDate("2024-01-01");
        dto.setSettlementConditionCode("C");
        dto.setSettlementConditionDescription("Capitalizable");
        dto.setCapitalizable(true);
        dto.setRenewal(true);
        dto.setPercentageYield(percentageYield);
        dto.setProfitabilityAtMaturity(profitabilityAtMaturity);
        dto.setInitialTotalInvested(initialTotalInvested);
        dto.setSettlements(List.of(settlement));

        assertEquals(product, dto.getProduct());
        assertEquals(amount, dto.getAmount());
        assertEquals(periodicity, dto.getPeriodicity());
        assertEquals("2025-12-31", dto.getMaturityDate());
        assertEquals("2024-01-01", dto.getOpeningValueDate());
        assertEquals("C", dto.getSettlementConditionCode());
        assertEquals("Capitalizable", dto.getSettlementConditionDescription());
        assertTrue(dto.isCapitalizable());
        assertTrue(dto.isRenewal());
        assertEquals(percentageYield, dto.getPercentageYield());
        assertEquals(profitabilityAtMaturity, dto.getProfitabilityAtMaturity());
        assertEquals(initialTotalInvested, dto.getInitialTotalInvested());
        assertEquals(List.of(settlement), dto.getSettlements());
    }
}


