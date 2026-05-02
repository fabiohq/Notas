package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;
    private CenterRequestDTO center;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public CenterRequestDTO getCenter() {
        return center;
    }

    public void setCenter(CenterRequestDTO center) {
        this.center = center;
    }
}



package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}





package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

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



package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;


import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PersonDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    
    @Valid
    private PersonDTO person;
    private OrganizationRequestDto organization;
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDtO electronicAddress;
    private PostalAddressRequestDTO postalAddress;
    private BankRequestDTO bank;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationRequestDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRequestDto organization) {
        this.organization = organization;
    }

    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public ElectronicAddressRequestDtO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressRequestDtO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }

    public PostalAddressRequestDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressRequestDTO postalAddress) {
        this.postalAddress = postalAddress;
    }

    public BankRequestDTO getBank() {
        return bank;
    }

    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;


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




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDtO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDto {
    private String registrationDate;
    private OrganizationNameRequestDTO organizationName;
    private DocumentRequestDTO document;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }

    public DocumentRequestDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
}





package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String givenName;
    private String lastName;
    private String secondLastName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
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
    
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.EmploymentInformationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PlaceOfBirthDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.PublicOfficeInformationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDto {
    @Valid
    @NotNull
    private PersonNameRequestDTO personName;
    private String motherName;
    private String fatherName;
    private String foreignTaxIndicator;
    private String genderCode;
    private String birthDate;
    private PlaceOfBirthDTO placeOfBirth;    
    private CodeNameDTO countryOfResidence;    
    private CodeNameDTO firstNationality;
    private CodeNameDTO secondNationality;
    private String residentialStatusCode;
    private String civilStatusCode;    
    private String staffCode;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private PublicOfficeInformationDTO publicOfficeInformation;    
    private String educationalLevelCode;
    private String accountingSectorCode;
    private EmploymentInformationDTO employmentInformation;
    private CodeNameDTO preferredLanguage;

    @Valid
    @NotNull
    private List<DocumentRequestDTO> documents;
    private DocumentDTO document; //Customer Search

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
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
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
    public PublicOfficeInformationDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
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
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }
    public DocumentDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
    
    
    
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {
    private String mobileNumber;
    private String phoneNumber;
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




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private ProvinceRequestDTO province;
    private String townName;
    private CountryRequestDTO country;

    public ProvinceRequestDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public CountryRequestDTO getCountry() {
        return country;
    }

    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
}




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

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




package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request;

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
