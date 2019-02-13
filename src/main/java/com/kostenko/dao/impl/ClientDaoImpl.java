package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

import java.util.ArrayList;
import java.util.List;

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
    public List<Client> getClients() {
        System.out.println("Getting clients.... Please wait");
        List<Client> clients = new ArrayList<>();
        return clients;
    }
}
