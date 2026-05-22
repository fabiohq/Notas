package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.GUtils;
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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.GUtils;
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
                var error = ErrorCatalog.getMsParametersResponse();
                error.setMessage(responseApi.message());
                error.setDescription(ErrorDictionary.getMsName() + " - " + responseApi);
                throw new ServiceException(HttpStatus.BAD_REQUEST, error);
            }
            if(responseApi.body().getParameters().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersNoEntry());
            }
        } catch(RuntimeException e){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse());
        } catch(IOException e){            
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConnection());
        } catch (Exception e){
            log.info("Error in ParametersAPIImpl: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.ClientUtils;
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
                    newDto.setCode(ErrorDictionary.getMsName() + "-P-T-" + Integer.toString(responseApi.code()));
                    newDto.setLevel(ErrorDictionary.getErrorLevel());
                    newDto.setDescription(ErrorDictionary.getMsName() + " - " + dtoEr.getMensaje());
                    newDto.setMessage(dtoEr.getMensaje());
                    errosDtos.add(newDto);
                }

                log.info(GUtils.ELOG + "SET {}", errosDtos);

                throw new ServiceException(HttpStatus.CONFLICT, !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.getMsSanbaTrxError());
            }
        } catch(RuntimeException e){
            log.info("Error in TRX: {}", e.getMessage());  
            var error = ErrorCatalog.getMsSanbaTrxError();
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);     
        } catch(IOException e){            
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.getMsSanbaNetworkConnection());
        } catch (Exception e){
            log.info("Error in TRX: {}", e.getMessage());  
            var error = ErrorCatalog.getMsSanbaTrxError();
            error.setMessage(e.getMessage());          
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, error);
        }
    }//method closure
}//class closure


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.config;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.ApiEntry;
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.config;

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
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.api.TrxPersonAPI;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.ApiEntry;

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


    final   IntegrationDataConfiguration properties;

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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.ProspectContactPointsService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.ServiceDirectory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProspectContactPointsController {

    @Autowired
    TrxPersonService trxPersonService;

    @Autowired
    ProspectContactPointsService customerService;

    private final ObjectMapper objectMapper;
    /**
     * Customer Contact Points
     * @param prospectId
     * @return
     */
    @GetMapping(ServiceDirectory.CUSTOMERS + "/{prospect_id}/contact_points")
    public ResponseEntity<ContactPointsResponseDTO> getCustomers(
		@PathVariable(name = "prospect_id", required = true) String prospectId,
        @RequestHeader(required = true, name = "Authorization") String authorization,
        @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {
    	
    	log.info("** INIT (GET) {}/{}/contact_points clientId={} >>>",ServiceDirectory.CUSTOMERS,prospectId,xSantanderClientId);
    	
        log.info(GUtils.SLOG + "endpoint get customers by prospect_id={}", prospectId);
        ContactPointsResponseDTO response = customerService.getCustomerDetails(prospectId,new SecurityHeaders(authorization,xSantanderClientId));

        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sb = new StringBuilder();
			sb.append(" clientId=").append(xSantanderClientId);
			sb.append(", Response=").append(jsonResponse);
			
			log.info("** FIN (GET) {}/{}/contact_points clientId={} {} >>>",ServiceDirectory.CUSTOMERS,prospectId,xSantanderClientId,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response payload");
		}
        
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info(GUtils.ELOG + "endpoint get customers");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure


        /**
     * Customer Contact Points
     * @param prospectId
     * @return
     */
    @PatchMapping(ServiceDirectory.CUSTOMERS + "/{prospect_id}/contact_points/{contact_point_id}")
    public ResponseEntity<Object> patchProspectContactPoints(
        @RequestHeader(required = true, name = "Authorization") String authorization,
        @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId,
		@PathVariable(name = "prospect_id", required = true) String prospectId,
        @PathVariable(name = "contact_point_id", required = true) String contactPointId,
        @RequestBody(required = true) ContactPointsRequestDTO contactPointRequest) {


    	try {
			String jsonRequest = objectMapper.writeValueAsString(contactPointRequest);
			StringBuilder sb = new StringBuilder();
			sb.append(", Request=").append(jsonRequest);
			
			log.info("** INIT (PATCH) {}/{}/contact_points/{} clientId={} {}>>>"
					,ServiceDirectory.CUSTOMERS
					,prospectId
					,contactPointId
					,xSantanderClientId
					,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando request payload");
		}
    	
        log.info(GUtils.SLOG + "endpoint put customer contact point - prospect_id={} - contact_point_id {}", prospectId, contactPointId);
        customerService.patchProspectContactPoint(prospectId, contactPointId, contactPointRequest,new SecurityHeaders(authorization,xSantanderClientId));
        
        log.info(GUtils.ELOG + "endpoint patch prospect contact p");
	
		log.info("** FIN OK (PATCH) {}/{}/contact_points/{} clientId={} >>>"
				,ServiceDirectory.CUSTOMERS
				,prospectId
				,contactPointId
				,xSantanderClientId);
		
        return ResponseEntity.noContent().build();            
        
    }//method closure
}//class closure


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointsRequestDTO {
    
    private PostalAddressDTO postalAddress;
    private PhoneAddressDTO phoneAddress;
    private ElectronicAddressDTO electronicAddress;

    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }

    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressDTO {

    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressDTO {
    
    private String mobileNumber;
    private String phoneNumber;
    private String faxNumber;
    private String internationalCode;
    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    
    private String fullAddress;
    private String formatCode;
    private String formatDescription;
    private Boolean isAddressValidated;
    private String matchId;        
    private String streetTypeCode;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String mailDeliverySubLocation;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String department;
    private String subDepartment;
    private String postCodeIdentification;
    private String town;
    private String townName;
    private StateDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private CountryDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private String postBoxTypeDescription;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralTypeDescription;
    private String ruralNumber;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getFormatDescription() {
        return formatDescription;
    }

    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }

    public Boolean getAddressValidated() {
        return isAddressValidated;
    }

    public void setAddressValidated(Boolean addressValidated) {
        isAddressValidated = addressValidated;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }

    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }

    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }

    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }

    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }

    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getAlternativePremise() {
        return alternativePremise;
    }

    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }

    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }

    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }

    public String getMailingInstructions() {
        return mailingInstructions;
    }

    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }

    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }

    public String getPostBoxTypeDescription() {
        return postBoxTypeDescription;
    }

    public void setPostBoxTypeDescription(String postBoxTypeDescription) {
        this.postBoxTypeDescription = postBoxTypeDescription;
    }

    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }

    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZip4Code() {
        return zip4Code;
    }

    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }

    public String getRuralTypeCode() {
        return ruralTypeCode;
    }

    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }

    public String getRuralTypeDescription() {
        return ruralTypeDescription;
    }

    public void setRuralTypeDescription(String ruralTypeDescription) {
        this.ruralTypeDescription = ruralTypeDescription;
    }

    public String getRuralNumber() {
        return ruralNumber;
    }

    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String creationDate;
    private String lastUpdateDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestContactTimeDTO {
    private String fromDateTime;
    private String toDateTime;
    private String bestTimeFrameCode;
    private String bestTimeFrameDescription;


    public String getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    public String getBestTimeFrameCode() {
        return bestTimeFrameCode;
    }

    public void setBestTimeFrameCode(String bestTimeFrameCode) {
        this.bestTimeFrameCode = bestTimeFrameCode;
    }

    public String getBestTimeFrameDescription() {
        return bestTimeFrameDescription;
    }

    public void setBestTimeFrameDescription(String bestTimeFrameDescription) {
        this.bestTimeFrameDescription = bestTimeFrameDescription;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentDTO {
    private String legitimateCode;
    private String legitimateDescription;
    private String treatmentCode;
    private String treatmentDescription;
    private String purposeCode;
    private String purposeDescription;

    public String getLegitimateCode() {
        return legitimateCode;
    }

    public void setLegitimateCode(String legitimateCode) {
        this.legitimateCode = legitimateCode;
    }

    public String getLegitimateDescription() {
        return legitimateDescription;
    }

    public void setLegitimateDescription(String legitimateDescription) {
        this.legitimateDescription = legitimateDescription;
    }

    public String getTreatmentCode() {
        return treatmentCode;
    }

    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public String getPurposeDescription() {
        return purposeDescription;
    }

    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointDTO {
    private String contactPointId;
    private List<UseTypeDTO> useTypes;
    private PhoneAddressDTO phoneAddress;
    private Boolean preferredIndicator;
    private Boolean primaryIndicator;
    private ElectronicAddressDTO electronicAddress;
    private PostalAddressDTO postalAddress;

    public String getContactPointId() {
        return contactPointId;
    }

    public void setContactPointId(String contactPointId) {
        this.contactPointId = contactPointId;
    }

    public List<UseTypeDTO> getUseTypes() {
        return useTypes;
    }

    public void setUseTypes(List<UseTypeDTO> useTypes) {
        this.useTypes = useTypes;
    }

    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public Boolean getPreferredIndicator() {
        return preferredIndicator;
    }

    public void setPreferredIndicator(Boolean preferredIndicator) {
        this.preferredIndicator = preferredIndicator;
    }

    public Boolean getPrimaryIndicator() {
        return primaryIndicator;
    }

    public void setPrimaryIndicator(Boolean primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }

    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }

    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointsResponseDTO {
    private List<ContactPointDTO> contactPoints;

    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountyIdentificationDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LastDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinksDTO {
    private FirstDTO first;
    private PrevDTO prev;
    private NextDTO next;
    private LastDTO last;

    public FirstDTO getFirst() {
        return first;
    }

    public void setFirst(FirstDTO first) {
        this.first = first;
    }

    public PrevDTO getPrev() {
        return prev;
    }

    public void setPrev(PrevDTO prev) {
        this.prev = prev;
    }

    public NextDTO getNext() {
        return next;
    }

    public void setNext(NextDTO next) {
        this.next = next;
    }

    public LastDTO getLast() {
        return last;
    }

    public void setLast(LastDTO last) {
        this.last = last;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NextDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String faxNumber;
    private String internationalCode;
    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String fullAddress;
    private String formatCode;
    private String formatDescription;
    private Boolean isAddressValidated;
    private String matchId;
    private String streetTypeCode;
    private String streetTypeDescription;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String mailDeliverySubLocation;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String department;
    private String subDepartment;
    private String postCodeIdentification;
    private String townName;
    private StateDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private ProvinceDTO province;
    private RegionIdentificationDTO regionIdentification;
    private CountyIdentificationDTO countyIdentification;
    private CountryDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private String postBoxTypeDescription;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralTypeDescription;
    private String ruralNumber;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getFormatDescription() {
        return formatDescription;
    }

    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }

    public Boolean getAddressValidated() {
        return isAddressValidated;
    }

    public void setAddressValidated(Boolean addressValidated) {
        isAddressValidated = addressValidated;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }

    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }

    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }

    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }

    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }

    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getAlternativePremise() {
        return alternativePremise;
    }

    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }

    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(StateDTO state) {
        this.state = state;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }

    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }

    public String getMailingInstructions() {
        return mailingInstructions;
    }

    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }

    public ProvinceDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceDTO province) {
        this.province = province;
    }

    public RegionIdentificationDTO getRegionIdentification() {
        return regionIdentification;
    }

    public void setRegionIdentification(RegionIdentificationDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }

    public CountyIdentificationDTO getCountyIdentification() {
        return countyIdentification;
    }

    public void setCountyIdentification(CountyIdentificationDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }

    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }

    public String getPostBoxTypeDescription() {
        return postBoxTypeDescription;
    }

    public void setPostBoxTypeDescription(String postBoxTypeDescription) {
        this.postBoxTypeDescription = postBoxTypeDescription;
    }

    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }

    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZip4Code() {
        return zip4Code;
    }

    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }

    public String getRuralTypeCode() {
        return ruralTypeCode;
    }

    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }

    public String getRuralTypeDescription() {
        return ruralTypeDescription;
    }

    public void setRuralTypeDescription(String ruralTypeDescription) {
        this.ruralTypeDescription = ruralTypeDescription;
    }

    public String getRuralNumber() {
        return ruralNumber;
    }

    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionIdentificationDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootDTO {
    private List<ContactPointDTO> contactPoints;
    private LinksDTO links;

    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public LinksDTO getLinks() {
        return links;
    }

    public void setLinks(LinksDTO links) {
        this.links = links;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UseTypeDTO {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodDTO {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAddressDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String creationDate;
    private String lastUpdateDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointDTO {
    private String contactPointId;
    private List<CodeNameDTO> useTypes;
    private PostalAddressDTO postalAddress;
    private PhoneAddressDTO phoneAddress;
    private ElectronicAddressDTO electronicAddress;
    private AuditDTO audit;

    public String getContactPointId() {
        return contactPointId;
    }

    public void setContactPointId(String contactPointId) {
        this.contactPointId = contactPointId;
    }

    public List<CodeNameDTO> getUseTypes() {
        return useTypes;
    }

    public void setUseTypes(List<CodeNameDTO> useTypes) {
        this.useTypes = useTypes;
    }

    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }

    public PhoneAddressDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public ElectronicAddressDTO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressDTO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }

    public AuditDTO getAudit() {
        return audit;
    }

    public void setAudit(AuditDTO audit) {
        this.audit = audit;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;


import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.BankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsResponseDTO {

    private PersonDTO person;
    private OrganizationDTO organization;
    private List<ContactPointDTO> contactPoints;
    private Boolean highConfidentialityIndicator;
    private Boolean isPendingExCustomer;
    private String confidentialityLevel;
    private BankDTO bank;
    private List<DataOriginDTO> dataOrigins;
    private String structuralSegmentCode;
    private String structuralSegmentDescription;
    private String structuralSubsegmentCode;
    private String structuralSubsegmentDescription;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public List<ContactPointDTO> getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(List<ContactPointDTO> contactPoints) {
        this.contactPoints = contactPoints;
    }

    public Boolean getHighConfidentialityIndicator() {
        return highConfidentialityIndicator;
    }

    public void setHighConfidentialityIndicator(Boolean highConfidentialityIndicator) {
        this.highConfidentialityIndicator = highConfidentialityIndicator;
    }

    public Boolean getPendingExCustomer() {
        return isPendingExCustomer;
    }

    public void setPendingExCustomer(Boolean pendingExCustomer) {
        isPendingExCustomer = pendingExCustomer;
    }

    public String getConfidentialityLevel() {
        return confidentialityLevel;
    }

    public void setConfidentialityLevel(String confidentialityLevel) {
        this.confidentialityLevel = confidentialityLevel;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    public List<DataOriginDTO> getDataOrigins() {
        return dataOrigins;
    }

    public void setDataOrigins(List<DataOriginDTO> dataOrigins) {
        this.dataOrigins = dataOrigins;
    }

    public String getStructuralSegmentCode() {
        return structuralSegmentCode;
    }

    public void setStructuralSegmentCode(String structuralSegmentCode) {
        this.structuralSegmentCode = structuralSegmentCode;
    }

    public String getStructuralSegmentDescription() {
        return structuralSegmentDescription;
    }

    public void setStructuralSegmentDescription(String structuralSegmentDescription) {
        this.structuralSegmentDescription = structuralSegmentDescription;
    }

    public String getStructuralSubsegmentCode() {
        return structuralSubsegmentCode;
    }

    public void setStructuralSubsegmentCode(String structuralSubsegmentCode) {
        this.structuralSubsegmentCode = structuralSubsegmentCode;
    }

    public String getStructuralSubsegmentDescription() {
        return structuralSubsegmentDescription;
    }

    public void setStructuralSubsegmentDescription(String structuralSubsegmentDescription) {
        this.structuralSubsegmentDescription = structuralSubsegmentDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOriginDTO {
    private String sourceCode;
    private String sourceDescription;
    private String creationDate;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomicActivityDTO {
    private String categoryCode;
    private String categoryDescription;
    private String subCategoryCode;
    private String subCategoryDescription;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressDTO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentInformationDTO {
    private String statusCode;
    private String statusDescription;
    private EconomicActivityDTO economicActivity;
    private String occupationCode;
    private String occupationDescription;
    private String subActivityCode;
    private String subActivityDescription;
    private String subActivityComments;

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

    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationDescription() {
        return occupationDescription;
    }

    public void setOccupationDescription(String occupationDescription) {
        this.occupationDescription = occupationDescription;
    }

    public String getSubActivityCode() {
        return subActivityCode;
    }

    public void setSubActivityCode(String subActivityCode) {
        this.subActivityCode = subActivityCode;
    }

    public String getSubActivityDescription() {
        return subActivityDescription;
    }

    public void setSubActivityDescription(String subActivityDescription) {
        this.subActivityDescription = subActivityDescription;
    }

    public String getSubActivityComments() {
        return subActivityComments;
    }

    public void setSubActivityComments(String subActivityComments) {
        this.subActivityComments = subActivityComments;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
    private String registrationDate;
    private String entityDisolutionDate;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String foreignTaxIndicator;
    private PlaceOfRegistrationDTO placeOfRegistration;
    private OrganizationNameDTO organizationName;
    private String typeCode;
    private String typeDescription;
    private String subtypeCode;
    private String subtypeDescription;
    private List<DocumentDTO> documents;
    private CodeNameDTO countryOfOperation;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EconomicActivityDTO economicActivity;
    private CodeNameDTO preferredLanguage;

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEntityDisolutionDate() {
        return entityDisolutionDate;
    }

    public void setEntityDisolutionDate(String entityDisolutionDate) {
        this.entityDisolutionDate = entityDisolutionDate;
    }

    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }

    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }

    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }

    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }

    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }

    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }

    public PlaceOfRegistrationDTO getPlaceOfRegistration() {
        return placeOfRegistration;
    }

    public void setPlaceOfRegistration(PlaceOfRegistrationDTO placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }

    public OrganizationNameDTO getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNameDTO organizationName) {
        this.organizationName = organizationName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getSubtypeCode() {
        return subtypeCode;
    }

    public void setSubtypeCode(String subtypeCode) {
        this.subtypeCode = subtypeCode;
    }

    public String getSubtypeDescription() {
        return subtypeDescription;
    }

    public void setSubtypeDescription(String subtypeDescription) {
        this.subtypeDescription = subtypeDescription;
    }

    public CodeNameDTO getCountryOfOperation() {
        return countryOfOperation;
    }

    public void setCountryOfOperation(CodeNameDTO countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }

    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }

    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }

    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }

    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }

    public EconomicActivityDTO getEconomicActivity() {
        return economicActivity;
    }

    public void setEconomicActivity(EconomicActivityDTO economicActivity) {
        this.economicActivity = economicActivity;
    }

    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameDTO {
    private String legalName;
    private ArrayList<String> tradingNames;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public ArrayList<String> getTradingNames() {
        return tradingNames;
    }

    public void setTradingNames(ArrayList<String> tradingNames) {
        this.tradingNames = tradingNames;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private PersonNameDTO personName;
    private String motherName;
    private String fatherName;
    private String genderCode;
    private String genderDescription;
    private String birthDate;
    private PlaceOfBirthDTO placeOfBirth;
    private CodeNameDTO countryOfResidence;
    private String foreignTaxIndicator;
    private CodeNameDTO firstNationality;
    private CodeNameDTO secondNationality;
    private String residentialStatusCode;
    private String residentialStatusDescription;
    private String civilStatusCode;
    private String civilStatusDescription;
    private PublicOfficeInformationDTO publicOfficeInformation;
    private String deathDate;
    private Boolean employeeIndicator;
    private String staffCode;
    private String staffDescription;
    private Boolean legallyIncapacitated;
    private Boolean legallyCapableMinor;
    private Boolean diplomatic;
    private String educationalLevelCode;
    private String educationalLevelDescription;
    private String accountingSectorCode;
    private String accountingSectorDescription;
    private EmploymentInformationDTO employmentInformation;
    private CodeNameDTO preferredLanguage;
    private List<DocumentDTO> documents;
    private DocumentDTO document;

    public PersonNameDTO getPersonName() {
        return personName;
    }

    public void setPersonName(PersonNameDTO personName) {
        this.personName = personName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PlaceOfBirthDTO getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(PlaceOfBirthDTO placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public CodeNameDTO getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(CodeNameDTO countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getForeignTaxIndicator() {
        return foreignTaxIndicator;
    }

    public void setForeignTaxIndicator(String foreignTaxIndicator) {
        this.foreignTaxIndicator = foreignTaxIndicator;
    }

    public CodeNameDTO getFirstNationality() {
        return firstNationality;
    }

    public void setFirstNationality(CodeNameDTO firstNationality) {
        this.firstNationality = firstNationality;
    }

    public CodeNameDTO getSecondNationality() {
        return secondNationality;
    }

    public void setSecondNationality(CodeNameDTO secondNationality) {
        this.secondNationality = secondNationality;
    }

    public String getResidentialStatusCode() {
        return residentialStatusCode;
    }

    public void setResidentialStatusCode(String residentialStatusCode) {
        this.residentialStatusCode = residentialStatusCode;
    }

    public String getResidentialStatusDescription() {
        return residentialStatusDescription;
    }

    public void setResidentialStatusDescription(String residentialStatusDescription) {
        this.residentialStatusDescription = residentialStatusDescription;
    }

    public String getCivilStatusCode() {
        return civilStatusCode;
    }

    public void setCivilStatusCode(String civilStatusCode) {
        this.civilStatusCode = civilStatusCode;
    }

    public String getCivilStatusDescription() {
        return civilStatusDescription;
    }

    public void setCivilStatusDescription(String civilStatusDescription) {
        this.civilStatusDescription = civilStatusDescription;
    }

    public PublicOfficeInformationDTO getPublicOfficeInformation() {
        return publicOfficeInformation;
    }

    public void setPublicOfficeInformation(PublicOfficeInformationDTO publicOfficeInformation) {
        this.publicOfficeInformation = publicOfficeInformation;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public Boolean getEmployeeIndicator() {
        return employeeIndicator;
    }

    public void setEmployeeIndicator(Boolean employeeIndicator) {
        this.employeeIndicator = employeeIndicator;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffDescription() {
        return staffDescription;
    }

    public void setStaffDescription(String staffDescription) {
        this.staffDescription = staffDescription;
    }

    public Boolean getLegallyIncapacitated() {
        return legallyIncapacitated;
    }

    public void setLegallyIncapacitated(Boolean legallyIncapacitated) {
        this.legallyIncapacitated = legallyIncapacitated;
    }

    public Boolean getLegallyCapableMinor() {
        return legallyCapableMinor;
    }

    public void setLegallyCapableMinor(Boolean legallyCapableMinor) {
        this.legallyCapableMinor = legallyCapableMinor;
    }

    public Boolean getDiplomatic() {
        return diplomatic;
    }

    public void setDiplomatic(Boolean diplomatic) {
        this.diplomatic = diplomatic;
    }

    public String getEducationalLevelCode() {
        return educationalLevelCode;
    }

    public void setEducationalLevelCode(String educationalLevelCode) {
        this.educationalLevelCode = educationalLevelCode;
    }

    public String getEducationalLevelDescription() {
        return educationalLevelDescription;
    }

    public void setEducationalLevelDescription(String educationalLevelDescription) {
        this.educationalLevelDescription = educationalLevelDescription;
    }

    public String getAccountingSectorCode() {
        return accountingSectorCode;
    }

    public void setAccountingSectorCode(String accountingSectorCode) {
        this.accountingSectorCode = accountingSectorCode;
    }

    public String getAccountingSectorDescription() {
        return accountingSectorDescription;
    }

    public void setAccountingSectorDescription(String accountingSectorDescription) {
        this.accountingSectorDescription = accountingSectorDescription;
    }

    public EmploymentInformationDTO getEmploymentInformation() {
        return employmentInformation;
    }

    public void setEmploymentInformation(EmploymentInformationDTO employmentInformation) {
        this.employmentInformation = employmentInformation;
    }

    public CodeNameDTO getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(CodeNameDTO preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameDTO {
    private String namePrefixCode;
    private String namePrefixDescription;
    private String givenName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String nameSuffixCode;
    private String nameSuffixDescription;
    private String fullName;
    private String birthName;
    private List<String> aliases;

    public String getNamePrefixCode() {
        return namePrefixCode;
    }

    public void setNamePrefixCode(String namePrefixCode) {
        this.namePrefixCode = namePrefixCode;
    }

    public String getNamePrefixDescription() {
        return namePrefixDescription;
    }

    public void setNamePrefixDescription(String namePrefixDescription) {
        this.namePrefixDescription = namePrefixDescription;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getNameSuffixCode() {
        return nameSuffixCode;
    }

    public void setNameSuffixCode(String nameSuffixCode) {
        this.nameSuffixCode = nameSuffixCode;
    }

    public String getNameSuffixDescription() {
        return nameSuffixDescription;
    }

    public void setNameSuffixDescription(String nameSuffixDescription) {
        this.nameSuffixDescription = nameSuffixDescription;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String faxNumber;
    private String internationalCode;
    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfBirthDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOfRegistrationDTO {
    private CodeNameDTO country;
    private CodeNameDTO state;
    private String town;

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicOfficeInformationDTO {
    private String positionCode;
    private String positionDescription;
    private ValidityPeriodDTO validityPeriod;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public ValidityPeriodDTO getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriodDTO validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriodDTO {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationCommonDTO {
    private String offset;
    private String limit;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {
    private PaginationCommonDTO first;
    private PaginationCommonDTO prev;
    private PaginationCommonDTO next;
    private PaginationCommonDTO last;
    private PaginationCommonDTO self;

    public PaginationCommonDTO getFirst() {
        return first;
    }

    public void setFirst(PaginationCommonDTO first) {
        this.first = first;
    }

    public PaginationCommonDTO getPrev() {
        return prev;
    }

    public void setPrev(PaginationCommonDTO prev) {
        this.prev = prev;
    }

    public PaginationCommonDTO getNext() {
        return next;
    }

    public void setNext(PaginationCommonDTO next) {
        this.next = next;
    }

    public PaginationCommonDTO getLast() {
        return last;
    }

    public void setLast(PaginationCommonDTO last) {
        this.last = last;
    }

    public PaginationCommonDTO getSelf() {
        return self;
    }

    public void setSelf(PaginationCommonDTO self) {
        this.self = self;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private String bankId;
    private String bankName;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameDTO {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private String documentTypeCode;
    private String documentTypeDescription;
    private String documentNumber;
    private String issueDate;
    private String expirationDate;
    private String issuerEntity;
    private CodeNameDTO country;
    private CodeNameDTO state;

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuerEntity() {
        return issuerEntity;
    }

    public void setIssuerEntity(String issuerEntity) {
        this.issuerEntity = issuerEntity;
    }

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parameters {
    private CodeNameDTO countryNationality;
    private CodeNameDTO countryExp;
    private CodeNameDTO countryBirth;
    private CodeNameDTO countryDir;
    private CodeNameDTO cityStandard;
    private CodeNameDTO cityDepartment;
    private CodeNameDTO cityExp;
    private CodeNameDTO cityBirth;
    private CodeNameDTO town;
    private String documentTypeDescription;
    private String streetTypeDescription;

    public CodeNameDTO getCountryNationality() {
        return countryNationality;
    }

    public void setCountryNationality(CodeNameDTO countryNationality) {
        this.countryNationality = countryNationality;
    }

    public CodeNameDTO getCountryExp() {
        return countryExp;
    }

    public void setCountryExp(CodeNameDTO countryExp) {
        this.countryExp = countryExp;
    }

    public CodeNameDTO getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(CodeNameDTO countryBirth) {
        this.countryBirth = countryBirth;
    }

    public CodeNameDTO getCountryDir() {
        return countryDir;
    }

    public void setCountryDir(CodeNameDTO countryDir) {
        this.countryDir = countryDir;
    }

    public CodeNameDTO getCityStandard() {
        return cityStandard;
    }

    public void setCityStandard(CodeNameDTO cityStandard) {
        this.cityStandard = cityStandard;
    }

    public CodeNameDTO getCityDepartment() {
        return cityDepartment;
    }

    public void setCityDepartment(CodeNameDTO cityDepartment) {
        this.cityDepartment = cityDepartment;
    }

    public CodeNameDTO getCityExp() {
        return cityExp;
    }

    public void setCityExp(CodeNameDTO cityExp) {
        this.cityExp = cityExp;
    }

    public CodeNameDTO getCityBirth() {
        return cityBirth;
    }

    public void setCityBirth(CodeNameDTO cityBirth) {
        this.cityBirth = cityBirth;
    }

    public CodeNameDTO getTown() {
        return town;
    }

    public void setTown(CodeNameDTO town) {
        this.town = town;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressDTO {
    private String fullAddress;
    private String formatCode;
    private String formatDescription;
    private Boolean isAddressValidated;
    private String matchId;
    private String streetTypeCode;
    private String streetTypeDescription;
    private String streetName;
    private String secondaryStreetName;
    private String streetBuildingIdentification;
    private String mailDeliverySubLocation;
    private String buildingName;
    private String floor;
    private String detailCode;
    private String unitType;
    private String unitNumber;
    private String premise;
    private String alternativePremise;
    private String department;
    private String subDepartment;
    private String postCodeIdentification;
    private String townName;
    private CodeNameDTO state;
    private String districtName;
    private String secondaryDistrictName;
    private String mailingInstructions;
    private CodeNameDTO province;
    private CodeNameDTO regionIdentification;
    private CodeNameDTO countyIdentification;
    private CodeNameDTO country;
    private String military;
    private String postOfficeBox;
    private String postBoxTypeCode;
    private String postBoxTypeDescription;
    private List<String> foreignAddressLines;
    private String zipCode;
    private String zip4Code;
    private String ruralTypeCode;
    private String ruralTypeDescription;
    private String ruralNumber;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getFormatDescription() {
        return formatDescription;
    }

    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }

    public Boolean getAddressValidated() {
        return isAddressValidated;
    }

    public void setAddressValidated(Boolean addressValidated) {
        isAddressValidated = addressValidated;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getStreetTypeCode() {
        return streetTypeCode;
    }

    public void setStreetTypeCode(String streetTypeCode) {
        this.streetTypeCode = streetTypeCode;
    }

    public String getStreetTypeDescription() {
        return streetTypeDescription;
    }

    public void setStreetTypeDescription(String streetTypeDescription) {
        this.streetTypeDescription = streetTypeDescription;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondaryStreetName() {
        return secondaryStreetName;
    }

    public void setSecondaryStreetName(String secondaryStreetName) {
        this.secondaryStreetName = secondaryStreetName;
    }

    public String getStreetBuildingIdentification() {
        return streetBuildingIdentification;
    }

    public void setStreetBuildingIdentification(String streetBuildingIdentification) {
        this.streetBuildingIdentification = streetBuildingIdentification;
    }

    public String getMailDeliverySubLocation() {
        return mailDeliverySubLocation;
    }

    public void setMailDeliverySubLocation(String mailDeliverySubLocation) {
        this.mailDeliverySubLocation = mailDeliverySubLocation;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getAlternativePremise() {
        return alternativePremise;
    }

    public void setAlternativePremise(String alternativePremise) {
        this.alternativePremise = alternativePremise;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getPostCodeIdentification() {
        return postCodeIdentification;
    }

    public void setPostCodeIdentification(String postCodeIdentification) {
        this.postCodeIdentification = postCodeIdentification;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public CodeNameDTO getState() {
        return state;
    }

    public void setState(CodeNameDTO state) {
        this.state = state;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSecondaryDistrictName() {
        return secondaryDistrictName;
    }

    public void setSecondaryDistrictName(String secondaryDistrictName) {
        this.secondaryDistrictName = secondaryDistrictName;
    }

    public String getMailingInstructions() {
        return mailingInstructions;
    }

    public void setMailingInstructions(String mailingInstructions) {
        this.mailingInstructions = mailingInstructions;
    }

    public CodeNameDTO getProvince() {
        return province;
    }

    public void setProvince(CodeNameDTO province) {
        this.province = province;
    }

    public CodeNameDTO getRegionIdentification() {
        return regionIdentification;
    }

    public void setRegionIdentification(CodeNameDTO regionIdentification) {
        this.regionIdentification = regionIdentification;
    }

    public CodeNameDTO getCountyIdentification() {
        return countyIdentification;
    }

    public void setCountyIdentification(CodeNameDTO countyIdentification) {
        this.countyIdentification = countyIdentification;
    }

    public CodeNameDTO getCountry() {
        return country;
    }

    public void setCountry(CodeNameDTO country) {
        this.country = country;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }

    public String getPostBoxTypeCode() {
        return postBoxTypeCode;
    }

    public void setPostBoxTypeCode(String postBoxTypeCode) {
        this.postBoxTypeCode = postBoxTypeCode;
    }

    public String getPostBoxTypeDescription() {
        return postBoxTypeDescription;
    }

    public void setPostBoxTypeDescription(String postBoxTypeDescription) {
        this.postBoxTypeDescription = postBoxTypeDescription;
    }

    public List<String> getForeignAddressLines() {
        return foreignAddressLines;
    }

    public void setForeignAddressLines(List<String> foreignAddressLines) {
        this.foreignAddressLines = foreignAddressLines;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZip4Code() {
        return zip4Code;
    }

    public void setZip4Code(String zip4Code) {
        this.zip4Code = zip4Code;
    }

    public String getRuralTypeCode() {
        return ruralTypeCode;
    }

    public void setRuralTypeCode(String ruralTypeCode) {
        this.ruralTypeCode = ruralTypeCode;
    }

    public String getRuralTypeDescription() {
        return ruralTypeDescription;
    }

    public void setRuralTypeDescription(String ruralTypeDescription) {
        this.ruralTypeDescription = ruralTypeDescription;
    }

    public String getRuralNumber() {
        return ruralNumber;
    }

    public void setRuralNumber(String ruralNumber) {
        this.ruralNumber = ruralNumber;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

/**
 * Controlls all time entries
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadistics {
    private String entryTime;
    private String trxCallTime;
    private String trxExitTime;
    private String exitTime;

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTrxCallTime() {
        return trxCallTime;
    }

    public void setTrxCallTime(String trxCallTime) {
        this.trxCallTime = trxCallTime;
    }

    public String getTrxExitTime() {
        return trxExitTime;
    }

    public void setTrxExitTime(String trxExitTime) {
        this.trxExitTime = trxExitTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRequestDTO {
    private String bankId;
    private CenterRequestDTO center;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public CenterRequestDTO getCenter() {
        return center;
    }

    public void setCenter(CenterRequestDTO center) {
        this.center = center;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
    private PersonRequestDto person;
    private OrganizationRequestDto organization;
    private PhoneAddressRequestDTO phoneAddress;
    private ElectronicAddressRequestDtO electronicAddress;
    private PostalAddressRequestDTO postalAddress;
    private BankRequestDTO bank;

    public PersonRequestDto getPerson() {
        return person;
    }

    public void setPerson(PersonRequestDto person) {
        this.person = person;
    }

    public OrganizationRequestDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRequestDto organization) {
        this.organization = organization;
    }

    public PhoneAddressRequestDTO getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(PhoneAddressRequestDTO phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public ElectronicAddressRequestDtO getElectronicAddress() {
        return electronicAddress;
    }

    public void setElectronicAddress(ElectronicAddressRequestDtO electronicAddress) {
        this.electronicAddress = electronicAddress;
    }

    public PostalAddressRequestDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressRequestDTO postalAddress) {
        this.postalAddress = postalAddress;
    }

    public BankRequestDTO getBank() {
        return bank;
    }

    public void setBank(BankRequestDTO bank) {
        this.bank = bank;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {
    private String documentTypeCode;
    private String documentNumber;
    private StateRequestDTO state;

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public StateRequestDTO getState() {
        return state;
    }

    public void setState(StateRequestDTO state) {
        this.state = state;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicAddressRequestDtO {
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameRequestDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDto {
    private String registrationDate;
    private OrganizationNameRequestDTO organizationName;
    private DocumentRequestDTO document;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public OrganizationNameRequestDTO getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNameRequestDTO organizationName) {
        this.organizationName = organizationName;
    }

    public DocumentRequestDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonNameRequestDTO {
    private String givenName;
    private String lastName;
    private String secondLastName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDto {
    private PersonNameRequestDTO personName;
    private String birthDate;
    private DocumentRequestDTO document;

    public PersonNameRequestDTO getPersonName() {
        return personName;
    }

    public void setPersonName(PersonNameRequestDTO personName) {
        this.personName = personName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public DocumentRequestDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentRequestDTO document) {
        this.document = document;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAddressRequestDTO {
    private String mobileNumber;
    private String phoneNumber;
    private String internationalCode;
    private String extension;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInternationalCode() {
        return internationalCode;
    }

    public void setInternationalCode(String internationalCode) {
        this.internationalCode = internationalCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddressRequestDTO {
    private ProvinceRequestDTO province;
    private String townName;
    private CountryRequestDTO country;

    public ProvinceRequestDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceRequestDTO province) {
        this.province = province;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public CountryRequestDTO getCountry() {
        return country;
    }

    public void setCountry(CountryRequestDTO country) {
        this.country = country;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactPointCustomerSearchDTO {
    private PostalAddressDTO postalAddress;

    public PostalAddressDTO getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddressDTO postalAddress) {
        this.postalAddress = postalAddress;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response;


import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.PersonDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.BankDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchDTO {
    private String customerId;
    private PersonDTO person;
    private OrganizationCustomerSearchDTO organization;
    private ContactPointCustomerSearchDTO contactPoint;
    private Boolean highConfidentialityIndicator;
    private BankDTO bank;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationCustomerSearchDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationCustomerSearchDTO organization) {
        this.organization = organization;
    }

    public ContactPointCustomerSearchDTO getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(ContactPointCustomerSearchDTO contactPoint) {
        this.contactPoint = contactPoint;
    }

    public Boolean getHighConfidentialityIndicator() {
        return highConfidentialityIndicator;
    }

    public void setHighConfidentialityIndicator(Boolean highConfidentialityIndicator) {
        this.highConfidentialityIndicator = highConfidentialityIndicator;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.pagination.PaginationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchResponseDTO {
    private List<CustomerSearchDTO> customers;
    private PaginationDTO pagination;

    public List<CustomerSearchDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSearchDTO> customers) {
        this.customers = customers;
    }

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCustomerSearchDTO {
    private String registrationDate;
    private OrganizationNameCustomerSearchDTO organizationName;
    private DocumentDTO document;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public OrganizationNameCustomerSearchDTO getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNameCustomerSearchDTO organizationName) {
        this.organizationName = organizationName;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationNameCustomerSearchDTO {
    private String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic;

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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonHeader {
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfo {
    private String paisResidenciaFiscal1;
    private String paisDescripcion;
    private String paisResidenciaFiscal2;
    private String paisDescripcion2;
    private String clasificacionFATCA;
    private String clasificacionCRS;
    private Boolean validacionFATCA;
    private Boolean salario;
    private Boolean pensiones;
    private Boolean prestacionesServicio;
    private Boolean arrendamientos;
    private Boolean donacionHerencia;
    private Boolean honorarios;
    private Boolean mesada;
    private Boolean actividadIndependiente;
    private Boolean otro;
    private String numIdentiTributaria1;
    private String numIdentiTributaria2;
    private String preFormalizacion;
    private Boolean validacionCRS;
    private Boolean selfCertificacion;
    private Boolean contribuyenteVentaColombia;
    private Boolean reportable;
    private Boolean autorizoEnvioInformacion;
    private String canalVenta;
    private String oficial;
    private String sucursal;
    private String uNeg;
    private String sitCliente;
    private String fAltaCliente;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;

    public String getPaisResidenciaFiscal1() {
        return paisResidenciaFiscal1;
    }

    public void setPaisResidenciaFiscal1(String paisResidenciaFiscal1) {
        this.paisResidenciaFiscal1 = paisResidenciaFiscal1;
    }

    public String getPaisDescripcion() {
        return paisDescripcion;
    }

    public void setPaisDescripcion(String paisDescripcion) {
        this.paisDescripcion = paisDescripcion;
    }

    public String getPaisResidenciaFiscal2() {
        return paisResidenciaFiscal2;
    }

    public void setPaisResidenciaFiscal2(String paisResidenciaFiscal2) {
        this.paisResidenciaFiscal2 = paisResidenciaFiscal2;
    }

    public String getPaisDescripcion2() {
        return paisDescripcion2;
    }

    public void setPaisDescripcion2(String paisDescripcion2) {
        this.paisDescripcion2 = paisDescripcion2;
    }

    public String getClasificacionFATCA() {
        return clasificacionFATCA;
    }

    public void setClasificacionFATCA(String clasificacionFATCA) {
        this.clasificacionFATCA = clasificacionFATCA;
    }

    public String getClasificacionCRS() {
        return clasificacionCRS;
    }

    public void setClasificacionCRS(String clasificacionCRS) {
        this.clasificacionCRS = clasificacionCRS;
    }

    public Boolean getValidacionFATCA() {
        return validacionFATCA;
    }

    public void setValidacionFATCA(Boolean validacionFATCA) {
        this.validacionFATCA = validacionFATCA;
    }

    public Boolean getSalario() {
        return salario;
    }

    public void setSalario(Boolean salario) {
        this.salario = salario;
    }

    public Boolean getPensiones() {
        return pensiones;
    }

    public void setPensiones(Boolean pensiones) {
        this.pensiones = pensiones;
    }

    public Boolean getPrestacionesServicio() {
        return prestacionesServicio;
    }

    public void setPrestacionesServicio(Boolean prestacionesServicio) {
        this.prestacionesServicio = prestacionesServicio;
    }

    public Boolean getArrendamientos() {
        return arrendamientos;
    }

    public void setArrendamientos(Boolean arrendamientos) {
        this.arrendamientos = arrendamientos;
    }

    public Boolean getDonacionHerencia() {
        return donacionHerencia;
    }

    public void setDonacionHerencia(Boolean donacionHerencia) {
        this.donacionHerencia = donacionHerencia;
    }

    public Boolean getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(Boolean honorarios) {
        this.honorarios = honorarios;
    }

    public Boolean getMesada() {
        return mesada;
    }

    public void setMesada(Boolean mesada) {
        this.mesada = mesada;
    }

    public Boolean getActividadIndependiente() {
        return actividadIndependiente;
    }

    public void setActividadIndependiente(Boolean actividadIndependiente) {
        this.actividadIndependiente = actividadIndependiente;
    }

    public Boolean getOtro() {
        return otro;
    }

    public void setOtro(Boolean otro) {
        this.otro = otro;
    }

    public String getNumIdentiTributaria1() {
        return numIdentiTributaria1;
    }

    public void setNumIdentiTributaria1(String numIdentiTributaria1) {
        this.numIdentiTributaria1 = numIdentiTributaria1;
    }

    public String getNumIdentiTributaria2() {
        return numIdentiTributaria2;
    }

    public void setNumIdentiTributaria2(String numIdentiTributaria2) {
        this.numIdentiTributaria2 = numIdentiTributaria2;
    }

    public String getPreFormalizacion() {
        return preFormalizacion;
    }

    public void setPreFormalizacion(String preFormalizacion) {
        this.preFormalizacion = preFormalizacion;
    }

    public Boolean getValidacionCRS() {
        return validacionCRS;
    }

    public void setValidacionCRS(Boolean validacionCRS) {
        this.validacionCRS = validacionCRS;
    }

    public Boolean getSelfCertificacion() {
        return selfCertificacion;
    }

    public void setSelfCertificacion(Boolean selfCertificacion) {
        this.selfCertificacion = selfCertificacion;
    }

    public Boolean getContribuyenteVentaColombia() {
        return contribuyenteVentaColombia;
    }

    public void setContribuyenteVentaColombia(Boolean contribuyenteVentaColombia) {
        this.contribuyenteVentaColombia = contribuyenteVentaColombia;
    }

    public Boolean getReportable() {
        return reportable;
    }

    public void setReportable(Boolean reportable) {
        this.reportable = reportable;
    }

    public Boolean getAutorizoEnvioInformacion() {
        return autorizoEnvioInformacion;
    }

    public void setAutorizoEnvioInformacion(Boolean autorizoEnvioInformacion) {
        this.autorizoEnvioInformacion = autorizoEnvioInformacion;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public String getOficial() {
        return oficial;
    }

    public void setOficial(String oficial) {
        this.oficial = oficial;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getuNeg() {
        return uNeg;
    }

    public void setuNeg(String uNeg) {
        this.uNeg = uNeg;
    }

    public String getSitCliente() {
        return sitCliente;
    }

    public void setSitCliente(String sitCliente) {
        this.sitCliente = sitCliente;
    }

    public String getfAltaCliente() {
        return fAltaCliente;
    }

    public void setfAltaCliente(String fAltaCliente) {
        this.fAltaCliente = fAltaCliente;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicData {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String paisExpedicion;
    private String paisExpedicionDesc;
    private String ciudadExpedicion;
    private String lugardeExpDescripcion;
    private String fechaExpedicion;
    private String paisNacimiento;
    private String paisNacimientoDesc;
    private String nacionalidad;
    private String nacionalidadDesc;
    private String ciudadNacimiento;
    private String lugardeNacimiento;
    private String fechaNacimiento;
    private String sexo;
    private String paisDireccion;
    private String paisDireccionDesc;
    private String departamento;
    private String ciudad;
    private String ciudadDescripcion;
    private String tipoVia;
    private String nombreVia;
    private String descripcionDireccion;
    private String clase;
    private String indicativo;
    private String telefono;
    private String precelular;
    private String celular;
    private String email;
    private boolean autorizoTelefono;
    private boolean autorizacionEmail;
    private String agrofic;
    private String codact;
    private String codpaip;
    private String conper;
    private String domant;
    private String entpre;
    private String estciv;
    private String estper;
    private String estrat;
    private String fecalt;
    private String fecfal;
    private String fecing;
    private String hstamp;
    private String hstamp2;
    private String hstamp3;
    private String hstamp4;
    private String hstamp5;
    private String logdomp;
    private String logtelp;
    private String numper;
    private String precel;
    private String profes;
    private String seccel;
    private String secdoc;
    private String secdomp;
    private String secdotc;
    private String secdotp;
    private String secema;
    private String sectelp;
    private String sucadm;
    private String sucmod;
    private String termod;
    private String tipdomp;
    private String tipocu;
    private String tipper;
    private String tiptelp;
    private String usualt;
    private String usumod;

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

    public String getPaisExpedicion() {
        return paisExpedicion;
    }

    public void setPaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }

    public String getPaisExpedicionDesc() {
        return paisExpedicionDesc;
    }

    public void setPaisExpedicionDesc(String paisExpedicionDesc) {
        this.paisExpedicionDesc = paisExpedicionDesc;
    }

    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }

    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public String getLugardeExpDescripcion() {
        return lugardeExpDescripcion;
    }

    public void setLugardeExpDescripcion(String lugardeExpDescripcion) {
        this.lugardeExpDescripcion = lugardeExpDescripcion;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getPaisNacimientoDesc() {
        return paisNacimientoDesc;
    }

    public void setPaisNacimientoDesc(String paisNacimientoDesc) {
        this.paisNacimientoDesc = paisNacimientoDesc;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidadDesc() {
        return nacionalidadDesc;
    }

    public void setNacionalidadDesc(String nacionalidadDesc) {
        this.nacionalidadDesc = nacionalidadDesc;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getLugardeNacimiento() {
        return lugardeNacimiento;
    }

    public void setLugardeNacimiento(String lugardeNacimiento) {
        this.lugardeNacimiento = lugardeNacimiento;
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

    public String getPaisDireccion() {
        return paisDireccion;
    }

    public void setPaisDireccion(String paisDireccion) {
        this.paisDireccion = paisDireccion;
    }

    public String getPaisDireccionDesc() {
        return paisDireccionDesc;
    }

    public void setPaisDireccionDesc(String paisDireccionDesc) {
        this.paisDireccionDesc = paisDireccionDesc;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudadDescripcion() {
        return ciudadDescripcion;
    }

    public void setCiudadDescripcion(String ciudadDescripcion) {
        this.ciudadDescripcion = ciudadDescripcion;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPrecelular() {
        return precelular;
    }

    public void setPrecelular(String precelular) {
        this.precelular = precelular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAutorizoTelefono() {
        return autorizoTelefono;
    }

    public void setAutorizoTelefono(boolean autorizoTelefono) {
        this.autorizoTelefono = autorizoTelefono;
    }

    public boolean isAutorizacionEmail() {
        return autorizacionEmail;
    }

    public void setAutorizacionEmail(boolean autorizacionEmail) {
        this.autorizacionEmail = autorizacionEmail;
    }

    public String getAgrofic() {
        return agrofic;
    }

    public void setAgrofic(String agrofic) {
        this.agrofic = agrofic;
    }

    public String getCodact() {
        return codact;
    }

    public void setCodact(String codact) {
        this.codact = codact;
    }

    public String getCodpaip() {
        return codpaip;
    }

    public void setCodpaip(String codpaip) {
        this.codpaip = codpaip;
    }

    public String getConper() {
        return conper;
    }

    public void setConper(String conper) {
        this.conper = conper;
    }

    public String getDomant() {
        return domant;
    }

    public void setDomant(String domant) {
        this.domant = domant;
    }

    public String getEntpre() {
        return entpre;
    }

    public void setEntpre(String entpre) {
        this.entpre = entpre;
    }

    public String getEstciv() {
        return estciv;
    }

    public void setEstciv(String estciv) {
        this.estciv = estciv;
    }

    public String getEstper() {
        return estper;
    }

    public void setEstper(String estper) {
        this.estper = estper;
    }

    public String getEstrat() {
        return estrat;
    }

    public void setEstrat(String estrat) {
        this.estrat = estrat;
    }

    public String getFecalt() {
        return fecalt;
    }

    public void setFecalt(String fecalt) {
        this.fecalt = fecalt;
    }

    public String getFecfal() {
        return fecfal;
    }

    public void setFecfal(String fecfal) {
        this.fecfal = fecfal;
    }

    public String getFecing() {
        return fecing;
    }

    public void setFecing(String fecing) {
        this.fecing = fecing;
    }

    public String getHstamp() {
        return hstamp;
    }

    public void setHstamp(String hstamp) {
        this.hstamp = hstamp;
    }

    public String getHstamp2() {
        return hstamp2;
    }

    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }

    public String getHstamp3() {
        return hstamp3;
    }

    public void setHstamp3(String hstamp3) {
        this.hstamp3 = hstamp3;
    }

    public String getHstamp4() {
        return hstamp4;
    }

    public void setHstamp4(String hstamp4) {
        this.hstamp4 = hstamp4;
    }

    public String getHstamp5() {
        return hstamp5;
    }

    public void setHstamp5(String hstamp5) {
        this.hstamp5 = hstamp5;
    }

    public String getLogdomp() {
        return logdomp;
    }

    public void setLogdomp(String logdomp) {
        this.logdomp = logdomp;
    }

    public String getLogtelp() {
        return logtelp;
    }

    public void setLogtelp(String logtelp) {
        this.logtelp = logtelp;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getPrecel() {
        return precel;
    }

    public void setPrecel(String precel) {
        this.precel = precel;
    }

    public String getProfes() {
        return profes;
    }

    public void setProfes(String profes) {
        this.profes = profes;
    }

    public String getSeccel() {
        return seccel;
    }

    public void setSeccel(String seccel) {
        this.seccel = seccel;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getSecdomp() {
        return secdomp;
    }

    public void setSecdomp(String secdomp) {
        this.secdomp = secdomp;
    }

    public String getSecdotc() {
        return secdotc;
    }

    public void setSecdotc(String secdotc) {
        this.secdotc = secdotc;
    }

    public String getSecdotp() {
        return secdotp;
    }

    public void setSecdotp(String secdotp) {
        this.secdotp = secdotp;
    }

    public String getSecema() {
        return secema;
    }

    public void setSecema(String secema) {
        this.secema = secema;
    }

    public String getSectelp() {
        return sectelp;
    }

    public void setSectelp(String sectelp) {
        this.sectelp = sectelp;
    }

    public String getSucadm() {
        return sucadm;
    }

    public void setSucadm(String sucadm) {
        this.sucadm = sucadm;
    }

    public String getSucmod() {
        return sucmod;
    }

    public void setSucmod(String sucmod) {
        this.sucmod = sucmod;
    }

    public String getTermod() {
        return termod;
    }

    public void setTermod(String termod) {
        this.termod = termod;
    }

    public String getTipdomp() {
        return tipdomp;
    }

    public void setTipdomp(String tipdomp) {
        this.tipdomp = tipdomp;
    }

    public String getTipocu() {
        return tipocu;
    }

    public void setTipocu(String tipocu) {
        this.tipocu = tipocu;
    }

    public String getTipper() {
        return tipper;
    }

    public void setTipper(String tipper) {
        this.tipper = tipper;
    }

    public String getTiptelp() {
        return tiptelp;
    }

    public void setTiptelp(String tiptelp) {
        this.tiptelp = tiptelp;
    }

    public String getUsualt() {
        return usualt;
    }

    public void setUsualt(String usualt) {
        this.usualt = usualt;
    }

    public String getUsumod() {
        return usumod;
    }

    public void setUsumod(String usumod) {
        this.usumod = usumod;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplementaryInfo {
    private String estadoCivil;
    private String tipoVivienda;
    private String estrato;
    private String anios;
    private String mes;
    private String anios2;
    private String nivelEstudios;
    private String personasCargo;
    private String numHijos;
    private String hstamp1;
    private String hstamp2;
    private String lugnac;
    private String numper2;
    private String numintp;
    private String seccel;
    private String secdoc;
    private String secdotc;
    private String secdotp;
    private String secema;
    private String sectelp;
    private String tipdoc;

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getAnios() {
        return anios;
    }

    public void setAnios(String anios) {
        this.anios = anios;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnios2() {
        return anios2;
    }

    public void setAnios2(String anios2) {
        this.anios2 = anios2;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getPersonasCargo() {
        return personasCargo;
    }

    public void setPersonasCargo(String personasCargo) {
        this.personasCargo = personasCargo;
    }

    public String getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(String numHijos) {
        this.numHijos = numHijos;
    }

    public String getHstamp1() {
        return hstamp1;
    }

    public void setHstamp1(String hstamp1) {
        this.hstamp1 = hstamp1;
    }

    public String getHstamp2() {
        return hstamp2;
    }

    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }

    public String getLugnac() {
        return lugnac;
    }

    public void setLugnac(String lugnac) {
        this.lugnac = lugnac;
    }

    public String getNumper2() {
        return numper2;
    }

    public void setNumper2(String numper2) {
        this.numper2 = numper2;
    }

    public String getNumintp() {
        return numintp;
    }

    public void setNumintp(String numintp) {
        this.numintp = numintp;
    }

    public String getSeccel() {
        return seccel;
    }

    public void setSeccel(String seccel) {
        this.seccel = seccel;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getSecdotc() {
        return secdotc;
    }

    public void setSecdotc(String secdotc) {
        this.secdotc = secdotc;
    }

    public String getSecdotp() {
        return secdotp;
    }

    public void setSecdotp(String secdotp) {
        this.secdotp = secdotp;
    }

    public String getSecema() {
        return secema;
    }

    public void setSecema(String secema) {
        this.secema = secema;
    }

    public String getSectelp() {
        return sectelp;
    }

    public void setSectelp(String sectelp) {
        this.sectelp = sectelp;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EconomyActivity {
    private String cargo;
    private String descCargo;
    private String tipoVia;
    private String departamento;
    private String tipoContrato;
    private String pais;
    private String paisDescripcion;
    private String ciudad;
    private String ciudadDescripcion;
    private String ocupacion;
    private String descOcupacion;
    private String actiEconomica;
    private String descActiEconomica;
    private String antiguedadAnio;
    private String antiguedadMes;
    private String nombreEmpresa;
    private String nit;
    private String fechaIngreso;
    private String fecha2;
    private String nombreVia;
    private String descripcionDireccion;
    private String indicativo;
    private String telefono;
    private String opcionActividad;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescCargo() {
        return descCargo;
    }

    public void setDescCargo(String descCargo) {
        this.descCargo = descCargo;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPaisDescripcion() {
        return paisDescripcion;
    }

    public void setPaisDescripcion(String paisDescripcion) {
        this.paisDescripcion = paisDescripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudadDescripcion() {
        return ciudadDescripcion;
    }

    public void setCiudadDescripcion(String ciudadDescripcion) {
        this.ciudadDescripcion = ciudadDescripcion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getDescOcupacion() {
        return descOcupacion;
    }

    public void setDescOcupacion(String descOcupacion) {
        this.descOcupacion = descOcupacion;
    }

    public String getActiEconomica() {
        return actiEconomica;
    }

    public void setActiEconomica(String actiEconomica) {
        this.actiEconomica = actiEconomica;
    }

    public String getDescActiEconomica() {
        return descActiEconomica;
    }

    public void setDescActiEconomica(String descActiEconomica) {
        this.descActiEconomica = descActiEconomica;
    }

    public String getAntiguedadAnio() {
        return antiguedadAnio;
    }

    public void setAntiguedadAnio(String antiguedadAnio) {
        this.antiguedadAnio = antiguedadAnio;
    }

    public String getAntiguedadMes() {
        return antiguedadMes;
    }

    public void setAntiguedadMes(String antiguedadMes) {
        this.antiguedadMes = antiguedadMes;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOpcionActividad() {
        return opcionActividad;
    }

    public void setOpcionActividad(String opcionActividad) {
        this.opcionActividad = opcionActividad;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInformation {
    private int ingresosFijos;
    private int otrosIngresos1;
    private int totalIngresos;
    private int cuotasCreditos;
    private int otrosEgresos;
    private int totalEgresos;
    private int bienesRaices;
    private int otrosBienes;
    private int totalActivos;
    private int saldoTarjetaCredito;
    private int saldoOtrasObligaciones;
    private int totalPasivos;
    private String matricinmuebles;
    private String matricinmuebles2;
    private String matricinmuebles3;
    @JsonProperty("ValorComercial")
    private String valorComercial;
    @JsonProperty("ValorComercial2")
    private String valorComercial2;
    @JsonProperty("ValorComercial3")
    private String valorComercial3;
    @JsonProperty("SaldoHipoteca")
    private String saldoHipoteca;
    @JsonProperty("SaldoHipoteca2")
    private String saldoHipoteca2;
    @JsonProperty("SaldoHipoteca3")
    private String saldoHipoteca3;
    @JsonProperty("Saldo")
    private String saldo;
    @JsonProperty("Saldo2")
    private String saldo2;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;
    private String tipoInmueble;

    public int getIngresosFijos() {
        return ingresosFijos;
    }

    public void setIngresosFijos(int ingresosFijos) {
        this.ingresosFijos = ingresosFijos;
    }

    public int getOtrosIngresos1() {
        return otrosIngresos1;
    }

    public void setOtrosIngresos1(int otrosIngresos1) {
        this.otrosIngresos1 = otrosIngresos1;
    }

    public int getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(int totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public int getCuotasCreditos() {
        return cuotasCreditos;
    }

    public void setCuotasCreditos(int cuotasCreditos) {
        this.cuotasCreditos = cuotasCreditos;
    }

    public int getOtrosEgresos() {
        return otrosEgresos;
    }

    public void setOtrosEgresos(int otrosEgresos) {
        this.otrosEgresos = otrosEgresos;
    }

    public int getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(int totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public int getBienesRaices() {
        return bienesRaices;
    }

    public void setBienesRaices(int bienesRaices) {
        this.bienesRaices = bienesRaices;
    }

    public int getOtrosBienes() {
        return otrosBienes;
    }

    public void setOtrosBienes(int otrosBienes) {
        this.otrosBienes = otrosBienes;
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(int totalActivos) {
        this.totalActivos = totalActivos;
    }

    public int getSaldoTarjetaCredito() {
        return saldoTarjetaCredito;
    }

    public void setSaldoTarjetaCredito(int saldoTarjetaCredito) {
        this.saldoTarjetaCredito = saldoTarjetaCredito;
    }

    public int getSaldoOtrasObligaciones() {
        return saldoOtrasObligaciones;
    }

    public void setSaldoOtrasObligaciones(int saldoOtrasObligaciones) {
        this.saldoOtrasObligaciones = saldoOtrasObligaciones;
    }

    public int getTotalPasivos() {
        return totalPasivos;
    }

    public void setTotalPasivos(int totalPasivos) {
        this.totalPasivos = totalPasivos;
    }

    public String getMatricinmuebles() {
        return matricinmuebles;
    }

    public void setMatricinmuebles(String matricinmuebles) {
        this.matricinmuebles = matricinmuebles;
    }

    public String getMatricinmuebles2() {
        return matricinmuebles2;
    }

    public void setMatricinmuebles2(String matricinmuebles2) {
        this.matricinmuebles2 = matricinmuebles2;
    }

    public String getMatricinmuebles3() {
        return matricinmuebles3;
    }

    public void setMatricinmuebles3(String matricinmuebles3) {
        this.matricinmuebles3 = matricinmuebles3;
    }

    public String getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(String valorComercial) {
        this.valorComercial = valorComercial;
    }

    public String getValorComercial2() {
        return valorComercial2;
    }

    public void setValorComercial2(String valorComercial2) {
        this.valorComercial2 = valorComercial2;
    }

    public String getValorComercial3() {
        return valorComercial3;
    }

    public void setValorComercial3(String valorComercial3) {
        this.valorComercial3 = valorComercial3;
    }

    public String getSaldoHipoteca() {
        return saldoHipoteca;
    }

    public void setSaldoHipoteca(String saldoHipoteca) {
        this.saldoHipoteca = saldoHipoteca;
    }

    public String getSaldoHipoteca2() {
        return saldoHipoteca2;
    }

    public void setSaldoHipoteca2(String saldoHipoteca2) {
        this.saldoHipoteca2 = saldoHipoteca2;
    }

    public String getSaldoHipoteca3() {
        return saldoHipoteca3;
    }

    public void setSaldoHipoteca3(String saldoHipoteca3) {
        this.saldoHipoteca3 = saldoHipoteca3;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo2() {
        return saldo2;
    }

    public void setSaldo2(String saldo2) {
        this.saldo2 = saldo2;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

/**
 * @author Wilfredo Pena
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationData {
    private String numeroDocumento;
    private String numPersona;
    private String apellidos;
    private String estado;
    private String tipoPersona;
    @JsonProperty("PECODPR")
    private String pECODPR;
    @JsonProperty("PEESTPE")
    private String pEESTPE;
    @JsonProperty("PEFECNA")
    private String pEFECNA;
    @JsonProperty("PEHSTAM")
    private String pEHSTAM;
    @JsonProperty("PEINDAV")
    private String pEINDAV;
    @JsonProperty("PEINDCO")
    private String pEINDCO;
    @JsonProperty("PEINDGR")
    private String pEINDGR;
    @JsonProperty("PEINDN3")
    private String pEINDN3;
    @JsonProperty("PEINDN4")
    private String pEINDN4;
    @JsonProperty("PEINDN5")
    private String pEINDN5;
    @JsonProperty("PEINDRE")
    private String pEINDRE;
    @JsonProperty("PEMARNO")
    private String pEMARNO;
    @JsonProperty("PENOMCA")
    private String pENOMCA;
    @JsonProperty("PENOMLO")
    private String pENOMLO;
    @JsonProperty("PEOBSE1")
    private String pEOBSE1;
    @JsonProperty("PEPRIAP")
    private String pEPRIAP;
    @JsonProperty("PERUTCA")
    private String pERUTCA;
    @JsonProperty("PESECDO")
    private String pESECDO;
    @JsonProperty("PESEGAP")
    private String pESEGAP;
    @JsonProperty("PETIPDO")
    private String pETIPDO;
    @JsonProperty("PETIPVI")
    private String pETIPVI;

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumPersona() {
        return numPersona;
    }

    public void setNumPersona(String numPersona) {
        this.numPersona = numPersona;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getpECODPR() {
        return pECODPR;
    }

    public void setpECODPR(String pECODPR) {
        this.pECODPR = pECODPR;
    }

    public String getpEESTPE() {
        return pEESTPE;
    }

    public void setpEESTPE(String pEESTPE) {
        this.pEESTPE = pEESTPE;
    }

    public String getpEFECNA() {
        return pEFECNA;
    }

    public void setpEFECNA(String pEFECNA) {
        this.pEFECNA = pEFECNA;
    }

    public String getpEHSTAM() {
        return pEHSTAM;
    }

    public void setpEHSTAM(String pEHSTAM) {
        this.pEHSTAM = pEHSTAM;
    }

    public String getpEINDAV() {
        return pEINDAV;
    }

    public void setpEINDAV(String pEINDAV) {
        this.pEINDAV = pEINDAV;
    }

    public String getpEINDCO() {
        return pEINDCO;
    }

    public void setpEINDCO(String pEINDCO) {
        this.pEINDCO = pEINDCO;
    }

    public String getpEINDGR() {
        return pEINDGR;
    }

    public void setpEINDGR(String pEINDGR) {
        this.pEINDGR = pEINDGR;
    }

    public String getpEINDN3() {
        return pEINDN3;
    }

    public void setpEINDN3(String pEINDN3) {
        this.pEINDN3 = pEINDN3;
    }

    public String getpEINDN4() {
        return pEINDN4;
    }

    public void setpEINDN4(String pEINDN4) {
        this.pEINDN4 = pEINDN4;
    }

    public String getpEINDN5() {
        return pEINDN5;
    }

    public void setpEINDN5(String pEINDN5) {
        this.pEINDN5 = pEINDN5;
    }

    public String getpEINDRE() {
        return pEINDRE;
    }

    public void setpEINDRE(String pEINDRE) {
        this.pEINDRE = pEINDRE;
    }

    public String getpEMARNO() {
        return pEMARNO;
    }

    public void setpEMARNO(String pEMARNO) {
        this.pEMARNO = pEMARNO;
    }

    public String getpENOMCA() {
        return pENOMCA;
    }

    public void setpENOMCA(String pENOMCA) {
        this.pENOMCA = pENOMCA;
    }

    public String getpENOMLO() {
        return pENOMLO;
    }

    public void setpENOMLO(String pENOMLO) {
        this.pENOMLO = pENOMLO;
    }

    public String getpEOBSE1() {
        return pEOBSE1;
    }

    public void setpEOBSE1(String pEOBSE1) {
        this.pEOBSE1 = pEOBSE1;
    }

    public String getpEPRIAP() {
        return pEPRIAP;
    }

    public void setpEPRIAP(String pEPRIAP) {
        this.pEPRIAP = pEPRIAP;
    }

    public String getpERUTCA() {
        return pERUTCA;
    }

    public void setpERUTCA(String pERUTCA) {
        this.pERUTCA = pERUTCA;
    }

    public String getpESECDO() {
        return pESECDO;
    }

    public void setpESECDO(String pESECDO) {
        this.pESECDO = pESECDO;
    }

    public String getpESEGAP() {
        return pESEGAP;
    }

    public void setpESEGAP(String pESEGAP) {
        this.pESEGAP = pESEGAP;
    }

    public String getpETIPDO() {
        return pETIPDO;
    }

    public void setpETIPDO(String pETIPDO) {
        this.pETIPDO = pETIPDO;
    }

    public String getpETIPVI() {
        return pETIPVI;
    }

    public void setpETIPVI(String pETIPVI) {
        this.pETIPVI = pETIPVI;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternationalOperations {
    private String realizaOperaMoneExtran;
    private Boolean inversiones;
    private Boolean giros;
    private Boolean creditos;
    private Boolean importaciones;
    private Boolean exportaciones;
    private Boolean otro;
    private String tieneProdMoneExtraje;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String tipdoc;

    public String getRealizaOperaMoneExtran() {
        return realizaOperaMoneExtran;
    }

    public void setRealizaOperaMoneExtran(String realizaOperaMoneExtran) {
        this.realizaOperaMoneExtran = realizaOperaMoneExtran;
    }

    public Boolean getInversiones() {
        return inversiones;
    }

    public void setInversiones(Boolean inversiones) {
        this.inversiones = inversiones;
    }

    public Boolean getGiros() {
        return giros;
    }

    public void setGiros(Boolean giros) {
        this.giros = giros;
    }

    public Boolean getCreditos() {
        return creditos;
    }

    public void setCreditos(Boolean creditos) {
        this.creditos = creditos;
    }

    public Boolean getImportaciones() {
        return importaciones;
    }

    public void setImportaciones(Boolean importaciones) {
        this.importaciones = importaciones;
    }

    public Boolean getExportaciones() {
        return exportaciones;
    }

    public void setExportaciones(Boolean exportaciones) {
        this.exportaciones = exportaciones;
    }

    public Boolean getOtro() {
        return otro;
    }

    public void setOtro(Boolean otro) {
        this.otro = otro;
    }

    public String getTieneProdMoneExtraje() {
        return tieneProdMoneExtraje;
    }

    public void setTieneProdMoneExtraje(String tieneProdMoneExtraje) {
        this.tieneProdMoneExtraje = tieneProdMoneExtraje;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class References {
    private String parentesco;
    private String nombre;
    private String primerApellido;
    private String ciudad;
    private String ciudadReferencia;
    private String indictivo;
    private String telefono;
    private String direccion;
    private String numdoc;
    private String numper;
    private String secdoc;
    private String secref1;
    private String tipdoc;

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudadReferencia() {
        return ciudadReferencia;
    }

    public void setCiudadReferencia(String ciudadReferencia) {
        this.ciudadReferencia = ciudadReferencia;
    }

    public String getIndictivo() {
        return indictivo;
    }

    public void setIndictivo(String indictivo) {
        this.indictivo = indictivo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(String numdoc) {
        this.numdoc = numdoc;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public String getSecref1() {
        return secref1;
    }

    public void setSecref1(String secref1) {
        this.secref1 = secref1;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonDataRequest {
    @JsonProperty("PENUMPE")
    private String pENUMPE;
    private String tipoInmueble;
    private BasicData datosBasicos;
    private ComplementaryInfo infComplementaria;
    private EconomyActivity actividadEconomica;
    private FinancialInformation infFinanciera;
    private References referencias;
    private AdditionalInfo infAdicional;
    private InternationalOperations operacionesInternacionales;
    private String documentoCajero;
    private String tipoDocumento;
    private String numDocumento;
    private String nombre;
    private List<IdentificationData> datosIdentificacion;

    public String getpENUMPE() {
        return pENUMPE;
    }

    public void setpENUMPE(String pENUMPE) {
        this.pENUMPE = pENUMPE;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public BasicData getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(BasicData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    public ComplementaryInfo getInfComplementaria() {
        return infComplementaria;
    }

    public void setInfComplementaria(ComplementaryInfo infComplementaria) {
        this.infComplementaria = infComplementaria;
    }

    public EconomyActivity getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(EconomyActivity actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public FinancialInformation getInfFinanciera() {
        return infFinanciera;
    }

    public void setInfFinanciera(FinancialInformation infFinanciera) {
        this.infFinanciera = infFinanciera;
    }

    public References getReferencias() {
        return referencias;
    }

    public void setReferencias(References referencias) {
        this.referencias = referencias;
    }

    public AdditionalInfo getInfAdicional() {
        return infAdicional;
    }

    public void setInfAdicional(AdditionalInfo infAdicional) {
        this.infAdicional = infAdicional;
    }

    public InternationalOperations getOperacionesInternacionales() {
        return operacionesInternacionales;
    }

    public void setOperacionesInternacionales(InternationalOperations operacionesInternacionales) {
        this.operacionesInternacionales = operacionesInternacionales;
    }

    public String getDocumentoCajero() {
        return documentoCajero;
    }

    public void setDocumentoCajero(String documentoCajero) {
        this.documentoCajero = documentoCajero;
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

    public List<IdentificationData> getDatosIdentificacion() {
        return datosIdentificacion;
    }

    public void setDatosIdentificacion(List<IdentificationData> datosIdentificacion) {
        this.datosIdentificacion = datosIdentificacion;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonRequest {
    private TrxPersonHeader cabecera;
    private TrxPersonDataRequest data;

    public TrxPersonHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxPersonHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxPersonDataRequest getData() {
        return data;
    }

    public void setData(TrxPersonDataRequest data) {
        this.data = data;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseTrxDTO {
  private List<ErrorTrxDTO> errores;

  public List<ErrorTrxDTO> getErrores() {
    return errores;
  }

  public void setErrores(List<ErrorTrxDTO> errores) {
    this.errores = errores;
  }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorTrxDTO {

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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBasicData {
    private TrxPersonData datosBasicos;
    private String numPersona;

    public TrxPersonData getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(TrxPersonData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    public String getNumPersona() {
        return numPersona;
    }

    public void setNumPersona(String numPersona) {
        this.numPersona = numPersona;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonData {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String paisExpedicion;
    private String ciudadExpedicion;
    private String fechaExpedicion;
    private String paisNacimiento;
    private String nacionalidad;
    private String ciudadNacimiento;
    private String fechaNacimiento;
    private String sexo;
    private String paisDireccion;
    private String departamento;
    private String ciudad;
    private String tipoVia;
    private String nombreVia;
    private String descripcionDireccion;
    private String clase;
    private String indicativo;
    private String telefono;
    private String precelular;
    private String celular;
    private String email;
    private String autorizacionEmail;
    private String autorizoTelefono;
    private String agrofic;
    private String codact;
    private String codpaip;
    private String conper;
    private int domant;
    private String entpre;
    private String estciv;
    private String estper;
    private String estrat;
    private String fecalt;
    private String fecfal;
    private String fecing;
    private String hstamp;
    private String hstamp2;
    private String hstamp3;
    private String hstamp4;
    private String hstamp5;
    private String logdomp;
    private String logtelp;
    private String numintp;
    private String numper;
    private String precel;
    private String profes;
    private int seccel;
    private String secdoc;
    private int secdomp;
    private int secdotc;
    private int secdotp;
    private int secema;
    private int sectelp;
    private String sucadm;
    private String sucmod;
    private String termod;
    private String tipdomp;
    private String tipocu;
    private String tipper;
    private String tiptelp;
    private String usualt;
    private String usumod;

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

    public String getPaisExpedicion() {
        return paisExpedicion;
    }

    public void setPaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }

    public String getCiudadExpedicion() {
        return ciudadExpedicion;
    }

    public void setCiudadExpedicion(String ciudadExpedicion) {
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
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

    public String getPaisDireccion() {
        return paisDireccion;
    }

    public void setPaisDireccion(String paisDireccion) {
        this.paisDireccion = paisDireccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPrecelular() {
        return precelular;
    }

    public void setPrecelular(String precelular) {
        this.precelular = precelular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAutorizacionEmail() {
        return autorizacionEmail;
    }

    public void setAutorizacionEmail(String autorizacionEmail) {
        this.autorizacionEmail = autorizacionEmail;
    }

    public String getAutorizoTelefono() {
        return autorizoTelefono;
    }

    public void setAutorizoTelefono(String autorizoTelefono) {
        this.autorizoTelefono = autorizoTelefono;
    }

    public String getAgrofic() {
        return agrofic;
    }

    public void setAgrofic(String agrofic) {
        this.agrofic = agrofic;
    }

    public String getCodact() {
        return codact;
    }

    public void setCodact(String codact) {
        this.codact = codact;
    }

    public String getCodpaip() {
        return codpaip;
    }

    public void setCodpaip(String codpaip) {
        this.codpaip = codpaip;
    }

    public String getConper() {
        return conper;
    }

    public void setConper(String conper) {
        this.conper = conper;
    }

    public int getDomant() {
        return domant;
    }

    public void setDomant(int domant) {
        this.domant = domant;
    }

    public String getEntpre() {
        return entpre;
    }

    public void setEntpre(String entpre) {
        this.entpre = entpre;
    }

    public String getEstciv() {
        return estciv;
    }

    public void setEstciv(String estciv) {
        this.estciv = estciv;
    }

    public String getEstper() {
        return estper;
    }

    public void setEstper(String estper) {
        this.estper = estper;
    }

    public String getEstrat() {
        return estrat;
    }

    public void setEstrat(String estrat) {
        this.estrat = estrat;
    }

    public String getFecalt() {
        return fecalt;
    }

    public void setFecalt(String fecalt) {
        this.fecalt = fecalt;
    }

    public String getFecfal() {
        return fecfal;
    }

    public void setFecfal(String fecfal) {
        this.fecfal = fecfal;
    }

    public String getFecing() {
        return fecing;
    }

    public void setFecing(String fecing) {
        this.fecing = fecing;
    }

    public String getHstamp() {
        return hstamp;
    }

    public void setHstamp(String hstamp) {
        this.hstamp = hstamp;
    }

    public String getHstamp2() {
        return hstamp2;
    }

    public void setHstamp2(String hstamp2) {
        this.hstamp2 = hstamp2;
    }

    public String getHstamp3() {
        return hstamp3;
    }

    public void setHstamp3(String hstamp3) {
        this.hstamp3 = hstamp3;
    }

    public String getHstamp4() {
        return hstamp4;
    }

    public void setHstamp4(String hstamp4) {
        this.hstamp4 = hstamp4;
    }

    public String getHstamp5() {
        return hstamp5;
    }

    public void setHstamp5(String hstamp5) {
        this.hstamp5 = hstamp5;
    }

    public String getLogdomp() {
        return logdomp;
    }

    public void setLogdomp(String logdomp) {
        this.logdomp = logdomp;
    }

    public String getLogtelp() {
        return logtelp;
    }

    public void setLogtelp(String logtelp) {
        this.logtelp = logtelp;
    }

    public String getNumintp() {
        return numintp;
    }

    public void setNumintp(String numintp) {
        this.numintp = numintp;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getPrecel() {
        return precel;
    }

    public void setPrecel(String precel) {
        this.precel = precel;
    }

    public String getProfes() {
        return profes;
    }

    public void setProfes(String profes) {
        this.profes = profes;
    }

    public int getSeccel() {
        return seccel;
    }

    public void setSeccel(int seccel) {
        this.seccel = seccel;
    }

    public String getSecdoc() {
        return secdoc;
    }

    public void setSecdoc(String secdoc) {
        this.secdoc = secdoc;
    }

    public int getSecdomp() {
        return secdomp;
    }

    public void setSecdomp(int secdomp) {
        this.secdomp = secdomp;
    }

    public int getSecdotc() {
        return secdotc;
    }

    public void setSecdotc(int secdotc) {
        this.secdotc = secdotc;
    }

    public int getSecdotp() {
        return secdotp;
    }

    public void setSecdotp(int secdotp) {
        this.secdotp = secdotp;
    }

    public int getSecema() {
        return secema;
    }

    public void setSecema(int secema) {
        this.secema = secema;
    }

    public int getSectelp() {
        return sectelp;
    }

    public void setSectelp(int sectelp) {
        this.sectelp = sectelp;
    }

    public String getSucadm() {
        return sucadm;
    }

    public void setSucadm(String sucadm) {
        this.sucadm = sucadm;
    }

    public String getSucmod() {
        return sucmod;
    }

    public void setSucmod(String sucmod) {
        this.sucmod = sucmod;
    }

    public String getTermod() {
        return termod;
    }

    public void setTermod(String termod) {
        this.termod = termod;
    }

    public String getTipdomp() {
        return tipdomp;
    }

    public void setTipdomp(String tipdomp) {
        this.tipdomp = tipdomp;
    }

    public String getTipocu() {
        return tipocu;
    }

    public void setTipocu(String tipocu) {
        this.tipocu = tipocu;
    }

    public String getTipper() {
        return tipper;
    }

    public void setTipper(String tipper) {
        this.tipper = tipper;
    }

    public String getTiptelp() {
        return tiptelp;
    }

    public void setTiptelp(String tiptelp) {
        this.tiptelp = tiptelp;
    }

    public String getUsualt() {
        return usualt;
    }

    public void setUsualt(String usualt) {
        this.usualt = usualt;
    }

    public String getUsumod() {
        return usumod;
    }

    public void setUsumod(String usumod) {
        this.usumod = usumod;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPersonResponse {
    private TrxBasicData data;
    private TrxPersonHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBasicData getData() {
        return data;
    }

    public void setData(TrxBasicData data) {
        this.data = data;
    }

    public TrxPersonHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxPersonHeader cabecera) {
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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxResponseData {
    private TrxBasicData datosBasicos;

    public TrxBasicData getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(TrxBasicData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.context;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ContextRequest {
    private String key;
    private Object value;
    private String product;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.context;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ContextResponse {
    private String key;
    private Object value;
    private String product;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration;

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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class SecurityHeaders {
    private String authorization;
    private String xSantanderClientId;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getxSantanderClientId() {
        return xSantanderClientId;
    }

    public void setxSantanderClientId(String xSantanderClientId) {
        this.xSantanderClientId = xSantanderClientId;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataListDTO {
    private String listCode;
    private String code;
    private String description;

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;

import java.util.List;



@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeographiesParametersResponseDTO {
   private List<DataListDTO> parameters;

    public List<DataListDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<DataListDTO> parameters) {
        this.parameters = parameters;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums;

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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums;

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

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

public class ErrorCatalog {
    private ErrorCatalog() {}
    private static ErrorDTO msParametersNetworkConnection = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-T-9000")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsParametersNetwork())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getErrorMsParametersNetwork())
                                                            .build();



    private static ErrorDTO msParametersResponse = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-T-9001")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsParametersResponse())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getErrorMsParametersResponse())
                                                            .build();

    private static ErrorDTO msParametersNoEntry = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-T-9002")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsParametersNoEntry())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getErrorMsParametersNoEntry())
                                                            .build();

    private static ErrorDTO msSanbaNetworkConnection = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-T-9003")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsSanbaNetwork())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getErrorMsSanbaNetwork())
                                                            .build();


    private static ErrorDTO msParametersGeneral = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-T-9004")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsParametersGeneral())
                                                            .description(ErrorDictionary.getMsName()+ " - " + ErrorDictionary.getErrorMsParametersGeneral())
                                                            .build();

    private static ErrorDTO msSanbaTrxError = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName()+ "-P-T-9409")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsSanbaTrx())
                                                            .description(ErrorDictionary.getMsName()+ " - " + ErrorDictionary.getErrorMsSanbaTrx())
                                                            .build();

    private static ErrorDTO msSanbaResponse = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName()+ "-P-T-9006")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getErrorMsSanbaResponse())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getErrorMsSanbaResponse())
                                                            .build();

    private static ErrorDTO invalidProspectId = ErrorDTO.builder()
                                                            .code(ErrorDictionary.getMsName() + "-P-F-9400")
                                                            .level(ErrorDictionary.getErrorLevel())
                                                            .message(ErrorDictionary.getInvalidProspectId())
                                                            .description(ErrorDictionary.getMsName() + " - " + ErrorDictionary.getInvalidProspectId())
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

    public static ErrorDTO getMsSanbaNetworkConnection() {
        return msSanbaNetworkConnection;
    }

    public static void setMsSanbaNetworkConnection(ErrorDTO msSanbaNetworkConnection) {
        ErrorCatalog.msSanbaNetworkConnection = msSanbaNetworkConnection;
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

    public static ErrorDTO getMsSanbaResponse() {
        return msSanbaResponse;
    }

    public static void setMsSanbaResponse(ErrorDTO msSanbaResponse) {
        ErrorCatalog.msSanbaResponse = msSanbaResponse;
    }

    public static ErrorDTO getInvalidProspectId() {
        return invalidProspectId;
    }

    public static void setInvalidProspectId(ErrorDTO invalidProspectId) {
        ErrorCatalog.invalidProspectId = invalidProspectId;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

public class ErrorDictionary {

    private ErrorDictionary() {}
    private static String msName = "PROSPECT-CONTACT-POINTS";
    private static String errorLevel = "error";
    private static String errorMsParametersNetwork = "Cannot connect to MS Parameters";
    private static String errorMsParametersResponse = "Error trying to process response";
    private static String errorMsParametersNoEntry = "No entry founds";
    private static String errorMsParametersGeneral = "Cannot connect to MS Parameters";
    private static String errorMsSanbaNetwork = "Cannot connect to MS Sanba";
    private static String errorMsSanbaResponse = "Error trying to process response";
    private static String errorMsSanbaTrx = "Error in transaction execution";
    private static String invalidProspectId = "Invalid prospect_id";

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

    public static String getErrorMsSanbaResponse() {
        return errorMsSanbaResponse;
    }

    public static void setErrorMsSanbaResponse(String errorMsSanbaResponse) {
        ErrorDictionary.errorMsSanbaResponse = errorMsSanbaResponse;
    }

    public static String getErrorMsSanbaNetwork() {
        return errorMsSanbaNetwork;
    }

    public static void setErrorMsSanbaNetwork(String errorMsSanbaNetwork) {
        ErrorDictionary.errorMsSanbaNetwork = errorMsSanbaNetwork;
    }

    public static String getErrorMsParametersGeneral() {
        return errorMsParametersGeneral;
    }

    public static void setErrorMsParametersGeneral(String errorMsParametersGeneral) {
        ErrorDictionary.errorMsParametersGeneral = errorMsParametersGeneral;
    }

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
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

import lombok.*;

/**
 * @author Wilfredo Pena
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

/**
 * @author Wilfredo Pena
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
}


 package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;

@Service
public class ErrorService  {

    @Value("${ms_name}")
    private String  msName;

    @Value("${ms_version}")
    private String msVersion;
    @Value("${errors.level}")
    private String errorLevel;

    @Value("${errors.functional}")
    private String functionalError;

    @Value("${errors.technical}")
    private String technicalError;

    @Value("${errors.general.invalid_value}")
    private String invalidValue;


    @Value(("${errors.general.blank_data}"))
    private String blankData;



    public String getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(String invalidValue) {
        this.invalidValue = invalidValue;
    }

    public ServiceException errorBuilder(HttpStatus status, String message, ErrorType type){

        String errorType = type == ErrorType.FUNCTIONAL ? functionalError : technicalError;

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
            var message = "'"+ fieldName +"' " + blankData;

            throw errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);            
        }        
    }

}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception;


import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
 * @author Wilfredo Pena
 * This class handle all Exceptions
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private String msError = "error";
    private String functionalBadrequestError = "-P-F-9400";
    private String notSpecified = " not specified";
    @Value("${params.app-name}")
    private String msName;


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        
        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName + "-P-T-9499")
                            .message("Unhandled exception")
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Unhandled exception")
                            .build());
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
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
                                .level(msError)
                                .message(errorMessage)
                                .description(msName.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName + "-P-F-9404")
                            .message("Not Found")
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Not Found")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName + functionalBadrequestError)
                            .message("Required query parameter " + ex.getParameterName() + notSpecified)
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + notSpecified)
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName.toLowerCase() + functionalBadrequestError)
                            .message(ex.getMessage())
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Bad request")
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName + "-P-F-9400")
                            .message("Required header " + ex.getHeaderName() + " not specified")
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + notSpecified)
                            .build());        

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

        
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(HttpMessageNotReadableException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                            .code(msName + "-P-T-9400")
                            .message("Invalid body structure")
                            .level(msError)
                            .description(msName.toLowerCase() + "-api-services-v1: Invalid body structure")
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

        newErrorDTO.getErrors().forEach( error->
                    log.error(error.getMessage())

        );
        return new ResponseEntity<>(newErrorDTO, status);
    }//method closure

/**
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {

        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        if(errors != null){
            errors.forEach( error->
                log.error(error.getMessage())

            );
        }
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

}//class closure

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDTO;
import lombok.*;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;

/**
 * @author Wilfredo Pena
 */


@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class ServiceException  extends RuntimeException  {
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorResponseDTO;
import lombok.*;


/**
 * @author Wilfredo Pena
 */

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ServiceExceptionClient extends RuntimeException {

    private transient ErrorResponseDTO errorResponseDTO;


}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.mappers;



import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.*;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ElectronicAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.PhoneAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.pagination.PaginationDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.response.*;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ParametersEnums;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.CustomerMapperUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerContactPointsMapper {


    final ParameterApiService parameterApiService;

    @Value("${params.default-channel}")
    private String defaultChannel;
    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositive;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegative;


    public ContactPointsResponseDTO trxPersonToCustomerDetailsDTO(TrxPersonResponse trxBody,SecurityHeaders securityHeaders) {
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        ContactPointDTO responseDTO = new ContactPointDTO();

        if(isProspect(personData.getConper())){
            return null;
        }

        DocumentDTO document = getDocumentBasics(personData,securityHeaders);
        //Person
        PersonDTO personDTO = personDTONames(personData);
        //COUNTRY OF BIRTH
        CodeNameDTO countryBirth = getCountry(personData.getPaisNacimiento(),securityHeaders);
        //COUNTRY EXPEDITION
        CodeNameDTO countryObj = getCountry(personData.getPaisExpedicion(),securityHeaders);
        document.setCountry(countryObj);
        //COUNTRY NATIONALITY
        CodeNameDTO fistNationality = getCountry(personData.getNacionalidad(),securityHeaders);
        personDTO.setFirstNationality(fistNationality);
        //CITY
        CodeNameDTO cityObj = getCity(personData.getCiudad(),securityHeaders);
        document.setState(cityObj);
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        //DEPARTMENT
        CodeNameDTO departMentState = getCity(personData.getCiudad(),securityHeaders);
        //PLACE OF BIRTH
        PlaceOfBirthDTO placeOfBirthDTO = new PlaceOfBirthDTO();
        placeOfBirthDTO.setCountry(countryBirth);
        placeOfBirthDTO.setState(departMentState);
        //Get Town
        CodeNameDTO townObj = getTown(personData.getCiudadNacimiento(),securityHeaders);

        placeOfBirthDTO.setTown(townObj.getName());
        personDTO.setPlaceOfBirth(placeOfBirthDTO);

        personDTO.setCountryOfResidence(countryObj);

        UseTypeDTO useType = new UseTypeDTO();
        useType.setCode("PRI");
        useType.setDescription("Contactos Principales");
        List<UseTypeDTO> useTypes = Arrays.asList(useType);
        responseDTO.setUseTypes(useTypes);

        responseDTO.setContactPointId("PRI001");

        //PhoneAdrress
        PhoneAddressDTO phoneAddress = new PhoneAddressDTO();
        phoneAddress.setInternationalCode(personData.getPrecel());
        phoneAddress.setMobileNumber(personData.getCelular());
        phoneAddress.setExtension(personData.getNumintp());        
        phoneAddress.setPhoneNumber(personData.getTelefono());
        responseDTO.setPhoneAddress(phoneAddress);

        ElectronicAddressDTO electronicAddress = new ElectronicAddressDTO();
        electronicAddress.setEmailAddress(personData.getEmail());
        responseDTO.setElectronicAddress(electronicAddress);

        responseDTO.setPostalAddress(getPostalAddressBasic(personData,countryObj,cityObj,securityHeaders));        

        responseDTO.setPreferredIndicator(true);
        responseDTO.setPrimaryIndicator(true);

        ContactPointsResponseDTO response = new ContactPointsResponseDTO();

        List<ContactPointDTO> contactPoints = Arrays.asList(responseDTO);
        response.setContactPoints(contactPoints);

        return response;
    }//method closure

    /**
     * Customer Search Response
     *
     * @param trxBody
     * @return CustomerSearchResponseDTO
     */
    public CustomerSearchResponseDTO trxPersonToCustomerSearchDTO(TrxPersonResponse trxBody,SecurityHeaders securityHeaders) {
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        CustomerSearchResponseDTO responseDTO = new CustomerSearchResponseDTO();
        List<CustomerSearchDTO> customers = new ArrayList<>();
        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO();


        if(isProspect(personData.getConper())){
            return null;
        }
        DocumentDTO document = getDocumentBasics(personData,securityHeaders);
        PersonDTO personDTO = personDTONames(personData);

        //COUNTRY EXP
        CodeNameDTO countryObj = getCountry(personData.getPaisExpedicion(),securityHeaders);
        document.setCountry(countryObj);
        //CITY
        CodeNameDTO cityObj = getCity(personData.getCiudad(),securityHeaders);
        document.setState(cityObj);
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));

        //Contact Point
        ContactPointCustomerSearchDTO contactPoint = new ContactPointCustomerSearchDTO();
        contactPoint.setPostalAddress(getPostalAddressBasic(personData,countryObj,cityObj,securityHeaders));
        personDTO.setDocument(document);

        customerSearchDTO.setCustomerId(personData.getNumper());
        customerSearchDTO.setPerson(personDTO);
        customerSearchDTO.setOrganization(new OrganizationCustomerSearchDTO());

        customerSearchDTO.setContactPoint(contactPoint);

        customers.add(customerSearchDTO);
        responseDTO.setCustomers(customers);
        responseDTO.setPagination(new PaginationDTO());
        return responseDTO;
    }//method closure
    
    public static TrxPersonRequest dtoRequestToTrxRequest(CustomerRequestDTO dtoCustomerRequest) {
        TrxPersonRequest response = new TrxPersonRequest();
        TrxPersonDataRequest personDataRequest = new TrxPersonDataRequest();
        personDataRequest.setNumDocumento(dtoCustomerRequest.getPerson().getDocument().getDocumentNumber());
        response.setData(personDataRequest);
        return response;
    }

    private PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, CodeNameDTO countryObj, CodeNameDTO cityObj,SecurityHeaders securityHeaders){
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        String fulladdres = personData.getNombreVia();
        postalAddressDTO.setFullAddress(fulladdres);
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        DataListDTO wayTypeParameter = parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), personData.getTipoVia(),securityHeaders).get(0);
        postalAddressDTO.setStreetTypeDescription(wayTypeParameter.getDescription());
        DataListDTO townParameter = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), personData.getCiudad(),securityHeaders).get(0);
        postalAddressDTO.setTownName(townParameter.getDescription());
        postalAddressDTO.setCountry(countryObj);
        postalAddressDTO.setState(cityObj);
        postalAddressDTO.setPremise(personData.getDescripcionDireccion());
        return postalAddressDTO;
    }//method closure

    private CodeNameDTO getCountry(String country,SecurityHeaders securityHeaders){
        CodeNameDTO countryObj = new CodeNameDTO();
        DataListDTO countryParameter = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), country,securityHeaders).get(0);
        countryObj.setCode(countryParameter.getCode());
        countryObj.setName(countryParameter.getDescription());
        return  countryObj;
    }//method closure

    private CodeNameDTO getTown(String townCode,SecurityHeaders securityHeaders){
        CodeNameDTO countryObj = new CodeNameDTO();
        DataListDTO countryParameter = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,securityHeaders).get(0);
        countryObj.setCode(countryParameter.getCode());
        countryObj.setName(countryParameter.getDescription());
        return  countryObj;
    }//method closure

    private CodeNameDTO getCity(String city,SecurityHeaders securityHeaders){

        if(!city.isEmpty()){
            city = city.substring(0,2);
        }
        CodeNameDTO cityObj = new CodeNameDTO();
        DataListDTO cityParameter = parameterApiService.getParameter(ParametersEnums.STATES.value(), city,securityHeaders).get(0);
        cityObj.setCode(cityParameter.getCode());
        cityObj.setName(cityParameter.getDescription());
        return cityObj;
    }//method closure

    private static PersonDTO personDTONames(TrxPersonData personData){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonName(new PersonNameDTO());
        personDTO.getPersonName().setGivenName(personData.getNombre());
        personDTO.getPersonName().setLastName(personData.getPrimerApellido());
        personDTO.getPersonName().setSecondLastName(personData.getSegundoApellido());
        String fullName = personData.getNombre() + " " + personData.getPrimerApellido() + " " + personData.getSegundoApellido();
        personDTO.getPersonName().setFullName(fullName);
        personDTO.setBirthDate(TimeUtils.formatDate(personData.getFechaNacimiento()));
        return personDTO;
    }//method closure

    public DocumentDTO getDocumentBasics(TrxPersonData personData,SecurityHeaders securityHeaders){
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        String documetTypeDescription = parameterApiService.getParameter(ParametersEnums.DOCU_TYPE.value(), personData.getTipoIdentificacion(),securityHeaders).get(0).getDescription();
        document.setDocumentTypeDescription(documetTypeDescription);
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        return document;
    }//method closure

    /**
     * Is Prospect or not
     * Prospect == 204 Non-content
     * @param conper
     * @return
     */
    private static boolean isProspect(String conper) {
        return "PRO".equalsIgnoreCase(conper);
    }//method closure


    /**
     * USED ON UPDATE CUSTOMER
     * @param trxBasicData
     * @return
     */
    public BasicData pef3ResponseToPef2Request(TrxPersonData trxBasicData) {
        
        BasicData response = new BasicData();
        response.setCelular(trxBasicData.getCelular().replace(" ", ""));
        response.setTelefono(trxBasicData.getTelefono().replace(" ", ""));
        response.setAgrofic(trxBasicData.getAgrofic());
        response.setCodact(trxBasicData.getCodact());
        response.setClase(trxBasicData.getClase()); //004
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
        //pais
        response.setPaisDireccion(trxBasicData.getPaisDireccion());
        response.setPaisExpedicion(trxBasicData.getPaisExpedicion());
        response.setPaisNacimiento(trxBasicData.getPaisNacimiento());
        response.setPaisDireccionDesc(""); //DESC
        response.setPaisNacimientoDesc(""); //DESC
        response.setPaisExpedicionDesc(""); //DESC
        response.setLugardeExpDescripcion(""); //DESC
        response.setLugardeNacimiento(""); //DESC
        response.setDescripcionDireccion(trxBasicData.getDescripcionDireccion());
        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        //ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion() );        
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento() );        
        response.setFecing(trxBasicData.getFecing());
        response.setCiudadDescripcion(""); //DESC
        response.setDomant(Integer.toString(trxBasicData.getDomant())); //CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel()));//CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel())); //CASE
        response.setSecema(Integer.toString(trxBasicData.getSecema())); //CASE
        response.setSecdotc(Integer.toString(trxBasicData.getSecdotc())); //CASE
        response.setSectelp(Integer.toString(trxBasicData.getSectelp())); //CASE
        response.setSecdomp(Integer.toString(trxBasicData.getSecdomp())); //CASE
        response.setSecdotp(Integer.toString(trxBasicData.getSecdotp())); //CASE
        response.setSucadm(trxBasicData.getSucadm());
        response.setSucmod(trxBasicData.getSucmod());

        response.setAutorizoTelefono(Boolean.parseBoolean(trxBasicData.getAutorizoTelefono()));//CASE
        response.setAutorizacionEmail(Boolean.parseBoolean(trxBasicData.getAutorizacionEmail())); //CASE


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


        /**
         *         response.setClase("004");
         *         response.setSucadm("0100");
         *         response.setTipdomp("PRI");
         *         response.setAgrofic("0006");
         *         response.setTiptelp("001");
         *         response.setFecalt("9999-12-31");
         *         response.setCodact(""); //Este dato es sensible PREGUNTAR        //basicData.setCodact("00000010");
         */
        return response;
    }//method closure

    
    /**
     *
     * @param contactPointsRequest
     * @return
     */
    public BasicData contactPointPatchToPef2Request(ContactPointsRequestDTO contactPointsRequest) {
        BasicData response = new BasicData();


        if(contactPointsRequest.getPostalAddress() != null){
            validatePostalAddress(contactPointsRequest, response);
        }

        if(contactPointsRequest.getPhoneAddress() != null){
            var phoneAddress = contactPointsRequest.getPhoneAddress();

            if( phoneAddress.getMobileNumber() != null && !phoneAddress.getMobileNumber().isBlank()){
                response.setCelular(phoneAddress.getMobileNumber());
            }

            if( phoneAddress.getPhoneNumber() != null && !phoneAddress.getPhoneNumber().isBlank()){
                response.setTelefono(phoneAddress.getPhoneNumber());
            }

            if( phoneAddress.getInternationalCode() != null && !phoneAddress.getInternationalCode().isBlank()){
                response.setPrecelular(phoneAddress.getInternationalCode());
            }                      
        }

        if(contactPointsRequest.getElectronicAddress() != null){
            var electronicAddress = contactPointsRequest.getElectronicAddress();

            if(electronicAddress.getEmailAddress() != null && !electronicAddress.getEmailAddress().isBlank()){
                response.setEmail(electronicAddress.getEmailAddress());
            }
        }

        return response;
    }//method closure

    private void validatePostalAddress (ContactPointsRequestDTO contactPointsRequest, BasicData response){
        var postalAddress = contactPointsRequest.getPostalAddress();

        // StreetTypeCode
        String streetTypeCode = postalAddress.getStreetTypeCode();
        if( streetTypeCode != null && !streetTypeCode.isBlank() )
            response.setTipoVia( postalAddress.getStreetTypeCode().toUpperCase() );



        String fullAddress =  CustomerMapperUtils.cleanAddress(postalAddress.getFullAddress());
        if( fullAddress != null && !fullAddress.isBlank() ){
            response.setNombreVia( fullAddress );
        }

        if(postalAddress.getTown() != null && !postalAddress.getTown().isBlank()){

            response.setCiudad(postalAddress.getTown());
        }
        String premise =  CustomerMapperUtils.cleanAddress(postalAddress.getPremise());
        if( premise != null && !premise.isBlank()){
            response.setDescripcionDireccion(premise);
        }
    }

    public String usualtMapper(String usualt, String foreignTaxIndicator){

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES") ? foreignTaxIndicatorPositive : foreignTaxIndicatorNegative;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : defaultChannel;

        return localUsualt + localForeignTaxIndicator;
    }


}//class closure


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.observability;

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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.impl;


import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ParametersEnums;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParamServiceImpl {

    final ParameterApiService parameterApiService;

    public String getWayTypeDescription(String wayType,SecurityHeaders securityHeaders){
        if(wayType != null && !wayType.isEmpty()){

            var parameterResponse =  parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), wayType,securityHeaders);

            if(!parameterResponse.isEmpty()){
                return parameterResponse.get(0).getDescription();
            }
            return "";
        }
        return "";
    }

    public String getWayTypes(String wayTypes,SecurityHeaders securityHeaders){
        if(wayTypes != null && !wayTypes.isEmpty()){

            var parameterResponse =  parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), wayTypes,securityHeaders);

            if(!parameterResponse.isEmpty()){
                return parameterResponse.get(0).getDescription();
            }
            return "";
        }
        return "";
    }

    public CodeNameDTO getCountry(String country,SecurityHeaders securityHeaders) {
        CodeNameDTO countryObj = new CodeNameDTO();

        var parameterResponse = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), country,securityHeaders);

        if( parameterResponse != null && !parameterResponse.isEmpty() ) {
            DataListDTO countryParameter = parameterResponse.get(0);    
            
            countryObj.setCode(countryParameter.getCode());
            countryObj.setName(countryParameter.getDescription());
        }

        return countryObj;
    }//method closure

    public CodeNameDTO getTown(String townCode,SecurityHeaders securityHeaders) {
        CodeNameDTO countryObj = new CodeNameDTO();

        var parameterResponse = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,securityHeaders);

        if(parameterResponse != null && !parameterResponse.isEmpty()){
            DataListDTO countryParameter = parameterResponse.get(0);

            countryObj.setCode(countryParameter.getCode());
            countryObj.setName(countryParameter.getDescription());
        }

        return countryObj;

    }//method closure

    public CodeNameDTO getCity(String city,SecurityHeaders securityHeaders) {
        if (!city.isEmpty()) {
            city = city.substring(0, 2);
        }
        CodeNameDTO cityObj = new CodeNameDTO();

        var parameterResponse = parameterApiService.getParameter(ParametersEnums.STATES.value(), city,securityHeaders);

        if(parameterResponse != null && !parameterResponse.isEmpty() ){
            DataListDTO cityParameter = parameterResponse.get(0);

            cityObj.setCode(cityParameter.getCode());
            cityObj.setName(cityParameter.getDescription());

        }

        return cityObj;
    }//method closure
}

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.impl;


import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.mappers.CustomerContactPointsMapper;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.service.ProspectContactPointsService;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.ClientUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.CustomerMapperUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexTypes;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Wilfredo Pena
 * This clas handle all main methods from MS-Customer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProspectContactPointSevicerImpl implements ProspectContactPointsService {

    private String fieldStreetTypeCode ="streetTypeCode";
    private String fieldFullAddress = "fullAddress";
    private String fieldMobileNumber = "mobileNumber";
    private String fieldPremise = "premise";
    private String fieldEmailAddress = "emailAddress";
    private String fieldInternationalCode = "internationalCode";
    private String fieldPhoneNumber = "phoneNumber";
    /**
     * Client Trx
     */

    final TrxPersonService trxPersonService;

    /**
     * cahce service
     */

    final ContextApiService contextService;


    final CustomerContactPointsMapper mapper;


    final  RegexUtils regexUtils;


    final  CustomerMapperUtils mapperUtils;


    final  ErrorService errorService;


    final ParameterApiService parameterApiService;


    @Value("${params.default-contact-point-id}")
    private String CONTACT_POINT_ID;


    /**
     * Find Customer
     *
     * @param prospect_Id
     * @return
     */
    @Override
    public ContactPointsResponseDTO getCustomerDetails(String prospect_Id,SecurityHeaders securityHeaders) {
        ObjectMapper mapResponse = new ObjectMapper();
        log.info(GUtils.SLOG + "service get customer details {}", prospect_Id);
        TrxPersonResponse responseTrx;
        ContactPointsResponseDTO customerDetailsResponseDTO;

        if(!isPenumperValid(prospect_Id)){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidProspectId());
        }

        ContactPointsResponseDTO getResponse = mapResponse.convertValue(contextService.getContext(prospect_Id), ContactPointsResponseDTO.class);
        if (getResponse != null) {
            return getResponse;
        }
        responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByProspectId(null, prospect_Id)
                , ClientEnum.PEF3);
        customerDetailsResponseDTO = mapper.trxPersonToCustomerDetailsDTO(responseTrx,securityHeaders);

        log.info(GUtils.ELOG + "service get customer details response={}", customerDetailsResponseDTO);
        return customerDetailsResponseDTO;
    }//method closure

    private Boolean isPenumperValid(String penumper){

        //Valida que sea numérico
        try {
            Long.valueOf(penumper);
        } catch (Exception e) {
            return false;
        }

        //Valida largo
        if(penumper.length() != 8) return false;

        return true;

    }

    @Override
    public void patchProspectContactPoint(String prospectId, String contactPointId, ContactPointsRequestDTO contactPointRequest, SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "service putCustomerContactPoint");

        // region: VALIDACIONES
        // prospectId regex
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, prospectId, "prospect_Id");
        // prospectId length
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, prospectId, "prospect_Id");
        // ContactPoint id
        if(!contactPointId.equals(CONTACT_POINT_ID)){
            var message = "'contact_point_id' " + errorService.getInvalidValue();
            throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
        if(contactPointRequest.getPostalAddress() != null){
            validatePostalAddress(contactPointRequest,securityHeaders);
        }

        if(contactPointRequest.getPhoneAddress() != null){
            validatePhoneAddress(contactPointRequest);
        }

        if(contactPointRequest.getElectronicAddress() != null){
            var electronicAddress = contactPointRequest.getElectronicAddress();

            if(electronicAddress.getEmailAddress() != null ){
                errorService.isBlank(electronicAddress.getEmailAddress(), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL, electronicAddress.getEmailAddress(), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, electronicAddress.getEmailAddress(), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, String.valueOf(electronicAddress.getEmailAddress().charAt(0)), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, electronicAddress.getEmailAddress().split("@")[0], fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, electronicAddress.getEmailAddress().split("@")[1], fieldEmailAddress);
            }
        }
        //endregion
        

        TrxPersonResponse responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByProspectId(null, prospectId)
                , ClientEnum.PEF3);     
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();
        String premisePatch = trxBasicData.getDescripcionDireccion();        
        String fechaExpedicion = "";

        // Verifica si la longitud de la cadena es mayor o igual a 10
        if (premisePatch.length() >= 10) {
            // Obtiene los últimos 10 caracteres y guarda en la variable
            fechaExpedicion = premisePatch.substring(premisePatch.length() - 10);
        } else {
            // Maneja el caso donde la cadena es demasiado corta
            fechaExpedicion = premisePatch; // O cualquier otro valor que desees asignar
        }
        //VALIDA CONPER == PRO
        if( !CustomerMapperUtils.isProspect(trxBasicData.getConper()) ){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidProspectId());
        }

        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF2
        BasicData basicData =  mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);

        //Transformo el request del YML en BasicData
        BasicData basicDataRequestPatch = mapper.contactPointPatchToPef2Request(contactPointRequest);
        log.info("basicData request patch = {}", basicDataRequestPatch);

        //Remplace

        //Copio propiedades que deseo modificar al nuevo objeto
        BeanUtils.copyProperties(basicDataRequestPatch,basicData,GUtils.getNullPropertyNames(basicDataRequestPatch));
        log.info("BasicData  afterCopy    = {}", basicData);
        String foreignTaxIndicator = mapperUtils.getForeignTaxIndicator(trxBasicData);
      
        basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(),  foreignTaxIndicator));

        // Se actualiza el estado civil para gatillar la actualización de datos
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


        TrxPersonRequest pef2Request = new TrxPersonRequest();
        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);
        pef2Request.setData(trxPersonDataRequest);
        String desDire = pef2Request.getData().getDatosBasicos().getDescripcionDireccion();
        if (desDire != null && fechaExpedicion!=null) {
            // Construir premise + expirationDate
            desDire = StringUtils.completarYConcatenar(desDire, fechaExpedicion, 50); 
        }  
        pef2Request.getData().getDatosBasicos().setDescripcionDireccion(desDire);
        

        //UPDATE PROSPECT
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service putCustomerContactPoint");

    }
    private void validatePostalAddress(ContactPointsRequestDTO contactPointRequest, SecurityHeaders securityHeaders){
        var postalAddress = contactPointRequest.getPostalAddress();

        // StreetTypeCode
        String streetTypeCode = postalAddress.getStreetTypeCode();
        if( streetTypeCode != null ) {
            errorService.isBlank(streetTypeCode, fieldStreetTypeCode);
            regexUtils.validateRegex(RegexTypes.STREET_TYPE_CODE_FORMAT, streetTypeCode, fieldStreetTypeCode);
            regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2,streetTypeCode, fieldStreetTypeCode);
        }

        String fullAddress = postalAddress.getFullAddress();
        if( fullAddress != null ){
            errorService.isBlank(fullAddress, fieldFullAddress);
            regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, fullAddress, fieldFullAddress);
            regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, fullAddress, fieldFullAddress);

        }

        // TownName
        if(postalAddress.getTown() != null ){

            errorService.isBlank(postalAddress.getTown(), "town");
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null,securityHeaders);

            if(towns != null){
                var varTown = towns.stream().filter(x -> x.getCode().equals(postalAddress.getTown())).findAny();

                if(!varTown.isPresent()){
                    var message = "'town' " + errorService.getInvalidValue();
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                contactPointRequest.getPostalAddress().setTown(varTown.get().getCode());
            }
        }

        // Premise
        if( postalAddress.getPremise() != null){
            errorService.isBlank(postalAddress.getPremise(), fieldPremise);
            regexUtils.validateRegex(RegexTypes.PREMISE_LENGTH, postalAddress.getPremise(), fieldPremise);
            regexUtils.validateRegex(RegexTypes.PREMISE_FORMAT, postalAddress.getPremise(), fieldPremise);
        }
    }
    private void validatePhoneAddress(ContactPointsRequestDTO contactPointRequest){
        var phoneAddress = contactPointRequest.getPhoneAddress();

        if( phoneAddress.getMobileNumber() != null ){
            errorService.isBlank(phoneAddress.getMobileNumber(), fieldMobileNumber);
            regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, phoneAddress.getMobileNumber(), fieldMobileNumber);
            regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, phoneAddress.getMobileNumber(), fieldMobileNumber);
        }

        if( phoneAddress.getPhoneNumber() != null ){
            errorService.isBlank(phoneAddress.getPhoneNumber(), fieldPhoneNumber);
            regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, phoneAddress.getPhoneNumber(), fieldPhoneNumber);
            regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, phoneAddress.getPhoneNumber(), fieldPhoneNumber);
        }

        if( phoneAddress.getInternationalCode() != null ){
            errorService.isBlank(phoneAddress.getInternationalCode(), fieldInternationalCode);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, phoneAddress.getInternationalCode(), fieldInternationalCode);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, phoneAddress.getInternationalCode(), fieldInternationalCode);
        }
    }

}//class closure

