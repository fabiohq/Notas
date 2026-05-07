package com.santander.bnc.bsn049.bncbsn049msprospects.utils;


import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.generic.TrxPersonHeader;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonDataRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.request.TrxPersonRequest;

public class ClientUtils {
    
    private ClientUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
}
    public static final String SANTANDER_CLIENT_ID = "x-santander-client-id";
    public static final String AUTHORIZATION = "Authorization";
    /**
     * This method
     * @param customerId
     * @return
     */
    public static TrxPersonRequest buildTrxRequestByCustomerId(ProspectRequestDTO prospectRequestDTO, String customerId){
        TrxPersonRequest requestTrx = new TrxPersonRequest();
        TrxPersonDataRequest personData = new TrxPersonDataRequest();

        personData.setpENUMPE(StringUtils.blankField(customerId));

        if(prospectRequestDTO !=null && prospectRequestDTO.getPerson()!=null){
            personData.setTipoDocumento(StringUtils.blankField(prospectRequestDTO.getPerson().getDocument().getDocumentTypeCode()));
            personData.setNumDocumento(StringUtils.blankField(prospectRequestDTO.getPerson().getDocument().getDocumentNumber()));
        }

        requestTrx.setData(personData);
        return requestTrx;
    }//method closure

    /**
     * These methods build the PayloadHeader of a POST TRX request
     * @param serviceRoute
     * @return
     */
    public static TrxPersonHeader buildHeader(String serviceRoute){
        String conectionTime = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd'T'HH:mm");
        String dateTime = TimeUtils.getSlocalDateTimeByFormat("yyyy-MM-dd");
        TrxPersonHeader txHeader = new TrxPersonHeader();
        txHeader.setRutaServicio(serviceRoute);
        txHeader.setFuncion("Intro");
        txHeader.setCanal("60");
        txHeader.setSecuencia(44204);
        Session txSession = new Session();
        txSession.setUsuario("@NETE781");
        txSession.setTerminal("");
        txSession.setHoraConexion(conectionTime);
        txSession.setPerfil("GCAJASTL");
        txSession.setSucursal("0100");
        txSession.setEntidad("0065");
        txSession.setDiasRestantesCambioClave("29");
        txSession.setFechaContable(dateTime);
        txSession.setEntorno("N");
        txSession.setTurno("");
        txHeader.setSesion(txSession);
        return txHeader;
    }//method closure
}//class closure

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

public class CompareStringUtils {

    public boolean ciudadMatch(String ciudadIngresada, String ciudadServicio) {
        double similarity = similarity(ciudadIngresada, ciudadServicio);
        return similarity > 0.85; // Consideramos que las cadenas son similares si la similitud es mayor o igual
                                  // al 85%

    }

    private double similarity(String s1, String s2) {
        String longer = s1;
        String shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = initializeCostsArray(s2.length() + 1);
        return calculateEditDistance(s1, s2, costs);
    }

    private int[] initializeCostsArray(int length) {
        int[] costs = new int[length];
        for (int i = 0; i < length; i++) {
            costs[i] = i;
        }
        return costs;
    }

    private int calculateEditDistance(String s1, String s2, int[] costs) {
        int lastValue;
        for (int i = 0; i < s1.length(); i++) {
            lastValue = costs[0];
            costs[0] = i + 1;
            for (int j = 0; j < s2.length(); j++) {
                int oldValue = costs[j + 1];
                if (s1.charAt(i) == s2.charAt(j)) {
                    costs[j + 1] = lastValue;
                } else {
                    costs[j + 1] = Math.min(Math.min(costs[j], lastValue), oldValue) + 1;
                }
                lastValue = oldValue;
            }
        }
        return costs[s2.length()];
    }

}
package com.santander.bnc.bsn049.bncbsn049msprospects.utils;


