@Test
void shouldCoverValidateEconomicDataRetfAndBgmf() throws Exception {
    Object retf = buildSettlement("RETF", "D", "1", "1000", "COP");
    Object bgmf = buildSettlement("BGMF", "C", "1", "1000", "COP");

    invokePrivate("validateEconomicDataRetfTermDepositInput",
            new Class<?>[]{Class.forName(pkgTerm() + ".TermDepositSettlementsDTO")},
            retf);

    invokePrivate("validateEconomicDataBgmf",
            new Class<?>[]{Class.forName(pkgTerm() + ".TermDepositSettlementsDTO")},
            bgmf);
}

@Test
void shouldCoverSimulatePlacementInputValidation() throws Exception {
    Object request = Class.forName("com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulateplacement.RequestSimulatePlacementDTO")
            .getDeclaredConstructor()
            .newInstance();

    Object amount = Class.forName("com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.simulateplacement.AmountRangeRequest")
            .getDeclaredConstructor()
            .newInstance();

    set(amount, "setMinAmount", "100");
    set(amount, "setMaxAmount", "10000");

    set(request, "setAmount", "1000");
    set(request, "setCurrency", "COP");
    set(request, "setTerm", "30");

    assertDoesNotThrow(() ->
            utils.simulatePlacementInputValidation(
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulateplacement.RequestSimulatePlacementDTO) request,
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.simulateplacement.AmountRangeRequest) amount
            )
    );
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


