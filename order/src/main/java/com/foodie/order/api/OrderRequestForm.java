package com.foodie.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestForm {

  private final String restaurantUuid;

  private final String userUuid;

  @JsonCreator
  public OrderRequestForm(@JsonProperty("UserUuid") String userUuid, @JsonProperty("restaurantUuid") String restaurantUuid) {
    this.userUuid = userUuid;
    this.restaurantUuid = restaurantUuid;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public String getRestaurantUuid() {
    return restaurantUuid;
  }

  @Override
  public String toString() {
    return "OrderRequestForm{" +
      "userUuid='" + userUuid + '\'' +
      ", restaurantUuid='" + restaurantUuid + '\'' +
      '}';
  }

}
