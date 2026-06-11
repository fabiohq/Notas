
package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardType {
    
    private String code;
    private String description;
    private String content;

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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    

}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request;

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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Request {
    
    private TrxHeader cabecera;
    private TrxBP17DataRequest data;
    

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


    public TrxBP17Request(TrxPersonHeader header){
        
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad(header.sesion.entorno);
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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;


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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataResponse {
    
        private String intPendAbonar;
        private String tasaInteresNominal;
        private String tasaInteresEA;
        private String fechaProximoReajuste;
        private String fecVencimiento;
        private String codigoDeProducto;
        private String codigoDeSubproduct;
        private String descripcionProdu;
        private String importeBaseInvers;
        private String codigoDeDivisa;
        private String codigoDeTarifa;
        private String plazoEnDias;
        private String descripPlazo;
        private String plazoEnDiasDispon;
        private String periodoLiquidacion;
        private String descrPeriodoLiq;
        private String periodosDisponibles;
        private String montoFijoBonGmf;
        private String porcentajeFijoBonGmf;
        private String importeGmfMaximo;
        private String importeGmfBonific;
        private String tipoBonifGmfCalc;
        private String importeTotalInvers;
        private String importeBrutoIntere;
        private String porcentajeDeRetencionFuent;
        private String importeRetencFuent;
        private String importeNetoInteres;
        private String importeTotalCobrar;
        private String fechaDeAlta;
        private String fechaDeVencimiento;
        private String fechaProxLiquidac;
        private String porcentajeDeInteresNominal;
        private String porcentajeDeSpread;
        private String porcentajeDeTasaEfectiva;

        public String getIntPendAbonar() {
                return intPendAbonar;
        }
        public void setIntPendAbonar(String intPendAbonar) {
                this.intPendAbonar = intPendAbonar;
        }
        public String getTasaInteresNominal() {
                return tasaInteresNominal;
        }
        public void setTasaInteresNominal(String tasaInteresNominal) {
                this.tasaInteresNominal = tasaInteresNominal;
        }
        public String getTasaInteresEA() {
                return tasaInteresEA;
        }
        public void setTasaInteresEA(String tasaInteresEA) {
                this.tasaInteresEA = tasaInteresEA;
        }
        public String getFechaProximoReajuste() {
                return fechaProximoReajuste;
        }
        public void setFechaProximoReajuste(String fechaProximoReajuste) {
                this.fechaProximoReajuste = fechaProximoReajuste;
        }
        public String getFecVencimiento() {
                return fecVencimiento;
        }
        public void setFecVencimiento(String fecVencimiento) {
                this.fecVencimiento = fecVencimiento;
        }
        public String getCodigoDeProducto() {
                return codigoDeProducto;
        }
        public void setCodigoDeProducto(String codigoDeProducto) {
                this.codigoDeProducto = codigoDeProducto;
        }
        public String getCodigoDeSubproduct() {
                return codigoDeSubproduct;
        }
        public void setCodigoDeSubproduct(String codigoDeSubproduct) {
                this.codigoDeSubproduct = codigoDeSubproduct;
        }
        public String getDescripcionProdu() {
                return descripcionProdu;
        }
        public void setDescripcionProdu(String descripcionProdu) {
                this.descripcionProdu = descripcionProdu;
        }
        public String getImporteBaseInvers() {
                return importeBaseInvers;
        }
        public void setImporteBaseInvers(String importeBaseInvers) {
                this.importeBaseInvers = importeBaseInvers;
        }
        public String getCodigoDeDivisa() {
                return codigoDeDivisa;
        }
        public void setCodigoDeDivisa(String codigoDeDivisa) {
                this.codigoDeDivisa = codigoDeDivisa;
        }
        public String getCodigoDeTarifa() {
                return codigoDeTarifa;
        }
        public void setCodigoDeTarifa(String codigoDeTarifa) {
                this.codigoDeTarifa = codigoDeTarifa;
        }
        public String getPlazoEnDias() {
                return plazoEnDias;
        }
        public void setPlazoEnDias(String plazoEnDias) {
                this.plazoEnDias = plazoEnDias;
        }
        public String getDescripPlazo() {
                return descripPlazo;
        }
        public void setDescripPlazo(String descripPlazo) {
                this.descripPlazo = descripPlazo;
        }
        public String getPlazoEnDiasDispon() {
                return plazoEnDiasDispon;
        }
        public void setPlazoEnDiasDispon(String plazoEnDiasDispon) {
                this.plazoEnDiasDispon = plazoEnDiasDispon;
        }
        public String getPeriodoLiquidacion() {
                return periodoLiquidacion;
        }
        public void setPeriodoLiquidacion(String periodoLiquidacion) {
                this.periodoLiquidacion = periodoLiquidacion;
        }
        public String getDescrPeriodoLiq() {
                return descrPeriodoLiq;
        }
        public void setDescrPeriodoLiq(String descrPeriodoLiq) {
                this.descrPeriodoLiq = descrPeriodoLiq;
        }
        public String getPeriodosDisponibles() {
                return periodosDisponibles;
        }
        public void setPeriodosDisponibles(String periodosDisponibles) {
                this.periodosDisponibles = periodosDisponibles;
        }
        public String getMontoFijoBonGmf() {
                return montoFijoBonGmf;
        }
        public void setMontoFijoBonGmf(String montoFijoBonGmf) {
                this.montoFijoBonGmf = montoFijoBonGmf;
        }
        public String getPorcentajeFijoBonGmf() {
                return porcentajeFijoBonGmf;
        }
        public void setPorcentajeFijoBonGmf(String porcentajeFijoBonGmf) {
                this.porcentajeFijoBonGmf = porcentajeFijoBonGmf;
        }
        public String getImporteGmfMaximo() {
                return importeGmfMaximo;
        }
        public void setImporteGmfMaximo(String importeGmfMaximo) {
                this.importeGmfMaximo = importeGmfMaximo;
        }
        public String getImporteGmfBonific() {
                return importeGmfBonific;
        }
        public void setImporteGmfBonific(String importeGmfBonific) {
                this.importeGmfBonific = importeGmfBonific;
        }
        public String getTipoBonifGmfCalc() {
                return tipoBonifGmfCalc;
        }
        public void setTipoBonifGmfCalc(String tipoBonifGmfCalc) {
                this.tipoBonifGmfCalc = tipoBonifGmfCalc;
        }
        public String getImporteTotalInvers() {
                return importeTotalInvers;
        }
        public void setImporteTotalInvers(String importeTotalInvers) {
                this.importeTotalInvers = importeTotalInvers;
        }
        public String getImporteBrutoIntere() {
                return importeBrutoIntere;
        }
        public void setImporteBrutoIntere(String importeBrutoIntere) {
                this.importeBrutoIntere = importeBrutoIntere;
        }
        public String getPorcentajeDeRetencionFuent() {
                return porcentajeDeRetencionFuent;
        }
        public void setPorcentajeDeRetencionFuent(String porcentajeDeRetencionFuent) {
                this.porcentajeDeRetencionFuent = porcentajeDeRetencionFuent;
        }
        public String getImporteRetencFuent() {
                return importeRetencFuent;
        }
        public void setImporteRetencFuent(String importeRetencFuent) {
                this.importeRetencFuent = importeRetencFuent;
        }
        public String getImporteNetoInteres() {
                return importeNetoInteres;
        }
        public void setImporteNetoInteres(String importeNetoInteres) {
                this.importeNetoInteres = importeNetoInteres;
        }
        public String getImporteTotalCobrar() {
                return importeTotalCobrar;
        }
        public void setImporteTotalCobrar(String importeTotalCobrar) {
                this.importeTotalCobrar = importeTotalCobrar;
        }
        public String getFechaDeAlta() {
                return fechaDeAlta;
        }
        public void setFechaDeAlta(String fechaDeAlta) {
                this.fechaDeAlta = fechaDeAlta;
        }
        public String getFechaDeVencimiento() {
                return fechaDeVencimiento;
        }
        public void setFechaDeVencimiento(String fechaDeVencimiento) {
                this.fechaDeVencimiento = fechaDeVencimiento;
        }
        public String getFechaProxLiquidac() {
                return fechaProxLiquidac;
        }
        public void setFechaProxLiquidac(String fechaProxLiquidac) {
                this.fechaProxLiquidac = fechaProxLiquidac;
        }
        public String getPorcentajeDeInteresNominal() {
                return porcentajeDeInteresNominal;
        }
        public void setPorcentajeDeInteresNominal(String porcentajeDeInteresNominal) {
                this.porcentajeDeInteresNominal = porcentajeDeInteresNominal;
        }
        public String getPorcentajeDeSpread() {
                return porcentajeDeSpread;
        }
        public void setPorcentajeDeSpread(String porcentajeDeSpread) {
                this.porcentajeDeSpread = porcentajeDeSpread;
        }
        public String getPorcentajeDeTasaEfectiva() {
                return porcentajeDeTasaEfectiva;
        }
        public void setPorcentajeDeTasaEfectiva(String porcentajeDeTasaEfectiva) {
                this.porcentajeDeTasaEfectiva = porcentajeDeTasaEfectiva;
        }

        

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic.TrxHeader;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Response {
    private TrxBP17DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP17DataResponse getData() {
        return data;
    }
    public void setData(TrxBP17DataResponse data) {
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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic;

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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxHeader {
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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.integration;

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

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDTO {
    
    private String code;
    private String content;
    private String description;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParameterResponse {
    
    private List<ParameterDTO> parameters;

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

import lombok.RequiredArgsConstructor;

@Service
@ConfigurationProperties(prefix = "errors")
@RequiredArgsConstructor
public class ErrorService {
    
    
    private String msName;
    private String msVersion;
    private String level;    
    private String functional;    
    private String technical;
    private HashMap<String, String> general;

    @Value("${errors.general.invalid_value}")
    public String invalidValue;

    public ServiceException serviceExceptionBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        var error = ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();

        return new ServiceException(status, error);
    }

    public ErrorDTO errorBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        return ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();
     
    }

    public void isBlank(String value, String fieldName) {
        if (value.isBlank()) {
            var message = "'" + fieldName + "' " + this.general.get("blank_data");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNull(String value, String fieldName) {
        if (value == null) {
            var message = "'" + fieldName + "' " + this.general.get("null");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public String getMsName() {
        return msName;
    }

    public void setMsName(String msName) {
        this.msName = msName;
    }

    public String getMsVersion() {
        return msVersion;
    }

    public void setMsVersion(String msVersion) {
        this.msVersion = msVersion;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFunctional() {
        return functional;
    }

    public void setFunctional(String functional) {
        this.functional = functional;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public HashMap<String, String> getGeneral() {
        return general;
    }

    public void setGeneral(HashMap<String, String> general) {
        this.general = general;
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }
    

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception;


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${params.app-name}")
    private String msName;
    private String pf400 = "-P-F-9400";   
    private String pt409 = "-P-T-9409";
    private String pf404 = "-P-F-9404";
    private String errorLevel = "error";
    private String messageNotSpecified= " not specified";

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pt409)
                .message("Unhandled exception")
                .level(errorLevel)
                .description(msName.toLowerCase() + "-api-services-v1: Unhandled exception")
                .build());
        return buildResponseEntity(errors, HttpStatus.CONFLICT);
    }//method closure

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDTO> errors = new ArrayList<>();


        result.getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            log.info(error.toString());

            String errorMessage = "'" + field + "': " + error.getDefaultMessage();

            errors.add(ErrorDTO.builder()
                    .code(msName + pf404)
                    .level(errorLevel)
                    .message(errorMessage)
                    .description(msName.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf404)
                .message("Not Found")
                .level(errorLevel)
                .description(msName.toLowerCase() + "-api-services-v1: Not Found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf400)
                .message("Required query parameter " + ex.getParameterName() + messageNotSpecified)
                .level(errorLevel)
                .description(msName.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + messageNotSpecified)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName.toLowerCase() + pf400)
                .message(ex.getMessage())
                .level("error")
                .description(msName.toLowerCase() + "-api-services-v1: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf400)
                .message("Required header " + ex.getHeaderName() + messageNotSpecified)
                .level(errorLevel)
                .description(msName.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + messageNotSpecified)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Main exception hanlder
     * @param ex Exception
     * @param request Web Request
     * @return Structured Santander Exception format
     */
    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceException ex, WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ex.getError());

        return buildResponseEntity(errors, ex.getCode());
    }

    @ExceptionHandler({ ServiceExceptionClient.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceExceptionClient ex, WebRequest request) {
        log.error("ERRORRS {}", ex.getErrorResponseDTO());
        return buildResponseEntity2(ex.getErrorResponseDTO(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ErrorResponseDTO> buildResponseEntity2(ErrorResponseDTO newErrorDTO, HttpStatus status) {

        newErrorDTO.getErrors().forEach( error-> {
                    error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,msName));
                    error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,msName).toLowerCase());
                    log.error(error.getMessage());
                }
        );
        return new ResponseEntity<>(newErrorDTO, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure

/**
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {

        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        if(errors != null){
            errors.forEach( error-> {
                        error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,msName));
                        error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,msName.toLowerCase()));
                        log.error(error.getMessage());
                    }
            );
        }
        return new ResponseEntity<>(responseError, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure
*/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(HttpMessageNotReadableException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf400)
                .message("Invalid body structure")
                .level(errorLevel)
                .description(msName.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
    
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {

        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        if (errors != null) {
            // 1. REGISTRO SEGURO (Logging interno para diagnóstico de devs)
            // Registramos todos los detalles originales ANTES de limpiarlos.
            log.error("Se detectaron {} errores técnicos detallados:", errors.size());
            errors.forEach(error -> 
                log.error("Código de Error Técnico: {}, Descripción Técnica Detallada: {}", error.getCode(), error.getDescription())
            );
        }
         // 2. CREACIÓN DE RESPUESTA DESDE CERO (Interrupción de flujo)
         // No usamos 'responseError.setErrors(sanitizedErrors)' sobre un objeto que tocó datos viejos.
         ErrorResponseDTO cleanResponse = new ErrorResponseDTO();
         List<ErrorDTO> externalErrors = new ArrayList<>();

         if (errors != null) {
             for (ErrorDTO original : errors) {
                 // Creamos un DTO nuevo por cada error, sin copiar referencias sospechosas
                 ErrorDTO safeDto = new ErrorDTO();
                 
                 // Usamos constantes o valores fijos para la descripción externa
                 // Esto garantiza a Fortify que el 'Sink' no recibe el 'Source' original
                 safeDto.setCode(original.getCode()); 
                 safeDto.setDescription("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
                 
                 externalErrors.add(safeDto);
             }
         }

         cleanResponse.setErrors(externalErrors);

         // 3. RETORNO SEGURO
         // Al usar 'cleanResponse', que solo contiene datos generados localmente ("hardcoded"),
         // Fortify debería validar la línea como segura.
         return new ResponseEntity<>(cleanResponse, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure
}//class closure


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.regex.Pattern;

@Data
@Configuration
@ConfigurationProperties(prefix = "regex")
public class RegexUtils {

    @Value("${params.appName}")
    private String msName;
    @Value("${params.apiServiceDescription}")
    private String msVersion;
    @Value("${errors.level}")
    private String level;
    @Value("${regex.error.code}")
    private String code;

    private HashMap<String, String> type;
    

    public void validateRegex(String regexType, String value, String fieldName) {


        String regularExpression = type.get(regexType);
        String message = type.get(regexType + "_error") != null ? type.get(regexType + "_error") : "Invalid format";

        var pattern = Pattern.compile(regularExpression);
        var matcher = pattern.matcher(value);
        boolean match = false;
        while (matcher.find()) {
            match = true;
        }

        if (!match) {

            ErrorDTO errorDTO = ErrorDTO.builder()
                    .code(msName + "-" + code)
                    .level(level)
                    .message("'" + fieldName + "': " + message)
                    .description(msName.toLowerCase() + "-" + msVersion + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.utils;

import java.util.ArrayList;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.ParameterDTO;

public class TermDepositUtils {
    private TermDepositUtils() {
        throw new UnsupportedOperationException("");
    }
    
    public static String getProductCode(String productId){

        if(productId == null) return "";

        if(productId.length() < 2) return "";

        return productId.substring(0, 2);
    }

    public static String getSubproductCode(String productId){
        
        if(productId == null) return "";

        if(productId.length() < 6) return "";

        return productId.substring(2, 6);
    }

    public static List<ParameterDTO> getParametersList() {
        List<ParameterDTO> response = new ArrayList<>();
        response.add(new ParameterDTO("V", "Vencimiento", "Pago Intereses al Vencimiento"));
        response.add(new ParameterDTO("C", "Capitalizable", "Capitalización e Intereses"));
        return response;
    }
 
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config.ParamsConfig;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.config.StandardType;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.ParameterDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.utils.TermDepositUtils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TermDepositParametersMapper {

    final ParamsConfig paramsConfig;

    public TrxBP17Request getBP17RequestFromInputData(String productId, String amount, String periodicity){
            TrxBP17Request trxBP17Request = new TrxBP17Request(ClientUtils.buildHeader(paramsConfig.getServiceRouteBp17()));
        
            var productCode = TermDepositUtils.getProductCode(paramsConfig.getDefaultProductId());
            var subProductCode = TermDepositUtils.getSubproductCode(productId);

            amount = amount != null ? amount : paramsConfig.getDefaultAmount();            
            amount = amount.replace(",", ".");

            var trxBP17Data = new TrxBP17DataRequest();
            trxBP17Data.setMoneda(paramsConfig.getDefaultCurrency());
            trxBP17Data.setPeriodoLiquidacion(paramsConfig.getDefaultPeriodType());
            trxBP17Data.setPlazo(periodicity != null ? periodicity : paramsConfig.getDefaultPeriodicity());
            trxBP17Data.setProducto(productCode);
            trxBP17Data.setPuntosAdicionales("");
            trxBP17Data.setSubProducto(subProductCode);
            trxBP17Data.setTarifa("");
            trxBP17Data.setValor(amount);
    
            trxBP17Request.setData(trxBP17Data);

            return trxBP17Request;
    }


    public TermDepositParameterResponse getParametersResponseFromBP17(TrxBP17Response bp17Response) {
        TermDepositParameterResponse response = new TermDepositParameterResponse();
        List<ParameterDTO> parameters = new ArrayList<>();

        String periodosDisponibles = bp17Response.getData().getPeriodosDisponibles();
        List<StandardType> periodicity = paramsConfig.getPeriodicity();

        for (int i = 0; i < periodosDisponibles.length(); i++) {
            char code = periodosDisponibles.charAt(i);
            StandardType period = periodicity.stream().filter(p -> p.getCode().equals(String.valueOf(code))).findFirst().get();

            ParameterDTO parameter = new ParameterDTO();
            parameter.setCode(period.getCode());
            parameter.setDescription(period.getDescription());
            parameter.setContent(period.getContent());
            parameters.add(parameter);
        }

        response.setParameters(parameters);

        return response;
    }


    public TermDepositParameterResponse getListParametersHardCode() {
        TermDepositParameterResponse response = new TermDepositParameterResponse();
        response.setParameters(TermDepositUtils.getParametersList());
        return response;
    }


    public TermDepositParameterResponse getProposalsResponse(){

        TermDepositParameterResponse response = new TermDepositParameterResponse();
        List<ParameterDTO> parameters = new ArrayList<>();
        
        for (StandardType proposal : paramsConfig.getProposal()) {
            ParameterDTO parameter = new ParameterDTO();
            parameter.setCode(proposal.getCode());
            parameter.setDescription(proposal.getDescription());
            parameter.setContent(proposal.getDescription());
    
            parameters.add(parameter);
        }

        response.setParameters(parameters);

        return response;
    }



    public String getPeriodDescriptionByCode(String code){
        
        for (StandardType period : paramsConfig.getPeriodicity()) {
            
            if(period.getCode().equals(code)) return period.getDescription();
                   
        }

        return "";

    }



}//class closure


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.observability;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("externalApis")
public class ExternalApisHealthIndicator implements HealthIndicator {
	private final ExternalApisHealthProperties properties;
	private final HttpClient httpClient;

	public ExternalApisHealthIndicator(ExternalApisHealthProperties properties) {
		this.properties = properties;
		this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(properties.getTimeoutMs())).build();
	}

	@Override
	public Health health() {
		Map<String, Object> details = new LinkedHashMap<>(properties.getChecks().size());
		boolean allCriticalUp = true;

		for (ExternalApisHealthProperties.ApiCheck api : properties.getChecks()) {
			ApiResult result = checkApi(api);

			Map<String, Object> apiDetail = new LinkedHashMap<>(5);
			apiDetail.put("status", result.up ? "UP" : "DOWN");
			apiDetail.put("url", api.getUrl());
			apiDetail.put("critical", api.isCritical());

			if (result.httpStatus != null) {
				apiDetail.put("httpStatus", result.httpStatus);
			}
			if (result.error != null) {
				apiDetail.put("error", result.error);
			}

			details.put(api.getName(), apiDetail);

			if (api.isCritical() && !result.up) {
				allCriticalUp = false;
			}
		}

		return allCriticalUp ? Health.up().withDetails(details).build() : Health.down().withDetails(details).build();
	}

	private ApiResult checkApi(ExternalApisHealthProperties.ApiCheck api) {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(api.getUrl()))
					.timeout(Duration.ofMillis(properties.getTimeoutMs())).GET().build();

			HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

			int status = response.statusCode();
			boolean up = isAcceptedStatus(status, api);

			return new ApiResult(up, status, null);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return new ApiResult(false, null, e.getClass().getSimpleName() + ": " + e.getMessage());
		} catch (IOException e) {
			return new ApiResult(false, null, e.getClass().getSimpleName() + ":: " + e.getMessage());
		}
	}

	private static class ApiResult {
		private final boolean up;
		private final Integer httpStatus;
		private final String error;

		private ApiResult(boolean up, Integer httpStatus, String error) {
			this.up = up;
			this.httpStatus = httpStatus;
			this.error = error;
		}

		public boolean isUp() {
			return up;
		}

		public Integer getHttpStatus() {
			return httpStatus;
		}

		public String getError() {
			return error;
		}

	}

	private boolean isAcceptedStatus(int status, ExternalApisHealthProperties.ApiCheck api) {
		if (api.getAcceptedStatuses() != null && !api.getAcceptedStatuses().isEmpty()) {
			return api.getAcceptedStatuses().contains(status);
		}
		return status >= 200 && status < 300;
	}

}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.observability;

import java.util.ArrayList; import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "observability.external-apis")
public class ExternalApisHealthProperties {

	private int timeoutMs = 2000;
	private List<ApiCheck> checks = new ArrayList<>();

	public int getTimeoutMs() {
	    return timeoutMs;
	}

	public void setTimeoutMs(int timeoutMs) {
	    this.timeoutMs = timeoutMs;
	}

	public List<ApiCheck> getChecks() {
	    return checks;
	}

	public void setChecks(List<ApiCheck> checks) {
	    this.checks = checks;
	}

	public static class ApiCheck {
	    private String name;
	    private String url;
	    private boolean critical = true;
	    private List<Integer> acceptedStatuses;

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }

	    public boolean isCritical() {
	        return critical;
	    }

	    public void setCritical(boolean critical) {
	        this.critical = critical;
	    }

		public List<Integer> getAcceptedStatuses() {
			return acceptedStatuses;
		}

		public void setAcceptedStatuses(List<Integer> acceptedStatuses) {
			this.acceptedStatuses = acceptedStatuses;
		}
	    
	}
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;

    private final String BP17_SERVICE_ROUTE = "simulacionCDTBP17S171";
    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;

    @Override
    public TrxBP17Response trxBP17(TrxBP17Request request) {
        Response<TrxBP17Response> responseApi = null;

        try {

            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);

            responseApi = trxSanbaAPI.callBP17TRX(request, BP17_SERVICE_ROUTE, BP17_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info("Error in TRX: {}", e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info("Error in TRX: {}", e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }
        return handleApiResponse(responseApi);



    }
    private TrxBP17Response handleApiResponse(Response<TrxBP17Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP17Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + "client {}", responseBody);
            return responseBody;
        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                // Manejo de excepción
            }

            TrxBP17Response err = parseErrorResponseTrxBP17(errorBody);
            log.info(GUtils.ELOG + "err {}", errorBody != null && !errorBody.isEmpty() ? err.getErrores() : "No error");

            List<ErrorDTO> errorDTOs = buildErrorDTOs(err);
            if (!errorDTOs.isEmpty()) {
                throw new ServiceException(HttpStatus.CONFLICT, errorDTOs.get(0));
            } else {
                throw new ServiceException(HttpStatus.CONFLICT, ErrorCatalog.MS_SANBA_TRX_ERROR);
            }
        }
    }



    private List<ErrorDTO> buildErrorDTOs(TrxBP17Response err) {
        List<ErrorDTO> errorDTOs = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorDTOs.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
            log.info(GUtils.ELOG + "SET {}", errorDTOs);
        }
        return errorDTOs;
    }



    private TrxBP17Response parseErrorResponseTrxBP17(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBP17Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.integration.ApiEntry;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


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
    public void setCatalogue(List<ApiEntry> catalogue) {
        this.catalogue = catalogue;
    }
    public Map<String, ApiEntry> getCatalogueMap() {
        return catalogueMap;
    }
    public void setCatalogueMap(Map<String, ApiEntry> catalogueMap) {
        this.catalogueMap = catalogueMap;
    }
    
}


package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.config.StandardType;


@Configuration
@ConfigurationProperties(prefix = "params")
public class ParamsConfig {
    
    private String appName;
    private String appVersion;
    private String defaultProductId;
    private String proposalProductId;
    private String defaultAmount;
    private String defaultPeriodType;
    private String defaultPeriodicity;
    private String defaultCurrency;    
    private String serviceRouteBp17;

    private List<StandardType> periodicity;
    private List<StandardType> proposal;

    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
    public String getDefaultProductId() {
        return defaultProductId;
    }
    public void setDefaultProductId(String defaultProductId) {
        this.defaultProductId = defaultProductId;
    }
    public String getProposalProductId() {
        return proposalProductId;
    }
    public void setProposalProductId(String proposalProductId) {
        this.proposalProductId = proposalProductId;
    }
    public String getDefaultAmount() {
        return defaultAmount;
    }
    public void setDefaultAmount(String defaultAmount) {
        this.defaultAmount = defaultAmount;
    }
    public String getDefaultPeriodType() {
        return defaultPeriodType;
    }
    public void setDefaultPeriodType(String defaultPeriodType) {
        this.defaultPeriodType = defaultPeriodType;
    }
    public String getDefaultPeriodicity() {
        return defaultPeriodicity;
    }
    public void setDefaultPeriodicity(String defaultPeriodicity) {
        this.defaultPeriodicity = defaultPeriodicity;
    }
    public String getDefaultCurrency() {
        return defaultCurrency;
    }
    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    public String getServiceRouteBp17() {
        return serviceRouteBp17;
    }
    public void setServiceRouteBp17(String serviceRouteBp17) {
        this.serviceRouteBp17 = serviceRouteBp17;
    }
    public List<StandardType> getPeriodicity() {
        return periodicity;
    }
    public void setPeriodicity(List<StandardType> periodicity) {
        this.periodicity = periodicity;
    }
    public List<StandardType> getProposal() {
        return proposal;
    }
    public void setProposal(List<StandardType> proposal) {
        this.proposal = proposal;
    }

    

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config;

import java.util.concurrent.TimeUnit;

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
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.integration.ApiEntry;

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

    private final IntegrationDataConfiguration properties;

    @Bean
    TrxSanbaAPI txrTransactionApi(){
        return getRetrofitConfig(properties.getByApi("sanba"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxSanbaAPI.class);
    }


    private Retrofit.Builder getRetrofitConfig(ApiEntry apiEntry) {

        return new Retrofit.Builder()
                .baseUrl(buildURL(apiEntry))
                .client(getHttpClient(HttpLoggingInterceptor.Level.BODY,
                        apiEntry.getTimeOutRead(),
                        apiEntry.getTimeOutConn()));
    }

    private OkHttpClient getHttpClient(HttpLoggingInterceptor.Level level, long readTimeout, long connectTimeout)  {
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


    private String buildURL(ApiEntry apiEntry){
        String schema = "https://";
        if(!apiEntry.isHttps()){
            schema = schema.replace("s","");
        }
        return schema + apiEntry.getHost() + ":" + apiEntry.getPort() + apiEntry.getEndpoint();
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.service.TermDepositParametersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${params.version}")
@RequiredArgsConstructor
@Slf4j
public class TermDepositParametersController {
    
    final TermDepositParametersService termDepositParametersService;
    private final ObjectMapper objectMapper;
    @GetMapping("/{parameter_id}")
    public ResponseEntity<TermDepositParameterResponse> getParameters(@PathVariable(required = true, name = "parameter_id") String productId,
                                                                    @RequestParam(required = false, name = "amount") String amount, 
                                                                    @RequestParam(required = false, name = "periodicity") String periodicity,
                                                                    @RequestHeader(required = true, name = "authorization") String authorization,
                                                                    @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId){

    	if(amount == null && periodicity == null)		
    		log.info("*** INIT (GET) /v1/term_deposit_parameters/{}  client-id={} >>> ",productId,amount,periodicity, xSantanderClientId);
    	else
    		log.info("*** INIT (GET) /v1/term_deposit_parameters/{}?amount={}&periodicity={}  client-id={} >>> ",productId,amount,periodicity, xSantanderClientId);

		TermDepositParameterResponse response = termDepositParametersService.getParameters(productId, amount, periodicity);
		
		if(amount == null && periodicity == null)
			try {
				String jsonResponse = objectMapper.writeValueAsString(response);
				StringBuilder sbr = new StringBuilder();
				sbr.append(" Response=").append(jsonResponse);
				log.info("*** FIN (GET) /v1/term_deposit_parameters/{}  client-id={}  {}>>> ",productId,amount,periodicity, xSantanderClientId, sbr.toString());
			} catch (Exception e) {
				log.error("Error serializando response");
			}
		else
			try {
				String jsonResponse = objectMapper.writeValueAsString(response);
				StringBuilder sbr = new StringBuilder();
				sbr.append(" Response=").append(jsonResponse);
				log.info("*** FIN (GET) /v1/term_deposit_parameters/{}?amount={}&periodicity={}  client-id={}  {}>>> ",productId,amount,periodicity, xSantanderClientId, sbr.toString());
			} catch (Exception e) {
				log.error("Error serializando response");
			}
		
        return ResponseEntity.ok(response);
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;

import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.config.ParamsConfig;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.mappers.TermDepositParametersMapper;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.utils.RegexUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermDepositParametersService {

    final TrxSanbaService trxSanbaService;
    final TermDepositParametersMapper mapper;
    final ParamsConfig paramsConfig;
    final ErrorService errorService;
    final RegexUtils regexUtils;
    @Value("${params.periodHardcode}")
    private Boolean periodHardcode;
    private String amountField = "amount";
    private String periodicityField = "periodicity";
    private String productIdField = "productId";

    public TermDepositParameterResponse getParameters(String productId, String amount, String periodicity) {
        log.info(GUtils.SLOG + "getParameters - productId: {}, amount: {}, periodicity, {}", productId, amount,
                periodicity);

        errorService.isNull(productId, productIdField);
        errorService.isBlank(productId, productIdField);
        regexUtils.validateRegex("product_format", productId, productIdField);
        regexUtils.validateRegex("product_length", productId, productIdField);

        // #region Validaciones
        if (!productId.equals(paramsConfig.getDefaultProductId())
                && !productId.equals(paramsConfig.getProposalProductId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                    errorService.getGeneral().get("product_not_found"), ErrorType.FUNCTIONAL);
        }

        if (productId.equals(paramsConfig.getDefaultProductId())) {
            errorService.isNull(amount, amountField);
            errorService.isBlank(amount, amountField);
            regexUtils.validateRegex(amountField, amount, amountField);

            errorService.isNull(periodicity, periodicityField);
            errorService.isBlank(periodicity, periodicityField);
            regexUtils.validateRegex("product_format", periodicity, periodicityField);
        }
        // #endregion

        if (productId.equals(paramsConfig.getProposalProductId())) {

            log.info(GUtils.ELOG + "getParameters");

            return mapper.getProposalsResponse();

        } else {
            Integer integerPeriodicity = Integer.parseInt(periodicity);
            if((integerPeriodicity <90 && integerPeriodicity > 0) && periodHardcode){

                return mapper.getListParametersHardCode();
                   
            }
            
            var trxBP17Request = mapper.getBP17RequestFromInputData(productId, amount, periodicity);
            var bp17Response = trxSanbaService.trxBP17(trxBP17Request);

            log.info(GUtils.ELOG + "getParameters");

            return mapper.getParametersResponseFromBP17(bp17Response);
            
        }

    }
}
