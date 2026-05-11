package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContextAPIImpl implements ContextApiService {

    /**
     * Person Retrofit Api
     */
    private final ContextAPI contextAPI;

    private static final String PRODUCT = "cdt";

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
        }catch (Exception e){
            log.error("Error putting caché. {}", e.getMessage());
        }

    }//method closure

    @Override
    public Object getContext(String key) {
        try{
            log.info(GUtils.SLOG+"client getContext KEY={}",key);
            return contextAPI.getCache(key,PRODUCT).execute().body().getValue();
        }catch (Exception e){
            log.error("Error getting caché {}", e.getMessage());
            return null;
        }
    }//method closure
}//class closure



package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;

import java.util.List;

public interface ParameterApiService {

    List<DataListDTO> getParameter(String parameterId,String valueCode,SecurityHeaders securityHeaders);

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ClientEnum;

public interface TrxPersonService {

    TrxPersonResponse callPostTRX(TrxPersonRequest request, ClientEnum action);

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.CustomerContactPointsService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerContactPointsController {

    final TrxPersonService trxPersonService;
    final CustomerContactPointsService customerService;
    private final ObjectMapper objectMapper;
    /**
     * Customer Contact Points
     * 
     * @param customerId
     * @return
     */
    @GetMapping(ServiceDirectory.CUSTOMERS + "/{customer_id}/contact_points")
    public ResponseEntity<ContactPointsResponseDTO> getCustomers(
            @PathVariable(name = "customer_id", required = true) String customerId,
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {
    	log.info("** INIT (GET) {}/{}/contact_points clientId={} >>>",ServiceDirectory.CUSTOMERS,customerId,xSantanderClientId);
    	log.info(GUtils.SLOG + "endpoint get customers by customer_id={}", customerId);
        ContactPointsResponseDTO response = customerService.getCustomerDetails(customerId,new SecurityHeaders(authorization,xSantanderClientId));

        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", Response=").append(jsonResponse);
			
			log.info("** FIN (GET) {}/{}/contact_points clientId={} {} >>>",ServiceDirectory.CUSTOMERS,customerId,xSantanderClientId,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
        
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info(GUtils.ELOG + "endpoint get customers");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }// method closure

    /**
     * Customer Contact Points
     * 
     * @param customerId
     * @return
     */
    @PutMapping(ServiceDirectory.CUSTOMERS + "/{customer_id}/contact_points/{contact_point_id}")
    public ResponseEntity<Object> putCustomerContactPoints(
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId,
            @PathVariable(name = "customer_id", required = true) String customerId,
            @PathVariable(name = "contact_point_id", required = true) String contactPointId,
            @RequestBody(required = true) ContactPointsRequestDTO contactPointRequest) {

    	try {
			String jsonRequest = objectMapper.writeValueAsString(contactPointRequest);
			StringBuilder sb = new StringBuilder();
			sb.append(", Request=").append(jsonRequest);
			
			log.info("** INIT (PUT) {}/{}/contact_points/{} clientId={} {}>>>"
					,ServiceDirectory.CUSTOMERS
					,customerId
					,contactPointId
					,xSantanderClientId
					,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando request payload");
		}
        log.info(GUtils.SLOG + "endpoint put customer contact point - customer_id={} - contact_point_id {}", customerId,
                contactPointId);
        customerService.putCustomerContactPoint(customerId, contactPointId, contactPointRequest,new SecurityHeaders(authorization,xSantanderClientId));

        log.info(GUtils.ELOG + "endpoint put customers");
        
        ResponseEntity<Object> response = ResponseEntity.noContent().build();
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(", Response=").append(jsonResponse);
			
			log.info("** FIN (PUT) {}/{}/contact_points/{} clientId={} {}>>>"
					,ServiceDirectory.CUSTOMERS
					,customerId
					,contactPointId
					,xSantanderClientId
					,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando RESPONSE payload");
		}
        return response;

    }// method closure
}// class closure



package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.config;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.ApiEntry;
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

package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.config;

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
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.ApiEntry;

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

   
    final IntegrationDataConfiguration properties;

    @Bean
    TrxPersonAPI txrTransactionApi(){
        return getRetrofitConfig(properties.getByApi("trx_person"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxPersonAPI.class);
    }

    @Bean
    ParametersAPI parametersAPI(){
        return getRetrofitConfig(properties.getByApi("parameters"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ParametersAPI.class);
    }

    @Bean
    ContextAPI contextAPI(){
        return getRetrofitConfig(properties.getByApi("context"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ContextAPI.class);
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
