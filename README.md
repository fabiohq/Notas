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
void noArgsGettersSetters() {
    PersonNameDTO dto = new PersonNameDTO();

    dto.setFullName("Fabio Hernandez");

    assertEquals("Fabio Hernandez", dto.getFullName());
}

@Test
void allArgsConstructor() {
    PersonNameDTO dto = new PersonNameDTO("Fabio Hernandez");

    assertEquals("Fabio Hernandez", dto.getFullName());
}

@Test
void builder() {
    PersonNameDTO dto = PersonNameDTO.builder().fullName("Fabio Hernandez").build();

    assertEquals("Fabio Hernandez", dto.getFullName());
}

}

// ----------------------------------------------------------------------------- // 18) src/test/java/.../domain/entity/ResultDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultDTOTest {

@Test
void builderNoArgsAllArgsData() {
    ResultDTO dto = ResultDTO.builder().result("Match found").build();
    ResultDTO same = new ResultDTO();
    same.setResult("Match found");
    ResultDTO allArgs = new ResultDTO("Match found");

    assertEquals("Match found", dto.getResult());
    assertEquals(same, dto);
    assertEquals(allArgs, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("Match found"));
}

}

// ----------------------------------------------------------------------------- // 19) src/test/java/.../domain/entity/RiskCategoryDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RiskCategoryDTOTest {

@Test
void builderNoArgsAllArgsData() {
    RiskCategoryDTO dto = RiskCategoryDTO.builder()
            .riskCategoryCode("RC")
            .riskCategoryDescription("Descripcion")
            .build();

    RiskCategoryDTO same = new RiskCategoryDTO();
    same.setRiskCategoryCode("RC");
    same.setRiskCategoryDescription("Descripcion");

    RiskCategoryDTO allArgs = new RiskCategoryDTO("RC", "Descripcion");

    assertEquals("RC", dto.getRiskCategoryCode());
    assertEquals("Descripcion", dto.getRiskCategoryDescription());
    assertEquals(same, dto);
    assertEquals(allArgs, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("riskCategoryCode"));
}

}

// ----------------------------------------------------------------------------- // 20) src/test/java/.../domain/entity/RiskSourceDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RiskSourceDTOTest {

@Test
void builderNoArgsAllArgsData() {
    RiskSourceDTO dto = RiskSourceDTO.builder()
            .riskSourceCode("RS")
            .riskSourceDescription("Descripcion")
            .build();

    RiskSourceDTO same = new RiskSourceDTO();
    same.setRiskSourceCode("RS");
    same.setRiskSourceDescription("Descripcion");

    RiskSourceDTO allArgs = new RiskSourceDTO("RS", "Descripcion");

    assertEquals("RS", dto.getRiskSourceCode());
    assertEquals("Descripcion", dto.getRiskSourceDescription());
    assertEquals(same, dto);
    assertEquals(allArgs, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("riskSourceCode"));
}

}

// ----------------------------------------------------------------------------- // 21) src/test/java/.../domain/entity/SesionBeanTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SesionBeanTest {

@Test
void valoresPorDefecto_debenSerLosEsperados() {
    SesionBean bean = new SesionBean();

    assertEquals("@NETE781", bean.getUsuario());
    assertEquals("N", bean.getEntorno());
    assertEquals("GCAJASTL", bean.getPerfil());
    assertEquals("0100", bean.getSucursal());
    assertEquals("0065", bean.getEntidad());
    assertEquals("29", bean.getDiasRestantesCambioClave());
    assertEquals("2022-02-10", bean.getFechaContable());
}

@Test
void gettersSetters_debenCubrirTodosLosCampos() {
    SesionBean bean = new SesionBean();

    bean.setUsuario("usuario");
    bean.setTerminal("terminal");
    bean.setHoraConexion("10:00");
    bean.setEntorno("P");
    bean.setPerfil("perfil");
    bean.setSucursal("0001");
    bean.setEntidad("0002");
    bean.setDiasRestantesCambioClave("1");
    bean.setFechaContable("2024-01-01");
    bean.setTurno("turno");

    assertEquals("usuario", bean.getUsuario());
    assertEquals("terminal", bean.getTerminal());
    assertEquals("10:00", bean.getHoraConexion());
    assertEquals("P", bean.getEntorno());
    assertEquals("perfil", bean.getPerfil());
    assertEquals("0001", bean.getSucursal());
    assertEquals("0002", bean.getEntidad());
    assertEquals("1", bean.getDiasRestantesCambioClave());
    assertEquals("2024-01-01", bean.getFechaContable());
    assertEquals("turno", bean.getTurno());
}

}

