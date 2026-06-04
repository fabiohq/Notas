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

    assertThrows(Exception.class, () ->
            utils.termDepositsInputValidation(
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
                    new TermDepositParametersRequest(),
                    new BanksParametersRequest("auth", "client")
            )
    );
}
