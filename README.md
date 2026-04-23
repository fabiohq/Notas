@Test
void shouldFormat15DigitNumberWhenInputIsBlank() {
    assertEquals("0,00", ContractsUtils.format15DigitNumber("   "));
}

@Test
void shouldFormat15DigitNumberWhenInputHasOneDigit() {
    assertEquals("0,09", ContractsUtils.format15DigitNumber("9"));
}

@Test
void shouldThrowWhenFoundBankChangesAndDoesNotMatchInput() {
    BanksParametersRequest request = new BanksParametersRequest();

    BanksParametersDTO dto = mock(BanksParametersDTO.class);
    when(dto.getBankId()).thenReturn("0065", "9999");

    BanksResponse response = new BanksResponse();
    response.setBanks(List.of(dto));

    String message = "bankId not found";

    HashMap<String, String> general = new HashMap<>();
    general.put("bankId_not_found", message);

    ServiceException expected = new ServiceException(
            HttpStatus.NOT_FOUND,
            ErrorDTO.builder()
                    .code("COD-005")
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