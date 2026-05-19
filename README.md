/*

Base de pruebas unitarias desacopladas para microservicio Spring Boot

Proyecto: bnc-bsn049-msiam

Stack sugerido: JUnit 5 + Mockito + AssertJ

Objetivo:

Probar clase por clase.


Evitar levantar todo el contexto de Spring cuando no sea necesario.


No acoplar pruebas a base de datos, endpoints reales, cache real ni configuración externa. */



// ========================================================= // build.gradle / pom.xml - Dependencias sugeridas // =========================================================

/* Maven:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>Gradle:

testImplementation 'org.springframework.boot:spring-boot-starter-test' testImplementation 'org.mockito:mockito-junit-jupiter' */

// ========================================================= // 1. Test para clase Service // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.service;

import static org.assertj.core.api.Assertions.assertThat; import static org.assertj.core.api.Assertions.assertThatThrownBy; import static org.mockito.Mockito.verify; import static org.mockito.Mockito.when;

import com.santander.bnc.bsn049.bncbsn049msiam.repository.UserRepository; import com.santander.bnc.bsn049.bncbsn049msiam.domain.User; import com.santander.bnc.bsn049.bncbsn049msiam.exception.BusinessException; import java.util.Optional; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) class UserServiceTest {

@Mock
private UserRepository userRepository;

@InjectMocks
private UserService userService;

private User user;

@BeforeEach
void setUp() {
    user = new User();
    user.setId(1L);
    user.setUsername("fabio");
    user.setActive(true);
}

@Test
@DisplayName("Debe retornar usuario cuando existe por id")
void shouldReturnUserWhenExistsById() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User result = userService.findById(1L);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getUsername()).isEqualTo("fabio");
    verify(userRepository).findById(1L);
}

@Test
@DisplayName("Debe lanzar excepción cuando el usuario no existe")
void shouldThrowExceptionWhenUserDoesNotExist() {
    when(userRepository.findById(99L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> userService.findById(99L))
            .isInstanceOf(BusinessException.class);

    verify(userRepository).findById(99L);
}

}

// ========================================================= // 2. Test para clase Controller // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.controller;

import static org.mockito.Mockito.when; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.santander.bnc.bsn049.bncbsn049msiam.dto.UserResponse; import com.santander.bnc.bsn049.bncbsn049msiam.service.UserService; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class) class UserControllerTest {

@Autowired
private MockMvc mockMvc;

@MockBean
private UserService userService;

@Test
@DisplayName("Debe responder 200 cuando consulta usuario por id")
void shouldReturnOkWhenFindUserById() throws Exception {
    UserResponse response = new UserResponse();
    response.setId(1L);
    response.setUsername("fabio");
    response.setActive(true);

    when(userService.findUserResponseById(1L)).thenReturn(response);

    mockMvc.perform(get("/users/{id}", 1L)
                    .header("x-santander-client-id", "263ec146"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.username").value("fabio"))
            .andExpect(jsonPath("$.active").value(true));
}

}

// ========================================================= // 3. Test para clase Mapper // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.santander.bnc.bsn049.bncbsn049msiam.domain.User; import com.santander.bnc.bsn049.bncbsn049msiam.dto.UserResponse; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;

class UserMapperTest {

private final UserMapper userMapper = new UserMapper();

@Test
@DisplayName("Debe mapear entidad User a UserResponse")
void shouldMapUserToUserResponse() {
    User user = new User();
    user.setId(1L);
    user.setUsername("fabio");
    user.setActive(true);

    UserResponse result = userMapper.toResponse(user);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
    assertThat(result.getUsername()).isEqualTo("fabio");
    assertThat(result.isActive()).isTrue();
}

@Test
@DisplayName("Debe retornar null cuando la entidad es null")
void shouldReturnNullWhenUserIsNull() {
    UserResponse result = userMapper.toResponse(null);

    assertThat(result).isNull();
}

}

// ========================================================= // 4. Test para clase Repository sin acoplar a PostgreSQL real // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.santander.bnc.bsn049.bncbsn049msiam.domain.UserEntity; import java.util.Optional; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest; import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase; import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest @AutoConfigureTestDatabase(replace = Replace.ANY) class UserRepositoryTest {

@Autowired
private UserRepository userRepository;

@Test
@DisplayName("Debe encontrar usuario por username")
void shouldFindUserByUsername() {
    UserEntity entity = new UserEntity();
    entity.setUsername("fabio");
    entity.setActive(true);

    userRepository.save(entity);

    Optional<UserEntity> result = userRepository.findByUsername("fabio");

    assertThat(result).isPresent();
    assertThat(result.get().getUsername()).isEqualTo("fabio");
}

}

// ========================================================= // 5. Test para clase Connector / Client externo // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.connector;

import static org.assertj.core.api.Assertions.assertThat; import static org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.verify; import static org.mockito.Mockito.when;

import com.santander.bnc.bsn049.bncbsn049msiam.dto.PkmResponse; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class) class PkmConnectorTest {

@Mock
private RestTemplate restTemplate;

@InjectMocks
private PkmConnector pkmConnector;

@Test
@DisplayName("Debe consultar PKM sin llamar endpoint real")
void shouldCallPkmWithoutRealEndpoint() {
    PkmResponse expected = new PkmResponse();
    expected.setStatus("OK");

    when(restTemplate.getForObject(any(String.class), any(Class.class)))
            .thenReturn(expected);

    PkmResponse result = pkmConnector.getInformation("123");

    assertThat(result).isNotNull();
    assertThat(result.getStatus()).isEqualTo("OK");
    verify(restTemplate).getForObject(any(String.class), any(Class.class));
}

}

