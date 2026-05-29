package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.request.TrxBP14Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14BGMP140Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.bp14.response.TrxBP14Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.integration.ApiEntry;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.OtherSourceRequestDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.SourceFundsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.request.TermDepositFundsRequestDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.SourceFundResponseDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.StatusInfoResponseDto;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain.termdepositfunds.response.TermDepositFundsResponseDto;

class DomainTest {

    @Test
    void testTrxBP14DataRequest() {
        TrxBP14DataRequest dto = new TrxBP14DataRequest();

        dto.setCodigoInversor("codigoInversor");
        dto.setSecuenciaIpf("secuenciaIpf");
        dto.setFormaDePagoIpf("formaDePagoIpf");
        dto.setValorImporte("valorImporte");
        dto.setCccDeCargo("cccDeCargo");
        dto.setTipoDeDocumento("tipoDeDocumento");
        dto.setNumeroDeDocumento("numeroDeDocumento");
        dto.setBancoDelDocumento("bancoDelDocumento");
        dto.setCentroDelDocumento("centroDelDocumento");
        dto.setCuentaDelDocumento("cuentaDelDocumento");
        dto.setPlazaDelDocumento("plazaDelDocumento");
        dto.setResponsableImpuesto("responsableImpuesto");
        dto.setTipoIdentif("tipoIdentif");
        dto.setNumeroDocumento("numeroDocumento");
        dto.setNombrePerson("nombrePerson");

        assertEquals("codigoInversor", dto.getCodigoInversor());
        assertEquals("secuenciaIpf", dto.getSecuenciaIpf());
        assertEquals("formaDePagoIpf", dto.getFormaDePagoIpf());
        assertEquals("valorImporte", dto.getValorImporte());
        assertEquals("cccDeCargo", dto.getCccDeCargo());
        assertEquals("tipoDeDocumento", dto.getTipoDeDocumento());
        assertEquals("numeroDeDocumento", dto.getNumeroDeDocumento());
        assertEquals("bancoDelDocumento", dto.getBancoDelDocumento());
        assertEquals("centroDelDocumento", dto.getCentroDelDocumento());
        assertEquals("cuentaDelDocumento", dto.getCuentaDelDocumento());
        assertEquals("plazaDelDocumento", dto.getPlazaDelDocumento());
        assertEquals("responsableImpuesto", dto.getResponsableImpuesto());
        assertEquals("tipoIdentif", dto.getTipoIdentif());
        assertEquals("numeroDocumento", dto.getNumeroDocumento());
        assertEquals("nombrePerson", dto.getNombrePerson());

        assertNotNull(TrxBP14DataRequest.builder()
                .codigoInversor("codigoInversor")
                .secuenciaIpf("secuenciaIpf")
                .formaDePagoIpf("formaDePagoIpf")
                .valorImporte("valorImporte")
                .cccDeCargo("cccDeCargo")
                .tipoDeDocumento("tipoDeDocumento")
                .numeroDeDocumento("numeroDeDocumento")
                .bancoDelDocumento("bancoDelDocumento")
                .centroDelDocumento("centroDelDocumento")
                .cuentaDelDocumento("cuentaDelDocumento")
                .plazaDelDocumento("plazaDelDocumento")
                .responsableImpuesto("responsableImpuesto")
                .tipoIdentif("tipoIdentif")
                .numeroDocumento("numeroDocumento")
                .nombrePerson("nombrePerson")
                .build());
    }

    @Test
    void testTrxBP14Request() {
        TrxHeader header = new TrxHeader();
        TrxBP14DataRequest data = new TrxBP14DataRequest();

        TrxBP14Request request = new TrxBP14Request();
        request.setCabecera(header);
        request.setData(data);

        assertEquals(header, request.getCabecera());
        assertEquals(data, request.getData());

        assertNotNull(TrxBP14Request.builder()
                .cabecera(header)
                .data(data)
                .build());
    }

