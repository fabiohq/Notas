private static final int MAX_LEN = 4096;
private static final Pattern JWT_PATTERN =
        Pattern.compile("^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$");

private static String sanitizeJwtForHeader(String token) {
    if (token == null) {
        throw new IllegalArgumentException("JWT nulo");
    }

    String t = token.trim();

    if (t.isEmpty() || t.length() > MAX_LEN) {
        throw new IllegalArgumentException("JWT con longitud inválida");
    }

    if (!JWT_PATTERN.matcher(t).matches()) {
        throw new IllegalArgumentException("JWT con formato inválido");
    }

    return t;
}