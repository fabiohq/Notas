package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;


import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.CustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.TrxPersonRequest;

public class ClientUtils {

    /**
     * This method
     * @param customerId
     * @return
     */
    public static TrxPersonRequest buildTrxRequestByCustomerId(CustomerRequestDTO customerRequestDTO, String customerId){
        TrxPersonRequest requestTrx = new TrxPersonRequest();
        TrxPersonDataRequest personData = new TrxPersonDataRequest();

        personData.setpENUMPE(StringUtils.blankField(customerId));

        if(customerRequestDTO!=null && customerRequestDTO.getPerson()!=null){
            personData.setTipoDocumento(StringUtils.blankField(customerRequestDTO.getPerson().getDocument().getDocumentTypeCode()));
            personData.setNumDocumento(StringUtils.blankField(customerRequestDTO.getPerson().getDocument().getDocumentNumber()));
        }

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

package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

public class CompareStringUtils {

    private static final int DISTANCIA_MAXIMA = 2;

    public boolean ciudadMatch(String ciudadIngresada, String ciudadServicio) {
        double similarity = similarity(ciudadIngresada, ciudadServicio);
        return similarity > 0.85; // Consideramos que las cadenas son similares si la similitud es mayor o igual al 85%

    }

    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = initializeCostsArray(s2.length() + 1);
        return calculateEditDistance(s1, s2, costs);
    }

    private int[] initializeCostsArray(int length) {
        int[] costs = new int[length];
        for (int i = 0; i < length; i++) {
            costs[i] = i;
        }
        return costs;
    }

    private int calculateEditDistance(String s1, String s2, int[] costs) {
        int lastValue;
        for (int i = 0; i < s1.length(); i++) {
            lastValue = costs[0];
            costs[0] = i + 1;
            for (int j = 0; j < s2.length(); j++) {
                int oldValue = costs[j + 1];
                if (s1.charAt(i) == s2.charAt(j)) {
                    costs[j + 1] = lastValue;
                } else {
                    costs[j + 1] = Math.min(Math.min(costs[j], lastValue), oldValue) + 1;
                }
                lastValue = oldValue;
            }
        }
        return costs[s2.length()];
    }

}

package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;



import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerMapperUtils {

    
    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegativeResponse;

    @Value("${params.deleteCustomerCode}")
    private String deleteCustomerCode;
    

    public static PersonDTO personDTONames(TrxPersonData personData, Parameters pa) {
        PersonDTO personDTO = new PersonDTO();
        String genderDesc = "HOMBRE";
        String genderCode = "H";
        if (personData.getSexo() != null && !personData.getSexo().isEmpty() && !personData.getSexo().equalsIgnoreCase("H")) {
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
        personDTO.getPersonName().setFullName(fullName.trim());
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
        placeOfBirthDTO.setTown(pa.getTown() != null ? pa.getTown().getName() : "");
        return placeOfBirthDTO;
    }//method closure

    /*
     * Obtains record origin
     */
    public static String getSourceCode(TrxPersonData personData) {
        String sourceCode = "OTRO";
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 2){
            sourceCode = usualt.substring(0,3);
        }

        return sourceCode.equals("ODS") ? sourceCode : "OTRO";
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

    //    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
//        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
//        postalAddressDTO.setFullAddress(personData.getNombreVia());
//        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
//        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
//        postalAddressDTO.setTown(pa.getCityStandard());
//        postalAddressDTO.setCountry(pa.getCountryDir());
//        postalAddressDTO.setState(pa.getCityDepartment());
//        if (personData.getDescripcionDireccion() != null) {
//            String premise = StringUtils.replaceSpaces(personData.getDescripcionDireccion().substring(0,-10));
//            postalAddressDTO.setPremise(premise.trim());
//        }
//
//        return postalAddressDTO;
//    }//method closure

    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        postalAddressDTO.setFullAddress(personData.getNombreVia());
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
        postalAddressDTO.setTown(pa.getCityStandard());
        postalAddressDTO.setCountry(pa.getCountryDir());
        postalAddressDTO.setState(pa.getCityDepartment());

        if (personData.getDescripcionDireccion() != null && personData.getDescripcionDireccion().length() >= 10) {
            String descripcionDireccion = personData.getDescripcionDireccion();
            String premise = descripcionDireccion.substring(0, descripcionDireccion.length() - 10);
            postalAddressDTO.setPremise(StringUtils.replaceSpaces(premise).trim());
        }

        return postalAddressDTO;
    }




    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        document.setState(pa.getCityExp());
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        document.setCountry(pa.getCountryExp());

        if (pa.getTownDocument() != null && pa.getTownDocument().getName() != null) {
            document.setTown(pa.getTownDocument().getName());
        } else {
            document.setTown("");
        }

        if (personData.getDescripcionDireccion() != null) {
            String descripcionDireccion = personData.getDescripcionDireccion();
            String expirationDate = descripcionDireccion.substring(descripcionDireccion.length() - 10);
            expirationDate = StringUtils.replaceSpaces(expirationDate).trim();

            if (isValidDate(expirationDate, "yyyy-MM-dd")) {
                document.setExpirationDate(expirationDate);
            } else {
                document.setExpirationDate("1999-12-31");
            }
        }

        return document;
    }

