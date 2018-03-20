package com.apress.prospring4.ch9;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxProgrammaticSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-tx-programmatic-style.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("contactService", ContactService.class);
        System.out.println("Contact count: " + contactService.countAll());
    }
}
