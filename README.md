package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Data {
    private String cdtDat;
    private String CERTIFI;
    private String fecha;
    private String secuencia;
    private String RETEN;
    private String secRen;
    private String INTABON;
    private String estado;
    private String numMov;
    private String interesPendienteLiquidar;
    private String pago;
    private String concepto;
    private String valor;

    public String getCdtDat() {
        return cdtDat;
    }

    public void setCdtDat(String cdtDat) {
        this.cdtDat = cdtDat;
    }

    public String getCERTIFI() {
        return CERTIFI;
    }

    public void setCERTIFI(String CERTIFI) {
        this.CERTIFI = CERTIFI;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getRETEN() {
        return RETEN;
    }

    public void setRETEN(String RETEN) {
        this.RETEN = RETEN;
    }

    public String getSecRen() {
        return secRen;
    }

    public void setSecRen(String secRen) {
        this.secRen = secRen;
    }

    public String getINTABON() {
        return INTABON;
    }

    public void setINTABON(String INTABON) {
        this.INTABON = INTABON;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumMov() {
        return numMov;
    }

    public void setNumMov(String numMov) {
        this.numMov = numMov;
    }

    public String getInteresPendienteLiquidar() {
        return interesPendienteLiquidar;
    }

    public void setInteresPendienteLiquidar(String interesPendienteLiquidar) {
        this.interesPendienteLiquidar = interesPendienteLiquidar;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}



============================


package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Response {
    private TrxBP49DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP49DataResponse getData() {
        return data;
    }

    public void setData(TrxBP49DataResponse data) {
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



====================================


package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49DataResponse {
    private ArrayList<TrxBP49Data> movimientos;

    public ArrayList<TrxBP49Data> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<TrxBP49Data> movimientos) {
        this.movimientos = movimientos;
    }
}
