ESTE TEST FALLO

@Test
	void shouldCalculateDepositSummaryWithData() {
		CalculateDepositSummaryRequestDTO request = summaryRequest();

		TrxBP31Response trx = mock(TrxBP31Response.class, RETURNS_DEEP_STUBS);
		CalculateDepositSummaryResponseDTO mapped = new CalculateDepositSummaryResponseDTO();

		when(trx.getData().getCdtsDats()).thenReturn(List.of(cdt("A", "1000"), cdt("N", "2000"), cdt("C", "9999")));
		when(trxSanbaService.trxBP31(any())).thenReturn(trx);
		when(mapper.calculateDepositSummaryMapper(any(), anyList(), anyString())).thenReturn(mapped);

		assertSame(mapped, service.calculateDepositSummary(request));
		verify(mapper).calculateDepositSummaryMapper(eq(trx), anyList(), anyString());
	}
EN ESTA LINEA 
		when(trx.getData().getCdtsDats()).thenReturn(List.of(cdt("A", "1000"), cdt("N", "2000"), cdt("C", "9999")));


ESTE ES EL ERROR 
org.mockito.exceptions.misusing.UnfinishedStubbingException: 
Unfinished stubbing detected here:
-> at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImplTest.shouldCalculateDepositSummaryWithData(TermDepositServiceImplTest.java:246)

E.g. thenReturn() may be missing.
Examples of correct stubbing:
    when(mock.isOk()).thenReturn(true);
    when(mock.isOk()).thenThrow(exception);
    doThrow(exception).when(mock).someVoidMethod();
Hints:
 1. missing thenReturn()
 2. you are trying to stub a final method, which is not supported
 3. you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed

	at com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO.getEstado(CdtsDatsDTO.java:68)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImplTest.cdt(TermDepositServiceImplTest.java:340)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImplTest.shouldCalculateDepositSummaryWithData(TermDepositServiceImplTest.java:246)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

******
ESTE TEST FALLO
@Test
	void shouldCreateTermDeposit() {
		RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

		TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
		TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
		ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

		when(trxSanbaService.trxPEPF(any())).thenReturn(new TrxPEPFDataResponse());
		when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
		when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
		when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

		ResponseTermDepositsDTO result = service.responseTermDepositsDTO(request,
				new AmountRangeRequest("auth", "client", null),
				new TermDepositParametersRequest("940250", "auth", "client"),
				new BanksParametersRequest("auth", "client"));

		assertSame(mapped, result);
		verify(termDepositUtils).termDepositsInputValidation(any(), any(), any(), any());
		verify(mapper).responseTermDepositsDTOMapper(bp02);
	}

    ESTA LINEA FALLA
    ResponseTermDepositsDTO result = service.responseTermDepositsDTO(request,
				new AmountRangeRequest("auth", "client", null),
				new TermDepositParametersRequest("940250", "auth", "client"),
				new BanksParametersRequest("auth", "client"));

EL ERROR 
java.lang.NullPointerException: Cannot invoke "String.replace(java.lang.CharSequence, java.lang.CharSequence)" because the return value of "com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositAmountDTO.getAmount()" is null
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImpl.trxBP02call(TermDepositServiceImpl.java:534)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImpl.responseTermDepositsDTO(TermDepositServiceImpl.java:280)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImplTest.shouldCreateTermDeposit(TermDepositServiceImplTest.java:188)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

******************

ESTE TEST FALLO 
@Test
	void shouldCreateTermDepositIgnoringPatrimonialError() {
		RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);

		TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
		TrxBp02Response bp02 = mock(TrxBp02Response.class, RETURNS_DEEP_STUBS);
		ResponseTermDepositsDTO mapped = new ResponseTermDepositsDTO();

		when(trxSanbaService.trxPEPF(any())).thenThrow(new RuntimeException("patrimonial"));
		when(trxSanbaService.trxBP01(any())).thenReturn(bp01);
		when(trxSanbaService.trxBP02(any())).thenReturn(bp02);
		when(mapper.responseTermDepositsDTOMapper(bp02)).thenReturn(mapped);

		assertSame(mapped, service.responseTermDepositsDTO(request, new AmountRangeRequest(),
				new TermDepositParametersRequest(), new BanksParametersRequest()));
	}

EN ESTA LINEA
assertSame(mapped, service.responseTermDepositsDTO(request, new AmountRangeRequest(),
				new TermDepositParametersRequest(), new BanksParametersRequest()));

ESTE ES EL ERROR 
java.lang.NullPointerException: Cannot invoke "String.replace(java.lang.CharSequence, java.lang.CharSequence)" because the return value of "com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositAmountDTO.getAmount()" is null
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImpl.trxBP02call(TermDepositServiceImpl.java:534)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImpl.responseTermDepositsDTO(TermDepositServiceImpl.java:280)
	at com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl.TermDepositServiceImplTest.shouldCreateTermDepositIgnoringPatrimonialError(TermDepositServiceImplTest.java:211)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


