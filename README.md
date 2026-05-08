package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParamsServiceImpl implements ParamService {
    
    final ParameterApiService parameterApiService;
    final ContextApiService contextService;    
    private ObjectMapper objectMapper;
    
    @Autowired
    public ParamsServiceImpl(ParameterApiService parameterApiService, ContextApiService contextApiService){
        this.parameterApiService = parameterApiService;
        this.contextService = contextApiService;
        this.objectMapper = new ObjectMapper();
    }


    private static final String SPLIT_KEY = "split";


    /**
     * Get document decription
     * @param personData
     * @return document description
     */
    private String getDocumentTypeDescription(TrxPersonData personData,String authorization,String xSantanderClientId) {
        
        if(personData.getTipoIdentificacion() == null || personData.getTipoIdentificacion().isBlank()) return "";            

        String docDescContextKey = ParametersEnums.DOCU_TYPE.value() + "-" + personData.getTipoIdentificacion();

        DataListDTO documentTypes = objectMapper.convertValue(contextService.getContext(docDescContextKey), DataListDTO.class);
        if( documentTypes == null){
            documentTypes = parameterApiService.getParameter(ParametersEnums.DOCU_TYPE.value(), personData.getTipoIdentificacion(),authorization,xSantanderClientId).get(0);
        }

       
        return documentTypes.getDescription();
    
    }

    /**
     * Get way types description
     * @param personData
     * @return wayType description
     */
    private String getWayTypeDescription(TrxPersonData personData,String authorization,String xSantanderClientId){

        if(personData.getTipoVia() == null || personData.getTipoVia().isEmpty()) return "";            

        String wayTypeContextKey = ParametersEnums.WAY_TYPE.value() + "-" + personData.getTipoVia();

        DataListDTO wayTypes = objectMapper.convertValue(contextService.getContext(wayTypeContextKey), DataListDTO.class);

        if(wayTypes == null){
            wayTypes = parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), personData.getTipoVia(),authorization,xSantanderClientId).get(0);
        }

    
        return wayTypes.getDescription();
    }


    /**
     * Get country
     * @param securityHeaders
     * @return country
     */
    public CodeNameDTO getCountry(String country,String authorization,String xSantanderClientId) {
        CodeNameDTO countryObj = new CodeNameDTO();
        String countryContextKey = ParametersEnums.COUNTRY.value() + (country != null && !country.isBlank() ? "-" + country : "");

        DataListDTO countryParameter = objectMapper.convertValue(contextService.getContext(countryContextKey), DataListDTO.class);
        if( countryParameter == null ){
            countryParameter = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), country,authorization,xSantanderClientId).get(0);
        }
        
        countryObj.setCode(countryParameter.getCode());
        countryObj.setName(countryParameter.getDescription());

       
        return countryObj;
    }//method closure


    /**
     * Get town
     * @param securityHeaders
     * @return town code and name
     */
    public CodeNameDTO getTown(String townCode,String authorization,String xSantanderClientId) {
        String townContextKey = ParametersEnums.TOWNS.value() + (townCode != null && !townCode.isBlank() ? "-" + townCode : "");
        CodeNameDTO townObj = new CodeNameDTO();

        DataListDTO townParameter = objectMapper.convertValue(contextService.getContext(townContextKey), DataListDTO.class);
        if( townParameter == null ){
            townParameter = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,authorization,xSantanderClientId).get(0);
        }

        townObj.setCode(townParameter.getCode());
        townObj.setName(townParameter.getDescription());

      
        return townObj;
    }//method closure

    /**
     * Get city
     * @param securityHeaders
     * @return city code and name
     */
    public CodeNameDTO getCity(String city,String authorization,String xSantanderClientId) {
        String cityContextKey = ParametersEnums.STATES.value();
        CodeNameDTO cityObj = new CodeNameDTO();
        

        if (!city.isEmpty()) {
            city = city.substring(0, 2);
            cityContextKey += "-" + city;
        }
        DataListDTO cityParameter = objectMapper.convertValue(contextService.getContext(cityContextKey), DataListDTO.class);
        if( cityParameter == null ){
            cityParameter = parameterApiService.getParameter(ParametersEnums.STATES.value(), city,authorization,xSantanderClientId).get(0);
        }
        cityObj.setCode(cityParameter.getCode());
        cityObj.setName(cityParameter.getDescription());

      
        return cityObj;
    }

    /**
     * Get parameters
     * @param personData
     * @return parameters list
     */
    public Parameters findParameters(TrxPersonData personData,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "Find parameters");
        String sNationality = personData.getNacionalidad();
        String sExp = personData.getPaisExpedicion();
        String sBirth = personData.getPaisNacimiento();
        String sCountryDir = personData.getPaisDireccion();
        String sCity = personData.getCiudad();
        String sDepartment = personData.getDepartamento();        
        String sCityExp = personData.getCiudadExpedicion();      
        String sCityBirth = personData.getCiudadNacimiento();
        

        List<CodeNameDTO> countries = getListParameterByGroup(new String[]{sNationality, sExp, sBirth, sCountryDir}, "Country",authorization,xSantanderClientId);
        List<CodeNameDTO> cities = getListParameterByGroup(new String[]{sCity, sDepartment, sCityExp, sCityBirth}, "City",authorization,xSantanderClientId);

        Parameters response = new Parameters();        
        response.setCountryNationality(getKey(countries, sNationality));
        response.setCountryExp(getKey(countries, sExp));
        response.setCountryBirth(getKey(countries, sBirth));
        response.setCountryDir(getKey(countries, sCountryDir));
        if(sCity != null && !sCity.isBlank()){
            response.setCityStandard(getTown(sCity,authorization,xSantanderClientId));
        }
        response.setCityDepartment(getKey(cities, sDepartment));
        if(sCityExp != null && !sCityExp.isBlank()){
            response.setCityExp(getKey(cities, sCityExp));
            response.setTownDocument(getTown(sCityExp, authorization, xSantanderClientId));
        }      
        response.setCityBirth(getKey(cities, sCityBirth));
        //Extras
        if(sCityBirth != null && !sCityBirth.isBlank()){
            response.setTown(getTown(sCityBirth,authorization,xSantanderClientId));
        }
        response.setStreetTypeDescription(getWayTypeDescription(personData,authorization,xSantanderClientId));
        response.setDocumentTypeDescription(getDocumentTypeDescription(personData,authorization,xSantanderClientId));

        log.info(GUtils.ELOG + "Return parameters = {} ", response);       
        return response;
    }//method closure

    private List<CodeNameDTO> getListParameterByGroup(String[] parameterGroup, String type,String authorization,String xSantanderClientId) {
        log.info("    find {}'s = {} ", type, parameterGroup);
        List<CodeNameDTO> listCodename = new ArrayList<>();
        Map<String, Long> groupMap = Arrays.stream(parameterGroup).filter(Objects::nonNull).filter(param -> !param.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("    look just for = " + groupMap);
        groupMap.forEach((key, count) -> {
                    if ("Country".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCountry(key,authorization,xSantanderClientId);
                        codeNameDTO.setCode(codeNameDTO.getCode() + SPLIT_KEY + key);
                        listCodename.add(codeNameDTO);
                    } else if ("City".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCity(key,authorization,xSantanderClientId);
                        codeNameDTO.setCode(codeNameDTO.getCode() + SPLIT_KEY + key);
                        listCodename.add(codeNameDTO);
                    }
                }
        );
        return listCodename;
    }//method closure

    private CodeNameDTO getKey(List<CodeNameDTO> groupCodeNameList, String key) {
                
        CodeNameDTO responseC = groupCodeNameList.stream().filter(x -> x.getCode().split(SPLIT_KEY).length == 2)
                                                        .findFirst().orElse(null);

        if(responseC != null){
            responseC = groupCodeNameList.stream().filter(x -> x.getCode().split(SPLIT_KEY)[1].equals(key))
                .findFirst().orElse(null);
        }
        
        
        CodeNameDTO response = new CodeNameDTO();
        if (responseC != null) {
            String replaced = responseC.getCode().replaceAll(SPLIT_KEY + key, "");
            response.setCode(replaced);
            response.setName(responseC.getName());
        }
        return response;
    }//method closure
}//class closure


