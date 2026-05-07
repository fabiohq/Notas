package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralNumber;
    
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
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
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
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
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
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
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactDayRequestDTO {
    private String day;
    private String fromDateTime;
    private String toDateTime;
    
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactTimeRequestDTO {
    private String fromDateTime;
    private String toDateTime;
    private String bestTimeFrameCode;
    private String bestTimeFrameDescription;
    
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }
    public String getBestTimeFrameCode() {
        return bestTimeFrameCode;
    }
    public void setBestTimeFrameCode(String bestTimeFrameCode) {
        this.bestTimeFrameCode = bestTimeFrameCode;
    }
    public String getBestTimeFrameDescription() {
        return bestTimeFrameDescription;
    }
    public void setBestTimeFrameDescription(String bestTimeFrameDescription) {
        this.bestTimeFrameDescription = bestTimeFrameDescription;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointRequestDTO {
    private List<UseTypeRequestDTO> useTypes;
    private Boolean preferredIndicator;
    private Boolean primaryIndicator;
    private ValidityPeriodRequestDTO validityPeriod;
    private BestContactTimeRequestDTO bestContactTime;
    private List<BestContactDayRequestDTO> bestContactDays;
    private PostalAddressRequestDTO postalAddress;
    @Valid
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDTO electronicAddress;
    private WebAddressRequestDTO webAddress;
    
    public List<UseTypeRequestDTO> getUseTypes() {
        return useTypes;
    }
    public void setUseTypes(List<UseTypeRequestDTO> useTypes) {
        this.useTypes = useTypes;
    }
    public Boolean getPreferredIndicator() {
        return preferredIndicator;
    }
    public void setPreferredIndicator(Boolean preferredIndicator) {
        this.preferredIndicator = preferredIndicator;
    }
    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }
    public void setPrimaryIndicator(Boolean primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    public BestContactTimeRequestDTO getBestContactTime() {
        return bestContactTime;
    }
    public void setBestContactTime(BestContactTimeRequestDTO bestContactTime) {
        this.bestContactTime = bestContactTime;
    }
    public List<BestContactDayRequestDTO> getBestContactDays() {
        return bestContactDays;
    }
    public void setBestContactDays(List<BestContactDayRequestDTO> bestContactDays) {
        this.bestContactDays = bestContactDays;
    }
    public PostalAddressRequestDTO getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(PostalAddressRequestDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
    public ElectronicAddressRequestDTO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressRequestDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public WebAddressRequestDTO getWebAddress() {
        return webAddress;
    }
    public void setWebAddress(WebAddressRequestDTO webAddress) {
        this.webAddress = webAddress;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfOperationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfResidenceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountyIdentificationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProspectRequestDTO {
    @Valid
    @NotNull
    private PersonRequestDTO person;
    private OrganizationRequestDTO organization;    
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    @Valid
    private List<ContactPointRequestDTO> contactPoints;
    
    public PersonRequestDTO getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDTO person) {
        this.person = person;
    }
    public OrganizationRequestDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDTO organization) {
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
    public List<ContactPointRequestDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointRequestDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document2RequestDTO {
    private String documentTypeCode;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private StateRequestDTO state;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
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
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull
    @Size(min = 2, max = 2)
    private String documentTypeCode;
    @NotNull
    @Digits(integer = 11, fraction = 0, message = "documentNumber must be numeric and not exceed 11 digits")
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private String town;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
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
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityRequestDTO {
    private String subCategoryCode;

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequestDTO {
    private String name;
    private EconomicActivityRequestDTO economicActivity;
    private AddressRequestDTO address;
    private String typeCode;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public AddressRequestDTO getAddress() {
        return address;
    }
    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationRequestDTO {
    private EconomicActivityRequestDTO economicActivity;
    private String occupationCode;
    private String statusCode;
    private String subActivityCode;
    private String subActivityComments;
    private EmployerRequestDTO employer;
    
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getSubActivityCode() {
        return subActivityCode;
    }
    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }
    public String getSubActivityComments() {
        return subActivityComments;
    }
    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }
    public EmployerRequestDTO getEmployer() {
        return employer;
    }
    public void setEmployer(EmployerRequestDTO employer) {
        this.employer = employer;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignTaxisRequestDTO {
    private CountryRequestDTO country;
    private String reasonCode;
    private DocumentRequestDTO document;
    private NoDocumentRequestDTO noDocument;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
    public NoDocumentRequestDTO getNoDocument() {
        return noDocument;
    }
    public void setNoDocument(NoDocumentRequestDTO noDocument) {
        this.noDocument = noDocument;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoDocumentRequestDTO {
    private String reasonCode;
    private String reasonDetails;
    
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public String getReasonDetails() {
        return reasonDetails;
    }
    public void setReasonDetails(String reasonDetails) {
        this.reasonDetails = reasonDetails;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;
    private List<String> tradingNames;
    
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public List<String> getTradingNames() {
        return tradingNames;
    }
    public void setTradingNames(List<String> tradingNames) {
        this.tradingNames = tradingNames;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO {
    private OrganizationNameRequestDTO organizationName;
    private String typeCode;
    private String subtypeCode;
    private String registrationDate;
    private String accountingSectorCode;
    private String residentialStatusCode;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationRequestDTO placeOfRegistration;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private List<DocumentRequestDTO> documents;
    private CountryOfOperationRequestDTO countryOfOperation;
    private EconomicActivityRequestDTO economicActivity;
    private PreferredLanguageRequestDTO preferredLanguage;
    
    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public PlaceOfRegistrationRequestDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }
    public void setPlaceOfRegistration(PlaceOfRegistrationRequestDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }
    public CountryOfOperationRequestDTO getCountryOfOperation() {
        return countryOfOperation;
    }
    public void setCountryOfOperation(CountryOfOperationRequestDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String namePrefixCode;

    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The name can only contain letters, spaces and accents")
    @NotNull
    private String givenName;
    private String middleName;
    @NotNull
    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The last name can only contain letters, spaces and accents")
    private String lastName;
    private String secondLastName;
    private String nameSuffixCode;
    private String birthName;
    private List<String> aliases;
    
    public String getNamePrefixCode() {
        return namePrefixCode;
    }
    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    public String getNameSuffixCode() {
        return nameSuffixCode;
    }
    public void setNameSuffixCode(String nameSuffixCode) {
        this.nameSuffixCode = nameSuffixCode;
    }
    public String getBirthName() {
        return birthName;
    }
    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @Valid
    @NotNull
    private PersonNameRequestDTO personName;
    private String motherName;
    private String fatherName;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private String genderCode;
    private String birthDate;
    private PlaceOfBirthRequestDTO placeOfBirth;
    private CountryOfResidenceRequestDTO countryOfResidence;
    private FirstNationalityRequestDTO firstNationality;
    private SecondNationalityRequestDTO secondNationality;
    private String residentialStatusCode;
    private String civilStatusCode;    
    private String staffCode;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private PublicOfficeInformationRequestDTO publicOfficeInformation;    
    private String educationalLevelCode;
    private String foreignTaxIndicator;
    private String accountingSectorCode;
    private EmploymentInformationRequestDTO employmentInformation;
    private PreferredLanguageRequestDTO preferredLanguage;
    @Valid
    @NotNull
    private List<DocumentRequestDTO> documents;
    
    public PersonNameRequestDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public PlaceOfBirthRequestDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(PlaceOfBirthRequestDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CountryOfResidenceRequestDTO getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(CountryOfResidenceRequestDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public FirstNationalityRequestDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(FirstNationalityRequestDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public SecondNationalityRequestDTO getSecondNationality() {
        return secondNationality;
    }
    public void setSecondNationality(SecondNationalityRequestDTO secondNationality) {
        this.secondNationality = secondNationality;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getCivilStatusCode() {
        return civilStatusCode;
    }
    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public Boolean getLegallyIncapacitated() {
        return legallyIncapacitated;
    }
    public void setLegallyIncapacitated(Boolean legallyIncapacitated) {
        this.legallyIncapacitated = legallyIncapacitated;
    }
    public Boolean getLegallyCapableMinor() {
        return legallyCapableMinor;
    }
    public void setLegallyCapableMinor(Boolean legallyCapableMinor) {
        this.legallyCapableMinor = legallyCapableMinor;
    }
    public Boolean getDiplomatic() {
        return diplomatic;
    }
    public void setDiplomatic(Boolean diplomatic) {
        this.diplomatic = diplomatic;
    }
    public PublicOfficeInformationRequestDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationRequestDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public EmploymentInformationRequestDTO getEmploymentInformation() {
        return employmentInformation;
    }
    public void setEmploymentInformation(EmploymentInformationRequestDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {

    
    private String mobileNumber;
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 3, message = "The International Code must not exceed 3 digits nor less than 2")
    private String internationalCode;

    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    private String townCode;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private String fullAddress;
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
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
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
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
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
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
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
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
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferredLanguageRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    

}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationRequestDTO {
    private String positionCode;
    private ValidityPeriodRequestDTO validityPeriod;
    
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootRequestDTO {
    private PersonRequestDTO person;
    private OrganizationRequestDTO organization;
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    private List<ContactPointRequestDTO> contactPoints;
    
    public PersonRequestDTO getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDTO person) {
        this.person = person;
    }
    public OrganizationRequestDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDTO organization) {
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
    public List<ContactPointRequestDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointRequestDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UseTypeRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAddressRequestDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
}
