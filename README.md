public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {
    
    // 1. REGISTRO INTERNO (Seguro para diagnóstico)
    if (errors != null) {
        errors.forEach(e -> log.error("Detalle técnico: {} - {}", e.getCode(), e.getDescription()));
    }

    // 2. CREACIÓN DE RESPUESTA DESDE CERO (Interrupción de flujo)
    // No usamos 'responseError.setErrors(sanitizedErrors)' sobre un objeto que tocó datos viejos.
    ErrorResponseDTO cleanResponse = new ErrorResponseDTO();
    List<ErrorDTO> externalErrors = new ArrayList<>();

    if (errors != null) {
        for (ErrorDTO original : errors) {
            // Creamos un DTO nuevo por cada error, sin copiar referencias sospechosas
            ErrorDTO safeDto = new ErrorDTO();
            
            // Usamos constantes o valores fijos para la descripción externa
            // Esto garantiza a Fortify que el 'Sink' no recibe el 'Source' original
            safeDto.setCode("MSG-GENERIC-001"); 
            safeDto.setDescription("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
            
            externalErrors.add(safeDto);
        }
    }

    cleanResponse.setErrors(externalErrors);

    // 3. RETORNO SEGURO
    // Al usar 'cleanResponse', que solo contiene datos generados localmente ("hardcoded"),
    // Fortify debería validar la línea como segura.
    return new ResponseEntity<>(cleanResponse, status != null ? status : HttpStatus.BAD_REQUEST);
}