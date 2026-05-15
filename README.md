// ============================================================================ // TESTS - infrastructure.adapters.input.rest // ============================================================================

// ----------------------------------------------------------------------------- // 1) src/test/java/.../infrastructure/adapters/input/rest/DatabaseControllerTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.infrastructure.adapters.input.rest;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.Mockito.;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException; import com.fasterxml.jackson.databind.ObjectMapper; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.input.UserManagementInputPort; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ExampleUserRecord; import com.santander.darwin.core.exceptions.NotFoundDarwinException;

@ExtendWith(MockitoExtension.class) class DatabaseControllerTest {

@Mock
private UserManagementInputPort userManagementInputPort;

@Mock
private ObjectMapper objectMapper;

@Mock
private ExampleUserRecord exampleUserRecord;

private DatabaseController controller;

@BeforeEach
void setUp() {
    controller = new DatabaseController(userManagementInputPort, objectMapper);
}

@Test
void createUser_debeRetornarUsuarioCreado() throws Exception {
    when(userManagementInputPort.createUser("Fabio", "fabio@test.com"))
            .thenReturn(exampleUserRecord);

    when(objectMapper.writeValueAsString(exampleUserRecord))
            .thenReturn("{\"name\":\"Fabio\"}");

    ExampleUserRecord result = controller.createUser("Fabio", "fabio@test.com");

    assertSame(exampleUserRecord, result);

    verify(userManagementInputPort)
            .createUser("Fabio", "fabio@test.com");

    verify(objectMapper)
            .writeValueAsString(exampleUserRecord);

    verifyNoMoreInteractions(userManagementInputPort, objectMapper);
}

@Test
void createUser_cuandoSerializacionFalla_debeRetornarUsuarioIgualmente() throws Exception {
    when(userManagementInputPort.createUser("Fabio", "fabio@test.com"))
            .thenReturn(exampleUserRecord);

    when(objectMapper.writeValueAsString(exampleUserRecord))
            .thenThrow(new JsonProcessingException("error") {
                private static final long serialVersionUID = 1L;
            });

    ExampleUserRecord result = controller.createUser("Fabio", "fabio@test.com");

    assertSame(exampleUserRecord, result);

    verify(userManagementInputPort)
            .createUser("Fabio", "fabio@test.com");

    verify(objectMapper)
            .writeValueAsString(exampleUserRecord);
}

@Test
void retrieveUser_cuandoExiste_debeRetornarUsuario() throws Exception {
    when(userManagementInputPort.findById(1L))
            .thenReturn(Optional.of(exampleUserRecord));

    when(objectMapper.writeValueAsString(exampleUserRecord))
            .thenReturn("{\"id\":1}");

    ExampleUserRecord result = controller.retrieveUser(1L);

    assertSame(exampleUserRecord, result);

    verify(userManagementInputPort)
            .findById(1L);

    verify(objectMapper)
            .writeValueAsString(exampleUserRecord);

    verifyNoMoreInteractions(userManagementInputPort, objectMapper);
}

@Test
void retrieveUser_cuandoSerializacionFalla_debeRetornarUsuario() throws Exception {
    when(userManagementInputPort.findById(1L))
            .thenReturn(Optional.of(exampleUserRecord));

    when(objectMapper.writeValueAsString(exampleUserRecord))
            .thenThrow(new JsonProcessingException("error") {
                private static final long serialVersionUID = 1L;
            });

    ExampleUserRecord result = controller.retrieveUser(1L);

    assertSame(exampleUserRecord, result);

    verify(userManagementInputPort)
            .findById(1L);

    verify(objectMapper)
            .writeValueAsString(exampleUserRecord);
}

@Test
void retrieveUser_cuandoNoExiste_debeLanzarNotFoundDarwinException() {
    when(userManagementInputPort.findById(99L))
            .thenReturn(Optional.empty());

    NotFoundDarwinException exception = assertThrows(
            NotFoundDarwinException.class,
            () -> controller.retrieveUser(99L));

    assertEquals("User not found", exception.getMessage());

    verify(userManagementInputPort)
            .findById(99L);

    verifyNoInteractions(objectMapper);
}

}

// ----------------------------------------------------------------------------- // 2) src/test/java/.../infrastructure/adapters/input/rest/WatchlistScreeningControllersTest.java // ----------------------------------------------------------------------------- package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.infrastructure.adapters.input.rest;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.Mockito.;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.DocumentDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.PersonDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.PersonNameDTO; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.WatchlistScreeningRequest; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException; import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service.WatchlistScreeningService;

@ExtendWith(MockitoExtension.class) class WatchlistScreeningControllersTest {

@Mock
private WatchlistScreeningService termDepositService;

@InjectMocks
private WatchlistScreeningControllers controller;

private WatchlistScreeningRequest request;

@BeforeEach
void setUp() {
    ArrayList<DocumentDTO> documents = new ArrayList<>();
    documents.add(DocumentDTO.builder()
            .documentNumber("123456")
            .documentTypeCode("CC")
            .build());

    request = WatchlistScreeningRequest.builder()
            .person(PersonDTO.builder()
                    .personName(PersonNameDTO.builder()
                            .fullName("Fabio Hernandez")
                            .build())
                    .documents(documents)
                    .build())
            .build();
}

@Test
void validateStatus_debeDelegarServicioYRetornarRespuesta() throws Exception {
    Object expected = new Object();

    when(termDepositService.validateStatus(request))
            .thenReturn(expected);

    Object result = controller.validateStatus(
            "Bearer token",
            "client-id",
            request);

    assertSame(expected, result);

    verify(termDepositService)
            .validateStatus(request);

    verifyNoMoreInteractions(termDepositService);
}

@Test
void validateStatus_cuandoServicioLanzaException_debePropagarla() throws Exception {
    ServiceException exception = mock(ServiceException.class);

    when(termDepositService.validateStatus(request))
            .thenThrow(exception);

    ServiceException result = assertThrows(
            ServiceException.class,
            () -> controller.validateStatus(
                    "Bearer token",
                    "client-id",
                    request));

    assertSame(exception, result);

    verify(termDepositService)
            .validateStatus(request);
}

}