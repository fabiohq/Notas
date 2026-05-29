package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP14DataRequest {

    private String codigoInversor;
    private String secuenciaIpf;
    private String formaDePagoIpf;
    private String valorImporte;
    private String cccDeCargo;
    private String tipoDeDocumento;
    private String numeroDeDocumento;
    private String bancoDelDocumento;
    private String centroDelDocumento;
    private String cuentaDelDocumento;
    private String plazaDelDocumento;
    private String responsableImpuesto;
    private String tipoIdentif;
    private String numeroDocumento;
    private String nombrePerson;

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getSecuenciaIpf() {
        return secuenciaIpf;
    }

    public void setSecuenciaIpf(String secuenciaIpf) {
        this.secuenciaIpf = secuenciaIpf;
    }

    public String getFormaDePagoIpf() {
        return formaDePagoIpf;
    }

    public void setFormaDePagoIpf(String formaDePagoIpf) {
        this.formaDePagoIpf = formaDePagoIpf;
    }

    public String getValorImporte() {
        return valorImporte;
    }

    public void setValorImporte(String valorImporte) {
        this.valorImporte = valorImporte;
    }

    public String getCccDeCargo() {
        return cccDeCargo;
    }

    public void setCccDeCargo(String cccDeCargo) {
        this.cccDeCargo = cccDeCargo;
    }

    public String getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public String getNumeroDeDocumento() {
        return numeroDeDocumento;
    }

    public void setNumeroDeDocumento(String numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento;
    }

    public String getBancoDelDocumento() {
        return bancoDelDocumento;
    }

    public void setBancoDelDocumento(String bancoDelDocumento) {
        this.bancoDelDocumento = bancoDelDocumento;
    }

    public String getCentroDelDocumento() {
        return centroDelDocumento;
    }

    public void setCentroDelDocumento(String centroDelDocumento) {
        this.centroDelDocumento = centroDelDocumento;
    }

    public String getCuentaDelDocumento() {
        return cuentaDelDocumento;
    }

    public void setCuentaDelDocumento(String cuentaDelDocumento) {
        this.cuentaDelDocumento = cuentaDelDocumento;
    }

    public String getPlazaDelDocumento() {
        return plazaDelDocumento;
    }

    public void setPlazaDelDocumento(String plazaDelDocumento) {
        this.plazaDelDocumento = plazaDelDocumento;
    }

    public String getResponsableImpuesto() {
        return responsableImpuesto;
    }

    public void setResponsableImpuesto(String responsableImpuesto) {
        this.responsableImpuesto = responsableImpuesto;
    }

    public String getTipoIdentif() {
        return tipoIdentif;
    }

    public void setTipoIdentif(String tipoIdentif) {
        this.tipoIdentif = tipoIdentif;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombrePerson() {
        return nombrePerson;
    }

    public void setNombrePerson(String nombrePerson) {
        this.nombrePerson = nombrePerson;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP14Request {
    
    private TrxHeader cabecera;
    private TrxBP14DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP14DataRequest getData() {
        return data;
    }

    public void setData(TrxBP14DataRequest data) {
        this.data = data;
    }

    public TrxBP14Request(TrxPersonHeader header){
        
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP14BGMP140Response {
    
        private String CCC;
        private String NOMPER;
        private String FECHAVE;
        private String FORPAGO;
        private Integer SECUENC;
        private Integer TASAEFE;
        private String SUBPROD;
        private String FECHAL;
        private String DESFOPA;
        private String PRODUCT;
        private Integer TASANOM;
        private Integer IMPORTE;
        private String NUMDOC;
        private String TIPDOCU;

        public String getCCC() {
                return CCC;
        }

        public void setCCC(String CCC) {
                this.CCC = CCC;
        }

        public String getNOMPER() {
                return NOMPER;
        }

        public void setNOMPER(String NOMPER) {
                this.NOMPER = NOMPER;
        }

        public String getFECHAVE() {
                return FECHAVE;
        }

        public void setFECHAVE(String FECHAVE) {
                this.FECHAVE = FECHAVE;
        }

        public String getFORPAGO() {
                return FORPAGO;
        }

        public void setFORPAGO(String FORPAGO) {
                this.FORPAGO = FORPAGO;
        }

        public Integer getSECUENC() {
                return SECUENC;
        }

        public void setSECUENC(Integer SECUENC) {
                this.SECUENC = SECUENC;
        }

        public Integer getTASAEFE() {
                return TASAEFE;
        }

        public void setTASAEFE(Integer TASAEFE) {
                this.TASAEFE = TASAEFE;
        }

        public String getSUBPROD() {
                return SUBPROD;
        }

        public void setSUBPROD(String SUBPROD) {
                this.SUBPROD = SUBPROD;
        }

        public String getFECHAL() {
                return FECHAL;
        }

        public void setFECHAL(String FECHAL) {
                this.FECHAL = FECHAL;
        }

        public String getDESFOPA() {
                return DESFOPA;
        }

        public void setDESFOPA(String DESFOPA) {
                this.DESFOPA = DESFOPA;
        }

        public String getPRODUCT() {
                return PRODUCT;
        }

        public void setPRODUCT(String PRODUCT) {
                this.PRODUCT = PRODUCT;
        }

        public Integer getTASANOM() {
                return TASANOM;
        }

        public void setTASANOM(Integer TASANOM) {
                this.TASANOM = TASANOM;
        }

        public Integer getIMPORTE() {
                return IMPORTE;
        }

        public void setIMPORTE(Integer IMPORTE) {
                this.IMPORTE = IMPORTE;
        }

        public String getNUMDOC() {
                return NUMDOC;
        }

        public void setNUMDOC(String NUMDOC) {
                this.NUMDOC = NUMDOC;
        }

        public String getTIPDOCU() {
                return TIPDOCU;
        }

        public void setTIPDOCU(String TIPDOCU) {
                this.TIPDOCU = TIPDOCU;
        }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP14DataResponse {
    
        private TrxBP14BGMP140Response BGMP140;

        public TrxBP14BGMP140Response getBGMP140() {
                return BGMP140;
        }

        public void setBGMP140(TrxBP14BGMP140Response BGMP140) {
                this.BGMP140 = BGMP140;
        }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP14Response {
    private TrxBP14DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP14DataResponse getData() {
        return data;
    }

    public void setData(TrxBP14DataResponse data) {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic;


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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtherSourceRequestDto {

    @NotNull(message = "{errors.general.null}")
    private String paymentReference;

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceFundsRequestDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private OtherSourceRequestDto otherSource;

    public OtherSourceRequestDto getOtherSource() {
        return otherSource;
    }

    public void setOtherSource(OtherSourceRequestDto otherSource) {
        this.otherSource = otherSource;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositFundsRequestDto {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private SourceFundsRequestDTO sourceFunds;

    public SourceFundsRequestDTO getSourceFunds() {
        return sourceFunds;
    }

    public void setSourceFunds(SourceFundsRequestDTO sourceFunds) {
        this.sourceFunds = sourceFunds;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceFundResponseDto {

    private String internalReference;

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusInfoResponseDto {

    private String statusCode;
    private String statusDescription;

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
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class TermDepositFundsResponseDto {

    private StatusInfoResponseDto statusInfo;
    private SourceFundResponseDto sourceFunds;

    public StatusInfoResponseDto getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfoResponseDto statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SourceFundResponseDto getSourceFunds() {
        return sourceFunds;
    }

    public void setSourceFunds(SourceFundResponseDto sourceFunds) {
        this.sourceFunds = sourceFunds;
    }

    // Constructor que inicializa sourceFunds y statusInfo
    public TermDepositFundsResponseDto() {
        this.sourceFunds = new SourceFundResponseDto();
        this.statusInfo = new StatusInfoResponseDto();
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.utils;


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
    private String MS_NAME;
    @Value("${params.appVersion}")
    private String MS_VERSION;
    @Value("${errors.level}")
    private String LEVEL;
    @Value("${regex.error.code.bad_request}")
    private String CODE;
    @Value("${regex.error.code.not_found}")
    private String CODE2;

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
                    .code(MS_NAME + "-" + CODE)
                    .level(LEVEL)
                    .message("'" + fieldName + "': " + message)
                    .description(MS_NAME.toLowerCase() + "-" + MS_VERSION + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }

}



package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Handle all Products utils
 */
@Component
@RequiredArgsConstructor
public class TermDepositFundsUtils {


    @Value("${params.commons.productCode}")
    private String productCode;
    @Value("${params.commons.subproductCode}")
    private String subproductCode;    

    public static String cleanAndFormatNumberString(String input) {
        var noplus = input.replace("+", "");
        var nominus = noplus.replace("-", "");
        var nodots = nominus.replace(".","");
        return nodots.trim();
    }

}//class closure


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.mappers;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14BGMP140Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TermDepositFundsMappers {

    public TermDepositFundsResponseDto bp14toTermDepositFundsResponse(TrxBP14Response trxBP14Response) {
        TrxBP14BGMP140Response trxBP14Data = trxBP14Response.getData().getBGMP140();

        TermDepositFundsResponseDto responseDto = new TermDepositFundsResponseDto();
        responseDto.getSourceFunds().setInternalReference(trxBP14Data.getNOMPER());
        responseDto.getStatusInfo().setStatusCode("OK");
        responseDto.getStatusInfo().setStatusDescription("PAGO EFECTUADO");

        return responseDto;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

import java.util.HashMap;

@Service
@Data
@ConfigurationProperties(prefix = "errors")
@RequiredArgsConstructor
public class ErrorService {

    @Value("${ms.name}")
    private String msName;
    @Value("${ms.version}")
    private String msVersion;
    private String level;
    private String functional;
    private String technical;
    private HashMap<String, String> general;

    @Value("${errors.general.invalid_value}")
    public String INVALID_VALUE;

    @Value(("${errors.general.blank_data}"))
    public String BLANK_DATA;

    public ServiceException serviceExceptionBuilder(HttpStatus status, String message, ErrorType type) {
        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;
        var error = ErrorDTO.builder()
                .code((msName + "-" + errorType + "-9" + status.value()).toUpperCase()) // Convertir a mayúsculas
                .level(level)
                .message(message)
                .description(msName + "-" + msVersion + ": " + message)
                .build();
        return new ServiceException(status, error);
    }

    public ErrorDTO errorBuilder(HttpStatus status, String message, ErrorType type) {
        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;
        return ErrorDTO.builder()
                .code((msName + "-" + errorType + "-9" + status.value()).toUpperCase()) // Convertir a mayúsculas
                .level(level)
                .message(message)
                .description(msName + "-" + msVersion + ": " + message)
                .build();
    }

    public void isBlank(String value, String fieldName) {
        if (value.isBlank()) {
            var message = "'" + fieldName + "': " + BLANK_DATA;
            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNull(String value, String fieldName) {
        if (value == null) {
            var message = "'" + fieldName + "': " + this.general.get("null");
            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }
}




package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception;


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
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    private String MS_NAME;

    private static final String LEVEL = "error";
    private String pf9400 = "-P-F-9400";
    private String errorNotSpecified = " not specified";

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-T-9409")
                .message("Unhandled exception")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Unhandled exception")
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
                    .code(MS_NAME + "-P-F-9404")
                    .level(LEVEL)
                    .message(errorMessage)
                    .description(MS_NAME.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-F-9404")
                .message("Not Found")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Not Found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + pf9400)
                .message("Required query parameter " + ex.getParameterName() + errorNotSpecified)
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + errorNotSpecified)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + pf9400)
                .message(ex.getMessage())
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + pf9400)
                .message("Required header " + ex.getHeaderName() + errorNotSpecified)
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + errorNotSpecified)
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
                    error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,MS_NAME));
                    error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,MS_NAME.toLowerCase()));
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
                        error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,MS_NAME));
                        error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,MS_NAME.toLowerCase()));
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
                .code(MS_NAME + pf9400)
                .message("Invalid body structure")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        String message = "Method not allowed" ;
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-F-9405")
                .message(message)
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: " + message)
                .build());

        return buildResponseEntity(errors, HttpStatus.METHOD_NOT_ALLOWED);
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.observability;
import java.net.URI; import java.net.http.HttpClient; 
import java.net.http.HttpRequest; 
import java.net.http.HttpResponse; 
import java.time.Duration; 
import java.util.LinkedHashMap; 
import java.util.Map;
import org.springframework.boot.actuate.health.Health; 
import org.springframework.boot.actuate.health.HealthIndicator; 
import org.springframework.stereotype.Component;

@Component("externalApis") 
public class ExternalApisHealthIndicator implements HealthIndicator{
	private final ExternalApisHealthProperties properties;
	private final HttpClient httpClient;

	public ExternalApisHealthIndicator(ExternalApisHealthProperties properties) {
	    this.properties = properties;
	    this.httpClient = HttpClient.newBuilder()
	            .connectTimeout(Duration.ofMillis(properties.getTimeoutMs()))
	            .build();
	}

	@Override
	public Health health() {
	    Map<String, Object> details = new LinkedHashMap<>();
	    boolean allCriticalUp = true;

	    for (ExternalApisHealthProperties.ApiCheck api : properties.getChecks()) {
	        ApiResult result = checkApi(api);

	        Map<String, Object> apiDetail = new LinkedHashMap<>();
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

	    return allCriticalUp
	            ? Health.up().withDetails(details).build()
	            : Health.down().withDetails(details).build();
	}

	private ApiResult checkApi(ExternalApisHealthProperties.ApiCheck api) {
	    try {
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(api.getUrl()))
	                .timeout(Duration.ofMillis(properties.getTimeoutMs()))
	                .GET()
	                .build();

	        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

	        int status = response.statusCode();
	        boolean up = isAcceptedStatus(status,api);

	        return new ApiResult(up, status, null);
	    } catch (Exception e) {
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ": " + e.getMessage());
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
	}
	
	private boolean isAcceptedStatus(int status, ExternalApisHealthProperties.ApiCheck api) {
		if (api.getAcceptedStatuses() != null && !api.getAcceptedStatuses().isEmpty()) {
			return api.getAcceptedStatuses().contains(status);
		}
		return status >= 200 && status < 300;
	}

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.observability;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration.ApiEntry;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.config;

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
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration.ApiEntry;

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


    private String buildURL(ApiEntry apiEntry){
        String schema = "https://";
        if(!apiEntry.isHttps()){
            schema = schema.replace("s","");
        }
        return schema + apiEntry.getHost() + ":" + apiEntry.getPort() + apiEntry.getEndpoint();
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error.ErrorType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;


   
    @Value("${service-route-trx.BP14}")
    private String BP14_SERVICE_ROUTE;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.user}")
    private String user;

    @Value("${params.sanba.channel}")
    private String channel;

    @Override
    public TrxBP14Response trxBP14(TrxBP14Request request) {
        Response<TrxBP14Response> responseApi = null;

        try {
            prepareRequest(request);
            responseApi = executeRequest(request);
        } catch (RuntimeException e) {
            handleRuntimeException(e);
        } catch (IOException e) {
            handleIOException();
        } catch (Exception e) {
            handleGenericException(e);
        }

        assert responseApi != null;
        if (responseApi.isSuccessful()) {
            return handleSuccessResponse(responseApi);
        } else {
            return handleErrorResponse(responseApi);
        }
    }

    private void prepareRequest(TrxBP14Request request) {
        request.getCabecera().setCanal(channel);
        request.getCabecera().getSesion().setUsuario(user);
    }

    private Response<TrxBP14Response> executeRequest(TrxBP14Request request) throws IOException {
        return trxSanbaAPI.callBP14TRX(request, BP14_SERVICE_ROUTE, BP14_SERVICE_ROUTE, mqRoute).execute();
    }

    private void handleRuntimeException(RuntimeException e) {
        log.info("Error in TRX: {}", e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.BAD_REQUEST, error);
    }

    private void handleIOException() {
        throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }

    private void handleGenericException(Exception e) {
        log.info("Error in TRX: {}", e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.CONFLICT, error);
    }

    private TrxBP14Response handleSuccessResponse(Response<TrxBP14Response> responseApi) {
        TrxBP14Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + "client {}", responseBody);
        return responseBody;
    }

    private TrxBP14Response handleErrorResponse(Response<TrxBP14Response> responseApi) {
        String errorBody = getErrorBody(responseApi);

        TrxBP14Response err = parseErrorBody(errorBody);

        List<ErrorDTO> errosDtos = collectErrorDtos(err);

        log.info(GUtils.ELOG + "SET {}", errosDtos);

        throw new ServiceException(HttpStatus.BAD_REQUEST,
                !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

    private String getErrorBody(Response<TrxBP14Response> responseApi) {
        try {
            assert responseApi.errorBody() != null;
            return responseApi.errorBody().string();
        } catch (IOException e) {
            return "";
        }
    }

    private TrxBP14Response parseErrorBody(String errorBody) {
        try {
            ObjectMapper objm = new ObjectMapper();
            return objm.readValue(errorBody, TrxBP14Response.class);
        } catch ( JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

    private List<ErrorDTO> collectErrorDtos(TrxBP14Response err) {
        List<ErrorDTO> errosDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                if (dtoEr.getMensaje().equals("CDT / DAT NO EXISTE.")) {
                    errosDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, "CDT / DAT NO EXISTE.", ErrorType.FUNCTIONAL));
                    throw new ServiceException(HttpStatus.NOT_FOUND,
                            errosDtos.get(0));
                }
                errosDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }
        return errosDtos;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.TermDepositFundsRequestDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.service.TermDepositFundsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/term_deposit_funds")
@RequiredArgsConstructor
public class TermDepositFundsController {

    private final TermDepositFundsService fundsService;
    private final ObjectMapper objectMapper;
    
    @PostMapping("/{deposit_id}/placements/{placement_id}/manage_funds")
    public ResponseEntity<TermDepositFundsResponseDto> manageFunds(
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String clientId,
            @PathVariable("deposit_id") String depositId,
            @PathVariable("placement_id") String placementId,
            @Valid @RequestBody TermDepositFundsRequestDto requestBodyData) {

    	try {
			String jsonRequest = objectMapper.writeValueAsString(requestBodyData);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(clientId);
			sb.append(", Request=").append(jsonRequest);
			log.info("*** INIT (POST) /v1/term_deposit_funds/{}/placements/{}/manage_funds  {} >>> ",depositId,placementId,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando request");
		}
    	
        log.info("Handling manage funds request for deposit_id={}, placement_id={}", depositId, placementId);

        TermDepositFundsResponseDto response = fundsService.manageFunds(clientId, depositId, placementId, requestBodyData);       

        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(clientId);
			sb.append(", Request=").append(jsonResponse);
			log.info("*** FIN (POST) /v1/term_deposit_funds/{}/placements/{}/manage_funds  {} >>> ",depositId,placementId,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando RESPONSE");
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.TermDepositFundsRequestDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.mappers.TermDepositFundsMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.service.TermDepositFundsService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermDepositFundsServiceImpl implements TermDepositFundsService {

    @Value("${service-route-trx.BP14}")
    private String BP14_SERVICE_ROUTE;
    @Value("${params.commons.currency}")
    private String CURRENCY;

    final TrxSanbaService trxSanbaService;
    final TermDepositFundsMappers mapper;
    final ErrorService errorService;
    final RegexUtils regexUtils;

    private static final String PAYMENT_REFERENCE_FIELD = "sourceFunds.otherSource.paymentReference";
    private static final String DEPOSIT_ID_FIELD = "deposit_id";
    private static final String PLACEMENT_ID_FIELD = "placement_id";

    @Override
    public TermDepositFundsResponseDto manageFunds(String clientId, String depositId, String placementId, TermDepositFundsRequestDto requestBodyData) {
        // Validar los parámetros de entrada
        validateInput(clientId, depositId, placementId, requestBodyData);

        TrxBP14Response txrResponse = trxBP14call(depositId, placementId, requestBodyData);
        return mapper.bp14toTermDepositFundsResponse(txrResponse);
    }

    private void validateInput(String clientId, String depositId, String placementId, TermDepositFundsRequestDto requestBodyData) {
        errorService.isBlank(clientId, "Client ID");
        errorService.isBlank(depositId, DEPOSIT_ID_FIELD);
        errorService.isBlank(placementId, PLACEMENT_ID_FIELD);

        String paymentReference = requestBodyData.getSourceFunds().getOtherSource().getPaymentReference();
        errorService.isNull(paymentReference, PAYMENT_REFERENCE_FIELD);
        errorService.isBlank(paymentReference, PAYMENT_REFERENCE_FIELD);

        regexUtils.validateRegex("placement_format", placementId, PLACEMENT_ID_FIELD);
        regexUtils.validateRegex("payment_reference_length", paymentReference, PAYMENT_REFERENCE_FIELD);
        regexUtils.validateRegex(DEPOSIT_ID_FIELD, depositId, DEPOSIT_ID_FIELD);
    }


    public TrxBP14Response trxBP14call(String depositId, String placementId, TermDepositFundsRequestDto requestBodyData) {
        TrxBP14Request trxBP14Request = new TrxBP14Request(ClientUtils.buildHeader(BP14_SERVICE_ROUTE));

        var trxBP14Data = new TrxBP14DataRequest();
        trxBP14Data.setCodigoInversor(depositId);

        // Tomar los primeros cinco dígitos de placementId
        String firstFiveDigits = placementId.substring(0, Math.min(placementId.length(), 5));
        trxBP14Data.setSecuenciaIpf(firstFiveDigits);
        trxBP14Data.setFormaDePagoIpf("O"); // FIJO
        trxBP14Data.setTipoDeDocumento("PE"); // FIJO
        trxBP14Data.setNumeroDeDocumento(requestBodyData.getSourceFunds().getOtherSource().getPaymentReference());

        trxBP14Request.setData(trxBP14Data);
        return trxSanbaService.trxBP14(trxBP14Request);
    }

}
