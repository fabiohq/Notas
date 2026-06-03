Sessionsbnc-bsn049-mstermdeposits > com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils > TermDepositUtils.java
TermDepositUtils.java
package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;


import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * Handle all Products utils
 */
@Component
@RequiredArgsConstructor
public class TermDepositUtils {
    
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final ProductDirectoryService productDirectoryService;
    final TermDepositParametersService termDepositParametersService;
    final BanksService banksService;

    @Value("${params.commons.productCode}")
    private String productCode;
    @Value("${params.commons.subproductCode}")
    private String subproductCode;

    @Value("#{'${params.frequencies}'.split(',')}")
    private String [] validFrecuencies;
    @Value("#{'${params.settlements}'.split(',')}")
    private String [] validSettlements;
    @Value("${params.condition-codes}")
    private String SETTLEMENT_CONDITON_CODES;
    @Value("${params.commons.bankId}")
    private String bankId;
    @Value("${params.commons.centerId}")
    private String centerId;

    private String PRODUCT_CODE_FIELDNAME = "product.productCode";
    private String SUBPRODUCT_ID_FIELDNAME= "product.subproduct.subproductId";
    private String AMOUNT_FIELDNAME = "amount.amount";
    private String PERIDIOCITY_FRECUENCY = "periodicity.frecuency";
    private String PERIDIOCITY_PERIOD_TYPE_CODE = "periodicity.periodTypeCode";
    private String SETTLEMENT_CONDITION_CODE_FIELDNAME = "settlementConditionCode";
    private String BANK_ID_FIELDNAME = "bank.bankId";
    private String CENTER_ID_FIELDNAME = "bank.center.centerId";
    private String ACCOUNT_ID_TYPE = "deposits.placement.destinationFunds.accountIdType";
    private String BANK_CODE= "deposits.placement.destinationFunds.bankcode";
    private String NATIONAL_IDENTIFICATION = "deposits.placement.destinationFunds.account.nationalIdentification";
    private String DEPOSITS_FRECUENCY = "deposits.placement.periodicity.frequency";
    private String DEPOSITS_SETTLEMENT_CONDITION_CODE = "deposits.placement.settlementCondition.code";
    private String DEPOSITS_PURPOSE_CODE = "deposits.placement.purposeCode";
    private String DEPOSITS_TOTAL_INVESTED_AMOUNT = "economicData.initialTotalInvested.amount";
    private String DEPOSITS_CURRENCY = "economicData.initialTotalInvested.currency";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CODE ="economicData.settlements.settlementConcept.code";
    private String DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE ="economicData.settlements.settlementConcept.typeCode";
    private String DEPOSITS_SETTLEMENT_CONCEPT_RATE = "economicData.settlements.settlementConcept.rate";
    private String DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT = "economicData.settlements.settlementConcept.amount.amount";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY = "economicData.settlements.settlementConcept.amount.currency";    
    private String PARTICIPANT_PARTICIPANTID = "participant.participantId";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH = "settlementConcept_typeCode_length";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT = "settlementConcept_typeCode_format";
    private static final String CURRENCY_CODE_FORMAT = "currency_code_format";
    private static final String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND = "economicData.settlements.settlemenmtConcept.amount.currency_not_found";
    private static final String INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT = "initialTotalInvested_amount_format";
    private static final String SETTLEMENT_CONCEPT_RATE_FORMAT = "settlementConcept_rate_format";
    private static final String DEPOSIT_ID = "deposit_id";
    private static final Double GMF = 0.0040;

    public static String cleanAndFormatNumberString(String input) {
        var noplus = input.replace("+", "");
        var nominus = noplus.replace("-", "");
        var nodots = nominus.replace(".","");
        return nodots.trim();
    }

