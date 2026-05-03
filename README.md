package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    private final String PRODUCT = "cdt";

    @Override
    public void putContext(String key, Object object) {
        try{
            log.info(GUtils.SLOG+"client putContext KEY={}",key);
            ContextRequest request = new ContextRequest();
            request.setProduct(PRODUCT);
            request.setValue(object);
            request.setKey(key);
            contextAPI.putCache(request).execute();
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



package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.GUtils;
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
    public List<DataListDTO> getParameter(String parameterId, String valueCode,SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "client get parameter id {} and valueCode {}", parameterId, valueCode);

        Response<GeographiesParametersResponseDTO> responseApi;
        try {
            responseApi = parametersAPI.getParameter(parameterId,valueCode,securityHeaders.getAuthorization(),securityHeaders.getxSantanderClientId()).execute();
            if (!((responseApi.isSuccessful() || responseApi.code() == 204) && responseApi.body() != null)){
                var error = ErrorCatalog.MS_PARAMETERS_RESPONSE;
                error.setMessage(responseApi.message());
                error.setDescription(ErrorDictionary.MS_NAME + " - " + responseApi);
                throw new ServiceException(HttpStatus.BAD_REQUEST, error);
            }
            if(responseApi.body().getParameters().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.MS_PARAMETERS_NO_ENTRY);
            }
        } catch(RuntimeException e){
            log.error("Runtime Exception calling parameters: {}", e.getMessage()); 
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.MS_PARAMETERS_RESPONSE );        
        } catch(IOException e){  
            log.error("IOException calling parameters: {}", e.getMessage());           
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_PARAMETERS_NETWORK_CONNECTION);
        } catch (Exception e){
            log.info("Unhandled exception calling parameters: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.MS_PARAMETERS_GENERAL);
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure

package com.santander.bnc.bsn049.bncbsn049mscustomer.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.ClientUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class Handle pre-calls from Client
 * Retrofit 2
 *
 * @author Wilfredo Peña
 * @see 'restClientConfig component'
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TrxPersonAPIImpl implements TrxPersonService {

    /**
     * Person Retrofit Api
     */
    private final TrxPersonAPI trxPersonAPI; 

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;       

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;

    private final ObjectMapper objectMapper;
    /**
     * Generic method
     * Build own PayloadHeader
     *
     * @param request
     * @param action
     * @return TrxPersonResponse DTO
     */
    @Override
    public TrxPersonResponse callPostTRX(TrxPersonRequest request, ClientEnum action) {
        String serviceRoute = action.value();
        log.info(GUtils.SLOG + "client {}", serviceRoute);
        request.setCabecera(ClientUtils.buildHeader(serviceRoute));
        try {

            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            
            try {
    			String jsonRequest = objectMapper.writeValueAsString(request);
    			StringBuilder sb = new StringBuilder();
    			sb.append(" serviceRoute=").append(serviceRoute);
    			sb.append(", idFormulario=").append(serviceRoute);
    			sb.append(", mqRoute=").append(mqRoute);
    			sb.append(", Request=").append(jsonRequest);
    			log.info("**** Request ConsultaDatosBasicosPNatural {}", sb.toString());
    		} catch (Exception e) {
    			log.error("Error serializando payload");
    		}
            
            
            Response<TrxPersonResponse> responseApi = trxPersonAPI.callPostTRX(request, serviceRoute, serviceRoute, mqRoute).execute();
            if (responseApi.isSuccessful()) {
                TrxPersonResponse responseB = responseApi.body();
                log.info(GUtils.ELOG + "client {}", responseB);
                return responseB;
            } else {
                String errorBody = responseApi.errorBody().string();
                ObjectMapper objm = new ObjectMapper();
                TrxPersonResponse err = objm.readValue(errorBody,TrxPersonResponse.class);
                log.info(GUtils.ELOG + "err {}", err.getErrores());
                List<ErrorDTO> errosDtos = new ArrayList<>();

                for(ErrorTrxDTO dtoEr :err.getErrores()){
                    ErrorDTO newDto  = new ErrorDTO();
                    newDto.setCode(ErrorDictionary.MS_NAME + "-P-T-" + Integer.toString(responseApi.code()));
                    newDto.setLevel(ErrorDictionary.ERROR_LEVEL);
                    newDto.setDescription(ErrorDictionary.MS_NAME + " - " + dtoEr.getMensaje());
                    newDto.setMessage(dtoEr.getMensaje());
                    errosDtos.add(newDto);
                }

                log.info(GUtils.ELOG + "SET {}", errosDtos);

                throw new ServiceException(HttpStatus.CONFLICT, !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
            }
        } catch(RuntimeException e){
            log.error("Error in TRX.: {}", e.toString());  

            var error = ErrorCatalog.MS_SANBA_TRX_RUNTIME_ERROR;
            error.setMessage(e.toString());                               
            if(e.getMessage().equals("PERSONA INEXISTENTE") || e.getMessage().equals("EL CLIENTE REQUERIDO NO ES PERSONA FISICA.") ){                
                throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.CUSTOMER_NOT_FOUND);     
            }
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);     
        } catch(IOException e){            
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
        } catch (Exception e){
            log.info("Error in TRX..: {}", e.toString());  
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.toString());          
            throw new ServiceException(HttpStatus.CONFLICT, error);
        }
    }//method closure
}//class closure



