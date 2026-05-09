package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String creationDate;
    private String lastUpdateDate;
    
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointDTO {
    private String contactPointId;
    private List<CodeNameDTO> useTypes;
    private PostalAddressDTO postalAddress;
    private PhoneAddressDTO phoneAddress;
    private ElectronicAddressDTO electronicAddress;
    private AuditDTO audit;
    
    public String getContactPointId() {
        return contactPointId;
    }
    public void setContactPointId(String contactPointId) {
        this.contactPointId = contactPointId;
    }
    public List<CodeNameDTO> getUseTypes() {
        return useTypes;
    }
    public void setUseTypes(List<CodeNameDTO> useTypes) {
        this.useTypes = useTypes;
    }
    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public AuditDTO getAudit() {
        return audit;
    }
    public void setAudit(AuditDTO audit) {
        this.audit = audit;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.BankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsResponseDTO {

    private PersonDTO person;
    private OrganizationDTO organization;
    private List<ContactPointDTO> contactPoints;
    private Boolean highConfidentialityIndicator;
    private Boolean isPendingExCustomer;
    private String confidentialityLevel;
    private BankDTO bank;
    private List<DataOriginDTO> dataOrigins;
    private String structuralSegmentCode;
    private String structuralSegmentDescription;
    private String structuralSubsegmentCode;
    private String structuralSubsegmentDescription;
    
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
    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
    public Boolean getHighConfidentialityIndicator() {
        return highConfidentialityIndicator;
    }
    public void setHighConfidentialityIndicator(Boolean highConfidentialityIndicator) {
        this.highConfidentialityIndicator = highConfidentialityIndicator;
    }
    public Boolean getIsPendingExCustomer() {
        return isPendingExCustomer;
    }
    public void setIsPendingExCustomer(Boolean isPendingExCustomer) {
        this.isPendingExCustomer = isPendingExCustomer;
    }
    public String getConfidentialityLevel() {
        return confidentialityLevel;
    }
    public void setConfidentialityLevel(String confidentialityLevel) {
        this.confidentialityLevel = confidentialityLevel;
    }
    public BankDTO getBank() {
        return bank;
    }
    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
    public List<DataOriginDTO> getDataOrigins() {
        return dataOrigins;
    }
    public void setDataOrigins(List<DataOriginDTO> dataOrigins) {
        this.dataOrigins = dataOrigins;
    }
    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }
    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }
    public String getStructuralSegmentDescription() {
        return structuralSegmentDescription;
    }
    public void setStructuralSegmentDescription(String structuralSegmentDescription) {
        this.structuralSegmentDescription = structuralSegmentDescription;
    }
    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }
    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }
    public String getStructuralSubsegmentDescription() {
        return structuralSubsegmentDescription;
    }
    public void setStructuralSubsegmentDescription(String structuralSubsegmentDescription) {
        this.structuralSubsegmentDescription = structuralSubsegmentDescription;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOriginDTO {
    private String sourceCode;
    private String sourceDescription;
    private String creationDate;
    
    public String getSourceCode() {
        return sourceCode;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    public String getSourceDescription() {
        return sourceDescription;
    }
    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityDTO {
    private String categoryCode;
    private String categoryDescription;
    private String subCategoryCode;
    private String subCategoryDescription;
    
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    public String getSubCategoryCode() {
        return subCategoryCode;
    }
    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }
    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }
    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationDTO {
    private String statusCode;
    private String statusDescription;
    private EconomicActivityDTO economicActivity;
    private String occupationCode;
    private String occupationDescription;
    private String subActivityCode;
    private String subActivityDescription;
    private String subActivityComments;
    
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusDescription() {
        return statusDescription;
    }
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getOccupationDescription() {
        return occupationDescription;
    }
    public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
    }
    public String getSubActivityCode() {
        return subActivityCode;
    }
    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }
    public String getSubActivityDescription() {
        return subActivityDescription;
    }
    public void setSubActivityDescription(String subActivityDescription) {
        this.subActivityDescription = subActivityDescription;
    }
    public String getSubActivityComments() {
        return subActivityComments;
    }
    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
    private String registrationDate;
    private String entityDisolutionDate;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationDTO placeOfRegistration;
    private OrganizationNameDTO organizationName;
    private String typeCode;
    private String typeDescription;
    private String subtypeCode;
    private String subtypeDescription;
    private List<DocumentDTO> documents;
    private CodeNameDTO countryOfOperation;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EconomicActivityDTO economicActivity;
    private CodeNameDTO preferredLanguage;

    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getEntityDisolutionDate() {
        return entityDisolutionDate;
    }
    public void setEntityDisolutionDate(String entityDisolutionDate) {
        this.entityDisolutionDate = entityDisolutionDate;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }
    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public PlaceOfRegistrationDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }
    public void setPlaceOfRegistration(PlaceOfRegistrationDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }
    public OrganizationNameDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameDTO organizationName) {
        this.organizationName = organizationName;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeDescription() {
        return typeDescription;
    }
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getSubtypeDescription() {
        return subtypeDescription;
    }
    public void setSubtypeDescription(String subtypeDescription) {
        this.subtypeDescription = subtypeDescription;
    }
    public List<DocumentDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }
    public CodeNameDTO getCountryOfOperation() {
        return countryOfOperation;
    }
    public void setCountryOfOperation(CodeNameDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }
    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }
    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private PersonNameDTO personName;
    private String motherName;
    private String fatherName;
    private String genderCode;
    private String genderDescription;
    private String birthDate;
    private PlaceOfBirthDTO placeOfBirth;
    private CodeNameDTO countryOfResidence;
    private String foreignTaxIndicator;
    private CodeNameDTO firstNationality;
    private CodeNameDTO secondNationality;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String civilStatusCode;
    private String civilStatusDescription;
    private PublicOfficeInformationDTO publicOfficeInformation;
    private String deathDate;
    private Boolean employeeIndicator;
    private String staffCode;
    private String staffDescription;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private String educationalLevelCode;
    private String educationalLevelDescription;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EmploymentInformationDTO employmentInformation;
    private CodeNameDTO preferredLanguage;
    private List<DocumentDTO> documents;
    private DocumentDTO document; //Customer Search
    
    public PersonNameDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameDTO personName) {
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
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getGenderDescription() {
        return genderDescription;
    }
    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public PlaceOfBirthDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(PlaceOfBirthDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CodeNameDTO getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(CodeNameDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public CodeNameDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(CodeNameDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public CodeNameDTO getSecondNationality() {
        return secondNationality;
    }
    public void setSecondNationality(CodeNameDTO secondNationality) {
        this.secondNationality = secondNationality;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }
    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }
    public String getCivilStatusCode() {
        return civilStatusCode;
    }
    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }
    public String getCivilStatusDescription() {
        return civilStatusDescription;
    }
    public void setCivilStatusDescription(String civilStatusDescription) {
        this.civilStatusDescription = civilStatusDescription;
    }
    public PublicOfficeInformationDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }
    public Boolean getEmployeeIndicator() {
        return employeeIndicator;
    }
    public void setEmployeeIndicator(Boolean employeeIndicator) {
        this.employeeIndicator = employeeIndicator;
    }
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public String getStaffDescription() {
        return staffDescription;
    }
    public void setStaffDescription(String staffDescription) {
        this.staffDescription = staffDescription;
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
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getEducationalLevelDescription() {
        return educationalLevelDescription;
    }
    public void setEducationalLevelDescription(String educationalLevelDescription) {
        this.educationalLevelDescription = educationalLevelDescription;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }
    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }
    public EmploymentInformationDTO getEmploymentInformation() {
        return employmentInformation;
    }
    public void setEmploymentInformation(EmploymentInformationDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }
    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public List<DocumentDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }
    public DocumentDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    


}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameDTO {
    private String namePrefixCode;
    private String namePrefixDescription;
    private String givenName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String nameSuffixCode;
    private String nameSuffixDescription;
    private String fullName;
    private String birthName;
    private List<String> aliases;
    
    public String getNamePrefixCode() {
        return namePrefixCode;
    }
    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }
    public String getNamePrefixDescription() {
        return namePrefixDescription;
    }
    public void setNamePrefixDescription(String namePrefixDescription) {
        this.namePrefixDescription = namePrefixDescription;
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
    public String getNameSuffixDescription() {
        return nameSuffixDescription;
    }
    public void setNameSuffixDescription(String nameSuffixDescription) {
        this.nameSuffixDescription = nameSuffixDescription;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String faxNumber;
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
    public String getFaxNumber() {
        return faxNumber;
    }
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
    
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationDTO {
    private String positionCode;
    private String positionDescription;
    private ValidityPeriodDTO validityPeriod;
    
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionDescription() {
        return positionDescription;
    }
    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
    public ValidityPeriodDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodDTO {
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
