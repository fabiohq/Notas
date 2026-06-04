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
    rangeResponse.setMinimumAmount(min);
    rangeResponse.setMaximumAmount(max);

    org.mockito.Mockito.when(productDirectoryService.amountRange(org.mockito.ArgumentMatchers.any()))
            .thenReturn(rangeResponse);

    assertDoesNotThrow(() -> utils.simulatePlacementInputValidation(request, amountRange));
}
