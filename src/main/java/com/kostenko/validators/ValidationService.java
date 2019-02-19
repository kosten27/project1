package com.kostenko.validators;


import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;
import com.kostenko.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

    void validateEmail(String email) throws BusinessException;

    void validatePhone(String phone) throws BusinessException;

    void validatePhoneUsed(String phone) throws BusinessException;

    void validateClientExists(long clientId) throws BusinessException;

    void validateClientField(int age, String email) throws BusinessException;

    void validateClientField(int age, String email, String phone) throws BusinessException;
}
