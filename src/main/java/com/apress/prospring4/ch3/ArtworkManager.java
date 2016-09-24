package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ArtworkManager {
  private List<ArtworkSender> senders;

  public ArtworkManager(List<ArtworkSender> senders) {
    this.senders = senders;
  }

  public void displaySenders() {
    System.out.println("All Senders:\n");

    for (ArtworkSender sender : senders) {
      System.out.println("Sender: " + sender.getFriendlyName());
    }
  }

  public static void main(String[] args) {
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:sender.xml");
    context.refresh();

    ArtworkManager manager = (ArtworkManager) context.getBean("artworkManager");
    manager.displaySenders();
  }
}
