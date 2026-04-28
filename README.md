package com.santander.bnc.bsn049.bncbsn049mscountries.utils;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.enums.ParametersEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DataUtils {
    private DataUtils() {}
    /**
     * TRANSLATE ISO 3122-5
     *
     * @param listCode
     * @param oldValueCode
     * @return
     */
    public static String translateValueCode(String listCode, String oldValueCode) {

        if (ParametersEnums.COUNTRY.value().equals(listCode)) {
            oldValueCode = translateCountry(oldValueCode);
        }
        return oldValueCode;
    }

    /**
     * ISO-3122-5
     *
     * @param countryLargeCode
     * @return
     */
    private static String translateCountry(String countryLargeCode) {
        List<DataListDTO> countryCode = getCountryRelTable().stream()
                .filter(x -> countryLargeCode.equals(x.getDescription())).toList();
        
        if (countryCode.isEmpty())
            return countryLargeCode;

        return countryCode.get(0).getCode();
    }

    private static List<DataListDTO> getCountryRelTable() {

        List<DataListDTO> response = new ArrayList<>();

       // OLDS
       response.add(new DataListDTO("", "AF", "AFG"));
       response.add(new DataListDTO("", "AL", "ALB"));
       response.add(new DataListDTO("", "AL", "ALB"));
       response.add(new DataListDTO("", "AS", "ASA"));
       response.add(new DataListDTO("", "DE", "DEU"));
       response.add(new DataListDTO("", "DE", "ALE"));
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
       response.add(new DataListDTO("", "AE", "ABU"));
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
       // NUEVOS
       response.add(new DataListDTO("", "AE", "AJM"));
       response.add(new DataListDTO("", "AO", "AGO"));
       response.add(new DataListDTO("", "AO", "ANG"));
       response.add(new DataListDTO("", "DZ", "ALG"));
       response.add(new DataListDTO("", "DZ", "DZA"));
       response.add(new DataListDTO("", "AQ", "ANR"));
       response.add(new DataListDTO("", "AW", "ARU"));
       response.add(new DataListDTO("", "BS", "BAH"));
       response.add(new DataListDTO("", "BH", "BAI"));
       response.add(new DataListDTO("", "BD", "BAN"));
       response.add(new DataListDTO("", "BB", "BAR"));
       response.add(new DataListDTO("", "BZ", "BEI"));
       response.add(new DataListDTO("", "BM", "BER"));
       response.add(new DataListDTO("", "BT", "BHU"));
       response.add(new DataListDTO("", "IO", "BIN"));
       response.add(new DataListDTO("", "BW", "BOT"));
       response.add(new DataListDTO("", "BI", "BUU"));
       response.add(new DataListDTO("", "BN", "BRU"));
       response.add(new DataListDTO("", "BG", "BUL"));
       response.add(new DataListDTO("", "VG", "BVI"));
       response.add(new DataListDTO("", "CM", "CAM"));
       response.add(new DataListDTO("", "KY", "CAY"));
       response.add(new DataListDTO("", "TD", "CHA"));
       response.add(new DataListDTO("", "CL", "CHI"));
       response.add(new DataListDTO("", "CN", "CHP"));
       response.add(new DataListDTO("", "CC", "COC"));
       response.add(new DataListDTO("", "CG", "CON"));
       response.add(new DataListDTO("", "CK", "COO"));
       response.add(new DataListDTO("", "CW", "CUR"));
       response.add(new DataListDTO("", "CV", "CVE"));
       response.add(new DataListDTO("", "CS", "CZE"));
       response.add(new DataListDTO("", "DK", "DEN"));
       response.add(new DataListDTO("", "DO", "DRE"));
       response.add(new DataListDTO("", "AE", "DUB"));
       response.add(new DataListDTO("", "EG", "EGI"));
       response.add(new DataListDTO("", "SV", "ELS"));
       response.add(new DataListDTO("", "GQ", "EQG"));    
       response.add(new DataListDTO("", "FK", "FAL"));
       response.add(new DataListDTO("", "GF", "FGU"));
       response.add(new DataListDTO("", "FJ", "FIJ"));
       response.add(new DataListDTO("", "PF", "FPO"));
       response.add(new DataListDTO("", "AE", "FUJ"));
       response.add(new DataListDTO("", "GM", "GAM"));
       response.add(new DataListDTO("", "GD", "GRA"));
       response.add(new DataListDTO("", "GR", "GRE"));
       response.add(new DataListDTO("", "GL", "GRN"));
       response.add(new DataListDTO("", "GP", "GUA"));
       response.add(new DataListDTO("", "GW", "GUB"));
       response.add(new DataListDTO("", "GN", "GUI"));
       response.add(new DataListDTO("", "GT", "GUT"));
       response.add(new DataListDTO("", "HT", "HAI"));
       response.add(new DataListDTO("", "HM", "HEA"));
       response.add(new DataListDTO("", "NL", "HOL"));
       response.add(new DataListDTO("", "HN", "HON"));
       response.add(new DataListDTO("", "IS", "ICE"));
       response.add(new DataListDTO("", "CI", "ICO"));
       response.add(new DataListDTO("", "ID", "INO"));
       response.add(new DataListDTO("", "IR", "IRA"));
       response.add(new DataListDTO("", "IE", "IRE"));
       response.add(new DataListDTO("", "JP", "JAP"));
       response.add(new DataListDTO("", "UM", "JOH"));
       response.add(new DataListDTO("", "KH", "KAM"));
       response.add(new DataListDTO("", "KP", "KON"));
       response.add(new DataListDTO("", "KW", "KUW"));
       response.add(new DataListDTO("", "LB", "LEB"));
       response.add(new DataListDTO("", "LS", "LES"));
       response.add(new DataListDTO("", "LR", "LIB"));
       response.add(new DataListDTO("", "LY", "LIY"));
       response.add(new DataListDTO("", "MY", "MAA"));
       response.add(new DataListDTO("", "MV", "MAD"));
       response.add(new DataListDTO("", "MG", "MAG"));
       response.add(new DataListDTO("", "ML", "MAI"));
       response.add(new DataListDTO("", "MT", "MAL"));
       response.add(new DataListDTO("", "MR", "MAT"));
       response.add(new DataListDTO("", "MU", "MAU"));
       response.add(new DataListDTO("", "MW", "MAW"));
       response.add(new DataListDTO("", "UM", "MID"));
       response.add(new DataListDTO("", "MN", "MOG"));
       response.add(new DataListDTO("", "MC", "MON"));
       response.add(new DataListDTO("", "MA", "MOR"));
       response.add(new DataListDTO("", "MS", "MOT"));
       response.add(new DataListDTO("", "NR", "NAU"));
       response.add(new DataListDTO("", "AN", "NEA"));
       response.add(new DataListDTO("", "NC", "NEW"));
       response.add(new DataListDTO("", "NZ", "NEZ"));
       response.add(new DataListDTO("", "NG", "NIG"));
       response.add(new DataListDTO("", "NF", "NOF"));
       response.add(new DataListDTO("", "OM", "OMA"));
       response.add(new DataListDTO("", "PG", "PAP"));
       response.add(new DataListDTO("", "PY", "PAR"));
       response.add(new DataListDTO("", "PA", "PCZ"));
       response.add(new DataListDTO("", "PH", "PHI"));
       response.add(new DataListDTO("", "PN", "PIT"));
       response.add(new DataListDTO("", "PT", "POR"));
       response.add(new DataListDTO("", "AE", "RAS"));
       response.add(new DataListDTO("", "RO", "ROM"));
       response.add(new DataListDTO("", "ZA", "SAF"));
       response.add(new DataListDTO("", "WS", "SAM"));
       response.add(new DataListDTO("", "SM", "SAN"));
       response.add(new DataListDTO("", "ST", "SAT"));
       response.add(new DataListDTO("", "SC", "SEY"));
       response.add(new DataListDTO("", "AE", "SHA"));
       response.add(new DataListDTO("", "SG", "SIN"));
       response.add(new DataListDTO("", "SB", "SOL"));
       response.add(new DataListDTO("", "LK", "SRI"));
       response.add(new DataListDTO("", "SH", "STH"));
       response.add(new DataListDTO("", "KN", "STK"));
       response.add(new DataListDTO("", "LC", "STL"));
       response.add(new DataListDTO("", "VC", "STV"));
       response.add(new DataListDTO("", "SD", "SUD"));
       response.add(new DataListDTO("", "SJ", "SVA"));
       response.add(new DataListDTO("", "SZ", "SWA"));
       response.add(new DataListDTO("", "CH", "SWI"));
       response.add(new DataListDTO("", "TZ", "TAN"));
       response.add(new DataListDTO("", "TW", "TAW"));
       response.add(new DataListDTO("", "TG", "TOG"));
       response.add(new DataListDTO("", "TK", "TOK"));
       response.add(new DataListDTO("", "TT", "TRI"));
       response.add(new DataListDTO("", "TA", "TRS"));
       response.add(new DataListDTO("", "TC", "TUK"));
       response.add(new DataListDTO("", "GB", "UKI"));
       response.add(new DataListDTO("", "AE", "UMM"));
       response.add(new DataListDTO("", "HV", "UPV"));
       response.add(new DataListDTO("", "UY", "URU"));
       response.add(new DataListDTO("", "UM", "USP"));
       response.add(new DataListDTO("", "PC", "UST"));
       response.add(new DataListDTO("", "VI", "USV"));
       response.add(new DataListDTO("", "VU", "VAN"));
       response.add(new DataListDTO("", "VN", "VIE"));
       response.add(new DataListDTO("", "UM", "WAK"));
       response.add(new DataListDTO("", "WF", "WAL"));
       response.add(new DataListDTO("", "EH", "WSA"));
       response.add(new DataListDTO("", "YE", "YEP"));
       response.add(new DataListDTO("", "ZR", "ZAI"));
       response.add(new DataListDTO("", "ZM", "ZAM"));
       response.add(new DataListDTO("", "ZW", "ZIM"));
       response.add(new DataListDTO("", "IO", "BOC"));
       response.add(new DataListDTO("", "FO", "FAE"));
       response.add(new DataListDTO("", "MM", "BUR"));
       response.add(new DataListDTO("", "SK", "SVK"));
       
              
   
        return response;
    }
    private static List<DataListDTO> originalTableByCode() {

        List<DataListDTO> response = new ArrayList<>();

        response.add(new DataListDTO("", "AE", "ABU"));
        response.add(new DataListDTO("", "AF", "AFG"));
        response.add(new DataListDTO("", "AE", "AJM"));
        response.add(new DataListDTO("", "AL", "ALB"));
        response.add(new DataListDTO("", "DE", "ALE"));
        response.add(new DataListDTO("", "DZ", "ALG"));
        response.add(new DataListDTO("", "AD", "AND"));
        response.add(new DataListDTO("", "AO", "ANG"));
        response.add(new DataListDTO("", "AQ", "ANR"));
        response.add(new DataListDTO("", "AN", "ANT"));
        response.add(new DataListDTO("", "AR", "ARG"));
        response.add(new DataListDTO("", "AW", "ARU"));       
        response.add(new DataListDTO("", "AC", "ASC"));
        response.add(new DataListDTO("", "AU", "AUS"));
        response.add(new DataListDTO("", "AT", "AUT"));
        response.add(new DataListDTO("", "BS", "BAH"));
        response.add(new DataListDTO("", "BH", "BAI"));
        response.add(new DataListDTO("", "BD", "BAN"));
        response.add(new DataListDTO("", "BB", "BAR"));
        response.add(new DataListDTO("", "BZ", "BEI"));
        response.add(new DataListDTO("", "BE", "BEL"));
        response.add(new DataListDTO("", "BJ", "BEN"));
        response.add(new DataListDTO("", "BM", "BER"));
        response.add(new DataListDTO("", "BT", "BHU"));
        response.add(new DataListDTO("", "IO", "BIN"));
        response.add(new DataListDTO("", "IO", "BOC"));
        response.add(new DataListDTO("", "BO", "BOL"));
        response.add(new DataListDTO("", "BW", "BOT"));
        response.add(new DataListDTO("", "BR", "BRA"));
        response.add(new DataListDTO("", "BN", "BRU"));
        response.add(new DataListDTO("", "BG", "BUL"));
        response.add(new DataListDTO("", "BI", "BUU"));
        response.add(new DataListDTO("", "VG", "BVI"));
        response.add(new DataListDTO("", "CF", "CAF"));
        response.add(new DataListDTO("", "CM", "CAM"));
        response.add(new DataListDTO("", "CA", "CAN"));
        response.add(new DataListDTO("", "KY", "CAY"));
        response.add(new DataListDTO("", "TD", "CHA"));
        response.add(new DataListDTO("", "CH", "CHE"));
        response.add(new DataListDTO("", "CL", "CHI"));
        response.add(new DataListDTO("", "CN", "CHN"));
        response.add(new DataListDTO("", "CN", "CHP"));
        response.add(new DataListDTO("", "CC", "COC"));
        response.add(new DataListDTO("", "CO", "COL"));
        response.add(new DataListDTO("", "KM", "COM"));
        response.add(new DataListDTO("", "CG", "CON"));
        response.add(new DataListDTO("", "CK", "COO"));
        response.add(new DataListDTO("", "CR", "CRI"));
        response.add(new DataListDTO("", "CU", "CUB"));
        response.add(new DataListDTO("", "CW", "CUR"));
        response.add(new DataListDTO("", "CV", "CVE"));
        response.add(new DataListDTO("", "CY", "CYP"));
        response.add(new DataListDTO("", "CS", "CZE"));
        response.add(new DataListDTO("", "DK", "DEN"));
        response.add(new DataListDTO("", "DJ", "DJI"));
        response.add(new DataListDTO("", "DO", "DOM"));
        response.add(new DataListDTO("", "DO", "DRE"));
        response.add(new DataListDTO("", "AE", "DUB"));
        response.add(new DataListDTO("", "EC", "ECU"));
        response.add(new DataListDTO("", "EG", "EGI"));
        response.add(new DataListDTO("", "SV", "ELS"));
        response.add(new DataListDTO("", "GQ", "EQG"));
        response.add(new DataListDTO("", "ES", "ESP"));
        response.add(new DataListDTO("", "ET", "ETH"));
        response.add(new DataListDTO("", "EU", "EUR"));
        response.add(new DataListDTO("", "ES", "EXT"));
        response.add(new DataListDTO("", "FO", "FAE"));
        response.add(new DataListDTO("", "FK", "FAL"));
        response.add(new DataListDTO("", "GF", "FGU"));
        response.add(new DataListDTO("", "FJ", "FIJ"));
        response.add(new DataListDTO("", "FI", "FIN"));
        response.add(new DataListDTO("", "PF", "FPO"));
        response.add(new DataListDTO("", "FR", "FRA"));
        response.add(new DataListDTO("", "AE", "FUJ"));
        response.add(new DataListDTO("", "GA", "GAB"));
        response.add(new DataListDTO("", "GM", "GAM"));
        response.add(new DataListDTO("", "GH", "GHA"));
        response.add(new DataListDTO("", "GI", "GIB"));
        response.add(new DataListDTO("", "GD", "GRA"));
        response.add(new DataListDTO("", "GR", "GRE"));
        response.add(new DataListDTO("", "GL", "GRN"));
        response.add(new DataListDTO("", "GP", "GUA"));
        response.add(new DataListDTO("", "GW", "GUB"));
        response.add(new DataListDTO("", "GN", "GUI"));
        response.add(new DataListDTO("", "GU", "GUM"));
        response.add(new DataListDTO("", "GT", "GUT"));
        response.add(new DataListDTO("", "GY", "GUY"));
        response.add(new DataListDTO("", "HT", "HAI"));
        response.add(new DataListDTO("", "HM", "HEA"));
        response.add(new DataListDTO("", "HK", "HKG"));
        response.add(new DataListDTO("", "NL", "HOL"));
        response.add(new DataListDTO("", "HN", "HON"));
        response.add(new DataListDTO("", "HU", "HUN"));
        response.add(new DataListDTO("", "IS", "ICE"));
        response.add(new DataListDTO("", "CI", "ICO"));
        response.add(new DataListDTO("", "IN", "IND"));
        response.add(new DataListDTO("", "ID", "INO"));
        response.add(new DataListDTO("", "IR", "IRA"));
        response.add(new DataListDTO("", "IE", "IRE"));
        response.add(new DataListDTO("", "IQ", "IRQ"));
        response.add(new DataListDTO("", "IL", "ISR"));
        response.add(new DataListDTO("", "IT", "ITA"));
        response.add(new DataListDTO("", "JM", "JAM"));
        response.add(new DataListDTO("", "JP", "JAP"));
        response.add(new DataListDTO("", "UM", "JOH"));
        response.add(new DataListDTO("", "JO", "JOR"));
        response.add(new DataListDTO("", "KH", "KAM"));
        response.add(new DataListDTO("", "KE", "KEN"));
        response.add(new DataListDTO("", "KI", "KIR"));
        response.add(new DataListDTO("", "KP", "KON"));
        response.add(new DataListDTO("", "KR", "KOR"));
        response.add(new DataListDTO("", "KW", "KUW"));
        response.add(new DataListDTO("", "LA", "LAO"));
        response.add(new DataListDTO("", "LB", "LEB"));
        response.add(new DataListDTO("", "LS", "LES"));
        response.add(new DataListDTO("", "LR", "LIB"));
        response.add(new DataListDTO("", "LI", "LIE"));
        response.add(new DataListDTO("", "LY", "LIY"));
        response.add(new DataListDTO("", "LU", "LUX"));
        response.add(new DataListDTO("", "MY", "MAA"));
        response.add(new DataListDTO("", "MO", "MAC"));
        response.add(new DataListDTO("", "MV", "MAD"));
        response.add(new DataListDTO("", "MG", "MAG"));
        response.add(new DataListDTO("", "ML", "MAI"));
        response.add(new DataListDTO("", "MT", "MAL"));
        response.add(new DataListDTO("", "MA", "MAR"));
        response.add(new DataListDTO("", "MR", "MAT"));
        response.add(new DataListDTO("", "MU", "MAU"));
        response.add(new DataListDTO("", "MW", "MAW"));
        response.add(new DataListDTO("", "MX", "MEX"));
        response.add(new DataListDTO("", "UM", "MID"));
        response.add(new DataListDTO("", "ES", "MOE"));
        response.add(new DataListDTO("", "MN", "MOG"));
        response.add(new DataListDTO("", "MC", "MON"));
        response.add(new DataListDTO("", "MA", "MOR"));
        response.add(new DataListDTO("", "MS", "MOT"));
        response.add(new DataListDTO("", "MZ", "MOZ"));
        response.add(new DataListDTO("", "NA", "NAM"));
        response.add(new DataListDTO("", "NR", "NAU"));
        response.add(new DataListDTO("", "AN", "NEA"));
        response.add(new DataListDTO("", "NC", "NEW"));
        response.add(new DataListDTO("", "NZ", "NEZ"));
        response.add(new DataListDTO("", "NI", "NIC"));
        response.add(new DataListDTO("", "NG", "NIG"));
        response.add(new DataListDTO("", "NU", "NIU"));
        response.add(new DataListDTO("", "NF", "NOF"));
        response.add(new DataListDTO("", "NO", "NOR"));
        response.add(new DataListDTO("", "OM", "OMA"));
        response.add(new DataListDTO("", "PK", "PAK"));
        response.add(new DataListDTO("", "PA", "PAN"));
        response.add(new DataListDTO("", "PG", "PAP"));
        response.add(new DataListDTO("", "PY", "PAR"));
        response.add(new DataListDTO("", "PA", "PCZ"));
        response.add(new DataListDTO("", "PH", "PHI"));
        response.add(new DataListDTO("", "PN", "PIT"));
        response.add(new DataListDTO("", "PL", "POL"));
        response.add(new DataListDTO("", "PT", "POR"));
        response.add(new DataListDTO("", "PR", "PRI"));
        response.add(new DataListDTO("", "QA", "QAT"));
        response.add(new DataListDTO("", "AE", "RAS"));
        response.add(new DataListDTO("", "RE", "REU"));
        response.add(new DataListDTO("", "RO", "ROM"));
        response.add(new DataListDTO("", "RU", "RUS"));
        response.add(new DataListDTO("", "RW", "RWA"));
        response.add(new DataListDTO("", "ZA", "SAF"));
        response.add(new DataListDTO("", "WS", "SAM"));
        response.add(new DataListDTO("", "SM", "SAN"));
        response.add(new DataListDTO("", "ST", "SAT"));
        response.add(new DataListDTO("", "SA", "SAU"));
        response.add(new DataListDTO("", "SN", "SEN"));
        response.add(new DataListDTO("", "SC", "SEY"));
        response.add(new DataListDTO("", "AE", "SHA"));
        response.add(new DataListDTO("", "SG", "SIN"));
        response.add(new DataListDTO("", "SL", "SLE"));
        response.add(new DataListDTO("", "SB", "SOL"));
        response.add(new DataListDTO("", "SO", "SOM"));
        response.add(new DataListDTO("", "LK", "SRI"));
        response.add(new DataListDTO("", "SH", "STH"));
        response.add(new DataListDTO("", "KN", "STK"));
        response.add(new DataListDTO("", "LC", "STL"));
        response.add(new DataListDTO("", "ST", "STP"));
        response.add(new DataListDTO("", "VC", "STV"));
        response.add(new DataListDTO("", "SD", "SUD"));
        response.add(new DataListDTO("", "SR", "SUR"));
        response.add(new DataListDTO("", "SJ", "SVA"));
        response.add(new DataListDTO("", "SZ", "SWA"));
        response.add(new DataListDTO("", "SE", "SWE"));
        response.add(new DataListDTO("", "CH", "SWI"));
        response.add(new DataListDTO("", "SY", "SYR"));
        response.add(new DataListDTO("", "TZ", "TAN"));
        response.add(new DataListDTO("", "TW", "TAW"));
        response.add(new DataListDTO("", "TH", "THA"));
        response.add(new DataListDTO("", "TG", "TOG"));
        response.add(new DataListDTO("", "TK", "TOK"));
        response.add(new DataListDTO("", "TO", "TON"));
        response.add(new DataListDTO("", "TT", "TRI"));
        response.add(new DataListDTO("", "TA", "TRS"));
        response.add(new DataListDTO("", "TC", "TUK"));
        response.add(new DataListDTO("", "TN", "TUN"));
        response.add(new DataListDTO("", "TR", "TUR"));
        response.add(new DataListDTO("", "TV", "TUV"));
        response.add(new DataListDTO("", "UG", "UGA"));
        response.add(new DataListDTO("", "GB", "UKI"));
        response.add(new DataListDTO("", "AE", "UMM"));
        response.add(new DataListDTO("", "HV", "UPV"));
        response.add(new DataListDTO("", "UY", "URU"));
        response.add(new DataListDTO("", "US", "USA"));
        response.add(new DataListDTO("", "UM", "USP"));
        response.add(new DataListDTO("", "PC", "UST"));
        response.add(new DataListDTO("", "VI", "USV"));
        response.add(new DataListDTO("", "VU", "VAN"));
        response.add(new DataListDTO("", "VA", "VAT"));
        response.add(new DataListDTO("", "VE", "VEN"));
        response.add(new DataListDTO("", "VN", "VIE"));
        response.add(new DataListDTO("", "UM", "WAK"));
        response.add(new DataListDTO("", "WF", "WAL"));
        response.add(new DataListDTO("", "EH", "WSA"));
        response.add(new DataListDTO("", "YE", "YEM"));
        response.add(new DataListDTO("", "YE", "YEP"));
        response.add(new DataListDTO("", "YU", "YUG"));
        response.add(new DataListDTO("", "ZR", "ZAI"));
        response.add(new DataListDTO("", "ZM", "ZAM"));
        response.add(new DataListDTO("", "ZW", "ZIM"));
        response.add(new DataListDTO("", "SK", "SVK"));
        response.add(new DataListDTO("", "AS", "ASA"));
        response.add(new DataListDTO("", "PE", "PER"));
        response.add(new DataListDTO("", "MM", "BUR"));

        return response;
    }

    public static String getIsoAlphaCodeByCountryCode(String countryCode) {

        List<DataListDTO> departmentList = getCountryRelTable().stream().filter(x -> x.getCode().equals(countryCode))
                .toList();

        if (departmentList.isEmpty())
            return "";

        return departmentList.get(0).getDescription();

    }
    public static String getInternalByCountryCode(String countryCode) {

        List<DataListDTO> originalList = originalTableByCode().stream().filter(x -> x.getCode().equals(countryCode))
                .collect(Collectors.toList());

        if (originalList.isEmpty())
            return "";

        return originalList.get(0).getDescription();

    }
    
}



