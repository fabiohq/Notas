package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OldAssociatedContractRequestDTO {

    private ContractRequestDTO contract;
    private String relationshipTypeCode;
    private ValidityPeriodRequestDTO validityPeriod;

    public ContractRequestDTO getContract() {
        return contract;
    }

    public void setContract(ContractRequestDTO contract) {
        this.contract = contract;
    }

    public String getRelationshipTypeCode() {
        return relationshipTypeCode;
    }

    public void setRelationshipTypeCode(String relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }

    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}
