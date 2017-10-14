package com.apress.prospring4.ch5.aspectj;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-config-xml.xml");
        context.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
