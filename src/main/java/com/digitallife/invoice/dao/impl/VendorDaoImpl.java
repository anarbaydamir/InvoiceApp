package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.VendorDaoInter;
import com.digitallife.invoice.entity.Vendor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("vendorDao")
public class VendorDaoImpl implements VendorDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Vendor> getAllVendors() {
        String jpql = "Select v from Vendor v";

        Query query = entityManager.createQuery(jpql,Vendor.class);
        List<Vendor> vendors = query.getResultList();

        return vendors;
    }

    @Override
    public Vendor getVendorById(int id) {
        Vendor vendor = entityManager.find(Vendor.class,id);

        return vendor;
    }

    @Override
    public Integer addVendor(Vendor vendor) {
        entityManager.persist(vendor);
        entityManager.flush();

        return vendor.getId();
    }

    @Override
    public boolean updateVendor(Vendor vendor) {
        try{
            entityManager.merge(vendor);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeVendor(int id) {
        try{
            Vendor vendor = entityManager.find(Vendor.class,id);
            entityManager.remove(vendor);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