    public void simulatePlacementInputValidation(RequestSimulatePlacementDTO requestBodyData, AmountRangeRequest amountRangeRequest){

        //Product Validation
        errorService.isBlank(requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        if(!productCode.equals(requestBodyData.getProduct().getProductCode())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
        }

        //SubProduct Validation        
        errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_format", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_length", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        if(!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
        }

        // Amount range validation
        String productAndSubProductId = requestBodyData.getProduct().getProductCode() + requestBodyData.getProduct().getSubproduct().getSubproductId();    
        amountRangeRequest.setProductId(productAndSubProductId);
        var amountRangeResponse = productDirectoryService.amountRange(amountRangeRequest);

        // Amount Validation
        errorService.isBlank(requestBodyData.getAmount().getAmount(), AMOUNT_FIELDNAME);
        regexUtils.validateRegex("amount_format", requestBodyData.getAmount().getAmount(), AMOUNT_FIELDNAME);
        amountValidation(requestBodyData.getAmount().getAmount(),
                amountRangeResponse.getMinimumAmount().getAmount(),
                amountRangeResponse.getMaximumAmount().getAmount());
                
        //Frecuency Validation
        errorService.isBlank(requestBodyData.getPeriodicity().getFrequency(), PERIDIOCITY_FRECUENCY);
        regexUtils.validateRegex("frecuency_code_format", requestBodyData.getPeriodicity().getFrequency(), PERIDIOCITY_FRECUENCY);
        frecuencyValidation(requestBodyData.getPeriodicity().getFrequency());
        
        //PeriodType Validation
        errorService.isBlank(requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        regexUtils.validateRegex("periodtype_code_format", requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        regexUtils.validateRegex("periodtype_code_length", requestBodyData.getPeriodicity().getPeriodTypeCode(), PERIDIOCITY_PERIOD_TYPE_CODE);
        if ( !"D".equals(requestBodyData.getPeriodicity().getPeriodTypeCode())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("periodtypecode_not_found"), ErrorType.FUNCTIONAL);
        }

        //SettlementConditionCode Validation
        errorService.isBlank(requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        regexUtils.validateRegex("settlementcondition_code_format", requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        regexUtils.validateRegex("settlementcondition_code_length", requestBodyData.getSettlementConditionCode(), SETTLEMENT_CONDITION_CODE_FIELDNAME);
        settlementContionCodeInputValidation(requestBodyData.getSettlementConditionCode());
    }


    //termDepositsInputValidation

    public void termDepositsInputValidation(RequestTermDepositsDTO requestBodyData,
                                            AmountRangeRequest amountRangeRequest,
                                            TermDepositParametersRequest termDepositParametersRequest,
                                            BanksParametersRequest banksParametersRequest) {
        validateBank(requestBodyData);
        validateCenter(requestBodyData);
        validateProduct(requestBodyData);
        validateSubproduct(requestBodyData);
        validateAccountIdType2(requestBodyData);
        validateBankCode(requestBodyData, banksParametersRequest);
        validateNationalIdentification(requestBodyData);
        validateSettlementConditionCode(requestBodyData);
        validatePurposeCode(requestBodyData, termDepositParametersRequest);
        validateTotalInvestedAmount(requestBodyData);
        validateCurrency(requestBodyData);
        validateAmountRange(requestBodyData,amountRangeRequest);                                     

        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        regexUtils.validateRegex("frecuency_code_format", requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        frecuencyValidation(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency());


        errorService.isBlank(requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex("settlementConcept_code_format", requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex("settlementConcept_code_length", requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        validateSettlementConcept(requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode());
        requestBodyData.getEconomicData().getSettlements().forEach( sett -> {
            if(sett.getSettlementConcept().getCode().equals("BGMF")){
                //economicData -> settlements [] -> settlementConcept -> code BGMF
                //economicData -> settlements [] -> settlementConcept -> typeCode BGMF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate BGMF
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }

            }
            if(sett.getSettlementConcept().getCode().equals("RETF")){
                //economicData -> settlements [] -> settlementConcept -> code RETF
                //economicData -> settlements [] -> settlementConcept -> typeCode RETF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate RETF
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }

            if(sett.getSettlementConcept().getCode().equals("ITEA")){
                //economicData -> settlements [] -> settlementConcept -> code ITEA
                //economicData -> settlements [] -> settlementConcept -> typeCode ITEA
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH , sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_TYPE_CODE);
                validateSettlementConceptTypeCodeInput(sett.getSettlementConcept().getTypeCode());
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                //economicData -> settlements [] -> settlementConcept -> rate ITEA
                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                //economicData -> settlements [] -> settlementConcept -> amount -> amount ITEA
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                //economicData -> settlements [] -> settlementConcept -> amount -> currency ITEA
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())){
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }
        });

    }

    public void validateAmountRange(RequestTermDepositsDTO requestBodyData,
    AmountRangeRequest amountRangeRequest) {
                 // Amount range validation
                 String productAndSubProductId = requestBodyData.getProduct().getProductCode() + requestBodyData.getProduct().getSubproduct().getSubproductId();    
                 amountRangeRequest.setProductId(productAndSubProductId);
                 var amountRangeResponse = productDirectoryService.amountRange(amountRangeRequest);
                                                        
          // Amount Validation
         errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), AMOUNT_FIELDNAME);
        regexUtils.validateRegex("amount_format", requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), AMOUNT_FIELDNAME);
        Double inputAmount = Double.parseDouble(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount().replace(",", ""));
        Double _minAmount = Double.parseDouble(amountRangeResponse.getMinimumAmount().getAmount().replace(",", ""));
        Double _maxAmount = Double.parseDouble(amountRangeResponse.getMaximumAmount().getAmount().replace(",", ""));
        Double max= _maxAmount+(_maxAmount*GMF);                        
        if (inputAmount < _minAmount) {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_under_limit_economicData"), ErrorType.FUNCTIONAL);
        }
        if(inputAmount > max){
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_over_limit_economicData"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateBank(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_format", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_length", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        if (!bankId.equals(requestBodyData.getBank().getBankId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("bankId_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateCenter(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        regexUtils.validateRegex("center_code_format", requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        regexUtils.validateRegex("center_code_length", requestBodyData.getBank().getCenter().getCenterId(), CENTER_ID_FIELDNAME);
        if (!centerId.equals(requestBodyData.getBank().getCenter().getCenterId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("centerId_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateProduct(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(), PRODUCT_CODE_FIELDNAME);
        if (!productCode.equals(requestBodyData.getProduct().getProductCode())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateSubproduct(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_format", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        regexUtils.validateRegex("subproduct_code_length", requestBodyData.getProduct().getSubproduct().getSubproductId(), SUBPRODUCT_ID_FIELDNAME);
        if (!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateAccountIdType2(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        regexUtils.validateRegex("accountIdType_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        regexUtils.validateRegex("accountIdType_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase(), ACCOUNT_ID_TYPE);
        if (!validateAccountIdType(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType().toUpperCase())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("accountIdType_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void validateBankCode(RequestTermDepositsDTO requestBodyData, BanksParametersRequest banksParametersRequest) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        regexUtils.validateRegex("bankId_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        regexUtils.validateRegex("bankId_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(), BANK_CODE);
        bankValidation(banksParametersRequest, requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode());
    }
    public void validateNationalIdentification(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        regexUtils.validateRegex("nationalIdentification_code_length", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        regexUtils.validateRegex("nationalIdentification_code_format", requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), NATIONAL_IDENTIFICATION);
        padLeftWithZeros(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount().getNationalIdentification(), 17);
    }
    public void validateSettlementConditionCode(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_format", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_length", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        settlementContionCodeInputValidationForDeposits(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase());
    }
    public void validatePurposeCode(RequestTermDepositsDTO requestBodyData, TermDepositParametersRequest termDepositParametersRequest) {
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(), DEPOSITS_PURPOSE_CODE);
        regexUtils.validateRegex("purposeCode_code_length", requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(), DEPOSITS_PURPOSE_CODE);
        purposeCodeValidation(termDepositParametersRequest, requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase());
    }
    public void validateTotalInvestedAmount(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), DEPOSITS_TOTAL_INVESTED_AMOUNT);
        regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(), DEPOSITS_TOTAL_INVESTED_AMOUNT);
    }
    public void validateCurrency(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(), DEPOSITS_CURRENCY);
        regexUtils.validateRegex(CURRENCY_CODE_FORMAT, requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(), DEPOSITS_CURRENCY);
        if (!"COP".equals(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("economicData.initialTotalInvested.currency_not_found"), ErrorType.FUNCTIONAL);
        }
    }



   public void deleteProspectCdtInputValidation(String depositId){
       errorService.isNull(depositId, DEPOSIT_ID);
       errorService.isBlank(depositId, DEPOSIT_ID);
       regexUtils.validateRegex("deposit_id_format", depositId, DEPOSIT_ID);
       regexUtils.validateRegex("strict_length_20", depositId, DEPOSIT_ID);
   }
    public void calculateDepositSummaryInputValidation(CalculateDepositSummaryRequestDTO calculateDepositSummaryRequestDTO){
        //Participant Validations
        errorService.isBlank(calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
        regexUtils.validateRegex("participants_id_format",calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
        regexUtils.validateRegex("participants_id_length",calculateDepositSummaryRequestDTO.getParticipants().getParticipantId(),PARTICIPANT_PARTICIPANTID);
    }
    public void settlementContionCodeInputValidation(String code) {
        if (!(code.length() == 1 && SETTLEMENT_CONDITON_CODES.contains(code))){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementconditioncode_not_found"), ErrorType.FUNCTIONAL);
        }
    }
    public void settlementContionCodeInputValidationForDeposits(String code) {
        if (!(code.length() == 1 && SETTLEMENT_CONDITON_CODES.contains(code))){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementcondition_code_not_found"), ErrorType.FUNCTIONAL);
        }
    }
        public static String settlementConditionCodeTransformation(String input){
            if (input.equals("C")){
                return input = "S";
            }else return input = "N";
        }


    public static boolean settlementConditionCodeValidation(String code){
        if (!"C".equals(code)){
            return false;
        }
        else return true;
    }


    public boolean frecuencyValidation(String input){
        
        for (String output : validFrecuencies) {
            if (output.equals(input)) {
                return false;
            }
        }
        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("frequency_not_found"), ErrorType.FUNCTIONAL);
    }


    public void amountValidation(String input, String minAmount, String maxAmount) {

        Double inputAmount = Double.parseDouble(input.replace(",", ""));
        Double _minAmount = Double.parseDouble(minAmount.replace(",", ""));
        Double _maxAmount = Double.parseDouble(maxAmount.replace(",", ""));

        if (inputAmount < _minAmount) {
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_under_limit"), ErrorType.FUNCTIONAL);
        }
        if(inputAmount > _maxAmount){
            throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST, errorService.getGeneral().get("amount_over_limit"), ErrorType.FUNCTIONAL);
        }
    }
    public static String padLeftWithZeros(String input, int length) {
        if (input.length() >= length) {
            return input; //
        }

        StringBuilder padded = new StringBuilder(input);
        while (padded.length() < length) {
            padded.insert(0, "0");
        }
        return padded.toString();
    }
    public static boolean validateAccountIdType(String input) {
        return input.equals("CC") || input.equals("CA");
    }
    public void validateSettlementConcept(String input) {
        if (!input.equals("BGMF") && !input.equals("RETF") && !input.equals("ITEA")){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'economicData.settlements.settlementConcept.code' not found",ErrorType.FUNCTIONAL);
        }
    }
    public void validateSettlementConceptTypeCodeInput(String input) {
        if (!input.equals("C") && !input.equals("D")){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("typeCode_not_found"), ErrorType.FUNCTIONAL);
        }

    }
    public String validateSettlementConceptTypeCode(String input) {
        if(input.equals("BGMF")){
            return input = "C";
        } else if (input.equals("RETF")) {
           return  input = "D";
        }
        else return input = "C";
    }
    public String purposeCodeValidation(TermDepositParametersRequest termDepositParametersRequest, String input){
        var init = input.substring(0,2);
        var termDeposit = termDepositParametersService.termDepositParameters(termDepositParametersRequest);
        var purpose = termDeposit.getParameters().stream().filter
                (x -> x.getCode().equals(init)).findFirst().orElse(null);
        if (purpose == null){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
        }
        if (!purpose.getCode().equals(init)){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
    }
        else return init;
    }
    public String bankValidation(BanksParametersRequest banksParametersRequest, String input){
        var banks = banksService.banksResponse(banksParametersRequest);
        var findBank = banks.getBanks().stream().filter(x -> x.getBankId().equals(input)).findFirst().orElse(null);
        if (findBank == null){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'deposit.placement.destinationFunds.bankcode' not found", ErrorType.FUNCTIONAL);
        }
        if (!findBank.getBankId().equals(input)){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "'deposit.placement.destinationFunds.bankcode' not found", ErrorType.FUNCTIONAL);
        }
        else return input;

    }


    public static Double parseDouble(String input) {

        var noplus = input.replace("+", "");
        var nominus = noplus.replace("-", "");
        var nodots = nominus.replace(".","");
        var _input = nodots.replace(",", ".");
        Double number = Double.parseDouble(_input);

        return number;
    }

    public static String toLinea2Decimal(String input) {

        Integer entero = Integer.parseInt(input.substring(0,13));
        String decimal = input.substring(13);
        String numero = entero.toString() + "," + decimal;

        return numero;
    }

    public static String format15DigitNumber(String number){

        if(number == null || number.isBlank() || number.isEmpty()) return "0,00";

        String noZerosNumber = number.replaceFirst("^0+(?!$)", "");

        if(noZerosNumber.length() == 1) return "0,0" + noZerosNumber;
        if(noZerosNumber.length() == 2) return "0," + noZerosNumber;        

        return noZerosNumber.substring(0, noZerosNumber.length()-2) + "," + noZerosNumber.substring(noZerosNumber.length() - 2, noZerosNumber.length());
    }

    public static String removeLeadingZeros(String number){

        if(number == null || number.isBlank() || number.isEmpty()) return "0";

        return number.replaceFirst("^0+(?!$)", "");

    }

}//class closure




los test existentes
package com.santander.bnc.bsn049.bncbsn049mstermdeposits.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.CalculateDepositSummaryRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.calculatedepositsummary.request.ParticipantsRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.AmountRangeResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.productdirectory.MaxAndMinAmountDto;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.domain.termdepositparameters.TermDepositParametersResponse;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.AmountRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.Product;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement.SubproductRequestDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.term_deposits.TermDepositDepositDto;
import com.santander.bnc.bsn049.bncbsn049mstermdeposits.response.term_deposits.TermDepositPlacementDTO;

class TermDepositUtilsTest {

	private TermDepositUtils utils;
	private ErrorService errorService;	
	private RegexUtils regexUtils;
    private ProductDirectoryService productDirectoryService;
    private TermDepositParametersService termDepositParametersService;
    private BanksService banksService;


	@BeforeEach
	void setUp() {
		RegexUtils regexUtils = mock(RegexUtils.class);
		errorService = mock(ErrorService.class);

        productDirectoryService = mock(ProductDirectoryService.class);
        termDepositParametersService = mock(TermDepositParametersService.class);
        banksService = mock(BanksService.class);

        this.regexUtils = mock(RegexUtils.class);
        errorService = mock(ErrorService.class);
        productDirectoryService = mock(ProductDirectoryService.class);
        termDepositParametersService = mock(TermDepositParametersService.class);
        banksService = mock(BanksService.class);

        utils = new TermDepositUtils(
                this.regexUtils,
                errorService,
                productDirectoryService,
                termDepositParametersService,
                banksService
        );

		ReflectionTestUtils.setField(utils, "validFrecuencies", new String[] { "30", "60", "90" });
		ReflectionTestUtils.setField(utils, "validSettlements", new String[] { "C", "V" });
		ReflectionTestUtils.setField(utils, "SETTLEMENT_CONDITON_CODES", "CV");
		
		ReflectionTestUtils.setField(utils, "productCode", "940250");
        ReflectionTestUtils.setField(utils, "subproductCode", "001");
        ReflectionTestUtils.setField(utils, "bankId", "001");
        ReflectionTestUtils.setField(utils, "centerId", "1234");

		when(errorService.getGeneral()).thenReturn(new HashMap<>());
		when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
				.thenReturn(new ServiceException(HttpStatus.BAD_REQUEST, ErrorDTO.builder().message("error").build()));
		
		
		



        HashMap<String, String> errors = new HashMap<>();
        errors.put("productcode_not_found", "product error");
        errors.put("subproductid_not_found", "subproduct error");
        errors.put("bankid_not_found", "bank error");
        errors.put("centerid_not_found", "center error");
        errors.put("accountIdType_not_found", "account error");
        errors.put("settlementconditioncode_not_found", "settlement error");
        errors.put("periodtypecode_not_found", "period error");
        errors.put("frecuency_not_found", "frequency error");
        errors.put("purposecode_not_found", "purpose error");
        errors.put("purposeCode_not_found", "purpose error");
        errors.put("amount_under_limit", "amount under");
        errors.put("amount_over_limit", "amount over");
        errors.put("amount_under_limit_economicData", "amount under");
        errors.put("amount_over_limit_economicData", "amount over");
        errors.put("economicData.initialTotalInvested.currency_not_found", "currency error");
        errors.put("deposits.placement.destinationFunds.bankcode_not_found", "bank code error");

        when(errorService.getGeneral()).thenReturn(errors);
        
//        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
//                .thenAnswer(inv -> new ServiceException(inv.getArgument(0),
//                        ErrorDTO.builder().message(String.valueOf(inv.getArgument(1))).build()));

        when(errorService.serviceExceptionBuilder(any(HttpStatus.class), any(), any(ErrorType.class)))
        .thenAnswer(inv -> new ServiceException(
                inv.getArgument(0),
                ErrorDTO.builder()
                        .message(inv.getArgument(1) == null ? "error" : String.valueOf(inv.getArgument(1)))
                        .build()
        ));
	}

	@Test
	void shouldCoverStaticUtils() {
		assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("+123.456"));
		assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
		assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("V"));
		assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
		assertFalse(TermDepositUtils.settlementConditionCodeValidation("V"));
		assertEquals("00012", TermDepositUtils.padLeftWithZeros("12", 5));
		assertEquals("12345", TermDepositUtils.padLeftWithZeros("12345", 5));
		assertTrue(TermDepositUtils.validateAccountIdType("CC"));
		assertTrue(TermDepositUtils.validateAccountIdType("CA"));
		assertFalse(TermDepositUtils.validateAccountIdType("TI"));
		assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
		assertEquals("123,45", TermDepositUtils.toLinea2Decimal("000000000012345"));
		assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
		assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
		assertEquals("0,05", TermDepositUtils.format15DigitNumber("5"));
		assertEquals("0,50", TermDepositUtils.format15DigitNumber("50"));
		assertEquals("123,45", TermDepositUtils.format15DigitNumber("0000012345"));
		assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
		assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
		assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
	}

	@Test
	void shouldCoverFrequencyValidAndInvalid() {
		assertFalse(utils.frecuencyValidation("30"));
		assertThrows(Exception.class, () -> utils.frecuencyValidation("15"));
	}

	@Test
	void shouldCoverAmountValidation() {
		assertDoesNotThrow(() -> utils.amountValidation("1000", "500", "2000"));
		assertThrows(Exception.class, () -> utils.amountValidation("100", "500", "2000"));
		assertThrows(Exception.class, () -> utils.amountValidation("3000", "500", "2000"));
	}

	@Test
	void shouldCoverSettlementConditionInputs() {
		assertDoesNotThrow(() -> utils.settlementContionCodeInputValidation("C"));
		assertDoesNotThrow(() -> utils.settlementContionCodeInputValidationForDeposits("V"));

		assertThrows(Exception.class, () -> utils.settlementContionCodeInputValidation("X"));
		assertThrows(Exception.class, () -> utils.settlementContionCodeInputValidationForDeposits("X"));
	}

	@Test
	void shouldCoverSettlementConceptValidations() {
		assertDoesNotThrow(() -> utils.validateSettlementConcept("BGMF"));
		assertDoesNotThrow(() -> utils.validateSettlementConcept("RETF"));
		assertDoesNotThrow(() -> utils.validateSettlementConcept("ITEA"));
		assertThrows(Exception.class, () -> utils.validateSettlementConcept("BAD"));

		assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("C"));
		assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("D"));
		assertThrows(Exception.class, () -> utils.validateSettlementConceptTypeCodeInput("X"));

		assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
		assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
		assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
	}
	
	
    @Test
    void shouldCoverStaticMethods() {
        assertEquals("123456", TermDepositUtils.cleanAndFormatNumberString("+123.456"));
        assertEquals("123,45", TermDepositUtils.toLinea2Decimal("000000000012345"));
        assertEquals("00012", TermDepositUtils.padLeftWithZeros("12", 5));
        assertEquals("12345", TermDepositUtils.padLeftWithZeros("12345", 5));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(null));
        assertEquals("0", TermDepositUtils.removeLeadingZeros(""));
        assertEquals("123", TermDepositUtils.removeLeadingZeros("000123"));
        assertEquals(1234.56, TermDepositUtils.parseDouble("+1.234,56"));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(null));
        assertEquals("0,00", TermDepositUtils.format15DigitNumber(""));
        assertEquals("0,05", TermDepositUtils.format15DigitNumber("5"));
        assertEquals("0,50", TermDepositUtils.format15DigitNumber("50"));
        assertEquals("123,45", TermDepositUtils.format15DigitNumber("0000012345"));
        assertEquals("S", TermDepositUtils.settlementConditionCodeTransformation("C"));
        assertEquals("N", TermDepositUtils.settlementConditionCodeTransformation("V"));
        assertTrue(TermDepositUtils.settlementConditionCodeValidation("C"));
        assertFalse(TermDepositUtils.settlementConditionCodeValidation("V"));
        assertTrue(TermDepositUtils.validateAccountIdType("CC"));
        assertTrue(TermDepositUtils.validateAccountIdType("CA"));
        assertFalse(TermDepositUtils.validateAccountIdType("XX"));
    }

    @Test
    void shouldCoverFrequencyAndAmountValidation() {
        assertFalse(utils.frecuencyValidation("30"));
        assertThrows(Exception.class, () -> utils.frecuencyValidation("15"));

        assertDoesNotThrow(() -> utils.amountValidation("1000", "500", "2000"));
        assertThrows(Exception.class, () -> utils.amountValidation("100", "500", "2000"));
//        assertThrows(ServiceException.class, () -> utils.amountValidation("3000", "500", "2000"));
    }

    @Test
    void shouldCoverSettlementValidations() {
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidation("C"));
        assertDoesNotThrow(() -> utils.settlementContionCodeInputValidationForDeposits("V"));
        assertThrows(Exception.class, () -> utils.settlementContionCodeInputValidation("X"));
        assertThrows(Exception.class, () -> utils.settlementContionCodeInputValidationForDeposits("X"));

        assertDoesNotThrow(() -> utils.validateSettlementConcept("BGMF"));
        assertDoesNotThrow(() -> utils.validateSettlementConcept("RETF"));
        assertDoesNotThrow(() -> utils.validateSettlementConcept("ITEA"));
        assertThrows(Exception.class, () -> utils.validateSettlementConcept("BAD"));

        assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("C"));
        assertDoesNotThrow(() -> utils.validateSettlementConceptTypeCodeInput("D"));
        assertThrows(Exception.class, () -> utils.validateSettlementConceptTypeCodeInput("X"));

        assertEquals("C", utils.validateSettlementConceptTypeCode("BGMF"));
        assertEquals("D", utils.validateSettlementConceptTypeCode("RETF"));
        assertEquals("C", utils.validateSettlementConceptTypeCode("ITEA"));
    }

