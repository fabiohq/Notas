@Test
void shouldCoverSimulatePlacementInputValidation() {
    var product = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.Product();
    product.setProductId("12");

    var subproduct = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.SubproductRequestDTO();
    subproduct.setSubproductId("001");
    product.setSubproduct(subproduct);

    var amount = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.AmountRequestDTO();
    amount.setAmount("1000");
    amount.setCurrency("COP");

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

    assertDoesNotThrow(() ->
            utils.simulatePlacementInputValidation(request, amountRange)
    );
}

product.setProductCode("12");
