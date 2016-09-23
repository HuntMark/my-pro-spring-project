package com.apress.prospring4.ch3.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimple")
public class InjectSimple {

  @Value("Chris Schaefer (annotation)")
  private String name;

  @Value("32")
  private int age;

  @Value("1.8")
  private float height;

  @Value("false")
  private boolean programmer;

  @Value("2000392309")
  private Long ageInSeconds;

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-annotation.xml");
    context.refresh();

    InjectSimple bean = (InjectSimple) context.getBean("injectSimple");
    System.out.println(bean);
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

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public boolean isProgrammer() {
    return programmer;
  }

  public void setProgrammer(boolean programmer) {
    this.programmer = programmer;
  }

  public Long getAgeInSeconds() {
    return ageInSeconds;
  }

  public void setAgeInSeconds(Long ageInSeconds) {
    this.ageInSeconds = ageInSeconds;
  }

  @Override
  public String toString() {
    return "[name: " + name
            + ", age: " + age
            + ", height: " + height
            + ", programmer: " + programmer
            + ", ageInSeconds: " + ageInSeconds + "]";
  }
}
