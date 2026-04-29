package com.santander.bnc.bsn049.bncbsn049mscustomer.utils;


import com.santander.bnc.bsn049.bncbsn049mscustomer.domain.parameters.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscustomer.enums.ParametersEnums;

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
     * return XXX value
     * @param code_iso
     * @return
     */
    public static String translateCountryToXXX(String code_iso) {
        List<DataListDTO> countryCode = getCountryRelTable().stream().filter(x -> code_iso.equals(x.getCode())).collect(Collectors.toList());

        if(countryCode.isEmpty())
            return code_iso;

        // RETURN XXX CODE (NO_ISO)
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
