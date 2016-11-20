package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanNamePrinterExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-xml.xml");
        context.refresh();

        BeanNamePrinter printer = (BeanNamePrinter) context.getBean("beanNamePrinter");
        printer.someOperation();
    }
}
