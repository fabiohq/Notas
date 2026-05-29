import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Test
void shouldHandleNoResourceFoundException() {
    NoResourceFoundException exception =
            new NoResourceFoundException("GET", "/test");

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("test-ms-P-F-9404", response.getBody().getErrors().get(0).getCode());
}

@Test
void shouldHandleHttpMessageNotReadableException() {
    HttpMessageNotReadableException exception =
            new HttpMessageNotReadableException("Invalid body");

    ResponseEntity<ErrorResponseDTO> response =
            handler.handleValidationExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().getErrors().size());
    assertEquals("test-ms-P-T-9400", response.getBody().getErrors().get(0).getCode());
}
