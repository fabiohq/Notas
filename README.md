private TrxBP13Response bp13() {
    TrxBP13Response trx = mock(TrxBP13Response.class, RETURNS_DEEP_STUBS);

    when(trx.getData().getImporteTipoTasa()).thenReturn("+000000001234");
    when(trx.getData().getNumDocumento()).thenReturn("123456789");
    when(trx.getData().getTipoDocumento()).thenReturn("CC");
    when(trx.getData().getTipoOperacion()).thenReturn("ING");
    when(trx.getData().getMotivoCancelacion()).thenReturn("NINGUNO");
    when(trx.getData().getLina1()).thenReturn("00010" + pad("123456789", 30) + "ING");
    when(trx.getData().getLina2()).thenReturn(pad("", 32) + "000000000123456" + pad("", 60));

    return trx;
}

private String pad(String value, int length) {
    String text = value == null ? "" : value;
    if (text.length() >= length) {
        return text.substring(0, length);
    }
    return text + " ".repeat(length - text.length());
}
