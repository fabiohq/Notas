package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31DataRequest {
    private String tipoDocumento;
    private String numDocumento;
    private String oficina;
    private String codigoInversor;
    private String nroCliente;
    private String ejecutivoComercial;
    private String indicadorEstado;
    private String tipoCustodia;
    private String tipoFecha;
    private String fechaDesde;
    private String fechaHasta;
    private String cccReposicionam;
    private String secuenciaReposicion;
    private String secRenovReposic;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getIndicadorEstado() {
        return indicadorEstado;
    }

    public void setIndicadorEstado(String indicadorEstado) {
        this.indicadorEstado = indicadorEstado;
    }

    public String getTipoCustodia() {
        return tipoCustodia;
    }

    public void setTipoCustodia(String tipoCustodia) {
        this.tipoCustodia = tipoCustodia;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getCccReposicionam() {
        return cccReposicionam;
    }

    public void setCccReposicionam(String cccReposicionam) {
        this.cccReposicionam = cccReposicionam;
    }

    public String getSecuenciaReposicion() {
        return secuenciaReposicion;
    }

    public void setSecuenciaReposicion(String secuenciaReposicion) {
        this.secuenciaReposicion = secuenciaReposicion;
    }

    public String getSecRenovReposic() {
        return secRenovReposic;
    }

    public void setSecRenovReposic(String secRenovReposic) {
        this.secRenovReposic = secRenovReposic;
    }
}




============



package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31Request {
    
    private TrxHeader cabecera;
    private TrxBP31DataRequest data;

    public TrxBP31Request(TrxPersonHeader header){
        
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

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP31DataRequest getData() {
        return data;
    }

    public void setData(TrxBP31DataRequest data) {
        this.data = data;
    }
}
