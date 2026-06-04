org.mockito.Mockito.lenient().when(errorService.getGeneral())
        .thenReturn(new java.util.HashMap<>());

org.mockito.Mockito.lenient().when(errorService.serviceExceptionBuilder(
        org.mockito.ArgumentMatchers.any(),
        org.mockito.ArgumentMatchers.any(),
        org.mockito.ArgumentMatchers.any()
)).thenReturn(new RuntimeException("error"));



@Test
void shouldCoverBankValidationHappyPath() {
    var bank = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
    bank.setBankId("0065");

    var banks = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
    banks.setBanks(java.util.List.of(bank));

    org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any()))
            .thenReturn(banks);

    assertEquals("0065",
            utils.bankValidation(new BanksParametersRequest("auth", "client"), "0065"));
}

@Test
void shouldCoverPurposeCodeValidationHappyPath() {
    var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParameterDTO();
    param.setCode("01");

    var response = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
    response.setParameters(java.util.List.of(param));

    org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(
            org.mockito.ArgumentMatchers.any()
    )).thenReturn(response);

    assertEquals("01",
            utils.purposeCodeValidation(new TermDepositParametersRequest(), "0100"));
}

@Test
void shouldCoverTermDepositsInputValidationHappyPath() {
    Object request = assertDoesNotThrow(() -> validTermDepositRequest());

    var bank = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
    bank.setBankId("0065");

    var banks = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
    banks.setBanks(java.util.List.of(bank));

    org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any()))
            .thenReturn(banks);

    var param = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParameterDTO();
    param.setCode("00");

    var params = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersDTO();
    params.setParameters(java.util.List.of(param));

    org.mockito.Mockito.when(termDepositParametersService.termDepositParameters(
            org.mockito.ArgumentMatchers.any()
    )).thenReturn(params);

    assertDoesNotThrow(() ->
            utils.termDepositsInputValidation(
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
                    new TermDepositParametersRequest(),
                    new BanksParametersRequest("auth", "client")
            )
    );
}


set(placement, "setPurposeCode", "000");

