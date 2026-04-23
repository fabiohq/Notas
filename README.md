@Test
void shouldReturnBadRequestDirectInvocation() {

    ContractsController controller =
        new ContractsController(termDepositService, trxSanbaService, objectMapper);

    ResponseEntity<?> response =
        controller.modifyAssociatedContract(
            "client",
            "auth",
            "   ",
            new ContractsServiceRequestDTO()
        );

    assertEquals(400, response.getStatusCodeValue());

    verify(termDepositService, never())
        .modifyAssociatedContract(any(), any());
}