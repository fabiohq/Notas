package com.santander.bnc.bsn049.bncbsn049mscontracts.client.service;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksParametersRequest;

public interface BanksService {
    BanksDTO banksResponse(BanksParametersRequest banksParametersRequest);
}
*******************************

package com.santander.bnc.bsn049.bncbsn049mscontracts.client.service;

import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.request.TrxPEPFDataRequest;

public interface TrxSanbaService {

    TrxBP17Response trxBP17(TrxBP17Request request);
    TrxBP13Response trxBP13(TrxBP13Request request);
    TrxBP31Response trxBP31(TrxBP31Request request);
    TrxPEPFDataResponse trxPEPF(TrxPEPFDataRequest trxPEPFDataRequest);
    TrxBp01Response trxBP01(TrxBp01Request trxBp01Request);
    TrxBp02Response trxBP02(TrxBp02Request trxBp02Request);
    TrxBP49Response trxBP49(TrxBP49Request trxBp49Request);
    TrxBP21Response trxBP21(TrxBP21Request trxBp21Request);
}
