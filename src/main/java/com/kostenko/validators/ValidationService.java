package com.kostenko.validators;


import com.kostenko.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

}
