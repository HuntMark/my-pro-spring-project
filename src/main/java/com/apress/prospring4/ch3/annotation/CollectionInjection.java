package com.apress.prospring4.ch3.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

@Service("injectCollection")
public class CollectionInjection {

  @Resource(name = "map")
  private Map<String, Object> map;

  @Resource(name = "props")
  private Properties props;

  @Resource(name = "set")
  private Set set;

  @Resource(name = "list")
  private List list;

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:app-context-annotation.xml");
    context.refresh();

    CollectionInjection bean = (CollectionInjection) context.getBean("injectCollection");
    bean.displayInfo();
  }

  public void displayInfo() {
    System.out.println("Map contents:\n");

    for (Map.Entry<String, Object> entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + " - Value: " + entry.getValue());
    }

    System.out.println("\nProperties contents:\n");

    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      System.out.println("Key: " + entry.getKey() + " - Value: " + entry.getValue());
    }

    System.out.println("\nSet contents:\n");

    for (Object obj : set) {
      System.out.println("Value: " + obj);
    }

    System.out.println("\nList contents:\n");

    for (Object obj : list) {
      System.out.println("Value: " + obj);
    }
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }

  public Properties getProps() {
    return props;
  }

  public void setProps(Properties props) {
    this.props = props;
  }

  public Set getSet() {
    return set;
  }

  public void setSet(Set set) {
    this.set = set;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}
