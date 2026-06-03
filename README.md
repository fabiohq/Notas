@Test
void shouldCoverGenericExceptionPrivateHandlers() throws Exception {

    assertNotNull(
            invoke("handle31GenericException",
                    Exception.class,
                    new Exception("generic")));

    assertNotNull(
            invoke("handle13GenericException",
                    Exception.class,
                    new Exception("generic")));

    assertNotNull(
            invoke("handle01GenericException",
                    Exception.class,
                    new Exception("generic")));

    assertNotNull(
            invoke("handle02GenericException",
                    Exception.class,
                    new Exception("generic")));

    assertNotNull(
            invoke("handle21GenericException",
                    Exception.class,
                    new Exception("generic")));
}


