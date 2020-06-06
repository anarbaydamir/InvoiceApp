package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.VendorDaoInter;
import com.digitallife.invoice.entity.Vendor;
import com.digitallife.invoice.service.inter.VendorServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VendorServiceImpl implements VendorServiceInter {

    @Autowired
    @Qualifier("vendorDao")
    private VendorDaoInter vendorDaoInter;

    @Override
    public List<Vendor> getAllVendors() {
        return vendorDaoInter.getAllVendors();
    }

    @Override
    public Vendor getVendorById(int id) {
        return vendorDaoInter.getVendorById(id);
    }

    @Override
    public Integer addVendor(Vendor vendor) {
        return vendorDaoInter.addVendor(vendor);
    }

    @Override
    public boolean updateVendor(Vendor vendor) {
        return vendorDaoInter.updateVendor(vendor);
    }

    @Override
    public boolean removeVendor(int id) {
        return vendorDaoInter.removeVendor(id);
    }
}
