package com.santander.bnc.bsn049.bncbsn049mscontracts.service.impl;

import com.santander.bnc.bsn049.bncbsn049mscontracts.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.ContractRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.ContractsServiceRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.contracts.request.OldAssociatedContractRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21DataRequest;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.request.TrxBP21Request;
import com.santander.bnc.bsn049.bncbsn049mscontracts.domain.host.bp21.response.TrxBP21Response;
import com.santander.bnc.bsn049.bncbsn049mscontracts.exception.error.ErrorService;


import com.santander.bnc.bsn049.bncbsn049mscontracts.service.ContractsService;
import com.santander.bnc.bsn049.bncbsn049mscontracts.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mscontracts.utils.ContractsUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ContractsImpl implements ContractsService {

    final TrxSanbaService trxSanbaService;
    final ContractsUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final ContractsUtils contractsUtils;
    private static final String CONTRACTID = "contract Id";
    private static final String INTERNALIDENTIFICATION = "InternalIdentification";
    private static final String RELATIONSHIP = "relationshipTypeCode";
    private static final String NATIONALIDENT = "NationalIdentification";
    @Value("${service-route-trx.BP21}")
    private String BP21_SERVICE_ROUTE;

    @Override
    public void modifyAssociatedContract(String contractId, ContractsServiceRequestDTO updateRequest) {

        validateUpdateContractA(contractId, updateRequest);
        trxBP21Call(contractId, updateRequest);

    }

    private void validateUpdateContractA(String contractId, ContractsServiceRequestDTO updateRequest) {
        // Validación para
        errorService.isNull(contractId, CONTRACTID);
        errorService.isBlank(contractId, CONTRACTID);
        regexUtils.validateRegex("only_numbers", contractId, CONTRACTID);
        regexUtils.validateRegex("strict_length_20", contractId, "contract_ Id");

        //nationalIdentification
        errorService.isNull(updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getNationalIdentification(),
                NATIONALIDENT);
        errorService.isBlank(updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getNationalIdentification(),
                NATIONALIDENT);
        regexUtils.validateRegex("national_length",
                updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getNationalIdentification(),
                NATIONALIDENT);
        regexUtils.validateRegex("national_format",
                updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getNationalIdentification(),
                NATIONALIDENT);


        //InternalIdentification
        errorService.isNull(updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getInternalIdentification(),
                INTERNALIDENTIFICATION);
        errorService.isBlank(updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getInternalIdentification(),
                INTERNALIDENTIFICATION);
        regexUtils.validateRegex("internal_identification_length",
                updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getInternalIdentification(),
                INTERNALIDENTIFICATION);
        regexUtils.validateRegex("internal_identification_format",
                updateRequest.getNewAssociatedContract().getContract().getContractIdentification().getInternalIdentification(),
                INTERNALIDENTIFICATION);



        //relationshipTypeCode
        errorService.isNull(updateRequest.getOldAssociatedContract().getRelationshipTypeCode(),
                RELATIONSHIP);
        errorService.isBlank(updateRequest.getOldAssociatedContract().getRelationshipTypeCode(),
                RELATIONSHIP);
        regexUtils.validateRegex("relationship_length",
                updateRequest.getOldAssociatedContract().getRelationshipTypeCode(),
                RELATIONSHIP);
        regexUtils.validateRegex("relationship_format",
                updateRequest.getOldAssociatedContract().getRelationshipTypeCode(),
                RELATIONSHIP);
        OldAssociatedContractRequestDTO oldContract = updateRequest.getOldAssociatedContract();
        String relationshipTypeCode = oldContract.getRelationshipTypeCode();
        if (!isValidRelationshipTypeCode(relationshipTypeCode)) {
            throw new IllegalArgumentException("'relationshipTypeCode': Invalid value");
        }
    }

    private boolean isValidRelationshipTypeCode(String relationshipTypeCode) {
        return "CC".equals(relationshipTypeCode) || "CA".equals(relationshipTypeCode);
    }

    private String buildObservation(ContractsServiceRequestDTO updateRequest) {
        StringBuilder observation = new StringBuilder();

        ContractRequestDTO newContract = updateRequest.getNewAssociatedContract().getContract();
        String nationalIdentification = newContract.getContractIdentification().getNationalIdentification();
        String internationalIdentifiaction = newContract.getContractIdentification().getInternalIdentification();

        BanksParametersRequest banksParametersRequest = BanksParametersRequest.builder()
                .authorization("authorization")
                .xSantanderClientId("xSantanderClientId")
                .build();

        contractsUtils.bankValidation(banksParametersRequest, nationalIdentification);

        OldAssociatedContractRequestDTO oldContract = updateRequest.getOldAssociatedContract();
        String relationshipTypeCode = oldContract.getRelationshipTypeCode();

        // Concatenar los valores en una sola cadena
        observation.append(nationalIdentification).append(relationshipTypeCode).append(internationalIdentifiaction);

        return observation.toString();
    }


    public TrxBP21Response trxBP21Call(String contractId, ContractsServiceRequestDTO updateRequest) {
        String observation = buildObservation(updateRequest);
        String zero = "0000000000000.00";
        TrxBP21Request trxBP21Request = new TrxBP21Request(ClientUtils.buildHeader(BP21_SERVICE_ROUTE));

        var trxBP21Data = new TrxBP21DataRequest();

        // Otras configuraciones de trxBP21Data
        trxBP21Data.setCertificado("ECP");
        trxBP21Data.setCcc(contractId);
        trxBP21Data.setSecuenciaImposicion("00001");
        trxBP21Data.setTarifaVigente("4250");
        trxBP21Data.setPlazo("090");
        trxBP21Data.setPeriodoLiquidacion("M");
        trxBP21Data.setDivisa("COP");
        trxBP21Data.setSaldoDeLaIpf(zero);
        trxBP21Data.setSpread("000.00000");
        trxBP21Data.setRenovacionAutomatic("N");
        trxBP21Data.setCapitalizaIntereses("N");
        trxBP21Data.setCapitalizaReajustes("N");
        trxBP21Data.setEjecutivoComercial("0001");
        trxBP21Data.setRentaProgramada(zero);
        trxBP21Data.setIndBloqueo("N");
        trxBP21Data.setCentroGestor("0001");
        trxBP21Data.setIndicadorGarantia("N");
        trxBP21Data.setIndicadorFraccionab("N");
        trxBP21Data.setSpreadRenovacion("000.00000");
        trxBP21Data.setSaldoEnajenacion("0000000000000.00");
        trxBP21Data.setObservaciones(observation);
        trxBP21Data.setTipoDeTasa("N");

        trxBP21Request.setData(trxBP21Data);
        return trxSanbaService.trxBP21(trxBP21Request);
    }
}
