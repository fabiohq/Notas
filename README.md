@Test
void shouldCoverLambda0WithIteaSettlement() throws Exception {
    Object request = validTermDepositRequest();

    Object itea = buildSettlement("ITEA", "C", "1", "1000", "COP");

    Object economicData = obj(pkgTerm() + ".TermDepositEconomicDataDTO");

    Object initialAmount = obj(pkgTerm() + ".TermDepositAmountDTO");
    set(initialAmount, "setAmount", "1000");
    set(initialAmount, "setCurrency", "COP");

    set(economicData, "setInitialTotalInvested", initialAmount);
    set(economicData, "setSettlements", java.util.List.of(itea));

    set(request, "setEconomicData", economicData);

    mockBanksAndParameters();

    assertDoesNotThrow(() -> utils.termDepositsInputValidation(
            (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
            new TermDepositParametersRequest(),
            new BanksParametersRequest("auth", "client")));
}

@Test
void shouldCoverLambda0WithUnknownSettlementCode() throws Exception {
    Object request = validTermDepositRequest();

    Object other = buildSettlement("OTRO", "C", "1", "1000", "COP");

    Object economicData = obj(pkgTerm() + ".TermDepositEconomicDataDTO");

    Object initialAmount = obj(pkgTerm() + ".TermDepositAmountDTO");
    set(initialAmount, "setAmount", "1000");
    set(initialAmount, "setCurrency", "COP");

    set(economicData, "setInitialTotalInvested", initialAmount);
    set(economicData, "setSettlements", java.util.List.of(other));

    set(request, "setEconomicData", economicData);

    mockBanksAndParameters();

    assertDoesNotThrow(() -> utils.termDepositsInputValidation(
            (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
            new TermDepositParametersRequest(),
            new BanksParametersRequest("auth", "client")));
}

private void mockBanksAndParameters() {
    var bank = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
    bank.setBankId("0065");

    var banks = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
    banks.setBanks(java.util.List.of(bank));

    org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any()))
            .thenReturn(banks);

    var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
    param.setCode("00");

    var params = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse();
    params.setParameters(java.util.List.of(param));

    org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(org.mockito.ArgumentMatchers.any()))
            .thenReturn(params);
}






set(placement, "setPurposeCode", "000");
