package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.pepf.TrxPEPFDataResponse.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PepfPEMFV0AResponseDTOTest {

    @Test
    void shouldCoverNoArgsConstructorSettersAndGetters() {
        PepfPEMFV0AResponseDTO dto = new PepfPEMFV0AResponseDTO();

        dto.setOficial("oficial");
        dto.setIdnoapl("idnoapl");
        dto.setIdsecer("idsecer");
        dto.setHonorar("honorar");
        dto.setReporta("reporta");
        dto.setSalario("salario");
        dto.setSecdoc("secdoc");
        dto.setSitcln("sitcln");
        dto.setPreserv("preserv");
        dto.setNroidt2("nroidt2");
        dto.setNroidt3("nroidt3");
        dto.setPension("pension");
        dto.setNroidt1("nroidt1");
        dto.setActind("actind");
        dto.setNumdoc("numdoc");
        dto.setUnineg("unineg");
        dto.setArriend("arriend");
        dto.setCanvta("canvta");
        dto.setSucural("sucural");
        dto.setIdcreco("idcreco");
        dto.setPaire01("paire01");
        dto.setAutcome("autcome");
        dto.setPaire02("paire02");
        dto.setPaire03("paire03");
        dto.setIdcrs("idcrs");
        dto.setDonhere("donhere");
        dto.setIdprepu("idprepu");
        dto.setIdfatca("idfatca");
        dto.setOfotro("ofotro");
        dto.setNumper("numper");
        dto.setMesada("mesada");
        dto.setTipdoc("tipdoc");
        dto.setFeccln("feccln");
        dto.setIdppexp("idppexp");
        dto.setIdpexpo("idpexpo");

        assertEquals("oficial", dto.getOficial());
        assertEquals("idnoapl", dto.getIdnoapl());
        assertEquals("idsecer", dto.getIdsecer());
        assertEquals("honorar", dto.getHonorar());
        assertEquals("reporta", dto.getReporta());
        assertEquals("salario", dto.getSalario());
        assertEquals("secdoc", dto.getSecdoc());
        assertEquals("sitcln", dto.getSitcln());
        assertEquals("preserv", dto.getPreserv());
        assertEquals("nroidt2", dto.getNroidt2());
        assertEquals("nroidt3", dto.getNroidt3());
        assertEquals("pension", dto.getPension());
        assertEquals("nroidt1", dto.getNroidt1());
        assertEquals("actind", dto.getActind());
        assertEquals("numdoc", dto.getNumdoc());
        assertEquals("unineg", dto.getUnineg());
        assertEquals("arriend", dto.getArriend());
        assertEquals("canvta", dto.getCanvta());
        assertEquals("sucural", dto.getSucural());
        assertEquals("idcreco", dto.getIdcreco());
        assertEquals("paire01", dto.getPaire01());
        assertEquals("autcome", dto.getAutcome());
        assertEquals("paire02", dto.getPaire02());
        assertEquals("paire03", dto.getPaire03());
        assertEquals("idcrs", dto.getIdcrs());
        assertEquals("donhere", dto.getDonhere());
        assertEquals("idprepu", dto.getIdprepu());
        assertEquals("idfatca", dto.getIdfatca());
        assertEquals("ofotro", dto.getOfotro());
        assertEquals("numper", dto.getNumper());
        assertEquals("mesada", dto.getMesada());
        assertEquals("tipdoc", dto.getTipdoc());
        assertEquals("feccln", dto.getFeccln());
        assertEquals("idppexp", dto.getIdppexp());
        assertEquals("idpexpo", dto.getIdpexpo());
    }

    @Test
    void shouldCoverBuilder() {
        PepfPEMFV0AResponseDTO dto = PepfPEMFV0AResponseDTO.builder()
                .oficial("oficial")
                .idnoapl("idnoapl")
                .idpexpo("idpexpo")
                .build();

        assertNotNull(dto);
        assertEquals("oficial", dto.getOficial());
        assertEquals("idnoapl", dto.getIdnoapl());
        assertEquals("idpexpo", dto.getIdpexpo());
    }

    @Test
    void shouldCoverAllArgsConstructor() {
        PepfPEMFV0AResponseDTO dto = new PepfPEMFV0AResponseDTO(
                "oficial", "idnoapl", "idsecer", "honorar", "reporta",
                "salario", "secdoc", "sitcln", "preserv", "nroidt2",
                "nroidt3", "pension", "nroidt1", "actind", "numdoc",
                "unineg", "arriend", "canvta", "sucural", "idcreco",
                "paire01", "autcome", "paire02", "paire03", "idcrs",
                "donhere", "idprepu", "idfatca", "ofotro", "numper",
                "mesada", "tipdoc", "feccln", "idppexp", "idpexpo"
        );

        assertNotNull(dto);
        assertEquals("oficial", dto.getOficial());
        assertEquals("idpexpo", dto.getIdpexpo());
    }
}