@Test
void shouldBuildResponseEntityWithNullErrors() {
    ResponseEntity<ErrorResponseDTO> response =
            handler.buildResponseEntity(null, HttpStatus.BAD_REQUEST);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertNull(response.getBody().getErrors());
}