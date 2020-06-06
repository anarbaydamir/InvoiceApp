package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.AmountDaoInter;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.service.inter.AmountServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "dateAmountService")
@Transactional
public class DateAmountServiceImpl implements AmountServiceInter<Date,Date> {

    @Autowired
    @Qualifier(value = "dateAmount")
    private AmountDaoInter<Date,Date> amountDaoInter;

    @Override
    public ClientVendorAmount getClientAmount(Date fromDate, Date toDate) {
        return amountDaoInter.getClientAmount(fromDate,toDate);
    }

    @Override
    public ClientVendorAmount getVendorAmount(Date fromDate, Date toDate) {
        return amountDaoInter.getVendorAmount(fromDate,toDate);
    }

    @Override
    public ClientVendorAmount getExpectedClientAmount(Date fromDate, Date toDate) {
        return amountDaoInter.getExpectedClientAmount(fromDate, toDate);
    }

    @Override
    public ClientVendorAmount getExpectedVendorAmount(Date fromDate, Date toDate) {
        return amountDaoInter.getExpectedVendorAmount(fromDate, toDate);
    }
}
