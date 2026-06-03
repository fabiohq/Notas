@Test
void shouldCoverTrxBP02callDecimalTrue() {
    ReflectionTestUtils.setField(service, "BP02_SERVICE_ROUTE", "BP02");
    ReflectionTestUtils.setField(service, "CURRENCY", "COP");
    ReflectionTestUtils.setField(service, "isDecimal", true);

    RequestTermDepositsDTO request = mock(RequestTermDepositsDTO.class, RETURNS_DEEP_STUBS);
    TrxBp01Response bp01 = mock(TrxBp01Response.class, RETURNS_DEEP_STUBS);
    TrxBp02Response expected = new TrxBp02Response();

    TermDepositParticipantDTO participant = new TermDepositParticipantDTO();
    participant.setParticipantId("123456789");

    when(request.getParticipants()).thenReturn(List.of(participant));
    when(request.getProduct().getProductCode()).thenReturn("940250");
    when(request.getProduct().getSubproduct().getSubproductId()).thenReturn("001");
    when(request.getDeposit().getPlacement().getPeriodicity().getFrequency()).thenReturn("30");
    when(request.getDeposit().getPlacement().getSettlementCondition().getCode()).thenReturn("C");
    when(request.getEconomicData().getInitialTotalInvested().getAmount()).thenReturn("1000,50");

    when(request.getEconomicData().getSettlements()).thenReturn(List.of(
            settlementDto("ITEA", "C", "1,23"),
            settlementDto("BGMF", "C", "0,00"),
            settlementDto("RETF", "D", "0,50")
    ));

    when(bp01.getData().getBGMP010().getCCCINVE()).thenReturn("12345678901234567890");
    when(trxSanbaService.trxBP02(any())).thenReturn(expected);

    TrxBp02Response result = service.trxBP02call(request, bp01);

    assertSame(expected, result);
    verify(trxSanbaService).trxBP02(any());
}


private TermDepositSettlementsDTO settlementDto(String code, String typeCode, String rate) {
    TermDepositAmountDTO amount = new TermDepositAmountDTO();
    amount.setAmount("100");
    amount.setCurrency("COP");

    TermDepositSettlementConceptDTO concept = new TermDepositSettlementConceptDTO();
    concept.setCode(code);
    concept.setTypeCode(typeCode);
    concept.setRate(rate);
    concept.setAmount(amount);

    TermDepositSettlementsDTO settlement = new TermDepositSettlementsDTO();
    settlement.setSettlementConcept(concept);

    return settlement;
}

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositAmountDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositParticipantDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositSettlementConceptDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositSettlementsDTO;
