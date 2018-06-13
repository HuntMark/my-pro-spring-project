package com.apress.prospring4.ch8.enversexample;

import org.springframework.context.support.GenericXmlApplicationContext;

public class EnversExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-envers-example.xml");
        ctx.refresh();

        MessageService service = ctx.getBean("messageService", MessageService.class);

        System.out.println("-----------------");

        Message message = new MessageFrom();
        message.setText("Some message FROM!");
        message = service.save(message);

        System.out.println("----- List -----");
        service.findAll()
                .forEach(System.out::println);

        message = service.findAuditByRevision(message.getId(), 1);
        System.out.println("History: " + message);

        message = new MessageTo();
        message.setText("Some message TO!");
        message = service.save(message);


        System.out.println("----- List -----");
        service.findAll()
                .forEach(System.out::println);

        message = service.findAuditByRevision(message.getId(), 2);
        System.out.println("History: " + message);
    }
}
