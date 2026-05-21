Test unitarios en un solo canvas 
Los test desacopla dos por clase y cubriendo el 100% de cobertura

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils;

public enum RegexTypes {
    ONLY_NUMBERS,
    PRODUCT_LENGTH,
    COMMERCIAL_OFFER_CODE_NUMBER_LENGTH,
    COMMERCIAL_OFFER_PRODUCT_CODE_LENGTH,
    COMMERCIAL_OFFER_CHANNEL_CODE_FORMAT,
    COMMERCIAL_OFFER_CHANNEL_CODE_LENGTH
}

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
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

    @Value("${regex.product_format}")
    private String regexProductFormat;
    @Value("${regex.product_format_error}")
    private String regexProductFormatError;

    @Value("${regex.product_length}")
    private String regexProductLength;

    @Value("${regex.product_length_error}")
    private String regexProductLengthError;

    @Value("${regex.commercialOffer_length}")
    private String regexCommercialOfferCodeNumerLength;
    @Value("${regex.commercialOffer_length_error}")
    private String regexCommercialOfferCodeNumerLengthError;

    @Value("${regex.commercialOffer_produc_code_length}")
    private String regexCommercialOfferProductCodeLength;
    @Value("${regex.commercialOffer_produc_code_length_error}")
    private String regexCommercialOfferProductCodeLengthError;

    @Value("${regex.commercialOffer_channel_code_format}")
    private String regexCommercialOfferChannelCodeFormat;
    @Value("${regex.commercialOffer_channel_code_format_error}")
    private String regexCommercialOfferChannelCodeFormatError;

    @Value("${regex.commercialOffer_channel_code_length}")
    private String regexCommercialOfferChannelCodeLength;
    @Value("${regex.commercialOffer_channel_code_length_error}")
    private String regexCommercialOfferChannelCodeLengthError;

    public void validateRegex(RegexTypes regex, String value, String fieldName) {

        String regularExpression = "";
        String message = "invalid format";

        switch (regex) {
            case ONLY_NUMBERS:
                regularExpression = regexProductFormat;
                message = regexProductFormatError;
                break;
            case PRODUCT_LENGTH:
                regularExpression = regexProductLength;
                message = regexProductLengthError;
                break;
            case COMMERCIAL_OFFER_CODE_NUMBER_LENGTH:
                regularExpression = regexCommercialOfferCodeNumerLength;
                message = regexCommercialOfferCodeNumerLengthError;
                break;
            case COMMERCIAL_OFFER_PRODUCT_CODE_LENGTH:
                regularExpression = regexCommercialOfferProductCodeLength;
                message = regexCommercialOfferProductCodeLengthError;
                break;
            case COMMERCIAL_OFFER_CHANNEL_CODE_FORMAT:
                regularExpression = regexCommercialOfferChannelCodeFormat;
                message = regexCommercialOfferChannelCodeFormatError;
                break;
            case COMMERCIAL_OFFER_CHANNEL_CODE_LENGTH:
                regularExpression = regexCommercialOfferChannelCodeLength;
                message = regexCommercialOfferChannelCodeLengthError;
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

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 *
 */
public class ServiceDirectory {

    private ServiceDirectory() {
        throw new IllegalStateException("Utility class");
      }
    private static final String API_VERSION = "/v3";
    private static final String PRODUCT_ENDPOINT = "/product";

    /**
     * Endpoints
     */
    public static final String PRODUCT = API_VERSION + PRODUCT_ENDPOINT;
}


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.CommerccialOfferDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response.AmountResponseDto;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermsDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PARAMETER CONTROLLER
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/products")
public class ProductControllers {

  @Autowired
  ProductsService productsService;
  
  private final ObjectMapper objectMapper;

  @GetMapping("/commercial_offer")
  public CommerccialOfferDTO getCommercialOffer(
      @RequestParam(required = true, name = "bank_id") String bankId,
      @RequestParam(required = true, name = "center_id") String centerId,
      @RequestParam(required = true, name = "product_code") String productCode,
      @RequestParam(required = true, name = "channel_code") String channelCode,
      @RequestHeader(required = true, name = "Authorization") String authorization,
      @RequestHeader(required = true, name = "x-santander-client-id") String clientId) {
  		  
		log.info("** INIT (GET) /v3/products/commercial_offer?bank_id={}&center_id={}&product_code={}&channel_code={}, clientId={} >>>",
				bankId, centerId, productCode, channelCode, clientId);
		log.info(GUtils.SLOG + "Controller getCommercialOffer");
		CommerccialOfferDTO response = productsService.getCommercialOffer(bankId, centerId, productCode, channelCode);
		try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(clientId);
			sb.append(", Response=").append(jsonResponse);

			log.info(
					"** FIN (GET) /v3/products/commercial_offer?bank_id={}&center_id={}&product_code={}&channel_code={}, {} >>> ",
					bankId, centerId, productCode, channelCode, sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
		
		return response; 
  }// method closure

  // class closure
  /**
   * Terms
   * 
   * @param product_id
   * @return
   */
  @GetMapping("/{product_id}/terms")
  public ResponseEntity<TermsDTO> getTProductTerms(@PathVariable(required = true, name = "product_id") String productId,
      @RequestHeader(required = true, name = "Authorization") String authorization,
      @RequestHeader(required = true, name = "x-santander-client-id") String clientId) {
	  
		log.info("** INIT (GET) /v3/products/{}/terms, clientId={} >>>", productId, clientId);
		log.info("endpoint get terms by product_id={}", productId);
		TermsDTO response = productsService.getProductTerms(productId);

		try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(clientId);
			sb.append(", Response=").append(jsonResponse);

			log.info("** FIN (GET) /v3/products/{}/terms, {} >>> ",productId, sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
		if (response == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.PERSON_IS_NOT_CLIENT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
  }// method closure



  @GetMapping("/{productId}/amount_range")
  public ResponseEntity<AmountResponseDto> amountRange(
          @PathVariable(name = "productId") String productId,
          @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
          @RequestHeader(required = true, name = "x-santander-client-id") String clientId) {

	  log.info("** INIT (GET) /v3/products/{}/amount_range, clientId={} >>>", productId, clientId);
      log.info(GUtils.SLOG + "endpoint get product by productId={}", productId);
      AmountResponseDto response = productsService.getProductAmountRange(productId);

      try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(clientId);
			sb.append(", Response=").append(jsonResponse);

			log.info("** FIN (GET) /v3/products/{}/amount_range, {} >>> ",productId, sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
      
      if (response == null) {
          throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PROSPECT_NOT_FOUND);
      }
      log.info(GUtils.ELOG + "endpoint get product");
      return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
}// class closure


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountResponseDto {

    private MaxAndMinAmountDto minimumAmount;
    private MaxAndMinAmountDto maximumAmount;
    
    public MaxAndMinAmountDto getMinimumAmount() {
        return minimumAmount;
    }
    public void setMinimumAmount(MaxAndMinAmountDto minimumAmount) {
        this.minimumAmount = minimumAmount;
    }
    public MaxAndMinAmountDto getMaximumAmount() {
        return maximumAmount;
    }
    public void setMaximumAmount(MaxAndMinAmountDto maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaxAndMinAmountDto {

    private String amount;
    private String currency;
    
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDTO {
    private Integer days;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    
}


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermsDTO {
    private List<TermDTO> terms;

    public List<TermDTO> getTerms() {
        return terms;
    }

    public void setTerms(List<TermDTO> terms) {
        this.terms = terms;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;





@Service
public class ErrorService {
    
    @Value("${ms_name}")
    private String msName;

    @Value("${ms_version}")
    private String msVersion;
    
    @Value("${errors.level}")
    private String errorLevel;
    
    @Value("${errors.functional}")
    private String functionalError;
    
    @Value("${errors.technical}")
    private String techinicalError;
    
    @Value("${errors.general.invalid_value}")
    public String invalidValue;

    @Value(("${errors.general.blank_data}"))
    public String blankValue;


    public ServiceException errorBuilder(HttpStatus status, String message, ErrorType type){

        String errorType = type == ErrorType.FUNCTIONAL ? functionalError : techinicalError;

        var error = ErrorDTO.builder()
                    .code(msName + "-" + errorType + "-9" + status.value())
                    .level(errorLevel)
                    .message(message)
                    .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                    .build();

        return new ServiceException(status, error);
    }

    public void isBlank(String value, String fieldName){
        if(value.isBlank()){            
            var message = "'"+ fieldName +"' " + blankValue;

            throw errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);            
        }        
    }

}

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;
}


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception;


import java.util.ArrayList;
import java.util.List;

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

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${params.app-name}")
    private String msName;
    private  String levelMessage = "error";
    private String pf9400 = "-P-F-9400";
    private String notSpectfiedMessage = " not specified";
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-T-9409")
                .message("Unhandled exception")
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Unhandled exception")
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
                    .code(msName + "-P-F-9404")
                    .level(levelMessage)
                    .message(errorMessage)
                    .description(msName.toLowerCase() + "-api-services-v3: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-F-9404")
                .message("Not Found")
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Not Found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message("Required query parameter " + ex.getParameterName() + notSpectfiedMessage)
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Required query parameter " + ex.getParameterName() + notSpectfiedMessage)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message(ex.getMessage())
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message("Required header " + ex.getHeaderName() + notSpectfiedMessage)
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Required header " + ex.getHeaderName() + notSpectfiedMessage)
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
                    error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,msName.toLowerCase()));
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
                .code(msName + pf9400)
                .message("Invalid body structure")
                .level("error")
                .description(msName.toLowerCase() + "-api-services-v3: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-F-9405")
                .message("Method not allowed")
                .level(levelMessage)
                .description(msName.toLowerCase() + "-api-services-v3: Method not allowed")
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


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.mappers;


import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductsMappers {

    private ModelMapper mapper;

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    void init() {
        mapper = new ModelMapper();
    }


}//class closure


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.observability;
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


package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.observability;

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

package com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response.AmountResponseDto;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.amountRange.Response.MaxAndMinAmountDto;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.domain.products.response.TermsDTO;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.service.ProductsService;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils.RegexTypes;
import com.santander.bnc.bsn049.bncbsn049msprdctdrctry.products.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.CommerccialOfferDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.OfferDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.commercialOffer.response.SubproductsDTO;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ErrorService errorService;
    private final RegexUtils regexUtils;    


    @Value("${products.product}")
    String product;

    @Value("${amount.minimum}")
    private String minimumAmount;

    @Value("${currency}")
    private String currency;

    @Value("${amount.maximum}")
    private String maximumAmount;    

    @Value("${errors.commercialOffer.bank_id_code}")
    private String bankIdCodeRrrorMessage;
    @Value("${errors.commercialOffer.center_id_code}")
    private String centerIdCodeErrorMessage;
    @Value("${errors.commercialOffer.product_id_code}")
    private String productIdCodeErrorMessage;
    @Value("${errors.commercialOffer.channel_id_code}")
    private String channelIdCodeErrorMessage;
    @Value("${center_id_code}")
    private String centerIdCode;
    @Value("${bank_id_code}")
    private String bankIdCode;
    @Value("${channel_code}")
    private String channelCodeId;
    @Value("${product_code_id}")
    private String productId;
    @Value("${offerName}")
    private String offerName;
    @Value("${offerDescription}")
    private String offerDesription;
    @Value("${offerProductCode}")
    private String offerProductCode;
    @Value("${offerProductDescription}")
    private String offerProductDescription;
    @Value("${subproductsSubproductsId}")
    private String subproductsSubproductsId;
    @Value("${subproductsName}")
    private String subproductsName;


    @Value("${products.days}")
    ArrayList<Integer> termDays;


    public TermsDTO getProductTerms(String productIdEnrty) {

        if (productIdEnrty == null || productIdEnrty.isBlank()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.BLANK_DATA);
        }
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, productIdEnrty, "product_id");
        regexUtils.validateRegex(RegexTypes.PRODUCT_LENGTH, productIdEnrty, "product_id");

        // 040250
        if (!productIdEnrty.equals(product)) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PRODUCT_NOT_EXIST);
        }

        TermsDTO response = new TermsDTO();
        ArrayList<TermDTO> lista = new ArrayList<>();

        for (int x : termDays) {
            TermDTO term = new TermDTO();
            term.setDays(x);
            lista.add(term);
        }
        response.setTerms(lista);

        return response;
    }// end getProductTerms

    @Override
    public CommerccialOfferDTO getCommercialOffer(String bankIdEntry, String centerIdEntry, String productCodeEntry,
            String channelCodeEntry) {
                String bankIdMessage = "bank_id";
        if (bankIdEntry != null) {
            errorService.isBlank(bankIdEntry, bankIdMessage);
            regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, bankIdEntry, bankIdMessage);
            regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CODE_NUMBER_LENGTH, bankIdEntry, bankIdMessage);
        }
        if (centerIdEntry != null) {
            String centerIdMessage = "center_id";
            errorService.isBlank(centerIdEntry, centerIdMessage);
            regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, centerIdEntry, centerIdMessage);
            regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CODE_NUMBER_LENGTH, centerIdEntry, centerIdMessage);
        }
        if (productCodeEntry != null) {
            String productCodeMessage = "product_code";
            errorService.isBlank(productCodeEntry, productCodeMessage);
            regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, productCodeEntry, productCodeMessage);
            regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_PRODUCT_CODE_LENGTH, productCodeEntry, productCodeMessage);
        }
        if (channelCodeEntry != null) {
            String channelCodeMessage= "channel_code";
            errorService.isBlank(channelCodeEntry, channelCodeMessage);
            regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CHANNEL_CODE_FORMAT, channelCodeEntry, channelCodeMessage);
            regexUtils.validateRegex(RegexTypes.COMMERCIAL_OFFER_CHANNEL_CODE_LENGTH, channelCodeEntry, channelCodeMessage);
        }

        if (productCodeEntry.equals(offerProductCode) && centerIdEntry.equals(centerIdCode) && bankIdEntry.equals(bankIdCode)
                && channelCodeEntry.equals(channelCodeId)) {
            CommerccialOfferDTO responseDto = new CommerccialOfferDTO();
            List<OfferDTO> offer = new ArrayList<>();
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setProductId(productId);
            offerDTO.setName(offerName);
            offerDTO.setDescription(offerDesription);
            offerDTO.setProductCode(offerProductCode);
            offerDTO.setProductDescription(offerProductDescription);
            List<SubproductsDTO> subProducts = new ArrayList<>();
            SubproductsDTO subproductsDTO = new SubproductsDTO();
            subproductsDTO.setSubproductsId(subproductsSubproductsId);
            subproductsDTO.setName(subproductsName);
            subProducts.add(subproductsDTO);
            offerDTO.setSubproducts(subProducts);
            offer.add(offerDTO);
            responseDto.setOffer(offer);
            return responseDto;
        } else {
            if (!bankIdEntry.equals(bankIdCode)) {
                throw errorService.errorBuilder(HttpStatus.NOT_FOUND, bankIdCodeRrrorMessage, ErrorType.FUNCTIONAL);
            } else if (!centerIdEntry.equals(centerIdCode)) {
                throw errorService.errorBuilder(HttpStatus.NOT_FOUND, centerIdCodeErrorMessage,
                        ErrorType.FUNCTIONAL);
            } else if (!productCodeEntry.equals(productId)) {
                throw errorService.errorBuilder(HttpStatus.NOT_FOUND, productIdCodeErrorMessage,
                        ErrorType.FUNCTIONAL);
            } else if (!channelCodeEntry.equals(channelCodeId)) {
                throw errorService.errorBuilder(HttpStatus.NOT_FOUND, channelIdCodeErrorMessage,
                        ErrorType.FUNCTIONAL);
            } else {
                return null;
            }
        }
    }

    @Override
    public AmountResponseDto getProductAmountRange(String productId) {

        // Validar el formato y longitud de productId
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, productId, "productId");
        regexUtils.validateRegex(RegexTypes.PRODUCT_LENGTH, productId, "productId");

        //040250
        if(!productId.equals(product)){
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PRODUCT_NOT_EXIST);
        }

        log.info("Obteniendo el rango de montos para el producto con productId={}", productId);

        // Utiliza los valores cargados desde el archivo de propiedades
        AmountResponseDto responseDto = new AmountResponseDto();

        MaxAndMinAmountDto minimumAmountdto = new MaxAndMinAmountDto(minimumAmount, currency);
        responseDto.setMinimumAmount(minimumAmountdto);
        MaxAndMinAmountDto maximumAmountdto = new MaxAndMinAmountDto(maximumAmount, currency);
        responseDto.setMaximumAmount(maximumAmountdto);



        log.info("Rango de montos obtenido con exito para el producto con productId={}", productId);
        return responseDto;
    }
}
// class closure



