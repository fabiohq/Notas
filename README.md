import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

import okhttp3.MediaType;
import okhttp3.ResponseBody;


private ResponseBody invalidJsonBody() {
    return ResponseBody.create(
            MediaType.parse("application/json"),
            "invalid-json"
    );
}





@Test
void shouldThrowTechnicalExceptionWhenBp31ErrorBodyCannotBeParsed() throws Exception {
    TrxBP31Request request = mock(TrxBP31Request.class);
    Call<TrxBP31Response> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP31TRX(
            request,
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "SBCDTTI01-ConsultaCDTDATTitular2654",
            "QCTFD"))
            .thenReturn(call);

    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP31(request));

    assertSame(expected, ex);
}

@Test
void shouldThrowTechnicalExceptionWhenPepfErrorBodyCannotBeParsed() throws Exception {
    TrxPEPFDataRequest request = mock(TrxPEPFDataRequest.class);
    Call<TrxPEPFDataResponse> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callPEPF(request, "PEPF", "PEPF", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxPEPF(request));

    assertSame(expected, ex);
}

@Test
void shouldThrowTechnicalExceptionWhenBp13ErrorBodyCannotBeParsed() throws Exception {
    TrxBP13Request request = mock(TrxBP13Request.class);
    Call<TrxBP13Response> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP13TRX(request, "consultaDatosIPF", "consultaDatosIPF", "QCTFD"))
            .thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP13(request));

    assertSame(expected, ex);
}

@Test
void shouldThrowTechnicalExceptionWhenBp01ErrorBodyCannotBeParsed() throws Exception {
    TrxBp01Request request = mock(TrxBp01Request.class);
    Call<TrxBp01Response> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP01(request, "BP01", "BP01", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP01(request));

    assertSame(expected, ex);
}

@Test
void shouldThrowTechnicalExceptionWhenBp02ErrorBodyCannotBeParsed() throws Exception {
    TrxBp02Request request = mock(TrxBp02Request.class);
    Call<TrxBp02Response> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP02(request, "BP02", "BP02", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP02(request));

    assertSame(expected, ex);
}

@Test
void shouldThrowTechnicalExceptionWhenBp49ErrorBodyCannotBeParsed() throws Exception {
    TrxBP49Request request = mock(TrxBP49Request.class);
    Call<TrxBP49Response> call = mock(Call.class);

    ServiceException expected = new ServiceException(HttpStatus.CONFLICT, "parse error");

    when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    when(trxSanbaAPI.callBP49(request, "BP49", "BP49", "QCTFD")).thenReturn(call);
    when(call.execute()).thenReturn(Response.error(400, invalidJsonBody()));

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.CONFLICT),
            anyString(),
            eq(ErrorType.TECHNICAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(ServiceException.class, () -> service.trxBP49(request));

    assertSame(expected, ex);
}