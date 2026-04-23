@Test
void shouldThrowWhenBankIsNotFound() {
    BanksParametersRequest request = new BanksParametersRequest();

    BanksParametersDTO dto = new BanksParametersDTO();
    dto.setGetBankId("9999");

    BanksResponse response = new BanksResponse();
    response.setBanks(java.util.List.of(dto));

    String message = "bankId not found";

    java.util.HashMap<String, String> general = new java.util.HashMap<>();
    general.put("bankId_not_found", message);

    ServiceException expected = new ServiceException(
            HttpStatus.NOT_FOUND,
            ErrorDTO.builder()
                    .code("COD-004")
                    .message(message)
                    .level("ERROR")
                    .description("desc")
                    .build()
    );

    when(banksService.banksResponse(request)).thenReturn(response);
    when(errorService.getGeneral()).thenReturn(general);
    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            eq(message),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(
            ServiceException.class,
            () -> contractsUtils.bankValidation(request, "0065")
    );

    assertSame(expected, ex);
}