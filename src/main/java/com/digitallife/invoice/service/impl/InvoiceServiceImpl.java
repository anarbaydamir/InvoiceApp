package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.ClientDaoInter;
import com.digitallife.invoice.dao.inter.InvoiceDaoInter;
import com.digitallife.invoice.dao.inter.ProjectDaoInter;
import com.digitallife.invoice.dao.inter.VendorDaoInter;
import com.digitallife.invoice.entity.*;
import com.digitallife.invoice.service.inter.InvoiceServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceServiceInter {

    @Autowired
    @Qualifier("invoiceDao")
    private InvoiceDaoInter invoiceDaoInter;

    @Autowired
    @Qualifier("clientDao")
    private ClientDaoInter clientDaoInter;

    @Autowired
    @Qualifier("vendorDao")
    private VendorDaoInter vendorDaoInter;

    @Autowired
    @Qualifier("projectDao")
    private ProjectDaoInter projectDaoInter;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDaoInter.getAllInvoices();
    }

    @Override
    public List<Invoice> getAllInvoiceByProject(Project project) {
        return invoiceDaoInter.getAllInvoiceByProject(project);
    }

    @Override
    public List<Invoice> getAllInvoiceByDate(Date fromDate, Date toDate) {
        return invoiceDaoInter.getAllInvoiceByDate(fromDate,toDate);
    }

    @Override
    public List<Invoice> getAllInvoiceReport(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate) {
        return invoiceDaoInter.getAllInvoiceByReport(client, vendor, project, paid, fromDate, toDate);
    }


    @Override
    public Invoice getInvoiceById(int id) {
        return invoiceDaoInter.getInvoiceById(id);
    }

    @Override
    public Integer addInvoice(Invoice invoice) {
        return invoiceDaoInter.addInvoice(invoice);
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        return invoiceDaoInter.updateInvoice(invoice);
    }

    @Override
    public boolean deleteInvoice(int id) {
        return invoiceDaoInter.deleteInvoice(id);
    }
}
