package com.santander.bnc.bsn049.bncbsn049mscontracts.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.ContractsServiceRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.service.ContractsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v3/contracts")
@RequiredArgsConstructor
@Slf4j
public class ContractsController {
    final ContractsService termDepositService;
    final TrxSanbaService trxSanbaService;
    private final ObjectMapper objectMapper; 
    @PostMapping("/{contract_id}/modify_associated_contract")
    public ResponseEntity<Object> modifyAssociatedContract(
            @RequestHeader("x-santander-client-id") String clientId,
            @RequestHeader("Authorization") String authorization,
            @PathVariable("contract_id") String contractId,
            @RequestBody ContractsServiceRequestDTO updateRequest) {
    	
    	try {
			String jsonRequest = objectMapper.writeValueAsString(updateRequest);
			StringBuilder sb = new StringBuilder();
			sb.append(", payload=").append(jsonRequest)
			.append("clientId=").append(clientId)
			.append(", contractId=").append(contractId);
			log.info("*** INIT modify_associated_contract {}  >>> ",sb.toString());
		} catch (Exception e) {
			log.error("Error serializando payload");
		}
    	
        if (contractId == null || contractId.isBlank()) {
        	log.warn("*** FIN contractId nulo o vacío, proceso finalizado  >>> ");
            return ResponseEntity.badRequest().body("");
        }

        termDepositService.modifyAssociatedContract(contractId, updateRequest);
        log.info("*** FIN modify_associated_contract");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
