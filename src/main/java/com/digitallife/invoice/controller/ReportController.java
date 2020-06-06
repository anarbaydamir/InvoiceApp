package com.digitallife.invoice.controller;

import com.digitallife.invoice.entity.*;
import com.digitallife.invoice.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ClientServiceInter clientServiceInter;

    @Autowired
    private VendorServiceInter vendorServiceInter;

    @Autowired
    private ProjectServiceInter projectServiceInter;

    @Autowired
    private InvoiceServiceInter invoiceServiceInter;

    @Autowired
    @Qualifier(value = "projectAmountService")
    private AmountServiceInter<Project,Object> amountProjectServiceInter;

    @Autowired
    @Qualifier(value = "dateAmountService")
    private  AmountServiceInter<Date,Date> amountDateServiceInter;

    @Autowired
    @Qualifier(value = "allAmountService")
    private AllAmountServiceInter allAmountServiceInter;

    @GetMapping(value = "projectreport")
    public ModelAndView getProject(@RequestParam(name = "projectId",required = false) Integer projectId){
        ModelAndView mv = new ModelAndView("reportbyproject");
        List<Project> projects = projectServiceInter.getAllProjects();
        mv.addObject("projects",projects);
        if (projectId != null){
            Project project = projectServiceInter.getProjectById(projectId);
            List<Invoice> invoices = invoiceServiceInter.getAllInvoiceByProject(project);
            mv.addObject("invoices",invoices);
            ClientVendorAmount clientAmount = amountProjectServiceInter.getClientAmount(project,new Object());
            mv.addObject("clientAmount",clientAmount);
            ClientVendorAmount vendorAmount = amountProjectServiceInter.getVendorAmount(project,new Object());
            mv.addObject("vendorAmount",vendorAmount);
            ClientVendorAmount expectedClientAmount = amountProjectServiceInter.getExpectedClientAmount(project,new Object());
            mv.addObject("expectedClientAmount",expectedClientAmount);
            ClientVendorAmount expectedVendorAmount = amountProjectServiceInter.getExpectedVendorAmount(project,new Object());
            mv.addObject("expectedVendorAmount",expectedVendorAmount);
        }
        return mv;
    }

    @GetMapping(value = "datereport")
    public ModelAndView getDate(@RequestParam(name = "fromDate", required = false) String fromDate,
                                @RequestParam(name = "toDate",required = false) String toDate) throws ParseException {
        ModelAndView mv = new ModelAndView("reportbydate");
        if (fromDate != null && toDate != null){
            //convert string to date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDateDt = dateFormat.parse(fromDate);
            Date toDateDt = dateFormat.parse(toDate);
            List<Invoice> invoices = invoiceServiceInter.getAllInvoiceByDate(fromDateDt,toDateDt);
            mv.addObject("invoices",invoices);
            ClientVendorAmount clientAmount = amountDateServiceInter.getClientAmount(fromDateDt,toDateDt);
            mv.addObject("clientAmount",clientAmount);
            ClientVendorAmount vendorAmount = amountDateServiceInter.getVendorAmount(fromDateDt,toDateDt);
            mv.addObject("vendorAmount",vendorAmount);
            ClientVendorAmount expectedClientAmount = amountDateServiceInter.getExpectedClientAmount(fromDateDt,toDateDt);
            mv.addObject("expectedClientAmount",expectedClientAmount);
            ClientVendorAmount expectedVendorAmount = amountDateServiceInter.getExpectedVendorAmount(fromDateDt,toDateDt);
            mv.addObject("expectedVendorAmount",expectedVendorAmount);
        }
        return mv;
    }

    @GetMapping(value = "report")
    public ModelAndView getReport(@RequestParam(name = "clientName",required = false) Integer clientName,
                                  @RequestParam(name = "vendorName",required = false) Integer vendorName,
                                  @RequestParam(name = "projectName",required = false) Integer projectName,
                                  @RequestParam(name = "paid", required = false) Short paid,
                                  @RequestParam(name = "fromDate",required = false) String fromDate,
                                  @RequestParam(name = "toDate",required = false) String toDate) throws ParseException{
        List<Client> clients = clientServiceInter.getAllClients();
        List<Vendor> vendors = vendorServiceInter.getAllVendors();
        List<Project> projects = projectServiceInter.getAllProjects();

        ModelAndView mv = new ModelAndView("report");
        mv.addObject("clients",clients);
        mv.addObject("vendors",vendors);
        mv.addObject("projects",projects);
        Client client = null;
        Vendor vendor = null;
        Project project = null;
        if (clientName!=null) {
            if (clientName != -1)
                client = clientServiceInter.getClientById(clientName);
        }
        if (clientName!=null) {
            if (vendorName != -1)
                vendor = vendorServiceInter.getVendorById(vendorName);
        }
        if (clientName!=null) {
            if (projectName != -1)
                project = projectServiceInter.getProjectById(projectName);
        }
        if (paid != null) {
            if (paid == -1)
                paid = null;
        }
        //convert string to date
        Date fromDateDt = null;
        Date toDateDt = null;
        if (fromDate !=null && toDate != null) {
            if (!fromDate.trim().isEmpty() && toDate.trim().isEmpty()){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fromDateDt = dateFormat.parse(fromDate);
                toDateDt = dateFormat.parse(toDate);
            }
        }
        List<Invoice> invoices = invoiceServiceInter.getAllInvoiceReport(client,vendor,project,paid,fromDateDt,toDateDt);
        ClientVendorAmount allAmounts = allAmountServiceInter.allAmount(client,vendor,project,paid,fromDateDt,toDateDt);
        mv.addObject("invoices",invoices);
        mv.addObject("allAmounts",allAmounts);
        return mv;
    }
}
