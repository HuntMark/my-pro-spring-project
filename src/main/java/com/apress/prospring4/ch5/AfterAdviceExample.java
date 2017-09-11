package com.apress.prospring4.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {
    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();

        for (int x = 0; x < 10; x++) {
            try {
                long key = keyGenerator.getKey();
                System.out.println("Key: " + key);
            } catch (SecurityException ex) {
                System.out.println("Weak Key Generated!");
            }
        }
    }

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new WeakKeyCheckAdvice());

        return (KeyGenerator) factory.getProxy();
    }
}
