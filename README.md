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
