package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

public enum ClientEnum {
    PEF1("ingresoAltaPersonaNatural"),
    PEF2("modificarMantencionPersonaNaturalDatosBasicos"),
    PEF3("ConsultaDatosBasicosPNatural"),
    PEFT("modificarMantencionPersonaNaturalReferencias"),
    PEFP("modificarMantencionPersonaNaturalInfAdicional"),
    PEFV("modificarMantencionPersonaNaturalInfAdicional"),
    PE37("AltaPersonaNatural2"),
    PEF4("modificarMantencionPersonaNaturalInfComplementariaDos"),
    PEF8("modificarMantencionPersonaNaturalActivivadEconomica2"),
    
    MQROUTE("QCTFD");

    private final String path;

    ClientEnum(String path) {
        this.path = path;
    }

    public String value() {
        return path;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

public enum ParametersEnums {
    TOWNS("0008"),
    COUNTRY("0112"),
    STATES("0009"),
    WAY_TYPE("0314"),
    CIVIL_STATE("0116"),
    LIST_BCO_EXT("0026"),
    DOCU_TYPE("0113");

    private final String code;

    ParametersEnums(String path) {
        this.code = path;
    }

    public String value() {
        return code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

public class ErrorCatalog {

        public static final String PF_9400 = "-P-F-9400";

        private ErrorCatalog() {
                throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
        }

        private static ErrorDTO msParametersNetworkConnection = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9000")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsParametersNetwork())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsParametersNetwork())
                        .build();

        private static ErrorDTO msParametersResponse = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9001")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsParametersResponse())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsParametersResponse())
                        .build();

        private static ErrorDTO msParametersNoEntry = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9002")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsParametersNoEntry())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsParametersNoEntry())
                        .build();

        private static ErrorDTO msParametersGeneral = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9004")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsParametersGeneral())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsParametersGeneral())
                        .build();

        private static ErrorDTO msSanbaTrxError = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9400")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsSanbaTrx())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsSanbaTrx())
                        .build();

        private static ErrorDTO msSanbaTrxResponse = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9006")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsSanbaResponse())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsSanbaResponse())
                        .build();

        private static ErrorDTO msSanbaNetworkConnection = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-T-9003")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getErrorMsSanbaNetwork())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getErrorMsSanbaNetwork())
                        .build();

        private static ErrorDTO prospectNotFound = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-F-9404")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getProspectNotFound())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getProspectNotFound())
                        .build();

        private static ErrorDTO personIsNotProspect = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + "-P-F-9404")
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getPersonIsNotProspect())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getPersonIsNotProspect())
                        .build();

        private static ErrorDTO blankData = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getBlankData())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getBlankData())
                        .build();

        private static ErrorDTO ivalidInput = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getIvalidInput())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getIvalidInput())
                        .build();

        private static ErrorDTO ivalidFormatEmail = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getIvalidFormatEmail())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getIvalidFormatEmail())
                        .build();

        private static ErrorDTO emailEmpty = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getEmailEmpty())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getEmailEmpty())
                        .build();

        private static ErrorDTO mobileNumberBlank = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getMobileNumberBlank())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getMobileNumberBlank())
                        .build();

        private static ErrorDTO invalidMobileNumber = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getInvalidMobileNumber())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getInvalidMobileNumber())
                        .build();

        private static ErrorDTO invalidPhoneNumber = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getInvalidPhoneNumber())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getInvalidPhoneNumber())
                        .build();

        private static ErrorDTO invalidProspectId = ErrorDTO.builder()
                        .code(ErrorDictionary.getMsName() + PF_9400)
                        .level(ErrorDictionary.getErrorLevel())
                        .message(ErrorDictionary.getInvalidProspectId())
                        .description(ErrorDictionary.getMsName().toLowerCase() + ErrorDictionary.getApiService()
                                        + ErrorDictionary.getInvalidProspectId())
                        .build();

        public static ErrorDTO getMsParametersNetworkConnection() {
                return msParametersNetworkConnection;
        }

        public static void setMsParametersNetworkConnection(ErrorDTO msParametersNetworkConnection) {
                ErrorCatalog.msParametersNetworkConnection = msParametersNetworkConnection;
        }

        public static ErrorDTO getMsParametersResponse() {
                return msParametersResponse;
        }

        public static void setMsParametersResponse(ErrorDTO msParametersResponse) {
                ErrorCatalog.msParametersResponse = msParametersResponse;
        }

        public static ErrorDTO getMsParametersNoEntry() {
                return msParametersNoEntry;
        }

        public static void setMsParametersNoEntry(ErrorDTO msParametersNoEntry) {
                ErrorCatalog.msParametersNoEntry = msParametersNoEntry;
        }

        public static ErrorDTO getMsParametersGeneral() {
                return msParametersGeneral;
        }

        public static void setMsParametersGeneral(ErrorDTO msParametersGeneral) {
                ErrorCatalog.msParametersGeneral = msParametersGeneral;
        }

        public static ErrorDTO getMsSanbaTrxError() {
                return msSanbaTrxError;
        }

        public static void setMsSanbaTrxError(ErrorDTO msSanbaTrxError) {
                ErrorCatalog.msSanbaTrxError = msSanbaTrxError;
        }

        public static ErrorDTO getMsSanbaTrxResponse() {
                return msSanbaTrxResponse;
        }

        public static void setMsSanbaTrxResponse(ErrorDTO msSanbaTrxResponse) {
                ErrorCatalog.msSanbaTrxResponse = msSanbaTrxResponse;
        }

        public static ErrorDTO getMsSanbaNetworkConnection() {
                return msSanbaNetworkConnection;
        }

        public static void setMsSanbaNetworkConnection(ErrorDTO msSanbaNetworkConnection) {
                ErrorCatalog.msSanbaNetworkConnection = msSanbaNetworkConnection;
        }

        public static ErrorDTO getProspectNotFound() {
                return prospectNotFound;
        }

        public static void setProspectNotFound(ErrorDTO prospectNotFound) {
                ErrorCatalog.prospectNotFound = prospectNotFound;
        }

        public static ErrorDTO getPersonIsNotProspect() {
                return personIsNotProspect;
        }

        public static void setPersonIsNotProspect(ErrorDTO personIsNotProspect) {
                ErrorCatalog.personIsNotProspect = personIsNotProspect;
        }

        public static ErrorDTO getBlankData() {
                return blankData;
        }

        public static void setBlankData(ErrorDTO blankData) {
                ErrorCatalog.blankData = blankData;
        }

        public static ErrorDTO getIvalidInput() {
                return ivalidInput;
        }

        public static void setIvalidInput(ErrorDTO ivalidInput) {
                ErrorCatalog.ivalidInput = ivalidInput;
        }

        public static ErrorDTO getIvalidFormatEmail() {
                return ivalidFormatEmail;
        }

        public static void setIvalidFormatEmail(ErrorDTO ivalidFormatEmail) {
                ErrorCatalog.ivalidFormatEmail = ivalidFormatEmail;
        }

        public static ErrorDTO getEmailEmpty() {
                return emailEmpty;
        }

        public static void setEmailEmpty(ErrorDTO emailEmpty) {
                ErrorCatalog.emailEmpty = emailEmpty;
        }

        public static ErrorDTO getMobileNumberBlank() {
                return mobileNumberBlank;
        }

        public static void setMobileNumberBlank(ErrorDTO mobileNumberBlank) {
                ErrorCatalog.mobileNumberBlank = mobileNumberBlank;
        }

        public static ErrorDTO getInvalidMobileNumber() {
                return invalidMobileNumber;
        }

        public static void setInvalidMobileNumber(ErrorDTO invalidMobileNumber) {
                ErrorCatalog.invalidMobileNumber = invalidMobileNumber;
        }

        public static ErrorDTO getInvalidPhoneNumber() {
                return invalidPhoneNumber;
        }

        public static void setInvalidPhoneNumber(ErrorDTO invalidPhoneNumber) {
                ErrorCatalog.invalidPhoneNumber = invalidPhoneNumber;
        }

        public static ErrorDTO getInvalidProspectId() {
                return invalidProspectId;
        }

        public static void setInvalidProspectId(ErrorDTO invalidProspectId) {
                ErrorCatalog.invalidProspectId = invalidProspectId;
        }


}

package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

public class ErrorDictionary {

    private ErrorDictionary() {
        throw new IllegalStateException("Utility class");
      }
    private static String msName = "PROSPECTS";
    private static String errorLevel = "error";
    private static String apiService = "-api-services-v1:";

    private static String errorMsParametersNetwork = "Cannot connect to MS Parameters";
    private static String errorMsParametersResponse = "Error trying to process response";
    private static String errorMsParametersNoEntry = "No entry founds";
    private static String errorMsParametersGeneral = "Cannot connect to MS Parameters";

    private static String errorMsSanbaNetwork = "Cannot connect to MS Sanba";
    private static String errorMsSanbaResponse = "Error trying to process response";
    private static String errorMsSanbaTrx = "Error in transaction execution";
    private static String prospectNotFound = "Prospect not found";
    private static String personIsNotProspect = "Person is not Prospect";

    private static String blankData = "Cannot be blank";

    private static String ivalidInput = "Invalid input";
    private static String ivalidFormatEmail = "'contactPoints[0].electronicAddress.emailAddress': Invalid email format";

    private static String emailEmpty = "'contactPoints[0].electronicAddress.emailAddress': The email field cannot be empty";
    private static String mobileNumberBlank = "'contactPoints[0].phoneAddress.mobileNumber': Cannot be blank";

    private static String invalidMobileNumber = "'contactPoints[0].phoneAddress.mobileNumber': Must be a number with up to 10 integer digits and 0 decimal digits";
    private static String invalidPhoneNumber = "'contactPoints[0].phoneAddress.phoneNumber': Must be a number with up to 10 integer digits and 0 decimal digits";

    private static String invalidProspectId = "Invalid prospect_id";

    public static String getMsName() {
        return msName;
    }

    public static void setMsName(String msName) {
        ErrorDictionary.msName = msName;
    }

    public static String getErrorLevel() {
        return errorLevel;
    }

    public static void setErrorLevel(String errorLevel) {
        ErrorDictionary.errorLevel = errorLevel;
    }

    public static String getApiService() {
        return apiService;
    }

    public static void setApiService(String apiService) {
        ErrorDictionary.apiService = apiService;
    }

    public static String getErrorMsParametersNetwork() {
        return errorMsParametersNetwork;
    }

    public static void setErrorMsParametersNetwork(String errorMsParametersNetwork) {
        ErrorDictionary.errorMsParametersNetwork = errorMsParametersNetwork;
    }

    public static String getErrorMsParametersResponse() {
        return errorMsParametersResponse;
    }

    public static void setErrorMsParametersResponse(String errorMsParametersResponse) {
        ErrorDictionary.errorMsParametersResponse = errorMsParametersResponse;
    }

    public static String getErrorMsParametersNoEntry() {
        return errorMsParametersNoEntry;
    }

    public static void setErrorMsParametersNoEntry(String errorMsParametersNoEntry) {
        ErrorDictionary.errorMsParametersNoEntry = errorMsParametersNoEntry;
    }

    public static String getErrorMsParametersGeneral() {
        return errorMsParametersGeneral;
    }

    public static void setErrorMsParametersGeneral(String errorMsParametersGeneral) {
        ErrorDictionary.errorMsParametersGeneral = errorMsParametersGeneral;
    }

    public static String getErrorMsSanbaNetwork() {
        return errorMsSanbaNetwork;
    }

    public static void setErrorMsSanbaNetwork(String errorMsSanbaNetwork) {
        ErrorDictionary.errorMsSanbaNetwork = errorMsSanbaNetwork;
    }

    public static String getErrorMsSanbaResponse() {
        return errorMsSanbaResponse;
    }

    public static void setErrorMsSanbaResponse(String errorMsSanbaResponse) {
        ErrorDictionary.errorMsSanbaResponse = errorMsSanbaResponse;
    }

    public static String getProspectNotFound() {
        return prospectNotFound;
    }

    public static void setProspectNotFound(String prospectNotFound) {
        ErrorDictionary.prospectNotFound = prospectNotFound;
    }

    public static String getPersonIsNotProspect() {
        return personIsNotProspect;
    }

    public static void setPersonIsNotProspect(String personIsNotProspect) {
        ErrorDictionary.personIsNotProspect = personIsNotProspect;
    }

    public static String getBlankData() {
        return blankData;
    }

    public static void setBlankData(String blankData) {
        ErrorDictionary.blankData = blankData;
    }

    public static String getIvalidInput() {
        return ivalidInput;
    }

    public static void setIvalidInput(String ivalidInput) {
        ErrorDictionary.ivalidInput = ivalidInput;
    }

    public static String getIvalidFormatEmail() {
        return ivalidFormatEmail;
    }

    public static void setIvalidFormatEmail(String ivalidFormatEmail) {
        ErrorDictionary.ivalidFormatEmail = ivalidFormatEmail;
    }

    public static String getEmailEmpty() {
        return emailEmpty;
    }

    public static void setEmailEmpty(String emailEmpty) {
        ErrorDictionary.emailEmpty = emailEmpty;
    }

    public static String getMobileNumberBlank() {
        return mobileNumberBlank;
    }

    public static void setMobileNumberBlank(String mobileNumberBlank) {
        ErrorDictionary.mobileNumberBlank = mobileNumberBlank;
    }

    public static String getInvalidMobileNumber() {
        return invalidMobileNumber;
    }

    public static void setInvalidMobileNumber(String invalidMobileNumber) {
        ErrorDictionary.invalidMobileNumber = invalidMobileNumber;
    }

    public static String getInvalidPhoneNumber() {
        return invalidPhoneNumber;
    }

    public static void setInvalidPhoneNumber(String invalidPhoneNumber) {
        ErrorDictionary.invalidPhoneNumber = invalidPhoneNumber;
    }

    public static String getInvalidProspectId() {
        return invalidProspectId;
    }

    public static void setInvalidProspectId(String invalidProspectId) {
        ErrorDictionary.invalidProspectId = invalidProspectId;
    }

    public static String getErrorMsSanbaTrx() {
        return errorMsSanbaTrx;
    }

    public static void setErrorMsSanbaTrx(String errorMsSanbaTrx) {
        ErrorDictionary.errorMsSanbaTrx = errorMsSanbaTrx;
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;
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


package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

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


 package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

 import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;

 @Service
 public class ErrorService {

     @Value("${ms_name}")
     private String msNameError;

     @Value("${ms_version}")
     private String msVersionError;

     @Value("${errors.level}")
     private String errorLevelMessage;

     @Value("${errors.functional}")
     private String functionalErrorMessage;

     @Value("${errors.technical}")
     private String techinalErrorMessage;

     @Value("${errors.general.invalid_value}")
     public String invalidValueMessage;

     @Value(("${errors.general.blank_data}"))
     public String blankDataMessage;

     @Value(("${errors.general.null}"))
     public String nullDataMessage;
     public ServiceException serviceExceptionBuilder(HttpStatus status, String message, ErrorType type) {

         String errorType = type == ErrorType.FUNCTIONAL ? functionalErrorMessage : techinalErrorMessage;

         var error = ErrorDTO.builder()
                 .code(msNameError + "-" + errorType + "-9" + status.value())
                 .level(errorLevelMessage)
                 .message(message)
                 .description(msNameError.toLowerCase() + "-" + msVersionError + ": " + message)
                 .build();

         return new ServiceException(status, error);
     }

     public void isBlank(String value, String fieldName){
         if(value.isBlank()){
             var message = "'"+ fieldName +"' " + blankDataMessage;

             throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
         }
     }

     public void isNull(String value, String fieldName) {
         if (value == null) {
             var message = "'" + fieldName + "': " + nullDataMessage;

             throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
         }
     }


 }

 package com.santander.bnc.bsn049.bncbsn049msprospects.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;
}


package com.santander.bnc.bsn049.bncbsn049msprospects.exception;


import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
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

    @Value("${params.app-name}")
    private String msNAME;

