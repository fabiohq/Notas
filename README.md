Java
package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientEnumTest {

    @Test
    void valuesShouldReturnExpectedPaths() {
        assertEquals("ingresoAltaPersonaNatural", ClientEnum.PEF1.value());
        assertEquals("modificarMantencionPersonaNaturalDatosBasicos", ClientEnum.PEF2.value());
        assertEquals("ConsultaDatosBasicosPNatural", ClientEnum.PEF3.value());
        assertEquals("modificarMantencionPersonaNaturalReferencias", ClientEnum.PEFT.value());
        assertEquals("modificarMantencionPersonaNaturalInfAdicional", ClientEnum.PEFP.value());
        assertEquals("modificarMantencionPersonaNaturalInfAdicional", ClientEnum.PEFV.value());
        assertEquals("AltaPersonaNatural2", ClientEnum.PE37.value());
        assertEquals("modificarMantencionPersonaNaturalInfComplementariaDos", ClientEnum.PEF4.value());
        assertEquals("modificarMantencionPersonaNaturalActivivadEconomica2", ClientEnum.PEF8.value());
        assertEquals("QCTFD", ClientEnum.MQROUTE.value());
    }

    @Test
    void enumShouldContainAllValues() {
        assertEquals(10, ClientEnum.values().length);

        assertNotNull(ClientEnum.valueOf("PEF1"));
        assertNotNull(ClientEnum.valueOf("PEF2"));
        assertNotNull(ClientEnum.valueOf("PEF3"));
        assertNotNull(ClientEnum.valueOf("PEFT"));
        assertNotNull(ClientEnum.valueOf("PEFP"));
        assertNotNull(ClientEnum.valueOf("PEFV"));
        assertNotNull(ClientEnum.valueOf("PE37"));
        assertNotNull(ClientEnum.valueOf("PEF4"));
        assertNotNull(ClientEnum.valueOf("PEF8"));
        assertNotNull(ClientEnum.valueOf("MQROUTE"));
    }

    @Test
    void enumNamesShouldMatch() {
        assertEquals("PEF1", ClientEnum.PEF1.name());
        assertEquals("MQROUTE", ClientEnum.MQROUTE.name());
    }
}
Java
package com.santander.bnc.bsn049.bncbsn049msprospects.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersEnumsTest {

    @Test
    void valuesShouldReturnExpectedCodes() {
        assertEquals("0008", ParametersEnums.TOWNS.value());
        assertEquals("0112", ParametersEnums.COUNTRY.value());
        assertEquals("0009", ParametersEnums.STATES.value());
        assertEquals("0314", ParametersEnums.WAY_TYPE.value());
        assertEquals("0116", ParametersEnums.CIVIL_STATE.value());
        assertEquals("0026", ParametersEnums.LIST_BCO_EXT.value());
        assertEquals("0113", ParametersEnums.DOCU_TYPE.value());
    }

    @Test
    void enumShouldContainAllValues() {
        assertEquals(7, ParametersEnums.values().length);

        assertNotNull(ParametersEnums.valueOf("TOWNS"));
        assertNotNull(ParametersEnums.valueOf("COUNTRY"));
        assertNotNull(ParametersEnums.valueOf("STATES"));
        assertNotNull(ParametersEnums.valueOf("WAY_TYPE"));
        assertNotNull(ParametersEnums.valueOf("CIVIL_STATE"));
        assertNotNull(ParametersEnums.valueOf("LIST_BCO_EXT"));
        assertNotNull(ParametersEnums.valueOf("DOCU_TYPE"));
    }

    @Test
    void enumNamesShouldMatch() {
        assertEquals("COUNTRY", ParametersEnums.COUNTRY.name());
        assertEquals("DOCU_TYPE", ParametersEnums.DOCU_TYPE.name());
    }
}