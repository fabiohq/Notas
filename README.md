package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String usuario;
    private String terminal;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private String diasRestantesCambioClave;
    private String fechaContable;
    private String turno;
    
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
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }


}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonHeader {
    private String rutaServicio;
    private Session sesion;
    private String funcion;
    private Integer secuencia;
    private String canal;
    private String resultado;
    
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

    
}
