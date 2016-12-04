package com.apress.prospring4.ch4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class ResourceDemo {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();

        File file = new File("test.txt");
        file.deleteOnExit();

        System.out.println(file.getPath());

//        Resource resource1 = context.getResource("file://" + file.getPath());
//        displayInfo(resource1);

        Resource resource2 = context.getResource("classpath:test.txt");
        displayInfo(resource2);

        Resource resource3 = context.getResource("http://www.google.com");
        displayInfo(resource3);
    }

    public static void displayInfo(Resource resource) throws IOException {
        System.out.println(resource.getClass());
        System.out.println(resource.getURL().getContent());
        System.out.println();
    }
}
