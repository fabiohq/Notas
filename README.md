package com.santander.bnc.bsn049.bncbsn049mscountries.observability;
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
