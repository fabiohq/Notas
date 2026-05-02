package com.santander.bnc.bsn049.bncbsn049mscustomer.mappers;

import com.santander.bnc.bsn049.bncbsn049mscustomer.client.service.ParameterApiService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.create.CreateCustomerRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.customer.response.*;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.generic.pagination.PaginationDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.DocumentRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonNameRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.request.PersonRequestDto;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.CustomerSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.search.response.OrganizationCustomerSearchDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.customer.update.UpdateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request.BasicData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ParametersEnums;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mscustomer.service.ParamService;
import com.santander.bnc.bsn049.bncbsn049mscustomer.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerMapper {

    final ParamService paramsService;
    final ParameterApiService parameterApiService;
    final CustomerMapperUtils customerMapperUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;

    @Value("${params.default-channel}")
    private String defaultChannel;

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositive;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegative;

    private static final String GIVEN_NAME_FIELD = "person.personName.givenName";
    private static final String LAST_NAME_FIELD = "person.personName.lastName";
    private static final String SECOND_LAST_NAME_FIELD = "person.personName.secondLastName";
    private static final String DOCUMENT_COUNTRY_CODE_FIELD = "person.documents.country.code";
    private static final String TOWN_FIELD = "person.documents.town";
    private static final String ISSUE_DATE_FIELD = "person.documents.issueDate";
    private static final String EXPIRA_DATE_FIELD  = "person.documents.expirationDate";
    private static final String PLACE_OF_BIRTH_COUNTRY_CODE_FIELD = "person.placeOfBirth.country.code";
    private static final String PLACE_OF_BIRTH_TOWN_FIELD = "person.placeOfBirth.town";
    private static final String BIRTH_DATE_FIELD = "person.birthDate";
    private static final String GENDER_CODE_FIELD = "person.genderCode";
    private static final String COUNTRY_OF_RESIDENCE_CODE_FIELD = "person.countryOfResidence.code";
    private static final String FIRST_NATIONALITY_CODE_FIELD = "person.firstNationality.code";
    private static final String DOCUMENT_NUMBER_FIELD = "person.documents.documentNumber";
    private static final String DOCUMENT_TYPE_CODE_FIELD = "person.documents.documentTypeCode";
    private static final String INTERNATIONAL_CODE_FIELD = "internationalCode";
    private static final String COD_PAID = "codPaid";
    private static final String DEFAULT_CITY = "99999";

    /**
     * Customer Details
     *
     * @param trxBody
     * @param securityHeaders
     * @return
     */
    public CustomerDetailsResponseDTO trxPersonToCustomerDetailsDTO(TrxPersonResponse trxBody, SecurityHeaders securityHeaders) {
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        CustomerDetailsResponseDTO responseDTO = new CustomerDetailsResponseDTO();

        if (CustomerMapperUtils.isNotCustomer(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData, securityHeaders);
        PersonDTO personDTO = CustomerMapperUtils.personDTONames(personData, param);
        // Foreign tax indicator
        personDTO.setForeignTaxIndicator(customerMapperUtils.getForeignTaxIndicator(personData));
        // Contact Point
        responseDTO.setContactPoints(List.of(CustomerMapperUtils.getContactPoint(personData, param)));
        personDTO.setDocuments(List.of(CustomerMapperUtils.getDocumentBasics(personData, param)));
        responseDTO.setPerson(personDTO);
        // Ex customer
        responseDTO.setPendingExCustomer("EXC".equals(personData.getConper()));
        // Source code
        DataOriginDTO dataOrigins = new DataOriginDTO();
        dataOrigins.setSourceCode(CustomerMapperUtils.getSourceCode(personData));
        responseDTO.setDataOrigins(List.of(dataOrigins));
        return responseDTO;
    }// method closure

    /**
     * MAP Customer Search Response
     *
     * @return CustomerSearchResponseDTO
     */
    public CustomerSearchResponseDTO trxPersonToCustomerSearchDTO(TrxPersonResponse trxBody, SecurityHeaders securityHeaders) {
        log.info(GUtils.SLOG + "mapp for search customer");
        TrxPersonData personData = trxBody.getData().getDatosBasicos();
        CustomerSearchResponseDTO responseDTO = new CustomerSearchResponseDTO();
        List<CustomerSearchDTO> customers = new ArrayList<>();
        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO();

        if (CustomerMapperUtils.isNotCustomer(personData.getConper())) {
            return null;
        }
        Parameters param = paramsService.findParameters(personData,securityHeaders);

        DocumentDTO document = CustomerMapperUtils.getDocumentBasics(personData, param);
        PersonDTO personDTO = CustomerMapperUtils.personDTONames(personData, param);
        personDTO.setPlaceOfBirth(null);// Hide in Search Customer
        personDTO.setGenderDescription(null);// Hide in Search Customer
        personDTO.setGenderCode(null);// Hide in Search Customer
        personDTO.setFirstNationality(null);// Hide in Search Customer
        personDTO.setCountryOfResidence(null);// Hide in Search Customer
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));

        // Contact Point
        ContactPointDTO contactPoint = CustomerMapperUtils.getContactPoint(personData, param);
        personDTO.setDocument(document);

        customerSearchDTO.setCustomerId(personData.getNumper());
        customerSearchDTO.setPerson(personDTO);
        customerSearchDTO.setOrganization(new OrganizationCustomerSearchDTO());
        contactPoint.setPhoneAddress(null);// Hide in Search Customer
        contactPoint.setElectronicAddress(null);// Hide in Search Customer
        contactPoint.setUseTypes(null);// Hide in Search Customer
        customerSearchDTO.setContactPoint(contactPoint);

        customers.add(customerSearchDTO);

        responseDTO.setCustomers(customers);
        responseDTO.setPagination(new PaginationDTO());
        return responseDTO;
    }// method closure

    /**
     * USED ON UPDATE CUSTOMER
     *
     * @param trxBasicData
     * @return
     */
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
        String fullAdressFormat = cleanAddress(trxBasicData.getNombreVia()); // Borra espacios en blanco y caracteres
        fullAdressFormat = replaceSpaces(fullAdressFormat);
        response.setNombreVia(fullAdressFormat);
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
        //response.setDescripcionDireccion("83 7 VAPTO 202                                   9999-12-31");


        response.setIndicativo(trxBasicData.getIndicativo());
        response.setTermod(trxBasicData.getTermod());
        // ciudad
        response.setCiudad(trxBasicData.getCiudad());
        response.setCiudadExpedicion(trxBasicData.getCiudadExpedicion());
        response.setCiudadNacimiento(trxBasicData.getCiudadNacimiento());
