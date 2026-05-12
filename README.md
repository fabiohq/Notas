package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BanksDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        BanksDTO dto = new BanksDTO();
        List<BanksParametersDTO> banks = List.of(new BanksParametersDTO("0065", "Banco Santander"));

        dto.setBanks(banks);

        assertThat(dto.getBanks()).isEqualTo(banks);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        List<BanksParametersDTO> banks = List.of(new BanksParametersDTO("0065", "Banco Santander"));

        BanksDTO dto = new BanksDTO(banks);

        assertThat(dto.getBanks()).isEqualTo(banks);
    }

    @Test
    void shouldCreateWithBuilder() {
        List<BanksParametersDTO> banks = List.of(new BanksParametersDTO("0065", "Banco Santander"));

        BanksDTO dto = BanksDTO.builder()
                .Banks(banks)
                .build();

        assertThat(dto.getBanks()).isEqualTo(banks);
    }

    @Test
    void shouldValidateEqualsHashCodeAndToString() {
        List<BanksParametersDTO> banks = List.of(new BanksParametersDTO("0065", "Banco Santander"));
        BanksDTO dto = new BanksDTO(banks);
        BanksDTO sameDto = new BanksDTO(banks);

        assertThat(dto)
                .isEqualTo(sameDto)
                .hasSameHashCodeAs(sameDto);
        assertThat(dto.toString()).contains("Banks");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BanksParametersDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        BanksParametersDTO dto = new BanksParametersDTO();

        dto.setBankId("0065");
        dto.setBankName("Banco Santander");

        assertThat(dto.getBankId()).isEqualTo("0065");
        assertThat(dto.getBankName()).isEqualTo("Banco Santander");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        BanksParametersDTO dto = new BanksParametersDTO("0065", "Banco Santander");

        assertThat(dto.getBankId()).isEqualTo("0065");
        assertThat(dto.getBankName()).isEqualTo("Banco Santander");
    }

    @Test
    void shouldCreateWithBuilder() {
        BanksParametersDTO dto = BanksParametersDTO.builder()
                .bankId("0065")
                .bankName("Banco Santander")
                .build();

        assertThat(dto.getBankId()).isEqualTo("0065");
        assertThat(dto.getBankName()).isEqualTo("Banco Santander");
    }

    @Test
    void shouldValidateEqualsHashCodeAndToString() {
        BanksParametersDTO dto = new BanksParametersDTO("0065", "Banco Santander");
        BanksParametersDTO sameDto = new BanksParametersDTO("0065", "Banco Santander");

        assertThat(dto)
                .isEqualTo(sameDto)
                .hasSameHashCodeAs(sameDto);
        assertThat(dto.toString()).contains("bankId", "bankName");
    }
}package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BanksParametersRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        BanksParametersRequest request = new BanksParametersRequest();

        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getXSantanderClientId()).isEqualTo("client-id");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        BanksParametersRequest request = new BanksParametersRequest("Bearer token", "client-id");

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getXSantanderClientId()).isEqualTo("client-id");
    }

    @Test
    void shouldCreateWithBuilder() {
        BanksParametersRequest request = BanksParametersRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getXSantanderClientId()).isEqualTo("client-id");
    }

    @Test
    void shouldValidateEqualsHashCodeAndToString() {
        BanksParametersRequest request = new BanksParametersRequest("Bearer token", "client-id");
        BanksParametersRequest sameRequest = new BanksParametersRequest("Bearer token", "client-id");

        assertThat(request)
                .isEqualTo(sameRequest)
                .hasSameHashCodeAs(sameRequest);
        assertThat(request.toString()).contains("authorization", "xSantanderClientId");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BanksResponseDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        BanksDTO banks = BanksDTO.builder()
                .Banks(List.of(new BanksParametersDTO("0065", "Banco Santander")))
                .build();
        BanksResponseDTO response = new BanksResponseDTO();

        response.setBanks(banks);

        assertThat(response.getBanks()).isEqualTo(banks);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        BanksDTO banks = BanksDTO.builder()
                .Banks(List.of(new BanksParametersDTO("0065", "Banco Santander")))
                .build();

        BanksResponseDTO response = new BanksResponseDTO(banks);

        assertThat(response.getBanks()).isEqualTo(banks);
    }

    @Test
    void shouldCreateWithBuilder() {
        BanksDTO banks = BanksDTO.builder()
                .Banks(List.of(new BanksParametersDTO("0065", "Banco Santander")))
                .build();

        BanksResponseDTO response = BanksResponseDTO.builder()
                .banks(banks)
                .build();

        assertThat(response.getBanks()).isEqualTo(banks);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepositPlacementRequestDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        DepositPlacementRequestDTO request = new DepositPlacementRequestDTO();

        request.setAuthorization("Bearer token");
        request.setxSantanderClientId("client-id");
        request.setDepositId("deposit-id");
        request.setPlacementId("placement-id");

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getxSantanderClientId()).isEqualTo("client-id");
        assertThat(request.getDepositId()).isEqualTo("deposit-id");
        assertThat(request.getPlacementId()).isEqualTo("placement-id");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        DepositPlacementRequestDTO request = new DepositPlacementRequestDTO(
                "Bearer token",
                "client-id",
                "deposit-id",
                "placement-id"
        );

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getxSantanderClientId()).isEqualTo("client-id");
        assertThat(request.getDepositId()).isEqualTo("deposit-id");
        assertThat(request.getPlacementId()).isEqualTo("placement-id");
    }

    @Test
    void shouldCreateWithBuilder() {
        DepositPlacementRequestDTO request = DepositPlacementRequestDTO.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .depositId("deposit-id")
                .placementId("placement-id")
                .build();

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getxSantanderClientId()).isEqualTo("client-id");
        assertThat(request.getDepositId()).isEqualTo("deposit-id");
        assertThat(request.getPlacementId()).isEqualTo("placement-id");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Account account = new Account();

        account.setNationalIdentification("12345678");
        account.setStatusDescription("Active");

        assertThat(account.getNationalIdentification()).isEqualTo("12345678");
        assertThat(account.getStatusDescription()).isEqualTo("Active");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Account account = new Account("12345678", "Active");

        assertThat(account.getNationalIdentification()).isEqualTo("12345678");
        assertThat(account.getStatusDescription()).isEqualTo("Active");
    }

    @Test
    void shouldCreateWithBuilder() {
        Account account = Account.builder()
                .nationalIdentification("12345678")
                .statusDescription("Active")
                .build();

        assertThat(account.getNationalIdentification()).isEqualTo("12345678");
        assertThat(account.getStatusDescription()).isEqualTo("Active");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Amount amount = new Amount();

        amount.setAmount("1000,00");
        amount.setCurrency("COP");

        assertThat(amount.getAmount()).isEqualTo("1000,00");
        assertThat(amount.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Amount amount = new Amount("1000,00", "COP");

        assertThat(amount.getAmount()).isEqualTo("1000,00");
        assertThat(amount.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithBuilder() {
        Amount amount = Amount.builder()
                .amount("1000,00")
                .currency("COP")
                .build();

        assertThat(amount.getAmount()).isEqualTo("1000,00");
        assertThat(amount.getCurrency()).isEqualTo("COP");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContractTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Product product = new Product("04", "CDT");
        Contract contract = new Contract();

        contract.setProduct(product);

        assertThat(contract.getProduct()).isEqualTo(product);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Product product = new Product("04", "CDT");

        Contract contract = new Contract(product);

        assertThat(contract.getProduct()).isEqualTo(product);
    }

    @Test
    void shouldCreateWithBuilder() {
        Product product = new Product("04", "CDT");

        Contract contract = Contract.builder()
                .product(product)
                .build();

        assertThat(contract.getProduct()).isEqualTo(product);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Currency currency = new Currency();

        currency.setCode("COP");

        assertThat(currency.getCode()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Currency currency = new Currency("COP");

        assertThat(currency.getCode()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithBuilder() {
        Currency currency = Currency.builder()
                .code("COP")
                .build();

        assertThat(currency.getCode()).isEqualTo("COP");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DepositPlacementResponseDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Contract contract = Contract.builder().product(new Product("04", "CDT")).build();
        Placement placement = Placement.builder().purposeCode("01").build();
        DepositPlacementResponseDTO response = new DepositPlacementResponseDTO();

        response.setContract(contract);
        response.setPlacement(placement);

        assertThat(response.getContract()).isEqualTo(contract);
        assertThat(response.getPlacement()).isEqualTo(placement);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Contract contract = Contract.builder().product(new Product("04", "CDT")).build();
        Placement placement = Placement.builder().purposeCode("01").build();

        DepositPlacementResponseDTO response = new DepositPlacementResponseDTO(contract, placement);

        assertThat(response.getContract()).isEqualTo(contract);
        assertThat(response.getPlacement()).isEqualTo(placement);
    }

    @Test
    void shouldCreateWithBuilder() {
        Contract contract = Contract.builder().product(new Product("04", "CDT")).build();
        Placement placement = Placement.builder().purposeCode("01").build();

        DepositPlacementResponseDTO response = DepositPlacementResponseDTO.builder()
                .contract(contract)
                .placement(placement)
                .build();

        assertThat(response.getContract()).isEqualTo(contract);
        assertThat(response.getPlacement()).isEqualTo(placement);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DestinationFundsTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Account account = new Account("12345678", "Active");
        DestinationFunds destinationFunds = new DestinationFunds();

        destinationFunds.setAccountIdType("CC");
        destinationFunds.setBankcode("0065");
        destinationFunds.setAccount(account);

        assertThat(destinationFunds.getAccountIdType()).isEqualTo("CC");
        assertThat(destinationFunds.getBankcode()).isEqualTo("0065");
        assertThat(destinationFunds.getAccount()).isEqualTo(account);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Account account = new Account("12345678", "Active");

        DestinationFunds destinationFunds = new DestinationFunds("CC", "0065", account);

        assertThat(destinationFunds.getAccountIdType()).isEqualTo("CC");
        assertThat(destinationFunds.getBankcode()).isEqualTo("0065");
        assertThat(destinationFunds.getAccount()).isEqualTo(account);
    }

    @Test
    void shouldCreateWithBuilder() {
        Account account = new Account("12345678", "Active");

        DestinationFunds destinationFunds = DestinationFunds.builder()
                .accountIdType("CC")
                .Bankcode("0065")
                .account(account)
                .build();

        assertThat(destinationFunds.getAccountIdType()).isEqualTo("CC");
        assertThat(destinationFunds.getBankcode()).isEqualTo("0065");
        assertThat(destinationFunds.getAccount()).isEqualTo(account);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitialTotalInvestedTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        InitialTotalInvested invested = new InitialTotalInvested();

        invested.setAmount("1000,00");
        invested.setCurrency("COP");

        assertThat(invested.getAmount()).isEqualTo("1000,00");
        assertThat(invested.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        InitialTotalInvested invested = new InitialTotalInvested("1000,00", "COP");

        assertThat(invested.getAmount()).isEqualTo("1000,00");
        assertThat(invested.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithBuilder() {
        InitialTotalInvested invested = InitialTotalInvested.builder()
                .amount("1000,00")
                .currency("COP")
                .build();

        assertThat(invested.getAmount()).isEqualTo("1000,00");
        assertThat(invested.getCurrency()).isEqualTo("COP");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OriginIdentifierTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        OriginIdentifier originIdentifier = new OriginIdentifier();

        originIdentifier.setCode("01");
        originIdentifier.setDescription("Origin");

        assertThat(originIdentifier.getCode()).isEqualTo("01");
        assertThat(originIdentifier.getDescription()).isEqualTo("Origin");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        OriginIdentifier originIdentifier = new OriginIdentifier("01", "Origin");

        assertThat(originIdentifier.getCode()).isEqualTo("01");
        assertThat(originIdentifier.getDescription()).isEqualTo("Origin");
    }

    @Test
    void shouldCreateWithBuilder() {
        OriginIdentifier originIdentifier = OriginIdentifier.builder()
                .code("01")
                .description("Origin")
                .build();

        assertThat(originIdentifier.getCode()).isEqualTo("01");
        assertThat(originIdentifier.getDescription()).isEqualTo("Origin");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodicityTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Periodicity periodicity = new Periodicity();

        periodicity.setFrequency(90);
        periodicity.setPeriodTypeCode("D");
        periodicity.setPeriodTypeDescription("Days");

        assertThat(periodicity.getFrequency()).isEqualTo(90);
        assertThat(periodicity.getPeriodTypeCode()).isEqualTo("D");
        assertThat(periodicity.getPeriodTypeDescription()).isEqualTo("Days");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Periodicity periodicity = new Periodicity(90, "D", "Days");

        assertThat(periodicity.getFrequency()).isEqualTo(90);
        assertThat(periodicity.getPeriodTypeCode()).isEqualTo("D");
        assertThat(periodicity.getPeriodTypeDescription()).isEqualTo("Days");
    }

    @Test
    void shouldCreateWithBuilder() {
        Periodicity periodicity = Periodicity.builder()
                .frequency(90)
                .periodTypeCode("D")
                .periodTypeDescription("Days")
                .build();

        assertThat(periodicity.getFrequency()).isEqualTo(90);
        assertThat(periodicity.getPeriodTypeCode()).isEqualTo("D");
        assertThat(periodicity.getPeriodTypeDescription()).isEqualTo("Days");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlacementTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PlacementIdentification placementIdentification = new PlacementIdentification("ISIN123");
        StatusInfo statusInfo = new StatusInfo("S", "Active");
        Subproduct subproduct = new Subproduct("0250", "CDT");
        Currency currency = new Currency("COP");
        Periodicity periodicity = new Periodicity(90, "D", "Days");
        OriginIdentifier originIdentifier = new OriginIdentifier("01", "Origin");
        SettlementCondition settlementCondition = new SettlementCondition("B", "Condition");
        DestinationFunds destinationFunds = new DestinationFunds("CC", "0065", new Account("123", "Active"));
        ProfitabilityAtMaturity profitabilityAtMaturity = new ProfitabilityAtMaturity("100,00", "COP");
        InitialTotalInvested initialTotalInvested = new InitialTotalInvested("1000,00", "COP");
        List<Settlement> settlements = List.of(new Settlement(new SettlementConcept()));

        Placement placement = new Placement();
        placement.setPlacementIdentification(placementIdentification);
        placement.setStatusInfo(statusInfo);
        placement.setSubproduct(subproduct);
        placement.setCurrency(currency);
        placement.setPeriodicity(periodicity);
        placement.setMaturityDate("2026-12-31");
        placement.setOpeningDate("2026-01-01");
        placement.setRenewable(true);
        placement.setCapitalized(true);
        placement.setBlocked(false);
        placement.setOriginIdentifier(originIdentifier);
        placement.setSettlementCondition(settlementCondition);
        placement.setAnnualPercentageYield("10,0000");
        placement.setRate("9,5000");
        placement.setDestinationFunds(destinationFunds);
        placement.setPurposeCode("01");
        placement.setPurposeDescription("Purpose");
        placement.setLastRenewalDate("2026-06-01");
        placement.setProfitabilityAtMaturity(profitabilityAtMaturity);
        placement.setInitialTotalInvested(initialTotalInvested);
        placement.setSettlements(settlements);

        assertThat(placement.getPlacementIdentification()).isEqualTo(placementIdentification);
        assertThat(placement.getStatusInfo()).isEqualTo(statusInfo);
        assertThat(placement.getSubproduct()).isEqualTo(subproduct);
        assertThat(placement.getCurrency()).isEqualTo(currency);
        assertThat(placement.getPeriodicity()).isEqualTo(periodicity);
        assertThat(placement.getMaturityDate()).isEqualTo("2026-12-31");
        assertThat(placement.getOpeningDate()).isEqualTo("2026-01-01");
        assertThat(placement.isRenewable()).isTrue();
        assertThat(placement.isCapitalized()).isTrue();
        assertThat(placement.isBlocked()).isFalse();
        assertThat(placement.getOriginIdentifier()).isEqualTo(originIdentifier);
        assertThat(placement.getSettlementCondition()).isEqualTo(settlementCondition);
        assertThat(placement.getAnnualPercentageYield()).isEqualTo("10,0000");
        assertThat(placement.getRate()).isEqualTo("9,5000");
        assertThat(placement.getDestinationFunds()).isEqualTo(destinationFunds);
        assertThat(placement.getPurposeCode()).isEqualTo("01");
        assertThat(placement.getPurposeDescription()).isEqualTo("Purpose");
        assertThat(placement.getLastRenewalDate()).isEqualTo("2026-06-01");
        assertThat(placement.getProfitabilityAtMaturity()).isEqualTo(profitabilityAtMaturity);
        assertThat(placement.getInitialTotalInvested()).isEqualTo(initialTotalInvested);
        assertThat(placement.getSettlements()).isEqualTo(settlements);
    }

    @Test
    void shouldCreateWithBuilder() {
        List<Settlement> settlements = List.of(new Settlement(new SettlementConcept()));

        Placement placement = Placement.builder()
                .placementIdentification(new PlacementIdentification("ISIN123"))
                .statusInfo(new StatusInfo("S", "Active"))
                .subproduct(new Subproduct("0250", "CDT"))
                .currency(new Currency("COP"))
                .periodicity(new Periodicity(90, "D", "Days"))
                .maturityDate("2026-12-31")
                .openingDate("2026-01-01")
                .isRenewable(true)
                .isCapitalized(true)
                .isBlocked(false)
                .originIdentifier(new OriginIdentifier("01", "Origin"))
                .settlementCondition(new SettlementCondition("B", "Condition"))
                .annualPercentageYield("10,0000")
                .rate("9,5000")
                .destinationFunds(new DestinationFunds("CC", "0065", new Account("123", "Active")))
                .purposeCode("01")
                .purposeDescription("Purpose")
                .lastRenewalDate("2026-06-01")
                .profitabilityAtMaturity(new ProfitabilityAtMaturity("100,00", "COP"))
                .initialTotalInvested(new InitialTotalInvested("1000,00", "COP"))
                .settlements(settlements)
                .build();

        assertThat(placement.getPlacementIdentification().getIsin()).isEqualTo("ISIN123");
        assertThat(placement.getStatusInfo().getStatusCode()).isEqualTo("S");
        assertThat(placement.getSubproduct().getSubproductId()).isEqualTo("0250");
        assertThat(placement.getCurrency().getCode()).isEqualTo("COP");
        assertThat(placement.getPeriodicity().getFrequency()).isEqualTo(90);
        assertThat(placement.isRenewable()).isTrue();
        assertThat(placement.isCapitalized()).isTrue();
        assertThat(placement.isBlocked()).isFalse();
        assertThat(placement.getSettlements()).isEqualTo(settlements);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlacementIdentificationTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PlacementIdentification identification = new PlacementIdentification();

        identification.setIsin("ISIN123");

        assertThat(identification.getIsin()).isEqualTo("ISIN123");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        PlacementIdentification identification = new PlacementIdentification("ISIN123");

        assertThat(identification.getIsin()).isEqualTo("ISIN123");
    }

    @Test
    void shouldCreateWithBuilder() {
        PlacementIdentification identification = PlacementIdentification.builder()
                .isin("ISIN123")
                .build();

        assertThat(identification.getIsin()).isEqualTo("ISIN123");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Product product = new Product();

        product.setProductCode("04");
        product.setProductDescription("CDT");

        assertThat(product.getProductCode()).isEqualTo("04");
        assertThat(product.getProductDescription()).isEqualTo("CDT");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Product product = new Product("04", "CDT");

        assertThat(product.getProductCode()).isEqualTo("04");
        assertThat(product.getProductDescription()).isEqualTo("CDT");
    }

    @Test
    void shouldCreateWithBuilder() {
        Product product = Product.builder()
                .productCode("04")
                .productDescription("CDT")
                .build();

        assertThat(product.getProductCode()).isEqualTo("04");
        assertThat(product.getProductDescription()).isEqualTo("CDT");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitabilityAtMaturityTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ProfitabilityAtMaturity profitability = new ProfitabilityAtMaturity();

        profitability.setAmount("100,00");
        profitability.setCurrency("COP");

        assertThat(profitability.getAmount()).isEqualTo("100,00");
        assertThat(profitability.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        ProfitabilityAtMaturity profitability = new ProfitabilityAtMaturity("100,00", "COP");

        assertThat(profitability.getAmount()).isEqualTo("100,00");
        assertThat(profitability.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithBuilder() {
        ProfitabilityAtMaturity profitability = ProfitabilityAtMaturity.builder()
                .amount("100,00")
                .currency("COP")
                .build();

        assertThat(profitability.getAmount()).isEqualTo("100,00");
        assertThat(profitability.getCurrency()).isEqualTo("COP");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SettlementTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        SettlementConcept concept = new SettlementConcept();
        Settlement settlement = new Settlement();

        settlement.setSettlementConcept(concept);

        assertThat(settlement.getSettlementConcept()).isEqualTo(concept);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        SettlementConcept concept = new SettlementConcept();

        Settlement settlement = new Settlement(concept);

        assertThat(settlement.getSettlementConcept()).isEqualTo(concept);
    }

    @Test
    void shouldCreateWithBuilder() {
        SettlementConcept concept = new SettlementConcept();

        Settlement settlement = Settlement.builder()
                .settlementConcept(concept)
                .build();

        assertThat(settlement.getSettlementConcept()).isEqualTo(concept);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SettlementConceptTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Amount amount = new Amount("100,00", "COP");
        SettlementConcept concept = new SettlementConcept();

        concept.setCode("BGMF");
        concept.setDescription("Concept");
        concept.setTypeCode("A");
        concept.setTypeDescription("Type");
        concept.setRate("1,0000");
        concept.setAmount(amount);

        assertThat(concept.getCode()).isEqualTo("BGMF");
        assertThat(concept.getDescription()).isEqualTo("Concept");
        assertThat(concept.getTypeCode()).isEqualTo("A");
        assertThat(concept.getTypeDescription()).isEqualTo("Type");
        assertThat(concept.getRate()).isEqualTo("1,0000");
        assertThat(concept.getAmount()).isEqualTo(amount);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Amount amount = new Amount("100,00", "COP");

        SettlementConcept concept = new SettlementConcept(
                "BGMF",
                "Concept",
                "A",
                "Type",
                "1,0000",
                amount
        );

        assertThat(concept.getCode()).isEqualTo("BGMF");
        assertThat(concept.getDescription()).isEqualTo("Concept");
        assertThat(concept.getTypeCode()).isEqualTo("A");
        assertThat(concept.getTypeDescription()).isEqualTo("Type");
        assertThat(concept.getRate()).isEqualTo("1,0000");
        assertThat(concept.getAmount()).isEqualTo(amount);
    }

    @Test
    void shouldCreateWithBuilder() {
        Amount amount = new Amount("100,00", "COP");

        SettlementConcept concept = SettlementConcept.builder()
                .code("BGMF")
                .description("Concept")
                .typeCode("A")
                .typeDescription("Type")
                .rate("1,0000")
                .amount(amount)
                .build();

        assertThat(concept.getCode()).isEqualTo("BGMF");
        assertThat(concept.getDescription()).isEqualTo("Concept");
        assertThat(concept.getTypeCode()).isEqualTo("A");
        assertThat(concept.getTypeDescription()).isEqualTo("Type");
        assertThat(concept.getRate()).isEqualTo("1,0000");
        assertThat(concept.getAmount()).isEqualTo(amount);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SettlementConditionTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        SettlementCondition condition = new SettlementCondition();

        condition.setCode("B");
        condition.setDescription("Condition");

        assertThat(condition.getCode()).isEqualTo("B");
        assertThat(condition.getDescription()).isEqualTo("Condition");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        SettlementCondition condition = new SettlementCondition("B", "Condition");

        assertThat(condition.getCode()).isEqualTo("B");
        assertThat(condition.getDescription()).isEqualTo("Condition");
    }

    @Test
    void shouldCreateWithBuilder() {
        SettlementCondition condition = SettlementCondition.builder()
                .code("B")
                .description("Condition")
                .build();

        assertThat(condition.getCode()).isEqualTo("B");
        assertThat(condition.getDescription()).isEqualTo("Condition");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusInfoTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        StatusInfo statusInfo = new StatusInfo();

        statusInfo.setStatusCode("S");
        statusInfo.setStatusDescription("Active");

        assertThat(statusInfo.getStatusCode()).isEqualTo("S");
        assertThat(statusInfo.getStatusDescription()).isEqualTo("Active");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        StatusInfo statusInfo = new StatusInfo("S", "Active");

        assertThat(statusInfo.getStatusCode()).isEqualTo("S");
        assertThat(statusInfo.getStatusDescription()).isEqualTo("Active");
    }

    @Test
    void shouldCreateWithBuilder() {
        StatusInfo statusInfo = StatusInfo.builder()
                .statusCode("S")
                .statusDescription("Active")
                .build();

        assertThat(statusInfo.getStatusCode()).isEqualTo("S");
        assertThat(statusInfo.getStatusDescription()).isEqualTo("Active");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubproductTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Subproduct subproduct = new Subproduct();

        subproduct.setSubproductId("0250");
        subproduct.setName("CDT");

        assertThat(subproduct.getSubproductId()).isEqualTo("0250");
        assertThat(subproduct.getName()).isEqualTo("CDT");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        Subproduct subproduct = new Subproduct("0250", "CDT");

        assertThat(subproduct.getSubproductId()).isEqualTo("0250");
        assertThat(subproduct.getName()).isEqualTo("CDT");
    }

    @Test
    void shouldCreateWithBuilder() {
        Subproduct subproduct = Subproduct.builder()
                .subproductId("0250")
                .name("CDT")
                .build();

        assertThat(subproduct.getSubproductId()).isEqualTo("0250");
        assertThat(subproduct.getName()).isEqualTo("CDT");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrxBP49DataRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TrxBP49DataRequest request = new TrxBP49DataRequest();

        request.setBuscarPor("C");
        request.setEnt("0065");
        request.setOfic("0060");
        request.setCuenta("1234567890");
        request.setSecuencia("001");
        request.setNumeroCertificado("00001");
        request.setDocumentoCajero("12345678");

        assertThat(request.getBuscarPor()).isEqualTo("C");
        assertThat(request.getEnt()).isEqualTo("0065");
        assertThat(request.getOfic()).isEqualTo("0060");
        assertThat(request.getCuenta()).isEqualTo("1234567890");
        assertThat(request.getSecuencia()).isEqualTo("001");
        assertThat(request.getNumeroCertificado()).isEqualTo("00001");
        assertThat(request.getDocumentoCajero()).isEqualTo("12345678");
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        TrxBP49DataRequest request = new TrxBP49DataRequest(
                "C", "0065", "0060", "1234567890", "001", "00001", "12345678"
        );

        assertThat(request.getBuscarPor()).isEqualTo("C");
        assertThat(request.getEnt()).isEqualTo("0065");
        assertThat(request.getOfic()).isEqualTo("0060");
        assertThat(request.getCuenta()).isEqualTo("1234567890");
        assertThat(request.getSecuencia()).isEqualTo("001");
        assertThat(request.getNumeroCertificado()).isEqualTo("00001");
        assertThat(request.getDocumentoCajero()).isEqualTo("12345678");
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxBP49DataRequest request = TrxBP49DataRequest.builder()
                .buscarPor("C")
                .ent("0065")
                .ofic("0060")
                .cuenta("1234567890")
                .secuencia("001")
                .numeroCertificado("00001")
                .documentoCajero("12345678")
                .build();

        assertThat(request.getBuscarPor()).isEqualTo("C");
        assertThat(request.getEnt()).isEqualTo("0065");
        assertThat(request.getOfic()).isEqualTo("0060");
        assertThat(request.getCuenta()).isEqualTo("1234567890");
        assertThat(request.getSecuencia()).isEqualTo("001");
        assertThat(request.getNumeroCertificado()).isEqualTo("00001");
        assertThat(request.getDocumentoCajero()).isEqualTo("12345678");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrxBP49RequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();
        TrxBP49Request request = new TrxBP49Request();

        request.setCabecera(header);
        request.setData(data);

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        TrxBP49Request request = new TrxBP49Request(header, data);

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxHeader header = new TrxHeader();
        TrxBP49DataRequest data = new TrxBP49DataRequest();

        TrxBP49Request request = TrxBP49Request.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrxBP49DataTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TrxBP49Data data = new TrxBP49Data();

        data.setCdtDat("cdtDat");
        data.setCERTIFI("certifi");
        data.setFecha("2026-01-01");
        data.setSecuencia("001");
        data.setRETEN("reten");
        data.setSecRen("secRen");
        data.setINTABON("intabon");
        data.setEstado("S");
        data.setNumMov("1");
        data.setInteresPendienteLiquidar("100,00");
        data.setPago("pago");
        data.setConcepto("concepto");
        data.setValor("1000,00");

        assertThat(data.getCdtDat()).isEqualTo("cdtDat");
        assertThat(data.getCERTIFI()).isEqualTo("certifi");
        assertThat(data.getFecha()).isEqualTo("2026-01-01");
        assertThat(data.getSecuencia()).isEqualTo("001");
        assertThat(data.getRETEN()).isEqualTo("reten");
        assertThat(data.getSecRen()).isEqualTo("secRen");
        assertThat(data.getINTABON()).isEqualTo("intabon");
        assertThat(data.getEstado()).isEqualTo("S");
        assertThat(data.getNumMov()).isEqualTo("1");
        assertThat(data.getInteresPendienteLiquidar()).isEqualTo("100,00");
        assertThat(data.getPago()).isEqualTo("pago");
        assertThat(data.getConcepto()).isEqualTo("concepto");
        assertThat(data.getValor()).isEqualTo("1000,00");
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxBP49Data data = TrxBP49Data.builder()
                .cdtDat("cdtDat")
                .CERTIFI("certifi")
                .fecha("2026-01-01")
                .secuencia("001")
                .RETEN("reten")
                .secRen("secRen")
                .INTABON("intabon")
                .estado("S")
                .numMov("1")
                .interesPendienteLiquidar("100,00")
                .pago("pago")
                .concepto("concepto")
                .valor("1000,00")
                .build();

        assertThat(data.getCdtDat()).isEqualTo("cdtDat");
        assertThat(data.getCERTIFI()).isEqualTo("certifi");
        assertThat(data.getValor()).isEqualTo("1000,00");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class TrxBP49DataResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());
        TrxBP49DataResponse response = new TrxBP49DataResponse();

        response.setMovimientos(movimientos);

        assertThat(response.getMovimientos()).isEqualTo(movimientos);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());

        TrxBP49DataResponse response = new TrxBP49DataResponse(movimientos);

        assertThat(response.getMovimientos()).isEqualTo(movimientos);
    }

    @Test
    void shouldCreateWithBuilder() {
        ArrayList<TrxBP49Data> movimientos = new ArrayList<>();
        movimientos.add(new TrxBP49Data());

        TrxBP49DataResponse response = TrxBP49DataResponse.builder()
                .movimientos(movimientos)
                .build();

        assertThat(response.getMovimientos()).isEqualTo(movimientos);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrxBP49ResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TrxBP49DataResponse data = new TrxBP49DataResponse();
        TrxHeader cabecera = new TrxHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of();
        Object conexion = new Object();
        TrxBP49Response response = new TrxBP49Response();

        response.setData(data);
        response.setCabecera(cabecera);
        response.setAutorizacion(autorizacion);
        response.setPaginacion(paginacion);
        response.setAvisos(avisos);
        response.setErrores(errores);
        response.setConexion(conexion);
        response.setOk(true);

        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getCabecera()).isEqualTo(cabecera);
        assertThat(response.getAutorizacion()).isEqualTo(autorizacion);
        assertThat(response.getPaginacion()).isEqualTo(paginacion);
        assertThat(response.getAvisos()).isEqualTo(avisos);
        assertThat(response.getErrores()).isEqualTo(errores);
        assertThat(response.getConexion()).isEqualTo(conexion);
        assertThat(response.getOk()).isTrue();
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxBP49DataResponse data = new TrxBP49DataResponse();
        TrxHeader cabecera = new TrxHeader();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of();

        TrxBP49Response response = TrxBP49Response.builder()
                .data(data)
                .cabecera(cabecera)
                .autorizacion("auth")
                .paginacion("page")
                .avisos(avisos)
                .errores(errores)
                .conexion("connection")
                .ok(true)
                .build();

        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getCabecera()).isEqualTo(cabecera);
        assertThat(response.getAvisos()).isEqualTo(avisos);
        assertThat(response.getErrores()).isEqualTo(errores);
        assertThat(response.getOk()).isTrue();
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Session session = new Session();

        session.setUsuario("user");
        session.setTerminal("terminal");
        session.setHoraConexion("120000");
        session.setEntorno("local");
        session.setPerfil("profile");
        session.setSucursal("0060");
        session.setEntidad("0065");
        session.setDiasRestantesCambioClave("10");
        session.setFechaContable("2026-01-01");
        session.setTurno("1");

        assertThat(session.getUsuario()).isEqualTo("user");
        assertThat(session.getTerminal()).isEqualTo("terminal");
        assertThat(session.getHoraConexion()).isEqualTo("120000");
        assertThat(session.getEntorno()).isEqualTo("local");
        assertThat(session.getPerfil()).isEqualTo("profile");
        assertThat(session.getSucursal()).isEqualTo("0060");
        assertThat(session.getEntidad()).isEqualTo("0065");
        assertThat(session.getDiasRestantesCambioClave()).isEqualTo("10");
        assertThat(session.getFechaContable()).isEqualTo("2026-01-01");
        assertThat(session.getTurno()).isEqualTo("1");
    }

    @Test
    void shouldCreateWithBuilder() {
        Session session = Session.builder()
                .usuario("user")
                .terminal("terminal")
                .horaConexion("120000")
                .entorno("local")
                .perfil("profile")
                .sucursal("0060")
                .entidad("0065")
                .diasRestantesCambioClave("10")
                .fechaContable("2026-01-01")
                .turno("1")
                .build();

        assertThat(session.getUsuario()).isEqualTo("user");
        assertThat(session.getTerminal()).isEqualTo("terminal");
        assertThat(session.getEntidad()).isEqualTo("0065");
        assertThat(session.getTurno()).isEqualTo("1");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrxHeaderTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        Session session = new Session();
        TrxHeader header = new TrxHeader();

        header.setRutaServicio("route");
        header.setSesion(session);
        header.setFuncion("function");
        header.setSecuencia(1);
        header.setCanal("60");
        header.setResultado("OK");

        assertThat(header.getRutaServicio()).isEqualTo("route");
        assertThat(header.getSesion()).isEqualTo(session);
        assertThat(header.getFuncion()).isEqualTo("function");
        assertThat(header.getSecuencia()).isEqualTo(1);
        assertThat(header.getCanal()).isEqualTo("60");
        assertThat(header.getResultado()).isEqualTo("OK");
    }

    @Test
    void shouldCreateWithBuilder() {
        Session session = new Session();

        TrxHeader header = TrxHeader.builder()
                .rutaServicio("route")
                .sesion(session)
                .funcion("function")
                .secuencia(1)
                .canal("60")
                .resultado("OK")
                .build();

        assertThat(header.getRutaServicio()).isEqualTo("route");
        assertThat(header.getSesion()).isEqualTo(session);
        assertThat(header.getFuncion()).isEqualTo("function");
        assertThat(header.getSecuencia()).isEqualTo(1);
        assertThat(header.getCanal()).isEqualTo("60");
        assertThat(header.getResultado()).isEqualTo("OK");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvDataRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PemfvInfoAdicional info = new PemfvInfoAdicional();
        PemfvDataRequest request = new PemfvDataRequest();

        request.setInfAdicional(info);

        assertThat(request.getInfAdicional()).isEqualTo(info);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        PemfvInfoAdicional info = new PemfvInfoAdicional();

        PemfvDataRequest request = new PemfvDataRequest(info);

        assertThat(request.getInfAdicional()).isEqualTo(info);
    }

    @Test
    void shouldCreateWithBuilder() {
        PemfvInfoAdicional info = new PemfvInfoAdicional();

        PemfvDataRequest request = PemfvDataRequest.builder()
                .infAdicional(info)
                .build();

        assertThat(request.getInfAdicional()).isEqualTo(info);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvInfoAdicionalTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PemfvInfoAdicional info = new PemfvInfoAdicional();

        info.setNumper("12345678");
        info.setCanalVenta("60");
        info.setAutorizoEnvioInformacion(true);

        assertThat(info.getNumper()).isEqualTo("12345678");
        assertThat(info.getCanalVenta()).isEqualTo("60");
        assertThat(info.isAutorizoEnvioInformacion()).isTrue();
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        PemfvInfoAdicional info = new PemfvInfoAdicional("12345678", "60", true);

        assertThat(info.getNumper()).isEqualTo("12345678");
        assertThat(info.getCanalVenta()).isEqualTo("60");
        assertThat(info.isAutorizoEnvioInformacion()).isTrue();
    }

    @Test
    void shouldCreateWithBuilder() {
        PemfvInfoAdicional info = PemfvInfoAdicional.builder()
                .numper("12345678")
                .canalVenta("60")
                .autorizoEnvioInformacion(true)
                .build();

        assertThat(info.getNumper()).isEqualTo("12345678");
        assertThat(info.getCanalVenta()).isEqualTo("60");
        assertThat(info.isAutorizoEnvioInformacion()).isTrue();
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TrxHeader header = new TrxHeader();
        PemfvDataRequest data = new PemfvDataRequest();
        PemfvRequest request = new PemfvRequest();

        request.setCabecera(header);
        request.setData(data);

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        TrxHeader header = new TrxHeader();
        PemfvDataRequest data = new PemfvDataRequest();

        PemfvRequest request = new PemfvRequest(header, data);

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }

    @Test
    void shouldCreateWithBuilder() {
        TrxHeader header = new TrxHeader();
        PemfvDataRequest data = new PemfvDataRequest();

        PemfvRequest request = PemfvRequest.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertThat(request.getCabecera()).isEqualTo(header);
        assertThat(request.getData()).isEqualTo(data);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvDataResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PemfvPEMFV0AResponse pemfv0A = new PemfvPEMFV0AResponse();
        PemfvDataResponse response = new PemfvDataResponse();

        response.setPemfv0A(pemfv0A);

        assertThat(response.getPemfv0A()).isEqualTo(pemfv0A);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        PemfvPEMFV0AResponse pemfv0A = new PemfvPEMFV0AResponse();

        PemfvDataResponse response = new PemfvDataResponse(pemfv0A);

        assertThat(response.getPemfv0A()).isEqualTo(pemfv0A);
    }

    @Test
    void shouldCreateWithBuilder() {
        PemfvPEMFV0AResponse pemfv0A = new PemfvPEMFV0AResponse();

        PemfvDataResponse response = PemfvDataResponse.builder()
                .pemfv0A(pemfv0A)
                .build();

        assertThat(response.getPemfv0A()).isEqualTo(pemfv0A);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvPEMFV0AResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PemfvPEMFV0AResponse response = new PemfvPEMFV0AResponse();

        response.setOFICIAL("OFICIAL");
        response.setIDNOAPL("IDNOAPL");
        response.setHONORAR("HONORAR");
        response.setREPORTA("REPORTA");
        response.setSALARIO("SALARIO");
        response.setSECDOC("SECDOC");
        response.setSITCLN("SITCLN");
        response.setPRESERV("PRESERV");
        response.setNROIDT2("NROIDT2");
        response.setNROIDT3("NROIDT3");
        response.setPENSION("PENSION");
        response.setNROIDT1("NROIDT1");
        response.setACTIND("ACTIND");
        response.setNUMDOC("NUMDOC");
        response.setUNINEG("UNINEG");
        response.setARRIEND("ARRIEND");
        response.setCANVTA("CANVTA");
        response.setSUCURAL("SUCURAL");
        response.setIDCRECO("IDCRECO");
        response.setPAIRE01("PAIRE01");
        response.setAUTCOME("AUTCOME");
        response.setPAIRE02("PAIRE02");
        response.setPAIRE03("PAIRE03");
        response.setIDCRS("IDCRS");
        response.setDONHERE("DONHERE");
        response.setIDPREPU("IDPREPU");
        response.setIDFATCA("IDFATCA");
        response.setOFOTRO("OFOTRO");
        response.setNUMPER("NUMPER");
        response.setMESADA("MESADA");
        response.setTIPDOC("TIPDOC");
        response.setFECCLN("FECCLN");
        response.setIDPPEXP("IDPPEXP");
        response.setIDPEXPO("IDPEXPO");

        assertThat(response.getOFICIAL()).isEqualTo("OFICIAL");
        assertThat(response.getIDNOAPL()).isEqualTo("IDNOAPL");
        assertThat(response.getHONORAR()).isEqualTo("HONORAR");
        assertThat(response.getREPORTA()).isEqualTo("REPORTA");
        assertThat(response.getSALARIO()).isEqualTo("SALARIO");
        assertThat(response.getSECDOC()).isEqualTo("SECDOC");
        assertThat(response.getSITCLN()).isEqualTo("SITCLN");
        assertThat(response.getPRESERV()).isEqualTo("PRESERV");
        assertThat(response.getNROIDT2()).isEqualTo("NROIDT2");
        assertThat(response.getNROIDT3()).isEqualTo("NROIDT3");
        assertThat(response.getPENSION()).isEqualTo("PENSION");
        assertThat(response.getNROIDT1()).isEqualTo("NROIDT1");
        assertThat(response.getACTIND()).isEqualTo("ACTIND");
        assertThat(response.getNUMDOC()).isEqualTo("NUMDOC");
        assertThat(response.getUNINEG()).isEqualTo("UNINEG");
        assertThat(response.getARRIEND()).isEqualTo("ARRIEND");
        assertThat(response.getCANVTA()).isEqualTo("CANVTA");
        assertThat(response.getSUCURAL()).isEqualTo("SUCURAL");
        assertThat(response.getIDCRECO()).isEqualTo("IDCRECO");
        assertThat(response.getPAIRE01()).isEqualTo("PAIRE01");
        assertThat(response.getAUTCOME()).isEqualTo("AUTCOME");
        assertThat(response.getPAIRE02()).isEqualTo("PAIRE02");
        assertThat(response.getPAIRE03()).isEqualTo("PAIRE03");
        assertThat(response.getIDCRS()).isEqualTo("IDCRS");
        assertThat(response.getDONHERE()).isEqualTo("DONHERE");
        assertThat(response.getIDPREPU()).isEqualTo("IDPREPU");
        assertThat(response.getIDFATCA()).isEqualTo("IDFATCA");
        assertThat(response.getOFOTRO()).isEqualTo("OFOTRO");
        assertThat(response.getNUMPER()).isEqualTo("NUMPER");
        assertThat(response.getMESADA()).isEqualTo("MESADA");
        assertThat(response.getTIPDOC()).isEqualTo("TIPDOC");
        assertThat(response.getFECCLN()).isEqualTo("FECCLN");
        assertThat(response.getIDPPEXP()).isEqualTo("IDPPEXP");
        assertThat(response.getIDPEXPO()).isEqualTo("IDPEXPO");
    }

    @Test
    void shouldCreateWithBuilder() {
        PemfvPEMFV0AResponse response = PemfvPEMFV0AResponse.builder()
                .OFICIAL("OFICIAL")
                .IDNOAPL("IDNOAPL")
                .HONORAR("HONORAR")
                .REPORTA("REPORTA")
                .SALARIO("SALARIO")
                .SECDOC("SECDOC")
                .SITCLN("SITCLN")
                .PRESERV("PRESERV")
                .NROIDT2("NROIDT2")
                .NROIDT3("NROIDT3")
                .PENSION("PENSION")
                .NROIDT1("NROIDT1")
                .ACTIND("ACTIND")
                .NUMDOC("NUMDOC")
                .UNINEG("UNINEG")
                .ARRIEND("ARRIEND")
                .CANVTA("CANVTA")
                .SUCURAL("SUCURAL")
                .IDCRECO("IDCRECO")
                .PAIRE01("PAIRE01")
                .AUTCOME("AUTCOME")
                .PAIRE02("PAIRE02")
                .PAIRE03("PAIRE03")
                .IDCRS("IDCRS")
                .DONHERE("DONHERE")
                .IDPREPU("IDPREPU")
                .IDFATCA("IDFATCA")
                .OFOTRO("OFOTRO")
                .NUMPER("NUMPER")
                .MESADA("MESADA")
                .TIPDOC("TIPDOC")
                .FECCLN("FECCLN")
                .IDPPEXP("IDPPEXP")
                .IDPEXPO("IDPEXPO")
                .build();

        assertThat(response.getOFICIAL()).isEqualTo("OFICIAL");
        assertThat(response.getIDPEXPO()).isEqualTo("IDPEXPO");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PemfvResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        PemfvDataResponse data = new PemfvDataResponse();
        TrxHeader cabecera = new TrxHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of();
        Object conexion = new Object();
        PemfvResponse response = new PemfvResponse();

        response.setData(data);
        response.setCabecera(cabecera);
        response.setAutorizacion(autorizacion);
        response.setPaginacion(paginacion);
        response.setAvisos(avisos);
        response.setErrores(errores);
        response.setConexion(conexion);
        response.setOk(true);

        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getCabecera()).isEqualTo(cabecera);
        assertThat(response.getAutorizacion()).isEqualTo(autorizacion);
        assertThat(response.getPaginacion()).isEqualTo(paginacion);
        assertThat(response.getAvisos()).isEqualTo(avisos);
        assertThat(response.getErrores()).isEqualTo(errores);
        assertThat(response.getConexion()).isEqualTo(conexion);
        assertThat(response.getOk()).isTrue();
    }

    @Test
    void shouldCreateWithBuilder() {
        PemfvDataResponse data = new PemfvDataResponse();
        TrxHeader cabecera = new TrxHeader();
        List<Object> avisos = List.of("aviso");
        List<ErrorTrxDTO> errores = List.of();

        PemfvResponse response = PemfvResponse.builder()
                .data(data)
                .cabecera(cabecera)
                .autorizacion("auth")
                .paginacion("page")
                .avisos(avisos)
                .errores(errores)
                .conexion("connection")
                .ok(true)
                .build();

        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getCabecera()).isEqualTo(cabecera);
        assertThat(response.getAvisos()).isEqualTo(avisos);
        assertThat(response.getErrores()).isEqualTo(errores);
        assertThat(response.getOk()).isTrue();
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiEntryTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ApiEntry apiEntry = new ApiEntry();

        apiEntry.setIntegrationType("sanba");
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setHttps(true);
        apiEntry.setEndpoint("/service");
        apiEntry.setTimeOutConn(1000);
        apiEntry.setTimeOutRead(2000);

        assertThat(apiEntry.getIntegrationType()).isEqualTo("sanba");
        assertThat(apiEntry.getHost()).isEqualTo("localhost");
        assertThat(apiEntry.getPort()).isEqualTo("8080");
        assertThat(apiEntry.isHttps()).isTrue();
        assertThat(apiEntry.getEndpoint()).isEqualTo("/service");
        assertThat(apiEntry.getTimeOutConn()).isEqualTo(1000);
        assertThat(apiEntry.getTimeOutRead()).isEqualTo(2000);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        ApiEntry apiEntry = new ApiEntry("sanba", "localhost", "8080", true, "/service", 1000, 2000);

        assertThat(apiEntry.getIntegrationType()).isEqualTo("sanba");
        assertThat(apiEntry.getHost()).isEqualTo("localhost");
        assertThat(apiEntry.getPort()).isEqualTo("8080");
        assertThat(apiEntry.isHttps()).isTrue();
        assertThat(apiEntry.getEndpoint()).isEqualTo("/service");
        assertThat(apiEntry.getTimeOutConn()).isEqualTo(1000);
        assertThat(apiEntry.getTimeOutRead()).isEqualTo(2000);
    }

    @Test
    void shouldValidateEqualsHashCodeAndToString() {
        ApiEntry apiEntry = new ApiEntry("sanba", "localhost", "8080", true, "/service", 1000, 2000);
        ApiEntry sameApiEntry = new ApiEntry("sanba", "localhost", "8080", true, "/service", 1000, 2000);

        assertThat(apiEntry)
                .isEqualTo(sameApiEntry)
                .hasSameHashCodeAs(sameApiEntry);
        assertThat(apiEntry.toString()).contains("integrationType", "host", "endpoint");
    }
}

