package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.MovementConceptUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.TermDepositUtils;

class TermDepositSettlementsMappersTest {

    @Test
    void settlementsReponseShouldMapAllValidConcepts() {
        TermDepositSettlementsMappers mapper = mapper();

        TrxBP49Response bp49 = bp49(
                movement("INTP", "000000000001234", "2026-06-01"),
                movement("RFIP", "-000000000000500", "2026-06-02"),
                movement("VTOP", "000000000009999", "2026-06-03"),
                movement("PAGP", "000000000000100", "2026-06-04"),
                movement("XXXX", "000000000000777", "2026-06-05")
        );

        TrxBP13Response bp13 = bp13("00001234");

        TermDepositSettlementsReponse response = mapper.settlementsReponse(bp49, bp13);

        assertNotNull(response);
        assertNotNull(response.getSettlements());
        assertEquals(4, response.getSettlements().size());

        var intp = response.getSettlements().get(0);
        assertEquals("INTP", intp.getProcessTypeCode());
        assertNotNull(intp.getProcessTypeDescription());
        assertEquals("12,34", intp.getAmount().getAmount());
        assertEquals("12,34", intp.getEarnedAmount().getAmount());
        assertEquals("COP", intp.getAmount().getCurrency());
        assertEquals("COP", intp.getEarnedAmount().getCurrency());
        assertEquals("2026-06-01", intp.getProcessDate());
        assertEquals("1234,0000", intp.getPostedIndicator());

        var rfip = response.getSettlements().get(1);
        assertEquals("RFIP", rfip.getProcessTypeCode());
        assertEquals("0,00", rfip.getAmount().getAmount());
        assertEquals("-5,00", rfip.getEarnedAmount().getAmount());
        assertEquals("", rfip.getPostedIndicator());

        var vtop = response.getSettlements().get(2);
        assertEquals("VTOP", vtop.getProcessTypeCode());
        assertEquals("0,00", vtop.getAmount().getAmount());
        assertEquals("99,99", vtop.getEarnedAmount().getAmount());
        assertEquals("", vtop.getPostedIndicator());

        var pagp = response.getSettlements().get(3);
        assertEquals("PAGP", pagp.getProcessTypeCode());
        assertEquals("1,00", pagp.getAmount().getAmount());
        assertEquals("1,00", pagp.getEarnedAmount().getAmount());
        assertEquals("1234,0000", pagp.getPostedIndicator());
    }

    @Test
    void settlementsReponseShouldMapNegativePostedIndicator() {
        TermDepositSettlementsMappers mapper = mapper();

        TrxBP49Response bp49 = bp49(
                movement("INTP", "000000000000100", "2026-06-01")
        );

        TrxBP13Response bp13 = bp13("-00001234");

        TermDepositSettlementsReponse response = mapper.settlementsReponse(bp49, bp13);

        assertEquals(1, response.getSettlements().size());
        assertEquals("-1234,0000", response.getSettlements().get(0).getPostedIndicator());
    }

    @Test
    void settlementsReponseShouldReturnEmptyWhenNoAllowedConcepts() {
        TermDepositSettlementsMappers mapper = mapper();

        TrxBP49Response bp49 = bp49(
                movement("AAAA", "000000000000100", "2026-06-01"),
                movement("BBBB", "000000000000200", "2026-06-02")
        );

        TrxBP13Response bp13 = bp13("00001234");

        TermDepositSettlementsReponse response = mapper.settlementsReponse(bp49, bp13);

        assertNotNull(response);
        assertNull(response.getSettlements());
    }

    @Test
    void setConceptShouldCoverAllBranches() throws Exception {
        TermDepositSettlementsMappers mapper = mapper();

        Method method = TermDepositSettlementsMappers.class
                .getDeclaredMethod("setConcept", String.class);
        method.setAccessible(true);

        assertNotNull(method.invoke(mapper, "INTP"));
        assertNotNull(method.invoke(mapper, "RFIP"));
        assertNotNull(method.invoke(mapper, "VTOP"));
        assertNotNull(method.invoke(mapper, "PAGP"));
        assertEquals("", method.invoke(mapper, "OTHER"));
    }

    private TermDepositSettlementsMappers mapper() {
        return new TermDepositSettlementsMappers(
                mock(TermDepositUtils.class),
                mock(RegexUtils.class),
                mock(ErrorService.class),
                mock(MovementConceptUtils.class)
        );
    }

    private TrxBP49Response bp49(TrxBP49Data... movements) {
        TrxBP49DataResponse data = new TrxBP49DataResponse();
        data.setMovimientos(new ArrayList<>(List.of(movements)));

        TrxBP49Response response = new TrxBP49Response();
        response.setData(data);

        return response;
    }

    private TrxBP49Data movement(String concepto, String valor, String fecha) {
        TrxBP49Data data = new TrxBP49Data();
        data.setConcepto(concepto);
        data.setValor(valor);
        data.setFecha(fecha);
        return data;
    }

    private TrxBP13Response bp13(String tipoEfectivo) {
        TrxBP13DataResponse data = new TrxBP13DataResponse();
        data.setTipoEfectivo(tipoEfectivo);

        TrxBP13Response response = new TrxBP13Response();
        response.setData(data);

        return response;
    }
}

******

assertEquals("", method.invoke(mapper, "OTHER"));

por
assertNull(method.invoke(mapper, "OTHER"));
