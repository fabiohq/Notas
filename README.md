import java.util.stream.Collectors; // Necesario para la refactorización
// ... otros imports ...

public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {
    ErrorResponseDTO responseError = new ErrorResponseDTO();
    
    // --- ESTA ES LA CLAVE DE LA CORRECCIÓN ---
    if (errors != null) {
        // 1. REGISTRO SEGURO (Logging interno para diagnóstico de devs)
        // Registramos todos los detalles originales ANTES de limpiarlos.
        log.error("Se detectaron {} errores técnicos detallados:", errors.size());
        errors.forEach(error -> 
            log.error("Código de Error Técnico: {}, Descripción Técnica Detallada: {}", error.getCode(), error.getDescription())
        );

        // 2. SANITIZACIÓN EXTERNA (Crear errores seguros para el cliente)
        // Creamos una NUEVA lista de ErrorDTOs "limpios" para la respuesta externa.
        List<ErrorDTO> sanitizedErrors = errors.stream()
            .map(originalError -> {
                ErrorDTO safeError = new ErrorDTO();
                // Opcional: Mantener el código técnico si no revela nada sensible,
                // o usar un código de error más genérico para el exterior.
                // Aquí aplicaremos la lógica de reemplazo original, pero con precaución.
                safeError.setCode(originalError.getCode().replaceAll(ErrorCatalog.MS_NAME, ErrorCatalog.MS_NAME));
                
                // --- PUNTO CRÍTICO DE SANITIZACIÓN ---
                // NO devolvemos la descripción técnica original.
                // La reemplazamos por un mensaje genérico para el usuario externo.
                safeError.setDescription("Ha ocurrido un error interno. Por favor, contacte al soporte con el código de error correspondiente.");
                
                return safeError;
            })
            .collect(Collectors.toList());

        // Asignamos la lista SANITIZADA a la respuesta externa.
        responseError.setErrors(sanitizedErrors);
    }
    // --- FIN DE LA CORRECCIÓN ---

    return new ResponseEntity<>(responseError, status != null ? status : HttpStatus.BAD_REQUEST);
}