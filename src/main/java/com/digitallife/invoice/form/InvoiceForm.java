package com.digitallife.invoice.form;

import java.util.Date;

public class InvoiceForm {

    private Integer id;
    private String action;
    private String invoiceNumber;
    private String invoiceDate;
    private int clientName;
    private int vendorName;
    private double netAmount;
    private int projectName;
    private int taxPerCent;
    private String note;
    private Short paymentStatus;

    public InvoiceForm() {
    }

    public InvoiceForm(Integer id, String action, String invoiceNumber, String invoiceDate, int clientName, int vendorName, double netAmount, int projectName, int taxPerCent, String note, Short paymentStatus) {
        this.id = id;
        this.action = action;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.clientName = clientName;
        this.vendorName = vendorName;
        this.netAmount = netAmount;
        this.projectName = projectName;
        this.taxPerCent = taxPerCent;
        this.note = note;
        this.paymentStatus = paymentStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getClientName() {
        return clientName;
    }

    public void setClientName(int clientName) {
        this.clientName = clientName;
    }

    public int getVendorName() {
        return vendorName;
    }

    public void setVendorName(int vendorName) {
        this.vendorName = vendorName;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public int getProjectName() {
        return projectName;
    }

    public void setProjectName(int projectName) {
        this.projectName = projectName;
    }

    public int getTaxPerCent() {
        return taxPerCent;
    }

    public void setTaxPerCent(int taxPerCent) {
        this.taxPerCent = taxPerCent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Short getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Short paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
