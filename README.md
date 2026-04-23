class ContractsUtilsTest {

    @Test
    void shouldCleanFormatNumberString() {
        String result = ContractsUtils.cleanFormatNumberString("1.234,56");
        assertEquals("123456", result);
    }

    @Test
    void shouldTransformSettlementConditionCode() {
        assertEquals("S", ContractsUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", ContractsUtils.settlementConditionCodeTransformation("X"));
    }

    @Test
    void shouldValidateSettlementConditionCode() {
        assertTrue(ContractsUtils.settlementConditionCodeValidation("C"));
        assertFalse(ContractsUtils.settlementConditionCodeValidation("X"));
    }

    @Test
    void shouldAddLeftZeros() {
        assertEquals("00123", ContractsUtils.addLeftWithZeros("123", 5));
        assertEquals("12345", ContractsUtils.addLeftWithZeros("12345", 5));
    }

    @Test
    void shouldValidateAccountIdType() {
        assertTrue(ContractsUtils.validateAccountIdType("CC"));
        assertTrue(ContractsUtils.validateAccountIdType("CA"));
        assertFalse(ContractsUtils.validateAccountIdType("XX"));
    }

    @Test
    void shouldValidateSettlementConcept() {
        assertTrue(ContractsUtils.validateSettlementConcept("BGMF"));
        assertTrue(ContractsUtils.validateSettlementConcept("REFT"));
        assertTrue(ContractsUtils.validateSettlementConcept("ITEA"));
        assertFalse(ContractsUtils.validateSettlementConcept("AAA"));
    }

    @Test
    void shouldValidateSettlementConceptTypeCode() {
        assertEquals("M", ContractsUtils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", ContractsUtils.validateSettlementConceptTypeCode("REFT"));
        assertEquals("C", ContractsUtils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldParseDouble() {
        assertEquals(1234.56, ContractsUtils.parseDouble("1.234,56"));
    }

    @Test
    void shouldConvertToLineaDecimal() {
        assertEquals("1234567890123.45",
                ContractsUtils.toLineaDecimal("123456789012345"));
    }

    @Test
    void shouldFormat15DigitNumber() {
        assertEquals("0,00", ContractsUtils.format15DigitNumber(null));
        assertEquals("0,00", ContractsUtils.format15DigitNumber(""));
        assertEquals("1,23", ContractsUtils.format15DigitNumber("123"));
    }

    @Test
    void shouldRemoveLeadingZeros() {
        assertEquals("123", ContractsUtils.removeLeadingZeros("000123"));
        assertEquals("0", ContractsUtils.removeLeadingZeros(null));
    }
}