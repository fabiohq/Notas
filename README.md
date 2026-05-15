// ============================================================================ // Tests unitarios en el MISMO ORDEN de las clases enviadas // JUnit 5 + Mockito, clase por clase, sin SpringBootTest // ============================================================================

// ----------------------------------------------------------------------------- // 1) src/test/java/.../application/ports/input/UserManagementInputPortTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.input;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.Mockito.;

import java.util.Optional;

import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.output.UserManagementOutputPort; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ExampleUserRecord;

@ExtendWith(MockitoExtension.class) class UserManagementInputPortTest {

@Mock
private UserManagementOutputPort userManagementOutputPort;

@Test
void createUser_debeDelegarPersistUser() {
    ExampleUserRecord expected = mock(ExampleUserRecord.class);
    when(userManagementOutputPort.persistUser("Fabio", "fabio@test.com")).thenReturn(expected);

    UserManagementInputPort port = new UserManagementInputPort(userManagementOutputPort);

    ExampleUserRecord result = port.createUser("Fabio", "fabio@test.com");

    assertSame(expected, result);
    verify(userManagementOutputPort).persistUser("Fabio", "fabio@test.com");
    verifyNoMoreInteractions(userManagementOutputPort);
}

@Test
void findById_debeDelegarFindById_cuandoExiste() {
    ExampleUserRecord user = mock(ExampleUserRecord.class);
    when(userManagementOutputPort.findById(1L)).thenReturn(Optional.of(user));

    UserManagementInputPort port = new UserManagementInputPort(userManagementOutputPort);

    Optional result = port.findById(1L);

    assertTrue(result.isPresent());
    assertSame(user, result.get());
    verify(userManagementOutputPort).findById(1L);
    verifyNoMoreInteractions(userManagementOutputPort);
}

@Test
void findById_debeDelegarFindById_cuandoNoExiste() {
    when(userManagementOutputPort.findById(99L)).thenReturn(Optional.empty());

    UserManagementInputPort port = new UserManagementInputPort(userManagementOutputPort);

    Optional result = port.findById(99L);

    assertTrue(result.isEmpty());
    verify(userManagementOutputPort).findById(99L);
    verifyNoMoreInteractions(userManagementOutputPort);
}

}

// ----------------------------------------------------------------------------- // 2) src/test/java/.../domain/entity/AltairRequestTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AltairRequestTest {

@Test
void gettersSetters_debenCubrirDataYCabecera() {
    AltairRequest request = new AltairRequest();
    DataRequestBean data = new DataRequestBean();
    CabeceraBean cabecera = new CabeceraBean();

    request.setData(data);
    request.setCabecera(cabecera);

    assertSame(data, request.getData());
    assertSame(cabecera, request.getCabecera());
}

}

// ----------------------------------------------------------------------------- // 3) src/test/java/.../domain/entity/AltairResponseTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AltairResponseTest {

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    AltairResponse response = new AltairResponse();
    DataResponseBean data = new DataResponseBean();
    CabeceraBean cabecera = new CabeceraBean();
    PaginacionBean paginacion = new PaginacionBean();
    List<MesageAltair> avisos = List.of(new MesageAltair());
    List<MesageAltair> errores = List.of(new MesageAltair());

    response.setData(data);
    response.setCabecera(cabecera);
    response.setAutorizacion("autorizacion");
    response.setPaginacion(paginacion);
    response.setAvisos(avisos);
    response.setErrores(errores);
    response.setConexion("conexion");
    response.setOk("ok");

    assertSame(data, response.getData());
    assertSame(cabecera, response.getCabecera());
    assertEquals("autorizacion", response.getAutorizacion());
    assertSame(paginacion, response.getPaginacion());
    assertSame(avisos, response.getAvisos());
    assertSame(errores, response.getErrores());
    assertEquals("conexion", response.getConexion());
    assertEquals("ok", response.getOk());
}

}

