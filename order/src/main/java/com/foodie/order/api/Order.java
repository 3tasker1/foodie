package com.foodie.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

  private final String uuid;

  private final String userUuid;

  private final String restaurantUuid;

  private final int cost;

  private OrderStatus orderStatus;

  @JsonCreator
  public Order(@JsonProperty("uuid") String uuid, @JsonProperty("userUuid") String userUuid,
                @JsonProperty("restaurantUuid") String restaurantUuid, @JsonProperty("cost") int cost,
                @JsonProperty("orderStatus") OrderStatus orderStatus) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.restaurantUuid = restaurantUuid;
    this.cost = cost;
    this.orderStatus = orderStatus;
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

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  @Override
  public String toString() {
    return "Order{" +
      "uuid='" + uuid + '\'' +
      ", userUuid='" + userUuid + '\'' +
      ", restaurantUuid='" + restaurantUuid + '\'' +
      ", cost=" + cost +
      ", orderStatus=" + orderStatus +
      '}';
  }
}
