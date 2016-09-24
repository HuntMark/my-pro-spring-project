package com.apress.prospring4.ch3;

public class ScpArtworkSender implements ArtworkSender {
  @Override
  public void sendArtwork(String artworkPath, Recipient recipient) {
    System.out.println("artworkPath: " + artworkPath + " - recipient: " + recipient);
  }

  @Override
  public String getFriendlyName() {
    return "Secure Copy Protocol";
  }

  @Override
  public String getShortName() {
    return "SCP";
  }
}
