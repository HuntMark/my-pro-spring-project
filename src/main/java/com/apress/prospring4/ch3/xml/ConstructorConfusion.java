package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConstructorConfusion {
  private String someValue;

  public ConstructorConfusion(String someValue) {
    System.out.println("ConstructorConfusion(String) called");
    this.someValue = someValue;
  }

  public ConstructorConfusion(int someValue) {
    System.out.println("ConstructorConfusion(int) called");
    this.someValue = "Number: " + Integer.toString(someValue);
  }

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-xml.xml");
    context.refresh();

    ConstructorConfusion cc = (ConstructorConfusion) context.getBean("constructorConfusion");
    System.out.println(cc);
  }

  @Override
  public String toString() {
    return someValue;
  }
}
