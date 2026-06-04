
@Test
void trxbp49ValidateShouldHandleEmptyResponse() throws Exception {
    TrxBP49Response response = new TrxBP49Response();

    java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO> errores =
            new java.util.ArrayList<>();

    response.setErrores(errores);
    response.setAvisos(new java.util.ArrayList<>());

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, response, new com.fasterxml.jackson.databind.ObjectMapper(), "{}", errors);

    assertEquals(0, errors.size());
}
