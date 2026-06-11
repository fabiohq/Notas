package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermDepositParticipantDTOTest {

    @Test
    void shouldCoverNoArgsConstructorGetterAndSetter() {
        TermDepositParticipantDTO dto = new TermDepositParticipantDTO();

        dto.setParticipantId("participant-001");

        assertEquals("participant-001", dto.getParticipantId());
    }
}
