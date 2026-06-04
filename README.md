import static org.junit.jupiter.api.Assertions.assertEquals;


@Test
void shouldCoverCleanAndFormatNumberString() throws Exception {
    Method method = TermDepositUtils.class
            .getDeclaredMethod("cleanAndFormatNumberString", String.class);
    method.setAccessible(true);

    assertEquals("12345", method.invoke(utils, "00012345"));
    assertEquals("0", method.invoke(utils, "000000"));
    assertEquals("ABC", method.invoke(utils, "ABC"));
}

@Test
void shouldCoverPurposeCodeValidation() throws Exception {
    Object parametersRequest =
            Class.forName("com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest")
                    .getDeclaredConstructor()
                    .newInstance();

    Method method = TermDepositUtils.class.getDeclaredMethod(
            "purposeCodeValidation",
            parametersRequest.getClass(),
            String.class
    );
    method.setAccessible(true);

    assertDoesNotThrow(() -> method.invoke(utils, parametersRequest, "001"));
}

@Test
void shouldCoverGetSettlementsInputValidation() {
    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest request =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest();

    request.setDeposit_id("12345678901234567890");
    request.setPlacement_id("12345-67890");

    assertDoesNotThrow(() -> utils.getSettlementsInputValidation(request));
}

@Test
void shouldCoverTermDepositsInputValidation() {
    Object request = assertDoesNotThrow(() -> validTermDepositRequest());

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest parametersRequest =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest();

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest banksRequest =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest("auth", "client");

    assertDoesNotThrow(() ->
            utils.termDepositsInputValidation(
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
                    parametersRequest,
                    banksRequest
            )
    );
}


org.mockito.Mockito.lenient().doNothing().when(regexUtils)
        .validateRegex(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());

org.mockito.Mockito.lenient().doNothing().when(errorService)
        .isBlank(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());
