package com.santander.bnc.bsn049.bncbsn049msprospects.observability;
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
	    Map<String, Object> details = new LinkedHashMap<>();
	    boolean allCriticalUp = true;

	    for (ExternalApisHealthProperties.ApiCheck api : properties.getChecks()) {
	        ApiResult result = checkApi(api);

	        Map<String, Object> apiDetail = new LinkedHashMap<>();
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


package com.santander.bnc.bsn049.bncbsn049msprospects.observability;

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
