package com.apress.prospring4.ch6;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:datasource-drivermanager.xml");
        ctx.refresh();
        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
        System.out.println("First name for contact id 1 is: " + contactDao.findFirstNameById(1L));
        System.out.println("Last name for contact id 1 is: " + contactDao.findLastNameById(1L));
        System.out.println("All contacts: " + contactDao.findAll());
        System.out.println("All contacts with detail: " + contactDao.findAllWithDetail());
        System.out.println("Contact by first name: " + contactDao.findByFirstName("Chris"));

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Chris");
        contact.setLastName("John");
        contact.setBirthDate(new Date((new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
        contactDao.update(contact);

        System.out.println("Contact by first name: " + contactDao.findByFirstName("Chris"));
    }
}
