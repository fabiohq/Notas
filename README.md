public String SafeTokenSanetizacion(String token) {
    if (token == null) {
        throw new IllegalArgumentException("Token nulo");
    }

    // 1. Limpieza de espacios y caracteres de control (CR/LF)
    String cleanToken = token.trim().replaceAll("[\\r\\n]", "");

    // 2. VALIDACIÓN DE LISTA BLANCA (Lo que Fortify exige)
    // Esta regex permite letras, números, puntos, guiones y guiones bajos (formato JWT)
    if (!cleanToken.matches("^[A-Za-z0-9-_\\.]+$")) {
        throw new SecurityException("Caracteres no permitidos detectados en el token"); 
    }

    return cleanToken;
}
