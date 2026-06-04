package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;

@ExtendWith(MockitoExtension.class)
@org.mockito.junit.jupiter.MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class TermDepositUtilsPrivateValidationTest {

	@Mock
	RegexUtils regexUtils;
	@Mock
	ErrorService errorService;
	@Mock
	ProductDirectoryService productDirectoryService;
	@Mock
	TermDepositParametersService termDepositParametersService;
	@Mock
	BanksService banksService;

	private TermDepositUtils utils;

	@BeforeEach
	void setUp() {
		utils = new TermDepositUtils(regexUtils, errorService, productDirectoryService, termDepositParametersService,
				banksService);

		ReflectionTestUtils.setField(utils, "bankId", "0065");
		ReflectionTestUtils.setField(utils, "centerId", "0100");
		ReflectionTestUtils.setField(utils, "productCode", "12");
		ReflectionTestUtils.setField(utils, "subproductCode", "001");
		ReflectionTestUtils.setField(utils, "validFrecuencies", new String[] { "30", "60", "90" });
		ReflectionTestUtils.setField(utils, "settlementCoditionCode", "CV");

//		org.mockito.Mockito.lenient().doNothing().when(regexUtils).validateRegex(
//				org.mockito.ArgumentMatchers.anyString(), org.mockito.ArgumentMatchers.anyString(),
//				org.mockito.ArgumentMatchers.anyString());

//		org.mockito.Mockito.lenient().doNothing().when(regexUtils).validateRegex(
//				org.mockito.ArgumentMatchers.anyString(), org.mockito.ArgumentMatchers.anyString(),
//				org.mockito.ArgumentMatchers.anyString());
//
//		org.mockito.Mockito.lenient().doNothing().when(errorService).isBlank(org.mockito.ArgumentMatchers.anyString(),
//				org.mockito.ArgumentMatchers.anyString());

		// **
		org.mockito.Mockito.lenient().when(errorService.getGeneral()).thenReturn(new java.util.HashMap<>());

		org.mockito.Mockito.lenient()
				.when(errorService.serviceExceptionBuilder(org.mockito.ArgumentMatchers.any(),
						org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any()))
				.thenReturn(new ServiceException(HttpStatus.BAD_REQUEST, "error"));

	}

	@Test
	void shouldCoverPrivateTermDepositInputValidations() throws Exception {
		Object request = validTermDepositRequest();

		invoke("bankValidationTermDepositInput", request);
		invoke("centerValidationTermDepositInput", request);
		invoke("productValidationTermDepositInput", request);
		invoke("subproductValidationTermDepositInput", request);
		invoke("accountIdTypeValidationTermDepositInput", request);
		invoke("nationalIdentificationValidationTermDepositInput", request);
		invoke("frecuencyValidationTermDepositInput", request);

		assertDoesNotThrow(() -> {
		});
	}

	@Test
	void shouldCoverBankCodePrivateValidation() throws Exception {
		Object request = validTermDepositRequest();

		com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO bankDto = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
		bankDto.setBankId("0065");

		com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO banksDto = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
		banksDto.setBanks(java.util.List.of(bankDto));

		org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any())).thenReturn(banksDto);

		invoke("bankCodeValidationTermDepositInput",
				new Class<?>[] { Class.forName(pkgTerm() + ".RequestTermDepositsDTO"), BanksParametersRequest.class },
				request, new BanksParametersRequest("auth", "client"));
	}

	@Test
	void shouldCoverSettlementConditionPrivateValidation() throws Exception {
		Method method = TermDepositUtils.class.getDeclaredMethod("settlementContionCodeInputValidation", String.class);
		method.setAccessible(true);

		method.invoke(utils, "C");
		method.invoke(utils, "X");
	}

	// ***
	@Test
	void shouldCoverCleanAndFormatNumberString() throws Exception {
		Method method = TermDepositUtils.class.getDeclaredMethod("cleanAndFormatNumberString", String.class);
		method.setAccessible(true);

		assertEquals("00012345", method.invoke(utils, "00012345"));
		assertEquals("000000", method.invoke(utils, "000000"));
		assertEquals("ABC", method.invoke(utils, "ABC"));
	}

	@Test
	void shouldCoverPurposeCodeValidation() throws Exception {
		Object parametersRequest = Class.forName(
				"com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest")
				.getDeclaredConstructor().newInstance();

		Method method = TermDepositUtils.class.getDeclaredMethod("purposeCodeValidation", parametersRequest.getClass(),
				String.class);
		method.setAccessible(true);

		assertThrows(Exception.class, () -> method.invoke(utils, parametersRequest, "001"));
	}

	@Test
	void shouldCoverGetSettlementsInputValidation() {
		com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest request = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest();

		request.setDeposit_id("12345678901234567890");
		request.setPlacement_id("12345-67890");

		assertDoesNotThrow(() -> utils.getSettlementsInputValidation(request));
	}

	@Test
	void shouldCoverTermDepositsInputValidation() {
		Object request = assertDoesNotThrow(() -> validTermDepositRequest());

		com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest parametersRequest = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest();

		com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest banksRequest = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest(
				"auth", "client");

		assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
				(com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
				parametersRequest, banksRequest));
	}

	@Test
	void shouldCoverBankValidationHappyPath() {
		var bank = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
		bank.setBankId("0065");

		var banks = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
		banks.setBanks(java.util.List.of(bank));

		org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any())).thenReturn(banks);

		assertEquals("0065", utils.bankValidation(new BanksParametersRequest("auth", "client"), "0065"));
	}

	@Test
	void shouldCoverPurposeCodeValidationHappyPath() {
		var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
		param.setCode("01");

		var response = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse();
		response.setParameters(java.util.List.of(param));

		org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(org.mockito.ArgumentMatchers.any()))
				.thenReturn(response);

		assertEquals("01", utils.purposeCodeValidation(new TermDepositParametersRequest(), "0100"));
	}

	@Test
	void shouldCoverTermDepositsInputValidationHappyPath() {
		Object request = assertDoesNotThrow(() -> validTermDepositRequest());

		var bank = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
		bank.setBankId("0065");

		var banks = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
		banks.setBanks(java.util.List.of(bank));

		org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any())).thenReturn(banks);

		var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
		param.setCode("00");

		var params = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse();
		params.setParameters(java.util.List.of(param));

		org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(org.mockito.ArgumentMatchers.any()))
				.thenReturn(params);

		assertDoesNotThrow(() -> utils.termDepositsInputValidation(
				(com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
				new TermDepositParametersRequest(), new BanksParametersRequest("auth", "client")));
	}

	// ***
	private void invoke(String methodName, Object request) throws Exception {
		Method method = TermDepositUtils.class.getDeclaredMethod(methodName,
				Class.forName(pkgTerm() + ".RequestTermDepositsDTO"));
		method.setAccessible(true);
		method.invoke(utils, request);
	}

	private void invoke(String methodName, Class<?>[] types, Object... args) throws Exception {
		Method method = TermDepositUtils.class.getDeclaredMethod(methodName, types);
		method.setAccessible(true);
		method.invoke(utils, args);
	}

	private Object validTermDepositRequest() throws Exception {
		Object center = obj(pkgTerm() + ".TermDepositCenterDTO");
		set(center, "setCenterId", "0100");

		Object bank = obj(pkgTerm() + ".TermDepositBankRequestDTO");
		set(bank, "setBankId", "0065");
		set(bank, "setCenter", center);

		Object subproduct = obj(pkgTerm() + ".TermDepositSubproductDTO");
		set(subproduct, "setSubproductId", "001");

		Object product = obj(pkgTerm() + ".TermDepositProductDTO");
		set(product, "setProductCode", "12");
		set(product, "setSubproduct", subproduct);

		Object account = obj(pkgTerm() + ".TermDepositAccountDTO");
		set(account, "setNationalIdentification", "123456");

		Object destinationFunds = obj(pkgTerm() + ".TermDepositDestinationFundsDTO");
		set(destinationFunds, "setAccountIdType", "CC");
		set(destinationFunds, "setBankcode", "0065");
		set(destinationFunds, "setAccount", account);

		Object periodicity = obj(pkgTerm() + ".TermDepositPeriodicityDTO");
		set(periodicity, "setFrequency", "30");

		Object settlementCondition = obj(pkgTerm() + ".TermDepositSettlementConditionDTO");
		set(settlementCondition, "setCode", "C");

		Object placement = obj(
				"com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits.TermDepositPlacementDTO");
		set(placement, "setDestinationFunds", destinationFunds);
		set(placement, "setPeriodicity", periodicity);
		set(placement, "setSettlementCondition", settlementCondition);
		set(placement, "setPurposeCode", "001");

		Object deposit = obj(pkgTerm() + ".TermDepositDepositDto");
		set(deposit, "setPlacement", placement);

		Object amount = obj(pkgTerm() + ".TermDepositAmountDTO");
		set(amount, "setAmount", "1000");
		set(amount, "setCurrency", "COP");

		Object concept = obj(pkgTerm() + ".TermDepositSettlementConceptDTO");
		set(concept, "setCode", "BGMF");
		set(concept, "setTypeCode", "C");
		set(concept, "setRate", "1");
		set(concept, "setAmount", amount);

		Object settlement = obj(pkgTerm() + ".TermDepositSettlementsDTO");
		set(settlement, "setSettlementConcept", concept);

		Object economicData = obj(pkgTerm() + ".TermDepositEconomicDataDTO");
		set(economicData, "setInitialTotalInvested", amount);
		set(economicData, "setSettlements", List.of(settlement));

		Object request = obj(pkgTerm() + ".RequestTermDepositsDTO");
		set(request, "setBank", bank);
		set(request, "setProduct", product);
		set(request, "setDeposit", deposit);
		set(request, "setEconomicData", economicData);

		return request;
	}

	private Object obj(String className) throws Exception {
		return Class.forName(className).getDeclaredConstructor().newInstance();
	}

	private void set(Object target, String methodName, Object value) throws Exception {
		Method method = null;
		for (Method m : target.getClass().getMethods()) {
			if (m.getName().equals(methodName) && m.getParameterCount() == 1) {
				method = m;
				break;
			}
		}
		if (method == null) {
			throw new NoSuchMethodException(methodName);
		}
		method.invoke(target, value);
	}

	private String pkgTerm() {
		return "com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits";
	}

	@Test
	void shouldCoverValidateEconomicDataRetfAndBgmf() throws Exception {
		Object retf = buildSettlement("RETF", "D", "1", "1000", "COP");
		Object bgmf = buildSettlement("BGMF", "C", "1", "1000", "COP");

		invokePrivate("validateEconomicDataRetfTermDepositInput",
				new Class<?>[] { Class.forName(pkgTerm() + ".TermDepositSettlementsDTO") }, retf);

		invokePrivate("validateEconomicDataBgmf",
				new Class<?>[] { Class.forName(pkgTerm() + ".TermDepositSettlementsDTO") }, bgmf);
	}

	@Test
	void shouldCoverSimulatePlacementInputValidation() {
		var product = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.Product();
		product.setProductCode("12");

		var subproduct = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.SubproductRequestDTO();
		subproduct.setSubproductId("001");
		product.setSubproduct(subproduct);

		var amount = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.AmountRequestDTO();
		amount.setAmount("1000");

		var periodicity = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.RequestDTO();
		periodicity.setFrequency("30");
		periodicity.setPeriodTypeCode("D");

		var request = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.RequestSimulatePlacementDTO();
		request.setProduct(product);
		request.setAmount(amount);
		request.setPeriodicity(periodicity);
		request.setSettlementConditionCode("C");

		var amountRange = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeRequest();
		amountRange.setAuthorization("auth");
		amountRange.setxSantanderClientId("client");
		amountRange.setProductId("12");

		var min = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.MaxAndMinAmountDto();
		min.setAmount("100");
		min.setCurrency(null);

		var max = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.MaxAndMinAmountDto();
		max.setAmount("10000");
		max.setCurrency(null);

		var rangeResponse = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeResponse();

		rangeResponse.setMinimunAmount(min);
		rangeResponse.setMaximumAmount(max);

		org.mockito.Mockito.when(productDirectoryService.amountRange(org.mockito.ArgumentMatchers.any()))
				.thenReturn(rangeResponse);

		assertDoesNotThrow(() -> utils.simulatePlacementInputValidation(request, amountRange));
	}

	private Object buildSettlement(String code, String typeCode, String rate, String amountValue, String currency)
			throws Exception {

		Object amount = obj(pkgTerm() + ".TermDepositAmountDTO");
		set(amount, "setAmount", amountValue);
		set(amount, "setCurrency", currency);

		Object concept = obj(pkgTerm() + ".TermDepositSettlementConceptDTO");
		set(concept, "setCode", code);
		set(concept, "setTypeCode", typeCode);
		set(concept, "setRate", rate);
		set(concept, "setAmount", amount);

		Object settlement = obj(pkgTerm() + ".TermDepositSettlementsDTO");
		set(settlement, "setSettlementConcept", concept);

		return settlement;
	}

	private void invokePrivate(String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
		Method method = TermDepositUtils.class.getDeclaredMethod(methodName, parameterTypes);
		method.setAccessible(true);
		method.invoke(utils, args);
	}

	@Test
	void shouldCoverLambda0WithAllSettlementConcepts() throws Exception {
		Object request = validTermDepositRequest();

		Object bgmf = buildSettlement("BGMF", "C", "1", "1000", "COP");
		Object retf = buildSettlement("RETF", "D", "1", "1000", "COP");
		Object other = buildSettlement("OTRO", "C", "1", "1000", "COP");

		Object economicData = obj(pkgTerm() + ".TermDepositEconomicDataDTO");

		Object initialAmount = obj(pkgTerm() + ".TermDepositAmountDTO");
		set(initialAmount, "setAmount", "1000");
		set(initialAmount, "setCurrency", "COP");

		set(economicData, "setInitialTotalInvested", initialAmount);
		set(economicData, "setSettlements", java.util.List.of(bgmf, retf, other));

		set(request, "setEconomicData", economicData);

		assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
				(com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
				new TermDepositParametersRequest(), new BanksParametersRequest("auth", "client")));
	}
}

