package com.foodie.order.api;

public class Order {


  private final String uuid;

  private final String userUuid;

  private final int cost;

  private Order(String uuid, String userUuid, int cost) {
    this.uuid = uuid;
    this.userUuid = userUuid;
    this.cost = cost;
  }

  public static Order fromRequest(OrderRequest orderRequest, String uuid) {
    return new Order(uuid, orderRequest.getUserUuid(), orderRequest.getCost());
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

  @Override
  public String toString() {
    return "Order{" +
      "uuid='" + uuid + '\'' +
      ", userUuid='" + userUuid + '\'' +
      ", cost=" + cost +
      '}';
  }
}