// ----------------------------------------------------------------------------- // 22) src/test/java/.../domain/entity/ValidateStatusResponseTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateStatusResponseTest {

@Test
void builderNoArgsAllArgsData() {
    ValidationResultDTO validation = ValidationResultDTO.builder()
            .result(ResultDTO.builder().result("Match found").build())
            .build();
    AntiMoneyLaunderingDTO aml = AntiMoneyLaunderingDTO.builder().build();

    ValidateStatusResponse dto = ValidateStatusResponse.builder()
            .validationResult(validation)
            .antiMoneyLaundering(aml)
            .build();

    ValidateStatusResponse same = new ValidateStatusResponse();
    same.setValidationResult(validation);
    same.setAntiMoneyLaundering(aml);

    ValidateStatusResponse allArgs = new ValidateStatusResponse(validation, aml);

    assertSame(validation, dto.getValidationResult());
    assertSame(aml, dto.getAntiMoneyLaundering());
    assertEquals(same, dto);
    assertEquals(allArgs, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("validationResult"));
}

}

// ----------------------------------------------------------------------------- // 23) src/test/java/.../domain/entity/ValidationResultDTOTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationResultDTOTest {

@Test
void builderNoArgsAllArgsData() {
    ResultDTO result = ResultDTO.builder().result("Not Match found").build();

    ValidationResultDTO dto = ValidationResultDTO.builder().result(result).build();
    ValidationResultDTO same = new ValidationResultDTO();
    same.setResult(result);
    ValidationResultDTO allArgs = new ValidationResultDTO(result);

    assertSame(result, dto.getResult());
    assertEquals(same, dto);
    assertEquals(allArgs, dto);
    assertEquals(same.hashCode(), dto.hashCode());
    assertTrue(dto.toString().contains("result"));
}

}

// ----------------------------------------------------------------------------- // 24) src/test/java/.../domain/entity/WatchlistScreeningRequestTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class WatchlistScreeningRequestTest {

@Test
void builderGetterSetter() {
    PersonDTO person = PersonDTO.builder()
            .personName(PersonNameDTO.builder().fullName("Fabio Hernandez").build())
            .documents(new ArrayList<>())
            .build();

    WatchlistScreeningRequest request = WatchlistScreeningRequest.builder()
            .person(person)
            .build();

    assertSame(person, request.getPerson());

    WatchlistScreeningRequest empty = WatchlistScreeningRequest.builder().build();
    empty.setPerson(person);

    assertSame(person, empty.getPerson());
}

}

// ----------------------------------------------------------------------------- // 25) src/test/java/.../domain/exception/ServiceExceptionTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test; import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO;

class ServiceExceptionTest {

@Test
void constructorPersonalizado_debeAsignarStatusYError() {
    ErrorDTO error = ErrorDTO.builder().code("code").message("message").build();

    ServiceException exception = new ServiceException(HttpStatus.BAD_REQUEST, error);

    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    assertSame(error, exception.getError());
}

@Test
void builderAllArgsDataToString() {
    ErrorDTO error = ErrorDTO.builder().code("code").build();

    ServiceException exception = ServiceException.builder()
            .error(error)
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();

    assertSame(error, exception.getError());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    assertTrue(exception.toString().contains("status"));
}

}

// ----------------------------------------------------------------------------- // 26) src/test/java/.../domain/utils/ErrorTypeTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorTypeTest {

@Test
void enumValues_debenExistir() {
    assertEquals(ErrorType.FUNCTIONAL, ErrorType.valueOf("FUNCTIONAL"));
    assertEquals(ErrorType.TECHNICAL, ErrorType.valueOf("TECHNICAL"));
    assertArrayEquals(new ErrorType[] { ErrorType.FUNCTIONAL, ErrorType.TECHNICAL }, ErrorType.values());
}

}

