@Test
void shouldCoverBankCodePrivateValidation() throws Exception {
    Object request = validTermDepositRequest();

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO bankDto =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
    bankDto.setBankId("0065");

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO banksDto =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
    banksDto.setBanks(java.util.List.of(bankDto));

    org.mockito.Mockito.when(banksService.banksResponse(org.mockito.ArgumentMatchers.any()))
            .thenReturn(banksDto);

    invoke("bankCodeValidationTermDepositInput",
            new Class<?>[]{
                    Class.forName(pkgTerm() + ".RequestTermDepositsDTO"),
                    BanksParametersRequest.class
            },
            request,
            new BanksParametersRequest("auth", "client"));
}

org.mockito.Mockito.lenient().doNothing().when(regexUtils)
        .validateRegex(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());
