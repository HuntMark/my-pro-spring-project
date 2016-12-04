package com.apress.prospring4.ch4;

import org.springframework.context.ApplicationListener;

public class MessageEventListener implements ApplicationListener<MessageEvent> {
    public MessageEventListener() {
        System.out.println("MessageEventListener is created.");
    }

    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(String.format("Received: %s", event.getMessage()));
    }
}
