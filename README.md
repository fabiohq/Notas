package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContextRequest {
    private String key;
    private Object value;
    private String product;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContextResponse {
    private String key;
    private Object value;
    private String product;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * params from properties.yml
 */
@AllArgsConstructor
@NoArgsConstructor
public class ApiEntry {
    private String integrationType;
    private String host;
    private String port;
    private boolean https;
    private String endpoint;
    private Integer timeOutConn;
    private Integer timeOutRead;

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isHttps() {
        return https;
    }

    public void setHttps(boolean https) {
        this.https = https;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getTimeOutConn() {
        return timeOutConn;
    }

    public void setTimeOutConn(Integer timeOutConn) {
        this.timeOutConn = timeOutConn;
    }

    public Integer getTimeOutRead() {
        return timeOutRead;
    }

    public void setTimeOutRead(Integer timeOutRead) {
        this.timeOutRead = timeOutRead;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration;

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


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters;

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


package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeographiesParametersResponseDTO {
    private List<DataListDTO> parameters;

    public List<DataListDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<DataListDTO> parameters) {
        this.parameters = parameters;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.enums;

public enum ClientEnum {
    PEF1("ingresoAltaPersonaNatural"),
    PEF2("modificarMantencionPersonaNaturalDatosBasicos"),
    PEF3("ConsultaDatosBasicosPNatural"),
    PEFT("modificarMantencionPersonaNaturalReferencias"),
    PEFP("modificarMantencionPersonaNaturalInfAdicional"),
    PEFV("modificarMantencionPersonaNaturalInfAdicional"),
    PE37("AltaPersonaNatural2"),
    PEF4("modificarMantencionPersonaNaturalInfComplementariaDos"),
    PEF8("modificarMantencionPersonaNaturalActivivadEconomica2"),
    
    MQROUTE("QCTFD");

    private final String path;

    ClientEnum(String path) {
        this.path = path;
    }

    public String value() {
        return path;
    }
}


package com.santander.bnc.bsn049.bncbsn049mscustomer.enums;

public enum ParametersEnums {
    TOWNS("0008"),
    COUNTRY("0112"),
    STATES("0009"),
    WAY_TYPE("0314"),
    CIVIL_STATE("0116"),
    LIST_BCO_EXT("0026"),
    DOCU_TYPE("0113");

    private final String code;

    ParametersEnums(String path) {
        this.code = path;
    }

    public String value() {
        return code;
    }
}


