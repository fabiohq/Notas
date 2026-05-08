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

falla 
@Test
	void dtoRequestToTrxRequestShouldDefaultCountryToColombiaWhenDocumentIsCC() {
		CreateProspectRequestDTO request = buildCreateRequest("CC", "US", null);

		TrxPersonRequest result = mapper.dtoRequestToTrxRequest(request, "auth", "client");

		assertEquals("COL", result.getData().getDatosBasicos().getPaisNacimiento());
		assertEquals("05101", result.getData().getDatosBasicos().getCiudadNacimiento());
	}

        linea fallo 
        TrxPersonRequest result = mapper.dtoRequestToTrxRequest(request, "auth", "client");
        
    error
    ServiceException(code=400 BAD_REQUEST, error=ErrorDTO(code=PROSPECTS-P-F-9400, message='person.placeOfBirth.state.code' Invalid value, level=error, description='person.placeOfBirth.state.code' Invalid value), timestamp=null)
	at com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapperTest.lambda$0(ProspectMapperTest.java:79)
	at org.mockito.internal.stubbing.StubbedInvocationMatcher.answer(StubbedInvocationMatcher.java:42)
	at org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:103)
	at org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)
	at org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:34)
	at org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:82)
	at org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(MockMethodAdvice.java:134)
	at com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService.serviceExceptionBuilder(ErrorService.java:36)
	at com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapper.validateStateTrxRequest(ProspectMapper.java:341)
	at com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapper.dtoRequestToTrxRequest(ProspectMapper.java:170)
	at com.santander.bnc.bsn049.bncbsn049msprospects.mappers.ProspectMapperTest.dtoRequestToTrxRequestShouldDefaultCountryToColombiaWhenDocumentIsCC(ProspectMapperTest.java:123)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)



