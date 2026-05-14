package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.partiesconsents.request.PartiesConsentsRequest;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.service.DataConsentManagementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/v5/data_consents_management")
@RequiredArgsConstructor
public class DataConsentManagementController {
    final DataConsentManagementService dataConsentManagementService;
    private final ObjectMapper objectMapper;
	@PostMapping("/parties/{party_id}/consent_agreements")
	public ResponseEntity getPartiesConsent(
			@RequestHeader(required = true, name = "Authorization") String authorization,
			@RequestHeader(required = true, name = "x-santander-client-id") String clientId,
			@PathVariable(name = "party_id") String partyId,
			@Valid @RequestBody PartiesConsentsRequest partiesConsentsRequest) {
		
		try {
			String jsonRequest = objectMapper.writeValueAsString(partiesConsentsRequest);
			StringBuilder sb = new StringBuilder();
			sb.append(", Request=").append(jsonRequest);
			
			log.info("**** INIT (POST) /v5/data_consents_management/parties/{}/consent_agreements clientId={} {}>>>"
					,partyId
					,clientId
					,sb.toString());
		} catch (Exception e) {
			log.error("Error serializando request payload");
		}
		
		dataConsentManagementService.getPartiesConsent(partyId, partiesConsentsRequest);
		
		log.info("**** FIN OK (POST) /v5/data_consents_management/parties/{}/consent_agreements clientId={} >>>"
				,partyId
				,clientId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
