Te dejo tests clase por clase, sin acoplar.
SessionTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        Session dto = new Session();

        dto.setUsuario("user");
        dto.setTerminal("term");
        dto.setHoraConexion("10:00");
        dto.setEntorno("N");
        dto.setPerfil("perfil");
        dto.setSucursal("0100");
        dto.setEntidad("0065");
        dto.setDiasRestantesCambioClave("29");
        dto.setFechaContable("2026-01-01");
        dto.setTurno("T");

        assertEquals("user", dto.getUsuario());
        assertEquals("term", dto.getTerminal());
        assertEquals("10:00", dto.getHoraConexion());
        assertEquals("N", dto.getEntorno());
        assertEquals("perfil", dto.getPerfil());
        assertEquals("0100", dto.getSucursal());
        assertEquals("0065", dto.getEntidad());
        assertEquals("29", dto.getDiasRestantesCambioClave());
        assertEquals("2026-01-01", dto.getFechaContable());
        assertEquals("T", dto.getTurno());

        Session built = Session.builder()
                .usuario("user")
                .terminal("term")
                .horaConexion("10:00")
                .entorno("N")
                .perfil("perfil")
                .sucursal("0100")
                .entidad("0065")
                .diasRestantesCambioClave("29")
                .fechaContable("2026-01-01")
                .turno("T")
                .build();

        assertEquals("user", built.getUsuario());

        Session allArgs = new Session("u", "t", "h", "e", "p", "s", "en", "d", "f", "tu");
        assertEquals("u", allArgs.getUsuario());
    }
}
TrxPersonHeaderTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrxPersonHeaderTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        Session session = new Session();
        TrxPersonHeader dto = new TrxPersonHeader();

        dto.setRutaServicio("route");
        dto.setSesion(session);
        dto.setFuncion("Intro");
        dto.setSecuencia(123);
        dto.setCanal("60");
        dto.setResultado("OK");

        assertEquals("route", dto.getRutaServicio());
        assertEquals(session, dto.getSesion());
        assertEquals("Intro", dto.getFuncion());
        assertEquals(123, dto.getSecuencia());
        assertEquals("60", dto.getCanal());
        assertEquals("OK", dto.getResultado());

        TrxPersonHeader built = TrxPersonHeader.builder()
                .rutaServicio("route")
                .sesion(session)
                .funcion("Intro")
                .secuencia(123)
                .canal("60")
                .resultado("OK")
                .build();

        assertEquals("route", built.getRutaServicio());

        TrxPersonHeader allArgs = new TrxPersonHeader("r", session, "f", 1, "c", "res");
        assertEquals("r", allArgs.getRutaServicio());
    }
}
ErrorTrxDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorTrxDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        ErrorTrxDTO dto = new ErrorTrxDTO();

        dto.setCodigo("001");
        dto.setMensaje("error");
        dto.setTransaccion("trx");

        assertEquals("001", dto.getCodigo());
        assertEquals("error", dto.getMensaje());
        assertEquals("trx", dto.getTransaccion());

        ErrorTrxDTO built = ErrorTrxDTO.builder()
                .codigo("002")
                .mensaje("mensaje")
                .transaccion("trx2")
                .build();

        assertEquals("002", built.getCodigo());

        ErrorTrxDTO allArgs = new ErrorTrxDTO("003", "msg", "trx3");
        assertEquals("003", allArgs.getCodigo());
    }
}
ErrorResponseTrxDTOTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTrxDTOTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        ErrorResponseTrxDTO dto = new ErrorResponseTrxDTO();
        dto.setErrores(errores);

        assertEquals(errores, dto.getErrores());

        ErrorResponseTrxDTO built = ErrorResponseTrxDTO.builder()
                .errores(errores)
                .build();

        assertEquals(errores, built.getErrores());

        ErrorResponseTrxDTO allArgs = new ErrorResponseTrxDTO(errores);
        assertEquals(errores, allArgs.getErrores());
    }
}
TrxBasicDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrxBasicDataTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        TrxPersonData personData = new TrxPersonData();

        TrxBasicData dto = new TrxBasicData();
        dto.setDatosBasicos(personData);
        dto.setNumPersona("123");

        assertEquals(personData, dto.getDatosBasicos());
        assertEquals("123", dto.getNumPersona());

        TrxBasicData built = TrxBasicData.builder()
                .datosBasicos(personData)
                .numPersona("456")
                .build();

        assertEquals("456", built.getNumPersona());

        TrxBasicData allArgs = new TrxBasicData(personData, "789");
        assertEquals("789", allArgs.getNumPersona());
    }
}
TrxResponseDataTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrxResponseDataTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        TrxBasicData basicData = new TrxBasicData();

        TrxResponseData dto = new TrxResponseData();
        dto.setDatosBasicos(basicData);

        assertEquals(basicData, dto.getDatosBasicos());

        TrxResponseData built = TrxResponseData.builder()
                .datosBasicos(basicData)
                .build();

        assertEquals(basicData, built.getDatosBasicos());

        TrxResponseData allArgs = new TrxResponseData(basicData);
        assertEquals(basicData, allArgs.getDatosBasicos());
    }
}
TrxPersonResponseTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.response;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic.TrxPersonHeader;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrxPersonResponseTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        TrxBasicData data = new TrxBasicData();
        TrxPersonHeader header = new TrxPersonHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        Object conexion = new Object();
        List<Object> avisos = List.of(new Object());
        List<ErrorTrxDTO> errores = List.of(new ErrorTrxDTO());

        TrxPersonResponse dto = new TrxPersonResponse();
        dto.setData(data);
        dto.setCabecera(header);
        dto.setAutorizacion(autorizacion);
        dto.setPaginacion(paginacion);
        dto.setAvisos(avisos);
        dto.setErrores(errores);
        dto.setConexion(conexion);
        dto.setOk(true);

        assertEquals(data, dto.getData());
        assertEquals(header, dto.getCabecera());
        assertEquals(autorizacion, dto.getAutorizacion());
        assertEquals(paginacion, dto.getPaginacion());
        assertEquals(avisos, dto.getAvisos());
        assertEquals(errores, dto.getErrores());
        assertEquals(conexion, dto.getConexion());
        assertTrue(dto.getOk());

        TrxPersonResponse built = TrxPersonResponse.builder()
                .data(data)
                .cabecera(header)
                .ok(true)
                .build();

        assertTrue(built.getOk());
    }
}
TrxPersonRequestTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request;