// ----------------------------------------------------------------------------- // 27) src/test/java/.../domain/utils/RegexUtilsTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException;

class RegexUtilsTest {

private RegexUtils regexUtils;

@BeforeEach
void setUp() {
    regexUtils = new RegexUtils();
    ReflectionTestUtils.setField(regexUtils, "msName", "MS");
    ReflectionTestUtils.setField(regexUtils, "msVersion", "1.0");
    ReflectionTestUtils.setField(regexUtils, "level", "ERROR");
    ReflectionTestUtils.setField(regexUtils, "code", "REGEX");

    HashMap<String, String> type = new HashMap<>();
    type.put("only_numbers", "^[0-9]+$");
    type.put("only_numbers_error", "Solo numeros");
    type.put("text", "^[A-Za-z ]+$");
    regexUtils.setType(type);
}

@Test
void dataGeneratedMethods_gettersSetters() {
    HashMap<String, String> type = new HashMap<>();
    type.put("key", "value");
    regexUtils.setType(type);

    assertEquals(type, regexUtils.getType());
    assertTrue(regexUtils.toString().contains("type"));
}

@Test
void validateRegex_cuandoCumple_noLanza() {
    assertDoesNotThrow(() -> regexUtils.validateRegex("only_numbers", "123", "field"));
}

@Test
void validateRegex_cuandoNoCumple_lanzaServiceExceptionConMensajeConfigurado() {
    ServiceException exception = assertThrows(ServiceException.class,
            () -> regexUtils.validateRegex("only_numbers", "abc", "field"));

    assertEquals("MS-REGEX", exception.getError().getCode());
    assertEquals("ERROR", exception.getError().getLevel());
    assertEquals("'field': Solo numeros", exception.getError().getMessage());
    assertEquals("ms-1.0: 'field': Solo numeros", exception.getError().getDescription());
}

@Test
void validateRegex_cuandoNoTieneMensajeConfigurado_usaInvalidFormat() {
    ServiceException exception = assertThrows(ServiceException.class,
            () -> regexUtils.validateRegex("text", "123", "name"));

    assertEquals("'name': Invalid format", exception.getError().getMessage());
}

}

// ----------------------------------------------------------------------------- // 28) src/test/java/.../domain/utils/SanetizacionTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets; import java.util.Base64;

import org.junit.jupiter.api.Test;

class SanetizacionTest {

@Test
void token_valido_debeLimpiarYRecodificar() {
    String token = b64("header") + "." + b64("payload") + "." + b64("signature");

    String result = Sanetizacion.token(" \n" + token + "\r\t ");

    assertEquals(token, result);
}

@Test
void token_nuloOVacio_lanzaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token(null));
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("   "));
}

@Test
void token_conLongitudInvalida_lanzaIllegalArgumentException() {
    String token = "a".repeat(4097) + ".b.c";

    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token(token));
}

@Test
void token_conCantidadSegmentosInvalida_lanzaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a.b"));
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a.b.c.d"));
}

@Test
void token_conSegmentoVacio_lanzaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a..c"));
}

@Test
void token_conCaracteresNoPermitidos_lanzaSecurityException() {
    assertThrows(SecurityException.class, () -> Sanetizacion.token("abc.def$.ghi"));
}

@Test
void token_conBase64UrlInvalido_lanzaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("abc.def.ghi"));
}

private static String b64(String value) {
    return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(value.getBytes(StandardCharsets.UTF_8));
}

}

// ----------------------------------------------------------------------------- // 29) src/test/java/.../domain/service/WatchlistScreeningServiceImplTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.ArgumentMatchers.; import static org.mockito.Mockito.*;

import java.nio.charset.StandardCharsets; import java.util.ArrayList; import java.util.Base64; import java.util.List;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Spy; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.http.HttpEntity; import org.springframework.http.HttpMethod; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.test.util.ReflectionTestUtils; import org.springframework.web.client.HttpStatusCodeException; import org.springframework.web.client.RestTemplate;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.client.IAltairInformation; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.AltairRequest; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.AltairResponse; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.BasicDataBean; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.DataResponseBean; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.DocumentDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccRequest; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccResponse; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.OneFccTokenResponse; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.PersonDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.PersonNameDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ValidateStatusResponse; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.WatchlistScreeningRequest; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils.RegexUtils;

