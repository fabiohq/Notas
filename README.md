package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;

public interface CustomerContactPointsService {
    
    public ContactPointsResponseDTO getCustomerDetails(String customeId,SecurityHeaders securityHeaders);
    public void putCustomerContactPoint(String customerId, String contactPointId, ContactPointsRequestDTO contactPointsRequestDTO,SecurityHeaders securityHeaders);
}

package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request.TrxPersonRequest;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.response.TrxPersonResponse;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.enums.ClientEnum;

public interface TrxPersonService {

    TrxPersonResponse callPostTRX(TrxPersonRequest request, ClientEnum action);

}

package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service;

public interface ContextApiService {

    void putContext(String key, Object object);
    Object getContext(String key);

}



package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.client.service;


import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.parameters.DataListDTO;

import java.util.List;

public interface ParameterApiService {

    List<DataListDTO> getParameter(String parameterId,String valueCode,SecurityHeaders securityHeaders);

}


package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.service;

import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.put.request.ContactPointsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.customer.contactpoint.response.ContactPointsResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.integration.SecurityHeaders;

public interface CustomerContactPointsService {
    
    public ContactPointsResponseDTO getCustomerDetails(String customeId,SecurityHeaders securityHeaders);
    public void putCustomerContactPoint(String customerId, String contactPointId, ContactPointsRequestDTO contactPointsRequestDTO,SecurityHeaders securityHeaders);
}
