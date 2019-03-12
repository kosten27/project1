package com.kostenko.validators.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

    @Mock
    private ClientDao clientDao;
    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationServiceImpl(clientDao);
    }

    @Test
    public void validateAgeTest() throws BusinessException {
        //GIVEN
        int age = 50;

        //WHEN
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongAgeTest() throws BusinessException {
        //GIVEN
        int age = 201;

        //WHEN
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateSmallAgeTest() throws BusinessException {
        //GIVEN
        int age = 5;

        //WHEN
        validationService.validateAge(age);
    }

    @Test
    public void validateEmailTest() throws BusinessException {
        //GIVEN
        String email = "test@test.com";

        //WHEN
        validationService.validateEmail(email);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongEmailTest() throws BusinessException {
        //GIVEN
        String email = "test@@test.com";

        //WHEN
        validationService.validateEmail(email);
    }

    @Test
    public void validatePhoneTest() throws BusinessException {
        //GIVEN
        String phone = "0502254850";

        //WHEN
        validationService.validatePhone(phone);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongPhoneTest() throws BusinessException {
        //GIVEN
        String phone = "0952254850";

        //WHEN
        validationService.validatePhone(phone);
    }

    @Test
    public void validatePhoneUsedFalseTest() throws BusinessException {
        //GIVEN
        String phone = "0502254850";
        when(clientDao.phoneUsed(phone)).thenReturn(false);

        //WHEN
        validationService.validatePhoneUsed(phone);

        //THEN
        verify(clientDao, times(1)).phoneUsed(phone);
    }

    @Test(expected = BusinessException.class)
    public void validatePhoneUsedTrueTest() throws BusinessException {
        //GIVEN
        String phone = "0502254850";
        when(clientDao.phoneUsed(phone)).thenReturn(true);

        //WHEN
        validationService.validatePhoneUsed(phone);

        //THEN
        verify(clientDao, times(1)).phoneUsed(phone);
    }

    @Test(expected = BusinessException.class)
    public void validateClientExistsFalseTest() throws BusinessException {
        //GIVEN
        long clientId = 1;
        Client client = null;
        when(clientDao.getClient(clientId)).thenReturn(client);

        //WHEN
        validationService.validateClientExists(clientId);
    }

    @Test
    public void validateClientExistsTrueTest() throws BusinessException {
        //GIVEN
        long clientId = 1;
        Client client = new Client(clientId);
        when(clientDao.getClient(clientId)).thenReturn(client);

        //WHEN
        validationService.validateClientExists(clientId);
    }
}