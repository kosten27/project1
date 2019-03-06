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
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    private ClientDao clientDao;
//    private ClientDao clientDao = Mockito.mock(ClientDao.class);

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

        //WHEN
        long clientId = clientService.createClient(name, surname, age, email, phone);

        //THEN
    }

    @Test
    public void findClient() {
        //GIVEN
        long id = 0;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0502254850";
        String email = "test@gmail.com";
        Client expectedClient = new Client(id, name, surname, age, email, phone);
        Mockito.when(clientDao.clientFound(id)).thenReturn(expectedClient.getId() == id);
        Mockito.when(clientDao.clientFound(0)).thenReturn(expectedClient.getId() == id);

        //WHEN
        boolean b = clientService.clientFound(id);

        //THEN
        Mockito.verify(clientDao, Mockito.times(1)).clientFound(id);
        Assert.assertTrue(b);

    }



}