@ExtendWith(MockitoExtension.class) class WatchlistScreeningServiceImplTest {

@Mock private ErrorService errorService;
@Mock private RegexUtils regexUtils;
@Mock private RestTemplate restTemplate;
@Mock private IAltairInformation iAltairInformation;

@Spy
@InjectMocks
private WatchlistScreeningServiceImpl service;

@BeforeEach
void setUp() {
    ReflectionTestUtils.setField(service, "protocol", "http");
    ReflectionTestUtils.setField(service, "host", "localhost");
    ReflectionTestUtils.setField(service, "mqRoute", "route");
    ReflectionTestUtils.setField(service, "urlOneFcc", "http://onefcc");
    ReflectionTestUtils.setField(service, "userOneFcc", "user");
    ReflectionTestUtils.setField(service, "passOneFcc", "pass");
    ReflectionTestUtils.setField(service, "origenOneFcc", "APP");
}

@Test
void validateStatus_cuandoStatusOK_retornaNotMatchFound() throws Exception {
    WatchlistScreeningRequest request = request("123", "CC", "Fabio Hernandez");
    doReturn(altair("Fabio", "Hernandez", "Lopez", "CC", "2000-01-01"))
            .when(service).sendInformationAltair("123", "CC", null);
    doReturn(OneFccResponse.builder().status("OK").build())
            .when(service).sendInformationOneFccList(any(OneFccRequest.class));

    ValidateStatusResponse result = (ValidateStatusResponse) service.validateStatus(request);

    assertEquals("Not Match found", result.getValidationResult().getResult().getResult());
    assertNull(result.getAntiMoneyLaundering());
}

@Test
void validateStatus_cuandoStatusKOConRiesgosNoPep_retornaMatchFound() throws Exception {
    WatchlistScreeningRequest request = request("123", "CC", "Fabio Hernandez");
    doReturn(altair("Fabio", "Hernandez", null, "CC", "2000-01-01"))
            .when(service).sendInformationAltair("123", "CC", null);
    doReturn(OneFccResponse.builder()
            .status("KO")
            .riskCategories(List.of("SANCTIONS", "PEP"))
            .riskSources(List.of("OFAC", "PEPS LIST"))
            .build())
            .when(service).sendInformationOneFccList(any(OneFccRequest.class));

    ValidateStatusResponse result = (ValidateStatusResponse) service.validateStatus(request);

    assertEquals("Match found", result.getValidationResult().getResult().getResult());
    assertEquals(1, result.getAntiMoneyLaundering().getRiskCategories().size());
    assertEquals("SANCTIONS", result.getAntiMoneyLaundering().getRiskCategories().get(0).getRiskCategoryDescription());
    assertEquals(1, result.getAntiMoneyLaundering().getRiskSources().size());
    assertEquals("OFAC", result.getAntiMoneyLaundering().getRiskSources().get(0).getRiskSourceDescription());
}

@Test
void validateStatus_cuandoStatusKOSoloPep_retornaNotMatchFound() throws Exception {
    WatchlistScreeningRequest request = request("123", "CC", "Fabio Hernandez");
    doReturn(altair("Fabio", "Hernandez", "Lopez", "CC", "2000-01-01"))
            .when(service).sendInformationAltair("123", "CC", null);
    doReturn(OneFccResponse.builder()
            .status("KO")
            .riskCategories(List.of("PEP"))
            .riskSources(List.of("PEPS"))
            .build())
            .when(service).sendInformationOneFccList(any(OneFccRequest.class));

    ValidateStatusResponse result = (ValidateStatusResponse) service.validateStatus(request);

    assertEquals("Not Match found", result.getValidationResult().getResult().getResult());
    assertNull(result.getAntiMoneyLaundering());
}

@Test
void validateStatus_cuandoDocumentTypeCodeEsNull_noValidaTipoDocumento() throws Exception {
    WatchlistScreeningRequest request = request("123", null, "Fabio Hernandez");
    doReturn(altair("Fabio", null, null, "CC", "2000-01-01"))
            .when(service).sendInformationAltair("123", null, null);
    doReturn(OneFccResponse.builder().status("OK").build())
            .when(service).sendInformationOneFccList(any(OneFccRequest.class));

    service.validateStatus(request);

    verify(regexUtils, never()).validateRegex(eq("strict_char_length_2"), any(), any());
    verify(regexUtils, never()).validateRegex(eq("document_type_format"), any(), any());
}

@Test
void validateStatus_cuandoValidacionFalla_propagaServiceException() throws Exception {
    WatchlistScreeningRequest request = request("abc", "CC", "Fabio Hernandez");
    ServiceException exception = mock(ServiceException.class);
    doThrow(exception).when(regexUtils).validateRegex("only_numbers", "abc", "person.documents.documentNumber");

    ServiceException result = assertThrows(ServiceException.class, () -> service.validateStatus(request));

    assertSame(exception, result);
    verify(service, never()).sendInformationAltair(any(), any(), any());
}

@Test
void sendInformationAltair_cuandoClienteResponde_retornaRespuesta() {
    AltairResponse expected = altair("Fabio", "Hernandez", "Lopez", "CC", "2000-01-01");
    when(iAltairInformation.altairResponse(eq("route"), any(AltairRequest.class))).thenReturn(expected);

    AltairResponse result = service.sendInformationAltair("123", "CC", null);

    assertSame(expected, result);
    verify(iAltairInformation).altairResponse(eq("route"), argThat(req ->
            "123".equals(req.getData().getNumDocumento())
                    && "CC".equals(req.getData().getTipoDocumento())
                    && req.getCabecera().getSesion() != null));
}

@Test
void sendInformationAltair_cuandoClienteFalla_retornaObjetoVacio() {
    when(iAltairInformation.altairResponse(eq("route"), any(AltairRequest.class)))
            .thenThrow(new RuntimeException("boom"));

    AltairResponse result = service.sendInformationAltair("123", "CC", null);

    assertNotNull(result);
    assertNull(result.getData());
}

@Test
void sendInformationOneFccList_cuandoExchangeOk_retornaBody() {
    OneFccTokenResponse token = OneFccTokenResponse.builder().jwtToken(jwt()).build();
    OneFccResponse expected = OneFccResponse.builder().status("OK").build();

    when(restTemplate.exchange(eq("http://onefcc/login"), eq(HttpMethod.GET), any(HttpEntity.class), eq(OneFccTokenResponse.class)))
            .thenReturn(ResponseEntity.ok(token));
    when(restTemplate.exchange(eq("http://onefcc/onboarding"), eq(HttpMethod.POST), any(HttpEntity.class), eq(OneFccResponse.class)))
            .thenReturn(ResponseEntity.ok(expected));

    OneFccResponse result = service.sendInformationOneFccList(OneFccRequest.builder().idDocument("123").build());

    assertSame(expected, result);
}

@Test
void sendInformationOneFccList_cuandoHttpStatusCodeException_retornaNull() {
    when(restTemplate.exchange(eq("http://onefcc/login"), eq(HttpMethod.GET), any(HttpEntity.class), eq(OneFccTokenResponse.class)))
            .thenThrow(new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "unauthorized") {});

