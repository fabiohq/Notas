package com.santander.bnc.bsn049.bncbsn049mscontracts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.ContractsServiceRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.service.ContractsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class ContractsControllerTest {

    @Mock
    private ContractsService termDepositService;

    @Mock
    private TrxSanbaService trxSanbaService;

    @Mock
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ContractsController controller =
                new ContractsController(termDepositService, trxSanbaService, objectMapper);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnNoContentWhenRequestIsValid() throws Exception {
        ContractsServiceRequestDTO request = new ContractsServiceRequestDTO();

        when(objectMapper.writeValueAsString(any(ContractsServiceRequestDTO.class)))
                .thenReturn("{\"ok\":true}");
        doNothing().when(termDepositService)
                .modifyAssociatedContract(eq("12345678901234567890"), any(ContractsServiceRequestDTO.class));

        mockMvc.perform(post("/v3/contracts/12345678901234567890/modify_associated_contract")
                        .header("x-santander-client-id", "client-id")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNoContent());

        verify(termDepositService)
                .modifyAssociatedContract(eq("12345678901234567890"), any(ContractsServiceRequestDTO.class));
    }

    @Test
    void shouldReturnBadRequestWhenContractIdIsBlank() throws Exception {
        when(objectMapper.writeValueAsString(any(ContractsServiceRequestDTO.class)))
                .thenReturn("{\"ok\":true}");

        mockMvc.perform(post("/v3/contracts/%20/modify_associated_contract")
                        .header("x-santander-client-id", "client-id")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        verify(termDepositService, never())
                .modifyAssociatedContract(any(), any(ContractsServiceRequestDTO.class));
    }

    @Test
    void shouldContinueWhenSerializationFailsAndReturnNoContent() throws Exception {
        when(objectMapper.writeValueAsString(any(ContractsServiceRequestDTO.class)))
                .thenThrow(new RuntimeException("serialization error"));
        doNothing().when(termDepositService)
                .modifyAssociatedContract(eq("12345678901234567890"), any(ContractsServiceRequestDTO.class));

        mockMvc.perform(post("/v3/contracts/12345678901234567890/modify_associated_contract")
                        .header("x-santander-client-id", "client-id")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNoContent());

        verify(termDepositService)
                .modifyAssociatedContract(eq("12345678901234567890"), any(ContractsServiceRequestDTO.class));
    }
}