package com.digitallife.invoice.service.inter;

import com.digitallife.invoice.entity.*;

import java.util.Date;
import java.util.List;

public interface InvoiceServiceInter {

    public List<Invoice> getAllInvoices();

    public List<Invoice> getAllInvoiceByProject(Project project);

    public List<Invoice> getAllInvoiceByDate(Date fromDate, Date toDate);

    public List<Invoice> getAllInvoiceReport(Client client, Vendor vendor, Project project, Short paid, Date fromDate, Date toDate);

    public Invoice getInvoiceById(int id);

    public Integer addInvoice(Invoice invoice);

    public boolean updateInvoice(Invoice invoice);

    public boolean deleteInvoice(int id);
}
