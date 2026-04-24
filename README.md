package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21Response {
    private TrxBP21DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP21DataResponse getData() {
        return data;
    }

    public void setData(TrxBP21DataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Object getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Object paginacion) {
        this.paginacion = paginacion;
    }

    public List<Object> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<Object> avisos) {
        this.avisos = avisos;
    }

    public List<ErrorTrxDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorTrxDTO> errores) {
        this.errores = errores;
    }

    public Object getConexion() {
        return conexion;
    }

    public void setConexion(Object conexion) {
        this.conexion = conexion;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}






=====================================




package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21DataResponse {

    private String saldoDesde;
    private String saldoHasta;
    private String diasDesde;
    private String diasHasta;
    private String tasaEfectiva;
    private String tasaNominal;

    public String getSaldoDesde() {
        return saldoDesde;
    }

    public void setSaldoDesde(String saldoDesde) {
        this.saldoDesde = saldoDesde;
    }

    public String getSaldoHasta() {
        return saldoHasta;
    }

    public void setSaldoHasta(String saldoHasta) {
        this.saldoHasta = saldoHasta;
    }

    public String getDiasDesde() {
        return diasDesde;
    }

    public void setDiasDesde(String diasDesde) {
        this.diasDesde = diasDesde;
    }

    public String getDiasHasta() {
        return diasHasta;
    }

    public void setDiasHasta(String diasHasta) {
        this.diasHasta = diasHasta;
    }

    public String getTasaEfectiva() {
        return tasaEfectiva;
    }

    public void setTasaEfectiva(String tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    public String getTasaNominal() {
        return tasaNominal;
    }

    public void setTasaNominal(String tasaNominal) {
        this.tasaNominal = tasaNominal;
    }
}
