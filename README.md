@Test
void shouldCoverTrxBP31SecondCallForDepositList() throws Exception {
    GetListDepositsRequestDTO request = GetListDepositsRequestDTO.builder()
            .participantId("12345678")
            .placementStatus("A")
            .limit("1")
            .offset("00001-00000")
            .build();

    TrxBP31Response first = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
    TrxBP31Response second = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);

    when(first.getData().getCdtsDats()).thenReturn(List.of(cdt("A", "1000")));
    when(second.getData().getCdtsDats()).thenReturn(List.of(cdt("P", "2000")));
    when(trxSanbaService.trxBP31(any())).thenReturn(second);

    Object result = invoke(
            "trxBP31SecondCallForDepositList",
            new Class<?>[] { GetListDepositsRequestDTO.class, TrxBP31Response.class, int.class },
            request,
            first,
            1
    );

    assertNotNull(result);
}

@Test
void shouldCoverTrxBP31RecallForDepositSummary() throws Exception {
    CalculateDepositSummaryRequestDTO request = summaryRequest();

    TrxBP31Response first = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
    TrxBP31Response second = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);

    when(first.getData().getCdtsDats()).thenReturn(List.of(cdt("A", "1000")));
    when(second.getData().getCdtsDats()).thenReturn(List.of(cdt("N", "2000")));
    when(trxSanbaService.trxBP31(any())).thenReturn(second);

    Object result = invoke(
            "trxBP31recallForDepositSummary",
            new Class<?>[] { CalculateDepositSummaryRequestDTO.class, TrxBP31Response.class },
            request,
            first
    );

    assertNotNull(result);
}

@Test
void shouldCoverTrxBP01call() {
    RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);
    TrxBp01Response response = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);

    when(request.getParticipants().get(0).getParticipantId()).thenReturn("123456789");
    when(request.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification())
            .thenReturn("123456789");
    when(request.getDeposit().getPlacement().getDestinationFunds().getAccountIdType())
            .thenReturn("CC");

    when(trxSanbaService.trxBP01(any())).thenReturn(response);

    assertSame(response, service.trxBP01call(request));
    verify(trxSanbaService).trxBP01(any());
}

@SuppressWarnings("unchecked")
private <T> T invoke(String methodName, Class<?>[] parameterTypes, Object... args) throws Exception {
    Method method = TermDepositServiceImpl.class.getDeclaredMethod(methodName, parameterTypes);
    method.setAccessible(true);
    return (T) method.invoke(service, args);
}
