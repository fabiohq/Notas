package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.mappers;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.*;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositFundsMappersTest {

    @Test
    void bp14toTermDepositFundsResponseShouldMapResponse() {
        TrxBP14BGMP140Response bgmp140 = new TrxBP14BGMP140Response();
        bgmp140.setNOMPER("INTERNAL-REF");

        TrxBP14DataResponse data = new TrxBP14DataResponse();
        data.setBGMP140(bgmp140);

        TrxBP14Response trxResponse = new TrxBP14Response();
        trxResponse.setData(data);

        TermDepositFundsMappers mapper = new TermDepositFundsMappers();

        TermDepositFundsResponseDto result =
                mapper.bp14toTermDepositFundsResponse(trxResponse);

        assertNotNull(result);
        assertNotNull(result.getSourceFunds());
        assertNotNull(result.getStatusInfo());
        assertEquals("INTERNAL-REF", result.getSourceFunds().getInternalReference());
        assertEquals("OK", result.getStatusInfo().getStatusCode());
        assertEquals("PAGO EFECTUADO", result.getStatusInfo().getStatusDescription());
    }
}
