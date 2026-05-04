

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


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfo {
    private String paisResidenciaFiscal1;
    private String paisDescripcion;
    private String paisResidenciaFiscal2;
    private String paisDescripcion2;
    private String clasificacionFATCA;
    private String clasificacionCRS;
    private Boolean validacionFATCA;
    private Boolean salario;
    private Boolean pensiones;
    private Boolean prestacionesServicio;
    private Boolean arrendamientos;
    private Boolean donacionHerencia;
    private Boolean honorarios;
    private Boolean mesada;
    private Boolean actividadIndependiente;
    private Boolean otro;
    private String numIdentiTributaria1;
    private String numIdentiTributaria2;
    private String preFormalizacion;
    private Boolean validacionCRS;
    private Boolean selfCertificacion;
    private Boolean contribuyenteVentaColombia;
    private Boolean reportable;
    private Boolean autorizoEnvioInformacion;
    private String canalVenta;
    private String oficial;
    private String sucursal;
    private String uNeg;
    private String sitCliente;
    private String fAltaCliente;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;
    
    public String getPaisResidenciaFiscal1() {
        return paisResidenciaFiscal1;
    }
    public void setPaisResidenciaFiscal1(String paisResidenciaFiscal1) {
        this.paisResidenciaFiscal1 = paisResidenciaFiscal1;
    }
    public String getPaisDescripcion() {
        return paisDescripcion;
    }
    public void setPaisDescripcion(String paisDescripcion) {
        this.paisDescripcion = paisDescripcion;
    }
    public String getPaisResidenciaFiscal2() {
        return paisResidenciaFiscal2;
    }
    public void setPaisResidenciaFiscal2(String paisResidenciaFiscal2) {
        this.paisResidenciaFiscal2 = paisResidenciaFiscal2;
    }
    public String getPaisDescripcion2() {
        return paisDescripcion2;
    }
    public void setPaisDescripcion2(String paisDescripcion2) {
        this.paisDescripcion2 = paisDescripcion2;
    }
    public String getClasificacionFATCA() {
        return clasificacionFATCA;
    }
    public void setClasificacionFATCA(String clasificacionFATCA) {
        this.clasificacionFATCA = clasificacionFATCA;
    }
    public String getClasificacionCRS() {
        return clasificacionCRS;
    }
    public void setClasificacionCRS(String clasificacionCRS) {
        this.clasificacionCRS = clasificacionCRS;
    }
    public Boolean getValidacionFATCA() {
        return validacionFATCA;
    }
    public void setValidacionFATCA(Boolean validacionFATCA) {
        this.validacionFATCA = validacionFATCA;
    }
    public Boolean getSalario() {
        return salario;
    }
    public void setSalario(Boolean salario) {
        this.salario = salario;
    }
    public Boolean getPensiones() {
        return pensiones;
    }
    public void setPensiones(Boolean pensiones) {
        this.pensiones = pensiones;
    }
    public Boolean getPrestacionesServicio() {
        return prestacionesServicio;
    }
    public void setPrestacionesServicio(Boolean prestacionesServicio) {
        this.prestacionesServicio = prestacionesServicio;
    }
    public Boolean getArrendamientos() {
        return arrendamientos;
    }
    public void setArrendamientos(Boolean arrendamientos) {
        this.arrendamientos = arrendamientos;
    }
    public Boolean getDonacionHerencia() {
        return donacionHerencia;
    }
    public void setDonacionHerencia(Boolean donacionHerencia) {
        this.donacionHerencia = donacionHerencia;
    }
    public Boolean getHonorarios() {
        return honorarios;
    }
    public void setHonorarios(Boolean honorarios) {
        this.honorarios = honorarios;
    }
    public Boolean getMesada() {
        return mesada;
    }
    public void setMesada(Boolean mesada) {
        this.mesada = mesada;
    }
    public Boolean getActividadIndependiente() {
        return actividadIndependiente;
    }
    public void setActividadIndependiente(Boolean actividadIndependiente) {
        this.actividadIndependiente = actividadIndependiente;
    }
    public Boolean getOtro() {
        return otro;
    }
    public void setOtro(Boolean otro) {
        this.otro = otro;
    }
    public String getNumIdentiTributaria1() {
        return numIdentiTributaria1;
    }
    public void setNumIdentiTributaria1(String numIdentiTributaria1) {
        this.numIdentiTributaria1 = numIdentiTributaria1;
    }
    public String getNumIdentiTributaria2() {
        return numIdentiTributaria2;
    }
    public void setNumIdentiTributaria2(String numIdentiTributaria2) {
        this.numIdentiTributaria2 = numIdentiTributaria2;
    }
    public String getPreFormalizacion() {
        return preFormalizacion;
    }
    public void setPreFormalizacion(String preFormalizacion) {
        this.preFormalizacion = preFormalizacion;
    }
    public Boolean getValidacionCRS() {
        return validacionCRS;
    }
    public void setValidacionCRS(Boolean validacionCRS) {
        this.validacionCRS = validacionCRS;
    }
    public Boolean getSelfCertificacion() {
        return selfCertificacion;
    }
    public void setSelfCertificacion(Boolean selfCertificacion) {
        this.selfCertificacion = selfCertificacion;
    }
    public Boolean getContribuyenteVentaColombia() {
        return contribuyenteVentaColombia;
    }
    public void setContribuyenteVentaColombia(Boolean contribuyenteVentaColombia) {
        this.contribuyenteVentaColombia = contribuyenteVentaColombia;
    }
    public Boolean getReportable() {
        return reportable;
    }
    public void setReportable(Boolean reportable) {
        this.reportable = reportable;
    }
    public Boolean getAutorizoEnvioInformacion() {
        return autorizoEnvioInformacion;
    }
    public void setAutorizoEnvioInformacion(Boolean autorizoEnvioInformacion) {
        this.autorizoEnvioInformacion = autorizoEnvioInformacion;
    }
    public String getCanalVenta() {
        return canalVenta;
    }
    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }
    public String getOficial() {
        return oficial;
    }
    public void setOficial(String oficial) {
        this.oficial = oficial;
    }
    public String getSucursal() {
        return sucursal;
    }
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    public String getuNeg() {
        return uNeg;
    }
    public void setuNeg(String uNeg) {
        this.uNeg = uNeg;
    }
    public String getSitCliente() {
        return sitCliente;
    }
    public void setSitCliente(String sitCliente) {
        this.sitCliente = sitCliente;
    }
    public String getfAltaCliente() {
        return fAltaCliente;
    }
    public void setfAltaCliente(String fAltaCliente) {
        this.fAltaCliente = fAltaCliente;
    }
    public String getNumdoc() {
        return numdoc;
    }
    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicData {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String paisExpedicion;
    private String paisExpedicionDesc;
    private String ciudadExpedicion;
    private String lugardeExpDescripcion;
    private String fechaExpedicion;
    private String paisNacimiento;
    private String paisNacimientoDesc;
    private String nacionalidad;
    private String nacionalidadDesc;
    private String ciudadNacimiento;
    private String lugardeNacimiento;
    private String fechaNacimiento;
    private String sexo;
    private String paisDireccion;
    private String paisDireccionDesc;
    private String departamento;
    private String ciudad;
    private String ciudadDescripcion;
    private String tipoVia;
    private String nombreVia;
    private String descripcionDireccion;
    private String clase;
    private String indicativo;
    private String telefono;
    private String precelular;
    private String celular;
    private String email;
    private Boolean autorizoTelefono;
    private Boolean autorizacionEmail;
    private String agrofic;
    private String codact;
    private String codpaip;
    private String conper;
    private String domant;
    private String entpre;
    private String estciv;
    private String estper;
    private String estrat;
    private String fecalt;
    private String fecfal; 
    private String fecing;
    private String hstamp;
    private String hstamp2;
    private String hstamp3;
    private String hstamp4;
    private String hstamp5;
    private String logdomp;
    private String logtelp;
    private String numper;
    private String precel;
    private String profes;
    private String seccel;
    private String secdoc;
    private String secdomp;
    private String secdotc;
    private String secdotp;
    private String secema;
    private String sectelp;
    private String sucadm;
    private String sucmod;
    private String termod;
    private String tipdomp;
    private String tipocu;
    private String tipper;
    private String tiptelp;
    private String usualt;
    private String usumod;
    
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public String getPaisExpedicion() {
        return paisExpedicion;
    }
    public void setPaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }
    public String getPaisExpedicionDesc() {
        return paisExpedicionDesc;
    }
    public void setPaisExpedicionDesc(String paisExpedicionDesc) {
        this.paisExpedicionDesc = paisExpedicionDesc;
    }
    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }
    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }
    public String getLugardeExpDescripcion() {
        return lugardeExpDescripcion;
    }
    public void setLugardeExpDescripcion(String lugardeExpDescripcion) {
        this.lugardeExpDescripcion = lugardeExpDescripcion;
    }
    public String getFechaExpedicion() {
        return fechaExpedicion;
    }
    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }
    public String getPaisNacimiento() {
        return paisNacimiento;
    }
    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }
    public String getPaisNacimientoDesc() {
        return paisNacimientoDesc;
    }
    public void setPaisNacimientoDesc(String paisNacimientoDesc) {
        this.paisNacimientoDesc = paisNacimientoDesc;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getNacionalidadDesc() {
        return nacionalidadDesc;
    }
    public void setNacionalidadDesc(String nacionalidadDesc) {
        this.nacionalidadDesc = nacionalidadDesc;
    }
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }
    public String getLugardeNacimiento() {
        return lugardeNacimiento;
    }
    public void setLugardeNacimiento(String lugardeNacimiento) {
        this.lugardeNacimiento = lugardeNacimiento;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getPaisDireccion() {
        return paisDireccion;
    }
    public void setPaisDireccion(String paisDireccion) {
        this.paisDireccion = paisDireccion;
    }
    public String getPaisDireccionDesc() {
        return paisDireccionDesc;
    }
    public void setPaisDireccionDesc(String paisDireccionDesc) {
        this.paisDireccionDesc = paisDireccionDesc;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCiudadDescripcion() {
        return ciudadDescripcion;
    }
    public void setCiudadDescripcion(String ciudadDescripcion) {
        this.ciudadDescripcion = ciudadDescripcion;
    }
    public String getTipoVia() {
        return tipoVia;
    }
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    public String getNombreVia() {
        return nombreVia;
    }
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }
    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }
    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getIndicativo() {
        return indicativo;
    }
    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getPrecelular() {
        return precelular;
    }
    public void setPrecelular(String precelular) {
        this.precelular = precelular;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getAutorizoTelefono() {
        return autorizoTelefono;
    }
    public void setAutorizoTelefono(Boolean autorizoTelefono) {
        this.autorizoTelefono = autorizoTelefono;
    }
    public Boolean getAutorizacionEmail() {
        return autorizacionEmail;
    }
    public void setAutorizacionEmail(Boolean autorizacionEmail) {
        this.autorizacionEmail = autorizacionEmail;
    }
    public String getAgrofic() {
        return agrofic;
    }
    public void setAgrofic(String agrofic) {
        this.agrofic = agrofic;
    }
    public String getCodact() {
        return codact;
    }
    public void setCodact(String codact) {
        this.codact = codact;
    }
    public String getCodpaip() {
        return codpaip;
    }
    public void setCodpaip(String codpaip) {
        this.codpaip = codpaip;
    }
    public String getConper() {
        return conper;
    }
    public void setConper(String conper) {
        this.conper = conper;
    }
    public String getDomant() {
        return domant;
    }
    public void setDomant(String domant) {
        this.domant = domant;
    }
    public String getEntpre() {
        return entpre;
    }
    public void setEntpre(String entpre) {
        this.entpre = entpre;
    }
    public String getEstciv() {
        return estciv;
    }
    public void setEstciv(String estciv) {
        this.estciv = estciv;
    }
    public String getEstper() {
        return estper;
    }
    public void setEstper(String estper) {
        this.estper = estper;
    }
    public String getEstrat() {
        return estrat;
    }
    public void setEstrat(String estrat) {
        this.estrat = estrat;
    }
    public String getFecalt() {
        return fecalt;
    }
    public void setFecalt(String fecalt) {
        this.fecalt = fecalt;
    }
    public String getFecfal() {
        return fecfal;
    }
    public void setFecfal(String fecfal) {
        this.fecfal = fecfal;
    }
    public String getFecing() {
        return fecing;
    }
    public void setFecing(String fecing) {
        this.fecing = fecing;
    }
    public String getHstamp() {
        return hstamp;
    }
    public void setHstamp(String hstamp) {
        this.hstamp = hstamp;
    }
    public String getHstamp2() {
        return hstamp2;
    }
    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }
    public String getHstamp3() {
        return hstamp3;
    }
    public void setHstamp3(String hstamp3) {
        this.hstamp3 = hstamp3;
    }
    public String getHstamp4() {
        return hstamp4;
    }
    public void setHstamp4(String hstamp4) {
        this.hstamp4 = hstamp4;
    }
    public String getHstamp5() {
        return hstamp5;
    }
    public void setHstamp5(String hstamp5) {
        this.hstamp5 = hstamp5;
    }
    public String getLogdomp() {
        return logdomp;
    }
    public void setLogdomp(String logdomp) {
        this.logdomp = logdomp;
    }
    public String getLogtelp() {
        return logtelp;
    }
    public void setLogtelp(String logtelp) {
        this.logtelp = logtelp;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getPrecel() {
        return precel;
    }
    public void setPrecel(String precel) {
        this.precel = precel;
    }
    public String getProfes() {
        return profes;
    }
    public void setProfes(String profes) {
        this.profes = profes;
    }
    public String getSeccel() {
        return seccel;
    }
    public void setSeccel(String seccel) {
        this.seccel = seccel;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getSecdomp() {
        return secdomp;
    }
    public void setSecdomp(String secdomp) {
        this.secdomp = secdomp;
    }
    public String getSecdotc() {
        return secdotc;
    }
    public void setSecdotc(String secdotc) {
        this.secdotc = secdotc;
    }
    public String getSecdotp() {
        return secdotp;
    }
    public void setSecdotp(String secdotp) {
        this.secdotp = secdotp;
    }
    public String getSecema() {
        return secema;
    }
    public void setSecema(String secema) {
        this.secema = secema;
    }
    public String getSectelp() {
        return sectelp;
    }
    public void setSectelp(String sectelp) {
        this.sectelp = sectelp;
    }
    public String getSucadm() {
        return sucadm;
    }
    public void setSucadm(String sucadm) {
        this.sucadm = sucadm;
    }
    public String getSucmod() {
        return sucmod;
    }
    public void setSucmod(String sucmod) {
        this.sucmod = sucmod;
    }
    public String getTermod() {
        return termod;
    }
    public void setTermod(String termod) {
        this.termod = termod;
    }
    public String getTipdomp() {
        return tipdomp;
    }
    public void setTipdomp(String tipdomp) {
        this.tipdomp = tipdomp;
    }
    public String getTipocu() {
        return tipocu;
    }
    public void setTipocu(String tipocu) {
        this.tipocu = tipocu;
    }
    public String getTipper() {
        return tipper;
    }
    public void setTipper(String tipper) {
        this.tipper = tipper;
    }
    public String getTiptelp() {
        return tiptelp;
    }
    public void setTiptelp(String tiptelp) {
        this.tiptelp = tiptelp;
    }
    public String getUsualt() {
        return usualt;
    }
    public void setUsualt(String usualt) {
        this.usualt = usualt;
    }
    public String getUsumod() {
        return usumod;
    }
    public void setUsumod(String usumod) {
        this.usumod = usumod;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplementaryInfo {
    private String estadoCivil;
    private String tipoVivienda;
    private String estrato;
    private String anios;
    private String mes;
    private String anios2;
    private String nivelEstudios;
    private String personasCargo;
    private String numHijos;
    private String hstamp1;
    private String hstamp2;
    private String lugnac;
    private String numper2;
    private String numintp;
    private String seccel;
    private String secdoc;
    private String secdotc;
    private String secdotp;
    private String secema;
    private String sectelp;
    private String tipdoc;
    
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getTipoVivienda() {
        return tipoVivienda;
    }
    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
    public String getEstrato() {
        return estrato;
    }
    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }
    public String getAnios() {
        return anios;
    }
    public void setAnios(String anios) {
        this.anios = anios;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public String getAnios2() {
        return anios2;
    }
    public void setAnios2(String anios2) {
        this.anios2 = anios2;
    }
    public String getNivelEstudios() {
        return nivelEstudios;
    }
    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }
    public String getPersonasCargo() {
        return personasCargo;
    }
    public void setPersonasCargo(String personasCargo) {
        this.personasCargo = personasCargo;
    }
    public String getNumHijos() {
        return numHijos;
    }
    public void setNumHijos(String numHijos) {
        this.numHijos = numHijos;
    }
    public String getHstamp1() {
        return hstamp1;
    }
    public void setHstamp1(String hstamp1) {
        this.hstamp1 = hstamp1;
    }
    public String getHstamp2() {
        return hstamp2;
    }
    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }
    public String getLugnac() {
        return lugnac;
    }
    public void setLugnac(String lugnac) {
        this.lugnac = lugnac;
    }
    public String getNumper2() {
        return numper2;
    }
    public void setNumper2(String numper2) {
        this.numper2 = numper2;
    }
    public String getNumintp() {
        return numintp;
    }
    public void setNumintp(String numintp) {
        this.numintp = numintp;
    }
    public String getSeccel() {
        return seccel;
    }
    public void setSeccel(String seccel) {
        this.seccel = seccel;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getSecdotc() {
        return secdotc;
    }
    public void setSecdotc(String secdotc) {
        this.secdotc = secdotc;
    }
    public String getSecdotp() {
        return secdotp;
    }
    public void setSecdotp(String secdotp) {
        this.secdotp = secdotp;
    }
    public String getSecema() {
        return secema;
    }
    public void setSecema(String secema) {
        this.secema = secema;
    }
    public String getSectelp() {
        return sectelp;
    }
    public void setSectelp(String sectelp) {
        this.sectelp = sectelp;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomyActivity {
    private String cargo;
    private String descCargo;
    private String tipoVia;
    private String departamento;
    private String tipoContrato;
    private String pais;
    private String paisDescripcion;
    private String ciudad;
    private String ciudadDescripcion;
    private String ocupacion;
    private String descOcupacion;
    private String actiEconomica;
    private String descActiEconomica;
    private String antiguedadAnio;
    private String antiguedadMes;
    private String nombreEmpresa;
    private String nit;
    private String fechaIngreso;
    private String fecha2;
    private String nombreVia;
    private String descripcionDireccion;
    private String indicativo;
    private String telefono;
    private String opcionActividad;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;
    
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getDescCargo() {
        return descCargo;
    }
    public void setDescCargo(String descCargo) {
        this.descCargo = descCargo;
    }
    public String getTipoVia() {
        return tipoVia;
    }
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getPaisDescripcion() {
        return paisDescripcion;
    }
    public void setPaisDescripcion(String paisDescripcion) {
        this.paisDescripcion = paisDescripcion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCiudadDescripcion() {
        return ciudadDescripcion;
    }
    public void setCiudadDescripcion(String ciudadDescripcion) {
        this.ciudadDescripcion = ciudadDescripcion;
    }
    public String getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public String getDescOcupacion() {
        return descOcupacion;
    }
    public void setDescOcupacion(String descOcupacion) {
        this.descOcupacion = descOcupacion;
    }
    public String getActiEconomica() {
        return actiEconomica;
    }
    public void setActiEconomica(String actiEconomica) {
        this.actiEconomica = actiEconomica;
    }
    public String getDescActiEconomica() {
        return descActiEconomica;
    }
    public void setDescActiEconomica(String descActiEconomica) {
        this.descActiEconomica = descActiEconomica;
    }
    public String getAntiguedadAnio() {
        return antiguedadAnio;
    }
    public void setAntiguedadAnio(String antiguedadAnio) {
        this.antiguedadAnio = antiguedadAnio;
    }
    public String getAntiguedadMes() {
        return antiguedadMes;
    }
    public void setAntiguedadMes(String antiguedadMes) {
        this.antiguedadMes = antiguedadMes;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getFecha2() {
        return fecha2;
    }
    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }
    public String getNombreVia() {
        return nombreVia;
    }
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }
    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }
    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }
    public String getIndicativo() {
        return indicativo;
    }
    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getOpcionActividad() {
        return opcionActividad;
    }
    public void setOpcionActividad(String opcionActividad) {
        this.opcionActividad = opcionActividad;
    }
    public String getNumdoc() {
        return numdoc;
    }
    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInformation {
    private int ingresosFijos;
    private int otrosIngresos1;
    private int totalIngresos;
    private int cuotasCreditos;
    private int otrosEgresos;
    private int totalEgresos;
    private int bienesRaices;
    private int otrosBienes;
    private int totalActivos;
    private int saldoTarjetaCredito;
    private int saldoOtrasObligaciones;
    private int totalPasivos;
    private String matricinmuebles;
    private String matricinmuebles2;
    private String matricinmuebles3;
    @JsonProperty("ValorComercial")
    private String valorComercial;
    @JsonProperty("ValorComercial2")
    private String valorComercial2;
    @JsonProperty("ValorComercial3")
    private String valorComercial3;
    @JsonProperty("SaldoHipoteca")
    private String saldoHipoteca;
    @JsonProperty("SaldoHipoteca2")
    private String saldoHipoteca2;
    @JsonProperty("SaldoHipoteca3")
    private String saldoHipoteca3;
    @JsonProperty("Saldo")
    private String saldo;
    @JsonProperty("Saldo2")
    private String saldo2;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;
    private String tipoInmueble;
    
    public int getIngresosFijos() {
        return ingresosFijos;
    }
    public void setIngresosFijos(int ingresosFijos) {
        this.ingresosFijos = ingresosFijos;
    }
    public int getOtrosIngresos1() {
        return otrosIngresos1;
    }
    public void setOtrosIngresos1(int otrosIngresos1) {
        this.otrosIngresos1 = otrosIngresos1;
    }
    public int getTotalIngresos() {
        return totalIngresos;
    }
    public void setTotalIngresos(int totalIngresos) {
        this.totalIngresos = totalIngresos;
    }
    public int getCuotasCreditos() {
        return cuotasCreditos;
    }
    public void setCuotasCreditos(int cuotasCreditos) {
        this.cuotasCreditos = cuotasCreditos;
    }
    public int getOtrosEgresos() {
        return otrosEgresos;
    }
    public void setOtrosEgresos(int otrosEgresos) {
        this.otrosEgresos = otrosEgresos;
    }
    public int getTotalEgresos() {
        return totalEgresos;
    }
    public void setTotalEgresos(int totalEgresos) {
        this.totalEgresos = totalEgresos;
    }
    public int getBienesRaices() {
        return bienesRaices;
    }
    public void setBienesRaices(int bienesRaices) {
        this.bienesRaices = bienesRaices;
    }
    public int getOtrosBienes() {
        return otrosBienes;
    }
    public void setOtrosBienes(int otrosBienes) {
        this.otrosBienes = otrosBienes;
    }
    public int getTotalActivos() {
        return totalActivos;
    }
    public void setTotalActivos(int totalActivos) {
        this.totalActivos = totalActivos;
    }
    public int getSaldoTarjetaCredito() {
        return saldoTarjetaCredito;
    }
    public void setSaldoTarjetaCredito(int saldoTarjetaCredito) {
        this.saldoTarjetaCredito = saldoTarjetaCredito;
    }
    public int getSaldoOtrasObligaciones() {
        return saldoOtrasObligaciones;
    }
    public void setSaldoOtrasObligaciones(int saldoOtrasObligaciones) {
        this.saldoOtrasObligaciones = saldoOtrasObligaciones;
    }
    public int getTotalPasivos() {
        return totalPasivos;
    }
    public void setTotalPasivos(int totalPasivos) {
        this.totalPasivos = totalPasivos;
    }
    public String getMatricinmuebles() {
        return matricinmuebles;
    }
    public void setMatricinmuebles(String matricinmuebles) {
        this.matricinmuebles = matricinmuebles;
    }
    public String getMatricinmuebles2() {
        return matricinmuebles2;
    }
    public void setMatricinmuebles2(String matricinmuebles2) {
        this.matricinmuebles2 = matricinmuebles2;
    }
    public String getMatricinmuebles3() {
        return matricinmuebles3;
    }
    public void setMatricinmuebles3(String matricinmuebles3) {
        this.matricinmuebles3 = matricinmuebles3;
    }
    public String getValorComercial() {
        return valorComercial;
    }
    public void setValorComercial(String valorComercial) {
        this.valorComercial = valorComercial;
    }
    public String getValorComercial2() {
        return valorComercial2;
    }
    public void setValorComercial2(String valorComercial2) {
        this.valorComercial2 = valorComercial2;
    }
    public String getValorComercial3() {
        return valorComercial3;
    }
    public void setValorComercial3(String valorComercial3) {
        this.valorComercial3 = valorComercial3;
    }
    public String getSaldoHipoteca() {
        return saldoHipoteca;
    }
    public void setSaldoHipoteca(String saldoHipoteca) {
        this.saldoHipoteca = saldoHipoteca;
    }
    public String getSaldoHipoteca2() {
        return saldoHipoteca2;
    }
    public void setSaldoHipoteca2(String saldoHipoteca2) {
        this.saldoHipoteca2 = saldoHipoteca2;
    }
    public String getSaldoHipoteca3() {
        return saldoHipoteca3;
    }
    public void setSaldoHipoteca3(String saldoHipoteca3) {
        this.saldoHipoteca3 = saldoHipoteca3;
    }
    public String getSaldo() {
        return saldo;
    }
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    public String getSaldo2() {
        return saldo2;
    }
    public void setSaldo2(String saldo2) {
        this.saldo2 = saldo2;
    }
    public String getNumdoc() {
        return numdoc;
    }
    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
    public String getTipoInmueble() {
        return tipoInmueble;
    }
    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Wilfredo Pena
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationData {
    private String numeroDocumento;
    private String numPersona;
    private String apellidos;
    private String estado;
    private String tipoPersona;
    @JsonProperty("PECODPR")
    private String pECODPR;
    @JsonProperty("PEESTPE")
    private String pEESTPE;
    @JsonProperty("PEFECNA")
    private String pEFECNA;
    @JsonProperty("PEHSTAM")
    private String pEHSTAM;
    @JsonProperty("PEINDAV")
    private String pEINDAV;
    @JsonProperty("PEINDCO")
    private String pEINDCO;
    @JsonProperty("PEINDGR")
    private String pEINDGR;
    @JsonProperty("PEINDN3")
    private String pEINDN3;
    @JsonProperty("PEINDN4")
    private String pEINDN4;
    @JsonProperty("PEINDN5")
    private String pEINDN5;
    @JsonProperty("PEINDRE")
    private String pEINDRE;
    @JsonProperty("PEMARNO")
    private String pEMARNO;
    @JsonProperty("PENOMCA")
    private String pENOMCA;
    @JsonProperty("PENOMLO")
    private String pENOMLO;
    @JsonProperty("PEOBSE1")
    private String pEOBSE1;
    @JsonProperty("PEPRIAP")
    private String pEPRIAP;
    @JsonProperty("PERUTCA")
    private String pERUTCA;
    @JsonProperty("PESECDO")
    private String pESECDO;
    @JsonProperty("PESEGAP")
    private String pESEGAP;
    @JsonProperty("PETIPDO")
    private String pETIPDO;
    @JsonProperty("PETIPVI")
    private String pETIPVI;
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getNumPersona() {
        return numPersona;
    }
    public void setNumPersona(String numPersona) {
        this.numPersona = numPersona;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public String getpECODPR() {
        return pECODPR;
    }
    public void setpECODPR(String pECODPR) {
        this.pECODPR = pECODPR;
    }
    public String getpEESTPE() {
        return pEESTPE;
    }
    public void setpEESTPE(String pEESTPE) {
        this.pEESTPE = pEESTPE;
    }
    public String getpEFECNA() {
        return pEFECNA;
    }
    public void setpEFECNA(String pEFECNA) {
        this.pEFECNA = pEFECNA;
    }
    public String getpEHSTAM() {
        return pEHSTAM;
    }
    public void setpEHSTAM(String pEHSTAM) {
        this.pEHSTAM = pEHSTAM;
    }
    public String getpEINDAV() {
        return pEINDAV;
    }
    public void setpEINDAV(String pEINDAV) {
        this.pEINDAV = pEINDAV;
    }
    public String getpEINDCO() {
        return pEINDCO;
    }
    public void setpEINDCO(String pEINDCO) {
        this.pEINDCO = pEINDCO;
    }
    public String getpEINDGR() {
        return pEINDGR;
    }
    public void setpEINDGR(String pEINDGR) {
        this.pEINDGR = pEINDGR;
    }
    public String getpEINDN3() {
        return pEINDN3;
    }
    public void setpEINDN3(String pEINDN3) {
        this.pEINDN3 = pEINDN3;
    }
    public String getpEINDN4() {
        return pEINDN4;
    }
    public void setpEINDN4(String pEINDN4) {
        this.pEINDN4 = pEINDN4;
    }
    public String getpEINDN5() {
        return pEINDN5;
    }
    public void setpEINDN5(String pEINDN5) {
        this.pEINDN5 = pEINDN5;
    }
    public String getpEINDRE() {
        return pEINDRE;
    }
    public void setpEINDRE(String pEINDRE) {
        this.pEINDRE = pEINDRE;
    }
    public String getpEMARNO() {
        return pEMARNO;
    }
    public void setpEMARNO(String pEMARNO) {
        this.pEMARNO = pEMARNO;
    }
    public String getpENOMCA() {
        return pENOMCA;
    }
    public void setpENOMCA(String pENOMCA) {
        this.pENOMCA = pENOMCA;
    }
    public String getpENOMLO() {
        return pENOMLO;
    }
    public void setpENOMLO(String pENOMLO) {
        this.pENOMLO = pENOMLO;
    }
    public String getpEOBSE1() {
        return pEOBSE1;
    }
    public void setpEOBSE1(String pEOBSE1) {
        this.pEOBSE1 = pEOBSE1;
    }
    public String getpEPRIAP() {
        return pEPRIAP;
    }
    public void setpEPRIAP(String pEPRIAP) {
        this.pEPRIAP = pEPRIAP;
    }
    public String getpERUTCA() {
        return pERUTCA;
    }
    public void setpERUTCA(String pERUTCA) {
        this.pERUTCA = pERUTCA;
    }
    public String getpESECDO() {
        return pESECDO;
    }
    public void setpESECDO(String pESECDO) {
        this.pESECDO = pESECDO;
    }
    public String getpESEGAP() {
        return pESEGAP;
    }
    public void setpESEGAP(String pESEGAP) {
        this.pESEGAP = pESEGAP;
    }
    public String getpETIPDO() {
        return pETIPDO;
    }
    public void setpETIPDO(String pETIPDO) {
        this.pETIPDO = pETIPDO;
    }
    public String getpETIPVI() {
        return pETIPVI;
    }
    public void setpETIPVI(String pETIPVI) {
        this.pETIPVI = pETIPVI;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternationalOperations {
    private String realizaOperaMoneExtran;
    private Boolean inversiones;
    private Boolean giros;
    private Boolean creditos;
    private Boolean importaciones;
    private Boolean exportaciones;
    private Boolean otro;
    private String tieneProdMoneExtraje;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;
    
    public String getRealizaOperaMoneExtran() {
        return realizaOperaMoneExtran;
    }
    public void setRealizaOperaMoneExtran(String realizaOperaMoneExtran) {
        this.realizaOperaMoneExtran = realizaOperaMoneExtran;
    }
    public Boolean getInversiones() {
        return inversiones;
    }
    public void setInversiones(Boolean inversiones) {
        this.inversiones = inversiones;
    }
    public Boolean getGiros() {
        return giros;
    }
    public void setGiros(Boolean giros) {
        this.giros = giros;
    }
    public Boolean getCreditos() {
        return creditos;
    }
    public void setCreditos(Boolean creditos) {
        this.creditos = creditos;
    }
    public Boolean getImportaciones() {
        return importaciones;
    }
    public void setImportaciones(Boolean importaciones) {
        this.importaciones = importaciones;
    }
    public Boolean getExportaciones() {
        return exportaciones;
    }
    public void setExportaciones(Boolean exportaciones) {
        this.exportaciones = exportaciones;
    }
    public Boolean getOtro() {
        return otro;
    }
    public void setOtro(Boolean otro) {
        this.otro = otro;
    }
    public String getTieneProdMoneExtraje() {
        return tieneProdMoneExtraje;
    }
    public void setTieneProdMoneExtraje(String tieneProdMoneExtraje) {
        this.tieneProdMoneExtraje = tieneProdMoneExtraje;
    }
    public String getNumdoc() {
        return numdoc;
    }
    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class References {
    private String parentesco;
    private String nombre;
    private String primerApellido;
    private String ciudad;
    private String ciudadReferencia;
    private String indictivo;
    private String telefono;
    private String direccion;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String secref1;
    private String tipdoc;
    
    public String getParentesco() {
        return parentesco;
    }
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCiudadReferencia() {
        return ciudadReferencia;
    }
    public void setCiudadReferencia(String ciudadReferencia) {
        this.ciudadReferencia = ciudadReferencia;
    }
    public String getIndictivo() {
        return indictivo;
    }
    public void setIndictivo(String indictivo) {
        this.indictivo = indictivo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNumdoc() {
        return numdoc;
    }
    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public String getSecref1() {
        return secref1;
    }
    public void setSecref1(String secref1) {
        this.secref1 = secref1;
    }
    public String getTipdoc() {
        return tipdoc;
    }
    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    
}




package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonDataRequest {
    @JsonProperty("PENUMPE")
    private String pENUMPE;
    private String tipoInmueble;
    private BasicData datosBasicos;
    private ComplementaryInfo infComplementaria;
    private EconomyActivity actividadEconomica;
    private FinancialInformation infFinanciera;
    private References referencias;
    private AdditionalInfo infAdicional;
    private InternationalOperations operacionesInternacionales;
    private String documentoCajero;
    private String tipoDocumento;
    private String numDocumento;
    private String nombre;
    private List<IdentificationData> datosIdentificacion;
    
    public String getpENUMPE() {
        return pENUMPE;
    }
    public void setpENUMPE(String pENUMPE) {
        this.pENUMPE = pENUMPE;
    }
    public String getTipoInmueble() {
        return tipoInmueble;
    }
    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
    public BasicData getDatosBasicos() {
        return datosBasicos;
    }
    public void setDatosBasicos(BasicData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }
    public ComplementaryInfo getInfComplementaria() {
        return infComplementaria;
    }
    public void setInfComplementaria(ComplementaryInfo infComplementaria) {
        this.infComplementaria = infComplementaria;
    }
    public EconomyActivity getActividadEconomica() {
        return actividadEconomica;
    }
    public void setActividadEconomica(EconomyActivity actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }
    public FinancialInformation getInfFinanciera() {
        return infFinanciera;
    }
    public void setInfFinanciera(FinancialInformation infFinanciera) {
        this.infFinanciera = infFinanciera;
    }
    public References getReferencias() {
        return referencias;
    }
    public void setReferencias(References referencias) {
        this.referencias = referencias;
    }
    public AdditionalInfo getInfAdicional() {
        return infAdicional;
    }
    public void setInfAdicional(AdditionalInfo infAdicional) {
        this.infAdicional = infAdicional;
    }
    public InternationalOperations getOperacionesInternacionales() {
        return operacionesInternacionales;
    }
    public void setOperacionesInternacionales(InternationalOperations operacionesInternacionales) {
        this.operacionesInternacionales = operacionesInternacionales;
    }
    public String getDocumentoCajero() {
        return documentoCajero;
    }
    public void setDocumentoCajero(String documentoCajero) {
        this.documentoCajero = documentoCajero;
    }
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<IdentificationData> getDatosIdentificacion() {
        return datosIdentificacion;
    }
    public void setDatosIdentificacion(List<IdentificationData> datosIdentificacion) {
        this.datosIdentificacion = datosIdentificacion;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonRequest {
    private TrxPersonHeader cabecera;
    private TrxPersonDataRequest data;
    
    public TrxPersonHeader getCabecera() {
        return cabecera;
    }
    public void setCabecera(TrxPersonHeader cabecera) {
        this.cabecera = cabecera;
    }
    public TrxPersonDataRequest getData() {
        return data;
    }
    public void setData(TrxPersonDataRequest data) {
        this.data = data;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseTrxDTO {
  private List<ErrorTrxDTO> errores;

  public List<ErrorTrxDTO> getErrores() {
    return errores;
  }
  public void setErrores(List<ErrorTrxDTO> errores) {
    this.errores = errores;
  }

  
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorTrxDTO {

    private String codigo;
    private String mensaje;
    private String transaccion;
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getTransaccion() {
        return transaccion;
    }
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBasicData {
    private TrxPersonData datosBasicos;
    private String numPersona;
    
    public TrxPersonData getDatosBasicos() {
        return datosBasicos;
    }
    public void setDatosBasicos(TrxPersonData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }
    public String getNumPersona() {
        return numPersona;
    }
    public void setNumPersona(String numPersona) {
        this.numPersona = numPersona;
    }
    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonData {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String paisExpedicion;
    private String ciudadExpedicion;
    private String fechaExpedicion;
    private String paisNacimiento;
    private String nacionalidad;
    private String ciudadNacimiento;
    private String fechaNacimiento;
    private String sexo;
    private String paisDireccion;
    private String departamento;
    private String ciudad;
    private String tipoVia;
    private String nombreVia;
    private String descripcionDireccion;
    private String clase;
    private String indicativo;
    private String telefono;
    private String precelular;
    private String celular;
    private String email;
    private String autorizacionEmail;
    private String autorizoTelefono;
    private String agrofic;
    private String codact;
    private String codpaip;
    private String conper;
    private int domant;
    private String entpre;
    private String estciv;
    private String estper;
    private String estrat;
    private String fecalt;
    private String fecfal;
    private String fecing;
    private String hstamp;
    private String hstamp2;
    private String hstamp3;
    private String hstamp4;
    private String hstamp5;
    private String logdomp;
    private String logtelp;
    private String numintp;
    private String numper;
    private String precel;
    private String profes;
    private int seccel;
    private String secdoc;
    private int secdomp;
    private int secdotc;
    private int secdotp;
    private int secema;
    private int sectelp;
    private String sucadm;
    private String sucmod;
    private String termod;
    private String tipdomp;
    private String tipocu;
    private String tipper;
    private String tiptelp;
    private String usualt;
    private String usumod;
    
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public String getPaisExpedicion() {
        return paisExpedicion;
    }
    public void setPaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }
    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }
    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }
    public String getFechaExpedicion() {
        return fechaExpedicion;
    }
    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }
    public String getPaisNacimiento() {
        return paisNacimiento;
    }
    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getPaisDireccion() {
        return paisDireccion;
    }
    public void setPaisDireccion(String paisDireccion) {
        this.paisDireccion = paisDireccion;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getTipoVia() {
        return tipoVia;
    }
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    public String getNombreVia() {
        return nombreVia;
    }
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }
    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }
    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getIndicativo() {
        return indicativo;
    }
    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getPrecelular() {
        return precelular;
    }
    public void setPrecelular(String precelular) {
        this.precelular = precelular;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAutorizacionEmail() {
        return autorizacionEmail;
    }
    public void setAutorizacionEmail(String autorizacionEmail) {
        this.autorizacionEmail = autorizacionEmail;
    }
    public String getAutorizoTelefono() {
        return autorizoTelefono;
    }
    public void setAutorizoTelefono(String autorizoTelefono) {
        this.autorizoTelefono = autorizoTelefono;
    }
    public String getAgrofic() {
        return agrofic;
    }
    public void setAgrofic(String agrofic) {
        this.agrofic = agrofic;
    }
    public String getCodact() {
        return codact;
    }
    public void setCodact(String codact) {
        this.codact = codact;
    }
    public String getCodpaip() {
        return codpaip;
    }
    public void setCodpaip(String codpaip) {
        this.codpaip = codpaip;
    }
    public String getConper() {
        return conper;
    }
    public void setConper(String conper) {
        this.conper = conper;
    }
    public int getDomant() {
        return domant;
    }
    public void setDomant(int domant) {
        this.domant = domant;
    }
    public String getEntpre() {
        return entpre;
    }
    public void setEntpre(String entpre) {
        this.entpre = entpre;
    }
    public String getEstciv() {
        return estciv;
    }
    public void setEstciv(String estciv) {
        this.estciv = estciv;
    }
    public String getEstper() {
        return estper;
    }
    public void setEstper(String estper) {
        this.estper = estper;
    }
    public String getEstrat() {
        return estrat;
    }
    public void setEstrat(String estrat) {
        this.estrat = estrat;
    }
    public String getFecalt() {
        return fecalt;
    }
    public void setFecalt(String fecalt) {
        this.fecalt = fecalt;
    }
    public String getFecfal() {
        return fecfal;
    }
    public void setFecfal(String fecfal) {
        this.fecfal = fecfal;
    }
    public String getFecing() {
        return fecing;
    }
    public void setFecing(String fecing) {
        this.fecing = fecing;
    }
    public String getHstamp() {
        return hstamp;
    }
    public void setHstamp(String hstamp) {
        this.hstamp = hstamp;
    }
    public String getHstamp2() {
        return hstamp2;
    }
    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }
    public String getHstamp3() {
        return hstamp3;
    }
    public void setHstamp3(String hstamp3) {
        this.hstamp3 = hstamp3;
    }
    public String getHstamp4() {
        return hstamp4;
    }
    public void setHstamp4(String hstamp4) {
        this.hstamp4 = hstamp4;
    }
    public String getHstamp5() {
        return hstamp5;
    }
    public void setHstamp5(String hstamp5) {
        this.hstamp5 = hstamp5;
    }
    public String getLogdomp() {
        return logdomp;
    }
    public void setLogdomp(String logdomp) {
        this.logdomp = logdomp;
    }
    public String getLogtelp() {
        return logtelp;
    }
    public void setLogtelp(String logtelp) {
        this.logtelp = logtelp;
    }
    public String getNumintp() {
        return numintp;
    }
    public void setNumintp(String numintp) {
        this.numintp = numintp;
    }
    public String getNumper() {
        return numper;
    }
    public void setNumper(String numper) {
        this.numper = numper;
    }
    public String getPrecel() {
        return precel;
    }
    public void setPrecel(String precel) {
        this.precel = precel;
    }
    public String getProfes() {
        return profes;
    }
    public void setProfes(String profes) {
        this.profes = profes;
    }
    public int getSeccel() {
        return seccel;
    }
    public void setSeccel(int seccel) {
        this.seccel = seccel;
    }
    public String getSecdoc() {
        return secdoc;
    }
    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }
    public int getSecdomp() {
        return secdomp;
    }
    public void setSecdomp(int secdomp) {
        this.secdomp = secdomp;
    }
    public int getSecdotc() {
        return secdotc;
    }
    public void setSecdotc(int secdotc) {
        this.secdotc = secdotc;
    }
    public int getSecdotp() {
        return secdotp;
    }
    public void setSecdotp(int secdotp) {
        this.secdotp = secdotp;
    }
    public int getSecema() {
        return secema;
    }
    public void setSecema(int secema) {
        this.secema = secema;
    }
    public int getSectelp() {
        return sectelp;
    }
    public void setSectelp(int sectelp) {
        this.sectelp = sectelp;
    }
    public String getSucadm() {
        return sucadm;
    }
    public void setSucadm(String sucadm) {
        this.sucadm = sucadm;
    }
    public String getSucmod() {
        return sucmod;
    }
    public void setSucmod(String sucmod) {
        this.sucmod = sucmod;
    }
    public String getTermod() {
        return termod;
    }
    public void setTermod(String termod) {
        this.termod = termod;
    }
    public String getTipdomp() {
        return tipdomp;
    }
    public void setTipdomp(String tipdomp) {
        this.tipdomp = tipdomp;
    }
    public String getTipocu() {
        return tipocu;
    }
    public void setTipocu(String tipocu) {
        this.tipocu = tipocu;
    }
    public String getTipper() {
        return tipper;
    }
    public void setTipper(String tipper) {
        this.tipper = tipper;
    }
    public String getTiptelp() {
        return tiptelp;
    }
    public void setTiptelp(String tiptelp) {
        this.tiptelp = tiptelp;
    }
    public String getUsualt() {
        return usualt;
    }
    public void setUsualt(String usualt) {
        this.usualt = usualt;
    }
    public String getUsumod() {
        return usumod;
    }
    public void setUsumod(String usumod) {
        this.usumod = usumod;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonResponse {
    private TrxBasicData data;
    private TrxPersonHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;
    
    public TrxBasicData getData() {
        return data;
    }
    public void setData(TrxBasicData data) {
        this.data = data;
    }
    public TrxPersonHeader getCabecera() {
        return cabecera;
    }
    public void setCabecera(TrxPersonHeader cabecera) {
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



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxResponseData {
    private TrxBasicData datosBasicos;

    public TrxBasicData getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(TrxBasicData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContextRequest {
    private String key;
    private Object value;
    private String product;
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ContextResponse {
    private String key;
    private Object value;
    private String product;
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * params from properties.yml
 */

@AllArgsConstructor
@NoArgsConstructor
public class ApiEntry {
    private String integrationType;
    private String host;
    private String port;
    private boolean https;
    private String endpoint;
    private Integer timeOutConn;
    private Integer timeOutRead;
    
    public String getIntegrationType() {
        return integrationType;
    }
    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public boolean isHttps() {
        return https;
    }
    public void setHttps(boolean https) {
        this.https = https;
    }
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public Integer getTimeOutConn() {
        return timeOutConn;
    }
    public void setTimeOutConn(Integer timeOutConn) {
        this.timeOutConn = timeOutConn;
    }
    public Integer getTimeOutRead() {
        return timeOutRead;
    }
    public void setTimeOutRead(Integer timeOutRead) {
        this.timeOutRead = timeOutRead;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SecurityHeaders {
    private String authorization;
    private String xSantanderClientId;
    
    public String getAuthorization() {
        return authorization;
    }
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
    public String getxSantanderClientId() {
        return xSantanderClientId;
    }
    public void setxSantanderClientId(String xSantanderClientId) {
        this.xSantanderClientId = xSantanderClientId;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataListDTO {
    private String listCode;
    private String code;
    private String description;
    
    public String getListCode() {
        return listCode;
    }
    public void setListCode(String listCode) {
        this.listCode = listCode;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeographiesParametersResponseDTO {
    List<DataListDTO> parameters;

    public List<DataListDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<DataListDTO> parameters) {
        this.parameters = parameters;
    }
}


***********************************************************************************************
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralNumber;
    
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }
    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }
    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }
    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getDetailCode() {
        return detailCode;
    }
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getPremise() {
        return premise;
    }
    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getAlternativePremise() {
        return alternativePremise;
    }
    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }
    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getMilitary() {
        return military;
    }
    public void setMilitary(String military) {
        this.military = military;
    }
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }
    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }
    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }
    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZip4Code() {
        return zip4Code;
    }
    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }
    public String getRuralTypeCode() {
        return ruralTypeCode;
    }
    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactDayRequestDTO {
    private String day;
    private String fromDateTime;
    private String toDateTime;
    
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactTimeRequestDTO {
    private String fromDateTime;
    private String toDateTime;
    private String bestTimeFrameCode;
    private String bestTimeFrameDescription;
    
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }
    public String getBestTimeFrameCode() {
        return bestTimeFrameCode;
    }
    public void setBestTimeFrameCode(String bestTimeFrameCode) {
        this.bestTimeFrameCode = bestTimeFrameCode;
    }
    public String getBestTimeFrameDescription() {
        return bestTimeFrameDescription;
    }
    public void setBestTimeFrameDescription(String bestTimeFrameDescription) {
        this.bestTimeFrameDescription = bestTimeFrameDescription;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointRequestDTO {
    private List<UseTypeRequestDTO> useTypes;
    private Boolean preferredIndicator;
    private Boolean primaryIndicator;
    private ValidityPeriodRequestDTO validityPeriod;
    private BestContactTimeRequestDTO bestContactTime;
    private List<BestContactDayRequestDTO> bestContactDays;
    private PostalAddressRequestDTO postalAddress;
    @Valid
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDTO electronicAddress;
    private WebAddressRequestDTO webAddress;
    
    public List<UseTypeRequestDTO> getUseTypes() {
        return useTypes;
    }
    public void setUseTypes(List<UseTypeRequestDTO> useTypes) {
        this.useTypes = useTypes;
    }
    public Boolean getPreferredIndicator() {
        return preferredIndicator;
    }
    public void setPreferredIndicator(Boolean preferredIndicator) {
        this.preferredIndicator = preferredIndicator;
    }
    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }
    public void setPrimaryIndicator(Boolean primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    public BestContactTimeRequestDTO getBestContactTime() {
        return bestContactTime;
    }
    public void setBestContactTime(BestContactTimeRequestDTO bestContactTime) {
        this.bestContactTime = bestContactTime;
    }
    public List<BestContactDayRequestDTO> getBestContactDays() {
        return bestContactDays;
    }
    public void setBestContactDays(List<BestContactDayRequestDTO> bestContactDays) {
        this.bestContactDays = bestContactDays;
    }
    public PostalAddressRequestDTO getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(PostalAddressRequestDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
    public ElectronicAddressRequestDTO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressRequestDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public WebAddressRequestDTO getWebAddress() {
        return webAddress;
    }
    public void setWebAddress(WebAddressRequestDTO webAddress) {
        this.webAddress = webAddress;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfOperationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfResidenceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountyIdentificationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProspectRequestDTO {
    @Valid
    @NotNull
    private PersonRequestDTO person;
    private OrganizationRequestDTO organization;    
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    @Valid
    private List<ContactPointRequestDTO> contactPoints;
    
    public PersonRequestDTO getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDTO person) {
        this.person = person;
    }
    public OrganizationRequestDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDTO organization) {
        this.organization = organization;
    }
    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }
    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }
    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }
    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }
    public BankRequestDTO getBank() {
        return bank;
    }
    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }
    public List<ContactPointRequestDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointRequestDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document2RequestDTO {
    private String documentTypeCode;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private StateRequestDTO state;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getIssuerEntity() {
        return issuerEntity;
    }
    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull
    @Size(min = 2, max = 2)
    private String documentTypeCode;
    @NotNull
    @Digits(integer = 11, fraction = 0, message = "documentNumber must be numeric and not exceed 11 digits")
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private String town;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getIssuerEntity() {
        return issuerEntity;
    }
    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityRequestDTO {
    private String subCategoryCode;

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequestDTO {
    private String name;
    private EconomicActivityRequestDTO economicActivity;
    private AddressRequestDTO address;
    private String typeCode;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public AddressRequestDTO getAddress() {
        return address;
    }
    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationRequestDTO {
    private EconomicActivityRequestDTO economicActivity;
    private String occupationCode;
    private String statusCode;
    private String subActivityCode;
    private String subActivityComments;
    private EmployerRequestDTO employer;
    
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getSubActivityCode() {
        return subActivityCode;
    }
    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }
    public String getSubActivityComments() {
        return subActivityComments;
    }
    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }
    public EmployerRequestDTO getEmployer() {
        return employer;
    }
    public void setEmployer(EmployerRequestDTO employer) {
        this.employer = employer;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignTaxisRequestDTO {
    private CountryRequestDTO country;
    private String reasonCode;
    private DocumentRequestDTO document;
    private NoDocumentRequestDTO noDocument;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
    public NoDocumentRequestDTO getNoDocument() {
        return noDocument;
    }
    public void setNoDocument(NoDocumentRequestDTO noDocument) {
        this.noDocument = noDocument;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoDocumentRequestDTO {
    private String reasonCode;
    private String reasonDetails;
    
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public String getReasonDetails() {
        return reasonDetails;
    }
    public void setReasonDetails(String reasonDetails) {
        this.reasonDetails = reasonDetails;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;
    private List<String> tradingNames;
    
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public List<String> getTradingNames() {
        return tradingNames;
    }
    public void setTradingNames(List<String> tradingNames) {
        this.tradingNames = tradingNames;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO {
    private OrganizationNameRequestDTO organizationName;
    private String typeCode;
    private String subtypeCode;
    private String registrationDate;
    private String accountingSectorCode;
    private String residentialStatusCode;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationRequestDTO placeOfRegistration;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private List<DocumentRequestDTO> documents;
    private CountryOfOperationRequestDTO countryOfOperation;
    private EconomicActivityRequestDTO economicActivity;
    private PreferredLanguageRequestDTO preferredLanguage;
    
    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public PlaceOfRegistrationRequestDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }
    public void setPlaceOfRegistration(PlaceOfRegistrationRequestDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }
    public CountryOfOperationRequestDTO getCountryOfOperation() {
        return countryOfOperation;
    }
    public void setCountryOfOperation(CountryOfOperationRequestDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String namePrefixCode;

    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The name can only contain letters, spaces and accents")
    @NotNull
    private String givenName;
    private String middleName;
    @NotNull
    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The last name can only contain letters, spaces and accents")
    private String lastName;
    private String secondLastName;
    private String nameSuffixCode;
    private String birthName;
    private List<String> aliases;
    
    public String getNamePrefixCode() {
        return namePrefixCode;
    }
    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    public String getNameSuffixCode() {
        return nameSuffixCode;
    }
    public void setNameSuffixCode(String nameSuffixCode) {
        this.nameSuffixCode = nameSuffixCode;
    }
    public String getBirthName() {
        return birthName;
    }
    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @Valid
    @NotNull
    private PersonNameRequestDTO personName;
    private String motherName;
    private String fatherName;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private String genderCode;
    private String birthDate;
    private PlaceOfBirthRequestDTO placeOfBirth;
    private CountryOfResidenceRequestDTO countryOfResidence;
    private FirstNationalityRequestDTO firstNationality;
    private SecondNationalityRequestDTO secondNationality;
    private String residentialStatusCode;
    private String civilStatusCode;    
    private String staffCode;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private PublicOfficeInformationRequestDTO publicOfficeInformation;    
    private String educationalLevelCode;
    private String foreignTaxIndicator;
    private String accountingSectorCode;
    private EmploymentInformationRequestDTO employmentInformation;
    private PreferredLanguageRequestDTO preferredLanguage;
    @Valid
    @NotNull
    private List<DocumentRequestDTO> documents;
    
    public PersonNameRequestDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public PlaceOfBirthRequestDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(PlaceOfBirthRequestDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CountryOfResidenceRequestDTO getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(CountryOfResidenceRequestDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public FirstNationalityRequestDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(FirstNationalityRequestDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public SecondNationalityRequestDTO getSecondNationality() {
        return secondNationality;
    }
    public void setSecondNationality(SecondNationalityRequestDTO secondNationality) {
        this.secondNationality = secondNationality;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getCivilStatusCode() {
        return civilStatusCode;
    }
    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public Boolean getLegallyIncapacitated() {
        return legallyIncapacitated;
    }
    public void setLegallyIncapacitated(Boolean legallyIncapacitated) {
        this.legallyIncapacitated = legallyIncapacitated;
    }
    public Boolean getLegallyCapableMinor() {
        return legallyCapableMinor;
    }
    public void setLegallyCapableMinor(Boolean legallyCapableMinor) {
        this.legallyCapableMinor = legallyCapableMinor;
    }
    public Boolean getDiplomatic() {
        return diplomatic;
    }
    public void setDiplomatic(Boolean diplomatic) {
        this.diplomatic = diplomatic;
    }
    public PublicOfficeInformationRequestDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationRequestDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public EmploymentInformationRequestDTO getEmploymentInformation() {
        return employmentInformation;
    }
    public void setEmploymentInformation(EmploymentInformationRequestDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {

    
    private String mobileNumber;
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 3, message = "The International Code must not exceed 3 digits nor less than 2")
    private String internationalCode;

    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    private String townCode;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private String fullAddress;
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralNumber;

    public String getFullAddress() {
        return fullAddress;
    }
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }
    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }
    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }
    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getDetailCode() {
        return detailCode;
    }
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getPremise() {
        return premise;
    }
    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getAlternativePremise() {
        return alternativePremise;
    }
    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }
    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getMilitary() {
        return military;
    }
    public void setMilitary(String military) {
        this.military = military;
    }
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }
    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }
    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }
    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZip4Code() {
        return zip4Code;
    }
    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }
    public String getRuralTypeCode() {
        return ruralTypeCode;
    }
    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferredLanguageRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    

}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationRequestDTO {
    private String positionCode;
    private ValidityPeriodRequestDTO validityPeriod;
    
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootRequestDTO {
    private PersonRequestDTO person;
    private OrganizationRequestDTO organization;
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    private List<ContactPointRequestDTO> contactPoints;
    
    public PersonRequestDTO getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDTO person) {
        this.person = person;
    }
    public OrganizationRequestDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDTO organization) {
        this.organization = organization;
    }
    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }
    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }
    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }
    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }
    public BankRequestDTO getBank() {
        return bank;
    }
    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }
    public List<ContactPointRequestDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointRequestDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UseTypeRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodRequestDTO {
    private String startDate;
    private String endDate;
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAddressRequestDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectCreatedResponseDTO {
    private String prospectId;

    public String getProspectId() {
        return prospectId;
    }

    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }   
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationCommonDTO {
    private String offset;
    private String limit;
    
    public String getOffset() {
        return offset;
    }
    public void setOffset(String offset) {
        this.offset = offset;
    }
    public String getLimit() {
        return limit;
    }
    public void setLimit(String limit) {
        this.limit = limit;
    }    
}

********>>>>>
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {
    private PaginationCommonDTO first;
    private PaginationCommonDTO prev;
    private PaginationCommonDTO next;
    private PaginationCommonDTO last;
    private PaginationCommonDTO self;
    
    public PaginationCommonDTO getFirst() {
        return first;
    }
    public void setFirst(PaginationCommonDTO first) {
        this.first = first;
    }
    public PaginationCommonDTO getPrev() {
        return prev;
    }
    public void setPrev(PaginationCommonDTO prev) {
        this.prev = prev;
    }
    public PaginationCommonDTO getNext() {
        return next;
    }
    public void setNext(PaginationCommonDTO next) {
        this.next = next;
    }
    public PaginationCommonDTO getLast() {
        return last;
    }
    public void setLast(PaginationCommonDTO last) {
        this.last = last;
    }
    public PaginationCommonDTO getSelf() {
        return self;
    }
    public void setSelf(PaginationCommonDTO self) {
        this.self = self;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private String bankId;
    private String bankName;
    
    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameDTO {
    private String code;
    private String name;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private String documentTypeCode;
    private String documentTypeDescription;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }
    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getIssuerEntity() {
        return issuerEntity;
    }
    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }
    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parameters {
    private CodeNameDTO countryNationality;
    private CodeNameDTO countryExp;
    private CodeNameDTO countryBirth;
    private CodeNameDTO countryDir;
    private CodeNameDTO cityStandard;
    private CodeNameDTO cityDepartment;
    private CodeNameDTO cityExp;
    private CodeNameDTO cityBirth;
    private CodeNameDTO town;
    private CodeNameDTO townDocument;
    private String documentTypeDescription;
    private String streetTypeDescription; //Way Type
    
    public CodeNameDTO getCountryNationality() {
        return countryNationality;
    }
    public void setCountryNationality(CodeNameDTO countryNationality) {
        this.countryNationality = countryNationality;
    }
    public CodeNameDTO getCountryExp() {
        return countryExp;
    }
    public void setCountryExp(CodeNameDTO countryExp) {
        this.countryExp = countryExp;
    }
    public CodeNameDTO getCountryBirth() {
        return countryBirth;
    }
    public void setCountryBirth(CodeNameDTO countryBirth) {
        this.countryBirth = countryBirth;
    }
    public CodeNameDTO getCountryDir() {
        return countryDir;
    }
    public void setCountryDir(CodeNameDTO countryDir) {
        this.countryDir = countryDir;
    }
    public CodeNameDTO getCityStandard() {
        return cityStandard;
    }
    public void setCityStandard(CodeNameDTO cityStandard) {
        this.cityStandard = cityStandard;
    }
    public CodeNameDTO getCityDepartment() {
        return cityDepartment;
    }
    public void setCityDepartment(CodeNameDTO cityDepartment) {
        this.cityDepartment = cityDepartment;
    }
    public CodeNameDTO getCityExp() {
        return cityExp;
    }
    public void setCityExp(CodeNameDTO cityExp) {
        this.cityExp = cityExp;
    }
    public CodeNameDTO getCityBirth() {
        return cityBirth;
    }
    public void setCityBirth(CodeNameDTO cityBirth) {
        this.cityBirth = cityBirth;
    }
    public CodeNameDTO getTown() {
        return town;
    }
    public void setTown(CodeNameDTO town) {
        this.town = town;
    }
    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }
    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }
    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }
    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }
    public CodeNameDTO getTownDocument() {
        return townDocument;
    }
    public void setTownDocument(CodeNameDTO townDocument) {
        this.townDocument = townDocument;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String fullAddress;
    private String formatCode;
    private String formatDescription;
    private Boolean isAddressValidated;
    private String matchId;
    private String streetTypeCode;
    private String streetTypeDescription;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String mailDeliverySubLocation;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String department;
    private String subDepartment;
    private String postCodeIdentification;
    private CodeNameDTO town;
    private CodeNameDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private CodeNameDTO province;
    private CodeNameDTO regionIdentification;
    private CodeNameDTO countyIdentification;
    private CodeNameDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private String postBoxTypeDescription;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralTypeDescription;
    private String ruralNumber;
    
    public String getFullAddress() {
        return fullAddress;
    }
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getFormatDescription() {
        return formatDescription;
    }
    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }
    public Boolean getIsAddressValidated() {
        return isAddressValidated;
    }
    public void setIsAddressValidated(Boolean isAddressValidated) {
        this.isAddressValidated = isAddressValidated;
    }
    public String getMatchId() {
        return matchId;
    }
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }
    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }
    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }
    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }
    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }
    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getDetailCode() {
        return detailCode;
    }
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getPremise() {
        return premise;
    }
    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getAlternativePremise() {
        return alternativePremise;
    }
    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getSubDepartment() {
        return subDepartment;
    }
    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public CodeNameDTO getTown() {
        return town;
    }
    public void setTown(CodeNameDTO town) {
        this.town = town;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }
    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public CodeNameDTO getProvince() {
        return province;
    }
    public void setProvince(CodeNameDTO province) {
        this.province = province;
    }
    public CodeNameDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(CodeNameDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CodeNameDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CodeNameDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public String getMilitary() {
        return military;
    }
    public void setMilitary(String military) {
        this.military = military;
    }
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }
    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }
    public String getPostBoxTypeDescription() {
        return postBoxTypeDescription;
    }
    public void setPostBoxTypeDescription(String postBoxTypeDescription) {
        this.postBoxTypeDescription = postBoxTypeDescription;
    }
    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }
    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZip4Code() {
        return zip4Code;
    }
    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }
    public String getRuralTypeCode() {
        return ruralTypeCode;
    }
    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }
    public String getRuralTypeDescription() {
        return ruralTypeDescription;
    }
    public void setRuralTypeDescription(String ruralTypeDescription) {
        this.ruralTypeDescription = ruralTypeDescription;
    }
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }    
}

>>>>>>>>>>>>>>>>>>>>>>
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String creationDate;
    private String lastUpdateDate;
    
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.PostalAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointDTO {
    private String contactPointId;
    private List<CodeNameDTO> useTypes;
    private PostalAddressDTO postalAddress;
    private PhoneAddressDTO phoneAddress;
    private ElectronicAddressDTO electronicAddress;
    private AuditDTO audit;

    public String getContactPointId() {
        return contactPointId;
    }

    public void setContactPointId(String contactPointId) {
        this.contactPointId = contactPointId;
    }

    public List<CodeNameDTO> getUseTypes() {
        return useTypes;
    }

    public void setUseTypes(List<CodeNameDTO> useTypes) {
        this.useTypes = useTypes;
    }

    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }

    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }

    public AuditDTO getAudit() {
        return audit;
    }

    public void setAudit(AuditDTO audit) {
        this.audit = audit;
    }

}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOriginDTO {
    private String sourceCode;
    private String sourceDescription;
    private String creationDate;
    
    public String getSourceCode() {
        return sourceCode;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    public String getSourceDescription() {
        return sourceDescription;
    }
    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityDTO {
    private String categoryCode;
    private String categoryDescription;
    private String subCategoryCode;
    private String subCategoryDescription;
    
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    public String getSubCategoryCode() {
        return subCategoryCode;
    }
    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }
    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }
    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationDTO {
    private String statusCode;
    private String statusDescription;
    private EconomicActivityDTO economicActivity;
    private String occupationCode;
    private String occupationDescription;
    private String subActivityCode;
    private String subActivityDescription;
    private String subActivityComments;
    
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusDescription() {
        return statusDescription;
    }
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getOccupationDescription() {
        return occupationDescription;
    }
    public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
    }
    public String getSubActivityCode() {
        return subActivityCode;
    }
    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }
    public String getSubActivityDescription() {
        return subActivityDescription;
    }
    public void setSubActivityDescription(String subActivityDescription) {
        this.subActivityDescription = subActivityDescription;
    }
    public String getSubActivityComments() {
        return subActivityComments;
    }
    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;




