
    private Object newObj(String simpleName) throws Exception {
    String base = "com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.";

    String[] candidates = {
            simpleName,
            simpleName.replace("TermDeposit", ""),
            simpleName.replace("DTO", "RequestDTO"),
            simpleName.replace("TermDeposit", "").replace("DTO", "RequestDTO")
    };

    for (String candidate : candidates) {
        try {
            return Class.forName(base + candidate)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (ClassNotFoundException ignored) {
        }
    }

    throw new ClassNotFoundException(base + simpleName);
}
