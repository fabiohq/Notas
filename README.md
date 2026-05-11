private void prepareSuccessfulPut(ContactPointsRequestDTO request, String estciv) {
    TrxPersonData trxPersonData = new TrxPersonData();
    trxPersonData.setDescripcionDireccion("DIRECCION2024-01-01");
    trxPersonData.setConper("CLI");
    trxPersonData.setUsualt("ABCNOOO");

    TrxBasicData trxBasicData = new TrxBasicData();
    trxBasicData.setDatosBasicos(trxPersonData);

    TrxPersonResponse trxResponse = new TrxPersonResponse();
    trxResponse.setData(trxBasicData);

    BasicData basicData = new BasicData();
    basicData.setDescripcionDireccion("DIRECCION");
    basicData.setUsualt("ABCNOOO");
    basicData.setEstciv(estciv);

    BasicData patchData = new BasicData();

    when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF3)))
            .thenReturn(trxResponse);

    when(mapper.pef3ResponseToPef2Request(trxPersonData))
            .thenReturn(basicData);

    when(mapper.contactPointPatchToPef2Request(request))
            .thenReturn(patchData);

    when(mapperUtils.getForeignTaxIndicator(trxPersonData))
            .thenReturn("NO");

    when(mapper.usualtMapper(any(), eq("NO")))
            .thenReturn("ABCNOOO");

    when(trxPersonService.callPostTRX(any(), eq(ClientEnum.PEF2)))
            .thenReturn(new TrxPersonResponse());
}



import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;