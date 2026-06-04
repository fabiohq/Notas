package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.response.TrxBP31Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;

import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class TrxSanbaServiceImplTest {

	@Mock
	TrxSanbaAPI trxSanbaAPI;
	@Mock
	ErrorService errorService;

	@Mock
	Call<TrxBP17Response> bp17Call;
	@Mock
	Call<TrxBP31Response> bp31Call;
	@Mock
	Call<TrxBP13Response> bp13Call;
	@Mock
	Call<TrxBp01Response> bp01Call;
	@Mock
	Call<TrxBp02Response> bp02Call;
	@Mock
	Call<TrxBP49Response> bp49Call;
	@Mock
	Call<TrxPEPFDataResponse> pepfCall;

	private TrxSanbaServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new TrxSanbaServiceImpl(trxSanbaAPI, errorService);

		ReflectionTestUtils.setField(service, "bp17ServiceRoute", "BP17_ROUTE");
		ReflectionTestUtils.setField(service, "pepfServiceRoute", "PEPF_ROUTE");
		ReflectionTestUtils.setField(service, "bp01ServiceRoute", "BP01_ROUTE");
		ReflectionTestUtils.setField(service, "bp02ServiceRoute", "BP02_ROUTE");
		ReflectionTestUtils.setField(service, "bp49ServiceRoute", "BP49_ROUTE");
		ReflectionTestUtils.setField(service, "mqRoute", "MQ_ROUTE");
		ReflectionTestUtils.setField(service, "channel", "CHANNEL");
		ReflectionTestUtils.setField(service, "user", "USER");
	}

	@Test
	void trxBP17ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBP17Request request = request(TrxBP17Request.class);
		TrxBP17Response body = new TrxBP17Response();

		when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
		when(bp17Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP17(request));
		assertEquals("CHANNEL", request.getCabecera().getCanal());
		assertEquals("USER", request.getCabecera().getSesion().getUsuario());
	}

	@Test
	void trxBP31ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBP31Request request = request(TrxBP31Request.class);
		TrxBP31Response body = new TrxBP31Response();

		when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
		when(bp31Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP31(request));
	}

	@Test
	void trxBP13ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBP13Request request = request(TrxBP13Request.class);
		TrxBP13Response body = new TrxBP13Response();

		when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
		when(bp13Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP13(request));
	}

	@Test
	void trxBP01ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBp01Request request = request(TrxBp01Request.class);
		TrxBp01Response body = new TrxBp01Response();

		when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
		when(bp01Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP01(request));
	}

	@Test
	void trxBP02ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBp02Request request = request(TrxBp02Request.class);
		TrxBp02Response body = new TrxBp02Response();

		when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
		when(bp02Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP02(request));
	}

	@Test
	void trxBP49ShouldReturnBodyWhenSuccess() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);
		TrxBP49Response body = new TrxBP49Response();

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxBP49(request));
	}

	@Test
	void trxPEPFShouldReturnBodyWhenSuccess() throws Exception {
		TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);
		TrxPEPFDataResponse body = new TrxPEPFDataResponse();

		when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
		when(pepfCall.execute()).thenReturn(Response.success(body));

		assertSame(body, service.trxPEPF(request));
	}

	@Test
	void trxBP17ShouldThrowServiceExceptionWhenRuntimeException() {
		TrxBP17Request request = request(TrxBP17Request.class);

		try {
			when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime error"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP17(request));
	}

	@Test
	void trxBP17ShouldThrowServiceExceptionWhenIOException() throws Exception {
		TrxBP17Request request = request(TrxBP17Request.class);

		when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
		when(bp17Call.execute()).thenThrow(new IOException("io error"));

		assertThrows(ServiceException.class, () -> service.trxBP17(request));
	}

	private <T> T request(Class<T> clazz) {
		try {
			T request = clazz.getDeclaredConstructor().newInstance();

			TrxHeader header = new TrxHeader();
			Session session = new Session();
			header.setSesion(session);

			clazz.getMethod("setCabecera", TrxHeader.class).invoke(request, header);

			return request;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void trxBP01ShouldThrowRuntimeBranch() {
		TrxBp01Request request = request(TrxBp01Request.class);

		try {
			when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP01(request));
	}

	@Test
	void trxBP02ShouldThrowRuntimeBranch() {
		TrxBp02Request request = request(TrxBp02Request.class);

		try {
			when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP02(request));
	}

	@Test
	void trxBP13ShouldThrowRuntimeBranch() {
		TrxBP13Request request = request(TrxBP13Request.class);

		try {
			when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP13(request));
	}

	@Test
	void trxBP31ShouldThrowRuntimeBranch() {
		TrxBP31Request request = request(TrxBP31Request.class);

		try {
			when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP31(request));
	}

	@Test
	void trxBP49ShouldThrowRuntimeBranch() {
		TrxBP49Request request = request(TrxBP49Request.class);

		try {
			when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxPEPFShouldThrowRuntimeBranch() {
		TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

		try {
			when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString()))
					.thenThrow(new RuntimeException("runtime"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ServiceException.class, () -> service.trxPEPF(request));
	}

	@Test
	void validateTrxBp31ShouldFillErrors() throws Exception {
		var trxError = org.mockito.Mockito
				.mock(com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
		when(trxError.getMensaje()).thenReturn("ERROR BP31");

		var err = new TrxBP31Response();
		err.setErrores(java.util.List.of(trxError));

		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
				.message("ERROR BP31").build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

		java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO> errors = new java.util.ArrayList<>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxBp31", TrxBP31Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), "{}", errors);

		assertEquals(1, errors.size());
	}

	@Test
	void validateTrxPEPFShouldFillErrors() throws Exception {
		var trxError = org.mockito.Mockito
				.mock(com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
		when(trxError.getMensaje()).thenReturn("ERROR PEPF");

		var parsed = new TrxPEPFDataResponse();
		parsed.setErrores(java.util.List.of(trxError));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(parsed);

		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
				.message("ERROR PEPF").build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

		java.util.List<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO> errors = new java.util.ArrayList<>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxPEPF",
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, TrxPEPFDataResponse.class,
				java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(), json, null, errors);

		assertEquals(1, errors.size());
	}

	@Test
	void trxbp49ValidateShouldFillErrors() throws Exception {
		var trxError = org.mockito.Mockito
				.mock(com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
		when(trxError.getMensaje()).thenReturn("ERROR BP49");

		var errorResponse = new com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.ErrorResponseTrxDTO();
		errorResponse.setErrores(java.util.List.of(trxError));

		var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		String json = mapper.writeValueAsString(errorResponse);

		var response = new TrxBP49Response();
		response.setErrores(new java.util.ArrayList<>());
		response.setAvisos(new java.util.ArrayList<>());

		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
				.message("ERROR BP49").build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);

		var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("trxbp49Validate", TrxBP49Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, response, mapper, json, errors);

		assertEquals(1, errors.size());
	}

//	private com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO trxError(String message) {
//		var error = org.mockito.Mockito
//				.mock(com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
//		when(error.getMensaje()).thenReturn(message);
//		return error;
//	}
//
//	private okhttp3.ResponseBody errorBody(String json) {
//		return okhttp3.ResponseBody.create(json, okhttp3.MediaType.parse("application/json"));
//	}
//
//	private void mockErrorBuilder(String message) {
//		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder().message(message)
//				.build();
//
//		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
//	}

	@Test
	void trxBP49ShouldThrowBadRequestWhenApiReturnsErrorBody() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		var err = new TrxBP49Response();
		err.setErrores(java.util.List.of(trxError("ERROR BP49")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

		mockErrorBuilder("ERROR BP49");

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist1() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		var err = new TrxBP49Response();
		err.setErrores(java.util.List.of(trxError("SECUENCIA NO EXISTE")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
				.message("SECUENCIA NO EXISTE").build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
		when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
				.thenReturn(new ServiceException(org.springframework.http.HttpStatus.NOT_FOUND, errorDto));

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxBP49ShouldThrowConflictWhenInvalidJsonErrorBody() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody("{invalid-json")));

		when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
				.thenReturn(new ServiceException(org.springframework.http.HttpStatus.CONFLICT, "json-error"));

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxbp49ValidateShouldFillErrorsFromValidJson() throws Exception {
		var err = new TrxBP49Response();
		err.setErrores(java.util.List.of(trxError("ERROR BP49")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		mockErrorBuilder("ERROR BP49");

		var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("trxbp49Validate", TrxBP49Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, null, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

		assertEquals(1, errors.size());
	}

	private okhttp3.ResponseBody errorBody(String json) {
		return okhttp3.ResponseBody.create(json, okhttp3.MediaType.parse("application/json"));
	}

	private com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO trxError(String message) {
		var error = org.mockito.Mockito
				.mock(com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO.class);
		when(error.getMensaje()).thenReturn(message);
		return error;
	}

	private void mockErrorBuilder(String message) {
		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder().message(message)
				.build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
	}

	private void mockServiceExceptionBuilder() {
		when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
				.thenReturn(new ServiceException(org.springframework.http.HttpStatus.CONFLICT, "error"));
	}

	@Test
	void trxBP01ShouldThrowWhenIOException() throws Exception {
		TrxBp01Request request = request(TrxBp01Request.class);

		when(trxSanbaAPI.callBP01(any(), anyString(), anyString(), anyString())).thenReturn(bp01Call);
		when(bp01Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP01(request));
	}

	@Test
	void trxBP02ShouldThrowWhenIOException() throws Exception {
		TrxBp02Request request = request(TrxBp02Request.class);

		when(trxSanbaAPI.callBP02(any(), anyString(), anyString(), anyString())).thenReturn(bp02Call);
		when(bp02Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP02(request));
	}

	@Test
	void trxBP13ShouldThrowWhenIOException() throws Exception {
		TrxBP13Request request = request(TrxBP13Request.class);

		when(trxSanbaAPI.callBP13TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp13Call);
		when(bp13Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP13(request));
	}

	@Test
	void trxBP17ShouldThrowWhenIOException() throws Exception {
		TrxBP17Request request = request(TrxBP17Request.class);

		when(trxSanbaAPI.callBP17TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp17Call);
		when(bp17Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP17(request));
	}

	@Test
	void trxBP31ShouldThrowWhenIOException() throws Exception {
		TrxBP31Request request = request(TrxBP31Request.class);

		when(trxSanbaAPI.callBP31TRX(any(), anyString(), anyString(), anyString())).thenReturn(bp31Call);
		when(bp31Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP31(request));
	}

	@Test
	void trxBP49ShouldThrowWhenIOException() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxPEPFShouldThrowWhenIOException() throws Exception {
		TrxPEPFDataRequest request = request(TrxPEPFDataRequest.class);

		when(trxSanbaAPI.callPEPF(any(), anyString(), anyString(), anyString())).thenReturn(pepfCall);
		when(pepfCall.execute()).thenThrow(new java.io.IOException("io"));

		assertThrows(ServiceException.class, () -> service.trxPEPF(request));
	}

	@Test
	void trxBP49ShouldThrowBadRequestWhenResponseHasErrors() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		var responseError = new TrxBP49Response();
		responseError.setErrores(java.util.List.of(trxError("ERROR BP49")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(responseError);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

		mockErrorBuilder("ERROR BP49");

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxBP49ShouldThrowNotFoundWhenSequenceDoesNotExist() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		var responseError = new TrxBP49Response();
		responseError.setErrores(java.util.List.of(trxError("SECUENCIA NO EXISTE")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(responseError);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody(json)));

		var errorDto = com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO.builder()
				.message("SECUENCIA NO EXISTE").build();

		when(errorService.errorBuilder(any(), anyString(), any())).thenReturn(errorDto);
		when(errorService.serviceExceptionBuilder(any(), anyString(), any()))
				.thenReturn(new ServiceException(org.springframework.http.HttpStatus.NOT_FOUND, "Deposit not found"));

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void trxBP49ShouldThrowConflictWhenInvalidJson() throws Exception {
		TrxBP49Request request = request(TrxBP49Request.class);

		when(trxSanbaAPI.callBP49(any(), anyString(), anyString(), anyString())).thenReturn(bp49Call);
		when(bp49Call.execute()).thenReturn(retrofit2.Response.error(400, errorBody("{bad-json")));

		mockServiceExceptionBuilder();

		assertThrows(ServiceException.class, () -> service.trxBP49(request));
	}

	@Test
	void validateTrxBp31ShouldFillErrorsFromErrObject() throws Exception {
		var err = new TrxBP31Response();
		err.setErrores(java.util.List.of(trxError("ERROR BP31")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		mockErrorBuilder("ERROR BP31");

		var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxBp31", TrxBP31Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, err, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

		assertEquals(1, errors.size());
	}

	@Test
	void validateTrxBp31ShouldThrowWhenInvalidJson() throws Exception {
		var err = new TrxBP31Response();
		err.setErrores(java.util.Collections.emptyList());

		mockServiceExceptionBuilder();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxBp31", TrxBP31Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		assertThrows(Exception.class, () -> method.invoke(service, err,
				new com.fasterxml.jackson.databind.ObjectMapper(), "{bad-json", new java.util.ArrayList<>()));
	}

	@Test
	void trxbp49ValidateShouldFillErrorsFromJson() throws Exception {
		var err = new TrxBP49Response();
		err.setErrores(java.util.List.of(trxError("ERROR BP49")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		mockErrorBuilder("ERROR BP49");

		var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("trxbp49Validate", TrxBP49Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, null, new com.fasterxml.jackson.databind.ObjectMapper(), json, errors);

		assertEquals(1, errors.size());
	}

	@Test
	void trxbp49ValidateShouldThrowWhenInvalidJson() throws Exception {
		mockServiceExceptionBuilder();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("trxbp49Validate", TrxBP49Response.class,
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, java.util.List.class);
		method.setAccessible(true);

		assertThrows(Exception.class, () -> method.invoke(service, null,
				new com.fasterxml.jackson.databind.ObjectMapper(), "{bad-json", new java.util.ArrayList<>()));
	}

	@Test
	void validateTrxPEPFShouldFillErrorsFromJson() throws Exception {
		var err = new TrxPEPFDataResponse();
		err.setErrores(java.util.List.of(trxError("ERROR PEPF")));

		String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(err);

		mockErrorBuilder("ERROR PEPF");

		var errors = new java.util.ArrayList<com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO>();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxPEPF",
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, TrxPEPFDataResponse.class,
				java.util.List.class);
		method.setAccessible(true);

		method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(), json, null, errors);

		assertEquals(1, errors.size());
	}

	@Test
	void validateTrxPEPFShouldThrowWhenInvalidJson() throws Exception {
		mockServiceExceptionBuilder();

		Method method = TrxSanbaServiceImpl.class.getDeclaredMethod("validateTrxPEPF",
				com.fasterxml.jackson.databind.ObjectMapper.class, String.class, TrxPEPFDataResponse.class,
				java.util.List.class);
		method.setAccessible(true);

		assertThrows(Exception.class, () -> method.invoke(service, new com.fasterxml.jackson.databind.ObjectMapper(),
				"{bad-json", null, new java.util.ArrayList<>()));
	}

}
