Te falta cubrir principalmente estos escenarios en PatchProspectMapper:
ramas null
ramas else
validaciones regex
fallback town 99999
throw serviceExceptionBuilder
método gigante pef3ResponseToPef2Request
lambdas internos
branches booleanos
Con esos métodos NO llegas al 100% solo con happy paths.
Te dejo EXACTAMENTE los tests que te faltan para subir a 95-100%.
1. cubrir pef3ResponseToPef2Request
Te falta ejecutar prácticamente todos los setters.
Java
@Test
void pef3ResponseToPef2RequestShouldMapAllFields() {

    TrxPersonData trx = new TrxPersonData();

    trx.setCelular("300 123");
    trx.setTelefono("601 123");
    trx.setAgrofic("AGR");
    trx.setCodact("ACT");
    trx.setClase("004");
    trx.setConper("PRO");
    trx.setDepartamento("05");
    trx.setCodpaip("CO");

    trx.setTipoIdentificacion("CC");
    trx.setNumeroIdentificacion("123");

    trx.setTipoVia("CL");
    trx.setNombreVia("10");
    trx.setPrecelular("57");

    trx.setSexo("M");
    trx.setPrimerApellido("PEREZ");
    trx.setSegundoApellido("GOMEZ");

    trx.setPaisDireccion("COL");
    trx.setPaisExpedicion("COL");
    trx.setPaisNacimiento("COL");

    trx.setDescripcionDireccion("DIR");
    trx.setIndicativo("57");

    trx.setCiudad("05001");
    trx.setCiudadExpedicion("05001");
    trx.setCiudadNacimiento("05001");

    trx.setFecing("2024-01-01");

    trx.setDomant(1);
    trx.setSeccel(2);
    trx.setSecema(3);
    trx.setSecdotc(4);
    trx.setSectelp(5);
    trx.setSecdomp(6);

    trx.setSucadm("001");
    trx.setSucmod("002");

    trx.setAutorizoTelefono("true");
    trx.setAutorizacionEmail("true");

    trx.setLogdomp("LOG");
    trx.setLogtelp("TEL");

    trx.setEthistamp("STAMP");
    trx.setEthistamp2("STAMP2");
    trx.setEthistamp3("STAMP3");
    trx.setEthistamp4("STAMP4");
    trx.setEthistamp5("STAMP5");

    trx.setRetesrat("R1");
    trx.setEtsciv("C");
    trx.setRetesper("R2");
    trx.setEtnrepre("R3");

    trx.setUsualt("UALT");

    trx.setFechaExpedicion("2024-01-01");
    trx.setFechaNacimiento("1990-01-01");

    trx.setFecalt("2020");
    trx.setFecfal("2030");

    trx.setEmail("mail@test.com");
    trx.setSecdoc("DOC");

    BasicData result = mapper.pef3ResponseToPef2Request(trx);

    assertEquals("300123", result.getCelular());
    assertEquals("601123", result.getTelefono());

    assertEquals("AGR", result.getAgrofic());
    assertEquals("ACT", result.getCodact());

    assertEquals("004", result.getClase());

    assertTrue(result.getAutorizoTelefono());
    assertTrue(result.getAutorizacionEmail());
}
2. cubrir branch town == null
validateBirthRequest
Java
@Test
void validateBirthRequestShouldSkipWhenTownNullAndTownCodeNull() {

    PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();

    BasicData data = new BasicData();

    mapper.validateBirthRequest(dto, data, "auth", "client");

    assertNull(data.getCiudadNacimiento());
}
3. cubrir fallback 99999
Actualmente NO cubres:
Java
else {
   var notInformedTown ...
}
TEST:
Java
@Test
void similarTownFunctionShouldAssign99999WhenNoMatch() {

    PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();
    dto.setTown("INVALID");

    DataListDTO informed = new DataListDTO();
    informed.setCode("99999");
    informed.setDescription("NO INFORMADO");

    when(parameterApiService.getParameter(any(), isNull(), any(), any()))
            .thenReturn(List.of(informed));

    BasicData data = new BasicData();

    mapper.similarTownFunction(dto, "auth", "client", data);

    assertEquals("99999", data.getCiudadNacimiento());
}
4. cubrir towns == null
Java
@Test
void similarTownFunctionShouldDoNothingWhenTownsNull() {

    PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();
    dto.setTown("MEDELLIN");

    when(parameterApiService.getParameter(any(), isNull(), any(), any()))
            .thenReturn(null);

    BasicData data = new BasicData();

    mapper.similarTownFunction(dto, "auth", "client", data);

    assertNull(data.getCiudadNacimiento());
}
5. cubrir THROW branch
Te falta esto:
Java
throw errorService.serviceExceptionBuilder(...)
TEST:
Java
@Test
void validatePlaceOfBirthShouldThrowWhenTownCodeInvalid() {

    PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();
    dto.setTownCode("999");

    when(parameterApiService.getParameter(any(), isNull(), any(), any()))
            .thenReturn(List.of());

    ErrorDTO error = ErrorDTO.builder()
            .code("ERR")
            .message("invalid")
            .build();

    ServiceException exception =
            new ServiceException(HttpStatus.BAD_REQUEST, error);

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.BAD_REQUEST),
            any(),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(exception);

    BasicData data = new BasicData();

    assertThrows(ServiceException.class,
            () -> mapper.validateplaceOfBirth(dto, data, "auth", "client"));
}
6. cubrir validateFirstNationality
Te falta el branch NULL.
Java
@Test
void validateFirstNationalityShouldSkipWhenNull() {

    PersonRequestDTO person = new PersonRequestDTO();

    BasicData data = new BasicData();

    mapper.validateFirstNationality(person, data);

    assertNull(data.getNacionalidad());
}
7. cubrir validateCountryOfResidence
Java
@Test
void validateCountryOfResidenceShouldSkipWhenNull() {

    PersonRequestDTO person = new PersonRequestDTO();

    BasicData data = new BasicData();

    mapper.validateCountryOfResidence(person, data);

    assertNull(data.getPaisDireccion());
}
8. cubrir issueDate null
Java
@Test
void validateDocumentIssueDateShouldSkipWhenNull() {

    DocumentRequestDTO doc = new DocumentRequestDTO();

    BasicData data = new BasicData();

    mapper.validateDocumentIssueDate(doc, data);

    assertNull(data.getFechaExpedicion());
}
9. cubrir expirationDate null
Java
@Test
void validateDocumentExpirationDateShouldSkipWhenNull() {

    DocumentRequestDTO doc = new DocumentRequestDTO();

    BasicData data = new BasicData();

    mapper.validateDocumentExpirationDate(doc, data);

    assertNull(data.getDescripcionDireccion());
}
10. cubrir validateCountryOfBirth
Java
@Test
void validateCountryOfBirthShouldSkipWhenNull() {

    PlaceOfBirthRequestDTO dto = new PlaceOfBirthRequestDTO();

    BasicData data = new BasicData();

    mapper.validateCountryOfBirth(dto, data);

    assertNull(data.getPaisNacimiento());
}
11. cubrir usualtMapper branches
Te faltan varios booleanos.
Java
@Test
void usualtMapperShouldReturnForeignTaxY() {

    String result = mapper.usualtMapper("ODS0000", "true");

    assertNotNull(result);
}

@Test
void usualtMapperShouldReturnForeignTaxN() {

    String result = mapper.usualtMapper("ODS0000", "false");

    assertNotNull(result);
}

@Test
void usualtMapperShouldHandleNull() {

    String result = mapper.usualtMapper(null, null);

    assertNull(result);
}
12. cubrir lambdas internos
Los lambdas lambda$0 y lambda$2 aparecen porque no ejecutaste streams con match positivo.
Los tests anteriores del 99999 ya cubren eso.
Resultado esperado
Con TODOS esos tests:
PatchProspectMapper
líneas: ~98-100%
branches: ~95-100%
ProspectMapper
líneas: ~90-95%
paquete mappers
90%
Eso elimina prácticamente todas las zonas rojas de las capturas.