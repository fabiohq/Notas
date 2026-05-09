SessionTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void shouldCoverSession() {

        Session dto = new Session();

        dto.setUsuario("USER");
        dto.setTerminal("TERM");
        dto.setHoraConexion("10:00");
        dto.setEntorno("DEV");
        dto.setPerfil("ADMIN");
        dto.setSucursal("001");
        dto.setEntidad("BNC");
        dto.setDiasRestantesCambioClave("30");
        dto.setFechaContable("20240101");
        dto.setTurno("A");

        assertEquals("USER", dto.getUsuario());
        assertEquals("TERM", dto.getTerminal());
        assertEquals("10:00", dto.getHoraConexion());
        assertEquals("DEV", dto.getEntorno());
        assertEquals("ADMIN", dto.getPerfil());
        assertEquals("001", dto.getSucursal());
        assertEquals("BNC", dto.getEntidad());
        assertEquals("30", dto.getDiasRestantesCambioClave());
        assertEquals("20240101", dto.getFechaContable());
        assertEquals("A", dto.getTurno());

        Session builder = Session.builder()
                .usuario("USER")
                .terminal("TERM")
                .horaConexion("10:00")
                .entorno("DEV")
                .perfil("ADMIN")
                .sucursal("001")
                .entidad("BNC")
                .diasRestantesCambioClave("30")
                .fechaContable("20240101")
                .turno("A")
                .build();

        assertEquals("USER", builder.getUsuario());

        Session allArgs = new Session(
                "USER", "TERM", "10:00", "DEV", "ADMIN",
                "001", "BNC", "30", "20240101", "A"
        );

        assertEquals("A", allArgs.getTurno());
    }
}
TrxPersonHeaderTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonHeaderTest {

    @Test
    void shouldCoverTrxPersonHeader() {

        Session session = new Session();

        TrxPersonHeader dto = new TrxPersonHeader();

        dto.setRutaServicio("ROUTE");
        dto.setSesion(session);
        dto.setFuncion("FUNC");
        dto.setSecuencia(1);
        dto.setCanal("WEB");
        dto.setResultado("OK");

        assertEquals("ROUTE", dto.getRutaServicio());
        assertSame(session, dto.getSesion());
        assertEquals("FUNC", dto.getFuncion());
        assertEquals(1, dto.getSecuencia());
        assertEquals("WEB", dto.getCanal());
        assertEquals("OK", dto.getResultado());

        TrxPersonHeader builder = TrxPersonHeader.builder()
                .rutaServicio("ROUTE")
                .sesion(session)
                .funcion("FUNC")
                .secuencia(1)
                .canal("WEB")
                .resultado("OK")
                .build();

        assertEquals("ROUTE", builder.getRutaServicio());

        TrxPersonHeader allArgs = new TrxPersonHeader(
                "ROUTE", session, "FUNC", 1, "WEB", "OK"
        );

        assertEquals("OK", allArgs.getResultado());
    }
}
AdditionalInfoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalInfoTest {

    @Test
    void shouldCoverAdditionalInfo() {

        AdditionalInfo dto = new AdditionalInfo();

        dto.setPaisResidenciaFiscal1("CO");
        dto.setPaisDescripcion("COLOMBIA");
        dto.setClasificacionFATCA("FATCA");
        dto.setValidacionFATCA(true);
        dto.setSalario(true);
        dto.setOtro(true);
        dto.setCanalVenta("WEB");
        dto.setNumdoc("123");

        assertEquals("CO", dto.getPaisResidenciaFiscal1());
        assertEquals("COLOMBIA", dto.getPaisDescripcion());
        assertEquals("FATCA", dto.getClasificacionFATCA());
        assertTrue(dto.getValidacionFATCA());
        assertTrue(dto.getSalario());
        assertTrue(dto.getOtro());
        assertEquals("WEB", dto.getCanalVenta());
        assertEquals("123", dto.getNumdoc());

        AdditionalInfo builder = AdditionalInfo.builder()
                .paisResidenciaFiscal1("CO")
                .paisDescripcion("COLOMBIA")
                .clasificacionFATCA("FATCA")
                .validacionFATCA(true)
                .salario(true)
                .otro(true)
                .canalVenta("WEB")
                .numdoc("123")
                .build();

        assertEquals("CO", builder.getPaisResidenciaFiscal1());

        AdditionalInfo allArgs = new AdditionalInfo(
                "CO", "COLOMBIA", "US", "USA",
                "FATCA", "CRS", true, true, true,
                true, true, true, true, true,
                true, "NIT1", "NIT2", "PRE",
                true, true, true, true, true,
                "WEB", "OF", "001", "NEG",
                "ACTIVE", "20240101", "123",
                "999", "1", "CC"
        );

        assertEquals("CC", allArgs.getTipdoc());
    }
}
BasicDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicDataTest {

    @Test
    void shouldCoverBasicData() {

        BasicData dto = new BasicData();

        dto.setTipoIdentificacion("CC");
        dto.setNumeroIdentificacion("123");
        dto.setNombre("JUAN");
        dto.setPrimerApellido("PEREZ");
        dto.setEmail("mail@test.com");
        dto.setAutorizoTelefono(true);
        dto.setAutorizacionEmail(true);
        dto.setTown("BOGOTA");

        assertEquals("CC", dto.getTipoIdentificacion());
        assertEquals("123", dto.getNumeroIdentificacion());
        assertEquals("JUAN", dto.getNombre());
        assertEquals("PEREZ", dto.getPrimerApellido());
        assertEquals("mail@test.com", dto.getEmail());
        assertTrue(dto.isAutorizoTelefono());
        assertTrue(dto.isAutorizacionEmail());
        assertEquals("BOGOTA", dto.getTown());

        BasicData builder = BasicData.builder()
                .tipoIdentificacion("CC")
                .numeroIdentificacion("123")
                .nombre("JUAN")
                .primerApellido("PEREZ")
                .email("mail@test.com")
                .autorizoTelefono(true)
                .autorizacionEmail(true)
                .town("BOGOTA")
                .build();

        assertEquals("CC", builder.getTipoIdentificacion());

        BasicData allArgs = new BasicData();

        allArgs.setTown("CALI");

        assertEquals("CALI", allArgs.getTown());
    }
}
ComplementaryInfoTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplementaryInfoTest {

    @Test
    void shouldCoverComplementaryInfo() {

        ComplementaryInfo dto = new ComplementaryInfo();

        dto.setEstadoCivil("S");
        dto.setTipoVivienda("CASA");
        dto.setEstrato("3");
        dto.setAnios("5");
        dto.setNivelEstudios("UNI");
        dto.setNumHijos("2");
        dto.setTipdoc("CC");

        assertEquals("S", dto.getEstadoCivil());
        assertEquals("CASA", dto.getTipoVivienda());
        assertEquals("3", dto.getEstrato());
        assertEquals("5", dto.getAnios());
        assertEquals("UNI", dto.getNivelEstudios());
        assertEquals("2", dto.getNumHijos());
        assertEquals("CC", dto.getTipdoc());

        ComplementaryInfo builder = ComplementaryInfo.builder()
                .estadoCivil("S")
                .tipoVivienda("CASA")
                .estrato("3")
                .anios("5")
                .nivelEstudios("UNI")
                .numHijos("2")
                .tipdoc("CC")
                .build();

        assertEquals("S", builder.getEstadoCivil());

        ComplementaryInfo allArgs = new ComplementaryInfo(
                "S", "CASA", "3", "5", "2",
                "1", "UNI", "3", "2", "H1",
                "H2", "BOG", "999", "1", "1",
                "1", "1", "1", "1", "1", "CC"
        );

        assertEquals("CC", allArgs.getTipdoc());
    }
}
EconomyActivityTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EconomyActivityTest {

    @Test
    void shouldCoverEconomyActivity() {

        EconomyActivity dto = new EconomyActivity();

        dto.setCargo("DEV");
        dto.setDescCargo("DEVELOPER");
        dto.setPais("CO");
        dto.setCiudad("BOG");
        dto.setTelefono("123");
        dto.setTipdoc("CC");

        assertEquals("DEV", dto.getCargo());
        assertEquals("DEVELOPER", dto.getDescCargo());
        assertEquals("CO", dto.getPais());
        assertEquals("BOG", dto.getCiudad());
        assertEquals("123", dto.getTelefono());
        assertEquals("CC", dto.getTipdoc());

        EconomyActivity builder = EconomyActivity.builder()
                .cargo("DEV")
                .descCargo("DEVELOPER")
                .pais("CO")
                .ciudad("BOG")
                .telefono("123")
                .tipdoc("CC")
                .build();

        assertEquals("DEV", builder.getCargo());

        EconomyActivity allArgs = new EconomyActivity(
                "DEV", "DEVELOPER", "CL", "DC",
                "FIX", "CO", "COL", "BOG", "BOGOTA",
                "ING", "INGENIERO", "ACT", "ACT DESC",
                "5", "2", "EMP", "900",
                "20240101", "20240102", "CALLE",
                "DIR", "+57", "123", "OP",
                "1", "999", "1", "CC"
        );

        assertEquals("CC", allArgs.getTipdoc());
    }
}
FinancialInformationTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinancialInformationTest {

    @Test
    void shouldCoverFinancialInformation() {

        FinancialInformation dto = new FinancialInformation();

        dto.setIngresosFijos(1000);
        dto.setOtrosIngresos1(200);
        dto.setTotalIngresos(1200);
        dto.setValorComercial("50000");
        dto.setSaldo("100");
        dto.setTipoInmueble("CASA");

        assertEquals(1000, dto.getIngresosFijos());
        assertEquals(200, dto.getOtrosIngresos1());
        assertEquals(1200, dto.getTotalIngresos());
        assertEquals("50000", dto.getValorComercial());
        assertEquals("100", dto.getSaldo());
        assertEquals("CASA", dto.getTipoInmueble());

        FinancialInformation builder = FinancialInformation.builder()
                .ingresosFijos(1000)
                .otrosIngresos1(200)
                .totalIngresos(1200)
                .valorComercial("50000")
                .saldo("100")
                .tipoInmueble("CASA")
                .build();

        assertEquals(1000, builder.getIngresosFijos());

        FinancialInformation allArgs = new FinancialInformation(
                1,2,3,4,5,6,7,8,9,10,11,12,
                "M1","M2","M3","VC1","VC2","VC3",
                "SH1","SH2","SH3","S1","S2",
                "123","999","1","CC","CASA"
        );

        assertEquals("CASA", allArgs.getTipoInmueble());
    }
}
IdentificationDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentificationDataTest {

    @Test
    void shouldCoverIdentificationData() {

        IdentificationData dto = new IdentificationData();

        dto.setNumeroDocumento("123");
        dto.setNumPersona("999");
        dto.setApellidos("PEREZ");
        dto.setTipoPersona("N");
        dto.setpETIPDO("CC");

        assertEquals("123", dto.getNumeroDocumento());
        assertEquals("999", dto.getNumPersona());
        assertEquals("PEREZ", dto.getApellidos());
        assertEquals("N", dto.getTipoPersona());
        assertEquals("CC", dto.getpETIPDO());

        IdentificationData builder = IdentificationData.builder()
                .numeroDocumento("123")
                .numPersona("999")
                .apellidos("PEREZ")
                .tipoPersona("N")
                .pETIPDO("CC")
                .build();

        assertEquals("123", builder.getNumeroDocumento());

        IdentificationData allArgs = new IdentificationData(
                "123","999","PEREZ","A","N",
                "1","1","20240101","H","A",
                "C","G","N3","N4","N5",
                "R","M","NCA","NLO","OBS",
                "PRI","RUT","SEC","SEG","CC","VI"
        );

        assertEquals("VI", allArgs.getpETIPVI());
    }
}
InternationalOperationsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalOperationsTest {

    @Test
    void shouldCoverInternationalOperations() {

        InternationalOperations dto = new InternationalOperations();

        dto.setRealizaOperaMoneExtran("YES");
        dto.setInversiones(true);
        dto.setGiros(true);
        dto.setTieneProdMoneExtraje("YES");
        dto.setTipdoc("CC");

        assertEquals("YES", dto.getRealizaOperaMoneExtran());
        assertTrue(dto.getInversiones());
        assertTrue(dto.getGiros());
        assertEquals("YES", dto.getTieneProdMoneExtraje());
        assertEquals("CC", dto.getTipdoc());

        InternationalOperations builder = InternationalOperations.builder()
                .realizaOperaMoneExtran("YES")
                .inversiones(true)
                .giros(true)
                .tieneProdMoneExtraje("YES")
                .tipdoc("CC")
                .build();

        assertEquals("YES", builder.getRealizaOperaMoneExtran());

        InternationalOperations allArgs = new InternationalOperations(
                "YES", true, true, true,
                true, true, true,
                "YES", "123", "999",
                "1", "CC"
        );

        assertEquals("CC", allArgs.getTipdoc());
    }
}
ReferencesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferencesTest {

    @Test
    void shouldCoverReferences() {

        References dto = new References();

        dto.setParentesco("PADRE");
        dto.setNombre("JUAN");
        dto.setTelefono("123");
        dto.setDireccion("DIR");
        dto.setTipdoc("CC");

        assertEquals("PADRE", dto.getParentesco());
        assertEquals("JUAN", dto.getNombre());
        assertEquals("123", dto.getTelefono());
        assertEquals("DIR", dto.getDireccion());
        assertEquals("CC", dto.getTipdoc());

        References builder = References.builder()
                .parentesco("PADRE")
                .nombre("JUAN")
                .telefono("123")
                .direccion("DIR")
                .tipdoc("CC")
                .build();

        assertEquals("PADRE", builder.getParentesco());

        References allArgs = new References(
                "PADRE", "JUAN", "PEREZ",
                "BOG", "BOGOTA", "+57",
                "123", "DIR", "1",
                "999", "1", "REF",
                "CC"
        );

        assertEquals("CC", allArgs.getTipdoc());
    }
}
TrxPersonDataRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonDataRequestTest {

    @Test
    void shouldCoverTrxPersonDataRequest() {

        BasicData basic = new BasicData();
        ComplementaryInfo comp = new ComplementaryInfo();
        EconomyActivity eco = new EconomyActivity();
        FinancialInformation fin = new FinancialInformation();
        References ref = new References();
        AdditionalInfo add = new AdditionalInfo();
        InternationalOperations inter = new InternationalOperations();

        List<IdentificationData> list =
                List.of(new IdentificationData());

        TrxPersonDataRequest dto = new TrxPersonDataRequest();

        dto.setPenumpe("999");
        dto.setTipoInmueble("CASA");
        dto.setDatosBasicos(basic);
        dto.setInfComplementaria(comp);
        dto.setActividadEconomica(eco);
        dto.setInfFinanciera(fin);
        dto.setReferencias(ref);
        dto.setInfAdicional(add);
        dto.setOperacionesInternacionales(inter);
        dto.setDocumentoCajero("123");
        dto.setTipoDocumento("CC");
        dto.setNumDocumento("999");
        dto.setNombre("JUAN");
        dto.setDatosIdentificacion(list);

        assertEquals("999", dto.getPenumpe());
        assertEquals("CASA", dto.getTipoInmueble());
        assertSame(basic, dto.getDatosBasicos());
        assertSame(comp, dto.getInfComplementaria());
        assertSame(eco, dto.getActividadEconomica());
        assertSame(fin, dto.getInfFinanciera());
        assertSame(ref, dto.getReferencias());
        assertSame(add, dto.getInfAdicional());
        assertSame(inter, dto.getOperacionesInternacionales());
        assertEquals("JUAN", dto.getNombre());
        assertSame(list, dto.getDatosIdentificacion());

        TrxPersonDataRequest builder = TrxPersonDataRequest.builder()
                .penumpe("999")
                .tipoInmueble("CASA")
                .nombre("JUAN")
                .datosIdentificacion(list)
                .build();

        assertEquals("999", builder.getPenumpe());
    }
}
TrxPersonRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic.TrxPersonHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonRequestTest {

    @Test
    void shouldCoverTrxPersonRequest() {

        TrxPersonHeader header = new TrxPersonHeader();
        TrxPersonDataRequest data = new TrxPersonDataRequest();

        TrxPersonRequest dto = new TrxPersonRequest();

        dto.setCabecera(header);
        dto.setData(data);

        assertSame(header, dto.getCabecera());
        assertSame(data, dto.getData());

        TrxPersonRequest builder = TrxPersonRequest.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertSame(header, builder.getCabecera());

        TrxPersonRequest allArgs =
                new TrxPersonRequest(header, data);

        assertSame(data, allArgs.getData());
    }
}
ErrorResponseTrxDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTrxDTOTest {

    @Test
    void shouldCoverErrorResponseTrxDTO() {

        List<String> errors = List.of("ERROR");

        ErrorResponseTrxDTO dto = new ErrorResponseTrxDTO();

        dto.setErrores(errors);

        assertSame(errors, dto.getErrores());

        ErrorResponseTrxDTO builder = ErrorResponseTrxDTO.builder()
                .errores(errors)
                .build();

        assertSame(errors, builder.getErrores());

        ErrorResponseTrxDTO allArgs =
                new ErrorResponseTrxDTO(errors);

        assertSame(errors, allArgs.getErrores());
    }
}
ErrorTrxDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTrxDTOTest {

    @Test
    void shouldCoverErrorTrxDTO() {

        ErrorTrxDTO dto = new ErrorTrxDTO();

        dto.setCodigo("001");
        dto.setMensaje("ERROR");
        dto.setTransaccion("TRX");

        assertEquals("001", dto.getCodigo());
        assertEquals("ERROR", dto.getMensaje());
        assertEquals("TRX", dto.getTransaccion());

        ErrorTrxDTO builder = ErrorTrxDTO.builder()
                .codigo("001")
                .mensaje("ERROR")
                .transaccion("TRX")
                .build();

        assertEquals("001", builder.getCodigo());

        ErrorTrxDTO allArgs =
                new ErrorTrxDTO("001", "ERROR", "TRX");

        assertEquals("TRX", allArgs.getTransaccion());
    }
}
TrxBasicDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxBasicDataTest {

    @Test
    void shouldCoverTrxBasicData() {

        TrxPersonData data = new TrxPersonData();

        TrxBasicData dto = new TrxBasicData();

        dto.setDatosBasicos(data);
        dto.setNumPersona("999");

        assertSame(data, dto.getDatosBasicos());
        assertEquals("999", dto.getNumPersona());

        TrxBasicData builder = TrxBasicData.builder()
                .datosBasicos(data)
                .numPersona("999")
                .build();

        assertEquals("999", builder.getNumPersona());

        TrxBasicData allArgs =
                new TrxBasicData(data, "999");

        assertEquals("999", allArgs.getNumPersona());
    }
}
TrxPersonDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonDataTest {

    @Test
    void shouldCoverTrxPersonData() {

        TrxPersonData dto = new TrxPersonData();

        dto.setTipoIdentificacion("CC");
        dto.setNumeroIdentificacion("123");
        dto.setNombre("JUAN");
        dto.setPrimerApellido("PEREZ");
        dto.setDomant(1);
        dto.setSeccel(2);
        dto.setTown("BOGOTA");

        assertEquals("CC", dto.getTipoIdentificacion());
        assertEquals("123", dto.getNumeroIdentificacion());
        assertEquals("JUAN", dto.getNombre());
        assertEquals("PEREZ", dto.getPrimerApellido());
        assertEquals(1, dto.getDomant());
        assertEquals(2, dto.getSeccel());
        assertEquals("BOGOTA", dto.getTown());

        TrxPersonData builder = TrxPersonData.builder()
                .tipoIdentificacion("CC")
                .numeroIdentificacion("123")
                .nombre("JUAN")
                .primerApellido("PEREZ")
                .domant(1)
                .seccel(2)
                .town("BOGOTA")
                .build();

        assertEquals("CC", builder.getTipoIdentificacion());
    }
}
TrxPersonResponseTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.generic.TrxPersonHeader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrxPersonResponseTest {

    @Test
    void shouldCoverTrxPersonResponse() {

        TrxBasicData data = new TrxBasicData();
        TrxPersonHeader header = new TrxPersonHeader();
        List<Object> avisos = List.of("WARN");
        List<ErrorTrxDTO> errores =
                List.of(new ErrorTrxDTO());

        TrxPersonResponse dto = new TrxPersonResponse();

        dto.setData(data);
        dto.setCabecera(header);
        dto.setAvisos(avisos);
        dto.setErrores(errores);
        dto.setOk(true);

        assertSame(data, dto.getData());
        assertSame(header, dto.getCabecera());
        assertSame(avisos, dto.getAvisos());
        assertSame(errores, dto.getErrores());
        assertTrue(dto.getOk());

        TrxPersonResponse builder = TrxPersonResponse.builder()
                .data(data)
                .cabecera(header)
                .avisos(avisos)
                .errores(errores)
                .ok(true)
                .build();

        assertTrue(builder.getOk());
    }
}
TrxResponseDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrxResponseDataTest {

    @Test
    void shouldCoverTrxResponseData() {

        TrxBasicData basicData = new TrxBasicData();

        TrxResponseData dto = new TrxResponseData();

        dto.setDatosBasicos(basicData);

        assertSame(basicData, dto.getDatosBasicos());

        TrxResponseData builder = TrxResponseData.builder()
                .datosBasicos(basicData)
                .build();

        assertSame(basicData, builder.getDatosBasicos());

        TrxResponseData allArgs =
                new TrxResponseData(basicData);

        assertSame(basicData, allArgs.getDatosBasicos());
    }
}