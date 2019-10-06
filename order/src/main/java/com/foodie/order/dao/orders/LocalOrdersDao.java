package com.foodie.order.dao.orders;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class LocalOrdersDao implements OrdersDao{

  private List<Order> orders = new ArrayList<>();


  @Override
  public Order saveOrder(OrderRequest orderRequest) {
    var newOrder = Order.fromRequest(orderRequest, UUID.randomUUID().toString());
    orders.add(newOrder);
    return newOrder;
  }

  @Override
  public List<Order> getOrders() {
    return Collections.unmodifiableList(orders);
  }
}
