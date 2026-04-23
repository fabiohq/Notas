
spring.profiles.active: local
---
darwin:
  region: boae
  suffix:
  app-name: bsn049
  logging:
    format: GLUONLOG
    gluon-log:
      company: bnc
      componentName: mscontracts
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
  core:
    exceptions:
      error-format: GLUON
  security:
    white-list:
      - /**
    connectors:
      pkm-connector:
        pkm-endpoint:
          - ${PKM_ENDPOINT:localhost://}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false
spring:
  application:
    name: bnc-bsn049-mscontracts
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  config:
    import: classpath:messages.properties, classpath:regex.properties

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049mscontracts: INFO
    okhttp3: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-}, %X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"

management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
      group:
        readiness:
          include: readinessState,ping,externalApis
        liveness:
          include: livenessState
observability:
  external-apis: 
    timeout-ms: 5000
    checks: 
      - name: backend-for-frontend-security 
        url: http://${bff-host}/actuator/health
        critical: false 
        accepted-statuses:
          - 200
          - 404
      - name: product-directory 
        url: http://${product-directory-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404
      - name: term-deposit 
        url: http://${term-deposit-parameters-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404
      - name: banksandbranches
        url: http://${banks-host}/actuator/health
        critical: false 
        accepted-statuses:
          - 200
          - 404
springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful
  port: 8083

params:
  appName: CONTRACTS
  appVersion: api-services-v3
  commons:
    productCode: "04"
    subproductCode: "0250"
    currency: "COP"
    bankId: "0065"
    centerId: "0060"
  pepf:
    user: "@NETE781"
    numper: "03018043"
  sanba:
    mqRoute: "QCTFD"
    user: "@NETE781"
    channel: "60"

  frequencies: "90,180,270,360,540"
  settlements: "BGMF,RETF,ITEA"
  condition-codes: TMVC

bff-host: "backend-for-frontend-sanba-gui.apps.ocp4-preprod.cosanpre.corp"
bff-port: 8080
bff-https: false

product-directory-host: "ms-productdirectory-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
product-directory-port: 8080
product-directory-https: false

term-deposit-parameters-host: "ms-termdepositparameters-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
term-deposit-parameters-port: 8080
term-deposit-parameters-https: false

banks-host: "ms-banksandbranches-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
banks-port: 8080
banks-https: false

integration:
  catalogue:
    - integrationType: sanba
      host: ${bff-host}
      port: ${bff-port}
      https: ${bff-https}
      endpoint: "/service-engine/procesar/"
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: product-directory
      host: ${product-directory-host}
      port: ${product-directory-port}
      https: ${product-directory-https}
      endpoint: "/v3/products/"      
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: term-deposit-parameters
      host: ${term-deposit-parameters-host}
      port: ${term-deposit-parameters-port}
      https: ${term-deposit-parameters-https}
      endpoint: "/v1/term_deposit_parameters/"
      timeOutConn: 1000
      timeOutRead: 1000
    - integrationType: banks
      host: ${banks-host}
      port: ${banks-port}
      https: ${banks-https}
      endpoint: "/v2/banks/"
      timeOutConn: 1000
      timeOutRead: 1000


service-route-trx:
  BP17: simulacionCDTBP17S171
  PEPF: modificarMantencionPersonaNaturalInfAdicional
  BP01: altaCuentaPlazoODS
  BP02: altaIpfOdsCtaExterna
  BP31: SBCDTTI01-ConsultaCDTDATTitular2654
  BP13: consultaDatosIPF
  BP49: SBCCG001ConsultaDetalladaMovimientos2652
  BP21: modificacionDatosIPF
****************************
package com.santander.bnc.bsn049.bncbsn049mscontracts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksParametersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanksServiceImpl implements BanksService {
    final BanksApi banksApi;

    @Override
    public BanksDTO banksResponse(BanksParametersRequest request) {
        try {
            return banksApi.callBanks(request.getAuthorization(), request.getxSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }
}

*******************************

package com.santander.bnc.bsn049.bncbsn049mscontracts.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorType;

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

import com.santander.bnc.bsn049.bncbsn049mscontracts.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.response.TrxBP31Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;
    private static final String ERROR = "Error in TRX: {}";
    private static final String CLIENT = "client {}";
    private static final String ERR = "err {}";
    private static final String ERROR_MESSAGE = "No error";
    private static final String ERROR_MESSAGES = "Error al leer el cuerpo del error de la respuesta API";
    private static final String SET = "SET {}";
    private static final String BP31_SERVICE_ROUTE = "SBCDTTI01-ConsultaCDTDATTitular2654";
    private static final String BP21_SERVICE_ROUTE = "modificacionDatosIPF";
    private static final String BP13_SERVICE_ROUTE = "consultaDatosIPF";
    @Value("${service-route-trx.BP17}")
    private  String BP17_SERVICE_ROUTE;
    @Value("${service-route-trx.PEPF}")
    private  String PEPF_SERVICE_ROUTE;
    @Value("${service-route-trx.BP01}")
    private  String BP01_SERVICE_ROUTE;
    @Value("${service-route-trx.BP02}")
    private  String BP02_SERVICE_ROUTE;
    @Value("${service-route-trx.BP49}")
    private  String BP49_SERVICE_ROUTE;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;
    @Value("${params.sanba.user}")
    private String user;
    @Value("${params.sanba.channel}")
    private String channel;
	private final ObjectMapper objectMapper;
    @Override
    public TrxBP17Response trxBP17(TrxBP17Request request) {
        Response<TrxBP17Response> responseApi = null;

        try {
			String jsonRequest = objectMapper.writeValueAsString(request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP17_SERVICE_ROUTE=").append(BP17_SERVICE_ROUTE);
			sb.append(" mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callBP17TRX {}", sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP17TRX-Error serializando payload");
		}
        
        try {

            responseApi = trxSanbaAPI.callBP17TRX(request, BP17_SERVICE_ROUTE, BP17_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        try {
			String jsonResponse = objectMapper.writeValueAsString(responseApi);
			StringBuilder sb = new StringBuilder();
			sb.append(", Response=").append(jsonResponse);
			log.info("trxSanbaAPI.callBP17TRX {}", sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP17TRX-Error serializando payload");
		}
        
        if (responseApi.isSuccessful()) {
            TrxBP17Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBP17Response err = parseErrorResponse17 (errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + SET, errosDtos);

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }
    private TrxBP17Response parseErrorResponse17(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBP17Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }
    public TrxBP31Response trxBP31(TrxBP31Request request) {
        Response<TrxBP31Response> responseApi = null;
        
        try {
			String jsonRequest = objectMapper.writeValueAsString(request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP31_SERVICE_ROUTE=").append(BP31_SERVICE_ROUTE);
			sb.append(" mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callBP31TRX {}", sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP31TRX-Error serializando payload");
		}
        
        try {

            responseApi = trxSanbaAPI.callBP31TRX(request, BP31_SERVICE_ROUTE, BP31_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

		try {
			String jsonResponse = objectMapper.writeValueAsString(responseApi);
			StringBuilder sb = new StringBuilder();
			sb.append(", Response=").append(jsonResponse);
			log.info("trxSanbaAPI.callBP31TRX {}", sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP31TRX-Error serializando payload");
		}
        
        if (responseApi.isSuccessful()) {
            TrxBP31Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBP31Response err = parseErrorResponse31(errorBody) ;

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = buildErrorDTOs31(err);



            log.info(GUtils.ELOG + SET, errosDtos);
            if (!errosDtos.isEmpty()
                && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")){

                    return null;
                }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }
    private List<ErrorDTO> buildErrorDTOs31(TrxBP31Response err) {
        List<ErrorDTO> errorDTOs = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorDTOs.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }
        log.info(GUtils.ELOG + SET, errorDTOs);
        return errorDTOs;
    }
    private TrxBP31Response parseErrorResponse31(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBP31Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

    @Override
    public TrxPEPFDataResponse trxPEPF(TrxPEPFDataRequest trxPEPFDataRequest) {
        Response<TrxPEPFDataResponse> responseApi = null;

		try {
			String jsonRequest = objectMapper.writeValueAsString(trxPEPFDataRequest);
			StringBuilder sb = new StringBuilder();
			sb.append("PEPF_SERVICE_ROUTE=").append(PEPF_SERVICE_ROUTE);
			sb.append(" mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callPEPF {}", sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callPEPF-Error serializando payload");
		}

        try {

            responseApi = trxSanbaAPI.callPEPF(trxPEPFDataRequest, PEPF_SERVICE_ROUTE, PEPF_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        try {
         	String jsonResponse = objectMapper.writeValueAsString(responseApi);
     		StringBuilder sb = new StringBuilder();
     		sb.append(", Response=").append(jsonResponse);
     		log.info("trxSanbaAPI.callPEPF {}",sb);
 		 } catch (Exception e) {
 			log.error("trxSanbaAPI.callPEPF-Error serializando payload");
 		 }
        
        if (responseApi.isSuccessful()) {
            TrxPEPFDataResponse responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }


            TrxPEPFDataResponse err = parseErrorResponsePEPF(errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = buildErrorDTOsPEPF (err);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")) {
                throw new ServiceException(HttpStatus.NO_CONTENT, "No message");
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private List<ErrorDTO> buildErrorDTOsPEPF(TrxPEPFDataResponse err) {
        List<ErrorDTO> errorDTOs = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorDTOs.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }
        log.info(GUtils.ELOG + SET, errorDTOs);
        return errorDTOs;
    }
    private TrxPEPFDataResponse parseErrorResponsePEPF(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxPEPFDataResponse.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }
     //
     @Override
     public TrxBP13Response trxBP13(TrxBP13Request request) {
         Response<TrxBP13Response> responseApi = null;
 
			try {
				String jsonRequest = objectMapper.writeValueAsString(request);
				StringBuilder sb = new StringBuilder();
				sb.append("BP13_SERVICE_ROUTE=").append(BP13_SERVICE_ROUTE);
				sb.append(" mqRoute=").append(mqRoute);
				sb.append(", payload=").append(jsonRequest);
				log.info("trxSanbaAPI.callBP13TRX {}", sb);
			} catch (Exception e) {
				log.error("trxSanbaAPI.callBP13TRX-Error serializando payload");
			}

         try {
 
             responseApi = trxSanbaAPI.callBP13TRX(request, BP13_SERVICE_ROUTE, BP13_SERVICE_ROUTE, mqRoute).execute();
 
         } catch (RuntimeException e) {
             log.info(ERROR, e.getMessage());
             var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
             error.setMessage(e.getMessage());
             throw new ServiceException(HttpStatus.BAD_REQUEST, error);
 
         } catch (IOException e) {
             throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
 
         } catch (Exception e) {
             log.info(ERROR, e.getMessage());
             var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
             error.setMessage(e.getMessage());
             throw new ServiceException(HttpStatus.CONFLICT, error);
 
         }
 
         try {
         	String jsonResponse = objectMapper.writeValueAsString(responseApi);
     		StringBuilder sb = new StringBuilder();
     		sb.append(", Response=").append(jsonResponse);
     		log.info("trxSanbaAPI.callBP13TRX {}",sb);
 		 } catch (Exception e) {
 			log.error("trxSanbaAPI.callBP13TRX-Error serializando payload");
 		 }
         
         if (responseApi.isSuccessful()) {
             TrxBP13Response responseBody = responseApi.body();
             log.info(GUtils.ELOG + CLIENT, responseBody);
             return responseBody;
 
         } else {
             String errorBody = "";
             try {
                 errorBody = responseApi.errorBody().string();
             } catch (IOException e) {
                 log.error(ERROR_MESSAGES, e);
             }

             TrxBP13Response err = parseErrorResponse13(errorBody);

             log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
             List<ErrorDTO> errosDtos = new ArrayList<>();
 
             for (ErrorTrxDTO dtoEr : err.getErrores()) {
 
                 errosDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
 
             }
 
             log.info(GUtils.ELOG + SET, errosDtos);
 
             throw new ServiceException(HttpStatus.CONFLICT,
                     !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
         }
     }
    private TrxBP13Response parseErrorResponse13(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBP13Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }
     @Override
    public TrxBp01Response trxBP01(TrxBp01Request trxBp01Request) {
        Response<TrxBp01Response> responseApi = null;
        
        try {
			String jsonRequest = objectMapper.writeValueAsString(trxBp01Request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP01_SERVICE_ROUTE=").append(BP01_SERVICE_ROUTE);
			sb.append(" mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callBP01 {}",sb);
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
        
        try {

            responseApi = trxSanbaAPI.callBP01(trxBp01Request, BP01_SERVICE_ROUTE, BP01_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }
        try {
        	String jsonResponse = objectMapper.writeValueAsString(responseApi);
    		StringBuilder sb = new StringBuilder();
    		sb.append(", Response=").append(jsonResponse);
    		log.info("trxSanbaAPI.callBP01 {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP01-Error serializando payload");
		}
        if (responseApi.isSuccessful()) {
            TrxBp01Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBp01Response err = parseErrorResponse01 (errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + SET, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    private TrxBp01Response parseErrorResponse01(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBp01Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }
    @Override
    public TrxBp02Response trxBP02(TrxBp02Request trxBp02Request) {
        Response<TrxBp02Response> responseApi = null;

        try {
			String jsonRequest = objectMapper.writeValueAsString(trxBp02Request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP02_SERVICE_ROUTE=").append(BP02_SERVICE_ROUTE);
			sb.append("mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callBP02 {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP02-Error serializando payload");
		}
        
        try {

            responseApi = trxSanbaAPI.callBP02(trxBp02Request, BP02_SERVICE_ROUTE, BP02_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        try {
        	String jsonResponse = objectMapper.writeValueAsString(responseApi);
    		StringBuilder sb = new StringBuilder();
    		sb.append(", Response=").append(jsonResponse);
    		log.info("trxSanbaAPI.callBP02 {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP02-Error serializando payload");
		}
        
        if (responseApi.isSuccessful()) {
            TrxBp02Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBp02Response err = parseErrorResponse02(errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + SET, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    private TrxBp02Response parseErrorResponse02 (String errorBody) {

        ObjectMapper objm = new ObjectMapper();

        try {
            return objm.readValue(errorBody, TrxBp02Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

    //
    @Override
    public TrxBP49Response trxBP49(TrxBP49Request trxBp49Request) {

        Response<TrxBP49Response> responseApi = null;


        try {
			String jsonRequest = objectMapper.writeValueAsString(trxBp49Request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP49_SERVICE_ROUTE=").append(BP49_SERVICE_ROUTE);
			sb.append("mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI-callBP49 {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP49-Error serializando payload");
		}
        
        try {

            responseApi = trxSanbaAPI.callBP49(trxBp49Request, BP49_SERVICE_ROUTE, BP49_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        try {
        	String jsonResponse = objectMapper.writeValueAsString(responseApi);
    		StringBuilder sb = new StringBuilder();
    		sb.append(", Response=").append(jsonResponse);
    		log.info("trxSanbaAPI-callBP49 {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP49-Error serializando payload");
		}
        
        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBP49Response err = parseErrorResponse49 (errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = buildErrorDTOs(err);
            if(!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE") ){
                throw  errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,"Deposit not found",ErrorType.FUNCTIONAL);
            }
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private List<ErrorDTO> buildErrorDTOs(TrxBP49Response err) {
        List<ErrorDTO> errorDTOs = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorDTOs.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }
        log.info(GUtils.ELOG + SET, errorDTOs);
        return errorDTOs;
    }
    private TrxBP49Response parseErrorResponse49 (String errorBody) {

        ObjectMapper objm = new ObjectMapper();
        try {
            return objm.readValue(errorBody, TrxBP49Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

    @Override
    public TrxBP21Response trxBP21(TrxBP21Request trxBp21Request) {
    	
        Response<TrxBP21Response> responseApi = null;

        trxBp21Request.getCabecera().setCanal(channel);
        trxBp21Request.getCabecera().getSesion().setUsuario(user);
        
        try {
			String jsonRequest = objectMapper.writeValueAsString(trxBp21Request);
			StringBuilder sb = new StringBuilder();
			sb.append("BP21_SERVICE_ROUTE=").append(BP21_SERVICE_ROUTE);
			sb.append("mqRoute=").append(mqRoute);
			sb.append(", payload=").append(jsonRequest);
			log.info("trxSanbaAPI.callBP21TRX {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP21TRX-Error serializando payload");
		}
    	
        
        try {

            responseApi = trxSanbaAPI.callBP21TRX(trxBp21Request, BP21_SERVICE_ROUTE, BP21_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(ERROR, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        try {
        	String jsonResponse = objectMapper.writeValueAsString(responseApi);
    		StringBuilder sb = new StringBuilder();
    		sb.append(", Response=").append(jsonResponse);
    		log.info("trxSanbaAPI.callBP21TRX {}",sb);
		} catch (Exception e) {
			log.error("trxSanbaAPI.callBP21TRX-Error serializando payload");
		}
        
		
        if (responseApi.isSuccessful()) {
            TrxBP21Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                log.error(ERROR_MESSAGES, e);
            }

            TrxBP21Response err = parseErrorResponse(errorBody);

            log.info(GUtils.ELOG + ERR, err != null ? err.getErrores() : ERROR_MESSAGE);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + SET, errosDtos);


            throw new ServiceException(HttpStatus.NOT_FOUND,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private TrxBP21Response parseErrorResponse(String errorBody) {

        ObjectMapper objm = new ObjectMapper();

        try {
            return objm.readValue(errorBody, TrxBP21Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }

}
