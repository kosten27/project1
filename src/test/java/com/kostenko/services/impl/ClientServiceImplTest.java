package com.kostenko.services.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;
import com.kostenko.validators.ValidationService;
import com.kostenko.validators.impl.ValidationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    private ClientDao clientDao;

    private ValidationService validationService = Mockito.mock(ValidationService.class);

    private ClientService clientService;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(clientDao, validationService);
    }

    @Test
    public void createClientWithFullParametersTest() throws Exception {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0502254850";
        String email = "test@gmail.com";
        when(clientDao.saveClient(any())).thenReturn(true);

        //WHEN
        clientService.createClient(name, surname, age, email, phone);

        //THEN
        verify(clientDao, times(1)).saveClient(any());
    }

    @Test
    public void findClient() {
        //GIVEN
        long id = 1;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0502254850";
        String email = "test@gmail.com";
        Client expectedClient = new Client(id, name, surname, age, email, phone);
        when(clientDao.clientFound(id)).thenReturn(expectedClient.getId() == id);

        //WHEN
        boolean b = clientService.clientFound(id);

        //THEN
        verify(clientDao, Mockito.times(1)).clientFound(id);
        Assert.assertTrue(b);
    }

    @Test
    public void modifyClientTest() {
        //GIVEN
        long id = 1;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0502254850";
        String email = "test@gmail.com";
        Client client = new Client(id, name, surname, age, email, phone);
        String newName = "new name";
        when(clientDao.getClient(id)).thenReturn(client);
        when(clientDao.updateClient(client)).thenReturn(true);

        //WHEN
        clientService.modifyClient(id, newName, surname, age, email, phone);

        //THEN
        verify(clientDao).getClient(id);
        verify(clientDao).updateClient(client);
    }

    @Test
    public void deleteClientTest() {
        //GIVEN
        long id = 1;
        when(clientDao.deleteClient(id)).thenReturn(true);

        //WHEN
        clientService.deleteClient(id);

        //THEN
        verify(clientDao, Mockito.times(1)).deleteClient(id);
    }

    @Test
    public void getAllClientsTest() {
        //GIVEN
        List<Client> clients = new ArrayList<>();
        when(clientDao.getAllClients()).thenReturn(clients);

        //WHEN
        clientService.getAllClients();

        //THEN
        verify(clientDao, times(1)).getAllClients();
    }
}