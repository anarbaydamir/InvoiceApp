package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.AmountDaoInter;
import com.digitallife.invoice.entity.ClientVendorAmount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository(value = "projectAmount")
public class ProjectAmountDaoImpl<Project,Object> implements AmountDaoInter<Project,Object> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ClientVendorAmount getClientAmount(Project project,Object o) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.taxAmount),sum(i.netAmount))" +
                " from Invoice i where i.vendorId is null and i.paymentStatus=1 and i.projectId=:project", ClientVendorAmount.class);
        typedQuery.setParameter("project",project);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getVendorAmount(Project project,Object o) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.taxAmount),sum(i.netAmount))" +
                " from Invoice i where i.clientId is null and i.paymentStatus=1 and i.projectId=:project", ClientVendorAmount.class);
        typedQuery.setParameter("project",project);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getExpectedClientAmount(Project project,Object o) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.netAmount))" +
                " from Invoice i where i.vendorId is null and i.projectId=:project", ClientVendorAmount.class);
        typedQuery.setParameter("project",project);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }

    @Override
    public ClientVendorAmount getExpectedVendorAmount(Project project,Object o) {
        TypedQuery<ClientVendorAmount> typedQuery = entityManager.createQuery("Select new com.digitallife.invoice.entity.ClientVendorAmount(sum(i.totalAmount),sum(i.netAmount))" +
                " from Invoice i where i.clientId is null and i.projectId=:project", ClientVendorAmount.class);
        typedQuery.setParameter("project",project);
        ClientVendorAmount clientAmount = typedQuery.getSingleResult();
        return clientAmount;
    }
}
