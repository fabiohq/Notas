package com.santander.bnc.bsn049.bncbsn049mscustomer.service.impl;


import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.CustomerDetailsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDictionary;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.mappers.CustomerMapper;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.CustomerService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.santander.bnc.bsn049.bncbsn049mscustomer.utils.CustomerMapperUtils.isPenumperValid;
import static com.santander.bnc.bsn049.bncbsn049mscustomer.utils.StringUtils.rightPad;

/**
 * @author Wilfredo Pena
 * This clas handle all main methods from MS-Customer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerSevicerImpl implements CustomerService {

    final TrxPersonService trxPersonService;
    final RegexUtils regexUtils;
    final CustomerMapper mapper;
    final CustomerMapperUtils mapperUtils;
    final ErrorService errorService;
    private static final String DOCUMENT_NUMBER_FIELD = "person.documents.documentNumber";
    private static final String DOCUMENT_TYPE_CODE_FIELD = "person.documents.documentTypeCode";

    private String SOURCECODE_FIELD = "person.dataOrigins.sourceCode";

    @Value("${params.default-update-agrofic}")
    private String DEFAULT_AGROFIC;

    @Override
    public CustomerDetailsResponseDTO getCustomerDetails(String customerId, SecurityHeaders securityHeaders) {
        ObjectMapper mapResponse = new ObjectMapper();
        log.info(GUtils.SLOG + "service get customer details {}", customerId);
        TrxPersonResponse responseTrx;
        CustomerDetailsResponseDTO customerDetailsResponseDTO;

        if (Boolean.FALSE.equals(isPenumperValid(customerId))) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.INVALID_CUSTOMER_ID);
        }

        responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, customerId), ClientEnum.PEF3);
        customerDetailsResponseDTO = mapper.trxPersonToCustomerDetailsDTO(responseTrx, securityHeaders);

        if (customerDetailsResponseDTO == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PERSON_IS_NOT_CLIENT);
        }

        // Manejo de ciudad de nacimiento y expedición en blanco
        if (customerDetailsResponseDTO.getPerson().getPlaceOfBirth() != null && customerDetailsResponseDTO.getPerson().getPlaceOfBirth().getTown().isBlank()) {
            customerDetailsResponseDTO.getPerson().getPlaceOfBirth().setTown(""); // Mantener el campo como vacío
        }

        for (DocumentDTO document : customerDetailsResponseDTO.getPerson().getDocuments()) {
            if (document.getTown().isBlank()) {
                document.setTown(""); // Mantener el campo como vacío
            }
        }

        log.info(GUtils.ELOG + "service get customer details response={}", customerDetailsResponseDTO);
        return customerDetailsResponseDTO;
    }//method closure

    /**
     * Search customer by customerID
     *
     * @param customerRequestDTO
     * @return
     */
    @Override
    public CustomerSearchResponseDTO searchCustomer(CustomerRequestDTO customerRequestDTO, SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "service search person {}", customerRequestDTO.getPerson());

        // Validar expresiones regulares para otros campos
        errorService.isNull(customerRequestDTO.getPerson().getDocument().getDocumentNumber(), DOCUMENT_NUMBER_FIELD);
        errorService.isBlank(customerRequestDTO.getPerson().getDocument().getDocumentNumber(), DOCUMENT_NUMBER_FIELD);
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, customerRequestDTO.getPerson().getDocument().getDocumentNumber(), DOCUMENT_NUMBER_FIELD);
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_11, customerRequestDTO.getPerson().getDocument().getDocumentNumber(), DOCUMENT_NUMBER_FIELD);

        errorService.isNull(customerRequestDTO.getPerson().getDocument().getDocumentTypeCode(), DOCUMENT_TYPE_CODE_FIELD);
        errorService.isBlank(customerRequestDTO.getPerson().getDocument().getDocumentTypeCode(), DOCUMENT_TYPE_CODE_FIELD);
        regexUtils.validateRegex(RegexTypes.DOCUMENT_TYPE_FORMAT, customerRequestDTO.getPerson().getDocument().getDocumentTypeCode(), DOCUMENT_TYPE_CODE_FIELD);
        regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, customerRequestDTO.getPerson().getDocument().getDocumentTypeCode(), DOCUMENT_TYPE_CODE_FIELD);

        TrxPersonResponse responseTrx;
        CustomerSearchResponseDTO customerResponseDTO;

        try {
            responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(customerRequestDTO, null), ClientEnum.PEF3);
        } catch (ServiceException e) {
            if (e.getMessage().equals(ErrorDictionary.CUSTOMER_NOT_FOUND)) {
                return null;
            }
            throw e;
        }

        customerResponseDTO = mapper.trxPersonToCustomerSearchDTO(responseTrx, securityHeaders);
        log.info(GUtils.ELOG + "service search person response={}", customerResponseDTO);
        return customerResponseDTO;
    }//method closure


    @Override
    public void updateCustomer(CreateCustomerRequestDTO customerRequestDTO, String customerId, SecurityHeaders securityHeaders) {
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, customerId, "customerId");
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, customerId, "customerId");

        log.info(GUtils.SLOG + "service update prospect id {} data= {}", customerId, customerRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}", customerId);
        responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, customerId), ClientEnum.PEF3);
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

        //VALIDA CONPER == NCL || CLI
        if (CustomerMapperUtils.isNotCustomer(trxBasicData.getConper())) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PERSON_IS_NOT_CUSTOMER);
        }
        log.info("responseTrx = {}", responseTrx);

        // Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData = mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData from pef3 = {}", basicData);

        // Limpio datos que no quiero modificar en customer
        customerRequestDTO = CustomerMapperUtils.cleanFieldsFromProspectUpdate(customerRequestDTO);
        // Transformo el request del PATCH en BasicData
        BasicData basicDataRequestPatch = mapper.prospectPatchToPef2Request(customerRequestDTO, securityHeaders, basicData.getTipoIdentificacion());
        log.info("basicData request patch = {}", basicDataRequestPatch);
        BeanUtils.copyProperties(basicDataRequestPatch, basicData, GUtils.getNullOrBlankPropertyNames(basicDataRequestPatch));

        // Aquí se maneja la lógica específica para el campo town
        if (basicData.getCiudadExpedicion() != null &&
                basicData.getCiudadExpedicion().isEmpty()) {
            basicData.setCiudadExpedicion("99999");
        }
        if (basicData.getCiudadNacimiento() != null &&
                basicData.getCiudadNacimiento().isEmpty() && basicData.getTipoIdentificacion().equals("CC")) {
            basicData.setCiudadNacimiento("99999");
        }



        String expirationDate = basicDataRequestPatch.getDescripcionDireccion();
        String premiseCopia = trxBasicData.getDescripcionDireccion();

        // Añade la validación y relleno si no tiene 60 caracteres
        if (premiseCopia.length() != 60) {
            premiseCopia = rightPad(premiseCopia.substring(0, premiseCopia.length() - 10), 50, ' ') + premiseCopia.substring(premiseCopia.length() - 10);
            basicData.setDescripcionDireccion(premiseCopia);
        }



        if (premiseCopia.length() == 60 && expirationDate != null) {
            // Construir premise + expirationDate
            String finalPremise = premiseCopia.substring(0, premiseCopia.length() - 10) + expirationDate;
            basicDataRequestPatch.setDescripcionDireccion(finalPremise);
            basicData.setDescripcionDireccion(finalPremise);
        }



        var secondLastNameOld = basicDataRequestPatch.getSegundoApellido();
        if (secondLastNameOld != null && secondLastNameOld.equals("")) {
            basicData.setSegundoApellido("");
        }

        log.info("BasicData afterCopy = {}", basicData);

        if (customerRequestDTO.getPerson() != null) {
            basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(), customerRequestDTO.getPerson().getForeignTaxIndicator()));
        } else {
            basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(), null));
        }

        // removeCustomer
        if (customerRequestDTO.getDataOrigins() != null &&
                !customerRequestDTO.getDataOrigins().isEmpty() &&
                customerRequestDTO.getDataOrigins().get(0) != null &&
                customerRequestDTO.getDataOrigins().get(0).getSourceCode() != null) {

            String sourceCode = customerRequestDTO.getDataOrigins().get(0).getSourceCode();

            errorService.isBlank(sourceCode, SOURCECODE_FIELD);
            regexUtils.validateRegex(RegexTypes.DATAORIGIN_SOURCECODE_FORMAT, sourceCode, SOURCECODE_FIELD);
            regexUtils.validateRegex(RegexTypes.DATAORIGIN_SOURCECODE_LENGTH, sourceCode, SOURCECODE_FIELD);

            if (sourceCode.equals("OTRO")) {
                basicData.setUsualt(mapperUtils.removeCustomer(trxBasicData));
            }
        }

        if (basicData.getEstciv() != null) {
            if (basicData.getEstciv().isBlank()) {
                basicData.setEstciv("S");
            } else if (basicData.getEstciv().equals("S")) {
                basicData.setEstciv("C");
            } else {
                basicData.setEstciv("S");
            }
        } else {
            basicData.setEstciv("S");
        }

        // Se setea valor por omisión para el campo agrofic al actualizar customer
        basicData.setAgrofic(DEFAULT_AGROFIC);

        TrxPersonRequest pef2Request = new TrxPersonRequest();
        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);

        pef2Request.setData(trxPersonDataRequest);

        // UPDATE PROSPECT
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");
    }



    @Override
    public void updateCustomersProspect(UpdateProspectRequestDTO customerRequestDTO, String customerId,SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "service update customer to prospect id {} data= {}",customerId, customerRequestDTO.getPerson());
        TrxPersonResponse responseTrx;

        // AQUI EL CACHE GET!!
        log.info("Start to get customer {}",customerId);
        responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, customerId)
                , ClientEnum.PEF3);
        TrxPersonData trxBasicData = responseTrx.getData().getDatosBasicos();

