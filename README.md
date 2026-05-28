package com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049msprspctcntctpnt.domain.host.person.generic.TrxPersonHeader; import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonDataRequestTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    BasicData basicData = new BasicData();
    ComplementaryInfo complementaryInfo = new ComplementaryInfo();
    EconomyActivity economyActivity = new EconomyActivity();
    FinancialInformation financialInformation = new FinancialInformation();
    References references = new References();
    AdditionalInfo additionalInfo = new AdditionalInfo();
    InternationalOperations internationalOperations = new InternationalOperations();
    List<IdentificationData> identificationData = List.of(new IdentificationData());

    TrxPersonDataRequest dto = new TrxPersonDataRequest();
    dto.setpENUMPE("PENUMPE");
    dto.setTipoInmueble("CASA");
    dto.setDatosBasicos(basicData);
    dto.setInfComplementaria(complementaryInfo);
    dto.setActividadEconomica(economyActivity);
    dto.setInfFinanciera(financialInformation);
    dto.setReferencias(references);
    dto.setInfAdicional(additionalInfo);
    dto.setOperacionesInternacionales(internationalOperations);
    dto.setDocumentoCajero("DOC-CAJERO");
    dto.setTipoDocumento("CC");
    dto.setNumDocumento("123456789");
    dto.setNombre("Fabio");
    dto.setDatosIdentificacion(identificationData);

    assertEquals("PENUMPE", dto.getpENUMPE());
    assertEquals("CASA", dto.getTipoInmueble());
    assertSame(basicData, dto.getDatosBasicos());
    assertSame(complementaryInfo, dto.getInfComplementaria());
    assertSame(economyActivity, dto.getActividadEconomica());
    assertSame(financialInformation, dto.getInfFinanciera());
    assertSame(references, dto.getReferencias());
    assertSame(additionalInfo, dto.getInfAdicional());
    assertSame(internationalOperations, dto.getOperacionesInternacionales());
    assertEquals("DOC-CAJERO", dto.getDocumentoCajero());
    assertEquals("CC", dto.getTipoDocumento());
    assertEquals("123456789", dto.getNumDocumento());
    assertEquals("Fabio", dto.getNombre());
    assertEquals(identificationData, dto.getDatosIdentificacion());
}

@Test
void shouldCreateWithBuilder() {
    BasicData basicData = new BasicData();
    List<IdentificationData> identificationData = List.of(new IdentificationData());

    TrxPersonDataRequest dto = TrxPersonDataRequest.builder()
            .pENUMPE("PENUMPE")
            .tipoInmueble("CASA")
            .datosBasicos(basicData)
            .tipoDocumento("CC")
            .numDocumento("123456789")
            .nombre("Fabio")
            .datosIdentificacion(identificationData)
            .build();

    assertEquals("PENUMPE", dto.getpENUMPE());
    assertEquals("CASA", dto.getTipoInmueble());
    assertSame(basicData, dto.getDatosBasicos());
    assertEquals("CC", dto.getTipoDocumento());
    assertEquals("123456789", dto.getNumDocumento());
    assertEquals("Fabio", dto.getNombre());
    assertEquals(identificationData, dto.getDatosIdentificacion());
}

}

class TrxPersonRequestTest {

@Test
void shouldCreateWithNoArgsConstructorAndSetters() {
    TrxPersonHeader header = new TrxPersonHeader();
    TrxPersonDataRequest data = new TrxPersonDataRequest();

    TrxPersonRequest dto = new TrxPersonRequest();
    dto.setCabecera(header);
    dto.setData(data);

    assertSame(header, dto.getCabecera());
    assertSame(data, dto.getData());
}

@Test
void shouldCreateWithAllArgsConstructor() {
    TrxPersonHeader header = new TrxPersonHeader();
    TrxPersonDataRequest data = new TrxPersonDataRequest();

    TrxPersonRequest dto = new TrxPersonRequest(header, data);

    assertSame(header, dto.getCabecera());
    assertSame(data, dto.getData());
}

@Test
void shouldCreateWithBuilder() {
    TrxPersonHeader header = new TrxPersonHeader();
    TrxPersonDataRequest data = new TrxPersonDataRequest();

    TrxPersonRequest dto = TrxPersonRequest.builder()
            .cabecera(header)
            .data(data)
            .build();

    assertSame(header, dto.getCabecera());
    assertSame(data, dto.getData());
}

}
