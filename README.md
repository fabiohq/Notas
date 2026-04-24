package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfHeaderResponseDTO {
    private int secuencia;
    private String rutaServicio;
    private PepfSessionResponseDTO sesion;
    private String resultado;

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public PepfSessionResponseDTO getSesion() {
        return sesion;
    }

    public void setSesion(PepfSessionResponseDTO sesion) {
        this.sesion = sesion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
