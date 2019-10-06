package com.foodie.restaurant;

import io.dropwizard.Configuration;

public class RestaurantApplicationConfig extends Configuration {

  private String kafka;

  private String zookeeper;

  public RestaurantApplicationConfig() {
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