    @Test
    void testTrxBP14BGMP140Response() {
        TrxBP14BGMP140Response response = new TrxBP14BGMP140Response();

        response.setCCC("CCC");
        response.setNOMPER("NOMPER");
        response.setFECHAVE("FECHAVE");
        response.setFORPAGO("FORPAGO");
        response.setSECUENC(1);
        response.setTASAEFE(2);
        response.setSUBPROD("SUBPROD");
        response.setFECHAL("FECHAL");
        response.setDESFOPA("DESFOPA");
        response.setPRODUCT("PRODUCT");
        response.setTASANOM(3);
        response.setIMPORTE(4);
        response.setNUMDOC("NUMDOC");
        response.setTIPDOCU("TIPDOCU");

        assertEquals("CCC", response.getCCC());
        assertEquals("NOMPER", response.getNOMPER());
        assertEquals("FECHAVE", response.getFECHAVE());
        assertEquals("FORPAGO", response.getFORPAGO());
        assertEquals(1, response.getSECUENC());
        assertEquals(2, response.getTASAEFE());
        assertEquals("SUBPROD", response.getSUBPROD());
        assertEquals("FECHAL", response.getFECHAL());
        assertEquals("DESFOPA", response.getDESFOPA());
        assertEquals("PRODUCT", response.getPRODUCT());
        assertEquals(3, response.getTASANOM());
        assertEquals(4, response.getIMPORTE());
        assertEquals("NUMDOC", response.getNUMDOC());
        assertEquals("TIPDOCU", response.getTIPDOCU());

        assertNotNull(TrxBP14BGMP140Response.builder()
                .CCC("CCC")
                .NOMPER("NOMPER")
                .FECHAVE("FECHAVE")
                .FORPAGO("FORPAGO")
                .SECUENC(1)
                .TASAEFE(2)
                .SUBPROD("SUBPROD")
                .FECHAL("FECHAL")
                .DESFOPA("DESFOPA")
                .PRODUCT("PRODUCT")
                .TASANOM(3)
                .IMPORTE(4)
                .NUMDOC("NUMDOC")
                .TIPDOCU("TIPDOCU")
                .build());
    }

    @Test
    void testTrxBP14DataResponse() {
        TrxBP14BGMP140Response bgmp140 = new TrxBP14BGMP140Response();

        TrxBP14DataResponse response = new TrxBP14DataResponse();
        response.setBGMP140(bgmp140);

        assertEquals(bgmp140, response.getBGMP140());

        assertNotNull(TrxBP14DataResponse.builder()
                .BGMP140(bgmp140)
                .build());
    }

    @Test
    void testTrxBP14Response() {
        TrxBP14DataResponse data = new TrxBP14DataResponse();
        TrxHeader header = new TrxHeader();
        Object autorizacion = new Object();
        Object paginacion = new Object();
        Object conexion = new Object();

        TrxBP14Response response = new TrxBP14Response();
        response.setData(data);
        response.setCabecera(header);
        response.setAutorizacion(autorizacion);
        response.setPaginacion(paginacion);
        response.setAvisos(List.of("aviso"));
        response.setErrores(List.of());
        response.setConexion(conexion);
        response.setOk(Boolean.TRUE);

        assertEquals(data, response.getData());
        assertEquals(header, response.getCabecera());
        assertEquals(autorizacion, response.getAutorizacion());
        assertEquals(paginacion, response.getPaginacion());
        assertEquals(List.of("aviso"), response.getAvisos());
        assertEquals(List.of(), response.getErrores());
        assertEquals(conexion, response.getConexion());
        assertTrue(response.getOk());

        assertNotNull(TrxBP14Response.builder()
                .data(data)
                .cabecera(header)
                .autorizacion(autorizacion)
                .paginacion(paginacion)
                .avisos(List.of("aviso"))
                .errores(List.of())
                .conexion(conexion)
                .ok(Boolean.TRUE)
                .build());
    }

    @Test
    void testSession() {
        Session session = new Session();

        session.setUsuario("usuario");
        session.setTerminal("terminal");
        session.setHoraConexion("horaConexion");
        session.setEntorno("entorno");
        session.setPerfil("perfil");
        session.setSucursal("sucursal");
        session.setEntidad("entidad");
        session.setDiasRestantesCambioClave("diasRestantesCambioClave");
        session.setFechaContable("fechaContable");
        session.setTurno("turno");

        assertEquals("usuario", session.getUsuario());
        assertEquals("terminal", session.getTerminal());
        assertEquals("horaConexion", session.getHoraConexion());
        assertEquals("entorno", session.getEntorno());
        assertEquals("perfil", session.getPerfil());
        assertEquals("sucursal", session.getSucursal());
        assertEquals("entidad", session.getEntidad());
        assertEquals("diasRestantesCambioClave", session.getDiasRestantesCambioClave());
        assertEquals("fechaContable", session.getFechaContable());
        assertEquals("turno", session.getTurno());

        assertNotNull(Session.builder()
                .usuario("usuario")
                .terminal("terminal")
                .horaConexion("horaConexion")
                .entorno("entorno")
                .perfil("perfil")
                .sucursal("sucursal")
                .entidad("entidad")
                .diasRestantesCambioClave("diasRestantesCambioClave")
                .fechaContable("fechaContable")
                .turno("turno")
                .build());
    }

