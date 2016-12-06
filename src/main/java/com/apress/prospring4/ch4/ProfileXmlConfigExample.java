package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.stream.Collectors;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:*-config.xml");
        context.refresh();

        FoodProviderService service = context.getBean("foodProviderService", FoodProviderService.class);

        service.provideLunchSet()
                .stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
