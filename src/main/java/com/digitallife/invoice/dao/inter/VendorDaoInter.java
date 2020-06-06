package com.digitallife.invoice.dao.inter;

import com.digitallife.invoice.entity.Vendor;

import java.util.List;

public interface VendorDaoInter {

    public List<Vendor> getAllVendors();

    public Vendor getVendorById(int id);

    public Integer addVendor(Vendor vendor);

    public boolean updateVendor(Vendor vendor);

    public boolean removeVendor(int id);
}
