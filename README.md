@Test
void dtoRequestToTrxRequestShouldDefaultCountryToColombiaWhenDocumentIsCC() {
    CreateProspectRequestDTO request = buildCreateRequest("CC", "US", null);

    // IMPORTANTE: este test no valida state
    request.getPerson().getPlaceOfBirth().setState(null);

    TrxPersonRequest result = mapper.dtoRequestToTrxRequest(request, "auth", "client");

    assertEquals("COL", result.getData().getDatosBasicos().getPaisNacimiento());
    assertEquals("05101", result.getData().getDatosBasicos().getCiudadNacimiento());
}