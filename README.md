package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfDataResponseDTO {
    private PepfPEMFV0AResponseDTO pemfvoaResponse;

    public PepfPEMFV0AResponseDTO getPemfvoaResponse() {
        return pemfvoaResponse;
    }

    public void setPemfvoaResponse(PepfPEMFV0AResponseDTO pemfvoaResponse) {
        this.pemfvoaResponse = pemfvoaResponse;
    }
}
