@Test
void shouldHandleMissingServletRequestParameterException() {
    MissingServletRequestParameterException exception =
            new MissingServletRequestParameterException("customerId", "String");

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("test-ms-P-F-9400", response.getBody().getErrors().get(0).getCode());
}

@Test
void shouldHandleMissingRequestHeaderException() throws Exception {
    Method method = GlobalExceptionHandlerTest.class
            .getDeclaredMethod("dummyMethod", String.class);

    MethodParameter methodParameter = new MethodParameter(method, 0);

    MissingRequestHeaderException exception =
            new MissingRequestHeaderException("x-client-id", methodParameter);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("test-ms-P-F-9400", response.getBody().getErrors().get(0).getCode());
}

@Test
void shouldHandleMethodArgumentNotValidException() throws Exception {
    Method method = GlobalExceptionHandlerTest.class
            .getDeclaredMethod("dummyMethod", String.class);

    MethodParameter methodParameter = new MethodParameter(method, 0);

    BeanPropertyBindingResult bindingResult =
            new BeanPropertyBindingResult(new Object(), "request");

    bindingResult.addError(
            new FieldError("request", "customerId", "must not be blank")
    );

    MethodArgumentNotValidException exception =
            new MethodArgumentNotValidException(methodParameter, bindingResult);

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("test-ms-P-F-9404", response.getBody().getErrors().get(0).getCode());
}

private void dummyMethod(String value) {
}
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.exception.error.ErrorResponseDTO;
