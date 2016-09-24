package com.apress.prospring4.ch3;

public class Recipient {
  private String name;

  public Recipient(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
