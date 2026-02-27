public final class JwtHeaderValidator {

    private static final int MAX_LEN = 4096;

    // JWT con 3 partes Base64URL
    private static final Pattern JWT_PATTERN =
            Pattern.compile("^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$");

    private JwtHeaderValidator() {}

    public static String validateForHeader(String token) {

        if (token == null)
            throw new IllegalArgumentException("JWT nulo");

        String t = token.trim();

        if (t.length() == 0 || t.length() > MAX_LEN)
            throw new IllegalArgumentException("Longitud inválida");

        // Prevención directa de HTTP header injection
        if (t.contains("\r") || t.contains("\n"))
            throw new IllegalArgumentException("JWT contiene caracteres inválidos");

        if (!JWT_PATTERN.matcher(t).matches())
            throw new IllegalArgumentException("Formato JWT inválido");

        return t;
    }
}