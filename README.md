// Dependencias sugeridas en pom.xml // junit-jupiter, mockito-junit-jupiter, spring-test, jackson-databind, jakarta-validation, spring-boot-starter-test

// ----------------------------------------------------------------------------- // src/test/java/.../application/ports/input/UserManagementInputPortTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.input;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.Mockito.;

import java.util.Optional;

import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.output.UserManagementOutputPort; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ExampleUserRecord;

@ExtendWith(MockitoExtension.class) class UserManagementInputPortTest {

@Mock
private UserManagementOutputPort outputPort;

@Test
void createUser_delegaEnOutputPort_yRetornaUsuario() {
    ExampleUserRecord expected = mock(ExampleUserRecord.class);
    when(outputPort.persistUser("Fabio", "fabio@test.com")).thenReturn(expected);

    UserManagementInputPort inputPort = new UserManagementInputPort(outputPort);

    ExampleUserRecord result = inputPort.createUser("Fabio", "fabio@test.com");

    assertSame(expected, result);
    verify(outputPort).persistUser("Fabio", "fabio@test.com");
    verifyNoMoreInteractions(outputPort);
}

@Test
void findById_delegaEnOutputPort_yRetornaOptionalConUsuario() {
    ExampleUserRecord user = mock(ExampleUserRecord.class);
    when(outputPort.findById(1L)).thenReturn(Optional.of(user));

    UserManagementInputPort inputPort = new UserManagementInputPort(outputPort);

    Optional<?> result = inputPort.findById(1L);

    assertTrue(result.isPresent());
    assertSame(user, result.get());
    verify(outputPort).findById(1L);
    verifyNoMoreInteractions(outputPort);
}

@Test
void findById_cuandoNoExiste_retornaOptionalVacio() {
    when(outputPort.findById(99L)).thenReturn(Optional.empty());

    UserManagementInputPort inputPort = new UserManagementInputPort(outputPort);

    Optional<?> result = inputPort.findById(99L);

    assertTrue(result.isEmpty());
    verify(outputPort).findById(99L);
    verifyNoMoreInteractions(outputPort);
}

}

// ----------------------------------------------------------------------------- // src/test/java/.../domain/entity/PlainBeansTest.java // Cubre POJOs con getters/setters y valores por defecto. // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList; import java.util.List;

import org.junit.jupiter.api.Test;

