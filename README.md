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

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsentStatusInfoTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ConsentStatusInfo dto = new ConsentStatusInfo();
        dto.setStatusCode("S");

        assertThat(dto.getStatusCode()).isEqualTo("S");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        ConsentStatusInfo allArgs = new ConsentStatusInfo("S");
        ConsentStatusInfo builder = ConsentStatusInfo.builder().statusCode("N").build();

        assertThat(allArgs.getStatusCode()).isEqualTo("S");
        assertThat(builder.getStatusCode()).isEqualTo("N");
    }

    @Test
    void shouldValidateStatusCodeNotNull() {
        var violations = validator.validate(new ConsentStatusInfo(null));

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("{errors.general.null}");
    }

    @Test
    void shouldValidateEqualsHashCodeAndToString() {
        ConsentStatusInfo dto = new ConsentStatusInfo("S");
        ConsentStatusInfo same = new ConsentStatusInfo("S");

        assertThat(dto).isEqualTo(same).hasSameHashCodeAs(same);
        assertThat(dto.toString()).contains("statusCode");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PartiesConsentsRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ConsentStatusInfo statusInfo = new ConsentStatusInfo("S");
        PartiesConsentsRequest request = new PartiesConsentsRequest();

        request.setStatusInfo(statusInfo);

        assertThat(request.getStatusInfo()).isEqualTo(statusInfo);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        ConsentStatusInfo statusInfo = new ConsentStatusInfo("S");

        PartiesConsentsRequest allArgs = new PartiesConsentsRequest(statusInfo);
        PartiesConsentsRequest builder = PartiesConsentsRequest.builder().statusInfo(statusInfo).build();

        assertThat(allArgs.getStatusInfo()).isEqualTo(statusInfo);
        assertThat(builder.getStatusInfo()).isEqualTo(statusInfo);
    }

    @Test
    void shouldValidateStatusInfoNotNull() {
        var violations = validator.validate(new PartiesConsentsRequest(null));

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("{errors.general.null}");
    }

    @Test
    void shouldCascadeValidationToStatusCode() {
        var violations = validator.validate(new PartiesConsentsRequest(new ConsentStatusInfo(null)));

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("statusInfo.statusCode");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountRangeRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        AmountRangeRequest request = new AmountRangeRequest();

        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");
        request.setProductId("04");

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getXSantanderClientId()).isEqualTo("client-id");
        assertThat(request.getProductId()).isEqualTo("04");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        AmountRangeRequest allArgs = new AmountRangeRequest("Bearer token", "client-id", "04");
        AmountRangeRequest builder = AmountRangeRequest.builder()
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .productId("04")
                .build();

        assertThat(allArgs).isEqualTo(builder);
        assertThat(builder.toString()).contains("authorization", "xSantanderClientId", "productId");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountRangeResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        MaxAndMinAmountDto minimum = new MaxAndMinAmountDto("1000,00", "COP");
        MaxAndMinAmountDto maximum = new MaxAndMinAmountDto("9999,00", "COP");
        AmountRangeResponse response = new AmountRangeResponse();

        response.setMinimunAmount(minimum);
        response.setMaximumAmount(maximum);

        assertThat(response.getMinimunAmount()).isEqualTo(minimum);
        assertThat(response.getMaximumAmount()).isEqualTo(maximum);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        MaxAndMinAmountDto minimum = new MaxAndMinAmountDto("1000,00", "COP");
        MaxAndMinAmountDto maximum = new MaxAndMinAmountDto("9999,00", "COP");

        AmountRangeResponse allArgs = new AmountRangeResponse(minimum, maximum);
        AmountRangeResponse builder = AmountRangeResponse.builder()
                .minimunAmount(minimum)
                .maximumAmount(maximum)
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountRangeResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        MaxAndMinAmountDto minimum = new MaxAndMinAmountDto("1000,00", "COP");
        MaxAndMinAmountDto maximum = new MaxAndMinAmountDto("9999,00", "COP");
        AmountRangeResponse response = new AmountRangeResponse();

        response.setMinimunAmount(minimum);
        response.setMaximumAmount(maximum);

        assertThat(response.getMinimunAmount()).isEqualTo(minimum);
        assertThat(response.getMaximumAmount()).isEqualTo(maximum);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        MaxAndMinAmountDto minimum = new MaxAndMinAmountDto("1000,00", "COP");
        MaxAndMinAmountDto maximum = new MaxAndMinAmountDto("9999,00", "COP");

        AmountRangeResponse allArgs = new AmountRangeResponse(minimum, maximum);
        AmountRangeResponse builder = AmountRangeResponse.builder()
                .minimunAmount(minimum)
                .maximumAmount(maximum)
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermDepositParametersDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TermDepositParametersDTO dto = new TermDepositParametersDTO();

        dto.setCode("90");
        dto.setContent("content");
        dto.setDescription("description");

        assertThat(dto.getCode()).isEqualTo("90");
        assertThat(dto.getContent()).isEqualTo("content");
        assertThat(dto.getDescription()).isEqualTo("description");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        TermDepositParametersDTO allArgs = new TermDepositParametersDTO("90", "content", "description");
        TermDepositParametersDTO builder = TermDepositParametersDTO.builder()
                .code("90")
                .content("content")
                .description("description")
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermDepositParametersRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TermDepositParametersRequest request = new TermDepositParametersRequest();

        request.setProductId("04");
        request.setAuthorization("Bearer token");
        request.setXSantanderClientId("client-id");

        assertThat(request.getProductId()).isEqualTo("04");
        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getXSantanderClientId()).isEqualTo("client-id");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        TermDepositParametersRequest allArgs = new TermDepositParametersRequest("04", "Bearer token", "client-id");
        TermDepositParametersRequest builder = TermDepositParametersRequest.builder()
                .productId("04")
                .authorization("Bearer token")
                .xSantanderClientId("client-id")
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TermDepositParametersResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        List<TermDepositParametersDTO> parameters = List.of(new TermDepositParametersDTO("90", "content", "description"));
        TermDepositParametersResponse response = new TermDepositParametersResponse();

        response.setParameters(parameters);

        assertThat(response.getParameters()).isEqualTo(parameters);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        List<TermDepositParametersDTO> parameters = List.of(new TermDepositParametersDTO("90", "content", "description"));

        TermDepositParametersResponse allArgs = new TermDepositParametersResponse(parameters);
        TermDepositParametersResponse builder = TermDepositParametersResponse.builder().parameters(parameters).build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermDepositTransactionRequestTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        TermDepositTransactionRequest request = new TermDepositTransactionRequest();

        request.setAuthorization("Bearer token");
        request.setClient_id("client-id");
        request.setDeposit_id("deposit-id");
        request.setPlacement_id("placement-id");
        request.setType_code(1);
        request.setCredit_debit_indicator("C");
        request.setStart_date("2026-01-01");
        request.setEnd_date("2026-12-31");
        request.setMinim_amount(100);
        request.setMaxim_amount(1000);
        request.setOffset("0");
        request.setLimit("10");

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getClient_id()).isEqualTo("client-id");
        assertThat(request.getDeposit_id()).isEqualTo("deposit-id");
        assertThat(request.getPlacement_id()).isEqualTo("placement-id");
        assertThat(request.getType_code()).isEqualTo(1);
        assertThat(request.getCredit_debit_indicator()).isEqualTo("C");
        assertThat(request.getStart_date()).isEqualTo("2026-01-01");
        assertThat(request.getEnd_date()).isEqualTo("2026-12-31");
        assertThat(request.getMinim_amount()).isEqualTo(100);
        assertThat(request.getMaxim_amount()).isEqualTo(1000);
        assertThat(request.getOffset()).isEqualTo("0");
        assertThat(request.getLimit()).isEqualTo("10");
    }

    @Test
    void shouldCreateWithBuilder() {
        TermDepositTransactionRequest request = TermDepositTransactionRequest.builder()
                .authorization("Bearer token")
                .client_id("client-id")
                .deposit_id("deposit-id")
                .placement_id("placement-id")
                .type_code(1)
                .credit_debit_indicator("C")
                .start_date("2026-01-01")
                .end_date("2026-12-31")
                .minim_amount(100)
                .maxim_amount(1000)
                .offset("0")
                .limit("10")
                .build();

        assertThat(request.getAuthorization()).isEqualTo("Bearer token");
        assertThat(request.getClient_id()).isEqualTo("client-id");
        assertThat(request.getLimit()).isEqualTo("10");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        AmountDTO dto = new AmountDTO();

        dto.setAmount("1000,00");
        dto.setCurrency("COP");

        assertThat(dto.getAmount()).isEqualTo("1000,00");
        assertThat(dto.getCurrency()).isEqualTo("COP");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        AmountDTO allArgs = new AmountDTO("1000,00", "COP");
        AmountDTO builder = AmountDTO.builder().amount("1000,00").currency("COP").build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceResultDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        AmountDTO amount = new AmountDTO("1000,00", "COP");
        BalanceResultDTO dto = new BalanceResultDTO();

        dto.setAmount(amount);
        dto.setCreditDebitIndicator("C");

        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getCreditDebitIndicator()).isEqualTo("C");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        AmountDTO amount = new AmountDTO("1000,00", "COP");

        BalanceResultDTO allArgs = new BalanceResultDTO(amount, "C");
        BalanceResultDTO builder = BalanceResultDTO.builder().amount(amount).creditDebitIndicator("C").build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceTypeDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        BalanceTypeDTO dto = new BalanceTypeDTO();

        dto.setTypeCode("1");
        dto.setTypeDescription("description");

        assertThat(dto.getTypeCode()).isEqualTo("1");
        assertThat(dto.getTypeDescription()).isEqualTo("description");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        BalanceTypeDTO allArgs = new BalanceTypeDTO("1", "description");
        BalanceTypeDTO builder = BalanceTypeDTO.builder().typeCode("1").typeDescription("description").build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HrefDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        HrefDTO dto = new HrefDTO();

        dto.setHref("/href");

        assertThat(dto.getHref()).isEqualTo("/href");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        HrefDTO allArgs = new HrefDTO("/href");
        HrefDTO builder = HrefDTO.builder().href("/href").build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinksDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        HrefDTO first = new HrefDTO("/first");
        HrefDTO prev = new HrefDTO("/prev");
        HrefDTO next = new HrefDTO("/next");
        LinksDTO dto = new LinksDTO();

        dto.set_first(first);
        dto.set_prev(prev);
        dto.set_next(next);

        assertThat(dto.get_first()).isEqualTo(first);
        assertThat(dto.get_prev()).isEqualTo(prev);
        assertThat(dto.get_next()).isEqualTo(next);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        HrefDTO first = new HrefDTO("/first");
        HrefDTO prev = new HrefDTO("/prev");
        HrefDTO next = new HrefDTO("/next");

        LinksDTO allArgs = new LinksDTO(first, prev, next);
        LinksDTO builder = LinksDTO.builder()._first(first)._prev(prev)._next(next).build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListTransactionDTOTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        AmountDTO amount = new AmountDTO("1000,00", "COP");
        ListTransactionDTO dto = new ListTransactionDTO();

        dto.setValueDate("2026-01-01");
        dto.setAmount(amount);
        dto.setDescription("description");

        assertThat(dto.getValueDate()).isEqualTo("2026-01-01");
        assertThat(dto.getAmount()).isEqualTo(amount);
        assertThat(dto.getDescription()).isEqualTo("description");
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        AmountDTO amount = new AmountDTO("1000,00", "COP");

        ListTransactionDTO allArgs = new ListTransactionDTO("2026-01-01", amount, "description");
        ListTransactionDTO builder = ListTransactionDTO.builder()
                .valueDate("2026-01-01")
                .amount(amount)
                .description("description")
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class TermDepositTransactionResponseTest {

    @Test
    void shouldCreateWithNoArgsConstructorAndSetters() {
        ArrayList<ListTransactionDTO> transactions = new ArrayList<>();
        transactions.add(new ListTransactionDTO());
        TermDepositTransactionResponse response = new TermDepositTransactionResponse();

        response.setListTransactions(transactions);

        assertThat(response.getListTransactions()).isEqualTo(transactions);
    }

    @Test
    void shouldCreateWithAllArgsConstructorAndBuilder() {
        ArrayList<ListTransactionDTO> transactions = new ArrayList<>();
        transactions.add(new ListTransactionDTO());

        TermDepositTransactionResponse allArgs = new TermDepositTransactionResponse(transactions);
        TermDepositTransactionResponse builder = TermDepositTransactionResponse.builder()
                .listTransactions(transactions)
                .build();

        assertThat(allArgs).isEqualTo(builder);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.ConsentStatusInfo;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class DataConsentManagementUtilsTest {

    @Mock RegexUtils regexUtils;
    @Mock ErrorService errorService;
    @Mock ProductDirectoryService productDirectoryService;
    @Mock TermDepositParametersService termDepositParametersService;
    @Mock BanksService banksService;

    @InjectMocks
    DataConsentManagementUtils utils;

    @Test
    void partiesConsentInputValidationShouldValidatePartyIdAndStatusCodeInOrder() {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("S"))
                .build();

        utils.partiesConsentInputValidation("12345678", request);

        InOrder inOrder = inOrder(errorService, regexUtils);
        inOrder.verify(errorService).isBlank("12345678", "party_id");
        inOrder.verify(errorService).isNull("12345678", "party_id");
        inOrder.verify(regexUtils).validateRegex("party_id_format", "12345678", "party_id");
        inOrder.verify(regexUtils).validateRegex("party_id_length", "12345678", "party_id");
        inOrder.verify(errorService).isBlank("S", "statusInfo.statusCode");
        inOrder.verify(regexUtils).validateRegex("status_code_format", "S", "statusInfo.statusCode");
        inOrder.verify(regexUtils).validateRegex("status_code_length", "S", "statusInfo.statusCode");
    }

    @Test
    void format15DigitNumberShouldFormatValues() {
        assertThat(DataConsentManagementUtils.format15DigitNumber(null)).isEqualTo("0,00");
        assertThat(DataConsentManagementUtils.format15DigitNumber("")).isEqualTo("0,00");
        assertThat(DataConsentManagementUtils.format15DigitNumber(" ")).isEqualTo("0,00");
        assertThat(DataConsentManagementUtils.format15DigitNumber("1")).isEqualTo("0,01");
        assertThat(DataConsentManagementUtils.format15DigitNumber("12")).isEqualTo("0,12");
        assertThat(DataConsentManagementUtils.format15DigitNumber("0000012345")).isEqualTo("123,45");
    }

    @Test
    void removeLeadingZerosShouldReturnExpectedValues() {
        assertThat(DataConsentManagementUtils.removeLeadingZeros(null)).isEqualTo("0");
        assertThat(DataConsentManagementUtils.removeLeadingZeros("")).isEqualTo("0");
        assertThat(DataConsentManagementUtils.removeLeadingZeros(" ")).isEqualTo("0");
        assertThat(DataConsentManagementUtils.removeLeadingZeros("000123")).isEqualTo("123");
        assertThat(DataConsentManagementUtils.removeLeadingZeros("0000")).isEqualTo("0");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class MovementConceptUtilsTest {

    @Test
    void shouldSetAndGetType() {
        MovementConceptUtils utils = new MovementConceptUtils();
        HashMap<String, String> type = new HashMap<>();
        type.put("001", "INTERESES");

        utils.setType(type);

        assertThat(utils.getType()).containsEntry("001", "INTERESES");
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();
        HashMap<String, String> type = new HashMap<>();
        type.put("party_id_format", "^[0-9]+$");
        type.put("party_id_format_error", "Invalid format (only numbers)");
        regexUtils.setType(type);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "DATA_CONSENT_MANAGEMENT");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "api-services-v5");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "error");
        ReflectionTestUtils.setField(regexUtils, "CODE", "P-F-9400");
    }

    @Test
    void validateRegexShouldNotThrowWhenValueMatches() {
        assertThatCode(() -> regexUtils.validateRegex("party_id_format", "12345", "party_id"))
                .doesNotThrowAnyException();
    }

    @Test
    void validateRegexShouldThrowServiceExceptionWhenValueDoesNotMatch() {
        assertThatThrownBy(() -> regexUtils.validateRegex("party_id_format", "ABC", "party_id"))
                .isInstanceOf(ServiceException.class);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ErrorServiceTest {

    private ErrorService errorService;

    @BeforeEach
    void setUp() {
        errorService = new ErrorService();
        errorService.setMsName("DATA_CONSENT_MANAGEMENT");
        errorService.setMsVersion("api-services-v5");
        errorService.setLevel("error");
        errorService.setFunctional("P-F");
        errorService.setTechnical("P-T");
        ReflectionTestUtils.setField(errorService, "BLANK_DATA", "Cannot be blank");
        ReflectionTestUtils.setField(errorService, "NULL_DATA", "Cannot be null");
    }

    @Test
    void serviceExceptionBuilderShouldBuildException() {
        ServiceException exception = errorService.serviceExceptionBuilder(
                HttpStatus.BAD_REQUEST,
                "'party_id': Cannot be blank",
                ErrorType.FUNCTIONAL
        );

        assertThat(exception.getCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getError().getCode()).isEqualTo("DATA_CONSENT_MANAGEMENT-P-F-9400");
        assertThat(exception.getError().getLevel()).isEqualTo("error");
        assertThat(exception.getError().getMessage()).isEqualTo("'party_id': Cannot be blank");
    }

    @Test
    void errorBuilderShouldBuildTechnicalError() {
        var error = errorService.errorBuilder(HttpStatus.CONFLICT, "Unhandled exception", ErrorType.TECHNICAL);

        assertThat(error.getCode()).isEqualTo("DATA_CONSENT_MANAGEMENT-P-T-9409");
        assertThat(error.getDescription()).isEqualTo("data_consent_management-api-services-v5: Unhandled exception");
    }

    @Test
    void isBlankShouldThrowOnlyWhenBlank() {
        assertThatThrownBy(() -> errorService.isBlank(" ", "party_id"))
                .isInstanceOf(ServiceException.class);
        assertThatCode(() -> errorService.isBlank("123", "party_id"))
                .doesNotThrowAnyException();
    }

    @Test
    void isNullShouldThrowOnlyWhenNull() {
        assertThatThrownBy(() -> errorService.isNull(null, "party_id"))
                .isInstanceOf(ServiceException.class);
        assertThatCode(() -> errorService.isNull("123", "party_id"))
                .doesNotThrowAnyException();
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorTypeTest {

    @Test
    void shouldContainExpectedValues() {
        assertThat(ErrorType.values()).containsExactly(ErrorType.FUNCTIONAL, ErrorType.TECHNICAL);
        assertThat(ErrorType.valueOf("FUNCTIONAL")).isEqualTo(ErrorType.FUNCTIONAL);
        assertThat(ErrorType.valueOf("TECHNICAL")).isEqualTo(ErrorType.TECHNICAL);
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.mappers;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49DataResponse;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.DataConsentManagementUtils;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.MovementConceptUtils;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TermDepositTransactionMappersTest {

    @Mock DataConsentManagementUtils dataConsentManagementUtils;
    @Mock RegexUtils regexUtils;
    @Mock ErrorService errorService;

    private MovementConceptUtils movementConceptUtils;
    private TermDepositTransactionMappers mapper;

    @BeforeEach
    void setUp() {
        movementConceptUtils = new MovementConceptUtils();
        HashMap<String, String> concepts = new HashMap<>();
        concepts.put("001", "INTERESES");
        movementConceptUtils.setType(concepts);
        mapper = new TermDepositTransactionMappers(dataConsentManagementUtils, regexUtils, errorService, movementConceptUtils);
    }

    @Test
    void transactionResponseShouldMapKnownConceptAndPositiveAmount() {
        TrxBP49Response trxResponse = buildTrxResponse("0000012345", "001");

        var response = mapper.transactionResponse(trxResponse);

        assertThat(response.getListTransactions()).hasSize(1);
        assertThat(response.getListTransactions().get(0).getAmount().getCurrency()).isEqualTo("COP");
        assertThat(response.getListTransactions().get(0).getAmount().getAmount()).isEqualTo("123,45");
        assertThat(response.getListTransactions().get(0).getValueDate()).isEqualTo("2026-01-01");
        assertThat(response.getListTransactions().get(0).getDescription()).isEqualTo("INTERESES");
    }

    @Test
    void transactionResponseShouldMapUnknownConceptAndNegativeAmount() {
        TrxBP49Response trxResponse = buildTrxResponse("-0000012345", "999");

        var response = mapper.transactionResponse(trxResponse);

        assertThat(response.getListTransactions()).hasSize(1);
        assertThat(response.getListTransactions().get(0).getAmount().getAmount()).isEqualTo("-123,45");
        assertThat(response.getListTransactions().get(0).getDescription()).isEqualTo("OTROS");
    }

    private TrxBP49Response buildTrxResponse(String valor, String concepto) {
        TrxBP49Data movement = TrxBP49Data.builder()
                .valor(valor)
                .concepto(concepto)
                .fecha("2026-01-01")
                .build();

        ArrayList<TrxBP49Data> movements = new ArrayList<>();
        movements.add(movement);

        return TrxBP49Response.builder()
                .data(TrxBP49DataResponse.builder().movimientos(movements).build())
                .build();
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.service.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response.PemfvResponse;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.ConsentStatusInfo;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.mappers.TermDepositTransactionMappers;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.DataConsentManagementUtils;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.RegexUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataConsentManagementServiceImplTest {

    @Mock TrxSanbaService trxSanbaService;
    @Mock DataConsentManagementUtils dataConsentManagementUtils;
    @Mock RegexUtils regexUtils;
    @Mock ErrorService errorService;
    @Mock TermDepositTransactionMappers transactionMappers;

    @InjectMocks
    DataConsentManagementServiceImpl service;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "PEMFV_SERVICE_ROUTE", "modificarMantencionPersonaNaturalInfAdicional");
    }

    @Test
    void getPartiesConsentShouldValidateAndCallPemfv() {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("S"))
                .build();
        when(trxSanbaService.trxPemfv(any(PemfvRequest.class))).thenReturn(new PemfvResponse());

        service.getPartiesConsent("12345678", request);

        verify(dataConsentManagementUtils).partiesConsentInputValidation("12345678", request);
        verify(trxSanbaService).trxPemfv(any(PemfvRequest.class));
    }

    @Test
    void trxPEMFVcallShouldBuildRequestWithAuthorizationTrueWhenStatusIsS() {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("S"))
                .build();
        PemfvResponse expected = new PemfvResponse();
        when(trxSanbaService.trxPemfv(any(PemfvRequest.class))).thenReturn(expected);

        PemfvResponse response = service.trxPEMFVcall("12345678", request);

        ArgumentCaptor<PemfvRequest> captor = ArgumentCaptor.forClass(PemfvRequest.class);
        verify(trxSanbaService).trxPemfv(captor.capture());

        assertThat(response).isEqualTo(expected);
        assertThat(captor.getValue().getData().getInfAdicional().getNumper()).isEqualTo("12345678");
        assertThat(captor.getValue().getData().getInfAdicional().getCanalVenta()).isEqualTo("ODS");
        assertThat(captor.getValue().getData().getInfAdicional().isAutorizoEnvioInformacion()).isTrue();
    }

    @Test
    void trxPEMFVcallShouldBuildRequestWithAuthorizationFalseWhenStatusIsN() {
        PartiesConsentsRequest request = PartiesConsentsRequest.builder()
                .statusInfo(new ConsentStatusInfo("N"))
                .build();
        when(trxSanbaService.trxPemfv(any(PemfvRequest.class))).thenReturn(new PemfvResponse());

        service.trxPEMFVcall("12345678", request);

        ArgumentCaptor<PemfvRequest> captor = ArgumentCaptor.forClass(PemfvRequest.class);
        verify(trxSanbaService).trxPemfv(captor.capture());

        assertThat(captor.getValue().getData().getInfAdicional().isAutorizoEnvioInformacion()).isFalse();
    }
}

