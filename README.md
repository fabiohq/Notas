package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.OrganizationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.BankRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateProspectRequestDTOTest {

    @Test
    void testGettersAndSetters() {
        UpdateProspectRequestDTO dto = new UpdateProspectRequestDTO();

        PersonDTO person = new PersonDTO();
        OrganizationDTO organization = new OrganizationDTO();
        BankRequestDTO bank = new BankRequestDTO();
        ContactPointDTO contactPoint = new ContactPointDTO();

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setStructuralSegmentCode("SEG01");
        dto.setStructuralSubsegmentCode("SUB01");
        dto.setBank(bank);
        dto.setContactPoints(List.of(contactPoint));

        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals("SEG01", dto.getStructuralSegmentCode());
        assertEquals("SUB01", dto.getStructuralSubsegmentCode());
        assertEquals(bank, dto.getBank());
        assertNotNull(dto.getContactPoints());
        assertEquals(1, dto.getContactPoints().size());
    }

    @Test
    void testBuilder() {
        PersonDTO person = new PersonDTO();

        UpdateProspectRequestDTO dto = UpdateProspectRequestDTO.builder()
                .person(person)
                .structuralSegmentCode("SEG01")
                .build();

        assertEquals(person, dto.getPerson());
        assertEquals("SEG01", dto.getStructuralSegmentCode());
    }
}