//        VALIDA CONPER == PRO
        if( CustomerMapperUtils.isNotCustomer(trxBasicData.getConper())){
            log.info("Actualizo PRO a NCL {}",customerId);
            trxBasicData.setConper("NCL");
        } else {
            log.info("Id ya es cliente {}",customerId);
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.PERSON_IS_NOT_PROSPECT);
        }

        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF4
        BasicData basicData =  mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);
        
        //Transformo el request del PATCH en BasicData
        BasicData basicDataRequestPatch = mapper.prospectPutToPef2Request(customerRequestDTO,securityHeaders);
        log.info("basicData request patch = {}", basicDataRequestPatch);
        //Copio propiedades que deseo modificar al nuevo objeto
        BeanUtils.copyProperties(basicDataRequestPatch,basicData,GUtils.getNullOrBlankPropertyNames(basicDataRequestPatch));
        log.info("BasicData  afterCopy    = {}", basicData);
        if(basicData.getTipoIdentificacion().equals("CE")){
            basicData.setCiudadNacimiento("");
        }
        
        if(customerRequestDTO.getPerson() != null){
            basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(), customerRequestDTO.getPerson().getForeignTaxIndicator() ));
        } else {
            basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(), null ));            
        }

        // Se setea valor por omisión para el campo agrofic al pasar de prospect a customer
        basicData.setAgrofic(DEFAULT_AGROFIC);

        TrxPersonRequest pef2Request = new TrxPersonRequest();
        TrxPersonDataRequest trxPersonDataRequest = new TrxPersonDataRequest();
        trxPersonDataRequest.setDatosBasicos(basicData);
        pef2Request.setData(trxPersonDataRequest);
        //UPDATE PROSPECT
        trxPersonService.callPostTRX(pef2Request, ClientEnum.PEF2);
        log.info(GUtils.ELOG + "service update prospect response");

    }

}//class closure




