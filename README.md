
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.MaxAndMinAmountDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.AmountRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.Product;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.RequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.SubproductRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TermDepositUtilsTest {

    @Mock
    private RegexUtils regexUtils;

    @Mock
    private ErrorService errorService;

    @Mock
    private ProductDirectoryService productDirectoryService;

    @Mock
    private TermDepositParametersService termDepositParametersService;

    @Mock
    private BanksService banksService;

    private TermDepositUtils utils;

    @BeforeEach
    void setUp() {
        utils = new TermDepositUtils(
                regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

        ReflectionTestUtils.setField(utils, "productCode", "60");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "validFrecuencies", new String[]{"30", "60", "90"});
        ReflectionTestUtils.setField(utils, "validSettlements", new String[]{"BGMF", "RETF", "ITEA"});
        ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "VENC");
        ReflectionTestUtils.setField(utils, "bankId", "0065");
        ReflectionTestUtils.setField(utils, "centerId", "0100");

        HashMap<String, String> general = new HashMap<>();
        general.put("productcode_not_found", "product not found");
        general.put("subproductid_not_found", "subproduct not found");
        general.put("amount_under_limit", "amount under limit");
        general.put("amount_over_limit", "amount over limit");
        general.put("purposeCode_not_found", "purpose not found");
        general.put("bankId_not_found", "bank not found");
        general.put("settlementconditioncode_not_found", "settlement condition not found");

        when(errorService.getGeneral()).thenReturn(general);
    }

    @Test
    void shouldCoverCleanAndFormatNumberString() {
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString(" +123.456 "));
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("-123.456"));
    }

    @Test
    void shouldCoverPadLeftWithZeros() {
        assertEquals("000123", TermDepositUtils.padLeftWithZeros("123", 6));
        assertEquals("123456", TermDepositUtils.padLeftWithZeros("123456", 6));
        assertEquals("1234567", TermDepositUtils.padLeftWithZeros("1234567", 6));
    }

    @Test
    void shouldCoverValidateAccountIdType() {
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("XX"));
    }

    @Test
    void shouldCoverValidateSettlementConcept() {
        assertTrue(TermDepositUtils.validateSettlementConcept("BGMF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("RETF"));
        assertTrue(TermDepositUtils.validateSettlementConcept("ITEA"));
        assertFalse(TermDepositUtils.validateSettlementConcept("OTRO"));
    }

    @Test
    void shouldCoverValidateSettlementConceptTypeCode() {
        assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
        assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldCoverParseDouble() {
        assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("-1.234,56"));
    }

    @Test
    void shouldCoverToLinea2Decimal() {
        assertEquals("1234567890123,45", TermDepositUtils.toLinea2Decimal("123456789012345"));
    }

    @Test
    void shouldCoverFormat15DigitNumber() {
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber("   "));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("000000000012345"));
    }

    @Test
    void shouldCoverPurposeCodeValidationWhenExists() {
        TermDepositParametersRequest request = new TermDepositParametersRequest();

        TermDepositParametersDTO parameter =
                new TermDepositParametersDTO("12", "content", "description");

        TermDepositParametersResponse response =
                new TermDepositParametersResponse(List.of(parameter));

        when(termDepositParametersService.termDepositParameters(request))
                .thenReturn(response);

        assertEquals("12", utils.purposeCodeValidation(request, "129999"));
    }

    @Test
    void shouldThrowWhenPurposeCodeDoesNotExist() {
        TermDepositParametersRequest request = new TermDepositParametersRequest();

        TermDepositParametersResponse response =
                new TermDepositParametersResponse(List.of());

        ServiceException exception =
                new ServiceException(HttpStatus.NOT_FOUND, null);

        when(termDepositParametersService.termDepositParameters(request))
                .thenReturn(response);

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.NOT_FOUND),
                any(),
                eq(ErrorType.FUNCTIONAL)
        )).thenReturn(exception);

        assertThrows(ServiceException.class,
                () -> utils.purposeCodeValidation(request, "129999"));
    }

    @Test
    void shouldCoverBankValidationWhenExists() {
        BanksParametersRequest request = new BanksParametersRequest();

        BanksParametersDTO bank =
                BanksParametersDTO.builder()
                        .bankId("0065")
                        .bankName("Santander")
                        .build();

        BanksDTO banksDTO =
                BanksDTO.builder()
                        .banks(List.of(bank))
                        .build();

        when(banksService.banksResponse(request)).thenReturn(banksDTO);

        assertEquals("0065", utils.bankValidation(request, "0065"));
    }

    @Test
    void shouldThrowWhenBankDoesNotExist() {
        BanksParametersRequest request = new BanksParametersRequest();

        BanksDTO banksDTO =
                BanksDTO.builder()
                        .banks(List.of())
                        .build();

        ServiceException exception =
                new ServiceException(HttpStatus.NOT_FOUND, null);

        when(banksService.banksResponse(request)).thenReturn(banksDTO);

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.NOT_FOUND),
                any(),
                eq(ErrorType.FUNCTIONAL)
        )).thenReturn(exception);

        assertThrows(ServiceException.class,
                () -> utils.bankValidation(request, "9999"));
    }

    @Test
    void shouldCoverSimulatePlacementInputValidationHappyPath() {
        RequestSimulatePlacementDTO request = buildValidSimulatePlacementRequest();
        AmountRangeRequest amountRangeRequest = new AmountRangeRequest();

        AmountRangeResponse amountRangeResponse =
                new AmountRangeResponse(
                        new MaxAndMinAmountDto("1000", "COP"),
                        new MaxAndMinAmountDto("9999999", "COP")
                );

        when(productDirectoryService.amountRange(amountRangeRequest))
                .thenReturn(amountRangeResponse);

        assertDoesNotThrow(() ->
                utils.simulatePlacementInputValidation(request, amountRangeRequest)
        );

        assertEquals("60001", amountRangeRequest.getProductId());

        verify(errorService, atLeastOnce()).isBlank(anyString(), anyString());
        verify(regexUtils, atLeastOnce()).validateRegex(anyString(), anyString(), anyString());
        verify(productDirectoryService).amountRange(amountRangeRequest);
    }

    @Test
    void shouldThrowWhenProductCodeIsInvalidInSimulatePlacement() {
        RequestSimulatePlacementDTO request = buildValidSimulatePlacementRequest();
        request.getProduct().setProductCode("99");

        ServiceException exception =
                new ServiceException(HttpStatus.NOT_FOUND, null);

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.NOT_FOUND),
                any(),
                eq(ErrorType.FUNCTIONAL)
        )).thenReturn(exception);

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldThrowWhenSubproductCodeIsInvalidInSimulatePlacement() {
        RequestSimulatePlacementDTO request = buildValidSimulatePlacementRequest();
        request.getProduct().getSubproduct().setSubproductId("999");

        ServiceException exception =
                new ServiceException(HttpStatus.NOT_FOUND, null);

        when(errorService.serviceExceptionBuilder(
                eq(HttpStatus.NOT_FOUND),
                any(),
                eq(ErrorType.FUNCTIONAL)
        )).thenReturn(exception);

        assertThrows(ServiceException.class,
                () -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    private RequestSimulatePlacementDTO buildValidSimulatePlacementRequest() {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId("001");

        Product product = new Product();
        product.setProductCode("60");
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount("5000");

        RequestDTO periodicity = new RequestDTO();
        periodicity.setFrequency("30");
        periodicity.setPeriodTypeCode("D");

        RequestSimulatePlacementDTO request = new RequestSimulatePlacementDTO();
        request.setProduct(product);
        request.setAmount(amount);
        request.setPeriodicity(periodicity);
        request.setSettlementConditionCode("VENC");

        return request;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MovementConceptUtilsTest {

    @Test
    void shouldCoverDataMethods() {
        MovementConceptUtils one = new MovementConceptUtils();
        MovementConceptUtils two = new MovementConceptUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("INTP", "INTERESES");

        one.setType(type);
        two.setType(type);

        assertEquals(type, one.getType());
        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.toString().contains("type"));
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    private RegexUtils regexUtils;

    @BeforeEach
    void setUp() {
        regexUtils = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("only_numbers", "^[0-9]+$");
        type.put("only_numbers_error", "Only numbers are allowed");
        type.put("letters", "^[A-Z]+$");

        regexUtils.setType(type);

        ReflectionTestUtils.setField(regexUtils, "MS_NAME", "BSN049");
        ReflectionTestUtils.setField(regexUtils, "MS_VERSION", "1.0.0");
        ReflectionTestUtils.setField(regexUtils, "LEVEL", "ERROR");
        ReflectionTestUtils.setField(regexUtils, "CODE", "400");
    }

    @Test
    void shouldPassWhenRegexMatches() {
        assertDoesNotThrow(() ->
                regexUtils.validateRegex("only_numbers", "123456", "deposit_id")
        );
    }

    @Test
    void shouldThrowWithCustomMessageWhenRegexDoesNotMatch() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex("only_numbers", "ABC", "deposit_id"));
    }

    @Test
    void shouldThrowWithDefaultMessageWhenErrorMessageDoesNotExist() {
        assertThrows(ServiceException.class,
                () -> regexUtils.validateRegex("letters", "123", "field"));
    }

    @Test
    void shouldCoverDataMethods() {
        RegexUtils other = new RegexUtils();

        HashMap<String, String> type = new HashMap<>();
        type.put("key", "value");

        other.setType(type);

        assertEquals(type, other.getType());
        assertTrue(other.toString().contains("type"));
    }
}
