var rangeResponse = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeResponse();
rangeResponse.setMinimumAmount("100");
rangeResponse.setMaximumAmount("10000");

org.mockito.Mockito.when(productDirectoryService.amountRange(
        org.mockito.ArgumentMatchers.any()
)).thenReturn(rangeResponse);




var amountRange = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeRequest();
amountRange.setAuthorization("auth");
amountRange.setxSantanderClientId("client");
amountRange.setProductId("12");

var rangeResponse = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeResponse();
rangeResponse.setMinimumAmount("100");
rangeResponse.setMaximumAmount("10000");

org.mockito.Mockito.when(productDirectoryService.amountRange(
        org.mockito.ArgumentMatchers.any()
)).thenReturn(rangeResponse);

assertDoesNotThrow(() ->
        utils.simulatePlacementInputValidation(request, amountRange)
);


