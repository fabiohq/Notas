package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.input;

import java.util.Optional;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.output.UserManagementOutputPort;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.usecases.UserManagementUseCase;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ExampleUserRecord;
import org.springframework.stereotype.Component;

/**
 * Input port for user application
 * 
 * @author Created by Team DCOE Mx
 */
@Component
public class UserManagementInputPort implements UserManagementUseCase {

	private final UserManagementOutputPort userManagementOutputPort;

	/**
	 * Constructs a new UserManagementInputPort with its specified output port.
	 * 
	 * @param userManagementOutputPort the UserManagementOutputPort to use for
	 *                                 database operations.
	 */
	public UserManagementInputPort(UserManagementOutputPort userManagementOutputPort) {
		this.userManagementOutputPort = userManagementOutputPort;
	}

	/**
	 * Creates a new user with the given name and email.
	 * 
	 * @param name  the name of the user
	 * @param email the email of the user
	 * @return the created user
	 */
	@Override
	public ExampleUserRecord createUser(String name, String email) {
		return userManagementOutputPort.persistUser(name, email);
	}

	/**
	 * Finds a user by the given id.
	 * 
	 * @param id the id of the user
	 * @return the user if found
	 */
	@Override
	public Optional<ExampleUserRecord> findById(Long id) {
		return userManagementOutputPort.findById(id);
	}

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AltairRequest {

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("data")
    private DataRequestBean data;
    @JsonInclude(Include.NON_NULL)
    @JsonProperty("cabecera")
    private CabeceraBean cabecera;

    public DataRequestBean getData() {
        return data;
    }

    public void setData(DataRequestBean data) {
        this.data = data;
    }

    public CabeceraBean getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraBean cabecera) {
        this.cabecera = cabecera;
    }

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import java.util.List;

public class AltairResponse {

    private DataResponseBean data;
    private CabeceraBean cabecera;
    private String autorizacion;
    private PaginacionBean paginacion;
    private List<MesageAltair> avisos;
    private List<MesageAltair> errores;
    private String conexion;
    private String ok;

    public DataResponseBean getData() {
        return data;
    }

    public void setData(DataResponseBean data) {
        this.data = data;
    }

    public CabeceraBean getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraBean cabecera) {
        this.cabecera = cabecera;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public PaginacionBean getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(PaginacionBean paginacion) {
        this.paginacion = paginacion;
    }

    public List<MesageAltair> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<MesageAltair> avisos) {
        this.avisos = avisos;
    }

    public List<MesageAltair> getErrores() {
        return errores;
    }

