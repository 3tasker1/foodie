package com.foodie.order;

import io.dropwizard.Configuration;

public class OrderApplicationConfig extends Configuration {

  private String kafka;

  private String zookeeper;

  public OrderApplicationConfig() {
  }

  public String getKafka() {
    return kafka;
  }

  public void setKafka(String kafka) {
    this.kafka = kafka;
  }

  public String getZookeeper() {
    return zookeeper;
  }

  public void setZookeeper(String zookeeper) {
    this.zookeeper = zookeeper;
  }
}
