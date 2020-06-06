package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.AmountDaoInter;
import com.digitallife.invoice.entity.ClientVendorAmount;
import com.digitallife.invoice.service.inter.AmountServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("projectAmountService")
@Transactional
public class ProjectAmountServiceImpl<Project,Object> implements AmountServiceInter<Project,Object> {

    @Autowired
    @Qualifier("projectAmount")
    private AmountDaoInter<Project,Object> amountDaoInter;

    @Override
    public ClientVendorAmount getClientAmount(Project project,Object o) {
        return amountDaoInter.getClientAmount(project,o);
    }

    @Override
    public ClientVendorAmount getVendorAmount(Project project,Object o) {
        return amountDaoInter.getVendorAmount(project,o);
    }

    @Override
    public ClientVendorAmount getExpectedClientAmount(Project project,Object o) {
        return amountDaoInter.getExpectedClientAmount(project,o);
    }

    @Override
    public ClientVendorAmount getExpectedVendorAmount(Project project,Object o) {
        return amountDaoInter.getExpectedVendorAmount(project,o);
    }
}
