package com.foodie.order.api;

public class OrderRequest {

  private final String userUuid;

  private final int cost;


  public OrderRequest(String userUuid, int cost) {
    this.userUuid = userUuid;
    this.cost = cost;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public int getCost() {
    return cost;
  }

  @Override
  public String toString() {
    return "OrderRequest{" +
      "userUuid='" + userUuid + '\'' +
      ", cost=" + cost +
      '}';
  }
}
