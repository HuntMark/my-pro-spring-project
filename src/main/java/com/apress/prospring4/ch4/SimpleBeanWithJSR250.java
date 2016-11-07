package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

public class SimpleBeanWithJSR250 {
    private static final String DEFAULT_NAME = "Luke Skywalker";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing bean");

        if (this.name == null) {
            System.out.println("Using default name");
            this.name = DEFAULT_NAME;
        }

        if (this.age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type "
                    + SimpleBeanWithJSR250.class);
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

        SimpleBeanWithJSR250 simpleBean1 = getBean("simpleBean1", context);
        SimpleBeanWithJSR250 simpleBean2 = getBean("simpleBean2", context);
        SimpleBeanWithJSR250 simpleBean3 = getBean("simpleBean3", context);
    }

    private static SimpleBeanWithJSR250 getBean(String beanName, ApplicationContext context) {
        try {
            SimpleBeanWithJSR250 bean = (SimpleBeanWithJSR250) context.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occured in bean configuration: " + ex.getMessage());
            return null;
        }
    }
}
