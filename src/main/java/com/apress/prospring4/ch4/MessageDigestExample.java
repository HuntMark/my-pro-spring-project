package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

        MessageDigester digester = (MessageDigester) context.getBean("digester");
        digester.digest("HelloWorld!");
    }
}
