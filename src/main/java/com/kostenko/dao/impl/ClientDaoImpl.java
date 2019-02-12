package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving client.... Please wait");
        return true;
    }

    @Override
    public boolean deleteClient(Client client) {
        System.out.println("Deleting client.... Please wait");
        return true;
    }

    @Override
    public Client[] getClients() {
        System.out.println("Getting clients.... Please wait");
        Client[] clients = new Client[10];
        return clients;
    }
}