    @Test
    void shouldCoverSimpleInputValidations() {
        utils.deleteProspectCdtInputValidation("12345678901234567890");

        CalculateDepositSummaryRequestDTO dto = new CalculateDepositSummaryRequestDTO();
        ParticipantsRequestDTO participants = new ParticipantsRequestDTO();
        participants.setParticipantId("12345678");
        dto.setParticipants(participants);

        utils.calculateDepositSummaryInputValidation(dto);

//        verify(regexUtils, atLeastOnce()).validateRegex(anyString(), anyString(), anyString());
    }

    @Test
    void shouldCoverSimulatePlacementInputValidationOk() {
        AmountRangeResponse range = new AmountRangeResponse();
        
        MaxAndMinAmountDto min = new MaxAndMinAmountDto();
        min.setAmount("500");
        MaxAndMinAmountDto max = new MaxAndMinAmountDto();
        max.setAmount("2000");
        range.setMinimumAmount(min);
        range.setMaximumAmount(max);

        when(productDirectoryService.amountRange(any())).thenReturn(range);

        RequestSimulatePlacementDTO request = simulateRequest("940250", "001", "1000", "30", "D", "C");
        //940250001
        //requestBodyData.getProduct().getProductCode() + requestBodyData.getProduct().getSubproduct().getSubproductId();    
//        assertDoesNotThrow(() -> utils.simulatePlacementInputValidation(request, new AmountRangeRequest()));
    }

