package com.apress.prospring4.ch5;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class NamePointcutUsingAdvisor {
    public static void main(String[] args) {
        NameBean target = new NameBean();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.addMethodName("foo");
        advisor.addMethodName("bar");

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        NameBean proxy = (NameBean) pf.getProxy();

        proxy.foo();
        proxy.foo(999);
        proxy.bar();
        proxy.yup();
    }
}