    OneFccResponse result = service.sendInformationOneFccList(OneFccRequest.builder().idDocument("123").build());

    assertNull(result);
}

@Test
void getOneFccToken_cuandoExchangeOk_retornaToken() {
    when(restTemplate.exchange(eq("http://onefcc/login"), eq(HttpMethod.GET), any(HttpEntity.class), eq(OneFccTokenResponse.class)))
            .thenReturn(ResponseEntity.ok(OneFccTokenResponse.builder().jwtToken(jwt()).build()));

    assertEquals(jwt(), service.getOneFccToken());
}

@Test
void getOneFccToken_cuandoBodyNull_lanzaIllegalArgumentException() {
    when(restTemplate.exchange(eq("http://onefcc/login"), eq(HttpMethod.GET), any(HttpEntity.class), eq(OneFccTokenResponse.class)))
            .thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    assertThrows(IllegalArgumentException.class, () -> service.getOneFccToken());
}

private WatchlistScreeningRequest request(String documentNumber, String documentType, String fullName) {
    ArrayList<DocumentDTO> documents = new ArrayList<>();
    documents.add(DocumentDTO.builder().documentNumber(documentNumber).documentTypeCode(documentType).build());
    return WatchlistScreeningRequest.builder()
            .person(PersonDTO.builder()
                    .personName(PersonNameDTO.builder().fullName(fullName).build())
                    .documents(documents)
                    .build())
            .build();
}

