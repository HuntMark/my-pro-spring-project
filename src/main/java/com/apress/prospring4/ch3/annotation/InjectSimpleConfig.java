package com.apress.prospring4.ch3.annotation;

import org.springframework.stereotype.Component;

@Component("injectSimpleConfig")
public class InjectSimpleConfig {
  private String name = "Chris Schaefer";
  private int age = 32;
  private float height = 1.8f;
  private boolean programmer = true;
  private Long ageInSeconds = 123450000L;

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
}
