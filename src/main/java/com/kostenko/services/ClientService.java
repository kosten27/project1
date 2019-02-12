package com.kostenko.services;

public interface ClientService {

    /**
     * add documentaition
     */
    void createClient(String name, String surname, String phone);
    void modifyClient(long id, String newName, String newSurname, String newPhone);
    void deleteClient(long id);
    void showClients();

}
