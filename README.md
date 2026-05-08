@Test
void validateRegexShouldCoverLengthCases() {
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_8, "12345678", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.STRICT_LENGTH_11, "12345678901", "field"));
}

@Test
void validateRegexShouldCoverPhoneAndGenderCases() {
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.PHONE_FORMAT, "3001234567", "phone"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_FORMAT, "M", "gender"));
}

@Test
void validateRegexShouldCoverEmailCases() {
    ReflectionTestUtils.setField(regexUtils, "regexEmailBetween", "^.{5,80}$");
    ReflectionTestUtils.setField(regexUtils, "regexEmailLength", "^.{1,80}$");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatLeft", "^[A-Za-z0-9._%+-]+$");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatRight", "^[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatFirstChar", "^[A-Za-z0-9]$");

    ReflectionTestUtils.setField(regexUtils, "regexEmailLengthError", "email length");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatLeftError", "email left");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatRigthError", "email right");
    ReflectionTestUtils.setField(regexUtils, "regexEmailFormatFirtCharError", "email first char");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_BETWEEN, "test@mail.com", "email"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_LENGTH, "test@mail.com", "email"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_LEFT, "test", "email"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_RIGHT, "mail.com", "email"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.EMAIL_FORMAT_FIRST_CHAR, "t", "email"));
}

@Test
void validateRegexShouldCoverAddressCases() {
    ReflectionTestUtils.setField(regexUtils, "regexAdressLength", "^.{1,50}$");
    ReflectionTestUtils.setField(regexUtils, "regexAdressFormat", "^[A-Za-z0-9 ]+$");
    ReflectionTestUtils.setField(regexUtils, "regexAdressLengthError", "address length");
    ReflectionTestUtils.setField(regexUtils, "regexAdressFormatError", "address format");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_LENGTH, "Cra 10", "address"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ADDRESS_FORMAT, "Cra 10", "address"));
}

@Test
void validateRegexShouldCoverInternationalCodeCases() {
    ReflectionTestUtils.setField(regexUtils, "regexInternationaCodeFormat", "^\\d+$");
    ReflectionTestUtils.setField(regexUtils, "regexInternationaCodeLength", "^\\d{2,3}$");
    ReflectionTestUtils.setField(regexUtils, "regexInternationaCodeFormatError", "international format");
    ReflectionTestUtils.setField(regexUtils, "regexInternationaCodeLengthError", "international length");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_FORMAT, "57", "code"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.INTERNATIONAL_CODE_LENGTH, "57", "code"));
}

@Test
void validateRegexShouldCoverTextCases() {
    ReflectionTestUtils.setField(regexUtils, "regexText20Length", "^.{1,20}$");
    ReflectionTestUtils.setField(regexUtils, "regexText20Format", "^[\\p{L} ]+$");
    ReflectionTestUtils.setField(regexUtils, "regexText40Length", "^.{1,40}$");
    ReflectionTestUtils.setField(regexUtils, "regexText40Format", "^[\\p{L} ]+$");

    ReflectionTestUtils.setField(regexUtils, "regexText20LengthError", "text20 length");
    ReflectionTestUtils.setField(regexUtils, "regexText20FormatError", "text20 format");
    ReflectionTestUtils.setField(regexUtils, "regexText40LengthError", "text40 length");
    ReflectionTestUtils.setField(regexUtils, "regexText40FormatError", "text40 format");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_LENGTH, "Perez", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_20_FORMAT, "Pérez", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_LENGTH, "Juan Carlos", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.TEXT_40_FORMAT, "Juan Carlos", "field"));
}

@Test
void validateRegexShouldCoverCountryDateAndStateCases() {
    ReflectionTestUtils.setField(regexUtils, "regexCountryCodeLength", "^[A-Z]{2}$");
    ReflectionTestUtils.setField(regexUtils, "regexCountryCodeFormat", "^[A-Z]{2}$");
    ReflectionTestUtils.setField(regexUtils, "regexBirthdayDate", "^\\d{4}-\\d{2}-\\d{2}$");
    ReflectionTestUtils.setField(regexUtils, "regexIssueDateFormat", "^\\d{4}-\\d{2}-\\d{2}$");
    ReflectionTestUtils.setField(regexUtils, "regexGenderCodeLength", "^.{1}$");

    ReflectionTestUtils.setField(regexUtils, "regexCountryCodeLengthError", "country length");
    ReflectionTestUtils.setField(regexUtils, "regexCountryCodeFormatError", "country format");
    ReflectionTestUtils.setField(regexUtils, "regexBirthdayDateFormat", "birthday date");
    ReflectionTestUtils.setField(regexUtils, "regexIssueDateFormatError", "issue date");
    ReflectionTestUtils.setField(regexUtils, "regexGenderCodeLengthError", "gender length");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRY_LENGTH, "CO", "country"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.COUNTRT_FORMAT, "CO", "country"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.BIRTH_DAY_DATE_FORMAT, "2000-01-01", "date"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.ISSUE_DATE_FORMAT, "2020-01-01", "date"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.GENDER_CODE_LENGTH, "M", "gender"));
}

@Test
void validateRegexShouldCoverSecondLastNameAndTownCases() {
    ReflectionTestUtils.setField(regexUtils, "regexSecondLastNameFormat", "^[\\p{L} ]*$");
    ReflectionTestUtils.setField(regexUtils, "regexSecondLastNameCeFormat", "^[\\p{L} ]*$");
    ReflectionTestUtils.setField(regexUtils, "regexTownDescpritionFormat", "^[\\p{L} ]+$");
    ReflectionTestUtils.setField(regexUtils, "regexTownDescriptionLength", "^.{1,40}$");
    ReflectionTestUtils.setField(regexUtils, "regexTownCodeLength", "^\\d{5}$");
    ReflectionTestUtils.setField(regexUtils, "regexStateCodeFormat", "^\\d+$");
    ReflectionTestUtils.setField(regexUtils, "regexStateCodeLength", "^\\d{2}$");

    ReflectionTestUtils.setField(regexUtils, "regexSecondLastNameFormatError", "second last name");
    ReflectionTestUtils.setField(regexUtils, "regexSecondLastNameCeFormatError", "second last name ce");
    ReflectionTestUtils.setField(regexUtils, "regexTownDescpritionFormatError", "town format");
    ReflectionTestUtils.setField(regexUtils, "regexTownDescriptionLengthError", "town length");
    ReflectionTestUtils.setField(regexUtils, "regexTownCodeLengthError", "town code length");
    ReflectionTestUtils.setField(regexUtils, "regexStateCodeFormatError", "state format");
    ReflectionTestUtils.setField(regexUtils, "regexStateCodeLegnthError", "state length");

    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_FORMAT, "Gomez", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_CE_FORMAT, "Gomez", "field"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_FORMAT, "Medellin", "town"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_DESCRIPTION_LENGTH, "Medellin", "town"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_TOWN_CODE_LENGTH, "05001", "townCode"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_FORMAT, "05", "state"));
    assertDoesNotThrow(() -> regexUtils.validateRegex(RegexTypes.REGEX_STATE_CODE_LENGTH, "05", "state"));
}