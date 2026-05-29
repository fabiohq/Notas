package com.santander.bnc.bsn049.bncbsn049mstrmdpstfnds.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

		dto.setCodigoInversor("123");
		dto.setSecuenciaIpf("456");
		dto.setFormaDePagoIpf("O");
		dto.setValorImporte("1000");

		assertEquals("123", dto.getCodigoInversor());
		assertEquals("456", dto.getSecuenciaIpf());
		assertEquals("O", dto.getFormaDePagoIpf());
		assertEquals("1000", dto.getValorImporte());

		assertNotNull(TrxBP14DataRequest.builder().build());
	}

	@Test
	void testTrxBP14Request() {
		TrxBP14Request request = new TrxBP14Request();

		TrxHeader header = new TrxHeader();
		TrxBP14DataRequest data = new TrxBP14DataRequest();

		request.setCabecera(header);
		request.setData(data);

		assertEquals(header, request.getCabecera());
		assertEquals(data, request.getData());

		assertNotNull(TrxBP14Request.builder().build());
	}

	@Test
	void testTrxBP14BGMP140Response() {
		TrxBP14BGMP140Response response = new TrxBP14BGMP140Response();

		response.setCCC("123456");
		response.setNOMPER("FABIO");
		response.setIMPORTE(1000);

		assertEquals("123456", response.getCCC());
		assertEquals("FABIO", response.getNOMPER());
		assertEquals(1000, response.getIMPORTE());

		assertNotNull(TrxBP14BGMP140Response.builder().build());
	}

	@Test
	void testTrxBP14DataResponse() {
		TrxBP14BGMP140Response bgmp140 = new TrxBP14BGMP140Response();

		TrxBP14DataResponse response = new TrxBP14DataResponse();
		response.setBGMP140(bgmp140);

		assertEquals(bgmp140, response.getBGMP140());

		assertNotNull(TrxBP14DataResponse.builder().build());
	}

	@Test
	void testTrxBP14Response() {
		TrxBP14Response response = new TrxBP14Response();

		TrxBP14DataResponse data = new TrxBP14DataResponse();
		TrxHeader header = new TrxHeader();

		response.setData(data);
		response.setCabecera(header);
		response.setOk(Boolean.TRUE);

		assertEquals(data, response.getData());
		assertEquals(header, response.getCabecera());
		assertTrue(response.getOk());

		assertNotNull(TrxBP14Response.builder().build());
	}

	@Test
	void testSession() {
		Session session = new Session();

		session.setUsuario("user");
		session.setEntidad("0065");

		assertEquals("user", session.getUsuario());
		assertEquals("0065", session.getEntidad());

		assertNotNull(Session.builder().build());
	}

	@Test
	void testTrxHeader() {
		TrxHeader header = new TrxHeader();

		header.setCanal("WEB");
		header.setFuncion("TEST");

		assertEquals("WEB", header.getCanal());
		assertEquals("TEST", header.getFuncion());

		assertNotNull(TrxHeader.builder().build());
	}

	@Test
	void testApiEntry() {
		ApiEntry apiEntry = new ApiEntry();

		apiEntry.setIntegrationType("SANBA");
		apiEntry.setHost("localhost");
		apiEntry.setHttps(true);

		assertEquals("SANBA", apiEntry.getIntegrationType());
		assertEquals("localhost", apiEntry.getHost());
		assertTrue(apiEntry.isHttps());
	}

	@Test
	void testOtherSourceRequestDto() {
		OtherSourceRequestDto dto = new OtherSourceRequestDto();

		dto.setPaymentReference("123456");

		assertEquals("123456", dto.getPaymentReference());

		assertNotNull(OtherSourceRequestDto.builder().build());
	}

	@Test
	void testSourceFundsRequestDTO() {
		OtherSourceRequestDto other = new OtherSourceRequestDto();

		SourceFundsRequestDTO dto = new SourceFundsRequestDTO();
		dto.setOtherSource(other);

		assertEquals(other, dto.getOtherSource());

		assertNotNull(SourceFundsRequestDTO.builder().build());
	}

	@Test
	void testTermDepositFundsRequestDto() {
		SourceFundsRequestDTO source = new SourceFundsRequestDTO();

		TermDepositFundsRequestDto dto = new TermDepositFundsRequestDto();
		dto.setSourceFunds(source);

		assertEquals(source, dto.getSourceFunds());

		assertNotNull(TermDepositFundsRequestDto.builder().build());
	}

	@Test
	void testSourceFundResponseDto() {
		SourceFundResponseDto dto = new SourceFundResponseDto();

		dto.setInternalReference("ABC123");

		assertEquals("ABC123", dto.getInternalReference());

		assertNotNull(SourceFundResponseDto.builder().build());
	}

	@Test
	void testStatusInfoResponseDto() {
		StatusInfoResponseDto dto = new StatusInfoResponseDto();

		dto.setStatusCode("OK");
		dto.setStatusDescription("SUCCESS");

		assertEquals("OK", dto.getStatusCode());
		assertEquals("SUCCESS", dto.getStatusDescription());

		assertNotNull(StatusInfoResponseDto.builder().build());
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

		assertNotNull(TermDepositFundsResponseDto.builder().sourceFunds(source).statusInfo(status).build());
	}
	
	
}
