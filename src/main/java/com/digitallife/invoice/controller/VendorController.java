package com.digitallife.invoice.controller;

import com.digitallife.invoice.entity.Vendor;
import com.digitallife.invoice.service.inter.VendorServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VendorController {

    @Autowired
    private VendorServiceInter vendorServiceInter;

    @GetMapping(value = "vendor")
    public ModelAndView getVendor(){
        List<Vendor> vendors = vendorServiceInter.getAllVendors();

        ModelAndView mv = new ModelAndView("vendor");
        mv.addObject("vendors",vendors);

        return mv;
    }

    @PostMapping(value = "vendor")
    public ModelAndView postVendor(@RequestParam(name = "id") Integer id,
                                   @RequestParam(name = "action") String action,
                                   @RequestParam(name = "name") String name,
                                   RedirectAttributes redirectAttributes){
        try {
            Vendor vendor = new Vendor(id,name);
            if (action.equals("update")){
                vendorServiceInter.updateVendor(vendor);
            }
            else if (action.equals("insert")){
                vendorServiceInter.addVendor(vendor);
            }
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/vendor");
        return mv;
    }

    @PostMapping(value = "vendor/delete")
    public ModelAndView deleteVendor(@RequestParam(name = "id") Integer id,
                                     RedirectAttributes redirectAttributes){
        try{
            vendorServiceInter.removeVendor(id);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/vendor");
        return mv;
    }
}