// ----------------------------------------------------------------------------- // 4) src/test/java/.../domain/entity/AntiMoneyLaunderingDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AntiMoneyLaunderingDTOTest {

@Test
void builderGettersSettersConstructoresEqualsHashCodeToString() {
    List<RiskCategoryDTO> categories = List.of(new RiskCategoryDTO("C1", "Categoria"));
    List<RiskSourceDTO> sources = List.of(new RiskSourceDTO("S1", "Fuente"));

    AntiMoneyLaunderingDTO dto = AntiMoneyLaunderingDTO.builder()
            .riskCategories(categories)
            .riskSources(sources)
            .build();

    assertEquals(categories, dto.getRiskCategories());
    assertEquals(sources, dto.getRiskSources());

    AntiMoneyLaunderingDTO empty = new AntiMoneyLaunderingDTO();
    empty.setRiskCategories(categories);
    empty.setRiskSources(sources);

    assertEquals(dto, empty);
    assertEquals(dto.hashCode(), empty.hashCode());
    assertTrue(dto.toString().contains("riskCategories"));

    AntiMoneyLaunderingDTO allArgs = new AntiMoneyLaunderingDTO(categories, sources);
    assertEquals(categories, allArgs.getRiskCategories());
    assertEquals(sources, allArgs.getRiskSources());
}

}

// ----------------------------------------------------------------------------- // 5) src/test/java/.../domain/entity/BasicDataBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicDataBeanTest {

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    BasicDataBean bean = new BasicDataBean();

    bean.setTipoIdentificacion("CC");
    bean.setNumeroIdentificacion("123");
    bean.setNombre("Fabio");
    bean.setPrimerApellido("Hernandez");
    bean.setSegundoApellido("Lopez");
    bean.setFechaNacimiento("2000-01-01");
    bean.setSexo("M");
    bean.setCiudad("Bogota");
    bean.setNacionalidad("CO");
    bean.setNumper("999");

    assertEquals("CC", bean.getTipoIdentificacion());
    assertEquals("123", bean.getNumeroIdentificacion());
    assertEquals("Fabio", bean.getNombre());
    assertEquals("Hernandez", bean.getPrimerApellido());
    assertEquals("Lopez", bean.getSegundoApellido());
    assertEquals("2000-01-01", bean.getFechaNacimiento());
    assertEquals("M", bean.getSexo());
    assertEquals("Bogota", bean.getCiudad());
    assertEquals("CO", bean.getNacionalidad());
    assertEquals("999", bean.getNumper());
}

}

// ----------------------------------------------------------------------------- // 6) src/test/java/.../domain/entity/CabeceraBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CabeceraBeanTest {

@Test
void valoresPorDefecto_debenSerLosEsperados() {
    CabeceraBean bean = new CabeceraBean();

    assertEquals("ConsultaDatosBasicosPNatural", bean.getRutaServicio());
    assertEquals("Intro", bean.getFuncion());
    assertEquals(44204, bean.getSecuencia());
    assertEquals("TFC", bean.getCanal());
}

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    CabeceraBean bean = new CabeceraBean();
    SesionBean sesion = new SesionBean();

    bean.setRutaServicio("ruta");
    bean.setSesion(sesion);
    bean.setFuncion("funcion");
    bean.setSecuencia(1);
    bean.setCanal("WEB");

    assertEquals("ruta", bean.getRutaServicio());
    assertSame(sesion, bean.getSesion());
    assertEquals("funcion", bean.getFuncion());
    assertEquals(1, bean.getSecuencia());
    assertEquals("WEB", bean.getCanal());
}

}

// ----------------------------------------------------------------------------- // 7) src/test/java/.../domain/entity/DataRequestBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataRequestBeanTest {

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    DataRequestBean bean = new DataRequestBean();

    bean.setPENUMPE("penumpe");
    bean.setTipoDocumento("CC");
    bean.setNumDocumento("123");
    bean.setNombre("Fabio");

    assertEquals("penumpe", bean.getPENUMPE());
    assertEquals("CC", bean.getTipoDocumento());
    assertEquals("123", bean.getNumDocumento());
    assertEquals("Fabio", bean.getNombre());
}

}

