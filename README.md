package com.santander.bnc.bsn049.bncbsn049mscstmrcntctpnts.domain.host.person.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EconomyActivityTest {

    @Test
    void shouldCoverAllGettersAndSetters() {

        EconomyActivity dto = new EconomyActivity();

        dto.setTipoVia("TV");
        dto.setDepartamento("01");
        dto.setTipoContrato("FIJO");
        dto.setPaisDescripcion("CO");
        dto.setCiudadDescripcion("BOG");

        dto.setOcupacion("DEV");
        dto.setDescOcupacion("Developer");

        dto.setActiEconomica("6201");
        dto.setDescActEconomica("Software");

        dto.setAntiguedadAnio("5");
        dto.setAntiguedadMes("6");

        dto.setNombreEmpresa("Santander");
        dto.setNit("900123");

        dto.setFechaIngreso("2020-01-01");
        dto.setFecha2("2024-01-01");

        dto.setNombreVia("CALLE 1");
        dto.setDescripcionDireccion("TORRE A");

        dto.setIndicativo("57");
        dto.setOpcionActividad("EMP");

        dto.setNumdoc("123");
        dto.setNumper("456");
        dto.setSecdoc("789");

        dto.setCargo("DEV");
        dto.setDescCargo("Developer Senior");

        dto.setPais("CO");
        dto.setCiudad("11001");
        dto.setTelefono("3001234567");
        dto.setTipdoc("CC");

        assertEquals("TV", dto.getTipoVia());
        assertEquals("01", dto.getDepartamento());
        assertEquals("FIJO", dto.getTipoContrato());
        assertEquals("CO", dto.getPaisDescripcion());
        assertEquals("BOG", dto.getCiudadDescripcion());

        assertEquals("DEV", dto.getOcupacion());
        assertEquals("Developer", dto.getDescOcupacion());

        assertEquals("6201", dto.getActiEconomica());
        assertEquals("Software", dto.getDescActEconomica());

        assertEquals("5", dto.getAntiguedadAnio());
        assertEquals("6", dto.getAntiguedadMes());

        assertEquals("Santander", dto.getNombreEmpresa());
        assertEquals("900123", dto.getNit());

        assertEquals("2020-01-01", dto.getFechaIngreso());
        assertEquals("2024-01-01", dto.getFecha2());

        assertEquals("CALLE 1", dto.getNombreVia());
        assertEquals("TORRE A", dto.getDescripcionDireccion());

        assertEquals("57", dto.getIndicativo());
        assertEquals("EMP", dto.getOpcionActividad());

        assertEquals("123", dto.getNumdoc());
        assertEquals("456", dto.getNumper());
        assertEquals("789", dto.getSecdoc());

        assertEquals("DEV", dto.getCargo());
        assertEquals("Developer Senior", dto.getDescCargo());

        assertEquals("CO", dto.getPais());
        assertEquals("11001", dto.getCiudad());
        assertEquals("3001234567", dto.getTelefono());
        assertEquals("CC", dto.getTipdoc());
    }

    @Test
    void shouldCoverBuilderAndConstructors() {

        EconomyActivity dto = EconomyActivity.builder()
                .cargo("DEV")
                .nombreEmpresa("Santander")
                .build();

        assertEquals("DEV", dto.getCargo());
        assertEquals("Santander", dto.getNombreEmpresa());

        EconomyActivity dto2 = new EconomyActivity();

        assertNotNull(dto2);
    }
}