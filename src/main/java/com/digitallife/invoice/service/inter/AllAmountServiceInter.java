package com.digitallife.invoice.service.inter;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;

import java.util.Date;

public interface AllAmountServiceInter {
    public ClientVendorAmount allAmount(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate);
}