@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
    private String registrationDate;
    private String entityDisolutionDate;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationDTO placeOfRegistration;
    private OrganizationNameDTO organizationName;
    private String typeCode;
    private String typeDescription;
    private String subtypeCode;
    private String subtypeDescription;
    private List<DocumentDTO> documents;
    private CodeNameDTO countryOfOperation;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EconomicActivityDTO economicActivity;
    private CodeNameDTO preferredLanguage;
    
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getEntityDisolutionDate() {
        return entityDisolutionDate;
    }
    public void setEntityDisolutionDate(String entityDisolutionDate) {
        this.entityDisolutionDate = entityDisolutionDate;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }
    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public PlaceOfRegistrationDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }
    public void setPlaceOfRegistration(PlaceOfRegistrationDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }
    public OrganizationNameDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameDTO organizationName) {
        this.organizationName = organizationName;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeDescription() {
        return typeDescription;
    }
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getSubtypeDescription() {
        return subtypeDescription;
    }
    public void setSubtypeDescription(String subtypeDescription) {
        this.subtypeDescription = subtypeDescription;
    }
    public List<DocumentDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }
    public CodeNameDTO getCountryOfOperation() {
        return countryOfOperation;
    }
    public void setCountryOfOperation(CodeNameDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }
    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }
    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameDTO {
    private String legalName;
    private List<String> tradingNames;
    
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public List<String> getTradingNames() {
        return tradingNames;
    }
    public void setTradingNames(List<String> tradingNames) {
        this.tradingNames = tradingNames;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private PersonNameDTO personName;
    private String motherName;
    private String fatherName;
    private String genderCode;
    private String genderDescription;
    private String birthDate;
    private PlaceOfBirthDTO placeOfBirth;
    private CodeNameDTO countryOfResidence;
    private String foreignTaxIndicator;
    private CodeNameDTO firstNationality;
    private CodeNameDTO secondNationality;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String civilStatusCode;
    private String civilStatusDescription;
    private PublicOfficeInformationDTO publicOfficeInformation;
    private String deathDate;
    private Boolean employeeIndicator;
    private String staffCode;
    private String staffDescription;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private String educationalLevelCode;
    private String educationalLevelDescription;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EmploymentInformationDTO employmentInformation;
    private CodeNameDTO preferredLanguage;
    private List<DocumentDTO> documents;
    
    public PersonNameDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameDTO personName) {
        this.personName = personName;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getGenderDescription() {
        return genderDescription;
    }
    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public PlaceOfBirthDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(PlaceOfBirthDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CodeNameDTO getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(CodeNameDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public CodeNameDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(CodeNameDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public CodeNameDTO getSecondNationality() {
        return secondNationality;
    }
    public void setSecondNationality(CodeNameDTO secondNationality) {
        this.secondNationality = secondNationality;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }
    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }
    public String getCivilStatusCode() {
        return civilStatusCode;
    }
    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }
    public String getCivilStatusDescription() {
        return civilStatusDescription;
    }
    public void setCivilStatusDescription(String civilStatusDescription) {
        this.civilStatusDescription = civilStatusDescription;
    }
    public PublicOfficeInformationDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }
    public Boolean getEmployeeIndicator() {
        return employeeIndicator;
    }
    public void setEmployeeIndicator(Boolean employeeIndicator) {
        this.employeeIndicator = employeeIndicator;
    }
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public String getStaffDescription() {
        return staffDescription;
    }
    public void setStaffDescription(String staffDescription) {
        this.staffDescription = staffDescription;
    }
    public Boolean getLegallyIncapacitated() {
        return legallyIncapacitated;
    }
    public void setLegallyIncapacitated(Boolean legallyIncapacitated) {
        this.legallyIncapacitated = legallyIncapacitated;
    }
    public Boolean getLegallyCapableMinor() {
        return legallyCapableMinor;
    }
    public void setLegallyCapableMinor(Boolean legallyCapableMinor) {
        this.legallyCapableMinor = legallyCapableMinor;
    }
    public Boolean getDiplomatic() {
        return diplomatic;
    }
    public void setDiplomatic(Boolean diplomatic) {
        this.diplomatic = diplomatic;
    }
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getEducationalLevelDescription() {
        return educationalLevelDescription;
    }
    public void setEducationalLevelDescription(String educationalLevelDescription) {
        this.educationalLevelDescription = educationalLevelDescription;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }
    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }
    public EmploymentInformationDTO getEmploymentInformation() {
        return employmentInformation;
    }
    public void setEmploymentInformation(EmploymentInformationDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }
    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public List<DocumentDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameDTO {
    private String namePrefixCode;
    private String namePrefixDescription;
    private String givenName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String nameSuffixCode;
    private String nameSuffixDescription;
    private String fullName;
    private String birthName;
    private List<String> aliases;
    
    public String getNamePrefixCode() {
        return namePrefixCode;
    }
    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }
    public String getNamePrefixDescription() {
        return namePrefixDescription;
    }
    public void setNamePrefixDescription(String namePrefixDescription) {
        this.namePrefixDescription = namePrefixDescription;
    }
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    public String getNameSuffixCode() {
        return nameSuffixCode;
    }
    public void setNameSuffixCode(String nameSuffixCode) {
        this.nameSuffixCode = nameSuffixCode;
    }
    public String getNameSuffixDescription() {
        return nameSuffixDescription;
    }
    public void setNameSuffixDescription(String nameSuffixDescription) {
        this.nameSuffixDescription = nameSuffixDescription;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getBirthName() {
        return birthName;
    }
    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String faxNumber;
    private String internationalCode;
    private String extension;
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getFaxNumber() {
        return faxNumber;
    }
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    public String getInternationalCode() {
        return internationalCode;
    }
    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
    
    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;

    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;


import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.BankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Details response dto
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectDetailResponseDTO {

    private PersonDTO person;
    private OrganizationDTO organization;
    private List<ContactPointDTO> contactPoints;
    private Boolean highConfidentialityIndicator;
    private Boolean isPendingExCustomer;
    private String confidentialityLevel;
    private BankDTO bank;
    private List<DataOriginDTO> dataOrigins;
    private String structuralSegmentCode;
    private String structuralSegmentDescription;
    private String structuralSubsegmentCode;
    private String structuralSubsegmentDescription;
    
    public PersonDTO getPerson() {
        return person;
    }
    public void setPerson(PersonDTO person) {
        this.person = person;
    }
    public OrganizationDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
    public Boolean getHighConfidentialityIndicator() {
        return highConfidentialityIndicator;
    }
    public void setHighConfidentialityIndicator(Boolean highConfidentialityIndicator) {
        this.highConfidentialityIndicator = highConfidentialityIndicator;
    }
    public Boolean getIsPendingExCustomer() {
        return isPendingExCustomer;
    }
    public void setIsPendingExCustomer(Boolean isPendingExCustomer) {
        this.isPendingExCustomer = isPendingExCustomer;
    }
    public String getConfidentialityLevel() {
        return confidentialityLevel;
    }
    public void setConfidentialityLevel(String confidentialityLevel) {
        this.confidentialityLevel = confidentialityLevel;
    }
    public BankDTO getBank() {
        return bank;
    }
    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
    public List<DataOriginDTO> getDataOrigins() {
        return dataOrigins;
    }
    public void setDataOrigins(List<DataOriginDTO> dataOrigins) {
        this.dataOrigins = dataOrigins;
    }
    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }
    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }
    public String getStructuralSegmentDescription() {
        return structuralSegmentDescription;
    }
    public void setStructuralSegmentDescription(String structuralSegmentDescription) {
        this.structuralSegmentDescription = structuralSegmentDescription;
    }
    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }
    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }
    public String getStructuralSubsegmentDescription() {
        return structuralSubsegmentDescription;
    }
    public void setStructuralSubsegmentDescription(String structuralSubsegmentDescription) {
        this.structuralSubsegmentDescription = structuralSubsegmentDescription;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationDTO {
    private String positionCode;
    private String positionDescription;
    private ValidityPeriodDTO validityPeriod;
    
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionDescription() {
        return positionDescription;
    }
    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
    public ValidityPeriodDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodDTO {
    private String startDate;
    private String endDate;
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }    
}

>>>>>
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;
    private CenterRequestDTO center;
    
    public String getBankId() {
        return bankId;
    }
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    public CenterRequestDTO getCenter() {
        return center;
    }
    public void setCenter(CenterRequestDTO center) {
        this.center = center;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull
    @Length(max = 2, message = "Invalid lenght (Max. 2)")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid format")
    private String documentTypeCode;    
    @NotNull(message = "documentNumber dont be null")    
    @Pattern(regexp = "^[0-9]+$", message = "Invalid format")
    @Length(max = 11, message = "Invalid lenght (Max. 11)")
    private String documentNumber;
    private StateRequestDTO state;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDtO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDto {
    private String registrationDate;
    private OrganizationNameRequestDTO organizationName;
    private DocumentRequestDTO document;
    
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String givenName;
    private String lastName;
    private String secondLastName;
    
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDto {

    private PersonNameRequestDTO personName;
    private String birthDate;
    @Valid
    private DocumentRequestDTO document;
    
    public PersonNameRequestDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String internationalCode;
    private String extension;
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getInternationalCode() {
        return internationalCode;
    }
    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private ProvinceRequestDTO province;
    private String townName;
    private CountryRequestDTO country;
    
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectRequestDTO {
    @Valid
    private PersonRequestDto person;
    private OrganizationRequestDto organization;
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDtO electronicAddress;
    private ContactPointDTO contactPointCustomer;
    
    public PersonRequestDto getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDto person) {
        this.person = person;
    }
    public OrganizationRequestDto getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDto organization) {
        this.organization = organization;
    }
    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
    public ElectronicAddressRequestDtO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressRequestDtO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public ContactPointDTO getContactPointCustomer() {
        return contactPointCustomer;
    }
    public void setContactPointCustomer(ContactPointDTO contactPointCustomer) {
        this.contactPointCustomer = contactPointCustomer;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }   
}

>>>>>>>>>>>>>>>>
package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCustomerSearchDTO {
    private OrganizationNameCustomerSearchDTO organizationName;
    private DocumentDTO document;
    private String typeCode;
    private String typeDescription;
    private String subtypeCode;
    private String subtypeDescription;
    private String registrationDate;
    
    public OrganizationNameCustomerSearchDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameCustomerSearchDTO organizationName) {
        this.organizationName = organizationName;
    }
    public DocumentDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeDescription() {
        return typeDescription;
    }
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getSubtypeDescription() {
        return subtypeDescription;
    }
    public void setSubtypeDescription(String subtypeDescription) {
        this.subtypeDescription = subtypeDescription;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameCustomerSearchDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCustomerSearchResponseDTO {
    private PersonNameCustomerSearchResponseDTO personName;
    private ProspectSearchPlaceOfBirthDTO placeOfBirth;
    private CodeNameDTO firstNationality;
    private String birthDate;
    private String genderCode;
    private String genderDescription;
    private CodeNameDTO countryOfResident;
    
    public PersonNameCustomerSearchResponseDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameCustomerSearchResponseDTO personName) {
        this.personName = personName;
    }
    public ProspectSearchPlaceOfBirthDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(ProspectSearchPlaceOfBirthDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CodeNameDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(CodeNameDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getGenderDescription() {
        return genderDescription;
    }
    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }
    public CodeNameDTO getCountryOfResident() {
        return countryOfResident;
    }
    public void setCountryOfResident(CodeNameDTO countryOfResident) {
        this.countryOfResident = countryOfResident;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameCustomerSearchResponseDTO {
    private String givenName;
    private String lastName;
    private String secondLastName;
    private String fullName;
    
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }   
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchDocumentDTO {
    private String documentTypeCode;
    private String documentTypeDescription;
    private String documentNumber;
    private String issueDate;
    private CodeNameDTO country;
    private CodeNameDTO state;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }
    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchDTO {
    private String prospectId;
    private PersonDTO person;
    private DocumentDTO document;
    private List<ContactPointDTO> contactPoints;
    
    public String getProspectId() {
        return prospectId;
    }
    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }
    public PersonDTO getPerson() {
        return person;
    }
    public void setPerson(PersonDTO person) {
        this.person = person;
    }
    public DocumentDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchPhoneAddressDTO {
    private String phoneNumber;
    private String internationalCode;
    private String mobileNumber;
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getInternationalCode() {
        return internationalCode;
    }
    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchPlaceOfBirthDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;
    
    public CodeNameDTO getCountry() {
        return country;
    }
    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }
    public CodeNameDTO getState() {
        return state;
    }
    public void setState(CodeNameDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.pagination.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchResponseDTO {
    private List<ProspectSearchDTO> prospects;
    private PaginationDTO pagination;
    
    public List<ProspectSearchDTO> getProspects() {
        return prospects;
    }
    public void setProspects(List<ProspectSearchDTO> prospects) {
        this.prospects = prospects;
    }
    public PaginationDTO getPagination() {
        return pagination;
    }
    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProspectSearchUseTypesDTO {
    private String code;
    private String description;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}


>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralNumber;
    
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }
    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }
    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }
    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getDetailCode() {
        return detailCode;
    }
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getPremise() {
        return premise;
    }
    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getAlternativePremise() {
        return alternativePremise;
    }
    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }
    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getMilitary() {
        return military;
    }
    public void setMilitary(String military) {
        this.military = military;
    }
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }
    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }
    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }
    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZip4Code() {
        return zip4Code;
    }
    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }
    public String getRuralTypeCode() {
        return ruralTypeCode;
    }
    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }


    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactDayRequestDTO {
    private String day;
    private String fromDateTime;
    private String toDateTime;
    
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactTimeRequestDTO {
    private String fromDateTime;
    private String toDateTime;
    private String bestTimeFrameCode;
    private String bestTimeFrameDescription;
    
    public String getFromDateTime() {
        return fromDateTime;
    }
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }
    public String getToDateTime() {
        return toDateTime;
    }
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }
    public String getBestTimeFrameCode() {
        return bestTimeFrameCode;
    }
    public void setBestTimeFrameCode(String bestTimeFrameCode) {
        this.bestTimeFrameCode = bestTimeFrameCode;
    }
    public String getBestTimeFrameDescription() {
        return bestTimeFrameDescription;
    }
    public void setBestTimeFrameDescription(String bestTimeFrameDescription) {
        this.bestTimeFrameDescription = bestTimeFrameDescription;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointRequestDTO {
    private List<UseTypeRequestDTO> useTypes;
    private Boolean preferredIndicator;
    private Boolean primaryIndicator;
    private ValidityPeriodRequestDTO validityPeriod;
    private BestContactTimeRequestDTO bestContactTime;
    private List<BestContactDayRequestDTO> bestContactDays;
    private PostalAddressRequestDTO postalAddress;
    @Valid
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDTO electronicAddress;
    private WebAddressRequestDTO webAddress;
    
    public List<UseTypeRequestDTO> getUseTypes() {
        return useTypes;
    }
    public void setUseTypes(List<UseTypeRequestDTO> useTypes) {
        this.useTypes = useTypes;
    }
    public Boolean getPreferredIndicator() {
        return preferredIndicator;
    }
    public void setPreferredIndicator(Boolean preferredIndicator) {
        this.preferredIndicator = preferredIndicator;
    }
    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }
    public void setPrimaryIndicator(Boolean primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
    public BestContactTimeRequestDTO getBestContactTime() {
        return bestContactTime;
    }
    public void setBestContactTime(BestContactTimeRequestDTO bestContactTime) {
        this.bestContactTime = bestContactTime;
    }
    public List<BestContactDayRequestDTO> getBestContactDays() {
        return bestContactDays;
    }
    public void setBestContactDays(List<BestContactDayRequestDTO> bestContactDays) {
        this.bestContactDays = bestContactDays;
    }
    public PostalAddressRequestDTO getPostalAddress() {
        return postalAddress;
    }
    public void setPostalAddress(PostalAddressRequestDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }
    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
    public ElectronicAddressRequestDTO getElectronicAddress() {
        return electronicAddress;
    }
    public void setElectronicAddress(ElectronicAddressRequestDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
    public WebAddressRequestDTO getWebAddress() {
        return webAddress;
    }
    public void setWebAddress(WebAddressRequestDTO webAddress) {
        this.webAddress = webAddress;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfOperationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryOfResidenceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountyIdentificationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document2RequestDTO {
    private String documentTypeCode;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private StateRequestDTO state;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getIssuerEntity() {
        return issuerEntity;
    }
    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {

    @NotNull
    @Size(min = 2, max = 2)
    private String documentTypeCode;
    @NotNull
    @Digits(integer = 11, fraction = 0, message = "documentNumber must be numeric and not exceed 11 digits")
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CountryRequestDTO country;
    private String town;
    
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getIssuerEntity() {
        return issuerEntity;
    }
    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityRequestDTO {
    private String subCategoryCode;

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequestDTO {
    private String name;
    private EconomicActivityRequestDTO economicActivity;
    private AddressRequestDTO address;
    private String typeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }

    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationRequestDTO {
    private EconomicActivityRequestDTO economicActivity;
    private String occupationCode;
    private String statusCode;
    private String subActivityCode;
    private String subActivityComments;
    private EmployerRequestDTO employer;
    
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public String getOccupationCode() {
        return occupationCode;
    }
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getSubActivityCode() {
        return subActivityCode;
    }
    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }
    public String getSubActivityComments() {
        return subActivityComments;
    }
    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }
    public EmployerRequestDTO getEmployer() {
        return employer;
    }
    public void setEmployer(EmployerRequestDTO employer) {
        this.employer = employer;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignTaxisRequestDTO {
    private CountryRequestDTO country;
    private String reasonCode;
    private DocumentRequestDTO document;
    private NoDocumentRequestDTO noDocument;

    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public DocumentRequestDTO getDocument() {
        return document;
    }
    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
    public NoDocumentRequestDTO getNoDocument() {
        return noDocument;
    }
    public void setNoDocument(NoDocumentRequestDTO noDocument) {
        this.noDocument = noDocument;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoDocumentRequestDTO {
    private String reasonCode;
    private String reasonDetails;
    
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public String getReasonDetails() {
        return reasonDetails;
    }
    public void setReasonDetails(String reasonDetails) {
        this.reasonDetails = reasonDetails;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;
    private List<String> tradingNames;
    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public List<String> getTradingNames() {
        return tradingNames;
    }
    public void setTradingNames(List<String> tradingNames) {
        this.tradingNames = tradingNames;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO {
    private OrganizationNameRequestDTO organizationName;
    private String typeCode;
    private String subtypeCode;
    private String registrationDate;
    private String accountingSectorCode;
    private String residentialStatusCode;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationRequestDTO placeOfRegistration;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private List<DocumentRequestDTO> documents;
    private CountryOfOperationRequestDTO countryOfOperation;
    private EconomicActivityRequestDTO economicActivity;
    private PreferredLanguageRequestDTO preferredLanguage;
    
    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getSubtypeCode() {
        return subtypeCode;
    }
    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public PlaceOfRegistrationRequestDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }
    public void setPlaceOfRegistration(PlaceOfRegistrationRequestDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }
    public CountryOfOperationRequestDTO getCountryOfOperation() {
        return countryOfOperation;
    }
    public void setCountryOfOperation(CountryOfOperationRequestDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }
    public EconomicActivityRequestDTO getEconomicActivity() {
        return economicActivity;
    }
    public void setEconomicActivity(EconomicActivityRequestDTO economicActivity) {
        this.economicActivity = economicActivity;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }


    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchProspectRequestDTO {
    @Valid
    @NotNull
    private PersonRequestDTO person;
    private OrganizationRequestDTO organization;
    private String structuralSegmentCode;
    private String structuralSubsegmentCode;
    private BankRequestDTO bank;
    private List<ContactPointRequestDTO> contactPoints;
    
    public PersonRequestDTO getPerson() {
        return person;
    }
    public void setPerson(PersonRequestDTO person) {
        this.person = person;
    }
    public OrganizationRequestDTO getOrganization() {
        return organization;
    }
    public void setOrganization(OrganizationRequestDTO organization) {
        this.organization = organization;
    }
    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }
    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }
    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }
    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }
    public BankRequestDTO getBank() {
        return bank;
    }
    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }
    public List<ContactPointRequestDTO> getContactPoints() {
        return contactPoints;
    }
    public void setContactPoints(List<ContactPointRequestDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String namePrefixCode;

    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The name can only contain letters, spaces and accents")
    @NotNull
    private String givenName;
    private String middleName;
    @NotNull
    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The last name can only contain letters, spaces and accents")
    private String lastName;
    @NotNull
    @Pattern(regexp = "^[\\p{L} \\s]+$", message = "The second last name can only contain letters, spaces and accents")
    private String secondLastName;
    private String nameSuffixCode;
    private String birthName;
    private List<String> aliases;
    
    public String getNamePrefixCode() {
        return namePrefixCode;
    }
    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }
    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    public String getNameSuffixCode() {
        return nameSuffixCode;
    }
    public void setNameSuffixCode(String nameSuffixCode) {
        this.nameSuffixCode = nameSuffixCode;
    }
    public String getBirthName() {
        return birthName;
    }
    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @Valid
    @NotNull
    private PersonNameRequestDTO personName;
    private String motherName;
    private String fatherName;
    private List<ForeignTaxisRequestDTO> foreignTaxes;
    private String genderCode;
    private String birthDate;
    private PlaceOfBirthRequestDTO placeOfBirth;
    private CountryOfResidenceRequestDTO countryOfResidence;
    private FirstNationalityRequestDTO firstNationality;
    private SecondNationalityRequestDTO secondNationality;
    private String residentialStatusCode;
    private String civilStatusCode;    
    private String staffCode;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private PublicOfficeInformationRequestDTO publicOfficeInformation;
    private String educationalLevelCode;
    private String foreignTaxIndicator;
    private String accountingSectorCode;
    private EmploymentInformationRequestDTO employmentInformation;
    private PreferredLanguageRequestDTO preferredLanguage;
    @Valid
    @NotNull
    private List<DocumentRequestDTO> documents;
    
    public PersonNameRequestDTO getPersonName() {
        return personName;
    }
    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public List<ForeignTaxisRequestDTO> getForeignTaxes() {
        return foreignTaxes;
    }
    public void setForeignTaxes(List<ForeignTaxisRequestDTO> foreignTaxes) {
        this.foreignTaxes = foreignTaxes;
    }
    public String getGenderCode() {
        return genderCode;
    }
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public PlaceOfBirthRequestDTO getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(PlaceOfBirthRequestDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public CountryOfResidenceRequestDTO getCountryOfResidence() {
        return countryOfResidence;
    }
    public void setCountryOfResidence(CountryOfResidenceRequestDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }
    public FirstNationalityRequestDTO getFirstNationality() {
        return firstNationality;
    }
    public void setFirstNationality(FirstNationalityRequestDTO firstNationality) {
        this.firstNationality = firstNationality;
    }
    public SecondNationalityRequestDTO getSecondNationality() {
        return secondNationality;
    }
    public void setSecondNationality(SecondNationalityRequestDTO secondNationality) {
        this.secondNationality = secondNationality;
    }
    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }
    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }
    public String getCivilStatusCode() {
        return civilStatusCode;
    }
    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public Boolean getLegallyIncapacitated() {
        return legallyIncapacitated;
    }
    public void setLegallyIncapacitated(Boolean legallyIncapacitated) {
        this.legallyIncapacitated = legallyIncapacitated;
    }
    public Boolean getLegallyCapableMinor() {
        return legallyCapableMinor;
    }
    public void setLegallyCapableMinor(Boolean legallyCapableMinor) {
        this.legallyCapableMinor = legallyCapableMinor;
    }
    public Boolean getDiplomatic() {
        return diplomatic;
    }
    public void setDiplomatic(Boolean diplomatic) {
        this.diplomatic = diplomatic;
    }
    public PublicOfficeInformationRequestDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }
    public void setPublicOfficeInformation(PublicOfficeInformationRequestDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }
    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }
    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }
    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }
    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }
    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }
    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }
    public EmploymentInformationRequestDTO getEmploymentInformation() {
        return employmentInformation;
    }
    public void setEmploymentInformation(EmploymentInformationRequestDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }
    public PreferredLanguageRequestDTO getPreferredLanguage() {
        return preferredLanguage;
    }
    public void setPreferredLanguage(PreferredLanguageRequestDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    public List<DocumentRequestDTO> getDocuments() {
        return documents;
    }
    public void setDocuments(List<DocumentRequestDTO> documents) {
        this.documents = documents;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {

    
    private String mobileNumber;
    private String phoneNumber;
    @NotNull
    @Size(min = 2, max = 3, message = "The International Code must not exceed 3 digits nor less than 2")
    private String internationalCode;

    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    private String townCode;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getTownCode() {
        return townCode;
    }
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationRequestDTO {
    private CountryRequestDTO country;
    private StateRequestDTO state;
    private String town;
    
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private String fullAddress;
    private String formatCode;
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String mailingInstructions;
    private String postCodeIdentification;
    private String townName;
    private String mailDeliverySubLocation;
    private StateRequestDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private ProvinceRequestDTO province;
    private RegionIdentificationRequestDTO regionIdentification;
    private CountyIdentificationRequestDTO countyIdentification;
    private CountryRequestDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralNumber;
    
    public String getFullAddress() {
        return fullAddress;
    }
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    public String getFormatCode() {
        return formatCode;
    }
    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }
    public String getStreetTypeCode() {
        return streetTypeCode;
    }
    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }
    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }
    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }
    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getDetailCode() {
        return detailCode;
    }
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getPremise() {
        return premise;
    }
    public void setPremise(String premise) {
        this.premise = premise;
    }
    public String getAlternativePremise() {
        return alternativePremise;
    }
    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }
    public String getMailingInstructions() {
        return mailingInstructions;
    }
    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }
    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }
    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }
    public String getTownName() {
        return townName;
    }
    public void setTownName(String townName) {
        this.townName = townName;
    }
    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }
    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }
    public StateRequestDTO getState() {
        return state;
    }
    public void setState(StateRequestDTO state) {
        this.state = state;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }
    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }
    public ProvinceRequestDTO getProvince() {
        return province;
    }
    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }
    public RegionIdentificationRequestDTO getRegionIdentification() {
        return regionIdentification;
    }
    public void setRegionIdentification(RegionIdentificationRequestDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }
    public CountyIdentificationRequestDTO getCountyIdentification() {
        return countyIdentification;
    }
    public void setCountyIdentification(CountyIdentificationRequestDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }
    public CountryRequestDTO getCountry() {
        return country;
    }
    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
    public String getMilitary() {
        return military;
    }
    public void setMilitary(String military) {
        this.military = military;
    }
    public String getPostOfficeBox() {
        return postOfficeBox;
    }
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }
    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }
    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }
    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZip4Code() {
        return zip4Code;
    }
    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }
    public String getRuralTypeCode() {
        return ruralTypeCode;
    }
    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }
    public String getRuralNumber() {
        return ruralNumber;
    }
    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferredLanguageRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationRequestDTO {
    private String positionCode;
    private ValidityPeriodRequestDTO validityPeriod;
    
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public ValidityPeriodRequestDTO getValidityPeriod() {
        return validityPeriod;
    }
    public void setValidityPeriod(ValidityPeriodRequestDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionIdentificationRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondNationalityRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UseTypeRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodRequestDTO {
    private String startDate;
    private String endDate;
    
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    
}



package com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAddressRequestDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}


>>>
>>>
package com.santander.bnc.bsn049.bncbsn049msprospects.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadComparisonRequest {
    private String ciudadIngresada;
    private String ciudadServicio;
    
    public String getCiudadIngresada() {
        return ciudadIngresada;
    }
    public void setCiudadIngresada(String ciudadIngresada) {
        this.ciudadIngresada = ciudadIngresada;
    }
    public String getCiudadServicio() {
        return ciudadServicio;
    }
    public void setCiudadServicio(String ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    
}


*************************************************************************************************
package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContextAPIImpl implements ContextApiService {

    /**
     * Person Retrofit Api
     */
    private final ContextAPI contextAPI;

    public static final String PRODUCT = "cdt";

    @Override
    public void putContext(String key, Object object) {
        try{
            log.info(GUtils.SLOG+"client putContext KEY={}",key);
            ContextRequest request = new ContextRequest();
            request.setProduct(PRODUCT);
            request.setValue(object);
            request.setKey(key);
            contextAPI.putCache(request).enqueue(new Callback<ContextResponse>() {

                @Override
                public void onResponse(Call<ContextResponse> call, Response<ContextResponse> response) {
                    log.info("put cache ok");
                }

                @Override
                public void onFailure(Call<ContextResponse> call, Throwable t) {
                    log.info("error put cache: {}", t.getMessage());
                }
                
            });
            log.info(GUtils.ELOG+"client putContext");
        } catch (RuntimeException e){
            log.error("Runtime Exception putting cache: {}", e.getMessage());            
        } catch (IOException e){
            log.error("IOException putting cache {}", e.getMessage());            
        } catch (Exception e){
            log.error("Unhandled exception putting cache. {}", e.getMessage());
        }

    }//method closure

    @Override
    public Object getContext(String key) {
        try{
            log.info(GUtils.SLOG+"client getContext KEY={}",key);
            return contextAPI.getCache(key,PRODUCT).execute().body().getValue();
        } catch (RuntimeException e){
            log.error("Runtime Exception getting cache: {}", e.getMessage());
            return null;
        } catch (IOException e){
            log.error("IOException getting cache {}", e.getMessage());
            return null;
        } catch (Exception e){
            log.error("Unhandled exception getting cache {}", e.getMessage());
            return null;
        }
    }//method closure
}//class closure





package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterAPIImpl implements ParameterApiService {

    /**
     * Person Retrofit Api
     */
    private final ParametersAPI parametersAPI;


    @Override
    public List<DataListDTO> getParameter(String parameterId, String valueCode,String authorization,String xSantanderClientId) {
       Response<GeographiesParametersResponseDTO> responseApi;
        try {
            responseApi = parametersAPI.getParameter(parameterId,valueCode,authorization,xSantanderClientId).execute();
            if (!((responseApi.isSuccessful() || responseApi.code() == 204) && responseApi.body() != null)){
                var error = ErrorCatalog.getMsParametersResponse();
                error.setMessage(responseApi.message());
                error.setDescription(ErrorDictionary.getMsName() + " - " + responseApi);
                throw new ServiceException(HttpStatus.BAD_REQUEST, error);
            }
            if(responseApi.body().getParameters().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersNoEntry());
            }
        } catch(RuntimeException e){
            log.error("Runtime Exception calling parameters: {}", e.getMessage()); 
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse() );        
        } catch(IOException e){  
            log.error("IOException calling parameters: {}", e.getMessage());           
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConnection());        
        } catch (Exception e){
            log.info("Unhandled exception calling parameters: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure



package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterAPIImpl implements ParameterApiService {

    /**
     * Person Retrofit Api
     */
    private final ParametersAPI parametersAPI;


    @Override
    public List<DataListDTO> getParameter(String parameterId, String valueCode,String authorization,String xSantanderClientId) {
       Response<GeographiesParametersResponseDTO> responseApi;
        try {
            responseApi = parametersAPI.getParameter(parameterId,valueCode,authorization,xSantanderClientId).execute();
            if (!((responseApi.isSuccessful() || responseApi.code() == 204) && responseApi.body() != null)){
                var error = ErrorCatalog.getMsParametersResponse();
                error.setMessage(responseApi.message());
                error.setDescription(ErrorDictionary.getMsName() + " - " + responseApi);
                throw new ServiceException(HttpStatus.BAD_REQUEST, error);
            }
            if(responseApi.body().getParameters().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersNoEntry());
            }
        } catch(RuntimeException e){
            log.error("Runtime Exception calling parameters: {}", e.getMessage()); 
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse() );        
        } catch(IOException e){  
            log.error("IOException calling parameters: {}", e.getMessage());           
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConnection());        
        } catch (Exception e){
            log.info("Unhandled exception calling parameters: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure


package com.santander.bnc.bsn049.bncbsn049msprospects.config;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.ApiEntry;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Component
@ConfigurationProperties(prefix = "integration")
public class IntegrationDataConfiguration {
    @Getter
    private List<ApiEntry> catalogue;
    private Map<String, ApiEntry> catalogueMap;

    private void loadCatalogueMap() {
        catalogueMap = catalogue.stream().collect(
                Collectors.toMap(ApiEntry::getIntegrationType, Function.identity()));
    }
    public ApiEntry getByApi(String integrationType){
        if(catalogueMap == null){
            loadCatalogueMap();
        }
        return catalogueMap.get(integrationType);
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.ApiEntry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RestClientConfig {

    @Autowired
    IntegrationDataConfiguration properties;

    @Bean
    TrxPersonAPI txrTransactionApi() {
        return getRetrofitConfig(properties.getByApi("trx_person"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxPersonAPI.class);
    }

    @Bean
    ParametersAPI parametersAPI() {
        return getRetrofitConfig(properties.getByApi("parameters"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ParametersAPI.class);
    }

    @Bean
    ContextAPI contextAPI() {
        return getRetrofitConfig(properties.getByApi("context"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ContextAPI.class);
    }

    private Retrofit.Builder getRetrofitConfig(ApiEntry apiEntry) {

        return new Retrofit.Builder()
                .baseUrl(buildURL(apiEntry))
                .client(getHttpClient(HttpLoggingInterceptor.Level.BODY,
                        apiEntry.getTimeOutRead(),
                        apiEntry.getTimeOutConn()));
    }

    private OkHttpClient getHttpClient(HttpLoggingInterceptor.Level level, long readTimeout, long connectTimeout) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(msg ->
		log.info("--> OKHTTP {}",msg)
		);
        interceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        return builder
                .build();
    }

    private ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
        return objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new StdDateFormat())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS)
                .registerModule(new JavaTimeModule());
    }

    private String buildURL(ApiEntry apiEntry) {
        String schema = "https://";
        if (!apiEntry.isHttps()) {
            schema = schema.replace("s", "");
        }
        return schema + apiEntry.getHost() + ":" + apiEntry.getPort() + apiEntry.getEndpoint();
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.CiudadComparisonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ProspectService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ClientUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.CompareStringUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ServiceDirectory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ProspectController {

    final ProspectService prospectService;
    private final ObjectMapper objectMapper;
    /**
     * PROSPECT SEARCH
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectSearchResponseDTO>
     */
    @PostMapping(ServiceDirectory.PROSPECT_SEARCH)
    public ResponseEntity<ProspectSearchResponseDTO> searchCustomers(@Valid @RequestBody ProspectRequestDTO request,
                                                                     @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId,
                                                                     @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization) {
        
    	 try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) {} {} >>>",ServiceDirectory.PROSPECT_SEARCH,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	 
    	log.info(GUtils.SLOG + "endpoint search prospect by person={}", request.getPerson());
        ProspectSearchResponseDTO response = prospectService.searchProspect(request,authorization,xSantanderClientId);

        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (POST) {} {} >>>",ServiceDirectory.PROSPECT_SEARCH,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        log.info(GUtils.ELOG + "endpoint search prospect {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * PROSPECT BASIC DATA
     * @param prospectId
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectDetailResponseDTO>
     */
    @GetMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<ProspectDetailResponseDTO> getProspect(@PathVariable(required = true, name = "prospectId" ) String prospectId,
                                                                 @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                                 @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId) {


		StringBuilder sb = new StringBuilder();
		sb.append(" clientId=").append(xSantanderClientId);		
		log.info("*** INIT (GET) {}/{} clientId={} >>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
 	
    	log.info(GUtils.SLOG + "endpoint get prospect by prospectId={}", prospectId);
        ProspectDetailResponseDTO response = prospectService.getProspectDetails(prospectId,authorization,xSantanderClientId);

        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sbr = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (GET) {}/{} {} >>>",ServiceDirectory.PROSPECT,prospectId,sbr.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        if(response == null){
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getProspectNotFound());    
        }
        log.info(GUtils.ELOG + "endpoint get prospect");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * CREATE PROSPECT
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectCreatedResponseDTO>
     */
    @PostMapping(ServiceDirectory.PROSPECT)
    public ResponseEntity<ProspectCreatedResponseDTO> createProspect(@Valid @RequestBody CreateProspectRequestDTO request,
                                                                     @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                                     @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId) {
        
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) {} {} >>>",ServiceDirectory.PROSPECT,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
    	log.info(GUtils.SLOG + "endpoint create prospect");        
        ProspectCreatedResponseDTO response  = prospectService.createProspect(request,authorization, xSantanderClientId);
        
        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (POST) {} {} >>>",ServiceDirectory.PROSPECT,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        log.info(GUtils.ELOG + "endpoint create prospect");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }//method closure

    /**
     * PATCH PROSPECT
     * @param prospectId
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return NO_CONTENT
     */
    @PatchMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<Object> updateCustomers(@PathVariable(name = "prospectId") String prospectId,
                                                  @RequestBody PatchProspectRequestDTO request,
                                                  @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                  @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId){
        
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (PATCH) {}/{} {}>>>",ServiceDirectory.PROSPECT,prospectId,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
    	log.info(GUtils.SLOG + "endpoint update prospect {}", prospectId);
        prospectService.updateProspect(request,prospectId,authorization,xSantanderClientId);
        
 			
 		log.info("*** FIN OK (PATCH) {}/{} >>>",ServiceDirectory.PROSPECT,prospectId);

        log.info(GUtils.ELOG + "endpoint update prospect.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }//method closure

    /**
     * DELETE PROSPECT
     * @param prospectId
     * @param authorization
     * @param xSantanderClientId
     * @return
     */
    @DeleteMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<ProspectDetailResponseDTO> removeProspect(
            @PathVariable(required = true, name = "prospectId" ) String prospectId,
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {

    	log.info("*** INIT (DELETE) {}/{} clientId={}>>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
    	
        log.info(GUtils.SLOG + "endpoint remove prospect {}", prospectId);
        prospectService.removeProspect(prospectId, authorization, xSantanderClientId);
        
        log.info("*** FIN OK (DELETE) {}/{} clientId={}>>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
        
        log.info(GUtils.ELOG + "endpoint remove prospect.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }//method closure

    @PostMapping("/compareCities")
    public ResponseEntity<Boolean> compareCities(@RequestBody CiudadComparisonRequest request) {
    	
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) /compareCities {} >>>",sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
        CompareStringUtils matcher = new CompareStringUtils();
        boolean isSimilar = matcher.ciudadMatch(request.getCiudadIngresada(), request.getCiudadServicio());

 		log.info("*** FIN (POST) /compareCities isSimilar={} >>>",isSimilar);
     
        return ResponseEntity.ok(isSimilar);
    }

}//class closure