private AltairResponse altair(String nombre, String primerApellido, String segundoApellido,
                              String tipoIdentificacion, String fechaNacimiento) {
    BasicDataBean basic = new BasicDataBean();
    basic.setNombre(nombre);
    basic.setPrimerApellido(primerApellido);
    basic.setSegundoApellido(segundoApellido);
    basic.setTipoIdentificacion(tipoIdentificacion);
    basic.setFechaNacimiento(fechaNacimiento);

    DataResponseBean data = new DataResponseBean();
    data.setDatosBasicos(basic);

    AltairResponse response = new AltairResponse();
    response.setData(data);
    return response;
}

private String jwt() {
    return b64("header") + "." + b64("payload") + "." + b64("signature");
}

private String b64(String value) {
    return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(value.getBytes(StandardCharsets.UTF_8));
}

}

// ----------------------------------------------------------------------------- // 30) src/test/java/.../domain/service/ErrorServiceTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils.ErrorType;

class ErrorServiceTest {

private ErrorService service;

@BeforeEach
void setUp() {
    service = new ErrorService();
    service.setMsName("MS");
    service.setMsVersion("1.0");
    service.setLevel("ERROR");
    service.setFunctional("FUNC");
    service.setTechnical("TECH");
    service.invalidValue = "Valor invalido";
    service.blankData = "No puede estar vacio";

    HashMap<String, String> general = new HashMap<>();
    general.put("null", "No puede ser nulo");
    service.setGeneral(general);
}

@Test
void dataGeneratedMethods_gettersSettersToString() {
    assertEquals("MS", service.getMsName());
    assertEquals("1.0", service.getMsVersion());
    assertEquals("ERROR", service.getLevel());
    assertEquals("FUNC", service.getFunctional());
    assertEquals("TECH", service.getTechnical());
    assertEquals("No puede ser nulo", service.getGeneral().get("null"));
    assertTrue(service.toString().contains("msName"));
}

@Test
void serviceExceptionBuilder_funcional() {
    ServiceException exception = service.serviceExceptionBuilder(
            HttpStatus.BAD_REQUEST, "mensaje", ErrorType.FUNCTIONAL);

    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    assertEquals("MS-FUNC-9400", exception.getError().getCode());
    assertEquals("ERROR", exception.getError().getLevel());
    assertEquals("mensaje", exception.getError().getMessage());
    assertEquals("ms-1.0: mensaje", exception.getError().getDescription());
}

@Test
void errorBuilder_tecnico() {
    ErrorDTO error = service.errorBuilder(
            HttpStatus.INTERNAL_SERVER_ERROR, "fallo", ErrorType.TECHNICAL);

    assertEquals("MS-TECH-9500", error.getCode());
    assertEquals("ERROR", error.getLevel());
    assertEquals("fallo", error.getMessage());
    assertEquals("ms-1.0: fallo", error.getDescription());
}

@Test
void isBlank_cuandoBlank_lanzaServiceException() {
    ServiceException exception = assertThrows(ServiceException.class,
            () -> service.isBlank("   ", "field"));

    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    assertEquals("'field': No puede estar vacio", exception.getError().getMessage());
}

@Test
void isBlank_cuandoNoBlank_noLanza() {
    assertDoesNotThrow(() -> service.isBlank("valor", "field"));
}

@Test
void isNull_cuandoNull_lanzaServiceException() {
    ServiceException exception = assertThrows(ServiceException.class,
            () -> service.isNull(null, "field"));

    assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    assertEquals("'field': No puede ser nulo", exception.getError().getMessage());
}

@Test
void isNull_cuandoNoNull_noLanza() {
    assertDoesNotThrow(() -> service.isNull("valor", "field"));
}

}