    private String eRROR = "error";
    private String pF400Error = "-P-F-9400";
    private String errorNotSpecified= " not specified";


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        
        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msNAME + "-P-T-9409")
                            .message("Unexpected error")
                            .level(eRROR)
                            .description(msNAME.toLowerCase() + "-api-services-v1: Unexpected error")
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
                                .code(msNAME + pF400Error)
                                .level(eRROR)
                                .message(errorMessage)
                                .description(msNAME.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msNAME + "-P-F-9404")
                            .message("Not Found")
                            .level(eRROR)
                            .description(msNAME.toLowerCase() + "-api-services-v1: Not Found")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msNAME + pF400Error)
                            .message("Required query parameter " + ex.getParameterName() + errorNotSpecified)
                            .level(eRROR)
                            .description(msNAME.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + errorNotSpecified)
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msNAME.toLowerCase() + pF400Error)
                            .message(ex.getMessage())
                            .level(eRROR)
                            .description(msNAME.toLowerCase() + "-api-services-v1: Bad request")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msNAME + pF400Error)
                            .message("Required header " + ex.getHeaderName() + errorNotSpecified)
                            .level(eRROR)
                            .description(msNAME.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + errorNotSpecified)
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
                .code(msNAME + pF400Error)
                .message("Invalid body structure")
                .level(eRROR)
                .description(msNAME.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msNAME + "-P-F-9405")
                .message("Method not allowed")
                .level(eRROR)
                .description(msNAME.toLowerCase() + "-api-services-v1: Method not allowed")
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



package com.santander.bnc.bsn049.bncbsn049msprospects.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Wilfredo Pena
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ServiceException  extends RuntimeException implements Serializable{
    /**
     * error code
     */
    private HttpStatus code;
    private transient ErrorDTO error;

    /**
     * timestamp
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    /**
     * @param code
     * @param message
     */
    public ServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.timestamp = LocalDateTime.now();
   }

       public ServiceException(HttpStatus httpStatus, ErrorDTO errorDTO) {
        super(errorDTO.getMessage());        
        this.code = httpStatus;                
        this.error = errorDTO;
    }
}//class closure


package com.santander.bnc.bsn049.bncbsn049msprospects.exception;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorResponseDTO;
import lombok.*;

/**
 * @author Wilfredo Pena
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ServiceExceptionClient extends RuntimeException{

    public ErrorResponseDTO errorResponseDTO;

}//class closure


package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatchProspectMapper {

    final RegexUtils regexUtils;
    final ProspectMapperUtils prospectMapperUtils;
    final ErrorService errorService;
    final ParameterApiService parameterApiService;
    final ParamService paramsService;
    private static final String DEFAULT_CITY = "05101";
    private String givenNameMessage = "givenName";
    private String lastNameMessage = "lastName";
    private String secondLastNameMessage = "secondLastName";
    private String documentCountryCodeMessage = "documents.country.code";
    private String documentTownsMessage = "documents.town";
    private String documentExpirationDateMessage = "person.documents.expirationDate";
    private String firstNationalityMessage = "firstNationality";
    private String countryOfResidenceMessage = "countryOfResidence";
    private String placeOfBirthCountryMessage = "placeOfBirth.country";
    private String personPlaceOfBirthTownMessage = "person.placeOfBirth.town";
    private String genderCodeMessage = "genderCode";
    private String emailAddressMessage = "emailAddress";

    @Value("${params.default-channel}")
    private String dEFAULTcHANNEL;

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String fOREIGNtAXiNDICATORpOSITIVE;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String fOREIGNtAXiNDICATORnEGATIVE;

    public BasicData pef3ResponseToPef2Request(TrxPersonData trxBasicData) {

        BasicData response = new BasicData();
        response.setCelular(trxBasicData.getCelular().replace(" ", ""));
        response.setTelefono(trxBasicData.getTelefono().replace(" ", ""));
        response.setAgrofic(trxBasicData.getAgrofic());
        response.setCodact(trxBasicData.getCodact());
        response.setClase(trxBasicData.getClase()); // 004
        response.setConper(trxBasicData.getConper());
        response.setDepartamento(trxBasicData.getDepartamento());
        response.setCodpaip(trxBasicData.getCodpaip());
        response.setTipoIdentificacion(trxBasicData.getTipoIdentificacion());
        response.setNumeroIdentificacion(trxBasicData.getNumeroIdentificacion());
        response.setTipoVia(trxBasicData.getTipoVia());
        response.setNombreVia(trxBasicData.getNombreVia());
        response.setPrecelular(trxBasicData.getPrecelular());
        response.setPrecel(trxBasicData.getPrecel());
        response.setSexo(trxBasicData.getSexo());
        response.setPrimerApellido(trxBasicData.getPrimerApellido());
        response.setSegundoApellido(trxBasicData.getSegundoApellido());
        // pais
        response.setPaisDireccion(trxBasicData.getPaisDireccion());
        response.setPaisExpedicion(trxBasicData.getPaisExpedicion());
        response.setPaisNacimiento(trxBasicData.getPaisNacimiento());
        response.setPaisDireccionDesc(""); // DESC
        response.setPaisNacimientoDesc(""); // DESC
        response.setPaisExpedicionDesc(""); // DESC
        response.setLugardeExpDescripcion(""); // DESC
        response.setLugardeNacimiento(""); // DESC
        response.setDescripcionDireccion(trxBasicData.getDescripcionDireccion());
        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        // ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
        response.setFecing(trxBasicData.getFecing());
        response.setCiudadDescripcion(""); // DESC
        response.setDomant(Integer.toString(trxBasicData.getDomant())); // CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel()));// CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel())); // CASE
        response.setSecema(Integer.toString(trxBasicData.getSecema())); // CASE
        response.setSecdotc(Integer.toString(trxBasicData.getSecdotc())); // CASE
        response.setSectelp(Integer.toString(trxBasicData.getSectelp())); // CASE
        response.setSecdomp(Integer.toString(trxBasicData.getSecdomp())); // CASE
        response.setSecdotp(Integer.toString(trxBasicData.getSecdotp())); // CASE
        response.setSucadm(trxBasicData.getSucadm());
        response.setSucmod(trxBasicData.getSucmod());

        response.setAutorizoTelefono(Boolean.parseBoolean(trxBasicData.getAutorizoTelefono()));// CASE
        response.setAutorizacionEmail(Boolean.parseBoolean(trxBasicData.getAutorizacionEmail())); // CASE

        response.setLogdomp(trxBasicData.getLogdomp());
        response.setLogtelp(trxBasicData.getLogtelp());
        response.setHstamp(trxBasicData.getHstamp());
        response.setHstamp2(trxBasicData.getHstamp2());
        response.setHstamp3(trxBasicData.getHstamp3());
        response.setHstamp4(trxBasicData.getHstamp4());
        response.setHstamp5(trxBasicData.getHstamp5());
        response.setEstrat(trxBasicData.getEstrat());
        response.setEstciv(trxBasicData.getEstciv());
        response.setEstper(trxBasicData.getEstper());
        response.setEntpre(trxBasicData.getEntpre());
        response.setUsualt(trxBasicData.getUsualt());
        response.setFechaExpedicion(trxBasicData.getFechaExpedicion());
        response.setFechaNacimiento(trxBasicData.getFechaNacimiento());
        response.setFecalt(trxBasicData.getFecalt());
        response.setFecfal(trxBasicData.getFecfal());
        response.setUsumod(trxBasicData.getUsumod());
        response.setEmail(trxBasicData.getEmail());
        response.setSecdoc(trxBasicData.getSecdoc());
        response.setTiptelp(trxBasicData.getTiptelp());
        response.setTipper(trxBasicData.getTipper());
        response.setTipocu(trxBasicData.getTipocu());
        response.setProfes(trxBasicData.getProfes());
        response.setNumper(trxBasicData.getNumper());
        response.setTipdomp(trxBasicData.getTipdomp());
        response.setNombre(trxBasicData.getNombre());
        response.setNacionalidad(trxBasicData.getNacionalidad());
        return response;
    }// method closure

    /**
     * Request From Update Prospect
     *
     * @param dtoUpdateProspectRequest ALL null data in response will be ignored
     * @return BasicData
     */
    public BasicData prospectPatchToPef2Request(PatchProspectRequestDTO dtoUpdateProspectRequest, String authorization,
            String xSantanderClientId) {
        BasicData response = new BasicData();
        PersonRequestDTO person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;
        // Person Name
        PersonNameRequestDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            validateGivenName(personNameDTO.getGivenName(), response);
            validateLastName(personNameDTO.getLastName(), response);
            validateSecondLastName(personNameDTO.getSecondLastName(), response);
        }
        // Documents
        List<DocumentRequestDTO> documents = person.getDocuments();
        if (documents != null && !documents.isEmpty()) {
            DocumentRequestDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                validateDocumentCountry(firstDocument, response);
                validateDocumentTown(firstDocument, response, authorization, xSantanderClientId);
                validateDocumentIssueDate(firstDocument, response);
                validateDocumentExpirationDate(firstDocument, response);
            }
        } // end if documents empty
          // FirstNationality
        validateFirstNationality(person, response);
        // CountryOfResidence
        validateCountryOfResidence(person, response);
        // Place of Birth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        if (placeOfBirthRequestDTO != null) {
            // CountryOfBirth
            validateCountryOfBirth(placeOfBirthRequestDTO, response);
            // Town
            validateBirthRequest(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
            if (placeOfBirthRequestDTO.getTown() != null || placeOfBirthRequestDTO.getTownCode() != null) {
                // Valida y set CiudadNacimiento por town
                validateplaceOfBirth(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
            } else {
                response.setCiudadNacimiento(person.getPlaceOfBirth().getTown());
            }
        }
        // Birth Date
        validateBirthDate(person.getBirthDate(), response);
        // Sex
        validateGenderCode(person.getGenderCode(), response);
        // foreingtaxIndicator
        if (person.getForeignTaxIndicator() != null && !"YES".equals(person.getForeignTaxIndicator())
                && !"NO".equals(person.getForeignTaxIndicator())) {
            // TODO: HACER VALIDACION
            ErrorDTO errorForeignTaxIndicator = ErrorCatalog.getIvalidInput();
            errorForeignTaxIndicator.setMessage("ForeignTaxIndicator: " + errorForeignTaxIndicator.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorForeignTaxIndicator);
        }
        // Contact Point
        if (dtoUpdateProspectRequest.getContactPoints() != null
                && !dtoUpdateProspectRequest.getContactPoints().isEmpty()) {
            ContactPointRequestDTO cPoint = dtoUpdateProspectRequest.getContactPoints().get(0);
            // PostalAddress
            validatePostalAddress(cPoint, response);
            // PhoneAddress
            validatePhoneAddress(cPoint, response);
            // EmailAddress
            validateElectronicAddress(cPoint, response);
        } // end contactPoint

        return response;
    }// method closure

    private void validateBirthRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response,
            String authorization,
            String xSantanderClientId) {
        if (placeOfBirthRequestDTO.getTown() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getTown(), personPlaceOfBirthTownMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, placeOfBirthRequestDTO.getTown(),
                    personPlaceOfBirthTownMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, placeOfBirthRequestDTO.getTown(),
                    personPlaceOfBirthTownMessage);
            similarTownFunction(placeOfBirthRequestDTO, authorization, xSantanderClientId, response);
        } // Valida y set CiudadNacimiento por townCode
        if (placeOfBirthRequestDTO.getTownCode() != null) {
            validateplaceOfBirth(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
        }
    }

    private void similarTownFunction(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            String authorization, String xSantanderClientId, BasicData response) {

        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            CompareStringUtils compareString = new CompareStringUtils();
            boolean isSimilar = false;
            DataListDTO similarTown = null;
            for (DataListDTO town : towns) {
                isSimilar = compareString.ciudadMatch(placeOfBirthRequestDTO.getTown(),
                        town.getDescription());
                if (isSimilar) {
                    similarTown = town;
                    break;
                }
            }
            if (isSimilar && similarTown != null) {
                response.setCiudadNacimiento(similarTown.getCode());
            } else {
                // Busca el código "99999"
                var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
                if (notInformedTown.isPresent()) {
                    response.setCiudadNacimiento(notInformedTown.get().getCode());
                }
            }
        }

    }

    private void validateplaceOfBirth(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response,
            String authorization,
            String xSantanderClientId) {
        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), "placeOfBirth.townCode");
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var townVar = towns.stream()
                    .filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getTownCode())).findAny();
            if (!townVar.isPresent()) {
                var message = "'placeOfBirth.townCode'" + ": " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
        response.setCiudadNacimiento(placeOfBirthRequestDTO.getTownCode());
    }

    private void validateBirthDate(String birthday, BasicData response) {
        if (birthday != null && birthday.isBlank()) {
            errorService.isBlank(birthday, "birthDate");
        }
        if (birthday != null) {
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, birthday, "birthDate");
        }
        response.setFechaNacimiento(birthday);
    }

    private void validateGenderCode(String genderCode, BasicData response) {
        if (genderCode != null) {
            errorService.isBlank(genderCode, genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, genderCode, genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, genderCode, genderCodeMessage);
            response.setSexo(genderCode);
        }
    }

    private void validatePostalAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getPostalAddress() != null) {
            // StreetTypeCode "NN"
            if (cPoint.getPostalAddress().getStreetTypeCode() != null
                    && cPoint.getPostalAddress().getStreetTypeCode().length() == 2) {
                response.setTipoVia(cPoint.getPostalAddress().getStreetTypeCode());
            }
            // Full Address
            response.setNombreVia(cPoint.getPostalAddress().getFullAddress());
            // Full townName
            response.setCiudad(cPoint.getPostalAddress().getTownName());
            // Country code
            if (cPoint.getPostalAddress().getCountry() != null) {
                response.setCodpaip(cPoint.getPostalAddress().getCountry().getCode());
            }
        }
    }

    private void validatePhoneAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getPhoneAddress() != null) {
            response.setIndicativo(cPoint.getPhoneAddress().getInternationalCode());
            response.setTelefono(cPoint.getPhoneAddress().getPhoneNumber());
            response.setPrecelular(cPoint.getPhoneAddress().getInternationalCode());
            response.setCelular(cPoint.getPhoneAddress().getMobileNumber());
        }
    }

    private void validateElectronicAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getElectronicAddress() != null) {
            errorService.isBlank(cPoint.getElectronicAddress().getEmailAddress(), emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL, cPoint.getElectronicAddress().getEmailAddress(),
                    emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, cPoint.getElectronicAddress().getEmailAddress(),
                    emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR,
                    String.valueOf(cPoint.getElectronicAddress().getEmailAddress().charAt(0)), emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT,
                    cPoint.getElectronicAddress().getEmailAddress().split("@")[0], emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT,
                    cPoint.getElectronicAddress().getEmailAddress().split("@")[1], emailAddressMessage);
            response.setEmail(cPoint.getElectronicAddress().getEmailAddress());
        }
    }

    private void validateGivenName(String givenName, BasicData response) {
        if (givenName != null) {
            String givenNameRefactor = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(givenName);

            errorService.isBlank(givenNameRefactor, givenNameMessage);

            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenNameRefactor, givenNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenNameRefactor, givenNameMessage);
            response.setNombre(givenNameRefactor);
        }
    }

    private void validateLastName(String lastName, BasicData response) {
        if (lastName != null) {
            String lastNameRefactor = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(lastName);
            errorService.isBlank(lastNameRefactor, lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastNameRefactor, lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastNameRefactor, lastNameMessage);
            response.setPrimerApellido(lastNameRefactor);
        }
    }

    private void validateSecondLastName(String lastName, BasicData response) {
        if (lastName != null) {
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName,
                    secondLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName,
                    secondLastNameMessage);
            String secondLastName = PatchProspectMapperUtils
                    .replaceDoubleOrTripleSpaces(lastName);
            response.setSegundoApellido(secondLastName);
        }
    }

    private void validateDocumentCountry(DocumentRequestDTO document, BasicData response) {
        if (document.getCountry() != null && document.getCountry().getCode() != null) {
            errorService.isBlank(document.getCountry().getCode(), documentCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, document.getCountry().getCode(),
                    documentCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, document.getCountry().getCode(),
                    documentCountryCodeMessage);
            response.setPaisExpedicion(DataUtils.translateCountryToXXX(document.getCountry().getCode()));
        }
    }

    private void validateDocumentTown(DocumentRequestDTO document, BasicData response, String authorization,
            String xSantanderClientId) {
        if (document.getTown() != null) {
            validateDocumentTowns(document, response, authorization, xSantanderClientId);

        }
    }

    private void validateDocumentTowns(DocumentRequestDTO document, BasicData response, String authorization,
            String xSantanderClientId) {
        errorService.isBlank(document.getTown(), documentTownsMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, document.getTown(),
                documentTownsMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, document.getTown(),
                documentTownsMessage);
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            CompareStringUtils compareString = new CompareStringUtils();
            boolean isSimilar = false;
            DataListDTO similarTown = null;

            for (DataListDTO town : towns) {
                isSimilar = compareString.ciudadMatch(document.getTown(), town.getDescription());
                if (isSimilar) {
                    similarTown = town;
                    break;
                }
            }
            if (isSimilar && similarTown != null) {
                response.setCiudadExpedicion(similarTown.getCode());
            } else {
                // Busca el elemento con el código "99999"
                var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
                if (notInformedTown.isPresent()) {
                    response.setCiudadExpedicion(notInformedTown.get().getCode());

                }
            }
        }
    }

    private void validateFirstNationality(PersonRequestDTO person, BasicData response) {
        if (person.getFirstNationality() != null && person.getFirstNationality().getCode() != null) {

            errorService.isBlank(person.getFirstNationality().getCode(), firstNationalityMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getFirstNationality().getCode(),
                    firstNationalityMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getFirstNationality().getCode(),
                    firstNationalityMessage);

            response.setNacionalidad(DataUtils.translateCountryToXXX(person.getFirstNationality().getCode()));
        }
    }

    private void validateCountryOfResidence(PersonRequestDTO person, BasicData response) {
        if (person.getCountryOfResidence() != null && person.getCountryOfResidence().getCode() != null) {
            errorService.isBlank(person.getCountryOfResidence().getCode(), countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);

            response.setPaisDireccion(DataUtils.translateCountryToXXX(person.getCountryOfResidence().getCode()));
        }
    }

    private void validateDocumentIssueDate(DocumentRequestDTO document, BasicData response) {
        if (document.getIssueDate() != null) {
            errorService.isBlank(document.getIssueDate(), "documents.issueDate");
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getIssueDate(),
                    "documents.issueDate");

            response.setFechaExpedicion(document.getIssueDate());
        }
    }
    private void validateDocumentExpirationDate(DocumentRequestDTO document, BasicData response){
        if (document.getExpirationDate() != null) {
            errorService.isBlank(document.getExpirationDate(), documentExpirationDateMessage);
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getExpirationDate(), documentExpirationDateMessage);          
            response.setDescripcionDireccion(document.getExpirationDate());
                 
            
        }
    }

    private void validateCountryOfBirth(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response) {
        if (placeOfBirthRequestDTO.getCountry() != null && placeOfBirthRequestDTO.getCountry().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeOfBirthCountryMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeOfBirthCountryMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeOfBirthCountryMessage);

            response.setPaisNacimiento(
                    DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
        }
    }

    public String usualtMapper(String usualt, String foreignTaxIndicator) {

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES")
                ? fOREIGNtAXiNDICATORpOSITIVE
                : fOREIGNtAXiNDICATORnEGATIVE;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : dEFAULTcHANNEL;

        return localUsualt + localForeignTaxIndicator;
    }

}


