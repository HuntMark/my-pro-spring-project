package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBeanWithInterface implements InitializingBean {
    public static final String DEFAULT_NAME = "Luke Skywalker";

    private String name;
    private Integer age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void myInit() {
        System.out.println("My Init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");

        if (name == null) {
            System.out.println("Using default name");
            this.name = DEFAULT_NAME;
        }

        if (this.age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("You must set the age property of any beans of type "
                    + SimpleBeanWithInterface.class);
        }
    }

    @Override
    public String toString() {
        return "SimpleBeanWithInterface{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();

        SimpleBeanWithInterface simpleBean1 = getBean("simpleBean1", ctx);
        SimpleBeanWithInterface simpleBean2 = getBean("simpleBean2", ctx);
        SimpleBeanWithInterface simpleBean3 = getBean("simpleBean3", ctx);
    }

    private static SimpleBeanWithInterface getBean(String beanName, ApplicationContext ctx) {
        try {
            SimpleBeanWithInterface bean = (SimpleBeanWithInterface) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("An error occured in bean configuration: " + ex.getMessage());
            return null;
        }
    }
}