import com.santander.bnc.bsn049.bncbsn049msprospects.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.enums.ParametersEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataUtils {

    /**
     * TRANSLATE ISO 3122-5
     *
     * @param listCode
     * @param oldValueCode
     * @return
     */
    public static String translateValueCode(String listCode, String oldValueCode) {
        
        if (ParametersEnums.COUNTRY.value().equals(listCode)) {
            oldValueCode = translateCountryToXX(oldValueCode);
        }
        if (ParametersEnums.STATES.value().equals(listCode)) {
            oldValueCode = translateDepartment(oldValueCode);
        }

        return oldValueCode;
    }

    /**
     * ISO-3122-5
     * Return XX value
     *
     * @param countryLargeCode
     * @return
     */
    public static String translateCountryToXX(String countryLargeCode) {
        List<DataListDTO> countryCode = getCountryRelTable().stream().filter(x -> countryLargeCode.equals(x.getDescription())).collect(Collectors.toList());
        
        if(countryCode.isEmpty()) 
            return countryLargeCode;
        
        return countryCode.get(0).getCode(); 
    }

    /**
     * @param codeIso
     * @return
     */
    public static String translateCountryToXXX(String codeIso) {
        List<DataListDTO> countryCode = getCountryRelTable().stream().filter(x -> codeIso.equals(x.getCode())).collect(Collectors.toList());

        if(countryCode.isEmpty())
            return codeIso;

        return countryCode.get(0).getDescription();
    }

    private static List<DataListDTO> getCountryRelTable() {

        List<DataListDTO> response = new ArrayList<>();
        response.add(new DataListDTO("", "AF", "AFG"));
        response.add(new DataListDTO("", "AL", "ALB"));
        response.add(new DataListDTO("", "DE", "DEU"));
        response.add(new DataListDTO("", "AD", "AND"));
        response.add(new DataListDTO("", "AO", "AGO"));
        response.add(new DataListDTO("", "AQ", "ATA"));
        response.add(new DataListDTO("", "AG", "ATG"));
        response.add(new DataListDTO("", "SA", "SAU"));
        response.add(new DataListDTO("", "DZ", "DZA"));
        response.add(new DataListDTO("", "AR", "ARG"));
        response.add(new DataListDTO("", "AW", "ABW"));
        response.add(new DataListDTO("", "AU", "AUS"));
        response.add(new DataListDTO("", "AT", "AUT"));
        response.add(new DataListDTO("", "BS", "BHS"));
        response.add(new DataListDTO("", "BD", "BGD"));
        response.add(new DataListDTO("", "BB", "BRB"));
        response.add(new DataListDTO("", "BH", "BHR"));
        response.add(new DataListDTO("", "BE", "BEL"));
        response.add(new DataListDTO("", "BZ", "BLZ"));
        response.add(new DataListDTO("", "BJ", "BEN"));
        response.add(new DataListDTO("", "BM", "BMU"));
        response.add(new DataListDTO("", "BO", "BOL"));
        response.add(new DataListDTO("", "BW", "BWA"));
        response.add(new DataListDTO("", "BR", "BRA"));
        response.add(new DataListDTO("", "BN", "BRN"));
        response.add(new DataListDTO("", "BG", "BGR"));
        response.add(new DataListDTO("", "BI", "BDI"));
        response.add(new DataListDTO("", "BT", "BTN"));
        response.add(new DataListDTO("", "CV", "CPV"));
        response.add(new DataListDTO("", "KH", "KHM"));
        response.add(new DataListDTO("", "CM", "CMR"));
        response.add(new DataListDTO("", "CA", "CAN"));
        response.add(new DataListDTO("", "QA", "QAT"));
        response.add(new DataListDTO("", "TD", "TCD"));
        response.add(new DataListDTO("", "CL", "CHL"));
        response.add(new DataListDTO("", "CN", "CHN"));
        response.add(new DataListDTO("", "CN", "CHN"));
        response.add(new DataListDTO("", "CY", "CYP"));
        response.add(new DataListDTO("", "CO", "COL"));
        response.add(new DataListDTO("", "KM", "COM"));
        response.add(new DataListDTO("", "KP", "PRK"));
        response.add(new DataListDTO("", "KR", "KOR"));
        response.add(new DataListDTO("", "CI", "CIV"));
        response.add(new DataListDTO("", "CR", "CRI"));
        response.add(new DataListDTO("", "CU", "CUB"));
        response.add(new DataListDTO("", "CW", "CUW"));
        response.add(new DataListDTO("", "DK", "DNK"));
        response.add(new DataListDTO("", "DM", "DMA"));
        response.add(new DataListDTO("", "EC", "ECU"));
        response.add(new DataListDTO("", "EG", "EGY"));
        response.add(new DataListDTO("", "SV", "SLV"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "AE", "ARE"));
        response.add(new DataListDTO("", "ES", "ESP"));
        response.add(new DataListDTO("", "US", "USA"));
        response.add(new DataListDTO("", "ET", "ETH"));
        response.add(new DataListDTO("", "PH", "PHL"));
        response.add(new DataListDTO("", "FI", "FIN"));
        response.add(new DataListDTO("", "FJ", "FJI"));
        response.add(new DataListDTO("", "FR", "FRA"));
        response.add(new DataListDTO("", "GA", "GAB"));
        response.add(new DataListDTO("", "GM", "GMB"));
        response.add(new DataListDTO("", "GH", "GHA"));
        response.add(new DataListDTO("", "GI", "GIB"));
        response.add(new DataListDTO("", "GD", "GRD"));
        response.add(new DataListDTO("", "GR", "GRC"));
        response.add(new DataListDTO("", "GL", "GRL"));
        response.add(new DataListDTO("", "GP", "GLP"));
        response.add(new DataListDTO("", "GU", "GUM"));
        response.add(new DataListDTO("", "GT", "GTM"));
        response.add(new DataListDTO("", "GF", "GUF"));
        response.add(new DataListDTO("", "GN", "GIN"));
        response.add(new DataListDTO("", "GW", "GNB"));
        response.add(new DataListDTO("", "GQ", "GNQ"));
        response.add(new DataListDTO("", "GY", "GUY"));
        response.add(new DataListDTO("", "HT", "HTI"));
        response.add(new DataListDTO("", "HN", "HND"));
        response.add(new DataListDTO("", "HK", "HKG"));
        response.add(new DataListDTO("", "HU", "HUN"));
        response.add(new DataListDTO("", "IN", "IND"));
        response.add(new DataListDTO("", "ID", "IDN"));
        response.add(new DataListDTO("", "IQ", "IRQ"));
        response.add(new DataListDTO("", "IR", "IRN"));
        response.add(new DataListDTO("", "IE", "IRL"));
        response.add(new DataListDTO("", "IS", "ISL"));
        response.add(new DataListDTO("", "KY", "CYM"));
        response.add(new DataListDTO("", "CC", "CCK"));
        response.add(new DataListDTO("", "CK", "COK"));
        response.add(new DataListDTO("", "HM", "HMD"));
        response.add(new DataListDTO("", "FK", "FLK"));
        response.add(new DataListDTO("", "PN", "PCN"));
        response.add(new DataListDTO("", "SB", "SLB"));
        response.add(new DataListDTO("", "TC", "TCA"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "UM", "UMI"));
        response.add(new DataListDTO("", "VG", "VGB"));
        response.add(new DataListDTO("", "VI", "VIR"));
        response.add(new DataListDTO("", "IL", "ISR"));
        response.add(new DataListDTO("", "IT", "ITA"));
        response.add(new DataListDTO("", "JM", "JAM"));
        response.add(new DataListDTO("", "JP", "JPN"));
        response.add(new DataListDTO("", "JO", "JOR"));
        response.add(new DataListDTO("", "KE", "KEN"));
        response.add(new DataListDTO("", "KI", "KIR"));
        response.add(new DataListDTO("", "KW", "KWT"));
        response.add(new DataListDTO("", "LA", "LAO"));
        response.add(new DataListDTO("", "LS", "LSO"));
        response.add(new DataListDTO("", "LB", "LBN"));
        response.add(new DataListDTO("", "LR", "LBR"));
        response.add(new DataListDTO("", "LY", "LBY"));
        response.add(new DataListDTO("", "LI", "LIE"));
        response.add(new DataListDTO("", "LU", "LUX"));
        response.add(new DataListDTO("", "MO", "MAC"));
        response.add(new DataListDTO("", "MG", "MDG"));
        response.add(new DataListDTO("", "MY", "MYS"));
        response.add(new DataListDTO("", "MW", "MWI"));
        response.add(new DataListDTO("", "MV", "MDV"));
        response.add(new DataListDTO("", "ML", "MLI"));
        response.add(new DataListDTO("", "MT", "MLT"));
        response.add(new DataListDTO("", "MA", "MAR"));
        response.add(new DataListDTO("", "MQ", "MTQ"));
        response.add(new DataListDTO("", "MU", "MUS"));
        response.add(new DataListDTO("", "MR", "MRT"));
        response.add(new DataListDTO("", "MX", "MEX"));
        response.add(new DataListDTO("", "MC", "MCO"));
        response.add(new DataListDTO("", "MN", "MNG"));
        response.add(new DataListDTO("", "MS", "MSR"));
        response.add(new DataListDTO("", "MZ", "MOZ"));
        response.add(new DataListDTO("", "NA", "NAM"));
        response.add(new DataListDTO("", "NR", "NRU"));
        response.add(new DataListDTO("", "NI", "NIC"));
        response.add(new DataListDTO("", "NG", "NGA"));
        response.add(new DataListDTO("", "NU", "NIU"));
        response.add(new DataListDTO("", "NF", "NFK"));
        response.add(new DataListDTO("", "NO", "NOR"));
        response.add(new DataListDTO("", "NC", "NCL"));
        response.add(new DataListDTO("", "NZ", "NZL"));
        response.add(new DataListDTO("", "OM", "OMN"));
        response.add(new DataListDTO("", "NL", "NLD"));
        response.add(new DataListDTO("", "PK", "PAK"));
        response.add(new DataListDTO("", "PA", "PAN"));
        response.add(new DataListDTO("", "PA", "PAN"));
        response.add(new DataListDTO("", "PG", "PNG"));
        response.add(new DataListDTO("", "PY", "PRY"));
        response.add(new DataListDTO("", "PE", "PER"));
        response.add(new DataListDTO("", "PF", "PYF"));
        response.add(new DataListDTO("", "PL", "POL"));
        response.add(new DataListDTO("", "PT", "PRT"));
        response.add(new DataListDTO("", "PR", "PRI"));
        response.add(new DataListDTO("", "GB", "GBR"));
        response.add(new DataListDTO("", "EH", "ESH"));
        response.add(new DataListDTO("", "CF", "CAF"));
        response.add(new DataListDTO("", "CG", "COG"));
        response.add(new DataListDTO("", "DO", "DOM"));
        response.add(new DataListDTO("", "RE", "REU"));
        response.add(new DataListDTO("", "RW", "RWA"));
        response.add(new DataListDTO("", "RO", "ROU"));
        response.add(new DataListDTO("", "RU", "RUS"));
        response.add(new DataListDTO("", "WS", "WSM"));
        response.add(new DataListDTO("", "KN", "KNA"));
        response.add(new DataListDTO("", "SM", "SMR"));
        response.add(new DataListDTO("", "PM", "SPM"));
        response.add(new DataListDTO("", "VC", "VCT"));
        response.add(new DataListDTO("", "SH", "SHN"));
        response.add(new DataListDTO("", "LC", "LCA"));
        response.add(new DataListDTO("", "ST", "STP"));
        response.add(new DataListDTO("", "SN", "SEN"));
        response.add(new DataListDTO("", "SC", "SYC"));
        response.add(new DataListDTO("", "SL", "SLE"));
        response.add(new DataListDTO("", "SG", "SGP"));
        response.add(new DataListDTO("", "SY", "SYR"));
        response.add(new DataListDTO("", "SO", "SOM"));
        response.add(new DataListDTO("", "LK", "LKA"));
        response.add(new DataListDTO("", "SZ", "SWZ"));
        response.add(new DataListDTO("", "ZA", "ZAF"));
        response.add(new DataListDTO("", "SD", "SDN"));
        response.add(new DataListDTO("", "SE", "SWE"));
        response.add(new DataListDTO("", "CH", "CHE"));
        response.add(new DataListDTO("", "SR", "SUR"));
        response.add(new DataListDTO("", "SJ", "SJM"));
        response.add(new DataListDTO("", "TH", "THA"));
        response.add(new DataListDTO("", "TW", "TWN"));
        response.add(new DataListDTO("", "TZ", "TZA"));
        response.add(new DataListDTO("", "IO", "IOT"));
        response.add(new DataListDTO("", "TG", "TGO"));
        response.add(new DataListDTO("", "TK", "TKL"));
        response.add(new DataListDTO("", "TO", "TON"));
        response.add(new DataListDTO("", "TT", "TTO"));
        response.add(new DataListDTO("", "TN", "TUN"));
        response.add(new DataListDTO("", "TR", "TUR"));
        response.add(new DataListDTO("", "TV", "TUV"));
        response.add(new DataListDTO("", "UG", "UGA"));
        response.add(new DataListDTO("", "UY", "URY"));
        response.add(new DataListDTO("", "VU", "VUT"));
        response.add(new DataListDTO("", "VA", "VAT"));
        response.add(new DataListDTO("", "VE", "VEN"));
        response.add(new DataListDTO("", "VN", "VNM"));
        response.add(new DataListDTO("", "WF", "WLF"));
        response.add(new DataListDTO("", "YE", "YEM"));
        response.add(new DataListDTO("", "YE", "YEM"));
        response.add(new DataListDTO("", "DJ", "DJI"));
        response.add(new DataListDTO("", "ZM", "ZMB"));
        response.add(new DataListDTO("", "ZW", "ZWE"));
        response.add(new DataListDTO("", "EU", "EUR"));
        response.add(new DataListDTO("", "AC", "ASC"));
        response.add(new DataListDTO("", "TA", "TAA"));
        response.add(new DataListDTO("", "YU", "YUG"));
        response.add(new DataListDTO("", "ZR", "ZAR"));
        response.add(new DataListDTO("", "CS", "CSK"));
        response.add(new DataListDTO("", "CS", "CSK"));
        response.add(new DataListDTO("", "AN", "ANT"));
        response.add(new DataListDTO("", "HV", "HVO"));
        response.add(new DataListDTO("", "PC", "PCI"));


        return response;
    }

    private static String translateDepartment(String stateCode) {

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> stateCode.equals(x.getCode())).collect(Collectors.toList());
        if(departmentList.isEmpty())
            return stateCode;

        return departmentList.get(0).getCode();
    }

    public static String translateDepartmentDesc(String stateCode) {

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> stateCode.equals(x.getCode())).collect(Collectors.toList());

        if(departmentList.isEmpty())
            return "";

        return departmentList.get(0).getDescription();

    }
    private static List<DataListDTO> getDepartmentsRelTable() {
        List<DataListDTO> response = new ArrayList<>();
        response.add(new DataListDTO("", "CO-ANT", "05"));
        response.add(new DataListDTO("", "CO-ATL", "08"));
        response.add(new DataListDTO("", "CO-DC", "11"));
        response.add(new DataListDTO("", "CO-BOL", "13"));
        response.add(new DataListDTO("", "CO-BOY", "15"));
        response.add(new DataListDTO("", "CO-CAL", "17"));
        response.add(new DataListDTO("", "CO-CAQ", "18"));
        response.add(new DataListDTO("", "CO-CAU", "19"));
        response.add(new DataListDTO("", "CO-CES", "20"));
        response.add(new DataListDTO("", "CO-COR", "23"));
        response.add(new DataListDTO("", "CO-CUN", "25"));
        response.add(new DataListDTO("", "CO-CHO", "27"));
        response.add(new DataListDTO("", "CO-HUI", "41"));
        response.add(new DataListDTO("", "CO-LAG", "44"));
        response.add(new DataListDTO("", "CO-MAG", "47"));
        response.add(new DataListDTO("", "CO-MET", "50"));
        response.add(new DataListDTO("", "CO-NAR", "52"));
        response.add(new DataListDTO("", "CO-NSA", "54"));
        response.add(new DataListDTO("", "CO-QUI", "63"));
        response.add(new DataListDTO("", "CO-RIS", "66"));
        response.add(new DataListDTO("", "CO-SAN", "68"));
        response.add(new DataListDTO("", "CO-SUC", "70"));
        response.add(new DataListDTO("", "CO-TOL", "73"));
        response.add(new DataListDTO("", "CO-VAC", "76"));
        response.add(new DataListDTO("", "CO-ARA", "81"));
        response.add(new DataListDTO("", "CO-CAS", "85"));
        response.add(new DataListDTO("", "CO-PUT", "86"));
        response.add(new DataListDTO("", "CO-SAP", "88"));
        response.add(new DataListDTO("", "CO-AMA", "91"));
        response.add(new DataListDTO("", "CO-GUA", "94"));
        response.add(new DataListDTO("", "CO-GUV", "95"));
        response.add(new DataListDTO("", "CO-VAU", "97"));
        response.add(new DataListDTO("", "CO-VID", "99"));
        return response;
    }


    public static String getStateCodeByCountryCodeAndStateIso(String countryCode, String stateIsoCode){        
  
        String militaryStateCode = countryCode + "-" + stateIsoCode;

        List<DataListDTO> departmentList = getDepartmentsRelTable().stream().filter(x -> x.getCode().equals(militaryStateCode)).collect(Collectors.toList());

        if(departmentList.isEmpty())
            return "";

        return departmentList.get(0).getDescription();            
        
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Global Utils
 */
public class GUtils {
      
    private GUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
}
    /**
     * Start log
     */
    public static final String SLOG = "--> Start ";

    /**
     * End log
     */
    public static final String ELOG = "<-- End ";

    public static String[] getNullOrBlankPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || (srcValue instanceof String && ((String) srcValue).isEmpty())) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class PatchProspectMapperUtils {


    /* private PatchProspectMapperUtils() {
        throw new IllegalStateException("Utility class");
      } */
    /**
     * For Update prospect Update blocks unnecessary fields
     * 
     * @param patchProspectRequestDTO
     * @return
     */
    public static PatchProspectRequestDTO cleanFieldsFromProspectUpdate(
            PatchProspectRequestDTO patchProspectRequestDTO) {
        // forces document null for avoid documents update.
        if (patchProspectRequestDTO.getPerson() != null && patchProspectRequestDTO.getPerson().getDocuments() != null &&
                    patchProspectRequestDTO.getPerson().getDocuments().get(0) != null) {
                patchProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentTypeCode(null);// Forces customer
                                                                                                    // CASE dont change
                                                                                                    // documents
                patchProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentNumber(null);// Forces customer
                                                                                                  // CASE dont change
                                                                                                  // documents
            }        
        // forces contact
        if (patchProspectRequestDTO.getContactPoints() != null
                && !patchProspectRequestDTO.getContactPoints().isEmpty()) {
            patchProspectRequestDTO.getContactPoints().get(0).setElectronicAddress(null);// Forces customer CASE dont
                                                                                         // change email
            patchProspectRequestDTO.getContactPoints().get(0).setPhoneAddress(null);// Forces customer CASE dont change
                                                                                    // phone
        }
        return patchProspectRequestDTO;
    }// method closure

    public static String replaceDoubleOrTripleSpaces(String cadena) {
        // Primero reemplazar espacios triples
        Pattern patternTriples = Pattern.compile("\\s{3}");
        Matcher matcherTriples = patternTriples.matcher(cadena);
        String cadenaSinTriples = matcherTriples.replaceAll(" ");

        // Luego reemplazar espacios dobles
        Pattern patternDobles = Pattern.compile("\\s{2}");
        Matcher matcherDobles = patternDobles.matcher(cadenaSinTriples);
        return matcherDobles.replaceAll(" ").trim();
    }

}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.domain.host.person.response.TrxPersonData;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.CodeNameDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.DocumentDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.Parameters;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.generic.PostalAddressDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProspectMapperUtils {

    @Value("${params.foreignTaxIndicatorFromBDD.positive}")
    private String foreignTaxIndicatorPositiveResponse;

    @Value("${params.foreignTaxIndicatorFromBDD.negative}")
    private String foreignTaxIndicatorNegativeResponse;

    @Value("${params.deleteProspectCode}")
    private String lowProspect;


    public static Boolean isPenumperValid(String penumper){

        //Valida que sea numérico
        try {
            Long.valueOf(penumper);
        } catch (Exception e) {
            return false;
        }
        //Valida largo
        return penumper.length() == 8;
    }//method closure

    public static PersonDTO personDTONames(TrxPersonData personData, Parameters pa) {
        PersonDTO personDTO = new PersonDTO();
        String genderDesc = "HOMBRE";
        String genderCode = "H";
        if (personData.getSexo() != null && !personData.getSexo().isEmpty() && !personData.getSexo().equalsIgnoreCase("H")) {
            genderDesc = "MUJER";
            genderCode = "M";
        }
        personDTO.setGenderCode(genderCode);
        personDTO.setGenderDescription(genderDesc);
        personDTO.setPersonName(new PersonNameDTO());
        personDTO.getPersonName().setGivenName(personData.getNombre());
        personDTO.getPersonName().setLastName(personData.getPrimerApellido());
        personDTO.getPersonName().setSecondLastName(personData.getSegundoApellido());
        String fullName = personData.getNombre() + " " + personData.getPrimerApellido() + " " + personData.getSegundoApellido();
        personDTO.getPersonName().setFullName(fullName.trim());
        personDTO.setBirthDate(TimeUtils.formatDate(personData.getFechaNacimiento()));
        personDTO.setPlaceOfBirth(ProspectMapperUtils.getPlaceOfBirth(pa));
        personDTO.setFirstNationality(pa.getCountryNationality());
        personDTO.setCountryOfResidence(pa.getCountryDir());
        return personDTO;
    }//method closure

    public static PlaceOfBirthDTO getPlaceOfBirth(Parameters pa) {
        PlaceOfBirthDTO placeOfBirthDTO = new PlaceOfBirthDTO();
        placeOfBirthDTO.setCountry(pa.getCountryBirth());
        placeOfBirthDTO.setState(pa.getCityBirth());
        placeOfBirthDTO.setTown(pa.getTown() != null ? pa.getTown().getName() : "");
        return placeOfBirthDTO;
    }//method closure

    /*
     * Obtains record origin
     */
    public static String getSourceCode(TrxPersonData personData) {
        String sourceCode = "OTRO";
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 2){
            sourceCode = usualt.substring(0,3);
        }

        return sourceCode.equals("ODS") ? sourceCode : "OTRO";
    }//method closure


    public static ContactPointDTO getContactPoint(TrxPersonData personData, Parameters pa) {
        PhoneAddressDTO phoneAddressDTO = new PhoneAddressDTO();
        ContactPointDTO contactPointDTO = new ContactPointDTO();
        contactPointDTO.setPostalAddress(getPostalAddressBasic(personData, pa));
        contactPointDTO.setUseTypes(List.of(new CodeNameDTO("PRI", "Contactos Principales")));
        contactPointDTO.setContactPointId("PRI001");

        phoneAddressDTO.setInternationalCode(personData.getPrecelular());
        phoneAddressDTO.setPhoneNumber(personData.getTelefono());
        phoneAddressDTO.setMobileNumber(personData.getCelular());
        contactPointDTO.setPhoneAddress(phoneAddressDTO);
        //Email
        ElectronicAddressDTO email = new ElectronicAddressDTO();
        email.setEmailAddress(personData.getEmail());
        contactPointDTO.setElectronicAddress(email);
        return contactPointDTO;
    }//method closure


    public String getForeignTaxIndicator(TrxPersonData personData) {
        var usualt = personData.getUsualt();

        if(usualt != null && !usualt.isBlank() && usualt.length() > 6){
            String foreignTaxIndicator = usualt;
            if(usualt.length() >3){
                foreignTaxIndicator  = usualt.substring(3,7);
            }

            if(foreignTaxIndicator.contains(foreignTaxIndicatorPositiveResponse)) {
                return "YES";       
            }
        }
        
        return "NO";
    }

    public String getForeingTaxIndicatoFromRequest(String foreignTaxIndicator){
        if(foreignTaxIndicator == null){
            return "";
        }

        if("YES".equals(foreignTaxIndicator)){
            return foreignTaxIndicatorPositiveResponse;
        }
        return foreignTaxIndicatorNegativeResponse;


    }

    public static PostalAddressDTO getPostalAddressBasic(TrxPersonData personData, Parameters pa) {
        PostalAddressDTO postalAddressDTO = new PostalAddressDTO();
        postalAddressDTO.setFullAddress(personData.getNombreVia());
        postalAddressDTO.setStreetTypeCode(personData.getTipoVia());
        postalAddressDTO.setStreetTypeDescription(pa.getStreetTypeDescription());
        postalAddressDTO.setTown(pa.getCityStandard());
        postalAddressDTO.setCountry(pa.getCountryDir());
        postalAddressDTO.setState(pa.getCityDepartment());      
        if (personData.getDescripcionDireccion().length() > 0 && personData.getDescripcionDireccion() != null) {
            
            String premise = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(personData.getDescripcionDireccion().substring(0,50));
            postalAddressDTO.setPremise(premise.trim());
        }  
        return postalAddressDTO;
    }//method closure



    public static DocumentDTO getDocumentBasics(TrxPersonData personData, Parameters pa) {
        DocumentDTO document = new DocumentDTO();
        document.setDocumentTypeCode(personData.getTipoIdentificacion());
        document.setDocumentTypeDescription(pa.getDocumentTypeDescription());
        document.setDocumentNumber(personData.getNumeroIdentificacion());
        document.setState(pa.getCityExp());
        document.setIssueDate(TimeUtils.formatDate(personData.getFechaExpedicion()));
        document.setCountry(pa.getCountryExp());
        document.setTown(pa.getTownDocument().getName());
        if(personData.getDescripcionDireccion().length() >50 && personData.getDescripcionDireccion() != null){
            String expirationDate = PatchProspectMapperUtils.replaceDoubleOrTripleSpaces(personData.getDescripcionDireccion().substring(50,60));
        document.setExpirationDate(expirationDate.trim());
        }
        return document;
    }//method closure

    /**
     * Is Prospect or not
     * Prospect == 204 Non-content
     *
     * @param conper
     * @return
     */
    public static boolean isNotProspect(String conper) {
        return !"PRO".equalsIgnoreCase(conper);
    }//method closure

    /**
     * For Update prospect Update blocks unnecessary fields
     * @param createProspectRequestDTO
     * @return
     */
    public static CreateProspectRequestDTO cleanFieldsFromProspectUpdate(CreateProspectRequestDTO createProspectRequestDTO){
    //forces document null for avoid documents update.
        if(createProspectRequestDTO.getPerson()!=null && createProspectRequestDTO.getPerson().getDocuments()!=null &&
                createProspectRequestDTO.getPerson().getDocuments().get(0) != null){
            createProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentTypeCode(null);//Forces customer CASE dont change documents
            createProspectRequestDTO.getPerson().getDocuments().get(0).setDocumentNumber(null);//Forces customer CASE dont change documents

        }
    
    // forces contact
        if (createProspectRequestDTO.getContactPoints() != null && !createProspectRequestDTO.getContactPoints().isEmpty()) {
        createProspectRequestDTO.getContactPoints().get(0).setElectronicAddress(null);//Forces customer CASE dont change email
        createProspectRequestDTO.getContactPoints().get(0).setPhoneAddress(null);//Forces customer CASE dont change phone
    }
        return createProspectRequestDTO;
}//method closure

    public String lowProspectLogic(TrxPersonData personData) {

        log.info("Usualt old: {}", personData.getUsualt());

        var usualt = personData.getUsualt();

        String foreignTaxIndicator = usualt;

        if(usualt != null && !usualt.isBlank()){

            if(usualt.length() > 6){

                foreignTaxIndicator  = usualt.substring(3,7);
                usualt = lowProspect + foreignTaxIndicator;

            }

            if(usualt.length() < 7){

                foreignTaxIndicator  = "CONN";
                usualt = lowProspect + foreignTaxIndicator;

            }

        } else {

            foreignTaxIndicator  = "CONN";
            usualt = lowProspect + foreignTaxIndicator;

        }

        log.info("Usualt new: {}", usualt);

        return usualt;
    }

}//class closure

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

