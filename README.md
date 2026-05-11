package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;

public class ClientUtils {
    private ClientUtils() {
        throw new UnsupportedOperationException("");
    }
    /**
     * This method
     * @param customer_id
     * @return
     */
    public static TrxPersonRequest buildTrxRequestByCustomerId(CustomerRequestDTO customerRequestDTO, String customer_id){
        TrxPersonRequest requestTrx = new TrxPersonRequest();
        TrxPersonDataRequest personData = new TrxPersonDataRequest();

        personData.setPenumpe(StringUtils.blankField(customer_id));   

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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;



import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperUtils {

    
    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegativeResponse;
    

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
        postalAddressDTO.setTown(pa.getCityStandard());
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
    public static boolean isNotCustomer(String conper) {
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
}//class closure


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Global Utils
 */
public class GUtils {

    private GUtils() {
        throw new UnsupportedOperationException("");
    }
    /**
     * Start log
     */
    public static final String SLOG = "--> Start ";

    /**
     * End log
     */
    public static final String ELOG = "<-- End ";


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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

public enum RegexTypes {

    ONLY_NUMBERS,
    STRICT_LENGTH_8,
    STRICT_CHAR_LENGTH_2,
    EMAIL,
    EMAIL_LENGTH_60,
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
    PREMISE_FORMAT,
    STREET_TYPE_CODE_FORMAT,
    EMAIL_BETWEEN;
    
    
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorDTO;
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

    @Value("${regex.email_between}")
    private String REGEX_EMAIL_BETWEEN;

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
            case EMAIL_LENGTH_60:
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
            case EMAIL_BETWEEN:
                regularExpression = REGEX_EMAIL_BETWEEN;
                message = REGEX_EMAIL_ERROR;
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 * @author Wilfredo Pena
 */
public class ServiceDirectory {
    
    private ServiceDirectory() {
        throw new UnsupportedOperationException("");
    }

    private static final String API_VERSION = "/v2";
    private static final String CUSTOMER_ENDPOINT = "/customer_contact_points";

    /**
     * Endpoints
     */
    public static final String CUSTOMERS = API_VERSION + CUSTOMER_ENDPOINT;
    public static final String CUSTOMERS_SEARCH = API_VERSION + CUSTOMER_ENDPOINT + "/search";
}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("");
    }
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    
    private TimeUtils() {
        throw new UnsupportedOperationException("");
    }
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.impl;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ContextApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.TrxPersonService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ClientEnum;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.mappers.CustomerContactPointsMapper;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.CustomerContactPointsService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.ClientUtils;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.CustomerMapperUtils;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.RegexTypes;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.StringUtils;

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
@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerSevicerImpl implements CustomerContactPointsService {


    /**
     * Client Trx
     */    
    final TrxPersonService trxPersonService;
    /**
     * cahce service
     */ 
    final ContextApiService contextService;    

 
    final CustomerContactPointsMapper mapper;
  
    final RegexUtils regexUtils;
  
    final CustomerMapperUtils mapperUtils;
   
    final ErrorService errorService;

   
    final ParameterApiService parameterApiService;

    private String fieldStreetTypeCode = "streetTypeCode";
    private String fieldFullAddress = "fullAddress";
    private String fieldPremise = "premise"; 
    private String fieldMobileNumber = "mobileNumber";   
    private String fieldPhoneNumber = "phoneNumber";
    private String fieldInternationalCode = "internationalCode";
    private String fieldEmailAddress = "emailAddress"; 
    private String noInformado = "NO INFORMADO";  
    
    


    @Value("${params.default-contact-point-id}")
    private String contactPointID; 


    /**
     * Find Customer
     *
     * @param customer_id
     * @return
     */
    @Override
    public ContactPointsResponseDTO getCustomerDetails(String customerId,SecurityHeaders securityHeaders) {
        ObjectMapper mapResponse = new ObjectMapper();
        log.info(GUtils.SLOG + "service get customer details {}", customerId);
        TrxPersonResponse responseTrx;
        ContactPointsResponseDTO customerDetailsResponseDTO;

        if(Boolean.FALSE.equals(isPenumperValid(customerId))){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidCustomerId());
        }

        ContactPointsResponseDTO getResponse = mapResponse.convertValue(contextService.getContext(customerId), ContactPointsResponseDTO.class);
        if (getResponse != null) {
            return getResponse;
        }
        responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, customerId)
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
    public void putCustomerContactPoint(String customerId, String contactPointId, ContactPointsRequestDTO contactPointRequest,SecurityHeaders securityHeaders) {

        log.info(GUtils.SLOG + "service putCustomerContactPoint");

        if(contactPointRequest == null){
            var message = "'body' " + errorService.invalidValue;
            throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }


        // region: VALIDACIONES
        // CustomerId regex
        regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, customerId, "customer_id");
        // CustomerId length
        regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, customerId, "customer_id");
        // ContactPoint id
        if(!contactPointId.equals(contactPointID)){
            var message = "'contact_point_id' " + errorService.invalidValue;
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
                regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH_60, electronicAddress.getEmailAddress(), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, String.valueOf(electronicAddress.getEmailAddress().charAt(0)), fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, electronicAddress.getEmailAddress(),fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, electronicAddress.getEmailAddress().split("@")[0], fieldEmailAddress);
                regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, electronicAddress.getEmailAddress().split("@")[1], fieldEmailAddress);
            }
        }
        // endregion
        

        TrxPersonResponse responseTrx = trxPersonService.callPostTRX(ClientUtils.buildTrxRequestByCustomerId(null, customerId)
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
        if( CustomerMapperUtils.isNotCustomer(trxBasicData.getConper())){
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidCustomerId());
        }

        log.info("responseTrx = {}", responseTrx);
        //Transformar PEF3 response (o caché) a Request PEF2
        BasicData basicData =  mapper.pef3ResponseToPef2Request(trxBasicData);
        log.info("BasicData   from pef3  = {}", basicData);

        //Transformo el request del YML en BasicData
        BasicData basicDataRequestPatch = mapper.contactPointPatchToPef2Request(contactPointRequest);
        log.info("basicData request patch = {}", basicDataRequestPatch);


        //Copio propiedades que deseo modificar al nuevo objeto
        BeanUtils.copyProperties(basicDataRequestPatch,basicData,GUtils.getNullPropertyNames(basicDataRequestPatch));
        log.info("BasicData  afterCopy    = {}", basicData);
        String foreignTaxIndicator = mapperUtils.getForeignTaxIndicator(trxBasicData);

        basicData.setUsualt(mapper.usualtMapper(basicData.getUsualt(), foreignTaxIndicator));

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
            regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, fullAddress, fieldFullAddress);
            regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, fullAddress, fieldFullAddress);
        }   

        // TownName
        if(postalAddress.getTown() != null ){

            errorService.isBlank(postalAddress.getTown(), "town");
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null,securityHeaders);

            if(towns != null){
                var varTown = towns.stream().filter(x -> x.getCode().equals(postalAddress.getTown())).findAny();

                if(!varTown.isPresent()){
                    var message = "'town' " + errorService.invalidValue;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                contactPointRequest.getPostalAddress().setTown(varTown.get().getCode());                    
            }
        }

        // Premise

        if( postalAddress.getPremise() == null || postalAddress.getPremise().isBlank()){
            if(postalAddress.getFullAddress() != null){           
            postalAddress.setPremise(noInformado);
            }
        }else{
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


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ParametersEnums;

@Service
public class ParamServiceImpl {

    @Autowired
    ParameterApiService parameterApiService;


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

    public String getWayTypes(String wayType,SecurityHeaders securityHeaders){
        if(wayType != null && !wayType.isEmpty()){

            var parameterResponse =  parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), wayType,securityHeaders);

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


