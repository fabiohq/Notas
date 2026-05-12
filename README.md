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
