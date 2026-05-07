package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataListDTO {
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



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeographiesParametersResponseDTO {
    List<DataListDTO> parameters;

    public List<DataListDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<DataListDTO> parameters) {
        this.parameters = parameters;
    }
}
