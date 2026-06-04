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

    assertThrows(Exception.class, () -> method.invoke(utils, parametersRequest, "001"));
}










@Test
void shouldCoverTermDepositsInputValidation() {
    Object request = assertDoesNotThrow(() -> validTermDepositRequest());

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest parametersRequest =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest();

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest banksRequest =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest("auth", "client");

    assertThrows(Exception.class, () ->
            utils.termDepositsInputValidation(
                    (com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO) request,
                    parametersRequest,
                    banksRequest
            )
    );
}






import static org.junit.jupiter.api.Assertions.assertThrows;