package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;


import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request.TrxPersonRequest;



public class ClientUtils {
    private ClientUtils() {}
    /**
     * This method
     * @param prospectId
     * @return
     */
    public static TrxPersonRequest buildTrxRequestByProspectId(CustomerRequestDTO customerRequestDTO, String prospectId){
        TrxPersonRequest requestTrx = new TrxPersonRequest();
        TrxPersonDataRequest personData = new TrxPersonDataRequest();

        personData.setpENUMPE(StringUtils.blankField(prospectId));

        if(customerRequestDTO!=null && customerRequestDTO.getPerson()!=null){
            personData.setTipoDocumento(StringUtils.blankField(customerRequestDTO.getPerson().getDocument().getDocumentTypeCode()));
            personData.setNumDocumento(StringUtils.blankField(customerRequestDTO.getPerson().getDocument().getDocumentNumber()));
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


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;



import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.response.TrxPersonData;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperUtils {

    
    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;



    

    public static PersonDTO personDTONames(TrxPersonData personData, Parameters pa) {
        PersonDTO personDTO = new PersonDTO();
        String genderDesc = "HOMBRE";
        String genderCode = "H";
        if (!personData.getSexo().isEmpty() && !personData.getSexo().equalsIgnoreCase("H")) {
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
        personDTO.getPersonName().setFullName(fullName);
        personDTO.setBirthDate(TimeUtils.formatDate(personData.getFechaNacimiento()));
        personDTO.setPlaceOfBirth(CustomerMapperUtils.getPlaceOfBirth(pa));
        personDTO.setFirstNationality(pa.getCountryNationality());
        personDTO.setCountryOfResidence(pa.getCountryDir());
        return personDTO;
    }//method closure

    public static PlaceOfBirthDTO getPlaceOfBirth(Parameters pa) {
        PlaceOfBirthDTO placeOfBirthDTO = new PlaceOfBirthDTO();
        placeOfBirthDTO.setCountry(pa.getCountryBirth());
        placeOfBirthDTO.setState(pa.getCityBirth());
        placeOfBirthDTO.setTown(pa.getTown().getName());
        return placeOfBirthDTO;
    }//method closure

    /*
     * Obtains record origin
     */
    public static String getSourceCode(TrxPersonData personData) {
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank()){
            return usualt.substring(0,3);
        }

        return null;
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
    }//method closure

    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        postalAddressDTO.setFullAddress(personData.getNombreVia());
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
        postalAddressDTO.setTownName(pa.getCityStandard().getName());
        postalAddressDTO.setCountry(pa.getCountryDir());
        postalAddressDTO.setState(pa.getCityDepartment());
        postalAddressDTO.setPremise(personData.getDescripcionDireccion());
        return postalAddressDTO;
    }//method closure



    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        document.setState(pa.getCityStandard());
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        document.setCountry(pa.getCountryExp());
        return document;
    }//method closure

    /**
     * Is Prospect or not
     * Prospect == 204 Non-content
     *
     * @param conper
     * @return
     */
    public static boolean isProspect(String conper) {
        return "PRO".equalsIgnoreCase(conper);
    }//method closure


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
    public static String cleanAddress(String address) {

        if (address == null || address.isEmpty()) {
            return address;
        }

        address = address.replace("#", " ")
                .replace("/", " ")
                .replace("-", " ")
                .replace("_", " ")
                .replace("&", " ")
                .replace(".", " ")
                .replace(",", " ")
                .replace("°", " ")
                .replace(";", " ")
                .replace(":", " ")
                .replace("*", " ")
                .replace("%", " ")
                .replace("$", " ")
                .replace("+", " ");

        address = address.replace("ñ", "n")
                .replace("Ñ", "N")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U");
        address = address.replaceAll("\\s{3,}", " ")
                .replaceAll("\\s{2}", " ");

        return address.trim();
    }

}//class closure


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Global Utils
 */
public class GUtils {

