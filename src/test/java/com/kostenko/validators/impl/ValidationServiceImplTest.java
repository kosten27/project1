package com.kostenko.validators.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationServiceImplTest {

    private ClientDao clientDao;
    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationServiceImpl(clientDao);
    }

    @Test
    public void validateAgeTest() throws BusinessException {
        //WHEN
        int age = 50;
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongAgeTest() throws BusinessException {
        //WHEN
        int age = 201;
        validationService.validateAge(age);
    }

}