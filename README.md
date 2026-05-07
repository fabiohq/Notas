package com.santander.bnc.bsn049.bncbsn049msprospects.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CiudadComparisonRequest {
    private String ciudadIngresada;
    private String ciudadServicio;
    
    public String getCiudadIngresada() {
        return ciudadIngresada;
    }
    public void setCiudadIngresada(String ciudadIngresada) {
        this.ciudadIngresada = ciudadIngresada;
    }
    public String getCiudadServicio() {
        return ciudadServicio;
    }
    public void setCiudadServicio(String ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    
}

package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ContextAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.integration.context.ContextResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public static final String PRODUCT = "cdt";

    @Override
    public void putContext(String key, Object object) {
        try{
            log.info(GUtils.SLOG+"client putContext KEY={}",key);
            ContextRequest request = new ContextRequest();
            request.setProduct(PRODUCT);
            request.setValue(object);
            request.setKey(key);
            contextAPI.putCache(request).enqueue(new Callback<ContextResponse>() {

                @Override
                public void onResponse(Call<ContextResponse> call, Response<ContextResponse> response) {
                    log.info("put cache ok");
                }

                @Override
                public void onFailure(Call<ContextResponse> call, Throwable t) {
                    log.info("error put cache: {}", t.getMessage());
                }
                
            });
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





package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
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
    public List<DataListDTO> getParameter(String parameterId, String valueCode,String authorization,String xSantanderClientId) {
       Response<GeographiesParametersResponseDTO> responseApi;
        try {
            responseApi = parametersAPI.getParameter(parameterId,valueCode,authorization,xSantanderClientId).execute();
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
            log.error("Runtime Exception calling parameters: {}", e.getMessage()); 
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse() );        
        } catch(IOException e){  
            log.error("IOException calling parameters: {}", e.getMessage());           
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConnection());        
        } catch (Exception e){
            log.info("Unhandled exception calling parameters: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure



package com.santander.bnc.bsn049.bncbsn049msprospects.client.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
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
    public List<DataListDTO> getParameter(String parameterId, String valueCode,String authorization,String xSantanderClientId) {
       Response<GeographiesParametersResponseDTO> responseApi;
        try {
            responseApi = parametersAPI.getParameter(parameterId,valueCode,authorization,xSantanderClientId).execute();
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
            log.error("Runtime Exception calling parameters: {}", e.getMessage()); 
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse() );        
        } catch(IOException e){  
            log.error("IOException calling parameters: {}", e.getMessage());           
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConnection());        
        } catch (Exception e){
            log.info("Unhandled exception calling parameters: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure
