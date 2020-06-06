package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.AllAmountDaoInter;
import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;
import com.digitallife.invoice.service.inter.AllAmountServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "allAmountService")
@Transactional
public class AllAmountServiceImpl implements AllAmountServiceInter {

    @Autowired
    @Qualifier(value = "allAmount")
    private AllAmountDaoInter allAmountDaoInter;

    @Override
    public ClientVendorAmount allAmount(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate) {
        return allAmountDaoInter.allAmount(client,vendor,project,paid,fromDate,toDate);
    }
}
