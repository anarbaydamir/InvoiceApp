package com.digitallife.invoice.service.inter;

import com.digitallife.invoice.entity.Vendor;

import java.util.List;

public interface VendorServiceInter {

    public List<Vendor> getAllVendors();

    public Vendor getVendorById(int id);

    public Integer addVendor(Vendor vendor);

    public boolean updateVendor(Vendor vendor);

    public boolean removeVendor(int id);
}
