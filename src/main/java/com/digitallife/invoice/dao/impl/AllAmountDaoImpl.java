package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.AllAmountDaoInter;
import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;

@Repository(value = "allAmount")
public class AllAmountDaoImpl implements AllAmountDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ClientVendorAmount allAmount(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate) {
        String queryStr = "select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.taxAmount),sum(i.netAmount)) from Invoice i where 1=1";
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

        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery(queryStr,ClientVendorAmount.class);
        if (client!=null){
            typedQuery.setParameter("client",client);
        }
        if (vendor!=null){
            typedQuery.setParameter("vendor",vendor);
        }
        if (project!=null){
            typedQuery.setParameter("project",project);
        }
        if (paid!=null){
            typedQuery.setParameter("paid",paid);
        }
        if (fromDate != null && toDate!=null){
            typedQuery.setParameter("fromDate",fromDate);
            typedQuery.setParameter("toDate",toDate);
        }

        ClientVendorAmount allAmount = typedQuery.getSingleResult();

        return allAmount;
    }
}
