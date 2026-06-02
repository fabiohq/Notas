this.regexUtils = mock(RegexUtils.class);
errorService = mock(ErrorService.class);
productDirectoryService = mock(ProductDirectoryService.class);
termDepositParametersService = mock(TermDepositParametersService.class);
banksService = mock(BanksService.class);

utils = new TermDepositUtils(
        this.regexUtils,
        errorService,
        productDirectoryService,
        termDepositParametersService,
        banksService
);


@Test
void shouldCoverTermDepositsInputValidationOk() throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurpose("010");

    RequestTermDepositsDTO request =
            termRequest("940250", "001", "001", "1234", "CC", "001",
                    "123456789", "C", "010", "1000", "COP", "30");

    assertDoesNotThrow(() -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));

    verify(productDirectoryService, atLeastOnce()).amountRange(any());
    verify(banksService, atLeastOnce()).banksResponse(any());
    verify(termDepositParametersService, atLeastOnce()).termDepositParameters(any());
}

@Test
void shouldCoverValidateAmountRangeUnderAndOverLimit() throws Exception {
    mockAmountRange("500", "2000");

    RequestTermDepositsDTO under =
            termRequest("940250", "001", "001", "1234", "CC", "001",
                    "123456789", "C", "010", "100", "COP", "30");

    RequestTermDepositsDTO over =
            termRequest("940250", "001", "001", "1234", "CC", "001",
                    "123456789", "C", "010", "999999", "COP", "30");

    assertThrows(Exception.class,
            () -> utils.validateAmountRange(under, new AmountRangeRequest()));

    assertThrows(Exception.class,
            () -> utils.validateAmountRange(over, new AmountRangeRequest()));
}

@Test
void shouldCoverValidateBankCodeInvalid() throws Exception {
    mockBanks("999");

    RequestTermDepositsDTO request =
            termRequest("940250", "001", "001", "1234", "CC", "001",
                    "123456789", "C", "010", "1000", "COP", "30");

    assertThrows(Exception.class,
            () -> utils.validateBankCode(request, new BanksParametersRequest()));
}

@Test
void shouldCoverSettlementConceptCurrencyInvalidInsideTermDepositsInputValidation() throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurpose("010");

    RequestTermDepositsDTO request =
            termRequest("940250", "001", "001", "1234", "CC", "001",
                    "123456789", "C", "010", "1000", "COP", "30");

    Object economicData = get(request, "getEconomicData");
    List<?> settlements = (List<?>) get(economicData, "getSettlements");
    Object settlementConcept = get(settlements.get(0), "getSettlementConcept");
    Object amount = get(settlementConcept, "getAmount");

    set(amount, "setCurrency", "USD");

    assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

private void mockAmountRange(String minValue, String maxValue) {
    AmountRangeResponse response = new AmountRangeResponse();

    MaxAndMinAmountDto min = new MaxAndMinAmountDto();
    min.setAmount(minValue);

    MaxAndMinAmountDto max = new MaxAndMinAmountDto();
    max.setAmount(maxValue);

    response.setMinimumAmount(min);
    response.setMaximumAmount(max);

    when(productDirectoryService.amountRange(any())).thenReturn(response);
}

private void mockBanks(String bankId) {
    BanksParametersDTO bank = new BanksParametersDTO();
    bank.setBankId(bankId);
    bank.setBankName("BANK");

    BanksDTO banksDTO = new BanksDTO();
    banksDTO.setBanks(List.of(bank));

    when(banksService.banksResponse(any())).thenReturn(banksDTO);
}

private void mockPurpose(String purposeCode) {
    TermDepositParametersResponse response = mock(TermDepositParametersResponse.class, RETURNS_DEEP_STUBS);

    when(termDepositParametersService.termDepositParameters(any())).thenReturn(response);
}


private Object get(Object target, String getterName) throws Exception {
    Method method = target.getClass().getMethod(getterName);
    return method.invoke(target);
}

private void set(Object target, String setterName, Object value) throws Exception {
    Method setter = findSetter(target.getClass(), setterName);
    setter.invoke(target, value);
}

private Method findSetter(Class<?> clazz, String setterName) {
    for (Method method : clazz.getMethods()) {
        if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
            return method;
        }
    }
    throw new IllegalArgumentException(setterName);
}
