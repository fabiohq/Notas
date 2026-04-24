package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataRequest {
    private String entidad;
    private String oficina;
    private String cuenta;
    private String numSecuencia;
    private String numCertificado;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumSecuencia() {
        return numSecuencia;
    }

    public void setNumSecuencia(String numSecuencia) {
        this.numSecuencia = numSecuencia;
    }

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }
}






=======================

package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13Request {

    private TrxHeader cabecera;
    private TrxBP13DataRequest data;

    public TrxBP13Request(TrxPersonHeader header){

        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setUsuario(header.sesion.usuario);
        session.setTerminal(header.sesion.terminal);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setEntorno(header.sesion.entorno);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setEntidad("0065");
        session.setDiasRestantesCambioClave(header.sesion.diasRestantesCambioClave);
        session.setFechaContable(header.sesion.fechaContable);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP13DataRequest getData() {
        return data;
    }

    public void setData(TrxBP13DataRequest data) {
        this.data = data;
    }
}