    // Método para validar si una cadena tiene el formato de fecha especificado
    private static boolean isValidDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

//    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
//        DocumentDTO document = new DocumentDTO();
//        document.setDocumentTypeCode(personData.getTipoIdentificacion());
//        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
//        document.setDocumentNumber(personData.getNumeroIdentificacion());
//        document.setState(pa.getCityExp());
//        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
//        document.setCountry(pa.getCountryExp());
//        document.setTown(pa.getTownDocument().getName() != null ? pa.getTown().getCode() : "");
//        if(personData.getDescripcionDireccion() != null){
//            String expirationDate = StringUtils.replaceSpaces(personData.getDescripcionDireccion().substring(50,60));
//        document.setExpirationDate(expirationDate.trim());
//        }
//        return document;
//    }//method closure

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


    public static CreateCustomerRequestDTO cleanFieldsFromProspectUpdate(CreateCustomerRequestDTO customerRequestDTO){
        //forces document null for avoid documents update.
        if(customerRequestDTO.getPerson()!=null && customerRequestDTO.getPerson().getDocument()!=null){
            
            customerRequestDTO.getPerson().getDocument().setDocumentTypeCode(null);//Forces customer CASE dont change documents
            customerRequestDTO.getPerson().getDocument().setDocumentNumber(null);//Forces customer CASE dont change documents
            
        }
        return customerRequestDTO;
    }

    public static UpdateProspectRequestDTO cleanFieldsCustomerProspectUpdate(UpdateProspectRequestDTO customerRequestDTO){
        //forces document null for avoid documents update.
        if(customerRequestDTO.getPerson()!=null && customerRequestDTO.getPerson().getDocument()!=null){
            
            customerRequestDTO.getPerson().getDocument().setDocumentTypeCode(null);//Forces customer CASE dont change documents
            customerRequestDTO.getPerson().getDocument().setDocumentNumber(null);//Forces customer CASE dont change documents
            
        }
        return customerRequestDTO;
    }

    public String removeCustomer(TrxPersonData personData) {

        log.info("Usualt old: {}", personData.getUsualt());

        var usualt = personData.getUsualt();        

        String foreignTaxIndicator = usualt;

        if(usualt != null && !usualt.isBlank()){

            if(usualt.length() > 6){

                foreignTaxIndicator  = usualt.substring(3,7);
                usualt = deleteCustomerCode + foreignTaxIndicator;

            }

            if(usualt.length() < 7){

                foreignTaxIndicator  = "CONN";
                usualt = deleteCustomerCode + foreignTaxIndicator;

            }

        } else {

            foreignTaxIndicator  = "CONN";
            usualt = deleteCustomerCode + foreignTaxIndicator;

        }

        log.info("Usualt new: {}", usualt);

        return usualt;
    }

}//class closure


package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;



