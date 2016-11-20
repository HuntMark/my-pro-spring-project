package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBeanWithInterface {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");

        if (this.filePath == null) {
            throw new IllegalArgumentException("You must specify the filePath property of "
                    + DestructiveBeanWithInterface.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + this.file.exists());
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("Destroying Bean");

        if (!this.file.delete()) {
            System.err.println("ERROR: failed to delete file.");
        }

        System.out.println("File exists: " + this.file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context-annotation.xml");
        context.refresh();

        DestructiveBeanWithInterface bean = (DestructiveBeanWithInterface) context.getBean("destructiveBean");
    }
}
