Sessionsbnc-bsn049-mscontracts > com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto > PepfSessionResponseDTO.java
PepfSessionResponseDTO.java
package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfSessionResponseDTO {
    private String usuario;
    private String terminal;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private int diasRestantesCambioClave;
    private String fechaContable;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getHoraConexion() {
        return horaConexion;
    }

    public void setHoraConexion(String horaConexion) {
        this.horaConexion = horaConexion;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public int getDiasRestantesCambioClave() {
        return diasRestantesCambioClave;
    }

    public void setDiasRestantesCambioClave(int diasRestantesCambioClave) {
        this.diasRestantesCambioClave = diasRestantesCambioClave;
    }

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }
}
Created with JaCoCo 0.8.11.202310140853