    @Test
    void shouldCoverSimulatePlacementInputValidationErrors() {
        AmountRangeResponse range = new AmountRangeResponse();
        MaxAndMinAmountDto min = new MaxAndMinAmountDto();
        min.setAmount("500");
        MaxAndMinAmountDto max = new MaxAndMinAmountDto();
        max.setAmount("2000");
        range.setMinimumAmount(min);
        range.setMaximumAmount(max);

        when(productDirectoryService.amountRange(any())).thenReturn(range);

        assertThrows(Exception.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("000000", "001", "1000", "30", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(Exception.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "999", "1000", "30", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(Exception.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "30", "M", "C"),
                        new AmountRangeRequest()));

        assertThrows(Exception.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "15", "D", "C"),
                        new AmountRangeRequest()));

        assertThrows(Exception.class,
                () -> utils.simulatePlacementInputValidation(
                        simulateRequest("940250", "001", "1000", "30", "D", "X"),
                        new AmountRangeRequest()));
    }



    @Test
    void shouldCoverPurposeCodeValidation() throws Exception {
        TermDepositParametersResponse response = mock(TermDepositParametersResponse.class, RETURNS_DEEP_STUBS);
        when(termDepositParametersService.termDepositParameters(any())).thenReturn(response);

//        assertDoesNotThrow(() -> utils.purposeCodeValidation(new TermDepositParametersRequest(), "010"));
//        assertDoesNotThrow(() -> utils.validatePurposeCode(termRequest("940250", "001", "001", "1234", "CC", "001", "123456789", "C", "010", "1000", "COP", "30"), new TermDepositParametersRequest()));
    }



    private RequestSimulatePlacementDTO simulateRequest(String productCode, String subproductCode, String amountValue,
                                                       String frequency, String periodType, String settlement) {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId(subproductCode);

        Product product = new Product();
        product.setProductCode(productCode);
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount(amountValue);

        RequestDTO periodicity = new RequestDTO();
        periodicity.setFrequency(frequency);
        periodicity.setPeriodTypeCode(periodType);

        RequestSimulatePlacementDTO request = new RequestSimulatePlacementDTO();
        request.setProduct(product);
        request.setAmount(amount);
        request.setPeriodicity(periodicity);
        request.setSettlementConditionCode(settlement);

        return request;
    }

    private RequestTermDepositsDTO termRequest(String productCode, String subproductCode, String bankId,
                                               String centerId, String accountType, String bankCode,
                                               String nationalId, String settlementCode, String purposeCode,
                                               String amount, String currency, String frequency) throws Exception {
        RequestTermDepositsDTO request = new RequestTermDepositsDTO();

        Object product = newObj("TermDepositProductDTO");
        Object subproduct = newObj("TermDepositSubproductDTO");
        call(subproduct, "setSubproductId", subproductCode);
        call(product, "setProductCode", productCode);
        call(product, "setSubproduct", subproduct);
        call(request, "setProduct", product);

        Object bank = newObj("TermDepositBankRequestDTO");
        Object center = newObj("TermDepositCenterDTO");
        call(center, "setCenterId", centerId);
        call(bank, "setBankId", bankId);
        call(bank, "setCenter", center);
        call(request, "setBank", bank);

        Object amountDto = newObj("TermDepositAmountDTO");
        call(amountDto, "setAmount", amount);
        call(amountDto, "setCurrency", currency);

        Object economic = newObj("TermDepositEconomicDataDTO");
        Object settlement = newObj("TermDepositSettlementsDTO");
        Object concept = newObj("TermDepositSettlementConceptDTO");
        Object conceptAmount = newObj("TermDepositAmountDTO");
        call(conceptAmount, "setAmount", "100");
        call(conceptAmount, "setCurrency", "COP");
        call(concept, "setCode", "BGMF");
        call(concept, "setTypeCode", "C");
        call(concept, "setRate", "0");
        call(concept, "setAmount", conceptAmount);
        call(settlement, "setSettlementConcept", concept);
        call(economic, "setInitialTotalInvested", amountDto);
        call(economic, "setSettlements", List.of(settlement));
        call(request, "setEconomicData", economic);

        Object deposit = newObj("TermDepositDepositDto");
        Object placement = newObjResponse("TermDepositPlacementDTO");
        Object destinationFunds = newObj("TermDepositDestinationFundsDTO");
        Object account = newObj("TermDepositAccountDTO");
        Object periodicity = newObj("TermDepositPeriodicityDTO");
        Object settlementCondition = newObj("TermDepositSettlementConditionDTO");

        call(account, "setNationalIdentification", nationalId);
        call(destinationFunds, "setAccountIdType", accountType);
        call(destinationFunds, "setBankcode", bankCode);
        call(destinationFunds, "setAccount", account);

        call(periodicity, "setFrequency", frequency);

        call(settlementCondition, "setCode", settlementCode);

        call(placement, "setDestinationFunds", destinationFunds);
        call(placement, "setPeriodicity", periodicity);
        call(placement, "setSettlementCondition", settlementCondition);
        call(placement, "setPurposeCode", purposeCode);

        call(deposit, "setPlacement", placement);
        call(request, "setDeposit", deposit);

        return request;
    }
    
    private Object newObj(String simpleName) throws Exception {
        String base = 
        		TermDepositDepositDto.class.getPackageName()+".";
        String[] candidates = {
                simpleName,
                simpleName.replace("TermDeposit", ""),
                simpleName.replace("DTO", "RequestDTO"),
                simpleName.replace("TermDeposit", "").replace("DTO", "RequestDTO")
        };

        for (String candidate : candidates) {
            try {
                return Class.forName(base + candidate)
                        .getDeclaredConstructor()
                        .newInstance();
            } catch (ClassNotFoundException ignored) {
            }
        }

        throw new ClassNotFoundException(base + simpleName);
    }

    private Object newObjResponse(String simpleName) throws Exception {
        String base = 
        		TermDepositPlacementDTO.class.getPackageName()+".";
        String[] candidates = {
                simpleName,
                simpleName.replace("TermDeposit", ""),
                simpleName.replace("DTO", "RequestDTO"),
                simpleName.replace("TermDeposit", "").replace("DTO", "RequestDTO")
        };

        for (String candidate : candidates) {
            try {
                return Class.forName(base + candidate)
                        .getDeclaredConstructor()
                        .newInstance();
            } catch (ClassNotFoundException ignored) {
            }
        }

        throw new ClassNotFoundException(base + simpleName);
    }
    private void call(Object target, String methodName, Object value) throws Exception {
        Method method = findMethod(target.getClass(), methodName);
        assertNotNull(method, methodName);
        method.setAccessible(true);
        method.invoke(target, value);
    }

    private Method findMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                return method;
            }
        }
        return null;
    }
    

    @Test
    void shouldCoverValidateAmountRangeUnderAndOverLimit() throws Exception {
        mockAmountRange("500", "2000");

        RequestTermDepositsDTO under =
                termRequest("940250", "001", "001", "1234", "CC", "001",
                        "123456789", "C", "010", "100", "COP", "30");

        RequestTermDepositsDTO over =
                termRequest("940250", "001", "001", "1234", "CC", "001",
                        "123456789", "C", "010", "999999", "COP", "30");

        assertThrows(Exception.class,
                () -> utils.validateAmountRange(under, new AmountRangeRequest()));

        assertThrows(Exception.class,
                () -> utils.validateAmountRange(over, new AmountRangeRequest()));
    }

    @Test
    void shouldCoverValidateBankCodeInvalid() throws Exception {
        mockBanks("999");

        RequestTermDepositsDTO request =
                termRequest("940250", "001", "001", "1234", "CC", "001",
                        "123456789", "C", "010", "1000", "COP", "30");

        assertThrows(Exception.class,
                () -> utils.validateBankCode(request, new BanksParametersRequest()));
    }

    @Test
    void shouldCoverSettlementConceptCurrencyInvalidInsideTermDepositsInputValidation() throws Exception {
        mockAmountRange("500", "2000");
        mockBanks("001");
        mockPurpose("010");

        RequestTermDepositsDTO request =
                termRequest("940250", "001", "001", "1234", "CC", "001",
                        "123456789", "C", "010", "1000", "COP", "30");

        Object economicData = get(request, "getEconomicData");
        List<?> settlements = (List<?>) get(economicData, "getSettlements");
        Object settlementConcept = get(settlements.get(0), "getSettlementConcept");
        Object amount = get(settlementConcept, "getAmount");

        set(amount, "setCurrency", "USD");

        assertThrows(Exception.class, () -> utils.termDepositsInputValidation(
                request,
                new AmountRangeRequest("auth", "client", null),
                new TermDepositParametersRequest("940250", "auth", "client"),
                new BanksParametersRequest("auth", "client")
        ));
    }
    
    private void mockAmountRange(String minValue, String maxValue) {
        AmountRangeResponse response = new AmountRangeResponse();

        MaxAndMinAmountDto min = new MaxAndMinAmountDto();
        min.setAmount(minValue);

        MaxAndMinAmountDto max = new MaxAndMinAmountDto();
        max.setAmount(maxValue);

        response.setMinimumAmount(min);
        response.setMaximumAmount(max);

        when(productDirectoryService.amountRange(any())).thenReturn(response);
    }

    private void mockBanks(String bankId) {
        BanksParametersDTO bank = new BanksParametersDTO();
        bank.setBankId(bankId);
        bank.setBankName("BANK");

        BanksDTO banksDTO = new BanksDTO();
        banksDTO.setBanks(List.of(bank));

        when(banksService.banksResponse(any())).thenReturn(banksDTO);
    }

    private void mockPurpose(String purposeCode) {
        TermDepositParametersResponse response = mock(TermDepositParametersResponse.class, RETURNS_DEEP_STUBS);

        when(termDepositParametersService.termDepositParameters(any())).thenReturn(response);
    }


    private Object get(Object target, String getterName) throws Exception {
        Method method = target.getClass().getMethod(getterName);
        return method.invoke(target);
    }

    private void set(Object target, String setterName, Object value) throws Exception {
        Method setter = findSetter(target.getClass(), setterName);
        setter.invoke(target, value);
    }

    private Method findSetter(Class<?> clazz, String setterName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                return method;
            }
        }
        throw new IllegalArgumentException(setterName);
    }
}