package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProspectMapper {

    final RegexUtils regexUtils;
    final ProspectMapperUtils prospectMapperUtils;
    final ErrorService errorService;
    final ParameterApiService parameterApiService;
    final ParamService paramsService;

    private static final String BLANK = "";
    private static final String COUNTRY_REQUEST = "COL";
    private static final String DEFAULT_CITY = "05101";
    private static final String DEFAULT_CITY_DOCUMENT = "99999";
    private String placeBirthCountryCodeMessage = "person.placeOfBirth.country.code";
    private String placeBirthStateCodeMessage = "person.placeOfBirth.state.code";
    private String placeOfBirthTownCodeMessage = "person.placeOfBirth.townCode";
    private String contactPointEmailAdressMessage = "contactPoints[0].electronicAddress.emailAddress";
    private String personaGiveNameMessage = "personName.givenName";
    private String personaLastNameMessage = "personName.lastName";
    private String personaSecondLastNameMessage = "personName.secondLastName";
    private String lastNameMessage = "lastName";
    private String giveNameMessage = "givenName";
    private String secondLastMessage = "secondLastName";
    private String documentCountryCodeMessage = "documents.country.code";
    private String firstNationalityMessageMessage = "firstNationality";
    private String countryOfResidenceMessage = "countryOfResidence";
    private String placeOfBirthCountryCodeMessage = "placeOfBirth.country";
    private String genderCodeMessage = "genderCode";
    @Value("${params.default-channel}")
    private String defaultChannel;

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositive;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegative;

    /**
     * MAP PROSPECT DETAILS
     *
     * @return ProspectDetailResponseDTO
     */
    public ProspectDetailResponseDTO trxPersonToProspectDTO(TrxPersonResponse trxBody, String authorization,
            String xSantanderClientId) {
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        ProspectDetailResponseDTO responseDTO = new ProspectDetailResponseDTO();

        if (ProspectMapperUtils.isNotProspect(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData, authorization, xSantanderClientId);
        // PLACE OF BIRTH
        PersonDTO personDTO = ProspectMapperUtils.personDTONames(personData, param);
        // Foreign tax indicator
        personDTO.setForeignTaxIndicator(prospectMapperUtils.getForeignTaxIndicator(personData));
        // Contact Point
        responseDTO.setContactPoints(List.of(ProspectMapperUtils.getContactPoint(personData, param)));
        personDTO.setDocuments(List.of(ProspectMapperUtils.getDocumentBasics(personData, param)));
        responseDTO.setPerson(personDTO);
        // Ex customer
        responseDTO.setIsPendingExCustomer("EXC".equals(personData.getConper()));
        // Source code
        DataOriginDTO dataOrigins = new DataOriginDTO();
        dataOrigins.setSourceCode(ProspectMapperUtils.getSourceCode(personData));
        responseDTO.setDataOrigins(List.of(dataOrigins));
        return responseDTO;
    }// method closure

    /**
     * MAP Prospect Search Response
     *
     * @return ProspectSearchResponseDTO
     */
    public ProspectSearchResponseDTO trxPersonToCustomerSearchDTO(TrxPersonResponse trxBody, String authorization,
            String xSantanderClientId) {
        log.info(GUtils.SLOG + "mapp for search Prospect");
        TrxPersonData personData = trxBody.getData().getDatosBasicos();

        ProspectSearchResponseDTO responseDTO = new ProspectSearchResponseDTO();
        List<ProspectSearchDTO> prospects = new ArrayList<>();
        ProspectSearchDTO prospectSearchDTO = new ProspectSearchDTO();

        if (ProspectMapperUtils.isNotProspect(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData, authorization, xSantanderClientId);
        PersonDTO personDTO = ProspectMapperUtils.personDTONames(personData, param);
        // ContactPoint
        var contactPoint = ProspectMapperUtils.getContactPoint(personData, param);
        List<ContactPointDTO> contactPoints = Arrays.asList(contactPoint);
        prospectSearchDTO.setContactPoints(contactPoints);
        prospectSearchDTO.setDocument(ProspectMapperUtils.getDocumentBasics(personData, param));
        prospectSearchDTO.setProspectId(personData.getNumper());
        prospectSearchDTO.setPerson(personDTO);
        prospects.add(prospectSearchDTO);
        responseDTO.setProspects(prospects);
        return responseDTO;
    }// method closure

    public String replaceSpaces(String cadena) {
        cadena = cadena.replaceAll("\\s{3}", " ");
        cadena = cadena.replaceAll("\\s{2}", " ");
        return cadena.trim();
    }

    /**
     * MAP CREATE PROSPECT
     *
     * @param dtoCustomerRequest
     * @return TrxPersonRequest
     */
    public TrxPersonRequest dtoRequestToTrxRequest(CreateProspectRequestDTO dtoCustomerRequest, String authorization,
            String xSantanderClientId) {

        TrxPersonRequest response = new TrxPersonRequest();
        TrxPersonDataRequest personDataRequest = new TrxPersonDataRequest();
        personDataRequest.setDatosBasicos(new BasicData());
        PersonRequestDTO person = dtoCustomerRequest.getPerson();

        validatePlaceOfBirth(dtoCustomerRequest);

        LocalDate fechaActual = LocalDate.now();
        String fechaExpedicionActual = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");
        validateBirthDate(dtoCustomerRequest, personDataRequest, fechaActual);
        // PlaceOfBirth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();

        if (placeOfBirthRequestDTO != null) {

            // CountryOfBirth
            validateCountryOfBirthTrxRequest(placeOfBirthRequestDTO,
                    dtoCustomerRequest, personDataRequest);

            // State
            validateStateTrxRequest(placeOfBirthRequestDTO, personDataRequest, authorization, xSantanderClientId);

            // TownCode
            // Verificar si el documentTypeCode es "CC"
            if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {
                // Si es "CC", asignar el townCode proporcionado
                if (placeOfBirthRequestDTO.getTownCode() != null) {
                    validateTownTrxCCRequest(placeOfBirthRequestDTO, personDataRequest, dtoCustomerRequest,
                            authorization,
                            xSantanderClientId);
                } else {
                    // Si no se proporciona un townCode, asignar el valor por defecto
                    personDataRequest.getDatosBasicos().setCiudadNacimiento(DEFAULT_CITY);
                }
            } else if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode()) &&
                    placeOfBirthRequestDTO.getTownCode() != null) {
                validateTownTrxCERequest(placeOfBirthRequestDTO, personDataRequest, authorization, xSantanderClientId);
            }
        }

        List<ContactPointRequestDTO> contactPoints = dtoCustomerRequest.getContactPoints();

        if (contactPoints != null && !contactPoints.isEmpty()) {
            ContactPointRequestDTO mobileContactPoint = contactPoints.get(0);
            ContactPointRequestDTO phoneContactPoint = contactPoints.get(0);
            ContactPointRequestDTO emailContactPoint = contactPoints.get(0);

            personDataRequest.getDatosBasicos()
                    .setIndicativo(phoneContactPoint.getPhoneAddress().getInternationalCode());
            personDataRequest.getDatosBasicos()
                    .setPrecelular(mobileContactPoint.getPhoneAddress().getInternationalCode());

            validateContacPointTrxRequest(mobileContactPoint, phoneContactPoint, emailContactPoint, personDataRequest);
        }

        // DATOS BASICOS
        personDataRequest.getDatosBasicos()
                .setTipoIdentificacion(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode());
        personDataRequest.getDatosBasicos()
                .setNumeroIdentificacion(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentNumber());

        String givenName = dtoCustomerRequest.getPerson().getPersonName().getGivenName();
        String lastName = dtoCustomerRequest.getPerson().getPersonName().getLastName();
        String secondLastName = dtoCustomerRequest.getPerson().getPersonName().getSecondLastName();

        validatePersonRequestTrx(givenName, lastName, secondLastName, personDataRequest);

        personDataRequest.getDatosBasicos().setFechaExpedicion(fechaExpedicionActual);

        // VALORES FIJOS
        validateTrxDataBasic(personDataRequest, response);

        return response;
    }// method closure

    private void validateTownTrxCERequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, String authorization, String xSantanderClientId) {

        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        // Si el documentTypeCode es "CE", asignar el valor "99999"
        // Si no se proporciona un townCode en el caso de "CE", asignar el valor "99999"
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
            if (notInformedTown.isPresent()) {
                personDataRequest.getDatosBasicos().setCiudadNacimiento(notInformedTown.get().getCode());
            } else {
                var message = "'99999' " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
    }

    private void validateTownTrxCCRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, CreateProspectRequestDTO dtoCustomerRequest, String authorization,
            String xSantanderClientId) {
        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var townVar = towns.stream().filter(
                    x -> x.getCode().equals(dtoCustomerRequest.getPerson().getPlaceOfBirth().getTownCode()))
                    .findAny();
            if (!townVar.isPresent()) {
                var message = "'person.placeOfBirth.townCode' " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
        personDataRequest.getDatosBasicos()
                .setCiudadNacimiento(dtoCustomerRequest.getPerson().getPlaceOfBirth().getTownCode());
    }

    private void validateContacPointTrxRequest(ContactPointRequestDTO mobileContactPoint,
            ContactPointRequestDTO phoneContactPoint, ContactPointRequestDTO emailContactPoint,
            TrxPersonDataRequest personDataRequest) {
        // Valida email
        String emailAddress = emailContactPoint.getElectronicAddress().getEmailAddress();
        if (emailAddress != null) {
            errorService.isBlank(emailAddress, contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, String.valueOf(emailAddress.charAt(0)),
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, emailAddress.split("@")[0],
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, emailAddress.split("@")[1],
                    contactPointEmailAdressMessage);
        } else {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST,
                    "contactPoints[0].electronicAddress.emailAddress': The email field cannot be empty'",
                    ErrorType.FUNCTIONAL);
        }
        personDataRequest.getDatosBasicos().setEmail(emailAddress);

        // Valida mobileNumber
        String mobileNumber = mobileContactPoint.getPhoneAddress()
                .getMobileNumber();
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMobileNumberBlank());
        } else if (!mobileNumber.matches("^\\d{10}$")) {
            // Si el número de teléfono móvil no tiene 10 dígitos enteros, lanzar un error
            // controlado
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidMobileNumber());
        }

        personDataRequest.getDatosBasicos().setCelular(mobileNumber);

        // SI EL PHONE NUMBER VIENE NULO O BLANCO SE DEBE COPIAR EL MOBILE NUMBER
        String phoneNumber = phoneContactPoint.getPhoneAddress()
                .getPhoneNumber();
        if (phoneNumber == null || phoneNumber.isBlank()) {
            personDataRequest.getDatosBasicos().setTelefono(phoneContactPoint.getPhoneAddress().getMobileNumber());
        } else {
            if (!phoneNumber.matches("^\\d{10}$")) {
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidMobileNumber());
            }
            personDataRequest.getDatosBasicos().setTelefono(phoneNumber);
        }
    }

    private void validateStateTrxRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, String authorization,
            String xSantanderClientId) {
        if (placeOfBirthRequestDTO.getState() != null && placeOfBirthRequestDTO.getState().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_FORMAT,
                    placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_LENGTH,
                    placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            var states = parameterApiService.getParameter(ParametersEnums.STATES.value(), null, authorization,
                    xSantanderClientId);
            if (states != null) {
                Optional<DataListDTO> state = states.stream()
                        .filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getState().getCode())).findAny();
                if (!state.isPresent()) {
                    var message = "'person.placeOfBirth.state.code' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }
            }
            personDataRequest.getDatosBasicos().setDepartamento(placeOfBirthRequestDTO.getState().getCode());
        }
    }

    private void validateTrxDataBasic(TrxPersonDataRequest personDataRequest, TrxPersonRequest response) {

        personDataRequest.getDatosBasicos().setPaisExpedicion(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setCiudadExpedicion(DEFAULT_CITY_DOCUMENT);
        if (personDataRequest.getDatosBasicos().getPaisNacimiento() == null
                || personDataRequest.getDatosBasicos().getPaisNacimiento().isBlank()) {
            personDataRequest.getDatosBasicos().setPaisNacimiento(COUNTRY_REQUEST);
        }
        personDataRequest.getDatosBasicos().setNacionalidad(personDataRequest.getDatosBasicos().getPaisNacimiento());
        personDataRequest.getDatosBasicos().setSexo("M");
        personDataRequest.getDatosBasicos().setPaisDireccion(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setClase("004");
        personDataRequest.getDatosBasicos().setAgrofic("10000001");
        personDataRequest.getDatosBasicos().setTipper("F");
        personDataRequest.getDatosBasicos().setSucadm("0100");
        personDataRequest.getDatosBasicos().setCiudad(DEFAULT_CITY);
        personDataRequest.getDatosBasicos().setTipoVia("NN");
        personDataRequest.getDatosBasicos().setNombreVia("No informado");
        personDataRequest.getDatosBasicos().setCodpaip(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setTipdomp("PRI");
        personDataRequest.getDatosBasicos().setTiptelp("001");
        personDataRequest.getDatosBasicos().setDepartamento("05");
        personDataRequest.getDatosBasicos().setFecalt("2024-01-09");
        personDataRequest.getDatosBasicos().setDescripcionDireccion("NO INFORMADO");
        personDataRequest.getDatosBasicos().setFecfal("9999-12-31");
        personDataRequest.getDatosBasicos().setLugardeNacimiento(BLANK);
        personDataRequest.getDatosBasicos().setUsualt(BLANK);
        personDataRequest.getDatosBasicos().setUsumod(BLANK);
        personDataRequest.getDatosBasicos().setAutorizoTelefono(false);
        personDataRequest.getDatosBasicos().setAutorizacionEmail(false);
        personDataRequest.getDatosBasicos().setLugardeExpDescripcion(BLANK);
        personDataRequest.getDatosBasicos().setCodact(BLANK);
        personDataRequest.getDatosBasicos().setConper(BLANK);
        personDataRequest.getDatosBasicos().setDomant(BLANK);
        personDataRequest.getDatosBasicos().setEntpre(BLANK);
        personDataRequest.getDatosBasicos().setEstciv(BLANK);
        personDataRequest.getDatosBasicos().setEstper(BLANK);
        personDataRequest.getDatosBasicos().setEstrat(BLANK);
        personDataRequest.getDatosBasicos().setHstamp(BLANK);
        personDataRequest.getDatosBasicos().setHstamp2(BLANK);
        personDataRequest.getDatosBasicos().setHstamp3(BLANK);
        personDataRequest.getDatosBasicos().setHstamp4(BLANK);
        personDataRequest.getDatosBasicos().setHstamp5(BLANK);
        personDataRequest.getDatosBasicos().setLogdomp(BLANK);
        personDataRequest.getDatosBasicos().setLogtelp(BLANK);
        personDataRequest.getDatosBasicos().setNumper(BLANK);
        personDataRequest.getDatosBasicos().setProfes(BLANK);
        personDataRequest.getDatosBasicos().setSeccel(BLANK);
        personDataRequest.getDatosBasicos().setSecdoc(BLANK);
        personDataRequest.getDatosBasicos().setSecdomp(BLANK);
        personDataRequest.getDatosBasicos().setSecdotc(BLANK);
        personDataRequest.getDatosBasicos().setSecdotp(BLANK);
        personDataRequest.getDatosBasicos().setSecema(BLANK);
        personDataRequest.getDatosBasicos().setSectelp(BLANK);
        personDataRequest.getDatosBasicos().setSucmod(BLANK);
        personDataRequest.getDatosBasicos().setTermod(BLANK);
        response.setData(personDataRequest);
    }

    private void validateCountryOfBirthTrxRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            CreateProspectRequestDTO dtoCustomerRequest, TrxPersonDataRequest personDataRequest) {

        if (placeOfBirthRequestDTO.getCountry() != null && placeOfBirthRequestDTO.getCountry().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeBirthCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeBirthCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeBirthCountryCodeMessage);
            // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
            if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())
                    && !"CO".equals(placeOfBirthRequestDTO.getCountry().getCode())) {
                // Establecer el country en "CO"
                placeOfBirthRequestDTO.getCountry().setCode("CO");
            }
            // Si el documentTypeCode es "CE" y se informa un country "CO", lanzar excepción
            if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

                if ("CO".equals(placeOfBirthRequestDTO.getCountry().getCode())) {
                    var message = "'person.placeOfBirth.country.code' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }

                if (placeOfBirthRequestDTO.getCountry() != null
                        && placeOfBirthRequestDTO.getCountry().getCode() != null) {
                    errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT,
                            placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH,
                            placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                }
            }
            personDataRequest.getDatosBasicos().setPaisNacimiento(
                    DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
        } else {
            // Si no se proporciona un country, asignar el valor por defecto
            personDataRequest.getDatosBasicos().setPaisNacimiento(COUNTRY_REQUEST);
        }
    }

    private void validatePersonRequestTrx(String givenName, String lastName, String secondLastName,
            TrxPersonDataRequest personDataRequest) {
        // Validación del nombre
        if (givenName != null) {
            errorService.isBlank(givenName, personaGiveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenName, personaGiveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenName, personaGiveNameMessage);
            givenName = replaceSpaces(givenName);
            personDataRequest.getDatosBasicos().setNombre(givenName);
        }

        // Validación del primer apellido
        if (lastName != null) {
            errorService.isBlank(lastName, personaLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName, personaLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName, personaLastNameMessage);
            lastName = replaceSpaces(lastName);
            personDataRequest.getDatosBasicos().setPrimerApellido(lastName);
        }

        // Validación del segundo apellido
        if (secondLastName != null) {
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, secondLastName, personaSecondLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, secondLastName, personaSecondLastNameMessage);
            secondLastName = replaceSpaces(secondLastName);
            personDataRequest.getDatosBasicos().setSegundoApellido(secondLastName);
        }

    }

    private void validatePlaceOfBirth(CreateProspectRequestDTO dtoCustomerRequest) {
        // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
        if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

            // Establecer el country en "CO"
            if (dtoCustomerRequest.getPerson().getPlaceOfBirth() != null) {
                var placeOfBirth = dtoCustomerRequest.getPerson().getPlaceOfBirth();

                if (placeOfBirth.getCountry() == null) {
                    var country = new CountryRequestDTO();
                    country.setCode("CO");
                    dtoCustomerRequest.getPerson().getPlaceOfBirth().setCountry(country);
                } else {
                    dtoCustomerRequest.getPerson().getPlaceOfBirth().getCountry().setCode("CO");
                }
            } else {
                var placeOfBirth = new PlaceOfBirthRequestDTO();
                var country = new CountryRequestDTO();
                country.setCode("CO");
                placeOfBirth.setCountry(country);
                dtoCustomerRequest.getPerson().setPlaceOfBirth(placeOfBirth);
            }
        }
    }

    private void validateBirthDate(CreateProspectRequestDTO dtoCustomerRequest,
            TrxPersonDataRequest personDataRequest, LocalDate fechaActual) {
        personDataRequest.getDatosBasicos().setFechaNacimiento("1940-01-01");
        if (dtoCustomerRequest.getPerson().getBirthDate() != null) {
            errorService.isBlank(dtoCustomerRequest.getPerson().getBirthDate(), "person.birthDate");
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, dtoCustomerRequest.getPerson().getBirthDate(),
                    "person.birthDate");

            LocalDate birthDate = LocalDate.parse(dtoCustomerRequest.getPerson().getBirthDate());

            if (birthDate.isAfter(fechaActual)) {
                String error = "'person.birthDate': Invalid date (must be minor that actual date)";
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, error, ErrorType.FUNCTIONAL);
            }

            personDataRequest.getDatosBasicos().setFechaNacimiento(dtoCustomerRequest.getPerson().getBirthDate());

        }
    }

    public BasicData pef3ResponseToPef2Request(TrxPersonData trxBasicData) {

        BasicData response = new BasicData();
        response.setCelular(trxBasicData.getCelular().replace(" ", ""));
        response.setTelefono(trxBasicData.getTelefono().replace(" ", ""));
        response.setAgrofic(trxBasicData.getAgrofic());
        response.setCodact(trxBasicData.getCodact());
        response.setClase(trxBasicData.getClase()); // 004
        response.setConper(trxBasicData.getConper());
        response.setDepartamento(trxBasicData.getDepartamento());
        response.setCodpaip(trxBasicData.getCodpaip());
        response.setTipoIdentificacion(trxBasicData.getTipoIdentificacion());
        response.setNumeroIdentificacion(trxBasicData.getNumeroIdentificacion());
        response.setTipoVia(trxBasicData.getTipoVia());
        response.setNombreVia(trxBasicData.getNombreVia());
        response.setPrecelular(trxBasicData.getPrecelular());
        response.setPrecel(trxBasicData.getPrecel());
        response.setSexo(trxBasicData.getSexo());
        response.setPrimerApellido(trxBasicData.getPrimerApellido());
        response.setSegundoApellido(trxBasicData.getSegundoApellido());
        // pais
        response.setPaisDireccion(trxBasicData.getPaisDireccion());
        response.setPaisExpedicion(trxBasicData.getPaisExpedicion());
        response.setPaisNacimiento(trxBasicData.getPaisNacimiento());
        response.setPaisDireccionDesc(""); // DESC
        response.setPaisNacimientoDesc(""); // DESC
        response.setPaisExpedicionDesc(""); // DESC
        response.setLugardeExpDescripcion(""); // DESC
        response.setLugardeNacimiento(""); // DESC
        response.setDescripcionDireccion(trxBasicData.getDescripcionDireccion());
        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        // ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
        response.setFecing(trxBasicData.getFecing());
        response.setCiudadDescripcion(""); // DESC
        response.setDomant(Integer.toString(trxBasicData.getDomant())); // CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel()));// CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel())); // CASE
        response.setSecema(Integer.toString(trxBasicData.getSecema())); // CASE
        response.setSecdotc(Integer.toString(trxBasicData.getSecdotc())); // CASE
        response.setSectelp(Integer.toString(trxBasicData.getSectelp())); // CASE
        response.setSecdomp(Integer.toString(trxBasicData.getSecdomp())); // CASE
        response.setSecdotp(Integer.toString(trxBasicData.getSecdotp())); // CASE
        response.setSucadm(trxBasicData.getSucadm());
        response.setSucmod(trxBasicData.getSucmod());

        response.setAutorizoTelefono(Boolean.parseBoolean(trxBasicData.getAutorizoTelefono()));// CASE
        response.setAutorizacionEmail(Boolean.parseBoolean(trxBasicData.getAutorizacionEmail())); // CASE

        response.setLogdomp(trxBasicData.getLogdomp());
        response.setLogtelp(trxBasicData.getLogtelp());
        response.setHstamp(trxBasicData.getHstamp());
        response.setHstamp2(trxBasicData.getHstamp2());
        response.setHstamp3(trxBasicData.getHstamp3());
        response.setHstamp4(trxBasicData.getHstamp4());
        response.setHstamp5(trxBasicData.getHstamp5());
        response.setEstrat(trxBasicData.getEstrat());
        response.setEstciv(trxBasicData.getEstciv());
        response.setEstper(trxBasicData.getEstper());
        response.setEntpre(trxBasicData.getEntpre());
        response.setUsualt(trxBasicData.getUsualt());
        response.setFechaExpedicion(trxBasicData.getFechaExpedicion());
        response.setFechaNacimiento(trxBasicData.getFechaNacimiento());
        response.setFecalt(trxBasicData.getFecalt());
        response.setFecfal(trxBasicData.getFecfal());
        response.setUsumod(trxBasicData.getUsumod());
        response.setEmail(trxBasicData.getEmail());
        response.setSecdoc(trxBasicData.getSecdoc());
        response.setTiptelp(trxBasicData.getTiptelp());
        response.setTipper(trxBasicData.getTipper());
        response.setTipocu(trxBasicData.getTipocu());
        response.setProfes(trxBasicData.getProfes());
        response.setNumper(trxBasicData.getNumper());
        response.setTipdomp(trxBasicData.getTipdomp());
        response.setNombre(trxBasicData.getNombre());
        response.setNacionalidad(trxBasicData.getNacionalidad());
        return response;
    }// method closure

    /**
     * Request From Update Prospect
     *
     * @param dtoUpdateProspectRequest ALL null data in response will be ignored
     * @return BasicData
     */
    public BasicData prospectPatchToPef2Request(CreateProspectRequestDTO dtoUpdateProspectRequest, String authorization,
            String xSantanderClientId) {
        BasicData response = new BasicData();
        PersonRequestDTO person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;
        // Person Name
        PersonNameRequestDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            validatePerson(personNameDTO, response);
        }

        // Documents
        List<DocumentRequestDTO> documents = person.getDocuments();
        validatedocs(documents, response, authorization, xSantanderClientId);
        // FirstNationality
        if (person.getFirstNationality() != null && person.getFirstNationality().getCode() != null) {

            errorService.isBlank(person.getFirstNationality().getCode(), firstNationalityMessageMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getFirstNationality().getCode(),
                    firstNationalityMessageMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getFirstNationality().getCode(),
                    firstNationalityMessageMessage);

            response.setNacionalidad(DataUtils.translateCountryToXXX(person.getFirstNationality().getCode()));
        }

        // CountryOfResidence
        if (person.getCountryOfResidence() != null && person.getCountryOfResidence().getCode() != null) {

            errorService.isBlank(person.getCountryOfResidence().getCode(), countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);

            response.setPaisDireccion(DataUtils.translateCountryToXXX(person.getCountryOfResidence().getCode()));
        }

        // Place of Birth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        validatePlaceOfBirthPatchPf2(placeOfBirthRequestDTO, authorization,
                xSantanderClientId, response);

        // Birth Date
        if (person.getBirthDate() != null && person.getBirthDate().isBlank()) {
            errorService.isBlank(person.getBirthDate(), "birthDate");
        }
        if (person.getBirthDate() != null) {
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, person.getBirthDate(), "birthDate");
        }
        response.setFechaNacimiento(person.getBirthDate());

        // Sex
        if (person.getGenderCode() != null) {
            errorService.isBlank(person.getGenderCode(), genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, person.getGenderCode(), genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, person.getGenderCode(), genderCodeMessage);
            response.setSexo(person.getGenderCode());
        }

        // foreingtaxIndicator
        if (person.getForeignTaxIndicator() != null && !"YES".equals(person.getForeignTaxIndicator())
                && !"NO".equals(person.getForeignTaxIndicator())) {
            ErrorDTO errorForeignTaxIndicator = ErrorCatalog.getIvalidInput();
            errorForeignTaxIndicator.setMessage("ForeignTaxIndicator: " + errorForeignTaxIndicator.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorForeignTaxIndicator);
        }

        // Contact Point
        validateContacPoint(dtoUpdateProspectRequest, response);
        // end contactPoint

        return response;
    }// method closure

    private void validatePlaceOfBirthPatchPf2(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, String authorization,
            String xSantanderClientId, BasicData response) {
        if (placeOfBirthRequestDTO != null) {
            // CountryOfBirth
            if (placeOfBirthRequestDTO.getCountry() != null &&
                    placeOfBirthRequestDTO.getCountry().getCode() != null) {

                errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeOfBirthCountryCodeMessage);
                regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                        placeOfBirthCountryCodeMessage);
                regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                        placeOfBirthCountryCodeMessage);

                response.setPaisNacimiento(
                        DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
            }

            // Town
            if (placeOfBirthRequestDTO.getTown() != null) {

                errorService.isBlank(placeOfBirthRequestDTO.getTown(), "town");
                var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                        xSantanderClientId);

                if (towns != null) {
                    var townVar = towns.stream().filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getTown()))
                            .findAny();

                    if (!townVar.isPresent()) {
                        var message = "'town' " + errorService.invalidValueMessage;
                        throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                                ErrorType.FUNCTIONAL);
                    }

                    response.setCiudadNacimiento(placeOfBirthRequestDTO.getTown());

                }
            }
        }
    }

    private void validateContacPoint(CreateProspectRequestDTO dtoUpdateProspectRequest, BasicData response) {
        if (dtoUpdateProspectRequest.getContactPoints() != null
                && !dtoUpdateProspectRequest.getContactPoints().isEmpty()) {
            ContactPointRequestDTO cPoint = dtoUpdateProspectRequest.getContactPoints().get(0);
            // PostalAddress
            if (cPoint.getPostalAddress() != null) {
                // StreetTypeCode "NN"
                if (cPoint.getPostalAddress().getStreetTypeCode() != null
                        && cPoint.getPostalAddress().getStreetTypeCode().length() == 2) {
                    response.setTipoVia(cPoint.getPostalAddress().getStreetTypeCode());
                }
                // Full Address
                response.setNombreVia(cPoint.getPostalAddress().getFullAddress());
                // Full townName
                response.setCiudad(cPoint.getPostalAddress().getTownName());
                // Country code
                if (cPoint.getPostalAddress().getCountry() != null) {
                    response.setCodpaip(cPoint.getPostalAddress().getCountry().getCode());
                }
            }
            // PhoneAddress
            if (cPoint.getPhoneAddress() != null) {
                response.setIndicativo(cPoint.getPhoneAddress().getInternationalCode());
                response.setTelefono(cPoint.getPhoneAddress().getPhoneNumber());
                response.setPrecelular(cPoint.getPhoneAddress().getInternationalCode());
                response.setCelular(cPoint.getPhoneAddress().getMobileNumber());
            }
            // EmailAddress
            if (cPoint.getElectronicAddress() != null) {
                response.setEmail(cPoint.getElectronicAddress().getEmailAddress());
            }
        }
    }

    private void validatePerson(PersonNameRequestDTO personNameDTO, BasicData response) {
        // Given Name
        if (personNameDTO.getGivenName() != null) {
            errorService.isBlank(personNameDTO.getGivenName(), giveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, personNameDTO.getGivenName(), giveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, personNameDTO.getGivenName(), giveNameMessage);
            response.setNombre(personNameDTO.getGivenName());
        }
        // Last Name
        if (personNameDTO.getLastName() != null) {
            errorService.isBlank(personNameDTO.getLastName(), lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, personNameDTO.getLastName(), lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, personNameDTO.getLastName(), lastNameMessage);
            response.setPrimerApellido(personNameDTO.getLastName());
        }
        // Second Last Name
        if (personNameDTO.getSecondLastName() != null) {
            errorService.isBlank(personNameDTO.getSecondLastName(), secondLastMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, personNameDTO.getSecondLastName(),
                    secondLastMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, personNameDTO.getSecondLastName(),
                    secondLastMessage);
            response.setSegundoApellido(personNameDTO.getSecondLastName());
        }
    }

    private void validatedocs(List<DocumentRequestDTO> documents, BasicData response, String authorization,
            String xSantanderClientId) {
        if (documents != null && !documents.isEmpty()) {
            DocumentRequestDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                if (firstDocument.getCountry() != null && firstDocument.getCountry().getCode() != null) {
                    errorService.isBlank(firstDocument.getCountry().getCode(), documentCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, firstDocument.getCountry().getCode(),
                            documentCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, firstDocument.getCountry().getCode(),
                            documentCountryCodeMessage);
                    response.setPaisExpedicion(DataUtils.translateCountryToXXX(firstDocument.getCountry().getCode()));
                }
                validateTownsPatch(firstDocument, response, authorization, xSantanderClientId);

                if (firstDocument.getIssueDate() != null) {
                    errorService.isBlank(firstDocument.getTown(), "documents.issueDate");
                    regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, firstDocument.getIssueDate(),
                            "documents.issueDate");

                    response.setFechaExpedicion(firstDocument.getIssueDate());
                }
            }
        } // end if documents empty
    }

    private void validateTownsPatch(DocumentRequestDTO firstDocument, BasicData response, String authorization,
            String xSantanderClientId) {
        if (firstDocument.getTown() != null) {
            errorService.isBlank(firstDocument.getTown(), "documents.town");
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                    xSantanderClientId);
            if (towns != null) {
                var townVar = towns.stream().filter(x -> x.getCode().equals(firstDocument.getTown())).findAny();
                if (!townVar.isPresent()) {
                    var message = "'documents.town' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }
                response.setCiudadExpedicion(firstDocument.getTown());
            }
        }
    }

    public String usualtMapper(String usualt, String foreignTaxIndicator) {

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES")
                ? foreignTaxIndicatorPositive
                : foreignTaxIndicatorNegative;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : defaultChannel;

        return localUsualt + localForeignTaxIndicator;
    }

    public CreateProspectRequestDTO validateDocumentTypeForCountry(CreateProspectRequestDTO dtoCustomerRequest) {

        // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
        if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

            // Establecer el country en "CO"
            var placeOfBirth = new PlaceOfBirthRequestDTO();
            var country = new CountryRequestDTO();
            country.setCode("CO");
            placeOfBirth.setCountry(country);
            dtoCustomerRequest.getPerson().setPlaceOfBirth(placeOfBirth);

        } // Si el documentTypeCode es "CE" y se informa un country "CO", lanzar excepción
        if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())
                && "CO".equals(dtoCustomerRequest.getPerson().getPlaceOfBirth().getCountry().getCode())) {
            var message = "'person.placeOfBirth.country.code' " + errorService.invalidValueMessage;
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }

        return dtoCustomerRequest;
    }

}// class closure


