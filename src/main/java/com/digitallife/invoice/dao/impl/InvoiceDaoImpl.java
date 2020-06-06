package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.InvoiceDaoInter;
import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.Invoice;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;
import com.digitallife.invoice.service.inter.ProjectServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements InvoiceDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ProjectServiceInter projectServiceInter;

    @Override
    public List<Invoice> getAllInvoices() {
        Query query = entityManager.createQuery("Select i from Invoice i",Invoice.class);
        List<Invoice> invoices = query.getResultList();

        return invoices;
    }

    @Override
    public List<Invoice> getAllInvoiceByProject(Project project) {
        Query query = entityManager.createQuery("Select i from Invoice i where i.projectId=:project",Invoice.class);
        query.setParameter("project",project);
        List<Invoice> invoices = query.getResultList();

        return invoices;
    }

    @Override
    public List<Invoice> getAllInvoiceByDate(Date fromDate, Date toDate) {
        Query query = entityManager.createQuery("select i from Invoice i where i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate",Invoice.class);
        query.setParameter("fromDate",fromDate);
        query.setParameter("toDate",toDate);
        List<Invoice> invoices = query.getResultList();

        return invoices;
    }

    @Override
    public List<Invoice> getAllInvoiceByReport(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate) {
        String queryStr = "select i from Invoice i where 1=1";
        if (client!=null){
            queryStr += " and i.clientId=:client";
        }
        if (vendor!=null){
            queryStr += " and i.vendorId=:vendor";
        }
        if (project!=null){
            queryStr += " and i.projectId=:project";
        }
        if (paid!=null){
            queryStr += " and i.paymentStatus=:paid";
        }
        if (fromDate != null && toDate!=null){
            queryStr += " and (i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate)";
        }

        Query query = entityManager.createQuery(queryStr,Invoice.class);
        if (client!=null){
            query.setParameter("client",client);
        }
        if (vendor!=null){
            query.setParameter("vendor",vendor);
        }
        if (project!=null){
            query.setParameter("project",project);
        }
        if (paid!=null){
            query.setParameter("paid",paid);
        }
        if (fromDate != null && toDate!=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
        }
        List<Invoice> invoices = query.getResultList();
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(int id) {
        Invoice invoice = entityManager.find(Invoice.class,id);
        return invoice;
    }

    @Override
    public Integer addInvoice(Invoice invoice) {
        entityManager.persist(invoice);
        entityManager.flush();

        return invoice.getId();
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        try{
            entityManager.merge(invoice);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteInvoice(int id) {
        try{
            Invoice invoice = entityManager.find(Invoice.class,id);
            entityManager.remove(invoice);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
