Te falta declarar el objeto request.
Pon esto arriba de los tests de trxBP49:
Java
private TrxBP49Request request;
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
}
También necesitas importar:
Java
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.Session;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request.TrxBP49Request;
Y para PEMFV:
Java
private PemfvRequest pemfvRequest;
en el mismo setUp():
Java
pemfvRequest = new PemfvRequest();
pemfvRequest.setCabecera(header);
Import:
Java
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request.PemfvRequest;
Con eso ya compilan todos los tests que te pasé.