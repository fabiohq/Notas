@Test
void shouldCleanCreateCustomerDocumentFields() {
    CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();
    PersonDTO person = new PersonDTO();
    DocumentDTO document = new DocumentDTO();

    document.setDocumentTypeCode("CC");
    document.setDocumentNumber("123456");

    person.setDocument(document);
    request.setPerson(person);

    CreateCustomerRequestDTO result = CustomerMapperUtils.cleanFieldsFromProspectUpdate(request);

    assertNull(result.getPerson().getDocument().getDocumentTypeCode());
    assertNull(result.getPerson().getDocument().getDocumentNumber());
}

@Test
void shouldNotFailCleanCreateCustomerWhenPersonIsNull() {
    CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();

    CreateCustomerRequestDTO result = CustomerMapperUtils.cleanFieldsFromProspectUpdate(request);

    assertSame(request, result);
}

@Test
void shouldNotFailCleanCreateCustomerWhenDocumentIsNull() {
    CreateCustomerRequestDTO request = new CreateCustomerRequestDTO();
    PersonDTO person = new PersonDTO();
    request.setPerson(person);

    CreateCustomerRequestDTO result = CustomerMapperUtils.cleanFieldsFromProspectUpdate(request);

    assertSame(request, result);
}

@Test
void shouldCleanUpdateProspectDocumentFields() {
    UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();
    PersonDTO person = new PersonDTO();
    DocumentDTO document = new DocumentDTO();

    document.setDocumentTypeCode("CC");
    document.setDocumentNumber("123456");

    person.setDocument(document);
    request.setPerson(person);

    UpdateProspectRequestDTO result = CustomerMapperUtils.cleanFieldsCustomerProspectUpdate(request);

    assertNull(result.getPerson().getDocument().getDocumentTypeCode());
    assertNull(result.getPerson().getDocument().getDocumentNumber());
}

@Test
void shouldNotFailCleanUpdateProspectWhenPersonIsNull() {
    UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();

    UpdateProspectRequestDTO result = CustomerMapperUtils.cleanFieldsCustomerProspectUpdate(request);

    assertSame(request, result);
}

@Test
void shouldNotFailCleanUpdateProspectWhenDocumentIsNull() {
    UpdateProspectRequestDTO request = new UpdateProspectRequestDTO();
    PersonDTO person = new PersonDTO();
    request.setPerson(person);

    UpdateProspectRequestDTO result = CustomerMapperUtils.cleanFieldsCustomerProspectUpdate(request);

    assertSame(request, result);
}

@Test
void shouldReturnNoForeignTaxIndicatorWhenUsualtIsNull() {
    TrxPersonData personData = new TrxPersonData();

    assertEquals("NO", utils.getForeignTaxIndicator(personData));
}

@Test
void shouldReturnNoForeignTaxIndicatorWhenUsualtIsBlank() {
    TrxPersonData personData = new TrxPersonData();
    personData.setUsualt("");

    assertEquals("NO", utils.getForeignTaxIndicator(personData));
}

@Test
void shouldReturnNoForeignTaxIndicatorWhenUsualtIsShort() {
    TrxPersonData personData = new TrxPersonData();
    personData.setUsualt("ODS");

    assertEquals("NO", utils.getForeignTaxIndicator(personData));
}

@Test
void shouldBuildPostalAddressWithoutPremiseWhenDescriptionIsNull() {
    TrxPersonData personData = new TrxPersonData();
    personData.setNombreVia("Calle 1");
    personData.setTipoVia("CL");

    Parameters pa = new Parameters();
    pa.setStreetTypeDescription("Calle");
    pa.setCityStandard("05001");
    pa.setCountryDir("CO");
    pa.setCityDepartment("05");

    PostalAddressDTO result = CustomerMapperUtils.getPostalAddressBasic(personData, pa);

    assertEquals("Calle 1", result.getFullAddress());
    assertNull(result.getPremise());
}

@Test
void shouldBuildPostalAddressWithoutPremiseWhenDescriptionLessThanTen() {
    TrxPersonData personData = new TrxPersonData();
    personData.setDescripcionDireccion("ABC");

    Parameters pa = new Parameters();

    PostalAddressDTO result = CustomerMapperUtils.getPostalAddressBasic(personData, pa);

    assertNull(result.getPremise());
}

@Test
void shouldBuildDocumentWithoutExpirationWhenDescriptionIsNull() {
    TrxPersonData personData = new TrxPersonData();
    personData.setTipoIdentificacion("CC");
    personData.setNumeroIdentificacion("123");
    personData.setFechaExpedicion("2020-01-01");

    Parameters pa = new Parameters();
    pa.setTownDocument(null);

    DocumentDTO result = CustomerMapperUtils.getDocumentBasics(personData, pa);

    assertEquals("", result.getTown());
    assertNull(result.getExpirationDate());
}

@Test
void shouldBuildDocumentWithEmptyTownWhenTownNameIsNull() {
    TrxPersonData personData = new TrxPersonData();
    personData.setFechaExpedicion("2020-01-01");

    Parameters pa = new Parameters();
    pa.setTownDocument(new CodeNameDTO("05001", null));

    DocumentDTO result = CustomerMapperUtils.getDocumentBasics(personData, pa);

    assertEquals("", result.getTown());
}

@Test
void shouldReturnOtroWhenUsualtIsNull() {
    TrxPersonData personData = new TrxPersonData();

    assertEquals("OTRO", CustomerMapperUtils.getSourceCode(personData));
}

@Test
void shouldReturnOtroWhenUsualtIsBlank() {
    TrxPersonData personData = new TrxPersonData();
    personData.setUsualt("");

    assertEquals("OTRO", CustomerMapperUtils.getSourceCode(personData));
}