// ----------------------------------------------------------------------------- // 8) src/test/java/.../domain/entity/DataResponseBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataResponseBeanTest {

@Test
void gettersSetters_debenCubrirDatosBasicos() {
    DataResponseBean bean = new DataResponseBean();
    BasicDataBean datosBasicos = new BasicDataBean();

    bean.setDatosBasicos(datosBasicos);

    assertSame(datosBasicos, bean.getDatosBasicos());
}

}

// ----------------------------------------------------------------------------- // 9) src/test/java/.../domain/entity/DocumentDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DocumentDTOTest {

@Test
void noArgsGettersSetters() {
    DocumentDTO dto = new DocumentDTO();

    dto.setDocumentNumber("123");
    dto.setDocumentTypeCode("CC");

    assertEquals("123", dto.getDocumentNumber());
    assertEquals("CC", dto.getDocumentTypeCode());
}

@Test
void allArgsConstructor() {
    DocumentDTO dto = new DocumentDTO("123", "CC");

    assertEquals("123", dto.getDocumentNumber());
    assertEquals("CC", dto.getDocumentTypeCode());
}

@Test
void builder() {
    DocumentDTO dto = DocumentDTO.builder()
            .documentNumber("123")
            .documentTypeCode("CC")
            .build();

    assertEquals("123", dto.getDocumentNumber());
    assertEquals("CC", dto.getDocumentTypeCode());
}

}

// ----------------------------------------------------------------------------- // 10) src/test/java/.../domain/entity/ErrorDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorDTOTest {

@Test
void builderAllArgsEqualsHashCodeToString() {
    ErrorDTO dto = ErrorDTO.builder()
            .code("code")
            .level("level")
            .message("message")
            .description("description")
            .build();

    ErrorDTO allArgs = new ErrorDTO("code", "level", "message", "description");

    assertEquals(allArgs, dto);
    assertEquals(allArgs.hashCode(), dto.hashCode());
    assertEquals("code", dto.getCode());
    assertEquals("level", dto.getLevel());
    assertEquals("message", dto.getMessage());
    assertEquals("description", dto.getDescription());
    assertTrue(dto.toString().contains("code"));
}

}

// ----------------------------------------------------------------------------- // 11) src/test/java/.../domain/entity/MesageAltairTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MesageAltairTest {

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    MesageAltair bean = new MesageAltair();

    bean.setCodigo("001");
    bean.setMensaje("mensaje");
    bean.setTransaccion("tx");

    assertEquals("001", bean.getCodigo());
    assertEquals("mensaje", bean.getMensaje());
    assertEquals("tx", bean.getTransaccion());
}

}

// ----------------------------------------------------------------------------- // 12) src/test/java/.../domain/entity/OneFccRequestTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OneFccRequestTest {

@Test
void builderDataToString() {
    OneFccRequest dto = OneFccRequest.builder()
            .idDocument("123")
            .name("Fabio")
            .documentType("CC")
            .doBOrEntityCreationDate("2000-01-01")
            .country("CO")
            .countryType("Country of Citizenship")
            .firstName("Fabio")
            .middleName("A")
            .firstSurname("Hernandez")
            .secondSurname("Lopez")
            .personType("I")
            .requester("APP")
            .build();

    assertEquals("123", dto.getIdDocument());
    assertEquals("Fabio", dto.getName());
    assertEquals("CC", dto.getDocumentType());
    assertEquals("2000-01-01", dto.getDoBOrEntityCreationDate());
    assertEquals("CO", dto.getCountry());
    assertEquals("Country of Citizenship", dto.getCountryType());
    assertEquals("Fabio", dto.getFirstName());
    assertEquals("A", dto.getMiddleName());
    assertEquals("Hernandez", dto.getFirstSurname());
    assertEquals("Lopez", dto.getSecondSurname());
    assertEquals("I", dto.getPersonType());
    assertEquals("APP", dto.getRequester());
    assertTrue(dto.toString().contains("idDocument"));
}

