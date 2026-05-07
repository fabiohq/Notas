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
