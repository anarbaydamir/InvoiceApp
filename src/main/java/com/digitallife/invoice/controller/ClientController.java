package com.digitallife.invoice.controller;

import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.service.inter.ClientServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientServiceInter clientServiceInter;

    @GetMapping(value = "client")
    public ModelAndView getClient(){
        List<Client> clients = clientServiceInter.getAllClients();
        ModelAndView mv = new ModelAndView("client");
        mv.addObject("clients",clients);

        return mv;
    }

    @PostMapping(value = "client")
    public ModelAndView postClient(@RequestParam(name = "id") Integer id,
                                   @RequestParam(name = "action") String action,
                                   @RequestParam(name = "name") String name,
                                   RedirectAttributes redirectAttributes){
        try {
            Client client = new Client(id,name);
            if (action.equals("update")){
                clientServiceInter.updateClient(client);
            }
            else if (action.equals("insert")){
                clientServiceInter.addClient(client);
            }
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/client");
        return mv;
    }

    @PostMapping(value = "client/delete")
    public ModelAndView deleteClient(@RequestParam(name = "id") Integer id,
                                     RedirectAttributes redirectAttributes){
        try {
            clientServiceInter.deleteClient(id);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message","error");
        }
        redirectAttributes.addFlashAttribute("message","success");
        ModelAndView mv = new ModelAndView("redirect:/client");
        return mv;
    }
}
