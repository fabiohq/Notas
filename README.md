package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;


import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.BankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchDTO {
    private String customerId;
    private PersonDTO person;
    private OrganizationCustomerSearchDTO organization;
    private ContactPointDTO contactPoint;
    private Boolean highConfidentialityIndicator;
    private BankDTO bank;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationCustomerSearchDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationCustomerSearchDTO organization) {
        this.organization = organization;
    }

    public ContactPointDTO getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(ContactPointDTO contactPoint) {
        this.contactPoint = contactPoint;
    }

    public Boolean getHighConfidentialityIndicator() {
        return highConfidentialityIndicator;
    }

    public void setHighConfidentialityIndicator(Boolean highConfidentialityIndicator) {
        this.highConfidentialityIndicator = highConfidentialityIndicator;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchResponseDTO {
    private List<CustomerSearchDTO> customers;
    private PaginationDTO pagination;

    public List<CustomerSearchDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSearchDTO> customers) {
        this.customers = customers;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
}










package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCustomerSearchDTO {
    private String registrationDate;
    private OrganizationNameCustomerSearchDTO organizationName;
    private DocumentDTO document;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public OrganizationNameCustomerSearchDTO getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNameCustomerSearchDTO organizationName) {
        this.organizationName = organizationName;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameCustomerSearchDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}
