package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

public enum ClientEnum {
    PEF1("ingresoAltaPersonaNatural"),
    PEF2("modificarMantencionPersonaNaturalDatosBasicos"),
    PEF3("ConsultaDatosBasicosPNatural"),
    PEFT("modificarMantencionPersonaNaturalReferencias"),
    PEFP("modificarMantencionPersonaNaturalInfAdicional"),
    PEFV("modificarMantencionPersonaNaturalInfAdicional"),
    PE37("AltaPersonaNatural2"),
    PEF4("modificarMantencionPersonaNaturalInfComplementariaDos"),
    PEF8("modificarMantencionPersonaNaturalActivivadEconomica2"),
    
    MQROUTE("QCTFD");

    private final String path;

    ClientEnum(String path) {
        this.path = path;
    }

    public String value() {
        return path;
    }
}

package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

public enum ParametersEnums {
    TOWNS("0008"),
    COUNTRY("0112"),
    STATES("0009"),
    WAY_TYPE("0314"),
    CIVIL_STATE("0116"),
    LIST_BCO_EXT("0026"),
    DOCU_TYPE("0113");

    private final String code;

    ParametersEnums(String path) {
        this.code = path;
    }

    public String value() {
        return code;
    }
}

