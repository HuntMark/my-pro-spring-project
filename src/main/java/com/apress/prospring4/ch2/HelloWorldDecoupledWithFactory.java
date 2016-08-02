package com.apress.prospring4.ch2;

public class HelloWorldDecoupledWithFactory {
    public static void main(String[] args) {
        MessageRenderer renderer = MessageSupportFactory.getInstance().getRenderer();
        MessageProvider provider = MessageSupportFactory.getInstance().getProvider();
        renderer.setMessageProvider(provider);
        renderer.render();
    }
}
