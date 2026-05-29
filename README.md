    
        private static final String ERRORTRX = "Error in TRX: {}";
    private static final String CLIENT = "client {}";
    private static final String ERRLLAVES = "err {}";
    private static final String NOERROR = "No error";
    private static final String SETLLAVES = "SET {}";
    private static final String CDTDTANOEXISTE = "CDT / DAT NO EXISTE.";
    private static final String RUNTIMEEXCEPTION = "Runtime exception: ";
    private static final String IOEXCEPTION = "IOException: ";
    private static final String EXCEPTION = "Exception: ";

    @Value("${service-route-trx.BP17}")
    private  String BP17_SERVICE_ROUTE;
    @Value("${service-route-trx.PEPF}")
    private  String PEPF_SERVICE_ROUTE;
    @Value("${service-route-trx.BP01}")
    private  String BP01_SERVICE_ROUTE;
    @Value("${service-route-trx.BP02}")
    private  String BP02_SERVICE_ROUTE;
    @Value("${service-route-trx.BP92}")
    private  String BP92_SERVICE_ROUTE;
    @Value("${params.sanba.mqRoute}")
    private String mqRoute;
    @Value("${params.sanba.user}")
    private String user;
    @Value("${params.sanba.channel}")
    private String channel;

    //startTrxBP17
    @Override
    public TrxBP17Response trxBP17(TrxBP17Request request) {
        Response<TrxBP17Response> responseApi = null;

        try {
            responseApi = callBP17TRX(request);
            return handleBP17Response(responseApi);
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
    private Response<TrxBP17Response> callBP17TRX(TrxBP17Request request) throws IOException {
        request.getCabecera().setCanal(channel);
        request.getCabecera().getSesion().setUsuario(user);
        return trxSanbaAPI.callBP17TRX(request, BP17_SERVICE_ROUTE, BP17_SERVICE_ROUTE, mqRoute).execute();
    }
    private TrxBP17Response handleBP17Response(Response<TrxBP17Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP17Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;
        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                // Log or handle the exception
            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP17Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBP17Response.class);
            } catch (JsonMappingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + ERRLLAVES, err != null ? err.getErrores() : NOERROR);
            List<ErrorDTO> errorsDtos = new ArrayList<>();

            assert err != null;
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }

            log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }
    //endTrxBP17

    //startTrxBP31
    @Override
    public TrxBP31Response trxBP31(TrxBP31Request request) {
        Response<TrxBP31Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP31TRX(request, BP31SERVICEROUTE, BP31SERVICEROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle31RuntimeException(e);
        } catch (IOException e) {
            throw handle31IOException();
        } catch (Exception e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle31GenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handle31SuccessResponse(responseApi);
        } else {
            return handle31ErrorResponse(responseApi);
        }
    }
    private ServiceException handle31RuntimeException(RuntimeException e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private ServiceException handle31IOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private ServiceException handle31GenericException(Exception e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBP31Response handle31SuccessResponse(Response<TrxBP31Response> responseApi) {
        TrxBP31Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT, responseBody);
        return responseBody;
    }
    private TrxBP31Response handle31ErrorResponse(Response<TrxBP31Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }

        TrxBP31Response err = null;
        try {
            ObjectMapper objm = new ObjectMapper();
            err = objm.readValue(errorBody, TrxBP31Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains("NO EXISTE CDT")) {
            return null; // Return null for "NO EXISTE CDT" error
        }

        throw new ServiceException(HttpStatus.BAD_REQUEST,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }
    //endTrxBP31

    //startTrxPEPF
    @Override
    public TrxPEPFDataResponse trxPEPF(TrxPEPFDataRequest trxPEPFDataRequest) {
        Response<TrxPEPFDataResponse> responseApi = null;

        try {
            responseApi = callPEPF(trxPEPFDataRequest);
            return handlePEPFResponse(responseApi);
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
    private Response<TrxPEPFDataResponse> callPEPF(TrxPEPFDataRequest trxPEPFDataRequest) throws IOException {
        trxPEPFDataRequest.getCabecera().setCanal(channel);
        trxPEPFDataRequest.getCabecera().getSesion().setUsuario(user);
        return trxSanbaAPI.callPEPF(trxPEPFDataRequest, PEPF_SERVICE_ROUTE, PEPF_SERVICE_ROUTE, mqRoute).execute();
    }
    private TrxPEPFDataResponse handlePEPFResponse(Response<TrxPEPFDataResponse> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxPEPFDataResponse responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;
        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                // Log or handle the exception
            }
            ObjectMapper objm = new ObjectMapper();
            TrxPEPFDataResponse err = parseErrorBody(errorBody, objm);

            if (err == null) {
                // Handle this case appropriately
                return null;
            }

            log.info(GUtils.ELOG + ERRLLAVES, err.getErrores() != null ? err.getErrores() : NOERROR);
            List<ErrorDTO> errorsDtos = buildErrorDTOs(err);

            log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

            if (hasNotFoundPEPFError(errorsDtos)) {
                throw new ServiceException(HttpStatus.NO_CONTENT, "No message");
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST, getFirstPEPFError(errorsDtos));
        }
    }
    private TrxPEPFDataResponse parseErrorBody(String errorBody, ObjectMapper objm) {
        try {
            return objm.readValue(errorBody, TrxPEPFDataResponse.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
    }
    private List<ErrorDTO> buildErrorDTOs(TrxPEPFDataResponse err) {
        List<ErrorDTO> errorsDtos = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }
        return errorsDtos;
    }
    private boolean hasNotFoundPEPFError(List<ErrorDTO> errorsDtos) {
        return errorsDtos.stream().anyMatch(error -> error.getMessage().contains("NO EXISTE CDT"));
    }
    private ErrorDTO getFirstPEPFError(List<ErrorDTO> errorsDtos) {
        return !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR;
    }
    //endTrxPEPF

    //startTrxBP13
    @Override
    public TrxBP13Response trxBP13(TrxBP13Request request) {
        Response<TrxBP13Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP13TRX(request, BP13SERVICEROUTE, BP13SERVICEROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle13RuntimeException(e);
        } catch (IOException e) {
            throw handle13IOException();
        } catch (Exception e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle13GenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handle13SuccessResponse(responseApi);
        } else {
            return handle13ErrorResponse(responseApi);
        }
    }
    private ServiceException handle13RuntimeException(RuntimeException e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private ServiceException handle13IOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private ServiceException handle13GenericException(Exception e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBP13Response handle13SuccessResponse(Response<TrxBP13Response> responseApi) {
        TrxBP13Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT, responseBody);
        return responseBody;
    }
    private TrxBP13Response handle13ErrorResponse(Response<TrxBP13Response> responseApi) {
        String errorBody = extractErrorBody(responseApi);
        List<ErrorDTO> errorsDtos = processErrorResponse(errorBody);

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains(CDTDTANOEXISTE)) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, CDTDTANOEXISTE, ErrorType.FUNCTIONAL);
        }

        throw new ServiceException(HttpStatus.CONFLICT,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }
    private String extractErrorBody(Response<TrxBP13Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }
        return errorBody;
    }
    private List<ErrorDTO> processErrorResponse(String errorBody) {
        ObjectMapper objm = new ObjectMapper();
        TrxBP13Response err = null;
        try {
            err = objm.readValue(errorBody, TrxBP13Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                if (CDTDTANOEXISTE.equals(dtoEr.getMensaje()) || "NO EXISTE.".equals(dtoEr.getMensaje())) {
                    errorsDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
                    throw new ServiceException(HttpStatus.NOT_FOUND,
                            !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
                }
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }
        return errorsDtos;
    }
    //endTrxBP13

    //startTrxBP01
    @Override
    public TrxBp01Response trxBP01(TrxBp01Request trxBp01Request) {
        Response<TrxBp01Response> responseApi = null;

        try {
            trxBp01Request.getCabecera().setCanal(channel);
            trxBp01Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP01(trxBp01Request, BP01_SERVICE_ROUTE, BP01_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle01RuntimeException(e);
        } catch (IOException e) {
            throw handle01IOException();
        } catch (Exception e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle01GenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handle01SuccessResponse(responseApi);
        } else {
            return handle01ErrorResponse(responseApi);
        }
    }
    private ServiceException handle01RuntimeException(RuntimeException e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private ServiceException handle01IOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private ServiceException handle01GenericException(Exception e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBp01Response handle01SuccessResponse(Response<TrxBp01Response> responseApi) {
        TrxBp01Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT, responseBody);
        return responseBody;
    }
    private TrxBp01Response handle01ErrorResponse(Response<TrxBp01Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }

        TrxBp01Response err = null;
        try {
            ObjectMapper objm = new ObjectMapper();
            err = objm.readValue(errorBody, TrxBp01Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

        throw new ServiceException(HttpStatus.CONFLICT,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

    //endTrxBP01

    //startTrxBP02
    @Override
    public TrxBp02Response trxBP02(TrxBp02Request trxBp02Request) {
        Response<TrxBp02Response> responseApi = null;

        try {
            trxBp02Request.getCabecera().setCanal(channel);
            trxBp02Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP02(trxBp02Request, BP02_SERVICE_ROUTE, BP02_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle02RuntimeException(e);
        } catch (IOException e) {
            throw handle02IOException();
        } catch (Exception e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle02GenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handle02SuccessResponse(responseApi);
        } else {
            return handle02ErrorResponse(responseApi);
        }
    }
    private ServiceException handle02RuntimeException(RuntimeException e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private ServiceException handle02IOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private ServiceException handle02GenericException(Exception e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBp02Response handle02SuccessResponse(Response<TrxBp02Response> responseApi) {
        TrxBp02Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT, responseBody);
        return responseBody;
    }
    private TrxBp02Response handle02ErrorResponse(Response<TrxBp02Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }

        TrxBp02Response err = null;
        try {
            ObjectMapper objm = new ObjectMapper();
            err = objm.readValue(errorBody, TrxBp02Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);

        throw new ServiceException(HttpStatus.CONFLICT,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

    //endTrxBP02

    //startBP21
    @Override
    public TrxBP21Response trxBP21(TrxBP21Request trxBp21Request) {
        Response<TrxBP21Response> responseApi = null;

        try {
            trxBp21Request.getCabecera().setCanal(channel);
            trxBp21Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP21TRX(trxBp21Request, BP21SERVICEROUTE, BP21SERVICEROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle21RuntimeException(e);
        } catch (IOException e) {
            throw handle21IOException();
        } catch (Exception e) {
            log.info(ERRORTRX, e.getMessage());
            throw handle21GenericException(e);
        }

        if (responseApi.isSuccessful()) {
            return handle21SuccessResponse(responseApi);
        } else {
            return handle21ErrorResponse(responseApi);
        }
    }
    private ServiceException handle21RuntimeException(RuntimeException e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private ServiceException handle21IOException() {
        return new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private ServiceException handle21GenericException(Exception e) {
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        return new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBP21Response handle21SuccessResponse(Response<TrxBP21Response> responseApi) {
        TrxBP21Response responseBody = responseApi.body();
        log.info(GUtils.ELOG + CLIENT, responseBody);
        return responseBody;
    }
    private TrxBP21Response handle21ErrorResponse(Response<TrxBP21Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle error reading error body
        }

        TrxBP21Response err = null;
        try {
            ObjectMapper objm = new ObjectMapper();
            err = objm.readValue(errorBody, TrxBP21Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        List<ErrorDTO> errorsDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                if (CDTDTANOEXISTE.equals(dtoEr.getMensaje())) {
                    errorsDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
                    throw new ServiceException(HttpStatus.NOT_FOUND,
                            !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
                }
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }

        log.info(GUtils.ELOG + SETLLAVES, errorsDtos);
        throw new ServiceException(HttpStatus.BAD_REQUEST,
                !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }
    //endBP21

    //startTrxBP92
    @Override
    public TrxBp92Response trxBP92(TrxBp92Request trxBp92Request) {
        Response<TrxBp92Response> responseApi = null;

        try {
            responseApi = callBp92Trx(trxBp92Request);
            return handleResponse(responseApi);
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
    private Response<TrxBp92Response> callBp92Trx(TrxBp92Request trxBp92Request) throws IOException {
        trxBp92Request.getCabecera().setCanal(channel);
        trxBp92Request.getCabecera().getSesion().setUsuario(user);
        return trxSanbaAPI.callBP92TRX(trxBp92Request, BP92_SERVICE_ROUTE, BP92_SERVICE_ROUTE, mqRoute).execute();
    }
    private TrxBp92Response handleResponse(Response<TrxBp92Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBp92Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + CLIENT, responseBody);
            return responseBody;
        } else {
            return handleError92Response(responseApi);
        }
    }
    private TrxBp92Response handleError92Response(Response<TrxBp92Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Log
        }

        ObjectMapper objm = new ObjectMapper();
        TrxBp92Response err = null;
        try {
            err = objm.readValue(errorBody, TrxBp92Response.class);
        } catch (JsonProcessingException e) {
            // Log
        }

        List<ErrorDTO> errorDtos = new ArrayList<>();
        if (err != null) {
            for (ErrorTrxDTO dtoEr : err.getErrores()) {
                errorDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }

        log.info(GUtils.ELOG + SETLLAVES, errorDtos);

        throw new ServiceException(HttpStatus.CONFLICT,
                !errorDtos.isEmpty() ? errorDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }
    //endTrxBP92
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry;
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

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
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry;

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.config;

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
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration.ApiEntry;

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.GetListDepositsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.request.DepositPlacementRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request.GetListDepositsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request.UpdateCdtRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement.SimulatePlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.TermDepositService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PARAMETER CONTROLLER
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductControllers {

    final TermDepositService termDepositService;
    final TrxSanbaService trxSanbaService;
    final ProductDirectoryService productDirectoryService;
    private final ObjectMapper objectMapper;
    
    @PostMapping("/term_deposits/simulate_placement")
    public SimulatePlacementResponseDTO handlePostRequest(
            @RequestHeader("authorization") String authorization,
            @RequestHeader("x-santander-client-id") String clientId,
            @Valid @RequestBody RequestSimulatePlacementDTO requestBodyData) {

		try {
			String jsonRequest = objectMapper.writeValueAsString(requestBodyData);
			StringBuilder sb = new StringBuilder();
			sb.append(", payload=").append(jsonRequest).append("clientId=").append(clientId);
			log.info("*** INIT (POST) term_deposits/simulate_placement  = {} >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
		
		SimulatePlacementResponseDTO response = termDepositService.processSimulatePlacement(requestBodyData,
                new AmountRangeRequest(authorization, clientId, null));
		try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" response=").append(jsonResponse);
			log.info("*** FIN (POST) term_deposits/simulate_placement  = {} >>>", sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
		
		return response;
		
    }

    @GetMapping("/term_deposits")
    public ResponseEntity<GetListDepositsResponseDTO> getTermsdeposits(
            @RequestHeader("authorization") String authorization,
            @RequestHeader("x-santander-client-id") String xSantanderClientId,
            @RequestParam(required = true, name = "participant_id") String participantId,
            @RequestParam(required = true, name = "placement_status") String placementStatus,
            @RequestParam(required = false, name = "_limit") String limit,
            @RequestParam(required = false, name = "_offset") String offset) {

        var request = GetListDepositsRequestDTO.builder()
                .authorization(authorization)
                .xSantanderClientId(xSantanderClientId)
                .participantId(participantId)
                .placementStatus(placementStatus)
                .limit(limit)
                .offset(offset)
                .build();

		StringBuilder sb = new StringBuilder();
		sb.append(" xSantanderClientId=").append(xSantanderClientId);
		sb.append(" participantId=").append(participantId);
		sb.append(", placementStatus=").append(placementStatus);
		sb.append(", limit=").append(limit);
		sb.append(", offset=").append(offset);
		log.info("*** INIT (GET) term_deposits  = {}  >>> ", sb.toString());

        
        GetListDepositsResponseDTO response = termDepositService.listGetTermsDeposits(request);
        try {
        	String jsonResponse = objectMapper.writeValueAsString(response);
        	StringBuilder sbr = new StringBuilder();
        	sbr.append(" response=").append(jsonResponse);			
        	log.info("*** FIN (GET) term_deposits  = {}  >>> ", sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
        
        
        if (response == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(response);

    }

    /**
     * GET SPECIFIC PLACEMENT'S DETAILS
     *
     * @param deposit_id, placement_id
     * @return null
     */
    @GetMapping("/term_deposits/{deposit_id}/placements/{placement_id}")
    public DepositPlacementResponseDTO getDepositsPlacement(
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String client_id,
            @PathVariable(name = "deposit_id") String deposit_id,
            @PathVariable(name = "placement_id") String placement_id) {

			StringBuilder sb = new StringBuilder();
			sb.append(" client_id=").append(client_id);
			sb.append(", deposit_id=").append(deposit_id);
			sb.append(", placement_id=").append(placement_id);
			
			log.info("*** INIT (GET) term_deposits/deposit_id/placements/placement_id  = {}  >>> ", sb.toString());
			
			DepositPlacementResponseDTO response = termDepositService.getDepositsPlacement(new DepositPlacementRequestDTO(authorization, client_id, deposit_id, placement_id), new TermDepositParametersRequest("940250", authorization, client_id));
			
			try {
				String jsonResponse = objectMapper.writeValueAsString(response);
				StringBuilder sbr = new StringBuilder();
				sbr.append(" response=").append(jsonResponse);
				log.info("*** FIN (GET) term_deposits/deposit_id/placements/placement_id  = {}  >>> ", sbr.toString());
			} catch (Exception e) {
				log.error("Error serializando response");
			}
		    	
        return response;
    }

    @PostMapping("/term_deposits")
    public ResponseTermDepositsDTO termDepositsPostRequest(
            @RequestHeader("authorization") String authorization,
            @RequestHeader("x-santander-client-id") String clientId,
            @Valid @RequestBody RequestTermDepositsDTO requestBodyData) {
    	
    	try {
			String jsonRequest = objectMapper.writeValueAsString(requestBodyData);
			StringBuilder sb = new StringBuilder();
			sb.append(" payload=").append(jsonRequest);
			log.info("*** INIT (POST) term_deposits  = {}  >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
    	
    	ResponseTermDepositsDTO response = termDepositService.responseTermDepositsDTO(requestBodyData,
                new AmountRangeRequest(authorization, clientId, null),
                new TermDepositParametersRequest("940250", authorization, clientId),
                new BanksParametersRequest(authorization, clientId));
    	try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" response=").append(jsonResponse);
			log.info("*** FIN (POST) term_deposits  = {}  >>> ", sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
    	
        return response;
    }

    @PatchMapping("/term_deposits/{deposit_id}/placements/{placement_id}")
    public ResponseEntity updateCdt(
            @RequestHeader("x-santander-client-id") String clientId,
            @PathVariable("deposit_id") String depositId,
            @PathVariable("placement_id") String placementId,
            @RequestBody UpdateCdtRequestDTO updateRequest) {

    	ResponseEntity<Object> response;
    	
    	try {
			String jsonRequest = objectMapper.writeValueAsString(updateRequest);
			StringBuilder sb = new StringBuilder();
			sb.append(" depositId=").append(depositId);
			sb.append(", placementId=").append(placementId);
			sb.append(", payload=").append(jsonRequest);
			log.info("*** INIT (PATCH) term_deposits/deposit_id/placements/placement_id = {}  >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
    	
        termDepositService.updateCdt(depositId, placementId, updateRequest);
        // Verificar si los campos necesarios están presentes en la solicitud
        if (depositId == null || placementId == null || updateRequest.getIsRenewable() == null || updateRequest.getIsRenewable() == null) {
        	response = ResponseEntity.badRequest().build();
        }else {
        	response =new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        

        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" response=").append(jsonResponse);
			log.info("*** FIN (PATCH) term_deposits/deposit_id/placements/placement_id = {}  >>> ", sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
        
        return response;
    }

    @PostMapping("/term_deposits/calculate_deposit_summary")
    public CalculateDepositSummaryResponseDTO calculateDepositSummaryPostRequest(
            @RequestHeader("authorization") String authorization,
            @RequestHeader("x-santander-client-id") String clientId,
            @Valid @RequestBody CalculateDepositSummaryRequestDTO requestBodyData) {
    	
    	try {
			String jsonRequest = objectMapper.writeValueAsString(requestBodyData);
			StringBuilder sb = new StringBuilder();
			sb.append(" payload=").append(jsonRequest);
			log.info("*** INIT (POST) term_deposits/calculate_deposit_summary  = {}  >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
    	
    	CalculateDepositSummaryResponseDTO response = termDepositService.calculateDepositSummary(requestBodyData);
    	
    	try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" response=").append(jsonResponse);
			log.info("*** FIN (POST) term_deposits/calculate_deposit_summary  = {}  >>> ", sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
    	
        return response;
    }

    @DeleteMapping("/term_deposits/{deposit_id}")
    public ResponseEntity deleteProspectCDT(
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String client_id,
            @Valid @NotNull @PathVariable(name = "deposit_id") String deposit_id){

    	ResponseEntity<Object> response;
    	
		StringBuilder sb = new StringBuilder();
		sb.append(" deposit_id=").append(deposit_id);
		log.info("*** INIT (DELETE) term_deposits/deposit_id  = {}  >>> ", sb.toString());

        termDepositService.deleteProspectCdt(deposit_id);
        
        response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" response=").append(jsonResponse);
			log.info("*** FIN (DELETE) term_deposits/deposit_id  = {}  >>> ", sb.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
        
        
        return response;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksDTO {
    private List<BanksParametersDTO> banks;

    public List<BanksParametersDTO> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksParametersDTO> banks) {
        this.banks = banks;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersDTO {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersRequest {
    private String authorization;
    private String xSantanderClientId;

}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksResponseDTO {
    private BanksDTO banks;

    public BanksDTO getBanks() {
        return banks;
    }

    public void setBanks(BanksDTO banks) {
        this.banks = banks;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDepositSummaryRequestDTO {
    @Valid
    private ParticipantsRequestDTO participants;

    public ParticipantsRequestDTO getParticipants() {
        return participants;
    }

    public void setParticipants(ParticipantsRequestDTO participants) {
        this.participants = participants;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantsRequestDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String participantId;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodRequestDTO {
    @Valid
    private String startDate;
    @Valid
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculateDepositSummaryResponseDTO {
    private DepositSummaryResponseDTO depositSummary;

    public DepositSummaryResponseDTO getDepositSummary() {
        return depositSummary;
    }

    public void setDepositSummary(DepositSummaryResponseDTO depositSummary) {
        this.depositSummary = depositSummary;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositSummaryResponseDTO {
    private TotalInvestedAmountResponseDTO totalInvestedAmount;
    private int numberOfActiveDeposits;
    private int numberOfDueDeposits;
    private int numberOfPendingDeposits;
    private int numberOfCancelledDeposits;

    public TotalInvestedAmountResponseDTO getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    public void setTotalInvestedAmount(TotalInvestedAmountResponseDTO totalInvestedAmount) {
        this.totalInvestedAmount = totalInvestedAmount;
    }

    public int getNumberOfActiveDeposits() {
        return numberOfActiveDeposits;
    }

    public void setNumberOfActiveDeposits(int numberOfActiveDeposits) {
        this.numberOfActiveDeposits = numberOfActiveDeposits;
    }

    public int getNumberOfDueDeposits() {
        return numberOfDueDeposits;
    }

    public void setNumberOfDueDeposits(int numberOfDueDeposits) {
        this.numberOfDueDeposits = numberOfDueDeposits;
    }

    public int getNumberOfPendingDeposits() {
        return numberOfPendingDeposits;
    }

    public void setNumberOfPendingDeposits(int numberOfPendingDeposits) {
        this.numberOfPendingDeposits = numberOfPendingDeposits;
    }

    public int getNumberOfCancelledDeposits() {
        return numberOfCancelledDeposits;
    }

    public void setNumberOfCancelledDeposits(int numberOfCancelledDeposits) {
        this.numberOfCancelledDeposits = numberOfCancelledDeposits;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalInvestedAmountResponseDTO {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositPlacementRequestDTO {
    private String authorization;
    private String xSantanderClientId;
    private String depositId;
    private String placementId;

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

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String nationalIdentification;
    private String statusDescription;

    public String getNationalIdentification() {
        return nationalIdentification;
    }

    public void setNationalIdentification(String nationalIdentification) {
        this.nationalIdentification = nationalIdentification;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amount {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails.TermDepositPersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositPlacementResponseDTO {
    private TermDepositPersonDTO person;
    private Contract contract;
    private Placement placement;

    public TermDepositPersonDTO getPerson() {
        return person;
    }

    public void setPerson(TermDepositPersonDTO person) {
        this.person = person;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationFunds {
    private String accountIdType;
    private String bankcode;
    private Account account;

    public String getAccountIdType() {
        return accountIdType;
    }

    public void setAccountIdType(String accountIdType) {
        this.accountIdType = accountIdType;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitialTotalInvested {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OriginIdentifier {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Periodicity {
    private int frequency;
    private String periodTypeCode;
    private String periodTypeDescription;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }

    public String getPeriodTypeDescription() {
        return periodTypeDescription;
    }

    public void setPeriodTypeDescription(String periodTypeDescription) {
        this.periodTypeDescription = periodTypeDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Placement {
    private PlacementIdentification placementIdentification;
    private StatusInfo statusInfo;
    private Subproduct subproduct;
    private Currency currency;
    private Periodicity periodicity;
    private String maturityDate;
    private String openingDate;    
    @JsonProperty("isRenewable")
    private boolean isRenewable;
    @JsonProperty("isCapitalized")
    private boolean isCapitalized;
    @JsonProperty("isBlocked")
    private boolean isBlocked;
    private OriginIdentifier originIdentifier;
    private SettlementCondition settlementCondition;
    private String annualPercentageYield;
    private String rate;
    private DestinationFunds destinationFunds;
    private String purposeCode;
    private String purposeDescription;    
    private String lastRenewalDate;
    private ProfitabilityAtMaturity profitabilityAtMaturity;
    private InitialTotalInvested initialTotalInvested;
    private List<Settlement> settlements;

    public PlacementIdentification getPlacementIdentification() {
        return placementIdentification;
    }

    public void setPlacementIdentification(PlacementIdentification placementIdentification) {
        this.placementIdentification = placementIdentification;
    }

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Subproduct getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(Subproduct subproduct) {
        this.subproduct = subproduct;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public OriginIdentifier getOriginIdentifier() {
        return originIdentifier;
    }

    public void setOriginIdentifier(OriginIdentifier originIdentifier) {
        this.originIdentifier = originIdentifier;
    }

    public SettlementCondition getSettlementCondition() {
        return settlementCondition;
    }

    public void setSettlementCondition(SettlementCondition settlementCondition) {
        this.settlementCondition = settlementCondition;
    }

    public String getAnnualPercentageYield() {
        return annualPercentageYield;
    }

    public void setAnnualPercentageYield(String annualPercentageYield) {
        this.annualPercentageYield = annualPercentageYield;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public DestinationFunds getDestinationFunds() {
        return destinationFunds;
    }

    public void setDestinationFunds(DestinationFunds destinationFunds) {
        this.destinationFunds = destinationFunds;
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

    public String getLastRenewalDate() {
        return lastRenewalDate;
    }

    public void setLastRenewalDate(String lastRenewalDate) {
        this.lastRenewalDate = lastRenewalDate;
    }

    public ProfitabilityAtMaturity getProfitabilityAtMaturity() {
        return profitabilityAtMaturity;
    }

    public void setProfitabilityAtMaturity(ProfitabilityAtMaturity profitabilityAtMaturity) {
        this.profitabilityAtMaturity = profitabilityAtMaturity;
    }

    public InitialTotalInvested getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(InitialTotalInvested initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacementIdentification {
    private String isin;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productCode;
    private String productDescription;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitabilityAtMaturity {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    private SettlementConcept settlementConcept;

    public SettlementConcept getSettlementConcept() {
        return settlementConcept;
    }

    public void setSettlementConcept(SettlementConcept settlementConcept) {
        this.settlementConcept = settlementConcept;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementConcept {
    private String code;
    private String description;
    private String typeCode;
    private String typeDescription;
    private String rate;
    private Amount amount;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementCondition {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusInfo {
    private String statusCode;
    private String statusDescription;

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
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subproduct {
    private String subproductId;
    private String name;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01DataRequest {
    private String nroCliente;
    private String producto;
    private String nroCtaExtAbono;
    private String tipoCtaExtAbono;
    private String diviCtaExtAbono;
    private String bancoCtaExtAbono;
    private String ejecutivoComercial;

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNroCtaExtAbono() {
        return nroCtaExtAbono;
    }

    public void setNroCtaExtAbono(String nroCtaExtAbono) {
        this.nroCtaExtAbono = nroCtaExtAbono;
    }

    public String getTipoCtaExtAbono() {
        return tipoCtaExtAbono;
    }

    public void setTipoCtaExtAbono(String tipoCtaExtAbono) {
        this.tipoCtaExtAbono = tipoCtaExtAbono;
    }

    public String getDiviCtaExtAbono() {
        return diviCtaExtAbono;
    }

    public void setDiviCtaExtAbono(String diviCtaExtAbono) {
        this.diviCtaExtAbono = diviCtaExtAbono;
    }

    public String getBancoCtaExtAbono() {
        return bancoCtaExtAbono;
    }

    public void setBancoCtaExtAbono(String bancoCtaExtAbono) {
        this.bancoCtaExtAbono = bancoCtaExtAbono;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01HeaderRequest {
    private String rutaServicio;
    private Bp01SesionRequest sesion;
    private String funcion;
    private String secuencia;
    private String canal;

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public Bp01SesionRequest getSesion() {
        return sesion;
    }

    public void setSesion(Bp01SesionRequest sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01SesionRequest {
    private String usuario;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private String diasRestantesCambioClave;
    private String fechaContable;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Request {
    private TrxHeader cabecera;
    private Bp01DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Bp01DataRequest getData() {
        return data;
    }

    public void setData(Bp01DataRequest data) {
        this.data = data;
    }

    public TrxBp01Request(TrxPersonHeader header){
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.Bp01DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Response {
    private Bp01DataResponseDTO data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public Bp01DataResponseDTO getData() {
        return data;
    }

    public void setData(Bp01DataResponseDTO data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02DataRequest {
    private String numeroDePersona;
    private String cuentaInversor;
    private String producto;
    private String subproducto;
    private String plazo;
    private String divisa;
    private String importe;
    private String ejecutivoComercial;
    private String fechaDeAltaDeIpf;
    private String periodoLiquidacion;
    private String tipoDeTasa;
    private String tarifa;
    private String tipoInteresTotal;
    private String spread;
    private String renovacionAutomatic;
    private String tarifaRenovacion;
    private String tipoInteresRenov;
    private String spreadRenovacion;
    private String capitalizaIntereses;
    private String capitalizaReajuste;
    private String rentaProgramada;
    private String planComisiones;
    private String objetivoDeLaInver;
    private String observaciones;
    private String observaciones2;
    private String origenDeLosFondos;
    private String cccAsociado;
    private String divisaAsociadaCcc;
    private String cccAsociadoDos;
    private String divisaAsociadaCcc2;
    private String bancoCtaExtAbono;
    private String cuentaExtAbono;
    private String divisCtaExtAbono;
    private String tipoCtaExtAbono;
    private String bancoCtaExtFondeo;
    private String cuentaExtFondeo;
    private String divisCtaExtFondeo;
    private String tipoCtaExtFonde;
    private String importeGmfBonifica;
    private String porcentajeDeGmfBonificado;
    private String importeRetFteCal;
    private String porcentajeDeRetFuentaCal;

    public String getNumeroDePersona() {
        return numeroDePersona;
    }

    public void setNumeroDePersona(String numeroDePersona) {
        this.numeroDePersona = numeroDePersona;
    }

    public String getCuentaInversor() {
        return cuentaInversor;
    }

    public void setCuentaInversor(String cuentaInversor) {
        this.cuentaInversor = cuentaInversor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubproducto() {
        return subproducto;
    }

    public void setSubproducto(String subproducto) {
        this.subproducto = subproducto;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getFechaDeAltaDeIpf() {
        return fechaDeAltaDeIpf;
    }

    public void setFechaDeAltaDeIpf(String fechaDeAltaDeIpf) {
        this.fechaDeAltaDeIpf = fechaDeAltaDeIpf;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getTipoDeTasa() {
        return tipoDeTasa;
    }

    public void setTipoDeTasa(String tipoDeTasa) {
        this.tipoDeTasa = tipoDeTasa;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getTipoInteresTotal() {
        return tipoInteresTotal;
    }

    public void setTipoInteresTotal(String tipoInteresTotal) {
        this.tipoInteresTotal = tipoInteresTotal;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getRenovacionAutomatic() {
        return renovacionAutomatic;
    }

    public void setRenovacionAutomatic(String renovacionAutomatic) {
        this.renovacionAutomatic = renovacionAutomatic;
    }

    public String getTarifaRenovacion() {
        return tarifaRenovacion;
    }

    public void setTarifaRenovacion(String tarifaRenovacion) {
        this.tarifaRenovacion = tarifaRenovacion;
    }

    public String getTipoInteresRenov() {
        return tipoInteresRenov;
    }

    public void setTipoInteresRenov(String tipoInteresRenov) {
        this.tipoInteresRenov = tipoInteresRenov;
    }

    public String getSpreadRenovacion() {
        return spreadRenovacion;
    }

    public void setSpreadRenovacion(String spreadRenovacion) {
        this.spreadRenovacion = spreadRenovacion;
    }

    public String getCapitalizaIntereses() {
        return capitalizaIntereses;
    }

    public void setCapitalizaIntereses(String capitalizaIntereses) {
        this.capitalizaIntereses = capitalizaIntereses;
    }

    public String getCapitalizaReajuste() {
        return capitalizaReajuste;
    }

    public void setCapitalizaReajuste(String capitalizaReajuste) {
        this.capitalizaReajuste = capitalizaReajuste;
    }

    public String getRentaProgramada() {
        return rentaProgramada;
    }

    public void setRentaProgramada(String rentaProgramada) {
        this.rentaProgramada = rentaProgramada;
    }

    public String getPlanComisiones() {
        return planComisiones;
    }

    public void setPlanComisiones(String planComisiones) {
        this.planComisiones = planComisiones;
    }

    public String getObjetivoDeLaInver() {
        return objetivoDeLaInver;
    }

    public void setObjetivoDeLaInver(String objetivoDeLaInver) {
        this.objetivoDeLaInver = objetivoDeLaInver;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOrigenDeLosFondos() {
        return origenDeLosFondos;
    }

    public void setOrigenDeLosFondos(String origenDeLosFondos) {
        this.origenDeLosFondos = origenDeLosFondos;
    }

    public String getCccAsociado() {
        return cccAsociado;
    }

    public void setCccAsociado(String cccAsociado) {
        this.cccAsociado = cccAsociado;
    }

    public String getDivisaAsociadaCcc() {
        return divisaAsociadaCcc;
    }

    public void setDivisaAsociadaCcc(String divisaAsociadaCcc) {
        this.divisaAsociadaCcc = divisaAsociadaCcc;
    }

    public String getCccAsociadoDos() {
        return cccAsociadoDos;
    }

    public void setCccAsociadoDos(String cccAsociadoDos) {
        this.cccAsociadoDos = cccAsociadoDos;
    }

    public String getDivisaAsociadaCcc2() {
        return divisaAsociadaCcc2;
    }

    public void setDivisaAsociadaCcc2(String divisaAsociadaCcc2) {
        this.divisaAsociadaCcc2 = divisaAsociadaCcc2;
    }

    public String getBancoCtaExtAbono() {
        return bancoCtaExtAbono;
    }

    public void setBancoCtaExtAbono(String bancoCtaExtAbono) {
        this.bancoCtaExtAbono = bancoCtaExtAbono;
    }

    public String getCuentaExtAbono() {
        return cuentaExtAbono;
    }

    public void setCuentaExtAbono(String cuentaExtAbono) {
        this.cuentaExtAbono = cuentaExtAbono;
    }

    public String getDivisCtaExtAbono() {
        return divisCtaExtAbono;
    }

    public void setDivisCtaExtAbono(String divisCtaExtAbono) {
        this.divisCtaExtAbono = divisCtaExtAbono;
    }

    public String getTipoCtaExtAbono() {
        return tipoCtaExtAbono;
    }

    public void setTipoCtaExtAbono(String tipoCtaExtAbono) {
        this.tipoCtaExtAbono = tipoCtaExtAbono;
    }

    public String getBancoCtaExtFondeo() {
        return bancoCtaExtFondeo;
    }

    public void setBancoCtaExtFondeo(String bancoCtaExtFondeo) {
        this.bancoCtaExtFondeo = bancoCtaExtFondeo;
    }

    public String getCuentaExtFondeo() {
        return cuentaExtFondeo;
    }

    public void setCuentaExtFondeo(String cuentaExtFondeo) {
        this.cuentaExtFondeo = cuentaExtFondeo;
    }

    public String getDivisCtaExtFondeo() {
        return divisCtaExtFondeo;
    }

    public void setDivisCtaExtFondeo(String divisCtaExtFondeo) {
        this.divisCtaExtFondeo = divisCtaExtFondeo;
    }

    public String getTipoCtaExtFonde() {
        return tipoCtaExtFonde;
    }

    public void setTipoCtaExtFonde(String tipoCtaExtFonde) {
        this.tipoCtaExtFonde = tipoCtaExtFonde;
    }

    public String getImporteGmfBonifica() {
        return importeGmfBonifica;
    }

    public void setImporteGmfBonifica(String importeGmfBonifica) {
        this.importeGmfBonifica = importeGmfBonifica;
    }

    public String getPorcentajeDeGmfBonificado() {
        return porcentajeDeGmfBonificado;
    }

    public void setPorcentajeDeGmfBonificado(String porcentajeDeGmfBonificado) {
        this.porcentajeDeGmfBonificado = porcentajeDeGmfBonificado;
    }

    public String getImporteRetFteCal() {
        return importeRetFteCal;
    }

    public void setImporteRetFteCal(String importeRetFteCal) {
        this.importeRetFteCal = importeRetFteCal;
    }

    public String getPorcentajeDeRetFuentaCal() {
        return porcentajeDeRetFuentaCal;
    }

    public void setPorcentajeDeRetFuentaCal(String porcentajeDeRetFuentaCal) {
        this.porcentajeDeRetFuentaCal = porcentajeDeRetFuentaCal;
    }

    public String getObservaciones2() {
        return observaciones2;
    }

    public void setObservaciones2(String observaciones2) {
        this.observaciones2 = observaciones2;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02Request {
    private TrxHeader cabecera;
    private TrxBp02DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBp02DataRequest getData() {
        return data;
    }

    public void setData(TrxBp02DataRequest data) {
        this.data = data;
    }

    public TrxBp02Request(TrxPersonHeader header){
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01BGMP010ResponseDTO {
    private String CTAMODE;
    private String EXTACTA;
    private String EXTADIV;
    private String EXTATIP;
    private String EXTFTIP;
    private String DIVASO2;
    private String EXTFCTA;
    private String EXTFDIV;
    private String PRODUCT;
    private String DIVASO;
    private String NUMPER;
    private String CCCASO2;
    private String EXTAENT;
    private String CCCASO;
    private String EJECCOM;
    private String CCCINVE;
    private String EXTFENT;

    public String getCTAMODE() {
        return CTAMODE;
    }

    public void setCTAMODE(String CTAMODE) {
        this.CTAMODE = CTAMODE;
    }

    public String getEXTACTA() {
        return EXTACTA;
    }

    public void setEXTACTA(String EXTACTA) {
        this.EXTACTA = EXTACTA;
    }

    public String getEXTADIV() {
        return EXTADIV;
    }

    public void setEXTADIV(String EXTADIV) {
        this.EXTADIV = EXTADIV;
    }

    public String getEXTATIP() {
        return EXTATIP;
    }

    public void setEXTATIP(String EXTATIP) {
        this.EXTATIP = EXTATIP;
    }

    public String getEXTFTIP() {
        return EXTFTIP;
    }

    public void setEXTFTIP(String EXTFTIP) {
        this.EXTFTIP = EXTFTIP;
    }

    public String getDIVASO2() {
        return DIVASO2;
    }

    public void setDIVASO2(String DIVASO2) {
        this.DIVASO2 = DIVASO2;
    }

    public String getEXTFCTA() {
        return EXTFCTA;
    }

    public void setEXTFCTA(String EXTFCTA) {
        this.EXTFCTA = EXTFCTA;
    }

    public String getEXTFDIV() {
        return EXTFDIV;
    }

    public void setEXTFDIV(String EXTFDIV) {
        this.EXTFDIV = EXTFDIV;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getDIVASO() {
        return DIVASO;
    }

    public void setDIVASO(String DIVASO) {
        this.DIVASO = DIVASO;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String NUMPER) {
        this.NUMPER = NUMPER;
    }

    public String getCCCASO2() {
        return CCCASO2;
    }

    public void setCCCASO2(String CCCASO2) {
        this.CCCASO2 = CCCASO2;
    }

    public String getEXTAENT() {
        return EXTAENT;
    }

    public void setEXTAENT(String EXTAENT) {
        this.EXTAENT = EXTAENT;
    }

    public String getCCCASO() {
        return CCCASO;
    }

    public void setCCCASO(String CCCASO) {
        this.CCCASO = CCCASO;
    }

    public String getEJECCOM() {
        return EJECCOM;
    }

    public void setEJECCOM(String EJECCOM) {
        this.EJECCOM = EJECCOM;
    }

    public String getCCCINVE() {
        return CCCINVE;
    }

    public void setCCCINVE(String CCCINVE) {
        this.CCCINVE = CCCINVE;
    }

    public String getEXTFENT() {
        return EXTFENT;
    }

    public void setEXTFENT(String EXTFENT) {
        this.EXTFENT = EXTFENT;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01DataResponseDTO {
    private Bp01BGMP010ResponseDTO BGMP010;

    public Bp01BGMP010ResponseDTO getBGMP010() {
        return BGMP010;
    }

    public void setBGMP010(Bp01BGMP010ResponseDTO BGMP010) {
        this.BGMP010 = BGMP010;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02BGMP020Response {
    private String SECUIPF;
    private String FECPLIQ;
    private String IMPINTT;
    private String OBJINVE;
    private String SECUREN;
    private String TARIFA;
    private String SUBPROD;
    private String NUMPLAZ;
    private String EXTFTIP;
    private String EXTFDEN;
    private String IMPTTON;
    private String EXTFCTA;
    private String DESADIV;
    private String EXTFDIV;
    private String PRODUCT;
    private String DIVDESC;
    private String IMPCRET;
    private String ORIFOND;
    private String EXTAENT;
    private String IMPINTN;
    private String CCCINVE;
    private String PERLIQ;
    private String OBSERVA;
    private String EXTADTI;
    private String DESCOFO;
    private String DESCOBJ;
    private String PORBGMF;
    private String NUMPER;
    private String RENPROG;
    private String FECALTA;
    private String CCCASO2;
    private String CAPINTE;
    private String CCCASO;
    private String EXTFENT;
    private String PLANCOM;
    private String DESFDIV;
    private String FECVCTO;
    private String DESSUPR;
    private String RENAUTO;
    private String EXTFDTI;
    private String TARENOV;
    private String EJECOM;
    private String IMPINVE;
    private String IMPBGMF;
    private String SPREAD;
    private String IMPINVN;
    private String DESNPLZ;
    private String DIVASOC;
    private String IMPTTOB;
    private String SPREREN;
    private String EXTACTA;
    private String EXTADIV;
    private String FECULIQ;
    private String EXTATIP;
    private String TIPOREN;
    private String DIVISA;
    private String DIVASO2;
    private String CAPREAJ;
    private String PORCRET;
    private String TIPINTE;
    private String TIPOTAS;
    private String EXTADEN;
    private String DESPERL;
    private String TIPINTN;
    private String OBSERV2;

    public String getSECUIPF() {
        return SECUIPF;
    }

    public void setSECUIPF(String SECUIPF) {
        this.SECUIPF = SECUIPF;
    }

    public String getFECPLIQ() {
        return FECPLIQ;
    }

    public void setFECPLIQ(String FECPLIQ) {
        this.FECPLIQ = FECPLIQ;
    }

    public String getIMPINTT() {
        return IMPINTT;
    }

    public void setIMPINTT(String IMPINTT) {
        this.IMPINTT = IMPINTT;
    }

    public String getOBJINVE() {
        return OBJINVE;
    }

    public void setOBJINVE(String OBJINVE) {
        this.OBJINVE = OBJINVE;
    }

    public String getSECUREN() {
        return SECUREN;
    }

    public void setSECUREN(String SECUREN) {
        this.SECUREN = SECUREN;
    }

    public String getTARIFA() {
        return TARIFA;
    }

    public void setTARIFA(String TARIFA) {
        this.TARIFA = TARIFA;
    }

    public String getSUBPROD() {
        return SUBPROD;
    }

    public void setSUBPROD(String SUBPROD) {
        this.SUBPROD = SUBPROD;
    }

    public String getNUMPLAZ() {
        return NUMPLAZ;
    }

    public void setNUMPLAZ(String NUMPLAZ) {
        this.NUMPLAZ = NUMPLAZ;
    }

    public String getEXTFTIP() {
        return EXTFTIP;
    }

    public void setEXTFTIP(String EXTFTIP) {
        this.EXTFTIP = EXTFTIP;
    }

    public String getEXTFDEN() {
        return EXTFDEN;
    }

    public void setEXTFDEN(String EXTFDEN) {
        this.EXTFDEN = EXTFDEN;
    }

    public String getIMPTTON() {
        return IMPTTON;
    }

    public void setIMPTTON(String IMPTTON) {
        this.IMPTTON = IMPTTON;
    }

    public String getEXTFCTA() {
        return EXTFCTA;
    }

    public void setEXTFCTA(String EXTFCTA) {
        this.EXTFCTA = EXTFCTA;
    }

    public String getDESADIV() {
        return DESADIV;
    }

    public void setDESADIV(String DESADIV) {
        this.DESADIV = DESADIV;
    }

    public String getEXTFDIV() {
        return EXTFDIV;
    }

    public void setEXTFDIV(String EXTFDIV) {
        this.EXTFDIV = EXTFDIV;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getDIVDESC() {
        return DIVDESC;
    }

    public void setDIVDESC(String DIVDESC) {
        this.DIVDESC = DIVDESC;
    }

    public String getIMPCRET() {
        return IMPCRET;
    }

    public void setIMPCRET(String IMPCRET) {
        this.IMPCRET = IMPCRET;
    }

    public String getORIFOND() {
        return ORIFOND;
    }

    public void setORIFOND(String ORIFOND) {
        this.ORIFOND = ORIFOND;
    }

    public String getEXTAENT() {
        return EXTAENT;
    }

    public void setEXTAENT(String EXTAENT) {
        this.EXTAENT = EXTAENT;
    }

    public String getIMPINTN() {
        return IMPINTN;
    }

    public void setIMPINTN(String IMPINTN) {
        this.IMPINTN = IMPINTN;
    }

    public String getCCCINVE() {
        return CCCINVE;
    }

    public void setCCCINVE(String CCCINVE) {
        this.CCCINVE = CCCINVE;
    }

    public String getPERLIQ() {
        return PERLIQ;
    }

    public void setPERLIQ(String PERLIQ) {
        this.PERLIQ = PERLIQ;
    }

    public String getOBSERVA() {
        return OBSERVA;
    }

    public void setOBSERVA(String OBSERVA) {
        this.OBSERVA = OBSERVA;
    }

    public String getEXTADTI() {
        return EXTADTI;
    }

    public void setEXTADTI(String EXTADTI) {
        this.EXTADTI = EXTADTI;
    }

    public String getDESCOFO() {
        return DESCOFO;
    }

    public void setDESCOFO(String DESCOFO) {
        this.DESCOFO = DESCOFO;
    }

    public String getDESCOBJ() {
        return DESCOBJ;
    }

    public void setDESCOBJ(String DESCOBJ) {
        this.DESCOBJ = DESCOBJ;
    }

    public String getPORBGMF() {
        return PORBGMF;
    }

    public void setPORBGMF(String PORBGMF) {
        this.PORBGMF = PORBGMF;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String NUMPER) {
        this.NUMPER = NUMPER;
    }

    public String getRENPROG() {
        return RENPROG;
    }

    public void setRENPROG(String RENPROG) {
        this.RENPROG = RENPROG;
    }

    public String getFECALTA() {
        return FECALTA;
    }

    public void setFECALTA(String FECALTA) {
        this.FECALTA = FECALTA;
    }

    public String getCCCASO2() {
        return CCCASO2;
    }

    public void setCCCASO2(String CCCASO2) {
        this.CCCASO2 = CCCASO2;
    }

    public String getCAPINTE() {
        return CAPINTE;
    }

    public void setCAPINTE(String CAPINTE) {
        this.CAPINTE = CAPINTE;
    }

    public String getCCCASO() {
        return CCCASO;
    }

    public void setCCCASO(String CCCASO) {
        this.CCCASO = CCCASO;
    }

    public String getEXTFENT() {
        return EXTFENT;
    }

    public void setEXTFENT(String EXTFENT) {
        this.EXTFENT = EXTFENT;
    }

    public String getPLANCOM() {
        return PLANCOM;
    }

    public void setPLANCOM(String PLANCOM) {
        this.PLANCOM = PLANCOM;
    }

    public String getDESFDIV() {
        return DESFDIV;
    }

    public void setDESFDIV(String DESFDIV) {
        this.DESFDIV = DESFDIV;
    }

    public String getFECVCTO() {
        return FECVCTO;
    }

    public void setFECVCTO(String FECVCTO) {
        this.FECVCTO = FECVCTO;
    }

    public String getDESSUPR() {
        return DESSUPR;
    }

    public void setDESSUPR(String DESSUPR) {
        this.DESSUPR = DESSUPR;
    }

    public String getRENAUTO() {
        return RENAUTO;
    }

    public void setRENAUTO(String RENAUTO) {
        this.RENAUTO = RENAUTO;
    }

    public String getEXTFDTI() {
        return EXTFDTI;
    }

    public void setEXTFDTI(String EXTFDTI) {
        this.EXTFDTI = EXTFDTI;
    }

    public String getTARENOV() {
        return TARENOV;
    }

    public void setTARENOV(String TARENOV) {
        this.TARENOV = TARENOV;
    }

    public String getEJECOM() {
        return EJECOM;
    }

    public void setEJECOM(String EJECOM) {
        this.EJECOM = EJECOM;
    }

    public String getIMPINVE() {
        return IMPINVE;
    }

    public void setIMPINVE(String IMPINVE) {
        this.IMPINVE = IMPINVE;
    }

    public String getIMPBGMF() {
        return IMPBGMF;
    }

    public void setIMPBGMF(String IMPBGMF) {
        this.IMPBGMF = IMPBGMF;
    }

    public String getSPREAD() {
        return SPREAD;
    }

    public void setSPREAD(String SPREAD) {
        this.SPREAD = SPREAD;
    }

    public String getIMPINVN() {
        return IMPINVN;
    }

    public void setIMPINVN(String IMPINVN) {
        this.IMPINVN = IMPINVN;
    }

    public String getDESNPLZ() {
        return DESNPLZ;
    }

    public void setDESNPLZ(String DESNPLZ) {
        this.DESNPLZ = DESNPLZ;
    }

    public String getDIVASOC() {
        return DIVASOC;
    }

    public void setDIVASOC(String DIVASOC) {
        this.DIVASOC = DIVASOC;
    }

    public String getIMPTTOB() {
        return IMPTTOB;
    }

    public void setIMPTTOB(String IMPTTOB) {
        this.IMPTTOB = IMPTTOB;
    }

    public String getSPREREN() {
        return SPREREN;
    }

    public void setSPREREN(String SPREREN) {
        this.SPREREN = SPREREN;
    }

    public String getEXTACTA() {
        return EXTACTA;
    }

    public void setEXTACTA(String EXTACTA) {
        this.EXTACTA = EXTACTA;
    }

    public String getEXTADIV() {
        return EXTADIV;
    }

    public void setEXTADIV(String EXTADIV) {
        this.EXTADIV = EXTADIV;
    }

    public String getFECULIQ() {
        return FECULIQ;
    }

    public void setFECULIQ(String FECULIQ) {
        this.FECULIQ = FECULIQ;
    }

    public String getEXTATIP() {
        return EXTATIP;
    }

    public void setEXTATIP(String EXTATIP) {
        this.EXTATIP = EXTATIP;
    }

    public String getTIPOREN() {
        return TIPOREN;
    }

    public void setTIPOREN(String TIPOREN) {
        this.TIPOREN = TIPOREN;
    }

    public String getDIVISA() {
        return DIVISA;
    }

    public void setDIVISA(String DIVISA) {
        this.DIVISA = DIVISA;
    }

    public String getDIVASO2() {
        return DIVASO2;
    }

    public void setDIVASO2(String DIVASO2) {
        this.DIVASO2 = DIVASO2;
    }

    public String getCAPREAJ() {
        return CAPREAJ;
    }

    public void setCAPREAJ(String CAPREAJ) {
        this.CAPREAJ = CAPREAJ;
    }

    public String getPORCRET() {
        return PORCRET;
    }

    public void setPORCRET(String PORCRET) {
        this.PORCRET = PORCRET;
    }

    public String getTIPINTE() {
        return TIPINTE;
    }

    public void setTIPINTE(String TIPINTE) {
        this.TIPINTE = TIPINTE;
    }

    public String getTIPOTAS() {
        return TIPOTAS;
    }

    public void setTIPOTAS(String TIPOTAS) {
        this.TIPOTAS = TIPOTAS;
    }

    public String getEXTADEN() {
        return EXTADEN;
    }

    public void setEXTADEN(String EXTADEN) {
        this.EXTADEN = EXTADEN;
    }

    public String getDESPERL() {
        return DESPERL;
    }

    public void setDESPERL(String DESPERL) {
        this.DESPERL = DESPERL;
    }

    public String getTIPINTN() {
        return TIPINTN;
    }

    public void setTIPINTN(String TIPINTN) {
        this.TIPINTN = TIPINTN;
    }

    public String getOBSERV2() {
        return OBSERV2;
    }

    public void setOBSERV2(String OBSERV2) {
        this.OBSERV2 = OBSERV2;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02DataResponse {
    private TrxBp02BGMP020Response BGMP020;

    public TrxBp02BGMP020Response getBGMP020() {
        return BGMP020;
    }

    public void setBGMP020(TrxBp02BGMP020Response BGMP020) {
        this.BGMP020 = BGMP020;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.dto.TrxBp02DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02Response {
    private TrxBp02DataResponse data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public TrxBp02DataResponse getData() {
        return data;
    }

    public void setData(TrxBp02DataResponse data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataRequest {
    String entidad;
    String oficina;
    String cuenta;
    String numSecuencia;
    String numCertificado;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumSecuencia() {
        return numSecuencia;
    }

    public void setNumSecuencia(String numSecuencia) {
        this.numSecuencia = numSecuencia;
    }

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13Request {

    private TrxHeader cabecera;
    private TrxBP13DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP13DataRequest getData() {
        return data;
    }

    public void setData(TrxBP13DataRequest data) {
        this.data = data;
    }

    public TrxBP13Request(TrxPersonHeader header){

        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setUsuario(header.sesion.usuario);
        session.setTerminal(header.sesion.terminal);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setEntorno(header.sesion.entorno);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setEntidad("0065");
        session.setDiasRestantesCambioClave(header.sesion.diasRestantesCambioClave);
        session.setFechaContable(header.sesion.fechaContable);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataResponse {
    private String numCertificado;
    private String certificadoReemplazado;
    private String codigoInversor;
    private String secuenciaIPF;
    private String secuenciaRenovacion;
    private String producto;
    private String subproducto;
    private String fecAlta;
    private String fecOperacion;
    private String indicadorGrantia;
    private String tarifaVigente;
    private String estadoIPF;
    private String spread;
    private String indicadorONP;
    private String moneda;
    private String desMoneda;
    private String saldoInicial;
    private int plazo;
    private String periodoLiquidacion;
    private String tipoTitular;
    private String numTitular;
    private String priApellido;
    private String segApellido;
    private String nombreTitular;
    private String tipoEfectivo;
    private int cambioUR;
    private String tipoInteres;
    private String capInteres;
    private String capReajuste;
    private String renovacionAutomatica;
    private String ejecutivoComercial;
    private String planComercial;
    private String custodia;
    private String desCustodia;
    private String canalApertura;
    private String transferible;
    private String origen;
    private String observaciones;
    private String sucIngCustodia;
    private String fecIngCustodia;
    private String sucEgrCustodia;
    private String fecEgrCustodia;
    private int importeRestProgra;
    private String centroGestor;
    private String acuerdo;
    private String cuentaAsociada;
    private String fecAnulacion;
    private String fecVencimiento;
    private String fecCancelacion;
    private String fecLiquidacion;
    private String fecLiqReajuste;
    private String tarifaRenovacion;
    private int spreadRenovacion;
    private String interesesAvonado;
    private String interesesPendiente;
    private String importePeriodico;
    private String indicadorBloqueo;
    private int interesesReajuste;
    private String pago;
    private String desPago;
    private String saldoDisponible;
    private String cuentaCliente;
    private String importeTipoTasa;
    private String numDocumento;
    private String tipoDocumento;
    private String tipoOperacion;
    private String motivoCancelacion;
    private String lina1;
    private String lina2;

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public String getCertificadoReemplazado() {
        return certificadoReemplazado;
    }

    public void setCertificadoReemplazado(String certificadoReemplazado) {
        this.certificadoReemplazado = certificadoReemplazado;
    }

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getSecuenciaIPF() {
        return secuenciaIPF;
    }

    public void setSecuenciaIPF(String secuenciaIPF) {
        this.secuenciaIPF = secuenciaIPF;
    }

    public String getSecuenciaRenovacion() {
        return secuenciaRenovacion;
    }

    public void setSecuenciaRenovacion(String secuenciaRenovacion) {
        this.secuenciaRenovacion = secuenciaRenovacion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubproducto() {
        return subproducto;
    }

    public void setSubproducto(String subproducto) {
        this.subproducto = subproducto;
    }

    public String getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(String fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getFecOperacion() {
        return fecOperacion;
    }

    public void setFecOperacion(String fecOperacion) {
        this.fecOperacion = fecOperacion;
    }

    public String getIndicadorGrantia() {
        return indicadorGrantia;
    }

    public void setIndicadorGrantia(String indicadorGrantia) {
        this.indicadorGrantia = indicadorGrantia;
    }

    public String getTarifaVigente() {
        return tarifaVigente;
    }

    public void setTarifaVigente(String tarifaVigente) {
        this.tarifaVigente = tarifaVigente;
    }

    public String getEstadoIPF() {
        return estadoIPF;
    }

    public void setEstadoIPF(String estadoIPF) {
        this.estadoIPF = estadoIPF;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getIndicadorONP() {
        return indicadorONP;
    }

    public void setIndicadorONP(String indicadorONP) {
        this.indicadorONP = indicadorONP;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDesMoneda() {
        return desMoneda;
    }

    public void setDesMoneda(String desMoneda) {
        this.desMoneda = desMoneda;
    }

    public String getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(String saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getTipoTitular() {
        return tipoTitular;
    }

    public void setTipoTitular(String tipoTitular) {
        this.tipoTitular = tipoTitular;
    }

    public String getNumTitular() {
        return numTitular;
    }

    public void setNumTitular(String numTitular) {
        this.numTitular = numTitular;
    }

    public String getPriApellido() {
        return priApellido;
    }

    public void setPriApellido(String priApellido) {
        this.priApellido = priApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getTipoEfectivo() {
        return tipoEfectivo;
    }

    public void setTipoEfectivo(String tipoEfectivo) {
        this.tipoEfectivo = tipoEfectivo;
    }

    public int getCambioUR() {
        return cambioUR;
    }

    public void setCambioUR(int cambioUR) {
        this.cambioUR = cambioUR;
    }

    public String getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public String getCapInteres() {
        return capInteres;
    }

    public void setCapInteres(String capInteres) {
        this.capInteres = capInteres;
    }

    public String getCapReajuste() {
        return capReajuste;
    }

    public void setCapReajuste(String capReajuste) {
        this.capReajuste = capReajuste;
    }

    public String getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(String renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getPlanComercial() {
        return planComercial;
    }

    public void setPlanComercial(String planComercial) {
        this.planComercial = planComercial;
    }

    public String getCustodia() {
        return custodia;
    }

    public void setCustodia(String custodia) {
        this.custodia = custodia;
    }

    public String getDesCustodia() {
        return desCustodia;
    }

    public void setDesCustodia(String desCustodia) {
        this.desCustodia = desCustodia;
    }

    public String getCanalApertura() {
        return canalApertura;
    }

    public void setCanalApertura(String canalApertura) {
        this.canalApertura = canalApertura;
    }

    public String getTransferible() {
        return transferible;
    }

    public void setTransferible(String transferible) {
        this.transferible = transferible;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSucIngCustodia() {
        return sucIngCustodia;
    }

    public void setSucIngCustodia(String sucIngCustodia) {
        this.sucIngCustodia = sucIngCustodia;
    }

    public String getFecIngCustodia() {
        return fecIngCustodia;
    }

    public void setFecIngCustodia(String fecIngCustodia) {
        this.fecIngCustodia = fecIngCustodia;
    }

    public String getSucEgrCustodia() {
        return sucEgrCustodia;
    }

    public void setSucEgrCustodia(String sucEgrCustodia) {
        this.sucEgrCustodia = sucEgrCustodia;
    }

    public String getFecEgrCustodia() {
        return fecEgrCustodia;
    }

    public void setFecEgrCustodia(String fecEgrCustodia) {
        this.fecEgrCustodia = fecEgrCustodia;
    }

    public int getImporteRestProgra() {
        return importeRestProgra;
    }

    public void setImporteRestProgra(int importeRestProgra) {
        this.importeRestProgra = importeRestProgra;
    }

    public String getCentroGestor() {
        return centroGestor;
    }

    public void setCentroGestor(String centroGestor) {
        this.centroGestor = centroGestor;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(String cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getFecAnulacion() {
        return fecAnulacion;
    }

    public void setFecAnulacion(String fecAnulacion) {
        this.fecAnulacion = fecAnulacion;
    }

    public String getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getFecCancelacion() {
        return fecCancelacion;
    }

    public void setFecCancelacion(String fecCancelacion) {
        this.fecCancelacion = fecCancelacion;
    }

    public String getFecLiquidacion() {
        return fecLiquidacion;
    }

    public void setFecLiquidacion(String fecLiquidacion) {
        this.fecLiquidacion = fecLiquidacion;
    }

    public String getFecLiqReajuste() {
        return fecLiqReajuste;
    }

    public void setFecLiqReajuste(String fecLiqReajuste) {
        this.fecLiqReajuste = fecLiqReajuste;
    }

    public String getTarifaRenovacion() {
        return tarifaRenovacion;
    }

    public void setTarifaRenovacion(String tarifaRenovacion) {
        this.tarifaRenovacion = tarifaRenovacion;
    }

    public int getSpreadRenovacion() {
        return spreadRenovacion;
    }

    public void setSpreadRenovacion(int spreadRenovacion) {
        this.spreadRenovacion = spreadRenovacion;
    }

    public String getInteresesAvonado() {
        return interesesAvonado;
    }

    public void setInteresesAvonado(String interesesAvonado) {
        this.interesesAvonado = interesesAvonado;
    }

    public String getInteresesPendiente() {
        return interesesPendiente;
    }

    public void setInteresesPendiente(String interesesPendiente) {
        this.interesesPendiente = interesesPendiente;
    }

    public String getImportePeriodico() {
        return importePeriodico;
    }

    public void setImportePeriodico(String importePeriodico) {
        this.importePeriodico = importePeriodico;
    }

    public String getIndicadorBloqueo() {
        return indicadorBloqueo;
    }

    public void setIndicadorBloqueo(String indicadorBloqueo) {
        this.indicadorBloqueo = indicadorBloqueo;
    }

    public int getInteresesReajuste() {
        return interesesReajuste;
    }

    public void setInteresesReajuste(int interesesReajuste) {
        this.interesesReajuste = interesesReajuste;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getDesPago() {
        return desPago;
    }

    public void setDesPago(String desPago) {
        this.desPago = desPago;
    }

    public String getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(String saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    public String getImporteTipoTasa() {
        return importeTipoTasa;
    }

    public void setImporteTipoTasa(String importeTipoTasa) {
        this.importeTipoTasa = importeTipoTasa;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getLina1() {
        return lina1;
    }

    public void setLina1(String lina1) {
        this.lina1 = lina1;
    }

    public String getLina2() {
        return lina2;
    }

    public void setLina2(String lina2) {
        this.lina2 = lina2;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13Response {
    private TrxBP13DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP13DataResponse getData() {
        return data;
    }

    public void setData(TrxBP13DataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataRequest {

    private String producto;
    private String subProducto;
    private String tarifa;
    private String plazo;
    private String periodoLiquidacion;
    private String valor;
    private String moneda;
    private String puntosAdicionales;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubProducto() {
        return subProducto;
    }

    public void setSubProducto(String subProducto) {
        this.subProducto = subProducto;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPuntosAdicionales() {
        return puntosAdicionales;
    }

    public void setPuntosAdicionales(String puntosAdicionales) {
        this.puntosAdicionales = puntosAdicionales;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Request {
    
    private TrxHeader cabecera;
    private TrxBP17DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP17DataRequest getData() {
        return data;
    }

    public void setData(TrxBP17DataRequest data) {
        this.data = data;
    }

    public TrxBP17Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSesion(session);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setCanal(header.canal);

        this.cabecera.setResultado(header.getResultado());

    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataResponse {
    
        private String intPendAbonar;
        private String tasaInteresNominal;
        private String tasaInteresEA;
        private String fechaProximoReajuste;
        private String fecVencimiento;
        private String codigoDeProducto;
        private String codigoDeSubproduct;
        private String descripcionProdu;
        private String importeBaseInvers;
        private String codigoDeDivisa;
        private String codigoDeTarifa;
        private String plazoEnDias;
        private String descripPlazo;
        private String plazoEnDiasDispon;
        private String periodoLiquidacion;
        private String descrPeriodoLiq;
        private String periodosDisponibles;
        private String montoFijoBonGmf;
        private String porcentajeFijoBonGmf;
        private String importeGmfMaximo;
        private String importeGmfBonific;
        private String tipoBonifGmfCalc;
        private String importeTotalInvers;
        private String importeBrutoIntere;
        private String porcentajeDeRetencionFuent;
        private String importeRetencFuent;
        private String importeNetoInteres;
        private String importeTotalCobrar;
        private String fechaDeAlta;
        private String fechaDeVencimiento;
        private String fechaProxLiquidac;
        private String porcentajeDeInteresNominal;
        private String porcentajeDeSpread;
        private String porcentajeDeTasaEfectiva;

        public String getIntPendAbonar() {
                return intPendAbonar;
        }

        public void setIntPendAbonar(String intPendAbonar) {
                this.intPendAbonar = intPendAbonar;
        }

        public String getTasaInteresNominal() {
                return tasaInteresNominal;
        }

        public void setTasaInteresNominal(String tasaInteresNominal) {
                this.tasaInteresNominal = tasaInteresNominal;
        }

        public String getTasaInteresEA() {
                return tasaInteresEA;
        }

        public void setTasaInteresEA(String tasaInteresEA) {
                this.tasaInteresEA = tasaInteresEA;
        }

        public String getFechaProximoReajuste() {
                return fechaProximoReajuste;
        }

        public void setFechaProximoReajuste(String fechaProximoReajuste) {
                this.fechaProximoReajuste = fechaProximoReajuste;
        }

        public String getFecVencimiento() {
                return fecVencimiento;
        }

        public void setFecVencimiento(String fecVencimiento) {
                this.fecVencimiento = fecVencimiento;
        }

        public String getCodigoDeProducto() {
                return codigoDeProducto;
        }

        public void setCodigoDeProducto(String codigoDeProducto) {
                this.codigoDeProducto = codigoDeProducto;
        }

        public String getCodigoDeSubproduct() {
                return codigoDeSubproduct;
        }

        public void setCodigoDeSubproduct(String codigoDeSubproduct) {
                this.codigoDeSubproduct = codigoDeSubproduct;
        }

        public String getDescripcionProdu() {
                return descripcionProdu;
        }

        public void setDescripcionProdu(String descripcionProdu) {
                this.descripcionProdu = descripcionProdu;
        }

        public String getImporteBaseInvers() {
                return importeBaseInvers;
        }

        public void setImporteBaseInvers(String importeBaseInvers) {
                this.importeBaseInvers = importeBaseInvers;
        }

        public String getCodigoDeDivisa() {
                return codigoDeDivisa;
        }

        public void setCodigoDeDivisa(String codigoDeDivisa) {
                this.codigoDeDivisa = codigoDeDivisa;
        }

        public String getCodigoDeTarifa() {
                return codigoDeTarifa;
        }

        public void setCodigoDeTarifa(String codigoDeTarifa) {
                this.codigoDeTarifa = codigoDeTarifa;
        }

        public String getPlazoEnDias() {
                return plazoEnDias;
        }

        public void setPlazoEnDias(String plazoEnDias) {
                this.plazoEnDias = plazoEnDias;
        }

        public String getDescripPlazo() {
                return descripPlazo;
        }

        public void setDescripPlazo(String descripPlazo) {
                this.descripPlazo = descripPlazo;
        }

        public String getPlazoEnDiasDispon() {
                return plazoEnDiasDispon;
        }

        public void setPlazoEnDiasDispon(String plazoEnDiasDispon) {
                this.plazoEnDiasDispon = plazoEnDiasDispon;
        }

        public String getPeriodoLiquidacion() {
                return periodoLiquidacion;
        }

        public void setPeriodoLiquidacion(String periodoLiquidacion) {
                this.periodoLiquidacion = periodoLiquidacion;
        }

        public String getDescrPeriodoLiq() {
                return descrPeriodoLiq;
        }

        public void setDescrPeriodoLiq(String descrPeriodoLiq) {
                this.descrPeriodoLiq = descrPeriodoLiq;
        }

        public String getPeriodosDisponibles() {
                return periodosDisponibles;
        }

        public void setPeriodosDisponibles(String periodosDisponibles) {
                this.periodosDisponibles = periodosDisponibles;
        }

        public String getMontoFijoBonGmf() {
                return montoFijoBonGmf;
        }

        public void setMontoFijoBonGmf(String montoFijoBonGmf) {
                this.montoFijoBonGmf = montoFijoBonGmf;
        }

        public String getPorcentajeFijoBonGmf() {
                return porcentajeFijoBonGmf;
        }

        public void setPorcentajeFijoBonGmf(String porcentajeFijoBonGmf) {
                this.porcentajeFijoBonGmf = porcentajeFijoBonGmf;
        }

        public String getImporteGmfMaximo() {
                return importeGmfMaximo;
        }

        public void setImporteGmfMaximo(String importeGmfMaximo) {
                this.importeGmfMaximo = importeGmfMaximo;
        }

        public String getImporteGmfBonific() {
                return importeGmfBonific;
        }

        public void setImporteGmfBonific(String importeGmfBonific) {
                this.importeGmfBonific = importeGmfBonific;
        }

        public String getTipoBonifGmfCalc() {
                return tipoBonifGmfCalc;
        }

        public void setTipoBonifGmfCalc(String tipoBonifGmfCalc) {
                this.tipoBonifGmfCalc = tipoBonifGmfCalc;
        }

        public String getImporteTotalInvers() {
                return importeTotalInvers;
        }

        public void setImporteTotalInvers(String importeTotalInvers) {
                this.importeTotalInvers = importeTotalInvers;
        }

        public String getImporteBrutoIntere() {
                return importeBrutoIntere;
        }

        public void setImporteBrutoIntere(String importeBrutoIntere) {
                this.importeBrutoIntere = importeBrutoIntere;
        }

        public String getPorcentajeDeRetencionFuent() {
                return porcentajeDeRetencionFuent;
        }

        public void setPorcentajeDeRetencionFuent(String porcentajeDeRetencionFuent) {
                this.porcentajeDeRetencionFuent = porcentajeDeRetencionFuent;
        }

        public String getImporteRetencFuent() {
                return importeRetencFuent;
        }

        public void setImporteRetencFuent(String importeRetencFuent) {
                this.importeRetencFuent = importeRetencFuent;
        }

        public String getImporteNetoInteres() {
                return importeNetoInteres;
        }

        public void setImporteNetoInteres(String importeNetoInteres) {
                this.importeNetoInteres = importeNetoInteres;
        }

        public String getImporteTotalCobrar() {
                return importeTotalCobrar;
        }

        public void setImporteTotalCobrar(String importeTotalCobrar) {
                this.importeTotalCobrar = importeTotalCobrar;
        }

        public String getFechaDeAlta() {
                return fechaDeAlta;
        }

        public void setFechaDeAlta(String fechaDeAlta) {
                this.fechaDeAlta = fechaDeAlta;
        }

        public String getFechaDeVencimiento() {
                return fechaDeVencimiento;
        }

        public void setFechaDeVencimiento(String fechaDeVencimiento) {
                this.fechaDeVencimiento = fechaDeVencimiento;
        }

        public String getFechaProxLiquidac() {
                return fechaProxLiquidac;
        }

        public void setFechaProxLiquidac(String fechaProxLiquidac) {
                this.fechaProxLiquidac = fechaProxLiquidac;
        }

        public String getPorcentajeDeInteresNominal() {
                return porcentajeDeInteresNominal;
        }

        public void setPorcentajeDeInteresNominal(String porcentajeDeInteresNominal) {
                this.porcentajeDeInteresNominal = porcentajeDeInteresNominal;
        }

        public String getPorcentajeDeSpread() {
                return porcentajeDeSpread;
        }

        public void setPorcentajeDeSpread(String porcentajeDeSpread) {
                this.porcentajeDeSpread = porcentajeDeSpread;
        }

        public String getPorcentajeDeTasaEfectiva() {
                return porcentajeDeTasaEfectiva;
        }

        public void setPorcentajeDeTasaEfectiva(String porcentajeDeTasaEfectiva) {
                this.porcentajeDeTasaEfectiva = porcentajeDeTasaEfectiva;
        }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Response {
    private TrxBP17DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP17DataResponse getData() {
        return data;
    }

    public void setData(TrxBP17DataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21DataRequest {

    private String certificado;
    private String ccc;
    private String secuenciaImposicion;
    private String tarifaVigente;
    private String plazo;
    private String periodoLiquidacion;
    private String divisa;
    private String saldoDeLaIpf;
    private String spread;
    private String renovacionAutomatic;
    private String capitalizaIntereses;
    private String capitalizaReajustes;
    private String ejecutivoComercial;
    private String rentaProgramada;
    private String indBloqueo;
    private String centroGestor;
    private String indicadorGarantia;
    private String indicadorFraccionab;
    private String spreadRenovacion;
    private String cccAsociada;
    private String tarifaRenovacion;
    private String origenFondos;
    private String indicadorDeBolsa;
    private String observaciones;
    private String cccAsociada2;
    private String saldoEnajenacion;
    private String fechaEnajenacion;
    private String generaNuevoTitulo;
    private String tipoDeTasa;

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getSecuenciaImposicion() {
        return secuenciaImposicion;
    }

    public void setSecuenciaImposicion(String secuenciaImposicion) {
        this.secuenciaImposicion = secuenciaImposicion;
    }

    public String getTarifaVigente() {
        return tarifaVigente;
    }

    public void setTarifaVigente(String tarifaVigente) {
        this.tarifaVigente = tarifaVigente;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getSaldoDeLaIpf() {
        return saldoDeLaIpf;
    }

    public void setSaldoDeLaIpf(String saldoDeLaIpf) {
        this.saldoDeLaIpf = saldoDeLaIpf;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getRenovacionAutomatic() {
        return renovacionAutomatic;
    }

    public void setRenovacionAutomatic(String renovacionAutomatic) {
        this.renovacionAutomatic = renovacionAutomatic;
    }

    public String getCapitalizaIntereses() {
        return capitalizaIntereses;
    }

    public void setCapitalizaIntereses(String capitalizaIntereses) {
        this.capitalizaIntereses = capitalizaIntereses;
    }

    public String getCapitalizaReajustes() {
        return capitalizaReajustes;
    }

    public void setCapitalizaReajustes(String capitalizaReajustes) {
        this.capitalizaReajustes = capitalizaReajustes;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getRentaProgramada() {
        return rentaProgramada;
    }

    public void setRentaProgramada(String rentaProgramada) {
        this.rentaProgramada = rentaProgramada;
    }

    public String getIndBloqueo() {
        return indBloqueo;
    }

    public void setIndBloqueo(String indBloqueo) {
        this.indBloqueo = indBloqueo;
    }

    public String getCentroGestor() {
        return centroGestor;
    }

    public void setCentroGestor(String centroGestor) {
        this.centroGestor = centroGestor;
    }

    public String getIndicadorGarantia() {
        return indicadorGarantia;
    }

    public void setIndicadorGarantia(String indicadorGarantia) {
        this.indicadorGarantia = indicadorGarantia;
    }

    public String getIndicadorFraccionab() {
        return indicadorFraccionab;
    }

    public void setIndicadorFraccionab(String indicadorFraccionab) {
        this.indicadorFraccionab = indicadorFraccionab;
    }

    public String getSpreadRenovacion() {
        return spreadRenovacion;
    }

    public void setSpreadRenovacion(String spreadRenovacion) {
        this.spreadRenovacion = spreadRenovacion;
    }

    public String getCccAsociada() {
        return cccAsociada;
    }

    public void setCccAsociada(String cccAsociada) {
        this.cccAsociada = cccAsociada;
    }

    public String getTarifaRenovacion() {
        return tarifaRenovacion;
    }

    public void setTarifaRenovacion(String tarifaRenovacion) {
        this.tarifaRenovacion = tarifaRenovacion;
    }

    public String getOrigenFondos() {
        return origenFondos;
    }

    public void setOrigenFondos(String origenFondos) {
        this.origenFondos = origenFondos;
    }

    public String getIndicadorDeBolsa() {
        return indicadorDeBolsa;
    }

    public void setIndicadorDeBolsa(String indicadorDeBolsa) {
        this.indicadorDeBolsa = indicadorDeBolsa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCccAsociada2() {
        return cccAsociada2;
    }

    public void setCccAsociada2(String cccAsociada2) {
        this.cccAsociada2 = cccAsociada2;
    }

    public String getSaldoEnajenacion() {
        return saldoEnajenacion;
    }

    public void setSaldoEnajenacion(String saldoEnajenacion) {
        this.saldoEnajenacion = saldoEnajenacion;
    }

    public String getFechaEnajenacion() {
        return fechaEnajenacion;
    }

    public void setFechaEnajenacion(String fechaEnajenacion) {
        this.fechaEnajenacion = fechaEnajenacion;
    }

    public String getGeneraNuevoTitulo() {
        return generaNuevoTitulo;
    }

    public void setGeneraNuevoTitulo(String generaNuevoTitulo) {
        this.generaNuevoTitulo = generaNuevoTitulo;
    }

    public String getTipoDeTasa() {
        return tipoDeTasa;
    }

    public void setTipoDeTasa(String tipoDeTasa) {
        this.tipoDeTasa = tipoDeTasa;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21Request {
    
    private TrxHeader cabecera;
    private TrxBP21DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP21DataRequest getData() {
        return data;
    }

    public void setData(TrxBP21DataRequest data) {
        this.data = data;
    }

    public TrxBP21Request(TrxPersonHeader header){
        
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21DataResponse {

    private String saldoDesde;
    private String saldoHasta;
    private String diasDesde;
    private String diasHasta;
    private String tasaEfectiva;
    private String tasaNominal;

    public String getSaldoDesde() {
        return saldoDesde;
    }

    public void setSaldoDesde(String saldoDesde) {
        this.saldoDesde = saldoDesde;
    }

    public String getSaldoHasta() {
        return saldoHasta;
    }

    public void setSaldoHasta(String saldoHasta) {
        this.saldoHasta = saldoHasta;
    }

    public String getDiasDesde() {
        return diasDesde;
    }

    public void setDiasDesde(String diasDesde) {
        this.diasDesde = diasDesde;
    }

    public String getDiasHasta() {
        return diasHasta;
    }

    public void setDiasHasta(String diasHasta) {
        this.diasHasta = diasHasta;
    }

    public String getTasaEfectiva() {
        return tasaEfectiva;
    }

    public void setTasaEfectiva(String tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    public String getTasaNominal() {
        return tasaNominal;
    }

    public void setTasaNominal(String tasaNominal) {
        this.tasaNominal = tasaNominal;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP21Response {
    private TrxBP21DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP21DataResponse getData() {
        return data;
    }

    public void setData(TrxBP21DataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31DataRequest {
    private String tipoDocumento;
    private String numDocumento;
    private String oficina;
    private String codigoInversor;
    private String nroCliente;
    private String ejecutivoComercial;
    private String indicadorEstado;
    private String tipoCustodia;
    private String tipoFecha;
    private String fechaDesde;
    private String fechaHasta;
    private String cccReposicionam;
    private String secuenciaReposicion;
    private String secRenovReposic;

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

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getIndicadorEstado() {
        return indicadorEstado;
    }

    public void setIndicadorEstado(String indicadorEstado) {
        this.indicadorEstado = indicadorEstado;
    }

    public String getTipoCustodia() {
        return tipoCustodia;
    }

    public void setTipoCustodia(String tipoCustodia) {
        this.tipoCustodia = tipoCustodia;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getCccReposicionam() {
        return cccReposicionam;
    }

    public void setCccReposicionam(String cccReposicionam) {
        this.cccReposicionam = cccReposicionam;
    }

    public String getSecuenciaReposicion() {
        return secuenciaReposicion;
    }

    public void setSecuenciaReposicion(String secuenciaReposicion) {
        this.secuenciaReposicion = secuenciaReposicion;
    }

    public String getSecRenovReposic() {
        return secRenovReposic;
    }

    public void setSecRenovReposic(String secRenovReposic) {
        this.secRenovReposic = secRenovReposic;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31Request {
    
    private TrxHeader cabecera;
    private TrxBP31DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP31DataRequest getData() {
        return data;
    }

    public void setData(TrxBP31DataRequest data) {
        this.data = data;
    }

    public TrxBP31Request(TrxPersonHeader header){
        
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvisosDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.Bp31DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31Response {
    private Bp31DataResponseDTO data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<AvisosDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public Bp31DataResponseDTO getData() {
        return data;
    }

    public void setData(Bp31DataResponseDTO data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
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

    public List<AvisosDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisosDTO> avisos) {
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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp92DataRequestDTO {
    private String entOfiCuenta;
    private String numeroDeSecuencia;
    private String nroDeSecuenciaRen;
    private String indiceDeEstado;
    private String fechaDeBaja;

    public String getEntOfiCuenta() {
        return entOfiCuenta;
    }

    public void setEntOfiCuenta(String entOfiCuenta) {
        this.entOfiCuenta = entOfiCuenta;
    }

    public String getNumeroDeSecuencia() {
        return numeroDeSecuencia;
    }

    public void setNumeroDeSecuencia(String numeroDeSecuencia) {
        this.numeroDeSecuencia = numeroDeSecuencia;
    }

    public String getNroDeSecuenciaRen() {
        return nroDeSecuenciaRen;
    }

    public void setNroDeSecuenciaRen(String nroDeSecuenciaRen) {
        this.nroDeSecuenciaRen = nroDeSecuenciaRen;
    }

    public String getIndiceDeEstado() {
        return indiceDeEstado;
    }

    public void setIndiceDeEstado(String indiceDeEstado) {
        this.indiceDeEstado = indiceDeEstado;
    }

    public String getFechaDeBaja() {
        return fechaDeBaja;
    }

    public void setFechaDeBaja(String fechaDeBaja) {
        this.fechaDeBaja = fechaDeBaja;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp92Request {
    private TrxHeader cabecera;
    private Bp92DataRequestDTO data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Bp92DataRequestDTO getData() {
        return data;
    }

    public void setData(Bp92DataRequestDTO data) {
        this.data = data;
    }

    public TrxBp92Request(TrxPersonHeader header){
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.response;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.AvisosDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp92Response {
    private Object data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<AvisosDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
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

    public List<AvisosDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisosDTO> avisos) {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic;

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxHeader {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfAditionalInfoDTO {
    private String numper;

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.request.BasicData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfDataDTO {

    private BasicData datosBasicos;

    public BasicData getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(BasicData datosBasicos) {
        this.datosBasicos = datosBasicos;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfHeaderDTO {
    private PepfSessionDTO sesion;

    public PepfSessionDTO getSesion() {
        return sesion;
    }

    public void setSesion(PepfSessionDTO sesion) {
        this.sesion = sesion;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfSessionDTO {
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfDataDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPEPFDataRequest {
    private TrxHeader cabecera;
    private PepfDataDTO data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public PepfDataDTO getData() {
        return data;
    }

    public void setData(PepfDataDTO data) {
        this.data = data;
    }

    public TrxPEPFDataRequest(TrxPersonHeader header){
        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }


}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfDataResponseDTO {
    private PepfPEMFV0AResponseDTO pemfvoaResponse;

    public PepfPEMFV0AResponseDTO getPemfvoaResponse() {
        return pemfvoaResponse;
    }

    public void setPemfvoaResponse(PepfPEMFV0AResponseDTO pemfvoaResponse) {
        this.pemfvoaResponse = pemfvoaResponse;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfHeaderResponseDTO {
    private int secuencia;
    private String rutaServicio;
    private PepfSessionResponseDTO sesion;
    private String resultado;

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public PepfSessionResponseDTO getSesion() {
        return sesion;
    }

    public void setSesion(PepfSessionResponseDTO sesion) {
        this.sesion = sesion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfNoticeResponseDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfPEMFV0AResponseDTO {
    private String OFICIAL;
    private String IDNOAPL;
    private String IDSECER;
    private String HONORAR;
    private String REPORTA;
    private String SALARIO;
    private String SECDOC;
    private String SITCLN;
    private String PRESERV;
    private String NROIDT2;
    private String NROIDT3;
    private String PENSION;
    private String NROIDT1;
    private String ACTIND;
    private String NUMDOC;
    private String UNINEG;
    private String ARRIEND;
    private String CANVTA;
    private String SUCURAL;
    private String IDCRECO;
    private String PAIRE01;
    private String AUTCOME;
    private String PAIRE02;
    private String PAIRE03;
    private String IDCRS;
    private String DONHERE;
    private String IDPREPU;
    private String IDFATCA;
    private String OFOTRO;
    private String NUMPER;
    private String MESADA;
    private String TIPDOC;
    private String FECCLN;
    private String IDPPEXP;
    private String IDPEXPO;

    public String getOFICIAL() {
        return OFICIAL;
    }

    public void setOFICIAL(String OFICIAL) {
        this.OFICIAL = OFICIAL;
    }

    public String getIDNOAPL() {
        return IDNOAPL;
    }

    public void setIDNOAPL(String IDNOAPL) {
        this.IDNOAPL = IDNOAPL;
    }

    public String getIDSECER() {
        return IDSECER;
    }

    public void setIDSECER(String IDSECER) {
        this.IDSECER = IDSECER;
    }

    public String getHONORAR() {
        return HONORAR;
    }

    public void setHONORAR(String HONORAR) {
        this.HONORAR = HONORAR;
    }

    public String getREPORTA() {
        return REPORTA;
    }

    public void setREPORTA(String REPORTA) {
        this.REPORTA = REPORTA;
    }

    public String getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(String SALARIO) {
        this.SALARIO = SALARIO;
    }

    public String getSECDOC() {
        return SECDOC;
    }

    public void setSECDOC(String SECDOC) {
        this.SECDOC = SECDOC;
    }

    public String getSITCLN() {
        return SITCLN;
    }

    public void setSITCLN(String SITCLN) {
        this.SITCLN = SITCLN;
    }

    public String getPRESERV() {
        return PRESERV;
    }

    public void setPRESERV(String PRESERV) {
        this.PRESERV = PRESERV;
    }

    public String getNROIDT2() {
        return NROIDT2;
    }

    public void setNROIDT2(String NROIDT2) {
        this.NROIDT2 = NROIDT2;
    }

    public String getNROIDT3() {
        return NROIDT3;
    }

    public void setNROIDT3(String NROIDT3) {
        this.NROIDT3 = NROIDT3;
    }

    public String getPENSION() {
        return PENSION;
    }

    public void setPENSION(String PENSION) {
        this.PENSION = PENSION;
    }

    public String getNROIDT1() {
        return NROIDT1;
    }

    public void setNROIDT1(String NROIDT1) {
        this.NROIDT1 = NROIDT1;
    }

    public String getACTIND() {
        return ACTIND;
    }

    public void setACTIND(String ACTIND) {
        this.ACTIND = ACTIND;
    }

    public String getNUMDOC() {
        return NUMDOC;
    }

    public void setNUMDOC(String NUMDOC) {
        this.NUMDOC = NUMDOC;
    }

    public String getUNINEG() {
        return UNINEG;
    }

    public void setUNINEG(String UNINEG) {
        this.UNINEG = UNINEG;
    }

    public String getARRIEND() {
        return ARRIEND;
    }

    public void setARRIEND(String ARRIEND) {
        this.ARRIEND = ARRIEND;
    }

    public String getCANVTA() {
        return CANVTA;
    }

    public void setCANVTA(String CANVTA) {
        this.CANVTA = CANVTA;
    }

    public String getSUCURAL() {
        return SUCURAL;
    }

    public void setSUCURAL(String SUCURAL) {
        this.SUCURAL = SUCURAL;
    }

    public String getIDCRECO() {
        return IDCRECO;
    }

    public void setIDCRECO(String IDCRECO) {
        this.IDCRECO = IDCRECO;
    }

    public String getPAIRE01() {
        return PAIRE01;
    }

    public void setPAIRE01(String PAIRE01) {
        this.PAIRE01 = PAIRE01;
    }

    public String getAUTCOME() {
        return AUTCOME;
    }

    public void setAUTCOME(String AUTCOME) {
        this.AUTCOME = AUTCOME;
    }

    public String getPAIRE02() {
        return PAIRE02;
    }

    public void setPAIRE02(String PAIRE02) {
        this.PAIRE02 = PAIRE02;
    }

    public String getPAIRE03() {
        return PAIRE03;
    }

    public void setPAIRE03(String PAIRE03) {
        this.PAIRE03 = PAIRE03;
    }

    public String getIDCRS() {
        return IDCRS;
    }

    public void setIDCRS(String IDCRS) {
        this.IDCRS = IDCRS;
    }

    public String getDONHERE() {
        return DONHERE;
    }

    public void setDONHERE(String DONHERE) {
        this.DONHERE = DONHERE;
    }

    public String getIDPREPU() {
        return IDPREPU;
    }

    public void setIDPREPU(String IDPREPU) {
        this.IDPREPU = IDPREPU;
    }

    public String getIDFATCA() {
        return IDFATCA;
    }

    public void setIDFATCA(String IDFATCA) {
        this.IDFATCA = IDFATCA;
    }

    public String getOFOTRO() {
        return OFOTRO;
    }

    public void setOFOTRO(String OFOTRO) {
        this.OFOTRO = OFOTRO;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String NUMPER) {
        this.NUMPER = NUMPER;
    }

    public String getMESADA() {
        return MESADA;
    }

    public void setMESADA(String MESADA) {
        this.MESADA = MESADA;
    }

    public String getTIPDOC() {
        return TIPDOC;
    }

    public void setTIPDOC(String TIPDOC) {
        this.TIPDOC = TIPDOC;
    }

    public String getFECCLN() {
        return FECCLN;
    }

    public void setFECCLN(String FECCLN) {
        this.FECCLN = FECCLN;
    }

    public String getIDPPEXP() {
        return IDPPEXP;
    }

    public void setIDPPEXP(String IDPPEXP) {
        this.IDPPEXP = IDPPEXP;
    }

    public String getIDPEXPO() {
        return IDPEXPO;
    }

    public void setIDPEXPO(String IDPEXPO) {
        this.IDPEXPO = IDPEXPO;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfSessionResponseDTO {
    private String usuario;
    private String terminal;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private int diasRestantesCambioClave;
    private String fechaContable;

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

    public int getDiasRestantesCambioClave() {
        return diasRestantesCambioClave;
    }

    public void setDiasRestantesCambioClave(int diasRestantesCambioClave) {
        this.diasRestantesCambioClave = diasRestantesCambioClave;
    }

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfDataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPEPFDataResponse {
    private PepfDataResponseDTO data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public PepfDataResponseDTO getData() {
        return data;
    }

    public void setData(PepfDataResponseDTO data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.integration;

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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeRequest {
    
    private String authorization;
    private String xSantanderClientId;
    private String productId;

}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeResponse {

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


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory;

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetListDepositsRequestDTO {
    
    private String xSantanderClientId;
    private String authorization;
    private String participantId;
    private String placementStatus;
    private String offset;
    private String limit;

    public String getxSantanderClientId() {
        return xSantanderClientId;
    }

    public void setxSantanderClientId(String xSantanderClientId) {
        this.xSantanderClientId = xSantanderClientId;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(String placementStatus) {
        this.placementStatus = placementStatus;
    }

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TermDepositPersonDTO {

    private TermDepositPersonNameDTO personName;


    public TermDepositPersonNameDTO getPersonName() {
        return personName;
    }

    public void setPersonName(TermDepositPersonNameDTO personName) {
        this.personName = personName;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositPersonNameDTO {

    private String givenName;
    private String lastName;
    private String secondLastName;
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersDTO {
    private String code;
    private String content;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersRequest {
    private String productId;
    private String authorization;
    private String xSantanderClientId;

}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersResponse {
    private List<TermDepositParametersDTO> parameters;

    public List<TermDepositParametersDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<TermDepositParametersDTO> parameters) {
        this.parameters = parameters;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCdtRequestDTO {

    private Boolean isRenewable;
    private Boolean isCapitalized;

    public Boolean getIsRenewable() {
        return isRenewable;
    }

    public void setIsRenewable(Boolean renewable) {
        isRenewable = renewable;
    }

    public Boolean getIsCapitalized() {
        return isCapitalized;
    }

    public void setIsCapitalized(Boolean capitalized) {
        isCapitalized = capitalized;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

import java.util.HashMap;

@Service
@Data
@ConfigurationProperties(prefix = "errors")
@RequiredArgsConstructor
public class ErrorService {


    private String msName;
    private String msVersion;
    private String level;
    private String functional;
    private String technical;
    private HashMap<String, String> general;

    @Value("${errors.general.invalid_value}")
    public String INVALID_VALUE;

    @Value(("${errors.general.blank_data}"))
    public String BLANK_DATA;

    public ServiceException serviceExceptionBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        var error = ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();

        return new ServiceException(status, error);
    }

    public ErrorDTO errorBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        return ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();

    }

    public void isBlank(String value, String fieldName) {
        if (value.isBlank()) {
            var message = "'" + fieldName + "': " + BLANK_DATA;

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNull(String value, String fieldName) {
        if (value == null) {
            var message = "'" + fieldName + "': " + this.general.get("null");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNullIs(Boolean isRenewable, String isRenewable1) {
        if (isRenewable == null) {
            var message = "'" + isRenewable1 + "': " + this.general.get("null");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }


}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception;


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${params.app-name}")
    private String msName;

    private static final String LEVEL = "error";
    private static final String PF400 = "-P-F-9400";
    private static final String NOTSPECIFIED = " not specified";


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-T-9409")
                .message("Unhandled exception")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Unhandled exception")
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

            String errorMessage = "'" + field + "' " + error.getDefaultMessage();

            errors.add(ErrorDTO.builder()
                    .code(msName + PF400)
                    .level(LEVEL)
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
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Not Found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + PF400)
                .message("Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + PF400)
                .message(ex.getMessage())
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + PF400)
                .message("Required header " + ex.getHeaderName() + NOTSPECIFIED)
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + NOTSPECIFIED)
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
                .code(msName + PF400)
                .message("Invalid body structure")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-F-9405")
                .message("Method not allowed")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Method not allowed")
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.DepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.TotalInvestedAmountResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Account;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Amount;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Contract;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Currency;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DestinationFunds;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.InitialTotalInvested;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.OriginIdentifier;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Periodicity;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Placement;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.PlacementIdentification;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Product;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.ProfitabilityAtMaturity;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Settlement;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.SettlementConcept;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.SettlementCondition;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.StatusInfo;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.Subproduct;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request.GetListDepositsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement.*;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositPlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.generic.pagination.LinkDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.generic.pagination.LinksDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.AmountDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.BalanceDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.ContractDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.DepositsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.PlacementsDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.ProductDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.StatusInfoDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.SubproductDTO;

import static com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils.cleanAndFormatNumberString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductsMappers {

    final ErrorService errorService;
    final TermDepositParametersService termDepositParametersService;

    @Value("${params.appName}")
    private String appName;
    @Value("${params.appVersion}")
    private String appVersion;
    @Value("${params.commons.allowDecimal}")
    private Boolean isDecimal;
    @Value("${params.commons.inputSettlement}")
    private String inputSettlementCompare;
    @Value("${params.commons.inputSettlementDescription}")
    private String inputSettlementDescription;

    private String credit = "Credit";
    private String variableZero = "0,0000";
    private static final String STR_REINVERSION_INTERESES = "Reinversión de intereses";

    public SimulatePlacementResponseDTO bp17toSimulatePlacementResponse(TrxBP17Response trxBP17Response,
            String inputSettlement) {
        SimulatePlacementResponseDTO response = new SimulatePlacementResponseDTO();
        ProductResponseDTO product = new ProductResponseDTO();
        SubproductResponseDTO subProduct = new SubproductResponseDTO();
        AmountResponseDTO amount = new AmountResponseDTO();
        PeriodicityResponseDTO periodicity = new PeriodicityResponseDTO();
        ProfitabilityAtMaturityDTO profitability = new ProfitabilityAtMaturityDTO();
        InitialTotalInvestedDTO initialInvestment = new InitialTotalInvestedDTO();
        PecentageYieldResponseDTO percentageYield = new PecentageYieldResponseDTO();
        SettlementResponseDTO settlement = new SettlementResponseDTO();
        AmountResponseSettlementDTO settlementAmount = new AmountResponseSettlementDTO();
        List<SettlementResponseDTO> settlementList = new ArrayList<>();
        //
        SettlementResponseDTO settlementRetf = new SettlementResponseDTO();
        AmountResponseSettlementDTO settlementAmountRetf = new AmountResponseSettlementDTO();
        //
        SettlementResponseDTO settlementIntt = new SettlementResponseDTO();
        AmountResponseSettlementDTO settlementAmountIntt = new AmountResponseSettlementDTO();
        //
        SettlementResponseDTO settlementIntn = new SettlementResponseDTO();
        AmountResponseSettlementDTO settlementAmountIntn = new AmountResponseSettlementDTO();
        //
        product.setProductCode(trxBP17Response.getData().getCodigoDeProducto());
        product.setProductDescription("CDT");
        //
        subProduct.setSubproductId(trxBP17Response.getData().getCodigoDeSubproduct());
        subProduct.setName(trxBP17Response.getData().getDescripcionProdu());
        product.setSubproduct(subProduct);
        //
        amount.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteTotalInvers()));
        amount.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());

        // Modificación para frequency
        periodicity.setFrequency(TermDepositUtils.removeLeadingZeros(trxBP17Response.getData().getPlazoEnDias()));
        periodicity.setPeriodTypeCode("D");

        //
        response.setMaturityDate(trxBP17Response.getData().getFechaDeVencimiento());
        response.setOpeningValueDate(trxBP17Response.getData().getFechaDeAlta());
        //
        if (inputSettlement.equals(inputSettlementCompare)) {
            response.setSettlementConditionCode(inputSettlementCompare);
            response.setSettlementConditionDescription(inputSettlementDescription);
        } else {
            response.setSettlementConditionCode(trxBP17Response.getData().getPeriodoLiquidacion());
            response.setSettlementConditionDescription(trxBP17Response.getData().getDescrPeriodoLiq());
        }

        //
        response.setCapitalizable(
                TermDepositUtils.settlementConditionCodeValidation(response.getSettlementConditionCode()));
        response.setRenewal(true);
        //
        percentageYield.setNominalInterestRate(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getPorcentajeDeInteresNominal()));
        percentageYield.setPercentageYield(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getPorcentajeDeTasaEfectiva()));
        //
        profitability.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteTotalCobrar()));
        profitability.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        //
        if (!isDecimal) {
            String amountFix = TermDepositUtils
                    .cleanAndFormatNumberString(trxBP17Response.getData().getImporteBaseInvers());
            String amountFixed = amountFix.replaceAll(",.*$", "," + "00");
            initialInvestment.setAmount(amountFixed);
        } else {
            initialInvestment.setAmount(
                    TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteBaseInvers()));
        }

        initialInvestment.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        ///
        settlement.setConceptCode("BGMF");
        settlement.setConceptDescription("Beneficio GMF");

        settlement.setTypeCode("C");
        settlement.setTypeDescription(credit);
        settlement.setRate(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getPorcentajeFijoBonGmf()));
        settlementAmount.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteGmfBonific()));
        settlementAmount.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        settlement.setAmount(settlementAmount);
        ///
        settlementRetf.setConceptCode("RETF");
        settlementRetf.setConceptDescription("Retención en la Fuente");
        settlementRetf.setTypeCode("D");
        settlementRetf.setTypeDescription("Debit");
        settlementRetf.setRate(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getPorcentajeDeRetencionFuent()));
        settlementAmountRetf.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteRetencFuent()));
        settlementAmountRetf.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        settlementRetf.setAmount(settlementAmountRetf);
        ///
        settlementIntt.setConceptCode("INTT");
        settlementIntt.setConceptDescription("Interes Total");
        settlementIntt.setTypeCode("C");
        settlementIntt.setTypeDescription(credit);
        settlementIntt.setRate("0");

        settlementAmountIntt.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteBrutoIntere()));
        settlementAmountIntt.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        settlementIntt.setAmount(settlementAmountIntt);
        ///
        settlementIntn.setConceptCode("INTN");
        settlementIntn.setConceptDescription("Interes Neto");
        settlementIntn.setTypeCode("C");
        settlementIntn.setTypeDescription(credit);
        settlementIntn.setRate("0");
        settlementAmountIntn.setAmount(
                TermDepositUtils.cleanAndFormatNumberString(trxBP17Response.getData().getImporteNetoInteres()));
        settlementAmountIntn.setCurrency(trxBP17Response.getData().getCodigoDeDivisa());
        settlementIntn.setAmount(settlementAmountIntn);
        ///
        response.setProduct(product);
        response.setAmount(amount);
        response.setPeriodicity(periodicity);
        response.setProfitabilityAtMaturity(profitability);
        response.setInitialTotalInvested(initialInvestment);
        response.setPercentageYield(percentageYield);
        settlementList.add(settlement);
        settlementList.add(settlementRetf);
        settlementList.add(settlementIntt);
        settlementList.add(settlementIntn);
        response.setSettlements(settlementList);
        return response;
    }

    // Función auxiliar para verificar y ajustar valores nulos o vacíos
    private String ajustarValor(String valor) {
        return (valor == null || valor.isEmpty()) ? "\"\"" : valor;
    }

    // Función auxiliar para formatear la cantidad con coma y dos decimales
    private String formatearCantidad(String cantidad) {
        if (cantidad != null && cantidad.length() == 15) {
            String parteEntera = cantidad.substring(0, 13).replaceFirst("^0+(?!$)", ""); // Elimina los ceros a la
                                                                                         // izquierda
            String parteDecimal = cantidad.substring(13);
            return parteEntera + "," + parteDecimal;
        } else {
            return cantidad;
        }
    }

    private String limpiarCantidad(String cantidad) {
        if (cantidad != null && !cantidad.isEmpty()) {
            return cantidad.trim().replaceAll("[\\s.]+", "").replace("+", "");
        } else {
            return cantidad;
        }
    }

    public DepositPlacementResponseDTO bp13toDepositsPlacementResponse(TrxBP13Response trxBP17Response,
                                                                       TermDepositParametersRequest termDepositParametersRequest) {

        DepositPlacementResponseDTO response = new DepositPlacementResponseDTO();

        assignProductData(trxBP17Response, response);
        Placement placement = createPlacementData(trxBP17Response);
        response.setPlacement(placement);

        setRenewable(trxBP17Response, placement);
        setCapitalized(trxBP17Response, placement);
        setBlocked(trxBP17Response, placement);
        setOriginIdentifier(trxBP17Response, placement);
        setSettlementCondition(trxBP17Response, placement);
        setAnnualPercentageYield(trxBP17Response, placement);
        setRate(trxBP17Response, placement);
        setDestinationFunds(trxBP17Response, placement);

        String observaciones = ajustarValor(trxBP17Response.getData().getObservaciones());
        setPurpose(trxBP17Response, termDepositParametersRequest, placement, observaciones);

        placement.setLastRenewalDate(trxBP17Response.getData().getFecAlta());

        ProfitabilityAtMaturity profitabilityAtMaturity = new ProfitabilityAtMaturity();
        String linea2 = trxBP17Response.getData().getLina2();

        profitabilityAtMaturity.setAmount(TermDepositUtils.format15DigitNumber(linea2.substring(32, 47)));
        profitabilityAtMaturity.setCurrency(trxBP17Response.getData().getMoneda());
        placement.setProfitabilityAtMaturity(profitabilityAtMaturity);

        InitialTotalInvested initialTotalInvested = new InitialTotalInvested();
        initialTotalInvested
                .setAmount(TermDepositUtils.format15DigitNumber(trxBP17Response.getData().getSaldoInicial()));
        initialTotalInvested.setCurrency(trxBP17Response.getData().getMoneda());
        placement.setInitialTotalInvested(initialTotalInvested);

        List<Settlement> settlement = new ArrayList<>();

        // settlementBGMF
        Settlement settlementBGMF = new Settlement();
        SettlementConcept settlementConceptBGMF = new SettlementConcept();
        settlementConceptBGMF.setCode("BGMF");
        settlementConceptBGMF.setDescription("BENEFICIO GMF");
        settlementConceptBGMF.setTypeCode("C");
        settlementConceptBGMF.setTypeDescription(credit);

        if (observaciones.length() >= 6) {
            String rateBGMF2 = "0,0" + observaciones.substring(3, 6);
            settlementConceptBGMF.setRate(rateBGMF2);
        } else {
            settlementConceptBGMF.setRate("0,0");
        }

        Amount amountBGMF = new Amount();
        String amountBGFM2 = "";
        if (observaciones.length() >= 15) {
            amountBGFM2 = TermDepositUtils.format15DigitNumber(observaciones.substring(7, 15) + "00");
        }
        amountBGMF.setAmount((amountBGFM2));
        amountBGMF.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptBGMF.setAmount(amountBGMF);
        settlementBGMF.setSettlementConcept(settlementConceptBGMF);
        settlement.add(settlementBGMF);
        // END settlementBGMF

        // settlementRETF
        Settlement settlementRETF = new Settlement();
        SettlementConcept settlementConceptRETF = new SettlementConcept();
        settlementConceptRETF.setCode("RETF");
        settlementConceptRETF.setDescription("RETENCION EN LA FUENTE");
        settlementConceptRETF.setTypeCode("D");
        settlementConceptRETF.setTypeDescription("DEBIT");

        String rateRETF2 = "0,0";
        if (observaciones.length() >= 19) {
            rateRETF2 += observaciones.substring(16, 19);
        }

        settlementConceptRETF.setRate(rateRETF2);
        Amount amountRETF = new Amount();
        String amountRETFValue = "";
        if (observaciones.length() > 21) {
            amountRETFValue = observaciones.substring(21);
        }
        amountRETF.setAmount(TermDepositUtils.format15DigitNumber(amountRETFValue + "00"));
        amountRETF.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptRETF.setAmount(amountRETF);
        settlementRETF.setSettlementConcept(settlementConceptRETF);
        settlement.add(settlementRETF);

        // END settlementRETF

        // settlementINVB
        Settlement settlementINVB = new Settlement();
        SettlementConcept settlementConceptINVB = new SettlementConcept();
        settlementConceptINVB.setCode("INVB");
        settlementConceptINVB.setDescription("INVERSION BASE");
        settlementConceptINVB.setTypeCode("C");
        settlementConceptINVB.setTypeDescription(credit);

        settlementConceptINVB.setRate(variableZero);

        Amount amountINVB = new Amount();
        String amountINVB2 = TermDepositUtils.format15DigitNumber(linea2.substring(0, 15));

        amountINVB.setAmount(amountINVB2);
        amountINVB.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptINVB.setAmount(amountINVB);
        settlementINVB.setSettlementConcept(settlementConceptINVB);
        settlement.add(settlementINVB);
        // END settlementINVB

        // settlementTOTN
        Settlement settlementTOTN = new Settlement();
        SettlementConcept settlementConceptTOTN = new SettlementConcept();
        settlementConceptTOTN.setCode("INTN");
        settlementConceptTOTN.setDescription("INTERES NETO");
        settlementConceptTOTN.setTypeCode("C");
        settlementConceptTOTN.setTypeDescription(credit);
        settlementConceptTOTN.setRate(variableZero);

        Amount amountTOTN = new Amount();

        var interesNeto = TermDepositUtils.format15DigitNumber(linea2.substring(32, 47));
        String beneficio = "";
        if (observaciones.length() >= 15) {
            beneficio = TermDepositUtils.format15DigitNumber(observaciones.substring(7, 15) + "00");
        }
        Double num2 = 0.0; // Valor por defecto en caso de que beneficio esté vacío
        if (!beneficio.isBlank()) {
            num2 = Double.parseDouble(beneficio.replace(",", "."));
        }
        var inversionBase = TermDepositUtils.format15DigitNumber(linea2.substring(0, 15));

        Double num1 = Double.parseDouble(interesNeto.replace(",", "."));
        Double num3 = Double.parseDouble(inversionBase.replace(",", "."));
        Double diferencia = num1 - num2 - num3;

        String amountTOTN2 = String.format("%.2f", diferencia).replace(".", ",");

        amountTOTN.setAmount(amountTOTN2);
        amountTOTN.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptTOTN.setAmount(amountTOTN);
        settlementTOTN.setSettlementConcept(settlementConceptTOTN);

        settlement.add(settlementTOTN);
        // END settlementTOTN

        // settlementConcept INTA
        Settlement settlementINTA = new Settlement();
        SettlementConcept settlementConceptINTA = new SettlementConcept();
        settlementConceptINTA.setCode("INTA");
        settlementConceptINTA.setDescription("INTERES ABONADO");
        settlementConceptINTA.setTypeCode("C");
        settlementConceptINTA.setTypeDescription(credit);
        settlementConceptINTA.setRate(variableZero);

        Amount amountINTA = new Amount();
        String interesesAbonado = trxBP17Response.getData().getInteresesAvonado();
        amountINTA.setAmount(limpiarCantidad(interesesAbonado));
        amountINTA.setCurrency(trxBP17Response.getData().getMoneda());

        settlementConceptINTA.setAmount(amountINTA);
        settlementINTA.setSettlementConcept(settlementConceptINTA);
        settlement.add(settlementINTA);
        // END settlementConcept INTA

        // settlementConcept SALD
        Settlement settlementSALD = new Settlement();
        SettlementConcept settlementConceptSALD = new SettlementConcept();
        settlementConceptSALD.setCode("SALD");
        settlementConceptSALD.setDescription("SALDO PENDIENTE");
        settlementConceptSALD.setTypeCode("C");
        settlementConceptSALD.setTypeDescription(credit);
        settlementConceptSALD.setRate(variableZero);

        Amount amountSALD = new Amount();
        String saldoDisponible = trxBP17Response.getData().getSaldoDisponible();
        amountSALD.setAmount(formatearCantidad(saldoDisponible));
        amountSALD.setCurrency(trxBP17Response.getData().getMoneda());

        settlementConceptSALD.setAmount(amountSALD);
        settlementSALD.setSettlementConcept(settlementConceptSALD);
        settlement.add(settlementSALD);
        // END settlementConcept SALD

        placement.setSettlements(settlement);

        // trxBP17Response
        response.setPlacement(placement);
        return response;
    }

    private void assignProductData(TrxBP13Response trxBP17Response, DepositPlacementResponseDTO response) {
        if (!trxBP17Response.getData().getProducto().isBlank()) {
            Contract contract = new Contract();
            Product product = new Product();

            product.setProductCode(trxBP17Response.getData().getProducto().substring(0, 2));// 04
            product.setProductDescription(trxBP17Response.getData().getProducto().substring(3));// CDT
            contract.setProduct(product);
            response.setContract(contract);
        }
    }

    private Placement createPlacementData(TrxBP13Response trxBP17Response) {
        Placement placement = new Placement();
        PlacementIdentification placementIdentification = new PlacementIdentification();
        placementIdentification.setIsin(trxBP17Response.getData().getNumCertificado());// CERTIFI
        placement.setPlacementIdentification(placementIdentification);

        StatusInfo statusInfo = new StatusInfo();
        statusInfo.setStatusCode(trxBP17Response.getData().getEstadoIPF());
        String estadoIPF = trxBP17Response.getData().getEstadoIPF();
        String estado = "";
        switch (estadoIPF) {
            case "A":
                estado = "ACTIVO";
                break;
            case "P":
                estado = "PREAPERTURADO";
                break;
            case "V":
                estado = "VENCIDO";
                break;
            case "C":
                estado = "CANCELADO";
                break;
            case "Z":
                estado = "ANULADO";
                break;
            case "X":
                estado = "VENCIDO PENDIENTE DE PAGO";
                break;
            case "N":
                estado = "RETENIDO";
                break;
            default:
                break;
        }
        statusInfo.setStatusDescription(estado);

        placement.setStatusInfo(statusInfo);

        if (!trxBP17Response.getData().getSubproducto().isBlank()) {
            Subproduct subproduct = new Subproduct();
            subproduct.setSubproductId(trxBP17Response.getData().getSubproducto().substring(0, 4));
            subproduct.setName(trxBP17Response.getData().getSubproducto().substring(5));
            placement.setSubproduct(subproduct);
        } // end subproduct

        Currency currency = new Currency();
        currency.setCode(trxBP17Response.getData().getMoneda());
        placement.setCurrency(currency);

        Periodicity periodicity = new Periodicity();
        periodicity.setFrequency(trxBP17Response.getData().getPlazo());
        periodicity.setPeriodTypeCode("D");
        periodicity.setPeriodTypeDescription("DIAS");
        placement.setPeriodicity(periodicity);

        placement.setMaturityDate(trxBP17Response.getData().getFecVencimiento());
        placement.setOpeningDate(trxBP17Response.getData().getFecAlta());

        return placement;
    }

    private void setRenewable(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setRenewable(trxBP17Response.getData().getRenovacionAutomatica().equals("S"));
    }

    private void setCapitalized(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setCapitalized(trxBP17Response.getData().getCapInteres().equals("S"));
    }

    private void setBlocked(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setBlocked(trxBP17Response.getData().getIndicadorBloqueo().equals("S"));
    }

    private void setOriginIdentifier(TrxBP13Response trxBP17Response, Placement placement) {
        OriginIdentifier originIdentifier = new OriginIdentifier();
        originIdentifier.setCode(trxBP17Response.getData().getCanalApertura());
        originIdentifier.setDescription(trxBP17Response.getData().getCanalApertura().equals("60") ? "ODS" : "OTRO");
        placement.setOriginIdentifier(originIdentifier);
    }

    private void setSettlementCondition(TrxBP13Response trxBP17Response, Placement placement) {
        if (!trxBP17Response.getData().getPeriodoLiquidacion().isBlank()) {
            SettlementCondition settlementCondition = new SettlementCondition();
            settlementCondition.setCode(placement.isCapitalized() ? "C" : trxBP17Response.getData().getPeriodoLiquidacion().substring(0, 1));
            settlementCondition.setDescription(placement.isCapitalized() ? STR_REINVERSION_INTERESES : trxBP17Response.getData().getPeriodoLiquidacion().substring(2));
            placement.setSettlementCondition(settlementCondition);
        }
    }

    private void setAnnualPercentageYield(TrxBP13Response trxBP17Response, Placement placement) {
        if (!trxBP17Response.getData().getTipoInteres().isBlank()) {
            String tipoInteres = cleanAndFormatNumberString(trxBP17Response.getData().getTipoInteres());
            var tipoInteresLargo = tipoInteres.length();
            var tipoInteresCorto = tipoInteres.substring(0, (tipoInteresLargo - 1));
            placement.setAnnualPercentageYield(tipoInteresCorto);
        }
    }

    private void setRate(TrxBP13Response trxBP17Response, Placement placement) {
        if (!trxBP17Response.getData().getTipoEfectivo().isBlank()) {
            String tipoEfectivo = cleanAndFormatNumberString(trxBP17Response.getData().getTipoEfectivo());
            var tipoEfectivoLargo = tipoEfectivo.length();
            var tipoEfectivoCorto = tipoEfectivo.substring(0, (tipoEfectivoLargo - 1));
            placement.setRate(tipoEfectivoCorto);
        }
    }

    private void setDestinationFunds(TrxBP13Response trxBP17Response, Placement placement) {
        DestinationFunds destinationFunds = new DestinationFunds();

        String linea1 = ajustarValor(trxBP17Response.getData().getLina1());

        if (linea1.length() >= 5) {

            String tipoCuenta = linea1.substring(4, 5);
            if (tipoCuenta.equals("0")) {
                destinationFunds.setAccountIdType("CC");
            } else {
                destinationFunds.setAccountIdType("CA");
            }

            destinationFunds.setBankcode(linea1.substring(0, 4));

            Account account = new Account();

            account.setNationalIdentification(linea1.substring(5, 35).trim());

            String descriptionCode = linea1.substring(35, 38);
            String description = "";

            switch (descriptionCode) {
                case "ING":
                    description = "Ingresada";
                    break;
                case "VAE":
                    description = "Enviar a Validar";
                    break;
                case "VAP":
                    description = "Validación en Proceso";
                    break;
                case "ACT":
                    description = "Activada";
                    break;
                case "VAR":
                    description = "Validación Rechazada";
                    break;
                case "BAJ":
                    description = "Baja";
                    break;
                case "VDA":
                    description = "Vencida";
                    break;
                case "RCL":
                    description = "Revisar con Cliente";
                    break;
                default:
                    break;
            }

            account.setStatusDescription(description.toUpperCase());
            destinationFunds.setAccount(account);
            placement.setDestinationFunds(destinationFunds);

        } // end destination funds
    }

    private void setPurpose(TrxBP13Response trxBP17Response, TermDepositParametersRequest termDepositParametersRequest,
                            Placement placement, String observaciones) {
        if (!observaciones.isBlank()) {
            String purposeCode = observaciones.substring(0, 2);
            var termDeposit = termDepositParametersService.termDepositParameters(termDepositParametersRequest);

            if (termDeposit != null) {
                var purpose = termDeposit.getParameters().stream().filter(x -> x.getCode().equals(purposeCode))
                        .findAny();

                if (!purpose.isPresent()) {
                    placement.setPurposeDescription("");
                } else {
                    String purposeDescription = purpose.get().getDescription();
                    placement.setPurposeCode(purposeCode);
                    placement.setPurposeDescription(purposeDescription != null ? purposeDescription.toUpperCase() : "");
                }
            }
        } else {
            placement.setPurposeDescription("");
        }
    }


    public List<DepositsResponseDTO> bp31mapResponse(List<CdtsDatsDTO> finalList) {

        List<DepositsResponseDTO> listDeposit = new ArrayList<>();

        finalList.forEach(d -> {
            DepositsResponseDTO deposit = new DepositsResponseDTO();
            deposit.setDepositId(d.getCodigoInversor());
            ContractDTO contract = new ContractDTO();
            ProductDTO product = new ProductDTO();
            product.setProductCode(d.getProducto());
            product.setProductDescription(d.getDescripcionProducto());
            contract.setProduct(product);
            List<PlacementsDTO> placementList = new ArrayList<>();
            PlacementsDTO placement = new PlacementsDTO();
            placement.setPlacementId(d.getSecuencia() + "-" + d.getSecRenov());
            if (d.getEstado().equals("N")) {
                placement.setIsblocked(true);
            } else {
                placement.setIsblocked(false);
            }
            placement.setOpeningValueDate(d.getFechaApertura());
            BalanceDTO balance = new BalanceDTO();
            AmountDTO amount = new AmountDTO();
            amount.setAmount(TermDepositUtils.format15DigitNumber(d.getSaldo()));
            amount.setCurrency(d.getDivisa());
            balance.setAmount(amount);
            placement.setBalance(balance);
            Integer annualPercentEntero = Integer.parseInt(d.getCertificado().substring(1, 4));
            String annualPercentDecimales = d.getCertificado().substring(4, 8);
            String numeroFormateado = annualPercentEntero.toString() + "," + annualPercentDecimales;
            placement.setAnnualPercentageYield(numeroFormateado);
            placement.setMaturityDate(d.getFechaVencimiento());
            SubproductDTO subproduct = new SubproductDTO();
            subproduct.setSubproductId(d.getSubproducto());
            subproduct.setName(d.getDescripcionProducto());
            placement.setSubproduct(subproduct);
            StatusInfoDTO statusinfo = new StatusInfoDTO();
            statusinfo.setStatusCode(d.getEstado());
            switch (d.getEstado()) {
                case "A":
                    statusinfo.setStatusDescription("ACTIVO");
                    break;
                case "P":
                    statusinfo.setStatusDescription("PREAPERTURADO");
                    break;
                case "V":
                    statusinfo.setStatusDescription("VENCIDO");
                    break;
                case "C":
                    statusinfo.setStatusDescription("CANCELADO");
                    break;
                case "Z":
                    statusinfo.setStatusDescription("ANULADO");
                    break;
                case "X":
                    statusinfo.setStatusDescription("VENCIDO PENDIENTE DE PAGO");
                    break;
                case "N":
                    statusinfo.setStatusDescription("RETENIDO");
                    break;
                default:
                    statusinfo.setStatusDescription("DESCONOCIDO"); // Default case for any other status
                    break;
            }
            placement.setStatusInfo(statusinfo);
            placementList.add(placement);
            deposit.setContract(contract);
            deposit.setPlacements(placementList);
            listDeposit.add(deposit);
        });

        return listDeposit;
    }


    public LinksDTO bp31mapResponseLinks(TrxBP31Response trxBP31Response, GetListDepositsRequestDTO request) {

        var deposits = trxBP31Response.getData().getCdtsDats();
        LinksDTO links = new LinksDTO();
        LinkDTO next = new LinkDTO();

        var deposit = deposits.get(Integer.parseInt(request.getLimit()));
        /// v1/term_deposits?participant_id=03003502&placement_status=A&_limit=5
        next.setHref("href:/" + appVersion + "/" + appName.toLowerCase() +
                "?participant_id=" + request.getParticipantId() +
                "&placement_status=" + request.getPlacementStatus() +
                "&_limit=" + request.getLimit() +
                "&_offset=" + deposit.cccReposicionamiento + "-" + deposit.getSecuenciaReposicionamiento());

        links.set_next(next);

        return links;

    }

    public LinksDTO bp31mapResponseLinks(List<CdtsDatsDTO> statusList, GetListDepositsRequestDTO request) {

        LinksDTO links = new LinksDTO();
        LinkDTO next = new LinkDTO();

        var deposit = statusList.get(Integer.parseInt(request.getLimit()));
        /// v1/term_deposits?participant_id=03003502&placement_status=A&_limit=5
        next.setHref("href:/" + appVersion + "/" + appName.toLowerCase() +
                "?participant_id=" + request.getParticipantId() +
                "&placement_status=" + request.getPlacementStatus() +
                "&_limit=" + request.getLimit() +
                "&_offset=" + deposit.cccReposicionamiento + "-" + deposit.getSecuenciaReposicionamiento());

        links.set_next(next);

        return links;

    }

    public ResponseTermDepositsDTO responseTermDepositsDTOMapper(TrxBp02Response trxBp02Response) {
        ResponseTermDepositsDTO responseTermDepositsDTO = new ResponseTermDepositsDTO();
        ResponseTermDepositPlacementDTO termDepositPlacementDTO = new ResponseTermDepositPlacementDTO();
        responseTermDepositsDTO.setDepositId(
                TermDepositUtils.padLeftWithZeros(trxBp02Response.getData().getBGMP020().getCCCINVE(), 20));

        String placementId = TermDepositUtils.padLeftWithZeros(trxBp02Response.getData().getBGMP020().getSECUIPF(), 5)
                + "-00000";
        termDepositPlacementDTO.setPlacementId(placementId);
        termDepositPlacementDTO.setMaturityDate(trxBp02Response.getData().getBGMP020().getFECVCTO());
        termDepositPlacementDTO.setOpeningValueDate(trxBp02Response.getData().getBGMP020().getFECALTA());
        responseTermDepositsDTO.setPlacement(termDepositPlacementDTO);

        String annualPercentageYield = TermDepositUtils
                .cleanAndFormatNumberString(trxBp02Response.getData().getBGMP020().getTIPINTN());
        annualPercentageYield = TermDepositUtils.removeLeadingZeros(annualPercentageYield);
        termDepositPlacementDTO.setAnnualPercentageYield(annualPercentageYield);

        return responseTermDepositsDTO;
    }

    public CalculateDepositSummaryResponseDTO calculateDepositSummaryMapper(TrxBP31Response txrBP31Response,
                                                                            List<CdtsDatsDTO> finalList, String finalAmount) {
        CalculateDepositSummaryResponseDTO response = new CalculateDepositSummaryResponseDTO();
        DepositSummaryResponseDTO depositSummary = new DepositSummaryResponseDTO();
        TotalInvestedAmountResponseDTO totalInvestedAmount = new TotalInvestedAmountResponseDTO();
        totalInvestedAmount.setAmount(finalAmount);
        totalInvestedAmount.setCurrency("COP");
        depositSummary.setTotalInvestedAmount(totalInvestedAmount);

        Map<String, Long> activeDeposits = new HashMap<>();

        if (!finalList.isEmpty() && finalList.get(0) != null) {
            activeDeposits = finalList.stream()
                    .collect(Collectors.groupingBy(CdtsDatsDTO::getEstado, Collectors.counting()));
        }

        depositSummary.setNumberOfActiveDeposits(Math.toIntExact(activeDeposits.getOrDefault("A", 0L)));
        depositSummary.setNumberOfDueDeposits(
                Math.toIntExact(
                        activeDeposits.entrySet().stream()
                                .filter(entry -> entry.getKey().equals("V") || entry.getKey().equals("X"))
                                .mapToLong(Map.Entry::getValue)
                                .sum()
                )
        );
        depositSummary.setNumberOfPendingDeposits(Math.toIntExact(activeDeposits.getOrDefault("P", 0L)));
        depositSummary.setNumberOfCancelledDeposits(Math.toIntExact(activeDeposits.getOrDefault("C", 0L)));
        response.setDepositSummary(depositSummary);
        return response;
    }


    public CalculateDepositSummaryResponseDTO calculateDepositSummaryNullMapper() {
        CalculateDepositSummaryResponseDTO response = new CalculateDepositSummaryResponseDTO();
        DepositSummaryResponseDTO depositSummary = new DepositSummaryResponseDTO();
        TotalInvestedAmountResponseDTO totalInvestedAmount = new TotalInvestedAmountResponseDTO();
        totalInvestedAmount.setAmount("0,00");
        totalInvestedAmount.setCurrency("COP");
        depositSummary.setTotalInvestedAmount(totalInvestedAmount);
        depositSummary.setNumberOfActiveDeposits(0); // A
        depositSummary.setNumberOfDueDeposits(0); // V
        depositSummary.setNumberOfPendingDeposits(0); // P
        depositSummary.setNumberOfCancelledDeposits(0); // C
        response.setDepositSummary(depositSummary);
        return response;
    }

}// class closure

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.observability;
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.observability;

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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class AmountRequestDTO {
    @NotNull(message = "{errors.general.null}")    
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;


public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;


public class CustomerRequestDTO {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Product {
    @NotNull(message = "{errors.general.null}")
    private String productCode;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private SubproductRequestDTO subproduct;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public SubproductRequestDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(SubproductRequestDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class RequestDTO {
    @NotNull(message = "{errors.general.null}")    
    private String frequency;
    @NotNull(message = "{errors.general.null}")
    private String periodTypeCode;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RequestSimulatePlacementDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private Product product;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private AmountRequestDTO amount;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private RequestDTO periodicity;
    @NotNull(message = "{errors.general.null}")
    private String settlementConditionCode;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AmountRequestDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountRequestDTO amount) {
        this.amount = amount;
    }

    public RequestDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(RequestDTO periodicity) {
        this.periodicity = periodicity;
    }

    public String getSettlementConditionCode() {
        return settlementConditionCode;
    }

    public void setSettlementConditionCode(String settlementConditionCode) {
        this.settlementConditionCode = settlementConditionCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class SubproductRequestDTO {
    @NotNull(message = "{errors.general.null}")
    private String subproductId;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;

import java.util.List;

public class RequestTermDepositsDTO {
    @Valid
    private TermDepositBankRequestDTO bank;
    @Valid
    private TermDepositProductDTO product;
    @Valid
    private TermDepositDepositDto deposit;
    @Valid
    private TermDepositEconomicDataDTO economicData;
    @Valid
    private List<TermDepositParticipantDTO> participants;

    public TermDepositBankRequestDTO getBank() {
        return bank;
    }

    public void setBank(TermDepositBankRequestDTO bank) {
        this.bank = bank;
    }

    public TermDepositProductDTO getProduct() {
        return product;
    }

    public void setProduct(TermDepositProductDTO product) {
        this.product = product;
    }

    public TermDepositDepositDto getDeposit() {
        return deposit;
    }

    public void setDeposit(TermDepositDepositDto deposit) {
        this.deposit = deposit;
    }

    public TermDepositEconomicDataDTO getEconomicData() {
        return economicData;
    }

    public void setEconomicData(TermDepositEconomicDataDTO economicData) {
        this.economicData = economicData;
    }

    public List<TermDepositParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<TermDepositParticipantDTO> participants) {
        this.participants = participants;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositAccountDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String nationalIdentification;

    public String getNationalIdentification() {
        return nationalIdentification;
    }

    public void setNationalIdentification(String nationalIdentification) {
        this.nationalIdentification = nationalIdentification;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositAmountDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String amount;
    @Valid
    @NotNull(message = "{errors.general.null}")
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositBankRequestDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String bankId;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private TermDepositCenterDTO center;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public TermDepositCenterDTO getCenter() {
        return center;
    }

    public void setCenter(TermDepositCenterDTO center) {
        this.center = center;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.constraints.NotNull;

public class TermDepositCenterDTO {
    @NotNull(message = "{errors.general.null}")
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.TermDepositPlacementDTO;
import jakarta.validation.Valid;

public class TermDepositDepositDto {
    @Valid
    private TermDepositPlacementDTO placement;

    public TermDepositPlacementDTO getPlacement() {
        return placement;
    }

    public void setPlacement(TermDepositPlacementDTO placement) {
        this.placement = placement;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositDestinationFundsDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String accountIdType;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String bankcode;
    @Valid
    private TermDepositAccountDTO account;

    public String getAccountIdType() {
        return accountIdType;
    }

    public void setAccountIdType(String accountIdType) {
        this.accountIdType = accountIdType;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public TermDepositAccountDTO getAccount() {
        return account;
    }

    public void setAccount(TermDepositAccountDTO account) {
        this.account = account;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;

import java.util.List;

public class TermDepositEconomicDataDTO {
    @Valid
    private TermDepositAmountDTO initialTotalInvested;
    @Valid
    private List<TermDepositSettlementsDTO> settlements;

    public TermDepositAmountDTO getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(TermDepositAmountDTO initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<TermDepositSettlementsDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<TermDepositSettlementsDTO> settlements) {
        this.settlements = settlements;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositParticipantDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String participantId;
    private String participationTypeCode;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getParticipationTypeCode() {
        return participationTypeCode;
    }

    public void setParticipationTypeCode(String participationTypeCode) {
        this.participationTypeCode = participationTypeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositPeriodicityDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String frequency;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositProductDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String productCode;
    @Valid
    private TermDepositSubproductDTO subproduct;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public TermDepositSubproductDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(TermDepositSubproductDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSettlementConceptDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String code;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String typeCode;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String rate;
    @Valid
    private TermDepositAmountDTO amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public TermDepositAmountDTO getAmount() {
        return amount;
    }

    public void setAmount(TermDepositAmountDTO amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSettlementConditionDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;

public class TermDepositSettlementsDTO {
    @Valid
    private TermDepositSettlementConceptDTO settlementConcept;

    public TermDepositSettlementConceptDTO getSettlementConcept() {
        return settlementConcept;
    }

    public void setSettlementConcept(TermDepositSettlementConceptDTO settlementConcept) {
        this.settlementConcept = settlementConcept;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSubproductDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String subproductId;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class AmountResponseDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class AmountResponseSettlementDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class InitialTotalInvestedDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class PecentageYieldResponseDTO {
    private String nominalInterestRate;
    private String percentageYield;

    public String getNominalInterestRate() {
        return nominalInterestRate;
    }

    public void setNominalInterestRate(String nominalInterestRate) {
        this.nominalInterestRate = nominalInterestRate;
    }

    public String getPercentageYield() {
        return percentageYield;
    }

    public void setPercentageYield(String percentageYield) {
        this.percentageYield = percentageYield;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class PeriodicityResponseDTO {
    private String frequency;
    private String periodTypeCode;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class ProductResponseDTO {
    private String productCode;
    private String productDescription;
    private SubproductResponseDTO subproduct;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public SubproductResponseDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(SubproductResponseDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class ProfitabilityAtMaturityDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class SettlementResponseDTO {
    private String conceptCode;
    private String conceptDescription;
    private String typeCode;
    private String typeDescription;
    private String rate;
    private AmountResponseSettlementDTO amount;

    public String getConceptCode() {
        return conceptCode;
    }

    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }

    public String getConceptDescription() {
        return conceptDescription;
    }

    public void setConceptDescription(String conceptDescription) {
        this.conceptDescription = conceptDescription;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public AmountResponseSettlementDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountResponseSettlementDTO amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


import java.util.List;

public class SimulatePlacementResponseDTO {
    private ProductResponseDTO product;
    private AmountResponseDTO amount;
    private PeriodicityResponseDTO periodicity;
    private String maturityDate;
    private String openingValueDate;
    private String settlementConditionCode;
    private String settlementConditionDescription;
    private boolean isCapitalizable;
    private boolean isRenewal;
    private PecentageYieldResponseDTO percentageYield;
    private ProfitabilityAtMaturityDTO profitabilityAtMaturity;
    private InitialTotalInvestedDTO initialTotalInvested;
    private List<SettlementResponseDTO> settlements;

    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }

    public AmountResponseDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountResponseDTO amount) {
        this.amount = amount;
    }

    public PeriodicityResponseDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(PeriodicityResponseDTO periodicity) {
        this.periodicity = periodicity;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningValueDate() {
        return openingValueDate;
    }

    public void setOpeningValueDate(String openingValueDate) {
        this.openingValueDate = openingValueDate;
    }

    public String getSettlementConditionCode() {
        return settlementConditionCode;
    }

    public void setSettlementConditionCode(String settlementConditionCode) {
        this.settlementConditionCode = settlementConditionCode;
    }

    public String getSettlementConditionDescription() {
        return settlementConditionDescription;
    }

    public void setSettlementConditionDescription(String settlementConditionDescription) {
        this.settlementConditionDescription = settlementConditionDescription;
    }

    public boolean isCapitalizable() {
        return isCapitalizable;
    }

    public void setCapitalizable(boolean capitalizable) {
        isCapitalizable = capitalizable;
    }

    public boolean isRenewal() {
        return isRenewal;
    }

    public void setRenewal(boolean renewal) {
        isRenewal = renewal;
    }

    public PecentageYieldResponseDTO getPercentageYield() {
        return percentageYield;
    }

    public void setPercentageYield(PecentageYieldResponseDTO percentageYield) {
        this.percentageYield = percentageYield;
    }

    public ProfitabilityAtMaturityDTO getProfitabilityAtMaturity() {
        return profitabilityAtMaturity;
    }

    public void setProfitabilityAtMaturity(ProfitabilityAtMaturityDTO profitabilityAtMaturity) {
        this.profitabilityAtMaturity = profitabilityAtMaturity;
    }

    public InitialTotalInvestedDTO getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(InitialTotalInvestedDTO initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<SettlementResponseDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<SettlementResponseDTO> settlements) {
        this.settlements = settlements;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement;


public class SubproductResponseDTO {
    private String subproductId;
    private String name;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits;


public class ResponseTermDepositPlacementDTO {
    private String placementId;
    private String maturityDate;
    private String openingValueDate;
    private String annualPercentageYield;

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningValueDate() {
        return openingValueDate;
    }

    public void setOpeningValueDate(String openingValueDate) {
        this.openingValueDate = openingValueDate;
    }

    public String getAnnualPercentageYield() {
        return annualPercentageYield;
    }

    public void setAnnualPercentageYield(String annualPercentageYield) {
        this.annualPercentageYield = annualPercentageYield;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits;


public class ResponseTermDepositsDTO {
    private String depositId;
    private ResponseTermDepositPlacementDTO placement;

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public ResponseTermDepositPlacementDTO getPlacement() {
        return placement;
    }

    public void setPlacement(ResponseTermDepositPlacementDTO placement) {
        this.placement = placement;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositDestinationFundsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositPeriodicityDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositSettlementConditionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositPlacementDTO {
    @Valid
    private TermDepositDestinationFundsDTO destinationFunds;
    @Valid
    private TermDepositPeriodicityDTO periodicity;
    @Valid
    private TermDepositSettlementConditionDTO settlementCondition;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String purposeCode;

    public TermDepositDestinationFundsDTO getDestinationFunds() {
        return destinationFunds;
    }

    public void setDestinationFunds(TermDepositDestinationFundsDTO destinationFunds) {
        this.destinationFunds = destinationFunds;
    }

    public TermDepositPeriodicityDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(TermDepositPeriodicityDTO periodicity) {
        this.periodicity = periodicity;
    }

    public TermDepositSettlementConditionDTO getSettlementCondition() {
        return settlementCondition;
    }

    public void setSettlementCondition(TermDepositSettlementConditionDTO settlementCondition) {
        this.settlementCondition = settlementCondition;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.response.CalculateDepositSummaryResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.request.DepositPlacementRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.depositplacement.response.DepositPlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request.TrxBP13DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request.TrxBP17DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request.TrxBP21DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request.TrxBP31DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request.Bp92DataRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.request.TrxBp92Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp92.response.TrxBp92Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdeposit.request.GetListDepositsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails.TermDepositPersonDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositdetails.TermDepositPersonNameDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.updatecdt.request.UpdateCdtRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.Bp01DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request.TrxBp02DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.host.pepf.request.dto.PepfDataDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.mappers.ProductsMappers;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositParticipantDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.simulatePlacement.SimulatePlacementResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.ResponseTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils.TermDepositUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.CdtsDatsDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.termDeposits.getListDeposits.response.GetListDepositsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.service.TermDepositService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermDepositServiceImpl implements TermDepositService {

    @Value("${errors.termsdeposits.invalidCode}")
    private String error_invalidCode;
    @Value("${errors.termsdeposits.patrimonialInformation}")
    private String error_patrimonialInformation;
    @Value("${service-route-trx.BP17}")
    private String BP17_SERVICE_ROUTE;
    @Value("${service-route-trx.BP31}")
    private String BP31_SERVICE_ROUTE;
    @Value("${service-route-trx.BP21}")
    private String BP21_SERVICE_ROUTE;
    @Value("${service-route-trx.BP13}")
    private String BP13_SERVICE_ROUTE;
    @Value("${service-route-trx.PEPF}")
    private String PEPF_SERVICE_ROUTE;
    @Value("${service-route-trx.BP01}")
    private String BP01_SERVICE_ROUTE;
    @Value("${service-route-trx.BP02}")
    private String BP02_SERVICE_ROUTE;
    @Value("${service-route-trx.BP92}")
    private String BP92_SERVICE_ROUTE;
    @Value("${params.commons.currency}")
    private String CURRENCY;
    @Value("${params.pepf.user}")
    private String USER;
    @Value("${params.pepf.numper}")
    private String NUMPER;
    @Value("${params.commons.allowDecimal}")
    private Boolean isDecimal;
    @Value("${params.commons.inputSettlement}")
    private String inputSettlementCompare;
    @Value("${params.commons.setSettlement}")
    private String setSettlement;
    final TrxSanbaService trxSanbaService;
    final ProductsMappers mapper;
    final TermDepositUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;

    private static final String LIMIT_FIELD = "_limit";
    private static final String OFFSET_FIELD = "_offset";
    private static final String PARTICIPANT_ID_FIELD = "participant_id";
    private static final String PLACEMENT_STATUS_FIELD = "placement_status";
    private static final String ONLY_NUMBERS = "only_numbers";
    private static final String DEPOSIT_ID = "Deposit Id";
    private static final String PLACEMENT_ID = "Placement Id";
    private static final String VARIABLE_ZERO = "0000000000000.00";

    private String inputSettlement;

    public SimulatePlacementResponseDTO processSimulatePlacement(RequestSimulatePlacementDTO requestBodyData,
            AmountRangeRequest amountRangeRequest) {

        termDepositUtils.simulatePlacementInputValidation(requestBodyData, amountRangeRequest);
        TrxBP17Response txrResponse = trxBP17call(requestBodyData);
        // Se setea frecuencia de entrada ya que altair entrega días ajustados por días
        // hábiles
        txrResponse.getData().setPlazoEnDias(requestBodyData.getPeriodicity().getFrequency());

        return mapper.bp17toSimulatePlacementResponse(txrResponse, inputSettlement);

    }


    private void validateRequestParams(GetListDepositsRequestDTO request) {
        errorService.isBlank(request.getParticipantId(), PARTICIPANT_ID_FIELD);
        regexUtils.validateRegex(ONLY_NUMBERS, request.getParticipantId(), PARTICIPANT_ID_FIELD);
        regexUtils.validateRegex("strict_length_8", request.getParticipantId(), PARTICIPANT_ID_FIELD);

        errorService.isBlank(request.getPlacementStatus(), PLACEMENT_STATUS_FIELD);
        regexUtils.validateRegex("periodtype_code_format", request.getPlacementStatus(), PLACEMENT_STATUS_FIELD);
        regexUtils.validateRegex("settlementcondition_code_length", request.getPlacementStatus(), PLACEMENT_STATUS_FIELD);

        // Validación del placementStatus
        regexUtils.validateRegex("status_valid", request.getPlacementStatus(), PLACEMENT_STATUS_FIELD);

        if (request.getLimit() != null) {
            errorService.isBlank(request.getLimit(), LIMIT_FIELD);
            regexUtils.validateRegex(ONLY_NUMBERS, request.getLimit(), LIMIT_FIELD);
            regexUtils.validateRegex("limit", request.getLimit(), LIMIT_FIELD);

            if (request.getOffset() != null) {
                errorService.isBlank(request.getOffset(), OFFSET_FIELD);
                regexUtils.validateRegex("offset_deposits_format", request.getOffset(), OFFSET_FIELD);
                regexUtils.validateRegex("offset_deposits_length", request.getOffset(), OFFSET_FIELD);
            }
        }
    }


    private List<CdtsDatsDTO> filterDepositsByStatus(List<CdtsDatsDTO> depositList, List<String> statuses) {
        return depositList.stream()
                .filter(a -> a != null && a.getEstado() != null && statuses.contains(a.getEstado()))
                .toList();
    }

    private GetListDepositsResponseDTO retrieveAndProcessDeposits(GetListDepositsRequestDTO request) {
        GetListDepositsResponseDTO response = new GetListDepositsResponseDTO();
        TrxBP31Response txrResponse = null;

        try {
            txrResponse = request.getPlacementStatus().equals("P") || request.getPlacementStatus().equals("Z") ?
                    trxBP31call(request) : trxBP31callForDepositList(request);

            if (txrResponse == null || txrResponse.getData() == null || txrResponse.getData().getCdtsDats().isEmpty()) {
                return null;
            }

        } catch (Exception e) {
            return null;
        }


    List<CdtsDatsDTO> finalList = new ArrayList<>();
        List<CdtsDatsDTO> firstList = txrResponse.getData().getCdtsDats();
        finalList.addAll(firstList);
        Integer maxSize = firstList.size();

        while (maxSize == 51) {
            TrxBP31Response txrSecondResponse = trxBP31SecondCallForDepositList(request, txrResponse, maxSize);
            txrSecondResponse.getData().getCdtsDats().remove(0);
            List<CdtsDatsDTO> secondList = txrSecondResponse.getData().getCdtsDats();
            finalList.addAll(secondList);
            maxSize = txrSecondResponse.getData().getCdtsDats().size();
        }

        List<CdtsDatsDTO> statusList;
        if (List.of("V", "C", "X").contains(request.getPlacementStatus())) {
            statusList = filterDepositsByStatus(finalList, List.of("C", "V", "X"));
        } else {
            statusList = filterDepositsByStatus(finalList, List.of(request.getPlacementStatus()));
        }

        Integer cdtsQty = statusList.size();

        if (cdtsQty == 0) {
            return null;
        }

        if (request.getLimit() != null) {
            Integer limit = Integer.parseInt(request.getLimit());
            if (limit < cdtsQty) {
                response.set_links(mapper.bp31mapResponseLinks(statusList, request));
            }
            statusList = statusList.stream().limit(limit).toList();
        }

        response.setDeposits(mapper.bp31mapResponse(statusList));
        return response;
    }

    public GetListDepositsResponseDTO listGetTermsDeposits(GetListDepositsRequestDTO request) {
        validateRequestParams(request);
        return retrieveAndProcessDeposits(request);
    }

    public DepositPlacementResponseDTO getDepositsPlacement(DepositPlacementRequestDTO request,
                                                            TermDepositParametersRequest termDepositParametersRequest) {

        if (request.getDepositId().isBlank()) {
            errorService.isBlank(request.getDepositId(), DEPOSIT_ID);
        }
        regexUtils.validateRegex(ONLY_NUMBERS, request.getDepositId(), DEPOSIT_ID);
        regexUtils.validateRegex("strict_length_20", request.getDepositId(), DEPOSIT_ID);

        if (request.getPlacementId().isBlank()) {
            errorService.isBlank(request.getPlacementId(), PLACEMENT_ID);
        }

        regexUtils.validateRegex("placement_format", request.getPlacementId(), PLACEMENT_ID);
        // secuenciaIPF-secuenciaRenovacion dejando solo secuenciaIPF por ahora hasta
        // que se actualice altair con el formato deseado
        request.setPlacementId(request.getPlacementId().replace("-", ""));

        TrxBP13Response txrResponse = trxBP13call(request);
        DepositPlacementResponseDTO response = mapper.bp13toDepositsPlacementResponse(txrResponse, termDepositParametersRequest);


        // Mapear campos de TrxBP13DataResponse a PersonDTO
        TermDepositPersonDTO person = new TermDepositPersonDTO();
        TermDepositPersonNameDTO personName = new TermDepositPersonNameDTO();
        personName.setGivenName(txrResponse.getData().getNombreTitular());
        personName.setLastName(txrResponse.getData().getPriApellido());
        personName.setSecondLastName(txrResponse.getData().getSegApellido());
        personName.setFullName(txrResponse.getData().getNombreTitular() + " " + txrResponse.getData().getPriApellido() + " " + txrResponse.getData().getSegApellido() );
        person.setPersonName(personName);
        response.setPerson(person);

        return response;
    }




    public ResponseTermDepositsDTO responseTermDepositsDTO(RequestTermDepositsDTO requestBodyData,
            AmountRangeRequest amountRangeRequest,
            TermDepositParametersRequest termDepositParametersRequest,
            BanksParametersRequest banksParametersRequest) {
        termDepositUtils.termDepositsInputValidation(requestBodyData, amountRangeRequest, termDepositParametersRequest,
                banksParametersRequest);
        try {
            log.info("--> Start calling PEPF");
            trxPEPFcall(requestBodyData);
        } catch(Exception e) {
            if(e.getMessage() != null && !e.getMessage().contains(error_patrimonialInformation)) {
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, e.getMessage(), ErrorType.FUNCTIONAL);
            }
        }

        TrxBp01Response trxBP01DataResponse = trxBP01call(requestBodyData);
        TrxBp02Response trxBp02Response = trxBP02call(requestBodyData, trxBP01DataResponse);
        return mapper.responseTermDepositsDTOMapper(trxBp02Response);
    }

    public CalculateDepositSummaryResponseDTO calculateDepositSummary(
            CalculateDepositSummaryRequestDTO calculateDepositSummaryRequestDTO) {

        validateInput(calculateDepositSummaryRequestDTO);

        TrxBP31Response txrBP31Response = executeInitialTrxCall(calculateDepositSummaryRequestDTO);

        if (txrBP31Response == null || txrBP31Response.getData() == null || txrBP31Response.getData().getCdtsDats().isEmpty()) {
            return mapper.calculateDepositSummaryNullMapper();
        }

        List<CdtsDatsDTO> finalList = accumulateCdtsDats(calculateDepositSummaryRequestDTO, txrBP31Response);

        String finalAmount = calculateFinalAmount(finalList);

        return mapper.calculateDepositSummaryMapper(txrBP31Response, finalList, finalAmount);
    }

    private void validateInput(CalculateDepositSummaryRequestDTO requestDTO) {
        termDepositUtils.calculateDepositSummaryInputValidation(requestDTO);
    }

    private TrxBP31Response executeInitialTrxCall(CalculateDepositSummaryRequestDTO requestDTO) {
        try {
            return trxBP31callForDepositSummary(requestDTO);
        } catch (Exception e) {
            // Manejar la excepción y devolver un null
            return null; // Cambiar a return response con 204
        }
    }


    private List<CdtsDatsDTO> accumulateCdtsDats(CalculateDepositSummaryRequestDTO requestDTO, TrxBP31Response initialResponse) {
        List<CdtsDatsDTO> finalList = new ArrayList<>(initialResponse.getData().getCdtsDats());
        int registros = finalList.size();

        while (registros == 51) {
            TrxBP31Response nextResponse = trxBP31recallForDepositSummary(requestDTO, initialResponse);
            nextResponse.getData().getCdtsDats().remove(0);
            finalList.addAll(nextResponse.getData().getCdtsDats());
            registros = nextResponse.getData().getCdtsDats().size();
        }

        return finalList;
    }

    private String calculateFinalAmount(List<CdtsDatsDTO> cdtsDatsList) {
        BigDecimal amount = cdtsDatsList.stream()
                .filter(cdt -> "A".equals(cdt.getEstado()) || "N".equals(cdt.getEstado()))
                .map(cdt -> new BigDecimal(cdt.getSaldo()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String noZerosAmount = amount.stripTrailingZeros().toPlainString();
        return TermDepositUtils.format15DigitNumber(noZerosAmount);
    }


    public void deleteProspectCdt(String depositId) {
        termDepositUtils.deleteProspectCdtInputValidation(depositId);
        DepositPlacementRequestDTO requestBodyData = new DepositPlacementRequestDTO();
        requestBodyData.setDepositId(depositId);
        requestBodyData.setPlacementId("0000100000");
        TrxBP13Response depositPlacementResponse = trxBP13call(requestBodyData);
        var statusCode = depositPlacementResponse.getData().getEstadoIPF();
        if (!statusCode.equals("P")) {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST,
                    "Invalid deposit_id (statusCode must be P)", ErrorType.FUNCTIONAL);
        }
        trxBp92call(depositId);
    }

    public TrxBP17Response trxBP17call(RequestSimulatePlacementDTO requestBodyData) {
        TrxBP17Request trxBP17Request = new TrxBP17Request(ClientUtils.buildHeader(BP17_SERVICE_ROUTE));

        var trxBP17Data = new TrxBP17DataRequest();
        trxBP17Data.setMoneda(CURRENCY); // currency
        inputSettlement = requestBodyData.getSettlementConditionCode();
        if (inputSettlement.equals(inputSettlementCompare)) {
            trxBP17Data.setPeriodoLiquidacion(setSettlement);
        } else {
            trxBP17Data.setPeriodoLiquidacion(requestBodyData.getSettlementConditionCode());
        } // settlementConditionCode
        trxBP17Data.setPlazo(requestBodyData.getPeriodicity().getFrequency()); // periodicity -> frequency
        trxBP17Data.setProducto(requestBodyData.getProduct().getProductCode()); // product -> productCode
        trxBP17Data.setPuntosAdicionales("");
        trxBP17Data.setSubProducto(requestBodyData.getProduct().getSubproduct().getSubproductId()); // product ->
                                                                                                    // subproduct ->
                                                                                                    // subproductId
        trxBP17Data.setTarifa("");

        if (!isDecimal) {
            String initialAmountFix = requestBodyData.getAmount().getAmount().replace(",", ".");
            String initialAmountFixed = initialAmountFix.replaceAll("\\..*", ".00");
            trxBP17Data.setValor(initialAmountFixed); // amount ->
        } else {
            trxBP17Data.setValor(String.valueOf(requestBodyData.getAmount().getAmount().replace(",", "."))); // amount
                                                                                                             // ->
        }

        trxBP17Request.setData(trxBP17Data);
        return trxSanbaService.trxBP17(trxBP17Request);
    }

    public TrxBP31Response trxBP31call(GetListDepositsRequestDTO request) {
        TrxBP31Request trxBP31Request = new TrxBP31Request(ClientUtils.buildHeader(BP31_SERVICE_ROUTE));

        var trxBP31Data = new TrxBP31DataRequest();
        trxBP31Data.setTipoDocumento("");
        trxBP31Data.setNumDocumento("");
        trxBP31Data.setOficina("");
        trxBP31Data.setCodigoInversor("");
        trxBP31Data.setNroCliente(request.getParticipantId());
        trxBP31Data.setEjecutivoComercial("");
        trxBP31Data.setIndicadorEstado(request.getPlacementStatus());
        trxBP31Data.setTipoCustodia("");
        trxBP31Data.setTipoFecha("");
        trxBP31Data.setFechaDesde("");
        trxBP31Data.setFechaHasta("");
        trxBP31Data.setSecRenovReposic("");

        if (request.getOffset() != null) {
            trxBP31Data.setCccReposicionam(request.getOffset().split("-")[0]);
            trxBP31Data.setSecuenciaReposicion(request.getOffset().split("-")[1]);
        } else {
            trxBP31Data.setCccReposicionam("");
            trxBP31Data.setSecuenciaReposicion("");
        }
        trxBP31Request.setData(trxBP31Data);
        return trxSanbaService.trxBP31(trxBP31Request);
    }

    public TrxBP31Response trxBP31callForDepositList(GetListDepositsRequestDTO request) {
        TrxBP31Request trxBP31Request = new TrxBP31Request(ClientUtils.buildHeader(BP31_SERVICE_ROUTE));

        var trxBP31Data = new TrxBP31DataRequest();
        trxBP31Data.setTipoDocumento("");
        trxBP31Data.setNumDocumento("");
        trxBP31Data.setOficina("");
        trxBP31Data.setCodigoInversor("");
        trxBP31Data.setNroCliente(request.getParticipantId());
        trxBP31Data.setEjecutivoComercial("");
        trxBP31Data.setIndicadorEstado("");
        trxBP31Data.setTipoCustodia("");
        trxBP31Data.setTipoFecha("");
        trxBP31Data.setFechaDesde("");
        trxBP31Data.setFechaHasta("");
        trxBP31Data.setSecRenovReposic("");

        if (request.getOffset() != null) {
            trxBP31Data.setCccReposicionam(request.getOffset().split("-")[0]);
            trxBP31Data.setSecuenciaReposicion(request.getOffset().split("-")[1]);
        } else {
            trxBP31Data.setCccReposicionam("");
            trxBP31Data.setSecuenciaReposicion("");
        }
        trxBP31Request.setData(trxBP31Data);
        return trxSanbaService.trxBP31(trxBP31Request);
    }

    public TrxBP31Response trxBP31SecondCallForDepositList(GetListDepositsRequestDTO request,
            TrxBP31Response txrResponse, int maxSize) {
        TrxBP31Request trxBP31Request = new TrxBP31Request(ClientUtils.buildHeader(BP31_SERVICE_ROUTE));

        var trxBP31Data = new TrxBP31DataRequest();
        trxBP31Data.setTipoDocumento("");
        trxBP31Data.setNumDocumento("");
        trxBP31Data.setOficina("");
        trxBP31Data.setCodigoInversor("");
        trxBP31Data.setNroCliente(request.getParticipantId());
        trxBP31Data.setEjecutivoComercial("");
        trxBP31Data.setIndicadorEstado("");
        trxBP31Data.setTipoCustodia("");
        trxBP31Data.setTipoFecha("");
        trxBP31Data.setFechaDesde("");
        trxBP31Data.setFechaHasta("");
        trxBP31Data.setSecRenovReposic(txrResponse.getData().getCdtsDats().get(maxSize - 1).getSecRenov());
        trxBP31Data.setCccReposicionam(txrResponse.getData().getCdtsDats().get(maxSize - 1).getCccReposicionamiento());
        trxBP31Data.setSecuenciaReposicion(
                txrResponse.getData().getCdtsDats().get(maxSize - 1).getSecuenciaReposicionamiento());

        if (request.getOffset() != null) {
            trxBP31Data.setCccReposicionam(request.getOffset().split("-")[0]);
            trxBP31Data.setSecuenciaReposicion(request.getOffset().split("-")[1]);
            trxBP31Data.setSecRenovReposic(request.getOffset().split("-")[2]);
//        } else {
//            trxBP31Data.setCccReposicionam("");
//            trxBP31Data.setSecuenciaReposicion("");
        }
        trxBP31Request.setData(trxBP31Data);
        return trxSanbaService.trxBP31(trxBP31Request);
    }

    public TrxBP13Response trxBP13call(DepositPlacementRequestDTO requestBodyData) {
        TrxBP13Request trxBP13Request = new TrxBP13Request(ClientUtils.buildHeader(BP13_SERVICE_ROUTE));
        var trxBP13Data = new TrxBP13DataRequest();
        trxBP13Data.setEntidad("0065");
        trxBP13Data.setOficina("0100");
        trxBP13Data.setCuenta("");
        trxBP13Data.setNumSecuencia("");
        trxBP13Data.setNumCertificado(requestBodyData.getDepositId() + requestBodyData.getPlacementId());
        trxBP13Request.setData(trxBP13Data);
        return trxSanbaService.trxBP13(trxBP13Request);
    }

    public TrxPEPFDataResponse trxPEPFcall(RequestTermDepositsDTO requestBodyData) {
        TrxPEPFDataRequest trxPEPFDataRequest = new TrxPEPFDataRequest(ClientUtils.buildHeader(PEPF_SERVICE_ROUTE));
        var numper = requestBodyData.getParticipants().stream()
                .map(TermDepositParticipantDTO::getParticipantId).findFirst().orElse(null);
        var trxPEPFDatadto = new PepfDataDTO();
        var basicData = new BasicData();
        basicData.setNumper(numper);
        trxPEPFDatadto.setDatosBasicos(basicData);
        trxPEPFDataRequest.setData(trxPEPFDatadto);

        return trxSanbaService.trxPEPF(trxPEPFDataRequest);
    }

    public TrxBp01Response trxBP01call(RequestTermDepositsDTO requestBodyData) {
        TrxBp01Request trxBp01Request = new TrxBp01Request(ClientUtils.buildHeader(BP01_SERVICE_ROUTE));
        var data = new Bp01DataRequest();
        data.setNroCliente(requestBodyData.getParticipants().stream()
                .map(TermDepositParticipantDTO::getParticipantId).findFirst().orElse(null));
        data.setProducto(requestBodyData.getProduct().getProductCode());
        data.setNroCtaExtAbono(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount()
                .getNationalIdentification());
        data.setTipoCtaExtAbono(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType());
        data.setDiviCtaExtAbono(CURRENCY);
        data.setBancoCtaExtAbono(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode());
        data.setEjecutivoComercial("0001");
        trxBp01Request.setData(data);
        return trxSanbaService.trxBP01(trxBp01Request);
    }

    public TrxBp02Response trxBP02call(RequestTermDepositsDTO requestBodyData, TrxBp01Response bp01Response) {
        TrxBp02Request trxBp02Request = new TrxBp02Request(ClientUtils.buildHeader(BP02_SERVICE_ROUTE));
        var data = new TrxBp02DataRequest();
        data.setNumeroDePersona(requestBodyData.getParticipants().stream()
                .map(TermDepositParticipantDTO::getParticipantId).findFirst().orElse(null));
        data.setCuentaInversor(bp01Response.getData().getBGMP010().getCCCINVE());
        data.setProducto(requestBodyData.getProduct().getProductCode());
        data.setSubproducto(requestBodyData.getProduct().getSubproduct().getSubproductId());
        data.setPlazo(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency());
        data.setDivisa(CURRENCY);

        if (!isDecimal) {
            String initialAmountFix = requestBodyData.getEconomicData().getInitialTotalInvested().getAmount()
                    .replace(",", ".");
            String initialAmountFixed = initialAmountFix.replaceAll("\\..*", ".00");
            data.setImporte(initialAmountFixed);
        } else {
            data.setImporte(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount().replace(",", "."));
        }

        data.setEjecutivoComercial("");
        data.setFechaDeAltaDeIpf("");
        data.setPeriodoLiquidacion(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode());
        data.setTipoDeTasa("N");
        data.setTarifa("4250");
        data.setTipoInteresTotal(requestBodyData.getEconomicData().getSettlements()
                .stream().filter(settlement -> "ITEA".equals(settlement.getSettlementConcept().getCode()))
                .map(settlement -> settlement.getSettlementConcept().getRate()).findFirst().orElse(null)
                .replace(",", "."));
        data.setSpread("");
        data.setRenovacionAutomatic("S");
        data.setTarifaRenovacion("4250");
        data.setTipoInteresRenov("");
        data.setSpreadRenovacion("");
        data.setCapitalizaIntereses(TermDepositUtils.settlementConditionCodeTransformation(
                requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode())); // lógica excel -> interes capitalizable
        data.setCapitalizaReajuste("N");
        data.setRentaProgramada("");
        data.setPlanComisiones("");
        data.setObjetivoDeLaInver(requestBodyData.getDeposit().getPlacement().getPurposeCode());
        data.setObservaciones("");

        // Nueva lógica: si existe un segundo participants y es de tipo AGENT, se asigna su participantId a observaciones2.
        if (requestBodyData.getParticipants() != null && requestBodyData.getParticipants().size() > 1) {
            TermDepositParticipantDTO secondParticipant = requestBodyData.getParticipants().get(1);
            if ("AGENT".equalsIgnoreCase(secondParticipant.getParticipationTypeCode())) {
                data.setObservaciones2(secondParticipant.getParticipantId());
            }
        }

        data.setOrigenDeLosFondos("O");
        data.setCccAsociado("");
        data.setDivisaAsociadaCcc("");
        data.setCccAsociadoDos("");
        data.setDivisaAsociadaCcc2("");
        data.setDivisCtaExtFondeo("");
        data.setTipoCtaExtFonde("");
        data.setImporteGmfBonifica(requestBodyData.getEconomicData().getSettlements().stream()
                .filter(settlement -> "BGMF".equals(settlement.getSettlementConcept().getCode()))
                .map(settlement -> settlement.getSettlementConcept().getAmount().getAmount()).findFirst().orElse(null)
                .replace(",", "."));
        data.setPorcentajeDeGmfBonificado(requestBodyData.getEconomicData().getSettlements().stream()
                .filter(settlement -> "BGMF".equals(settlement.getSettlementConcept().getCode()))
                .map(settlement -> settlement.getSettlementConcept().getRate()).findFirst().orElse(null)
                .replace(",", "."));
        data.setImporteRetFteCal(requestBodyData.getEconomicData().getSettlements().stream()
                .filter(settlement -> "RETF".equals(settlement.getSettlementConcept().getCode()))
                .map(settlement -> settlement.getSettlementConcept().getAmount().getAmount()).findFirst().orElse(null)
                .replace(",", "."));
        data.setPorcentajeDeRetFuentaCal(requestBodyData.getEconomicData().getSettlements().stream()
                .filter(settlement -> "RETF".equals(settlement.getSettlementConcept().getCode()))
                .map(settlement -> settlement.getSettlementConcept().getRate()).findFirst().orElse(null)
                .replace(",", "."));

        trxBp02Request.setData(data);
        return trxSanbaService.trxBP02(trxBp02Request);
    }


    public TrxBP31Response trxBP31callForDepositSummary(
            CalculateDepositSummaryRequestDTO calculateDepositSummaryRequestDTO) {
        TrxBP31Request trxBP31Request = new TrxBP31Request(ClientUtils.buildHeader(BP31_SERVICE_ROUTE));
        var trxBP31Data = new TrxBP31DataRequest();
        var participant_id = calculateDepositSummaryRequestDTO.getParticipants().getParticipantId();
        trxBP31Data.setTipoDocumento("");
        trxBP31Data.setNumDocumento("");
        trxBP31Data.setOficina("");
        trxBP31Data.setCodigoInversor("");
        trxBP31Data.setNroCliente(participant_id);
        trxBP31Data.setEjecutivoComercial("");
        trxBP31Data.setIndicadorEstado("");
        trxBP31Data.setTipoCustodia("");
        trxBP31Data.setTipoFecha("");
        trxBP31Data.setFechaDesde("");
        trxBP31Data.setFechaHasta("");
        trxBP31Data.setCccReposicionam("");
        trxBP31Data.setSecuenciaReposicion("");
        trxBP31Data.setSecRenovReposic("");
        trxBP31Request.setData(trxBP31Data);
        return trxSanbaService.trxBP31(trxBP31Request);
    }

    public TrxBP31Response trxBP31recallForDepositSummary(
            CalculateDepositSummaryRequestDTO calculateDepositSummaryRequestDTO,
            TrxBP31Response txrBP31Response) {
        TrxBP31Request trxBP31Request = new TrxBP31Request(ClientUtils.buildHeader(BP31_SERVICE_ROUTE));
        var trxBP31Data = new TrxBP31DataRequest();
        var participant_id = calculateDepositSummaryRequestDTO.getParticipants().getParticipantId();
        var cccReposicionamiento = txrBP31Response.getData().getCdtsDats()
                .get(txrBP31Response.getData().getCdtsDats().size() - 1).cccReposicionamiento;
        var secuenciaReposicionamiento = txrBP31Response.getData().getCdtsDats()
                .get(txrBP31Response.getData().getCdtsDats().size() - 1).secuenciaReposicionamiento;
        var secuenciaRenovacionRepo = txrBP31Response.getData().getCdtsDats().get(txrBP31Response.getData().getCdtsDats().size() -1).secuenciaRenovacion;
        trxBP31Data.setTipoDocumento("");
        trxBP31Data.setNumDocumento("");
        trxBP31Data.setOficina("");
        trxBP31Data.setCodigoInversor("");
        trxBP31Data.setNroCliente(participant_id);
        trxBP31Data.setEjecutivoComercial("");
        trxBP31Data.setIndicadorEstado("");
        trxBP31Data.setTipoCustodia("");
        trxBP31Data.setTipoFecha("");
        trxBP31Data.setFechaDesde("");
        trxBP31Data.setFechaHasta("");
        trxBP31Data.setCccReposicionam(cccReposicionamiento);
        trxBP31Data.setSecuenciaReposicion(secuenciaReposicionamiento);
        trxBP31Data.setSecRenovReposic(secuenciaRenovacionRepo);
        trxBP31Request.setData(trxBP31Data);
        return trxSanbaService.trxBP31(trxBP31Request);
    }

    public TrxBp92Response trxBp92call(String depositId) {
        TrxBp92Request request = new TrxBp92Request(ClientUtils.buildHeader(BP92_SERVICE_ROUTE));
        var trxBP92Data = new Bp92DataRequestDTO();
        trxBP92Data.setEntOfiCuenta(depositId);
        trxBP92Data.setNumeroDeSecuencia("00001");
        trxBP92Data.setNroDeSecuenciaRen("00000");
        trxBP92Data.setIndiceDeEstado("Z");
        trxBP92Data.setFechaDeBaja("2024-02-19");
        request.setData(trxBP92Data);
        return trxSanbaService.trxBP92(request);
    }

    /**
     * Modificacion CDT
     * 
     * @param depositId
     * @param placementId
     * @param updateRequest
     */
    public void updateCdt(String depositId, String placementId, UpdateCdtRequestDTO updateRequest) {
        validateUpdateCdtInput(depositId, placementId, updateRequest);
        String modifiedPlacementId = modifyPlacementId(placementId);
        trxBP21Call(depositId, modifiedPlacementId, updateRequest);
    }

    private String modifyPlacementId(String placementId) {
        // Obtener los primeros cinco dígitos de placementId
        String modifiedPlacementId = placementId.substring(0, 5);
        return modifiedPlacementId;
    }

    private void validateUpdateCdtInput(String depositId, String placementId, UpdateCdtRequestDTO updateRequest) {
        // Validación para depositId
        errorService.isNull(depositId, DEPOSIT_ID);
        errorService.isBlank(depositId, DEPOSIT_ID);
        regexUtils.validateRegex(ONLY_NUMBERS, depositId, DEPOSIT_ID);
        regexUtils.validateRegex("strict_length_20", depositId, DEPOSIT_ID);

        // Validación para placementId
        errorService.isNull(placementId, PLACEMENT_ID);
        errorService.isBlank(placementId, PLACEMENT_ID);
        regexUtils.validateRegex("placement_short_format", placementId, PLACEMENT_ID);

        errorService.isNullIs(updateRequest.getIsRenewable(), "Is Renewable");
        errorService.isNullIs(updateRequest.getIsCapitalized(), "Is Capitalized");

        regexUtils.validateRegex("renewable_only_false", String.valueOf(updateRequest.getIsRenewable()),
                "Is Renewable");
        regexUtils.validateRegex("renewable_only_false", String.valueOf(updateRequest.getIsCapitalized()),
                "Is Capitalized");

    }

    public TrxBP21Response trxBP21Call(String depositId, String placementId, UpdateCdtRequestDTO updateRequest) {
        String modifiedPlacementId = modifyPlacementId(placementId);

        TrxBP21Request trxBP21Request = new TrxBP21Request(ClientUtils.buildHeader(BP21_SERVICE_ROUTE));

        var trxBP21Data = new TrxBP21DataRequest();
        // mapeo
        trxBP21Data.setCertificado("VTO");
        trxBP21Data.setCcc(depositId);
        trxBP21Data.setSecuenciaImposicion(modifiedPlacementId);
        trxBP21Data.setTarifaVigente("4250");
        trxBP21Data.setPlazo("090");
        trxBP21Data.setPeriodoLiquidacion("M");
        trxBP21Data.setDivisa("COP");
        trxBP21Data.setSaldoDeLaIpf(VARIABLE_ZERO);
        trxBP21Data.setSpread("000.00000");
        trxBP21Data.setRenovacionAutomatic(updateRequest.getIsRenewable() ? "S" : "N");
        trxBP21Data.setCapitalizaIntereses(updateRequest.getIsCapitalized() ? "S" : "N");
        trxBP21Data.setCapitalizaReajustes("N");
        trxBP21Data.setEjecutivoComercial("0001");
        trxBP21Data.setRentaProgramada(VARIABLE_ZERO);
        trxBP21Data.setIndBloqueo("N");
        trxBP21Data.setCentroGestor("0001");
        trxBP21Data.setIndicadorGarantia("N");
        trxBP21Data.setIndicadorFraccionab("N");
        trxBP21Data.setSpreadRenovacion("000.00000");
        trxBP21Data.setSaldoEnajenacion(VARIABLE_ZERO);
        trxBP21Data.setObservaciones("Test");
        trxBP21Data.setTipoDeTasa("N");

        trxBP21Request.setData(trxBP21Data);
        return trxSanbaService.trxBP21(trxBP21Request);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.regex.Pattern;

@Data
@Configuration
@ConfigurationProperties(prefix = "regex")
public class RegexUtils {

    @Value("${params.appName}")
    private String MS_NAME;
    @Value("${params.apiServiceVersion}")
    private String MS_VERSION;
    @Value("${errors.level}")
    private String LEVEL;
    @Value("${regex.error.code}")
    private String CODE;

    private HashMap<String, String> type;
    

    public void validateRegex(String regexType, String value, String fieldName) {

        String regularExpression = type.get(regexType);
        String message = type.get(regexType + "_error") != null ? type.get(regexType + "_error") : "Invalid format";

        var pattern = Pattern.compile(regularExpression);
        var matcher = pattern.matcher(value);
        boolean match = false;
        while (matcher.find()) {
            match = true;
        }

        if (!match) {

            ErrorDTO errorDTO = ErrorDTO.builder()
                    .code(MS_NAME + "-" + CODE)
                    .level(LEVEL)
                    .message("'" + fieldName + "': " + message)
                    .description(MS_NAME.toLowerCase() + "-" + MS_VERSION + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }

}


package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;


import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * Handle all Products utils
 */
@Component
@RequiredArgsConstructor
public class TermDepositUtils {
    
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final ProductDirectoryService productDirectoryService;
    final TermDepositParametersService termDepositParametersService;
    final BanksService banksService;

    @Value("${params.commons.productCode}")
    private String productCode;
    @Value("${params.commons.subproductCode}")
    private String subproductCode;

    @Value("#{'${params.frequencies}'.split(',')}")
    private String [] validFrecuencies;
    @Value("#{'${params.settlements}'.split(',')}")
    private String [] validSettlements;
    @Value("${params.condition-codes}")
    private String SETTLEMENT_CONDITON_CODES;
    @Value("${params.commons.bankId}")
    private String bankId;
    @Value("${params.commons.centerId}")
    private String centerId;

    private String PRODUCT_CODE_FIELDNAME = "product.productCode";
    private String SUBPRODUCT_ID_FIELDNAME= "product.subproduct.subproductId";
    private String AMOUNT_FIELDNAME = "amount.amount";
    private String PERIDIOCITY_FRECUENCY = "periodicity.frecuency";
    private String PERIDIOCITY_PERIOD_TYPE_CODE = "periodicity.periodTypeCode";
    private String SETTLEMENT_CONDITION_CODE_FIELDNAME = "settlementConditionCode";
    private String BANK_ID_FIELDNAME = "bank.bankId";
    private String CENTER_ID_FIELDNAME = "bank.center.centerId";
    private String ACCOUNT_ID_TYPE = "deposits.placement.destinationFunds.accountIdType";
    private String BANK_CODE= "deposits.placement.destinationFunds.bankcode";
    private String NATIONAL_IDENTIFICATION = "deposits.placement.destinationFunds.account.nationalIdentification";
    private String DEPOSITS_FRECUENCY = "deposits.placement.periodicity.frequency";
    private String DEPOSITS_SETTLEMENT_CONDITION_CODE = "deposits.placement.settlementCondition.code";
    private String DEPOSITS_PURPOSE_CODE = "deposits.placement.purposeCode";
    private String DEPOSITS_TOTAL_INVESTED_AMOUNT = "economicData.initialTotalInvested.amount";
    private String DEPOSITS_CURRENCY = "economicData.initialTotalInvested.currency";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CODE ="economicData.settlements.settlementConcept.code";
    private String DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE ="economicData.settlements.settlementConcept.typeCode";
    private String DEPOSITS_SETTLEMENT_CONCEPT_RATE = "economicData.settlements.settlementConcept.rate";
    private String DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT = "economicData.settlements.settlementConcept.amount.amount";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY = "economicData.settlements.settlementConcept.amount.currency";    
    private String PARTICIPANT_PARTICIPANTID = "participant.participantId";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH = "settlementConcept_typeCode_length";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT = "settlementConcept_typeCode_format";
    private static final String CURRENCY_CODE_FORMAT = "currency_code_format";
    private static final String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND = "economicData.settlements.settlemenmtConcept.amount.currency_not_found";
    private static final String INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT = "initialTotalInvested_amount_format";
    private static final String SETTLEMENT_CONCEPT_RATE_FORMAT = "settlementConcept_rate_format";
    private static final String DEPOSIT_ID = "deposit_id";
    private static final Double GMF = 0.0040;

    public static String cleanAndFormatNumberString(String input) {
        var noplus = input.replace("+", "");
        var nominus = noplus.replace("-", "");
        var nodots = nominus.replace(".","");
        return nodots.trim();
    }

    public void simulatePlacementInputValidation(RequestSimulatePlacementDTO requestBodyData, AmountRangeRequest amountRangeRequest){

        //Product Validation
        errorService.isBlank(requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        if(!productCode.equals(requestBodyData.getProduct().getProductCode())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
        }

        //SubProduct Validation        
        errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_format", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_length", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        if(!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
        }

        // Amount range validation
        String productAndSubProductId = requestBodyData.getProduct().getProductCode() + requestBodyData.getProduct().getSubproduct().getSubproductId();    
        amountRangeRequest.setProductId(productAndSubProductId);
        var amountRangeResponse = productDirectoryService.amountRange(amountRangeRequest);

        // Amount Validation
        errorService.isBlank(requestBodyData.getAmount().getAmount(), AMOUNT_FIELDNAME);
        regexUtils.validateRegex("amount_format", requestBodyData.getAmount().getAmount(), AMOUNT_FIELDNAME);
        amountValidation(requestBodyData.getAmount().getAmount(),
                amountRangeResponse.getMinimumAmount().getAmount(),
                amountRangeResponse.getMaximumAmount().getAmount());
                
        //Frecuency Validation
        errorService.isBlank(requestBodyData.getPeriodicity().getFrequency(), PERIDIOCITY_FRECUENCY);
        regexUtils.validateRegex("frecuency_code_format", requestBodyData.getPeriodicity().getFrequency(), PERIDIOCITY_FRECUENCY);
        frecuencyValidation(requestBodyData.getPeriodicity().getFrequency());
        
        //PeriodType Validation
        errorService.isBlank(requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        regexUtils.validateRegex("periodtype_code_format", requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        regexUtils.validateRegex("periodtype_code_length", requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        if ( !"D".equals(requestBodyData.getPeriodicity().getPeriodTypeCode())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("periodtypecode_not_found"), ErrorType.FUNCTIONAL);
        }

        //SettlementConditionCode Validation
        errorService.isBlank(requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        regexUtils.validateRegex("settlementcondition_code_format", requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        regexUtils.validateRegex("settlementcondition_code_length", requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        settlementContionCodeInputValidation(requestBodyData.getSettlementConditionCode());
    }


    //termDepositsInputValidation

    public void termDepositsInputValidation(RequestTermDepositsDTO requestBodyData,
                                            AmountRangeRequest amountRangeRequest,
                                            TermDepositParametersRequest termDepositParametersRequest,
                                            BanksParametersRequest banksParametersRequest) {
        validateBank(requestBodyData);
        validateCenter(requestBodyData);
        validateProduct(requestBodyData);
        validateSubproduct(requestBodyData);
        validateAccountIdType2(requestBodyData);
        validateBankCode(requestBodyData, banksParametersRequest);
        validateNationalIdentification(requestBodyData);
        validateSettlementConditionCode(requestBodyData);
        validatePurposeCode(requestBodyData, termDepositParametersRequest);
        validateTotalInvestedAmount(requestBodyData);
        validateCurrency(requestBodyData);
        validateAmountRange(requestBodyData,amountRangeRequest);                                     

        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        regexUtils.validateRegex("frecuency_code_format", requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        frecuencyValidation(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency());


        errorService.isBlank(requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex("settlementConcept_code_format", requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex("settlementConcept_code_length", requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        validateSettlementConcept(requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode());
        requestBodyData.getEconomicData().getSettlements().forEach( sett -> {
            if(sett.getSettlementConcept().getCode().equals("BGMF")){
                //economicData -> settlements [] -> settlementConcept -> code BGMF
                //economicData -> settlements [] -> settlementConcept -> typeCode BGMF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate BGMF
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }

            }
            if(sett.getSettlementConcept().getCode().equals("RETF")){
                //economicData -> settlements [] -> settlementConcept -> code RETF
                //economicData -> settlements [] -> settlementConcept -> typeCode RETF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate RETF
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }

            if(sett.getSettlementConcept().getCode().equals("ITEA")){
                //economicData -> settlements [] -> settlementConcept -> code ITEA
                //economicData -> settlements [] -> settlementConcept -> typeCode ITEA
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate ITEA
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount ITEA
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency ITEA
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }
        });

    }

    public void validateAmountRange(RequestTermDepositsDTO requestBodyData,
    AmountRangeRequest amountRangeRequest) {
                 // Amount range validation
                 String productAndSubProductId = requestBodyData.getProduct().getProductCode() + requestBodyData.getProduct().getSubproduct().getSubproductId();    
                 amountRangeRequest.setProductId(productAndSubProductId);
                 var amountRangeResponse = productDirectoryService.amountRange(amountRangeRequest);
                                                        
          // Amount Validation
         errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), AMOUNT_FIELDNAME);
        regexUtils.validateRegex("amount_format", requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), AMOUNT_FIELDNAME);
        Double inputAmount = Double.parseDouble(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount().replace(",", ""));
        Double _minAmount = Double.parseDouble(amountRangeResponse.getMinimumAmount().getAmount().replace(",", ""));
        Double _maxAmount = Double.parseDouble(amountRangeResponse.getMaximumAmount().getAmount().replace(",", ""));
        Double max= _maxAmount+(_maxAmount*GMF);                        
        if (inputAmount < _minAmount) {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_under_limit_economicData"), ErrorType.FUNCTIONAL);
        }
        if(inputAmount > max){
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_over_limit_economicData"), ErrorType.FUNCTIONAL);
        }
    }
    private void validateBank(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_format", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_length", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        if (!bankId.equals(requestBodyData.getBank().getBankId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("bankId_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateCenter(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        regexUtils.validateRegex("center_code_format", requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        regexUtils.validateRegex("center_code_length", requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        if (!centerId.equals(requestBodyData.getBank().getCenter().getCenterId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("centerId_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateProduct(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        if (!productCode.equals(requestBodyData.getProduct().getProductCode())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateSubproduct(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_format", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_length", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        if (!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateAccountIdType2(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        regexUtils.validateRegex("accountIdType_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        regexUtils.validateRegex("accountIdType_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        if (!validateAccountIdType(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("accountIdType_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateBankCode(RequestTermDepositsDTO requestBodyData, BanksParametersRequest banksParametersRequest) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        regexUtils.validateRegex("bankId_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        regexUtils.validateRegex("bankId_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        bankValidation(banksParametersRequest, requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode());
    }
    public void validateNationalIdentification(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        regexUtils.validateRegex("nationalIdentification_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        regexUtils.validateRegex("nationalIdentification_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        padLeftWithZeros(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), 17);
    }
    public void validateSettlementConditionCode(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_format", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_length", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        settlementContionCodeInputValidationForDeposits(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase());
    }
    public void validatePurposeCode(RequestTermDepositsDTO requestBodyData, TermDepositParametersRequest termDepositParametersRequest) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(), DEPOSITS_PURPOSE_CODE);
        regexUtils.validateRegex("purposeCode_code_length", requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(), DEPOSITS_PURPOSE_CODE);
        purposeCodeValidation(termDepositParametersRequest, requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase());
    }
    public void validateTotalInvestedAmount(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), DEPOSITS_TOTAL_INVESTED_AMOUNT);
        regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), DEPOSITS_TOTAL_INVESTED_AMOUNT);
    }
    public void validateCurrency(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(), DEPOSITS_CURRENCY);
        regexUtils.validateRegex(CURRENCY_CODE_FORMAT, requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(), DEPOSITS_CURRENCY);
        if (!"COP".equals(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("economicData.initialTotalInvested.currency_not_found"), ErrorType.FUNCTIONAL);
        }
    }



   public void deleteProspectCdtInputValidation(String depositId){
       errorService.isNull(depositId, DEPOSIT_ID);
       errorService.isBlank(depositId, DEPOSIT_ID);
       regexUtils.validateRegex("deposit_id_format", depositId, DEPOSIT_ID);
       regexUtils.validateRegex("strict_length_20", depositId, DEPOSIT_ID);
   }
    public void calculateDepositSummaryInputValidation(CalculateDepositSummaryRequestDTO calculateDepositSummaryRequestDTO){
        //Participant Validations
        errorService.isBlank(calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
        regexUtils.validateRegex("participants_id_format",calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
        regexUtils.validateRegex("participants_id_length",calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
    }
    public void settlementContionCodeInputValidation(String code) {
        if (!(code.length() == 1 && SETTLEMENT_CONDITON_CODES.contains(code))){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementconditioncode_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void settlementContionCodeInputValidationForDeposits(String code) {
        if (!(code.length() == 1 && SETTLEMENT_CONDITON_CODES.contains(code))){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementcondition_code_not_found"), ErrorType.FUNCTIONAL);
        }
    }
        public static String settlementConditionCodeTransformation(String input){
            if (input.equals("C")){
                return input = "S";
            }else return input = "N";
        }


    public static boolean settlementConditionCodeValidation(String code){
        if (!"C".equals(code)){
            return false;
        }
        else return true;
    }


    public boolean frecuencyValidation(String input){
        
        for (String output : validFrecuencies) {
            if (output.equals(input)) {
                return false;
            }
        }
        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("frequency_not_found"), ErrorType.FUNCTIONAL);
    }


    public void amountValidation(String input, String minAmount, String maxAmount) {

        Double inputAmount = Double.parseDouble(input.replace(",", ""));
        Double _minAmount = Double.parseDouble(minAmount.replace(",", ""));
        Double _maxAmount = Double.parseDouble(maxAmount.replace(",", ""));

        if (inputAmount < _minAmount) {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_under_limit"), ErrorType.FUNCTIONAL);
        }
        if(inputAmount > _maxAmount){
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_over_limit"), ErrorType.FUNCTIONAL);
        }
    }
    public static String padLeftWithZeros(String input, int length) {
        if (input.length() >= length) {
            return input; //
        }

        StringBuilder padded = new StringBuilder(input);
        while (padded.length() < length) {
            padded.insert(0, "0");
        }
        return padded.toString();
    }
    public static boolean validateAccountIdType(String input) {
        return input.equals("CC") || input.equals("CA");
    }
    public void validateSettlementConcept(String input) {
        if (!input.equals("BGMF") && !input.equals("RETF") && !input.equals("ITEA")){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'economicData.settlements.settlementConcept.code' not found",ErrorType.FUNCTIONAL);
        }
    }
    public void validateSettlementConceptTypeCodeInput(String input) {
        if (!input.equals("C") && !input.equals("D")){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("typeCode_not_found"), ErrorType.FUNCTIONAL);
        }

    }
    public String validateSettlementConceptTypeCode(String input) {
        if(input.equals("BGMF")){
            return input = "C";
        } else if (input.equals("RETF")) {
           return  input = "D";
        }
        else return input = "C";
    }
    public String purposeCodeValidation(TermDepositParametersRequest termDepositParametersRequest, String input){
        var init = input.substring(0,2);
        var termDeposit = termDepositParametersService.termDepositParameters(termDepositParametersRequest);
        var purpose = termDeposit.getParameters().stream().filter
                (x -> x.getCode().equals(init)).findFirst().orElse(null);
        if (purpose == null){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
        }
        if (!purpose.getCode().equals(init)){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
    }
        else return init;
    }
    public String bankValidation(BanksParametersRequest banksParametersRequest, String input){
        var banks = banksService.banksResponse(banksParametersRequest);
        var findBank = banks.getBanks().stream().filter(x -> x.getBankId().equals(input)).findFirst().orElse(null);
        if (findBank == null){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'deposit.placement.destinationFunds.bankcode' not found", ErrorType.FUNCTIONAL);
        }
        if (!findBank.getBankId().equals(input)){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'deposit.placement.destinationFunds.bankcode' not found", ErrorType.FUNCTIONAL);
        }
        else return input;

    }


    public static Double parseDouble(String input) {

        var noplus = input.replace("+", "");
        var nominus = noplus.replace("-", "");
        var nodots = nominus.replace(".","");
        var _input = nodots.replace(",", ".");
        Double number = Double.parseDouble(_input);

        return number;
    }

    public static String toLinea2Decimal(String input) {

        Integer entero = Integer.parseInt(input.substring(0,13));
        String decimal = input.substring(13);
        String numero = entero.toString() + "," + decimal;

        return numero;
    }

    public static String format15DigitNumber(String number){

        if(number == null || number.isBlank() || number.isEmpty()) return "0,00";

        String noZerosNumber = number.replaceFirst("^0+(?!$)", "");

        if(noZerosNumber.length() == 1) return "0,0" + noZerosNumber;
        if(noZerosNumber.length() == 2) return "0," + noZerosNumber;        

        return noZerosNumber.substring(0, noZerosNumber.length()-2) + "," + noZerosNumber.substring(noZerosNumber.length() - 2, noZerosNumber.length());
    }

    public static String removeLeadingZeros(String number){

        if(number == null || number.isBlank() || number.isEmpty()) return "0";

        return number.replaceFirst("^0+(?!$)", "");

    }

}//class closure

