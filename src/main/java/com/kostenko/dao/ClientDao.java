package com.kostenko.dao;

import com.kostenko.domain.Client;

public interface ClientDao {
    boolean saveClient(Client client);
    boolean deleteClient(Client client);
    Client[] getClients();
}
