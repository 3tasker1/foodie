package com.foodie.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestForm {

  private final String userUuid;

  @JsonCreator
  public OrderRequestForm(@JsonProperty("UserUuid") String userUuid) {
    this.userUuid = userUuid;
  }

  public String getUserUuid() {
    return userUuid;
  }

  @Override
  public String toString() {
    return "OrderRequestForm{" +
      "userUuid='" + userUuid + '\'' +
      '}';
  }
}
