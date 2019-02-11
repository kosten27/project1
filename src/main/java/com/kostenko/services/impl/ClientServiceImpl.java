package com.kostenko.services.impl;
import com.kostenko.dao.ClientDao;
import com.kostenko.dao.impl.ClientDaoImpl;
import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(String name, String surname, String phone) {

        Client client = new Client(name, surname, phone);
        boolean result = clientDao.saveClient(client);
        if(result) {
            System.out.println("Client saved: " + client);
        }
    }

    @Override
    public void deleteClient() {

    }
}
