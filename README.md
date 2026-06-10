package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorType;

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
    private final String errorTrx = "Error in TRX: {}";
    private final String client = "client {}";
    private final String errLlaves = "err {}";
    private final String noError = "No error";
    private final String setLlaves = "SET {}";
    private final String cdtDtaNoExiste = "CDT / DAT NO EXISTE.";
    private static final String RUNTIMEEXCEPTION = "Runtime exception: ";
    private static final String IOEXCEPTION = "IOException: ";
    private static final String EXCEPTION = "Exception: ";

    @Value("${service-route-trx.BP49}")
    private  String BP49_SERVICE_ROUTE;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;


    //startTrxBP13
    @Override
    public TrxBP13Response trxBP13(TrxBP13Request request) {
        Response<TrxBP13Response> responseApi = null;

        try {
            responseApi = callBP13Trx(request);
            return handleBP13Response(responseApi);
        } catch (RuntimeException e) {
            log.error(RUNTIMEEXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.MS_SANBA_TRX_ERROR);
        } catch (IOException e) {
            log.error(IOEXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }
    protected Response<TrxBP13Response> callBP13Trx(TrxBP13Request request) throws IOException {
        request.getCabecera().setCanal(channel);
        request.getCabecera().getSesion().setUsuario(user);
        return trxSanbaAPI.callBP13TRX(request, BP13_SERVICE_ROUTE, BP13_SERVICE_ROUTE, mqRoute).execute();
    }
    protected TrxBP13Response handleBP13Response(Response<TrxBP13Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP13Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + client, responseBody);
            return responseBody;
        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                // Log or handle the exception
            }

            ObjectMapper objm = new ObjectMapper();
            TrxBP13Response err;
            try {
                err = objm.readValue(errorBody, TrxBP13Response.class);
            } catch (IOException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }

            log.info(GUtils.ELOG + errLlaves, err != null ? err.getErrores() : noError);
            assert err != null;
            List<ErrorDTO> errorsDtos = buildErrorDTOs(err);

            log.info(GUtils.ELOG + setLlaves, errorsDtos);

            if (hasNotFound13Error(errorsDtos)) {
                throw new ServiceException(HttpStatus.NOT_FOUND, cdtDtaNoExiste);
            }

            throw new ServiceException(HttpStatus.CONFLICT, getFirst13Error(errorsDtos));
        }
    }
    protected List<ErrorDTO> buildErrorDTOs(TrxBP13Response err) {
        List<ErrorDTO> errorsDtos = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            if (dtoEr.getMensaje().equals(cdtDtaNoExiste) || dtoEr.getMensaje().equals("NO EXISTE.")) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            } else {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }
        return errorsDtos;
    }
    protected boolean hasNotFound13Error(List<ErrorDTO> errorsDtos) {
        return errorsDtos.stream().anyMatch(error -> error.getMessage().contains(cdtDtaNoExiste));
    }
    protected ErrorDTO getFirst13Error(List<ErrorDTO> errorsDtos) {
        return !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR;
    }
    //endTrxBP13


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
    protected void prepareRequest(TrxBP49Request trxBp49Request) {
        trxBp49Request.getCabecera().setCanal(channel);
        trxBp49Request.getCabecera().getSesion().setUsuario(user);
    }
    protected Response<TrxBP49Response> callTrx(TrxBP49Request trxBp49Request) throws IOException {
        return trxSanbaAPI.callBP49(trxBp49Request, BP49_SERVICE_ROUTE, BP49_SERVICE_ROUTE, mqRoute).execute();
    }
    protected void handleRuntimeException(RuntimeException e) {
        log.info(errorTrx, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    protected void handleIOException() {
        throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    protected void handleOtherExceptions(Exception e) {
        log.info(errorTrx, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.CONFLICT, error);
    }
    protected TrxBP49Response processResponse(Response<TrxBP49Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + client, responseBody);
            return responseBody;
        } else {
            return handleErrorResponse(responseApi);
        }
    }
    protected TrxBP49Response handleErrorResponse(Response<TrxBP49Response> responseApi) {
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

        log.info(GUtils.ELOG + errLlaves, err != null ? err.getErrores() : noError);
        List<ErrorDTO> errorsDtos = new ArrayList<>();

        assert err != null;
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }

        log.info(GUtils.ELOG + setLlaves, errorsDtos);
        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found", ErrorType.FUNCTIONAL);
        }

        throw new ServiceException(HttpStatus.BAD_REQUEST, !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

}
