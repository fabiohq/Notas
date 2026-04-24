package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractsServiceRequestDTO {

    private NewAssociatedContractRequestDTO newAssociatedContract;
    private OldAssociatedContractRequestDTO oldAssociatedContract;

    public NewAssociatedContractRequestDTO getNewAssociatedContract() {
        return newAssociatedContract;
    }

    public void setNewAssociatedContract(NewAssociatedContractRequestDTO newAssociatedContract) {
        this.newAssociatedContract = newAssociatedContract;
    }

    public OldAssociatedContractRequestDTO getOldAssociatedContract() {
        return oldAssociatedContract;
    }

    public void setOldAssociatedContract(OldAssociatedContractRequestDTO oldAssociatedContract) {
        this.oldAssociatedContract = oldAssociatedContract;
    }
}




======================


package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractIdentificationRequestDTO {

    private String nationalIdentification;
    private String internalIdentification;

    public String getNationalIdentification() {
        return nationalIdentification;
    }

    public void setNationalIdentification(String nationalIdentification) {
        this.nationalIdentification = nationalIdentification;
    }

    public String getInternalIdentification() {
        return internalIdentification;
    }

    public void setInternalIdentification(String internalIdentification) {
        this.internalIdentification = internalIdentification;
    }
}





============================

package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequestDTO {
    private Boolean isExternalContract;
    private ContractIdentificationRequestDTO contractIdentification;

    public Boolean getExternalContract() {
        return isExternalContract;
    }

    public void setExternalContract(Boolean externalContract) {
        isExternalContract = externalContract;
    }

    public ContractIdentificationRequestDTO getContractIdentification() {
        return contractIdentification;
    }

    public void setContractIdentification(ContractIdentificationRequestDTO contractIdentification) {
        this.contractIdentification = contractIdentification;
    }
}




=========================================


package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodRequestDTO {

    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
======================================================




package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAssociatedContractRequestDTO {
    private ContractRequestDTO contract;
    private ValidityPeriodRequestDTO validityPeriod;

    public ContractRequestDTO getContract() {
        return contract;
    }

    public void setContract(ContractRequestDTO contract) {
        this.contract = contract;
    }

    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}



