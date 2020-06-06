package com.digitallife.invoice.dao.inter;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;

import java.util.Date;

public interface AllAmountDaoInter {
    public ClientVendorAmount allAmount(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate);
}
