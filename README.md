import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

@Test
void shouldMapFromTrxPersonHeader() {
    TrxPersonHeader header = buildTrxPersonHeader();

    TrxBP49Request request = new TrxBP49Request(header);

    assertThat(request.getCabecera().getSecuencia()).isEqualTo(123);
    assertThat(request.getCabecera().getRutaServicio()).isEqualTo("BP49");
    assertThat(request.getCabecera().getFuncion()).isEqualTo("function");
    assertThat(request.getCabecera().getCanal()).isEqualTo("60");
    assertThat(request.getCabecera().getResultado()).isEqualTo("OK");

    assertThat(request.getCabecera().getSesion().getEntidad()).isEqualTo("0065");
    assertThat(request.getCabecera().getSesion().getEntorno()).isEqualTo("local");
    assertThat(request.getCabecera().getSesion().getFechaContable()).isEqualTo("2026-01-01");
    assertThat(request.getCabecera().getSesion().getHoraConexion()).isEqualTo("120000");
    assertThat(request.getCabecera().getSesion().getPerfil()).isEqualTo("profile");
    assertThat(request.getCabecera().getSesion().getSucursal()).isEqualTo("0060");
    assertThat(request.getCabecera().getSesion().getTerminal()).isEqualTo("terminal");
    assertThat(request.getCabecera().getSesion().getUsuario()).isEqualTo("user");
}

private TrxPersonHeader buildTrxPersonHeader() {
    TrxPersonHeader header = new TrxPersonHeader();
    com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session session =
            new com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session();

    session.setEntorno("local");
    session.setFechaContable("2026-01-01");
    session.setHoraConexion("120000");
    session.setPerfil("profile");
    session.setSucursal("0060");
    session.setTerminal("terminal");
    session.setUsuario("user");

    header.setSesion(session);
    header.setSecuencia(123);
    header.setRutaServicio("BP49");
    header.setFuncion("function");
    header.setCanal("60");
    header.setResultado("OK");

    return header;
}


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

@Test
void shouldMapFromTrxPersonHeader() {
    TrxPersonHeader header = buildTrxPersonHeader();

    PemfvRequest request = new PemfvRequest(header);

    assertThat(request.getCabecera().getSecuencia()).isEqualTo(123);
    assertThat(request.getCabecera().getRutaServicio()).isEqualTo("PEMFV");
    assertThat(request.getCabecera().getFuncion()).isEqualTo("function");
    assertThat(request.getCabecera().getCanal()).isEqualTo("60");
    assertThat(request.getCabecera().getResultado()).isEqualTo("OK");

    assertThat(request.getCabecera().getSesion().getEntidad()).isEqualTo("0065");
    assertThat(request.getCabecera().getSesion().getEntorno()).isEqualTo("local");
    assertThat(request.getCabecera().getSesion().getFechaContable()).isEqualTo("2026-01-01");
    assertThat(request.getCabecera().getSesion().getHoraConexion()).isEqualTo("120000");
    assertThat(request.getCabecera().getSesion().getPerfil()).isEqualTo("profile");
    assertThat(request.getCabecera().getSesion().getSucursal()).isEqualTo("0060");
    assertThat(request.getCabecera().getSesion().getTerminal()).isEqualTo("terminal");
    assertThat(request.getCabecera().getSesion().getUsuario()).isEqualTo("user");
}

private TrxPersonHeader buildTrxPersonHeader() {
    TrxPersonHeader header = new TrxPersonHeader();
    com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session session =
            new com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session();

    session.setEntorno("local");
    session.setFechaContable("2026-01-01");
    session.setHoraConexion("120000");
    session.setPerfil("profile");
    session.setSucursal("0060");
    session.setTerminal("terminal");
    session.setUsuario("user");

    header.setSesion(session);
    header.setSecuencia(123);
    header.setRutaServicio("PEMFV");
    header.setFuncion("function");
    header.setCanal("60");
    header.setResultado("OK");

    return header;
}
