package com.apress.prospring4.ch5.aspectjstyle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJAnnotationExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-config-annotation.xml");
        context.refresh();

        MyBean myBean = (MyBean) context.getBean("myBean");
        myBean.execute();
    }
}
