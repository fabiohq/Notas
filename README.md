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
      componentName: msprtvcntrl
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

spring:
  application:
    name: bnc-bsn049-msprtvcntrl
  session:
    store-type: none
  cache:
    type: CAFFEINE #Activated cache caffeine by default (If you want to change the cache to JBoss DataGrid, check the documentacion in confluence)
    caffeine:
      spec: expireAfterWrite=10m #Specifies that each entry should be automatically removed from the cache once that duration has elapsed after the entry’s creation
  lifecycle.timeout-per-shutdown-phase: 2m

logging:
  level:
    com.santander.bnc.bsn049.bncbsn049msprtvcntrl: ${LOG_LEVEL_APP:DEBUG}
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
          include: readinessState,ping,externalApis
        liveness:
          include: livenessState
observability:
  external-apis: 
    timeout-ms: 5000
    checks: 
      - name: backend-for-frontend 
        url: http://${bff-host}/actuator/health
        critical: false 
        accepted-statuses:
          - 200
          - 404
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

bff-host: "backend-for-frontend-security-sanba-gui.apps.ocp4-preprod.cosanpre.corp"
bff-port: 443
bff-https: true
auth:
  x-santander-client-id: 263ec146
  jwt:
    iss: CO_ODS
    public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvtR/wGjrtb5FZMt5rhMixKynE61Sz9curpgPIYUwk/js8hvc8UlIK4vUMEb0RusUIKrccy4k3seX1Da8RcXbUeEy1VAM2SS5bFCsB5FWoGQkPgomrRVLfNWwlIb9ekn1Gal7Y84NzoxW2uJ0849phJlI8fa1snPHL396ZnwqEDEryFmbJZbdNc4zIarEc2hOYM/GTWc9RP5h2BLEU6nUD5TU94PM5AY+18WoVUOPQZ4wdRdST1D7Fq/8+BYMlPuwZMHZO2N8zhkIJm+744jGBQ8yeHubHO8E+wtlu4fqmQZNA1WissqRIMRnmS7bjh8hgn006omWrVWVAthXTT73iQIDAQAB
    exp-claim-name: exp
msoc:
  acr: "1"
  url: http://localhost:8081
  termdeposit:
    authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJPbmJvYXJkaW5nIiwiaXNzIjoib2RzIiwic2NvcGUiOiJPbmJvYXJkaW5nIiwiY2lkIjoiMWQyYTlmYTktNDQ2ZS00MmE3LTk4ZjktNThmZWYyNTkzYjNmIiwianRpIjoiZjNiM2Y2MzgtMGVhZi00ODc0LTg1NDUtNjcyZjlkOWMxNzNlIiwiaWF0IjoxNzAyNTY1Njk0LCJuYmYiOjE3MDI1NjU2OTQsImV4cCI6MTcwMjU2NjI2NCwic3ViIjoib2RzIiwiYWNyIjoiNSJ9.um9lMxyKh6mzrA5QmNb_2jYTowVgrL2r51RnjMCRIH3RsirXtHT54UunALCQFXBwJUWfXBatGZ4KcTLx9x16kZPNhUir0uj0hdMail53m66RUA_eQgBpHMF5-W6QlCJp0XVPlvseZZqV6RfJVgt9XaSq2cVR8AIIPNARCOryvLvON-XolLQHnbO-GpY5vpSpdyUQrybHybh0tLgGeGPA48VfqXOILIIMYA9J105xEvGiC53bBkV0Xy6QIG332_UFTFT2gYXgOsxZtwBTUYAipRTh5ogh3u8X4Nf67PqRMy9DwPt-SCWhKkhGO2RPL4QHIhzKdZSEqWRQ4oIESw9kbA
client:
  sanba:
    url: "https://backend-for-frontend-security-sanba-gui.apps.ocp4-preprod.cosanpre.corp/service-engine/procesar/concatosClientesBuscarPETF?idFormulario=concatosClientesBuscarPETF"
    user: "ODSUSU01"
    channel: "60"
    mqRoute: QCTFD


package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.controllers;

 import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services.OCService;

