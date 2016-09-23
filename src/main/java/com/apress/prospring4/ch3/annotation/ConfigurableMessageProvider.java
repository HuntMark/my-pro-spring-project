package com.apress.prospring4.ch3.annotation;

import com.apress.prospring4.ch3.MessageProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageProvider")
public class ConfigurableMessageProvider implements MessageProvider {
  private String message;
  /*
  // Constructor Injection with @Value annotation
  @Autowired
  public ConfigurableMessageProvider(@Value("Configurable message (annotation)") String message) {
    this.message = message;
  }
  */

  // message parameter value defined in xml file
  @Autowired
  public ConfigurableMessageProvider(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
