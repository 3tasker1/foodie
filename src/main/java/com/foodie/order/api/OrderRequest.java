package com.foodie.order.api;

public class OrderRequest {

  private final String userUuid;

  private final String restaurantUuid;

  private final int cost;


  public OrderRequest(String userUuid, String restaurantUuid, int cost) {
    this.userUuid = userUuid;
    this.restaurantUuid = restaurantUuid;
    this.cost = cost;
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
    return "OrderRequest{" +
      "userUuid='" + userUuid + '\'' +
      ", restaurantUuid='" + restaurantUuid + '\'' +
      ", cost=" + cost +
      '}';
  }
}
