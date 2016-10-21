package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBean {
  private String name;
  private int age;

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-xml.xml");
    context.refresh();

    SimpleBean parent = (SimpleBean) context.getBean("inheritParent");
    SimpleBean child = (SimpleBean) context.getBean("inheritChild");

    System.out.println(parent);
    System.out.println(child);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "SimpleBean{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
  }
}
