package com.example.jsr349;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("myBeanValidationService")
public class MyBeanValidationService {

    private Validator validator;

    @Autowired
    public MyBeanValidationService(Validator validator) {
        this.validator = validator;
    }

    public Set<ConstraintViolation<Customer>> validateCustomer(Customer customer) {
        return validator.validate(customer);
    }
}
