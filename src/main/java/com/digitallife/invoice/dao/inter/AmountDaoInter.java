package com.digitallife.invoice.dao.inter;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;

import java.util.Date;
import java.util.List;

public interface AmountDaoInter<T,U> {
    public ClientVendorAmount getClientAmount(T t,U u);

    public ClientVendorAmount getVendorAmount(T t,U u);

    public ClientVendorAmount getExpectedClientAmount(T t,U u);

    public ClientVendorAmount getExpectedVendorAmount(T t,U u);

}