class PlainBeansTest {

@Test
void altairRequest_gettersSetters() {
    AltairRequest bean = new AltairRequest();
    DataRequestBean data = new DataRequestBean();
    CabeceraBean cabecera = new CabeceraBean();

    bean.setData(data);
    bean.setCabecera(cabecera);

    assertSame(data, bean.getData());
    assertSame(cabecera, bean.getCabecera());
}

@Test
void altairResponse_gettersSetters() {
    AltairResponse bean = new AltairResponse();
    DataResponseBean data = new DataResponseBean();
    CabeceraBean cabecera = new CabeceraBean();
    PaginacionBean paginacion = new PaginacionBean();
    List<MesageAltair> avisos = List.of(new MesageAltair());
    List<MesageAltair> errores = List.of(new MesageAltair());

    bean.setData(data);
    bean.setCabecera(cabecera);
    bean.setAutorizacion("auth");
    bean.setPaginacion(paginacion);
    bean.setAvisos(avisos);
    bean.setErrores(errores);
    bean.setConexion("conexion");
    bean.setOk("ok");

    assertSame(data, bean.getData());
    assertSame(cabecera, bean.getCabecera());
    assertEquals("auth", bean.getAutorizacion());
    assertSame(paginacion, bean.getPaginacion());
    assertSame(avisos, bean.getAvisos());
    assertSame(errores, bean.getErrores());
    assertEquals("conexion", bean.getConexion());
    assertEquals("ok", bean.getOk());
}

@Test
void basicDataBean_gettersSetters() {
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

@Test
void cabeceraBean_valoresPorDefecto_ySetters() {
    CabeceraBean bean = new CabeceraBean();
    assertEquals("ConsultaDatosBasicosPNatural", bean.getRutaServicio());
    assertEquals("Intro", bean.getFuncion());
    assertEquals(44204, bean.getSecuencia());
    assertEquals("TFC", bean.getCanal());

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

@Test
void dataRequestBean_gettersSetters() {
    DataRequestBean bean = new DataRequestBean();
    bean.setPENUMPE("p");
    bean.setTipoDocumento("CC");
    bean.setNumDocumento("123");
    bean.setNombre("Fabio");

    assertEquals("p", bean.getPENUMPE());
    assertEquals("CC", bean.getTipoDocumento());
    assertEquals("123", bean.getNumDocumento());
    assertEquals("Fabio", bean.getNombre());
}

@Test
void dataResponseBean_gettersSetters() {
    DataResponseBean bean = new DataResponseBean();
    BasicDataBean datos = new BasicDataBean();
    bean.setDatosBasicos(datos);
    assertSame(datos, bean.getDatosBasicos());
}

@Test
void documentDTO_constructoresBuilderGettersSetters() {
    DocumentDTO empty = new DocumentDTO();
    empty.setDocumentNumber("123");
    empty.setDocumentTypeCode("CC");
    assertEquals("123", empty.getDocumentNumber());
    assertEquals("CC", empty.getDocumentTypeCode());

    DocumentDTO allArgs = new DocumentDTO("456", "TI");
    assertEquals("456", allArgs.getDocumentNumber());
    assertEquals("TI", allArgs.getDocumentTypeCode());

    DocumentDTO built = DocumentDTO.builder().documentNumber("789").documentTypeCode("CE").build();
    assertEquals("789", built.getDocumentNumber());
    assertEquals("CE", built.getDocumentTypeCode());
}

@Test
void mesageAltair_gettersSetters() {
    MesageAltair bean = new MesageAltair();
    bean.setCodigo("001");
    bean.setMensaje("mensaje");
    bean.setTransaccion("tx");

    assertEquals("001", bean.getCodigo());
    assertEquals("mensaje", bean.getMensaje());
    assertEquals("tx", bean.getTransaccion());
}

@Test
void personDTO_yPersonNameDTO_constructoresBuilderGettersSetters() {
    PersonNameDTO name = new PersonNameDTO();
    name.setFullName("Fabio Hernandez");
    assertEquals("Fabio Hernandez", name.getFullName());

    PersonNameDTO builtName = PersonNameDTO.builder().fullName("Otro Nombre").build();
    assertEquals("Otro Nombre", builtName.getFullName());

    ArrayList<DocumentDTO> documents = new ArrayList<>();
    documents.add(DocumentDTO.builder().documentNumber("123").documentTypeCode("CC").build());

    PersonDTO person = new PersonDTO();
    person.setPersonName(name);
    person.setDocuments(documents);

    assertSame(name, person.getPersonName());
    assertSame(documents, person.getDocuments());

    PersonDTO built = PersonDTO.builder().personName(builtName).documents(documents).build();
    assertSame(builtName, built.getPersonName());
    assertSame(documents, built.getDocuments());
}

@Test
void sesionBean_valoresPorDefecto_ySetters() {
    SesionBean bean = new SesionBean();
    assertEquals("@NETE781", bean.getUsuario());
    assertEquals("N", bean.getEntorno());
    assertEquals("GCAJASTL", bean.getPerfil());
    assertEquals("0100", bean.getSucursal());
    assertEquals("0065", bean.getEntidad());
    assertEquals("29", bean.getDiasRestantesCambioClave());
    assertEquals("2022-02-10", bean.getFechaContable());

    bean.setUsuario("user");
    bean.setTerminal("terminal");
    bean.setHoraConexion("10:00");
    bean.setEntorno("P");
    bean.setPerfil("perfil");
    bean.setSucursal("0001");
    bean.setEntidad("0002");
    bean.setDiasRestantesCambioClave("1");
    bean.setFechaContable("2024-01-01");
    bean.setTurno("turno");

    assertEquals("user", bean.getUsuario());
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

@Test
void watchlistScreeningRequest_builderGetterSetter() {
    PersonDTO person = PersonDTO.builder()
            .personName(PersonNameDTO.builder().fullName("Fabio").build())
            .documents(new ArrayList<>())
            .build();

    WatchlistScreeningRequest request = WatchlistScreeningRequest.builder().person(person).build();
    assertSame(person, request.getPerson());

    WatchlistScreeningRequest empty = WatchlistScreeningRequest.builder().build();
    empty.setPerson(person);
    assertSame(person, empty.getPerson());
}

@Test
void paginacionBean_sePuedeInstanciar() {
    assertNotNull(new PaginacionBean());
}

}

// ----------------------------------------------------------------------------- // src/test/java/.../domain/entity/LombokDtoTest.java // Cubre DTOs con Lombok @Data/@Builder. // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LombokDtoTest {

@Test
void antiMoneyLaunderingDTO_dataBuilderConstructores() {
    List<RiskCategoryDTO> categories = List.of(new RiskCategoryDTO("C1", "Cat 1"));
    List<RiskSourceDTO> sources = List.of(new RiskSourceDTO("S1", "Source 1"));

    AntiMoneyLaunderingDTO dto = AntiMoneyLaunderingDTO.builder()
            .riskCategories(categories)
            .riskSources(sources)
            .build();

    assertEquals(categories, dto.getRiskCategories());
    assertEquals(sources, dto.getRiskSources());

    AntiMoneyLaunderingDTO dto2 = new AntiMoneyLaunderingDTO();
    dto2.setRiskCategories(categories);
    dto2.setRiskSources(sources);
    assertEquals(dto, dto2);
    assertEquals(dto.hashCode(), dto2.hashCode());
    assertTrue(dto.toString().contains("riskCategories"));

    AntiMoneyLaunderingDTO dto3 = new AntiMoneyLaunderingDTO(categories, sources);
    assertEquals(categories, dto3.getRiskCategories());
    assertEquals(sources, dto3.getRiskSources());
}

@Test
void errorDTO_builderAllArgs() {
    ErrorDTO dto = ErrorDTO.builder()
            .code("code")
            .level("level")
            .message("message")
            .description("description")
            .build();

    ErrorDTO dto2 = new ErrorDTO("code", "level", "message", "description");
    assertEquals(dto, dto2);
    assertEquals("code", dto.getCode());
    assertEquals("level", dto.getLevel());
    assertEquals("message", dto.getMessage());
    assertEquals("description", dto.getDescription());
}

@Test
void oneFccRequest_dataBuilder() {
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
void oneFccResponse_dataBuilder() {
    OneFccResponse dto = OneFccResponse.builder()
            .idDocument("123")
            .name("Fabio")
            .documentType("CC")
            .status("OK")
            .riskCategories(List.of("R1"))
            .riskSources(List.of("S1"))
            .build();

    assertEquals("123", dto.getIdDocument());
    assertEquals("Fabio", dto.getName());
    assertEquals("CC", dto.getDocumentType());
    assertEquals("OK", dto.getStatus());
    assertEquals(List.of("R1"), dto.getRiskCategories());
    assertEquals(List.of("S1"), dto.getRiskSources());
}

@Test
void oneFccTokenResponse_dataBuilder() {
    OneFccTokenResponse dto = OneFccTokenResponse.builder().jwtToken("a.b.c").build();
    assertEquals("a.b.c", dto.getJwtToken());
    dto.setJwtToken("x.y.z");
    assertEquals("x.y.z", dto.getJwtToken());
}

@Test
void resultRiskValidateDtos_dataBuilderConstructores() {
    ResultDTO result = ResultDTO.builder().result("Match found").build();
    assertEquals("Match found", result.getResult());

    ValidationResultDTO validation = ValidationResultDTO.builder().result(result).build();
    assertSame(result, validation.getResult());

    RiskCategoryDTO category = RiskCategoryDTO.builder()
            .riskCategoryCode("RC")
            .riskCategoryDescription("Risk")
            .build();
    assertEquals("RC", category.getRiskCategoryCode());
    assertEquals("Risk", category.getRiskCategoryDescription());

    RiskSourceDTO source = RiskSourceDTO.builder()
            .riskSourceCode("RS")
            .riskSourceDescription("Source")
            .build();
    assertEquals("RS", source.getRiskSourceCode());
    assertEquals("Source", source.getRiskSourceDescription());

    AntiMoneyLaunderingDTO aml = AntiMoneyLaunderingDTO.builder()
            .riskCategories(List.of(category))
            .riskSources(List.of(source))
            .build();

    ValidateStatusResponse response = ValidateStatusResponse.builder()
            .validationResult(validation)
            .antiMoneyLaundering(aml)
            .build();

    assertSame(validation, response.getValidationResult());
    assertSame(aml, response.getAntiMoneyLaundering());
}

}

// ----------------------------------------------------------------------------- // src/test/java/.../domain/exception/ServiceExceptionTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test; import org.springframework.http.HttpStatus;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ErrorDTO;

class ServiceExceptionTest {

@Test
void constructorStatusError_getters() {
    ErrorDTO error = ErrorDTO.builder().code("c").message("m").build();
    ServiceException ex = new ServiceException(HttpStatus.BAD_REQUEST, error);

    assertSame(error, ex.getError());
    assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
}

@Test
void builderAllArgs_yDataMethods() {
    ErrorDTO error = ErrorDTO.builder().code("c").build();
    ServiceException ex = ServiceException.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .error(error)
            .build();

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatus());
    assertSame(error, ex.getError());
    assertTrue(ex.toString().contains("status"));
}

}

// ----------------------------------------------------------------------------- // src/test/java/.../domain/utils/SanetizacionTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets; import java.util.Base64;

import org.junit.jupiter.api.Test;

class SanetizacionTest {

private static String b64(String value) {
    return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(value.getBytes(StandardCharsets.UTF_8));
}

@Test
void token_valido_retornaTokenRecodificado() {
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
void token_largoOConSegmentosInvalidos_lanzaIllegalArgumentException() {
    String tooLong = "a".repeat(4097) + ".b.c";
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token(tooLong));
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a.b"));
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a..c"));
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("a.b.c.d"));
}

@Test
void token_conCaracterNoPermitido_lanzaSecurityException() {
    assertThrows(SecurityException.class, () -> Sanetizacion.token("abc.def$.ghi"));
}

@Test
void token_segmentoNoBase64Url_lanzaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> Sanetizacion.token("abc.def.ghi"));
}

}

// ----------------------------------------------------------------------------- // src/test/java/.../domain/utils/RegexUtilsTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.utils;

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
    type.
