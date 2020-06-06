/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitallife.invoice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anarbaydamirov
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "Invoice.findByNetAmount", query = "SELECT i FROM Invoice i WHERE i.netAmount = :netAmount"),
    @NamedQuery(name = "Invoice.findByTaxAmount", query = "SELECT i FROM Invoice i WHERE i.taxAmount = :taxAmount"),
    @NamedQuery(name = "Invoice.findByTotalAmount", query = "SELECT i FROM Invoice i WHERE i.totalAmount = :totalAmount"),
    @NamedQuery(name = "Invoice.findByPaymentStatus", query = "SELECT i FROM Invoice i WHERE i.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Invoice.findByNote", query = "SELECT i FROM Invoice i WHERE i.note = :note")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "invoice_number")
    private String invoiceNumber;
    @Column(name = "invoice_date")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "net_amount")
    private BigDecimal netAmount;
    @Column(name = "tax_amount")
    private BigDecimal taxAmount;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "payment_status")
    private Short paymentStatus;
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private Project projectId;
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    @ManyToOne
    private Vendor vendorId;

    public Invoice() {
    }

    public Invoice(Integer id, String invoiceNumber, Date invoiceDate, BigDecimal netAmount, BigDecimal taxAmount, BigDecimal totalAmount, Short paymentStatus, String note, Client clientId, Project projectId, Vendor vendorId) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.netAmount = netAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.note = note;
        this.clientId = clientId;
        this.projectId = projectId;
        this.vendorId = vendorId;
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Short getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Short paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public Vendor getVendorId() {
        return vendorId;
    }

    public void setVendorId(Vendor vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{ invoiceNumber: "+"\""+invoiceNumber+"\""+" }";
    }
    
}