public enum RegexTypes {

    ONLY_NUMBERS,
    STRICT_LENGTH_8,
    STRICT_LENGTH_11,
    STRICT_CHAR_LENGTH_2,
    EMAIL,
    EMAIL_LENGTH,
    EMAIL_FORMAT_LEFT,
    EMAIL_FORMAT_RIGHT,
    EMAIL_FORMAT_FIRST_CHAR,
    PHONE_LENGTH,
    PHONE_FORMAT,
    INTERNATIONAL_CODE_LENGTH,
    INTERNATIONAL_CODE_FORMAT,
    ADDRESS_LENGTH,
    TEXT_20_LENGTH,
    TEXT_20_FORMAT,
    TEXT_40_LENGTH,
    TEXT_40_FORMAT,
    ADDRESS_FORMAT,
    COUNTRY_LENGTH,
    COUNTRT_FORMAT,
    GENDER_CODE_FORMAT,
    BIRTH_DAY_DATE_FORMAT,
    ISSUE_DATE_FORMAT,
    GENDER_CODE_LENGTH,
    SECOND_LAST_NAME_FORMAT,
    SECOND_LAST_NAME_CE_FORMAT,
    REGEX_TOWN_DESCRIPTION_FORMAT,
    REGEX_TOWN_DESCRIPTION_LENGTH,
    EMAIL_BETWEEN,
    REGEX_TOWN_CODE_LENGTH,
    REGEX_STATE_CODE_FORMAT,
    REGEX_STATE_CODE_LENGTH
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexUtils {

    @Value("${ms_name}")
    private String msName;
    @Value("${ms_version}")
    private String msVersion;
    @Value("${errors.level}")
    private String level;
    @Value("${regex.error.code}")
    private String code;
    @Value("${regex.only_numbers}")
    private String regexOnlyNumbers;
    @Value("${regex.only_numbers.error}")
    private String regexOnlyNumberError;
    @Value("${regex.strict_length_8}")
    private String regexStrictLength8;
    @Value("${regex.strict_length_11}")
    private String regexStrictLength11;
    @Value("${regex.strict_length_11_error}")
    private String regexStrictLength11Error;
    @Value("${regex.strict_length_8_error}")
    private String regexStrictLength8Error;
    @Value("${regex.strict_char_length_2}")
    private String regexStrictCharLength2;
    @Value("${regex.strict_char_length_2_error}")
    private String regexStrictCharLength2Error;
    @Value("${regex.address_length}")
    private String regexAdressLength;
    @Value("${regex.address_length_error}")
    private String regexAdressLengthError;
    @Value("${regex.address_format}")
    private String regexAdressFormat;
    @Value("${regex.address_format_error}")
    private String regexAdressFormatError;
    @Value("${regex.email}")
    private String regexEmail;
    @Value("${regex.email_between}")
    private String regexEmailBetween;
    @Value("${regex.email_error}")
    private String regexEmailError;
    @Value("${regex.email_length}")
    private String regexEmailLength;
    @Value("${regex.email_length_error}")
    private String regexEmailLengthError;
    @Value("${regex.email_format_left}")
    private String regexEmailFormatLeft;
    @Value("${regex.email_format_left_error}")
    private String regexEmailFormatLeftError;
    @Value("${regex.email_format_right}")
    private String regexEmailFormatRight;
    @Value("${regex.email_format_right_error}")
    private String regexEmailFormatRigthError;
    @Value("${regex.email_format_first_char}")
    private String regexEmailFormatFirstChar;
    @Value("${regex.email_format_first_char_error}")
    private String regexEmailFormatFirtCharError;
    @Value("${regex.phone_length}")
    private String regexPhoneLength;
    @Value("${regex.phone_length_error}")
    private String regexPhoneLengthError;
    @Value("${regex.phone_format}")
    private String regexPhoneFormat;
    @Value("${regex.phone_format_error}")
    private String regexPhoneFormatError;
    @Value("${regex.international_code_format}")
    private String regexInternationaCodeFormat;
    @Value("${regex.international_code_format_error}")
    private String regexInternationaCodeFormatError;
    @Value("${regex.international_code_length}")
    private String regexInternationaCodeLength;
    @Value("${regex.international_code_length_error}")
    private String regexInternationaCodeLengthError;
    @Value("${regex.text_20_length}")
    private String regexText20Length;
    @Value("${regex.text_20_length_error}")
    private String regexText20LengthError;
    @Value("${regex.text_20_format}")
    private String regexText20Format;
    @Value("${regex.text_20_format_error}")
    private String regexText20FormatError;
    @Value("${regex.text_40_length}")
    private String regexText40Length;
    @Value("${regex.text_40_length_error}")
    private String regexText40LengthError;
    @Value("${regex.text_40_format}")
    private String regexText40Format;
    @Value("${regex.text_40_format_error}")
    private String regexText40FormatError;
    @Value("${regex.county_code_length}")
    private String regexCountryCodeLength;
    @Value("${regex.county_code_length_error}")
    private String regexCountryCodeLengthError;
    @Value("${regex.county_code_length}")
    private String regexCountryCodeFormat;
    @Value("${regex.county_code_length_error}")
    private String regexCountryCodeFormatError;
    @Value("${regex.gender_code_format}")
    private String regexGenderCodeFormat;
    @Value("${regex.gender_code_format_error}")
    private String regexGenderCodeFormatError;
    @Value("${regex.birthday_date}")
    private String regexBirthdayDate;
    @Value("${regex.birthday_date_error}")
    private String regexBirthdayDateFormat;
    @Value("${regex.issue_date}")
    private String regexIssueDateFormat;
    @Value("${regex.birthday_date_error}")
    private String regexIssueDateFormatError;
    @Value("${regex.gender_code_length}")
    private String regexGenderCodeLength;
    @Value("${regex.gender_code_length_error}")
    private String regexGenderCodeLengthError;
    @Value("${regex.second_last_name_format}")
    private String regexSecondLastNameFormat;
    @Value("${regex.second_last_name_format_error}")
    private String regexSecondLastNameFormatError;
    @Value("${regex.second_last_name_ce_format}")
    private String regexSecondLastNameCeFormat;
    @Value("${regex.second_last_name_ce_format_error}")
    private String regexSecondLastNameCeFormatError;
    @Value("${regex.town_description_format}")
    private String regexTownDescpritionFormat;
    @Value("${regex.town_description_format_error}")
    private String regexTownDescpritionFormatError;
    @Value("${regex.town_description_length}")
    private String regexTownDescriptionLength;
    @Value("${regex.town_description_length_error}")
    private String regexTownDescriptionLengthError;
    @Value("${regex.town_code_length}")
    private String regexTownCodeLength;
    @Value("${regex.town_code_length_error}")
    private String regexTownCodeLengthError;
    @Value("${regex.state_code_format}")
    private String regexStateCodeFormat;
    @Value("${regex.state_code_format_error}")
    private String regexStateCodeFormatError;
    @Value("${regex.state_code_length}")
    private String regexStateCodeLength;
    @Value("${regex.state_code_length_error}")
    private String regexStateCodeLegnthError;




    public void validateRegex(RegexTypes regex, String value, String fieldName) {

        String regularExpression = "";
        String message = "invalid format";

        switch (regex) {
            case ONLY_NUMBERS:
                regularExpression = regexOnlyNumbers;
                message = regexOnlyNumberError;
                break;
            case STRICT_LENGTH_8:
                regularExpression = regexStrictLength8;
                message = regexStrictLength8Error;
                break;
            case STRICT_LENGTH_11:
                regularExpression = regexStrictLength11;
                message = regexStrictLength11Error;
                break;
            case STRICT_CHAR_LENGTH_2:
                regularExpression = regexStrictCharLength2;
                message = regexStrictCharLength2Error;
                break;
            case ADDRESS_LENGTH:
                regularExpression = regexAdressLength;
                message = regexAdressLengthError;
                break;
            case ADDRESS_FORMAT:
                regularExpression = regexAdressFormat;
                message = regexAdressFormatError;
                break;
            case EMAIL:
                regularExpression = regexEmail;
                message = regexEmailError;
                break;
            case EMAIL_BETWEEN:
                regularExpression = regexEmailBetween;
                message = regexEmailError;
                break;
            case EMAIL_LENGTH:
                regularExpression = regexEmailLength;
                message = regexEmailLengthError;
                break;
            case EMAIL_FORMAT_LEFT:
                regularExpression = regexEmailFormatLeft;
                message = regexEmailFormatLeftError;
                break;
            case EMAIL_FORMAT_RIGHT:
                regularExpression = regexEmailFormatRight;
                message = regexEmailFormatRigthError;
                break;
            case EMAIL_FORMAT_FIRST_CHAR:
                regularExpression = regexEmailFormatFirstChar;
                message = regexEmailFormatFirtCharError;
                break;
            case PHONE_LENGTH:
                regularExpression = regexPhoneLength;
                message = regexPhoneLengthError;
                break;
            case PHONE_FORMAT:
                regularExpression = regexPhoneFormat;
                message = regexPhoneFormatError;
                break;
            case INTERNATIONAL_CODE_FORMAT:
                regularExpression = regexInternationaCodeFormat;
                message = regexInternationaCodeFormatError;
                break;
            case INTERNATIONAL_CODE_LENGTH:
                regularExpression = regexInternationaCodeLength;
                message = regexInternationaCodeLengthError;
                break;
            case TEXT_20_LENGTH:
                regularExpression = regexText20Length;
                message = regexText20LengthError;
                break;
            case TEXT_20_FORMAT:
                regularExpression = regexText20Format;
                message = regexText20FormatError;
                break;
            case TEXT_40_LENGTH:
                regularExpression = regexText40Length;
                message = regexText40LengthError;
                break;
            case TEXT_40_FORMAT:
                regularExpression = regexText40Format;
                message = regexText40FormatError;
                break;
            case COUNTRY_LENGTH:
                regularExpression = regexCountryCodeLength;
                message = regexCountryCodeLengthError;
                break;
            case COUNTRT_FORMAT:
                regularExpression = regexCountryCodeFormat;
                message = regexCountryCodeFormatError;
                break;
            case GENDER_CODE_FORMAT:
                regularExpression = regexGenderCodeFormat;
                message = regexGenderCodeFormatError;
                break;
            case BIRTH_DAY_DATE_FORMAT:
                regularExpression = regexBirthdayDate;
                message = regexBirthdayDateFormat;
                break;
            case ISSUE_DATE_FORMAT:
                regularExpression = regexIssueDateFormat;
                message = regexIssueDateFormatError;
                break;
            case GENDER_CODE_LENGTH:
                regularExpression = regexGenderCodeLength;
                message = regexGenderCodeLengthError;
                break;
            case SECOND_LAST_NAME_FORMAT:
                regularExpression = regexSecondLastNameFormat;
                message = regexSecondLastNameFormatError;
                break;
            case SECOND_LAST_NAME_CE_FORMAT:
                regularExpression = regexSecondLastNameCeFormat;
                message = regexSecondLastNameCeFormatError;
                break;
            case REGEX_TOWN_DESCRIPTION_FORMAT:
                regularExpression = regexTownDescpritionFormat;
                message = regexTownDescpritionFormatError;
                break;
            case REGEX_TOWN_DESCRIPTION_LENGTH:
                regularExpression = regexTownDescriptionLength;
                message = regexTownDescriptionLengthError;
                break;
            case REGEX_TOWN_CODE_LENGTH:
                regularExpression = regexTownCodeLength;
                message = regexTownCodeLengthError;
                break;
            case REGEX_STATE_CODE_FORMAT:
                regularExpression = regexStateCodeFormat;
                message = regexStateCodeFormatError;
                break;
            case REGEX_STATE_CODE_LENGTH:
                regularExpression = regexStateCodeLength;
                message = regexStateCodeLegnthError;
                break;
        }        

        var pattern = Pattern.compile(regularExpression);
        var matcher = pattern.matcher(value);
        boolean match = false;
        while (matcher.find()) {
            match = true;
        }

        if (!match) {

            ErrorDTO errorDTO = ErrorDTO.builder()
                    .code(msName + "-" + code)
                    .level(level)
                    .message("'" + fieldName + "': " + message)
                    .description(msName.toLowerCase() + "-" + msVersion + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

/**
 * THIS CLAS HANDLE ALL ENDPOINTS to expose
 * @author Wilfredo Pena
 */
public class ServiceDirectory {

    private static final String API_VERSION = "/v1";
    private static final String PROSPECT_ENDPOINT = "/prospects";

    /**
     * Endpoints
     */
    public final static String PROSPECT = API_VERSION + PROSPECT_ENDPOINT;
    public final static String PROSPECT_SEARCH = API_VERSION + PROSPECT_ENDPOINT + "/search";
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringUtils {

    final RegexUtils regexUtils;
    final ErrorService errorService;

    private String SECOND_LAST_NAME_FIELD = "person.personName.secondLastName";

    public static String blankField(String field){
        if(field == null){
            return "";
        }else{
            return field;
        }
    }

    public void inputSencondLastNameValidation(String input, String lastName){
        if("CE".equals(input) || "CC".equals(input)){
            if (lastName != null){
                regexUtils.validateRegex(RegexTypes.SECOND_LAST_NAME_CE_FORMAT,lastName,SECOND_LAST_NAME_FIELD);
            }
        }
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String getSlocalDateTimeByFormat(String pattern){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String unformattedDate){
        LocalDate dateF = LocalDate.parse(unformattedDate);
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
         return dateF.format(newFormat);
    }

}