import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerMapperUtils {

    
    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegativeResponse;

    @Value("${params.deleteCustomerCode}")
    private String deleteCustomerCode;
    

    public static PersonDTO personDTONames(TrxPersonData personData, Parameters pa) {
        PersonDTO personDTO = new PersonDTO();
        String genderDesc = "HOMBRE";
        String genderCode = "H";
        if (personData.getSexo() != null && !personData.getSexo().isEmpty() && !personData.getSexo().equalsIgnoreCase("H")) {
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
        personDTO.getPersonName().setFullName(fullName.trim());
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
        placeOfBirthDTO.setTown(pa.getTown() != null ? pa.getTown().getName() : "");
        return placeOfBirthDTO;
    }//method closure

    /*
     * Obtains record origin
     */
    public static String getSourceCode(TrxPersonData personData) {
        String sourceCode = "OTRO";
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 2){
            sourceCode = usualt.substring(0,3);
        }

        return sourceCode.equals("ODS") ? sourceCode : "OTRO";
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

    //    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
//        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
//        postalAddressDTO.setFullAddress(personData.getNombreVia());
//        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
//        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
//        postalAddressDTO.setTown(pa.getCityStandard());
//        postalAddressDTO.setCountry(pa.getCountryDir());
//        postalAddressDTO.setState(pa.getCityDepartment());
//        if (personData.getDescripcionDireccion() != null) {
//            String premise = StringUtils.replaceSpaces(personData.getDescripcionDireccion().substring(0,-10));
//            postalAddressDTO.setPremise(premise.trim());
//        }
//
//        return postalAddressDTO;
//    }//method closure

    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        postalAddressDTO.setFullAddress(personData.getNombreVia());
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
        postalAddressDTO.setTown(pa.getCityStandard());
        postalAddressDTO.setCountry(pa.getCountryDir());
        postalAddressDTO.setState(pa.getCityDepartment());

        if (personData.getDescripcionDireccion() != null && personData.getDescripcionDireccion().length() >= 10) {
            String descripcionDireccion = personData.getDescripcionDireccion();
            String premise = descripcionDireccion.substring(0, descripcionDireccion.length() - 10);
            postalAddressDTO.setPremise(StringUtils.replaceSpaces(premise).trim());
        }

        return postalAddressDTO;
    }




    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        document.setState(pa.getCityExp());
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        document.setCountry(pa.getCountryExp());

        if (pa.getTownDocument() != null && pa.getTownDocument().getName() != null) {
            document.setTown(pa.getTownDocument().getName());
        } else {
            document.setTown("");
        }

        if (personData.getDescripcionDireccion() != null) {
            String descripcionDireccion = personData.getDescripcionDireccion();
            String expirationDate = descripcionDireccion.substring(descripcionDireccion.length() - 10);
            expirationDate = StringUtils.replaceSpaces(expirationDate).trim();

            if (isValidDate(expirationDate, "yyyy-MM-dd")) {
                document.setExpirationDate(expirationDate);
            } else {
                document.setExpirationDate("1999-12-31");
            }
        }

        return document;
    }

    // Método para validar si una cadena tiene el formato de fecha especificado
    private static boolean isValidDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

//    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
//        DocumentDTO document = new DocumentDTO();
//        document.setDocumentTypeCode(personData.getTipoIdentificacion());
//        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
//        document.setDocumentNumber(personData.getNumeroIdentificacion());
//        document.setState(pa.getCityExp());
//        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
//        document.setCountry(pa.getCountryExp());
//        document.setTown(pa.getTownDocument().getName() != null ? pa.getTown().getCode() : "");
//        if(personData.getDescripcionDireccion() != null){
//            String expirationDate = StringUtils.replaceSpaces(personData.getDescripcionDireccion().substring(50,60));
//        document.setExpirationDate(expirationDate.trim());
//        }
//        return document;
//    }//method closure

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


    public static CreateCustomerRequestDTO cleanFieldsFromProspectUpdate(CreateCustomerRequestDTO customerRequestDTO){
        //forces document null for avoid documents update.
        if(customerRequestDTO.getPerson()!=null && customerRequestDTO.getPerson().getDocument()!=null){
            
            customerRequestDTO.getPerson().getDocument().setDocumentTypeCode(null);//Forces customer CASE dont change documents
            customerRequestDTO.getPerson().getDocument().setDocumentNumber(null);//Forces customer CASE dont change documents
            
        }
        return customerRequestDTO;
    }

    public static UpdateProspectRequestDTO cleanFieldsCustomerProspectUpdate(UpdateProspectRequestDTO customerRequestDTO){
        //forces document null for avoid documents update.
        if(customerRequestDTO.getPerson()!=null && customerRequestDTO.getPerson().getDocument()!=null){
            
            customerRequestDTO.getPerson().getDocument().setDocumentTypeCode(null);//Forces customer CASE dont change documents
            customerRequestDTO.getPerson().getDocument().setDocumentNumber(null);//Forces customer CASE dont change documents
            
        }
        return customerRequestDTO;
    }

    public String removeCustomer(TrxPersonData personData) {

        log.info("Usualt old: {}", personData.getUsualt());

        var usualt = personData.getUsualt();        

        String foreignTaxIndicator = usualt;

        if(usualt != null && !usualt.isBlank()){

            if(usualt.length() > 6){

                foreignTaxIndicator  = usualt.substring(3,7);
                usualt = deleteCustomerCode + foreignTaxIndicator;

            }

            if(usualt.length() < 7){

                foreignTaxIndicator  = "CONN";
                usualt = deleteCustomerCode + foreignTaxIndicator;

            }

        } else {

            foreignTaxIndicator  = "CONN";
            usualt = deleteCustomerCode + foreignTaxIndicator;

        }

        log.info("Usualt new: {}", usualt);

        return usualt;
    }

}//class closure


