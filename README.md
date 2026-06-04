Sessionsbnc-bsn049-mstrmdpstsettlmn > com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl > TrxSanbaServiceImpl.java
TrxSanbaServiceImpl.java
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.response.TrxBP31Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;

    private static final String BP31_SERVICE_ROUTE = "SBCDTTI01-ConsultaCDTDATTitular2654";
    private static final String BP13_SERVICE_ROUTE = "consultaDatosIPF";
    @Value("${service-route-trx.BP17}")
    private String bp17ServiceRoute;
    @Value("${service-route-trx.PEPF}")
    private String pepfServiceRoute;
    @Value("${service-route-trx.BP01}")
    private String bp01ServiceRoute;
    @Value("${service-route-trx.BP02}")
    private String bp02ServiceRoute;
    @Value("${service-route-trx.BP49}")
    private String bp49ServiceRoute;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;

    private String errorTrxMessage = "Error in TRX: {}";
    private String errorClientMessage = "client {}";
    private String errMessage = "err {}";
    private String noErrorMessage = "No error";
    private String setErrorMessage = "SET {}";

    @Override
    public TrxBP17Response trxBP17(TrxBP17Request request) {
        Response<TrxBP17Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP17TRX(request, bp17ServiceRoute, bp17ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP17Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP17Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBP17Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    public TrxBP31Response trxBP31(TrxBP31Request request) {
        Response<TrxBP31Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP31TRX(request, BP31_SERVICE_ROUTE, BP31_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP31Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP31Response err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            validateTrxBp31(err, objm, errorBody, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")) {

                return null;
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private void validateTrxBp31(TrxBP31Response err, ObjectMapper objm, String errorBody, List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxBP31Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : err.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }

        log.info(GUtils.ELOG + setErrorMessage, errosDtos);
    }

    @Override
    public TrxPEPFDataResponse trxPEPF(TrxPEPFDataRequest trxPEPFDataRequest) {
        Response<TrxPEPFDataResponse> responseApi = null;

        try {
            trxPEPFDataRequest.getCabecera().setCanal(channel);
            trxPEPFDataRequest.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callPEPF(trxPEPFDataRequest, pepfServiceRoute, pepfServiceRoute, mqRoute)
                    .execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxPEPFDataResponse responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();

            TrxPEPFDataResponse err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            validateTrxPEPF(objm, errorBody, err, errosDtos);
            log.info(GUtils.ELOG + setErrorMessage, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")) {
                return null;
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private void validateTrxPEPF(ObjectMapper objm, String errorBody, TrxPEPFDataResponse err,
            List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxPEPFDataResponse.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : getError.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }
    }

    //
    @Override
    public TrxBP13Response trxBP13(TrxBP13Request request) {
        Response<TrxBP13Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP13TRX(request, BP13_SERVICE_ROUTE, BP13_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP13Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP13Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBP13Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    @Override
    public TrxBp01Response trxBP01(TrxBp01Request trxBp01Request) {
        Response<TrxBp01Response> responseApi = null;

        try {
            trxBp01Request.getCabecera().setCanal(channel);
            trxBp01Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP01(trxBp01Request, bp01ServiceRoute, bp01ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBp01Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBp01Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBp01Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    @Override
    public TrxBp02Response trxBP02(TrxBp02Request trxBp02Request) {
        Response<TrxBp02Response> responseApi = null;

        try {
            trxBp02Request.getCabecera().setCanal(channel);
            trxBp02Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP02(trxBp02Request, bp02ServiceRoute, bp02ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBp02Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBp02Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBp02Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    //
    @Override
    public TrxBP49Response trxBP49(TrxBP49Request trxBp49Request) {

        Response<TrxBP49Response> responseApi = null;

        try {
            trxBp49Request.getCabecera().setCanal(channel);
            trxBp49Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP49(trxBp49Request, bp49ServiceRoute, bp49ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP49Response err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            trxbp49Validate(err, objm, errorBody, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found",
                        ErrorType.FUNCTIONAL);
            }
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    private void trxbp49Validate(TrxBP49Response err, ObjectMapper objm, String errorBody, List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxBP49Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : getError.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }

        log.info(GUtils.ELOG + setErrorMessage, errosDtos);
    }
}
Created with JaCoCo 0.8.12.202403310830
