package com.apress.prospring4.ch3.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("constructorConfusion")
public class ConstructorConfusion {
  private String someValue;

  @Autowired
  public ConstructorConfusion(@Value("90") String someValue) {
    System.out.println("ConstructorConfusion(String) called (annotation)");
    this.someValue = someValue;
  }

  public ConstructorConfusion(int someValue) {
    System.out.println("ConstructorConfusion(int) called (annotation)");
    this.someValue = "Number: " + Integer.toString(someValue);
  }

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-annotation.xml");
    context.refresh();

    ConstructorConfusion cc = (ConstructorConfusion) context.getBean("constructorConfusion");
    System.out.println(cc);
  }

  @Override
  public String toString() {
    return someValue;
  }
}
