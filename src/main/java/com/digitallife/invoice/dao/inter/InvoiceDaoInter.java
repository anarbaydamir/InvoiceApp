package com.digitallife.invoice.dao.inter;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.Invoice;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;

import java.util.Date;
import java.util.List;

public interface InvoiceDaoInter {

    public List<Invoice> getAllInvoices();

    public List<Invoice> getAllInvoiceByProject(Project project);

    public List<Invoice> getAllInvoiceByDate(Date fromDate,Date toDate);

    public List<Invoice> getAllInvoiceByReport(Client client, Vendor vendor,Project project,Short paid,Date fromDate,Date toDate);

    public Invoice getInvoiceById(int id);

    public Integer addInvoice(Invoice invoice);

    public boolean updateInvoice(Invoice invoice);

    public boolean deleteInvoice(int id);
}
