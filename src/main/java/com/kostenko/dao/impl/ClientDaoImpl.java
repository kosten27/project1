package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private static ClientDao clientDao;

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
    public boolean updateClient(Client client) {
        System.out.println("Updating client.... Please wait");
        map.put(client.getId(), client);
        return true;
    }

    @Override
    public boolean deleteClient(long clientId) {
        System.out.println("Deleting client.... Please wait");
        if (map.containsKey(clientId)) {
            map.remove(clientId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Client getClient(long clientId) {
        System.out.println("Getting client.... Please wait");
        return map.get(clientId);
    }

    @Override
    public List<Client> getAllClients() {
        System.out.println("Getting clients.... Please wait");
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean phoneUsed(String phone) {
        for (Map.Entry<Long, Client> entry : map.entrySet()) {
            if (entry.getValue().getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public static ClientDao getInstance() {
        if(clientDao == null) {
            synchronized(ClientDaoImpl.class) {
                if(clientDao == null) {
                    clientDao = new ClientDaoImpl();
                }
            }
        }
        return clientDao;
    }

}
