// Clase interna simple
public class SafeToken {
    private final String value;
    private SafeToken(String value) { this.value = value; }
    
    public static SafeToken of(String rawToken) {
        // Aquí llamas a tu lógica de sanitización regex
        String sanitized = safeHeaderSanetizacion(rawToken); 
        return new SafeToken(sanitized);
    }
    
    public String getValue() { return value; }
}
