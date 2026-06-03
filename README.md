private Object settlement(
        String code,
        String typeCode,
        String rate,
        String amount,
        String currency) throws Exception {

    Object settlement = newObj("TermDepositSettlementsDTO");

    Object concept = newObj("TermDepositSettlementConceptDTO");

    Object amountDto = newObj("TermDepositAmountDTO");
    set(amountDto, "setAmount", amount);
    set(amountDto, "setCurrency", currency);

    set(concept, "setCode", code);
    set(concept, "setTypeCode", typeCode);
    set(concept, "setRate", rate);
    set(concept, "setAmount", amountDto);

    set(settlement, "setSettlementConcept", concept);

    return settlement;
}