package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Global Utils
 */
public class GUtils {
    /**
     * Start log
     */
    public final static String SLOG = "--> Start ";

    /**
     * End log
     */
    public final static String ELOG = "<-- End ";

    public static String[] getNullOrBlankPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || (srcValue instanceof String && ((String) srcValue).isEmpty())) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

public enum RegexTypes {

    ONLY_NUMBERS,
    STRICT_LENGTH_8,
    STRICT_LENGTH_11,
    STRICT_CHAR_LENGTH_2,
    EMAIL,
    PHONE_LENGTH,
    PHONE_FORMAT,
    INTERNATIONAL_CODE_LENGTH,
    INTERNATIONAL_CODE_FORMAT,
    ADDRESS_LENGTH,
    TEXT_20_LENGTH,
    TEXT_20_FORMAT,
    TEXT_40_LENGTH,
    TEXT_40_FORMAT,
    ADDRESS_FORMAT,
    COUNTRY_LENGTH,
    COUNTRY_FORMAT,    
    GENDER_CODE_FORMAT,
    BIRTHDAY_FORMAT,
    TOWN_FORMAT,
    TOWN_LENGTH,
    DOCUMENT_TYPE_FORMAT,
    DOCUMENT_COUNTRY_TYPE,    
    GENDER_CODE_LENGTH,
    DATAORIGIN_SOURCECODE_FORMAT,
    DATAORIGIN_SOURCECODE_LENGTH,
    REGEX_TOWN_DESCRIPTION_FORMAT,
    REGEX_TOWN_DESCRIPTION_LENGTH,
    ISSUE_DATE_FORMAT


}

