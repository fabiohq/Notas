package com.santander.bnc.bsn049.bncbsn049mscountries.enums;
public enum ParametersEnums {
    TOWNS("0008"),
    STATES("0009"),
    WAY_TYPE("0314"),
    CIVIL_STATE("0116"),
    LIST_EMPLACEMENT("0305"),
    LIST_BCO_EXT("0026"),
    DOCU_TYPE("0113"),
    COUNTRY("0112");

    private final String code;

    ParametersEnums(String path) {
        this.code = path;
    }

    public String value() {
        return code;
    }

    public static Boolean isValidEnumValue(String value){        

        if(value == null) return false;

        return value.contains(TOWNS.value()) ||
                value.contains(STATES.value()) ||
                value.contains(WAY_TYPE.value()) ||
                value.contains(CIVIL_STATE.value()) ||
                value.contains(LIST_EMPLACEMENT.value()) ||
                value.contains(LIST_BCO_EXT.value()) ||
                value.contains(DOCU_TYPE.value()) ||
                value.contains(COUNTRY.value());

    }
}
