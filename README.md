package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCustomerSearchDTO {
    private OrganizationNameCustomerSearchDTO organizationName;
    private DocumentDTO document;
    private String typeCode;
    private String typeDescription;
    private String subtypeCode;
    private String subtypeDescription;
    private String registrationDate;
    
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
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCustomerSearchResponseDTO {
    private PersonNameCustomerSearchResponseDTO personName;
    private ProspectSearchPlaceOfBirthDTO placeOfBirth;
    private CodeNameDTO firstNationality;
    private String birthDate;
    private String genderCode;
    private String genderDescription;
    private CodeNameDTO countryOfResident;
    
    public PersonNameCustomerSearchResponseDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameCustomerSearchResponseDTO personName) {
        this.personName = personName;
    }
    public ProspectSearchPlaceOfBirthDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(ProspectSearchPlaceOfBirthDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CodeNameDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(CodeNameDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
    public CodeNameDTO getCountryOfResident() {
        return countryOfResident;
    }
    public void setCountryOfResident(CodeNameDTO countryOfResident) {
        this.countryOfResident = countryOfResident;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameCustomerSearchResponseDTO {
    private String givenName;
    private String lastName;
    private String secondLastName;
    private String fullName;
    
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
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }   
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchDocumentDTO {
    private String documentTypeCode;
    private String documentTypeDescription;
    private String documentNumber;
    private String issueDate;
    private CodeNameDTO country;
    private CodeNameDTO state;
    
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

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchDTO {
    private String prospectId;
    private PersonDTO person;
    private DocumentDTO document;
    private List<ContactPointDTO> contactPoints;
    
    public String getProspectId() {
        return prospectId;
    }
    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }
    public PersonDTO getPerson() {
        return person;
    }
    public void setPerson(PersonDTO person) {
        this.person = person;
    }
    public DocumentDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchPhoneAddressDTO {
    private String phoneNumber;
    private String internationalCode;
    private String mobileNumber;
    
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
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchPlaceOfBirthDTO {
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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchResponseDTO {
    private List<ProspectSearchDTO> prospects;
    private PaginationDTO pagination;
    
    public List<ProspectSearchDTO> getProspects() {
        return prospects;
    }
    public void setProspects(List<ProspectSearchDTO> prospects) {
        this.prospects = prospects;
    }
    public PaginationDTO getPagination() {
        return pagination;
    }
    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchUseTypesDTO {
    private String code;
    private String description;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
