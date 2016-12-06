package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr330Example {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-annotation.xml");
        context.refresh();

        MessageRenderer renderer = context.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
