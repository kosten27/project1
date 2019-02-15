package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static ClientDao clientDao = new ClientDaoImpl();

    private Map<Long, Client> map = new HashMap<>();
    private static long generator = 0;

    private ClientDaoImpl() {

    }

    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving client.... Please wait");
        client.setId(generator++);
        map.put(client.getId(), client);
        return true;
    }

    @Override
    public boolean deleteClient(Client client) {
        System.out.println("Deleting client.... Please wait");
        return true;
    }

    @Override
    public List<Client> getAllClients() {
        System.out.println("Getting clients.... Please wait");
        return new ArrayList<>(map.values());
    }

    public static ClientDao getInstance() {
        return clientDao;
    }

}
