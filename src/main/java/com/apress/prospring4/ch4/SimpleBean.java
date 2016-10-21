package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBean {
  private static final String DEFAULT_NAME = "Luke Skywalker";

  private String name;
  private int age = Integer.MIN_VALUE;

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void init() {
    System.out.println("Initializing bean");

    if (this.name == null) {
      System.out.println("Using default name");
      this.name = DEFAULT_NAME;
    }

    if (this.age == Integer.MIN_VALUE) {
      throw new IllegalArgumentException("You must set the age property of any beans of type "
              + SimpleBean.class);
    }
  }

  @Override
  public String toString() {
    return "SimpleBean{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
  }

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-xml.xml");
    context.refresh();

    SimpleBean simpleBean1 = getBean("simpleBean1", context);
    SimpleBean simpleBean2 = getBean("simpleBean2", context);
    SimpleBean simpleBean3 = getBean("simpleBean3", context);
  }

  private static SimpleBean getBean(String beanName, ApplicationContext context) {
    try {
      SimpleBean bean = (SimpleBean) context.getBean(beanName);
      System.out.println(bean);
      return bean;
    } catch (BeanCreationException ex) {
      System.out.println("An error occured in bean configuration: " + ex.getMessage());
      return null;
    }
  }
}
