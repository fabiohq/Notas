public String sanetizarHeader(String header) {
    if (header == null) {
        throw new IllegalArgumentException("Header nulo");
    }
    
    // 1. Limpieza básica
    String cleanHeader = header.trim().replace("\n", "").replace("\r", "");
    
    // 2. Validación de formato (Indispensable para Fortify)
    // Suponiendo que es un JWT, validamos caracteres permitidos:
    if (!cleanHeader.matches("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+$")) {
        throw new SecurityException("Formato de token inválido");
    }
    
    return cleanHeader;
}
