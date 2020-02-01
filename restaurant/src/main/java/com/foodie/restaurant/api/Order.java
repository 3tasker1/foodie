package com.foodie.restaurant.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

  private final String uuid;

  private final String userUuid;

  private final String restaurantUuid;

  private final int cost;

  @JsonCreator
  private Order(@JsonProperty("uuid") String uuid, @JsonProperty("userUuid") String userUuid,
                @JsonProperty("restaurantUuid") String restaurantUuid, @JsonProperty("cost") int cost) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.restaurantUuid = restaurantUuid;
    this.cost = cost;
  }

  public String getUuid() {
    return uuid;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public int getCost() {
    return cost;
  }

  public String getRestaurantUuid() {
    return restaurantUuid;
  }

  @Override
  public String toString() {
    return "Order{" +
      "uuid='" + uuid + '\'' +
      ", userUuid='" + userUuid + '\'' +
      ", restaurantUuid='" + restaurantUuid + '\'' +
      ", cost=" + cost +
      '}';
  }
}
