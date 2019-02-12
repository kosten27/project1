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
    public void modifyClient(long id, String newName, String newSurname, String newPhone) {
        Client client = new Client(id);
        client.setName(newName);
        client.setSurname(newSurname);
        client.setPhone(newPhone);
        boolean result = clientDao.saveClient(client);
        if(result) {
            System.out.println("Modify client: " + client);
        }
    }

    @Override
    public void deleteClient(long id) {
        Client client = new Client(id);
        boolean result = clientDao.deleteClient(client);
        if(result) {
            System.out.println("Delete client: " + client);
        }
    }

    @Override
    public void showClients() {
        Client[] clients = clientDao.getClients();
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
