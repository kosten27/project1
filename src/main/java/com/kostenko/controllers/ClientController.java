package com.kostenko.controllers;

import com.kostenko.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clients2")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients2")
    public String showClients(ModelMap modelMap){
        System.out.println("showClients");
        modelMap.put("message", clientService.getAllClients());
        return "clients";
    }
}
