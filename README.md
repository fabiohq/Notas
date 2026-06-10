package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void shouldCover() {
        Account dto = new Account();
        dto.setNationalIdentification("123");
        dto.setStatusDescription("ACTIVE");

        assertEquals("123", dto.getNationalIdentification());
        assertEquals("ACTIVE", dto.getStatusDescription());

        Account all = new Account("123", "ACTIVE");
        assertEquals("123", all.getNationalIdentification());

        Account builder = Account.builder()
                .nationalIdentification("123")
                .statusDescription("ACTIVE")
                .build();

        assertEquals("ACTIVE", builder.getStatusDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    @Test
    void shouldCover() {
        Amount dto = new Amount();
        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());

        Amount all = new Amount("1000", "COP");
        assertEquals("1000", all.getAmount());

        Amount builder = Amount.builder()
                .amount("1000")
                .currency("COP")
                .build();

        assertEquals("COP", builder.getCurrency());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void shouldCover() {
        Product dto = new Product();
        dto.setProductCode("60");
        dto.setProductDescription("CDT");

        assertEquals("60", dto.getProductCode());
        assertEquals("CDT", dto.getProductDescription());

        Product all = new Product("60", "CDT");
        assertEquals("60", all.getProductCode());

        Product builder = Product.builder()
                .productCode("60")
                .productDescription("CDT")
                .build();

        assertEquals("CDT", builder.getProductDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {
    @Test
    void shouldCover() {
        Product product = new Product("60", "CDT");

        Contract dto = new Contract();
        dto.setProduct(product);

        assertEquals(product, dto.getProduct());

        Contract all = new Contract(product);
        assertEquals(product, all.getProduct());

        Contract builder = Contract.builder()
                .product(product)
                .build();

        assertEquals(product, builder.getProduct());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    @Test
    void shouldCover() {
        Currency dto = new Currency();
        dto.setCode("COP");

        assertEquals("COP", dto.getCode());

        Currency all = new Currency("COP");
        assertEquals("COP", all.getCode());

        Currency builder = Currency.builder()
                .code("COP")
                .build();

        assertEquals("COP", builder.getCode());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationFundsTest {
    @Test
    void shouldCover() {
        Account account = new Account("123", "ACTIVE");

        DestinationFunds dto = new DestinationFunds();
        dto.setAccountIdType("CC");
        dto.setBankcode("0065");
        dto.setAccount(account);

        assertEquals("CC", dto.getAccountIdType());
        assertEquals("0065", dto.getBankcode());
        assertEquals(account, dto.getAccount());

        DestinationFunds all = new DestinationFunds("CC", "0065", account);
        assertEquals(account, all.getAccount());

        DestinationFunds builder = DestinationFunds.builder()
                .accountIdType("CC")
                .bankcode("0065")
                .account(account)
                .build();

        assertEquals("0065", builder.getBankcode());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialTotalInvestedTest {
    @Test
    void shouldCover() {
        InitialTotalInvested dto = new InitialTotalInvested();
        dto.setAmount("1000");
        dto.setCurrency("COP");

        assertEquals("1000", dto.getAmount());
        assertEquals("COP", dto.getCurrency());

        InitialTotalInvested all = new InitialTotalInvested("1000", "COP");
        assertEquals("1000", all.getAmount());

        InitialTotalInvested builder = InitialTotalInvested.builder()
                .amount("1000")
                .currency("COP")
                .build();

        assertEquals("COP", builder.getCurrency());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OriginIdentifierTest {
    @Test
    void shouldCover() {
        OriginIdentifier dto = new OriginIdentifier();
        dto.setCode("01");
        dto.setDescription("ORIGIN");

        assertEquals("01", dto.getCode());
        assertEquals("ORIGIN", dto.getDescription());

        OriginIdentifier all = new OriginIdentifier("01", "ORIGIN");
        assertEquals("01", all.getCode());

        OriginIdentifier builder = OriginIdentifier.builder()
                .code("01")
                .description("ORIGIN")
                .build();

        assertEquals("ORIGIN", builder.getDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlacementIdentificationTest {
    @Test
    void shouldCover() {
        PlacementIdentification dto = new PlacementIdentification();
        dto.setIsin("ISIN001");

        assertEquals("ISIN001", dto.getIsin());

        PlacementIdentification all = new PlacementIdentification("ISIN001");
        assertEquals("ISIN001", all.getIsin());

        PlacementIdentification builder = PlacementIdentification.builder()
                .isin("ISIN001")
                .build();

        assertEquals("ISIN001", builder.getIsin());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfitabilityAtMaturityTest {
    @Test
    void shouldCover() {
        ProfitabilityAtMaturity dto = new ProfitabilityAtMaturity();
        dto.setAmount("500");
        dto.setCurrency("COP");

        assertEquals("500", dto.getAmount());
        assertEquals("COP", dto.getCurrency());

        ProfitabilityAtMaturity all = new ProfitabilityAtMaturity("500", "COP");
        assertEquals("500", all.getAmount());

        ProfitabilityAtMaturity builder = ProfitabilityAtMaturity.builder()
                .amount("500")
                .currency("COP")
                .build();

        assertEquals("COP", builder.getCurrency());
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettlementConditionTest {
    @Test
    void shouldCover() {
        SettlementCondition dto = new SettlementCondition();
        dto.setCode("C");
        dto.setDescription("Capitalizable");

        assertEquals("C", dto.getCode());
        assertEquals("Capitalizable", dto.getDescription());

        SettlementCondition all = new SettlementCondition("C", "Capitalizable");
        assertEquals("C", all.getCode());

        SettlementCondition builder = SettlementCondition.builder()
                .code("C")
                .description("Capitalizable")
                .build();

        assertEquals("Capitalizable", builder.getDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusInfoTest {
    @Test
    void shouldCover() {
        StatusInfo dto = new StatusInfo();
        dto.setStatusCode("A");
        dto.setStatusDescription("ACTIVE");

        assertEquals("A", dto.getStatusCode());
        assertEquals("ACTIVE", dto.getStatusDescription());

        StatusInfo all = new StatusInfo("A", "ACTIVE");
        assertEquals("A", all.getStatusCode());

        StatusInfo builder = StatusInfo.builder()
                .statusCode("A")
                .statusDescription("ACTIVE")
                .build();

        assertEquals("ACTIVE", builder.getStatusDescription());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubproductTest {
    @Test
    void shouldCover() {
        Subproduct dto = new Subproduct();
        dto.setSubproductId("001");
        dto.setName("CDT Digital");

        assertEquals("001", dto.getSubproductId());
        assertEquals("CDT Digital", dto.getName());

        Subproduct all = new Subproduct("001", "CDT Digital");
        assertEquals("001", all.getSubproductId());

        Subproduct builder = Subproduct.builder()
                .subproductId("001")
                .name("CDT Digital")
                .build();

        assertEquals("CDT Digital", builder.getName());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettlementConceptTest {
    @Test
    void shouldCover() {
        Amount amount = new Amount("100", "COP");

        SettlementConcept dto = new SettlementConcept();
        dto.setCode("BGMF");
        dto.setDescription("GMF");
        dto.setTypeCode("C");
        dto.setTypeDescription("Credit");
        dto.setRate("1.0");
        dto.setAmount(amount);

        assertEquals("BGMF", dto.getCode());
        assertEquals("GMF", dto.getDescription());
        assertEquals("C", dto.getTypeCode());
        assertEquals("Credit", dto.getTypeDescription());
        assertEquals("1.0", dto.getRate());
        assertEquals(amount, dto.getAmount());

        SettlementConcept all = new SettlementConcept("BGMF", "GMF", "C", "Credit", "1.0", amount);
        assertEquals(amount, all.getAmount());

        SettlementConcept builder = SettlementConcept.builder()
                .code("BGMF")
                .description("GMF")
                .typeCode("C")
                .typeDescription("Credit")
                .rate("1.0")
                .amount(amount)
                .build();

        assertEquals("BGMF", builder.getCode());
    }
}
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettlementTest {
    @Test
    void shouldCover() {
        SettlementConcept concept = SettlementConcept.builder()
                .code("BGMF")
                .amount(new Amount("100", "COP"))
                .build();

        Settlement dto = new Settlement();
        dto.setSettlementConcept(concept);

        assertEquals(concept, dto.getSettlementConcept());

        Settlement all = new Settlement(concept);
        assertEquals(concept, all.getSettlementConcept());

        Settlement builder = Settlement.builder()
                .settlementConcept(concept)
                .build();

        assertEquals(concept, builder.getSettlementConcept());
    }
}
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicityTest {
    @Test
    void shouldCoverDataMethods() {
        Periodicity one = new Periodicity();
        one.setFrequency(30);
        one.setPeriodTypeCode("D");
        one.setPeriodTypeDescription("Days");

        assertEquals(30, one.getFrequency());
        assertEquals("D", one.getPeriodTypeCode());
        assertEquals("Days", one.getPeriodTypeDescription());

        Periodicity two = new Periodicity(30, "D", "Days");

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("frequency"));

        Periodicity builder = Periodicity.builder()
                .frequency(30)
                .periodTypeCode("D")
                .periodTypeDescription("Days")
                .build();

        assertEquals(one, builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepositPlacementResponseDTOTest {
    @Test
    void shouldCover() {
        Contract contract = Contract.builder()
                .product(new Product("60", "CDT"))
                .build();

        Placement placement = Placement.builder()
                .maturityDate("2025-12-31")
                .build();

        DepositPlacementResponseDTO dto = new DepositPlacementResponseDTO();
        dto.setContract(contract);
        dto.setPlacement(placement);

        assertEquals(contract, dto.getContract());
        assertEquals(placement, dto.getPlacement());

        DepositPlacementResponseDTO all = new DepositPlacementResponseDTO(contract, placement);
        assertEquals(contract, all.getContract());

        DepositPlacementResponseDTO builder = DepositPlacementResponseDTO.builder()
                .contract(contract)
                .placement(placement)
                .build();

        assertEquals(placement, builder.getPlacement());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlacementTest {

    @Test
    void shouldCoverGettersSettersBuilderAndDataMethods() {
        PlacementIdentification placementIdentification = new PlacementIdentification("ISIN");
        StatusInfo statusInfo = new StatusInfo("A", "ACTIVE");
        Subproduct subproduct = new Subproduct("001", "CDT");
        Currency currency = new Currency("COP");
        Periodicity periodicity = new Periodicity(30, "D", "Days");
        OriginIdentifier originIdentifier = new OriginIdentifier("01", "Origin");
        SettlementCondition settlementCondition = new SettlementCondition("C", "Capitalizable");
        DestinationFunds destinationFunds = new DestinationFunds("CC", "0065", new Account("123", "ACTIVE"));
        ProfitabilityAtMaturity profitabilityAtMaturity = new ProfitabilityAtMaturity("100", "COP");
        InitialTotalInvested initialTotalInvested = new InitialTotalInvested("1000", "COP");
        Settlement settlement = new Settlement(new SettlementConcept("BGMF", "GMF", "C", "Credit", "1.0", new Amount("10", "COP")));

        Placement one = new Placement();

        one.setPlacementIdentification(placementIdentification);
        one.setStatusInfo(statusInfo);
        one.setSubproduct(subproduct);
        one.setCurrency(currency);
        one.setPeriodicity(periodicity);
        one.setMaturityDate("2025-12-31");
        one.setOpeningDate("2024-01-01");
        one.setRenewable(true);
        one.setCapitalized(true);
        one.setBlocked(false);
        one.setOriginIdentifier(originIdentifier);
        one.setSettlementCondition(settlementCondition);
        one.setAnnualPercentageYield("12.5");
        one.setRate("10");
        one.setDestinationFunds(destinationFunds);
        one.setPurposeCode("12");
        one.setPurposeDescription("Purpose");
        one.setLastRenewalDate("2024-12-31");
        one.setProfitabilityAtMaturity(profitabilityAtMaturity);
        one.setInitialTotalInvested(initialTotalInvested);
        one.setSettlements(List.of(settlement));

        assertEquals(placementIdentification, one.getPlacementIdentification());
        assertEquals(statusInfo, one.getStatusInfo());
        assertEquals(subproduct, one.getSubproduct());
        assertEquals(currency, one.getCurrency());
        assertEquals(periodicity, one.getPeriodicity());
        assertEquals("2025-12-31", one.getMaturityDate());
        assertEquals("2024-01-01", one.getOpeningDate());
        assertTrue(one.isRenewable());
        assertTrue(one.isCapitalized());
        assertFalse(one.isBlocked());
        assertEquals(originIdentifier, one.getOriginIdentifier());
        assertEquals(settlementCondition, one.getSettlementCondition());
        assertEquals("12.5", one.getAnnualPercentageYield());
        assertEquals("10", one.getRate());
        assertEquals(destinationFunds, one.getDestinationFunds());
        assertEquals("12", one.getPurposeCode());
        assertEquals("Purpose", one.getPurposeDescription());
        assertEquals("2024-12-31", one.getLastRenewalDate());
        assertEquals(profitabilityAtMaturity, one.getProfitabilityAtMaturity());
        assertEquals(initialTotalInvested, one.getInitialTotalInvested());
        assertEquals(List.of(settlement), one.getSettlements());

        Placement two = Placement.builder()
                .placementIdentification(placementIdentification)
                .statusInfo(statusInfo)
                .subproduct(subproduct)
                .currency(currency)
                .periodicity(periodicity)
                .maturityDate("2025-12-31")
                .openingDate("2024-01-01")
                .isRenewable(true)
                .isCapitalized(true)
                .isBlocked(false)
                .originIdentifier(originIdentifier)
                .settlementCondition(settlementCondition)
                .annualPercentageYield("12.5")
                .rate("10")
                .destinationFunds(destinationFunds)
                .purposeCode("12")
                .purposeDescription("Purpose")
                .lastRenewalDate("2024-12-31")
                .profitabilityAtMaturity(profitabilityAtMaturity)
                .initialTotalInvested(initialTotalInvested)
                .settlements(List.of(settlement))
                .build();

        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("maturityDate"));
    }
}
