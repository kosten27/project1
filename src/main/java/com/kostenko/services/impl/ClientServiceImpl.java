package com.kostenko.services.impl;
import com.kostenko.dao.ClientDao;
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
    public long createClient(String name, String surname, int age, String email, String phone) {
        try {
            validationService.validateClientField(age, email, phone);
            Client client = new Client(name, surname, age, email, phone);
            boolean result = clientDao.saveClient(client);
            if(result) {
                System.out.println("Client saved: " + client);
                return client.getId();
            } else {
                System.out.println("Client not saved.");
            }
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean clientFound(long clientId) {
        return clientDao.clientFound(clientId);
    }

    @Override
    public void modifyClient(long clientId, String name, String surname, int age, String email, String phone) {

        try {
            validationService.validateClientField(age, email);
            Client client = clientDao.getClient(clientId);
            if (client != null) {
                client.setName(name);
                client.setSurname(surname);
                client.setAge(age);
                client.setEmail(email);
                if (!client.getPhone().equals(phone)) {

                    validationService.validatePhone(phone);
                    validationService.validatePhoneUsed(phone);
                    client.setPhone(phone);
                }

                boolean result = clientDao.updateClient(client);
                if (result) {
                    System.out.println("Modify client: " + client);
                } else {
                    System.out.println("Client wasn't modify.");
                }
            } else {
                System.out.println("Client wasn't found.");
            }
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteClient(long clientId) {
        boolean result = clientDao.deleteClient(clientId);
        if (result) {
            System.out.println("Delete client with id: " + clientId);
        } else {
            System.out.println("Can't delete client with id: " + clientId);
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    @Override
    public Client getClient(long id) {
        return clientDao.getClient(id);
    }
}
