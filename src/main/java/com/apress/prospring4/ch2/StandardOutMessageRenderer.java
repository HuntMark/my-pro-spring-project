package com.apress.prospring4.ch2;

public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider provider;

    @Override
    public void render() {
        if (getMessageProvider() == null) {
            throw new RuntimeException("You must set the property messageProvider of class: " + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(getMessageProvider().getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.provider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.provider;
    }
}
