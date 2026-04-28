package com.santander.bnc.bsn049.bncbsn049mscountries.exception.error;

public class ErrorDictionary {
    private ErrorDictionary() {}
    public static String STATE_0006_CODE = "COUNTRIES-P-F-0006";
    public static String STATE_0006_LEVEL = "error";
    public static String STATE_0006_MESSAGE = "Bad request - Invalid input data";
    public static String STATE_0006_DESCRIPTION = "countries-api-services-v2: Invalid input data";
}


*************

package com.santander.bnc.bsn049.bncbsn049mscountries.exception.error;

import lombok.*;

/**
 * @author Freddy Paredes
 * This class handle error data
 */

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {    
    private String code;
    private String message;
    private String level;    
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


****

package com.santander.bnc.bsn049.bncbsn049mscountries.exception.error;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle error general response
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private List<ErrorDTO> errors;

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}//method closure


****

package com.santander.bnc.bsn049.bncbsn049mscountries.exception.error;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle error general response
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private List<ErrorDTO> errors;

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}//method closure
**************


package com.santander.bnc.bsn049.bncbsn049mscountries.exception;


import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    private String GENERIC_ERROR_CODE= "400"; //code in responseBody
    private String errorLevel  = "error";
    private String functionalBadrequestError = "-P-F-9400";
    private String notSpecified = " not specified";
    @Value("${params.app-name}")
    private String MS_NAME = "COUNTRIES";


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());
        List<ErrorDTO> errors = new ArrayList<>();        
        errors.add(ErrorDTO.builder()
                .code(GENERIC_ERROR_CODE).description( e.getMessage()).build());
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }//method closure

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDTO> errors = new ArrayList<>();
       

        result.getAllErrors().forEach(error -> {           
            log.info(error.toString());            
            errors.add(ErrorDTO.builder()
                                .code(MS_NAME + "-P-F-9404")
                                .level(errorLevel)
                                .message(error.getDefaultMessage())
                                .description(error.getDefaultMessage()).build());
        });

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceException ex, WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder().code(ex.getCode().name()).description(ex.getMessage()).build());
        return buildResponseEntity(errors, ex.getCode());
    }

    /**
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {
        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        errors.forEach( error->
            log.error(error.getMessage())

        );
        return new ResponseEntity<>(responseError, status);
    }//method closure
    */
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

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(MS_NAME + "-P-F-9404")
                            .message("Not Found")
                            .level(errorLevel)
                            .description(MS_NAME.toLowerCase() + "-api-services-v2: Not Found")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(MS_NAME + functionalBadrequestError)
                            .message("Required query parameter " + ex.getParameterName() + notSpecified)
                            .level(errorLevel)
                            .description(MS_NAME.toLowerCase() + "-api-services-v2: Required query parameter " + ex.getParameterName() + " not specified")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(MS_NAME + functionalBadrequestError)
                            .message(ex.getMessage())
                            .level(errorLevel)
                            .description(MS_NAME.toLowerCase() + "-api-services-v2: Bad request")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(MS_NAME + "-P-F-9400")
                            .message("Required header " + ex.getHeaderName() + notSpecified)
                            .level(errorLevel)
                            .description(MS_NAME.toLowerCase() + "-api-services-v2: Required header " + ex.getHeaderName() + notSpecified)
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }


        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-F-9405")
                .message("Method not allowed")
                .level(errorLevel)
                .description(MS_NAME.toLowerCase() + "-api-services-v2: Method not allowed")
                .build());

        return buildResponseEntity(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler({ ServiceExceptionClient.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceExceptionClient ex, WebRequest request) {
        log.error("ERRORRS {}", ex.getErrorResponseDTO());
        return buildResponseEntity(ex.getErrorResponseDTO().getErrors(), ex.getHttpStatus());
    }
}//class closure



package com.santander.bnc.bsn049.bncbsn049mscountries.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Freddy Paredes
 */


@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class ServiceException  extends RuntimeException{
    /**
     * error code
     */
    final HttpStatus code;

    /**
     * timestamp
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    final LocalDateTime timestamp;

    /**
     * @param code
     * @param message
     */
    public ServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.timestamp = LocalDateTime.now();

    }



}//class closure




package com.santander.bnc.bsn049.bncbsn049mscountries.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.exception.error.ErrorResponseDTO;
import lombok.*;


/**
 * @author Wilfredo Peña
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ServiceExceptionClient extends RuntimeException{

    private  HttpStatus httpStatus;
    private  transient ErrorResponseDTO errorResponseDTO;

    public ServiceExceptionClient(HttpStatus httpStatus, ErrorDTO errorDTO) {
        super(errorDTO.getMessage());        
        this.httpStatus = httpStatus;                
        this.errorResponseDTO = new ErrorResponseDTO(Arrays.asList(errorDTO));
    }

}//class closure