@Test
void settersDataEqualsHashCode() {
    OneFccRequest dto = OneFccRequest.builder().build();
    dto.setIdDocument("123");
    dto.setName("Fabio");

    OneFccRequest same = OneFccRequest.builder().idDocument("123").name("Fabio").build();

    assertEquals(same, dto);
    assertEquals(same.hashCode(), dto.hashCode());
}

}

// ----------------------------------------------------------------------------- // 13) src/test/java/.../domain/entity/OneFccResponseTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class OneFccResponseTest {

@Test
void builderDataSettersEqualsHashCodeToString() {
    OneFccResponse dto = OneFccResponse.builder()
            .idDocument("123")
            .name("Fabio")
            .documentType("CC")
            .status("OK")
            .riskCategories(List.of("R1"))
            .riskSources(List.of("S1"))
            .build();

    OneFccResponse same = OneFccResponse.builder().build();
    same.setIdDocument("123");
    same.setName("Fabio");
    same.setDocumentType("CC");
    same.setStatus("OK");
    same.setRiskCategories(List.of("R1"));
    same.setRiskSources(List.of("S1"));

    assertEquals(same, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertEquals("OK", dto.getStatus());
    assertTrue(dto.toString().contains("riskCategories"));
}

}

// ----------------------------------------------------------------------------- // 14) src/test/java/.../domain/entity/OneFccTokenResponseTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OneFccTokenResponseTest {

@Test
void builderDataSettersEqualsHashCodeToString() {
    OneFccTokenResponse dto = OneFccTokenResponse.builder().jwtToken("a.b.c").build();
    OneFccTokenResponse same = OneFccTokenResponse.builder().build();
    same.setJwtToken("a.b.c");

    assertEquals("a.b.c", dto.getJwtToken());
    assertEquals(same, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("jwtToken"));
}

}

// ----------------------------------------------------------------------------- // 15) src/test/java/.../domain/entity/PaginacionBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaginacionBeanTest {

@Test
void sePuedeInstanciar() {
    assertNotNull(new PaginacionBean());
}

}

// ----------------------------------------------------------------------------- // 16) src/test/java/.../domain/entity/PersonDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PersonDTOTest {

@Test
void noArgsGettersSetters() {
    PersonDTO dto = new PersonDTO();
    PersonNameDTO name = PersonNameDTO.builder().fullName("Fabio Hernandez").build();
    ArrayList<DocumentDTO> documents = new ArrayList<>();
    documents.add(DocumentDTO.builder().documentNumber("123").documentTypeCode("CC").build());

    dto.setPersonName(name);
    dto.setDocuments(documents);

    assertSame(name, dto.getPersonName());
    assertSame(documents, dto.getDocuments());
}

@Test
void allArgsConstructor() {
    PersonNameDTO name = PersonNameDTO.builder().fullName("Fabio Hernandez").build();
    ArrayList<DocumentDTO> documents = new ArrayList<>();

    PersonDTO dto = new PersonDTO(name, documents);

    assertSame(name, dto.getPersonName());
    assertSame(documents, dto.getDocuments());
}

@Test
void builder() {
    PersonNameDTO name = PersonNameDTO.builder().fullName("Fabio Hernandez").build();
    ArrayList<DocumentDTO> documents = new ArrayList<>();

    PersonDTO dto = PersonDTO.builder()
            .personName(name)
            .documents(documents)
            .build();

    assertSame(name, dto.getPersonName());
    assertSame(documents, dto.getDocuments());
}

}

// ----------------------------------------------------------------------------- // 17) src/test/java/.../domain/entity/PersonNameDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonNameDTOTest {

@Test
void noArgsGett