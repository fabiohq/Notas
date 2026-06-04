@ExtendWith(MockitoExtension.class)
@org.mockito.junit.jupiter.MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class TermDepositUtilsPrivateValidationTest {


org.mockito.Mockito.lenient().doNothing().when(regexUtils)
        .validateRegex(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString());



@Test
void shouldCoverBankCodePrivateValidation() throws Exception {
    Object request = validTermDepositRequest();

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO bankDto =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersDTO();
    bankDto.setBankId("0065");

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO banksDto =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO();
    banksDto.setBanks(java.util.List.of(bankDto));

    com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksResponseDTO response =
            new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksResponseDTO();
    response.setBanks(banksDto);

    org.mockito.Mockito.when(banksService.getBanks(org.mockito.ArgumentMatchers.any()))
            .thenReturn(response);

    invoke("bankCodeValidationTermDepositInput",
            new Class<?>[]{
                    Class.forName(pkgTerm() + ".RequestTermDepositsDTO"),
                    BanksParametersRequest.class
            },
            request,
            new BanksParametersRequest("auth", "client"));
}
