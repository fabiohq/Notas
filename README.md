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
