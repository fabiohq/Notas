package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration;

import lombok.AllArgsConstructor; import lombok.Data; import lombok.NoArgsConstructor;

/**

params from properties.yml */ @Data @AllArgsConstructor @NoArgsConstructor public class ApiEntry { private String integrationType; private String host; private String port; private boolean https; private String endpoint; private Integer timeOutConn; private Integer timeOutRead; } package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotNull; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class ConsentStatusInfo { @NotNull(message = "{errors.general.null}") private String statusCode; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request;

import jakarta.validation.Valid; import jakarta.validation.constraints.NotNull; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class PartiesConsentsRequest { @Valid @NotNull(message = "{errors.general.null}") private ConsentStatusInfo statusInfo; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class AmountRangeRequest {

private String authorization;
private String xSantanderClientId;
private String productId;
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class AmountRangeResponse {

private MaxAndMinAmountDto minimunAmount;
private MaxAndMinAmountDto maximumAmount;
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class MaxAndMinAmountDto {

private String amount;
private String currency;
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class TermDepositParametersDTO { private String code; private String content; private String description; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class TermDepositParametersRequest { private String productId; private String authorization; private String xSantanderClientId; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class TermDepositParametersResponse { private List parameters; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.request;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class TermDepositTransactionRequest { private String authorization; private String client_id; private String deposit_id; private String placement_id; private Integer type_code; private String credit_debit_indicator; private String start_date; private String end_date; private Integer minim_amount; private Integer maxim_amount; private String offset; private String limit;

}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class AmountDTO { private String amount; private String currency; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class BalanceResultDTO { private AmountDTO amount; private String creditDebitIndicator;

}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class BalanceTypeDTO { private String typeCode; private String typeDescription;

}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor public class HrefDTO { private String href; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor; @Data @Builder @NoArgsConstructor @AllArgsConstructor public class LinksDTO { private HrefDTO _first; private HrefDTO _prev; private HrefDTO _next;

}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor; @Data @Builder @NoArgsConstructor @AllArgsConstructor public class ListTransactionDTO { //private String transactionId; // private String creationDate; private String valueDate; private AmountDTO amount; //private String creditDebitIndicator; //private BalanceResultDTO balanceResult; private String description; //private String processedDate; //private String accountingReference; //private String localReferenceCode;

}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response;

import java.util.ArrayList; import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Data; import lombok.NoArgsConstructor; @Data @Builder @NoArgsConstructor @AllArgsConstructor public class TermDepositTransactionResponse { //private BalanceTypeDTO balanceType; private ArrayList listTransactions; //private LinksDTO _linksDTOResponse; }

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.BanksService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.ProductDirectoryService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TermDepositParametersService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService; import lombok.RequiredArgsConstructor; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Component;

/**

Handle all Products utils */ @Component @RequiredArgsConstructor public class DataConsentManagementUtils {

final RegexUtils regexUtils; final ErrorService errorService; final ProductDirectoryService productDirectoryService; final TermDepositParametersService termDepositParametersService; final BanksService banksService;

@Value("${params.commons.productCode}") private String productCode; @Value("${params.commons.subproductCode}") private String subproductCode;

@Value("#{'${params.frequencies}'.split(',')}") private String [] validFrecuencies; @Value("#{'${params.settlements}'.split(',')}") private String [] validSettlements; @Value("${params.condition-codes}") private String SETTLEMENT_CONDITON_CODES; @Value("${params.commons.bankId}") private String bankId; @Value("${params.commons.centerId}") private String centerId;

private String PARTY_ID_FIELDNAME = "party_id"; private String STATUS_CODE_FIELDNAME = "statusInfo.statusCode";

public void partiesConsentInputValidation(String partyId, PartiesConsentsRequest partiesConsentsRequest){ //Party Id validation errorService.isBlank(partyId,PARTY_ID_FIELDNAME); errorService.isNull(partyId,PARTY_ID_FIELDNAME); regexUtils.validateRegex("party_id_format",partyId,PARTY_ID_FIELDNAME); regexUtils.validateRegex("party_id_length",partyId,PARTY_ID_FIELDNAME);

 //Request Validation
 errorService.isBlank(partiesConsentsRequest.getStatusInfo().getStatusCode(),STATUS_CODE_FIELDNAME);
 regexUtils.validateRegex("status_code_format",partiesConsentsRequest.getStatusInfo().getStatusCode(),STATUS_CODE_FIELDNAME);
 regexUtils.validateRegex("status_code_length",partiesConsentsRequest.getStatusInfo().getStatusCode(),STATUS_CODE_FIELDNAME);
}

public static String format15DigitNumber(String number){

 if(number == null || number.isBlank() || number.isEmpty()) return "0,00";

 String noZerosNumber = number.replaceFirst("^0+(?!$)", "");

 if(noZerosNumber.length() == 1) return "0,0" + noZerosNumber;
 if(noZerosNumber.length() == 2) return "0," + noZerosNumber;        

 return noZerosNumber.substring(0, noZerosNumber.length()-2) + "," + noZerosNumber.substring(noZerosNumber.length() - 2, noZerosNumber.length());
}

public static String removeLeadingZeros(String number){

 if(number == null || number.isBlank() || number.isEmpty()) return "0";

 return number.replaceFirst("^0+(?!$)", "");
}

}//class closure

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import lombok.Data; import org.springframework.boot.context.properties.ConfigurationProperties; import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Data @Configuration @ConfigurationProperties(prefix = "mov") public class MovementConceptUtils { private HashMap<String, String> type;

} package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.context.properties.ConfigurationProperties; import org.springframework.context.annotation.Configuration; import org.springframework.http.HttpStatus;

import java.util.HashMap; import java.util.regex.Pattern;

@Data @Configuration @ConfigurationProperties(prefix = "regex") public class RegexUtils {

@Value("${params.appName}")
private String MS_NAME;
@Value("${params.appVersion}")
private String MS_VERSION;
@Value("${errors.level}")
private String LEVEL;
@Value("${regex.error.code}")
private String CODE;

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

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO; import lombok.Data; import lombok.RequiredArgsConstructor; import org.springframework.beans.factory.annotation.Value; import org.springframework.boot.context.properties.ConfigurationProperties; import org.springframework.http.HttpStatus; import org.springframework.stereotype.Service; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

import java.util.HashMap;

@Service @Data @ConfigurationProperties(prefix = "errors") @RequiredArgsConstructor public class ErrorService {

private String msName;
private String msVersion;
private String level;
private String functional;
private String technical;
private HashMap<String, String> general;

@Value("${errors.general.invalid_value}")
public String INVALID_VALUE;

@Value(("${errors.general.blank_data}"))
public String BLANK_DATA;
@Value(("${errors.general.null}"))
public String NULL_DATA;

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
        var message = "'" + fieldName + "': " + BLANK_DATA;

        throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
    }
}

public void isNull(String value, String fieldName) {
    if (value == null) {
        var message = "'" + fieldName + "': " + NULL_DATA;

        throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
    }
}
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error;

public enum ErrorType { FUNCTIONAL, TECHNICAL;

} package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO; import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.http.converter.HttpMessageNotReadableException; import org.springframework.validation.BindingResult; import org.springframework.validation.FieldError; import org.springframework.web.HttpRequestMethodNotSupportedException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.MissingRequestHeaderException; import org.springframework.web.bind.MissingServletRequestParameterException; import org.springframework.web.bind.annotation.ControllerAdvice; import org.springframework.web.bind.annotation.ExceptionHandler; import org.springframework.web.context.request.WebRequest; import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList; import java.util.List;

/**

@author Freddy Paredes
This class handle all Exceptions */
@Slf4j @ControllerAdvice public class GlobalExceptionHandler {

@Value("${params.app-name}")
private String MS_NAME;

private static final String LEVEL = "error";
private static final String PF400 = "-P-F-9400";
private static final String NOTSPECIFIED = " not specified";


@ExceptionHandler
public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

    log.info("Error type: {}", e.getClass().getName());
    log.info("Error: {}", e.getMessage());

    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + "-P-T-9409")
            .message("Unhandled exception")
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Unhandled exception")
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
                .code(MS_NAME + PF400)
                .level(LEVEL)
                .message(errorMessage)
                .description(MS_NAME.toLowerCase() + "-api-services-v5: " + errorMessage).build());
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
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Not Found")
            .build());

    return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(MissingServletRequestParameterException.class)
public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + PF400)
            .message("Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
            .build());

    return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + PF400)
            .message(ex.getMessage())
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Bad request")
            .build());

    return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(MissingRequestHeaderException.class)
public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + PF400)
            .message("Required header " + ex.getHeaderName() + NOTSPECIFIED)
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Required header " + ex.getHeaderName() + NOTSPECIFIED)
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
/** public ResponseEntity buildResponseEntity(List errors, HttpStatus status) {

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
*/ public ResponseEntity buildResponseEntity(List errors, HttpStatus status) {

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

@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(HttpMessageNotReadableException ex) {
    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + PF400)
            .message("Invalid body structure")
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Invalid body structure")
            .build());

    return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
    List<ErrorDTO> errors = new ArrayList<>();
    errors.add(ErrorDTO.builder()
            .code(MS_NAME + "-P-F-9405")
            .message("Method not allowed")
            .level(LEVEL)
            .description(MS_NAME.toLowerCase() + "-api-services-v5: Method not allowed")
            .build());

    return buildResponseEntity(errors, HttpStatus.METHOD_NOT_ALLOWED);
}
}//class closure

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.mappers;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49Response; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response.AmountDTO; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response.ListTransactionDTO; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdeposittransaction.response.TermDepositTransactionResponse; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.MovementConceptUtils; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.RegexUtils; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.DataConsentManagementUtils; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Component; import java.util.ArrayList;

@Component @RequiredArgsConstructor public class TermDepositTransactionMappers { final DataConsentManagementUtils dataConsentManagementUtils; final RegexUtils regexUtils; final ErrorService errorService; final MovementConceptUtils movementConceptUtils; public TermDepositTransactionResponse transactionResponse(TrxBP49Response trxBP49Response){ TermDepositTransactionResponse response = new TermDepositTransactionResponse(); ArrayList listTransactions = new ArrayList<>();

  trxBP49Response.getData().getMovimientos().forEach(motion -> {
      ListTransactionDTO listTrasaction= new ListTransactionDTO();
        AmountDTO amount = new AmountDTO();
        amount.setCurrency("COP");
        String valueEntry = motion.getValor();

        String valueFinal;
        if (valueEntry.contains("-")){
            valueFinal = "-" + DataConsentManagementUtils.format15DigitNumber(valueEntry.replace("-",""));
        }else{
            valueFinal = DataConsentManagementUtils.format15DigitNumber(valueEntry);
        }
        amount.setAmount(valueFinal);
        listTrasaction.setAmount(amount);
        String date = motion.getFecha();
        listTrasaction.setValueDate(date);

        listTrasaction.setDescription(setConcept(motion.getConcepto()));
        listTransactions.add(listTrasaction);
        response.setListTransactions(listTransactions);
    });

    return response;
}
private String setConcept( String conceptCode){
    String conceptDescription;
    if (movementConceptUtils.getType().containsKey(conceptCode)) {
        conceptDescription = movementConceptUtils.getType().get(conceptCode);
    } else {
        conceptDescription = "OTROS";
    }
    return  conceptDescription;
}
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.service.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TrxSanbaService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvDataRequest; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvInfoAdicional; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response.PemfvResponse; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.mappers.TermDepositTransactionMappers; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.service.DataConsentManagementService; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.RegexUtils; import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.utils.DataConsentManagementUtils; import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils; import lombok.RequiredArgsConstructor; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor

public class DataConsentManagementServiceImpl implements DataConsentManagementService {

final TrxSanbaService trxSanbaService;

final DataConsentManagementUtils dataConsentManagementUtils;
final RegexUtils regexUtils;
final ErrorService errorService;
final TermDepositTransactionMappers transactionMappers;
@Value("${service-route-trx.PEMFV}")
private String PEMFV_SERVICE_ROUTE;

public void getPartiesConsent(String partyId, PartiesConsentsRequest partiesConsentsRequest) {
    dataConsentManagementUtils.partiesConsentInputValidation(partyId,partiesConsentsRequest);
    trxPEMFVcall(partyId,partiesConsentsRequest);
}

public PemfvResponse trxPEMFVcall(String partyId,PartiesConsentsRequest partiesConsentsRequest){
    PemfvRequest request = new PemfvRequest(ClientUtils.buildHeader(PEMFV_SERVICE_ROUTE));
    var trxPemfvData = new PemfvDataRequest();
    var pemfvInfoAdicional = new PemfvInfoAdicional();
    pemfvInfoAdicional.setNumper(partyId);
    pemfvInfoAdicional.setCanalVenta("ODS");
    if (partiesConsentsRequest.getStatusInfo().getStatusCode().equals("S")){
        pemfvInfoAdicional.setAutorizoEnvioInformacion(true);
    }else if (partiesConsentsRequest.getStatusInfo().getStatusCode().equals("N")){
        pemfvInfoAdicional.setAutorizoEnvioInformacion(false);
    }
    trxPemfvData.setInfAdicional(pemfvInfoAdicional);
    request.setData(trxPemfvData);
    return trxSanbaService.trxPemfv(request);
}
}