import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.generic.TrxPersonHeader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrxPersonRequestTest {

    @Test
    void shouldCoverGettersSettersBuilderConstructorsAndToString() {
        TrxPersonHeader header = new TrxPersonHeader();
        TrxPersonDataRequest data = new TrxPersonDataRequest();

        TrxPersonRequest dto = new TrxPersonRequest();
        dto.setCabecera(header);
        dto.setData(data);

        assertEquals(header, dto.getCabecera());
        assertEquals(data, dto.getData());
        assertTrue(dto.toString().contains("TrxPersonRequest"));

        TrxPersonRequest built = TrxPersonRequest.builder()
                .cabecera(header)
                .data(data)
                .build();

        assertEquals(header, built.getCabecera());

        TrxPersonRequest allArgs = new TrxPersonRequest(header, data);
        assertEquals(data, allArgs.getData());
    }
}
InternationalOperationsTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InternationalOperationsTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        InternationalOperations dto = new InternationalOperations();

        dto.setRealizaOperaMoneExtran("S");
        dto.setInversiones(true);
        dto.setGiros(true);
        dto.setCreditos(true);
        dto.setImportaciones(true);
        dto.setExportaciones(true);
        dto.setOtro(false);
        dto.setTieneProdMoneExtraje("N");
        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("1");
        dto.setTipdoc("CC");

        assertEquals("S", dto.getRealizaOperaMoneExtran());
        assertTrue(dto.getInversiones());
        assertTrue(dto.getGiros());
        assertTrue(dto.getCreditos());
        assertTrue(dto.getImportaciones());
        assertTrue(dto.getExportaciones());
        assertFalse(dto.getOtro());
        assertEquals("N", dto.getTieneProdMoneExtraje());
        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("CC", dto.getTipdoc());

        InternationalOperations built = InternationalOperations.builder()
                .realizaOperaMoneExtran("S")
                .inversiones(true)
                .numdoc("123")
                .build();

        assertEquals("S", built.getRealizaOperaMoneExtran());
    }
}
ReferencesTest.java
Java
package com.santander.bnc.bsn049.bncbsn049mscustomer.domain.host.person.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReferencesTest {

    @Test
    void shouldCoverGettersSettersBuilderAndConstructors() {
        References dto = new References();

        dto.setParentesco("PADRE");
        dto.setNombre("Juan");
        dto.setPrimerApellido("Perez");
        dto.setCiudad("05001");
        dto.setCiudadReferencia("Medellin");
        dto.setIndictivo("57");
        dto.setTelefono("3001234567");
        dto.setDireccion("Calle 1");
        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("1");
        dto.setSecref1("2");
        dto.setTipdoc("CC");

        assertEquals("PADRE", dto.getParentesco());
        assertEquals("Juan", dto.getNombre());
        assertEquals("Perez", dto.getPrimerApellido());
        assertEquals("05001", dto.getCiudad());
        assertEquals("Medellin", dto.getCiudadReferencia());
        assertEquals("57", dto.getIndictivo());
        assertEquals("3001234567", dto.getTelefono());
        assertEquals("Calle 1", dto.getDireccion());
        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("1", dto.getSecdoc());
        assertEquals("2", dto.getSecref1());
        assertEquals("CC", dto.getTipdoc());

        References built = References.builder()
                .nombre("Ana")
                .telefono("300")
                .build();

        assertEquals("Ana", built.getNombre());
    }
}
Para las clases muy grandes (BasicData, AdditionalInfo, ComplementaryInfo, EconomyActivity, FinancialInformation, IdentificationData, TrxPersonData, TrxPersonDataRequest) te recomiendo hacerlas con un solo test por clase usando setters/getters principales + builder, porque si pruebas todos los campos una por una el archivo queda enorme.