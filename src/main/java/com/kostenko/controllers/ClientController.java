package com.kostenko.controllers;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.services.ClientService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.validators.impl.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients2")
public class ClientController {
//    public static final ClientDBDao CLIENT_DAO = new ClientDBDao(new DataSourceDB());
    @Autowired
//    private ClientService clientService = new ClientServiceImpl(CLIENT_DAO, new ValidationServiceImpl(CLIENT_DAO));
    private ClientService clientService;

    @GetMapping()
    public String showClients(ModelMap modelMap){
        System.out.println("showClients");
        modelMap.put("message", clientService.getAllClients());
        return "clients";
    }

    @PostMapping()
    public void postClient(@RequestParam String name, @RequestParam String surname, @RequestParam String phone) {
        clientService.createClient(name, surname, phone);
    }
}
