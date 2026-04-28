package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    
    private String code;
    private String name;
    private String internal;  
    private String isoAlpha3;
    
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
    public String getInternal() {
        return internal;
    }
    public void setInternal(String internal) {
        this.internal = internal;
    }
    public String getIsoAlpha3() {
        return isoAlpha3;
    }
    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }
    
}





*************************

package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataListDTO {
    @JsonIgnore
    private String listCode;
    private String code;
    private String description;

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

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



*****************


package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class SecurityHeaders {
    private String authorization;
    private String xSantanderClientId;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getxSantanderClientId() {
        return xSantanderClientId;
    }

    public void setxSantanderClientId(String xSantanderClientId) {
        this.xSantanderClientId = xSantanderClientId;
    }
}



**********************

package com.santander.bnc.bsn049.bncbsn049mscountries.domain;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
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


************************



package com.santander.bnc.bsn049.bncbsn049mscountries.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TownsDTO {
    @JsonIgnore
    private String listCode;
    private String code;
    private String description;
    private List<StateDTO> states;

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

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

    public List<StateDTO> getStates() {
        return states;
    }

    public void setStates(List<StateDTO> states) {
        this.states = states;
    }
}

