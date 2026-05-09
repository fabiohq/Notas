package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

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

package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactTimeDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentDTO {
    private String legitimateCode;
    private String legitimateDescription;
    private String treatmentCode;
    private String treatmentDescription;
    private String purposeCode;
    private String purposeDescription;
    
    public String getLegitimateCode() {
        return legitimateCode;
    }
    public void setLegitimateCode(String legitimateCode) {
        this.legitimateCode = legitimateCode;
    }
    public String getLegitimateDescription() {
        return legitimateDescription;
    }
    public void setLegitimateDescription(String legitimateDescription) {
        this.legitimateDescription = legitimateDescription;
    }
    public String getTreatmentCode() {
        return treatmentCode;
    }
    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }
    public String getTreatmentDescription() {
        return treatmentDescription;
    }
    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }
    public String getPurposeCode() {
        return purposeCode;
    }
    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }
    public String getPurposeDescription() {
        return purposeDescription;
    }
    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointDTO {
    private String contactPointId;
    private List<UseTypeDTO> useTypes;
    private PhoneAddressDTO phoneAddress;
    private Boolean preferredIndicator;
    private Boolean primaryIndicator;
    private ElectronicAddressDTO electronicAddress;
    private PostalAddressDTO postalAddress;
    
    public String getContactPointId() {
        return contactPointId;
    }
    public void setContactPointId(String contactPointId) {
        this.contactPointId = contactPointId;
    }
    public List<UseTypeDTO> getUseTypes() {
        return useTypes;
    }
    public void setUseTypes(List<UseTypeDTO> useTypes) {
        this.useTypes = useTypes;
    }
    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
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
    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
    

 }


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointsResponseDTO {
    private List<ContactPointDTO> contactPoints;

    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountyIdentificationDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LastDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinksDTO {
    private FirstDTO first;
    private PrevDTO prev;
    private NextDTO next;
    private LastDTO last;
    
    public FirstDTO getFirst() {
        return first;
    }
    public void setFirst(FirstDTO first) {
        this.first = first;
    }
    public PrevDTO getPrev() {
        return prev;
    }
    public void setPrev(PrevDTO prev) {
        this.prev = prev;
    }
    public NextDTO getNext() {
        return next;
    }
    public void setNext(NextDTO next) {
        this.next = next;
    }
    public LastDTO getLast() {
        return last;
    }
    public void setLast(LastDTO last) {
        this.last = last;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NextDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    private String townName;
    private StateDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private ProvinceDTO province;
    private RegionIdentificationDTO regionIdentification;
    private CountyIdentificationDTO countyIdentification;
    private CountryDTO country;
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
    public Boolean getIsAddressValidated() {
        return isAddressValidated;
    }
    public void setIsAddressValidated(Boolean isAddressValidated) {
        this.isAddressValidated = isAddressValidated;
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
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public StateDTO getState() {
        return state;
    }
    public void setState(StateDTO state) {
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
    public ProvinceDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceDTO province) {
        this.province = province;
    }
    public RegionIdentificationDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryDTO getCountry() {
        return country;
    }
    public void setCountry(CountryDTO country) {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionIdentificationDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootDTO {
    private List<ContactPointDTO> contactPoints;
    private LinksDTO links;
    
    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
    public LinksDTO getLinks() {
        return links;
    }
    public void setLinks(LinksDTO links) {
        this.links = links;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UseTypeDTO {
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAddressDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
