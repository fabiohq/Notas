private TrxBP49Request buildRequest() {
    Session session = new Session();
    session.setUsuario("original-user");

    TrxHeader header = new TrxHeader();
    header.setCanal("original-channel");
    header.setSesion(session);

    TrxBP49Request request = new TrxBP49Request();
    request.setCabecera(header);

    return request;
}

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request.TrxBP49Request;