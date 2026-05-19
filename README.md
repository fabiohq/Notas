private void setField(Object target, String fieldName, Object value) throws Exception {
    Field field = target.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(target, value);
}

private String publicKeyAsBase64(RSAPublicKey publicKey) {
    return Base64.getEncoder().encodeToString(publicKey.getEncoded());
}

private Exchange exchange() {
    return ExchangeBuilder.anExchange(camelContext).build();
}

private String buildJwt(
        String issuer,
        String scope,
        List<String> audience,
        Instant expiration
) throws Exception {

    JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
            .issuer(issuer)
            .claim("scope", scope)
            .expirationTime(Date.from(expiration));

    if (audience != null) {
        claimsBuilder.audience(audience);
    }

    SignedJWT signedJWT = new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
            claimsBuilder.build()
    );

    signedJWT.sign(new RSASSASigner((RSAPrivateKey) keyPair.getPrivate()));

    return signedJWT.serialize();
}

