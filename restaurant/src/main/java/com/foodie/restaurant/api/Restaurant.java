package com.foodie.restaurant.api;

public class Restaurant {

  private final String uuid;

  private final String name;

  public Restaurant(String uuid, String name) {
    this.uuid = uuid;
    this.name = name;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Restaurant{" +
      "uuid='" + uuid + '\'' +
      ", name='" + name + '\'' +
      '}';
  }
}