package com.santander.bnc.bsn049.bncbsn049mscustomer.service.impl;


import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.GUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParamsServiceImpl implements ParamService {

    @Autowired
    ParameterApiService parameterApiService;

    private final static String SPLIT_KEY = "split";

    private String getDocumentTypeDescription(TrxPersonData personData,SecurityHeaders securityHeaders) {

        if(personData.getTipoIdentificacion() != null && !personData.getTipoIdentificacion().isBlank()){
            var parameterResponse = parameterApiService.getParameter(ParametersEnums.DOCU_TYPE.value(), personData.getTipoIdentificacion(),securityHeaders);

            if( parameterResponse != null && !parameterResponse.isEmpty() ){
                return parameterResponse.get(0).getDescription();
            }
            return "";
        }

        return "";        
    }

    private String getWayTypeDescription(TrxPersonData personData, SecurityHeaders securityHeaders ){
        if(personData.getTipoVia() != null && !personData.getTipoVia().isEmpty()){

            var parameterResponse =  parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), personData.getTipoVia(),securityHeaders);

            if(!parameterResponse.isEmpty()){
                return parameterResponse.get(0).getDescription();
            }
            return "";
        }
        return "";
    }

    private CodeNameDTO getCountry(String country,SecurityHeaders securityHeaders) {
        CodeNameDTO countryObj = new CodeNameDTO();

        var parameterResponse = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), country,securityHeaders);

        if( parameterResponse != null && !parameterResponse.isEmpty() ) {
            DataListDTO countryParameter = parameterResponse.get(0);    
            
            countryObj.setCode(countryParameter.getCode());
            countryObj.setName(countryParameter.getDescription());
        }

        return countryObj;
    }//method closure

    private CodeNameDTO getTown(String townCode,SecurityHeaders securityHeaders) {
        
        CodeNameDTO countryObj = new CodeNameDTO();

        var parameterResponse = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,securityHeaders);

        if(parameterResponse != null && !parameterResponse.isEmpty()){
            DataListDTO countryParameter = parameterResponse.get(0);

            countryObj.setCode(countryParameter.getCode());
            countryObj.setName(countryParameter.getDescription());
        }

        return countryObj;

    }//method closure

    private CodeNameDTO getCity(String city,SecurityHeaders securityHeaders) {
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


    public Parameters findParameters(TrxPersonData personData,SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "Find parameters");
        String sNationality = personData.getNacionalidad();
        String sExp = personData.getPaisExpedicion();
        String sBirth = personData.getPaisNacimiento();
        String sCountryDir = personData.getPaisDireccion();
        String sCity = personData.getCiudad();
        String sDepartment = personData.getDepartamento();
        String sCityExp = personData.getCiudadExpedicion();
        String sCityBirth = personData.getCiudadNacimiento();

        List<CodeNameDTO> countries = getListParameterByGroup(new String[]{sNationality, sExp, sBirth, sCountryDir}, "Country",securityHeaders);
        List<CodeNameDTO> cities = getListParameterByGroup(new String[]{sCity, sDepartment, sCityExp, sCityBirth}, "City",securityHeaders);

        Parameters response = new Parameters();
        response.setCountryNationality(getKey(countries, sNationality));
        response.setCountryExp(getKey(countries, sExp));
        response.setCountryBirth(getKey(countries, sBirth));
        response.setCountryDir(getKey(countries, sCountryDir));
        if(sCity != null && !sCity.isBlank()){
            response.setCityStandard(getTown(sCity,securityHeaders));
        }
        response.setCityDepartment(getKey(cities, sDepartment));
        if(sCityExp != null && !sCityExp.isBlank()){
            response.setCityExp(getKey(cities, sCityExp));
            response.setTownDocument(getTown(sCityExp, securityHeaders));
        }
        response.setCityBirth(getKey(cities, sCityBirth));
        //Extras
        if(sCityBirth != null && !sCityBirth.isBlank()){
            response.setTown(getTown(sCityBirth,securityHeaders));
        }
        response.setStreetTypeDescription(getWayTypeDescription(personData,securityHeaders));
        response.setDocumentTypeDescription(getDocumentTypeDescription(personData,securityHeaders));

        log.info(GUtils.ELOG + "Return parameters = {} ", response);
        return response;
    }//method closure

    private List<CodeNameDTO> getListParameterByGroup(String[] parameterGroup, String type,SecurityHeaders securityHeaders) {
        log.info("    find {}'s = {} ", type, parameterGroup);
        List<CodeNameDTO> listCodename = new ArrayList<>();
        Map<String, Long> groupMap = Arrays.stream(parameterGroup).filter(Objects::nonNull).filter(param -> !param.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("    look just for = " + groupMap);
        groupMap.forEach((key, count) -> {
                    if ("Country".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCountry(key,securityHeaders);
                        codeNameDTO.setCode(codeNameDTO.getCode() + SPLIT_KEY + key);
                        listCodename.add(codeNameDTO);
                    } else if ("City".equalsIgnoreCase(type)) {
                        CodeNameDTO codeNameDTO = getCity(key,securityHeaders);
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
