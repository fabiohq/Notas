private String errorJson(String message) {
    return "{\"errores\":[{\"mensaje\":\"" + message + "\"}]}";
}

private ResponseBody errorBody(String message) {
    return ResponseBody.create(
            MediaType.parse("application/json"),
            errorJson(message)
    );
}

private ErrorDTO errorDto(String message) {
    return ErrorDTO.builder()
            .code("CONTRACTS-P-F-9400")
            .message(message)
            .level("error")
            .description("contracts-api-services-v3: " + message)
            .build();
}


____________<<<<_______