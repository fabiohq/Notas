package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull
    @Length(max = 2, message = "Invalid lenght (Max. 2)")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid format")
    private String documentTypeCode;    
    @NotNull(message = "documentNumber dont be null")    
    @Pattern(regexp = "^[0-9]+$", message = "Invalid format")
    @Length(max = 11, message = "Invalid lenght (Max. 11)")
    private String documentNumber;
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
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDto {

    private PersonNameRequestDTO personName;
    private String birthDate;
    @Valid
    private DocumentRequestDTO document;
    
    public PersonNameRequestDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectRequestDTO {
    @Valid
    private PersonRequestDto person;
    private OrganizationRequestDto organization;
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDtO electronicAddress;
    private ContactPointDTO contactPointCustomer;
    
    public PersonRequestDto getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDto person) {
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
    public ContactPointDTO getContactPointCustomer() {
        return contactPointCustomer;
    }
    public void setContactPointCustomer(ContactPointDTO contactPointCustomer) {
        this.contactPointCustomer = contactPointCustomer;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

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
