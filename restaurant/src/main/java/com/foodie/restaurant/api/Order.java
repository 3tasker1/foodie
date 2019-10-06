package com.foodie.restaurant.api;

public class Order {

  private final String uuid;

  private final String userUuid;

  private final String restaurantUuid;

  private final int cost;

  private Order(String uuid, String userUuid, String restaurantUuid, int cost) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.restaurantUuid = restaurantUuid;
    this.cost = cost;
  }

  public static Order fromRequest(OrderRequest orderRequest, String uuid) {
    return new Order(uuid, orderRequest.getUserUuid(), orderRequest.getRestaurantUuid(), orderRequest.getCost());
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
