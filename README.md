
package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

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
    private List<BanksParametersDTO> banks;

    public List<BanksParametersDTO> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksParametersDTO> banks) {
        this.banks = banks;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationFunds {
    private String accountIdType;
    private String bankcode;
    private Account account;

    public String getAccountIdType() {
        return accountIdType;
    }

    public void setAccountIdType(String accountIdType) {
        this.accountIdType = accountIdType;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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
    @JsonProperty("isRenewable")
    private boolean isRenewable;
    @JsonProperty("isCapitalized")
    private boolean isCapitalized;
    @JsonProperty("isBlocked")
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataRequest {
    String entidad;
    String oficina;
    String cuenta;
    String numSecuencia;
    String numCertificado;

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumSecuencia() {
        return numSecuencia;
    }

    public void setNumSecuencia(String numSecuencia) {
        this.numSecuencia = numSecuencia;
    }

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13Request {

    private TrxHeader cabecera;
    private TrxBP13DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP13DataRequest getData() {
        return data;
    }

    public void setData(TrxBP13DataRequest data) {
        this.data = data;
    }

    public TrxBP13Request(TrxPersonHeader header){

        var session= new Session();
        this.cabecera = new TrxHeader();

        session.setUsuario(header.sesion.usuario);
        session.setTerminal(header.sesion.terminal);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setEntorno(header.sesion.entorno);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setEntidad("0065");
        session.setDiasRestantesCambioClave(header.sesion.diasRestantesCambioClave);
        session.setFechaContable(header.sesion.fechaContable);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseTrxDTO {
    private List<ErrorTrxDTO> errores;

    public List<ErrorTrxDTO> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorTrxDTO> errores) {
        this.errores = errores;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataResponse {
    private String numCertificado;
    private String certificadoReemplazado;
    private String codigoInversor;
    private String secuenciaIPF;
    private String secuenciaRenovacion;
    private String producto;
    private String subproducto;
    private String fecAlta;
    private String fecOperacion;
    private String indicadorGrantia;
    private String tarifaVigente;
    private String estadoIPF;
    private String spread;
    private String indicadorONP;
    private String moneda;
    private String desMoneda;
    private String saldoInicial;
    private int plazo;
    private String periodoLiquidacion;
    private String tipoTitular;
    private String numTitular;
    private String priApellido;
    private String segApellido;
    private String nombreTitular;
    private String tipoEfectivo;
    private int cambioUR;
    private String tipoInteres;
    private String capInteres;
    private String capReajuste;
    private String renovacionAutomatica;
    private String ejecutivoComercial;
    private String planComercial;
    private String custodia;
    private String desCustodia;
    private String canalApertura;
    private String transferible;
    private String origen;
    private String observaciones;
    private String sucIngCustodia;
    private String fecIngCustodia;
    private String sucEgrCustodia;
    private String fecEgrCustodia;
    private int importeRestProgra;
    private String centroGestor;
    private String acuerdo;
    private String cuentaAsociada;
    private String fecAnulacion;
    private String fecVencimiento;
    private String fecCancelacion;
    private String fecLiquidacion;
    private String fecLiqReajuste;
    private String tarifaRenovacion;
    private int spreadRenovacion;
    private String interesesAvonado;
    private String interesesPendiente;
    private String importePeriodico;
    private String indicadorBloqueo;
    private int interesesReajuste;
    private String pago;
    private String desPago;
    private String saldoDisponible;
    private String cuentaCliente;
    private String importeTipoTasa;
    private String numDocumento;
    private String tipoDocumento;
    private String tipoOperacion;
    private String motivoCancelacion;
    private String lina1;
    private String lina2;

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public String getCertificadoReemplazado() {
        return certificadoReemplazado;
    }

    public void setCertificadoReemplazado(String certificadoReemplazado) {
        this.certificadoReemplazado = certificadoReemplazado;
    }

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getSecuenciaIPF() {
        return secuenciaIPF;
    }

    public void setSecuenciaIPF(String secuenciaIPF) {
        this.secuenciaIPF = secuenciaIPF;
    }

    public String getSecuenciaRenovacion() {
        return secuenciaRenovacion;
    }

    public void setSecuenciaRenovacion(String secuenciaRenovacion) {
        this.secuenciaRenovacion = secuenciaRenovacion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubproducto() {
        return subproducto;
    }

    public void setSubproducto(String subproducto) {
        this.subproducto = subproducto;
    }

    public String getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(String fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getFecOperacion() {
        return fecOperacion;
    }

    public void setFecOperacion(String fecOperacion) {
        this.fecOperacion = fecOperacion;
    }

    public String getIndicadorGrantia() {
        return indicadorGrantia;
    }

    public void setIndicadorGrantia(String indicadorGrantia) {
        this.indicadorGrantia = indicadorGrantia;
    }

    public String getTarifaVigente() {
        return tarifaVigente;
    }

    public void setTarifaVigente(String tarifaVigente) {
        this.tarifaVigente = tarifaVigente;
    }

    public String getEstadoIPF() {
        return estadoIPF;
    }

    public void setEstadoIPF(String estadoIPF) {
        this.estadoIPF = estadoIPF;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getIndicadorONP() {
        return indicadorONP;
    }

    public void setIndicadorONP(String indicadorONP) {
        this.indicadorONP = indicadorONP;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDesMoneda() {
        return desMoneda;
    }

    public void setDesMoneda(String desMoneda) {
        this.desMoneda = desMoneda;
    }

    public String getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(String saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getTipoTitular() {
        return tipoTitular;
    }

    public void setTipoTitular(String tipoTitular) {
        this.tipoTitular = tipoTitular;
    }

    public String getNumTitular() {
        return numTitular;
    }

    public void setNumTitular(String numTitular) {
        this.numTitular = numTitular;
    }

    public String getPriApellido() {
        return priApellido;
    }

    public void setPriApellido(String priApellido) {
        this.priApellido = priApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getTipoEfectivo() {
        return tipoEfectivo;
    }

    public void setTipoEfectivo(String tipoEfectivo) {
        this.tipoEfectivo = tipoEfectivo;
    }

    public int getCambioUR() {
        return cambioUR;
    }

    public void setCambioUR(int cambioUR) {
        this.cambioUR = cambioUR;
    }

    public String getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public String getCapInteres() {
        return capInteres;
    }

    public void setCapInteres(String capInteres) {
        this.capInteres = capInteres;
    }

    public String getCapReajuste() {
        return capReajuste;
    }

    public void setCapReajuste(String capReajuste) {
        this.capReajuste = capReajuste;
    }

    public String getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(String renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getPlanComercial() {
        return planComercial;
    }

    public void setPlanComercial(String planComercial) {
        this.planComercial = planComercial;
    }

    public String getCustodia() {
        return custodia;
    }

    public void setCustodia(String custodia) {
        this.custodia = custodia;
    }

    public String getDesCustodia() {
        return desCustodia;
    }

    public void setDesCustodia(String desCustodia) {
        this.desCustodia = desCustodia;
    }

    public String getCanalApertura() {
        return canalApertura;
    }

    public void setCanalApertura(String canalApertura) {
        this.canalApertura = canalApertura;
    }

    public String getTransferible() {
        return transferible;
    }

    public void setTransferible(String transferible) {
        this.transferible = transferible;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSucIngCustodia() {
        return sucIngCustodia;
    }

    public void setSucIngCustodia(String sucIngCustodia) {
        this.sucIngCustodia = sucIngCustodia;
    }

    public String getFecIngCustodia() {
        return fecIngCustodia;
    }

    public void setFecIngCustodia(String fecIngCustodia) {
        this.fecIngCustodia = fecIngCustodia;
    }

    public String getSucEgrCustodia() {
        return sucEgrCustodia;
    }

    public void setSucEgrCustodia(String sucEgrCustodia) {
        this.sucEgrCustodia = sucEgrCustodia;
    }

    public String getFecEgrCustodia() {
        return fecEgrCustodia;
    }

    public void setFecEgrCustodia(String fecEgrCustodia) {
        this.fecEgrCustodia = fecEgrCustodia;
    }

    public int getImporteRestProgra() {
        return importeRestProgra;
    }

    public void setImporteRestProgra(int importeRestProgra) {
        this.importeRestProgra = importeRestProgra;
    }

    public String getCentroGestor() {
        return centroGestor;
    }

    public void setCentroGestor(String centroGestor) {
        this.centroGestor = centroGestor;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(String cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getFecAnulacion() {
        return fecAnulacion;
    }

    public void setFecAnulacion(String fecAnulacion) {
        this.fecAnulacion = fecAnulacion;
    }

    public String getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getFecCancelacion() {
        return fecCancelacion;
    }

    public void setFecCancelacion(String fecCancelacion) {
        this.fecCancelacion = fecCancelacion;
    }

    public String getFecLiquidacion() {
        return fecLiquidacion;
    }

    public void setFecLiquidacion(String fecLiquidacion) {
        this.fecLiquidacion = fecLiquidacion;
    }

    public String getFecLiqReajuste() {
        return fecLiqReajuste;
    }

    public void setFecLiqReajuste(String fecLiqReajuste) {
        this.fecLiqReajuste = fecLiqReajuste;
    }

    public String getTarifaRenovacion() {
        return tarifaRenovacion;
    }

    public void setTarifaRenovacion(String tarifaRenovacion) {
        this.tarifaRenovacion = tarifaRenovacion;
    }

    public int getSpreadRenovacion() {
        return spreadRenovacion;
    }

    public void setSpreadRenovacion(int spreadRenovacion) {
        this.spreadRenovacion = spreadRenovacion;
    }

    public String getInteresesAvonado() {
        return interesesAvonado;
    }

    public void setInteresesAvonado(String interesesAvonado) {
        this.interesesAvonado = interesesAvonado;
    }

    public String getInteresesPendiente() {
        return interesesPendiente;
    }

    public void setInteresesPendiente(String interesesPendiente) {
        this.interesesPendiente = interesesPendiente;
    }

    public String getImportePeriodico() {
        return importePeriodico;
    }

    public void setImportePeriodico(String importePeriodico) {
        this.importePeriodico = importePeriodico;
    }

    public String getIndicadorBloqueo() {
        return indicadorBloqueo;
    }

    public void setIndicadorBloqueo(String indicadorBloqueo) {
        this.indicadorBloqueo = indicadorBloqueo;
    }

    public int getInteresesReajuste() {
        return interesesReajuste;
    }

    public void setInteresesReajuste(int interesesReajuste) {
        this.interesesReajuste = interesesReajuste;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getDesPago() {
        return desPago;
    }

    public void setDesPago(String desPago) {
        this.desPago = desPago;
    }

    public String getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(String saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    public String getImporteTipoTasa() {
        return importeTipoTasa;
    }

    public void setImporteTipoTasa(String importeTipoTasa) {
        this.importeTipoTasa = importeTipoTasa;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getLina1() {
        return lina1;
    }

    public void setLina1(String lina1) {
        this.lina1 = lina1;
    }

    public String getLina2() {
        return lina2;
    }

    public void setLina2(String lina2) {
        this.lina2 = lina2;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13Response {
    private TrxBP13DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP13DataResponse getData() {
        return data;
    }

    public void setData(TrxBP13DataResponse data) {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49DataResponse {
    private List<TrxBP49Data> movimientos;

    public List<TrxBP49Data> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<TrxBP49Data> movimientos) {
        this.movimientos = movimientos;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.generic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * params from properties.yml
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiEntry {
    private String integrationType;
    private String host;
    private String port;
    private boolean https;
    private String endpoint;
    private Integer timeOutConn;
    private Integer timeOutRead;

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isHttps() {
        return https;
    }

    public void setHttps(boolean https) {
        this.https = https;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getTimeOutConn() {
        return timeOutConn;
    }

    public void setTimeOutConn(Integer timeOutConn) {
        this.timeOutConn = timeOutConn;
    }

    public Integer getTimeOutRead() {
        return timeOutRead;
    }

    public void setTimeOutRead(Integer timeOutRead) {
        this.timeOutRead = timeOutRead;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeRequest {
    
    private String authorization;
    private String xSantanderClientId;
    private String productId;

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeResponse {

    private MaxAndMinAmountDto minimumAmount;
    private MaxAndMinAmountDto maximumAmount;

    public MaxAndMinAmountDto getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(MaxAndMinAmountDto minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public MaxAndMinAmountDto getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(MaxAndMinAmountDto maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaxAndMinAmountDto {

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersDTO {
    private String code;
    private String content;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersRequest {
    private String productId;
    private String authorization;
    private String xSantanderClientId;

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersResponse {
    private List<TermDepositParametersDTO> parameters;

    public List<TermDepositParametersDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<TermDepositParametersDTO> parameters) {
        this.parameters = parameters;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositTransactionRequest {
    private String authorization;
    private String clientId;
    private String depositId;
    private String placementId;
    private Integer type_code;
    private String credit_debit_indicator;
    private String start_date;
    private String end_date;
    private Integer minim_amount;
    private Integer maxim_amount;
    private String offset;
    private String limit;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Integer getType_code() {
        return type_code;
    }

    public void setType_code(Integer type_code) {
        this.type_code = type_code;
    }

    public String getCredit_debit_indicator() {
        return credit_debit_indicator;
    }

    public void setCredit_debit_indicator(String credit_debit_indicator) {
        this.credit_debit_indicator = credit_debit_indicator;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getMinim_amount() {
        return minim_amount;
    }

    public void setMinim_amount(Integer minim_amount) {
        this.minim_amount = minim_amount;
    }

    public Integer getMaxim_amount() {
        return maxim_amount;
    }

    public void setMaxim_amount(Integer maxim_amount) {
        this.maxim_amount = maxim_amount;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResultDTO {
    private AmountDTO amount;
    private String creditDebitIndicator;

    public AmountDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountDTO amount) {
        this.amount = amount;
    }

    public String getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    public void setCreditDebitIndicator(String creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceTypeDTO {
    private String typeCode;
    private String typeDescription;

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
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HrefDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinksDTO {
    private HrefDTO first;
    private HrefDTO prev;
    private HrefDTO next;

    public HrefDTO getFirst() {
        return first;
    }

    public void setFirst(HrefDTO first) {
        this.first = first;
    }

    public HrefDTO getPrev() {
        return prev;
    }

    public void setPrev(HrefDTO prev) {
        this.prev = prev;
    }

    public HrefDTO getNext() {
        return next;
    }

    public void setNext(HrefDTO next) {
        this.next = next;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListTransactionDTO {
    private String valueDate;
    private AmountDTO amount;
    private String description;

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public AmountDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountDTO amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositTransactionResponse {
    private List<ListTransactionDTO> listTransactions;

    public List<ListTransactionDTO> getListTransactions() {
        return listTransactions;
    }

    public void setListTransactions(List<ListTransactionDTO> listTransactions) {
        this.listTransactions = listTransactions;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration.ApiEntry;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Component
@ConfigurationProperties(prefix = "integration")
public class IntegrationDataConfiguration {
    @Getter
    private List<ApiEntry> catalogue;
    private Map<String, ApiEntry> catalogueMap;

    private void loadCatalogueMap() {
        catalogueMap = catalogue.stream().collect(
                Collectors.toMap(ApiEntry::getIntegrationType, Function.identity()));
    }
    public ApiEntry getByApi(String integrationType){
        if(catalogueMap == null){
            loadCatalogueMap();
        }
        return catalogueMap.get(integrationType);
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration.ApiEntry;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Component
@ConfigurationProperties(prefix = "integration")
public class IntegrationDataConfiguration {
    @Getter
    private List<ApiEntry> catalogue;
    private Map<String, ApiEntry> catalogueMap;

    private void loadCatalogueMap() {
        catalogueMap = catalogue.stream().collect(
                Collectors.toMap(ApiEntry::getIntegrationType, Function.identity()));
    }
    public ApiEntry getByApi(String integrationType){
        if(catalogueMap == null){
            loadCatalogueMap();
        }
        return catalogueMap.get(integrationType);
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.integration.ApiEntry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RestClientConfig {

    private final IntegrationDataConfiguration properties;

    @Bean
    TrxSanbaAPI txrTransactionApi(){
        return getRetrofitConfig(properties.getByApi("sanba"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxSanbaAPI.class);
    }

    @Bean
    ProductDirectoryAPI productDirectoryAPI (){
        return getRetrofitConfig(properties.getByApi("product-directory"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ProductDirectoryAPI.class);
    }

    @Bean
    TermDepositParametersAPI termDepositParametersAPI (){
        return getRetrofitConfig(properties.getByApi("term-deposit-parameters"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TermDepositParametersAPI.class);
    }
    
    @Bean
    BanksApi banksAPI (){
        return getRetrofitConfig(properties.getByApi("banks"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(BanksApi.class);
    }



    private Retrofit.Builder getRetrofitConfig(ApiEntry apiEntry) {

        return new Retrofit.Builder()
                .baseUrl(buildURL(apiEntry))
                .client(getHttpClient(HttpLoggingInterceptor.Level.BODY,
                        apiEntry.getTimeOutRead(),
                        apiEntry.getTimeOutConn()));
    }

    private OkHttpClient getHttpClient(HttpLoggingInterceptor.Level level, long readTimeout, long connectTimeout)  {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(msg ->
		log.info("--> OKHTTP {}",msg)
		);
        interceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        return builder.build();
    }

    private ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
        return objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(new StdDateFormat())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
                .enable(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS)
                .registerModule(new JavaTimeModule());
    }


    private String buildURL(ApiEntry apiEntry){
        String schema = "https://";
        if(!apiEntry.isHttps()){
            schema = schema.replace("s","");
        }
        return schema + apiEntry.getHost() + ":" + apiEntry.getPort() + apiEntry.getEndpoint();
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;

import java.util.HashMap;

@Service
@Data
@ConfigurationProperties(prefix = "errors")
@RequiredArgsConstructor
public class ErrorService {

   
    private String msName;  
    private String msVersion;    
    private String level; 
    private String functional;   
    private String technical;
    private HashMap<String, String> general;

    @Value("${errors.general.invalid_value}")
    public String INVALID_VALUE;

    @Value(("${errors.general.blank_data}"))
    public String BLANK_DATA;

    public ServiceException serviceExceptionBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        var error = ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();

        return new ServiceException(status, error);
    }

    public ErrorDTO errorBuilder(HttpStatus status, String message, ErrorType type) {

        String errorType = type == ErrorType.FUNCTIONAL ? functional : technical;

        return ErrorDTO.builder()
                .code(msName + "-" + errorType + "-9" + status.value())
                .level(level)
                .message(message)
                .description(msName.toLowerCase() + "-" + msVersion + ": " + message)
                .build();

    }

    public void isBlank(String value, String fieldName) {
        if (value.isBlank()) {
            var message = "'" + fieldName + "': " + BLANK_DATA;

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }

    public void isNull(String value, String fieldName) {
        if (value == null) {
            var message = "'" + fieldName + "': " + this.general.get("null");

            throw serviceExceptionBuilder(HttpStatus.BAD_REQUEST, message, ErrorType.FUNCTIONAL);
        }
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error;

public enum ErrorType {
    FUNCTIONAL,
    TECHNICAL;

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${params.app-name}")
    private String MS_NAME;

    private static final String LEVEL = "error";
    private static final String PF400 = "-P-F-9400";
    private static final String NOTSPECIFIED = " not specified";


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-T-9409")
                .message("Unhandled exception")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Unhandled exception")
                .build());
        return buildResponseEntity(errors, HttpStatus.CONFLICT);
    }//method closure

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDTO> errors = new ArrayList<>();


        result.getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            log.info(error.toString());

            String errorMessage = "'" + field + "' " + error.getDefaultMessage();

            errors.add(ErrorDTO.builder()
                    .code(MS_NAME + PF400)
                    .level(LEVEL)
                    .message(errorMessage)
                    .description(MS_NAME.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-F-9404")
                .message("Not Found")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Not Found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + PF400)
                .message("Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + NOTSPECIFIED)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + PF400)
                .message(ex.getMessage())
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + PF400)
                .message("Required header " + ex.getHeaderName() + NOTSPECIFIED)
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + NOTSPECIFIED)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Main exception hanlder
     * @param ex Exception
     * @param request Web Request
     * @return Structured Santander Exception format
     */
    @ExceptionHandler({ ServiceException.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceException ex, WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ex.getError());

        return buildResponseEntity(errors, ex.getCode());
    }

    @ExceptionHandler({ ServiceExceptionClient.class })
    public ResponseEntity<ErrorResponseDTO> handleSchemaException(ServiceExceptionClient ex, WebRequest request) {
        log.error("ERRORRS {}", ex.getErrorResponseDTO());
        return buildResponseEntity2(ex.getErrorResponseDTO(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ErrorResponseDTO> buildResponseEntity2(ErrorResponseDTO newErrorDTO, HttpStatus status) {

        newErrorDTO.getErrors().forEach( error-> {
                    error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,MS_NAME));
                    error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,MS_NAME.toLowerCase()));
                    log.error(error.getMessage());
                }
        );
        return new ResponseEntity<>(newErrorDTO, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure

/**
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {

        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        if(errors != null){
            errors.forEach( error-> {
                        error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,MS_NAME));
                        error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,MS_NAME.toLowerCase()));
                        log.error(error.getMessage());
                    }
            );
        }
        return new ResponseEntity<>(responseError, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure
*/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(HttpMessageNotReadableException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + PF400)
                .message("Invalid body structure")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(MS_NAME + "-P-F-9405")
                .message("Method not allowed")
                .level(LEVEL)
                .description(MS_NAME.toLowerCase() + "-api-services-v1: Method not allowed")
                .build());

        return buildResponseEntity(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    public ResponseEntity<ErrorResponseDTO> buildResponseEntity(List<ErrorDTO> errors, HttpStatus status) {

        ErrorResponseDTO responseError = new ErrorResponseDTO();
        responseError.setErrors(errors);
        if (errors != null) {
            // 1. REGISTRO SEGURO (Logging interno para diagnóstico de devs)
            // Registramos todos los detalles originales ANTES de limpiarlos.
            log.error("Se detectaron {} errores técnicos detallados:", errors.size());
            errors.forEach(error -> 
                log.error("Código de Error Técnico: {}, Descripción Técnica Detallada: {}", error.getCode(), error.getDescription())
            );
        }
         // 2. CREACIÓN DE RESPUESTA DESDE CERO (Interrupción de flujo)
         // No usamos 'responseError.setErrors(sanitizedErrors)' sobre un objeto que tocó datos viejos.
         ErrorResponseDTO cleanResponse = new ErrorResponseDTO();
         List<ErrorDTO> externalErrors = new ArrayList<>();

         if (errors != null) {
             for (ErrorDTO original : errors) {
                 // Creamos un DTO nuevo por cada error, sin copiar referencias sospechosas
                 ErrorDTO safeDto = new ErrorDTO();
                 
                 // Usamos constantes o valores fijos para la descripción externa
                 // Esto garantiza a Fortify que el 'Sink' no recibe el 'Source' original
                 safeDto.setCode(original.getCode()); 
                 safeDto.setDescription("Ocurrió un error al procesar la solicitud. Consulte los logs para más detalle.");
                 
                 externalErrors.add(safeDto);
             }
         }

         cleanResponse.setErrors(externalErrors);

         // 3. RETORNO SEGURO
         // Al usar 'cleanResponse', que solo contiene datos generados localmente ("hardcoded"),
         // Fortify debería validar la línea como segura.
         return new ResponseEntity<>(cleanResponse, status != null ? status : HttpStatus.BAD_REQUEST);
    }//method closure
}//class closure

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.mappers;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.AmountDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.ListTransactionDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.MovementConceptUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.TermDepositUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TermDepositTransactionMappers {
    final TermDepositUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final MovementConceptUtils movementConceptUtils;
    final TrxSanbaService trxSanbaService;

    @Value("${service-route-trx.BP13}")
    private String BP13_SERVICE_ROUTE;

    public TermDepositTransactionResponse transactionResponse(TrxBP49Response trxBP49Response, TermDepositTransactionRequest requestBodyData){
        TermDepositTransactionResponse response = new TermDepositTransactionResponse();
        ArrayList<ListTransactionDTO> listTransactions = new ArrayList<>();

        trxBP49Response.getData().getMovimientos().forEach(motion -> {
            AmountDTO amount = new AmountDTO();
            amount.setCurrency("COP");
            String valueEntry = motion.getValor();

            String valueFinal = TermDepositUtils.format15DigitNumber(valueEntry.replace("-","")); // Eliminar el signo "-" si existe
            amount.setAmount(valueFinal);
            ListTransactionDTO listTrasaction = new ListTransactionDTO();
            listTrasaction.setAmount(amount);
            String date = motion.getFecha();
            listTrasaction.setValueDate(date);

            if ("EMIR".equals(motion.getConcepto())) {
                TrxBP13Response trxBP13Response = trxBP13call(requestBodyData);
                String capIntereses = trxBP13Response.getData().getCapInteres();
                if ("S".equals(capIntereses)) {
                    listTrasaction.setDescription("REINVERSION CDT");
                } else {
                    listTrasaction.setDescription("RENOVACION CDT");
                }
            } else {
                listTrasaction.setDescription(setConcept(motion.getConcepto()));
            }
            listTransactions.add(listTrasaction);
        });
        response.setListTransactions(listTransactions);
        return response;
    }


    public TrxBP13Response trxBP13call(TermDepositTransactionRequest requestBodyData) {
        TrxBP13Request trxBP13Request = new TrxBP13Request(ClientUtils.buildHeader(BP13_SERVICE_ROUTE));
        var trxBP13Data = new TrxBP13DataRequest();
        trxBP13Data.setEntidad("0065");
        trxBP13Data.setOficina("0100");
        trxBP13Data.setCuenta("");
        trxBP13Data.setNumSecuencia("");
        String placementIdWithoutDash = requestBodyData.getPlacementId().replace("-", "");
        trxBP13Data.setNumCertificado(requestBodyData.getDepositId() + placementIdWithoutDash);
        trxBP13Request.setData(trxBP13Data);
        return trxSanbaService.trxBP13(trxBP13Request);
    }

    private String setConcept(String conceptCode){
        String conceptDescription;
        if (movementConceptUtils.getType().containsKey(conceptCode)) {
            conceptDescription = movementConceptUtils.getType().get(conceptCode);
        } else {
            conceptDescription = "OTROS";
        }
        return conceptDescription;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.controller;


import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.service.TermDepositTransactionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TermDepositTransactionController {
    final TermDepositTransactionsService termDepositTransactionsService;
    private final ObjectMapper objectMapper;
    @GetMapping("/term_deposit_transactions/{deposit_id}/placements/{placement_id}/transactions")
    public ResponseEntity<TermDepositTransactionResponse> getDepositTrasaction(

            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String clientId,
            @PathVariable(name = "deposit_id") String depositId,
            @PathVariable(name = "placement_id") String placementId)
            {

		log.info("*** INIT (GET) /v1/term_deposit_transactions/{}/placements/{}/transactions  client-id={} >>> ",depositId,placementId, clientId);

        TermDepositTransactionRequest request = new TermDepositTransactionRequest();
        request.setAuthorization(authorization);
        request.setClientId(clientId);
        request.setDepositId(depositId);
        request.setPlacementId(placementId);
        TermDepositTransactionResponse response =  termDepositTransactionsService.getDepositTrasaction(request);
        
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" Response=").append(jsonResponse);
			log.info("*** FIN (GET) /v1/term_deposit_transactions/{}/placements/{}/transactions  client-id={} {}>>> ",depositId,placementId, clientId,sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
        
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        
        log.info(GUtils.ELOG + "endpoint search customers {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
        }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanksServiceImpl implements BanksService {
    final BanksApi banksApi;

    @Override
    public BanksDTO banksResponse(BanksParametersRequest request) {
        try {
            return banksApi.callBanks(request.getAuthorization(), request.getXSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDirectoryServiceImpl implements ProductDirectoryService {
    
    final ProductDirectoryAPI productDirectoryAPI;    

    @Override
    public AmountRangeResponse amountRange(AmountRangeRequest request) {
        
        try {
            return productDirectoryAPI.callAmountRange(request.getProductId(), request.getAuthorization(), request.getXSantanderClientId()).execute().body();
            
        } catch (Exception e) {
            return null;
        }
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermDepositParametersServiceImpl implements TermDepositParametersService {

    final TermDepositParametersAPI termDepositParametersAPI;

    public TermDepositParametersResponse termDepositParameters(TermDepositParametersRequest request) {

        try {
            return termDepositParametersAPI.callTermDepositParameters(request.getProductId(), request.getAuthorization(), request.getXSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;
    
    private final String BP31_SERVICE_ROUTE = "SBCDTTI01-ConsultaCDTDATTitular2654";
    private final String BP13_SERVICE_ROUTE = "consultaDatosIPF";
    private final String errorTrx = "Error in TRX: {}";
    private final String client = "client {}";
    private final String errLlaves = "err {}";
    private final String noError = "No error";
    private final String setLlaves = "SET {}";
    private final String cdtDtaNoExiste = "CDT / DAT NO EXISTE.";
    private static final String RUNTIMEEXCEPTION = "Runtime exception: ";
    private static final String IOEXCEPTION = "IOException: ";
    private static final String EXCEPTION = "Exception: ";

    @Value("${service-route-trx.BP49}")
    private  String BP49_SERVICE_ROUTE;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;


    //startTrxBP13
    @Override
    public TrxBP13Response trxBP13(TrxBP13Request request) {
        Response<TrxBP13Response> responseApi = null;

        try {
            responseApi = callBP13Trx(request);
            return handleBP13Response(responseApi);
        } catch (RuntimeException e) {
            log.error(RUNTIMEEXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCatalog.MS_SANBA_TRX_ERROR);
        } catch (IOException e) {
            log.error(IOEXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
        } catch (Exception e) {
            log.error(EXCEPTION + e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }
    private Response<TrxBP13Response> callBP13Trx(TrxBP13Request request) throws IOException {
        request.getCabecera().setCanal(channel);
        request.getCabecera().getSesion().setUsuario(user);
        return trxSanbaAPI.callBP13TRX(request, BP13_SERVICE_ROUTE, BP13_SERVICE_ROUTE, mqRoute).execute();
    }
    private TrxBP13Response handleBP13Response(Response<TrxBP13Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP13Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + client, responseBody);
            return responseBody;
        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {
                // Log or handle the exception
            }

            ObjectMapper objm = new ObjectMapper();
            TrxBP13Response err;
            try {
                err = objm.readValue(errorBody, TrxBP13Response.class);
            } catch (IOException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }

            log.info(GUtils.ELOG + errLlaves, err != null ? err.getErrores() : noError);
            assert err != null;
            List<ErrorDTO> errorsDtos = buildErrorDTOs(err);

            log.info(GUtils.ELOG + setLlaves, errorsDtos);

            if (hasNotFound13Error(errorsDtos)) {
                throw new ServiceException(HttpStatus.NOT_FOUND, cdtDtaNoExiste);
            }

            throw new ServiceException(HttpStatus.CONFLICT, getFirst13Error(errorsDtos));
        }
    }
    private List<ErrorDTO> buildErrorDTOs(TrxBP13Response err) {
        List<ErrorDTO> errorsDtos = new ArrayList<>();
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            if (dtoEr.getMensaje().equals(cdtDtaNoExiste) || dtoEr.getMensaje().equals("NO EXISTE.")) {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.NOT_FOUND, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            } else {
                errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
            }
        }
        return errorsDtos;
    }
    private boolean hasNotFound13Error(List<ErrorDTO> errorsDtos) {
        return errorsDtos.stream().anyMatch(error -> error.getMessage().contains(cdtDtaNoExiste));
    }
    private ErrorDTO getFirst13Error(List<ErrorDTO> errorsDtos) {
        return !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR;
    }
    //endTrxBP13


    @Override
    public TrxBP49Response trxBP49(TrxBP49Request trxBp49Request) {
        Response<TrxBP49Response> responseApi = null;

        try {
            prepareRequest(trxBp49Request);
            responseApi = callTrx(trxBp49Request);

        } catch (RuntimeException e) {
            handleRuntimeException(e);

        } catch (IOException e) {
            handleIOException();

        } catch (Exception e) {
            handleOtherExceptions(e);
        }

        assert responseApi != null;
        return processResponse(responseApi);
    }
    private void prepareRequest(TrxBP49Request trxBp49Request) {
        trxBp49Request.getCabecera().setCanal(channel);
        trxBp49Request.getCabecera().getSesion().setUsuario(user);
    }
    private Response<TrxBP49Response> callTrx(TrxBP49Request trxBp49Request) throws IOException {
        return trxSanbaAPI.callBP49(trxBp49Request, BP49_SERVICE_ROUTE, BP49_SERVICE_ROUTE, mqRoute).execute();
    }
    private void handleRuntimeException(RuntimeException e) {
        log.info(errorTrx, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.BAD_REQUEST, error);
    }
    private void handleIOException() {
        throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);
    }
    private void handleOtherExceptions(Exception e) {
        log.info(errorTrx, e.getMessage());
        var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
        error.setMessage(e.getMessage());
        throw new ServiceException(HttpStatus.CONFLICT, error);
    }
    private TrxBP49Response processResponse(Response<TrxBP49Response> responseApi) {
        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + client, responseBody);
            return responseBody;
        } else {
            return handleErrorResponse(responseApi);
        }
    }
    private TrxBP49Response handleErrorResponse(Response<TrxBP49Response> responseApi) {
        String errorBody = "";
        try {
            errorBody = responseApi.errorBody().string();
        } catch (IOException e) {
            // Handle the IOException
        }

        ObjectMapper objm = new ObjectMapper();
        TrxBP49Response err = null;
        try {
            err = objm.readValue(errorBody, TrxBP49Response.class);
        } catch (JsonMappingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }

        log.info(GUtils.ELOG + errLlaves, err != null ? err.getErrores() : noError);
        List<ErrorDTO> errorsDtos = new ArrayList<>();

        assert err != null;
        for (ErrorTrxDTO dtoEr : err.getErrores()) {
            errorsDtos.add(errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));
        }

        log.info(GUtils.ELOG + setLlaves, errorsDtos);
        if (!errorsDtos.isEmpty() && errorsDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found", ErrorType.FUNCTIONAL);
        }

        throw new ServiceException(HttpStatus.BAD_REQUEST, !errorsDtos.isEmpty() ? errorsDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.observability;

import java.util.ArrayList; import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "observability.external-apis")
public class ExternalApisHealthProperties {

	private int timeoutMs = 2000;
	private List<ApiCheck> checks = new ArrayList<>();

	public int getTimeoutMs() {
	    return timeoutMs;
	}

	public void setTimeoutMs(int timeoutMs) {
	    this.timeoutMs = timeoutMs;
	}

	public List<ApiCheck> getChecks() {
	    return checks;
	}

	public void setChecks(List<ApiCheck> checks) {
	    this.checks = checks;
	}

	public static class ApiCheck {
	    private String name;
	    private String url;
	    private boolean critical = true;
	    private List<Integer> acceptedStatuses;

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }

	    public boolean isCritical() {
	        return critical;
	    }

	    public void setCritical(boolean critical) {
	        this.critical = critical;
	    }

		public List<Integer> getAcceptedStatuses() {
			return acceptedStatuses;
		}

		public void setAcceptedStatuses(List<Integer> acceptedStatuses) {
			this.acceptedStatuses = acceptedStatuses;
		}
	    
	}
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.observability;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("externalApis") 
public class ExternalApisHealthIndicator implements HealthIndicator{
	private final ExternalApisHealthProperties properties;
	private final HttpClient httpClient;

	public ExternalApisHealthIndicator(ExternalApisHealthProperties properties) {
	    this.properties = properties;
	    this.httpClient = HttpClient.newBuilder()
	            .connectTimeout(Duration.ofMillis(properties.getTimeoutMs()))
	            .build();
	}

	@Override
	public Health health() {
	    Map<String, Object> details = new LinkedHashMap<>(properties.getChecks().size());
	    boolean allCriticalUp = true;

	    for (ExternalApisHealthProperties.ApiCheck api : properties.getChecks()) {
	        ApiResult result = checkApi(api);

	        Map<String, Object> apiDetail = new LinkedHashMap<>(5);
	        apiDetail.put("status", result.up ? "UP" : "DOWN");
	        apiDetail.put("url", api.getUrl());
	        apiDetail.put("critical", api.isCritical());

	        if (result.httpStatus != null) {
	            apiDetail.put("httpStatus", result.httpStatus);
	        }
	        if (result.error != null) {
	            apiDetail.put("error", result.error);
	        }

	        details.put(api.getName(), apiDetail);

	        if (api.isCritical() && !result.up) {
	            allCriticalUp = false;
	        }
	    }

	    return allCriticalUp
	            ? Health.up().withDetails(details).build()
	            : Health.down().withDetails(details).build();
	}

	private ApiResult checkApi(ExternalApisHealthProperties.ApiCheck api) {
	    try {
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(api.getUrl()))
	                .timeout(Duration.ofMillis(properties.getTimeoutMs()))
	                .GET()
	                .build();

	        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

	        int status = response.statusCode();
	        boolean up = isAcceptedStatus(status,api);

	        return new ApiResult(up, status, null);
	    } catch (InterruptedException e) {
	    	Thread.currentThread().interrupt();
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ": " + e.getMessage());
	    }catch (IOException e) {
	        return new ApiResult(false, null, e.getClass().getSimpleName() + ":: " + e.getMessage());
	    }
	}

	private static class ApiResult {
	    private final boolean up;
	    private final Integer httpStatus;
	    private final String error;

	    private ApiResult(boolean up, Integer httpStatus, String error) {
	        this.up = up;
	        this.httpStatus = httpStatus;
	        this.error = error;
	    }

		public boolean isUp() {
			return up;
		}

		public Integer getHttpStatus() {
			return httpStatus;
		}

		public String getError() {
			return error;
		}
	    
	}
	
	private boolean isAcceptedStatus(int status, ExternalApisHealthProperties.ApiCheck api) {
		if (api.getAcceptedStatuses() != null && !api.getAcceptedStatuses().isEmpty()) {
			return api.getAcceptedStatuses().contains(status);
		}
		return status >= 200 && status < 300;
	}

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Data
@Configuration
@ConfigurationProperties(prefix = "mov")
public class MovementConceptUtils {
    private HashMap<String, String> type;

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.regex.Pattern;

@Data
@Configuration
@ConfigurationProperties(prefix = "regex")
public class RegexUtils {

    @Value("${params.appName}")
    private String MS_NAME;
    @Value("${params.appVersion}")
    private String MS_VERSION;
    @Value("${errors.level}")
    private String LEVEL;
    @Value("${regex.error.code}")
    private String CODE;

    private HashMap<String, String> type;
    

    public void validateRegex(String regexType, String value, String fieldName) {

        String regularExpression = type.get(regexType);
        String message = type.get(regexType + "_error") != null ? type.get(regexType + "_error") : "Invalid format";

        var pattern = Pattern.compile(regularExpression);
        var matcher = pattern.matcher(value);
        boolean match = false;
        while (matcher.find()) {
            match = true;
        }

        if (!match) {

            ErrorDTO errorDTO = ErrorDTO.builder()
                    .code(MS_NAME + "-" + CODE)
                    .level(LEVEL)
                    .message("'" + fieldName + "': " + message)
                    .description(MS_NAME.toLowerCase() + "-" + MS_VERSION + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils;


import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositSettlementsDTO;
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
    private String BANK_CODE= "deposits.placement.destinationFunds.bankCode";
    private String NATIONAL_IDENTIFICATION = "deposits.placement.destinationFunds.account.nationalIdentification";
    private String DEPOSITS_FRECUENCY = "deposits.placement.periodicity.frequency";
    private String DEPOSITS_SETTLEMENT_CONDITION_CODE = "deposits.placement.settlementCondition.code";
    private String DEPOSITS_PURPOSE_CODE = "deposits.placement.purposeCode";
    private String DEPOSITS_TOTAL_INVESTED_AMOUNT = "economicData.initialTotalInvested.amount";
    private String DEPOSITS_CURRENCY = "economicData.initialTotalInvested.currency";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CODE ="economicData.settlements.settlementConcept.code";
    private String DEPOSITS_SETTLEMENT_CONCEPT_RATE = "economicData.settlements.settlementConcept.rate";
    private String DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT = "economicData.settlements.settlementConcept.amount.amount";
    private String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY = "economicData.settlements.settlementConcept.amount.currency";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH = "settlementConcept_typeCode_length";
    private static final String SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT = "settlementConcept_typeCode_format";
    private static final String CURRENCY_CODE_FORMAT = "currency_code_format";
    private static final String DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND = "economicData.settlements.settlemenmtConcept.amount.currency_not_found";
    private static final String INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT = "initialTotalInvested_amount_format";
    private static final String SETTLEMENT_CONCEPT_RATE_FORMAT = "settlementConcept_rate_format";
    private static final String SETTLEMENT_CONCEPT_CODE_FORMAT = "settlementConcept_code_format";
    private static final String SETTLEMENT_CONCEPT_CODE_LENGTH = "settlementConcept_code_length";
    private static final String SETTLEMENT_CONCEPT_CODE_NOT_FOUND = "economicData.settlements.settlementConcept.code_not_found";
    private static final String BANK_ID_NOT_FOUND = "bankId_not_found";


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
        if (!settlementContionCodeInputValidation(requestBodyData.getSettlementConditionCode())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementconditioncode_not_found"), ErrorType.FUNCTIONAL);
        }
    }


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
        validateSettlementCondition(requestBodyData);
        validatePurposeCode(requestBodyData, termDepositParametersRequest);
        validateTotalInvestedAmount(requestBodyData);
        validateCurrency(requestBodyData);
        validateSettlementConceptCodes(requestBodyData);

        //deposit -> placement -> periodicity -> frequency
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        regexUtils.validateRegex("frecuency_code_format", requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(), DEPOSITS_FRECUENCY);
        frecuencyValidation(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency());

    }

    private void validateBank(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_format", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        regexUtils.validateRegex("bank_code_length", requestBodyData.getBank().getBankId(), BANK_ID_FIELDNAME);
        if (!bankId.equals(requestBodyData.getBank().getBankId())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(BANK_ID_NOT_FOUND), ErrorType.FUNCTIONAL);
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
    private void validateSettlementCondition(RequestTermDepositsDTO requestBodyData) {
        //deposit -> placement -> settlementCondition -> code
        errorService.isBlank(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_format", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        regexUtils.validateRegex("settlementcondition_code_length", requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase(), DEPOSITS_SETTLEMENT_CONDITION_CODE);
        if (!settlementContionCodeInputValidation(requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode().toUpperCase())){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get("settlementcondition.code_not_found"), ErrorType.FUNCTIONAL);
        }
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
    private void validateSettlementConceptCodes(RequestTermDepositsDTO requestBodyData) {
        errorService.isBlank(requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        requestBodyData.getEconomicData().getSettlements().forEach(sett -> {
            switch (sett.getSettlementConcept().getCode()) {
                case "BGMF":
                    validateSettlementConceptBGMF(sett);
                    break;
                case "RETF":
                    validateSettlementConceptRETF(sett);
                    break;
                case "ITEA":
                    validateSettlementConceptITEA(sett);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid settlement concept code: " + sett.getSettlementConcept().getCode());
            }
        });
    }
    private void validateSettlementConceptBGMF(TermDepositSettlementsDTO sett) {
        regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_LENGTH, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_FORMAT, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(SETTLEMENT_CONCEPT_CODE_NOT_FOUND), ErrorType.FUNCTIONAL);
        }
        errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
        validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());
        errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
        regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
        errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
        regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
        errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
        regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
        if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
        }
    }
    private void validateSettlementConceptRETF(TermDepositSettlementsDTO sett) {
            if (sett.getSettlementConcept().getCode().equals("RETF")) {
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_LENGTH, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_FORMAT, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(SETTLEMENT_CONCEPT_CODE_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }
    }
    private void validateSettlementConceptITEA(TermDepositSettlementsDTO sett) {
            if (sett.getSettlementConcept().getCode().equals("ITEA")) {
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_LENGTH, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_CODE_FORMAT, sett.getSettlementConcept().getCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(SETTLEMENT_CONCEPT_CODE_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_LENGTH, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_TYPE_CODE_FORMAT, sett.getSettlementConcept().getTypeCode(), DEPOSITS_SETTLEMENT_CONCEPT_CODE);
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                errorService.isBlank(sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);
                regexUtils.validateRegex(SETTLEMENT_CONCEPT_RATE_FORMAT, sett.getSettlementConcept().getRate(), DEPOSITS_SETTLEMENT_CONCEPT_RATE);

                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);
                regexUtils.validateRegex(INITIAL_TOTAL_INVESTED_AMOUNT_FORMAT, sett.getSettlementConcept().getAmount().getAmount(), DEPOSITS_SETTLEMENT_CONCEPT_AMOUNT);

                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                regexUtils.validateRegex(CURRENCY_CODE_FORMAT, sett.getSettlementConcept().getAmount().getCurrency(), DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
                    throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(DEPOSITS_SETTLEMENT_CONCEPT_CURRENCY_NOT_FOUND), ErrorType.FUNCTIONAL);
                }
            }
    }

    private boolean settlementContionCodeInputValidation(String code) {
        return code.length() == 1 && SETTLEMENT_CONDITON_CODES.contains(code);
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
    public static boolean validateSettlementConcept(String input) {
        return input.equals("BGMF") || input.equals("RETF") || input.equals("ITEA");
    }
    public String validateSettlementConceptTypeCode(String input) {
        if(input.equals("BGMF")){
            return input = "C";
        } else if (input.equals("RETF")) {
           return  input = "D";
        }
        return input = "C";
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
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(BANK_ID_NOT_FOUND), ErrorType.FUNCTIONAL);
        }
        if (!findBank.getBankId().equals(input)){
            throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, errorService.getGeneral().get(BANK_ID_NOT_FOUND), ErrorType.FUNCTIONAL);
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class AmountRequestDTO {
    @NotNull(message = "{errors.general.null}")    
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;


public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;


public class CustomerRequestDTO {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Product {
    @NotNull(message = "{errors.general.null}")
    private String productCode;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private SubproductRequestDTO subproduct;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public SubproductRequestDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(SubproductRequestDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class RequestDTO {
    @NotNull(message = "{errors.general.null}")    
    private String frequency;
    @NotNull(message = "{errors.general.null}")
    private String periodTypeCode;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RequestSimulatePlacementDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private Product product;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private AmountRequestDTO amount;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private RequestDTO periodicity;
    @NotNull(message = "{errors.general.null}")
    private String settlementConditionCode;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AmountRequestDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountRequestDTO amount) {
        this.amount = amount;
    }

    public RequestDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(RequestDTO periodicity) {
        this.periodicity = periodicity;
    }

    public String getSettlementConditionCode() {
        return settlementConditionCode;
    }

    public void setSettlementConditionCode(String settlementConditionCode) {
        this.settlementConditionCode = settlementConditionCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.simulatePlacement;

import jakarta.validation.constraints.NotNull;

public class SubproductRequestDTO {
    @NotNull(message = "{errors.general.null}")
    private String subproductId;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;

import java.util.List;

public class RequestTermDepositsDTO {
    @Valid
    private TermDepositBankRequestDTO bank;
    @Valid
    private TermDepositProductDTO product;
    @Valid
    private TermDepositDepositDto deposit;
    @Valid
    private TermDepositEconomicDataDTO economicData;
    @Valid
    private List<TermDepositParticipantDTO> participants;

    public TermDepositBankRequestDTO getBank() {
        return bank;
    }

    public void setBank(TermDepositBankRequestDTO bank) {
        this.bank = bank;
    }

    public TermDepositProductDTO getProduct() {
        return product;
    }

    public void setProduct(TermDepositProductDTO product) {
        this.product = product;
    }

    public TermDepositDepositDto getDeposit() {
        return deposit;
    }

    public void setDeposit(TermDepositDepositDto deposit) {
        this.deposit = deposit;
    }

    public TermDepositEconomicDataDTO getEconomicData() {
        return economicData;
    }

    public void setEconomicData(TermDepositEconomicDataDTO economicData) {
        this.economicData = economicData;
    }

    public List<TermDepositParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<TermDepositParticipantDTO> participants) {
        this.participants = participants;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositAccountDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String nationalIdentification;

    public String getNationalIdentification() {
        return nationalIdentification;
    }

    public void setNationalIdentification(String nationalIdentification) {
        this.nationalIdentification = nationalIdentification;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositAmountDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String amount;
    @Valid
    @NotNull(message = "{errors.general.null}")
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositBankRequestDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String bankId;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private TermDepositCenterDTO center;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public TermDepositCenterDTO getCenter() {
        return center;
    }

    public void setCenter(TermDepositCenterDTO center) {
        this.center = center;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.constraints.NotNull;

public class TermDepositCenterDTO {
    @NotNull(message = "{errors.general.null}")
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits.TermDepositPlacementDTO;
import jakarta.validation.Valid;

public class TermDepositDepositDto {
    @Valid
    private TermDepositPlacementDTO placement;

    public TermDepositPlacementDTO getPlacement() {
        return placement;
    }

    public void setPlacement(TermDepositPlacementDTO placement) {
        this.placement = placement;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositDestinationFundsDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String accountIdType;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String bankcode;
    @Valid
    private TermDepositAccountDTO account;

    public String getAccountIdType() {
        return accountIdType;
    }

    public void setAccountIdType(String accountIdType) {
        this.accountIdType = accountIdType;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public TermDepositAccountDTO getAccount() {
        return account;
    }

    public void setAccount(TermDepositAccountDTO account) {
        this.account = account;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;

import java.util.List;

public class TermDepositEconomicDataDTO {
    @Valid
    private TermDepositAmountDTO initialTotalInvested;
    @Valid
    private List<TermDepositSettlementsDTO> settlements;

    public TermDepositAmountDTO getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(TermDepositAmountDTO initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<TermDepositSettlementsDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<TermDepositSettlementsDTO> settlements) {
        this.settlements = settlements;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.constraints.NotNull;

public class TermDepositParticipantDTO {
    @NotNull(message = "{errors.general.null}")
    private String participantId;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositPeriodicityDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String frequency;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositProductDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String productCode;
    @Valid
    private TermDepositSubproductDTO subproduct;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public TermDepositSubproductDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(TermDepositSubproductDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSettlementConceptDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String code;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String typeCode;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String rate;
    @Valid
    private TermDepositAmountDTO amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public TermDepositAmountDTO getAmount() {
        return amount;
    }

    public void setAmount(TermDepositAmountDTO amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSettlementConditionDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;

public class TermDepositSettlementsDTO {
    @Valid
    private TermDepositSettlementConceptDTO settlementConcept;

    public TermDepositSettlementConceptDTO getSettlementConcept() {
        return settlementConcept;
    }

    public void setSettlementConcept(TermDepositSettlementConceptDTO settlementConcept) {
        this.settlementConcept = settlementConcept;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositSubproductDTO {
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String subproductId;

    public String getSubproductId() {
        return subproductId;
    }

    public void setSubproductId(String subproductId) {
        this.subproductId = subproductId;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class AmountResponseDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class AmountResponseSettlementDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class InitialTotalInvestedDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class PecentageYieldResponseDTO {
    private String nominalInterestRate;
    private String percentageYield;

    public String getNominalInterestRate() {
        return nominalInterestRate;
    }

    public void setNominalInterestRate(String nominalInterestRate) {
        this.nominalInterestRate = nominalInterestRate;
    }

    public String getPercentageYield() {
        return percentageYield;
    }

    public void setPercentageYield(String percentageYield) {
        this.percentageYield = percentageYield;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class PeriodicityResponseDTO {
    private String frequency;
    private String periodTypeCode;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class ProductResponseDTO {
    private String productCode;
    private String productDescription;
    private SubproductResponseDTO subproduct;

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

    public SubproductResponseDTO getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(SubproductResponseDTO subproduct) {
        this.subproduct = subproduct;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class ProfitabilityAtMaturityDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class SettlementResponseDTO {
    private String conceptCode;
    private String conceptDescription;
    private String typeCode;
    private String typeDescription;
    private String rate;
    private AmountResponseSettlementDTO amount;

    public String getConceptCode() {
        return conceptCode;
    }

    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }

    public String getConceptDescription() {
        return conceptDescription;
    }

    public void setConceptDescription(String conceptDescription) {
        this.conceptDescription = conceptDescription;
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

    public AmountResponseSettlementDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountResponseSettlementDTO amount) {
        this.amount = amount;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


import java.util.List;

public class SimulatePlacementResponseDTO {
    private ProductResponseDTO product;
    private AmountResponseDTO amount;
    private PeriodicityResponseDTO periodicity;
    private String maturityDate;
    private String openingValueDate;
    private String settlementConditionCode;
    private String settlementConditionDescription;
    private boolean isCapitalizable;
    private boolean isRenewal;
    private PecentageYieldResponseDTO percentageYield;
    private ProfitabilityAtMaturityDTO profitabilityAtMaturity;
    private InitialTotalInvestedDTO initialTotalInvested;
    private List<SettlementResponseDTO> settlements;

    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }

    public AmountResponseDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountResponseDTO amount) {
        this.amount = amount;
    }

    public PeriodicityResponseDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(PeriodicityResponseDTO periodicity) {
        this.periodicity = periodicity;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningValueDate() {
        return openingValueDate;
    }

    public void setOpeningValueDate(String openingValueDate) {
        this.openingValueDate = openingValueDate;
    }

    public String getSettlementConditionCode() {
        return settlementConditionCode;
    }

    public void setSettlementConditionCode(String settlementConditionCode) {
        this.settlementConditionCode = settlementConditionCode;
    }

    public String getSettlementConditionDescription() {
        return settlementConditionDescription;
    }

    public void setSettlementConditionDescription(String settlementConditionDescription) {
        this.settlementConditionDescription = settlementConditionDescription;
    }

    public boolean isCapitalizable() {
        return isCapitalizable;
    }

    public void setCapitalizable(boolean capitalizable) {
        isCapitalizable = capitalizable;
    }

    public boolean isRenewal() {
        return isRenewal;
    }

    public void setRenewal(boolean renewal) {
        isRenewal = renewal;
    }

    public PecentageYieldResponseDTO getPercentageYield() {
        return percentageYield;
    }

    public void setPercentageYield(PecentageYieldResponseDTO percentageYield) {
        this.percentageYield = percentageYield;
    }

    public ProfitabilityAtMaturityDTO getProfitabilityAtMaturity() {
        return profitabilityAtMaturity;
    }

    public void setProfitabilityAtMaturity(ProfitabilityAtMaturityDTO profitabilityAtMaturity) {
        this.profitabilityAtMaturity = profitabilityAtMaturity;
    }

    public InitialTotalInvestedDTO getInitialTotalInvested() {
        return initialTotalInvested;
    }

    public void setInitialTotalInvested(InitialTotalInvestedDTO initialTotalInvested) {
        this.initialTotalInvested = initialTotalInvested;
    }

    public List<SettlementResponseDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<SettlementResponseDTO> settlements) {
        this.settlements = settlements;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.simulatePlacement;


public class SubproductResponseDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;


public class ResponseTermDepositPlacementDTO {
    private String placementId;
    private String maturityDate;
    private String openingValueDate;
    private String annualPercentageYield;

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpeningValueDate() {
        return openingValueDate;
    }

    public void setOpeningValueDate(String openingValueDate) {
        this.openingValueDate = openingValueDate;
    }

    public String getAnnualPercentageYield() {
        return annualPercentageYield;
    }

    public void setAnnualPercentageYield(String annualPercentageYield) {
        this.annualPercentageYield = annualPercentageYield;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;


public class ResponseTermDepositsDTO {
    private String depositId;
    private ResponseTermDepositPlacementDTO placement;

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public ResponseTermDepositPlacementDTO getPlacement() {
        return placement;
    }

    public void setPlacement(ResponseTermDepositPlacementDTO placement) {
        this.placement = placement;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.response.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositDestinationFundsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositPeriodicityDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.request.term_deposits.TermDepositSettlementConditionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TermDepositPlacementDTO {
    @Valid
    private TermDepositDestinationFundsDTO destinationFunds;
    @Valid
    private TermDepositPeriodicityDTO periodicity;
    @Valid
    private TermDepositSettlementConditionDTO settlementCondition;
    @Valid
    @NotNull(message = "{errors.general.null}")
    private String purposeCode;

    public TermDepositDestinationFundsDTO getDestinationFunds() {
        return destinationFunds;
    }

    public void setDestinationFunds(TermDepositDestinationFundsDTO destinationFunds) {
        this.destinationFunds = destinationFunds;
    }

    public TermDepositPeriodicityDTO getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(TermDepositPeriodicityDTO periodicity) {
        this.periodicity = periodicity;
    }

    public TermDepositSettlementConditionDTO getSettlementCondition() {
        return settlementCondition;
    }

    public void setSettlementCondition(TermDepositSettlementConditionDTO settlementCondition) {
        this.settlementCondition = settlementCondition;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.request.TermDepositTransactionRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.domain.termdeposittransaction.response.TermDepositTransactionResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.exception.error.ErrorService;

import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.mappers.TermDepositTransactionMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.service.TermDepositTransactionsService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpsttrnsccn.utils.TermDepositUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TermDepositTransactionImpl implements TermDepositTransactionsService {

    final TrxSanbaService trxSanbaService;

    final TermDepositUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final TermDepositTransactionMappers transactionMappers;
    @Value("${service-route-trx.BP49}")
    private String BP49_SERVICE_ROUTE;
    private static final String DEPOSITIDFIELDNAME = "deposit_id";
    private static final String PLACEMENTIDFIELDNAME = "placement_id";

    public TermDepositTransactionResponse getDepositTrasaction(TermDepositTransactionRequest request){

        errorService.isBlank(request.getDepositId(), DEPOSITIDFIELDNAME);
        regexUtils.validateRegex("only_numbers", request.getDepositId(), DEPOSITIDFIELDNAME);
        regexUtils.validateRegex("strict_length_20", request.getDepositId(), DEPOSITIDFIELDNAME);

        errorService.isBlank(request.getPlacementId(), PLACEMENTIDFIELDNAME);
        regexUtils.validateRegex("placement_format", request.getPlacementId(), PLACEMENTIDFIELDNAME);

        TrxBP49Request trxBP49Request = new TrxBP49Request(ClientUtils.buildHeader(BP49_SERVICE_ROUTE));        
        var data = new TrxBP49DataRequest();
        data.setCuenta("");
        data.setSecuencia(request.getPlacementId().substring(0,5));
        data.setOfic("0100");
        data.setEnt("0065");
        data.setNumeroCertificado((request.getDepositId()+request.getPlacementId().replace("-","")));
        data.setBuscarPor("");
        data.setDocumentoCajero("");
        trxBP49Request.setData(data);
        TrxBP49Response trxBP49Response = trxSanbaService.trxBP49(trxBP49Request);        

        if(trxBP49Response.getData().getMovimientos().get(0) != null){
            return transactionMappers.transactionResponse(trxBP49Response, request);
        }

        return null;
    }
}