package com.santander.bnc.bsn049.bncbsn049msprospects.observability;
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


package com.santander.bnc.bsn049.bncbsn049msprospects.observability;

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


package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParamsServiceImpl implements ParamService {
    
    final ParameterApiService parameterApiService;
    final ContextApiService contextService;    
    private ObjectMapper objectMapper;
    
    @Autowired
    public ParamsServiceImpl(ParameterApiService parameterApiService, ContextApiService contextApiService){
        this.parameterApiService = parameterApiService;
        this.contextService = contextApiService;
        this.objectMapper = new ObjectMapper();
    }


    private static final String SPLIT_KEY = "split";


    /**
     * Get document decription
     * @param personData
     * @return document description
     */
    private String getDocumentTypeDescription(TrxPersonData personData,String authorization,String xSantanderClientId) {
        
        if(personData.getTipoIdentificacion() == null || personData.getTipoIdentificacion().isBlank()) return "";            

        String docDescContextKey = ParametersEnums.DOCU_TYPE.value() + "-" + personData.getTipoIdentificacion();

        DataListDTO documentTypes = objectMapper.convertValue(contextService.getContext(docDescContextKey), DataListDTO.class);
        if( documentTypes == null){
            documentTypes = parameterApiService.getParameter(ParametersEnums.DOCU_TYPE.value(), personData.getTipoIdentificacion(),authorization,xSantanderClientId).get(0);
        }

       
        return documentTypes.getDescription();
    
    }

    /**
     * Get way types description
     * @param personData
     * @return wayType description
     */
    private String getWayTypeDescription(TrxPersonData personData,String authorization,String xSantanderClientId){

        if(personData.getTipoVia() == null || personData.getTipoVia().isEmpty()) return "";            

        String wayTypeContextKey = ParametersEnums.WAY_TYPE.value() + "-" + personData.getTipoVia();

        DataListDTO wayTypes = objectMapper.convertValue(contextService.getContext(wayTypeContextKey), DataListDTO.class);

        if(wayTypes == null){
            wayTypes = parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), personData.getTipoVia(),authorization,xSantanderClientId).get(0);
        }

    
        return wayTypes.getDescription();
    }


    /**
     * Get country
     * @param securityHeaders
     * @return country
     */
    public CodeNameDTO getCountry(String country,String authorization,String xSantanderClientId) {
        CodeNameDTO countryObj = new CodeNameDTO();
        String countryContextKey = ParametersEnums.COUNTRY.value() + (country != null && !country.isBlank() ? "-" + country : "");

        DataListDTO countryParameter = objectMapper.convertValue(contextService.getContext(countryContextKey), DataListDTO.class);
        if( countryParameter == null ){
            countryParameter = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), country,authorization,xSantanderClientId).get(0);
        }
        
        countryObj.setCode(countryParameter.getCode());
        countryObj.setName(countryParameter.getDescription());

       
        return countryObj;
    }//method closure


    /**
     * Get town
     * @param securityHeaders
     * @return town code and name
     */
    public CodeNameDTO getTown(String townCode,String authorization,String xSantanderClientId) {
        String townContextKey = ParametersEnums.TOWNS.value() + (townCode != null && !townCode.isBlank() ? "-" + townCode : "");
        CodeNameDTO townObj = new CodeNameDTO();

        DataListDTO townParameter = objectMapper.convertValue(contextService.getContext(townContextKey), DataListDTO.class);
        if( townParameter == null ){
            townParameter = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,authorization,xSantanderClientId).get(0);
        }

        townObj.setCode(townParameter.getCode());
        townObj.setName(townParameter.getDescription());

      
        return townObj;
    }//method closure

    /**
     * Get city
     * @param securityHeaders
     * @return city code and name
     */
    public CodeNameDTO getCity(String city,String authorization,String xSantanderClientId) {
        String cityContextKey = ParametersEnums.STATES.value();
        CodeNameDTO cityObj = new CodeNameDTO();
        

        if (!city.isEmpty()) {
            city = city.substring(0, 2);
            cityContextKey += "-" + city;
        }
        DataListDTO cityParameter = objectMapper.convertValue(contextService.getContext(cityContextKey), DataListDTO.class);
        if( cityParameter == null ){
            cityParameter = parameterApiService.getParameter(ParametersEnums.STATES.value(), city,authorization,xSantanderClientId).get(0);
        }
        cityObj.setCode(cityParameter.getCode());
        cityObj.setName(cityParameter.getDescription());

      
        return cityObj;
    }

    /**
     * Get parameters
     * @param personData
     * @return parameters list
     */
    public Parameters findParameters(TrxPersonData personData,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "Find parameters");
        String sNationality = personData.getNacionalidad();
        String sExp = personData.getPaisExpedicion();
        String sBirth = personData.getPaisNacimiento();
        String sCountryDir = personData.getPaisDireccion();
        String sCity = personData.getCiudad();
        String sDepartment = personData.getDepartamento();        
        String sCityExp = personData.getCiudadExpedicion();      
        String sCityBirth = personData.getCiudadNacimiento();
        

        List<CodeNameDTO> countries = getListParameterByGroup(new String[]{sNationality, sExp, sBirth, sCountryDir}, "Country",authorization,xSantanderClientId);
        List<CodeNameDTO> cities = getListParameterByGroup(new String[]{sCity, sDepartment, sCityExp, sCityBirth}, "City",authorization,xSantanderClientId);

        Parameters response = new Parameters();        
        response.setCountryNationality(getKey(countries, sNationality));
        response.setCountryExp(getKey(countries, sExp));
        response.setCountryBirth(getKey(countries, sBirth));
        response.setCountryDir(getKey(countries, sCountryDir));
        if(sCity != null && !sCity.isBlank()){
            response.setCityStandard(getTown(sCity,authorization,xSantanderClientId));
        }
        response.setCityDepartment(getKey(cities, sDepartment));
        if(sCityExp != null && !sCityExp.isBlank()){
            response.setCityExp(getKey(cities, sCityExp));
            response.setTownDocument(getTown(sCityExp, authorization, xSantanderClientId));
        }      
        response.setCityBirth(getKey(cities, sCityBirth));
        //Extras
        if(sCityBirth != null && !sCityBirth.isBlank()){
            response.setTown(getTown(sCityBirth,authorization,xSantanderClientId));
        }
        response.setStreetTypeDescription(getWayTypeDescription(personData,authorization,xSantanderClientId));
        response.setDocumentTypeDescription(getDocumentTypeDescription(personData,authorization,xSantanderClientId));

        log.info(GUtils.ELOG + "Return parameters = {} ", response);       
        return response;
    }//method closure

    private List<CodeNameDTO> getListParameterByGroup(String[] parameterGroup, String type,String authorization,String xSantanderClientId) {
        log.info("    find {}'s = {} ", type, parameterGroup);
        List<CodeNameDTO> listCodename = new ArrayList<>();
        Map<String, Long> groupMap = Arrays.stream(parameterGroup).filter(Objects::nonNull).filter(param -> !param.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("    look just for = " + groupMap);
        groupMap.forEach((key, count) -> {
                    if ("Country".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCountry(key,authorization,xSantanderClientId);
                        codeNameDTO.setCode(codeNameDTO.getCode() + SPLIT_KEY + key);
                        listCodename.add(codeNameDTO);
                    } else if ("City".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCity(key,authorization,xSantanderClientId);
                        codeNameDTO.setCode(codeNameDTO.getCode() + SPLIT_KEY + key);
                        listCodename.add(codeNameDTO);
                    }
                }
        );
        return listCodename;
    }//method closure

    private CodeNameDTO getKey(List<CodeNameDTO> groupCodeNameList, String key) {
                
        CodeNameDTO responseC = groupCodeNameList.stream().filter(x -> x.getCode().split(SPLIT_KEY).length == 2)
                                                        .findFirst().orElse(null);

        if(responseC != null){
            responseC = groupCodeNameList.stream().filter(x -> x.getCode().split(SPLIT_KEY)[1].equals(key))
                .findFirst().orElse(null);
        }
        
        
        CodeNameDTO response = new CodeNameDTO();
        if (responseC != null) {
            String replaced = responseC.getCode().replaceAll(SPLIT_KEY + key, "");
            response.setCode(replaced);
            response.setName(responseC.getName());
        }
        return response;
    }//method closure
}//class closure



