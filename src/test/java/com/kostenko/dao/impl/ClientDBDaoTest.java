package com.kostenko.dao.impl;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.domain.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ClientDBDaoTest {

    private static final String URL = "jdbc:h2:mem:LuxoftShop;DB_CLOSE_DELAY=-1";

    private DataSourceDB dataSource;
    private ClientDBDao clientDao;

    @Before
    public void setUp() {
        dataSource = new DataSourceDB(URL);
        clientDao = new ClientDBDao(dataSource);
    }

    @Test
    public void saveClientTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        boolean result = clientDao.saveClient(client);
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertTrue(result);
    }

    @Ignore
    private Client getTestClient() {
        long clientId = 1;
        String clientName = "test";
        String clientSurname = "test";
        int clientAge = 10;
        String clientPhone = "0502254850";
        String clientEmail = "test@gmail.com";
        return new Client(clientId, clientName, clientSurname, clientAge, clientEmail, clientPhone);
    }

    @Test
    public void clientFoundTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        boolean result = clientDao.clientFound(client.getId());
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void updateClientTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        client.setAge(client.getAge() + 1);
        clientDao.updateClient(client);
        Client newClient = clientDao.getClient(client.getId());
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertEquals(client.getAge(), newClient.getAge());
    }

    @Test
    public void deleteClientTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        clientDao.deleteClient(client.getId());
        Client newClient = clientDao.getClient(client.getId());

        //THEN
        Assert.assertNull(newClient);
    }

    @Test
    public void getClientTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        Client newClient = clientDao.getClient(client.getId());
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertNotNull(newClient);
    }

    @Test
    public void getAllClientsTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        List<Client> clients = clientDao.getAllClients();
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertTrue(clients.size() == 1);
    }

    @Test
    public void phoneUsedTest() {
        //GIVEN
        Client client = getTestClient();

        //WHEN
        clientDao.saveClient(client);
        boolean phoneUsed = clientDao.phoneUsed(client.getPhone());
        clientDao.deleteClient(client.getId());

        //THEN
        Assert.assertTrue(phoneUsed);
    }
}