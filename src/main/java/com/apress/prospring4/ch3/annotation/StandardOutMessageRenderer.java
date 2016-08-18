package com.apress.prospring4.ch3.annotation;

import com.apress.prospring4.ch3.MessageProvider;
import com.apress.prospring4.ch3.MessageRenderer;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {
  private MessageProvider messageProvider;

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the property messageProvider of class: "
              + com.apress.prospring4.ch3.StandardOutMessageRenderer.class.getName());
    }

    System.out.println(messageProvider.getMessage());
  }

  /*
  Setter Injection via @Autowired (Spring)
  @Override
  @Autowired
  public void setMessageProvider(MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }
  */

  /*
  Setter Injection via @Resource (JSR-250)
  @Override
  @Resource(name = "messageProvider")
  public void setMessageProvider(MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }
  */

  // Setter Injection via @Inject (JSR-299)
  @Override
  @Inject
  public void setMessageProvider(MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }

  @Override
  public MessageProvider getMessageProvider() {
    return messageProvider;
  }
}
