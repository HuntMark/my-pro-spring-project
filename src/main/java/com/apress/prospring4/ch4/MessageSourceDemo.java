package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

        Locale english = Locale.ENGLISH;
        Locale czech = new Locale("cs", "CZ");
        Locale russian = new Locale("ru", "RU");

        System.out.println(context.getMessage("msg", null, english));
        System.out.println(context.getMessage("msg", null, czech));
        System.out.println(context.getMessage("msg", null, russian));

        System.out.println(context.getMessage("nameMsg", new Object[] {"Chris", "Schaefer"}, english));
    }
}
