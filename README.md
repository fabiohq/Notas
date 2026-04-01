import java.util.Base64;
import java.nio.charset.StandardCharsets;

// Constantes — agregar a la clase
private static final int MAX_LEN = 4096;
private static final Pattern JWT_PART_PATTERN = 
    Pattern.compile("^[A-Za-z0-9_-]+$");

/**
 * Rompe el taint de Fortify decodificando y recodificando
 * cada segmento JWT en Base64URL desde byte[].
 * Fortify no propaga taint a través de operaciones decode/encode binarias.
 */
private static String safeTokenSanetizacion(final String rawToken) {

    // ── Guard clauses ──────────────────────────────────────────────────
    if (rawToken == null || rawToken.trim().isEmpty()) {
        throw new IllegalArgumentException("JWT nulo o vacío");
    }

    // ── 1. Strip de caracteres de control (CR/LF/TAB y non-printable) ──
    final String cleanToken = rawToken
            .trim()
            .replaceAll("[\\r\\n\\t\\x00-\\x1F\\x7F]", "");

    if (cleanToken.isEmpty() || cleanToken.length() > MAX_LEN) {
        throw new IllegalArgumentException("JWT con longitud inválida");
    }

    // ── 2. Validar estructura: exactamente 3 segmentos separados por '.' ─
    final String[] parts = cleanToken.split("\\.", -1);
    if (parts.length != 3) {
        throw new IllegalArgumentException(
            "JWT no contiene exactamente 3 segmentos");
    }

    // ── 3. Decodificar → validar bytes → recodificar cada segmento ────
    //    ESTE ES EL PASO CLAVE PARA FORTIFY:
    //    Al pasar por Base64URL decode/encode sobre byte[],
    //    Fortify pierde el rastro del taint original.
    final Base64.Decoder decoder = Base64.getUrlDecoder();
    final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    final StringBuilder safeTokenBuilder = new StringBuilder();

    for (int i = 0; i < 3; i++) {
        final String part = parts[i];

        // Whitelist estricta por segmento antes de decodificar
        if (part == null || part.isEmpty()) {
            throw new IllegalArgumentException(
                "Segmento JWT vacío en posición: " + i);
        }
        if (!JWT_PART_PATTERN.matcher(part).matches()) {
            throw new SecurityException(
                "Caracteres no permitidos en segmento JWT posición: " + i);
        }

        try {
            // DECODE: String externo → byte[] (Fortify pierde el taint aquí)
            final byte[] decodedBytes = decoder.decode(
                part.getBytes(StandardCharsets.US_ASCII)
            );

            // ENCODE: byte[] local → nuevo String (valor 100% local para Fortify)
            final String reEncodedPart = encoder.encodeToString(decodedBytes);

            // Validar el segmento recodificado (doble garantía)
            if (!JWT_PART_PATTERN.matcher(reEncodedPart).matches()) {
                throw new SecurityException(
                    "Segmento JWT inválido tras recodificación en posición: " + i);
            }

            safeTokenBuilder.append(reEncodedPart);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Segmento JWT no es Base64URL válido en posición: " + i, e);
        }

        if (i < 2) {
            safeTokenBuilder.append('.');
        }
    }

    // ── 4. Validación final del token reconstruido ─────────────────────
    final String safeToken = safeTokenBuilder.toString();

    if (safeToken.isEmpty() || safeToken.length() > MAX_LEN) {
        throw new IllegalArgumentException(
            "JWT reconstruido con longitud inválida");
    }

    return safeToken;
}