package com.apress.prospring4.ch8;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJpaSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-jpa.xml");
        ctx.refresh();
        ContactService contactService = ctx.getBean(
                "springJpaContactService", ContactService.class);
        listContacts("Find all:", contactService.findAll());
        listContacts("Find by first name:", contactService.findByFirstName("Chris"));
        listContacts("Find by first and last name:",
                contactService.findByFirstNameAndLastName("Chris", "Schaefer"));
    }

    private static void listContacts(String message, List<Contact> contacts) {
        System.out.println("");
        System.out.println(message);
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }
}