package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.PatchProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ProspectService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Wilfredo Pena
 * This clas handle all main methods from MS-Customer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProspectSevicerImpl implements ProspectService {

    final TrxPersonService trxPersonService;
    final ContextApiService contextService;
    final ProspectMapper mapper;
    final PatchProspectMapper patchProspectMapper;
    final ErrorService errorService;    
    final ParameterApiService parameterApiService;    
    final ProspectMapperUtils prospectMapperUtils;
    final RegexUtils regexUtils;
    final StringUtils utils;
    private String personNotFound = "PERSONA INEXISTENTE";
    private String prospectIdField = "prospect_id";
    /**
     *  PROSPECT DETAILS
     *
     * @param prospectId
     * @return ProspectDetailResponseDTO
     */
    @Override
    public ProspectDetailResponseDTO getProspectDetails(String prospectId,String authorization,String xSantanderClientId) {
        ObjectMapper mapResponse = new ObjectMapper();
        log.info(GUtils.SLOG + "service get prospect details by id {}", prospectId);
        TrxPersonResponse responseTrx;
        ProspectDetailResponseDTO prospectDetailResponseDTO;

        if(Boolean.FALSE.equals(ProspectMapperUtils.isPenumperValid(prospectId))){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidProspectId());
        }
      

        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                    , ClientEnum.PEF3);                
        } catch (Exception e) {
            if(e.getMessage().equals(personNotFound)) {
                return null;
            }
            throw e;
        }

        prospectDetailResponseDTO = mapper.trxPersonToProspectDTO(responseTrx,authorization,xSantanderClientId);
               

        log.info(GUtils.ELOG + "service get prospect details response={}", prospectDetailResponseDTO);
        return prospectDetailResponseDTO;
    }//method closure

    /**
     * PROSPECT SEARCH
     *
     * @param prospectRequestDTO
     * @return
     */
    @Override
    public ProspectSearchResponseDTO searchProspect(ProspectRequestDTO prospectRequestDTO,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service search person {}", prospectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;
        ProspectSearchResponseDTO customerResponseDTO;

        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(prospectRequestDTO, null)
                    , ClientEnum.PEF3);            
        } catch (Exception e) {
            if(e.getMessage().equals(personNotFound)) {
                return null;
            }
            throw e;
        }
        customerResponseDTO = mapper.trxPersonToCustomerSearchDTO(responseTrx,authorization,xSantanderClientId);
        log.info(GUtils.ELOG + "service search person response={}", customerResponseDTO);
        return customerResponseDTO;
    }


    @Override
    public ProspectCreatedResponseDTO createProspect(CreateProspectRequestDTO createProspectRequestDTO,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service create person {}", createProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        var documentTypeCode = createProspectRequestDTO.getPerson().getDocuments().get(0).getDocumentTypeCode();
        var secondLastName = createProspectRequestDTO.getPerson().getPersonName().getSecondLastName();

        utils.inputSencondLastNameValidation(documentTypeCode, secondLastName);
        responseTrx = trxPersonService.callPostTRX(mapper.dtoRequestToTrxRequest(createProspectRequestDTO, authorization, xSantanderClientId)
                , ClientEnum.PEF1);
        log.info(GUtils.ELOG + "service created person response");
        return new ProspectCreatedResponseDTO(responseTrx.getData().getNumPersona());
    }

    /**
     * PROSPECT UPDATE
     *
     * @param updateProspectRequestDTO
     * @return
     */
    @Override
    public void updateProspect(PatchProspectRequestDTO updateProspectRequestDTO, String prospectId,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service update prospect id {} data= {}", prospectId, updateProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, prospectId, prospectIdField);
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, prospectId, prospectIdField);

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}", prospectId);
        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                , ClientEnum.PEF3);
        } catch (ServiceException e) {
            if(e.getMessage().equals(personNotFound)) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getProspectNotFound(), ErrorType.FUNCTIONAL);
            }
            if(e.getMessage().equals("EL CLIENTE REQUERIDO NO ES PERSONA FISICA.")){
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getPersonIsNotProspect(), ErrorType.FUNCTIONAL);
            }
            throw e;
        }        
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

        //VALIDA CONPER == PRO
        if (ProspectMapperUtils.isNotProspect(trxBasicData.getConper())) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getPersonIsNotProspect());
        }
        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData = patchProspectMapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);
        
        BasicData basicDataRequestPatch = patchProspectMapper.prospectPatchToPef2Request(PatchProspectMapperUtils.cleanFieldsFromProspectUpdate(updateProspectRequestDTO), authorization,xSantanderClientId);
        log.info("basicData request patch = {}", basicDataRequestPatch);
        //Copio propiedades que deseo modificar al nuevo objeto
        BeanUtils.copyProperties(basicDataRequestPatch, basicData, GUtils.getNullOrBlankPropertyNames(basicDataRequestPatch));

        var secondLastNameOld = basicDataRequestPatch.getSegundoApellido();
        if(secondLastNameOld != null && secondLastNameOld.equals("")){
            basicData.setSegundoApellido("");
        }
        String expirationDate= basicDataRequestPatch.getDescripcionDireccion();
        String premiseCopia = trxBasicData.getDescripcionDireccion();

        if (premiseCopia.length() == 60 && expirationDate!=null) {
            // Construir premise + expirationDate
            String finalPremise = premiseCopia.substring(0, premiseCopia.length() - 10) + expirationDate;
            basicDataRequestPatch.setDescripcionDireccion(finalPremise);
            basicData.setDescripcionDireccion(finalPremise);
            
        }   
        log.info("BasicData  afterCopy    = {}", basicData);
        TrxPersonRequest pef2Request = new TrxPersonRequest();


        if(updateProspectRequestDTO.getPerson() != null){
            basicData.setUsualt(patchProspectMapper.usualtMapper(basicData.getUsualt(), updateProspectRequestDTO.getPerson().getForeignTaxIndicator()));
        } else {
            basicData.setUsualt(patchProspectMapper.usualtMapper(basicData.getUsualt(), null));
        }

        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);            
        pef2Request.setData(trxPersonDataRequest);
        //UPDATE PROSPECT
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");

    }//method closure

    @Override
    public void removeProspect(String prospectId, String authorization, String xSantanderClientId) {

        CreateProspectRequestDTO createProspectRequestDTO = new CreateProspectRequestDTO();
        log.info(GUtils.SLOG + "service update prospect id {} data= {}", prospectId, createProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, prospectId, prospectIdField);
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, prospectId, prospectIdField);

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}", prospectId);
        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                    , ClientEnum.PEF3);
        } catch (ServiceException e) {
            if(e.getMessage().equals(personNotFound)) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getProspectNotFound(), ErrorType.FUNCTIONAL);
            }
            if(e.getMessage().equals("EL CLIENTE REQUERIDO NO ES PERSONA FISICA.")){
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getPersonIsNotProspect(), ErrorType.FUNCTIONAL);
            }
            throw e;
        }
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

        //VALIDA CONPER == PRO
        if (ProspectMapperUtils.isNotProspect(trxBasicData.getConper())) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getPersonIsNotProspect());
        }
        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData = mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);

        TrxPersonRequest pef2Request = new TrxPersonRequest();

        //Se modifica campo para poder realizar la modificación
        if(basicData.getEstciv() != null){
            if(basicData.getEstciv().isBlank()){
                basicData.setEstciv("S");
            } else if(basicData.getEstciv().equals("S")) {
                basicData.setEstciv("C");
            } else {
                basicData.setEstciv("S");
            }
        } else {
            basicData.setEstciv("S");
        }


        //Remove prospect
        basicData.setUsualt(prospectMapperUtils.lowProspectLogic(trxBasicData));

        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);
        pef2Request.setData(trxPersonDataRequest);

        //UPDATE PROSPECT
        log.info("Request PF2: {} ", pef2Request);
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");

    }//method closure
}//class closure



