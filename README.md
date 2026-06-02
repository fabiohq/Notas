
@Test
void shouldCoverRenewableCapitalizedBlockedBranches() throws Exception {
    TrxBP13Response trx = mock(TrxBP13Response.class);
    TrxBP13DataResponse data = mock(TrxBP13DataResponse.class);

    when(trx.getData()).thenReturn(data);

    Placement placement = new Placement();

    when(data.getIndicadorONP()).thenReturn("S");
    invoke("setRenewable",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertTrue(placement.isRenewable());

    when(data.getIndicadorONP()).thenReturn("N");
    invoke("setRenewable",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertFalse(placement.isRenewable());

    when(data.getIndicadorGarantia()).thenReturn("S");
    invoke("setCapitalized",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertTrue(placement.isCapitalized());

    when(data.getIndicadorGarantia()).thenReturn("N");
    invoke("setCapitalized",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertFalse(placement.isCapitalized());

    when(data.getIndicadorBloqueo()).thenReturn("S");
    invoke("setBlocked",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertTrue(placement.isBlocked());

    when(data.getIndicadorBloqueo()).thenReturn("N");
    invoke("setBlocked",
            new Class<?>[] { TrxBP13Response.class, Placement.class },
            new Object[] { trx, placement });
    assertFalse(placement.isBlocked());
}
