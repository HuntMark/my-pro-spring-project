package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {
  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    // You can swap app-context-xml.xml with app-context-annotation.xml and vice versa
    context.load("classpath:app-context-annotation.xml");
    context.refresh();

    MessageProvider messageProvider = context.getBean("messageProvider", MessageProvider.class);
    System.out.println(messageProvider.getMessage());
  }
}
