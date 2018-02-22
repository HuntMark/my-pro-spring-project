package com.apress.prospring4.ch7;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringHibernateSample {

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-hibernate.xml");
        context.refresh();

        ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

        Contact contact = contactDao.findById(1L);
        contactDao.delete(contact);
        listContactsWithDetails(contactDao.findAllWithDetail());
    }

    private static void listContactsWithDetails(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contact without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);

            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }

            if (contact.getHobbies() != null) {
                for (Hobby hobby : contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }

            System.out.println();
        }
    }
}
