@Test
void shouldCoverRuntimeAndIOExceptionPrivateHandlers() throws Exception {
    assertNotNull(invoke("handle31RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertNotNull(invoke("handle13RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertNotNull(invoke("handle01RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertNotNull(invoke("handle02RuntimeException", RuntimeException.class, new RuntimeException("runtime")));
    assertNotNull(invoke("handle21RuntimeException", RuntimeException.class, new RuntimeException("runtime")));

    assertNotNull(invoke("handle31IOException"));
    assertNotNull(invoke("handle13IOException"));
    assertNotNull(invoke("handle01IOException"));
    assertNotNull(invoke("handle02IOException"));
    assertNotNull(invoke("handle21IOException"));
}
