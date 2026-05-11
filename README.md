@Test
void shouldValidatePostalAddressAndCompletePut() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    PostalAddressDTO postal = new PostalAddressDTO();
    postal.setStreetTypeCode("CL");
    postal.setFullAddress("CALLE 123");
    postal.setTown("11001");
    postal.setPremise("APT 101");
    request.setPostalAddress(postal);

    prepareSuccessfulPut(request, "");

    when(parameterApiService.getParameter(anyString(), any(), eq(headers)))
            .thenReturn(List.of(new DataListDTO("0008", "11001", "BOGOTA")));

    assertDoesNotThrow(() ->
            service.putCustomerContactPoint("12345678", "PRI001", request, headers)
    );

    verify(regexUtils, atLeastOnce()).validateRegex(any(), anyString(), anyString());
    verify(errorService, atLeastOnce()).isBlank(anyString(), anyString());
    verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF2));
}

@Test
void shouldValidatePostalAddressWithNullPremiseAndSetNoInformado() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    PostalAddressDTO postal = new PostalAddressDTO();
    postal.setFullAddress("CALLE 123");
    postal.setTown("11001");
    postal.setPremise(null);
    request.setPostalAddress(postal);

    prepareSuccessfulPut(request, "S");

    when(parameterApiService.getParameter(anyString(), any(), eq(headers)))
            .thenReturn(List.of(new DataListDTO("0008", "11001", "BOGOTA")));

    assertDoesNotThrow(() ->
            service.putCustomerContactPoint("12345678", "PRI001", request, headers)
    );

    assertEquals("NO INFORMADO", request.getPostalAddress().getPremise());
}

@Test
void shouldThrowWhenTownDoesNotExist() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    PostalAddressDTO postal = new PostalAddressDTO();
    postal.setTown("99999");
    request.setPostalAddress(postal);

    ServiceException serviceException =
            new ServiceException(HttpStatus.BAD_REQUEST, "invalid town");

    when(parameterApiService.getParameter(anyString(), any(), eq(headers)))
            .thenReturn(List.of(new DataListDTO("0008", "11001", "BOGOTA")));

    when(errorService.errorBuilder(any(), anyString(), any()))
            .thenReturn(serviceException);

    ServiceException exception = assertThrows(
            ServiceException.class,
            () -> service.putCustomerContactPoint("12345678", "PRI001", request, headers)
    );

    assertSame(serviceException, exception);
}

@Test
void shouldValidatePhoneAddressAndEmailAddress() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    PhoneAddressDTO phone = new PhoneAddressDTO();
    phone.setMobileNumber("3001234567");
    phone.setPhoneNumber("6011234567");
    phone.setInternationalCode("57");

    ElectronicAddressDTO email = new ElectronicAddressDTO();
    email.setEmailAddress("test@mail.com");

    request.setPhoneAddress(phone);
    request.setElectronicAddress(email);

    prepareSuccessfulPut(request, "C");

    assertDoesNotThrow(() ->
            service.putCustomerContactPoint("12345678", "PRI001", request, headers)
    );

    verify(regexUtils, atLeastOnce()).validateRegex(any(), anyString(), anyString());
    verify(errorService, atLeastOnce()).isBlank(anyString(), anyString());
    verify(trxPersonService).callPostTRX(any(), eq(ClientEnum.PEF2));
}

@Test
void shouldThrowWhenInvalidCustomerIdLengthInPut() {
    ContactPointsRequestDTO request = new ContactPointsRequestDTO();

    doThrow(new ServiceException(HttpStatus.BAD_REQUEST, "invalid"))
            .when(regexUtils)
            .validateRegex(any(), eq("123"), eq("customer_id"));

    assertThrows(
            ServiceException.class,
            () -> service.putCustomerContactPoint("123", "PRI001", request, headers)
    );
}

@Test
void shouldThrowWhenInvalidCustomerIdInGetDetails() {
    assertThrows(
            ServiceException.class,
            () -> service.getCustomerDetails("123ABC", headers)
    );

    assertThrows(
            ServiceException.class,
            () -> service.getCustomerDetails("123", headers)
    );
}