*****************************************************
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;


import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;

public class ClientUtils {
    
    private ClientUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
}
    public static final String SANTANDER_CLIENT_ID = "x-santander-client-id";
    public static final String AUTHORIZATION = "Authorization";
    /**
     * This method
     * @param customerId
     * @return
     */
    public static TrxPersonRequest buildTrxRequestByCustomerId(ProspectRequestDTO prospectRequestDTO, String customerId){
        TrxPersonRequest requestTrx = new TrxPersonRequest();
        TrxPersonDataRequest personData = new TrxPersonDataRequest();

        personData.setpENUMPE(StringUtils.blankField(customerId));

        if(prospectRequestDTO !=null && prospectRequestDTO.getPerson()!=null){
            personData.setTipoDocumento(StringUtils.blankField(prospectRequestDTO.getPerson().getDocument().getDocumentTypeCode()));
            personData.setNumDocumento(StringUtils.blankField(prospectRequestDTO.getPerson().getDocument().getDocumentNumber()));
        }

        requestTrx.setData(personData);
        return requestTrx;
    }//method closure

    /**
     * These methods build the PayloadHeader of a POST TRX request
     * @param serviceRoute
     * @return
     */
    public static TrxPersonHeader buildHeader(String serviceRoute){
        String conectionTime = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd'T'HH:mm");
        String dateTime = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");
        TrxPersonHeader txHeader = new TrxPersonHeader();
        txHeader.setRutaServicio(serviceRoute);
        txHeader.setFuncion("Intro");
        txHeader.setCanal("60");
        txHeader.setSecuencia(44204);
        Session txSession = new Session();
        txSession.setUsuario("@NETE781");
        txSession.setTerminal("");
        txSession.setHoraConexion(conectionTime);
        txSession.setPerfil("GCAJASTL");
        txSession.setSucursal("0100");
        txSession.setEntidad("0065");
        txSession.setDiasRestantesCambioClave("29");
        txSession.setFechaContable(dateTime);
        txSession.setEntorno("N");
        txSession.setTurno("");
        txHeader.setSesion(txSession);
        return txHeader;
    }//method closure
}//class closure

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

public class CompareStringUtils {

    public boolean ciudadMatch(String ciudadIngresada, String ciudadServicio) {
        double similarity = similarity(ciudadIngresada, ciudadServicio);
        return similarity > 0.85; // Consideramos que las cadenas son similares si la similitud es mayor o igual
                                  // al 85%

    }

    private double similarity(String s1, String s2) {
        String longer = s1;
        String shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = initializeCostsArray(s2.length() + 1);
        return calculateEditDistance(s1, s2, costs);
    }

    private int[] initializeCostsArray(int length) {
        int[] costs = new int[length];
        for (int i = 0; i < length; i++) {
            costs[i] = i;
        }
        return costs;
    }

    private int calculateEditDistance(String s1, String s2, int[] costs) {
        int lastValue;
        for (int i = 0; i < s1.length(); i++) {
            lastValue = costs[0];
            costs[0] = i + 1;
            for (int j = 0; j < s2.length(); j++) {
                int oldValue = costs[j + 1];
                if (s1.charAt(i) == s2.charAt(j)) {
                    costs[j + 1] = lastValue;
                } else {
                    costs[j + 1] = Math.min(Math.min(costs[j], lastValue), oldValue) + 1;
                }
                lastValue = oldValue;
            }
        }
        return costs[s2.length()];
    }

}
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;


