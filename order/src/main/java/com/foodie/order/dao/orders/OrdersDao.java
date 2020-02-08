package com.foodie.order.dao.orders;

import com.foodie.order.api.Order;

import java.util.List;
import java.util.Optional;

public interface OrdersDao {

  void saveOrder(Order order);

  List<Order> getOrders();

  Optional<Order> findOrder(String uuid);

}