    @Test
    void testTrxHeader() {
        Session session = new Session();

        TrxHeader header = new TrxHeader();
        header.setRutaServicio("rutaServicio");
        header.setSesion(session);
        header.setFuncion("funcion");
        header.setSecuencia(1);
        header.setCanal("canal");
        header.setResultado("resultado");

        assertEquals("rutaServicio", header.getRutaServicio());
        assertEquals(session, header.getSesion());
        assertEquals("funcion", header.getFuncion());
        assertEquals(1, header.getSecuencia());
        assertEquals("canal", header.getCanal());
        assertEquals("resultado", header.getResultado());

        assertNotNull(TrxHeader.builder()
                .rutaServicio("rutaServicio")
                .sesion(session)
                .funcion("funcion")
                .secuencia(1)
                .canal("canal")
                .resultado("resultado")
                .build());
    }

    @Test
    void testApiEntry() {
        ApiEntry apiEntry = new ApiEntry();

        apiEntry.setIntegrationType("SANBA");
        apiEntry.setHost("localhost");
        apiEntry.setPort("8080");
        apiEntry.setHttps(true);
        apiEntry.setEndpoint("/api");
        apiEntry.setTimeOutConn(30);
        apiEntry.setTimeOutRead(60);

        assertEquals("SANBA", apiEntry.getIntegrationType());
        assertEquals("localhost", apiEntry.getHost());
        assertEquals("8080", apiEntry.getPort());
        assertTrue(apiEntry.isHttps());
        assertEquals("/api", apiEntry.getEndpoint());
        assertEquals(30, apiEntry.getTimeOutConn());
        assertEquals(60, apiEntry.getTimeOutRead());

        ApiEntry allArgs = new ApiEntry("SANBA", "localhost", "8080", true, "/api", 30, 60);

        assertEquals("SANBA", allArgs.getIntegrationType());
        assertEquals("localhost", allArgs.getHost());
        assertEquals("8080", allArgs.getPort());
        assertTrue(allArgs.isHttps());
        assertEquals("/api", allArgs.getEndpoint());
        assertEquals(30, allArgs.getTimeOutConn());
        assertEquals(60, allArgs.getTimeOutRead());
    }

    @Test
    void testOtherSourceRequestDto() {
        OtherSourceRequestDto dto = new OtherSourceRequestDto();
        dto.setPaymentReference("123456");

        assertEquals("123456", dto.getPaymentReference());

        assertNotNull(OtherSourceRequestDto.builder()
                .paymentReference("123456")
                .build());
    }

    @Test
    void testSourceFundsRequestDTO() {
        OtherSourceRequestDto other = new OtherSourceRequestDto();

        SourceFundsRequestDTO dto = new SourceFundsRequestDTO();
        dto.setOtherSource(other);

        assertEquals(other, dto.getOtherSource());

        assertNotNull(SourceFundsRequestDTO.builder()
                .otherSource(other)
                .build());
    }

    @Test
    void testTermDepositFundsRequestDto() {
        SourceFundsRequestDTO source = new SourceFundsRequestDTO();

        TermDepositFundsRequestDto dto = new TermDepositFundsRequestDto();
        dto.setSourceFunds(source);

        assertEquals(source, dto.getSourceFunds());

        assertNotNull(TermDepositFundsRequestDto.builder()
                .sourceFunds(source)
                .build());
    }

    @Test
    void testSourceFundResponseDto() {
        SourceFundResponseDto dto = new SourceFundResponseDto();
        dto.setInternalReference("ABC123");

        assertEquals("ABC123", dto.getInternalReference());

        assertNotNull(SourceFundResponseDto.builder()
                .internalReference("ABC123")
                .build());
    }

    @Test
    void testStatusInfoResponseDto() {
        StatusInfoResponseDto dto = new StatusInfoResponseDto();

        dto.setStatusCode("OK");
        dto.setStatusDescription("SUCCESS");

        assertEquals("OK", dto.getStatusCode());
        assertEquals("SUCCESS", dto.getStatusDescription());

        assertNotNull(StatusInfoResponseDto.builder()
                .statusCode("OK")
                .statusDescription("SUCCESS")
                .build());
    }

    @Test
    void testTermDepositFundsResponseDto() {
        TermDepositFundsResponseDto dto = new TermDepositFundsResponseDto();

        assertNotNull(dto.getSourceFunds());
        assertNotNull(dto.getStatusInfo());

        SourceFundResponseDto source = new SourceFundResponseDto();
        StatusInfoResponseDto status = new StatusInfoResponseDto();

        dto.setSourceFunds(source);
        dto.setStatusInfo(status);

        assertEquals(source, dto.getSourceFunds());
        assertEquals(status, dto.getStatusInfo());

        assertNotNull(TermDepositFundsResponseDto.builder()
                .sourceFunds(source)
                .statusInfo(status)
                .build());
    }
}
