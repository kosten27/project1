package com.kostenko.services.impl;
import com.kostenko.dao.ClientDao;
import com.kostenko.dao.impl.ClientDaoImpl;
import com.kostenko.domain.Client;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.services.ClientService;
import com.kostenko.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surname, String phone) {

        createClient(name, surname, 0, null, phone);
    }

    @Override
    public void createClient(String name, String surname, int age, String email, String phone) {

        try {
            validationService.validateAge(age);
            validationService.validateEmail(email);
            validationService.validatePhone(phone);
            validationService.validatePhoneUsed(clientDao, phone);
            Client client = new Client(name, surname, age, email, phone);
            boolean result = clientDao.saveClient(client);
            if(result) {
                System.out.println("Client saved: " + client);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyClient(long id, String newName, String newSurname, String newPhone) {
        Client client = new Client(id);
        client.setName(newName);
        client.setSurname(newSurname);
        client.setPhone(newPhone);
        boolean result = clientDao.saveClient(client);
        if(result) {
            System.out.println("Modify client: " + client);
        }
    }

    @Override
    public void deleteClient(long id) {
        Client client = new Client(id);
        boolean result = clientDao.deleteClient(client);
        if(result) {
            System.out.println("Delete client: " + client);
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
