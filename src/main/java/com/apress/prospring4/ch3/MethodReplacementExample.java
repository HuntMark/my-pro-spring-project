package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementExample {
  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-xml.xml");
    context.refresh();

    ReplacementTarget replacementTarget = (ReplacementTarget) context.getBean("replacementTarget");
    ReplacementTarget standardTarget = (ReplacementTarget) context.getBean("standardTarget");

    displayInfo(replacementTarget);
    displayInfo(standardTarget);
  }

  private static void displayInfo(ReplacementTarget target) {
    System.out.println(target.formatMessage("Hello World!"));

    StopWatch stopWatch = new StopWatch();
    stopWatch.start("perfTest");

    for (int x = 0; x < 1000000; x++) {
      String out = target.formatMessage("foo");
    }

    stopWatch.stop();

    System.out.println("1000000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");
  }
}