>>>>>>>>>>>>>>>>>>>>>>
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorDTO;

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

    @Value("${regex.strict_length_11}")
    private String REGEX_STRICT_LENGTH_11;

    @Value("${regex.strict_length_11_error}")
    private String REGEX_STRICT_LENGTH_11_ERROR;

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

    @Value("${regex.text_20_length}")
    private String REGEX_TEXT_20_LENGTH;

    @Value("${regex.text_20_length_error}")
    private String REGEX_TEXT_20_LENGTH_ERROR;

    @Value("${regex.text_20_format}")
    private String REGEX_TEXT_20_FORMAT;

    @Value("${regex.text_20_format_error}")
    private String REGEX_TEXT_20_FORMAT_ERROR;

    @Value("${regex.text_40_length}")
    private String REGEX_TEXT_40_LENGTH;

    @Value("${regex.text_40_length_error}")
    private String REGEX_TEXT_40_LENGTH_ERROR;

    @Value("${regex.text_40_format}")
    private String REGEX_TEXT_40_FORMAT;

    @Value("${regex.text_40_format_error}")
    private String REGEX_TEXT_40_FORMAT_ERROR;

    @Value("${regex.country_code_length}")
    private String REGEX_COUNTRY_CODE_LENGTH;

    @Value("${regex.country_code_length_error}")
    private String REGEX_COUNTRY_CODE_LENGTH_ERROR;

    @Value("${regex.country_code_format}")
    private String REGEX_COUNTRY_CODE_FORMAT;

    @Value("${regex.country_code_format_error}")
    private String REGEX_COUNTRY_CODE_FORMAT_ERROR;

    @Value("${regex.gender_code_format}")
    private String REGEX_GENDER_CODE_FORMAT;

    @Value("${regex.gender_code_format_error}")
    private String REGEX_GENDER_CODE_ERROR_FORMAT;

    @Value("${regex.town_format}")
    private String REGEX_TOWN_FORMAT;

    @Value("${regex.town_format_error}")
    private String REGEX_TOWN_FORMAT_ERROR;

    @Value("${regex.town_length}")
    private String REGEX_TOWN_LENGTH;

    @Value("${regex.town_length_error}")
    private String REGEX_TOWN_LENGHT_ERROR;

    @Value("${regex.document_type_format}")
    private  String REGEX_DOCUMENT_TYPE_FORMAT;

    @Value("${regex.document_type_format_error}")
    private  String REGEX_DOCUMENT_TYPE_FORMAT_ERROR;

    @Value("${regex.document_type_country}")
    private  String REGEX_DOCUMENT_TYPE_COUNTRY;

    @Value("${regex.document_type_country_error}")
    private  String REGEX_DOCUMENT_TYPE_COUNTRY_ERROR;

    @Value("${regex.birthday_format}")
    private String REGEX_BIRTHDAY_FORMAT;

    @Value("${regex.birthday_format_error}")
    private String REGEX_BIRTHDAY_FORMAT_ERROR;

    @Value("${regex.gender_code_length}")
    private String REGEX_GENDER_CODE_LENGTH;

    @Value("${regex.gender_code_length_error}")
    private String REGEX_GENDER_CODE_LENGTH_ERROR;

    @Value("${regex.dataorigin_sourcecode_format}")
    private String REGEX_DATAORIGIN_SOURCECODE_FORMAT;

    @Value("${regex.dataorigin_sourcecode_format_error}")
    private String REGEX_DATAORIGIN_SOURCECODE_FORMAT_ERROR;

    @Value("${regex.dataorigin_sourcecode_length_4}")
    private String REGEX_DATAORIGIN_SOURCECODE_LENGTH;

    @Value("${regex.dataorigin_sourcecode_length_4_error}")
    private String REGEX_DATAORIGIN_SOURCECODE_LENGTH_ERROR;
    @Value("${regex.town_description_format}")
    private String REGEX_TOWN_DESCRIPTION_FORMAT;
    @Value("${regex.town_description_format_error}")
    private String REGEX_TOWN_DESCRIPTION_FORMAT_ERROR;
    @Value("${regex.town_description_length}")
    private String REGEX_TOWN_DESCRIPTION_LENGTH;
    @Value("${regex.town_description_length_error}")
    private String REGEX_TOWN_DESCRIPTION_LENGTH_ERROR;
    @Value("${regex.issue_date}")
    private String REGEX_ISSUE_DATE_FORMAT;
    @Value("${regex.issue_date_error}")
    private String REGEX_ISSUE_DATE_ERROR_FORMAT;

    public void validateRegex(RegexTypes regex, String value, String fieldName) {

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
            case STRICT_LENGTH_11:
                regularExpression = REGEX_STRICT_LENGTH_11;
                message = REGEX_STRICT_LENGTH_11_ERROR;
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
            case TEXT_20_LENGTH:
                regularExpression = REGEX_TEXT_20_LENGTH;
                message = REGEX_TEXT_20_LENGTH_ERROR;
                break;
            case TEXT_20_FORMAT:
                regularExpression = REGEX_TEXT_20_FORMAT;
                message = REGEX_TEXT_20_FORMAT_ERROR;
                break;
            case TEXT_40_LENGTH:
                regularExpression = REGEX_TEXT_40_LENGTH;
                message = REGEX_TEXT_40_LENGTH_ERROR;
                break;
            case TEXT_40_FORMAT:
                regularExpression = REGEX_TEXT_40_FORMAT;
                message = REGEX_TEXT_40_FORMAT_ERROR;
                break;
            case COUNTRY_LENGTH:
                regularExpression = REGEX_COUNTRY_CODE_LENGTH;
                message = REGEX_COUNTRY_CODE_LENGTH_ERROR;
                break;
            case COUNTRY_FORMAT:
                regularExpression = REGEX_COUNTRY_CODE_FORMAT;
                message = REGEX_COUNTRY_CODE_FORMAT_ERROR;
                break;
            case GENDER_CODE_FORMAT:
                regularExpression = REGEX_GENDER_CODE_FORMAT;
                message = REGEX_GENDER_CODE_ERROR_FORMAT;
                break;
            case BIRTHDAY_FORMAT:
                regularExpression = REGEX_BIRTHDAY_FORMAT;
                message = REGEX_BIRTHDAY_FORMAT_ERROR;
                break;
            case TOWN_FORMAT:
                regularExpression = REGEX_TOWN_FORMAT;
                message = REGEX_TOWN_FORMAT_ERROR;
                break;
            case TOWN_LENGTH:
                regularExpression = REGEX_TOWN_LENGTH;
                message = REGEX_TOWN_LENGHT_ERROR;
                break;
            case DOCUMENT_TYPE_FORMAT:
                regularExpression = REGEX_DOCUMENT_TYPE_FORMAT;
                message = REGEX_DOCUMENT_TYPE_FORMAT_ERROR;
                break;
            case DOCUMENT_COUNTRY_TYPE:
                regularExpression = REGEX_DOCUMENT_TYPE_COUNTRY;
                message = REGEX_DOCUMENT_TYPE_COUNTRY_ERROR;
                break;
            case GENDER_CODE_LENGTH:
                regularExpression = REGEX_GENDER_CODE_LENGTH;
                message = REGEX_GENDER_CODE_LENGTH_ERROR;
                break;
            case DATAORIGIN_SOURCECODE_FORMAT:
                regularExpression = REGEX_DATAORIGIN_SOURCECODE_FORMAT;
                message = REGEX_DATAORIGIN_SOURCECODE_FORMAT_ERROR;
                break;
            case DATAORIGIN_SOURCECODE_LENGTH:
                regularExpression = REGEX_DATAORIGIN_SOURCECODE_LENGTH;
                message = REGEX_DATAORIGIN_SOURCECODE_LENGTH_ERROR;
                break;
            case REGEX_TOWN_DESCRIPTION_FORMAT:
                regularExpression = REGEX_TOWN_DESCRIPTION_FORMAT;
                message = REGEX_TOWN_DESCRIPTION_FORMAT_ERROR;
                break;
            case REGEX_TOWN_DESCRIPTION_LENGTH:
                regularExpression = REGEX_TOWN_DESCRIPTION_LENGTH;
                message = REGEX_TOWN_DESCRIPTION_LENGTH_ERROR;
                break;
            case ISSUE_DATE_FORMAT:
                regularExpression = REGEX_ISSUE_DATE_FORMAT;
                message = REGEX_ISSUE_DATE_ERROR_FORMAT;
                break;

        }

        // log.info("Check regex {} to value {}", regularExpression, value);

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
>>>>
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 * @author Wilfredo Pena
 */