import lombok.extern.slf4j.Slf4j;

 @RestController
 @RequestMapping("/operativecontrol")
 @Slf4j

 public class OCController {
	 private final ObjectMapper objectMapper;
     private final OCService ocService;

     public OCController(OCService ocService,ObjectMapper objectMapper) {
         this.ocService = ocService;
         this.objectMapper = objectMapper;
     }

     @PostMapping("/validation")
     public ResponseEntity<String> validateOperativeControl(
            @RequestHeader("Authorization") String jwtToken,
            @RequestHeader("X-Operative-Control-Rules") String operativeControlRules,
            @RequestHeader("serviceEndpoint") String serviceEndpoint,
            @RequestHeader("serviceMethod") String serviceMethod,
            @RequestBody(required = false) String requestBody) {

        try {

        	try {
    			String jsonRequest = objectMapper.writeValueAsString(requestBody);
    			StringBuilder sb = new StringBuilder();
    			sb.append(", payload=").append(jsonRequest);
    			log.debug("*** INIT (POST) /operativecontrol/validation {} >>> ", sb.toString());
    			log.debug("*** header Authorization={} >>> ", jwtToken);
    			log.debug("*** header X-Operative-Control-Rules={} >>> ", operativeControlRules);
    			log.debug("*** header serviceEndpoint={} >>> ", serviceEndpoint);
    			log.debug("*** header serviceMethod={} >>> ", serviceMethod);

    		} catch (Exception e) {
    			log.error("Error serializando request");
    		}
        	
            // Simulación de la creación de un objeto Exchange de Apache Camel
            Exchange exchange = ExchangeBuilder.anExchange(ocService.getCamelContext()).build();
            exchange.getIn().setHeader("Authorization", jwtToken);
            exchange.getIn().setHeader("X-Operative-Control-Rules", operativeControlRules);
            exchange.getIn().setHeader("serviceEndpoint", serviceEndpoint);
            exchange.getIn().setHeader("serviceMethod", serviceMethod);
            
            if (requestBody != null) {
                exchange.getIn().setBody(requestBody);
            }

            boolean isValid = ocService.validatePenumperAndContracts(exchange);

            log.debug("** FIN (POST) /operativecontrol/validation , PENUMPE or Contract IS valid={} >>>",isValid);
            
            if (isValid) {
                return ResponseEntity.ok("PENUMPER and/or CONTRACT validation successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("PENUMPE or Contract validation failed");
            }
        } catch (Exception e) {
        	log.error("** FIN ERROR (POST) /operativecontrol/validation, Error during PENUMPER or Contract validation: {} >>>", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error during PENUMPER or Contract validation. Consulte los logs para más detalle.");
        }
    }

 }

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.Models.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pem758ADto {
    
    @JsonProperty("PECODPR")
    private String pecodpr;
    
    @JsonProperty("PECODSU")
    private String pecodsu;
    
    @JsonProperty("PESECDO")
    private int pesecdo;
    
    @JsonProperty("PEDESCO")
    private String pedesco;
    
    @JsonProperty("PECODEN")
    private String pecoden;
    
    @JsonProperty("PETIPVI")
    private String petipvi;
    
    @JsonProperty("PEHSTAM")
    private String pehstam;
    
    @JsonProperty("PEOBSE2")
    private String peobse2;
    
    @JsonProperty("PENOMCA")
    private String penomca;
    
    @JsonProperty("NBPOBLA")
    private String nbpobla;
    
    @JsonProperty("PECDGEN")
    private String pecdgen;
    
    @JsonProperty("NUMPER")
    private String numper;
    
    @JsonProperty("PECODOF")
    private String pecodof;
    
    @JsonProperty("PENUMCO")
    private String penumco;
    
    @JsonProperty("PEOBSE1")
    private String peobse1;

    // Getters y setters

    public String getPecodpr() {
        return pecodpr;
    }

    public void setPecodpr(String pecodpr) {
        this.pecodpr = pecodpr;
    }

    public String getPecodsu() {
        return pecodsu;
    }

    public void setPecodsu(String pecodsu) {
        this.pecodsu = pecodsu;
    }

    public int getPesecdo() {
        return pesecdo;
    }

    public void setPesecdo(int pesecdo) {
        this.pesecdo = pesecdo;
    }

    public String getPedesco() {
        return pedesco;
    }

    public void setPedesco(String pedesco) {
        this.pedesco = pedesco;
    }

    public String getPecoden() {
        return pecoden;
    }

    public void setPecoden(String pecoden) {
        this.pecoden = pecoden;
    }

    public String getPetipvi() {
        return petipvi;
    }

    public void setPetipvi(String petipvi) {
        this.petipvi = petipvi;
    }

    public String getPehstam() {
        return pehstam;
    }

    public void setPehstam(String pehstam) {
        this.pehstam = pehstam;
    }

    public String getPeobse2() {
        return peobse2;
    }

    public void setPeobse2(String peobse2) {
        this.peobse2 = peobse2;
    }

    public String getPenomca() {
        return penomca;
    }

    public void setPenomca(String penomca) {
        this.penomca = penomca;
    }

    public String getNbpobla() {
        return nbpobla;
    }

    public void setNbpobla(String nbpobla) {
        this.nbpobla = nbpobla;
    }

    public String getPecdgen() {
        return pecdgen;
    }

    public void setPecdgen(String pecdgen) {
        this.pecdgen = pecdgen;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getPecodof() {
        return pecodof;
    }

    public void setPecodof(String pecodof) {
        this.pecodof = pecodof;
    }

    public String getPenumco() {
        return penumco;
    }

    public void setPenumco(String penumco) {
        this.penumco = penumco;
    }

    public String getPeobse1() {
        return peobse1;
    }

    public void setPeobse1(String peobse1) {
        this.peobse1 = peobse1;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.observability;
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

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.observability;

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

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import com.santander.bnc.bsn049.bncbsn049msprtvcntrl.Models.Dtos.Pem758ADto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class OCService {

    private final CamelContext camelContext;
    private final RestTemplate restTemplate;

    @Value("${msoc.termdeposit.authorization}")
    private String TD_AUTHORIZATION;

    @Value("${auth.jwt.public-key}")
    private String PUBLIC_KEY;

    @Value("${msoc.url}")
    private String OPERATIVE_CONTROL_URL;

    @Value("${msoc.acr}")
    private String ACR_DEFINED;

    @Value("${client.sanba.url}")
    private String SANBA_URL;

    @Value("${client.sanba.channel}")
    private String SANBA_CHANNEL;

    @Value("${client.sanba.user}")
    private String SANBA_USER;

    @Value("${client.sanba.mqRoute}")
    private String SANBA_MQROUTE;

    @Autowired
    public OCService(CamelContext camelContext, RestTemplate restTemplate) {
        this.camelContext = camelContext;
        this.restTemplate = restTemplate;
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public boolean validatePenumperAndContracts(Exchange exchange) {
        try {
            log.info("START OC");
            String octestHeader = exchange.getIn().getHeader("octest", String.class);

            if (octestHeader != null) {
                return true;
            }

            String acrExtracted = extractAcr(exchange);

            if (!acrExtracted.equals(ACR_DEFINED.toString())) {
                log.info("El claim acr del token no coincide con el esperado. Se ignora control operativo.");
                return true;
            }

            String extractedPENUMPER = extractPENUMPERBasedOnRule(exchange);
            String contractIdFromRequest = extractContractIdBasedOnRule(exchange);
            String expectedPENUMPER = extractPENUMPERFromJwt(exchange);

            log.info("VALOR DEL PENUMPER EXTRAIDO EN LA REGLA: " + extractedPENUMPER);
            log.info("VALOR DEL ID DE CONTRATO EXTRAIDO EN LA REGLA: " + contractIdFromRequest);

            // validar PENUMPER si esta presente
            if (!extractedPENUMPER.isEmpty() && !isValidPenumper(extractedPENUMPER)) {
                log.info("VALIDACION DEL PENUMPER ERRONEA: NO ES VALIDO");
                return false;
            }

            // comparar PENUMPER extraido con el PENUMPER del JWT si ambos estan presentes
            if (!extractedPENUMPER.isEmpty() && !expectedPENUMPER.equals(extractedPENUMPER)) {
                log.info("VALIDACION DEL PENUMPER ERRONEA: NO COINCIDE CON EL JWT");
                return false;
            }

            // validar ID de contrato si esta presente
            if (!contractIdFromRequest.isEmpty() && !isValidContractId(contractIdFromRequest)) {
                log.info("VALIDACION DEL ID DE CONTRATO ERRONEA: NO ES VALIDO");
                return false;
            }

            // Realizar validación de contrato si es necesario
            if (!contractIdFromRequest.isEmpty()) {
                String penumperToUse = extractedPENUMPER.isEmpty() ? expectedPENUMPER : extractedPENUMPER;
                log.info("PENUMPER A USAR PARA LLAMADA A SANBA, CON TRIM Y TOSTRING: " + "'" + penumperToUse.trim()
                        + "'");
                Pem758ADto producto = extractContractBySanba(penumperToUse.trim(), contractIdFromRequest);

                if (producto == null) {
                    return false;
                }

                log.info("REALIZA LA LLAMADA A SANBA Y VALIDA SI EL CONTRATO ESTA PRESENTE");
                return validarContrato(contractIdFromRequest, producto);
            }

            log.info("Validación exitosa o no requerida");
            return true;
        } catch (Exception e) {
            log.error("Error en la validación: {}", e.getMessage());
            return false;
        }
    }

    private boolean isValidPenumper(String penumper) {
        return penumper.length() == 8 && penumper.matches("\\d+");
    }

    private boolean isValidContractId(String contractId) {
        return contractId.length() == 20 && contractId.matches("\\d+");
    }

    private String extractPENUMPERFromJwt(Exchange exchange) {

        String bearerToken = "";
        bearerToken = exchange.getIn().getHeader("Authorization").toString();

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            log.info("Authorization header is missing or does not contain Bearer token");
            return null;
        }

        // String token = bearerToken.replaceFirst("^Bearer\\s+", ""); // quita el
        // "Bearer " si esta presente
        String token = bearerToken.substring(7);
        log.info("JWT Token: {}", token);
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            String claimPenumper = claimsSet.getStringClaim("attPersonNumberCode");
            log.info("PENUMPER extraído del JWT: {}", claimPenumper);

            return claimPenumper;
        } catch (Exception e) {
            log.error("Error al decodificar el JWT o extraer el PENUMPER: {}", e.getMessage());
            return null;
        }
    }

    private String extractContractIdBasedOnRule(Exchange exchange) throws Exception {
        String serviceEndpoint = exchange.getIn().getHeader("serviceEndpoint", String.class);
        String consumerUrl = exchange.getIn().getHeader("CamelHttpUri", String.class);
        String path = extractPath(serviceEndpoint, consumerUrl);
        String requestMethod = exchange.getIn().getHeader("serviceMethod", String.class);
        log.info("El REQUEST METHOD ES: " + requestMethod);
        log.info("Path de servicio CONTRACT: {}", path);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rules = mapper.readTree(exchange.getIn().getHeader("X-Operative-Control-Rules", String.class))
                .path("operativeControl").path("rules");
        String contractId = "";

        for (JsonNode rule : rules) {
            String rulePath = rule.path("path").asText();
            String ruleMethod = rule.path("method").asText();
            log.info("Path extraido  de regla (CONTRACT): {}", rulePath);
            String transformedPath = transformServicePath(path, rulePath);
            log.info("Path transformado para comparar (CONTRACT): {}", transformedPath);

            if (rule.path("active").asBoolean() && transformedPath.equals(rulePath)
                    && requestMethod.equals(ruleMethod)) {
                String location = rule.path("cccUbication").asText();
                String position = rule.path("cccPosition").asText();

                log.info("Regla encontrada para deposit_id - Ubicación: {}, Posición: {}", location, position);
                if ((location.isEmpty() || location == null) && (position.isEmpty() || position == null)) {
                    return "";
                }

                if ("url".equals(location)) {
                    String[] pathParts = path.split("/");
                    int positionIndex = Integer.parseInt(position) - 1;
                    if (pathParts.length > positionIndex) {
                        contractId = pathParts[positionIndex];
                    }
                    log.info("deposit_id extraído de la URL: {}", contractId);
                }
                break;
            }
        }

        return contractId;
    }

    private String extractAcr(Exchange exchange) {

        String bearerToken = "";
        bearerToken = exchange.getIn().getHeader("Authorization").toString();

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            log.info("No viene header Authorization o no contiene el token Bearer.");
            return null;
        }

        // String token = bearerToken.replaceFirst("^Bearer\\s+", ""); // quita el
        // "Bearer " si esta presente
        String token = bearerToken.substring(7);
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            String acr = claimsSet.getStringClaim("acr");
            log.info("EL ACR EXTRAIDO ES: {}", acr);

            return acr;
        } catch (Exception e) {
            log.error("Error al decodificar el JWT o extraer el PENUMPER: {}", e.getMessage());
            return null;
        }
    }

    private String extractPENUMPERBasedOnRule(Exchange exchange) throws Exception {
        String serviceEndpoint = exchange.getIn().getHeader("serviceEndpoint", String.class);
        String consumerUrl = exchange.getIn().getHeader("CamelHttpUri", String.class);
        String path = extractPath(serviceEndpoint, consumerUrl);
        String requestMethod = exchange.getIn().getHeader("serviceMethod", String.class);
        log.info("**PENUMPER** Path de servicio: {}", path);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rules = mapper.readTree(exchange.getIn().getHeader("X-Operative-Control-Rules", String.class))
                .path("operativeControl").path("rules");
        String penumper = "";

        for (JsonNode rule : rules) {
            String rulePath = rule.path("path").asText();
            String ruleMethod = rule.path("method").asText();
            log.info("**PENUMPER** Path extraido  de regla (PENUMPER): {}", rulePath);
            String transformedPath = transformServicePath(path, rulePath);
            log.info("**PENUMPER** Path transformado para comparar (PENUMPER): {}", transformedPath);

            if (rule.path("active").asBoolean() && transformedPath.equals(rulePath)
                    && requestMethod.equals(ruleMethod)) {
                String location = rule.path("clientUbication").asText();
                String position = rule.path("clientPosition").asText();

                if ((location.isEmpty() || location == null) && (position.isEmpty() || position == null)) {
                    return "";
                }

                log.info("**PENUMPER** Regla encontrada para PENUMPER - Ubicación: {}, Posición: {}", location,
                        position);

                switch (location) {
                    case "url":
                        if ("queryparam".equals(position)) {
                            if (path.contains("?")) {
                                String[] queryParams = path.split("\\?", 2)[1].split("&");
                                for (String param : queryParams) {
                                    String[] keyValue = param.split("=", 2);
                                    if ("participant_id".equals(keyValue[0])) {
                                        penumper = keyValue.length > 1 ? keyValue[1] : "";
                                        break;
                                    }
                                }
                                log.info("PENUMPER extraído del query param: {}", penumper);
                            } else {
                                log.info("No hay parámetros de query en la URL");
                            }
                        } else {
                            String[] pathParts = path.split("/");
                            int positionIndex = Integer.parseInt(position) - 1;
                            if (pathParts.length > positionIndex) {
                                penumper = pathParts[positionIndex];
                            }
                            log.info("PENUMPER extraído de la URL: {}", penumper);
                        }

                        break;
                    case "body":
                        String jsonBody = exchange.getIn().getBody(String.class);
                        penumper = JsonPath.parse(jsonBody).read(position, String.class);
                        log.info("PENUMPER extraído del body: {}", penumper);
                        break;
                    case "header":
                        penumper = exchange.getIn().getHeader(position, String.class);
                        log.info("PENUMPER extraído del header: {}", penumper);
                        break;
                    default:
                        log.error("Ubicación desconocida para la extracción de PENUMPER: {}", location);
                        throw new IllegalArgumentException("Ubicación desconocida: " + location);
                }
                break;
            }
        }

        return penumper;
    }

    private String transformServicePath(String servicePath, String rulePath) {
        if ("/".equals(servicePath)) {
            return servicePath;
        }

        String[] pathAndQuery = servicePath.split("\\?", 2);
        String pathPart = pathAndQuery[0];
        String queryPart = pathAndQuery.length > 1 ? pathAndQuery[1] : "";

        String[] serviceParts = pathPart.split("/");
        String[] ruleParts = rulePath.split("/");
        StringBuilder transformedPath = new StringBuilder();

        for (int i = 0; i < ruleParts.length && i < serviceParts.length; i++) {
            if (ruleParts[i].equals("{customer_id}") && isValidPenumper(serviceParts[i])) {
                transformedPath.append("{customer_id}");
            } else if (ruleParts[i].equals("{deposit_id}") && isValidContractId(serviceParts[i])) {
                transformedPath.append("{deposit_id}");
            } else if (ruleParts[i].startsWith("{") && ruleParts[i].endsWith("}")) {
                transformedPath.append("{randomNum}");
            } else {
                transformedPath.append(serviceParts[i]);
            }
            if (i < ruleParts.length - 1 || i < serviceParts.length - 1) {
                transformedPath.append("/");
            }
        }

        if (!queryPart.isEmpty()) {
            String transformedQuery = transformQueryString(queryPart);
            if (!transformedQuery.isEmpty()) {
                transformedPath.append("?").append(transformedQuery);
            }
        }

        return transformedPath.toString();
    }

    private String transformQueryString(String queryString) {
        String[] queryParams = queryString.split("&");
        StringBuilder transformedQuery = new StringBuilder();

        for (String param : queryParams) {
            String[] keyValue = param.split("=", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";

            if ("participant_id".equals(key) && isValidPenumper(value)) {
                transformedQuery.append(key).append("={customer_id}");
            } else {
                transformedQuery.append(key).append("={randomNum}");
            }

            transformedQuery.append("&");
        }

        if (transformedQuery.length() > 0) {
            transformedQuery.setLength(transformedQuery.length() - 1);
        }

        log.info("Transformación de la url query: " + transformedQuery.toString());
        return transformedQuery.toString();
    }

    private String extractPath(String serviceEndpoint, String consumerUrl) {
        String basePath = "/operativecontrol/validation";

        if (consumerUrl != null && consumerUrl.startsWith(basePath) && consumerUrl.length() > basePath.length()) {
            return consumerUrl.substring(basePath.length());
        } else {
            try {
                URL url = new URL(serviceEndpoint);
                String path = url.getPath();
                String query = url.getQuery();
                if (query != null && !query.isEmpty()) {
                    path += "?" + query;
                }
                return path;
            } catch (MalformedURLException e) {
                log.error("Error al extraer el path de serviceEndpoint: {}", serviceEndpoint, e);
                return "";
            }
        }
    }

    public Pem758ADto extractContractBySanba(String penumper, String contractId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("mqRoute", SANBA_MQROUTE);

        String last12DigitsOfContractId = contractId.substring(contractId.length() - 12);

        String requestJson = "{"
                + "\"cabecera\": {"
                + "    \"rutaServicio\": \"concatosClientesBuscarPETF\","
                + "    \"sesion\": {"
                + "        \"usuario\": \"" + SANBA_USER + "\","
                + "        \"terminal\": \"\","
                + "        \"horaConexion\": \"2022-02-10T11:56\","
                + "        \"entorno\": \"N\","
                + "        \"perfil\": \"GCAJASTL\","
                + "        \"sucursal\": \"0100\","
                + "        \"entidad\": \"0065\","
                + "        \"diasRestantesCambioClave\": \"29\","
                + "        \"fechaContable\": \"2022-02-10\","
                + "        \"turno\": \"\""
                + "    },"
                + "    \"funcion\": \"Intro\","
                + "    \"secuencia\": 44204,"
                + "    \"canal\": \"" + SANBA_CHANNEL + "\""
                + "},"
                + "\"data\": {"
                + "    \"numeroCliente\": \"" + penumper + "\","
                + "    \"codigoEntidad\": \"0065\","
                + "    \"indicadorRellamada\": \"S\","
                + "    \"numeroContrato\": \"" + last12DigitsOfContractId + "\","
                + "    \"codigoOficina\": \"0100\","
                + "    \"codigoProducto\": \"04\","
                + "    \"codigoSubproducto\": \"\""
                + "}"
                + "}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(SANBA_URL, HttpMethod.POST, entity, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.getBody());
            JsonNode pem758aNode = rootNode.path("data").path("PEM758A");

            if (pem758aNode.isMissingNode() || pem758aNode.isNull()) {
                log.info("PEM758A no encontrado en la respuesta de Sanba");
                return null;
            }

            return mapper.treeToValue(pem758aNode, Pem758ADto.class);

        } catch (JsonProcessingException e) {
            log.error("Error procesando el JSON: " + e.getMessage());
            return null;
        }
    }

    public boolean validarContrato(String contractId, Pem758ADto producto) {
        String penumco = producto.getPenumco();
        String last12DigitsOfContractId = contractId.substring(contractId.length() - 12);

        if (last12DigitsOfContractId.equals(penumco)) {
            log.info("Contrato validado exitosamente: {}", contractId);
            return true;
        } else {
            log.info("No se encontró el contrato en la lista de contratos de Sanba: {}", contractId);
            return false;
        }
    }

}

package com.santander.bnc.bsn049.bncbsn049msprtvcntrl;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.santander.bnc.bsn049.bncbsn049msprtvcntrl.observability.ExternalApisHealthProperties;

/**
 * The main class of the Spring applications.
 *
 * @author Santander Technology
 */
@SpringBootApplication
@EnableConfigurationProperties(ExternalApisHealthProperties.class)
public class Application {

	/**
	 * Main method of the application
	 * where the application entry-point is.
	 * Depending on the kind of application,
	 * the context of Spring can be configured
	 * to support not web, reactive web, and
	 * servlet web applications.
	 *
	 * This application is defined as Servlet
	 * web application (WebApplicationType.SERVLET)
	 *
	 * @param args input arguments
	 */
	public static void main(String[] args) {


		new SpringApplicationBuilder(Application.class)
			.web(WebApplicationType.SERVLET)
			.run(args);
	}

}