************

package com.santander.bnc.bsn049.bncbsn049mscustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.CustomerDetailsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.CustomerService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    final CustomerService customerService;
    private final ObjectMapper objectMapper;

    /**
     * Customer search
     * @param request
     * @return
     */
    @PostMapping(ServiceDirectory.CUSTOMERS_SEARCH)
    public ResponseEntity<CustomerSearchResponseDTO> searchCustomers(
           @Valid @RequestBody(required = true) CustomerRequestDTO request,
           @RequestHeader(required = true, name = "Authorization") String authorization,
           @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {

    	try {
			String jsonRequest = objectMapper.writeValueAsString(request);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", payload=").append(jsonRequest);
			log.info("*** INIT (POST) /v3/customers/search..= {} >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
    	
        log.info(GUtils.SLOG + "endpoint search customers by person={}", request.getPerson());
        CustomerSearchResponseDTO response = customerService.searchCustomer(request, new SecurityHeaders(authorization, xSantanderClientId));
        
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", Response=").append(jsonResponse);
			log.info("*** FIN (POST) /v3/customers/search= {} >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
        
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
               
        log.info(GUtils.ELOG + "endpoint search customers {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * Customer Details
     * @param customerId
     * @return
     */
    @GetMapping(ServiceDirectory.CUSTOMERS + "/{customerId}")
    public ResponseEntity<CustomerDetailsResponseDTO> getCustomers(@PathVariable(required = true, name = "customerId") String customerId,
                                                                   @RequestHeader(required = true, name = "Authorization") String authorization,
                                                                   @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {
        log.info(GUtils.SLOG + "endpoint get customers by customerId={}", customerId);

		StringBuilder sb = new StringBuilder();
		sb.append(" clientId=").append(xSantanderClientId);
		log.info("*** INIT. (GET) /v3/customers/{} - {}>>> ", customerId,sb.toString());
        
        CustomerDetailsResponseDTO response = customerService.getCustomerDetails(customerId,new SecurityHeaders(authorization,xSantanderClientId));
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" clientId=").append(xSantanderClientId);
			sbr.append(", Response=").append(jsonResponse);
			log.info("***... FIN (GET) /v3/customers/{} - {} >>> ",customerId, sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
        
        if(response == null){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.PERSON_IS_NOT_CLIENT);
        }
        log.info(GUtils.ELOG + "endpoint. get customers");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * PATCH PROSPECT
     * Until no DTO definition left Object.class
     *
     * @return null
     */
    @PatchMapping(ServiceDirectory.CUSTOMERS + "/{customerId}")
    public ResponseEntity<Object> updateCustomers(@PathVariable(name = "customerId") String customerId,
                                                  @RequestBody CreateCustomerRequestDTO request,
                                                  @RequestHeader(required = true, name = "Authorization") String authorization,
                                                  @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId){
        log.info(GUtils.SLOG + "endpoint update customer {}", customerId);
        
        try {
			String jsonRequest = objectMapper.writeValueAsString(request);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", payload=").append(jsonRequest);
			log.info("*** INIT (PATCH) /v3/customers/{} - {}>>> ",customerId, sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
        
        customerService.updateCustomer(request,customerId,new SecurityHeaders(authorization, xSantanderClientId));
        log.info(GUtils.ELOG + "endpoint update customer.");
        
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", Response=").append(jsonResponse);
			log.info("*** FIN (PATCH) /v3/customers/{} - {}>>> ",customerId, sb.toString());
		} catch (Exception e) {
			log.error("Error response serializando payload");
		}
        
        return response;
    }//method closure

    /**
     * PUT CUSTOMER FROM PROSPECT
     * @param customerId
     * @return null
     */
    @PutMapping(ServiceDirectory.CUSTOMERS + "/{customerId}")
    public ResponseEntity<Object> updateCustomersProspect(@PathVariable(name = "customerId") String customerId,
                                                  @RequestBody UpdateProspectRequestDTO request,
                                                  @RequestHeader(required = true, name = "Authorization") String authorization,
                                                  @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId){
        log.info(GUtils.SLOG + "endpoint update customer to prospect {}", customerId);
        
        try {
			String jsonRequest = objectMapper.writeValueAsString(request);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", payload=").append(jsonRequest);
			log.info("*** INIT (PUT) /v3/customers/{} - {}>>> ",customerId, sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
        
        customerService.updateCustomersProspect(request,customerId,new SecurityHeaders(authorization, xSantanderClientId));
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        log.info(GUtils.ELOG + "endpoint update customer to prospect.");
        
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", Response=").append(jsonResponse);
			log.info("*** FIN (PUT) /v3/customers/{} - {}>>> ",customerId, sb.toString());
		} catch (Exception e) {
			log.error("Error response serializando payload");
		}
        
        return response;
    }//method closure

}//class closure



package com.santander.bnc.bsn049.bncbsn049mscustomer.config;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.ApiEntry;
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



package com.santander.bnc.bsn049.bncbsn049mscustomer.config;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.ApiEntry;
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
