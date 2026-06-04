var err = new TrxBP49Response();
err.setErrores(java.util.List.of(trxError));
err.setAvisos(java.util.Collections.emptyList());


var err = new TrxBP31Response();
err.setErrores(java.util.List.of(trxError));
err.setAvisos(java.util.Collections.emptyList());

var parsed = new TrxPEPFDataResponse();
parsed.setErrores(java.util.List.of(trxError));
parsed.setAvisos(java.util.Collections.emptyList());

