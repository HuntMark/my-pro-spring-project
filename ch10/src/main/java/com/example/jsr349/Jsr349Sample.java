package com.example.jsr349;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349Sample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:jsr349-app-context.xml");
        context.refresh();

        MyBeanValidationService validationService = context.getBean("myBeanValidationService", MyBeanValidationService.class);

        Customer customer = new Customer();
        customer.setFirstName("C");
        customer.setLastName("Schaefer");
        customer.setCustomerType(null);
        customer.setGender(null);

        validateCustomer(customer, validationService);
    }

    private static void validateCustomer(Customer customer, MyBeanValidationService validationService) {
        Set<ConstraintViolation<Customer>> violations = validationService.validateCustomer(customer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
        System.out.println("No. of violations: " + violations.size());

        for (ConstraintViolation<Customer> violation : violations) {
            System.out.println("Validation error for property: " + violation.getPropertyPath()
                    + " with value: " + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        }
    }
}