public class ServiceDirectory {

    private static final String API_VERSION = "/v3";
    private static final String CUSTOMER_ENDPOINT = "/customers";

    /**
     * Endpoints
     */
    public final static String CUSTOMERS = API_VERSION + CUSTOMER_ENDPOINT;
    public final static String CUSTOMERS_SEARCH = API_VERSION + CUSTOMER_ENDPOINT + "/search";
}
>>>>>>>>>>>>>>>>>>
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringUtils {

    public static String blankField(String field){
        if(field == null){
            return "";
        }else{
            return field;
        }
    }

    public static String replaceSpaces(String cadena) {
        // Primero reemplazar espacios triples
        Pattern patternTriples = Pattern.compile("\\s{3}");
        Matcher matcherTriples = patternTriples.matcher(cadena);
        String cadenaSinTriples = matcherTriples.replaceAll(" ");

        // Luego reemplazar espacios dobles
        Pattern patternDobles = Pattern.compile("\\s{2}");
        Matcher matcherDobles = patternDobles.matcher(cadenaSinTriples);
        return matcherDobles.replaceAll(" ");
    }

    public static String rightPad(String str, int length, char padChar) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(padChar);
        }
        return sb.toString();
    }

}

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getSlocalDateTimeByFormat(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String unformattedDate){
        if(unformattedDate == null || unformattedDate.isBlank()){
            return "";
        }
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
>>>>>>>>>>>>>>

spring.profiles.active: local
---
darwin:
  region: boae
  suffix:
  app-name: bsn049
  logging:
    format: GLUONLOG
    gluon-log:
      company: bnc
      componentName: mscustomer
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
  core:
    exceptions:
      error-format: GLUON
  security:
    white-list:
      - /**
    connectors:
      pkm-connector:
        pkm-endpoint:
          - ${env.pkm-endpoint:localhost://}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false

spring:
  application:
    name: bnc-bsn049-mscustomer
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  jackson:
    default-property-inclusion: NON_NULL
  config:
    import: classpath:messages.properties,classpath:regex.properties

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049mscustomer: INFO
    okhttp3: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-}, %X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"
management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
      group:
        readiness:
          include: readinessState,ping,externalApis
        liveness:
          include: livenessState
observability:
  external-apis: 
    timeout-ms: 5000
    checks: 
      - name: backend-for-frontend 
        url: ${OBSERVABILITY_PROTOCOL}${bff-host}/actuator/health
        critical: false 
        accepted-statuses:
          - 200
          - 404
      - name: parameter 
        url: ${OBSERVABILITY_PROTOCOL}${ms-parameter-host}/actuator/health
        critical: false
        accepted-statuses:
          - 200
          - 404

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful
  port: 9152

OBSERVABILITY_PROTOCOL: "http://"
bff-host: "backend-for-frontend-sanba-gui.apps.ocp4-preprod.cosanpre.corp"
bff-port: 443
bff-https: true
ms-parameter-host: "ms-parameters-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp"
#ms-parameter-host: "localhost"
ms-parameter-port: 443
ms-parameter-https: true

#ms-parameter-port: 8099
cache-host: localhost
cache-port: 8080

params:
  app-name: CUSTOMERS
  default-channel: ODS
  deleteCustomerCode: '999'
  default-update-agrofic: "0003"
  foreignTaxIndicatorFromBDD:
    positive: "CONS"
    negative: "CONN"
  sanba:
    mqRoute: "QCTFD1"
    user: "@NETE781"
    channel: "60"

integration:
  catalogue:
    - integrationType: trx_person
      host: ${bff-host}
      port: ${bff-port}
      https: ${bff-https}
      endpoint: "/service-engine/procesar/"
      timeOutConn: 30000
      timeOutRead: 30000
    - integrationType: context
      host: ${cache-host}
      port: ${cache-port}
      https: false
      endpoint: "/cache/service/"
      timeOutConn: 30000
      timeOutRead: 30000
    - integrationType: parameters
      host: ${ms-parameter-host}
      port: ${ms-parameter-port}
      https: ${ms-parameter-https}
      endpoint: "/v1/administrative_geographies_parameters/"
      timeOutConn: 30000
      timeOutRead: 30000



>>>>>>>>>>>>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>>>>>>>>>>>>
ms_name= CUSTOMERS
ms_version= api-service-v3
errors.level= error
errors.functional = P-F
errors.technical = P-T
#Errores generales
errors.general.null= Cannot be null
errors.general.empty= Cannot be empty
errors.genetal.blank= Cannot be blank
errors.general.invalid_format = Invalid format
errors.general.invalid_length = Invalid length
errors.general.invalid_length_11 = Invalid length (Max. 11)
errors.general.unhandled_exception = Unexpected error
errors.general.invalid_value = Invalid value
errors.general.blank_data = Cannot be blank
errors.general.country_not_found = Country not found
errors.general.date_invalid = Date invalid
errors.general.code_not_exist = The code does not exist
errors.general.country_is_blank = The code can only contain letters
errors.general.town_is_blank = The town can only contain letters, spaces and accents
errors.general.birthdate_is_blank = The birthDate must follow the schema aaaa-mm-dd
#Errores especÃ­ficos

>>>>>>>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>>>>>>>>

regex.error.code = P-F-9400

regex.only_numbers= ^[0-9]+$
regex.only_numbers.error = Invalid format (must be numbers)

regex.strict_length_8 = ^[0-9]{8,8}$
regex.strict_length_8_error = Invalid length (must be 8)

regex.strict_length_11 = ^[0-9]{1,11}$
regex.strict_length_11_error = Invalid length (must be 11)

regex.strict_char_length_2 = ^[a-zA-Z]{2,2}$
regex.strict_char_length_2_error = Invalid length (must be 2)

regex.text_20_length = ^[\\p{L} ]{1,20}+$
regex.text_20_length_error = Invalid length (max 20)

regex.text_20_format = ^[\\p{L} ]+$
regex.text_20_format_error = Invalid format

regex.text_40_length = ^[\\p{L} ]{1,40}+$
regex.text_40_length_error = Invalid length (max 40)

regex.text_40_format = ^[\\p{L} ]+$
regex.text_40_format_error = Invalid format

regex.country_code_length = ^[\\p{L} ]{2,2}$
regex.country_code_length_error = Size must be between 2 and 2

regex.country_code_format = ^[A-Za-z]+$
regex.country_code_format_error = The code can only contain letters

regex.gender_code_format = ^[HM]{1,1}$
regex.gender_code_format_error = The genderCode can only contain letters (M or H)

regex.gender_code_length = ^[\\w\\W]{1,1}$
regex.gender_code_length_error = Invalid gender Code (only valid H or M)

regex.address_length = ^[\\w\\W]{1,17}$
regex.address_length_error = Invalid length (max 17)

regex.address_format = [0-9a-zA-ZÃ¡Ã©Ã­Ã±Ã³ÃºÃ¼ÃÃÃÃÃÃÃ]+
regex.address_format_error = Invalid format

regex.email = ^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$
regex.email_error = Invalid format

regex.phone_length = ^[0-9]{10,10}$
regex.phone_length_error = Invalid length (must be 10)

regex.phone_format = ^[0-9]+$
regex.phone_format_error = Invalid format (only numbers)

regex.international_code_length = ^[0-9]{2,3}$
regex.international_code_length_error = Invalid length (must be 3)

regex.international_code_format = ^[0-9]+$
regex.international_code_format_error = Invalid format (only numbers)

regex.birthday_format = ^[0-9]{4}-[0-9]{2}-[0-9]{2}$
//regex.birthday_format_error = Invalid date format
regex.birthday_format_error = The birthDate must follow the schema aaaa-mm-dd

regex.town_format = ^[0-9]+$
regex.town_format_error = Invalid format (only numbers)

regex.town_length = ^[0-9]{5,5}$
regex.town_length_error = Invalid length (5 digit)

regex.document_type_format = (CC|CE)
regex.document_type_format_error = Invalid value (only valid CC or CE)

regex.document_type_country = ^[CO]+$
regex.document_type_country_error= Invalid value (must be CO)

regex.dataorigin_sourcecode_format = (OTRO)
regex.dataorigin_sourcecode_format_error = Invalid value (only valid OTRO)

regex.dataorigin_sourcecode_length_4 = ^[a-zA-Z]{4,4}$
regex.dataorigin_sourcecode_length_4_error = Invalid length (must be 4)

regex.town_description_format = ^[a-zA-Z()-]+(?: [a-zA-Z()-]+)*$
regex.town_description_format_error = Invalid format (only letters)

regex.town_description_length = ^(?=.{1,40} [a-zA-Z()-]{1,40}$)[a-zA-Z()-]+(?: [a-zA-Z()-]+)*$
regex.town_description_length_error = Invalid length (must be between 1 and 40)

regex.issue_date = ^[0-9]{4}-[0-9]{2}-[0-9]{2}$
regex.issue_date_error = Invalid date format

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# App name for exceptions
exceptions.app.name=bnc-bsn049-mscustomer

# Custom description and message for my_exception
my_exception.description=my description
my_exception.code=1001

>>>>>>>>>>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>>>>>>>>>>
# Custom description and message for my_exception
my_exception.description=my description
my_exception.message=my message
>>>>>>>>>>>>>>>>>>>>>>
>>>>>>>>>>>>>>>>>>>>>>
# Custom description and message for my_exception
my_exception.description=mi descripcion
my_exception.message=mi mensaje









