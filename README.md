@Test
@DisplayName("Debe propagar excepción cuando el producto no existe")
void getParametersShouldThrowWhenProductDoesNotExist() {
    String invalidProductId = "999999";

    ServiceException expectedException =
            new ServiceException(HttpStatus.BAD_REQUEST, null);

    HashMap<String, String> general = new HashMap<>();
    general.put("product_not_found", "Producto no encontrado");

    when(errorService.getGeneral()).thenReturn(general);

    when(errorService.serviceExceptionBuilder(
            eq(HttpStatus.NOT_FOUND),
            eq("Producto no encontrado"),
            eq(ErrorType.FUNCTIONAL)
    )).thenReturn(expectedException);

    ServiceException actualException = assertThrows(
            ServiceException.class,
            () -> service.getParameters(invalidProductId, null, null)
    );

    assertSame(expectedException, actualException);

    verify(mapper, never()).getProposalsResponse();
    verify(mapper, never()).getListParametersHardCode();
    verifyNoInteractions(trxSanbaService);
}

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.exception.error.ErrorType;
