package com.apress.prospring4.ch8;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJpaSample {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-jpa.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("jpaContactService", ContactService.class);
        Contact contact = contactService.findById(1L);
        contactService.delete(contact);
        listContacts(contactService.findAllByNativeQuery());
    }

    private static void listContacts(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts without details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }

    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts with details:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail :
                        contact.getContactTelDetails()) {
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