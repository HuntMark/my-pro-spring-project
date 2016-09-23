package com.apress.prospring4.ch3.xml;

import com.apress.prospring4.ch3.Oracle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {
  private Oracle oracle;

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-xml.xml");
    context.refresh();

    InjectRef bean = (InjectRef) context.getBean("injectRef");
    System.out.println(bean);
  }

  public void setOracle(Oracle oracle) {
    this.oracle = oracle;
  }

  @Override
  public String toString() {
    return oracle.defineMeaningOfLife();
  }
}
