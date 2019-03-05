package com.kostenko.dao;

import com.kostenko.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * The ClientDao class declares methods for managing clients in the database as an administrator.
     *
     * @author Artem Kostenko
     */

    boolean saveClient(Client client);

    boolean clientFound(long clientId);

    boolean updateClient(Client client);

    boolean deleteClient(long clientId);

    Client getClient(long clientId);

    List<Client> getAllClients();

    boolean phoneUsed(String phone);
}
