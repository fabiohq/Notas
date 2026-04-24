package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Request {

    private TrxHeader cabecera;
    private Bp01DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Bp01DataRequest getData() {
        return data;
    }

    public void setData(Bp01DataRequest data) {
        this.data = data;
    }

    public TrxBp01Request(TrxPersonHeader header){
        var session= new Session();
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

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }
}





===========================




package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01SesionRequest {
    private String usuario;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private String diasRestantesCambioClave;
    private String fechaContable;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getDiasRestantesCambioClave() {
        return diasRestantesCambioClave;
    }

    public void setDiasRestantesCambioClave(String diasRestantesCambioClave) {
        this.diasRestantesCambioClave = diasRestantesCambioClave;
    }

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }
}

===================================================



package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01DataRequest {
    private String nroCliente;
    private String producto;
    private String nroCtaExtAbono;
    private String tipoCtaExtAbono;
    private String diviCtaExtAbono;
    private String bancoCtaExtAbono;
    private String ejecutivoComercial;

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNroCtaExtAbono() {
        return nroCtaExtAbono;
    }

    public void setNroCtaExtAbono(String nroCtaExtAbono) {
        this.nroCtaExtAbono = nroCtaExtAbono;
    }

    public String getTipoCtaExtAbono() {
        return tipoCtaExtAbono;
    }

    public void setTipoCtaExtAbono(String tipoCtaExtAbono) {
        this.tipoCtaExtAbono = tipoCtaExtAbono;
    }

    public String getDiviCtaExtAbono() {
        return diviCtaExtAbono;
    }

    public void setDiviCtaExtAbono(String diviCtaExtAbono) {
        this.diviCtaExtAbono = diviCtaExtAbono;
    }

    public String getBancoCtaExtAbono() {
        return bancoCtaExtAbono;
    }

    public void setBancoCtaExtAbono(String bancoCtaExtAbono) {
        this.bancoCtaExtAbono = bancoCtaExtAbono;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }
}



====================================

package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01HeaderRequest {
    private String rutaServicio;
    private Bp01SesionRequest sesion;
    private String funcion;
    private String secuencia;
    private String canal;

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public Bp01SesionRequest getSesion() {
        return sesion;
    }

    public void setSesion(Bp01SesionRequest sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
}
