package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationCommonDTO {
    private String offset;
    private String limit;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}











package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {
    private PaginationCommonDTO first;
    private PaginationCommonDTO prev;
    private PaginationCommonDTO next;
    private PaginationCommonDTO last;
    private PaginationCommonDTO self;

    public PaginationCommonDTO getFirst() {
        return first;
    }

    public void setFirst(PaginationCommonDTO first) {
        this.first = first;
    }

    public PaginationCommonDTO getPrev() {
        return prev;
    }

    public void setPrev(PaginationCommonDTO prev) {
        this.prev = prev;
    }

    public PaginationCommonDTO getNext() {
        return next;
    }

    public void setNext(PaginationCommonDTO next) {
        this.next = next;
    }

    public PaginationCommonDTO getLast() {
        return last;
    }

    public void setLast(PaginationCommonDTO last) {
        this.last = last;
    }

    public PaginationCommonDTO getSelf() {
        return self;
    }

    public void setSelf(PaginationCommonDTO self) {
        this.self = self;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private String bankId;
    private String bankName;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private String documentTypeCode;
    private String documentTypeDescription;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town; 

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuerEntity() {
        return issuerEntity;
    }

    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }    
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String fullAddress;
    private String formatCode;
    private String formatDescription;
    private Boolean isAddressValidated;
    private String matchId;
    private String streetTypeCode;
    private String streetTypeDescription;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String mailDeliverySubLocation;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String department;
    private String subDepartment;
    private String postCodeIdentification;
    private CodeNameDTO town;
    private CodeNameDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private CodeNameDTO province;
    private CodeNameDTO regionIdentification;
    private CodeNameDTO countyIdentification;
    private CodeNameDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private String postBoxTypeDescription;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralTypeDescription;
    private String ruralNumber;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getFormatDescription() {
        return formatDescription;
    }

    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }

    public Boolean getAddressValidated() {
        return isAddressValidated;
    }

    public void setAddressValidated(Boolean addressValidated) {
        isAddressValidated = addressValidated;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }

    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }

    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }

    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }

    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }

    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getAlternativePremise() {
        return alternativePremise;
    }

    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }

    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }

    public CodeNameDTO getTown() {
        return town;
    }

    public void setTown(CodeNameDTO town) {
        this.town = town;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }

    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }

    public String getMailingInstructions() {
        return mailingInstructions;
    }

    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }

    public CodeNameDTO getProvince() {
        return province;
    }

    public void setProvince(CodeNameDTO province) {
        this.province = province;
    }

    public CodeNameDTO getRegionIdentification() {
        return regionIdentification;
    }

    public void setRegionIdentification(CodeNameDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }

    public CodeNameDTO getCountyIdentification() {
        return countyIdentification;
    }

    public void setCountyIdentification(CodeNameDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }

    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }

    public String getPostBoxTypeDescription() {
        return postBoxTypeDescription;
    }

    public void setPostBoxTypeDescription(String postBoxTypeDescription) {
        this.postBoxTypeDescription = postBoxTypeDescription;
    }

    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }

    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZip4Code() {
        return zip4Code;
    }

    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }

    public String getRuralTypeCode() {
        return ruralTypeCode;
    }

    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }

    public String getRuralTypeDescription() {
        return ruralTypeDescription;
    }

    public void setRuralTypeDescription(String ruralTypeDescription) {
        this.ruralTypeDescription = ruralTypeDescription;
    }

    public String getRuralNumber() {
        return ruralNumber;
    }

    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Controlls all time entries
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadistics {
    private String entryTime;
    private String trxCallTime;
    private String trxExitTime;
    private String exitTime;

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTrxCallTime() {
        return trxCallTime;
    }

    public void setTrxCallTime(String trxCallTime) {
        this.trxCallTime = trxCallTime;
    }

    public String getTrxExitTime() {
        return trxExitTime;
    }

    public void setTrxExitTime(String trxExitTime) {
        this.trxExitTime = trxExitTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
}
