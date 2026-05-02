package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.DataOriginDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.OrganizationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.BankRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonRequestDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequestDTO {
    private PersonRequestDto person;
    private OrganizationDTO organization;
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    private List<ContactPointDTO> contactPoints;
    private List<DataOriginDTO> dataOrigins;

    public PersonRequestDto getPerson() {
        return person;
    }

    public void setPerson(PersonRequestDto person) {
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

    public List<DataOriginDTO> getDataOrigins() {
        return dataOrigins;
    }

    public void setDataOrigins(List<DataOriginDTO> dataOrigins) {
        this.dataOrigins = dataOrigins;
    }
}
