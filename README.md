package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

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
