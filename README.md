package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatchProspectMapper {

    final RegexUtils regexUtils;
    final ProspectMapperUtils prospectMapperUtils;
    final ErrorService errorService;
    final ParameterApiService parameterApiService;
    final ParamService paramsService;
    private static final String DEFAULT_CITY = "05101";
    private String givenNameMessage = "givenName";
    private String lastNameMessage = "lastName";
    private String secondLastNameMessage = "secondLastName";
    private String documentCountryCodeMessage = "documents.country.code";
    private String documentTownsMessage = "documents.town";
    private String documentExpirationDateMessage = "person.documents.expirationDate";
    private String firstNationalityMessage = "firstNationality";
    private String countryOfResidenceMessage = "countryOfResidence";
    private String placeOfBirthCountryMessage = "placeOfBirth.country";
    private String personPlaceOfBirthTownMessage = "person.placeOfBirth.town";
    private String genderCodeMessage = "genderCode";
    private String emailAddressMessage = "emailAddress";

    @Value("${params.default-channel}")
    private String dEFAULTcHANNEL;

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String fOREIGNtAXiNDICATORpOSITIVE;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String fOREIGNtAXiNDICATORnEGATIVE;

    public BasicData pef3ResponseToPef2Request(TrxPersonData trxBasicData) {

        BasicData response = new BasicData();
        response.setCelular(trxBasicData.getCelular().replace(" ", ""));
        response.setTelefono(trxBasicData.getTelefono().replace(" ", ""));
        response.setAgrofic(trxBasicData.getAgrofic());
        response.setCodact(trxBasicData.getCodact());
        response.setClase(trxBasicData.getClase()); // 004
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
        // pais
        response.setPaisDireccion(trxBasicData.getPaisDireccion());
        response.setPaisExpedicion(trxBasicData.getPaisExpedicion());
        response.setPaisNacimiento(trxBasicData.getPaisNacimiento());
        response.setPaisDireccionDesc(""); // DESC
        response.setPaisNacimientoDesc(""); // DESC
        response.setPaisExpedicionDesc(""); // DESC
        response.setLugardeExpDescripcion(""); // DESC
        response.setLugardeNacimiento(""); // DESC
        response.setDescripcionDireccion(trxBasicData.getDescripcionDireccion());
        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        // ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
        response.setFecing(trxBasicData.getFecing());
        response.setCiudadDescripcion(""); // DESC
        response.setDomant(Integer.toString(trxBasicData.getDomant())); // CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel()));// CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel())); // CASE
        response.setSecema(Integer.toString(trxBasicData.getSecema())); // CASE
        response.setSecdotc(Integer.toString(trxBasicData.getSecdotc())); // CASE
        response.setSectelp(Integer.toString(trxBasicData.getSectelp())); // CASE
        response.setSecdomp(Integer.toString(trxBasicData.getSecdomp())); // CASE
        response.setSecdotp(Integer.toString(trxBasicData.getSecdotp())); // CASE
        response.setSucadm(trxBasicData.getSucadm());
        response.setSucmod(trxBasicData.getSucmod());

        response.setAutorizoTelefono(Boolean.parseBoolean(trxBasicData.getAutorizoTelefono()));// CASE
        response.setAutorizacionEmail(Boolean.parseBoolean(trxBasicData.getAutorizacionEmail())); // CASE

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
    }// method closure

    /**
     * Request From Update Prospect
     *
     * @param dtoUpdateProspectRequest ALL null data in response will be ignored
     * @return BasicData
     */
    public BasicData prospectPatchToPef2Request(PatchProspectRequestDTO dtoUpdateProspectRequest, String authorization,
            String xSantanderClientId) {
        BasicData response = new BasicData();
        PersonRequestDTO person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;
        // Person Name
        PersonNameRequestDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            validateGivenName(personNameDTO.getGivenName(), response);
            validateLastName(personNameDTO.getLastName(), response);
            validateSecondLastName(personNameDTO.getSecondLastName(), response);
        }
        // Documents
        List<DocumentRequestDTO> documents = person.getDocuments();
        if (documents != null && !documents.isEmpty()) {
            DocumentRequestDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                validateDocumentCountry(firstDocument, response);
                validateDocumentTown(firstDocument, response, authorization, xSantanderClientId);
                validateDocumentIssueDate(firstDocument, response);
                validateDocumentExpirationDate(firstDocument, response);
            }
        } // end if documents empty
          // FirstNationality
        validateFirstNationality(person, response);
        // CountryOfResidence
        validateCountryOfResidence(person, response);
        // Place of Birth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        if (placeOfBirthRequestDTO != null) {
            // CountryOfBirth
            validateCountryOfBirth(placeOfBirthRequestDTO, response);
            // Town
            validateBirthRequest(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
            if (placeOfBirthRequestDTO.getTown() != null || placeOfBirthRequestDTO.getTownCode() != null) {
                // Valida y set CiudadNacimiento por town
                validateplaceOfBirth(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
            } else {
                response.setCiudadNacimiento(person.getPlaceOfBirth().getTown());
            }
        }
        // Birth Date
        validateBirthDate(person.getBirthDate(), response);
        // Sex
        validateGenderCode(person.getGenderCode(), response);
        // foreingtaxIndicator
        if (person.getForeignTaxIndicator() != null && !"YES".equals(person.getForeignTaxIndicator())
                && !"NO".equals(person.getForeignTaxIndicator())) {
            // TODO: HACER VALIDACION
            ErrorDTO errorForeignTaxIndicator = ErrorCatalog.getIvalidInput();
            errorForeignTaxIndicator.setMessage("ForeignTaxIndicator: " + errorForeignTaxIndicator.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorForeignTaxIndicator);
        }
        // Contact Point
        if (dtoUpdateProspectRequest.getContactPoints() != null
                && !dtoUpdateProspectRequest.getContactPoints().isEmpty()) {
            ContactPointRequestDTO cPoint = dtoUpdateProspectRequest.getContactPoints().get(0);
            // PostalAddress
            validatePostalAddress(cPoint, response);
            // PhoneAddress
            validatePhoneAddress(cPoint, response);
            // EmailAddress
            validateElectronicAddress(cPoint, response);
        } // end contactPoint

        return response;
    }// method closure

    private void validateBirthRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response,
            String authorization,
            String xSantanderClientId) {
        if (placeOfBirthRequestDTO.getTown() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getTown(), personPlaceOfBirthTownMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, placeOfBirthRequestDTO.getTown(),
                    personPlaceOfBirthTownMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, placeOfBirthRequestDTO.getTown(),
                    personPlaceOfBirthTownMessage);
            similarTownFunction(placeOfBirthRequestDTO, authorization, xSantanderClientId, response);
        } // Valida y set CiudadNacimiento por townCode
        if (placeOfBirthRequestDTO.getTownCode() != null) {
            validateplaceOfBirth(placeOfBirthRequestDTO, response, authorization, xSantanderClientId);
        }
    }

    private void similarTownFunction(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            String authorization, String xSantanderClientId, BasicData response) {

        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            CompareStringUtils compareString = new CompareStringUtils();
            boolean isSimilar = false;
            DataListDTO similarTown = null;
            for (DataListDTO town : towns) {
                isSimilar = compareString.ciudadMatch(placeOfBirthRequestDTO.getTown(),
                        town.getDescription());
                if (isSimilar) {
                    similarTown = town;
                    break;
                }
            }
            if (isSimilar && similarTown != null) {
                response.setCiudadNacimiento(similarTown.getCode());
            } else {
                // Busca el código "99999"
                var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
                if (notInformedTown.isPresent()) {
                    response.setCiudadNacimiento(notInformedTown.get().getCode());
                }
            }
        }

    }

    private void validateplaceOfBirth(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response,
            String authorization,
            String xSantanderClientId) {
        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), "placeOfBirth.townCode");
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var townVar = towns.stream()
                    .filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getTownCode())).findAny();
            if (!townVar.isPresent()) {
                var message = "'placeOfBirth.townCode'" + ": " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
        response.setCiudadNacimiento(placeOfBirthRequestDTO.getTownCode());
    }

    private void validateBirthDate(String birthday, BasicData response) {
        if (birthday != null && birthday.isBlank()) {
            errorService.isBlank(birthday, "birthDate");
        }
        if (birthday != null) {
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, birthday, "birthDate");
        }
        response.setFechaNacimiento(birthday);
    }

    private void validateGenderCode(String genderCode, BasicData response) {
        if (genderCode != null) {
            errorService.isBlank(genderCode, genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, genderCode, genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, genderCode, genderCodeMessage);
            response.setSexo(genderCode);
        }
    }

    private void validatePostalAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getPostalAddress() != null) {
            // StreetTypeCode "NN"
            if (cPoint.getPostalAddress().getStreetTypeCode() != null
                    && cPoint.getPostalAddress().getStreetTypeCode().length() == 2) {
                response.setTipoVia(cPoint.getPostalAddress().getStreetTypeCode());
            }
            // Full Address
            response.setNombreVia(cPoint.getPostalAddress().getFullAddress());
            // Full townName
            response.setCiudad(cPoint.getPostalAddress().getTownName());
            // Country code
            if (cPoint.getPostalAddress().getCountry() != null) {
                response.setCodpaip(cPoint.getPostalAddress().getCountry().getCode());
            }
        }
    }

    private void validatePhoneAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getPhoneAddress() != null) {
            response.setIndicativo(cPoint.getPhoneAddress().getInternationalCode());
            response.setTelefono(cPoint.getPhoneAddress().getPhoneNumber());
            response.setPrecelular(cPoint.getPhoneAddress().getInternationalCode());
            response.setCelular(cPoint.getPhoneAddress().getMobileNumber());
        }
    }

    private void validateElectronicAddress(ContactPointRequestDTO cPoint, BasicData response) {
        if (cPoint.getElectronicAddress() != null) {
            errorService.isBlank(cPoint.getElectronicAddress().getEmailAddress(), emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL, cPoint.getElectronicAddress().getEmailAddress(),
                    emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, cPoint.getElectronicAddress().getEmailAddress(),
                    emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR,
                    String.valueOf(cPoint.getElectronicAddress().getEmailAddress().charAt(0)), emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT,
                    cPoint.getElectronicAddress().getEmailAddress().split("@")[0], emailAddressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT,
                    cPoint.getElectronicAddress().getEmailAddress().split("@")[1], emailAddressMessage);
            response.setEmail(cPoint.getElectronicAddress().getEmailAddress());
        }
    }

    private void validateGivenName(String givenName, BasicData response) {
        if (givenName != null) {
            String givenNameRefactor = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(givenName);

            errorService.isBlank(givenNameRefactor, givenNameMessage);

            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenNameRefactor, givenNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenNameRefactor, givenNameMessage);
            response.setNombre(givenNameRefactor);
        }
    }

    private void validateLastName(String lastName, BasicData response) {
        if (lastName != null) {
            String lastNameRefactor = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(lastName);
            errorService.isBlank(lastNameRefactor, lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastNameRefactor, lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastNameRefactor, lastNameMessage);
            response.setPrimerApellido(lastNameRefactor);
        }
    }

    private void validateSecondLastName(String lastName, BasicData response) {
        if (lastName != null) {
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName,
                    secondLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName,
                    secondLastNameMessage);
            String secondLastName = PatchProspectMapperUtils
                    .replaceDoubleOrTripleSpaces(lastName);
            response.setSegundoApellido(secondLastName);
        }
    }

    private void validateDocumentCountry(DocumentRequestDTO document, BasicData response) {
        if (document.getCountry() != null && document.getCountry().getCode() != null) {
            errorService.isBlank(document.getCountry().getCode(), documentCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, document.getCountry().getCode(),
                    documentCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, document.getCountry().getCode(),
                    documentCountryCodeMessage);
            response.setPaisExpedicion(DataUtils.translateCountryToXXX(document.getCountry().getCode()));
        }
    }

    private void validateDocumentTown(DocumentRequestDTO document, BasicData response, String authorization,
            String xSantanderClientId) {
        if (document.getTown() != null) {
            validateDocumentTowns(document, response, authorization, xSantanderClientId);

        }
    }

    private void validateDocumentTowns(DocumentRequestDTO document, BasicData response, String authorization,
            String xSantanderClientId) {
        errorService.isBlank(document.getTown(), documentTownsMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, document.getTown(),
                documentTownsMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, document.getTown(),
                documentTownsMessage);
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            CompareStringUtils compareString = new CompareStringUtils();
            boolean isSimilar = false;
            DataListDTO similarTown = null;

            for (DataListDTO town : towns) {
                isSimilar = compareString.ciudadMatch(document.getTown(), town.getDescription());
                if (isSimilar) {
                    similarTown = town;
                    break;
                }
            }
            if (isSimilar && similarTown != null) {
                response.setCiudadExpedicion(similarTown.getCode());
            } else {
                // Busca el elemento con el código "99999"
                var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
                if (notInformedTown.isPresent()) {
                    response.setCiudadExpedicion(notInformedTown.get().getCode());

                }
            }
        }
    }

    private void validateFirstNationality(PersonRequestDTO person, BasicData response) {
        if (person.getFirstNationality() != null && person.getFirstNationality().getCode() != null) {

            errorService.isBlank(person.getFirstNationality().getCode(), firstNationalityMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getFirstNationality().getCode(),
                    firstNationalityMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getFirstNationality().getCode(),
                    firstNationalityMessage);

            response.setNacionalidad(DataUtils.translateCountryToXXX(person.getFirstNationality().getCode()));
        }
    }

    private void validateCountryOfResidence(PersonRequestDTO person, BasicData response) {
        if (person.getCountryOfResidence() != null && person.getCountryOfResidence().getCode() != null) {
            errorService.isBlank(person.getCountryOfResidence().getCode(), countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);

            response.setPaisDireccion(DataUtils.translateCountryToXXX(person.getCountryOfResidence().getCode()));
        }
    }

    private void validateDocumentIssueDate(DocumentRequestDTO document, BasicData response) {
        if (document.getIssueDate() != null) {
            errorService.isBlank(document.getIssueDate(), "documents.issueDate");
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getIssueDate(),
                    "documents.issueDate");

            response.setFechaExpedicion(document.getIssueDate());
        }
    }
    private void validateDocumentExpirationDate(DocumentRequestDTO document, BasicData response){
        if (document.getExpirationDate() != null) {
            errorService.isBlank(document.getExpirationDate(), documentExpirationDateMessage);
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getExpirationDate(), documentExpirationDateMessage);          
            response.setDescripcionDireccion(document.getExpirationDate());
                 
            
        }
    }

    private void validateCountryOfBirth(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, BasicData response) {
        if (placeOfBirthRequestDTO.getCountry() != null && placeOfBirthRequestDTO.getCountry().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeOfBirthCountryMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeOfBirthCountryMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeOfBirthCountryMessage);

            response.setPaisNacimiento(
                    DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
        }
    }

    public String usualtMapper(String usualt, String foreignTaxIndicator) {

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES")
                ? fOREIGNtAXiNDICATORpOSITIVE
                : fOREIGNtAXiNDICATORnEGATIVE;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : dEFAULTcHANNEL;

        return localUsualt + localForeignTaxIndicator;
    }

}



