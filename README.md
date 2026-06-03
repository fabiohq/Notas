@Test
void shouldCoverTermDepositsInputValidationFullSettlementsOk() throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurposeOk();

    RequestTermDepositsDTO request = requestWithThreeSettlements(
            "940250", "001", "001", "1234", "CC", "001",
            "123456789", "C", "010", "1000", "COP", "30"
    );

    assertDoesNotThrow(() -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

@Test
void shouldThrowWhenTermDepositFrequencyIsInvalid() throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurposeOk();

    RequestTermDepositsDTO request = requestWithThreeSettlements(
            "940250", "001", "001", "1234", "CC", "001",
            "123456789", "C", "010", "1000", "COP", "15"
    );

    assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

@Test
void shouldThrowWhenFirstSettlementConceptIsInvalid() throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurposeOk();

    RequestTermDepositsDTO request = requestWithThreeSettlements(
            "940250", "001", "001", "1234", "CC", "001",
            "123456789", "C", "010", "1000", "COP", "30"
    );

    changeSettlementCode(request, 0, "BAD");

    assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

@Test
void shouldThrowWhenBgmfCurrencyIsNotCop() throws Exception {
    assertInvalidSettlementCurrency("BGMF");
}

@Test
void shouldThrowWhenRetfCurrencyIsNotCop() throws Exception {
    assertInvalidSettlementCurrency("RETF");
}

@Test
void shouldThrowWhenIteaCurrencyIsNotCop() throws Exception {
    assertInvalidSettlementCurrency("ITEA");
}

@Test
void shouldThrowWhenBgmfTypeCodeIsInvalid() throws Exception {
    assertInvalidSettlementTypeCode("BGMF");
}

@Test
void shouldThrowWhenRetfTypeCodeIsInvalid() throws Exception {
    assertInvalidSettlementTypeCode("RETF");
}

@Test
void shouldThrowWhenIteaTypeCodeIsInvalid() throws Exception {
    assertInvalidSettlementTypeCode("ITEA");
}


private void assertInvalidSettlementCurrency(String code) throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurposeOk();

    RequestTermDepositsDTO request = requestWithThreeSettlements(
            "940250", "001", "001", "1234", "CC", "001",
            "123456789", "C", "010", "1000", "COP", "30"
    );

    changeSettlementCurrency(request, code, "USD");

    assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

private void assertInvalidSettlementTypeCode(String code) throws Exception {
    mockAmountRange("500", "2000");
    mockBanks("001");
    mockPurposeOk();

    RequestTermDepositsDTO request = requestWithThreeSettlements(
            "940250", "001", "001", "1234", "CC", "001",
            "123456789", "C", "010", "1000", "COP", "30"
    );

    changeSettlementTypeCode(request, code, "X");

    assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    ));
}

private RequestTermDepositsDTO requestWithThreeSettlements(
        String productCode, String subproductCode, String bankId,
        String centerId, String accountType, String bankCode,
        String nationalId, String settlementCode, String purposeCode,
        String amount, String currency, String frequency) throws Exception {

    RequestTermDepositsDTO request = termRequest(
            productCode, subproductCode, bankId, centerId, accountType,
            bankCode, nationalId, settlementCode, purposeCode,
            amount, currency, frequency
    );

    Object economicData = get(request, "getEconomicData");

    Object bgmf = settlement("BGMF", "C", "0", "100", "COP");
    Object retf = settlement("RETF", "D", "0", "100", "COP");
    Object itea = settlement("ITEA", "C", "0", "100", "COP");

    set(economicData, "setSettlements", List.of(bgmf, retf, itea));

    return request;
}

private void changeSettlementCurrency(RequestTermDepositsDTO request, String code, String currency) throws Exception {
    Object concept = findSettlementConcept(request, code);
    Object amount = get(concept, "getAmount");
    set(amount, "setCurrency", currency);
}

private void changeSettlementTypeCode(RequestTermDepositsDTO request, String code, String typeCode) throws Exception {
    Object concept = findSettlementConcept(request, code);
    set(concept, "setTypeCode", typeCode);
}

private void changeSettlementCode(RequestTermDepositsDTO request, int index, String code) throws Exception {
    Object economicData = get(request, "getEconomicData");
    List<?> settlements = (List<?>) get(economicData, "getSettlements");
    Object concept = get(settlements.get(index), "getSettlementConcept");
    set(concept, "setCode", code);
}

private Object findSettlementConcept(RequestTermDepositsDTO request, String code) throws Exception {
    Object economicData = get(request, "getEconomicData");
    List<?> settlements = (List<?>) get(economicData, "getSettlements");

    for (Object settlement : settlements) {
        Object concept = get(settlement, "getSettlementConcept");
        if (code.equals(get(concept, "getCode"))) {
            return concept;
        }
    }

    throw new IllegalArgumentException(code);
}

private void mockPurposeOk() {
    com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO parameter =
            new com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersDTO();

    parameter.setCode("010");
    parameter.setDescription("PURPOSE");

    TermDepositParametersResponse response = new TermDepositParametersResponse();
    response.setParameters(List.of(parameter));

    when(termDepositParametersService.termDepositParameters(any())).thenReturn(response);
}
