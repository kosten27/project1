package com.kostenko.validators.impl;


import com.kostenko.dao.ClientDao;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationServiceImpl implements ValidationService{
    @Autowired
    private final ClientDao clientDao;

    public ValidationServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void validateAge(int age) throws BusinessException {
        if(age < 6 || age > 200) {
            throw new BusinessException("Incorrect age. Age must be more than 5 and less than 201.");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        Pattern pattern = Pattern.compile("^(\\w+(\\.\\w+)*@[\\w-]+(\\.\\w)*(\\.\\w{2,}))$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new BusinessException("Incorrect email.");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        Pattern pattern = Pattern.compile("^(0(67|97|50)\\d{7})$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new BusinessException("Incorrect phone. Phone must have 10 digits and have an operator code 067, 097 or 050.");
        }
    }

    @Override
    public void validatePhoneUsed(String phone) throws BusinessException {
        if (clientDao.phoneUsed(phone)) {
            throw new BusinessException("Phone already used.");
        }
    }

    @Override
    public void validateClientExists(long clientId) throws BusinessException {
        if (clientDao.getClient(clientId) == null) {
            throw new BusinessException("Client with id "  + clientId + " not found.");
        }
    }

    @Override
    public void validateClientField(int age, String email) throws BusinessException {
        validateAge(age);
        validateEmail(email);
    }

    @Override
    public void validateClientField(int age, String email, String phone) throws BusinessException {
        validateAge(age);
        validateEmail(email);
        validatePhone(phone);
        validatePhoneUsed(phone);
    }
}
