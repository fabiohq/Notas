package com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.ParameterDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.domain.parameters.response.TermDepositParameterResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdepoprmtrs.service.TermDepositParametersService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TermDepositParametersController.class)
@TestPropertySource(properties = {
        "params.version=/v1/term_deposit_parameters"
})
class TermDepositParametersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TermDepositParametersService termDepositParametersService;

    @Autowired
    private ObjectMapper objectMapper;

    private TermDepositParameterResponse buildResponse() {
        ParameterDTO parameter = ParameterDTO.builder()
                .code("V")
                .content("Vencimiento")
                .description("Pago Intereses al Vencimiento")
                .build();

        return TermDepositParameterResponse.builder()
                .parameters(List.of(parameter))
                .build();
    }

    @Test
    @DisplayName("Debe retornar parámetros cuando amount y periodicity no vienen")
    void getParametersWithoutQueryParamsShouldReturnOk() throws Exception {
        String productId = "010001";
        TermDepositParameterResponse response = buildResponse();

        when(termDepositParametersService.getParameters(productId, null, null))
                .thenReturn(response);

        mockMvc.perform(get("/v1/term_deposit_parameters/{parameter_id}", productId)
                        .header("authorization", "Bearer token")
                        .header("x-santander-client-id", "client-test"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)))
                .andExpect(jsonPath("$.parameters[0].code").value("V"))
                .andExpect(jsonPath("$.parameters[0].content").value("Vencimiento"))
                .andExpect(jsonPath("$.parameters[0].description").value("Pago Intereses al Vencimiento"));

        verify(termDepositParametersService)
                .getParameters(eq(productId), eq(null), eq(null));
    }

    @Test
    @DisplayName("Debe retornar parámetros cuando amount y periodicity vienen como query params")
    void getParametersWithQueryParamsShouldReturnOk() throws Exception {
        String productId = "010001";
        String amount = "1000000";
        String periodicity = "90";
        TermDepositParameterResponse response = buildResponse();

        when(termDepositParametersService.getParameters(productId, amount, periodicity))
                .thenReturn(response);

        mockMvc.perform(get("/v1/term_deposit_parameters/{parameter_id}", productId)
                        .param("amount", amount)
                        .param("periodicity", periodicity)
                        .header("authorization", "Bearer token")
                        .header("x-santander-client-id", "client-test"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)))
                .andExpect(jsonPath("$.parameters[0].code").value("V"));

        verify(termDepositParametersService)
                .getParameters(eq(productId), eq(amount), eq(periodicity));
    }

    @Test
    @DisplayName("Debe retornar 400 cuando falta header authorization")
    void getParametersWithoutAuthorizationHeaderShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/v1/term_deposit_parameters/{parameter_id}", "010001")
                        .header("x-santander-client-id", "client-test"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe retornar 400 cuando falta header x-santander-client-id")
    void getParametersWithoutClientIdHeaderShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/v1/term_deposit_parameters/{parameter_id}", "010001")
                        .header("authorization", "Bearer token"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe retornar 404 cuando la ruta no existe")
    void unknownPathShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/v1/unknown/010001")
                        .header("authorization", "Bearer token")
                        .header("x-santander-client-id", "client-test"))
                .andExpect(status().isNotFound());
    }
}
