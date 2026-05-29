package com.santander.bnc.bsn049.bncbsn049mstermdeposits.request.simulatePlacement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class SimulatePlacementRequestDtosTest {

    @Test
    void shouldCoverAmountRequestDTO() {
        AmountRequestDTO dto = new AmountRequestDTO();
        dto.setAmount("1000");
        assertEquals("1000", dto.getAmount());
    }

    @Test
    void shouldCoverBankRequestDTO() {
        BankRequestDTO dto = new BankRequestDTO();
        dto.setBankId("001");
        assertEquals("001", dto.getBankId());
    }

    @Test
    void shouldCoverCenterRequestDTO() {
        CenterRequestDTO dto = new CenterRequestDTO();
        dto.setCenterId("1234");
        assertEquals("1234", dto.getCenterId());
    }

    @Test
    void shouldCoverCustomerRequestDTO() {
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setCustomerId("999999");
        assertEquals("999999", dto.getCustomerId());
    }

    @Test
    void shouldCoverSubproductRequestDTO() {
        SubproductRequestDTO dto = new SubproductRequestDTO();
        dto.setSubproductId("SUB01");
        assertEquals("SUB01", dto.getSubproductId());
    }

    @Test
    void shouldCoverRequestDTO() {
        RequestDTO dto = new RequestDTO();
        dto.setFrequency("30");
        dto.setPeriodTypeCode("D");

        assertEquals("30", dto.getFrequency());
        assertEquals("D", dto.getPeriodTypeCode());
    }

    @Test
    void shouldCoverProduct() {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId("SUB01");

        Product dto = new Product();
        dto.setProductCode("940250");
        dto.setSubproduct(subproduct);

        assertEquals("940250", dto.getProductCode());
        assertSame(subproduct, dto.getSubproduct());
    }

    @Test
    void shouldCoverRequestSimulatePlacementDTO() {
        SubproductRequestDTO subproduct = new SubproductRequestDTO();
        subproduct.setSubproductId("SUB01");

        Product product = new Product();
        product.setProductCode("940250");
        product.setSubproduct(subproduct);

        AmountRequestDTO amount = new AmountRequestDTO();
        amount.setAmount("1000");

        RequestDTO periodicity = new RequestDTO();
        periodicity.setFrequency("30");
        periodicity.setPeriodTypeCode("D");

        RequestSimulatePlacementDTO dto = new RequestSimulatePlacementDTO();
        dto.setProduct(product);
        dto.setAmount(amount);
        dto.setPeriodicity(periodicity);
        dto.setSettlementConditionCode("V");

        assertSame(product, dto.getProduct());
        assertSame(amount, dto.getAmount());
        assertSame(periodicity, dto.getPeriodicity());
        assertEquals("V", dto.getSettlementConditionCode());
    }
}
