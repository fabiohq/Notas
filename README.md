package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksDTO {
    private List<BanksParametersDTO> Banks;
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersDTO {
    private String bankId;
    private String bankName;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersRequest {
    private String authorization;
    private String xSantanderClientId;
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksResponseDTO {
    private BanksDTO banks;

    public BanksDTO getBanks() {
        return banks;
    }

    public void setBanks(BanksDTO banks) {
        this.banks = banks;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositPlacementRequestDTO {
    private String authorization;
    private String xSantanderClientId;
    private String depositId;
    private String placementId;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getxSantanderClientId() {
        return xSantanderClientId;
    }

    public void setxSantanderClientId(String xSantanderClientId) {
        this.xSantanderClientId = xSantanderClientId;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String nationalIdentification;
    private String statusDescription;

    public String getNationalIdentification() {
        return nationalIdentification;
    }

    public void setNationalIdentification(String nationalIdentification) {
        this.nationalIdentification = nationalIdentification;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amount {
    private String amount;
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositPlacementResponseDTO {
    private Contract contract;
    private Placement placement;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationFunds {
    private String accountIdType;
    private String Bankcode;
    private Account account;

    public String getAccountIdType() {
        return accountIdType;
    }

    public void setAccountIdType(String accountIdType) {
        this.accountIdType = accountIdType;
    }

    public String getBankcode() {
        return Bankcode;
    }

    public void setBankcode(String bankcode) {
        Bankcode = bankcode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitialTotalInvested {
    private String amount;
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OriginIdentifier {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Periodicity {
    private int frequency;
    private String periodTypeCode;
    private String periodTypeDescription;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }

    public String getPeriodTypeDescription() {
        return periodTypeDescription;
    }

    public void setPeriodTypeDescription(String periodTypeDescription) {
        this.periodTypeDescription = periodTypeDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Placement {
    private PlacementIdentification placementIdentification;
    private StatusInfo statusInfo;
    private Subproduct subproduct;
    private Currency currency;
    private Periodicity periodicity;
    private String maturityDate;
    private String openingDate;
    private boolean isRenewable;
    private boolean isCapitalized;
    private boolean isBlocked;
    private OriginIdentifier originIdentifier;
    private SettlementCondition settlementCondition;
    private String annualPercentageYield;
    private String rate;
    private DestinationFunds destinationFunds;
    private String purposeCode;
    private String purposeDescription;
    private String lastRenewalDate;
    private ProfitabilityAtMaturity profitabilityAtMaturity;
    private InitialTotalInvested initialTotalInvested;
    private List<Settlement> settlements;

    public PlacementIdentification getPlacementIdentification() {
        return placementIdentification;
    }

    public void setPlacementIdentification(PlacementIdentification placementIdentification) {
        this.placementIdentification = placementIdentification;
    }

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(StatusInfo statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Subproduct getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(Subproduct subproduct) {
        this.subproduct = subproduct;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public boolean isRenewable() {
        return isRenewable;
    }

    public void setRenewable(boolean renewable) {
        isRenewable = renewable;
    }

    public boolean isCapitalized() {
        return isCapitalized;
    }

    public void setCapitalized(boolean capitalized) {
        isCapitalized = capitalized;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public OriginIdentifier getOriginIdentifier() {
        return originIdentifier;
    }

    public void setOriginIdentifier(OriginIdentifier originIdentifier) {
        this.originIdentifier = originIdentifier;
    }

    public SettlementCondition getSettlementCondition() {
        return settlementCondition;
    }

    public void setSettlementCondition(SettlementCondition settlementCondition) {
        this.settlementCondition = settlementCondition;
    }

    public String getAnnualPercentageYield() {
        return annualPercentageYield;
    }

    public void setAnnualPercentageYield(String annualPercentageYield) {
        this.annualPercentageYield = annualPercentageYield;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public DestinationFunds getDestinationFunds() {
        return destinationFunds;
    }

    public void setDestinationFunds(DestinationFunds destinationFunds) {
        this.destinationFunds = destinationFunds;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public String getPurposeDescription() {
        return purposeDescription;
    }

    public void setPurposeDescription(String purposeDescription) {
        this.purposeDescription = purposeDescription;
    }

    public String getLastRenewalDate() {
        return lastRenewalDate;
    }

    public void setLastRenewalDate(String lastRenewalDate) {
        this.lastRenewalDate = lastRenewalDate;
    }

    public ProfitabilityAtMaturity getProfitabilityAtMaturity() {
        return profitabilityAtMaturity;
    }

    public void setProfitabilityAtMaturity(ProfitabilityAtMaturity profitabilityAtMaturity) {
        this.profitabilityAtMaturity = profitabilityAtMaturity;
    }

    public InitialTotalInvested getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(InitialTotalInvested initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacementIdentification {
    private String isin;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productCode;
    private String productDescription;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitabilityAtMaturity {
    private String amount;
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    private SettlementConcept settlementConcept;

    public SettlementConcept getSettlementConcept() {
        return settlementConcept;
    }

    public void setSettlementConcept(SettlementConcept settlementConcept) {
        this.settlementConcept = settlementConcept;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementConcept {
    private String code;
    private String description;
    private String typeCode;
    private String typeDescription;
    private String rate;
    private Amount amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementCondition {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusInfo {
    private String statusCode;
    private String statusDescription;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subproduct {
    private String subproductId;
    private String name;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49DataRequest {
    private String buscarPor;
    private String ent;
    private String ofic;
    private String cuenta;
    private String secuencia;
    private String numeroCertificado;
    private String documentoCajero;

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getOfic() {
        return ofic;
    }

    public void setOfic(String ofic) {
        this.ofic = ofic;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    public void setNumeroCertificado(String numeroCertificado) {
        this.numeroCertificado = numeroCertificado;
    }

    public String getDocumentoCajero() {
        return documentoCajero;
    }

    public void setDocumentoCajero(String documentoCajero) {
        this.documentoCajero = documentoCajero;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Request {
    private TrxHeader cabecera;
    private TrxBP49DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP49DataRequest getData() {
        return data;
    }

    public void setData(TrxBP49DataRequest data) {
        this.data = data;
    }

    public TrxBP49Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);

        session.setUsuario(header.sesion.usuario);

        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSesion(session);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setCanal(header.canal);

        this.cabecera.setResultado(header.getResultado());

    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Data {
    private String cdtDat;
    private String CERTIFI;
    private String fecha;
    private String secuencia;
    private String RETEN;
    private String secRen;
    private String INTABON;
    private String estado;
    private String numMov;
    private String interesPendienteLiquidar;
    private String pago;
    private String concepto;
    private String valor;

    public String getCdtDat() {
        return cdtDat;
    }

    public void setCdtDat(String cdtDat) {
        this.cdtDat = cdtDat;
    }

    public String getCERTIFI() {
        return CERTIFI;
    }

    public void setCERTIFI(String CERTIFI) {
        this.CERTIFI = CERTIFI;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getRETEN() {
        return RETEN;
    }

    public void setRETEN(String RETEN) {
        this.RETEN = RETEN;
    }

    public String getSecRen() {
        return secRen;
    }

    public void setSecRen(String secRen) {
        this.secRen = secRen;
    }

    public String getINTABON() {
        return INTABON;
    }

    public void setINTABON(String INTABON) {
        this.INTABON = INTABON;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumMov() {
        return numMov;
    }

    public void setNumMov(String numMov) {
        this.numMov = numMov;
    }

    public String getInteresPendienteLiquidar() {
        return interesPendienteLiquidar;
    }

    public void setInteresPendienteLiquidar(String interesPendienteLiquidar) {
        this.interesPendienteLiquidar = interesPendienteLiquidar;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49DataResponse {
    private ArrayList<TrxBP49Data> movimientos;

    public ArrayList<TrxBP49Data> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<TrxBP49Data> movimientos) {
        this.movimientos = movimientos;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Response {
    private TrxBP49DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP49DataResponse getData() {
        return data;
    }

    public void setData(TrxBP49DataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Object getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Object paginacion) {
        this.paginacion = paginacion;
    }

    public List<Object> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<Object> avisos) {
        this.avisos = avisos;
    }

    public List<ErrorTrxDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorTrxDTO> errores) {
        this.errores = errores;
    }

    public Object getConexion() {
        return conexion;
    }

    public void setConexion(Object conexion) {
        this.conexion = conexion;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String usuario;
    private String terminal;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private String diasRestantesCambioClave;
    private String fechaContable;
    private String turno;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getHoraConexion() {
        return horaConexion;
    }

    public void setHoraConexion(String horaConexion) {
        this.horaConexion = horaConexion;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDiasRestantesCambioClave() {
        return diasRestantesCambioClave;
    }

    public void setDiasRestantesCambioClave(String diasRestantesCambioClave) {
        this.diasRestantesCambioClave = diasRestantesCambioClave;
    }

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}



package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxHeader {
    private String rutaServicio;
    private Session sesion;
    private String funcion;
    private Integer secuencia;
    private String canal;
    private String resultado;

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public Session getSesion() {
        return sesion;
    }

    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvDataRequest {
    private PemfvInfoAdicional infAdicional;

    public PemfvInfoAdicional getInfAdicional() {
        return infAdicional;
    }

    public void setInfAdicional(PemfvInfoAdicional infAdicional) {
        this.infAdicional = infAdicional;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvInfoAdicional {
    private String numper;
    private String canalVenta;
    private boolean autorizoEnvioInformacion;

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public boolean isAutorizoEnvioInformacion() {
        return autorizoEnvioInformacion;
    }

    public void setAutorizoEnvioInformacion(boolean autorizoEnvioInformacion) {
        this.autorizoEnvioInformacion = autorizoEnvioInformacion;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.request;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvRequest {
    private TrxHeader cabecera;
    private PemfvDataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public PemfvDataRequest getData() {
        return data;
    }

    public void setData(PemfvDataRequest data) {
        this.data = data;
    }

    public PemfvRequest(TrxPersonHeader header){
        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);

        session.setUsuario(header.sesion.usuario);

        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSesion(session);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setCanal(header.canal);

        this.cabecera.setResultado(header.getResultado());
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvDataResponse {
    private PemfvPEMFV0AResponse pemfv0A;

    public PemfvPEMFV0AResponse getPemfv0A() {
        return pemfv0A;
    }

    public void setPemfv0A(PemfvPEMFV0AResponse pemfv0A) {
        this.pemfv0A = pemfv0A;
    }
}



package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvPEMFV0AResponse {
    private String OFICIAL;
    private String IDNOAPL;
    private String HONORAR;
    private String REPORTA;
    private String SALARIO;
    private String SECDOC;
    private String SITCLN;
    private String PRESERV;
    private String NROIDT2;
    private String NROIDT3;
    private String PENSION;
    private String NROIDT1;
    private String ACTIND;
    private String NUMDOC;
    private String UNINEG;
    private String ARRIEND;
    private String CANVTA;
    private String SUCURAL;
    private String IDCRECO;
    private String PAIRE01;
    private String AUTCOME;
    private String PAIRE02;
    private String PAIRE03;
    private String IDCRS;
    private String DONHERE;
    private String IDPREPU;
    private String IDFATCA;
    private String OFOTRO;
    private String NUMPER;
    private String MESADA;
    private String TIPDOC;
    private String FECCLN;
    private String IDPPEXP;
    private String IDPEXPO;

    public String getOFICIAL() {
        return OFICIAL;
    }

    public void setOFICIAL(String OFICIAL) {
        this.OFICIAL = OFICIAL;
    }

    public String getIDNOAPL() {
        return IDNOAPL;
    }

    public void setIDNOAPL(String IDNOAPL) {
        this.IDNOAPL = IDNOAPL;
    }

    public String getHONORAR() {
        return HONORAR;
    }

    public void setHONORAR(String HONORAR) {
        this.HONORAR = HONORAR;
    }

    public String getREPORTA() {
        return REPORTA;
    }

    public void setREPORTA(String REPORTA) {
        this.REPORTA = REPORTA;
    }

    public String getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(String SALARIO) {
        this.SALARIO = SALARIO;
    }

    public String getSECDOC() {
        return SECDOC;
    }

    public void setSECDOC(String SECDOC) {
        this.SECDOC = SECDOC;
    }

    public String getSITCLN() {
        return SITCLN;
    }

    public void setSITCLN(String SITCLN) {
        this.SITCLN = SITCLN;
    }

    public String getPRESERV() {
        return PRESERV;
    }

    public void setPRESERV(String PRESERV) {
        this.PRESERV = PRESERV;
    }

    public String getNROIDT2() {
        return NROIDT2;
    }

    public void setNROIDT2(String NROIDT2) {
        this.NROIDT2 = NROIDT2;
    }

    public String getNROIDT3() {
        return NROIDT3;
    }

    public void setNROIDT3(String NROIDT3) {
        this.NROIDT3 = NROIDT3;
    }

    public String getPENSION() {
        return PENSION;
    }

    public void setPENSION(String PENSION) {
        this.PENSION = PENSION;
    }

    public String getNROIDT1() {
        return NROIDT1;
    }

    public void setNROIDT1(String NROIDT1) {
        this.NROIDT1 = NROIDT1;
    }

    public String getACTIND() {
        return ACTIND;
    }

    public void setACTIND(String ACTIND) {
        this.ACTIND = ACTIND;
    }

    public String getNUMDOC() {
        return NUMDOC;
    }

    public void setNUMDOC(String NUMDOC) {
        this.NUMDOC = NUMDOC;
    }

    public String getUNINEG() {
        return UNINEG;
    }

    public void setUNINEG(String UNINEG) {
        this.UNINEG = UNINEG;
    }

    public String getARRIEND() {
        return ARRIEND;
    }

    public void setARRIEND(String ARRIEND) {
        this.ARRIEND = ARRIEND;
    }

    public String getCANVTA() {
        return CANVTA;
    }

    public void setCANVTA(String CANVTA) {
        this.CANVTA = CANVTA;
    }

    public String getSUCURAL() {
        return SUCURAL;
    }

    public void setSUCURAL(String SUCURAL) {
        this.SUCURAL = SUCURAL;
    }

    public String getIDCRECO() {
        return IDCRECO;
    }

    public void setIDCRECO(String IDCRECO) {
        this.IDCRECO = IDCRECO;
    }

    public String getPAIRE01() {
        return PAIRE01;
    }

    public void setPAIRE01(String PAIRE01) {
        this.PAIRE01 = PAIRE01;
    }

    public String getAUTCOME() {
        return AUTCOME;
    }

    public void setAUTCOME(String AUTCOME) {
        this.AUTCOME = AUTCOME;
    }

    public String getPAIRE02() {
        return PAIRE02;
    }

    public void setPAIRE02(String PAIRE02) {
        this.PAIRE02 = PAIRE02;
    }

    public String getPAIRE03() {
        return PAIRE03;
    }

    public void setPAIRE03(String PAIRE03) {
        this.PAIRE03 = PAIRE03;
    }

    public String getIDCRS() {
        return IDCRS;
    }

    public void setIDCRS(String IDCRS) {
        this.IDCRS = IDCRS;
    }

    public String getDONHERE() {
        return DONHERE;
    }

    public void setDONHERE(String DONHERE) {
        this.DONHERE = DONHERE;
    }

    public String getIDPREPU() {
        return IDPREPU;
    }

    public void setIDPREPU(String IDPREPU) {
        this.IDPREPU = IDPREPU;
    }

    public String getIDFATCA() {
        return IDFATCA;
    }

    public void setIDFATCA(String IDFATCA) {
        this.IDFATCA = IDFATCA;
    }

    public String getOFOTRO() {
        return OFOTRO;
    }

    public void setOFOTRO(String OFOTRO) {
        this.OFOTRO = OFOTRO;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String NUMPER) {
        this.NUMPER = NUMPER;
    }

    public String getMESADA() {
        return MESADA;
    }

    public void setMESADA(String MESADA) {
        this.MESADA = MESADA;
    }

    public String getTIPDOC() {
        return TIPDOC;
    }

    public void setTIPDOC(String TIPDOC) {
        this.TIPDOC = TIPDOC;
    }

    public String getFECCLN() {
        return FECCLN;
    }

    public void setFECCLN(String FECCLN) {
        this.FECCLN = FECCLN;
    }

    public String getIDPPEXP() {
        return IDPPEXP;
    }

    public void setIDPPEXP(String IDPPEXP) {
        this.IDPPEXP = IDPPEXP;
    }

    public String getIDPEXPO() {
        return IDPEXPO;
    }

    public void setIDPEXPO(String IDPEXPO) {
        this.IDPEXPO = IDPEXPO;
    }
}


package com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.pemfv.response;

import com.santander.bnc.bsn049.bncbsn049msdtcnsntmngmnt.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PemfvResponse {
    private PemfvDataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public PemfvDataResponse getData() {
        return data;
    }

    public void setData(PemfvDataResponse data) {
        this.data = data;
    }

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Object getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Object paginacion) {
        this.paginacion = paginacion;
    }

    public List<Object> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<Object> avisos) {
        this.avisos = avisos;
    }

    public List<ErrorTrxDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorTrxDTO> errores) {
        this.errores = errores;
    }

    public Object getConexion() {
        return conexion;
    }

    public void setConexion(Object conexion) {
        this.conexion = conexion;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}


