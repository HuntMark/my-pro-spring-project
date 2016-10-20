package com.apress.prospring4.ch3;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class FormatMessageReplacer implements MethodReplacer {
  @Override
  public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
    if (isFormatMessageMethod(method)) {
      String msg = (String) objects[0];
      return "<h2>" + msg + "</h2>";
    } else {
      throw new IllegalArgumentException("Unable to reimplement method " + method.getName());
    }
  }

  private boolean isFormatMessageMethod(Method method) {
    return method.getParameterTypes().length == 1
            && "formatMessage".equals(method.getName())
            && method.getReturnType() == String.class
            && method.getParameterTypes()[0] == String.class;

  }
}