********************


package com.santander.bnc.bsn049.bncbsn049mscountries.utils;

/**
 * Global Utils
 */
public class GUtils {
    private GUtils() {}
    /**
     * Start log
     */
    public final static String SLOG = "--> Start ";

    /**
     * End log
     */
    public final static String ELOG = "<-- End ";
}

**********************************


package com.santander.bnc.bsn049.bncbsn049mscountries.utils;

import com.santander.bnc.bsn049.bncbsn049mscountries.domain.CountryDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.DataListDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.StateDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.TownsDTO;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.StatesResponse;
import com.santander.bnc.bsn049.bncbsn049mscountries.domain.response.TownsResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * Handle all Params utils
 */
public class ParamsUtils {
    private ParamsUtils() {}
    /**
     * Build Towns Response
     * @param responseState
     * @param responseTowns
     * @return
     */
    public static TownsResponse formatTownsResponse(List<DataListDTO> responseState, List<TownsDTO> responseTowns){

        if(responseTowns != null && !responseTowns.isEmpty()){
            CountryDTO country = new CountryDTO("CO", "Colombia", null,null);
            List<TownsDTO> towns = new ArrayList<>();
            List<StateDTO> states = new ArrayList<>();
            responseState.forEach(st ->
                states.add(new StateDTO(st.getCode(), st.getDescription()))
            );
            responseTowns.forEach(tw ->
                towns.add(new TownsDTO(tw.getListCode(), tw.getCode(), tw.getDescription(), states))
            );
            return new TownsResponse(country,towns);
        }
        return null;
    }//method closure

    /**
     * DataList List ---> States Response
     * @param response
     * @return
     */
    public static StatesResponse formaStatesResponse(List<DataListDTO> response){

        if(response != null && !response.isEmpty()){
            CountryDTO country = new CountryDTO("CO", "Colombia", null,null);
            List<StateDTO> states = new ArrayList<>();

            response.forEach(st ->
                states.add(new StateDTO(st.getCode(), st.getDescription()))
            );

            return new StatesResponse(country, states);
        }
        return null;
    }//method closure


    public static Boolean stringContainNumbers(String str){
        if(str == null) return false;
        if(str.isBlank()) return false;

        char[] chars = str.toCharArray();        
        for(char c : chars){
            if(Character.isDigit(c)){
                return true;
            }
        }        
            return false;
    }

}//class closure
