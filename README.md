package com.santander.bnc.bsn049.bncbsn049msprospects.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.CiudadComparisonRequest;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.request.CreateProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.create.response.ProspectCreatedResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.prospect.response.ProspectDetailResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.request.ProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.search.response.ProspectSearchResponseDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.domain.prospect.update.request.PatchProspectRequestDTO;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049msprospects.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049msprospects.service.ProspectService;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ClientUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.CompareStringUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049msprospects.utils.ServiceDirectory;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ProspectController {

    final ProspectService prospectService;
    private final ObjectMapper objectMapper;
    /**
     * PROSPECT SEARCH
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectSearchResponseDTO>
     */
    @PostMapping(ServiceDirectory.PROSPECT_SEARCH)
    public ResponseEntity<ProspectSearchResponseDTO> searchCustomers(@Valid @RequestBody ProspectRequestDTO request,
                                                                     @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId,
                                                                     @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization) {
        
    	 try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) {} {} >>>",ServiceDirectory.PROSPECT_SEARCH,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	 
    	log.info(GUtils.SLOG + "endpoint search prospect by person={}", request.getPerson());
        ProspectSearchResponseDTO response = prospectService.searchProspect(request,authorization,xSantanderClientId);

        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (POST) {} {} >>>",ServiceDirectory.PROSPECT_SEARCH,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        log.info(GUtils.ELOG + "endpoint search prospect {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * PROSPECT BASIC DATA
     * @param prospectId
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectDetailResponseDTO>
     */
    @GetMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<ProspectDetailResponseDTO> getProspect(@PathVariable(required = true, name = "prospectId" ) String prospectId,
                                                                 @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                                 @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId) {


		StringBuilder sb = new StringBuilder();
		sb.append(" clientId=").append(xSantanderClientId);		
		log.info("*** INIT (GET) {}/{} clientId={} >>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
 	
    	log.info(GUtils.SLOG + "endpoint get prospect by prospectId={}", prospectId);
        ProspectDetailResponseDTO response = prospectService.getProspectDetails(prospectId,authorization,xSantanderClientId);

        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sbr = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (GET) {}/{} {} >>>",ServiceDirectory.PROSPECT,prospectId,sbr.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        if(response == null){
            throw new ServiceException(HttpStatus.NOT_FOUND, ErrorCatalog.getProspectNotFound());    
        }
        log.info(GUtils.ELOG + "endpoint get prospect");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }//method closure

    /**
     * CREATE PROSPECT
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return ResponseEntity<ProspectCreatedResponseDTO>
     */
    @PostMapping(ServiceDirectory.PROSPECT)
    public ResponseEntity<ProspectCreatedResponseDTO> createProspect(@Valid @RequestBody CreateProspectRequestDTO request,
                                                                     @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                                     @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId) {
        
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) {} {} >>>",ServiceDirectory.PROSPECT,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
    	log.info(GUtils.SLOG + "endpoint create prospect");        
        ProspectCreatedResponseDTO response  = prospectService.createProspect(request,authorization, xSantanderClientId);
        
        try {
 			String jsonResponse = objectMapper.writeValueAsString(response);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Response=").append(jsonResponse);
 			
 			log.info("*** FIN (POST) {} {} >>>",ServiceDirectory.PROSPECT,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando RESPONSE payload");
 		}
        
        log.info(GUtils.ELOG + "endpoint create prospect");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }//method closure

    /**
     * PATCH PROSPECT
     * @param prospectId
     * @param request
     * @param authorization
     * @param xSantanderClientId
     * @return NO_CONTENT
     */
    @PatchMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<Object> updateCustomers(@PathVariable(name = "prospectId") String prospectId,
                                                  @RequestBody PatchProspectRequestDTO request,
                                                  @RequestHeader(required = true, name = ClientUtils.AUTHORIZATION) String authorization,
                                                  @RequestHeader(required = true, name = ClientUtils.SANTANDER_CLIENT_ID) String xSantanderClientId){
        
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" clientId=").append(xSantanderClientId);
 			sb.append(", Request=").append(jsonRequest);
 			
 			log.info("*** INIT (PATCH) {}/{} {}>>>",ServiceDirectory.PROSPECT,prospectId,sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
    	log.info(GUtils.SLOG + "endpoint update prospect {}", prospectId);
        prospectService.updateProspect(request,prospectId,authorization,xSantanderClientId);
        
 			
 		log.info("*** FIN OK (PATCH) {}/{} >>>",ServiceDirectory.PROSPECT,prospectId);

        log.info(GUtils.ELOG + "endpoint update prospect.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }//method closure

    /**
     * DELETE PROSPECT
     * @param prospectId
     * @param authorization
     * @param xSantanderClientId
     * @return
     */
    @DeleteMapping(ServiceDirectory.PROSPECT + "/{prospectId}")
    public ResponseEntity<ProspectDetailResponseDTO> removeProspect(
            @PathVariable(required = true, name = "prospectId" ) String prospectId,
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String xSantanderClientId) {

    	log.info("*** INIT (DELETE) {}/{} clientId={}>>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
    	
        log.info(GUtils.SLOG + "endpoint remove prospect {}", prospectId);
        prospectService.removeProspect(prospectId, authorization, xSantanderClientId);
        
        log.info("*** FIN OK (DELETE) {}/{} clientId={}>>>",ServiceDirectory.PROSPECT,prospectId,xSantanderClientId);
        
        log.info(GUtils.ELOG + "endpoint remove prospect.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }//method closure

    @PostMapping("/compareCities")
    public ResponseEntity<Boolean> compareCities(@RequestBody CiudadComparisonRequest request) {
    	
    	try {
 			String jsonRequest = objectMapper.writeValueAsString(request);
 			StringBuilder sb = new StringBuilder();
 			sb.append(" Request=").append(jsonRequest);
 			
 			log.info("*** INIT (POST) /compareCities {} >>>",sb.toString());
 		} catch (Exception e) {
 			log.error("Error serializando REQUEST payload");
 		}
    	
        CompareStringUtils matcher = new CompareStringUtils();
        boolean isSimilar = matcher.ciudadMatch(request.getCiudadIngresada(), request.getCiudadServicio());

 		log.info("*** FIN (POST) /compareCities isSimilar={} >>>",isSimilar);
     
        return ResponseEntity.ok(isSimilar);
    }

}
