package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.DataOriginDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.OrganizationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.BankRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerRequestDTOTest {

    @Test
    void shouldCoverSettersAndGetters() {
        CreateCustomerRequestDTO dto = new CreateCustomerRequestDTO();

        PersonRequestDto person = new PersonRequestDto();
        OrganizationDTO organization = new OrganizationDTO();
        BankRequestDTO bank = new BankRequestDTO();
        List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());
        List<DataOriginDTO> dataOrigins = List.of(new DataOriginDTO());

        dto.setPerson(person);
        dto.setOrganization(organization);
        dto.setStructuralSegmentCode("SEG");
        dto.setStructuralSubsegmentCode("SUB");
        dto.setBank(bank);
        dto.setContactPoints(contactPoints);
        dto.setDataOrigins(dataOrigins);

        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertEquals(bank, dto.getBank());
        assertEquals(contactPoints, dto.getContactPoints());
        assertEquals(dataOrigins, dto.getDataOrigins());
    }

    @Test
    void shouldCoverBuilder() {
        PersonRequestDto person = new PersonRequestDto();
        OrganizationDTO organization = new OrganizationDTO();
        BankRequestDTO bank = new BankRequestDTO();
        List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());
        List<DataOriginDTO> dataOrigins = List.of(new DataOriginDTO());

        CreateCustomerRequestDTO dto = CreateCustomerRequestDTO.builder()
                .person(person)
                .organization(organization)
                .structuralSegmentCode("SEG")
                .structuralSubsegmentCode("SUB")
                .bank(bank)
                .contactPoints(contactPoints)
                .dataOrigins(dataOrigins)
                .build();

        assertNotNull(dto);
        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertEquals(bank, dto.getBank());
        assertEquals(contactPoints, dto.getContactPoints());
        assertEquals(dataOrigins, dto.getDataOrigins());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PersonRequestDto person = new PersonRequestDto();
        OrganizationDTO organization = new OrganizationDTO();
        BankRequestDTO bank = new BankRequestDTO();
        List<ContactPointDTO> contactPoints = List.of(new ContactPointDTO());
        List<DataOriginDTO> dataOrigins = List.of(new DataOriginDTO());

        CreateCustomerRequestDTO dto = new CreateCustomerRequestDTO(
                person,
                organization,
                "SEG",
                "SUB",
                bank,
                contactPoints,
                dataOrigins
        );

        assertNotNull(dto);
        assertEquals(person, dto.getPerson());
        assertEquals(organization, dto.getOrganization());
        assertEquals("SEG", dto.getStructuralSegmentCode());
        assertEquals("SUB", dto.getStructuralSubsegmentCode());
        assertEquals(bank, dto.getBank());
        assertEquals(contactPoints, dto.getContactPoints());
        assertEquals(dataOrigins, dto.getDataOrigins());
    }
}