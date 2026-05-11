package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.impl;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.api.ParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.GeographiesParametersResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.GUtils;
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
                error.setDescription(ErrorDictionary.MS_NAME + " - " + responseApi);
                throw new ServiceException(HttpStatus.BAD_REQUEST, error);
            }
            if(responseApi.body().getParameters().isEmpty()){
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersNoEntry());
            }
        } catch(RuntimeException e){
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersResponse());        
        } catch(IOException e){            
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.getMsParametersNetworkConection());        
        } catch (Exception e){
            log.info("Error in ParametersAPIImpl: {}", e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMsParametersGeneral());
        }
        log.info(GUtils.ELOG + "client get parameter id {}", parameterId);
        return responseApi.body().getParameters();
    }//method closure

}//class closure
