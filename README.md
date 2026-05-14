package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks.BanksParametersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanksServiceImpl implements BanksService {
    final BanksApi banksApi;

    @Override
    public BanksDTO banksResponse(BanksParametersRequest request) {
        try {
            return banksApi.callBanks(request.getAuthorization(), request.getXSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }
}

package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.productdirectory.AmountRangeResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDirectoryServiceImpl implements ProductDirectoryService {
    
    final ProductDirectoryAPI productDirectoryAPI;
    
    @Value("${clients.product-directory.amount-range}")
    private String amountRangeUri;

    @Override
    public AmountRangeResponse amountRange(AmountRangeRequest request) {
        
        try {
            return productDirectoryAPI.callAmountRange(request.getProductId(), request.getAuthorization(), request.getXSantanderClientId()).execute().body();
            
        } catch (Exception e) {
            return null;
        }
    }

}



package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.termdepositparameters.TermDepositParametersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermDepositParametersServiceImpl implements TermDepositParametersService {

    final TermDepositParametersAPI termDepositParametersAPI;

    public TermDepositParametersResponse termDepositParameters(TermDepositParametersRequest request) {

        try {
            return termDepositParametersAPI.callTermDepositParameters(request.getProductId(), request.getAuthorization(), request.getXSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }

}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response.PemfvResponse;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.exception.error.ErrorType;

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

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.service.TrxSanbaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;
    
    private final String BP31_SERVICE_ROUTE = "SBCDTTI01-ConsultaCDTDATTitular2654";
    private final String BP13_SERVICE_ROUTE = "consultaDatosIPF";
    private static final String ERRORTRX = "Error in TRX: {}";
    private static final String CLIENT = "client {}";
    private static final String ERRLLAVES = "err {}";
    private static final String NOERRO = "No error";
    private static final String SETLLAVES = "SET {}";

    @Value("${service-route-trx.BP49}")
    private  String BP49_SERVICE_ROUTE;
    @Value("${service-route-trx.PEMFV}")
    private  String BP49_SERVICE_PEMFV;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;       

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;

    @Override
    public TrxBP49Response trxBP49(TrxBP49Request trxBp49Request) {
        Response<TrxBP49Response> responseApi = null;

        try {
            prepareRequest(trxBp49Request);
            responseApi = callTrx(trxBp49Request);

        } catch (RuntimeException e) {
            handleRuntimeException(e);

        } catch (IOException e) {
            handleIOException();

        } catch (Exception e) {
            handleOtherExceptions(e);
        }

        assert responseApi != null;
        return processResponse(responseApi);
    }
    private void prepareRequest(TrxBP49Request trxBp49Request) {
        trxBp49Request.getCabecera().setCanal(channel);
        trxBp49Request.getCabecera().getSesion().setUsuario(user);
    }
    private Response<TrxBP49Response> callTrx(TrxBP49Request trxBp49Request) throws IOException {
        return trxSanbaAPI.callBP49(trxBp49Request, BP49_SERVICE_ROUTE, BP49_SERVICE_ROUTE, mqRoute).execute();
    }
    private void handleRuntimeException(RuntimeException e) {
        log.info(ERRORTRX, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private void handleIOException() {
        throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private void handleOtherExceptions(Exception e) {
        log.info(ERRORTRX, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBP49Response processResponse(Response<TrxBP49Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;
        } else {
            return handleErrorResponse(responseApi);
        }
    }
    private TrxBP49Response handleErrorResponse(Response<TrxBP49Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle the IOException
        }

        ObjectMapper objm = new ObjectMapper();
        TrxBP49Response err = null;
        try {
            err = objm.readValue(errorBody, TrxBP49Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        log.info(GUtils.ELOG + ERRLLAVES, err != null ? err.getErrores() : NOERRO);
        List<ErrorDTO> errorsDtos = new ArrayList<>();

        assert err != null;
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);
        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found", ErrorType.FUNCTIONAL);
        }

        throw new ServiceException(HttpStatus.BAD_REQUEST, !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

    @Override
    public PemfvResponse trxPemfv(PemfvRequest pemfvRequest) {
        Response<PemfvResponse> responseApi = null;

        try {
            prepareRequest(pemfvRequest);
            responseApi = trxSanbaAPI.callPEMFV(pemfvRequest, BP49_SERVICE_PEMFV, BP49_SERVICE_PEMFV, mqRoute).execute();

        } catch (RuntimeException e) {
            throw handlePemfvRuntimeException(e);
        } catch (IOException e) {
            throw handlePemfvIOException();
        } catch (Exception e) {
            throw handlePemfvGenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handlePemfvSuccessResponse(responseApi);
        } else {
            return handlePemfvErrorResponse(responseApi);
        }
    }

    private void prepareRequest(PemfvRequest pemfvRequest) {
        pemfvRequest.getCabecera().setCanal(channel);
        pemfvRequest.getCabecera().getSesion().setUsuario(user);
    }

    private ServiceException handlePemfvRuntimeException(RuntimeException e) {
        log.info(ERRORTRX, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }

    private ServiceException handlePemfvIOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }

    private ServiceException handlePemfvGenericException(Exception e) {
        log.info(ERRORTRX, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }

    private PemfvResponse handlePemfvSuccessResponse(Response<PemfvResponse> responseApi) {
        PemfvResponse responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT , responseBody);
        return responseBody;
    }

    private PemfvResponse handlePemfvErrorResponse(Response<PemfvResponse> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }

        PemfvResponse err = null;
        try {
            ObjectMapper objm = new ObjectMapper();
            err = objm.readValue(errorBody, PemfvResponse.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = buildErrorDTOs(err);

        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found", ErrorType.FUNCTIONAL);
        }

        throw new ServiceException(HttpStatus.BAD_REQUEST,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

    private List<ErrorDTO> buildErrorDTOs(PemfvResponse err) {
        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }
        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);
        return errorsDtos;
    }


}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.config;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration.ApiEntry;
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


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.config;

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
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.integration.ApiEntry;

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

    private final IntegrationDataConfiguration properties;

    @Bean
    TrxSanbaAPI txrTransactionApi(){
        return getRetrofitConfig(properties.getByApi("sanba"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxSanbaAPI.class);
    }

    @Bean
    ProductDirectoryAPI productDirectoryAPI (){
        return getRetrofitConfig(properties.getByApi("product-directory"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ProductDirectoryAPI.class);
    }

    @Bean
    TermDepositParametersAPI termDepositParametersAPI (){
        return getRetrofitConfig(properties.getByApi("term-deposit-parameters"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TermDepositParametersAPI.class);
    }
    
    @Bean
    BanksApi banksAPI (){
        return getRetrofitConfig(properties.getByApi("banks"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(BanksApi.class);
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

        return builder.build();
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