package com.santander.bnc.bsn049.bncbsn049msprospects.mappers;

import com.santander.bnc.bsn049.bncbsn049msprospects.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.*;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProspectMapper {

    final RegexUtils regexUtils;
    final ProspectMapperUtils prospectMapperUtils;
    final ErrorService errorService;
    final ParameterApiService parameterApiService;
    final ParamService paramsService;

    private static final String BLANK = "";
    private static final String COUNTRY_REQUEST = "COL";
    private static final String DEFAULT_CITY = "05101";
    private static final String DEFAULT_CITY_DOCUMENT = "99999";
    private String placeBirthCountryCodeMessage = "person.placeOfBirth.country.code";
    private String placeBirthStateCodeMessage = "person.placeOfBirth.state.code";
    private String placeOfBirthTownCodeMessage = "person.placeOfBirth.townCode";
    private String contactPointEmailAdressMessage = "contactPoints[0].electronicAddress.emailAddress";
    private String personaGiveNameMessage = "personName.givenName";
    private String personaLastNameMessage = "personName.lastName";
    private String personaSecondLastNameMessage = "personName.secondLastName";
    private String lastNameMessage = "lastName";
    private String giveNameMessage = "givenName";
    private String secondLastMessage = "secondLastName";
    private String documentCountryCodeMessage = "documents.country.code";
    private String firstNationalityMessageMessage = "firstNationality";
    private String countryOfResidenceMessage = "countryOfResidence";
    private String placeOfBirthCountryCodeMessage = "placeOfBirth.country";
    private String genderCodeMessage = "genderCode";
    @Value("${params.default-channel}")
    private String defaultChannel;

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositive;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegative;

    /**
     * MAP PROSPECT DETAILS
     *
     * @return ProspectDetailResponseDTO
     */
    public ProspectDetailResponseDTO trxPersonToProspectDTO(TrxPersonResponse trxBody, String authorization,
            String xSantanderClientId) {
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        ProspectDetailResponseDTO responseDTO = new ProspectDetailResponseDTO();

        if (ProspectMapperUtils.isNotProspect(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData, authorization, xSantanderClientId);
        // PLACE OF BIRTH
        PersonDTO personDTO = ProspectMapperUtils.personDTONames(personData, param);
        // Foreign tax indicator
        personDTO.setForeignTaxIndicator(prospectMapperUtils.getForeignTaxIndicator(personData));
        // Contact Point
        responseDTO.setContactPoints(List.of(ProspectMapperUtils.getContactPoint(personData, param)));
        personDTO.setDocuments(List.of(ProspectMapperUtils.getDocumentBasics(personData, param)));
        responseDTO.setPerson(personDTO);
        // Ex customer
        responseDTO.setIsPendingExCustomer("EXC".equals(personData.getConper()));
        // Source code
        DataOriginDTO dataOrigins = new DataOriginDTO();
        dataOrigins.setSourceCode(ProspectMapperUtils.getSourceCode(personData));
        responseDTO.setDataOrigins(List.of(dataOrigins));
        return responseDTO;
    }// method closure

    /**
     * MAP Prospect Search Response
     *
     * @return ProspectSearchResponseDTO
     */
    public ProspectSearchResponseDTO trxPersonToCustomerSearchDTO(TrxPersonResponse trxBody, String authorization,
            String xSantanderClientId) {
        log.info(GUtils.SLOG + "mapp for search Prospect");
        TrxPersonData personData = trxBody.getData().getDatosBasicos();

        ProspectSearchResponseDTO responseDTO = new ProspectSearchResponseDTO();
        List<ProspectSearchDTO> prospects = new ArrayList<>();
        ProspectSearchDTO prospectSearchDTO = new ProspectSearchDTO();

        if (ProspectMapperUtils.isNotProspect(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData, authorization, xSantanderClientId);
        PersonDTO personDTO = ProspectMapperUtils.personDTONames(personData, param);
        // ContactPoint
        var contactPoint = ProspectMapperUtils.getContactPoint(personData, param);
        List<ContactPointDTO> contactPoints = Arrays.asList(contactPoint);
        prospectSearchDTO.setContactPoints(contactPoints);
        prospectSearchDTO.setDocument(ProspectMapperUtils.getDocumentBasics(personData, param));
        prospectSearchDTO.setProspectId(personData.getNumper());
        prospectSearchDTO.setPerson(personDTO);
        prospects.add(prospectSearchDTO);
        responseDTO.setProspects(prospects);
        return responseDTO;
    }// method closure

    public String replaceSpaces(String cadena) {
        cadena = cadena.replaceAll("\\s{3}", " ");
        cadena = cadena.replaceAll("\\s{2}", " ");
        return cadena.trim();
    }

    /**
     * MAP CREATE PROSPECT
     *
     * @param dtoCustomerRequest
     * @return TrxPersonRequest
     */
    public TrxPersonRequest dtoRequestToTrxRequest(CreateProspectRequestDTO dtoCustomerRequest, String authorization,
            String xSantanderClientId) {

        TrxPersonRequest response = new TrxPersonRequest();
        TrxPersonDataRequest personDataRequest = new TrxPersonDataRequest();
        personDataRequest.setDatosBasicos(new BasicData());
        PersonRequestDTO person = dtoCustomerRequest.getPerson();

        validatePlaceOfBirth(dtoCustomerRequest);

        LocalDate fechaActual = LocalDate.now();
        String fechaExpedicionActual = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");
        validateBirthDate(dtoCustomerRequest, personDataRequest, fechaActual);
        // PlaceOfBirth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();

        if (placeOfBirthRequestDTO != null) {

            // CountryOfBirth
            validateCountryOfBirthTrxRequest(placeOfBirthRequestDTO,
                    dtoCustomerRequest, personDataRequest);

            // State
            validateStateTrxRequest(placeOfBirthRequestDTO, personDataRequest, authorization, xSantanderClientId);

            // TownCode
            // Verificar si el documentTypeCode es "CC"
            if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {
                // Si es "CC", asignar el townCode proporcionado
                if (placeOfBirthRequestDTO.getTownCode() != null) {
                    validateTownTrxCCRequest(placeOfBirthRequestDTO, personDataRequest, dtoCustomerRequest,
                            authorization,
                            xSantanderClientId);
                } else {
                    // Si no se proporciona un townCode, asignar el valor por defecto
                    personDataRequest.getDatosBasicos().setCiudadNacimiento(DEFAULT_CITY);
                }
            } else if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode()) &&
                    placeOfBirthRequestDTO.getTownCode() != null) {
                validateTownTrxCERequest(placeOfBirthRequestDTO, personDataRequest, authorization, xSantanderClientId);
            }
        }

        List<ContactPointRequestDTO> contactPoints = dtoCustomerRequest.getContactPoints();

        if (contactPoints != null && !contactPoints.isEmpty()) {
            ContactPointRequestDTO mobileContactPoint = contactPoints.get(0);
            ContactPointRequestDTO phoneContactPoint = contactPoints.get(0);
            ContactPointRequestDTO emailContactPoint = contactPoints.get(0);

            personDataRequest.getDatosBasicos()
                    .setIndicativo(phoneContactPoint.getPhoneAddress().getInternationalCode());
            personDataRequest.getDatosBasicos()
                    .setPrecelular(mobileContactPoint.getPhoneAddress().getInternationalCode());

            validateContacPointTrxRequest(mobileContactPoint, phoneContactPoint, emailContactPoint, personDataRequest);
        }

        // DATOS BASICOS
        personDataRequest.getDatosBasicos()
                .setTipoIdentificacion(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode());
        personDataRequest.getDatosBasicos()
                .setNumeroIdentificacion(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentNumber());

        String givenName = dtoCustomerRequest.getPerson().getPersonName().getGivenName();
        String lastName = dtoCustomerRequest.getPerson().getPersonName().getLastName();
        String secondLastName = dtoCustomerRequest.getPerson().getPersonName().getSecondLastName();

        validatePersonRequestTrx(givenName, lastName, secondLastName, personDataRequest);

        personDataRequest.getDatosBasicos().setFechaExpedicion(fechaExpedicionActual);

        // VALORES FIJOS
        validateTrxDataBasic(personDataRequest, response);

        return response;
    }// method closure

    private void validateTownTrxCERequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, String authorization, String xSantanderClientId) {

        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        // Si el documentTypeCode es "CE", asignar el valor "99999"
        // Si no se proporciona un townCode en el caso de "CE", asignar el valor "99999"
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
            if (notInformedTown.isPresent()) {
                personDataRequest.getDatosBasicos().setCiudadNacimiento(notInformedTown.get().getCode());
            } else {
                var message = "'99999' " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
    }

    private void validateTownTrxCCRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, CreateProspectRequestDTO dtoCustomerRequest, String authorization,
            String xSantanderClientId) {
        errorService.isBlank(placeOfBirthRequestDTO.getTownCode(), placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, placeOfBirthRequestDTO.getTownCode(),
                placeOfBirthTownCodeMessage);
        var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                xSantanderClientId);
        if (towns != null) {
            var townVar = towns.stream().filter(
                    x -> x.getCode().equals(dtoCustomerRequest.getPerson().getPlaceOfBirth().getTownCode()))
                    .findAny();
            if (!townVar.isPresent()) {
                var message = "'person.placeOfBirth.townCode' " + errorService.invalidValueMessage;
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                        ErrorType.FUNCTIONAL);
            }
        }
        personDataRequest.getDatosBasicos()
                .setCiudadNacimiento(dtoCustomerRequest.getPerson().getPlaceOfBirth().getTownCode());
    }

    private void validateContacPointTrxRequest(ContactPointRequestDTO mobileContactPoint,
            ContactPointRequestDTO phoneContactPoint, ContactPointRequestDTO emailContactPoint,
            TrxPersonDataRequest personDataRequest) {
        // Valida email
        String emailAddress = emailContactPoint.getElectronicAddress().getEmailAddress();
        if (emailAddress != null) {
            errorService.isBlank(emailAddress, contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, emailAddress,
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, String.valueOf(emailAddress.charAt(0)),
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, emailAddress.split("@")[0],
                    contactPointEmailAdressMessage);
            regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, emailAddress.split("@")[1],
                    contactPointEmailAdressMessage);
        } else {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST,
                    "contactPoints[0].electronicAddress.emailAddress': The email field cannot be empty'",
                    ErrorType.FUNCTIONAL);
        }
        personDataRequest.getDatosBasicos().setEmail(emailAddress);

        // Valida mobileNumber
        String mobileNumber = mobileContactPoint.getPhoneAddress()
                .getMobileNumber();
        if (mobileNumber == null || mobileNumber.isBlank()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getMobileNumberBlank());
        } else if (!mobileNumber.matches("^\\d{10}$")) {
            // Si el número de teléfono móvil no tiene 10 dígitos enteros, lanzar un error
            // controlado
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidMobileNumber());
        }

        personDataRequest.getDatosBasicos().setCelular(mobileNumber);

        // SI EL PHONE NUMBER VIENE NULO O BLANCO SE DEBE COPIAR EL MOBILE NUMBER
        String phoneNumber = phoneContactPoint.getPhoneAddress()
                .getPhoneNumber();
        if (phoneNumber == null || phoneNumber.isBlank()) {
            personDataRequest.getDatosBasicos().setTelefono(phoneContactPoint.getPhoneAddress().getMobileNumber());
        } else {
            if (!phoneNumber.matches("^\\d{10}$")) {
                throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.getInvalidMobileNumber());
            }
            personDataRequest.getDatosBasicos().setTelefono(phoneNumber);
        }
    }

    private void validateStateTrxRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            TrxPersonDataRequest personDataRequest, String authorization,
            String xSantanderClientId) {
        if (placeOfBirthRequestDTO.getState() != null && placeOfBirthRequestDTO.getState().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_FORMAT,
                    placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_LENGTH,
                    placeOfBirthRequestDTO.getState().getCode(), placeBirthStateCodeMessage);
            var states = parameterApiService.getParameter(ParametersEnums.STATES.value(), null, authorization,
                    xSantanderClientId);
            if (states != null) {
                Optional<DataListDTO> state = states.stream()
                        .filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getState().getCode())).findAny();
                if (!state.isPresent()) {
                    var message = "'person.placeOfBirth.state.code' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }
            }
            personDataRequest.getDatosBasicos().setDepartamento(placeOfBirthRequestDTO.getState().getCode());
        }
    }

    private void validateTrxDataBasic(TrxPersonDataRequest personDataRequest, TrxPersonRequest response) {

        personDataRequest.getDatosBasicos().setPaisExpedicion(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setCiudadExpedicion(DEFAULT_CITY_DOCUMENT);
        if (personDataRequest.getDatosBasicos().getPaisNacimiento() == null
                || personDataRequest.getDatosBasicos().getPaisNacimiento().isBlank()) {
            personDataRequest.getDatosBasicos().setPaisNacimiento(COUNTRY_REQUEST);
        }
        personDataRequest.getDatosBasicos().setNacionalidad(personDataRequest.getDatosBasicos().getPaisNacimiento());
        personDataRequest.getDatosBasicos().setSexo("M");
        personDataRequest.getDatosBasicos().setPaisDireccion(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setClase("004");
        personDataRequest.getDatosBasicos().setAgrofic("10000001");
        personDataRequest.getDatosBasicos().setTipper("F");
        personDataRequest.getDatosBasicos().setSucadm("0100");
        personDataRequest.getDatosBasicos().setCiudad(DEFAULT_CITY);
        personDataRequest.getDatosBasicos().setTipoVia("NN");
        personDataRequest.getDatosBasicos().setNombreVia("No informado");
        personDataRequest.getDatosBasicos().setCodpaip(COUNTRY_REQUEST);
        personDataRequest.getDatosBasicos().setTipdomp("PRI");
        personDataRequest.getDatosBasicos().setTiptelp("001");
        personDataRequest.getDatosBasicos().setDepartamento("05");
        personDataRequest.getDatosBasicos().setFecalt("2024-01-09");
        personDataRequest.getDatosBasicos().setDescripcionDireccion("NO INFORMADO");
        personDataRequest.getDatosBasicos().setFecfal("9999-12-31");
        personDataRequest.getDatosBasicos().setLugardeNacimiento(BLANK);
        personDataRequest.getDatosBasicos().setUsualt(BLANK);
        personDataRequest.getDatosBasicos().setUsumod(BLANK);
        personDataRequest.getDatosBasicos().setAutorizoTelefono(false);
        personDataRequest.getDatosBasicos().setAutorizacionEmail(false);
        personDataRequest.getDatosBasicos().setLugardeExpDescripcion(BLANK);
        personDataRequest.getDatosBasicos().setCodact(BLANK);
        personDataRequest.getDatosBasicos().setConper(BLANK);
        personDataRequest.getDatosBasicos().setDomant(BLANK);
        personDataRequest.getDatosBasicos().setEntpre(BLANK);
        personDataRequest.getDatosBasicos().setEstciv(BLANK);
        personDataRequest.getDatosBasicos().setEstper(BLANK);
        personDataRequest.getDatosBasicos().setEstrat(BLANK);
        personDataRequest.getDatosBasicos().setHstamp(BLANK);
        personDataRequest.getDatosBasicos().setHstamp2(BLANK);
        personDataRequest.getDatosBasicos().setHstamp3(BLANK);
        personDataRequest.getDatosBasicos().setHstamp4(BLANK);
        personDataRequest.getDatosBasicos().setHstamp5(BLANK);
        personDataRequest.getDatosBasicos().setLogdomp(BLANK);
        personDataRequest.getDatosBasicos().setLogtelp(BLANK);
        personDataRequest.getDatosBasicos().setNumper(BLANK);
        personDataRequest.getDatosBasicos().setProfes(BLANK);
        personDataRequest.getDatosBasicos().setSeccel(BLANK);
        personDataRequest.getDatosBasicos().setSecdoc(BLANK);
        personDataRequest.getDatosBasicos().setSecdomp(BLANK);
        personDataRequest.getDatosBasicos().setSecdotc(BLANK);
        personDataRequest.getDatosBasicos().setSecdotp(BLANK);
        personDataRequest.getDatosBasicos().setSecema(BLANK);
        personDataRequest.getDatosBasicos().setSectelp(BLANK);
        personDataRequest.getDatosBasicos().setSucmod(BLANK);
        personDataRequest.getDatosBasicos().setTermod(BLANK);
        response.setData(personDataRequest);
    }

    private void validateCountryOfBirthTrxRequest(PlaceOfBirthRequestDTO placeOfBirthRequestDTO,
            CreateProspectRequestDTO dtoCustomerRequest, TrxPersonDataRequest personDataRequest) {

        if (placeOfBirthRequestDTO.getCountry() != null && placeOfBirthRequestDTO.getCountry().getCode() != null) {
            errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeBirthCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeBirthCountryCodeMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                    placeBirthCountryCodeMessage);
            // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
            if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())
                    && !"CO".equals(placeOfBirthRequestDTO.getCountry().getCode())) {
                // Establecer el country en "CO"
                placeOfBirthRequestDTO.getCountry().setCode("CO");
            }
            // Si el documentTypeCode es "CE" y se informa un country "CO", lanzar excepción
            if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

                if ("CO".equals(placeOfBirthRequestDTO.getCountry().getCode())) {
                    var message = "'person.placeOfBirth.country.code' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }

                if (placeOfBirthRequestDTO.getCountry() != null
                        && placeOfBirthRequestDTO.getCountry().getCode() != null) {
                    errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT,
                            placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH,
                            placeOfBirthRequestDTO.getCountry().getCode(),
                            placeBirthCountryCodeMessage);
                }
            }
            personDataRequest.getDatosBasicos().setPaisNacimiento(
                    DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
        } else {
            // Si no se proporciona un country, asignar el valor por defecto
            personDataRequest.getDatosBasicos().setPaisNacimiento(COUNTRY_REQUEST);
        }
    }

    private void validatePersonRequestTrx(String givenName, String lastName, String secondLastName,
            TrxPersonDataRequest personDataRequest) {
        // Validación del nombre
        if (givenName != null) {
            errorService.isBlank(givenName, personaGiveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenName, personaGiveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenName, personaGiveNameMessage);
            givenName = replaceSpaces(givenName);
            personDataRequest.getDatosBasicos().setNombre(givenName);
        }

        // Validación del primer apellido
        if (lastName != null) {
            errorService.isBlank(lastName, personaLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName, personaLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName, personaLastNameMessage);
            lastName = replaceSpaces(lastName);
            personDataRequest.getDatosBasicos().setPrimerApellido(lastName);
        }

        // Validación del segundo apellido
        if (secondLastName != null) {
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, secondLastName, personaSecondLastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, secondLastName, personaSecondLastNameMessage);
            secondLastName = replaceSpaces(secondLastName);
            personDataRequest.getDatosBasicos().setSegundoApellido(secondLastName);
        }

    }

    private void validatePlaceOfBirth(CreateProspectRequestDTO dtoCustomerRequest) {
        // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
        if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

            // Establecer el country en "CO"
            if (dtoCustomerRequest.getPerson().getPlaceOfBirth() != null) {
                var placeOfBirth = dtoCustomerRequest.getPerson().getPlaceOfBirth();

                if (placeOfBirth.getCountry() == null) {
                    var country = new CountryRequestDTO();
                    country.setCode("CO");
                    dtoCustomerRequest.getPerson().getPlaceOfBirth().setCountry(country);
                } else {
                    dtoCustomerRequest.getPerson().getPlaceOfBirth().getCountry().setCode("CO");
                }
            } else {
                var placeOfBirth = new PlaceOfBirthRequestDTO();
                var country = new CountryRequestDTO();
                country.setCode("CO");
                placeOfBirth.setCountry(country);
                dtoCustomerRequest.getPerson().setPlaceOfBirth(placeOfBirth);
            }
        }
    }

    private void validateBirthDate(CreateProspectRequestDTO dtoCustomerRequest,
            TrxPersonDataRequest personDataRequest, LocalDate fechaActual) {
        personDataRequest.getDatosBasicos().setFechaNacimiento("1940-01-01");
        if (dtoCustomerRequest.getPerson().getBirthDate() != null) {
            errorService.isBlank(dtoCustomerRequest.getPerson().getBirthDate(), "person.birthDate");
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, dtoCustomerRequest.getPerson().getBirthDate(),
                    "person.birthDate");

            LocalDate birthDate = LocalDate.parse(dtoCustomerRequest.getPerson().getBirthDate());

            if (birthDate.isAfter(fechaActual)) {
                String error = "'person.birthDate': Invalid date (must be minor that actual date)";
                throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, error, ErrorType.FUNCTIONAL);
            }

            personDataRequest.getDatosBasicos().setFechaNacimiento(dtoCustomerRequest.getPerson().getBirthDate());

        }
    }

    public BasicData pef3ResponseToPef2Request(TrxPersonData trxBasicData) {

        BasicData response = new BasicData();
        response.setCelular(trxBasicData.getCelular().replace(" ", ""));
        response.setTelefono(trxBasicData.getTelefono().replace(" ", ""));
        response.setAgrofic(trxBasicData.getAgrofic());
        response.setCodact(trxBasicData.getCodact());
        response.setClase(trxBasicData.getClase()); // 004
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
        // pais
        response.setPaisDireccion(trxBasicData.getPaisDireccion());
        response.setPaisExpedicion(trxBasicData.getPaisExpedicion());
        response.setPaisNacimiento(trxBasicData.getPaisNacimiento());
        response.setPaisDireccionDesc(""); // DESC
        response.setPaisNacimientoDesc(""); // DESC
        response.setPaisExpedicionDesc(""); // DESC
        response.setLugardeExpDescripcion(""); // DESC
        response.setLugardeNacimiento(""); // DESC
        response.setDescripcionDireccion(trxBasicData.getDescripcionDireccion());
        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        // ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
        response.setFecing(trxBasicData.getFecing());
        response.setCiudadDescripcion(""); // DESC
        response.setDomant(Integer.toString(trxBasicData.getDomant())); // CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel()));// CASE
        response.setSeccel(Integer.toString(trxBasicData.getSeccel())); // CASE
        response.setSecema(Integer.toString(trxBasicData.getSecema())); // CASE
        response.setSecdotc(Integer.toString(trxBasicData.getSecdotc())); // CASE
        response.setSectelp(Integer.toString(trxBasicData.getSectelp())); // CASE
        response.setSecdomp(Integer.toString(trxBasicData.getSecdomp())); // CASE
        response.setSecdotp(Integer.toString(trxBasicData.getSecdotp())); // CASE
        response.setSucadm(trxBasicData.getSucadm());
        response.setSucmod(trxBasicData.getSucmod());

        response.setAutorizoTelefono(Boolean.parseBoolean(trxBasicData.getAutorizoTelefono()));// CASE
        response.setAutorizacionEmail(Boolean.parseBoolean(trxBasicData.getAutorizacionEmail())); // CASE

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
    }// method closure

    /**
     * Request From Update Prospect
     *
     * @param dtoUpdateProspectRequest ALL null data in response will be ignored
     * @return BasicData
     */
    public BasicData prospectPatchToPef2Request(CreateProspectRequestDTO dtoUpdateProspectRequest, String authorization,
            String xSantanderClientId) {
        BasicData response = new BasicData();
        PersonRequestDTO person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;
        // Person Name
        PersonNameRequestDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            validatePerson(personNameDTO, response);
        }

        // Documents
        List<DocumentRequestDTO> documents = person.getDocuments();
        validatedocs(documents, response, authorization, xSantanderClientId);
        // FirstNationality
        if (person.getFirstNationality() != null && person.getFirstNationality().getCode() != null) {

            errorService.isBlank(person.getFirstNationality().getCode(), firstNationalityMessageMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getFirstNationality().getCode(),
                    firstNationalityMessageMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getFirstNationality().getCode(),
                    firstNationalityMessageMessage);

            response.setNacionalidad(DataUtils.translateCountryToXXX(person.getFirstNationality().getCode()));
        }

        // CountryOfResidence
        if (person.getCountryOfResidence() != null && person.getCountryOfResidence().getCode() != null) {

            errorService.isBlank(person.getCountryOfResidence().getCode(), countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);
            regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, person.getCountryOfResidence().getCode(),
                    countryOfResidenceMessage);

            response.setPaisDireccion(DataUtils.translateCountryToXXX(person.getCountryOfResidence().getCode()));
        }

        // Place of Birth
        PlaceOfBirthRequestDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        validatePlaceOfBirthPatchPf2(placeOfBirthRequestDTO, authorization,
                xSantanderClientId, response);

        // Birth Date
        if (person.getBirthDate() != null && person.getBirthDate().isBlank()) {
            errorService.isBlank(person.getBirthDate(), "birthDate");
        }
        if (person.getBirthDate() != null) {
            regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, person.getBirthDate(), "birthDate");
        }
        response.setFechaNacimiento(person.getBirthDate());

        // Sex
        if (person.getGenderCode() != null) {
            errorService.isBlank(person.getGenderCode(), genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, person.getGenderCode(), genderCodeMessage);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, person.getGenderCode(), genderCodeMessage);
            response.setSexo(person.getGenderCode());
        }

        // foreingtaxIndicator
        if (person.getForeignTaxIndicator() != null && !"YES".equals(person.getForeignTaxIndicator())
                && !"NO".equals(person.getForeignTaxIndicator())) {
            ErrorDTO errorForeignTaxIndicator = ErrorCatalog.getIvalidInput();
            errorForeignTaxIndicator.setMessage("ForeignTaxIndicator: " + errorForeignTaxIndicator.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorForeignTaxIndicator);
        }

        // Contact Point
        validateContacPoint(dtoUpdateProspectRequest, response);
        // end contactPoint

        return response;
    }// method closure

    private void validatePlaceOfBirthPatchPf2(PlaceOfBirthRequestDTO placeOfBirthRequestDTO, String authorization,
            String xSantanderClientId, BasicData response) {
        if (placeOfBirthRequestDTO != null) {
            // CountryOfBirth
            if (placeOfBirthRequestDTO.getCountry() != null &&
                    placeOfBirthRequestDTO.getCountry().getCode() != null) {

                errorService.isBlank(placeOfBirthRequestDTO.getCountry().getCode(), placeOfBirthCountryCodeMessage);
                regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, placeOfBirthRequestDTO.getCountry().getCode(),
                        placeOfBirthCountryCodeMessage);
                regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, placeOfBirthRequestDTO.getCountry().getCode(),
                        placeOfBirthCountryCodeMessage);

                response.setPaisNacimiento(
                        DataUtils.translateCountryToXXX(placeOfBirthRequestDTO.getCountry().getCode()));
            }

            // Town
            if (placeOfBirthRequestDTO.getTown() != null) {

                errorService.isBlank(placeOfBirthRequestDTO.getTown(), "town");
                var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                        xSantanderClientId);

                if (towns != null) {
                    var townVar = towns.stream().filter(x -> x.getCode().equals(placeOfBirthRequestDTO.getTown()))
                            .findAny();

                    if (!townVar.isPresent()) {
                        var message = "'town' " + errorService.invalidValueMessage;
                        throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                                ErrorType.FUNCTIONAL);
                    }

                    response.setCiudadNacimiento(placeOfBirthRequestDTO.getTown());

                }
            }
        }
    }

    private void validateContacPoint(CreateProspectRequestDTO dtoUpdateProspectRequest, BasicData response) {
        if (dtoUpdateProspectRequest.getContactPoints() != null
                && !dtoUpdateProspectRequest.getContactPoints().isEmpty()) {
            ContactPointRequestDTO cPoint = dtoUpdateProspectRequest.getContactPoints().get(0);
            // PostalAddress
            if (cPoint.getPostalAddress() != null) {
                // StreetTypeCode "NN"
                if (cPoint.getPostalAddress().getStreetTypeCode() != null
                        && cPoint.getPostalAddress().getStreetTypeCode().length() == 2) {
                    response.setTipoVia(cPoint.getPostalAddress().getStreetTypeCode());
                }
                // Full Address
                response.setNombreVia(cPoint.getPostalAddress().getFullAddress());
                // Full townName
                response.setCiudad(cPoint.getPostalAddress().getTownName());
                // Country code
                if (cPoint.getPostalAddress().getCountry() != null) {
                    response.setCodpaip(cPoint.getPostalAddress().getCountry().getCode());
                }
            }
            // PhoneAddress
            if (cPoint.getPhoneAddress() != null) {
                response.setIndicativo(cPoint.getPhoneAddress().getInternationalCode());
                response.setTelefono(cPoint.getPhoneAddress().getPhoneNumber());
                response.setPrecelular(cPoint.getPhoneAddress().getInternationalCode());
                response.setCelular(cPoint.getPhoneAddress().getMobileNumber());
            }
            // EmailAddress
            if (cPoint.getElectronicAddress() != null) {
                response.setEmail(cPoint.getElectronicAddress().getEmailAddress());
            }
        }
    }

    private void validatePerson(PersonNameRequestDTO personNameDTO, BasicData response) {
        // Given Name
        if (personNameDTO.getGivenName() != null) {
            errorService.isBlank(personNameDTO.getGivenName(), giveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, personNameDTO.getGivenName(), giveNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, personNameDTO.getGivenName(), giveNameMessage);
            response.setNombre(personNameDTO.getGivenName());
        }
        // Last Name
        if (personNameDTO.getLastName() != null) {
            errorService.isBlank(personNameDTO.getLastName(), lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, personNameDTO.getLastName(), lastNameMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, personNameDTO.getLastName(), lastNameMessage);
            response.setPrimerApellido(personNameDTO.getLastName());
        }
        // Second Last Name
        if (personNameDTO.getSecondLastName() != null) {
            errorService.isBlank(personNameDTO.getSecondLastName(), secondLastMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, personNameDTO.getSecondLastName(),
                    secondLastMessage);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, personNameDTO.getSecondLastName(),
                    secondLastMessage);
            response.setSegundoApellido(personNameDTO.getSecondLastName());
        }
    }

    private void validatedocs(List<DocumentRequestDTO> documents, BasicData response, String authorization,
            String xSantanderClientId) {
        if (documents != null && !documents.isEmpty()) {
            DocumentRequestDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                if (firstDocument.getCountry() != null && firstDocument.getCountry().getCode() != null) {
                    errorService.isBlank(firstDocument.getCountry().getCode(), documentCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, firstDocument.getCountry().getCode(),
                            documentCountryCodeMessage);
                    regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, firstDocument.getCountry().getCode(),
                            documentCountryCodeMessage);
                    response.setPaisExpedicion(DataUtils.translateCountryToXXX(firstDocument.getCountry().getCode()));
                }
                validateTownsPatch(firstDocument, response, authorization, xSantanderClientId);

                if (firstDocument.getIssueDate() != null) {
                    errorService.isBlank(firstDocument.getTown(), "documents.issueDate");
                    regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, firstDocument.getIssueDate(),
                            "documents.issueDate");

                    response.setFechaExpedicion(firstDocument.getIssueDate());
                }
            }
        } // end if documents empty
    }

    private void validateTownsPatch(DocumentRequestDTO firstDocument, BasicData response, String authorization,
            String xSantanderClientId) {
        if (firstDocument.getTown() != null) {
            errorService.isBlank(firstDocument.getTown(), "documents.town");
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, authorization,
                    xSantanderClientId);
            if (towns != null) {
                var townVar = towns.stream().filter(x -> x.getCode().equals(firstDocument.getTown())).findAny();
                if (!townVar.isPresent()) {
                    var message = "'documents.town' " + errorService.invalidValueMessage;
                    throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message,
                            ErrorType.FUNCTIONAL);
                }
                response.setCiudadExpedicion(firstDocument.getTown());
            }
        }
    }

    public String usualtMapper(String usualt, String foreignTaxIndicator) {

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES")
                ? foreignTaxIndicatorPositive
                : foreignTaxIndicatorNegative;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : defaultChannel;

        return localUsualt + localForeignTaxIndicator;
    }

    public CreateProspectRequestDTO validateDocumentTypeForCountry(CreateProspectRequestDTO dtoCustomerRequest) {

        // Verificar si el documentTypeCode es "CC" y el country es distinto de "CO"
        if ("CC".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())) {

            // Establecer el country en "CO"
            var placeOfBirth = new PlaceOfBirthRequestDTO();
            var country = new CountryRequestDTO();
            country.setCode("CO");
            placeOfBirth.setCountry(country);
            dtoCustomerRequest.getPerson().setPlaceOfBirth(placeOfBirth);

        } // Si el documentTypeCode es "CE" y se informa un country "CO", lanzar excepción
        if ("CE".equals(dtoCustomerRequest.getPerson().getDocuments().get(0).getDocumentTypeCode())
                && "CO".equals(dtoCustomerRequest.getPerson().getPlaceOfBirth().getCountry().getCode())) {
            var message = "'person.placeOfBirth.country.code' " + errorService.invalidValueMessage;
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }

        return dtoCustomerRequest;
    }

}// class closure