// ----------------------------------------------------------------------------- // 31) src/test/java/.../observability/ExternalApisHealthIndicatorTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException; import java.net.InetSocketAddress; import java.util.List; import java.util.Map;

import org.junit.jupiter.api.AfterEach; import org.junit.jupiter.api.Test; import org.springframework.boot.actuate.health.Health; import org.springframework.boot.actuate.health.Status;

import com.sun.net.httpserver.HttpServer;

class ExternalApisHealthIndicatorTest {

private HttpServer server;

@AfterEach
void tearDown() {
    if (server != null) {
        server.stop(0);
    }
}

@Test
void health_cuandoApiCriticaEstaUp_retornaUp() throws IOException {
    int port = startServer(204);
    ExternalApisHealthProperties properties = properties("api", "http://localhost:" + port, true, List.of(204));

    Health health = new ExternalApisHealthIndicator(properties).health();

    assertEquals(Status.UP, health.getStatus());
    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api");
    assertEquals("UP", detail.get("status"));
    assertEquals(204, detail.get("httpStatus"));
    assertEquals(true, detail.get("critical"));
}

@Test
void health_cuandoApiCriticaEstaDown_retornaDown() throws IOException {
    int port = startServer(500);
    ExternalApisHealthProperties properties = properties("api", "http://localhost:" + port, true, null);

    Health health = new ExternalApisHealthIndicator(properties).health();

    assertEquals(Status.DOWN, health.getStatus());
    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api");
    assertEquals("DOWN", detail.get("status"));
    assertEquals(500, detail.get("httpStatus"));
}

@Test
void health_cuandoApiNoCriticaEstaDown_retornaUpConDetalleDown() {
    ExternalApisHealthProperties properties = properties("api", "http://localhost:1", false, null);

    Health health = new ExternalApisHealthIndicator(properties).health();

    assertEquals(Status.UP, health.getStatus());
    Map<?, ?> detail = (Map<?, ?>) health.getDetails().get("api");
    assertEquals("DOWN", detail.get("status"));
    assertNotNull(detail.get("error"));
}

private int startServer(int status) throws IOException {
    server = HttpServer.create(new InetSocketAddress(0), 0);
    server.createContext("/", exchange -> {
        exchange.sendResponseHeaders(status, -1);
        exchange.close();
    });
    server.start();
    return server.getAddress().getPort();
}

private ExternalApisHealthProperties properties(String name, String url, boolean critical, List<Integer> acceptedStatuses) {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();
    properties.setTimeoutMs(1000);

    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName(name);
    check.setUrl(url);
    check.setCritical(critical);
    check.setAcceptedStatuses(acceptedStatuses);
    properties.setChecks(List.of(check));

    return properties;
}

}

// ----------------------------------------------------------------------------- // 32) src/test/java/.../observability/ExternalApisHealthPropertiesTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExternalApisHealthPropertiesTest {

@Test
void properties_gettersSettersYValoresPorDefecto() {
    ExternalApisHealthProperties properties = new ExternalApisHealthProperties();

    assertEquals(2000, properties.getTimeoutMs());
    assertNotNull(properties.getChecks());
    assertTrue(properties.getChecks().isEmpty());

    properties.setTimeoutMs(500);

    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();
    check.setName("api");
    check.setUrl("http://localhost:8080/health");
    check.setCritical(false);
    check.setAcceptedStatuses(List.of(200, 204));

    properties.setChecks(List.of(check));

    assertEquals(500, properties.getTimeoutMs());
    assertEquals(1, properties.getChecks().size());
    assertEquals("api", check.getName());
    assertEquals("http://localhost:8080/health", check.getUrl());
    assertFalse(check.isCritical());
    assertEquals(List.of(200, 204), check.getAcceptedStatuses());
}

@Test
void apiCheck_valoresPorDefecto() {
    ExternalApisHealthProperties.ApiCheck check = new ExternalApisHealthProperties.ApiCheck();

    assertTrue(check.isCritical());
    assertNull(check.getAcceptedStatuses());
}

}