// ========================================================= // 6. Test para configuración de propiedades Auth // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.context.properties.EnableConfigurationProperties; import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class AuthPropertiesTest {

private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(TestConfig.class)
        .withPropertyValues(
                "auth.x-santander-client-id=263ec146",
                "auth.jwt.iss=CO_ODS",
                "auth.jwt.exp-claim-name=exp",
                "auth.jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A"
        );

@Test
@DisplayName("Debe cargar propiedades de autenticación")
void shouldLoadAuthProperties() {
    contextRunner.run(context -> {
        AuthProperties properties = context.getBean(AuthProperties.class);

        assertThat(properties.getXSantanderClientId()).isEqualTo("263ec146");
        assertThat(properties.getJwt().getIss()).isEqualTo("CO_ODS");
        assertThat(properties.getJwt().getExpClaimName()).isEqualTo("exp");
        assertThat(properties.getJwt().getPublicKey()).isNotBlank();
    });
}

@EnableConfigurationProperties(AuthProperties.class)
static class TestConfig {
}

}

// ========================================================= // 7. Test para configuración de cache Caffeine // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.boot.test.context.runner.ApplicationContextRunner; import org.springframework.cache.CacheManager;

class CacheConfigTest {

private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withUserConfiguration(CacheConfig.class)
        .withPropertyValues(
                "spring.cache.type=CAFFEINE",
                "spring.cache.caffeine.spec=expireAfterWrite=10m",
                "caffeine.allow-null-values=false"
        );

@Test
@DisplayName("Debe crear CacheManager con Caffeine")
void shouldCreateCaffeineCacheManager() {
    contextRunner.run(context -> {
        assertThat(context).hasSingleBean(CacheManager.class);
        assertThat(context.getBean(CacheManager.class)).isNotNull();
    });
}

}

// ========================================================= // 8. Test para clase de excepciones / handler global // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

@Test
@DisplayName("Debe convertir BusinessException en respuesta controlada")
void shouldHandleBusinessException() {
    BusinessException exception = new BusinessException("USER_NOT_FOUND", "Usuario no encontrado");

    ResponseEntity<ErrorResponse> response = handler.handleBusinessException(exception);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode().is4xxClientError()).isTrue();
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getCode()).isEqualTo("USER_NOT_FOUND");
    assertThat(response.getBody().getMessage()).isEqualTo("Usuario no encontrado");
}

}

// ========================================================= // 9. Test para validaciones de request DTO // =========================================================

package com.santander.bnc.bsn049.bncbsn049msiam.dto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation; import jakarta.validation.Validation; import jakarta.validation.Validator; import java.util.Set; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;

class UserRequestTest {

private Validator validator;

@BeforeEach
void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
}

@Test
@DisplayName("Debe fallar cuando username está vacío")
void shouldFailWhenUsernameIsBlank() {
    UserRequest request = new UserRequest();
    request.setUsername("");

    Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

    assertThat(violations).isNotEmpty();
}

@Test
@DisplayName("Debe pasar cuando request es válido")
void shouldPassWhenRequestIsValid() {
    UserRequest request = new UserRequest();
    request.setUsername("fabio");

    Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

    assertThat(violations).isEmpty();
}

}

// ========================================================= // 10. application-test.yml sugerido // =========================================================

/* spring: profiles: active: test datasource: url: jdbc:h2:mem:testdb;MODE=PostgreSQL;DB_CLOSE_DELAY=-1 driver-class-name: org.h2.Driver username: sa password: jpa: hibernate: ddl-auto: create-drop cache: type: simple

connectors: pkm-connector: pkm-endpoint: - http://localhost/mock

auth: x-santander-client-id: 263ec146 jwt: iss: CO_ODS public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A exp-claim-name: exp */

// ========================================================= // Reglas para mantener las pruebas desacopladas // =========================================================

/*

1. Services:

Usar @ExtendWith(MockitoExtension.class).

Mockear repositories, connectors y mappers externos.

No usar @SpringBootTest para lógica de negocio simple.



2. Controllers:

Usar @WebMvcTest(Controller.class).

Mockear servicios con @MockBean.

Validar status HTTP, headers y body JSON.



3. Repositories:

Usar @DataJpaTest.

No conectarse al PostgreSQL local real.

Usar H2 o Testcontainers si se necesita compatibilidad fuerte con PostgreSQL.



4. Connectors:

No llamar endpoints reales.

Mockear RestTemplate, WebClient, FeignClient o Camel ProducerTemplate.



5. Configurations:

Usar ApplicationContextRunner.

Probar solo que los beans se crean y las propiedades se cargan.



6. DTOs:

Probar validaciones con Validator.



7. Excepciones:

Probar handlers directamente o con @WebMvcTest si afectan endpoints. */



