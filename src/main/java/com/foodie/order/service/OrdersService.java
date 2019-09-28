package com.foodie.order.service;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequest;
import com.foodie.order.dao.orders.OrdersDao;

import java.util.List;
import java.util.UUID;

public class OrdersService {

  private final OrdersDao ordersDao;


  public OrdersService(OrdersDao ordersDao) {
    this.ordersDao = ordersDao;
  }


  public List<Order> getOrders() {
    return ordersDao.getOrders();
  }

  public Order newOrder(String userUuid) {

    var orderRequest = new OrderRequest(userUuid, 2000);
    return ordersDao.saveOrder(orderRequest);

  }

}
