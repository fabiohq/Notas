spring.profiles.active: local
---
darwin:
  region: boae
  suffix:
  app-name: bsn049
  logging:
    format: GLUONLOG
    gluon-log:
      company: bnc
      componentName: msiam
      componentId: CHANGEIT_CMPT_ID
      componentType: microservice
      appId: CHANGEIT_APP_ID
    entity: ESP
    paas-app-version: "6.1.0"
  core:
    exceptions:
      error-format: GLUON
  security:
    white-list:
      - /**
    connectors:
      pkm-connector:
        pkm-endpoint:
          - ${env.pkm-endpoint:localhost://}
    caffeine:
      # disable null values in cache for performance reasons
      allow-null-values: false

datasource.url: "jdbc:postgresql://localhost:5434/postgres?currentSchema=authentication"
datasource.driver-class-name: "org.postgresql.Driver"
datasource.username: "postgres"
datasource.password: "gady"

spring:
  application:
    name: bnc-bsn049-msiam
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m
  datasource:
    url: "${datasource.url}"
    username: "${datasource.username}"
    password: "${datasource.password}"
    driver-class-name: "${datasource.driver-class-name}"

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049msiam: DEBUG
    okhttp3: INFO
    root: WARN
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level [%X{traceId:-}, %X{spanId:-}] [${spring.application.name:}] %logger{36} - %ms%n"
management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
      group:
        readiness:
          include: readinessState,ping,db
        liveness:
          include: livenessState

springdoc:
  swagger-ui:
    disable-swagger-default-url: true
    path: /swagger-ui.html

server:
  max-http-request-header-size: 128KB
  forward-headers-strategy: framework
  shutdown: graceful
  address: 0.0.0.0
  port: 8083
camel:
  springboot:
    main-run-controller: true
auth:
  x-santander-client-id: 263ec146
  jwt:
    iss: CO_ODS
    public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvtR/wGjrtb5FZMt5rhMixKynE61Sz9curpgPIYUwk/js8hvc8UlIK4vUMEb0RusUIKrccy4k3seX1Da8RcXbUeEy1VAM2SS5bFCsB5FWoGQkPgomrRVLfNWwlIb9ekn1Gal7Y84NzoxW2uJ0849phJlI8fa1snPHL396ZnwqEDEryFmbJZbdNc4zIarEc2hOYM/GTWc9RP5h2BLEU6nUD5TU94PM5AY+18WoVUOPQZ4wdRdST1D7Fq/8+BYMlPuwZMHZO2N8zhkIJm+744jGBQ8yeHubHO8E+wtlu4fqmQZNA1WissqRIMRnmS7bjh8hgn006omWrVWVAthXTT73iQIDAQAB
    exp-claim-name: exp
	
	

package com.santander.bnc.bsn049.bncbsn049msiam.controllers;

 import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.bnc.bsn049.bncbsn049msiam.services.SecurityService;

import lombok.extern.slf4j.Slf4j;

 @RestController
 @Slf4j
 @RequestMapping("/auth")
 public class SecurityController {

		private final SecurityService securityService;

		@Autowired
		public SecurityController(SecurityService securityService) {
			this.securityService = securityService;
		}

		@GetMapping("/validateToken")
		public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader,
				@RequestHeader("X-Santander-Client-Id") String santanderClientIdHeader,
				@RequestHeader("serviceEndpoint") String serviceEndpoint, @RequestHeader("apiName") String apiName) {
			try {

				log.debug("** INIT (GET) /auth/validateToken clientId={}, serviceEndpoint={} >>>",
						santanderClientIdHeader, serviceEndpoint);
				log.debug("** clientId={}, Authorization={} >>>", santanderClientIdHeader, authorizationHeader);
				log.debug("** clientId={}, apiName={} >>>", santanderClientIdHeader, apiName);

				// Simulación de la creación de un objeto Exchange de Apache Camel
				Exchange exchange = ExchangeBuilder.anExchange(securityService.getCamelContext()).build();
				exchange.getIn().setHeader("Authorization", authorizationHeader);
				exchange.getIn().setHeader("X-Santander-Client-Id", santanderClientIdHeader);
				exchange.getIn().setHeader("serviceEndpoint", serviceEndpoint);
				exchange.getIn().setHeader("apiName", apiName);

				Boolean isValid = securityService.validateJwt(exchange);

				log.debug("** FIN (GET) /auth/validateToken clientId={}, Is the token valid={} >>>",
						santanderClientIdHeader, isValid);

				if (Boolean.TRUE.equals(isValid)) {
					return ResponseEntity.ok("Token validation successful");
				} else {
					// Obtener el errorDescription del Exchange
					String errorDescription = exchange.getIn().getHeader("errorDescription", String.class);
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDescription);
				}
			} catch (Exception e) {
				log.error("** FIN ERROR (GET) /auth/validateToken clientId={}, Error validando Token={} >>>",
						santanderClientIdHeader, e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Ocurrió un error al procesar la solicitud de validacion token. Consulte los logs para más detalle.: ");
			}
		}
 }


package com.santander.bnc.bsn049.bncbsn049msiam.observability;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("externalApis") 
public class ExternalApisHealthIndicator implements HealthIndicator{
	private final ExternalApisHealthProperties properties;
	private final HttpClient httpClient;

	public ExternalApisHealthIndicator(ExternalApisHealthProperties properties) {
	    this.properties = properties;
	    this.httpClient = HttpClient.newBuilder()
	            .connectTimeout(Duration.ofMillis(properties.getTimeoutMs()))
	            .build();
	}

	@Override
	public Health health() {
	    Map<String, Object> details = new LinkedHashMap<>(properties.getChecks().size());
	    boolean allCriticalUp = true;

	    for (ExternalApisHealthProperties.ApiCheck api : properties.getChecks()) {
	        ApiResult result = checkApi(api);

	        Map<String, Object> apiDetail = new LinkedHashMap<>(5);
	        apiDetail.put("status", result.up ? "UP" : "DOWN");
	        apiDetail.put("url", api.getUrl());
	        apiDetail.put("critical", api.isCritical());

	        if (result.httpStatus != null) {
	            apiDetail.put("httpStatus", result.httpStatus);
	        }
	        if (result.error != null) {
	            apiDetail.put("error", result.error);
	        }

	        details.put(api.getName(), apiDetail);

	        if (api.isCritical() && !result.up) {
	            allCriticalUp = false;
	        }
	    }

	    return allCriticalUp
	            ? Health.up().withDetails(details).build()
	            : Health.down().withDetails(details).build();
	}

	private ApiResult checkApi(ExternalApisHealthProperties.ApiCheck api) {
	    try {
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(api.getUrl()))
	                .timeout(Duration.ofMillis(properties.getTimeoutMs()))
	                .GET()
	                .build();

	        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

	        int status = response.statusCode();
	        boolean up = isAcceptedStatus(status,api);

	        return new ApiResult(up, status, null);
	    } catch (InterruptedException e) {
	    	Thread.currentThread().interrupt();
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ": " + e.getMessage());
	    }catch (IOException e) {
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ":: " + e.getMessage());
	    }
	}

	private static class ApiResult {
	    private final boolean up;
	    private final Integer httpStatus;
	    private final String error;

	    private ApiResult(boolean up, Integer httpStatus, String error) {
	        this.up = up;
	        this.httpStatus = httpStatus;
	        this.error = error;
	    }

		public boolean isUp() {
			return up;
		}

		public Integer getHttpStatus() {
			return httpStatus;
		}

		public String getError() {
			return error;
		}
	    
	}
	
	private boolean isAcceptedStatus(int status, ExternalApisHealthProperties.ApiCheck api) {
		if (api.getAcceptedStatuses() != null && !api.getAcceptedStatuses().isEmpty()) {
			return api.getAcceptedStatuses().contains(status);
		}
		return status >= 200 && status < 300;
	}

}

package com.santander.bnc.bsn049.bncbsn049msiam.observability;

import java.util.ArrayList; import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "observability.external-apis")
public class ExternalApisHealthProperties {

	private int timeoutMs = 2000;
	private List<ApiCheck> checks = new ArrayList<>();

	public int getTimeoutMs() {
	    return timeoutMs;
	}

	public void setTimeoutMs(int timeoutMs) {
	    this.timeoutMs = timeoutMs;
	}

	public List<ApiCheck> getChecks() {
	    return checks;
	}

	public void setChecks(List<ApiCheck> checks) {
	    this.checks = checks;
	}

	public static class ApiCheck {
	    private String name;
	    private String url;
	    private boolean critical = true;
	    private List<Integer> acceptedStatuses;

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }

	    public boolean isCritical() {
	        return critical;
	    }

	    public void setCritical(boolean critical) {
	        this.critical = critical;
	    }

		public List<Integer> getAcceptedStatuses() {
			return acceptedStatuses;
		}

		public void setAcceptedStatuses(List<Integer> acceptedStatuses) {
			this.acceptedStatuses = acceptedStatuses;
		}
	    
	}
}

package com.santander.bnc.bsn049.bncbsn049msiam.services;


import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SecurityService {

    private final JdbcTemplate jdbcTemplate;
    private final CamelContext camelContext;

    @Autowired
    public SecurityService(JdbcTemplate jdbcTemplate, CamelContext camelContext) {
        this.jdbcTemplate = jdbcTemplate;
        this.camelContext = camelContext;
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    @Value("${auth.jwt.public-key}")
    private String PUBLIC_KEY;

    @Value("${auth.jwt.iss}")
    private String JWT_ISS;

    @Value("${auth.x-santander-client-id}")
    private String X_SANTANDER_CLIENT_ID;

    @Value("${auth.jwt.exp-claim-name:exp}")
    private String EXP_CLAIM_NAME;

    private String INVALID_JWT = "Invalid Jwt";
    private String UNEXPECTED_JWT_TOKEN = "Unexpected Jwt token";
    private String AUTHORIZATION_HEADER_ERROR = "Authorization header not present";
    private String INVALID_BEARER = "Invalid bearer token";
    private String VALID_SIGN = "Valid sign";
    private String INVALID_SIGN = "Invalid sign";
    private String VALID_ISS = "Valid ISS";
    private String INVALID_ISS = "Invalid ISS";
    private String SANTANDER_CLIENT_ID_NOT_PRESENT = "Header X-Santander-Client-Id no present";
    private String VALID_SANTANDER_CLIENT_ID = "Valid X-Santander-Client-Id";
    private String INVALID_SANTANDER_CLIENT_ID = "Invalid X-Santander-Client-Id";
    private String EXPIRED_TOKEN = "Token has expired";
    private String INVALID_CLAIMS = "Invalid claims";
    private String INVALID_SCOPE_FOR_SERVICE = "The scope does not have the necessary permissions";
    private String IS_JWT_VALID = "isJwtValid";
    private String ERROR_DESCRIPTION = "errorDescription";


    public Boolean validateJwt(Exchange ex) throws Exception {

        String bearerToken = "";

        try {
            bearerToken = ex.getIn().getHeader("Authorization").toString();
        } catch (Exception e) {
            log.info(AUTHORIZATION_HEADER_ERROR);
            ex.getIn().setHeader(IS_JWT_VALID, false);
            ex.getIn().setHeader(ERROR_DESCRIPTION, AUTHORIZATION_HEADER_ERROR);
            return false;
        }


        log.info("Start validate token: {}", bearerToken);
        String token = "";

        try{
            token = bearerToken.split("Bearer ")[1];
        }catch(Exception e){
            log.info(INVALID_BEARER);
            ex.getIn().setHeader(IS_JWT_VALID, false);
            ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_BEARER);
            return false;
        }

        try {
            // Convierte la clave pública PEM a un objeto RSAPublicKey
            PublicKey parsedPublicKey = parsePEM(PUBLIC_KEY);
            if(parsedPublicKey == null){
                log.info(INVALID_JWT);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_JWT);
                return false;
            }

            // Configura el validador JWT
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) parsedPublicKey);

            // Parse JWT
            SignedJWT signedJWT = null;
            try {
                signedJWT = SignedJWT.parse(token);
            } catch (Exception e) {
                log.info(UNEXPECTED_JWT_TOKEN);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, UNEXPECTED_JWT_TOKEN);
                return false;
            }

            // VALIDACION DE FIRMA DIGITAL
            // Check Jwt Sign
            if (signedJWT.verify(verifier)) {
                log.info(VALID_SIGN);
            } else {
                log.info(INVALID_SIGN);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_SIGN);
                return false;
            }

            // Validate Santander-Client-Id
            String santanderClientId ="";
            try {
                santanderClientId = ex.getIn().getHeader("X-Santander-Client-Id").toString();
            } catch (Exception e) {
                log.info("Error: {}", e.getMessage());
                log.info(SANTANDER_CLIENT_ID_NOT_PRESENT);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, SANTANDER_CLIENT_ID_NOT_PRESENT);
                return false;
            }

            if(santanderClientId.equals(X_SANTANDER_CLIENT_ID)){
                log.info(VALID_SANTANDER_CLIENT_ID);
            } else {
                log.info(INVALID_SANTANDER_CLIENT_ID);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_SANTANDER_CLIENT_ID);
                return false;
            }

            // VALIDACION DE CLAIMS

            // Validate ISS
            if(verifyIssInClaims(signedJWT)){
                log.info(VALID_ISS);
            } else {
                log.info(INVALID_ISS);
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_ISS);
                return false;
            }

            Map<String, Object> headersMap = ex.getIn().getHeaders();
            headersMap.forEach((key, value) -> log.info("Header '{}': '{}'", key, value));



            // Obtain JWTClaimsSet from token
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            // Validate if token is expired
            if (isTokenExpired(signedJWT)) {
                log.info("Token has expired");
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, EXPIRED_TOKEN);
                return false;
            }

            // Validate claims
            if (!areClaimsValid(signedJWT)) {
                log.info("Invalid claims");
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_CLAIMS);
                return false;
            }


            //VALIDACION DE SCOPES

            String scope = claimsSet.getStringClaim("scope");
            String apiName = ex.getIn().getHeader("apiName", String.class);


            if (apiName == null || apiName.isEmpty()) {
                log.info("The apiName is invalid or not provided.");
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, "Invalid apiName header");
                return false;
            }



            if (!isScopeValid(scope, apiName)) {
                log.info("Scope no autorizado para acceder al servicio de negocio.");
                ex.getIn().setHeader(IS_JWT_VALID, false);
                ex.getIn().setHeader(ERROR_DESCRIPTION, INVALID_SCOPE_FOR_SERVICE);
                return false;
            }

            ex.getIn().setHeader(IS_JWT_VALID, true);

        } catch (Exception e) {
            // Maneja excepciones
            log.error("ERROR = {} {}" , e.getCause() , e.getMessage());
            ex.getIn().setHeader(IS_JWT_VALID, false);
            ex.getIn().setHeader(ERROR_DESCRIPTION, e.getMessage());
            e.printStackTrace();
            return false;
        }


        return true;
    }

    private boolean isScopeValid(String scope, String apiName) {

        log.info("Scope a validar:{}", scope);
        log.info("ApiName a validar: {}", apiName);

        // Verifica si el scope existe en la tabla Scopes
        String sqlScope = "SELECT COUNT(1) FROM authentication.\"Scopes\" WHERE \"ScopeName\" = ?";
        Integer countScope = 0;
        try {
            countScope = jdbcTemplate.queryForObject(sqlScope, new Object[]{scope}, Integer.class);            
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            log.info("Stacktrace: {}", e.getStackTrace());
            throw e;
        }
        if (countScope == null || countScope == 0) {
            return false; // El scope no existe
        }

        // Verifica si el scope puede acceder al servicio de negocio especificado
        String sqlService = "SELECT COUNT(1) FROM authentication.\"ScopeAPICatalog\" WHERE \"ScopeName\" = ? AND \"ServiceName\" = ?";
        Integer countService = 0 ;
        try {
            countService = jdbcTemplate.queryForObject(sqlService, new Object[]{scope, apiName}, Integer.class);            
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            log.info("Stacktrace: {}", e.getStackTrace());
            throw e;
        }
        return countService != null && countService > 0;
    }

    private boolean isTokenExpired(SignedJWT signedJWT) {

        JWTClaimsSet claimsSet =  null;

        try {
            claimsSet = signedJWT.getJWTClaimsSet();
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return false;
        }

        Date expirationTime = claimsSet.getExpirationTime();
        return new Date().after(expirationTime); // true si el token ha expirado
    }

    private boolean areClaimsValid(SignedJWT signedJWT) {

        JWTClaimsSet claimsSet =  null;

        try {
            claimsSet = signedJWT.getJWTClaimsSet();
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return false;
        }


        // Valida 'aud' claim
        List<String> audience = claimsSet.getAudience();
        if (audience == null || audience.isEmpty()) {
            log.info("Invalid 'aud' claim");
            return false;
        }

        return true;
    }


    private PublicKey parsePEM(String publicKey) throws Exception {

        String pemContent = publicKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""); // Elimina caracteres de espacio en blanco

        byte[] keyBytes = Base64.getDecoder().decode(pemContent);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        PublicKey _publicKey = null;

        try{
            _publicKey = keyFactory.generatePublic(keySpec);
        }catch(Exception e){
            log.info(e.getMessage());
            return null;
        }

        return _publicKey;
    }

    /*
     * Verify that iss claim is correct
     */
    private boolean verifyIssInClaims(SignedJWT signedJWT){

        JWTClaimsSet jwtClaimsSet =  null;

        try {
            jwtClaimsSet = signedJWT.getJWTClaimsSet();            
        } catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            return false;
        }

        log.info("Claims del JWT: " + jwtClaimsSet.toJSONObject());
        
        String iss = jwtClaimsSet.getClaim("iss").toString();        
        
        if(!iss.equals(JWT_ISS)){
            return false;
        };
            
        return true;
    }


}
