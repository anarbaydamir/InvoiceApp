package com.digitallife.invoice.entity;

import java.math.BigDecimal;

public class ClientVendorAmount {

    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private BigDecimal netAmount;

    public ClientVendorAmount(BigDecimal totalAmount, BigDecimal netAmount) {
        this.totalAmount = totalAmount;
        this.netAmount = netAmount;
    }

    public ClientVendorAmount(BigDecimal totalAmount, BigDecimal taxAmount, BigDecimal netAmount) {
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.netAmount = netAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }
}
