package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.OrganizationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.BankRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProspectRequestDTO {
    private PersonDTO person;
    private OrganizationDTO organization;
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    private List<ContactPointDTO> contactPoints;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }

    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }

    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }

    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }

    public BankRequestDTO getBank() {
        return bank;
    }

    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }

    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
}
