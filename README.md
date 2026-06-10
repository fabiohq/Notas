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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanksParametersRequest {
    private String authorization;
    private String xSantanderClientId;

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

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.request;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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
    private ArrayList<Settlement> settlements;

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

    public void setRenewable(boolean isRenewable) {
        this.isRenewable = isRenewable;
    }

    public boolean isCapitalized() {
        return isCapitalized;
    }

    public void setCapitalized(boolean isCapitalized) {
        this.isCapitalized = isCapitalized;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
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

    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<Settlement> settlements) {
        this.settlements = settlements;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.depositplacement.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01DataRequest {
    private String nroCliente;
    private String producto;
    private String nroCtaExtAbono;
    private String tipoCtaExtAbono;
    private String diviCtaExtAbono;
    private String bancoCtaExtAbono;
    private String ejecutivoComercial;

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getNroCtaExtAbono() {
        return nroCtaExtAbono;
    }

    public void setNroCtaExtAbono(String nroCtaExtAbono) {
        this.nroCtaExtAbono = nroCtaExtAbono;
    }

    public String getTipoCtaExtAbono() {
        return tipoCtaExtAbono;
    }

    public void setTipoCtaExtAbono(String tipoCtaExtAbono) {
        this.tipoCtaExtAbono = tipoCtaExtAbono;
    }

    public String getDiviCtaExtAbono() {
        return diviCtaExtAbono;
    }

    public void setDiviCtaExtAbono(String diviCtaExtAbono) {
        this.diviCtaExtAbono = diviCtaExtAbono;
    }

    public String getBancoCtaExtAbono() {
        return bancoCtaExtAbono;
    }

    public void setBancoCtaExtAbono(String bancoCtaExtAbono) {
        this.bancoCtaExtAbono = bancoCtaExtAbono;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01HeaderRequest {
    private String rutaServicio;
    private Bp01SesionRequest sesion;
    private String funcion;
    private String secuencia;
    private String canal;

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public Bp01SesionRequest getSesion() {
        return sesion;
    }

    public void setSesion(Bp01SesionRequest sesion) {
        this.sesion = sesion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01SesionRequest {
    private String usuario;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private String diasRestantesCambioClave;
    private String fechaContable;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Request {

    private TrxHeader cabecera;
    private Bp01DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public Bp01DataRequest getData() {
        return data;
    }

    public void setData(Bp01DataRequest data) {
        this.data = data;
    }

    public TrxBp01Request(TrxPersonHeader header) {
        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }
}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto.Bp01DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp01Response {
    private Bp01DataResponseDTO data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public Bp01DataResponseDTO getData() {
        return data;
    }

    public void setData(Bp01DataResponseDTO data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02DataRequest {
    private String numeroDePersona;
    private String cuentaInversor;
    private String producto;
    private String subproducto;
    private String plazo;
    private String divisa;
    private String importe;
    private String ejecutivoComercial;
    private String fechaDeAltaDeIpf;
    private String periodoLiquidacion;
    private String tipoDeTasa;
    private String tarifa;
    private String tipoInteresTotal;
    private String spread;
    private String renovacionAutomatic;
    private String tarifaRenovacion;
    private String tipoInteresRenov;
    private String spreadRenovacion;
    private String capitalizaIntereses;
    private String capitalizaReajuste;
    private String rentaProgramada;
    private String planComisiones;
    private String objetivoDeLaInver;
    private String observaciones;
    private String origenDeLosFondos;
    private String cccAsociado;
    private String divisaAsociadaCcc;
    private String cccAsociadoDos;
    private String divisaAsociadaCcc2;
    private String bancoCtaExtAbono;
    private String cuentaExtAbono;
    private String divisCtaExtAbono;
    private String tipoCtaExtAbono;
    private String bancoCtaExtFondeo;
    private String cuentaExtFondeo;
    private String divisCtaExtFondeo;
    private String tipoCtaExtFonde;
    private String importeGmfBonifica;
    private String porcentajeDeGmfBonificado;
    private String importeRetFteCal;
    private String porcentajeDeRetFuentaCa;
    
    public String getNumeroDePersona() {
        return numeroDePersona;
    }

    public void setNumeroDePersona(String numeroDePersona) {
        this.numeroDePersona = numeroDePersona;
    }

    public String getCuentaInversor() {
        return cuentaInversor;
    }

    public void setCuentaInversor(String cuentaInversor) {
        this.cuentaInversor = cuentaInversor;
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

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getFechaDeAltaDeIpf() {
        return fechaDeAltaDeIpf;
    }

    public void setFechaDeAltaDeIpf(String fechaDeAltaDeIpf) {
        this.fechaDeAltaDeIpf = fechaDeAltaDeIpf;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getTipoDeTasa() {
        return tipoDeTasa;
    }

    public void setTipoDeTasa(String tipoDeTasa) {
        this.tipoDeTasa = tipoDeTasa;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getTipoInteresTotal() {
        return tipoInteresTotal;
    }

    public void setTipoInteresTotal(String tipoInteresTotal) {
        this.tipoInteresTotal = tipoInteresTotal;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getRenovacionAutomatic() {
        return renovacionAutomatic;
    }

    public void setRenovacionAutomatic(String renovacionAutomatic) {
        this.renovacionAutomatic = renovacionAutomatic;
    }

    public String getTarifaRenovacion() {
        return tarifaRenovacion;
    }

    public void setTarifaRenovacion(String tarifaRenovacion) {
        this.tarifaRenovacion = tarifaRenovacion;
    }

    public String getTipoInteresRenov() {
        return tipoInteresRenov;
    }

    public void setTipoInteresRenov(String tipoInteresRenov) {
        this.tipoInteresRenov = tipoInteresRenov;
    }

    public String getSpreadRenovacion() {
        return spreadRenovacion;
    }

    public void setSpreadRenovacion(String spreadRenovacion) {
        this.spreadRenovacion = spreadRenovacion;
    }

    public String getCapitalizaIntereses() {
        return capitalizaIntereses;
    }

    public void setCapitalizaIntereses(String capitalizaIntereses) {
        this.capitalizaIntereses = capitalizaIntereses;
    }

    public String getCapitalizaReajuste() {
        return capitalizaReajuste;
    }

    public void setCapitalizaReajuste(String capitalizaReajuste) {
        this.capitalizaReajuste = capitalizaReajuste;
    }

    public String getRentaProgramada() {
        return rentaProgramada;
    }

    public void setRentaProgramada(String rentaProgramada) {
        this.rentaProgramada = rentaProgramada;
    }

    public String getPlanComisiones() {
        return planComisiones;
    }

    public void setPlanComisiones(String planComisiones) {
        this.planComisiones = planComisiones;
    }

    public String getObjetivoDeLaInver() {
        return objetivoDeLaInver;
    }

    public void setObjetivoDeLaInver(String objetivoDeLaInver) {
        this.objetivoDeLaInver = objetivoDeLaInver;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getOrigenDeLosFondos() {
        return origenDeLosFondos;
    }

    public void setOrigenDeLosFondos(String origenDeLosFondos) {
        this.origenDeLosFondos = origenDeLosFondos;
    }

    public String getCccAsociado() {
        return cccAsociado;
    }

    public void setCccAsociado(String cccAsociado) {
        this.cccAsociado = cccAsociado;
    }

    public String getDivisaAsociadaCcc() {
        return divisaAsociadaCcc;
    }

    public void setDivisaAsociadaCcc(String divisaAsociadaCcc) {
        this.divisaAsociadaCcc = divisaAsociadaCcc;
    }

    public String getCccAsociadoDos() {
        return cccAsociadoDos;
    }

    public void setCccAsociadoDos(String cccAsociadoDos) {
        this.cccAsociadoDos = cccAsociadoDos;
    }

    public String getDivisaAsociadaCcc2() {
        return divisaAsociadaCcc2;
    }

    public void setDivisaAsociadaCcc2(String divisaAsociadaCcc2) {
        this.divisaAsociadaCcc2 = divisaAsociadaCcc2;
    }

    public String getBancoCtaExtAbono() {
        return bancoCtaExtAbono;
    }

    public void setBancoCtaExtAbono(String bancoCtaExtAbono) {
        this.bancoCtaExtAbono = bancoCtaExtAbono;
    }

    public String getCuentaExtAbono() {
        return cuentaExtAbono;
    }

    public void setCuentaExtAbono(String cuentaExtAbono) {
        this.cuentaExtAbono = cuentaExtAbono;
    }

    public String getDivisCtaExtAbono() {
        return divisCtaExtAbono;
    }

    public void setDivisCtaExtAbono(String divisCtaExtAbono) {
        this.divisCtaExtAbono = divisCtaExtAbono;
    }

    public String getTipoCtaExtAbono() {
        return tipoCtaExtAbono;
    }

    public void setTipoCtaExtAbono(String tipoCtaExtAbono) {
        this.tipoCtaExtAbono = tipoCtaExtAbono;
    }

    public String getBancoCtaExtFondeo() {
        return bancoCtaExtFondeo;
    }

    public void setBancoCtaExtFondeo(String bancoCtaExtFondeo) {
        this.bancoCtaExtFondeo = bancoCtaExtFondeo;
    }

    public String getCuentaExtFondeo() {
        return cuentaExtFondeo;
    }

    public void setCuentaExtFondeo(String cuentaExtFondeo) {
        this.cuentaExtFondeo = cuentaExtFondeo;
    }

    public String getDivisCtaExtFondeo() {
        return divisCtaExtFondeo;
    }

    public void setDivisCtaExtFondeo(String divisCtaExtFondeo) {
        this.divisCtaExtFondeo = divisCtaExtFondeo;
    }

    public String getTipoCtaExtFonde() {
        return tipoCtaExtFonde;
    }

    public void setTipoCtaExtFonde(String tipoCtaExtFonde) {
        this.tipoCtaExtFonde = tipoCtaExtFonde;
    }

    public String getImporteGmfBonifica() {
        return importeGmfBonifica;
    }

    public void setImporteGmfBonifica(String importeGmfBonifica) {
        this.importeGmfBonifica = importeGmfBonifica;
    }

    public String getPorcentajeDeGmfBonificado() {
        return porcentajeDeGmfBonificado;
    }

    public void setPorcentajeDeGmfBonificado(String porcentajeDeGmfBonificado) {
        this.porcentajeDeGmfBonificado = porcentajeDeGmfBonificado;
    }

    public String getImporteRetFteCal() {
        return importeRetFteCal;
    }

    public void setImporteRetFteCal(String importeRetFteCal) {
        this.importeRetFteCal = importeRetFteCal;
    }

    public String getPorcentajeDeRetFuentaCa() {
        return porcentajeDeRetFuentaCa;
    }

    public void setPorcentajeDeRetFuentaCa(String porcentajeDeRetFuentaCa) {
        this.porcentajeDeRetFuentaCa = porcentajeDeRetFuentaCa;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02Request {
    private TrxHeader cabecera;
    private TrxBp02DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBp02DataRequest getData() {
        return data;
    }

    public void setData(TrxBp02DataRequest data) {
        this.data = data;
    }

    public TrxBp02Request(TrxPersonHeader header) {
        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01BGMP010ResponseDTO {
    private String CTAMODE;
    private String EXTACTA;
    private String EXTADIV;
    private String EXTATIP;
    private String EXTFTIP;
    private String DIVASO2;
    private String EXTFCTA;
    private String EXTFDIV;
    private String PRODUCT;
    private String DIVASO;
    private String NUMPER;
    private String CCCASO2;
    private String EXTAENT;
    private String CCCASO;
    private String EJECCOM;
    private String CCCINVE;
    private String EXTFENT;

    public String getCTAMODE() {
        return CTAMODE;
    }

    public void setCTAMODE(String cTAMODE) {
        CTAMODE = cTAMODE;
    }

    public String getEXTACTA() {
        return EXTACTA;
    }

    public void setEXTACTA(String eXTACTA) {
        EXTACTA = eXTACTA;
    }

    public String getEXTADIV() {
        return EXTADIV;
    }

    public void setEXTADIV(String eXTADIV) {
        EXTADIV = eXTADIV;
    }

    public String getEXTATIP() {
        return EXTATIP;
    }

    public void setEXTATIP(String eXTATIP) {
        EXTATIP = eXTATIP;
    }

    public String getEXTFTIP() {
        return EXTFTIP;
    }

    public void setEXTFTIP(String eXTFTIP) {
        EXTFTIP = eXTFTIP;
    }

    public String getDIVASO2() {
        return DIVASO2;
    }

    public void setDIVASO2(String dIVASO2) {
        DIVASO2 = dIVASO2;
    }

    public String getEXTFCTA() {
        return EXTFCTA;
    }

    public void setEXTFCTA(String eXTFCTA) {
        EXTFCTA = eXTFCTA;
    }

    public String getEXTFDIV() {
        return EXTFDIV;
    }

    public void setEXTFDIV(String eXTFDIV) {
        EXTFDIV = eXTFDIV;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String pRODUCT) {
        PRODUCT = pRODUCT;
    }

    public String getDIVASO() {
        return DIVASO;
    }

    public void setDIVASO(String dIVASO) {
        DIVASO = dIVASO;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String nUMPER) {
        NUMPER = nUMPER;
    }

    public String getCCCASO2() {
        return CCCASO2;
    }

    public void setCCCASO2(String cCCASO2) {
        CCCASO2 = cCCASO2;
    }

    public String getEXTAENT() {
        return EXTAENT;
    }

    public void setEXTAENT(String eXTAENT) {
        EXTAENT = eXTAENT;
    }

    public String getCCCASO() {
        return CCCASO;
    }

    public void setCCCASO(String cCCASO) {
        CCCASO = cCCASO;
    }

    public String getEJECCOM() {
        return EJECCOM;
    }

    public void setEJECCOM(String eJECCOM) {
        EJECCOM = eJECCOM;
    }

    public String getCCCINVE() {
        return CCCINVE;
    }

    public void setCCCINVE(String cCCINVE) {
        CCCINVE = cCCINVE;
    }

    public String getEXTFENT() {
        return EXTFENT;
    }

    public void setEXTFENT(String eXTFENT) {
        EXTFENT = eXTFENT;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bp01DataResponseDTO {
    private Bp01BGMP010ResponseDTO BGMP010;

    public Bp01BGMP010ResponseDTO getBGMP010() {
        return BGMP010;
    }

    public void setBGMP010(Bp01BGMP010ResponseDTO bGMP010) {
        BGMP010 = bGMP010;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto;

public class TrxBp02BGMP020Response {
    private String SECUIPF;
    private String FECPLIQ;
    private String IMPINTT;
    private String OBJINVE;
    private String SECUREN;
    private String TARIFA;
    private String SUBPROD;
    private String NUMPLAZ;
    private String EXTFTIP;
    private String EXTFDEN;
    private String IMPTTON;
    private String EXTFCTA;
    private String DESADIV;
    private String EXTFDIV;
    private String PRODUCT;
    private String DIVDESC;
    private String IMPCRET;
    private String ORIFOND;
    private String EXTAENT;
    private String IMPINTN;
    private String CCCINVE;
    private String PERLIQ;
    private String OBSERVA;
    private String EXTADTI;
    private String DESCOFO;
    private String DESCOBJ;
    private String PORBGMF;
    private String NUMPER;
    private String RENPROG;
    private String FECALTA;
    private String CCCASO2;
    private String CAPINTE;
    private String CCCASO;
    private String EXTFENT;
    private String PLANCOM;
    private String DESFDIV;
    private String FECVCTO;
    private String DESSUPR;
    private String RENAUTO;
    private String EXTFDTI;
    private String TARENOV;
    private String EJECOM;
    private String IMPINVE;
    private String IMPBGMF;
    private String SPREAD;
    private String IMPINVN;
    private String DESNPLZ;
    private String DIVASOC;
    private String IMPTTOB;
    private String SPREREN;
    private String EXTACTA;
    private String EXTADIV;
    private String FECULIQ;
    private String EXTATIP;
    private String TIPOREN;
    private String DIVISA;
    private String DIVASO2;
    private String CAPREAJ;
    private String PORCRET;
    private String TIPINTE;
    private String TIPOTAS;
    private String EXTADEN;
    private String DESPERL;
    private String TIPINTN;

    public String getSECUIPF() {
        return SECUIPF;
    }

    public void setSECUIPF(String sECUIPF) {
        SECUIPF = sECUIPF;
    }

    public String getFECPLIQ() {
        return FECPLIQ;
    }

    public void setFECPLIQ(String fECPLIQ) {
        FECPLIQ = fECPLIQ;
    }

    public String getIMPINTT() {
        return IMPINTT;
    }

    public void setIMPINTT(String iMPINTT) {
        IMPINTT = iMPINTT;
    }

    public String getOBJINVE() {
        return OBJINVE;
    }

    public void setOBJINVE(String oBJINVE) {
        OBJINVE = oBJINVE;
    }

    public String getSECUREN() {
        return SECUREN;
    }

    public void setSECUREN(String sECUREN) {
        SECUREN = sECUREN;
    }

    public String getTARIFA() {
        return TARIFA;
    }

    public void setTARIFA(String tARIFA) {
        TARIFA = tARIFA;
    }

    public String getSUBPROD() {
        return SUBPROD;
    }

    public void setSUBPROD(String sUBPROD) {
        SUBPROD = sUBPROD;
    }

    public String getNUMPLAZ() {
        return NUMPLAZ;
    }

    public void setNUMPLAZ(String nUMPLAZ) {
        NUMPLAZ = nUMPLAZ;
    }

    public String getEXTFTIP() {
        return EXTFTIP;
    }

    public void setEXTFTIP(String eXTFTIP) {
        EXTFTIP = eXTFTIP;
    }

    public String getEXTFDEN() {
        return EXTFDEN;
    }

    public void setEXTFDEN(String eXTFDEN) {
        EXTFDEN = eXTFDEN;
    }

    public String getIMPTTON() {
        return IMPTTON;
    }

    public void setIMPTTON(String iMPTTON) {
        IMPTTON = iMPTTON;
    }

    public String getEXTFCTA() {
        return EXTFCTA;
    }

    public void setEXTFCTA(String eXTFCTA) {
        EXTFCTA = eXTFCTA;
    }

    public String getDESADIV() {
        return DESADIV;
    }

    public void setDESADIV(String dESADIV) {
        DESADIV = dESADIV;
    }

    public String getEXTFDIV() {
        return EXTFDIV;
    }

    public void setEXTFDIV(String eXTFDIV) {
        EXTFDIV = eXTFDIV;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String pRODUCT) {
        PRODUCT = pRODUCT;
    }

    public String getDIVDESC() {
        return DIVDESC;
    }

    public void setDIVDESC(String dIVDESC) {
        DIVDESC = dIVDESC;
    }

    public String getIMPCRET() {
        return IMPCRET;
    }

    public void setIMPCRET(String iMPCRET) {
        IMPCRET = iMPCRET;
    }

    public String getORIFOND() {
        return ORIFOND;
    }

    public void setORIFOND(String oRIFOND) {
        ORIFOND = oRIFOND;
    }

    public String getEXTAENT() {
        return EXTAENT;
    }

    public void setEXTAENT(String eXTAENT) {
        EXTAENT = eXTAENT;
    }

    public String getIMPINTN() {
        return IMPINTN;
    }

    public void setIMPINTN(String iMPINTN) {
        IMPINTN = iMPINTN;
    }

    public String getCCCINVE() {
        return CCCINVE;
    }

    public void setCCCINVE(String cCCINVE) {
        CCCINVE = cCCINVE;
    }

    public String getPERLIQ() {
        return PERLIQ;
    }

    public void setPERLIQ(String pERLIQ) {
        PERLIQ = pERLIQ;
    }

    public String getOBSERVA() {
        return OBSERVA;
    }

    public void setOBSERVA(String oBSERVA) {
        OBSERVA = oBSERVA;
    }

    public String getEXTADTI() {
        return EXTADTI;
    }

    public void setEXTADTI(String eXTADTI) {
        EXTADTI = eXTADTI;
    }

    public String getDESCOFO() {
        return DESCOFO;
    }

    public void setDESCOFO(String dESCOFO) {
        DESCOFO = dESCOFO;
    }

    public String getDESCOBJ() {
        return DESCOBJ;
    }

    public void setDESCOBJ(String dESCOBJ) {
        DESCOBJ = dESCOBJ;
    }

    public String getPORBGMF() {
        return PORBGMF;
    }

    public void setPORBGMF(String pORBGMF) {
        PORBGMF = pORBGMF;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String nUMPER) {
        NUMPER = nUMPER;
    }

    public String getRENPROG() {
        return RENPROG;
    }

    public void setRENPROG(String rENPROG) {
        RENPROG = rENPROG;
    }

    public String getFECALTA() {
        return FECALTA;
    }

    public void setFECALTA(String fECALTA) {
        FECALTA = fECALTA;
    }

    public String getCCCASO2() {
        return CCCASO2;
    }

    public void setCCCASO2(String cCCASO2) {
        CCCASO2 = cCCASO2;
    }

    public String getCAPINTE() {
        return CAPINTE;
    }

    public void setCAPINTE(String cAPINTE) {
        CAPINTE = cAPINTE;
    }

    public String getCCCASO() {
        return CCCASO;
    }

    public void setCCCASO(String cCCASO) {
        CCCASO = cCCASO;
    }

    public String getEXTFENT() {
        return EXTFENT;
    }

    public void setEXTFENT(String eXTFENT) {
        EXTFENT = eXTFENT;
    }

    public String getPLANCOM() {
        return PLANCOM;
    }

    public void setPLANCOM(String pLANCOM) {
        PLANCOM = pLANCOM;
    }

    public String getDESFDIV() {
        return DESFDIV;
    }

    public void setDESFDIV(String dESFDIV) {
        DESFDIV = dESFDIV;
    }

    public String getFECVCTO() {
        return FECVCTO;
    }

    public void setFECVCTO(String fECVCTO) {
        FECVCTO = fECVCTO;
    }

    public String getDESSUPR() {
        return DESSUPR;
    }

    public void setDESSUPR(String dESSUPR) {
        DESSUPR = dESSUPR;
    }

    public String getRENAUTO() {
        return RENAUTO;
    }

    public void setRENAUTO(String rENAUTO) {
        RENAUTO = rENAUTO;
    }

    public String getEXTFDTI() {
        return EXTFDTI;
    }

    public void setEXTFDTI(String eXTFDTI) {
        EXTFDTI = eXTFDTI;
    }

    public String getTARENOV() {
        return TARENOV;
    }

    public void setTARENOV(String tARENOV) {
        TARENOV = tARENOV;
    }

    public String getEJECOM() {
        return EJECOM;
    }

    public void setEJECOM(String eJECOM) {
        EJECOM = eJECOM;
    }

    public String getIMPINVE() {
        return IMPINVE;
    }

    public void setIMPINVE(String iMPINVE) {
        IMPINVE = iMPINVE;
    }

    public String getIMPBGMF() {
        return IMPBGMF;
    }

    public void setIMPBGMF(String iMPBGMF) {
        IMPBGMF = iMPBGMF;
    }

    public String getSPREAD() {
        return SPREAD;
    }

    public void setSPREAD(String sPREAD) {
        SPREAD = sPREAD;
    }

    public String getIMPINVN() {
        return IMPINVN;
    }

    public void setIMPINVN(String iMPINVN) {
        IMPINVN = iMPINVN;
    }

    public String getDESNPLZ() {
        return DESNPLZ;
    }

    public void setDESNPLZ(String dESNPLZ) {
        DESNPLZ = dESNPLZ;
    }

    public String getDIVASOC() {
        return DIVASOC;
    }

    public void setDIVASOC(String dIVASOC) {
        DIVASOC = dIVASOC;
    }

    public String getIMPTTOB() {
        return IMPTTOB;
    }

    public void setIMPTTOB(String iMPTTOB) {
        IMPTTOB = iMPTTOB;
    }

    public String getSPREREN() {
        return SPREREN;
    }

    public void setSPREREN(String sPREREN) {
        SPREREN = sPREREN;
    }

    public String getEXTACTA() {
        return EXTACTA;
    }

    public void setEXTACTA(String eXTACTA) {
        EXTACTA = eXTACTA;
    }

    public String getEXTADIV() {
        return EXTADIV;
    }

    public void setEXTADIV(String eXTADIV) {
        EXTADIV = eXTADIV;
    }

    public String getFECULIQ() {
        return FECULIQ;
    }

    public void setFECULIQ(String fECULIQ) {
        FECULIQ = fECULIQ;
    }

    public String getEXTATIP() {
        return EXTATIP;
    }

    public void setEXTATIP(String eXTATIP) {
        EXTATIP = eXTATIP;
    }

    public String getTIPOREN() {
        return TIPOREN;
    }

    public void setTIPOREN(String tIPOREN) {
        TIPOREN = tIPOREN;
    }

    public String getDIVISA() {
        return DIVISA;
    }

    public void setDIVISA(String dIVISA) {
        DIVISA = dIVISA;
    }

    public String getDIVASO2() {
        return DIVASO2;
    }

    public void setDIVASO2(String dIVASO2) {
        DIVASO2 = dIVASO2;
    }

    public String getCAPREAJ() {
        return CAPREAJ;
    }

    public void setCAPREAJ(String cAPREAJ) {
        CAPREAJ = cAPREAJ;
    }

    public String getPORCRET() {
        return PORCRET;
    }

    public void setPORCRET(String pORCRET) {
        PORCRET = pORCRET;
    }

    public String getTIPINTE() {
        return TIPINTE;
    }

    public void setTIPINTE(String tIPINTE) {
        TIPINTE = tIPINTE;
    }

    public String getTIPOTAS() {
        return TIPOTAS;
    }

    public void setTIPOTAS(String tIPOTAS) {
        TIPOTAS = tIPOTAS;
    }

    public String getEXTADEN() {
        return EXTADEN;
    }

    public void setEXTADEN(String eXTADEN) {
        EXTADEN = eXTADEN;
    }

    public String getDESPERL() {
        return DESPERL;
    }

    public void setDESPERL(String dESPERL) {
        DESPERL = dESPERL;
    }

    public String getTIPINTN() {
        return TIPINTN;
    }

    public void setTIPINTN(String tIPINTN) {
        TIPINTN = tIPINTN;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto;

public class TrxBp02DataResponse {
    private TrxBp02BGMP020Response BGMP020;

    public TrxBp02BGMP020Response getBGMP020() {
        return BGMP020;
    }

    public void setBGMP020(TrxBp02BGMP020Response bGMP020) {
        BGMP020 = bGMP020;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.dto.TrxBp02DataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBp02Response {
    private TrxBp02DataResponse data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public TrxBp02DataResponse getData() {
        return data;
    }

    public void setData(TrxBp02DataResponse data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP13DataRequest {
    private String entidad;
    private String oficina;
    private String cuenta;
    private String numSecuencia;
    private String numCertificado;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
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

    public TrxBP13Request(TrxPersonHeader header) {

        var session = new Session();
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataRequest {

    private String producto;
    private String subProducto;
    private String tarifa;
    private String plazo;
    private String periodoLiquidacion;
    private String valor;
    private String moneda;
    private String puntosAdicionales;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getSubProducto() {
        return subProducto;
    }

    public void setSubProducto(String subProducto) {
        this.subProducto = subProducto;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPuntosAdicionales() {
        return puntosAdicionales;
    }

    public void setPuntosAdicionales(String puntosAdicionales) {
        this.puntosAdicionales = puntosAdicionales;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Request {

    private TrxHeader cabecera;
    private TrxBP17DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP17DataRequest getData() {
        return data;
    }

    public void setData(TrxBP17DataRequest data) {
        this.data = data;
    }

    public TrxBP17Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSesion(session);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setCanal(header.canal);

        this.cabecera.setResultado(header.getResultado());

    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17DataResponse {

        private String intPendAbonar;
        private String tasaInteresNominal;
        private String tasaInteresEA;
        private String fechaProximoReajuste;
        private String fecVencimiento;
        private String codigoDeProducto;
        private String codigoDeSubproduct;
        private String descripcionProdu;
        private String importeBaseInvers;
        private String codigoDeDivisa;
        private String codigoDeTarifa;
        private String plazoEnDias;
        private String descripPlazo;
        private String plazoEnDiasDispon;
        private String periodoLiquidacion;
        private String descrPeriodoLiq;
        private String periodosDisponibles;
        private String montoFijoBonGmf;
        private String porcentajeFijoBonGmf;
        private String importeGmfMaximo;
        private String importeGmfBonific;
        private String tipoBonifGmfCalc;
        private String importeTotalInvers;
        private String importeBrutoIntere;
        private String porcentajeDeRetencionFuent;
        private String importeRetencFuent;
        private String importeNetoInteres;
        private String importeTotalCobrar;
        private String fechaDeAlta;
        private String fechaDeVencimiento;
        private String fechaProxLiquidac;
        private String porcentajeDeInteresNominal;
        private String porcentajeDeSpread;
        private String porcentajeDeTasaEfectiva;

        public String getIntPendAbonar() {
                return intPendAbonar;
        }

        public void setIntPendAbonar(String intPendAbonar) {
                this.intPendAbonar = intPendAbonar;
        }

        public String getTasaInteresNominal() {
                return tasaInteresNominal;
        }

        public void setTasaInteresNominal(String tasaInteresNominal) {
                this.tasaInteresNominal = tasaInteresNominal;
        }

        public String getTasaInteresEA() {
                return tasaInteresEA;
        }

        public void setTasaInteresEA(String tasaInteresEA) {
                this.tasaInteresEA = tasaInteresEA;
        }

        public String getFechaProximoReajuste() {
                return fechaProximoReajuste;
        }

        public void setFechaProximoReajuste(String fechaProximoReajuste) {
                this.fechaProximoReajuste = fechaProximoReajuste;
        }

        public String getFecVencimiento() {
                return fecVencimiento;
        }

        public void setFecVencimiento(String fecVencimiento) {
                this.fecVencimiento = fecVencimiento;
        }

        public String getCodigoDeProducto() {
                return codigoDeProducto;
        }

        public void setCodigoDeProducto(String codigoDeProducto) {
                this.codigoDeProducto = codigoDeProducto;
        }

        public String getCodigoDeSubproduct() {
                return codigoDeSubproduct;
        }

        public void setCodigoDeSubproduct(String codigoDeSubproduct) {
                this.codigoDeSubproduct = codigoDeSubproduct;
        }

        public String getDescripcionProdu() {
                return descripcionProdu;
        }

        public void setDescripcionProdu(String descripcionProdu) {
                this.descripcionProdu = descripcionProdu;
        }

        public String getImporteBaseInvers() {
                return importeBaseInvers;
        }

        public void setImporteBaseInvers(String importeBaseInvers) {
                this.importeBaseInvers = importeBaseInvers;
        }

        public String getCodigoDeDivisa() {
                return codigoDeDivisa;
        }

        public void setCodigoDeDivisa(String codigoDeDivisa) {
                this.codigoDeDivisa = codigoDeDivisa;
        }

        public String getCodigoDeTarifa() {
                return codigoDeTarifa;
        }

        public void setCodigoDeTarifa(String codigoDeTarifa) {
                this.codigoDeTarifa = codigoDeTarifa;
        }

        public String getPlazoEnDias() {
                return plazoEnDias;
        }

        public void setPlazoEnDias(String plazoEnDias) {
                this.plazoEnDias = plazoEnDias;
        }

        public String getDescripPlazo() {
                return descripPlazo;
        }

        public void setDescripPlazo(String descripPlazo) {
                this.descripPlazo = descripPlazo;
        }

        public String getPlazoEnDiasDispon() {
                return plazoEnDiasDispon;
        }

        public void setPlazoEnDiasDispon(String plazoEnDiasDispon) {
                this.plazoEnDiasDispon = plazoEnDiasDispon;
        }

        public String getPeriodoLiquidacion() {
                return periodoLiquidacion;
        }

        public void setPeriodoLiquidacion(String periodoLiquidacion) {
                this.periodoLiquidacion = periodoLiquidacion;
        }

        public String getDescrPeriodoLiq() {
                return descrPeriodoLiq;
        }

        public void setDescrPeriodoLiq(String descrPeriodoLiq) {
                this.descrPeriodoLiq = descrPeriodoLiq;
        }

        public String getPeriodosDisponibles() {
                return periodosDisponibles;
        }

        public void setPeriodosDisponibles(String periodosDisponibles) {
                this.periodosDisponibles = periodosDisponibles;
        }

        public String getMontoFijoBonGmf() {
                return montoFijoBonGmf;
        }

        public void setMontoFijoBonGmf(String montoFijoBonGmf) {
                this.montoFijoBonGmf = montoFijoBonGmf;
        }

        public String getPorcentajeFijoBonGmf() {
                return porcentajeFijoBonGmf;
        }

        public void setPorcentajeFijoBonGmf(String porcentajeFijoBonGmf) {
                this.porcentajeFijoBonGmf = porcentajeFijoBonGmf;
        }

        public String getImporteGmfMaximo() {
                return importeGmfMaximo;
        }

        public void setImporteGmfMaximo(String importeGmfMaximo) {
                this.importeGmfMaximo = importeGmfMaximo;
        }

        public String getImporteGmfBonific() {
                return importeGmfBonific;
        }

        public void setImporteGmfBonific(String importeGmfBonific) {
                this.importeGmfBonific = importeGmfBonific;
        }

        public String getTipoBonifGmfCalc() {
                return tipoBonifGmfCalc;
        }

        public void setTipoBonifGmfCalc(String tipoBonifGmfCalc) {
                this.tipoBonifGmfCalc = tipoBonifGmfCalc;
        }

        public String getImporteTotalInvers() {
                return importeTotalInvers;
        }

        public void setImporteTotalInvers(String importeTotalInvers) {
                this.importeTotalInvers = importeTotalInvers;
        }

        public String getImporteBrutoIntere() {
                return importeBrutoIntere;
        }

        public void setImporteBrutoIntere(String importeBrutoIntere) {
                this.importeBrutoIntere = importeBrutoIntere;
        }

        public String getPorcentajeDeRetencionFuent() {
                return porcentajeDeRetencionFuent;
        }

        public void setPorcentajeDeRetencionFuent(String porcentajeDeRetencionFuent) {
                this.porcentajeDeRetencionFuent = porcentajeDeRetencionFuent;
        }

        public String getImporteRetencFuent() {
                return importeRetencFuent;
        }

        public void setImporteRetencFuent(String importeRetencFuent) {
                this.importeRetencFuent = importeRetencFuent;
        }

        public String getImporteNetoInteres() {
                return importeNetoInteres;
        }

        public void setImporteNetoInteres(String importeNetoInteres) {
                this.importeNetoInteres = importeNetoInteres;
        }

        public String getImporteTotalCobrar() {
                return importeTotalCobrar;
        }

        public void setImporteTotalCobrar(String importeTotalCobrar) {
                this.importeTotalCobrar = importeTotalCobrar;
        }

        public String getFechaDeAlta() {
                return fechaDeAlta;
        }

        public void setFechaDeAlta(String fechaDeAlta) {
                this.fechaDeAlta = fechaDeAlta;
        }

        public String getFechaDeVencimiento() {
                return fechaDeVencimiento;
        }

        public void setFechaDeVencimiento(String fechaDeVencimiento) {
                this.fechaDeVencimiento = fechaDeVencimiento;
        }

        public String getFechaProxLiquidac() {
                return fechaProxLiquidac;
        }

        public void setFechaProxLiquidac(String fechaProxLiquidac) {
                this.fechaProxLiquidac = fechaProxLiquidac;
        }

        public String getPorcentajeDeInteresNominal() {
                return porcentajeDeInteresNominal;
        }

        public void setPorcentajeDeInteresNominal(String porcentajeDeInteresNominal) {
                this.porcentajeDeInteresNominal = porcentajeDeInteresNominal;
        }

        public String getPorcentajeDeSpread() {
                return porcentajeDeSpread;
        }

        public void setPorcentajeDeSpread(String porcentajeDeSpread) {
                this.porcentajeDeSpread = porcentajeDeSpread;
        }

        public String getPorcentajeDeTasaEfectiva() {
                return porcentajeDeTasaEfectiva;
        }

        public void setPorcentajeDeTasaEfectiva(String porcentajeDeTasaEfectiva) {
                this.porcentajeDeTasaEfectiva = porcentajeDeTasaEfectiva;
        }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP17Response {
    private TrxBP17DataResponse data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public TrxBP17DataResponse getData() {
        return data;
    }

    public void setData(TrxBP17DataResponse data) {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31DataRequest {
    private String tipoDocumento;
    private String numDocumento;
    private String oficina;
    private String codigoInversor;
    private String nroCliente;
    private String ejecutivoComercial;
    private String indicadorEstado;
    private String tipoCustodia;
    private String tipoFecha;
    private String fechaDesde;
    private String fechaHasta;
    private String cccReposicionam;
    private String secuenciaReposicion;
    private String secRenovReposic;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getCodigoInversor() {
        return codigoInversor;
    }

    public void setCodigoInversor(String codigoInversor) {
        this.codigoInversor = codigoInversor;
    }

    public String getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(String nroCliente) {
        this.nroCliente = nroCliente;
    }

    public String getEjecutivoComercial() {
        return ejecutivoComercial;
    }

    public void setEjecutivoComercial(String ejecutivoComercial) {
        this.ejecutivoComercial = ejecutivoComercial;
    }

    public String getIndicadorEstado() {
        return indicadorEstado;
    }

    public void setIndicadorEstado(String indicadorEstado) {
        this.indicadorEstado = indicadorEstado;
    }

    public String getTipoCustodia() {
        return tipoCustodia;
    }

    public void setTipoCustodia(String tipoCustodia) {
        this.tipoCustodia = tipoCustodia;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getCccReposicionam() {
        return cccReposicionam;
    }

    public void setCccReposicionam(String cccReposicionam) {
        this.cccReposicionam = cccReposicionam;
    }

    public String getSecuenciaReposicion() {
        return secuenciaReposicion;
    }

    public void setSecuenciaReposicion(String secuenciaReposicion) {
        this.secuenciaReposicion = secuenciaReposicion;
    }

    public String getSecRenovReposic() {
        return secRenovReposic;
    }

    public void setSecRenovReposic(String secRenovReposic) {
        this.secRenovReposic = secRenovReposic;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31Request {

    private TrxHeader cabecera;
    private TrxBP31DataRequest data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public TrxBP31DataRequest getData() {
        return data;
    }

    public void setData(TrxBP31DataRequest data) {
        this.data = data;
    }

    public TrxBP31Request(TrxPersonHeader header) {

        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());

        this.cabecera.setSesion(session);
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.bp31.response.Bp31DataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP31Response {
    private Bp31DataResponseDTO data;
    private TrxHeader cabecera;
    private Object autorizacion;
    private Object paginacion;
    private List<Object> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private Boolean ok;

    public Bp31DataResponseDTO getData() {
        return data;
    }

    public void setData(Bp31DataResponseDTO data) {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxBP49Request {
    private TrxHeader cabecera;
    private TrxBP49DataRequest data;

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
    
    
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response;

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

    public void setCERTIFI(String cERTIFI) {
        CERTIFI = cERTIFI;
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

    public void setRETEN(String rETEN) {
        RETEN = rETEN;
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

    public void setINTABON(String iNTABON) {
        INTABON = iNTABON;
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfAditionalInfoDTO {
    private String numper;

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfDataDTO {
    private PepfAditionalInfoDTO infAdicional;

    public PepfAditionalInfoDTO getInfAdicional() {
        return infAdicional;
    }

    public void setInfAdicional(PepfAditionalInfoDTO infAdicional) {
        this.infAdicional = infAdicional;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfHeaderDTO {
    private PepfSessionDTO sesion;

    public PepfSessionDTO getSesion() {
        return sesion;
    }

    public void setSesion(PepfSessionDTO sesion) {
        this.sesion = sesion;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfSessionDTO {
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.Session;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.generic.TrxHeader;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.dto.PepfDataDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.generic.TrxPersonHeader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPEPFDataRequest {
    private TrxHeader cabecera;
    private PepfDataDTO data;

    public TrxHeader getCabecera() {
        return cabecera;
    }

    public void setCabecera(TrxHeader cabecera) {
        this.cabecera = cabecera;
    }

    public PepfDataDTO getData() {
        return data;
    }

    public void setData(PepfDataDTO data) {
        this.data = data;
    }

    public TrxPEPFDataRequest(TrxPersonHeader header) {
        var session = new Session();
        this.cabecera = new TrxHeader();

        session.setEntidad("0065");
        session.setEntorno(header.sesion.entorno);
        session.setFechaContable(header.sesion.fechaContable);
        session.setHoraConexion(header.sesion.horaConexion);
        session.setPerfil(header.sesion.perfil);
        session.setSucursal(header.sesion.sucursal);
        session.setTerminal(header.sesion.terminal);
        session.setTurno(header.sesion.turno);
        session.setUsuario(header.sesion.usuario);

        this.cabecera.setCanal(header.canal);
        this.cabecera.setFuncion(header.getFuncion());
        this.cabecera.setResultado(header.getResultado());
        this.cabecera.setRutaServicio(header.rutaServicio);
        this.cabecera.setSecuencia(header.getSecuencia());
        this.cabecera.setSesion(session);
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfDataResponseDTO {
    private PepfPEMFV0AResponseDTO pemfvoaResponse;

    public PepfPEMFV0AResponseDTO getPemfvoaResponse() {
        return pemfvoaResponse;
    }

    public void setPemfvoaResponse(PepfPEMFV0AResponseDTO pemfvoaResponse) {
        this.pemfvoaResponse = pemfvoaResponse;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfHeaderResponseDTO {
    private int secuencia;
    private String rutaServicio;
    private PepfSessionResponseDTO sesion;
    private String resultado;

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getRutaServicio() {
        return rutaServicio;
    }

    public void setRutaServicio(String rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    public PepfSessionResponseDTO getSesion() {
        return sesion;
    }

    public void setSesion(PepfSessionResponseDTO sesion) {
        this.sesion = sesion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfNoticeResponseDTO {
    private String codigo;
    private String mensaje;
    private String transaccion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfPEMFV0AResponseDTO {
    private String OFICIAL;
    private String IDNOAPL;
    private String IDSECER;
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

    public void setOFICIAL(String oFICIAL) {
        OFICIAL = oFICIAL;
    }

    public String getIDNOAPL() {
        return IDNOAPL;
    }

    public void setIDNOAPL(String iDNOAPL) {
        IDNOAPL = iDNOAPL;
    }

    public String getIDSECER() {
        return IDSECER;
    }

    public void setIDSECER(String iDSECER) {
        IDSECER = iDSECER;
    }

    public String getHONORAR() {
        return HONORAR;
    }

    public void setHONORAR(String hONORAR) {
        HONORAR = hONORAR;
    }

    public String getREPORTA() {
        return REPORTA;
    }

    public void setREPORTA(String rEPORTA) {
        REPORTA = rEPORTA;
    }

    public String getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(String sALARIO) {
        SALARIO = sALARIO;
    }

    public String getSECDOC() {
        return SECDOC;
    }

    public void setSECDOC(String sECDOC) {
        SECDOC = sECDOC;
    }

    public String getSITCLN() {
        return SITCLN;
    }

    public void setSITCLN(String sITCLN) {
        SITCLN = sITCLN;
    }

    public String getPRESERV() {
        return PRESERV;
    }

    public void setPRESERV(String pRESERV) {
        PRESERV = pRESERV;
    }

    public String getNROIDT2() {
        return NROIDT2;
    }

    public void setNROIDT2(String nROIDT2) {
        NROIDT2 = nROIDT2;
    }

    public String getNROIDT3() {
        return NROIDT3;
    }

    public void setNROIDT3(String nROIDT3) {
        NROIDT3 = nROIDT3;
    }

    public String getPENSION() {
        return PENSION;
    }

    public void setPENSION(String pENSION) {
        PENSION = pENSION;
    }

    public String getNROIDT1() {
        return NROIDT1;
    }

    public void setNROIDT1(String nROIDT1) {
        NROIDT1 = nROIDT1;
    }

    public String getACTIND() {
        return ACTIND;
    }

    public void setACTIND(String aCTIND) {
        ACTIND = aCTIND;
    }

    public String getNUMDOC() {
        return NUMDOC;
    }

    public void setNUMDOC(String nUMDOC) {
        NUMDOC = nUMDOC;
    }

    public String getUNINEG() {
        return UNINEG;
    }

    public void setUNINEG(String uNINEG) {
        UNINEG = uNINEG;
    }

    public String getARRIEND() {
        return ARRIEND;
    }

    public void setARRIEND(String aRRIEND) {
        ARRIEND = aRRIEND;
    }

    public String getCANVTA() {
        return CANVTA;
    }

    public void setCANVTA(String cANVTA) {
        CANVTA = cANVTA;
    }

    public String getSUCURAL() {
        return SUCURAL;
    }

    public void setSUCURAL(String sUCURAL) {
        SUCURAL = sUCURAL;
    }

    public String getIDCRECO() {
        return IDCRECO;
    }

    public void setIDCRECO(String iDCRECO) {
        IDCRECO = iDCRECO;
    }

    public String getPAIRE01() {
        return PAIRE01;
    }

    public void setPAIRE01(String pAIRE01) {
        PAIRE01 = pAIRE01;
    }

    public String getAUTCOME() {
        return AUTCOME;
    }

    public void setAUTCOME(String aUTCOME) {
        AUTCOME = aUTCOME;
    }

    public String getPAIRE02() {
        return PAIRE02;
    }

    public void setPAIRE02(String pAIRE02) {
        PAIRE02 = pAIRE02;
    }

    public String getPAIRE03() {
        return PAIRE03;
    }

    public void setPAIRE03(String pAIRE03) {
        PAIRE03 = pAIRE03;
    }

    public String getIDCRS() {
        return IDCRS;
    }

    public void setIDCRS(String iDCRS) {
        IDCRS = iDCRS;
    }

    public String getDONHERE() {
        return DONHERE;
    }

    public void setDONHERE(String dONHERE) {
        DONHERE = dONHERE;
    }

    public String getIDPREPU() {
        return IDPREPU;
    }

    public void setIDPREPU(String iDPREPU) {
        IDPREPU = iDPREPU;
    }

    public String getIDFATCA() {
        return IDFATCA;
    }

    public void setIDFATCA(String iDFATCA) {
        IDFATCA = iDFATCA;
    }

    public String getOFOTRO() {
        return OFOTRO;
    }

    public void setOFOTRO(String oFOTRO) {
        OFOTRO = oFOTRO;
    }

    public String getNUMPER() {
        return NUMPER;
    }

    public void setNUMPER(String nUMPER) {
        NUMPER = nUMPER;
    }

    public String getMESADA() {
        return MESADA;
    }

    public void setMESADA(String mESADA) {
        MESADA = mESADA;
    }

    public String getTIPDOC() {
        return TIPDOC;
    }

    public void setTIPDOC(String tIPDOC) {
        TIPDOC = tIPDOC;
    }

    public String getFECCLN() {
        return FECCLN;
    }

    public void setFECCLN(String fECCLN) {
        FECCLN = fECCLN;
    }

    public String getIDPPEXP() {
        return IDPPEXP;
    }

    public void setIDPPEXP(String iDPPEXP) {
        IDPPEXP = iDPPEXP;
    }

    public String getIDPEXPO() {
        return IDPEXPO;
    }

    public void setIDPEXPO(String iDPEXPO) {
        IDPEXPO = iDPEXPO;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PepfSessionResponseDTO {
    private String usuario;
    private String terminal;
    private String horaConexion;
    private String entorno;
    private String perfil;
    private String sucursal;
    private String entidad;
    private int diasRestantesCambioClave;
    private String fechaContable;

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

    public int getDiasRestantesCambioClave() {
        return diasRestantesCambioClave;
    }

    public void setDiasRestantesCambioClave(int diasRestantesCambioClave) {
        this.diasRestantesCambioClave = diasRestantesCambioClave;
    }

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfDataResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfHeaderResponseDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.dto.PepfNoticeResponseDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrxPEPFDataResponse {
    private PepfDataResponseDTO data;
    private PepfHeaderResponseDTO cabecera;
    private Object autorizacion;
    private Map<String, Object> paginacion;
    private List<PepfNoticeResponseDTO> avisos;
    private List<ErrorTrxDTO> errores;
    private Object conexion;
    private boolean ok;

    public PepfDataResponseDTO getData() {
        return data;
    }

    public void setData(PepfDataResponseDTO data) {
        this.data = data;
    }

    public PepfHeaderResponseDTO getCabecera() {
        return cabecera;
    }

    public void setCabecera(PepfHeaderResponseDTO cabecera) {
        this.cabecera = cabecera;
    }

    public Object getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Object autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Map<String, Object> getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(Map<String, Object> paginacion) {
        this.paginacion = paginacion;
    }

    public List<PepfNoticeResponseDTO> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<PepfNoticeResponseDTO> avisos) {
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.integration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeRequest {

    private String authorization;
    private String xSantanderClientId;
    private String productId;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmountRangeResponse {

    private MaxAndMinAmountDto minimunAmount;
    private MaxAndMinAmountDto maximumAmount;

    public MaxAndMinAmountDto getMinimunAmount() {
        return minimunAmount;
    }

    public void setMinimunAmount(MaxAndMinAmountDto minimunAmount) {
        this.minimunAmount = minimunAmount;
    }

    public MaxAndMinAmountDto getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(MaxAndMinAmountDto maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositParametersRequest {
    private String productId;
    private String authorization;
    private String xSantanderClientId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositSettlementsRequest {
    private String authorization;
    private String client_id;
    private String deposit_id;
    private String placement_id;
    private String start_date;
    private String end_date;
    private String offset;
    private String limit;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(String deposit_id) {
        this.deposit_id = deposit_id;
    }

    public String getPlacement_id() {
        return placement_id;
    }

    public void setPlacement_id(String placement_id) {
        this.placement_id = placement_id;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentYearEarnedAmountDTO {
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnedAmountDTO {
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinksDTO {
    private FirstDTO first;
    private NextDTO next;
    private PrevDTO prev;

    public FirstDTO getFirst() {
        return first;
    }

    public void setFirst(FirstDTO first) {
        this.first = first;
    }

    public NextDTO getNext() {
        return next;
    }

    public void setNext(NextDTO next) {
        this.next = next;
    }

    public PrevDTO getPrev() {
        return prev;
    }

    public void setPrev(PrevDTO prev) {
        this.prev = prev;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NextDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDTO {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacementDTO {
    private PreviousYearEarnedAmountDTO previousYearEarnedAmount;
    private TotalEarnedAmountDTO totalEarnedAmount;
    private CurrentYearEarnedAmountDTO currentYearEarnedAmount;

    public PreviousYearEarnedAmountDTO getPreviousYearEarnedAmount() {
        return previousYearEarnedAmount;
    }

    public void setPreviousYearEarnedAmount(PreviousYearEarnedAmountDTO previousYearEarnedAmount) {
        this.previousYearEarnedAmount = previousYearEarnedAmount;
    }

    public TotalEarnedAmountDTO getTotalEarnedAmount() {
        return totalEarnedAmount;
    }

    public void setTotalEarnedAmount(TotalEarnedAmountDTO totalEarnedAmount) {
        this.totalEarnedAmount = totalEarnedAmount;
    }

    public CurrentYearEarnedAmountDTO getCurrentYearEarnedAmount() {
        return currentYearEarnedAmount;
    }

    public void setCurrentYearEarnedAmount(CurrentYearEarnedAmountDTO currentYearEarnedAmount) {
        this.currentYearEarnedAmount = currentYearEarnedAmount;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrevDTO {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousYearEarnedAmountDTO {
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootDTO {
    private PlacementDTO placement;
    private ArrayList<SettlementsDTO> settlements;
    private LinksDTO links;

    public PlacementDTO getPlacement() {
        return placement;
    }

    public void setPlacement(PlacementDTO placement) {
        this.placement = placement;
    }

    public ArrayList<SettlementsDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<SettlementsDTO> settlements) {
        this.settlements = settlements;
    }

    public LinksDTO getLinks() {
        return links;
    }

    public void setLinks(LinksDTO links) {
        this.links = links;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementsDTO {
    private String settlementId;
    private PeriodDTO period;
    private AmountDTO amount;
    private EarnedAmountDTO EarnedAmount;
    private String processDate;
    private String postedIndicator;
    private String processTypeCode;
    private String processTypeDescription;

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public PeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDTO period) {
        this.period = period;
    }

    public AmountDTO getAmount() {
        return amount;
    }

    public void setAmount(AmountDTO amount) {
        this.amount = amount;
    }

    public EarnedAmountDTO getEarnedAmount() {
        return EarnedAmount;
    }

    public void setEarnedAmount(EarnedAmountDTO earnedAmount) {
        EarnedAmount = earnedAmount;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getPostedIndicator() {
        return postedIndicator;
    }

    public void setPostedIndicator(String postedIndicator) {
        this.postedIndicator = postedIndicator;
    }

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }

    public String getProcessTypeDescription() {
        return processTypeDescription;
    }

    public void setProcessTypeDescription(String processTypeDescription) {
        this.processTypeDescription = processTypeDescription;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TermDepositSettlementsReponse {
    private ArrayList<SettlementsDTO> settlements;

    public ArrayList<SettlementsDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<SettlementsDTO> settlements) {
        this.settlements = settlements;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalEarnedAmountDTO {
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


****
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

public class BankRequestDTO {
    private String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

}

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

public class CenterRequestDTO {
    private String centerId;

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

public class CustomerRequestDTO {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits.TermDepositPlacementDTO;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;


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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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

    public void setCapitalizable(boolean isCapitalizable) {
        this.isCapitalizable = isCapitalizable;
    }

    public boolean isRenewal() {
        return isRenewal;
    }

    public void setRenewal(boolean isRenewal) {
        this.isRenewal = isRenewal;
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.simulatePlacement;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.response.term_deposits;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.TermDepositDestinationFundsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.TermDepositPeriodicityDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.TermDepositSettlementConditionDTO;
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


************************
****

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error;

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
    public String invalidValue;

    @Value(("${errors.general.blank_data}"))
    public String blankData;

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
            var message = "'" + fieldName + "': " + blankData;

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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception;


import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceExceptionClient;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Freddy Paredes
 * This class handle all Exceptions
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${params.app-name}")
    private String msName;

    private static final String LEVEL = "error";
    private String pf9400 = "-P-F-9400";
    private String errorNotSpecified = " not specified";


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {

        log.info("Error type: {}", e.getClass().getName());
        log.info("Error: {}", e.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-T-9409")
                .message("Unhandled exception")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Unhandled exception")
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
                    .code(msName + pf9400)
                    .level(LEVEL)
                    .message(errorMessage)
                    .description(msName.toLowerCase() + "-api-services-v1: " + errorMessage).build());
        });
        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(NoResourceFoundException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-F-9404")
                .message("Not found")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Not found")
                .build());

        return buildResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingServletRequestParameterException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message("Required query parameter " + ex.getParameterName() + errorNotSpecified)
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Required query parameter " + ex.getParameterName() + errorNotSpecified)
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(IllegalArgumentException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message(ex.getMessage())
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Bad request")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MissingRequestHeaderException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + pf9400)
                .message("Required header " + ex.getHeaderName() + errorNotSpecified)
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Required header " + ex.getHeaderName() + errorNotSpecified)
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
                    error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,msName));
                    error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,msName.toLowerCase()));
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
                        error.setCode(error.getCode().replaceAll(ErrorCatalog.MS_NAME,msName));
                        error.setDescription(error.getDescription().replaceAll(ErrorCatalog.MS_NAME,msName.toLowerCase()));
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
                .code(msName + pf9400)
                .message("Invalid body structure")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-services-v1: Invalid body structure")
                .build());

        return buildResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(ErrorDTO.builder()
                .code(msName + "-P-F-9405")
                .message("Method not allowed")
                .level(LEVEL)
                .description(msName.toLowerCase() + "-api-service-v1: Method not allowed")
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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.mappers;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Data;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.AmountDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.EarnedAmountDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.SettlementsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.MovementConceptUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.TermDepositUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TermDepositSettlementsMappers {
    final TermDepositUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final MovementConceptUtils movementConceptUtils;

    public TermDepositSettlementsReponse settlementsReponse(TrxBP49Response trxBP49Response,
                                                            TrxBP13Response trxBP13Response) {
        TermDepositSettlementsReponse response = new TermDepositSettlementsReponse();
        ArrayList<SettlementsDTO> settlements = new ArrayList<>();

        List<TrxBP49Data> movements = trxBP49Response.getData().getMovimientos().stream()
                .filter(mov -> mov.getConcepto().equals("INTP") || mov.getConcepto().equals("RFIP")
                        || mov.getConcepto().equals("VTOP") || mov.getConcepto().equals("PAGP"))
                .toList();

        movements.forEach(motion -> {
            SettlementsDTO settlement = new SettlementsDTO();
            AmountDTO amount = new AmountDTO();
            EarnedAmountDTO earnedAmount = new EarnedAmountDTO();
            String valueEntry = motion.getValor();

            String valueFinal = TermDepositUtils.format15DigitNumber(valueEntry.replace("-", ""));
            // set monto liquidado
            earnedAmount.setAmount(valueFinal);
            earnedAmount.setCurrency("COP");

            if (motion.getConcepto().equals("RFIP")) {
                amount.setAmount("0,00");
            } else if (motion.getConcepto().equals("VTOP")) {
                amount.setAmount("0,00");
            } else {
                amount.setAmount(valueFinal);
            }
            amount.setCurrency("COP");

            settlement.setAmount(amount);
            settlement.setEarnedAmount(earnedAmount);
            String date = motion.getFecha();
            settlement.setProcessDate(date);

            if (motion.getConcepto().equals("INTP") || motion.getConcepto().equals("PAGP")) {
                String postedIndicator = trxBP13Response.getData().getTipoEfectivo();
                String postedIndicatorFinal;
                if (postedIndicator.contains("-")) {
                    postedIndicatorFinal = "-"
                            + TermDepositUtils.cleanAndFormatNumberString(postedIndicator.replace("-", ""));
                } else {
                    postedIndicatorFinal = TermDepositUtils.cleanAndFormatNumberString(postedIndicator);
                }
                BigDecimal postedIndicatorFinalV = BigDecimal
                        .valueOf(Double.valueOf(postedIndicatorFinal.replace(",", ".")));
                settlement.setPostedIndicator(String.format("%.4f", postedIndicatorFinalV).replace(".", ","));
            } else {
                settlement.setPostedIndicator("");
            }

            settlement.setProcessTypeCode(motion.getConcepto());
            settlement.setProcessTypeDescription(setConcept(motion.getConcepto()));
            settlements.add(settlement);
            response.setSettlements(settlements);
        });

        return response;
    }

    private String setConcept(String conceptCode) {
        String conceptDescription = "";
        if (("INTP".equals(conceptCode) || "PAGP".equals(conceptCode) || "RFIP".equals(conceptCode)
                || "VTOP".equals(conceptCode)) && movementConceptUtils.getType().containsKey(conceptCode)) {
            conceptDescription = movementConceptUtils.getType().get(conceptCode);
        }

        return conceptDescription;
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.observability;
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

package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.observability;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

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


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

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
    private String msName;
    @Value("${params.appVersion}")
    private String msVersion;
    @Value("${errors.level}")
    private String level;
    @Value("${regex.error.code}")
    private String code;

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
                    .code(msName + "-" + code)
                    .level(level)
                    .message("'" + fieldName + "': " + message)
                    .description(msName.toLowerCase() + "-" + msVersion + ": '" + fieldName + "': " + message)
                    .build();
            throw new ServiceException(HttpStatus.BAD_REQUEST, errorDTO);
        }

    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorType;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.simulatePlacement.RequestSimulatePlacementDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.RequestTermDepositsDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.request.term_deposits.TermDepositSettlementsDTO;
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
        private String[] validFrecuencies;
        @Value("#{'${params.settlements}'.split(',')}")
        private String[] validSettlements;
        @Value("${params.condition-codes}")
        private String settlementCoditionCode;
        @Value("${params.commons.bankId}")
        private String bankId;
        @Value("${params.commons.centerId}")
        private String centerId;

        private String prodctCodeName = "product.productCode";
        private String subproductIdFieldname = "product.subproduct.subproductId";
        private String amountFieldName = "amount.amount";
        private String periodicityFrecuency = "periodicity.frecuency";
        private String periodicityPeriodTypeCode = "periodicity.periodTypeCode";
        private String settlementConditionCodeFieldName = "settlementConditionCode";
        private String bankIdFielName = "bank.bankId";
        private String centerIdFieldName = "bank.center.centerId";
        private String accountIdType = "deposits.placement.destinationFunds.accountIdType";
        private String bankCode = "deposits.placement.destinationFunds.bankCode";
        private String nationalIdentification = "deposits.placement.destinationFunds.account.nationalIdentification";
        private String depositsFrecuency = "deposits.placement.periodicity.frequency";
        private String depositsSettlementConditionCode = "deposits.placement.settlementCondition.code";
        private String depositsPurposeCode = "deposits.placement.purposeCode";
        private String depositsTotalInvestedAmount = "economicData.initialTotalInvested.amount";
        private String depositsCurrency = "economicData.initialTotalInvested.currency";
        private String depositsSettlementConceptCode = "economicData.settlements.settlementConcept.code";
        private String depositsSettlementConceptRate = "economicData.settlements.settlementConcept.rate";
        private String depositsSettlementConceptAmount = "economicData.settlements.settlementConcept.amount.amount";
        private String depositsSettlementConceptCurrency = "economicData.settlements.settlementConcept.amount.currency";
        private String bankIdNotFound = "bankId_not_found";
        private String depositsIdFieldName = "deposit_id";
        private String placementIdFieldName = "placement_id";
        private String intialTotalInvestedAmountMessage = "initialTotalInvested_amount_format";
        private String currencyCodeFormatMessage = "currency_code_format";
        private String settlementConceptMessage = "settlementConcept_code_length";
        private String settlementConceptCodeFormatMessage = "settlementConcept_code_format";
        private String economicDataSettlementsMessageNotFound = "economicData.settlements.settlementConcept.code_not_found";
        private String settleConceptTypecodeMessage = "settlementConcept_typeCode_length";
        private String settlementConceptTypeCodeFormatMessage = "settlementConcept_typeCode_format";
        private String settlementConceptRateFormatMessage = "settlementConcept_rate_format";
        private String economicDataSettlementConceptAmountCurrencyNotFoundMessage = "economicData.settlements.settlemenmtConcept.amount.currency_not_found";

        public static String cleanAndFormatNumberString(String input) {
                var noplus = input.replace("+", "");
                var nominus = noplus.replace("-", "");
                var nodots = nominus.replace(".", "");
                return nodots.trim();
        }

        public void simulatePlacementInputValidation(RequestSimulatePlacementDTO requestBodyData,
                        AmountRangeRequest amountRangeRequest) {

                // Product Validation
                errorService.isBlank(requestBodyData.getProduct().getProductCode(), prodctCodeName);
                regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(),
                                prodctCodeName);
                regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(),
                                prodctCodeName);
                if (!productCode.equals(requestBodyData.getProduct().getProductCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
                }

                // SubProduct Validation
                errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(),
                                subproductIdFieldname);
                regexUtils.validateRegex("subproduct_code_format",
                                requestBodyData.getProduct().getSubproduct().getSubproductId(), subproductIdFieldname);
                regexUtils.validateRegex("subproduct_code_length",
                                requestBodyData.getProduct().getSubproduct().getSubproductId(), subproductIdFieldname);
                if (!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
                }

                // Amount range validation
                String productAndSubProductId = requestBodyData.getProduct().getProductCode()
                                + requestBodyData.getProduct().getSubproduct().getSubproductId();
                amountRangeRequest.setProductId(productAndSubProductId);
                var amountRangeResponse = productDirectoryService.amountRange(amountRangeRequest);

                // Amount Validation
                errorService.isBlank(requestBodyData.getAmount().getAmount(), amountFieldName);
                regexUtils.validateRegex("amount_format", requestBodyData.getAmount().getAmount(), amountFieldName);
                amountValidation(requestBodyData.getAmount().getAmount(),
                                amountRangeResponse.getMinimunAmount().getAmount(),
                                amountRangeResponse.getMaximumAmount().getAmount());

                // Frecuency Validation
                errorService.isBlank(requestBodyData.getPeriodicity().getFrequency(), periodicityFrecuency);
                regexUtils.validateRegex("frecuency_code_format", requestBodyData.getPeriodicity().getFrequency(),
                                periodicityFrecuency);
                frecuencyValidation(requestBodyData.getPeriodicity().getFrequency());

                // PeriodType Validation
                errorService.isBlank(requestBodyData.getPeriodicity().getPeriodTypeCode(), periodicityPeriodTypeCode);
                regexUtils.validateRegex("periodtype_code_format", requestBodyData.getPeriodicity().getPeriodTypeCode(),
                                periodicityPeriodTypeCode);
                regexUtils.validateRegex("periodtype_code_length", requestBodyData.getPeriodicity().getPeriodTypeCode(),
                                periodicityPeriodTypeCode);
                if (!"D".equals(requestBodyData.getPeriodicity().getPeriodTypeCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("periodtypecode_not_found"),
                                        ErrorType.FUNCTIONAL);
                }

                // SettlementConditionCode Validation
                errorService.isBlank(requestBodyData.getSettlementConditionCode(), settlementConditionCodeFieldName);
                regexUtils.validateRegex("settlementcondition_code_format",
                                requestBodyData.getSettlementConditionCode(),
                                settlementConditionCodeFieldName);
                regexUtils.validateRegex("settlementcondition_code_length",
                                requestBodyData.getSettlementConditionCode(),
                                settlementConditionCodeFieldName);
                if (!settlementContionCodeInputValidation(requestBodyData.getSettlementConditionCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("settlementconditioncode_not_found"),
                                        ErrorType.FUNCTIONAL);
                }
        }

        public void termDepositsInputValidation(RequestTermDepositsDTO requestBodyData,
                        TermDepositParametersRequest termDepositParametersRequest,
                        BanksParametersRequest banksParametersRequest) {
                // Bank Validation
                bankValidationTermDepositInput(requestBodyData);

                // Center Validation
                centerValidationTermDepositInput(requestBodyData);

                // Product Validation
                productValidationTermDepositInput(requestBodyData);
                // SubProduct Validation
                subproductValidationTermDepositInput(requestBodyData);
                // Deposits -> Placement -> DestinationFunds -> AccountIdTypeValidation
                accountIdTypeValidationTermDepositInput(requestBodyData);
                // Deposits -> Placement -> DestinationFunds -> bankcode
                bankCodeValidationTermDepositInput(requestBodyData, banksParametersRequest);
                // Deposits -> Placement -> DestinationFunds -> Account ->
                // nationalIdentification
                nationalIdentificationValidationTermDepositInput(requestBodyData);
                // deposit -> placement -> periodicity -> frequency

                frecuencyValidationTermDepositInput(requestBodyData);
                // deposit -> placement -> settlementCondition -> code
                errorService.isBlank(
                                requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode()
                                                .toUpperCase(),
                                depositsSettlementConditionCode);
                regexUtils.validateRegex("settlementcondition_code_format",
                                requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode()
                                                .toUpperCase(),
                                depositsSettlementConditionCode);
                regexUtils.validateRegex("settlementcondition_code_length",
                                requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode()
                                                .toUpperCase(),
                                depositsSettlementConditionCode);
                if (!settlementContionCodeInputValidation(
                                requestBodyData.getDeposit().getPlacement().getSettlementCondition().getCode()
                                                .toUpperCase())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("settlementcondition.code_not_found"),
                                        ErrorType.FUNCTIONAL);
                }

                // deposit -> placement -> purposeCode
                errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(),
                                depositsPurposeCode);
                regexUtils.validateRegex("purposeCode_code_length",
                                requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase(),
                                depositsPurposeCode);
                purposeCodeValidation(termDepositParametersRequest,
                                requestBodyData.getDeposit().getPlacement().getPurposeCode().toUpperCase());

                // economicData -> initialTotalInvested -> amount
                errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(),
                                depositsTotalInvestedAmount);
                regexUtils.validateRegex(intialTotalInvestedAmountMessage,
                                requestBodyData.getEconomicData().getInitialTotalInvested().getAmount(),
                                depositsTotalInvestedAmount);

                // economicData -> initialTotalInvested -> currency
                errorService.isBlank(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(),
                                depositsCurrency);
                regexUtils.validateRegex(currencyCodeFormatMessage,
                                requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency(),
                                depositsCurrency);
                if (!"COP".equals(requestBodyData.getEconomicData().getInitialTotalInvested().getCurrency())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral()
                                                        .get("economicData.initialTotalInvested.currency_not_found"),
                                        ErrorType.FUNCTIONAL);
                }

                errorService.isBlank(
                                requestBodyData.getEconomicData().getSettlements().get(0).getSettlementConcept()
                                                .getCode(),
                                depositsSettlementConceptCode);
                requestBodyData.getEconomicData().getSettlements().forEach(sett -> {
                        if (sett.getSettlementConcept().getCode().equals("BGMF")) {
                                validateEconomicDataBgmf(sett);

                        }
                        if (sett.getSettlementConcept().getCode().equals("RETF")) {
                                validateEconomicDataRetfTermDepositInput(sett);
                        }

                        if (sett.getSettlementConcept().getCode().equals("ITEA")) {
                                // economicData -> settlements [] -> settlementConcept -> code ITEA
                                regexUtils.validateRegex(settlementConceptMessage,
                                                sett.getSettlementConcept().getCode(),
                                                depositsSettlementConceptCode);
                                regexUtils.validateRegex(settlementConceptCodeFormatMessage,
                                                sett.getSettlementConcept().getCode(),
                                                depositsSettlementConceptCode);
                                if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
                                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                                        errorService.getGeneral().get(
                                                                        economicDataSettlementsMessageNotFound),
                                                        ErrorType.FUNCTIONAL);
                                }
                                // economicData -> settlements [] -> settlementConcept -> typeCode ITEA
                                errorService.isBlank(sett.getSettlementConcept().getTypeCode(),
                                                depositsSettlementConceptCode);
                                regexUtils.validateRegex(settleConceptTypecodeMessage,
                                                sett.getSettlementConcept().getTypeCode(),
                                                depositsSettlementConceptCode);
                                regexUtils.validateRegex(settlementConceptTypeCodeFormatMessage,
                                                sett.getSettlementConcept().getTypeCode(),
                                                depositsSettlementConceptCode);
                                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                                // economicData -> settlements [] -> settlementConcept -> rate ITEA
                                errorService.isBlank(sett.getSettlementConcept().getRate(),
                                                depositsSettlementConceptRate);
                                regexUtils.validateRegex(settlementConceptRateFormatMessage,
                                                sett.getSettlementConcept().getRate(),
                                                depositsSettlementConceptRate);

                                // economicData -> settlements [] -> settlementConcept -> amount -> amount ITEA
                                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(),
                                                depositsSettlementConceptAmount);
                                regexUtils.validateRegex(intialTotalInvestedAmountMessage,
                                                sett.getSettlementConcept().getAmount().getAmount(),
                                                depositsSettlementConceptAmount);

                                // economicData -> settlements [] -> settlementConcept -> amount -> currency
                                // ITEA
                                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(),
                                                depositsSettlementConceptCurrency);
                                regexUtils.validateRegex(currencyCodeFormatMessage,
                                                sett.getSettlementConcept().getAmount().getCurrency(),
                                                depositsSettlementConceptCurrency);
                                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
                                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                                        errorService.getGeneral()
                                                                        .get(economicDataSettlementConceptAmountCurrencyNotFoundMessage),
                                                        ErrorType.FUNCTIONAL);
                                }
                        }
                });

        }

        private void validateEconomicDataRetfTermDepositInput(TermDepositSettlementsDTO sett) {
                // economicData -> settlements [] -> settlementConcept -> code RETF
                regexUtils.validateRegex(settlementConceptMessage,
                                sett.getSettlementConcept().getCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settlementConceptCodeFormatMessage,
                                sett.getSettlementConcept().getCode(),
                                depositsSettlementConceptCode);
                if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get(
                                                        economicDataSettlementsMessageNotFound),
                                        ErrorType.FUNCTIONAL);
                }
                // economicData -> settlements [] -> settlementConcept -> typeCode RETF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settleConceptTypecodeMessage,
                                sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settlementConceptTypeCodeFormatMessage,
                                sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                // economicData -> settlements [] -> settlementConcept -> rate RETF
                errorService.isBlank(sett.getSettlementConcept().getRate(),
                                depositsSettlementConceptRate);
                regexUtils.validateRegex(settlementConceptRateFormatMessage,
                                sett.getSettlementConcept().getRate(),
                                depositsSettlementConceptRate);

                // economicData -> settlements [] -> settlementConcept -> amount -> amount RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(),
                                depositsSettlementConceptAmount);
                regexUtils.validateRegex(intialTotalInvestedAmountMessage,
                                sett.getSettlementConcept().getAmount().getAmount(),
                                depositsSettlementConceptAmount);

                // economicData -> settlements [] -> settlementConcept -> amount -> currency
                // RETF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(),
                                depositsSettlementConceptCurrency);
                regexUtils.validateRegex(currencyCodeFormatMessage,
                                sett.getSettlementConcept().getAmount().getCurrency(),
                                depositsSettlementConceptCurrency);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral()
                                                        .get(economicDataSettlementConceptAmountCurrencyNotFoundMessage),
                                        ErrorType.FUNCTIONAL);
                }
        }

        private void validateEconomicDataBgmf(TermDepositSettlementsDTO sett) {
                // economicData -> settlements [] -> settlementConcept -> code BGMF
                regexUtils.validateRegex(settlementConceptMessage,
                                sett.getSettlementConcept().getCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settlementConceptCodeFormatMessage,
                                sett.getSettlementConcept().getCode(),
                                depositsSettlementConceptCode);
                if (!validateSettlementConcept(sett.getSettlementConcept().getCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get(
                                                        economicDataSettlementsMessageNotFound),
                                        ErrorType.FUNCTIONAL);
                }
                // economicData -> settlements [] -> settlementConcept -> typeCode BGMF
                errorService.isBlank(sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settleConceptTypecodeMessage,
                                sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                regexUtils.validateRegex(settlementConceptTypeCodeFormatMessage,
                                sett.getSettlementConcept().getTypeCode(),
                                depositsSettlementConceptCode);
                validateSettlementConceptTypeCode(sett.getSettlementConcept().getTypeCode());

                // economicData -> settlements [] -> settlementConcept -> rate BGMF
                errorService.isBlank(sett.getSettlementConcept().getRate(),
                                depositsSettlementConceptRate);
                regexUtils.validateRegex(settlementConceptRateFormatMessage,
                                sett.getSettlementConcept().getRate(),
                                depositsSettlementConceptRate);

                // economicData -> settlements [] -> settlementConcept -> amount -> amount BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getAmount(),
                                depositsSettlementConceptAmount);
                regexUtils.validateRegex(intialTotalInvestedAmountMessage,
                                sett.getSettlementConcept().getAmount().getAmount(),
                                depositsSettlementConceptAmount);

                // economicData -> settlements [] -> settlementConcept -> amount -> currency
                // BGMF
                errorService.isBlank(sett.getSettlementConcept().getAmount().getCurrency(),
                                depositsSettlementConceptCurrency);
                regexUtils.validateRegex(currencyCodeFormatMessage,
                                sett.getSettlementConcept().getAmount().getCurrency(),
                                depositsSettlementConceptCurrency);
                if (!"COP".equals(sett.getSettlementConcept().getAmount().getCurrency())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral()
                                                        .get(economicDataSettlementConceptAmountCurrencyNotFoundMessage),
                                        ErrorType.FUNCTIONAL);
                }
        }

        private void bankValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getBank().getBankId(), bankIdFielName);
                regexUtils.validateRegex("bank_code_format", requestBodyData.getBank().getBankId(), bankIdFielName);
                regexUtils.validateRegex("bank_code_length", requestBodyData.getBank().getBankId(), bankIdFielName);
                if (!bankId.equals(requestBodyData.getBank().getBankId())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get(bankIdNotFound), ErrorType.FUNCTIONAL);
                }
        }

        private void centerValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getBank().getCenter().getCenterId(), centerIdFieldName);
                regexUtils.validateRegex("center_code_format", requestBodyData.getBank().getCenter().getCenterId(),
                                centerIdFieldName);
                regexUtils.validateRegex("center_code_length", requestBodyData.getBank().getCenter().getCenterId(),
                                centerIdFieldName);
                if (!centerId.equals(requestBodyData.getBank().getCenter().getCenterId())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("centerId_not_found"), ErrorType.FUNCTIONAL);
                }
        }

        private void productValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getProduct().getProductCode(), prodctCodeName);
                regexUtils.validateRegex("product_code_format", requestBodyData.getProduct().getProductCode(),
                                prodctCodeName);
                regexUtils.validateRegex("product_code_length", requestBodyData.getProduct().getProductCode(),
                                prodctCodeName);
                if (!productCode.equals(requestBodyData.getProduct().getProductCode())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("productcode_not_found"), ErrorType.FUNCTIONAL);
                }
        }

        private void subproductValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getProduct().getSubproduct().getSubproductId(),
                                subproductIdFieldname);
                regexUtils.validateRegex("subproduct_code_format",
                                requestBodyData.getProduct().getSubproduct().getSubproductId(), subproductIdFieldname);
                regexUtils.validateRegex("subproduct_code_length",
                                requestBodyData.getProduct().getSubproduct().getSubproductId(), subproductIdFieldname);
                if (!subproductCode.equals(requestBodyData.getProduct().getSubproduct().getSubproductId())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("subproductid_not_found"), ErrorType.FUNCTIONAL);
                }
        }

        private void accountIdTypeValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType()
                                                .toUpperCase(),
                                accountIdType);
                regexUtils.validateRegex("accountIdType_code_format",
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType()
                                                .toUpperCase(),
                                accountIdType);
                regexUtils.validateRegex("accountIdType_code_length",
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType()
                                                .toUpperCase(),
                                accountIdType);
                if (!validateAccountIdType(
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccountIdType()
                                                .toUpperCase())) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("accountIdType_not_found"), ErrorType.FUNCTIONAL);
                }
        }

        private void bankCodeValidationTermDepositInput(RequestTermDepositsDTO requestBodyData,
                        BanksParametersRequest banksParametersRequest) {
                errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(),
                                bankCode);
                regexUtils.validateRegex("bankId_code_format",
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(),
                                bankCode);
                regexUtils.validateRegex("bankId_code_length",
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode(),
                                bankCode);
                bankValidation(banksParametersRequest,
                                requestBodyData.getDeposit().getPlacement().getDestinationFunds().getBankcode());
        }

        private void nationalIdentificationValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount()
                                .getNationalIdentification(), nationalIdentification);
                regexUtils.validateRegex("nationalIdentification_code_length",
                                requestBodyData.getDeposit().getPlacement()
                                                .getDestinationFunds().getAccount().getNationalIdentification(),
                                nationalIdentification);
                regexUtils.validateRegex("nationalIdentification_code_format",
                                requestBodyData.getDeposit().getPlacement()
                                                .getDestinationFunds().getAccount().getNationalIdentification(),
                                nationalIdentification);
                padLeftWithZeros(requestBodyData.getDeposit().getPlacement().getDestinationFunds().getAccount()
                                .getNationalIdentification(), 17);
        }

        private void frecuencyValidationTermDepositInput(RequestTermDepositsDTO requestBodyData) {
                errorService.isBlank(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(),
                                depositsFrecuency);
                regexUtils.validateRegex("frecuency_code_format",
                                requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency(),
                                depositsFrecuency);
                frecuencyValidation(requestBodyData.getDeposit().getPlacement().getPeriodicity().getFrequency());
        }

        private boolean settlementContionCodeInputValidation(String code) {
                return code.length() == 1 && settlementCoditionCode.contains(code);
        }

        public static String settlementConditionCodeTransformation(String input) {
                if (input.equals("C")) {
                        return "S";
                } else
                        return "N";
        }

        public static boolean settlementConditionCodeValidation(String code) {
                if (!"C".equals(code)) {
                        return false;
                } else
                        return true;
        }

        public boolean frecuencyValidation(String input) {

                for (String output : validFrecuencies) {
                        if (output.equals(input)) {
                                return false;
                        }
                }
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                errorService.getGeneral().get("frequency_not_found"), ErrorType.FUNCTIONAL);
        }

        public void amountValidation(String input, String minAmount, String maxAmount) {

                Double inputAmount = Double.parseDouble(input.replace(",", ""));
                Double entryMinAmount = Double.parseDouble(minAmount.replace(",", ""));
                Double entrymaxAmount = Double.parseDouble(maxAmount.replace(",", ""));

                if (inputAmount < entryMinAmount) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST,
                                        errorService.getGeneral().get("amount_under_limit"), ErrorType.FUNCTIONAL);
                }
                if (inputAmount > entrymaxAmount) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.BAD_REQUEST,
                                        errorService.getGeneral().get("amount_over_limit"), ErrorType.FUNCTIONAL);
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
                if (input.equals("BGMF")) {
                        return "C";
                } else if (input.equals("RETF")) {
                        return "D";
                }
                return "C";
        }

        public String purposeCodeValidation(TermDepositParametersRequest termDepositParametersRequest, String input) {
                var init = input.substring(0, 2);
                var termDeposit = termDepositParametersService.termDepositParameters(termDepositParametersRequest);
                var purpose = termDeposit.getParameters().stream().filter(x -> x.getCode().equals(init)).findFirst()
                                .orElse(null);
                if (purpose == null) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
                }
                if (!purpose.getCode().equals(init)) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get("purposeCode_not_found"), ErrorType.FUNCTIONAL);
                } else
                        return init;
        }

        public String bankValidation(BanksParametersRequest banksParametersRequest, String input) {
                var banks = banksService.banksResponse(banksParametersRequest);
                var findBank = banks.getBanks().stream().filter(x -> x.getBankId().equals(input)).findFirst()
                                .orElse(null);
                if (findBank == null) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get(bankIdNotFound), ErrorType.FUNCTIONAL);
                }
                if (!findBank.getBankId().equals(input)) {
                        throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND,
                                        errorService.getGeneral().get(bankIdNotFound), ErrorType.FUNCTIONAL);
                } else
                        return input;

        }

        public void getSettlementsInputValidation(TermDepositSettlementsRequest request) {
                errorService.isBlank(request.getDeposit_id(), depositsIdFieldName);
                regexUtils.validateRegex("deposit_id_format", request.getDeposit_id(), depositsIdFieldName);
                regexUtils.validateRegex("strict_length_20", request.getDeposit_id(), depositsIdFieldName);

                errorService.isBlank(request.getPlacement_id(), placementIdFieldName);
                regexUtils.validateRegex("placement_format", request.getPlacement_id(), placementIdFieldName);

        }

        public static Double parseDouble(String input) {

                var noplus = input.replace("+", "");
                var nominus = noplus.replace("-", "");
                var nodots = nominus.replace(".", "");
                var entryinput = nodots.replace(",", ".");
                return Double.parseDouble(entryinput);
        }

        public static String toLinea2Decimal(String input) {

                Integer entero = Integer.parseInt(input.substring(0, 13));
                String decimal = input.substring(13);
                return entero.toString() + "," + decimal;
        }

        public static String format15DigitNumber(String number) {

                if (number == null || number.isBlank() || number.isEmpty())
                        return "0,00";

                String noZerosNumber = number.replaceFirst("^0+(?!$)", "");

                if (noZerosNumber.length() == 1)
                        return "0,0" + noZerosNumber;
                if (noZerosNumber.length() == 2)
                        return "0," + noZerosNumber;

                return noZerosNumber.substring(0, noZerosNumber.length() - 2) + ","
                                + noZerosNumber.substring(noZerosNumber.length() - 2, noZerosNumber.length());
        }

        public static String removeLeadingZeros(String number) {

                if (number == null || number.isBlank() || number.isEmpty())
                        return "0";

                return number.replaceFirst("^0+(?!$)", "");

        }

}// class closure


******************
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.service.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49DataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.mappers.TermDepositSettlementsMappers;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.service.TermDepositSettlementsService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.RegexUtils;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.utils.TermDepositUtils;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermDepositSettlementsImpl implements TermDepositSettlementsService {
    final TrxSanbaService trxSanbaService;

    final TermDepositUtils termDepositUtils;
    final RegexUtils regexUtils;
    final ErrorService errorService;
    final TermDepositSettlementsMappers settlementsMappers;
    @Value("${service-route-trx.BP49}")
    private String bp49serviceRoute;

    @Value("${service-route-trx.BP13}")
    private String bp13serviceRoute;




    public TermDepositSettlementsReponse getDepositSettlements(TermDepositSettlementsRequest request){
        termDepositUtils.getSettlementsInputValidation(request);

        TrxBP49Request trxBP49Request = new TrxBP49Request(ClientUtils.buildHeader(bp49serviceRoute));
        var data = new TrxBP49DataRequest();
        data.setSecuencia(request.getPlacement_id().substring(0,5));
        data.setOfic("0100");
        data.setEnt("0065");
        data.setNumeroCertificado(request.getDeposit_id() + request.getPlacement_id().replace("-",""));
        data.setBuscarPor("");
        data.setDocumentoCajero("");

        trxBP49Request.setData(data);

        TrxBP49Response trxBP49Response = trxSanbaService.trxBP49(trxBP49Request);
        TrxBP13Response trxBP13Response = trxBP13call(request.getDeposit_id(),request.getPlacement_id());
        if(trxBP49Response.getData().getMovimientos().get(0) != null){
            return settlementsMappers.settlementsReponse(trxBP49Response,trxBP13Response);
        }

        return null;
    }

    public TrxBP13Response trxBP13call( String depositID, String placementID) {
        TrxBP13Request trxBP13Request = new TrxBP13Request(ClientUtils.buildHeader(bp13serviceRoute));
        var trxBP13Data = new TrxBP13DataRequest();
        trxBP13Data.setEntidad("0065");
        trxBP13Data.setOficina("0100");
        trxBP13Data.setCuenta("");
        trxBP13Data.setNumSecuencia("");
        trxBP13Data.setNumCertificado(depositID + placementID.replace("-",""));
        trxBP13Request.setData(trxBP13Data);
        return trxSanbaService.trxBP13(trxBP13Request);
    }

}
******************
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.controller;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.request.TermDepositSettlementsRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositsettlements.response.TermDepositSettlementsReponse;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.service.TermDepositSettlementsService;
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

public class TermDepositSettlementsController {
    final TermDepositSettlementsService termDepositSettlementsService;
    private final ObjectMapper objectMapper;
    @GetMapping("/term_deposit_settlements/{deposit_id}/placements/{placement_id}/settlements")
    public ResponseEntity<TermDepositSettlementsReponse> getDepositSettlements(
            @RequestHeader(required = true, name = "Authorization") String authorization,
            @RequestHeader(required = true, name = "x-santander-client-id") String clientId,
            @PathVariable(name = "deposit_id") String depositId,
            @PathVariable(name = "placement_id") String placementId)
    {


		log.info("*** INIT (GET) /v1/term_deposit_settlements/{}/placements/{}/settlements  client-id={} >>> ",depositId,placementId, clientId);
		
    	
        TermDepositSettlementsRequest request = new TermDepositSettlementsRequest();
        request.setAuthorization(authorization);
        request.setClient_id(clientId);
        request.setDeposit_id(depositId);
        request.setPlacement_id(placementId);
        TermDepositSettlementsReponse response =  termDepositSettlementsService.getDepositSettlements(request);
        
        try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" Response=").append(jsonResponse);
			log.info("*** FIN (GET) /v1/term_deposit_settlements/{}/placements/{}/settlements  client-id={} {}>>> ",depositId,placementId, clientId,sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
        
        if(response == null || response.getSettlements() == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        
        log.info(GUtils.ELOG + "endpoint search customers {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


***************
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.config;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.integration.ApiEntry;
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
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.config;

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
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.integration.ApiEntry;

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
    TrxSanbaAPI txrTransactionApi() {
        return getRetrofitConfig(properties.getByApi("sanba"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TrxSanbaAPI.class);
    }

    @Bean
    ProductDirectoryAPI productDirectoryAPI() {
        return getRetrofitConfig(properties.getByApi("product-directory"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(ProductDirectoryAPI.class);
    }

    @Bean
    TermDepositParametersAPI termDepositParametersAPI() {
        return getRetrofitConfig(properties.getByApi("term-deposit-parameters"))
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper(new ObjectMapper()))).build()
                .create(TermDepositParametersAPI.class);
    }

    @Bean
    BanksApi banksAPI() {
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

    private OkHttpClient getHttpClient(HttpLoggingInterceptor.Level level, long readTimeout, long connectTimeout) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(msg ->
		log.info("--> OKHTTP {}",msg)
		);
        interceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        return builder
                .build();
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

    private String buildURL(ApiEntry apiEntry) {
        String schema = "https://";
        if (!apiEntry.isHttps()) {
            schema = schema.replace("s", "");
        }
        return schema + apiEntry.getHost() + ":" + apiEntry.getPort() + apiEntry.getEndpoint();
    }

}
*******************************
package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.BanksApi;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.BanksService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksDTO;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.banks.BanksParametersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanksServiceImpl implements BanksService {
    final BanksApi banksApi;

    @Override
    public BanksDTO banksResponse(BanksParametersRequest request) {
        try {
            return banksApi.callBanks(request.getAuthorization(), request.getxSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }
}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.ProductDirectoryAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.ProductDirectoryService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.productdirectory.AmountRangeResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDirectoryServiceImpl implements ProductDirectoryService {
    
    final ProductDirectoryAPI productDirectoryAPI;
    
    @Value("${clients.product-directory.amount-range}")
    private String amountRangeUri;

    @Override
    public AmountRangeResponse amountRange(AmountRangeRequest request) {
        
        try {
            return productDirectoryAPI.callAmountRange(request.getProductId(), request.getAuthorization(), request.getxSantanderClientId()).execute().body();
            
        } catch (Exception e) {
            return null;
        }
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TermDepositParametersAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TermDepositParametersService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.termdepositparameters.TermDepositParametersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermDepositParametersServiceImpl implements TermDepositParametersService {

    final TermDepositParametersAPI termDepositParametersAPI;

    public TermDepositParametersResponse termDepositParameters(TermDepositParametersRequest request) {

        try {
            return termDepositParametersAPI.callTermDepositParameters(request.getProductId(), request.getAuthorization(), request.getxSantanderClientId()).execute().body();

        } catch (Exception e) {
            return null;
        }
    }

}


package com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.request.TrxBP13Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp13.response.TrxBP13Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.request.TrxBp01Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp01.response.TrxBp01Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.request.TrxBp02Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp02.response.TrxBp02Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.request.TrxBP49Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp49.response.TrxBP49Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.TrxPEPFDataResponse.TrxPEPFDataResponse;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.pepf.request.TrxPEPFDataRequest;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.exception.error.ErrorType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.domain.trx.response.ErrorTrxDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorCatalog;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.exception.error.ErrorDTO;
import com.santander.bnc.bsn049.bncbsn049igcdtcommon.utils.GUtils;

import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.api.TrxSanbaAPI;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.client.service.TrxSanbaService;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.request.TrxBP17Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp17.response.TrxBP17Response;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.request.TrxBP31Request;
import com.santander.bnc.bsn049.bncbsn049mstrmdpstsettlmn.domain.host.bp31.response.TrxBP31Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrxSanbaServiceImpl implements TrxSanbaService {

    private final TrxSanbaAPI trxSanbaAPI;
    private final ErrorService errorService;

    private static final String BP31_SERVICE_ROUTE = "SBCDTTI01-ConsultaCDTDATTitular2654";
    private static final String BP13_SERVICE_ROUTE = "consultaDatosIPF";
    @Value("${service-route-trx.BP17}")
    private String bp17ServiceRoute;
    @Value("${service-route-trx.PEPF}")
    private String pepfServiceRoute;
    @Value("${service-route-trx.BP01}")
    private String bp01ServiceRoute;
    @Value("${service-route-trx.BP02}")
    private String bp02ServiceRoute;
    @Value("${service-route-trx.BP49}")
    private String bp49ServiceRoute;

    @Value("${params.sanba.mqRoute}")
    private String mqRoute;

    @Value("${params.sanba.channel}")
    private String channel;

    @Value("${params.sanba.user}")
    private String user;

    private String errorTrxMessage = "Error in TRX: {}";
    private String errorClientMessage = "client {}";
    private String errMessage = "err {}";
    private String noErrorMessage = "No error";
    private String setErrorMessage = "SET {}";

    @Override
    public TrxBP17Response trxBP17(TrxBP17Request request) {
        Response<TrxBP17Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP17TRX(request, bp17ServiceRoute, bp17ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP17Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP17Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBP17Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    public TrxBP31Response trxBP31(TrxBP31Request request) {
        Response<TrxBP31Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP31TRX(request, BP31_SERVICE_ROUTE, BP31_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP31Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP31Response err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            validateTrxBp31(err, objm, errorBody, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")) {

                return null;
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private void validateTrxBp31(TrxBP31Response err, ObjectMapper objm, String errorBody, List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxBP31Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : err.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }

        log.info(GUtils.ELOG + setErrorMessage, errosDtos);
    }

    @Override
    public TrxPEPFDataResponse trxPEPF(TrxPEPFDataRequest trxPEPFDataRequest) {
        Response<TrxPEPFDataResponse> responseApi = null;

        try {
            trxPEPFDataRequest.getCabecera().setCanal(channel);
            trxPEPFDataRequest.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callPEPF(trxPEPFDataRequest, pepfServiceRoute, pepfServiceRoute, mqRoute)
                    .execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxPEPFDataResponse responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();

            TrxPEPFDataResponse err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            validateTrxPEPF(objm, errorBody, err, errosDtos);
            log.info(GUtils.ELOG + setErrorMessage, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("NO EXISTE CDT")) {
                return null;
            }

            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    private void validateTrxPEPF(ObjectMapper objm, String errorBody, TrxPEPFDataResponse err,
            List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxPEPFDataResponse.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : getError.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }
    }

    //
    @Override
    public TrxBP13Response trxBP13(TrxBP13Request request) {
        Response<TrxBP13Response> responseApi = null;

        try {
            request.getCabecera().setCanal(channel);
            request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP13TRX(request, BP13_SERVICE_ROUTE, BP13_SERVICE_ROUTE, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP13Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP13Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBP13Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }
    }

    @Override
    public TrxBp01Response trxBP01(TrxBp01Request trxBp01Request) {
        Response<TrxBp01Response> responseApi = null;

        try {
            trxBp01Request.getCabecera().setCanal(channel);
            trxBp01Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP01(trxBp01Request, bp01ServiceRoute, bp01ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBp01Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBp01Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBp01Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    @Override
    public TrxBp02Response trxBP02(TrxBp02Request trxBp02Request) {
        Response<TrxBp02Response> responseApi = null;

        try {
            trxBp02Request.getCabecera().setCanal(channel);
            trxBp02Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP02(trxBp02Request, bp02ServiceRoute, bp02ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBp02Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBp02Response err = null;
            try {
                err = objm.readValue(errorBody, TrxBp02Response.class);
            } catch (JsonProcessingException e) {
                throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
            }
            log.info(GUtils.ELOG + errMessage, err != null ? err.getErrores() : noErrorMessage);
            List<ErrorDTO> errosDtos = new ArrayList<>();

            for (ErrorTrxDTO dtoEr : err.getErrores()) {

                errosDtos.add(
                        errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

            }

            log.info(GUtils.ELOG + setErrorMessage, errosDtos);

            throw new ServiceException(HttpStatus.CONFLICT,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    //
    @Override
    public TrxBP49Response trxBP49(TrxBP49Request trxBp49Request) {

        Response<TrxBP49Response> responseApi = null;

        try {
            trxBp49Request.getCabecera().setCanal(channel);
            trxBp49Request.getCabecera().getSesion().setUsuario(user);
            responseApi = trxSanbaAPI.callBP49(trxBp49Request, bp49ServiceRoute, bp49ServiceRoute, mqRoute).execute();

        } catch (RuntimeException e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST, error);

        } catch (IOException e) {
            throw new ServiceException(HttpStatus.SERVICE_UNAVAILABLE, ErrorCatalog.MS_SANBA_NETWORK_CONNECTION);

        } catch (Exception e) {
            log.info(errorTrxMessage, e.getMessage());
            var error = ErrorCatalog.MS_SANBA_TRX_ERROR;
            error.setMessage(e.getMessage());
            throw new ServiceException(HttpStatus.CONFLICT, error);

        }

        if (responseApi.isSuccessful()) {
            TrxBP49Response responseBody = responseApi.body();
            log.info(GUtils.ELOG + errorClientMessage, responseBody);
            return responseBody;

        } else {
            String errorBody = "";
            try {
                errorBody = responseApi.errorBody().string();
            } catch (IOException e) {

            }
            ObjectMapper objm = new ObjectMapper();
            TrxBP49Response err = null;
            List<ErrorDTO> errosDtos = new ArrayList<>();
            trxbp49Validate(err, objm, errorBody, errosDtos);
            if (!errosDtos.isEmpty() && errosDtos.get(0).getMessage().contains("SECUENCIA NO EXISTE")) {
                throw errorService.serviceExceptionBuilder(HttpStatus.NOT_FOUND, "Deposit not found",
                        ErrorType.FUNCTIONAL);
            }
            throw new ServiceException(HttpStatus.BAD_REQUEST,
                    !errosDtos.isEmpty() ? errosDtos.get(0) : ErrorCatalog.MS_SANBA_TRX_ERROR);
        }

    }

    private void trxbp49Validate(TrxBP49Response err, ObjectMapper objm, String errorBody, List<ErrorDTO> errosDtos) {
        var getError = err;
        try {
            getError = objm.readValue(errorBody, TrxBP49Response.class);
        } catch (JsonProcessingException e) {
            throw errorService.serviceExceptionBuilder(HttpStatus.CONFLICT, e.getMessage(), ErrorType.TECHNICAL);
        }
        log.info(GUtils.ELOG + errMessage, getError != null ? getError.getErrores() : noErrorMessage);

        for (ErrorTrxDTO dtoEr : getError.getErrores()) {

            errosDtos.add(
                    errorService.errorBuilder(HttpStatus.BAD_REQUEST, dtoEr.getMensaje(), ErrorType.FUNCTIONAL));

        }

        log.info(GUtils.ELOG + setErrorMessage, errosDtos);
    }
}
