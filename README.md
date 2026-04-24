package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataRequest {

    private String producto;
    private String subProducto;
    private String tarifa;
    private String plazo;
    private String periodoLiquidacion;
    private String valor;
    private String moneda;
    private String puntosAdicionales;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubProducto() {
        return subProducto;
    }

    public void setSubProducto(String subProducto) {
        this.subProducto = subProducto;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPuntosAdicionales() {
        return puntosAdicionales;
    }

    public void setPuntosAdicionales(String puntosAdicionales) {
        this.puntosAdicionales = puntosAdicionales;
    }
}



===============


package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Request {
    
    private TrxHeader cabecera;
    private TrxBP17DataRequest data;

    public TrxBP17Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSesion(session);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setCanal(header.canal);

        this.cabecera.setResultado(header.getResultado());

    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP17DataRequest getData() {
        return data;
    }

    public void setData(TrxBP17DataRequest data) {
        this.data = data;
    }
}