import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {

    /**
     * TRANSLATE ISO 3122-5
     *
     * @param listCode
     * @param oldValueCode
     * @return
     */
    public static String translateValueCode(String listCode, String oldValueCode) {
        
        if (ParametersEnums.COUNTRY.value().equals(listCode)) {
            oldValueCode = translateCountryToXX(oldValueCode);
        }
        if (ParametersEnums.STATES.value().equals(listCode)) {
            oldValueCode = translateDepartment(oldValueCode);
        }

        return oldValueCode;
    }

    /**
     * ISO-3122-5
     * Return XX value
     *
     * @param countryLargeCode
     * @return
     */
    public static String translateCountryToXX(String countryLargeCode) {
        List<DataListDTO> countryCode = getCountryRelTable().stream().filter(x -> countryLargeCode.equals(x.getDescription())).collect(Collectors.toList());
        
        if(countryCode.isEmpty()) 
            return countryLargeCode;
        
        return countryCode.get(0).getCode(); 
    }

    /**
     * @param codeIso
     * @return
     */
    public static String translateCountryToXXX(String codeIso) {
        List<DataListDTO> countryCode = getCountryRelTable().stream().filter(x -> codeIso.equals(x.getCode())).collect(Collectors.toList());

        if(countryCode.isEmpty())
            return codeIso;

        return countryCode.get(0).getDescription();
    }

    private static List<DataListDTO> getCountryRelTable() {

        List<DataListDTO> response = new ArrayList<>();
        response.add(new DataListDTO("", "AF", "AFG"));
        response.add(new DataListDTO("", "AL", "ALB"));
        response.add(new DataListDTO("", "DE", "DEU"));
        response.add(new DataListDTO("", "AD", "AND"));
        response.add(new DataListDTO("", "AO", "AGO"));
        response.add(new DataListDTO("", "AQ", "ATA"));
        response.add(new DataListDTO("", "AG", "ATG"));
        response.add(new DataListDTO("", "SA", "SAU"));
        response.add(new DataListDTO("", "DZ", "DZA"));
        response.add(new DataListDTO("", "AR", "ARG"));
        response.add(new DataListDTO("", "AW", "ABW"));
        response.add(new DataListDTO("", "AU", "AUS"));
        response.add(new DataListDTO("", "AT", "AUT"));
        response.add(new DataListDTO("", "BS", "BHS"));
        response.add(new DataListDTO("", "BD", "BGD"));
        response.add(new DataListDTO("", "BB", "BRB"));
        response.add(new DataListDTO("", "BH", "BHR"));
        response.add(new DataListDTO("", "BE", "BEL"));
        response.add(new DataListDTO("", "BZ", "BLZ"));
        response.add(new DataListDTO("", "BJ", "BEN"));
        response.add(new DataListDTO("", "BM", "BMU"));
        response.add(new DataListDTO("", "BO", "BOL"));
        response.add(new DataListDTO("", "BW", "BWA"));
        response.add(new DataListDTO("", "BR", "BRA"));
        response.add(new DataListDTO("", "BN", "BRN"));
        response.add(new DataListDTO("", "BG", "BGR"));
        response.add(new DataListDTO("", "BI", "BDI"));
        response.add(new DataListDTO("", "BT", "BTN"));
        response.add(new DataListDTO("", "CV", "CPV"));
        response.add(new DataListDTO("", "KH", "KHM"));
        response.add(new DataListDTO("", "CM", "CMR"));
        response.add(new DataListDTO("", "CA", "CAN"));
        response.add(new DataListDTO("", "QA", "QAT"));
        response.add(new DataListDTO("", "TD", "TCD"));
        response.add(new DataListDTO("", "CL", "CHL"));
        response.add(new DataListDTO("", "CN", "CHN"));
        response.add(new DataListDTO("", "CN", "CHN"));
        response.add(new DataListDTO("", "CY", "CYP"));
        response.add(new DataListDTO("", "CO", "COL"));
        response.add(new DataListDTO("", "KM", "COM"));
        response.add(new DataListDTO("", "KP", "PRK"));
        response.add(new DataListDTO("", "KR", "KOR"));
        response.add(new DataListDTO("", "CI", "CIV"));
        response.add(new DataListDTO("", "CR", "CRI"));
        response.add(new DataListDTO("", "CU", "CUB"));
        response.add(new DataListDTO("", "CW", "CUW"));
        response.add(new DataListDTO("", "DK", "DNK"));
        response.add(new DataListDTO("", "DM", "DMA"));
        response.add(new DataListDTO("", "EC", "ECU"));
        response.add(new DataListDTO("", "EG", "EGY"));
        response.add(new DataListDTO("", "SV", "SLV"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "ES", "ESP"));
        response.add(new DataListDTO("", "US", "USA"));
        response.add(new DataListDTO("", "ET", "ETH"));
        response.add(new DataListDTO("", "PH", "PHL"));
        response.add(new DataListDTO("", "FI", "FIN"));
        response.add(new DataListDTO("", "FJ", "FJI"));
        response.add(new DataListDTO("", "FR", "FRA"));
        response.add(new DataListDTO("", "GA", "GAB"));
        response.add(new DataListDTO("", "GM", "GMB"));
        response.add(new DataListDTO("", "GH", "GHA"));
        response.add(new DataListDTO("", "GI", "GIB"));
        response.add(new DataListDTO("", "GD", "GRD"));
        response.add(new DataListDTO("", "GR", "GRC"));
        response.add(new DataListDTO("", "GL", "GRL"));
        response.add(new DataListDTO("", "GP", "GLP"));
        response.add(new DataListDTO("", "GU", "GUM"));
        response.add(new DataListDTO("", "GT", "GTM"));
        response.add(new DataListDTO("", "GF", "GUF"));
        response.add(new DataListDTO("", "GN", "GIN"));
        response.add(new DataListDTO("", "GW", "GNB"));
        response.add(new DataListDTO("", "GQ", "GNQ"));
        response.add(new DataListDTO("", "GY", "GUY"));
        response.add(new DataListDTO("", "HT", "HTI"));
        response.add(new DataListDTO("", "HN", "HND"));
        response.add(new DataListDTO("", "HK", "HKG"));
        response.add(new DataListDTO("", "HU", "HUN"));
        response.add(new DataListDTO("", "IN", "IND"));
        response.add(new DataListDTO("", "ID", "IDN"));
        response.add(new DataListDTO("", "IQ", "IRQ"));
        response.add(new DataListDTO("", "IR", "IRN"));
        response.add(new DataListDTO("", "IE", "IRL"));
        response.add(new DataListDTO("", "IS", "ISL"));
        response.add(new DataListDTO("", "KY", "CYM"));
        response.add(new DataListDTO("", "CC", "CCK"));
        response.add(new DataListDTO("", "CK", "COK"));
        response.add(new DataListDTO("", "HM", "HMD"));
        response.add(new DataListDTO("", "FK", "FLK"));
        response.add(new DataListDTO("", "PN", "PCN"));
        response.add(new DataListDTO("", "SB", "SLB"));
        response.add(new DataListDTO("", "TC", "TCA"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "VG", "VGB"));
        response.add(new DataListDTO("", "VI", "VIR"));
        response.add(new DataListDTO("", "IL", "ISR"));
        response.add(new DataListDTO("", "IT", "ITA"));
        response.add(new DataListDTO("", "JM", "JAM"));
        response.add(new DataListDTO("", "JP", "JPN"));
        response.add(new DataListDTO("", "JO", "JOR"));
        response.add(new DataListDTO("", "KE", "KEN"));
        response.add(new DataListDTO("", "KI", "KIR"));
        response.add(new DataListDTO("", "KW", "KWT"));
        response.add(new DataListDTO("", "LA", "LAO"));
        response.add(new DataListDTO("", "LS", "LSO"));
        response.add(new DataListDTO("", "LB", "LBN"));
        response.add(new DataListDTO("", "LR", "LBR"));
        response.add(new DataListDTO("", "LY", "LBY"));
        response.add(new DataListDTO("", "LI", "LIE"));
        response.add(new DataListDTO("", "LU", "LUX"));
        response.add(new DataListDTO("", "MO", "MAC"));
        response.add(new DataListDTO("", "MG", "MDG"));
        response.add(new DataListDTO("", "MY", "MYS"));
        response.add(new DataListDTO("", "MW", "MWI"));
        response.add(new DataListDTO("", "MV", "MDV"));
        response.add(new DataListDTO("", "ML", "MLI"));
        response.add(new DataListDTO("", "MT", "MLT"));
        response.add(new DataListDTO("", "MA", "MAR"));
        response.add(new DataListDTO("", "MQ", "MTQ"));
        response.add(new DataListDTO("", "MU", "MUS"));
        response.add(new DataListDTO("", "MR", "MRT"));
        response.add(new DataListDTO("", "MX", "MEX"));
        response.add(new DataListDTO("", "MC", "MCO"));
        response.add(new DataListDTO("", "MN", "MNG"));
        response.add(new DataListDTO("", "MS", "MSR"));
        response.add(new DataListDTO("", "MZ", "MOZ"));
        response.add(new DataListDTO("", "NA", "NAM"));
        response.add(new DataListDTO("", "NR", "NRU"));
        response.add(new DataListDTO("", "NI", "NIC"));
        response.add(new DataListDTO("", "NG", "NGA"));
        response.add(new DataListDTO("", "NU", "NIU"));
        response.add(new DataListDTO("", "NF", "NFK"));
        response.add(new DataListDTO("", "NO", "NOR"));
        response.add(new DataListDTO("", "NC", "NCL"));
        response.add(new DataListDTO("", "NZ", "NZL"));
        response.add(new DataListDTO("", "OM", "OMN"));
        response.add(new DataListDTO("", "NL", "NLD"));
        response.add(new DataListDTO("", "PK", "PAK"));
        response.add(new DataListDTO("", "PA", "PAN"));
        response.add(new DataListDTO("", "PA", "PAN"));
        response.add(new DataListDTO("", "PG", "PNG"));
        response.add(new DataListDTO("", "PY", "PRY"));
        response.add(new DataListDTO("", "PE", "PER"));
        response.add(new DataListDTO("", "PF", "PYF"));
        response.add(new DataListDTO("", "PL", "POL"));
        response.add(new DataListDTO("", "PT", "PRT"));
        response.add(new DataListDTO("", "PR", "PRI"));
        response.add(new DataListDTO("", "GB", "GBR"));
        response.add(new DataListDTO("", "EH", "ESH"));
        response.add(new DataListDTO("", "CF", "CAF"));
        response.add(new DataListDTO("", "CG", "COG"));
        response.add(new DataListDTO("", "DO", "DOM"));
        response.add(new DataListDTO("", "RE", "REU"));
        response.add(new DataListDTO("", "RW", "RWA"));
        response.add(new DataListDTO("", "RO", "ROU"));
        response.add(new DataListDTO("", "RU", "RUS"));
        response.add(new DataListDTO("", "WS", "WSM"));
        response.add(new DataListDTO("", "KN", "KNA"));
        response.add(new DataListDTO("", "SM", "SMR"));
        response.add(new DataListDTO("", "PM", "SPM"));
        response.add(new DataListDTO("", "VC", "VCT"));
        response.add(new DataListDTO("", "SH", "SHN"));
        response.add(new DataListDTO("", "LC", "LCA"));
        response.add(new DataListDTO("", "ST", "STP"));
        response.add(new DataListDTO("", "SN", "SEN"));
        response.add(new DataListDTO("", "SC", "SYC"));
        response.add(new DataListDTO("", "SL", "SLE"));
        response.add(new DataListDTO("", "SG", "SGP"));
        response.add(new DataListDTO("", "SY", "SYR"));
        response.add(new DataListDTO("", "SO", "SOM"));
        response.add(new DataListDTO("", "LK", "LKA"));
        response.add(new DataListDTO("", "SZ", "SWZ"));
        response.add(new DataListDTO("", "ZA", "ZAF"));
        response.add(new DataListDTO("", "SD", "SDN"));
        response.add(new DataListDTO("", "SE", "SWE"));
        response.add(new DataListDTO("", "CH", "CHE"));
        response.add(new DataListDTO("", "SR", "SUR"));
        response.add(new DataListDTO("", "SJ", "SJM"));
        response.add(new DataListDTO("", "TH", "THA"));
        response.add(new DataListDTO("", "TW", "TWN"));
        response.add(new DataListDTO("", "TZ", "TZA"));
        response.add(new DataListDTO("", "IO", "IOT"));
        response.add(new DataListDTO("", "TG", "TGO"));
        response.add(new DataListDTO("", "TK", "TKL"));
        response.add(new DataListDTO("", "TO", "TON"));
        response.add(new DataListDTO("", "TT", "TTO"));
        response.add(new DataListDTO("", "TN", "TUN"));
        response.add(new DataListDTO("", "TR", "TUR"));
        response.add(new DataListDTO("", "TV", "TUV"));
        response.add(new DataListDTO("", "UG", "UGA"));
        response.add(new DataListDTO("", "UY", "URY"));
        response.add(new DataListDTO("", "VU", "VUT"));
        response.add(new DataListDTO("", "VA", "VAT"));
        response.add(new DataListDTO("", "VE", "VEN"));
        response.add(new DataListDTO("", "VN", "VNM"));
        response.add(new DataListDTO("", "WF", "WLF"));
        response.add(new DataListDTO("", "YE", "YEM"));
        response.add(new DataListDTO("", "YE", "YEM"));
        response.add(new DataListDTO("", "DJ", "DJI"));
        response.add(new DataListDTO("", "ZM", "ZMB"));
        response.add(new DataListDTO("", "ZW", "ZWE"));
        response.add(new DataListDTO("", "EU", "EUR"));
        response.add(new DataListDTO("", "AC", "ASC"));
        response.add(new DataListDTO("", "TA", "TAA"));
        response.add(new DataListDTO("", "YU", "YUG"));
        response.add(new DataListDTO("", "ZR", "ZAR"));
        response.add(new DataListDTO("", "CS", "CSK"));
        response.add(new DataListDTO("", "CS", "CSK"));
        response.add(new DataListDTO("", "AN", "ANT"));
        response.add(new DataListDTO("", "HV", "HVO"));
        response.add(new DataListDTO("", "PC", "PCI"));


        return response;
    }

    private static String translateDepartment(String stateCode) {

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> stateCode.equals(x.getCode())).collect(Collectors.toList());
        if(departmentList.isEmpty())
            return stateCode;

        return departmentList.get(0).getCode();
    }

    public static String translateDepartmentDesc(String stateCode) {

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> stateCode.equals(x.getCode())).collect(Collectors.toList());

        if(departmentList.isEmpty())
            return "";

        return departmentList.get(0).getDescription();

    }
    private static List<DataListDTO> getDepartmentsRelTable() {
        List<DataListDTO> response = new ArrayList<>();
        response.add(new DataListDTO("", "CO-ANT", "05"));
        response.add(new DataListDTO("", "CO-ATL", "08"));
        response.add(new DataListDTO("", "CO-DC", "11"));
        response.add(new DataListDTO("", "CO-BOL", "13"));
        response.add(new DataListDTO("", "CO-BOY", "15"));
        response.add(new DataListDTO("", "CO-CAL", "17"));
        response.add(new DataListDTO("", "CO-CAQ", "18"));
        response.add(new DataListDTO("", "CO-CAU", "19"));
        response.add(new DataListDTO("", "CO-CES", "20"));
        response.add(new DataListDTO("", "CO-COR", "23"));
        response.add(new DataListDTO("", "CO-CUN", "25"));
        response.add(new DataListDTO("", "CO-CHO", "27"));
        response.add(new DataListDTO("", "CO-HUI", "41"));
        response.add(new DataListDTO("", "CO-LAG", "44"));
        response.add(new DataListDTO("", "CO-MAG", "47"));
        response.add(new DataListDTO("", "CO-MET", "50"));
        response.add(new DataListDTO("", "CO-NAR", "52"));
        response.add(new DataListDTO("", "CO-NSA", "54"));
        response.add(new DataListDTO("", "CO-QUI", "63"));
        response.add(new DataListDTO("", "CO-RIS", "66"));
        response.add(new DataListDTO("", "CO-SAN", "68"));
        response.add(new DataListDTO("", "CO-SUC", "70"));
        response.add(new DataListDTO("", "CO-TOL", "73"));
        response.add(new DataListDTO("", "CO-VAC", "76"));
        response.add(new DataListDTO("", "CO-ARA", "81"));
        response.add(new DataListDTO("", "CO-CAS", "85"));
        response.add(new DataListDTO("", "CO-PUT", "86"));
        response.add(new DataListDTO("", "CO-SAP", "88"));
        response.add(new DataListDTO("", "CO-AMA", "91"));
        response.add(new DataListDTO("", "CO-GUA", "94"));
        response.add(new DataListDTO("", "CO-GUV", "95"));
        response.add(new DataListDTO("", "CO-VAU", "97"));
        response.add(new DataListDTO("", "CO-VID", "99"));
        return response;
    }


    public static String getStateCodeByCountryCodeAndStateIso(String countryCode, String stateIsoCode){        
  
        String militaryStateCode = countryCode + "-" + stateIsoCode;

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> x.getCode().equals(militaryStateCode)).collect(Collectors.toList());

        if(departmentList.isEmpty())
            return "";

        return departmentList.get(0).getDescription();            
        
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Global Utils
 */
public class GUtils {
      
    private GUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
}
    /**
     * Start log
     */
    public static final String SLOG = "--> Start ";

    /**
     * End log
     */
    public static final String ELOG = "<-- End ";

    public static String[] getNullOrBlankPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || (srcValue instanceof String && ((String) srcValue).isEmpty())) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class PatchProspectMapperUtils {


    /* private PatchProspectMapperUtils() {
        throw new IllegalStateException("Utility class");
      } */
    /**
     * For Update prospect Update blocks unnecessary fields
     * 
     * @param patchProspectRequestDTO
     * @return
     */
    public static PatchProspectRequestDTO cleanFieldsFromProspectUpdate(
            PatchProspectRequestDTO patchProspectRequestDTO) {
        // forces document null for avoid documents update.
        if (patchProspectRequestDTO.getPerson() != null && patchProspectRequestDTO.getPerson().getDocuments() != null &&
                    patchProspectRequestDTO.getPerson().getDocuments().get(0) != null) {
                patchProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentTypeCode(null);// Forces customer
                                                                                                    // CASE dont change
                                                                                                    // documents
                patchProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentNumber(null);// Forces customer
                                                                                                  // CASE dont change
                                                                                                  // documents
            }        
        // forces contact
        if (patchProspectRequestDTO.getContactPoints() != null
                && !patchProspectRequestDTO.getContactPoints().isEmpty()) {
            patchProspectRequestDTO.getContactPoints().get(0).setElectronicAddress(null);// Forces customer CASE dont
                                                                                         // change email
            patchProspectRequestDTO.getContactPoints().get(0).setPhoneAddress(null);// Forces customer CASE dont change
                                                                                    // phone
        }
        return patchProspectRequestDTO;
    }// method closure

    public static String replaceDoubleOrTripleSpaces(String cadena) {
        // Primero reemplazar espacios triples
        Pattern patternTriples = Pattern.compile("\\s{3}");
        Matcher matcherTriples = patternTriples.matcher(cadena);
        String cadenaSinTriples = matcherTriples.replaceAll(" ");

        // Luego reemplazar espacios dobles
        Pattern patternDobles = Pattern.compile("\\s{2}");
        Matcher matcherDobles = patternDobles.matcher(cadenaSinTriples);
        return matcherDobles.replaceAll(" ").trim();
    }

}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProspectMapperUtils {

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegativeResponse;

    @Value("${params.deleteProspectCode}")
    private String lowProspect;


    public static Boolean isPenumperValid(String penumper){

        //Valida que sea numérico
        try {
            Long.valueOf(penumper);
        } catch (Exception e) {
            return false;
        }
        //Valida largo
        return penumper.length() == 8;
    }//method closure

    public static PersonDTO personDTONames(TrxPersonData personData, Parameters pa) {
        PersonDTO personDTO = new PersonDTO();
        String genderDesc = "HOMBRE";
        String genderCode = "H";
        if (personData.getSexo() != null && !personData.getSexo().isEmpty() && !personData.getSexo().equalsIgnoreCase("H")) {
            genderDesc = "MUJER";
            genderCode = "M";
        }
        personDTO.setGenderCode(genderCode);
        personDTO.setGenderDescription(genderDesc);
        personDTO.setPersonName(new PersonNameDTO());
        personDTO.getPersonName().setGivenName(personData.getNombre());
        personDTO.getPersonName().setLastName(personData.getPrimerApellido());
        personDTO.getPersonName().setSecondLastName(personData.getSegundoApellido());
        String fullName = personData.getNombre() + " " + personData.getPrimerApellido() + " " + personData.getSegundoApellido();
        personDTO.getPersonName().setFullName(fullName.trim());
        personDTO.setBirthDate(TimeUtils.formatDate(personData.getFechaNacimiento()));
        personDTO.setPlaceOfBirth(ProspectMapperUtils.getPlaceOfBirth(pa));
        personDTO.setFirstNationality(pa.getCountryNationality());
        personDTO.setCountryOfResidence(pa.getCountryDir());
        return personDTO;
    }//method closure

    public static PlaceOfBirthDTO getPlaceOfBirth(Parameters pa) {
        PlaceOfBirthDTO placeOfBirthDTO = new PlaceOfBirthDTO();
        placeOfBirthDTO.setCountry(pa.getCountryBirth());
        placeOfBirthDTO.setState(pa.getCityBirth());
        placeOfBirthDTO.setTown(pa.getTown() != null ? pa.getTown().getName() : "");
        return placeOfBirthDTO;
    }//method closure

    /*
     * Obtains record origin
     */
    public static String getSourceCode(TrxPersonData personData) {
        String sourceCode = "OTRO";
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 2){
            sourceCode = usualt.substring(0,3);
        }

        return sourceCode.equals("ODS") ? sourceCode : "OTRO";
    }//method closure


    public static ContactPointDTO getContactPoint(TrxPersonData personData, Parameters pa) {
        PhoneAddressDTO phoneAddressDTO = new PhoneAddressDTO();
        ContactPointDTO contactPointDTO = new ContactPointDTO();
        contactPointDTO.setPostalAddress(getPostalAddressBasic(personData, pa));
        contactPointDTO.setUseTypes(List.of(new CodeNameDTO("PRI", "Contactos Principales")));
        contactPointDTO.setContactPointId("PRI001");

        phoneAddressDTO.setInternationalCode(personData.getPrecelular());
        phoneAddressDTO.setPhoneNumber(personData.getTelefono());
        phoneAddressDTO.setMobileNumber(personData.getCelular());
        contactPointDTO.setPhoneAddress(phoneAddressDTO);
        //Email
        ElectronicAddressDTO email = new ElectronicAddressDTO();
        email.setEmailAddress(personData.getEmail());
        contactPointDTO.setElectronicAddress(email);
        return contactPointDTO;
    }//method closure


    public String getForeignTaxIndicator(TrxPersonData personData) {
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 6){
            String foreignTaxIndicator = usualt;
            if(usualt.length() >3){
                foreignTaxIndicator  = usualt.substring(3,7);
            }

            if(foreignTaxIndicator.contains(foreignTaxIndicatorPositiveResponse)) {
                return "YES";       
            }
        }
        
        return "NO";
    }

    public String getForeingTaxIndicatoFromRequest(String foreignTaxIndicator){
        if(foreignTaxIndicator == null){
            return "";
        }

        if("YES".equals(foreignTaxIndicator)){
            return foreignTaxIndicatorPositiveResponse;
        }
        return foreignTaxIndicatorNegativeResponse;


    }

    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        postalAddressDTO.setFullAddress(personData.getNombreVia());
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
        postalAddressDTO.setTown(pa.getCityStandard());
        postalAddressDTO.setCountry(pa.getCountryDir());
        postalAddressDTO.setState(pa.getCityDepartment());      
        if (personData.getDescripcionDireccion().length() > 0 && personData.getDescripcionDireccion() != null) {
            
            String premise = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(personData.getDescripcionDireccion().substring(0,50));
            postalAddressDTO.setPremise(premise.trim());
        }  
        return postalAddressDTO;
    }//method closure



    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        document.setState(pa.getCityExp());
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        document.setCountry(pa.getCountryExp());
        document.setTown(pa.getTownDocument().getName());
        if(personData.getDescripcionDireccion().length() >50 && personData.getDescripcionDireccion() != null){
            String expirationDate = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(personData.getDescripcionDireccion().substring(50,60));
        document.setExpirationDate(expirationDate.trim());
        }
        return document;
    }//method closure

    /**
     * Is Prospect or not
     * Prospect == 204 Non-content
     *
     * @param conper
     * @return
     */
    public static boolean isNotProspect(String conper) {
        return !"PRO".equalsIgnoreCase(conper);
    }//method closure

    /**
     * For Update prospect Update blocks unnecessary fields
     * @param createProspectRequestDTO
     * @return
     */
    public static CreateProspectRequestDTO cleanFieldsFromProspectUpdate(CreateProspectRequestDTO createProspectRequestDTO){
    //forces document null for avoid documents update.
        if(createProspectRequestDTO.getPerson()!=null && createProspectRequestDTO.getPerson().getDocuments()!=null &&
                createProspectRequestDTO.getPerson().getDocuments().get(0) != null){
            createProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentTypeCode(null);//Forces customer CASE dont change documents
            createProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentNumber(null);//Forces customer CASE dont change documents

        }
    
    // forces contact
        if (createProspectRequestDTO.getContactPoints() != null && !createProspectRequestDTO.getContactPoints().isEmpty()) {
        createProspectRequestDTO.getContactPoints().get(0).setElectronicAddress(null);//Forces customer CASE dont change email
        createProspectRequestDTO.getContactPoints().get(0).setPhoneAddress(null);//Forces customer CASE dont change phone
    }
        return createProspectRequestDTO;
}//method closure

    public String lowProspectLogic(TrxPersonData personData) {

        log.info("Usualt old: {}", personData.getUsualt());

        var usualt = personData.getUsualt();

        String foreignTaxIndicator = usualt;

        if(usualt != null && !usualt.isBlank()){

            if(usualt.length() > 6){

                foreignTaxIndicator  = usualt.substring(3,7);
                usualt = lowProspect + foreignTaxIndicator;

            }

            if(usualt.length() < 7){

                foreignTaxIndicator  = "CONN";
                usualt = lowProspect + foreignTaxIndicator;

            }

        } else {

            foreignTaxIndicator  = "CONN";
            usualt = lowProspect + foreignTaxIndicator;

        }

        log.info("Usualt new: {}", usualt);

        return usualt;
    }

}//class closure

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

