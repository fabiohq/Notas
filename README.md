private boolean isAcceptedStatus(int status, ExternalApisHealthProperties.ApiCheck api) {
    if (api.getAcceptedStatuses() != null && !api.getAcceptedStatuses().isEmpty()) {
        return api.getAcceptedStatuses().contains(status);
    }
    return status >= 200 && status < 300;
}