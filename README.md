   public DepositPlacementResponseDTO bp13toDepositsPlacementResponse(TrxBP13Response trxBP17Response,
                                                                       TermDepositParametersRequest termDepositParametersRequest) {

        DepositPlacementResponseDTO response = new DepositPlacementResponseDTO();

        assignProductData(trxBP17Response, response);
        Placement placement = createPlacementData(trxBP17Response);
        response.setPlacement(placement);

        setRenewable(trxBP17Response, placement);
        setCapitalized(trxBP17Response, placement);
        setBlocked(trxBP17Response, placement);
        setOriginIdentifier(trxBP17Response, placement);
        setSettlementCondition(trxBP17Response, placement);
        setAnnualPercentageYield(trxBP17Response, placement);
        setRate(trxBP17Response, placement);
        setDestinationFunds(trxBP17Response, placement);

        String observaciones = ajustarValor(trxBP17Response.getData().getObservaciones());
        setPurpose(trxBP17Response, termDepositParametersRequest, placement, observaciones);

        placement.setLastRenewalDate(trxBP17Response.getData().getFecAlta());

        ProfitabilityAtMaturity profitabilityAtMaturity = new ProfitabilityAtMaturity();
        String linea2 = trxBP17Response.getData().getLina2();

        profitabilityAtMaturity.setAmount(TermDepositUtils.format15DigitNumber(linea2.substring(32, 47)));
        profitabilityAtMaturity.setCurrency(trxBP17Response.getData().getMoneda());
        placement.setProfitabilityAtMaturity(profitabilityAtMaturity);

        InitialTotalInvested initialTotalInvested = new InitialTotalInvested();
        initialTotalInvested
                .setAmount(TermDepositUtils.format15DigitNumber(trxBP17Response.getData().getSaldoInicial()));
        initialTotalInvested.setCurrency(trxBP17Response.getData().getMoneda());
        placement.setInitialTotalInvested(initialTotalInvested);

        List<Settlement> settlement = new ArrayList<>();

        // settlementBGMF
        Settlement settlementBGMF = new Settlement();
        SettlementConcept settlementConceptBGMF = new SettlementConcept();
        settlementConceptBGMF.setCode("BGMF");
        settlementConceptBGMF.setDescription("BENEFICIO GMF");
        settlementConceptBGMF.setTypeCode("C");
        settlementConceptBGMF.setTypeDescription(credit);

        if (observaciones.length() >= 6) {
            String rateBGMF2 = "0,0" + observaciones.substring(3, 6);
            settlementConceptBGMF.setRate(rateBGMF2);
        } else {
            settlementConceptBGMF.setRate("0,0");
        }

        Amount amountBGMF = new Amount();
        String amountBGFM2 = "";
        if (observaciones.length() >= 15) {
            amountBGFM2 = TermDepositUtils.format15DigitNumber(observaciones.substring(7, 15) + "00");
        }
        amountBGMF.setAmount((amountBGFM2));
        amountBGMF.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptBGMF.setAmount(amountBGMF);
        settlementBGMF.setSettlementConcept(settlementConceptBGMF);
        settlement.add(settlementBGMF);
        // END settlementBGMF

        // settlementRETF
        Settlement settlementRETF = new Settlement();
        SettlementConcept settlementConceptRETF = new SettlementConcept();
        settlementConceptRETF.setCode("RETF");
        settlementConceptRETF.setDescription("RETENCION EN LA FUENTE");
        settlementConceptRETF.setTypeCode("D");
        settlementConceptRETF.setTypeDescription("DEBIT");

        String rateRETF2 = "0,0";
        if (observaciones.length() >= 19) {
            rateRETF2 += observaciones.substring(16, 19);
        }

        settlementConceptRETF.setRate(rateRETF2);
        Amount amountRETF = new Amount();
        String amountRETFValue = "";
        if (observaciones.length() > 21) {
            amountRETFValue = observaciones.substring(21);
        }
        amountRETF.setAmount(TermDepositUtils.format15DigitNumber(amountRETFValue + "00"));
        amountRETF.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptRETF.setAmount(amountRETF);
        settlementRETF.setSettlementConcept(settlementConceptRETF);
        settlement.add(settlementRETF);

        // END settlementRETF

        // settlementINVB
        Settlement settlementINVB = new Settlement();
        SettlementConcept settlementConceptINVB = new SettlementConcept();
        settlementConceptINVB.setCode("INVB");
        settlementConceptINVB.setDescription("INVERSION BASE");
        settlementConceptINVB.setTypeCode("C");
        settlementConceptINVB.setTypeDescription(credit);

        settlementConceptINVB.setRate(variableZero);

        Amount amountINVB = new Amount();
        String amountINVB2 = TermDepositUtils.format15DigitNumber(linea2.substring(0, 15));

        amountINVB.setAmount(amountINVB2);
        amountINVB.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptINVB.setAmount(amountINVB);
        settlementINVB.setSettlementConcept(settlementConceptINVB);
        settlement.add(settlementINVB);
        // END settlementINVB

        // settlementTOTN
        Settlement settlementTOTN = new Settlement();
        SettlementConcept settlementConceptTOTN = new SettlementConcept();
        settlementConceptTOTN.setCode("INTN");
        settlementConceptTOTN.setDescription("INTERES NETO");
        settlementConceptTOTN.setTypeCode("C");
        settlementConceptTOTN.setTypeDescription(credit);
        settlementConceptTOTN.setRate(variableZero);

        Amount amountTOTN = new Amount();

        var interesNeto = TermDepositUtils.format15DigitNumber(linea2.substring(32, 47));
        String beneficio = "";
        if (observaciones.length() >= 15) {
            beneficio = TermDepositUtils.format15DigitNumber(observaciones.substring(7, 15) + "00");
        }
        Double num2 = 0.0; // Valor por defecto en caso de que beneficio esté vacío
        if (!beneficio.isBlank()) {
            num2 = Double.parseDouble(beneficio.replace(",", "."));
        }
        var inversionBase = TermDepositUtils.format15DigitNumber(linea2.substring(0, 15));

        Double num1 = Double.parseDouble(interesNeto.replace(",", "."));
        Double num3 = Double.parseDouble(inversionBase.replace(",", "."));
        Double diferencia = num1 - num2 - num3;

        String amountTOTN2 = String.format("%.2f", diferencia).replace(".", ",");

        amountTOTN.setAmount(amountTOTN2);
        amountTOTN.setCurrency(trxBP17Response.getData().getMoneda());
        settlementConceptTOTN.setAmount(amountTOTN);
        settlementTOTN.setSettlementConcept(settlementConceptTOTN);

        settlement.add(settlementTOTN);
        // END settlementTOTN

        // settlementConcept INTA
        Settlement settlementINTA = new Settlement();
        SettlementConcept settlementConceptINTA = new SettlementConcept();
        settlementConceptINTA.setCode("INTA");
        settlementConceptINTA.setDescription("INTERES ABONADO");
        settlementConceptINTA.setTypeCode("C");
        settlementConceptINTA.setTypeDescription(credit);
        settlementConceptINTA.setRate(variableZero);

        Amount amountINTA = new Amount();
        String interesesAbonado = trxBP17Response.getData().getInteresesAvonado();
        amountINTA.setAmount(limpiarCantidad(interesesAbonado));
        amountINTA.setCurrency(trxBP17Response.getData().getMoneda());

        settlementConceptINTA.setAmount(amountINTA);
        settlementINTA.setSettlementConcept(settlementConceptINTA);
        settlement.add(settlementINTA);
        // END settlementConcept INTA

        // settlementConcept SALD
        Settlement settlementSALD = new Settlement();
        SettlementConcept settlementConceptSALD = new SettlementConcept();
        settlementConceptSALD.setCode("SALD");
        settlementConceptSALD.setDescription("SALDO PENDIENTE");
        settlementConceptSALD.setTypeCode("C");
        settlementConceptSALD.setTypeDescription(credit);
        settlementConceptSALD.setRate(variableZero);

        Amount amountSALD = new Amount();
        String saldoDisponible = trxBP17Response.getData().getSaldoDisponible();
        amountSALD.setAmount(formatearCantidad(saldoDisponible));
        amountSALD.setCurrency(trxBP17Response.getData().getMoneda());

        settlementConceptSALD.setAmount(amountSALD);
        settlementSALD.setSettlementConcept(settlementConceptSALD);
        settlement.add(settlementSALD);
        // END settlementConcept SALD

        placement.setSettlements(settlement);

        // trxBP17Response
        response.setPlacement(placement);
        return response;
    }

    private void assignProductData(TrxBP13Response trxBP17Response, DepositPlacementResponseDTO response) {
        if (!trxBP17Response.getData().getProducto().isBlank()) {
            Contract contract = new Contract();
            Product product = new Product();

            product.setProductCode(trxBP17Response.getData().getProducto().substring(0, 2));// 04
            product.setProductDescription(trxBP17Response.getData().getProducto().substring(3));// CDT
            contract.setProduct(product);
            response.setContract(contract);
        }
    }

    private Placement createPlacementData(TrxBP13Response trxBP17Response) {
        Placement placement = new Placement();
        PlacementIdentification placementIdentification = new PlacementIdentification();
        placementIdentification.setIsin(trxBP17Response.getData().getNumCertificado());// CERTIFI
        placement.setPlacementIdentification(placementIdentification);

        StatusInfo statusInfo = new StatusInfo();
        statusInfo.setStatusCode(trxBP17Response.getData().getEstadoIPF());
        String estadoIPF = trxBP17Response.getData().getEstadoIPF();
        String estado = "";
        switch (estadoIPF) {
            case "A":
                estado = "ACTIVO";
                break;
            case "P":
                estado = "PREAPERTURADO";
                break;
            case "V":
                estado = "VENCIDO";
                break;
            case "C":
                estado = "CANCELADO";
                break;
            case "Z":
                estado = "ANULADO";
                break;
            case "X":
                estado = "VENCIDO PENDIENTE DE PAGO";
                break;
            case "N":
                estado = "RETENIDO";
                break;
            default:
                break;
        }
        statusInfo.setStatusDescription(estado);

        placement.setStatusInfo(statusInfo);

        if (!trxBP17Response.getData().getSubproducto().isBlank()) {
            Subproduct subproduct = new Subproduct();
            subproduct.setSubproductId(trxBP17Response.getData().getSubproducto().substring(0, 4));
            subproduct.setName(trxBP17Response.getData().getSubproducto().substring(5));
            placement.setSubproduct(subproduct);
        } // end subproduct

        Currency currency = new Currency();
        currency.setCode(trxBP17Response.getData().getMoneda());
        placement.setCurrency(currency);

        Periodicity periodicity = new Periodicity();
        periodicity.setFrequency(trxBP17Response.getData().getPlazo());
        periodicity.setPeriodTypeCode("D");
        periodicity.setPeriodTypeDescription("DIAS");
        placement.setPeriodicity(periodicity);

        placement.setMaturityDate(trxBP17Response.getData().getFecVencimiento());
        placement.setOpeningDate(trxBP17Response.getData().getFecAlta());

        return placement;
    }

    private void setRenewable(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setRenewable(trxBP17Response.getData().getRenovacionAutomatica().equals("S"));
    }

    private void setCapitalized(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setCapitalized(trxBP17Response.getData().getCapInteres().equals("S"));
    }

    private void setBlocked(TrxBP13Response trxBP17Response, Placement placement) {
        placement.setBlocked(trxBP17Response.getData().getIndicadorBloqueo().equals("S"));
    }
