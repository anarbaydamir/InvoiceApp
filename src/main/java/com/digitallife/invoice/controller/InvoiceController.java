package com.digitallife.invoice.controller;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.entity.Invoice;
import com.digitallife.invoice.entity.Project;
import com.digitallife.invoice.entity.Vendor;
import com.digitallife.invoice.form.InvoiceForm;
import com.digitallife.invoice.service.inter.ClientServiceInter;
import com.digitallife.invoice.service.inter.InvoiceServiceInter;
import com.digitallife.invoice.service.inter.ProjectServiceInter;
import com.digitallife.invoice.service.inter.VendorServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceServiceInter invoiceServiceInter;

    @Autowired
    private ClientServiceInter clientServiceInter;

    @Autowired
    private VendorServiceInter vendorServiceInter;

    @Autowired
    private ProjectServiceInter projectServiceInter;

    @GetMapping(value = "invoice")
    public ModelAndView getInvoice(){
        List<Invoice> invoices = invoiceServiceInter.getAllInvoices();
        List<Client> clients = clientServiceInter.getAllClients();
        List<Vendor> vendors = vendorServiceInter.getAllVendors();
        List<Project> projects = projectServiceInter.getAllProjects();

        ModelAndView mv = new ModelAndView("invoice");
        mv.addObject("invoices",invoices);
        mv.addObject("clients",clients);
        mv.addObject("vendors",vendors);
        mv.addObject("projects",projects);

        return mv;
    }

    @PostMapping(value = "invoice")
    public ModelAndView postInvoice(@ModelAttribute("invoice")InvoiceForm invoiceForm,
                                    RedirectAttributes redirectAttributes) {
        try{
            double taxAmount = 0.00;
            if (invoiceForm.getTaxPerCent() == 18){
                taxAmount = (invoiceForm.getNetAmount() * 18)/100;
            }
            BigDecimal totalAmount = BigDecimal.valueOf(invoiceForm.getNetAmount() + taxAmount);
            Client client = clientServiceInter.getClientById(invoiceForm.getClientName());
            Vendor vendor = vendorServiceInter.getVendorById(invoiceForm.getVendorName());
            Project project = projectServiceInter.getProjectById(invoiceForm.getProjectName());

            //convert string to java.util.date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(invoiceForm.getInvoiceDate());

            Invoice invoice = new Invoice(invoiceForm.getId(),invoiceForm.getInvoiceNumber(),date,
                    BigDecimal.valueOf(invoiceForm.getNetAmount()),
                    BigDecimal.valueOf(taxAmount),totalAmount,invoiceForm.getPaymentStatus(),
                    invoiceForm.getNote(),client,project,vendor);

            if (invoiceForm.getAction().equals("update")){
                invoiceServiceInter.updateInvoice(invoice);
            }
            else if (invoiceForm.getAction().equals("insert")){
                invoiceServiceInter.addInvoice(invoice);
            }
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/invoice");
        return mv;
    }

    @PostMapping(value = "invoice/delete")
    public ModelAndView deleteInvoice(@RequestParam(name = "id") Integer id,
                                      RedirectAttributes redirectAttributes){
        try{
            invoiceServiceInter.deleteInvoice(id);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/invoice");
        return mv;
    }

    @PostMapping(value = "invoice/pay")
    public ModelAndView payInvoice(@RequestParam(name = "id") Integer id,
                                   RedirectAttributes redirectAttributes){
        try {
            Invoice invoice = invoiceServiceInter.getInvoiceById(id);
            invoice.setPaymentStatus((short)1);

            invoiceServiceInter.updateInvoice(invoice);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/invoice");
        return mv;
    }

    @ModelAttribute("invoice")
    public InvoiceForm invoiceForm(){
        return new InvoiceForm();
    }
}