//        response.setCiudadExpedicion("");
//        response.setCiudadNacimiento("");
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
     *
     * @param dtoUpdateProspectRequest
     * @return
     */
    public BasicData prospectPatchToPef2Request(CreateCustomerRequestDTO dtoUpdateProspectRequest,SecurityHeaders securityHeaders, String tipoIdentificacion ) {
        BasicData response = new BasicData();
        PersonRequestDto person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;
        // Person Name
        PersonNameRequestDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            processGivenName(personNameDTO.getGivenName(), response);
            processLastName(personNameDTO.getLastName(), response);
            processSecondLastName(personNameDTO.getSecondLastName(), response, tipoIdentificacion);
        }

        // Documents
        List<DocumentRequestDTO> documents = person.getDocuments();
        if (documents != null && !documents.isEmpty()) {
            DocumentRequestDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                processDocumentCountry(firstDocument, response);
                processDocumentTown(firstDocument, response, securityHeaders);
                processDocumentIssueDate(firstDocument, response);
                processDocumentExpirationDate(firstDocument, response);
            }
        }

        // Place of Birth
        PlaceOfBirthDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        if (placeOfBirthRequestDTO != null) {
            processCountryOfBirth(placeOfBirthRequestDTO.getCountry(), response, securityHeaders);
            if (placeOfBirthRequestDTO.getTown() != null) {
                processTown(placeOfBirthRequestDTO.getTown(), response, securityHeaders);
            }
            processTownCode(placeOfBirthRequestDTO.getTownCode(), response, securityHeaders);
        }



        // Birth Date
        if (person.getBirthDate() != null) {
            if (person.getBirthDate().isBlank()) {
                errorService.isBlank(person.getBirthDate(), BIRTH_DATE_FIELD);
            }
            regexUtils.validateRegex(RegexTypes.BIRTHDAY_FORMAT, person.getBirthDate(),
                    BIRTH_DATE_FIELD);
            response.setFechaNacimiento(person.getBirthDate());
        }
        // Sex
        if (person.getGenderCode() != null) {
            errorService.isBlank(person.getGenderCode(), GENDER_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, person.getGenderCode(), GENDER_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, person.getGenderCode(), GENDER_CODE_FIELD);
            response.setSexo(person.getGenderCode());
        }
        return response;
    }// method closure

    private void processGivenName(String givenName, BasicData response) {
        if (givenName != null) {
            if (givenName.isBlank()) {
                errorService.isBlank(givenName, GIVEN_NAME_FIELD);
            } else {
                regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenName, GIVEN_NAME_FIELD);
                regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenName, GIVEN_NAME_FIELD);
                response.setNombre(StringUtils.replaceSpaces(givenName));
            }
        }
    }

    private void processLastName(String lastName, BasicData response) {
        if (lastName != null) {
            if (lastName.isBlank()) {
                errorService.isBlank(lastName, LAST_NAME_FIELD);
            } else {
                regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName, LAST_NAME_FIELD);
                regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName, LAST_NAME_FIELD);
                response.setPrimerApellido(StringUtils.replaceSpaces(lastName));
            }
        }
    }

    private void processSecondLastName(String secondLastName, BasicData response, String tipoIdentificacion) {
        if (secondLastName == null){
            return; // Opcional: si el segundo apellido es opcional
        }
        log.info("Segundo apellido: {}", secondLastName);

        if ("CE".equals(tipoIdentificacion) || "CC".equals(tipoIdentificacion)) {
            if (!secondLastName.isBlank()) {
                regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, secondLastName, SECOND_LAST_NAME_FIELD);
                regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, secondLastName, SECOND_LAST_NAME_FIELD);
            }
        }
        response.setSegundoApellido(StringUtils.replaceSpaces(secondLastName));
    }


    private void processDocumentCountry(DocumentRequestDTO document, BasicData response) {
        if (document.getCountry() != null && document.getCountry().getCode() != null) {
            errorService.isBlank(document.getCountry().getCode(), DOCUMENT_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, document.getCountry().getCode(),
                    DOCUMENT_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, document.getCountry().getCode(),
                    DOCUMENT_COUNTRY_CODE_FIELD);
            response.setPaisExpedicion(DataUtils.translateCountryToXXX(document.getCountry().getCode()));
        }
    }

    private void processDocumentTown(DocumentRequestDTO document, BasicData response, SecurityHeaders securityHeaders) {
        if (document != null && document.getTown() != null) {
            processDocumentTown(document.getTown(), response, securityHeaders);
        }
    }

    private void processDocumentTown(String town, BasicData response, SecurityHeaders securityHeaders) {
        if (town.isBlank() || town.isEmpty()) {
            response.setCiudadExpedicion(DEFAULT_CITY); // Si 'town' en el documento está en blanco, se establece como '99999'
        } else {
            // Validación y asignación normal si 'town' en el documento tiene valor
            errorService.isBlank(town, TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, town, TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, town, TOWN_FIELD);

            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders);

            if (towns != null) {
                String townCode = findDocumentTownCode(town, towns);
                response.setCiudadExpedicion(townCode);
            }
        }
    }



    private String findDocumentTownCode(String town, List<DataListDTO> towns) {
        CompareStringUtils compareString = new CompareStringUtils();
        for (DataListDTO townDto : towns) {
            if (compareString.ciudadMatch(town, townDto.getDescription())) {
                return townDto.getCode();
            }
        }

        // Si no encuentra una ciudad similar, retorna el código "99999"
        return towns.stream()
                .filter(x -> x.getCode().equals("99999"))
                .findAny()
                .map(DataListDTO::getCode)
                .orElse(null);
    }

    private void processDocumentIssueDate(DocumentRequestDTO document, BasicData response) {
        if (document.getIssueDate() != null) {
            errorService.isBlank(document.getIssueDate(), ISSUE_DATE_FIELD);
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getIssueDate(), ISSUE_DATE_FIELD);

            response.setFechaExpedicion(document.getIssueDate());
        }
    }
    private void processDocumentExpirationDate(DocumentRequestDTO document, BasicData response){
        if (document.getExpirationDate() != null) {
            errorService.isBlank(document.getExpirationDate(), EXPIRA_DATE_FIELD);
            regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, document.getExpirationDate(), EXPIRA_DATE_FIELD);         
            response.setDescripcionDireccion(document.getExpirationDate());    
        }
    }

    private void processCountryOfBirth(CodeNameDTO countryOfBirth, BasicData response, SecurityHeaders securityHeaders) {
        if (countryOfBirth != null && countryOfBirth.getCode() != null) {
            errorService.isBlank(countryOfBirth.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);

            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, countryOfBirth.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, countryOfBirth.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);

            var countries = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), null, securityHeaders);

            if (countries != null) {
                var country = countries.stream().filter(x -> x.getCode().equals(countryOfBirth.getCode())).findAny();

                if (!country.isPresent()) {
                    var message = "'person.placeOfBirth.country.code' " + errorService.INVALID_VALUE;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setPaisNacimiento(DataUtils.translateCountryToXXX(countryOfBirth.getCode()));
            }
        }
    }

    private void processTown(String town, BasicData response, SecurityHeaders securityHeaders) {
        if (town != null) {
            processValidTown(town, response, securityHeaders);
        } else {
            response.setCiudadNacimiento(DEFAULT_CITY); // Si 'town' es null, se establece como '99999'
        }
    }

    private void processValidTown(String town, BasicData response, SecurityHeaders securityHeaders) {
        if (town.isBlank()) {
            response.setCiudadNacimiento(DEFAULT_CITY); // Si 'town' está en blanco, se establece como '99999'
        } else {
            // Validación y asignación normal si 'town' tiene valor
            errorService.isBlank(town, PLACE_OF_BIRTH_TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, town, PLACE_OF_BIRTH_TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, town, PLACE_OF_BIRTH_TOWN_FIELD);

            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders);

            if (towns != null) {
                String townCode = findTownCode(town, towns);
                response.setCiudadNacimiento(townCode);
            }
        }
    }


    private String findTownCode(String town, List<DataListDTO> towns) {
        CompareStringUtils compareString = new CompareStringUtils();
        boolean isSimilar = false;
        DataListDTO similarTown = null;

        for (DataListDTO townDto : towns) {
            isSimilar = compareString.ciudadMatch(town, townDto.getDescription());
            if (isSimilar) {
                similarTown = townDto;
                break;
            }
        }

        if (isSimilar && similarTown != null) {
            return similarTown.getCode();
        } else {
            var notInformedTown = towns.stream().filter(x -> x.getCode().equals("99999")).findAny();
            if (notInformedTown.isPresent()) {
                return notInformedTown.get().getCode();
            }
        }

        return null; // Return a default value if no match is found
    }

    private void processTownCode(String townCode, BasicData response, SecurityHeaders securityHeaders) {
        if (townCode != null) {
            errorService.isBlank(townCode, "placeOfBirth.townCode");
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders);

            if (towns != null) {
                var town = towns.stream().filter(x -> x.getCode().equals(townCode)).findAny();

                if (!town.isPresent()) {
                    var message = "'placeOfBirth.townCode': " + errorService.INVALID_VALUE;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setCiudadNacimiento(townCode);
            }
        }
    }

    public BasicData prospectPutToPef2Request(UpdateProspectRequestDTO dtoUpdateProspectRequest, SecurityHeaders securityHeaders) {
        BasicData response = new BasicData();
        PersonDTO person = dtoUpdateProspectRequest.getPerson();
        if (person == null)
            return response;

        // Person Name
        PersonNameDTO personNameDTO = person.getPersonName();
        if (personNameDTO != null) {
            processPutGivenName(personNameDTO.getGivenName(), response);
            processPutLastName(personNameDTO.getLastName(), response);
            processPutSecondLastName(personNameDTO.getSecondLastName(), response);
        }

        // Gender
        if (person.getGenderCode() != null) {
            errorService.isBlank(person.getGenderCode(), GENDER_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, person.getGenderCode(), GENDER_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, person.getGenderCode(), GENDER_CODE_FIELD);
            response.setSexo(person.getGenderCode());
        }

        // Birth Date
        if (person.getBirthDate() != null) {
            errorService.isBlank(person.getBirthDate(), BIRTH_DATE_FIELD);
            regexUtils.validateRegex(RegexTypes.BIRTHDAY_FORMAT, person.getBirthDate(),
                    BIRTH_DATE_FIELD);
            errorService.isValidDate(person.getBirthDate(), BIRTH_DATE_FIELD);
            response.setFechaNacimiento(person.getBirthDate());
        }

        // Place of Birth
        PlaceOfBirthDTO placeOfBirthRequestDTO = person.getPlaceOfBirth();
        if (placeOfBirthRequestDTO != null) {
            processPutCountryOfBirth(placeOfBirthRequestDTO.getCountry(),response,securityHeaders);
            processPutTownName(placeOfBirthRequestDTO.getTown(), response, securityHeaders);
        }

        // CountryOfResidence
        processPutCountryOfResidence(person.getCountryOfResidence(), response, securityHeaders);

        // FirstNationality
        processPutFirstNationality(person.getFirstNationality(), response, securityHeaders);

        // Documents
        processPutDocuments(person.getDocuments(), response, securityHeaders);

        // Contact Point
        List<ContactPointDTO> contactPoints = dtoUpdateProspectRequest.getContactPoints();
        if (contactPoints != null) {
            for (ContactPointDTO contactPoint : contactPoints) {
                processPutContactPoint(contactPoint, response);
            }
        }

        return response;
    }

    private void processPutGivenName(String givenName, BasicData response) {
        if (givenName != null) {
            errorService.isBlank(givenName, GIVEN_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, givenName, GIVEN_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, givenName, GIVEN_NAME_FIELD);
            response.setNombre(givenName);
        }
    }

    private void processPutLastName(String lastName, BasicData response) {
        if (lastName != null) {
            errorService.isBlank(lastName, LAST_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, lastName, LAST_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, lastName, LAST_NAME_FIELD);
            response.setPrimerApellido(lastName);
        }
    }

    private void processPutSecondLastName(String secondLastName, BasicData response) {
        if (secondLastName != null) {
            errorService.isBlank(secondLastName, SECOND_LAST_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, secondLastName, SECOND_LAST_NAME_FIELD);
            regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, secondLastName, SECOND_LAST_NAME_FIELD);
            response.setSegundoApellido(secondLastName);
        }
    }

    private void processPutCountryOfBirth(CodeNameDTO countryDTO, BasicData response, SecurityHeaders securityHeaders) {
        if (countryDTO != null && countryDTO.getCode() != null) {
            errorService.isBlank(countryDTO.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, countryDTO.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, countryDTO.getCode(), PLACE_OF_BIRTH_COUNTRY_CODE_FIELD);

            var country = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), null, securityHeaders);

            if (country != null) {
                var placeOfBirthCountry = country.stream().filter(x -> x.getCode().equals(countryDTO.getCode())).findAny();

                if (!placeOfBirthCountry.isPresent()) {
                    var message = "'person.placeOfBirth.country.code': " + errorService.CODE_NOT_EXIST;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setPaisNacimiento(DataUtils.translateCountryToXXX(countryDTO.getCode()));
            }
        }
    }

    private void processPutTownName(String town, BasicData response, SecurityHeaders securityHeaders) {
        if (town != null) {
            errorService.isBlank(town, PLACE_OF_BIRTH_TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, town, PLACE_OF_BIRTH_TOWN_FIELD);
            regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, town, PLACE_OF_BIRTH_TOWN_FIELD);
            var towns = parameterApiService.getParameter(ParametersEnums.TOWNS.value(), null, securityHeaders);

            if (towns != null) {
                var placeOfBirthTown = towns.stream().filter(x -> x.getDescription().equals(town)).map(DataListDTO::getCode).findAny();
                if (!placeOfBirthTown.isPresent()) {
                    var message = "'person.placeOfBirth.town': " + errorService.INVALID_VALUE;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }
                response.setCiudadNacimiento(placeOfBirthTown.get());
            }
        }
    }

    private void processPutCountryOfResidence(CodeNameDTO countryDTO, BasicData response, SecurityHeaders securityHeaders) {
        if (countryDTO != null && countryDTO.getCode() != null) {
            errorService.isBlank(countryDTO.getCode(), COUNTRY_OF_RESIDENCE_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, countryDTO.getCode(), COUNTRY_OF_RESIDENCE_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, countryDTO.getCode(), COUNTRY_OF_RESIDENCE_CODE_FIELD);

            var country = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), null, securityHeaders);

            if (country != null) {
                var countryOfResidence = country.stream().filter(x -> x.getCode().equals(countryDTO.getCode())).findAny();

                if (!countryOfResidence.isPresent()) {
                    var message = "'person.countryOfResidence.code': " + errorService.CODE_NOT_EXIST;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setPaisDireccion(DataUtils.translateCountryToXXX(countryDTO.getCode()));
            }
        }
    }

    // FirstNationality
    private void processPutFirstNationality(CodeNameDTO countryDTO, BasicData response, SecurityHeaders securityHeaders) {
        if (countryDTO != null && countryDTO.getCode() != null) {
            errorService.isBlank(countryDTO.getCode(), FIRST_NATIONALITY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, countryDTO.getCode(), FIRST_NATIONALITY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, countryDTO.getCode(), FIRST_NATIONALITY_CODE_FIELD);

            var country = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), null, securityHeaders);

            if (country != null) {
                var firstNationalityCountry = country.stream().filter(x -> x.getCode().equals(countryDTO.getCode())).findAny();

                if (!firstNationalityCountry.isPresent()) {
                    var message = "'person.firstNationality.code': " + errorService.CODE_NOT_EXIST;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setNacionalidad(DataUtils.translateCountryToXXX(countryDTO.getCode()));
            }
        }
    }

    private void processPutDocuments(List<DocumentDTO> documents, BasicData response, SecurityHeaders securityHeaders) {
        if (documents != null && !documents.isEmpty()) {
            DocumentDTO firstDocument = documents.get(0);
            if (firstDocument != null) {
                processPutDocumentTypeCode(firstDocument.getDocumentTypeCode());
                processPutDocumentNumber(firstDocument.getDocumentNumber());
                processPutIssueDate(firstDocument.getIssueDate(), response);
                processPutDocumentCountry(firstDocument.getCountry(), response, securityHeaders);
            }
        }
    }

    private void processPutDocumentTypeCode(String documentTypeCode) {
        if (documentTypeCode != null) {
            errorService.isBlank(documentTypeCode, DOCUMENT_TYPE_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.DOCUMENT_TYPE_FORMAT, documentTypeCode, DOCUMENT_TYPE_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, documentTypeCode, DOCUMENT_TYPE_CODE_FIELD);
        }
    }

    private void processPutDocumentNumber(String documentNumber) {
        if (documentNumber != null) {
            errorService.isBlank(documentNumber, DOCUMENT_NUMBER_FIELD);
            regexUtils.validateRegex(RegexTypes.ONLY_NUMBERS, documentNumber, DOCUMENT_NUMBER_FIELD);
            regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_11, documentNumber, DOCUMENT_NUMBER_FIELD);
        }
    }

    private void processPutIssueDate(String issueDate, BasicData response) {
        if (issueDate != null) {
            errorService.isBlank(issueDate, ISSUE_DATE_FIELD);
            regexUtils.validateRegex(RegexTypes.BIRTHDAY_FORMAT, issueDate, ISSUE_DATE_FIELD);
            errorService.isValidDate(issueDate, ISSUE_DATE_FIELD);
            response.setFechaExpedicion(issueDate);
        }
    }

    private void processPutDocumentCountry(CodeNameDTO country, BasicData response, SecurityHeaders securityHeaders) {
        if (country != null && country.getCode() != null) {
            errorService.isBlank(country.getCode(), DOCUMENT_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_FORMAT, country.getCode(), DOCUMENT_COUNTRY_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, country.getCode(), DOCUMENT_COUNTRY_CODE_FIELD);

            var countryList = parameterApiService.getParameter(ParametersEnums.COUNTRY.value(), null, securityHeaders);

            if (countryList != null) {
                var documentCountry = countryList.stream().filter(x -> x.getCode().equals(country.getCode())).findAny();

                if (!documentCountry.isPresent()) {
                    var message = "'person.documents.country.code': " + errorService.CODE_NOT_EXIST;
                    throw errorService.errorBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
                }

                response.setPaisExpedicion(DataUtils.translateCountryToXXX(country.getCode()));
            }
        }
    }

    private void processPutContactPoint(ContactPointDTO contactPoint, BasicData response) {
        if (contactPoint == null) {
            return;
        }

        processPutPostalAddress(contactPoint.getPostalAddress(), response);
        processPutPhoneAddress(contactPoint.getPhoneAddress(), response);
        processPutEmailAddress(contactPoint.getElectronicAddress(), response);
    }

    private void processPutPostalAddress(PostalAddressDTO postalAddress, BasicData response) {
        if (postalAddress == null) {
            return;
        }

        if (postalAddress.getStreetTypeCode() != null) {
            if (!postalAddress.getStreetTypeCode().isBlank()) {
                regexUtils.validateRegex(RegexTypes.STRICT_CHAR_LENGTH_2, postalAddress.getStreetTypeCode(), "streetTypeCode");
            }
            response.setTipoVia(postalAddress.getStreetTypeCode());
        }

        if (postalAddress.getFullAddress() != null) {
            response.setNombreVia(postalAddress.getFullAddress());
        }

        if (postalAddress.getTown() != null) {
            response.setCiudad(postalAddress.getTown().getName());
        }

        if (postalAddress.getCountry() != null && postalAddress.getCountry().getCode() != null) {
            errorService.isBlank(postalAddress.getCountry().getCode(), COD_PAID);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, postalAddress.getCountry().getCode(), COD_PAID);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, postalAddress.getCountry().getCode(), COD_PAID);
            response.setCodpaip(postalAddress.getCountry().getCode());
        }
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

    private void processPutPhoneAddress(PhoneAddressDTO phoneAddress, BasicData response) {
        if (phoneAddress == null) {
            return;
        }

        response.setIndicativo(phoneAddress.getInternationalCode());
        if (phoneAddress.getInternationalCode() != null && !phoneAddress.getInternationalCode().isBlank()) {
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, phoneAddress.getInternationalCode(), INTERNATIONAL_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, phoneAddress.getInternationalCode(), INTERNATIONAL_CODE_FIELD);
        }

        response.setTelefono(phoneAddress.getPhoneNumber());
        if (phoneAddress.getPhoneNumber() != null && !phoneAddress.getPhoneNumber().isBlank()) {
            regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, phoneAddress.getPhoneNumber(), "phoneNumber");
            regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, phoneAddress.getPhoneNumber(), "phoneNumber");
        }

        response.setPrecelular(phoneAddress.getInternationalCode());
        if (phoneAddress.getInternationalCode() != null && !phoneAddress.getInternationalCode().isBlank()) {
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, phoneAddress.getInternationalCode(), INTERNATIONAL_CODE_FIELD);
            regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, phoneAddress.getInternationalCode(), INTERNATIONAL_CODE_FIELD);
        }

        response.setCelular(phoneAddress.getMobileNumber());
        if (phoneAddress.getMobileNumber() != null && !phoneAddress.getMobileNumber().isBlank()) {
            regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, phoneAddress.getMobileNumber(), "mobileNumber");
            regexUtils.validateRegex(RegexTypes.PHONE_LENGTH, phoneAddress.getMobileNumber(), "mobileNumber");
        }
    }


    private void processPutEmailAddress(ElectronicAddressDTO electronicAddress, BasicData response) {
        if (electronicAddress == null) {
            return;
        }

        response.setEmail(electronicAddress.getEmailAddress());
        if (electronicAddress.getEmailAddress() != null && !electronicAddress.getEmailAddress().isBlank()) {
            regexUtils.validateRegex(RegexTypes.EMAIL, electronicAddress.getEmailAddress(), "emailAddress");
        }
    }


    public String usualtMapper(String usualt, String foreignTaxIndicator){

        var localForeignTaxIndicator = foreignTaxIndicator != null && foreignTaxIndicator.contains("YES") ? foreignTaxIndicatorPositive : foreignTaxIndicatorNegative;

        var localUsualt = usualt != null && usualt.length() > 2 ? usualt.substring(0, 3) : defaultChannel;

        return localUsualt + localForeignTaxIndicator;
    }
}// class closure
