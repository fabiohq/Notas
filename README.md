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