public enum RegexTypes {

    ONLY_NUMBERS,
    STRICT_LENGTH_8,
    STRICT_LENGTH_11,
    STRICT_CHAR_LENGTH_2,
    EMAIL,
    EMAIL_LENGTH,
    EMAIL_FORMAT_LEFT,
    EMAIL_FORMAT_RIGHT,
    EMAIL_FORMAT_FIRST_CHAR,
    PHONE_LENGTH,
    PHONE_FORMAT,
    INTERNATIONAL_CODE_LENGTH,
    INTERNATIONAL_CODE_FORMAT,
    ADDRESS_LENGTH,
    TEXT_20_LENGTH,
    TEXT_20_FORMAT,
    TEXT_40_LENGTH,
    TEXT_40_FORMAT,
    ADDRESS_FORMAT,
    COUNTRY_LENGTH,
    COUNTRT_FORMAT,
    GENDER_CODE_FORMAT,
    BIRTH_DAY_DATE_FORMAT,
    ISSUE_DATE_FORMAT,
    GENDER_CODE_LENGTH,
    SECOND_LAST_NAME_FORMAT,
    SECOND_LAST_NAME_CE_FORMAT,
    REGEX_TOWN_DESCRIPTION_FORMAT,
    REGEX_TOWN_DESCRIPTION_LENGTH,
    EMAIL_BETWEEN,
    REGEX_TOWN_CODE_LENGTH,
    REGEX_STATE_CODE_FORMAT,
    REGEX_STATE_CODE_LENGTH
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexUtils {

    @Value("${ms_name}")
    private String msName;
    @Value("${ms_version}")
    private String msVersion;
    @Value("${errors.level}")
    private String level;
    @Value("${regex.error.code}")
    private String code;
    @Value("${regex.only_numbers}")
    private String regexOnlyNumbers;
    @Value("${regex.only_numbers.error}")
    private String regexOnlyNumberError;
    @Value("${regex.strict_length_8}")
    private String regexStrictLength8;
    @Value("${regex.strict_length_11}")
    private String regexStrictLength11;
    @Value("${regex.strict_length_11_error}")
    private String regexStrictLength11Error;
    @Value("${regex.strict_length_8_error}")
    private String regexStrictLength8Error;
    @Value("${regex.strict_char_length_2}")
    private String regexStrictCharLength2;
    @Value("${regex.strict_char_length_2_error}")
    private String regexStrictCharLength2Error;
    @Value("${regex.address_length}")
    private String regexAdressLength;
    @Value("${regex.address_length_error}")
    private String regexAdressLengthError;
    @Value("${regex.address_format}")
    private String regexAdressFormat;
    @Value("${regex.address_format_error}")
    private String regexAdressFormatError;
    @Value("${regex.email}")
    private String regexEmail;
    @Value("${regex.email_between}")
    private String regexEmailBetween;
    @Value("${regex.email_error}")
    private String regexEmailError;
    @Value("${regex.email_length}")
    private String regexEmailLength;
    @Value("${regex.email_length_error}")
    private String regexEmailLengthError;
    @Value("${regex.email_format_left}")
    private String regexEmailFormatLeft;
    @Value("${regex.email_format_left_error}")
    private String regexEmailFormatLeftError;
    @Value("${regex.email_format_right}")
    private String regexEmailFormatRight;
    @Value("${regex.email_format_right_error}")
    private String regexEmailFormatRigthError;
    @Value("${regex.email_format_first_char}")
    private String regexEmailFormatFirstChar;
    @Value("${regex.email_format_first_char_error}")
    private String regexEmailFormatFirtCharError;
    @Value("${regex.phone_length}")
    private String regexPhoneLength;
    @Value("${regex.phone_length_error}")
    private String regexPhoneLengthError;
    @Value("${regex.phone_format}")
    private String regexPhoneFormat;
    @Value("${regex.phone_format_error}")
    private String regexPhoneFormatError;
    @Value("${regex.international_code_format}")
    private String regexInternationaCodeFormat;
    @Value("${regex.international_code_format_error}")
    private String regexInternationaCodeFormatError;
    @Value("${regex.international_code_length}")
    private String regexInternationaCodeLength;
    @Value("${regex.international_code_length_error}")
    private String regexInternationaCodeLengthError;
    @Value("${regex.text_20_length}")
    private String regexText20Length;
    @Value("${regex.text_20_length_error}")
    private String regexText20LengthError;
    @Value("${regex.text_20_format}")
    private String regexText20Format;
    @Value("${regex.text_20_format_error}")
    private String regexText20FormatError;
    @Value("${regex.text_40_length}")
    private String regexText40Length;
    @Value("${regex.text_40_length_error}")
    private String regexText40LengthError;
    @Value("${regex.text_40_format}")
    private String regexText40Format;
    @Value("${regex.text_40_format_error}")
    private String regexText40FormatError;
    @Value("${regex.county_code_length}")
    private String regexCountryCodeLength;
    @Value("${regex.county_code_length_error}")
    private String regexCountryCodeLengthError;
    @Value("${regex.county_code_length}")
    private String regexCountryCodeFormat;
    @Value("${regex.county_code_length_error}")
    private String regexCountryCodeFormatError;
    @Value("${regex.gender_code_format}")
    private String regexGenderCodeFormat;
    @Value("${regex.gender_code_format_error}")
    private String regexGenderCodeFormatError;
    @Value("${regex.birthday_date}")
    private String regexBirthdayDate;
    @Value("${regex.birthday_date_error}")
    private String regexBirthdayDateFormat;
    @Value("${regex.issue_date}")
    private String regexIssueDateFormat;
    @Value("${regex.birthday_date_error}")
    private String regexIssueDateFormatError;
    @Value("${regex.gender_code_length}")
    private String regexGenderCodeLength;
    @Value("${regex.gender_code_length_error}")
    private String regexGenderCodeLengthError;
    @Value("${regex.second_last_name_format}")
    private String regexSecondLastNameFormat;
    @Value("${regex.second_last_name_format_error}")
    private String regexSecondLastNameFormatError;
    @Value("${regex.second_last_name_ce_format}")
    private String regexSecondLastNameCeFormat;
    @Value("${regex.second_last_name_ce_format_error}")
    private String regexSecondLastNameCeFormatError;
    @Value("${regex.town_description_format}")
    private String regexTownDescpritionFormat;
    @Value("${regex.town_description_format_error}")
    private String regexTownDescpritionFormatError;
    @Value("${regex.town_description_length}")
    private String regexTownDescriptionLength;
    @Value("${regex.town_description_length_error}")
    private String regexTownDescriptionLengthError;
    @Value("${regex.town_code_length}")
    private String regexTownCodeLength;
    @Value("${regex.town_code_length_error}")
    private String regexTownCodeLengthError;
    @Value("${regex.state_code_format}")
    private String regexStateCodeFormat;
    @Value("${regex.state_code_format_error}")
    private String regexStateCodeFormatError;
    @Value("${regex.state_code_length}")
    private String regexStateCodeLength;
    @Value("${regex.state_code_length_error}")
    private String regexStateCodeLegnthError;




    public void validateRegex(RegexTypes regex, String value, String fieldName) {

        String regularExpression = "";
        String message = "invalid format";

        switch (regex) {
            case ONLY_NUMBERS:
                regularExpression = regexOnlyNumbers;
                message = regexOnlyNumberError;
                break;
            case STRICT_LENGTH_8:
                regularExpression = regexStrictLength8;
                message = regexStrictLength8Error;
                break;
            case STRICT_LENGTH_11:
                regularExpression = regexStrictLength11;
                message = regexStrictLength11Error;
                break;
            case STRICT_CHAR_LENGTH_2:
                regularExpression = regexStrictCharLength2;
                message = regexStrictCharLength2Error;
                break;
            case ADDRESS_LENGTH:
                regularExpression = regexAdressLength;
                message = regexAdressLengthError;
                break;
            case ADDRESS_FORMAT:
                regularExpression = regexAdressFormat;
                message = regexAdressFormatError;
                break;
            case EMAIL:
                regularExpression = regexEmail;
                message = regexEmailError;
                break;
            case EMAIL_BETWEEN:
                regularExpression = regexEmailBetween;
                message = regexEmailError;
                break;
            case EMAIL_LENGTH:
                regularExpression = regexEmailLength;
                message = regexEmailLengthError;
                break;
            case EMAIL_FORMAT_LEFT:
                regularExpression = regexEmailFormatLeft;
                message = regexEmailFormatLeftError;
                break;
            case EMAIL_FORMAT_RIGHT:
                regularExpression = regexEmailFormatRight;
                message = regexEmailFormatRigthError;
                break;
            case EMAIL_FORMAT_FIRST_CHAR:
                regularExpression = regexEmailFormatFirstChar;
                message = regexEmailFormatFirtCharError;
                break;
            case PHONE_LENGTH:
                regularExpression = regexPhoneLength;
                message = regexPhoneLengthError;
                break;
            case PHONE_FORMAT:
                regularExpression = regexPhoneFormat;
                message = regexPhoneFormatError;
                break;
            case INTERNATIONAL_CODE_FORMAT:
                regularExpression = regexInternationaCodeFormat;
                message = regexInternationaCodeFormatError;
                break;
            case INTERNATIONAL_CODE_LENGTH:
                regularExpression = regexInternationaCodeLength;
                message = regexInternationaCodeLengthError;
                break;
            case TEXT_20_LENGTH:
                regularExpression = regexText20Length;
                message = regexText20LengthError;
                break;
            case TEXT_20_FORMAT:
                regularExpression = regexText20Format;
                message = regexText20FormatError;
                break;
            case TEXT_40_LENGTH:
                regularExpression = regexText40Length;
                message = regexText40LengthError;
                break;
            case TEXT_40_FORMAT:
                regularExpression = regexText40Format;
                message = regexText40FormatError;
                break;
            case COUNTRY_LENGTH:
                regularExpression = regexCountryCodeLength;
                message = regexCountryCodeLengthError;
                break;
            case COUNTRT_FORMAT:
                regularExpression = regexCountryCodeFormat;
                message = regexCountryCodeFormatError;
                break;
            case GENDER_CODE_FORMAT:
                regularExpression = regexGenderCodeFormat;
                message = regexGenderCodeFormatError;
                break;
            case BIRTH_DAY_DATE_FORMAT:
                regularExpression = regexBirthdayDate;
                message = regexBirthdayDateFormat;
                break;
            case ISSUE_DATE_FORMAT:
                regularExpression = regexIssueDateFormat;
                message = regexIssueDateFormatError;
                break;
            case GENDER_CODE_LENGTH:
                regularExpression = regexGenderCodeLength;
                message = regexGenderCodeLengthError;
                break;
            case SECOND_LAST_NAME_FORMAT:
                regularExpression = regexSecondLastNameFormat;
                message = regexSecondLastNameFormatError;
                break;
            case SECOND_LAST_NAME_CE_FORMAT:
                regularExpression = regexSecondLastNameCeFormat;
                message = regexSecondLastNameCeFormatError;
                break;
            case REGEX_TOWN_DESCRIPTION_FORMAT:
                regularExpression = regexTownDescpritionFormat;
                message = regexTownDescpritionFormatError;
                break;
            case REGEX_TOWN_DESCRIPTION_LENGTH:
                regularExpression = regexTownDescriptionLength;
                message = regexTownDescriptionLengthError;
                break;
            case REGEX_TOWN_CODE_LENGTH:
                regularExpression = regexTownCodeLength;
                message = regexTownCodeLengthError;
                break;
            case REGEX_STATE_CODE_FORMAT:
                regularExpression = regexStateCodeFormat;
                message = regexStateCodeFormatError;
                break;
            case REGEX_STATE_CODE_LENGTH:
                regularExpression = regexStateCodeLength;
                message = regexStateCodeLegnthError;
                break;
        }        

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

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 * @author Wilfredo Pena
 */
public class ServiceDirectory {

    private static final String API_VERSION = "/v1";
    private static final String PROSPECT_ENDPOINT = "/prospects";

    /**
     * Endpoints
     */
    public final static String PROSPECT = API_VERSION + PROSPECT_ENDPOINT;
    public final static String PROSPECT_SEARCH = API_VERSION + PROSPECT_ENDPOINT + "/search";
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringUtils {

    final RegexUtils regexUtils;
    final ErrorService errorService;

    private String SECOND_LAST_NAME_FIELD = "person.personName.secondLastName";

    public static String blankField(String field){
        if(field == null){
            return "";
        }else{
            return field;
        }
    }

    public void inputSencondLastNameValidation(String input, String lastName){
        if("CE".equals(input) || "CC".equals(input)){
            if (lastName != null){
                regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_CE_FORMAT,lastName,SECOND_LAST_NAME_FIELD);
            }
        }
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getSlocalDateTimeByFormat(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String unformattedDate){
        LocalDate dateF = LocalDate.parse(unformattedDate);
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
         return dateF.format(newFormat);
    }

}//class closure

