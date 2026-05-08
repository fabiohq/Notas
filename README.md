@Test
void dtoRequestToTrxRequestShouldUseMobileWhenPhoneIsBlank() {
    when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
        .thenReturn(List.of(
            DataListDTO.builder().code("05101").description("CIUDAD").build(),
            DataListDTO.builder().code("05").description("ANTIOQUIA").build()
        ));

    CreateProspectRequestDTO request = buildCreateRequest("CC", "CO", "05101");

    request.getContactPoints().get(0)
        .getPhoneAddress()
        .setPhoneNumber("");

    TrxPersonRequest result =
        mapper.dtoRequestToTrxRequest(request, "auth", "client");

    assertEquals(
        "3001234567",
        result.getData().getDatosBasicos().getTelefono()
    );
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenEmailIsNull() {
    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "CO", "05101");

    request.getContactPoints().get(0)
        .setElectronicAddress(null);

    assertThrows(ServiceException.class,
        () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenTownCodeDoesNotExist() {

    when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
        .thenReturn(List.of(
            DataListDTO.builder().code("99999").description("NO INFORMADO").build()
        ));

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "CO", "05101");

    assertThrows(ServiceException.class,
        () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenStateDoesNotExist() {

    when(parameterApiService.getParameter(eq(ParametersEnums.STATES.value()),
            isNull(), anyString(), anyString()))
        .thenReturn(List.of());

    when(parameterApiService.getParameter(eq(ParametersEnums.TOWNS.value()),
            isNull(), anyString(), anyString()))
        .thenReturn(List.of(
            DataListDTO.builder().code("05101").description("CIUDAD").build()
        ));

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "CO", "05101");

    assertThrows(ServiceException.class,
        () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
}

@Test
void dtoRequestToTrxRequestShouldDefaultCountryWhenBirthCountryNull() {

    when(parameterApiService.getParameter(anyString(), isNull(), anyString(), anyString()))
        .thenReturn(List.of(
            DataListDTO.builder().code("05101").description("CIUDAD").build(),
            DataListDTO.builder().code("05").description("ANTIOQUIA").build()
        ));

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", null, "05101");

    request.getPerson().getPlaceOfBirth().setCountry(null);

    TrxPersonRequest result =
        mapper.dtoRequestToTrxRequest(request, "auth", "client");

    assertEquals(
        "COL",
        result.getData().getDatosBasicos().getPaisNacimiento()
    );
}

@Test
void prospectPatchToPef2RequestShouldUseDefaultChannel() {

    String result = mapper.usualtMapper(null, null);

    assertEquals("ODSNO", result);
}

@Test
void prospectPatchToPef2RequestShouldUseNegativeIndicator() {

    String result = mapper.usualtMapper("ABC123", "NO");

    assertEquals("ABCNO", result);
}

@Test
void validateDocumentTypeForCountryShouldCreatePlaceOfBirthWhenNull() {

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "US", null);

    request.getPerson().setPlaceOfBirth(null);

    CreateProspectRequestDTO result =
        mapper.validateDocumentTypeForCountry(request);

    assertNotNull(result.getPerson().getPlaceOfBirth());
    assertEquals(
        "CO",
        result.getPerson().getPlaceOfBirth().getCountry().getCode()
    );
}

@Test
void validateDocumentTypeForCountryShouldNotModifyWhenNotCC() {

    CreateProspectRequestDTO request =
        buildCreateRequest("TI", "US", null);

    CreateProspectRequestDTO result =
        mapper.validateDocumentTypeForCountry(request);

    assertEquals(
        "US",
        result.getPerson().getPlaceOfBirth().getCountry().getCode()
    );
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenPhoneInvalid() {

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "CO", "05101");

    request.getContactPoints().get(0)
        .getPhoneAddress()
        .setPhoneNumber("ABC");

    assertThrows(ServiceException.class,
        () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenMobileBlank() {

    CreateProspectRequestDTO request =
        buildCreateRequest("CC", "CO", "05101");

    request.getContactPoints().get(0)
        .getPhoneAddress()
        .setMobileNumber("");

    assertThrows(ServiceException.class,
        () -> mapper.dtoRequestToTrxRequest(request, "auth", "client"));
}

@Test
void dtoRequestToTrxRequestShouldThrowWhenCountryIsInvalidForCE() {

    CreateProspectRequestDTO request =
        buildCreateRequest("CE", "CO", "05101");

    assertThrows(ServiceException.class,
        () -> mapper.validateDocumentTypeForCountry(request));
}