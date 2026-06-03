@Test
void shouldCalculateDepositSummaryWithData() {
    CalculateDepositSummaryRequestDTO request = summaryRequest();

    TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
    CalculateDepositSummaryResponseDTO mapped = new CalculateDepositSummaryResponseDTO();

    List<CdtsDatsDTO> cdts = List.of(
            cdt("A", "1000"),
            cdt("N", "2000"),
            cdt("C", "9999")
    );

    when(trx.getData().getCdtsDats()).thenReturn(cdts);
    when(trxSanbaService.trxBP31(any())).thenReturn(trx);
    when(mapper.calculateDepositSummaryMapper(any(), anyList(), anyString())).thenReturn(mapped);

    assertSame(mapped, service.calculateDepositSummary(request));
    verify(mapper).calculateDepositSummaryMapper(eq(trx), anyList(), anyString());
}

@Test
void shouldCreateTermDeposit() {
    RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

    when(request.getEconomicData().getInitialTotalInvested().getAmount()).thenReturn("1000");
    when(request.getEconomicData().getInitialTotalInvested().getCurrency()).thenReturn("COP");

    TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
    TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
    ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

    when(trxSanbaService.trxPEPF(any())).thenReturn(new TrxPEPFDataResponse());
    when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
    when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
    when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

    ResponseTermDepositsDTO result = service.responseTermDepositsDTO(
            request,
            new AmountRangeRequest("auth", "client", null),
            new TermDepositParametersRequest("940250", "auth", "client"),
            new BanksParametersRequest("auth", "client")
    );

    assertSame(mapped, result);
    verify(termDepositUtils).termDepositsInputValidation(any(), any(), any(), any());
    verify(mapper).responseTermDepositsDTOMapper(bp02);
}

@Test
void shouldCreateTermDepositIgnoringPatrimonialError() {
    RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

    when(request.getEconomicData().getInitialTotalInvested().getAmount()).thenReturn("1000");
    when(request.getEconomicData().getInitialTotalInvested().getCurrency()).thenReturn("COP");

    TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
    TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
    ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

    when(trxSanbaService.trxPEPF(any())).thenThrow(new RuntimeException("patrimonial"));
    when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
    when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
    when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

    assertSame(mapped, service.responseTermDepositsDTO(
            request,
            new AmountRangeRequest(),
            new TermDepositParametersRequest(),
            new BanksParametersRequest()
    ));
}

private CdtsDatsDTO cdt(String status, String saldo) {
    CdtsDatsDTO dto = new CdtsDatsDTO();

    dto.estado = status;
    dto.saldo = saldo;
    dto.cccReposicionamiento = "00000000000000000001";
    dto.secuenciaReposicionamiento = "00001";
    dto.secuenciaRenovacion = "00000";

    return dto;
}
