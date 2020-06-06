package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.AmountDaoInter;
import com.digitallife.invoice.entity.ClientVendorAmount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;

@Repository(value = "dateAmount")
public class DateAmountDaoImpl implements AmountDaoInter<Date,Date> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ClientVendorAmount getClientAmount(Date fromDate, Date toDate) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.taxAmount),sum(i.netAmount))" +
                " from Invoice i where i.vendorId is null and i.paymentStatus=1 and (i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate)", ClientVendorAmount.class);
        typedQuery.setParameter("fromDate",fromDate);
        typedQuery.setParameter("toDate",toDate);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getVendorAmount(Date fromDate, Date toDate) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.taxAmount),sum(i.netAmount))" +
                " from Invoice i where i.clientId is null and i.paymentStatus=1 and (i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate)", ClientVendorAmount.class);
        typedQuery.setParameter("fromDate",fromDate);
        typedQuery.setParameter("toDate",toDate);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getExpectedClientAmount(Date fromDate, Date toDate) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.netAmount))" +
                " from Invoice i where i.vendorId is null and (i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate)", ClientVendorAmount.class);
        typedQuery.setParameter("fromDate",fromDate);
        typedQuery.setParameter("toDate",toDate);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getExpectedVendorAmount(Date fromDate, Date toDate) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.netAmount))" +
                " from Invoice i where i.clientId is null and (i.invoiceDate>=:fromDate and i.invoiceDate<=:toDate)", ClientVendorAmount.class);
        typedQuery.setParameter("fromDate",fromDate);
        typedQuery.setParameter("toDate",toDate);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }
}
