package com.kostenko.services;

import com.kostenko.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * The ClientService class declares methods for managing clients as an administrator.
     *
     * @author Artem Kostenko
     */

    void createClient(String name, String surname, String phone);

    void createClient(String name, String surname, int age, String email, String phone);

    void modifyClient(long id, String newName, String newSurname, String newPhone);

    void deleteClient(long id);

    List<Client> getAllClients();
}
