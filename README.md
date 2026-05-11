package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.mappers;



import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.*;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ElectronicAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.PhoneAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.generic.pagination.PaginationDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.search.response.*;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.utils.TimeUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
        CodeNameDTO townObj = getTown(personData.getCiudad(),securityHeaders);
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

        responseDTO.setPostalAddress(getPostalAddressBasic(personData,countryObj,cityObj,townObj,securityHeaders));

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
        //TOWN
        CodeNameDTO townObj = getCity(personData.getCiudad(),securityHeaders);
        document.setTown(townObj);
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));




        //Contact Point
        ContactPointCustomerSearchDTO contactPoint = new ContactPointCustomerSearchDTO();
        contactPoint.setPostalAddress(getPostalAddressBasic(personData,countryObj,cityObj,townObj,securityHeaders));
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

    private PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, CodeNameDTO countryObj, CodeNameDTO cityObj,CodeNameDTO townObj,SecurityHeaders securityHeaders){
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        String fulladdress = personData.getNombreVia();
        postalAddressDTO.setFullAddress(fulladdress);
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        DataListDTO wayTypeParameter = parameterApiService.getParameter(ParametersEnums.WAY_TYPE.value(), personData.getTipoVia(),securityHeaders).get(0);
        postalAddressDTO.setStreetTypeDescription(wayTypeParameter.getDescription());       
        

        postalAddressDTO.setTown(townObj);
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
        CodeNameDTO townObj = new CodeNameDTO();
        DataListDTO countryParameter = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), townCode,securityHeaders).get(0);
        townObj.setCode(countryParameter.getCode());
        townObj.setName(countryParameter.getDescription());
        return  townObj;
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
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
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
           validatePhoneAddress(contactPointsRequest, response);              
        }

        if(contactPointsRequest.getElectronicAddress() != null){
            var electronicAddress = contactPointsRequest.getElectronicAddress();

            if(electronicAddress.getEmailAddress() != null && !electronicAddress.getEmailAddress().isBlank()){
                response.setEmail(electronicAddress.getEmailAddress());
            }
        }

        return response;
    }//method closure

    public String usualtMapper(String usualt, String foreignTaxIndicator){

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES") ? foreignTaxIndicatorPositive : foreignTaxIndicatorNegative;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : defaultChannel;

        return localUsualt + localForeignTaxIndicator;
    }

    public static String cleanAddress(String address) {
        address = address.replace("#", " ");
        address = address.replace("/", " ");
        address = address.replace("-", " ");
        address = address.replace("_", " ");
        address = address.replace("&", " ");
        address = address.replace("#", " ");
        address = address.replace(".", " ");
        address = address.replace(",", " ");
        address = address.replace("°", " ");
        address = address.replace(";", " ");
        address = address.replace(":", " ");
        address = address.replace("*", " ");
        address = address.replace("+", " ");
        address = address.replace("%", " ");
        address = address.replace("$", " ");
        address = address.replace("ñ", "n");
        address = address.replace("Ñ", "N");
        address = address.replace("á", "a");
        address = address.replace("é", "e");
        address = address.replace("í", "i");
        address = address.replace("ó", "o");
        address = address.replace("ú", "u");
        address = address.replace("Á", "A");
        address = address.replace("É", "E");
        address = address.replace("Í", "I");
        address = address.replace("Ó", "O");
        address = address.replace("Ú", "U");
        return address;

    }

    public static String replaceSpaces(String cadena) {
        // Primero reemplazar espacios triples
        Pattern patternTriples = Pattern.compile("\\s{3}");
        Matcher matcherTriples = patternTriples.matcher(cadena);
        String cadenaSinTriples = matcherTriples.replaceAll(" ");

        // Luego reemplazar espacios dobles
        Pattern patternDobles = Pattern.compile("\\s{2}");
        Matcher matcherDobles = patternDobles.matcher(cadenaSinTriples);
        return matcherDobles.replaceAll(" ").trim();
    }

    private void validatePostalAddress(ContactPointsRequestDTO contactPointsRequest,BasicData response){
        var postalAddress = contactPointsRequest.getPostalAddress();

        // StreetTypeCode
        String streetTypeCode = postalAddress.getStreetTypeCode();
        if( streetTypeCode != null && !streetTypeCode.isBlank() )
            response.setTipoVia( postalAddress.getStreetTypeCode() );
            

        String fullAddress = postalAddress.getFullAddress();
        if( fullAddress != null && !fullAddress.isBlank() ){
            fullAddress = cleanAddress(fullAddress);
            fullAddress = replaceSpaces(fullAddress);
            response.setNombreVia( fullAddress );
        }         

        if(postalAddress.getTown() != null && !postalAddress.getTown().isBlank()){
            response.setCiudad(postalAddress.getTown());
        }

        if( postalAddress.getPremise() != null && !postalAddress.getPremise().isBlank()){
            String formatPremise = postalAddress.getPremise();
            formatPremise = cleanAddress(formatPremise);
            formatPremise = replaceSpaces(formatPremise);
            response.setDescripcionDireccion(formatPremise);
        }
    }

    private void validatePhoneAddress(ContactPointsRequestDTO contactPointsRequest,BasicData response){
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

    
}//class closure
