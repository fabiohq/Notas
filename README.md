package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersRequest {
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
