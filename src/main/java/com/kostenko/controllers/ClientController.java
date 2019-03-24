package com.kostenko.controllers;

import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
@Autowired
private ClientService clientService;

    @GetMapping("/clients")
    public String showClients(ModelMap modelMap){
        List<String> clients = new ArrayList<>();
        for (Client client : clientService.getAllClients()) {
            clients.add(client.toString());
        }
        modelMap.put("clients", clients);
        return "clients/clients";
    }

    @PostMapping("/clients")
    public String postClient(ModelMap modelMap, @RequestParam(required = false) String method, @RequestParam(required = false) Long clientId, @RequestParam(required = false) String name, @RequestParam(required = false) String surname, @RequestParam(required = false) Integer age, @RequestParam(required = false) String email, @RequestParam(required = false) String phone) {
        if ("delete".equals(method)) {
            return deleteClient(modelMap, clientId);
        } else if ("put".equals(method)) {
            return putClient(modelMap, clientId, name, surname, age, email, phone);
        }

        clientService.createClient(name, surname, age, email, phone);
        return showClients(modelMap);
    }

    @PutMapping("/clients")
    public String putClient(ModelMap modelMap, @RequestParam long clientId, @RequestParam String name, @RequestParam String surname, @RequestParam int age, @RequestParam String email, @RequestParam String phone) {
        clientService.modifyClient(clientId, name, surname, age, email, phone);
        return showClients(modelMap);
    }

    @DeleteMapping("/clients")
    public String deleteClient(ModelMap modelMap, @RequestParam long clientId) {
        clientService.deleteClient(clientId);
        return showClients(modelMap);
    }

    @GetMapping("/clients/addClient")
    public String mappingAddClient(ModelMap modelMap) {
        return "clients/addClient";
    }

    @GetMapping("/clients/modifyClient")
    public String mappingModifyClient(ModelMap modelMap) {
        return "clients/modifyClient";
    }

    @GetMapping("/clients/deleteClient")
    public String mappingDeleteClient(ModelMap modelMap) {
        return "clients/deleteClient";
    }
}