package com.santander.bnc.bsn049.bncbsn049msprospects.service.impl;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.PatchProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ProspectService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Wilfredo Pena
 * This clas handle all main methods from MS-Customer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProspectSevicerImpl implements ProspectService {

    final TrxPersonService trxPersonService;
    final ContextApiService contextService;
    final ProspectMapper mapper;
    final PatchProspectMapper patchProspectMapper;
    final ErrorService errorService;    
    final ParameterApiService parameterApiService;    
    final ProspectMapperUtils prospectMapperUtils;
    final RegexUtils regexUtils;
    final StringUtils utils;
    private String personNotFound = "PERSONA INEXISTENTE";
    private String prospectIdField = "prospect_id";
    /**
     *  PROSPECT DETAILS
     *
     * @param prospectId
     * @return ProspectDetailResponseDTO
     */
    @Override
    public ProspectDetailResponseDTO getProspectDetails(String prospectId,String authorization,String xSantanderClientId) {
        ObjectMapper mapResponse = new ObjectMapper();
        log.info(GUtils.SLOG + "service get prospect details by id {}", prospectId);
        TrxPersonResponse responseTrx;
        ProspectDetailResponseDTO prospectDetailResponseDTO;

        if(Boolean.FALSE.equals(ProspectMapperUtils.isPenumperValid(prospectId))){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidProspectId());
        }
      

        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                    , ClientEnum.PEF3);                
        } catch (Exception e) {
            if(e.getMessage().equals(personNotFound)) {
                return null;
            }
            throw e;
        }

        prospectDetailResponseDTO = mapper.trxPersonToProspectDTO(responseTrx,authorization,xSantanderClientId);
               

        log.info(GUtils.ELOG + "service get prospect details response={}", prospectDetailResponseDTO);
        return prospectDetailResponseDTO;
    }//method closure

    /**
     * PROSPECT SEARCH
     *
     * @param prospectRequestDTO
     * @return
     */
    @Override
    public ProspectSearchResponseDTO searchProspect(ProspectRequestDTO prospectRequestDTO,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service search person {}", prospectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;
        ProspectSearchResponseDTO customerResponseDTO;

        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(prospectRequestDTO, null)
                    , ClientEnum.PEF3);            
        } catch (Exception e) {
            if(e.getMessage().equals(personNotFound)) {
                return null;
            }
            throw e;
        }
        customerResponseDTO = mapper.trxPersonToCustomerSearchDTO(responseTrx,authorization,xSantanderClientId);
        log.info(GUtils.ELOG + "service search person response={}", customerResponseDTO);
        return customerResponseDTO;
    }


    @Override
    public ProspectCreatedResponseDTO createProspect(CreateProspectRequestDTO createProspectRequestDTO,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service create person {}", createProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        var documentTypeCode = createProspectRequestDTO.getPerson().getDocuments().get(0).getDocumentTypeCode();
        var secondLastName = createProspectRequestDTO.getPerson().getPersonName().getSecondLastName();

        utils.inputSencondLastNameValidation(documentTypeCode, secondLastName);
        responseTrx = trxPersonService.callPostTRX(mapper.dtoRequestToTrxRequest(createProspectRequestDTO, authorization, xSantanderClientId)
                , ClientEnum.PEF1);
        log.info(GUtils.ELOG + "service created person response");
        return new ProspectCreatedResponseDTO(responseTrx.getData().getNumPersona());
    }

    /**
     * PROSPECT UPDATE
     *
     * @param updateProspectRequestDTO
     * @return
     */
    @Override
    public void updateProspect(PatchProspectRequestDTO updateProspectRequestDTO, String prospectId,String authorization,String xSantanderClientId) {
        log.info(GUtils.SLOG + "service update prospect id {} data= {}", prospectId, updateProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, prospectId, prospectIdField);
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, prospectId, prospectIdField);

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}", prospectId);
        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                , ClientEnum.PEF3);
        } catch (ServiceException e) {
            if(e.getMessage().equals(personNotFound)) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getProspectNotFound(), ErrorType.FUNCTIONAL);
            }
            if(e.getMessage().equals("EL CLIENTE REQUERIDO NO ES PERSONA FISICA.")){
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getPersonIsNotProspect(), ErrorType.FUNCTIONAL);
            }
            throw e;
        }        
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

        //VALIDA CONPER == PRO
        if (ProspectMapperUtils.isNotProspect(trxBasicData.getConper())) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getPersonIsNotProspect());
        }
        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData = patchProspectMapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);
        
        BasicData basicDataRequestPatch = patchProspectMapper.prospectPatchToPef2Request(PatchProspectMapperUtils.cleanFieldsFromProspectUpdate(updateProspectRequestDTO), authorization,xSantanderClientId);
        log.info("basicData request patch = {}", basicDataRequestPatch);
        //Copio propiedades que deseo modificar al nuevo objeto
        BeanUtils.copyProperties(basicDataRequestPatch, basicData, GUtils.getNullOrBlankPropertyNames(basicDataRequestPatch));

        var secondLastNameOld = basicDataRequestPatch.getSegundoApellido();
        if(secondLastNameOld != null && secondLastNameOld.equals("")){
            basicData.setSegundoApellido("");
        }
        String expirationDate= basicDataRequestPatch.getDescripcionDireccion();
        String premiseCopia = trxBasicData.getDescripcionDireccion();

        if (premiseCopia.length() == 60 && expirationDate!=null) {
            // Construir premise + expirationDate
            String finalPremise = premiseCopia.substring(0, premiseCopia.length() - 10) + expirationDate;
            basicDataRequestPatch.setDescripcionDireccion(finalPremise);
            basicData.setDescripcionDireccion(finalPremise);
            
        }   
        log.info("BasicData  afterCopy    = {}", basicData);
        TrxPersonRequest pef2Request = new TrxPersonRequest();


        if(updateProspectRequestDTO.getPerson() != null){
            basicData.setUsualt(patchProspectMapper.usualtMapper(basicData.getUsualt(), updateProspectRequestDTO.getPerson().getForeignTaxIndicator()));
        } else {
            basicData.setUsualt(patchProspectMapper.usualtMapper(basicData.getUsualt(), null));
        }

        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);            
        pef2Request.setData(trxPersonDataRequest);
        //UPDATE PROSPECT
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");

    }//method closure

    @Override
    public void removeProspect(String prospectId, String authorization, String xSantanderClientId) {

        CreateProspectRequestDTO createProspectRequestDTO = new CreateProspectRequestDTO();
        log.info(GUtils.SLOG + "service update prospect id {} data= {}", prospectId, createProspectRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, prospectId, prospectIdField);
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, prospectId, prospectIdField);

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}", prospectId);
        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, prospectId)
                    , ClientEnum.PEF3);
        } catch (ServiceException e) {
            if(e.getMessage().equals(personNotFound)) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getProspectNotFound(), ErrorType.FUNCTIONAL);
            }
            if(e.getMessage().equals("EL CLIENTE REQUERIDO NO ES PERSONA FISICA.")){
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, ErrorDictionary.getPersonIsNotProspect(), ErrorType.FUNCTIONAL);
            }
            throw e;
        }
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

        //VALIDA CONPER == PRO
        if (ProspectMapperUtils.isNotProspect(trxBasicData.getConper())) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getPersonIsNotProspect());
        }
        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData = mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);

        TrxPersonRequest pef2Request = new TrxPersonRequest();

        //Se modifica campo para poder realizar la modificación
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


        //Remove prospect
        basicData.setUsualt(prospectMapperUtils.lowProspectLogic(trxBasicData));

        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);
        pef2Request.setData(trxPersonDataRequest);

        //UPDATE PROSPECT
        log.info("Request PF2: {} ", pef2Request);
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");

    }//method closure
}//class closure


