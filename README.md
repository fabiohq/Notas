Sí. Para PEMFV también debes crear la variable de clase.
Arriba del test:
Java
private PemfvRequest pemfvRequest;
y en el @BeforeEach:
Java
@BeforeEach
void setUp() {

    Session session = new Session();
    session.setUsuario("user");

    TrxPersonHeader header = new TrxPersonHeader();
    header.setSesion(session);

    request = new TrxBP49Request();
    request.setCabecera(header);

    pemfvRequest = new PemfvRequest();
    pemfvRequest.setCabecera(header);
}
Imports:
Java
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
Con eso desaparece ese error.