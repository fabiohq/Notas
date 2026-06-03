@Test
void shouldHandleOtherSpringExceptionsAsGeneric() throws Exception {
    assertEquals(HttpStatus.CONFLICT,
            handler.handleException(new MissingServletRequestParameterException("id", "String"))
                    .getStatusCode());

    assertEquals(HttpStatus.CONFLICT,
            handler.handleException(new RuntimeException("no resource"))
                    .getStatusCode());

    assertEquals(HttpStatus.CONFLICT,
            handler.handleException(new RuntimeException("client error"))
                    .getStatusCode());
}