    public void setErrores(List<MesageAltair> errores) {
        this.errores = errores;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AntiMoneyLaunderingDTO {
    
    private List<RiskCategoryDTO> riskCategories;
    public List<RiskSourceDTO> riskSources;
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

public class BasicDataBean {
    
    private String tipoIdentificacion;

    private String numeroIdentificacion;

    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String fechaNacimiento;

    private String sexo;

    private String ciudad;

    private String nacionalidad;

    private String numper;

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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CabeceraBean {

    @JsonProperty("rutaServicio")
    private String rutaServicio = "ConsultaDatosBasicosPNatural";

    @JsonProperty("sesion")
    private SesionBean sesion;

    @JsonProperty("funcion")
    private String funcion = "Intro";

    @JsonProperty("secuencia")
    private int secuencia = 44204;

    @JsonProperty("canal")
    private String canal = "TFC";

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public SesionBean getSesion() {
        return sesion;
    }

    public void setSesion(SesionBean sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataRequestBean {

    @JsonProperty("PENUMPE")
    private String PENUMPE;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("numDocumento")
    private String numDocumento;

    @JsonProperty("nombre")
    private String nombre;

    public String getPENUMPE() {
        return PENUMPE;
    }

    public void setPENUMPE(String pENUMPE) {
        PENUMPE = pENUMPE;
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

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

public class DataResponseBean {

    private BasicDataBean datosBasicos;

    public BasicDataBean getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(BasicDataBean datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

    @NotNull(message = "{errors.general.null}")
    private String documentNumber;

    private String documentTypeCode;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String code;

    private String level;

    private String message;

    private String description;

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

public class MesageAltair {
    
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


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneFccRequest {

    private String idDocument;

    private String name;

    private String documentType;

    private String doBOrEntityCreationDate;

    private String country;

    private String countryType;

    private String firstName;

    private String middleName;

    private String firstSurname;

    private String secondSurname;

    private String personType;
    
    private String requester;

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneFccResponse {

    private String idDocument;

    private String name;

    private String documentType;

    private String status;

    private List<String> riskCategories;

    private List<String> riskSources;

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneFccTokenResponse {

    private String jwtToken;

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

public class PaginacionBean {
    
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @Valid
    @NotNull(message = "{errors.general.null}")
    private PersonNameDTO personName;

    @Valid
    @NotNull(message = "{errors.general.null}")
    private ArrayList<DocumentDTO> documents;

    public PersonNameDTO getPersonName() {
        return personName;
    }

    public void setPersonName(PersonNameDTO personName) {
        this.personName = personName;
    }

    public ArrayList<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<DocumentDTO> documents) {
        this.documents = documents;
    }

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonNameDTO {

    @NotNull(message = "{errors.general.null}")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

    private String result;

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskCategoryDTO {

    private String riskCategoryCode;
    private String riskCategoryDescription;

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskSourceDTO {

    private String riskSourceCode;
    private String riskSourceDescription;
    
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesionBean {

    @JsonProperty("usuario")
    private String usuario = "@NETE781";

    @JsonProperty("terminal")
    private String terminal;

    @JsonProperty("horaConexion")
    private String horaConexion;

    @JsonProperty("entorno")
    private String entorno = "N";

    @JsonProperty("perfil")
    private String perfil = "GCAJASTL";

    @JsonProperty("sucursal")
    private String sucursal = "0100";

    @JsonProperty("entidad")
    private String entidad = "0065";

    @JsonProperty("diasRestantesCambioClave")
    private String diasRestantesCambioClave = "29";

    @JsonProperty("fechaContable")
    private String fechaContable = "2022-02-10";

    @JsonProperty("turno")
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

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateStatusResponse {

    private ValidationResultDTO validationResult;

    public AntiMoneyLaunderingDTO antiMoneyLaundering;
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResultDTO {

    private ResultDTO result;
   
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchlistScreeningRequest {

    @Valid
    @NotNull(message = "{errors.general.null}")
    private PersonDTO person;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceException extends Exception {

    private ErrorDTO error;

    private HttpStatus status;

    public ServiceException(HttpStatus status, ErrorDTO error) {
        this.error = error;
        this.status = status;

    }

    public ErrorDTO getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }



}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

public enum ErrorType {

    FUNCTIONAL,
    TECHNICAL;

}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "regex")
public class RegexUtils {

    @Value("${params.appName}")
    private String msName;
    @Value("${params.appVersion}")
    private String msVersion;
    @Value("${errors.level}")
    private String level;
    @Value("${regex.error.code}")
    private String code;

    private HashMap<String, String> type;

    public void validateRegex(String regexType, String value, String fieldName) throws ServiceException {

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

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

public class Sanetizacion {

	private Sanetizacion() {}
	
	private static final int MAX_LEN = 4096;
	private static final Pattern JWT_PART_PATTERN = Pattern.compile("^[A-Za-z0-9_-]+$");
	private static final String CLEAN_TOKEN_PATTERN = "[\\r\\n\t\\x00-\\x1F\\x7F]";
	

    /**
    Sanitiza un JWT externo rompiendo la cadena de taint de Fortify
    mediante decode/encode Base64URL sobre byte[].
    @param rawToken Token JWT crudo proveniente de servicio externo
    @return Token JWT validado y reconstruido desde memoria local
    @throws IllegalArgumentException si el token es nulo, vacío o malformado
    @throws SecurityException si contiene caracteres no permitidos */ 
    public static String token(final String rawToken) {

    // Guard clauses
    if (rawToken == null || rawToken.trim().isEmpty()) { throw new IllegalArgumentException("JWT nulo o vacío"); }

    // 1. Eliminar caracteres de control CR/LF y non-printable
    final String cleanToken = rawToken .trim() .replaceAll(CLEAN_TOKEN_PATTERN, "");

    if (cleanToken.isEmpty() || cleanToken.length() > MAX_LEN) { throw new IllegalArgumentException("JWT con longitud inválida"); }

    // 2. Validar estructura: exactamente 3 segmentos
    final String[] parts = cleanToken.split("\\.", -1); if (parts.length != 3) { throw new IllegalArgumentException( "JWT no contiene exactamente 3 segmentos"); }

    // 3. Por cada segmento: whitelist -> decode -> encode
    final Base64.Decoder decoder = Base64.getUrlDecoder(); final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding(); final StringBuilder safeTokenBuilder = new StringBuilder();

    for (int i = 0; i < 3; i++) { final String part = parts[i];

     if (part == null || part.isEmpty()) {
         throw new IllegalArgumentException("Segmento JWT vacío en posición: " + i);
     }

     // Whitelist estricta antes de decodificar
     if (!JWT_PART_PATTERN.matcher(part).matches()) {
         throw new SecurityException("Caracteres no permitidos en segmento JWT posición: " + i);
     }

     try {
         // TAINT BREAK: String tainted → byte[] -> nuevo String local
         final byte[] decodedBytes = decoder.decode(part.getBytes(StandardCharsets.US_ASCII));

         final String reEncodedPart = encoder.encodeToString(decodedBytes);

         // Whitelist post-recodificación (doble garantía)
         if (!JWT_PART_PATTERN.matcher(reEncodedPart).matches()) {
             throw new SecurityException("Segmento inválido tras recodificación en posición: " + i);
         }

         safeTokenBuilder.append(reEncodedPart);

     } catch (IllegalArgumentException e) {
         throw new IllegalArgumentException("Segmento no es Base64URL válido en posición: " + i, e);
     }

     if (i < 2) {
         safeTokenBuilder.append('.');
     }
    }

    return safeTokenBuilder.toString(); }   
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.client.IAltairInformation;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.AltairRequest;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.AltairResponse;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.AntiMoneyLaunderingDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.CabeceraBean;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.DataRequestBean;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccRequest;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccResponse;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccTokenResponse;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ResultDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.RiskCategoryDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.RiskSourceDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.SesionBean;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ValidateStatusResponse;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ValidationResultDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.WatchlistScreeningRequest;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils.Sanetizacion;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.infrastructure.config.LocalDateTypeAdapter;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class WatchlistScreeningServiceImpl implements WatchlistScreeningService {

    @Value("")
    private String exemptRiskSources;

    @Autowired
    private ErrorService errorService;

    @Autowired
    private RegexUtils regexUtils;

    @Autowired
    RestTemplate restTemplate;

    @Value("${engine.protocol}")
    private String protocol;
    @Value("${engine.host}")
    private String host;
    @Value("${engine.mqRoute}")
    private String mqRoute;
    @Value("${urlOneFcc}")
    private String urlOneFcc;
    @Value("${userOneFcc}")
    private String userOneFcc;
    @Value("${passOneFcc}")
    private String passOneFcc;    
    @Value("${origenOneFcc}")
    private String origenOneFcc;
    
    private ObjectMapper mapper = new ObjectMapper(); 
    @Autowired
    IAltairInformation iAltairInformation;
    private String personNameFullNameField = "person.personName.fullName";
    private String personNameDocumentNumber = "person.documents.documentNumber";
    private String documntDocumentTypeCode = "person.documents.documentTypeCode";
    private static final Pattern PEP_PATTERN = Pattern.compile("\\bPEP(S)?\\b",Pattern.CASE_INSENSITIVE);
//    private static final int MAX_LEN = 4096; 
//    private static final Pattern JWT_PATTERN = Pattern.compile("^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$");
    @Override
    public Object validateStatus(WatchlistScreeningRequest request) throws ServiceException {
        validateRequest(request);
        String jsonRequest = null;
        try {
        	jsonRequest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);             
		} catch (JsonProcessingException e) {
		}       
        log.info("*** INICIA PROCESO validateStatus - Request={} >>>",jsonRequest);
        
        AltairResponse altairResponse = null;
        altairResponse = sendInformationAltair(request.getPerson().getDocuments().get(0).getDocumentNumber(),
                request.getPerson().getDocuments().get(0).getDocumentTypeCode(), null);
        String fullNameAltair = "";

        if (altairResponse.getData().getDatosBasicos().getNombre() != null) {
            if (altairResponse.getData().getDatosBasicos().getPrimerApellido() != null) {
                if (altairResponse.getData().getDatosBasicos().getSegundoApellido() != null) {
                    fullNameAltair = altairResponse.getData().getDatosBasicos().getNombre() + " "
                            + altairResponse.getData().getDatosBasicos().getPrimerApellido() + " "
                            + altairResponse.getData().getDatosBasicos().getSegundoApellido();
                } else {
                    fullNameAltair = altairResponse.getData().getDatosBasicos().getNombre() + " "
                            + altairResponse.getData().getDatosBasicos().getPrimerApellido();
                }
            }
        }
        OneFccRequest requestOneFcc = OneFccRequest.builder()
                /**.idDocument(Integer.valueOf(request.getPerson().getDocuments().get(0).getDocumentNumber())+"")*/
                .idDocument(request.getPerson().getDocuments().get(0).getDocumentNumber())
                .name(fullNameAltair).documentType(altairResponse.getData().getDatosBasicos().getTipoIdentificacion())
                .doBOrEntityCreationDate(altairResponse.getData().getDatosBasicos().getFechaNacimiento())
                .country("CO").countryType("Country of Citizenship").personType("I")
                .firstSurname(altairResponse.getData().getDatosBasicos().getPrimerApellido())
                .requester(origenOneFcc)
                .build();

        OneFccResponse response = sendInformationOneFccList(requestOneFcc);
        ResultDTO resultDTO = new ResultDTO();
        ValidationResultDTO validationResult = new ValidationResultDTO();
        ValidateStatusResponse statusResponse = new ValidateStatusResponse();

        if (response.getStatus().equals("OK")) {
            resultDTO.setResult("Not Match found");
            validationResult.setResult(resultDTO);
            statusResponse.setValidationResult(validationResult);
        } else if (response.getStatus().equals("KO")) {
            AntiMoneyLaunderingDTO antiMoneyLaundering = new AntiMoneyLaunderingDTO();
            List<RiskCategoryDTO> risks = new ArrayList<>();
            List<RiskSourceDTO> sources = new ArrayList<>();

            for (String item : response.getRiskCategories()) {                
            	/**
            	 * Solo se adicionan respuestas diferentes a PEP
            	 */
                if(item!= null && !PEP_PATTERN.matcher(item).find()) {
                	RiskCategoryDTO riskCategory = new RiskCategoryDTO();
                	riskCategory.setRiskCategoryDescription(item);
                	risks.add(riskCategory);
                	
                }else {                	
                	log.info("EXCLUIDA CATEGORIA DE RIESGO [{}], DEL RESPONSE >>>",item);
                }
            }
            
            for (String item : response.getRiskSources()) {
            	/**
            	 * Solo se adicionan listas diferentes a PEP
            	 */ 
            	if(item!= null && !PEP_PATTERN.matcher(item).find()) {
            		RiskSourceDTO riskSource = new RiskSourceDTO();
                    riskSource.setRiskSourceDescription(item);
                    sources.add(riskSource);
            	}else {
            		log.info("EXCLUIDA FUENTE DE RIESGO [{}], DEL RESPONSE >>>",item);
                }   
            }

            if(risks.isEmpty() && sources.isEmpty()) {
            	resultDTO.setResult("Not Match found");            
            }else {
            	log.info("CONTINUAN LISTA {} {}, EN EL RESPONSE >>>",sources,risks);
            	resultDTO.setResult("Match found");
            	antiMoneyLaundering.setRiskCategories(risks);
                antiMoneyLaundering.setRiskSources(sources);
                statusResponse.setAntiMoneyLaundering(antiMoneyLaundering);
            }
            
            validationResult.setResult(resultDTO);
            statusResponse.setValidationResult(validationResult);           
        }
        
        try {       	
			String jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(statusResponse);
			log.info("FINALIZA PROCESO validateStatus RESPONSE={} >>>",jsonResponse);
		} catch (JsonProcessingException e) {
			log.info("ERROR PROCESO validateStatus ={} >>>",e.getMessage());
		}
        return statusResponse;
    }

    private void validateRequest(WatchlistScreeningRequest request) throws ServiceException {
        String fullName = request.getPerson().getPersonName().getFullName();
        String documentNumber = request.getPerson().getDocuments().get(0).getDocumentNumber();
        String documentTypeCode = request.getPerson().getDocuments().get(0).getDocumentTypeCode();

        errorService.isNull(fullName, personNameFullNameField);
        errorService.isBlank(fullName, personNameFullNameField);
        regexUtils.validateRegex("text_80_format", fullName, personNameFullNameField);
        regexUtils.validateRegex("text_80_length", fullName, personNameFullNameField);

        errorService.isNull(documentNumber, personNameDocumentNumber);
        errorService.isBlank(documentNumber, personNameDocumentNumber);
        regexUtils.validateRegex("only_numbers", documentNumber, personNameDocumentNumber);
        regexUtils.validateRegex("max_length_11", documentNumber, personNameDocumentNumber);

        // Nueva validaciÃ³n para documentTypeCode usando regexUtils
        if (documentTypeCode != null) {
            errorService.isBlank(documentTypeCode, documntDocumentTypeCode);
            regexUtils.validateRegex("strict_char_length_2", documentTypeCode, documntDocumentTypeCode);
            regexUtils.validateRegex("document_type_format", documentTypeCode, documntDocumentTypeCode);
        }

    }

    public AltairResponse sendInformationAltair(String document, String typeDocument, String penumpe) {
        AltairResponse altairResponse = new AltairResponse();
        AltairRequest altairRequest = new AltairRequest();
        CabeceraBean cabecera = new CabeceraBean();
        SesionBean sesionBean = new SesionBean();
        cabecera.setSesion(sesionBean);
        altairRequest.setCabecera(cabecera);
        DataRequestBean dataRequest = new DataRequestBean();
        dataRequest.setNumDocumento(document);
        dataRequest.setTipoDocumento(typeDocument);
        dataRequest.setPENUMPE(penumpe);
        altairRequest.setData(dataRequest);
        try {

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();
            String prettyRequest = gson.toJson(altairRequest);
            log.info("INICIA CONSULTA ALTAIR >>> ");
            String url = protocol+"://"+host+"/ConsultaDatosBasicosPNatural?idFormulario=ConsultaDatosBasicosPNatural";
            log.info("URL: {} >>>",url);
            log.info("REQUEST={} >>>",prettyRequest);
           
            altairResponse = iAltairInformation.altairResponse(mqRoute, altairRequest);
           
            String prettyResponse = gson.toJson(altairResponse);
            log.info("RESPONSE={} >>> ",prettyResponse);
        } catch (RetryableException e) {
            log.error("RetryableException Altair={} >>> ", e);
            if (document != null) {
                // throw new DesiredException("'party.person.documents[0].documentNumber' not
                // found", 404);
            } else {
                // throw new DesiredException("'party.partyId' not found", 404);
            }
        } catch (Exception e) {
            log.error("Exception Altair={} >>> ", e);
            if (document != null) {
                // throw new DesiredException("'party.person.documents[0].documentNumber' not
                // found", 404);
            } else {
                // throw new DesiredException("'party.partyId' not found", 404);
            }
        }

        return altairResponse;
    }

    public OneFccResponse sendInformationOneFccList(OneFccRequest request) {
    	log.info("INICIA PROCESO ONE-FCC >>> ");
        ResponseEntity<OneFccResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        try {
            String rawToken =getOneFccToken();
            String safeToken = Sanetizacion.token(rawToken);
           
            HttpHeaders headers = new HttpHeaders();            
            headers.setBearerAuth(safeToken);
            HttpEntity<OneFccRequest> entity = new HttpEntity<>(request, headers);
            
            log.info("ONE-FCC REQUEST LISTAS >>> ");
            String url = urlOneFcc + "/onboarding";
            log.info("URL={} >>> ",url); 
            log.info("HEADER={} >>> ", headers); 
            try {
            	ObjectMapper mapper = new ObjectMapper(); 
                String jsonRequest = mapper.writerWithDefaultPrettyPrinter() .writeValueAsString(request);
                log.info("BODY={} >>>",jsonRequest);
			} catch (Exception e) {
				log.error("ERROR JSON REQUEST={} >>> ",e.getMessage());
			}

            response = restTemplate.exchange(urlOneFcc + "/onboarding", HttpMethod.POST, entity,OneFccResponse.class);
            log.info("RESPONSE LISTAS={} >>>",response.getBody());

        } catch (HttpStatusCodeException e) {
        	log.error("HttpStatusCodeException CONSUMIENDO ONEFCC STATUS_CODE={} - RESPONSE={} >>>",e.getStatusCode(),e.getResponseBodyAsString());
        }catch (Exception e) {
        	log.error("Exception CONSUMIENDO ONEFCC MENSAJE={} - DETALLE={} >>>",e.getMessage(),e.getStackTrace());
        }

        return response.getBody();

    }

    public String getOneFccToken() {
    	log.info("GET TOKEN >>> ");
    	String url = urlOneFcc+"/login";
    	log.info("URL={} >>>",url);
    	log.debug("USER={} >>>",userOneFcc);
    	log.debug("PASS={} >>>",passOneFcc);
        ResponseEntity<OneFccTokenResponse> responseToken = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(userOneFcc, passOneFcc);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            responseToken = restTemplate.exchange(urlOneFcc + "/login", HttpMethod.GET, entity,OneFccTokenResponse.class);
            log.info("RESPONSE TOKEN={} >>>",responseToken.getBody());
        } catch (Exception e) {
        	log.error("ERROR CONONSULTANDO TOKEN={} >>>",e.getMessage());
        }
        
        if (responseToken == null)
            throw new IllegalArgumentException("Resouesta de token nulo");
        if (responseToken.getBody() == null)
            throw new IllegalArgumentException("Resouesta de token nulo");
        
        return responseToken.getBody().getJwtToken();
    }    
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils.ErrorType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
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

    @Value(("${errors.general.blank_data}"))
    public String blankData;

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

    public void isBlank(String value, String fieldName) throws ServiceException {
        if (value.isBlank()) {
            var message = "'" + fieldName + "': " + blankData;

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNull(String value, String fieldName) throws ServiceException {
        if (value == null) {
            var message = "'" + fieldName + "': " + this.general.get("null");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

}


package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;
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
	    } catch (InterruptedException e) {
	    	Thread.currentThread().interrupt();
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ": " + e.getMessage());
	    }catch (IOException e) {
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

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;

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
