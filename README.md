package com.santander.bnc.bsn049.bncbsn049mscontracts.domain.banks;

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
