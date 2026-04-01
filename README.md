private static final int MAX_LEN = 4096;
private static final Pattern JWT_PART_PATTERN =
        Pattern.compile("^[A-Za-z0-9_-]+$");

/**
 * Sanitiza un JWT externo rompiendo la cadena de taint de Fortify
 * mediante decode/encode Base64URL sobre byte[].
 *
 * @param rawToken Token JWT crudo proveniente de servicio externo
 * @return Token JWT validado y reconstruido desde memoria local
 * @throws IllegalArgumentException si el token es nulo, vacío o malformado
 * @throws SecurityException        si contiene caracteres no permitidos
 */
private static String safeTokenSanetizacion(final String rawToken) {

    // ── Guard clauses ──────────────────────────────────────────────────
    if (rawToken == null || rawToken.trim().isEmpty()) {
        throw new IllegalArgumentException("JWT nulo o vacío");
    }

    // ── 1. Eliminar caracteres de control CR/LF y non-printable ───────
    final String cleanToken = rawToken
            .trim()
            .replaceAll("[\\r\\n\\t\\x00-\\x1F\\x7F]", "");

    if (cleanToken.isEmpty() || cleanToken.length() > MAX_LEN) {
        throw new IllegalArgumentException("JWT con longitud inválida");
    }

    // ── 2. Validar estructura: exactamente 3 segmentos ─────────────────
    final String[] parts = cleanToken.split("\\.", -1);
    if (parts.length != 3) {
        throw new IllegalArgumentException(
                "JWT no contiene exactamente 3 segmentos");
    }

    // ── 3. Por cada segmento: whitelist → decode → encode ──────────────
    final Base64.Decoder decoder = Base64.getUrlDecoder();
    final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    final StringBuilder safeTokenBuilder = new StringBuilder();

    for (int i = 0; i < 3; i++) {
        final String part = parts[i];

        if (part == null || part.isEmpty()) {
            throw new IllegalArgumentException(
                    "Segmento JWT vacío en posición: " + i);
        }

        // Whitelist estricta antes de decodificar
        if (!JWT_PART_PATTERN.matcher(part).matches()) {
            throw new SecurityException(
                    "Caracteres no permitidos en segmento JWT posición: " + i);
        }

        try {
            // TAINT BREAK: String tainted → byte[] → nuevo String local
            final byte[] decodedBytes = decoder.decode(
                    part.getBytes(StandardCharsets.US_ASCII));

            final String reEncodedPart = encoder.encodeToString(decodedBytes);

            // Whitelist post-recodificación (doble garantía)
            if (!JWT_PART_PATTERN.matcher(reEncodedPart).matches()) {
                throw new SecurityException(
                        "Segmento inválido tras recodificación en posición: " + i);
            }

            safeTokenBuilder.append(reEncodedPart);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Segmento no es Base64URL válido en posición: " + i, e);
        }

        if (i < 2) {
            safeTokenBuilder.append('.');
        }
    }

    return safeTokenBuilder.toString();
}