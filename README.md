@Test
void shouldCoverPurposeCodeValidationHappyPath() {
    var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
    param.setCode("01");

    var response = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse();
    response.setParameters(java.util.List.of(param));

    org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(
            org.mockito.ArgumentMatchers.any()
    )).thenReturn(response);

    assertEquals("01",
            utils.purposeCodeValidation(new TermDepositParametersRequest(), "0100"));
}

var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
param.setCode("00");

var params = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse();
params.setParameters(java.util.List.of(param));

org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(
        org.mockito.ArgumentMatchers.any()
)).thenReturn(params);


