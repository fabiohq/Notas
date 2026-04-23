@Test
void shouldModifyAssociatedContractSuccessfullyWhenRelationshipTypeCodeIsCA() {
    String contractId = "12345678901234567890";
    String nationalIdentification = "0065";
    String internalIdentification = "1234567890";
    String relationshipTypeCode = "CA";

    ContractsServiceRequestDTO request = mockRequest(
            nationalIdentification,
            internalIdentification,
            relationshipTypeCode
    );

    TrxBP21Response expectedResponse = mock(TrxBP21Response.class);

    when(contractsUtils.bankValidation(any(), eq(nationalIdentification)))
            .thenReturn(nationalIdentification);
    when(trxSanbaService.trxBP21(any()))
            .thenReturn(expectedResponse);

    contractsImpl.modifyAssociatedContract(contractId, request);

    verify(regexUtils).validateRegex("relationship_length", relationshipTypeCode, "relationshipTypeCode");
    verify(regexUtils).validateRegex("relationship_format", relationshipTypeCode, "relationshipTypeCode");
    verify(trxSanbaService).trxBP21(any());
}