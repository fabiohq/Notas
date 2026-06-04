@Test
void trxbp49ValidateShouldHandleEmptyResponse() throws Exception {
    var err = new TrxBP49Response();
    err.setErrores(java.util.Collections.emptyList());
    err.setAvisos(java.util.Collections.emptyList());

    var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

    Method method = TrxSanbaServiceImpl.class.getDeclaredMethod(
            "trxbp49Validate",
            TrxBP49Response.class,
            com.fasterxml.jackson.databind.ObjectMapper.class,
            String.class,
            java.util.List.class);
    method.setAccessible(true);

    method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), "{}", errors);

    assertEquals(0, errors.size());
}
