package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTermDepositPlacementDTOTest {

    @Test
    void shouldCoverGettersAndSetters() {
        ResponseTermDepositPlacementDTO dto = new ResponseTermDepositPlacementDTO();

        dto.setPlacementId("placement-001");
        dto.setMaturityDate("2025-12-31");
        dto.setOpeningValueDate("2024-01-01");
        dto.setAnnualPercentageYield("12.5");

        assertEquals("placement-001", dto.getPlacementId());
        assertEquals("2025-12-31", dto.getMaturityDate());
        assertEquals("2024-01-01", dto.getOpeningValueDate());
        assertEquals("12.5", dto.getAnnualPercentageYield());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTermDepositsDTOTest {

    @Test
    void shouldCoverGettersAndSetters() {
        ResponseTermDepositsDTO dto = new ResponseTermDepositsDTO();

        ResponseTermDepositPlacementDTO placement =
                new ResponseTermDepositPlacementDTO();

        placement.setPlacementId("placement-001");
        placement.setMaturityDate("2025-12-31");
        placement.setOpeningValueDate("2024-01-01");
        placement.setAnnualPercentageYield("12.5");

        dto.setDepositId("deposit-001");
        dto.setPlacement(placement);

        assertEquals("deposit-001", dto.getDepositId());
        assertEquals(placement, dto.getPlacement());
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositDestinationFundsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositPeriodicityDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositSettlementConditionDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositPlacementDTOTest {

    @Test
    void shouldCoverGettersAndSetters() {
        TermDepositPlacementDTO dto = new TermDepositPlacementDTO();

        TermDepositDestinationFundsDTO destinationFunds =
                new TermDepositDestinationFundsDTO();
        TermDepositPeriodicityDTO periodicity =
                new TermDepositPeriodicityDTO();
        TermDepositSettlementConditionDTO settlementCondition =
                new TermDepositSettlementConditionDTO();

        dto.setDestinationFunds(destinationFunds);
        dto.setPeriodicity(periodicity);
        dto.setSettlementCondition(settlementCondition);
        dto.setPurposeCode("001");

        assertEquals(destinationFunds, dto.getDestinationFunds());
        assertEquals(periodicity, dto.getPeriodicity());
        assertEquals(settlementCondition, dto.getSettlementCondition());
        assertEquals("001", dto.getPurposeCode());
    }
}
