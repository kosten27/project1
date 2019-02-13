package com.kostenko.services;

public interface ClientService {

    /**
     * The ClientService class declares methods for managing clients as an administrator.
     *
     * @author Artem Kostenko
     */

    void createClient(String name, String surname, String phone);

    void modifyClient(long id, String newName, String newSurname, String newPhone);

    void deleteClient(long id);

    void showClients();
}
