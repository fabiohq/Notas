package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49DataRequest {
    private String buscarPor;
    private String ent;
    private String ofic;
    private String cuenta;
    private String secuencia;
    private String numeroCertificado;
    private String documentoCajero;

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getOfic() {
        return ofic;
    }

    public void setOfic(String ofic) {
        this.ofic = ofic;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public String getDocumentoCajero() {
        return documentoCajero;
    }

    public void setDocumentoCajero(String documentoCajero) {
        this.documentoCajero = documentoCajero;
    }
}




====================



package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Request {
    private TrxHeader cabecera;
    private TrxBP49DataRequest data;

    public TrxBP49Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);

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

    public TrxBP49DataRequest getData() {
        return data;
    }

    public void setData(TrxBP49DataRequest data) {
        this.data = data;
    }
}