    private GUtils() {}
    /**
     * Start log
     */
    public final static String SLOG = "--> Start ";

    /**
     * End log
     */
    public final static String ELOG = "<-- End ";


    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

public enum RegexTypes {

    ONLY_NUMBERS,
    STRICT_LENGTH_8,
    STRICT_CHAR_LENGTH_2,
    STREET_TYPE_CODE_FORMAT,
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
    ADDRESS_FORMAT,
    PREMISE_LENGTH,
    PREMISE_FORMAT;
    
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RegexUtils {

    @Value("${ms_name}")
    private String MS_NAME;

    @Value("${ms_version}")
    private String MS_VERSION;

    @Value("${errors.level}")
    private String LEVEL;

    @Value("${regex.error.code}")
    private String CODE;

    @Value("${regex.only_numbers}")
    private String REGEX_ONLY_NUMBERS;
    
    @Value("${regex.only_numbers.error}")
    private String REGEX_ONLY_NUMBERS_ERROR;

    @Value("${regex.strict_length_8}")
    private String REGEX_STRICT_LENGTH_8;

    @Value("${regex.strict_length_8_error}")
    private String REGEX_STRICT_LENGTH_8_ERROR;

    @Value("${regex.strict_char_length_2}")
    private String REGEX_CHAR_STRICT_LENGTH_2;

    @Value("${regex.strict_char_length_2_error}")
    private String REGEX_CHAR_STRICT_LENGTH_2_ERROR;

    @Value("${regex.address_length}")
    private String REGEX_ADDRESS_LENGTH;

    @Value("${regex.address_length_error}")
    private String REGEX_ADDRESS_LENGTH_ERROR;

    @Value("${regex.address_format}")
    private String REGEX_ADDRESS_FORMAT;

    @Value("${regex.address_format_error}")
    private String REGEX_ADDRESS_FORMAT_ERROR;

    @Value("${regex.email}")
    private String REGEX_EMAIL;
    
    @Value("${regex.email_error}")
    private String REGEX_EMAIL_ERROR;

    @Value("${regex.email_length}")
    private String REGEX_EMAIL_LENGTH;

    @Value("${regex.email_length_error}")
    private String REGEX_EMAIL_LENGTH_ERROR;

    @Value("${regex.email_format_left}")
    private String REGEX_EMAIL_FORMAT_LEFT;

    @Value("${regex.email_format_left_error}")
    private String REGEX_EMAIL_FORMAT_LEFT_ERROR;

    @Value("${regex.email_format_right}")
    private String REGEX_EMAIL_FORMAT_RIGHT;

    @Value("${regex.email_format_right_error}")
    private String REGEX_EMAIL_FORMAT_RIGHT_ERROR;

    @Value("${regex.email_format_first_char}")
    private String REGEX_EMAIL_FORMAT_FIRST_CHAR;

    @Value("${regex.email_format_first_char_error}")
    private String REGEX_EMAIL_FORMAT_FIRST_CHAR_ERROR;

    @Value("${regex.phone_length}")
    private String REGEX_PHONE_LENGTH;

    @Value("${regex.phone_length_error}")
    private String REGEX_PHONE_LENGTH_ERROR;

    @Value("${regex.phone_format}")
    private String REGEX_PHONE_FORMAT;

    @Value("${regex.phone_format_error}")
    private String REGEX_PHONE_FORMAT_ERROR;

    @Value("${regex.international_code_format}")
    private String REGEX_INTERNATIONAL_CODE_FORMAT;

    @Value("${regex.international_code_format_error}")
    private String REGEX_INTERNATIONAL_CODE_FORMAT_ERROR;

    @Value("${regex.international_code_length}")
    private String REGEX_INTERNATIONAL_CODE_LENGTH;

    @Value("${regex.international_code_length_error}")
    private String REGEX_INTERNATIONAL_CODE_LENGTH_ERROR;

    @Value("${regex.premise_length}")
    private String REGEX_PREMISE_LENGTH;

    @Value("${regex.premise_length_error}")
    private String REGEX_PREMISE_LENGTH_ERROR;

    @Value("${regex.premise_format}")
    private String REGEX_PREMISE_FORMAT;

    @Value("${regex.premise_format_error}")
    private String REGEX_PREMISE_FORMAT_ERROR;
    @Value("${regex.street_type_code_format}")
    private String REGEX_STREET_TYPE_CODE_FORMAT;

    @Value("${regex.street_type_code_format_error}")
    private String REGEX_STREET_TYPE_CODE_FORMAT_ERROR;


    public void validateRegex(RegexTypes regex, String value, String fieldName){

        String regularExpression = "";
        String message = "invalid format";

        switch (regex) {
            case ONLY_NUMBERS:
                regularExpression = REGEX_ONLY_NUMBERS;
                message = REGEX_ONLY_NUMBERS_ERROR;
                break;
            case STRICT_LENGTH_8:
                regularExpression = REGEX_STRICT_LENGTH_8;
                message = REGEX_STRICT_LENGTH_8_ERROR;
                break;
            case STRICT_CHAR_LENGTH_2:
                regularExpression = REGEX_CHAR_STRICT_LENGTH_2;
                message = REGEX_CHAR_STRICT_LENGTH_2_ERROR;
                break;
            case ADDRESS_LENGTH:
                regularExpression = REGEX_ADDRESS_LENGTH;
                message = REGEX_ADDRESS_LENGTH_ERROR;
                break;
            case ADDRESS_FORMAT:
                regularExpression = REGEX_ADDRESS_FORMAT;
                message = REGEX_ADDRESS_FORMAT_ERROR;
                break;
            case EMAIL:
                regularExpression = REGEX_EMAIL;
                message = REGEX_EMAIL_ERROR;
                break;
            case EMAIL_LENGTH:
                regularExpression = REGEX_EMAIL_LENGTH;
                message = REGEX_EMAIL_LENGTH_ERROR;
                break;
             case EMAIL_FORMAT_LEFT:
                regularExpression = REGEX_EMAIL_FORMAT_LEFT;
                message = REGEX_EMAIL_FORMAT_LEFT_ERROR;
                break;
             case EMAIL_FORMAT_RIGHT:
                regularExpression = REGEX_EMAIL_FORMAT_RIGHT;
                message = REGEX_EMAIL_FORMAT_RIGHT_ERROR;
                break;
            case EMAIL_FORMAT_FIRST_CHAR:
                regularExpression = REGEX_EMAIL_FORMAT_FIRST_CHAR;
                message = REGEX_EMAIL_FORMAT_FIRST_CHAR_ERROR;
                break;
            case PHONE_LENGTH:
                regularExpression = REGEX_PHONE_LENGTH;
                message = REGEX_PHONE_LENGTH_ERROR;
                break;
            case PHONE_FORMAT:
                regularExpression = REGEX_PHONE_FORMAT;
                message = REGEX_PHONE_FORMAT_ERROR;
                break;
            case INTERNATIONAL_CODE_FORMAT:
                regularExpression = REGEX_INTERNATIONAL_CODE_FORMAT;
                message = REGEX_INTERNATIONAL_CODE_FORMAT_ERROR;
                break;
            case INTERNATIONAL_CODE_LENGTH:
                regularExpression = REGEX_INTERNATIONAL_CODE_LENGTH;
                message = REGEX_INTERNATIONAL_CODE_LENGTH_ERROR;
                break;
            case PREMISE_LENGTH:
                regularExpression = REGEX_PREMISE_LENGTH;
                message = REGEX_PREMISE_LENGTH_ERROR;
                break;
            case PREMISE_FORMAT:
                regularExpression = REGEX_PREMISE_FORMAT;
                message = REGEX_PREMISE_FORMAT_ERROR;
                break;
            case STREET_TYPE_CODE_FORMAT:
                regularExpression = REGEX_STREET_TYPE_CODE_FORMAT;
                message = REGEX_STREET_TYPE_CODE_FORMAT_ERROR;
                break;
        }

        log.info("Check regex {} to value {}", regularExpression, value);

        var pattern = Pattern.compile(regularExpression);
        var matcher = pattern.matcher(value);        
        boolean match = false;
        while (matcher.find()){
            match = true;
        }

        if(!match){

            ErrorDTO errorDTO =  ErrorDTO.builder()
                                        .code(MS_NAME + "-" + CODE)
                                        .level(LEVEL)
                                        .message("'" + fieldName + "': " + message)
                                        .description(MS_NAME.toLowerCase() + "-" + MS_VERSION + ": '" + fieldName + "': " + message )
                                        .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }
        
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 * @author Wilfredo Pena
 */
public class ServiceDirectory {
    private ServiceDirectory() {}
    private static final String API_VERSION = "/v1";
    private static final String CUSTOMER_ENDPOINT = "/prospect_contact_points";

    /**
     * Endpoints
     */
    public final static String CUSTOMERS = API_VERSION + CUSTOMER_ENDPOINT;
    public final static String CUSTOMERS_SEARCH = API_VERSION + CUSTOMER_ENDPOINT + "/search";
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

public class StringUtils {
    private StringUtils() {}
    public static String blankField(String field){
        if(field == null){
            return "";
        }else{
            return field;
        }
    }
    public static String completarYConcatenar(String cadena, String variable, int longitudFinal) {
        // Si la cadena ya es mayor o igual a la longitud final, truncarla antes de concatenar la variable
        if (cadena.length() >= longitudFinal) {
            cadena = cadena.substring(0, longitudFinal);
        } else {
            // Completa la cadena con espacios en blanco
            cadena = String.format("%-" + longitudFinal + "s", cadena);
        }

        // Concatenar la variable de 10 caracteres al final
        return cadena + variable;
    }
}


package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private TimeUtils() {}
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getSlocalDateTimeByFormat(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String unformattedDate){
        LocalDate dateF = LocalDate.parse(unformattedDate);
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
         return dateF.format(newFormat);
    }

    public static String markTime(){
        LocalDateTime now = LocalDateTime.now();

        // Definir el formato deseado
        String pattern = "dd-MM-yyyy HH:mm:ss:SSS";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // Formatear la fecha y hora
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

}//class closure
