package com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic;


public class TrxPersonHeader {
    public String rutaServicio;
    public Session sesion;
    public String funcion;
    public Integer secuencia;
    public String canal;
    public String resultado;

    public TrxPersonHeader() {
    }



    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "TrxPersonHeader{" +
                "rutaServicio='" + rutaServicio + '\'' +
                ", sesion=" + sesion +
                ", funcion='" + funcion + '\'' +
                ", secuencia=" + secuencia +
                ", canal='" + canal + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
