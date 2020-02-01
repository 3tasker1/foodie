package com.foodie.order.dao.orders;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequest;

import java.util.*;

public class LocalOrdersDao implements OrdersDao{

  private List<Order> orders = new ArrayList<>();


  @Override
  public void saveOrder(Order order) {
    orders.remove(order);
    orders.add(order);
  }

  @Override
  public List<Order> getOrders() {
    return Collections.unmodifiableList(orders);
  }

  @Override
  public Optional<Order> findOrder(String uuid) {
    return orders.stream()
      .filter(order -> order.getUuid().equals(uuid))
      .findFirst();
  }
}
