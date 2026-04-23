import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.dto.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.dto.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.dto.BanksResponse;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorType;

@Test
void shouldFormat15DigitNumberWhenInputHasTwoDigits() {
    assertEquals("0,12", ContractsUtils.format15DigitNumber("12"));
}

@Test
void shouldReturnCWhenSettlementConceptTypeCodeIsUnknown() {
    assertEquals("C", ContractsUtils.validateSettlementConceptTypeCode("XXXX"));
}

@Test
void shouldReturnInputWhenBankExists() {
    BanksParametersRequest request = new BanksParametersRequest();

    BanksParametersDTO dto = new BanksParametersDTO();
    dto.setBankId("0065");

    BanksResponse response = new BanksResponse();
    response.setBanks(List.of(dto));

    when(banksService.banksResponse(request)).thenReturn(response);

    String result = contractsUtils.bankValidation(request, "0065");

    assertEquals("0065", result);
}

@Test
void shouldThrowWhenBankIsNotFound() {
    BanksParametersRequest request = new BanksParametersRequest();

    BanksParametersDTO dto = new BanksParametersDTO();
    dto.setBankId("9999");

    BanksResponse response = new BanksResponse();
    response.setBanks(List.of(dto));

    ServiceException expected = new ServiceException(
            HttpStatus.NOT_FOUND,
            ErrorDTO.builder()
                    .code("COD-004")
                    .message("bankid not found")
                    .level("ERROR")
                    .description("desc")
                    .build()
    );

    when(banksService.banksResponse(request)).thenReturn(response);
    when(errorService.getGeneral()).thenReturn(new java.util.HashMap<>() {{
        put("bankid_not_found", "bankid not found");
    }});
    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            eq("bankid not found"),
            eq(ErrorType.FUNCTIONAL)))
            .thenReturn(expected);

    ServiceException ex = assertThrows(
            ServiceException.class,
            () -> contractsUtils.bankValidation(request, "0065")
    );

    assertSame(expected, ex);
}